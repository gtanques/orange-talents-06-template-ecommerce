package orange.com.br.mercadolivre.usuarios.repository;

import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
