package orange.com.br.mercadolivre.categorias.controller;

import orange.com.br.mercadolivre.categorias.Categoria;
import orange.com.br.mercadolivre.categorias.dto.NovaCategoriaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/categorias/novo")
public class NovaCategoriaController {

    @PersistenceContext
    private final EntityManager  entityManager;

    public NovaCategoriaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@Valid @RequestBody NovaCategoriaRequest request){

        Categoria categoria = request.toModel(entityManager);
        entityManager.persist(categoria);

        return ResponseEntity.ok().build();
    }

}
