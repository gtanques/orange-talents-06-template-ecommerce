package orange.com.br.mercadolivre.emails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    /**
     * @param corpoEmail
     * @param assunto
     * @param origem
     * @param destino
     */
    void enviar(@NotBlank String corpoEmail, @NotBlank String assunto, @NotBlank @Email String origem, @NotBlank @Email String destino);


    /**
     * @param assunto
     * @param corpoEmail
     * @param linkProduto
     * @param destino
     */
    void enviarAvisoTransacaoRecusada(String assunto, String corpoEmail, String linkProduto, String destino);

    /**
     *
     * @param assunto
     * @param sku
     * @param produto
     * @param qntd
     * @param valor
     * @param tipoPagamento
     */
    void enviarAvisoTransacaoEfetuada(String assunto, String sku, String produto, String qntd, String valor, String tipoPagamento);
}
