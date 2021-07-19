package orange.com.br.mercadolivre.produtos.caracteristicas.dto;

import orange.com.br.mercadolivre.produtos.caracteristicas.CaracteristicaProduto;

public class ProdutoDetalheCaracteristicaResponse {

    private String nome;
    private String descricao;

    public ProdutoDetalheCaracteristicaResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
