package orange.com.br.mercadolivre.configuracao.seguranca.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenRequest {

    private String login;
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TokenRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }

}
