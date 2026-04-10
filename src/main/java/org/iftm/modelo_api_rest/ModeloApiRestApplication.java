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

		

	}
}
