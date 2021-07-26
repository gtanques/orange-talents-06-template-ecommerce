package orange.com.br.mercadolivre.compras.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.enumeradores.GatewayPagamento;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.existe.ExisteValid;
import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @Positive
    @NotNull
    private final Integer quantidade;

    @NotNull
    @ExisteValid(entidade = Produto.class, campo = "id")
    private final Long idProduto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final GatewayPagamento gateway;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCompraRequest(Integer quantidade, Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Compra toModel(EntityManager entityManager, Usuario comprador){
        Produto produto = entityManager.find(Produto.class, idProduto);
        boolean abaterEstoque = produto.abaterEstoque(this.quantidade);
        if (abaterEstoque){
            return new Compra(produto, this.quantidade, comprador, this.gateway);
        }else{
            throw new ExcecaoPersonalizada("Quantidade indisponivél do produto.", HttpStatus.BAD_REQUEST);
        }
    }

}
