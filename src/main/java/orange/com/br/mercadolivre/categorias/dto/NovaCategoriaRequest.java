package orange.com.br.mercadolivre.categorias.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.categorias.Categoria;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.existe.ExisteValid;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCategoriaRequest {

    @NotNull
    @NotBlank
    private String nome;

    @ExisteValid(entidade = Categoria.class, propriedade = "id")
    private Long idCategoriaMae;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(EntityManager entityManager) {
        Categoria categoria = idCategoriaMae != null ? entityManager.find(Categoria.class, idCategoriaMae) : null;
        return new Categoria(this.nome, categoria);
    }

}
