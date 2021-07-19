package orange.com.br.mercadolivre.produtos.dto;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.caracteristicas.dto.ProdutoDetalheCaracteristicaResponse;
import orange.com.br.mercadolivre.produtos.imagens.ImagemProduto;
import orange.com.br.mercadolivre.produtos.opinioes.uteis.OpinioesUtil;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class ProdutoDetalheResponse {

    private final String nome;
    private final BigDecimal valor;
    private final Integer quantidade;
    private final String descricao;
    private final Set<ProdutoDetalheCaracteristicaResponse> caracteristicas;
    private final Set<String> imagens;
    private final Set<String> perguntas;
    private final Set<Map<String, String>> opinioes;
    private final double mediaNotas;
    private final int totalOpinioes;

    public ProdutoDetalheResponse(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapeiaCaracteristicas(ProdutoDetalheCaracteristicaResponse::new);
        this.imagens = produto.mapeiaImagens(ImagemProduto::getUrl);
        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);
        OpinioesUtil opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao()));
        this.mediaNotas = opinioes.media();
        this.totalOpinioes = opinioes.total();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ProdutoDetalheCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

}
