package orange.com.br.mercadolivre.usuarios.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.unico.UnicoValid;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioRequest {

    @Email
    @NotBlank
    @NotNull
    @UnicoValid(entidade = Usuario.class, campo = "login" )
    private String login;

    @NotBlank
    @NotNull
    @Length(min = 6)
    private String senha;

    @Deprecated
    private UsuarioRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login, this.senha);
    }

}
