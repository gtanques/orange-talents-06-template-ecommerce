package orange.com.br.mercadolivre.emails;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.pagamentos.transacoes.Transacao;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    private final Mailer mailer;

    @Autowired
    public Emails(Mailer mailer) {
        this.mailer = mailer;
    }

    public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
        System.out.println("novaPergunta");

        mailer.enviar("<html> . . . </html>", pergunta.getPergunta(),
                pergunta.getUsuario().getEmail(),
                pergunta.getProduto().getUsuario().getEmail());
    }

    public void desejoDeCompra(@NotNull @Valid Compra compra) {
        System.out.println("desejoDeCompra");
        mailer.enviar("<html> . . . </html>",
                compra.getProduto().getNome(),
                compra.getComprador().getEmail(),
                compra.getProduto().getUsuario().getEmail());
    }

    public void avisoTransacaoRecusada(@NotNull @Valid Transacao transacao) {
        System.out.println("avisoTransacaoRecusada");
        mailer.enviarAvisoTransacaoRecusada("Pagamento Recusado",
                "Falha ao processar o pagamento do produto " +
                        transacao.getCompra().getProduto().getNome(),
                "Tente novamente: http://localhost:8080/produtos/detalhe/" +
                        transacao.getCompra().getId(),
                transacao.getCompra().getComprador().getEmail());
    }

    public void transacaoEfetuada(@NotNull @Valid Transacao transacao) {
        System.out.println("avisoTransacaoEfetuada");
        mailer.enviarAvisoTransacaoEfetuada("Compra efetuada com sucesso!",
                "SKU:" + transacao.getCompra().getProduto().getId(),
                "Produto:" + transacao.getCompra().getProduto().getNome(),
                "Quantidade adquirida:" + transacao.getCompra().getQuantidade(),
                "Valor total:" + transacao.getCompra().getValorTotal(),
                "Forma de pagamento:" + transacao.getGateway().toString());
    }

}
