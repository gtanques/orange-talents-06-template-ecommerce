package orange.com.br.mercadolivre.pagamento.eventosfinalizarcompra;

import orange.com.br.mercadolivre.compras.Compra;

public interface ProcessarFechamentoCompra {

    void processar(Compra compra);

}
