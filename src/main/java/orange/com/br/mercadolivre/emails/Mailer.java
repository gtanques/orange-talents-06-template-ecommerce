package orange.com.br.mercadolivre.emails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    void enviar(@NotBlank String corpoEmail, @NotBlank String assunto, @NotBlank @Email String origem, @NotBlank @Email String destino);

    void enviarAvisoTransacaoRecusada(String assunto, String corpoEmail, String linkProduto, String destino);

    void enviarAvisoTransacaoEfetuada(String assunto, String sku, String produto, String qntd, String valor, String tipoPagamento);

}
