package orange.com.br.mercadolivre.compras;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.configuracoes.validacao.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.configuracoes.validacao.anotacoes.existe.ExisteValid;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaCompraRequest {

    @Positive
    @NotNull
    private final Integer quantidade;

    @NotNull
    @ExisteValid(entidade = Produto.class, propriedade = "id")
    private final Long idProduto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final EnumGatewayPagamento gateway;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCompraRequest(Integer quantidade, Long idProduto, EnumGatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Compra toModel(EntityManager entityManager, Usuario comprador){
        Produto produto = entityManager.find(Produto.class, idProduto);
        boolean abaterEstoque = produto.abaterEstoque(this.quantidade);
        if (abaterEstoque){
            BigDecimal valorCompra = produto.getValor().multiply(BigDecimal.valueOf(this.quantidade));
            return new Compra(produto, this.quantidade, valorCompra, comprador, this.gateway);
        }else{
            throw new ExcecaoPersonalizada("Quantidade do produto indisponível.", HttpStatus.BAD_REQUEST);
        }
    }

}
