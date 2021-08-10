package orange.com.br.mercadolivre.compras.dto;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.enumeradores.GatewayPagamento;
import orange.com.br.mercadolivre.compras.enumeradores.StatusCompra;
import orange.com.br.mercadolivre.compras.repository.CompraRepository;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.existe.ExisteValid;
import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.transacoes.Transacao;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public class FinalizaCompraRequest {

    @NotNull
    @ExisteValid(entidade = Compra.class, propriedade = "id")
    private final String idCompra;

    @NotNull
    private final String statusCompra;

    @NotNull
    private final GatewayPagamento gateway;

    public FinalizaCompraRequest(String idCompra, String statusCompra, GatewayPagamento pagamento) {
        this.idCompra = idCompra;
        this.statusCompra = statusCompra;
        this.gateway = pagamento;
    }

    public Transacao toModel(CompraRepository compraRepository) {
        boolean existeCompraFinalizada = compraRepository.existsByIdAndStatus(this.idCompra, StatusCompra.FINALIZADA);

        if (existeCompraFinalizada) {
            throw new ExcecaoPersonalizada("Compra já finalizada.", HttpStatus.BAD_REQUEST);
        }

        Compra compra = compraRepository.findById(this.idCompra).get();
        return new Transacao(compra, this.statusCompra, this.gateway);
    }

}
