package orange.com.br.mercadolivre.produtos.caracteristicas.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.caracteristicas.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaProdutoRequest {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String descricao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CaracteristicaProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto toModel(Produto produto){
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

}
