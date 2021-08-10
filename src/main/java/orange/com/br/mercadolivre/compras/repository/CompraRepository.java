package orange.com.br.mercadolivre.compras.repository;

import orange.com.br.mercadolivre.compras.Compra;
import orange.com.br.mercadolivre.compras.enumeradores.StatusCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, String> {

    boolean existsByIdAndStatus(String id, StatusCompra statusCompra);
}
