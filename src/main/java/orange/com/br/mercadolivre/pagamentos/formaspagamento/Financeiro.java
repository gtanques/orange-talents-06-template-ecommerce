package orange.com.br.mercadolivre.pagamentos.formaspagamento;

public interface Financeiro<T> {

    String pagar(String idCompra);

    T processarTransacao(String idCompra);

}
