package orange.com.br.mercadolivre.pagamento.formaspagamento;

import orange.com.br.mercadolivre.pagamento.Financeiro;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Component
public class Paypal implements Financeiro {

    @Override
    public String pagar(Long idCompra) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/retorno-paypal/{id}").buildAndExpand(idCompra).toUri();
        return "paypal.com/buyerId=" + idCompra + "?redirectUrl="+ uri.toString();
    }

}
