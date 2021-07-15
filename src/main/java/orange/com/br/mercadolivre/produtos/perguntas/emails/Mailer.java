package orange.com.br.mercadolivre.produtos.perguntas.emails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    /**
     * @param body | corpo e-mail
     * @param subject | assunto
     * @param nameFrom | nome provedor
     * @param from | origem
     * @param to | destino
     */
    void send(@NotBlank String body, @NotBlank String subject, @NotBlank @Email String from, @NotBlank @Email String to);

}
