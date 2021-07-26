package orange.com.br.mercadolivre.configuracao.validacao.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoPersonalizada extends RuntimeException {

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ExcecaoPersonalizada(String mensagem, HttpStatus httpStatus)
    {
        super(mensagem);
        this.httpStatus = httpStatus;

    }

}
