package orange.com.br.mercadolivre.produtos.repository;

import orange.com.br.mercadolivre.produtos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
