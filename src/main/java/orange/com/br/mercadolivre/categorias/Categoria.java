package orange.com.br.mercadolivre.categorias;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    private Categoria() {
    }

    public Categoria(@NotNull @NotBlank String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

}
