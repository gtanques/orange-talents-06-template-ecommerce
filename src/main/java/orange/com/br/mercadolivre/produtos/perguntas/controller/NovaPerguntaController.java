package orange.com.br.mercadolivre.produtos.perguntas.controller;

import orange.com.br.mercadolivre.configuracao.validacao.excecoes.ExcecaoDeIdNaoEncontradoPersonalizada;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;
import orange.com.br.mercadolivre.produtos.perguntas.dto.NovaPerguntaRequest;
import orange.com.br.mercadolivre.produtos.repository.ProdutoRepository;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos/perguntas")
public class NovaPerguntaController {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    private final ProdutoRepository produtoRepository;

    public NovaPerguntaController(EntityManager entityManager, ProdutoRepository produtoRepository) {
        this.entityManager = entityManager;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inserir(@PathVariable Long id,
                                     @RequestBody @Valid NovaPerguntaRequest request,
                                     Authentication authentication) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ExcecaoDeIdNaoEncontradoPersonalizada(id));
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Pergunta pergunta = request.toModel(produto, usuario);
        entityManager.persist(pergunta);

        return ResponseEntity.ok().build();
    }
}
