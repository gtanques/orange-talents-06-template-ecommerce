package orange.com.br.mercadolivre.compras.eventosfinalizarcompra;

import orange.com.br.mercadolivre.compras.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GerarRankingRequest {

    @NotNull
    @NotBlank
    private String idCompra;

    @NotNull
    @NotBlank
    private Long idVendedor;

    @Deprecated
    private GerarRankingRequest() {
    }

    public GerarRankingRequest(Compra compra) {
        this.idCompra = compra.getId();
        this.idVendedor = compra.getProduto().getUsuario().getId();
    }

    public String getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

}
