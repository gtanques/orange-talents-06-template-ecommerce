package orange.com.br.mercadolivre.produtos.perguntas.controller;

import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.perguntas.Pergunta;
import orange.com.br.mercadolivre.produtos.perguntas.dto.NovaPerguntaRequest;
import orange.com.br.mercadolivre.emails.Emails;
import orange.com.br.mercadolivre.produtos.repository.ProdutoRepository;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    private final ProdutoRepository produtoRepository;
    private final Emails emails;

    @Autowired
    public NovaPerguntaController(EntityManager entityManager, ProdutoRepository produtoRepository, Emails emails) {
        this.entityManager = entityManager;
        this.produtoRepository = produtoRepository;
        this.emails = emails;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inserir(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request, Authentication authentication) {
        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(() -> new ExcecaoPersonalizada("Id: " + id + " não encontrado.", HttpStatus.NOT_FOUND));

        Usuario usuario = (Usuario) authentication.getPrincipal();
        Pergunta pergunta = request.toModel(produto, usuario);

        entityManager.persist(pergunta);
        emails.novaPergunta(pergunta);

        return ResponseEntity.ok().build();
    }
}
