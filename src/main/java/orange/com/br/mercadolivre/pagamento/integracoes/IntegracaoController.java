package orange.com.br.mercadolivre.pagamento.integracoes;

import orange.com.br.mercadolivre.pagamento.integracoes.dto.GerarNotaFiscalRequest;
import orange.com.br.mercadolivre.pagamento.integracoes.dto.GerarRankingRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class IntegracaoController {

    @PostMapping("/notas-fiscais")
    public void gerarNF(@RequestBody GerarNotaFiscalRequest request) throws InterruptedException {
        System.out.println("Gerando NF para " + request.getIdCompra() + " do comprador " + request.getIdComprador());
        Thread.sleep(100);
    }

    @PostMapping("/ranking")
    public void gerarPontuacao(@RequestBody GerarRankingRequest request) throws InterruptedException {
        System.out.println("Adicionando pontuação da compra " + request.getIdCompra() + " para o vendedor " + request.getIdVendedor());
        Thread.sleep(100);
    }

}
