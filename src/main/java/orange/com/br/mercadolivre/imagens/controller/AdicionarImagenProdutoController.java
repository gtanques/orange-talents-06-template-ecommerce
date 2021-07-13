package orange.com.br.mercadolivre.imagens.controller;

import orange.com.br.mercadolivre.imagens.dto.ImagensRequest;
import orange.com.br.mercadolivre.imagens.upload.UploadImagem;
import orange.com.br.mercadolivre.produtos.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("produtos/imagens")
public class AdicionarImagenProdutoController {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    private final UploadImagem uploadFakeImagem;

    public AdicionarImagenProdutoController(EntityManager entityManager, UploadImagem uploadFakeImagem) {
        this.entityManager = entityManager;
        this.uploadFakeImagem = uploadFakeImagem;
    }

    @PostMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid ImagensRequest request){
        Set<String> urls = uploadFakeImagem.enviar(request.getListaImagens());
        Produto produto = entityManager.find(Produto.class, id);
        produto.adicionarImagens(urls);
        entityManager.merge(produto);

        return ResponseEntity.ok().build();
    }

}
