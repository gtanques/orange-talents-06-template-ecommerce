package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.configuracoes.validacao.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.configuracoes.validacao.anotacoes.existe.ExisteValid;
import orange.com.br.mercadolivre.pagamentos.transacoes.Transacao;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public class FinalizaCompraRequest {

    @NotNull
    @ExisteValid(entidade = Compra.class, propriedade = "id")
    private final String idCompra;

    @NotNull
    private final String statusCompra;

    @NotNull
    private final EnumGatewayPagamento gateway;

    public FinalizaCompraRequest(String idCompra, String statusCompra, EnumGatewayPagamento pagamento) {
        this.idCompra = idCompra;
        this.statusCompra = statusCompra;
        this.gateway = pagamento;
    }

    public Transacao toModel(CompraRepository compraRepository) {
        boolean existeCompraFinalizada = compraRepository.existsByIdAndStatus(this.idCompra, EnumStatusCompra.FINALIZADA);

        if (existeCompraFinalizada) {
            throw new ExcecaoPersonalizada("Compra já finalizada.", HttpStatus.BAD_REQUEST);
        }

        Compra compra = compraRepository.findById(this.idCompra).get();
        return new Transacao(compra, this.statusCompra, this.gateway);
    }

}
