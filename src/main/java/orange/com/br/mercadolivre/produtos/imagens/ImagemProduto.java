package orange.com.br.mercadolivre.produtos.imagens;

import orange.com.br.mercadolivre.produtos.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_imagem_produto")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private Produto produto;

    @Deprecated
    private ImagemProduto() {
    }

    public ImagemProduto(@NotNull @NotBlank @URL String url, @Valid @NotNull Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    public String getUrl() {
        return url;
    }

}
