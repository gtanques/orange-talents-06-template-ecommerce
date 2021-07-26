package orange.com.br.mercadolivre.configuracao.validacao.dto;

public class ErroGlobalResponse {

    private String erro;

    public String getErro() {
        return erro;
    }

    public ErroGlobalResponse(String erro) {
        this.erro = erro;
    }

}
