package org.iftm.modelo_api_rest;

import org.iftm.modelo_api_rest.entities.Usuario;

import java.util.List;
import java.util.Optional;

import org.iftm.modelo_api_rest.entities.Emprestimo;
import org.iftm.modelo_api_rest.entities.ItemEmprestimo;
import org.iftm.modelo_api_rest.services.EmprestimoService;
import org.iftm.modelo_api_rest.services.ItemEmprestimoService;
import org.iftm.modelo_api_rest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModeloApiRestApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemEmprestimoService itemEmprestimoService;

	@Autowired
	private EmprestimoService emprestimoService;

	public static void main(String[] args) {
	SpringApplication.run(ModeloApiRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
        Long codigo = 1L; 
        Optional<Usuario> retorno = usuarioService.findById(codigo);

        if (retorno.isEmpty()){
            System.out.println("Não existe usuário com código " + codigo + ".");
        } else {
            Usuario usu = retorno.get();
            System.out.println("Usuário encontrado pelo ID: " + usu.getNome() + " - " + usu.getTipoUsuario());
        
        }

		long codigoEmprestimo = 3L;
		Optional<Emprestimo> retornoEmprestimo = emprestimoService.findById(codigoEmprestimo);

		if (retornoEmprestimo.isEmpty()){
			System.out.println("Não existe empréstimo com código " + codigoEmprestimo + ".");
		} else {
			Emprestimo emprestimo = retornoEmprestimo.get();
			System.out.println("Empréstimo encontrado pelo ID: " + emprestimo.getDataEmprestimo() + " - " + emprestimo.getUsuario().getNome());
		
		}

		
		Long codigoItemEmprestimo = 4L; 
		Optional<ItemEmprestimo> retornoItemEmprestimo = itemEmprestimoService.findById(codigoItemEmprestimo);

		if (retornoItemEmprestimo.isEmpty()){
			System.out.println("Não existe item de empréstimo com código " + codigoItemEmprestimo + ".");
		} else {
			ItemEmprestimo itemEmprestimo = retornoItemEmprestimo.get();
			System.out.println("Item de Empréstimo encontrado pelo ID: " + itemEmprestimo.getStatus() + " - " + itemEmprestimo.getDataDevolucaoPrevista());
		
		}

		String cpf = "456.789.123-45";
		List<ItemEmprestimo> itensPorCpf = itemEmprestimoService.buscarItensPorCpfUsuario(cpf);
		for (ItemEmprestimo itemEmprestimo : itensPorCpf) {
			System.out.println("Item de Empréstimo encontrado pelo ID: " + itemEmprestimo.getStatus() + " - " + itemEmprestimo.getDataDevolucaoPrevista());
					
		}
			

}
	

}
