package orange.com.br.mercadolivre.pagamentos.eventosfinalizarcompra;

import orange.com.br.mercadolivre.compras.Compra;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GerarPontuacaoRanking implements ProcessarFechamentoCompra {

    @Override
    public void processar(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }

}
