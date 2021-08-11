package orange.com.br.mercadolivre.produtos.opinioes;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tb_opiniao")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    @Deprecated
    private Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) Integer nota,
                   @NotNull @NotBlank String titulo,
                   @NotNull @NotBlank @Length(max = 500) String descricao,
                   @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() { return nota; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return Objects.equals(id, opiniao.id) &&
                Objects.equals(nota, opiniao.nota) &&
                Objects.equals(titulo, opiniao.titulo) &&
                Objects.equals(descricao, opiniao.descricao) &&
                Objects.equals(produto, opiniao.produto) &&
                Objects.equals(usuario, opiniao.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, titulo, descricao, produto, usuario);
    }

}
