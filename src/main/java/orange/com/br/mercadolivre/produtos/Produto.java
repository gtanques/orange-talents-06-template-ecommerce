package orange.com.br.mercadolivre.produtos;

import orange.com.br.mercadolivre.caracteristicas.CaracteristicaProduto;
import orange.com.br.mercadolivre.caracteristicas.dto.CaracteristicaProdutoRequest;
import orange.com.br.mercadolivre.categorias.Categoria;
import orange.com.br.mercadolivre.produtos.imagens.ImagemProduto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @Deprecated
    private Produto() {
    }

    public Produto(@NotNull @NotBlank String nome,
                   @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive Integer quantidade,
                   @NotNull @NotBlank @Length(max=1000) String descricao,
                   @Valid @NotNull Categoria categoria,
                   @Valid @NotNull Usuario usuario,
                   @Valid @NotNull @Size(min=3) Collection<CaracteristicaProdutoRequest> caracteristicas) {
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

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public void adicionarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(url -> new ImagemProduto(url, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

}
