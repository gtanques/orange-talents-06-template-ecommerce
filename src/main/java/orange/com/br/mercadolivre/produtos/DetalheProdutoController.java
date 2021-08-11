package orange.com.br.mercadolivre.produtos;

import orange.com.br.mercadolivre.configuracoes.validacao.ExcecaoPersonalizada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/detalhe")
public class DetalheProdutoController {

    @Autowired
    private final ProdutoRepository repository;

    public DetalheProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        Produto produto = repository
                .findById(id)
                .orElseThrow(() -> new ExcecaoPersonalizada("Id: " + id + " não encontrado.", HttpStatus.NOT_FOUND));

        return ResponseEntity.ok().body(new ProdutoDetalheResponse(produto));
    }

}
