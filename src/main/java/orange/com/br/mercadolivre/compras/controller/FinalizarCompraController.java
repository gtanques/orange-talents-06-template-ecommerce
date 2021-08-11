package orange.com.br.mercadolivre.compras.controller;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.dto.FinalizaCompraRequest;
import orange.com.br.mercadolivre.compras.enumeradores.StatusCompra;
import orange.com.br.mercadolivre.compras.repository.CompraRepository;
import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.emails.Emails;
import orange.com.br.mercadolivre.pagamento.integracoes.Integracao;
import orange.com.br.mercadolivre.transacoes.Transacao;
import orange.com.br.mercadolivre.transacoes.enumeradores.StatusTransacao;
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

    private CompraRepository compraRepository;
    private EntityManager entityManager;
    private Emails emails;
    private Integracao integracao;

    @Autowired
    public FinalizarCompraController(CompraRepository compraRepository, EntityManager entityManager, Emails emails, Integracao integracao) {
        this.compraRepository = compraRepository;
        this.entityManager = entityManager;
        this.emails = emails;
        this.integracao = integracao;
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

        if (transacao.getStatusCompra() == StatusTransacao.SUCESSO) {
            compra.alterarStatusCompra(StatusCompra.FINALIZADA);
            compraRepository.save(compra);
            integracao.processaNotaFiscal(compra);
            integracao.gerarPontuacaoRank(compra);
            emails.transacaoEfetuada(transacao);
        } else {
            emails.avisoTransacaoRecusada(transacao);
        }

        return ResponseEntity.ok().build();
    }


}
