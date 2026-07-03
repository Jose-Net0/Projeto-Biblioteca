package org.iftm.modelo_api_rest.services;

import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.entities.Livro;
import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.repositories.EmprestimoRepository;
import org.iftm.modelo_api_rest.repositories.ItemEmprestimoRepository;
import org.iftm.modelo_api_rest.repositories.LivroRepository;
import org.iftm.modelo_api_rest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    // Serviço principal para registro e gerenciamento de empréstimos
    // Implementa regras de negócio: disponibilidade, limites, prazos e geração de comprovantes

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    private static final int MAX_ACTIVE_LOANS = 3;
    private static final int DEFAULT_LOAN_DAYS = 7;
    private static final String BOOK_STATUS_AVAILABLE = "Disponível";
    private static final String BOOK_STATUS_EMPRESTADO = "Emprestado";
    private static final String BOOK_STATUS_RESERVED = "Reservado";
    private static final String BOOK_STATUS_MAINTENANCE = "Em manutenção";

    public List<Emprestimo> findAll() {
        // Retorna todos os empréstimos registrados
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> findById(Long id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> findByUsuarioId(Long usuarioId) {
        return emprestimoRepository.findByUsuarioCodigoUsuario(usuarioId);
    }

    public List<Emprestimo> findByCodigoEmprestimo(Long codigoEmprestimo) {
        return emprestimoRepository.findByCodigoEmprestimo(codigoEmprestimo);
    }

    public List<Emprestimo> findByItemCodigoItemEmprestimo(Long codigoItemEmprestimo) {
        return emprestimoRepository.findByItemEmprestimoCodigoItemEmprestimo(codigoItemEmprestimo);
    }

    public List<Emprestimo> findByDataEmprestimo(Date dataEmprestimo) {
        return emprestimoRepository.findByDataEmprestimo(dataEmprestimo);
    }

    public List<Emprestimo> findByDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        return emprestimoRepository.findByDataDevolucaoPrevista(dataDevolucaoPrevista);
    }

    public Emprestimo save(Emprestimo emprestimo) {
        // Salva empréstimo simples com validações básicas
        // Validação: objeto não nulo e usuário presente
        if (emprestimo == null) {
            return null;
        }
        Usuario usuario = emprestimo.getUsuario();
        if (usuario == null || usuario.getBloqueio() != null) {
            return null;
        }
        // Validação: data de empréstimo não pode ser no futuro
        if (emprestimo.getDataEmprestimo().after(new Date())) {
            return null;
        }
        // Persiste o empréstimo
        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public String registrarEmprestimo(String matricula, String cpf, String codigoLivro, String nomeBibliotecario) {
        // Registra um novo empréstimo seguindo as regras de negócio (RN01..RN06)
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula do aluno é obrigatória.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do aluno é obrigatório.");
        }
        if (codigoLivro == null || codigoLivro.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do livro é obrigatório.");
        }
        if (nomeBibliotecario == null || nomeBibliotecario.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do bibliotecário responsável é obrigatório.");
        }

        Usuario usuario = findUsuarioByMatriculaAndCpf(matricula, cpf);
        // Valida se o aluno existe e está apto para empréstimos
        validateUserForLoan(usuario);

        Livro livro = findLivroByCodigoTexto(codigoLivro);
        // Valida se o livro existe e está disponível
        validateBookForLoan(livro);

        long overdueLoans = itemEmprestimoRepository.countOverdueLoansByUsuarioId(usuario.getCodigoUsuario());
        if (overdueLoans > 0) {
            throw new IllegalStateException("Aluno com empréstimo em atraso ou bloqueio ativo. Empréstimo não permitido.");
        }

        long activeLoans = itemEmprestimoRepository.countActiveLoansByUsuarioId(usuario.getCodigoUsuario());
        if (activeLoans >= MAX_ACTIVE_LOANS) {
            throw new IllegalStateException("Cada usuário pode ter no máximo 3 livros emprestados simultaneamente.");
        }

        Date dataEmprestimo = new Date();
        Date dataPrevista = addBusinessDays(dataEmprestimo, DEFAULT_LOAN_DAYS);

        // Atualiza disponibilidade do exemplar: decrementa quantidade e marca como emprestado
        livro.setQuantidadeExemplares(livro.getQuantidadeExemplares() - 1);
        livro.setStatus(BOOK_STATUS_EMPRESTADO);
        livroRepository.save(livro);

        // Cria e persiste o registro de empréstimo com bibliotecário responsável
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucaoPrevista(dataPrevista);
        emprestimo.setBibliotecarioResponsavel(nomeBibliotecario);
        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);

        // Criar item de empréstimo relacionado ao empréstimo salvo
        ItemEmprestimo item = new ItemEmprestimo();
        item.setEmprestimo(savedEmprestimo);
        item.setDataDevolucaoPrevista(dataPrevista);
        item.setDataDevolucaoReal(null);
        item.setStatus(BOOK_STATUS_EMPRESTADO);
        item.setMultaGerada(0.0);
        itemEmprestimoRepository.save(item);

        // Registra histórico e retorna texto do comprovante
        registrarHistoricoEmprestimo(usuario, livro, savedEmprestimo);
        return gerarComprovante(usuario, livro, savedEmprestimo);
    }

    private Usuario findUsuarioByMatriculaAndCpf(String matricula, String cpf) {
        return usuarioRepository.findByMatricula(matricula).stream()
                .filter(u -> cpf.equals(u.getCpf()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    private Livro findLivroByCodigoTexto(String codigoLivro) {
        try {
            Long livroId = Long.valueOf(codigoLivro);
            return livroRepository.findById(livroId).orElseThrow(() -> new RuntimeException("Livro não encontrado."));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Código do livro inválido.");
        }
    }

    private void validateUserForLoan(Usuario usuario) {
        // Validações relacionadas ao usuário antes de permitir empréstimo
        if (usuario == null) {
            throw new RuntimeException("Aluno não encontrado.");
        }
        if (usuario.getStatus() == null || !usuario.getStatus().equalsIgnoreCase("ATIVO")) {
            throw new IllegalStateException("Aluno inativo não pode realizar empréstimos.");
        }
        if (usuario.getBloqueio() != null) {
            throw new IllegalStateException("Aluno com bloqueio ativo. Empréstimo não permitido.");
        }
    }

    private void validateBookForLoan(Livro livro) {
        // Validações relacionadas ao exemplar antes de ser emprestado
        if (livro == null) {
            throw new RuntimeException("Livro não encontrado.");
        }
        if (livro.getQuantidadeExemplares() <= 0) {
            throw new IllegalStateException("Livro indisponível para empréstimo.");
        }
        String status = livro.getStatus();
        if (status == null || !status.equalsIgnoreCase(BOOK_STATUS_AVAILABLE)) {
            if (status != null && (status.equalsIgnoreCase(BOOK_STATUS_RESERVED) || status.equalsIgnoreCase(BOOK_STATUS_MAINTENANCE) || status.equalsIgnoreCase(BOOK_STATUS_EMPRESTADO))) {
                throw new IllegalStateException("Livro indisponível para empréstimo.");
            }
            throw new IllegalStateException("Livro com status inválido para empréstimo.");
        }
    }

    private Date addBusinessDays(Date date, int businessDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int addedDays = 0;
        while (addedDays < businessDays) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                addedDays++;
            }
        }
        return calendar.getTime();
    }

    private String gerarComprovante(Usuario usuario, Livro livro, Emprestimo emprestimo) {
        return String.format(
                "COMPROVANTE DE EMPRÉSTIMO:\nAluno: %s\nMatrícula: %s\nCPF: %s\nLivro: %s\nCódigo do livro: %d\nData do empréstimo: %tF\nData prevista de devolução: %tF\nBibliotecário responsável: %s\nCódigo do empréstimo: %d",
                usuario.getNome(), usuario.getMatricula(), usuario.getCpf(), livro.getTitulo(), livro.getCodigo(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucaoPrevista(), emprestimo.getBibliotecarioResponsavel(), emprestimo.getCodigoEmprestimo());
    }

    private void registrarHistoricoEmprestimo(Usuario usuario, Livro livro, Emprestimo emprestimo) {
        System.out.printf("HISTÓRICO: Empréstimo %d registrado para aluno %s (matrícula %s) do livro %s (código %d) pelo bibliotecário %s.%n",
                emprestimo.getCodigoEmprestimo(), usuario.getNome(), usuario.getMatricula(), livro.getTitulo(), livro.getCodigo(), emprestimo.getBibliotecarioResponsavel());
    }

    public void deleteById(Long id) {
        emprestimoRepository.deleteById(id);
    }

    // Método para emprestar livros: negócio complexo
    public Emprestimo emprestarLivros(Long usuarioId, List<Long> livroIds) {
        if (usuarioId == null || livroIds == null || livroIds.isEmpty()) {
            return null;
        }

        // Buscar usuário
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null || usuario.getBloqueio() != null) {
            return null;
        }

        // Verificar disponibilidade dos livros
        for (Long livroId : livroIds) {
            Livro livro = livroRepository.findById(livroId).orElse(null);
            if (livro == null || livro.getQuantidadeExemplares() <= 0) {
                return null;
            }
        }

        // Criar empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(new Date());
        // Data devolução prevista: 14 dias
        Date dataPrevista = new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000);
        emprestimo.setDataDevolucaoPrevista(dataPrevista);

        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);

        // Criar itens de empréstimo e decrementar quantidade
        for (Long livroId : livroIds) {
            Livro livro = livroRepository.findById(livroId).orElse(null);
            if (livro == null) {
                return null;
            }
            livro.setQuantidadeExemplares(livro.getQuantidadeExemplares() - 1);
            livroRepository.save(livro);

            ItemEmprestimo item = new ItemEmprestimo();
            item.setEmprestimo(savedEmprestimo);
            item.setDataDevolucaoPrevista(dataPrevista);
            item.setStatus("Emprestado");
            itemEmprestimoRepository.save(item);
        }

        return savedEmprestimo;
    }
}