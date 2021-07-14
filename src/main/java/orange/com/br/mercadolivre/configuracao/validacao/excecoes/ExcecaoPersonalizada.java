package orange.com.br.mercadolivre.configuracao.validacao.excecoes;

public class ExcecaoPersonalizada extends RuntimeException {
    public ExcecaoPersonalizada(String mensagem) {
        super(mensagem);
    }
}
