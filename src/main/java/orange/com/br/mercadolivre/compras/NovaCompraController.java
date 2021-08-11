package orange.com.br.mercadolivre.compras;

import orange.com.br.mercadolivre.emails.Emails;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class NovaCompraController {

    private final EntityManager entityManager;
    private final Emails emails;

    @Autowired
    public NovaCompraController(EntityManager entityManager, Emails emails) {
        this.entityManager = entityManager;
        this.emails = emails;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid NovaCompraRequest request, Authentication auth) {
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();
        Compra novaCompra = request.toModel(entityManager, usuarioLogado);
        entityManager.persist(novaCompra);
        emails.desejoDeCompra(novaCompra);
        String urlPagamento = novaCompra.getGateway().financeiro().pagar(novaCompra.getId());

        return ResponseEntity.status(302).body(urlPagamento);
    }

}
