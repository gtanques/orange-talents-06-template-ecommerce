package orange.com.br.mercadolivre.produtos.controller;

import orange.com.br.mercadolivre.produtos.Produto;
import orange.com.br.mercadolivre.produtos.dto.NovoProdutoRequest;
import orange.com.br.mercadolivre.usuarios.Usuario;
import orange.com.br.mercadolivre.usuarios.util.buscarusuario.BuscarUsuario;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private  final BuscarUsuario buscarUsuario;

    public NovoProdutoController(EntityManager entityManager, BuscarUsuario buscarUsuario) {
        this.entityManager = entityManager;
        this.buscarUsuario = buscarUsuario;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@RequestBody @Valid NovoProdutoRequest request, Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        buscarUsuario.verificaSeUsuarioPossuiProdutosCadastrados(usuario);
        Produto produto = request.toModel(entityManager, usuario);
        entityManager.persist(produto);

        return ResponseEntity.ok().build();
    }

}
