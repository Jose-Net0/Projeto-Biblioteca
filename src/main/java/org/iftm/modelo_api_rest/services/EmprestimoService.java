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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ItemEmprestimoRepository itemEmprestimoRepository;

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> findById(Long id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo save(Emprestimo emprestimo) {
        // Validação: usuário deve existir e não estar bloqueado
        if (emprestimo == null) {
            return null;
        }
        Usuario usuario = emprestimo.getUsuario();
        if (usuario == null || usuario.getBloqueio() != null) {
            return null;
        }
        // Validação: data de empréstimo não pode ser futura
        if (emprestimo.getDataEmprestimo().after(new Date())) {
            return null;
        }
        return emprestimoRepository.save(emprestimo);
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