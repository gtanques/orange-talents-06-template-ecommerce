package orange.com.br.mercadolivre.usuarios.controller;

import orange.com.br.mercadolivre.usuarios.Usuario;
import orange.com.br.mercadolivre.usuarios.dto.UsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios/novo")
public class UsuarioController {

    @PersistenceContext
    private final EntityManager entityManager;

    public UsuarioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid UsuarioRequest request){
        Usuario usuario = request.toModel();
        entityManager.persist(usuario);

        return ResponseEntity.ok().build();
    }

}
