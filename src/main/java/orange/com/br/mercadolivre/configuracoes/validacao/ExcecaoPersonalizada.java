package orange.com.br.mercadolivre.configuracoes.validacao;

import org.springframework.http.HttpStatus;

public class ExcecaoPersonalizada extends RuntimeException {

    private HttpStatus httpStatus;

    public ExcecaoPersonalizada(String mensagem, HttpStatus httpStatus) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
