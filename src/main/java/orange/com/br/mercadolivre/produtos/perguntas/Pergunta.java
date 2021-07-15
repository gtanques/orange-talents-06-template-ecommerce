package orange.com.br.mercadolivre.produtos.perguntas;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "tb_pergunta_produto")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, updatable = false)
    private Instant dataCriacao = Instant.now();

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

    public Usuario getUsuario() { return usuario; }

    public Produto getProduto() { return produto; }

    public String getPergunta() {
        return titulo;
    }

}
