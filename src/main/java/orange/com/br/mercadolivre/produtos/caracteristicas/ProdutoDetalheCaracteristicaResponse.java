package orange.com.br.mercadolivre.produtos.caracteristicas;

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
