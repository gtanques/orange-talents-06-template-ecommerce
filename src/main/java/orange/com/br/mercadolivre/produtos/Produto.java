package orange.com.br.mercadolivre.produtos;

import orange.com.br.mercadolivre.categorias.Categoria;
import orange.com.br.mercadolivre.produtos.caracteristicas.CaracteristicaProduto;
import orange.com.br.mercadolivre.produtos.caracteristicas.dto.CaracteristicaProdutoRequest;
import orange.com.br.mercadolivre.produtos.imagens.ImagemProduto;
import orange.com.br.mercadolivre.produtos.opinioes.Opiniao;
import orange.com.br.mercadolivre.produtos.opinioes.uteis.OpinioesUtil;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.MERGE)
    private final Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto")
    @OrderBy("titulo asc")
    private final SortedSet<Pergunta> perguntas = new TreeSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto")
    private final Set<Opiniao> opinioes = new HashSet<>();

    @Deprecated
    private Produto() {
    }

    public Produto(@NotNull @NotBlank String nome,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Integer quantidade,
                   @NotNull @NotBlank @Length(max = 1000) String descricao,
                   @Valid @NotNull Categoria categoria,
                   @Valid @NotNull Usuario usuario,
                   @Valid @NotNull @Size(min = 3) Collection<CaracteristicaProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
    }

    public Long getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public void adicionarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(url -> new ImagemProduto(url, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapearCaracteristica) {
        return this.caracteristicas.stream()
                .map(funcaoMapearCaracteristica)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapearImagem) {
        return this.imagens.stream()
                .map(funcaoMapearImagem)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapearPergunta) {
        return this.perguntas.stream()
                .map(funcaoMapearPergunta)
                .collect(Collectors.toSet());
    }

    public OpinioesUtil getOpinioes() {
        return new OpinioesUtil(this.opinioes);
    }

    public boolean abaterEstoque(@Positive Integer quantidadeParaAbate) {
        if (this.quantidade > 0){
            this.quantidade -= quantidadeParaAbate;
            return true;
        }

        return  false;
    }

}
