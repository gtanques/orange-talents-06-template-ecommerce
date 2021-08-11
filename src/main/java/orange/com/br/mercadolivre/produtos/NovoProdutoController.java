package orange.com.br.mercadolivre.produtos;

import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    public NovoProdutoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid NovoProdutoRequest request, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = request.toModel(entityManager, usuario);
        entityManager.persist(produto);

        return ResponseEntity.ok().build();
    }

}
