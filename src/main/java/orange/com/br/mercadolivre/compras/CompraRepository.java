package orange.com.br.mercadolivre.compras;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, String> {

    boolean existsByIdAndStatus(String id, EnumStatusCompra enumStatusCompra);
}