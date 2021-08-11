package orange.com.br.mercadolivre.pagamentos.formaspagamento;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@Component
public class Paypal implements Financeiro<Integer> {

    @Override
    public String pagar(String idCompra) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/retorno-paypal/{id}").buildAndExpand(idCompra).toUri();
        return "paypal.com/buyerId=" + idCompra + "?redirectUrl=" + uri;
    }

    @Override
    public Integer processarTransacao(String idCompra) {
        if (idCompra.startsWith("3") || idCompra.startsWith("f")) {
            return 0;
        }

        return 1;
    }
}
