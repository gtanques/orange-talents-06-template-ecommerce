package orange.com.br.mercadolivre.usuarios;

import orange.com.br.mercadolivre.usuarios.util.CodificaSenhaLimpa;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, updatable = false)
    Instant dataCriacao = Instant.now();

    @Deprecated
    private Usuario() {
    }

    public Usuario(@NotNull @NotBlank String login, @NotNull @NotBlank String senha) {
        this.login = login;
        this.senha = CodificaSenhaLimpa.hash(senha);
    }

}
