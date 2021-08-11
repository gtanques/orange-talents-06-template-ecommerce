package orange.com.br.mercadolivre.configuracoes.validacao;

public class ErroGlobalResponse {

    private String erro;

    public ErroGlobalResponse(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

}
