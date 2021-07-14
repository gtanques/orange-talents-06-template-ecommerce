package orange.com.br.mercadolivre.produtos.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import orange.com.br.mercadolivre.categorias.Categoria;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.existe.ExisteValid;
import orange.com.br.mercadolivre.configuracao.validacao.anotacoes.nomerepetidolista.NomeRepetidoListaValid;
import orange.com.br.mercadolivre.configuracao.validacao.excecoes.ExcecaoDeProibidoPersonalizada;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.caracteristicas.dto.CaracteristicaProdutoRequest;
import orange.com.br.mercadolivre.usuarios.Usuario;
import orange.com.br.mercadolivre.usuarios.util.VerificarUsuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class NovoProdutoRequest implements VerificarUsuario {

    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Positive
    private Integer quantidade;

    @Length(max = 1000)
    @NotNull
    private String descricao;

    @ExisteValid(entidade = Categoria.class, campo = "id")
    @NotNull
    private Long idCategoria;

    @Size(min = 3)
    @NotNull
    @NomeRepetidoListaValid
    List<CaracteristicaProdutoRequest> caracteristicas;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoProdutoRequest(String nome,
                              BigDecimal valor,
                              Integer quantidade,
                              String descricao,
                              Long idCategoria,
                              List<CaracteristicaProdutoRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public Produto toModel(EntityManager entityManager, Usuario usuario) {
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);
        verificaSeUsuarioPossuiProdutosCadastrados(entityManager, usuario);
        return new Produto(this.nome,
                        this.valor,
                        this.quantidade,
                        this.descricao,
                        categoria,
                        usuario,
                        this.caracteristicas);
    }

    @Override
    public void verificaSeUsuarioPossuiProdutosCadastrados(EntityManager entityManager, Usuario usuario) {
        Query query = entityManager.createQuery("select 1 from Produto where usuario.login=:login");
        query.setParameter("login", usuario.getLogin());

        List<?> list = query.getResultList();
        if (!list.isEmpty()){
            throw new ExcecaoDeProibidoPersonalizada("usuário já possui 1 produto cadastrado.");
        }
    }
}
