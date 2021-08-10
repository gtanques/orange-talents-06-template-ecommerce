package orange.com.br.mercadolivre.pagamento;

public interface Financeiro <T>{
    String pagar(String idCompra);

    T processarTransacao(String idCompra);
}
