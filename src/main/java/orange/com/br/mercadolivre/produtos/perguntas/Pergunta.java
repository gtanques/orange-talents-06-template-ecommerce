package orange.com.br.mercadolivre.produtos.perguntas;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pergunta_produto")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    @Deprecated
    private Pergunta() {
    }

    public Pergunta(String titulo, @Valid @NotNull Usuario usuario, @Valid @NotNull Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }
}
