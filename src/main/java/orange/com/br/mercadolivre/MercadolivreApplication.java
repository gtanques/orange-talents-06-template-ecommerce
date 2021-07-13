package orange.com.br.mercadolivre;

import orange.com.br.mercadolivre.usuarios.Usuario;
import orange.com.br.mercadolivre.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MercadolivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolivreApplication.class, args);
	}

	@Bean
	public CommandLineRunner criarUsuario(UsuarioRepository usuarioRepository){
		return (args -> {
			System.out.println("[COMMAND-LINE] - Criando usuários no H2");
			usuarioRepository.save(new Usuario("naruto@gmail.com", "789456"));
		});
	}

}
