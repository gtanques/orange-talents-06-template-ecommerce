package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.configuracoes.validacao.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.emails.Emails;
import orange.com.br.mercadolivre.compras.eventosfinalizarcompra.GerarNotaFiscal;
import orange.com.br.mercadolivre.compras.eventosfinalizarcompra.GerarPontuacaoRanking;
import orange.com.br.mercadolivre.pagamentos.transacoes.EnumStatusTransacao;
import orange.com.br.mercadolivre.pagamentos.transacoes.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/compras/finalizar")
public class FinalizarCompraController {

    private final CompraRepository compraRepository;
    private final EntityManager entityManager;
    private final Emails emails;
    private final GerarNotaFiscal gerarNotaFiscal;
    private final GerarPontuacaoRanking gerarPontuacaoRanking;

    @Autowired
    public FinalizarCompraController(CompraRepository compraRepository,
                                     EntityManager entityManager,
                                     Emails emails,
                                     GerarNotaFiscal gerarNotaFiscal,
                                     GerarPontuacaoRanking gerarPontuacaoRanking) {
        this.compraRepository = compraRepository;
        this.entityManager = entityManager;
        this.emails = emails;
        this.gerarNotaFiscal = gerarNotaFiscal;
        this.gerarPontuacaoRanking = gerarPontuacaoRanking;
    }

    @PostMapping("/{idCompra}")
    @Transactional
    public ResponseEntity<?> finalizarCompra(@PathVariable String idCompra) {
        Compra compra = compraRepository
                .findById(idCompra)
                .orElseThrow(() -> new ExcecaoPersonalizada("Id: " + idCompra + " não encontrado", HttpStatus.NOT_FOUND));

        String retornoGateway = compra.getGateway().financeiro().processarTransacao(compra.getId()).toString();

        FinalizaCompraRequest finalizaCompraRequest = new FinalizaCompraRequest(compra.getId(), retornoGateway, compra.getGateway());
        Transacao transacao = finalizaCompraRequest.toModel(compraRepository);

        entityManager.persist(transacao);

        if (transacao.getStatusCompra() == EnumStatusTransacao.SUCESSO) {
            compra.alterarStatusCompra(EnumStatusCompra.FINALIZADA);
            compraRepository.save(compra);
            gerarNotaFiscal.processar(compra);
            gerarPontuacaoRanking.processar(compra);
            emails.transacaoEfetuada(transacao);
        } else {
            emails.avisoTransacaoRecusada(transacao);
        }

        return ResponseEntity.ok().build();
    }


}
