package org.iftm.modelo_api_rest;

import org.iftm.modelo_api_rest.entities.Usuario;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModeloApiRestApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ItemEmprestimoRepository itemEmprestimoRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	public static void main(String[] args) {
	SpringApplication.run(ModeloApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
        Usuario u1 = new Usuario(null, "Maltida Santana", "000.000.000-00", "20241234", "MaltiSa@email.com", "senha123","ESTUDANTE");
        usuarioRepository.save(u1);


        
        Emprestimo e1 = new Emprestimo(null,Date.from(Instant.now()), Date.from(Instant.now()));
        emprestimoRepository.save(e1);

        ItemEmprestimo it1 = new ItemEmprestimo(null, Date.from(Instant.now()), Date.from(Instant.now()), "Devolido", 0.0);
        ItemEmprestimo it2 = new ItemEmprestimo(null, Date.from(Instant.now()), Date.from(Instant.now()), "Devolido", 1.0);
        itemEmprestimoRepository.saveAll(Arrays.asList(it1, it2));

	
		Long codigo = 3L;

		Optional<Usuario> retorno = usuarioRepository.findById(codigo);
		if (retorno.isEmpty()){
			System.out.println("Não existe usuário com código " + codigo + ".");
		}else{
			Usuario usu = retorno.get();
			System.out.println(usu.getNome());			
		}

		System.out.println("\n-----------------------------------------");

        
        System.out.println("-> Listando todos os Usuários cadastrados:");
        Iterable<Usuario> todosUsuarios = usuarioRepository.findAll();
        for (Usuario u : todosUsuarios) {
            System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail());
        }

        System.out.println("\n-----------------------------------------");

        // 4. EXCLUSÃO (deleteById)
        System.out.println("-> Testando a Exclusão:");
        Long idParaExcluir = 1L; // Excluindo o ItemEmprestimo de ID 1
        if (itemEmprestimoRepository.existsById(idParaExcluir)) {
            itemEmprestimoRepository.deleteById(idParaExcluir);
            System.out.println("Item de Empréstimo com ID " + idParaExcluir + " foi excluído com sucesso.");
        } else {
            System.out.println("Item de Empréstimo não encontrado para exclusão.");
        }

        System.out.println("\n-----------------------------------------");

        System.out.println("-> Testando QueryMethod (Buscar usuários por Perfil 'ESTUDANTE'):");
        try {
            System.out.println("Lembre-se de descomentar e chamar os métodos do seu Repository aqui!");
        } catch (Exception e) {
            System.out.println("Erro ao testar QueryMethod: " + e.getMessage());
        }
		

	}
}
