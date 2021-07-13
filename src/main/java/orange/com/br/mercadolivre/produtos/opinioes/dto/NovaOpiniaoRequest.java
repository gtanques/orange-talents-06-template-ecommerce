package orange.com.br.mercadolivre.produtos.opinioes.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.produtos.opinioes.Opiniao;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaOpiniaoRequest {

    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @NotNull
    @NotBlank
    @Length(max = 100)
    private String titulo;

    @NotNull
    @NotBlank
    @Length(max = 500)
    private String descricao;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaOpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }

}
