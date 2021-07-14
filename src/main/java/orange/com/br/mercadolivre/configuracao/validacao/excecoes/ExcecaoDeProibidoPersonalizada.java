package orange.com.br.mercadolivre.configuracao.validacao.excecoes;

public class ExcecaoDeProibidoPersonalizada extends RuntimeException {
    public ExcecaoDeProibidoPersonalizada(String mensagem) {
        super(mensagem);
    }
}
