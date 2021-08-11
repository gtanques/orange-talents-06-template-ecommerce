package orange.com.br.mercadolivre.pagamento.integracoes;

import orange.com.br.mercadolivre.compras.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Integracao {
    public void processaNotaFiscal(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }

    public void gerarPontuacaoRank(Compra compra){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getUsuario().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}
