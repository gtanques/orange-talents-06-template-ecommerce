package orange.com.br.mercadolivre.produtos.controller;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.dto.NovoProdutoRequest;
import orange.com.br.mercadolivre.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/novo")
public class NovoProdutoController {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public NovoProdutoController(EntityManager entityManager, UsuarioRepository usuarioRepository) {
        this.entityManager = entityManager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid NovoProdutoRequest request){
        Produto produto = request.toModel(entityManager, usuarioRepository);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }

}
