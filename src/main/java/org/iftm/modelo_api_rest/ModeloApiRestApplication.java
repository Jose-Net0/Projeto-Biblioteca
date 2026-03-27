package org.iftm.modelo_api_rest;

import org.iftm.modelo_api_rest.entities.Usuario;
import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.repostories.UsuarioRepository;
import org.iftm.modelo_api_rest.repostories.EmprestimoRepository;
import org.iftm.modelo_api_rest.repostories.ItemEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ItemEmprestimoRepository itemEmprestimoRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	public static void main(String[] args) {
	SpringApplication.run(ApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
        Usuario u1 = new Usuario(null, "Maltida Santana", "MaltiSa@email.com");
        usuarioRepository.save(u1);

        
        Emprestimo e1 = new Emprestimo(null, Instant.now(), u1);
        emprestimoRepository.save(e1);

        ItemEmprestimo it1 = new ItemEmprestimo(null, 2, 50.0, e1);
        ItemEmprestimo it2 = new ItemEmprestimo(null, 1, 120.0, e1);
        itemEmprestimoRepository.saveAll(Arrays.asList(it1, it2));

	}
}
