package orange.com.br.mercadolivre.pagamento.eventosfinalizarcompra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas-fiscais")
public class GerarNotaFiscalController {

    @PostMapping
    public void gerarNF(@RequestBody GerarNotaFiscalRequest request) throws InterruptedException {
        System.out.println("Gerando NF para " + request.getIdCompra() + " do comprador " + request.getIdComprador());
        Thread.sleep(100);
    }

}
