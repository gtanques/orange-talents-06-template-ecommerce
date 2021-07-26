package orange.com.br.mercadolivre.emails;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta){
        System.out.println("novaPergunta");

        mailer.send("<html> . . . </html>", pergunta.getPergunta(), pergunta.getUsuario().getEmail(), pergunta.getProduto().getUsuario().getEmail());
    }

    public void desejoDeCompra(@NotNull @Valid Compra compra){
        System.out.println("desejoDeCompra");
        mailer.send("<html> . . . </html>",
                compra.getProduto().getNome(),
                compra.getComprador().getEmail(),
                compra.getProduto().getUsuario().getEmail());
    }

}
