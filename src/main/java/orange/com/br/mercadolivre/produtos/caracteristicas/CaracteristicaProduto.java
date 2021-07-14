package orange.com.br.mercadolivre.produtos.caracteristicas;

import orange.com.br.mercadolivre.produtos.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_caracteristica_produto")
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @Deprecated
    private CaracteristicaProduto() {
    }

    public CaracteristicaProduto(@NotNull @NotBlank String nome,
                                 @NotNull @NotBlank String descricao,
                                 @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaProduto that = (CaracteristicaProduto) o;
        return id.equals(that.id) && nome.equals(that.nome) && descricao.equals(that.descricao) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, produto);
    }

}
