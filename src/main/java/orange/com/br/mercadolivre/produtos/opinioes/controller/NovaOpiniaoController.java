package orange.com.br.mercadolivre.produtos.opinioes.controller;


import orange.com.br.mercadolivre.produtos.opinioes.Opiniao;
import orange.com.br.mercadolivre.produtos.opinioes.dto.NovaOpiniaoRequest;
import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos/opinioes")
public class NovaOpiniaoController {

    @PersistenceContext
    private final EntityManager entityManager;

    public NovaOpiniaoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> inserir(@PathVariable Long id,
                                     @Valid @RequestBody NovaOpiniaoRequest request,
                                     Authentication authentication){
        Produto produto = entityManager.find(Produto.class, id);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Opiniao opiniao = request.toModel(produto, usuario);
        entityManager.persist(opiniao);

        return ResponseEntity.ok().build();
    }

}
