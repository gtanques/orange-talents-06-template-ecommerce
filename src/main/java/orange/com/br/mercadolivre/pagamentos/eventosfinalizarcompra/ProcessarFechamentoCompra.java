package orange.com.br.mercadolivre.pagamentos.eventosfinalizarcompra;

import orange.com.br.mercadolivre.compras.Compra;

public interface ProcessarFechamentoCompra {

    void processar(Compra compra);

}
