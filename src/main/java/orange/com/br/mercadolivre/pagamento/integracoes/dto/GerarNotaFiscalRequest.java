package orange.com.br.mercadolivre.pagamento.integracoes.dto;

import orange.com.br.mercadolivre.compras.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GerarNotaFiscalRequest {

    @NotNull
    @NotBlank
    private String idCompra;

    @NotNull
    @NotBlank
    private Long idComprador;

    @Deprecated
    private GerarNotaFiscalRequest() {
    }

    public GerarNotaFiscalRequest(Compra compra) {
        this.idCompra = compra.getId();
        this.idComprador = compra.getComprador().getId();
    }

    public String getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

}
