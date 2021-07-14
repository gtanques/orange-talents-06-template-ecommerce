package orange.com.br.mercadolivre.configuracao.validacao.excecoes;

public class ExcecaoDeIdNaoEncontradoPersonalizada extends RuntimeException {

    public ExcecaoDeIdNaoEncontradoPersonalizada(Long id)
    {
        super("id:" + id + " não encontrado.");
    }

}
