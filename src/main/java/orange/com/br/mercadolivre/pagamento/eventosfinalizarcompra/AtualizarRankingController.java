package orange.com.br.mercadolivre.pagamento.eventosfinalizarcompra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class AtualizarRankingController {

    @PostMapping
    public void gerarPontuacao(@RequestBody GerarRankingRequest request) throws InterruptedException {
        System.out.println("Adicionando pontuação da compra " + request.getIdCompra() + " para o vendedor " + request.getIdVendedor());
        Thread.sleep(100);
    }

}
