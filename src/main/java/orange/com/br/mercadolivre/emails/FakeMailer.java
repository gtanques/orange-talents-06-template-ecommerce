package orange.com.br.mercadolivre.emails;

import org.springframework.stereotype.Component;

@Component
public class FakeMailer implements Mailer {

    @Override
    public void enviar(String corpoEmail, String assunto, String origem, String destino) {
        System.out.println(assunto);
        System.out.println(corpoEmail);
        System.out.println(origem);
        System.out.println(destino);
    }

    @Override
    public void enviarAvisoTransacaoRecusada(String assunto, String corpoEmail, String linkProduto, String destino) {
        System.out.println(assunto);
        System.out.println(corpoEmail);
        System.out.println(linkProduto);
        System.out.println(destino);
    }

    @Override
    public void enviarAvisoTransacaoEfetuada(String assunto, String sku, String produto, String quantidade, String valor, String tipoPagamento) {
        System.out.println(assunto);
        System.out.println(sku);
        System.out.println(produto);
        System.out.println(quantidade);
        System.out.println(valor);
        System.out.println(tipoPagamento);
    }

}
