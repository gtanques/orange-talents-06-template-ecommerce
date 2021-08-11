package orange.com.br.mercadolivre.usuarios;

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
@RequestMapping("/usuarios/novo")
public class NovoUsuarioController {

    @PersistenceContext
    private final EntityManager entityManager;

    public NovoUsuarioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid NovoUsuarioRequest request) {
        Usuario usuario = request.toModel();
        entityManager.persist(usuario);

        return ResponseEntity.ok().build();
    }

}
