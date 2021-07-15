package orange.com.br.mercadolivre.produtos.perguntas.emails;

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

}
