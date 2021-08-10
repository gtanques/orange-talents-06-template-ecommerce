package orange.com.br.mercadolivre.pagamento.formaspagamento;

import orange.com.br.mercadolivre.pagamento.Financeiro;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class PagSeguro implements Financeiro<String> {

    @Override
    public String pagar(String idCompra) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/retorno-pagseguro/{id}").buildAndExpand(idCompra).toUri();
        return "pagseguro.com/returnId=" + idCompra + "?redirectUrl=" + uri.toString();
    }

    @Override
    public String processarTransacao(String idCompra) {
        if(idCompra.startsWith("3")){
            return "Erro";
        }

        return "Sucesso";
    }

}
