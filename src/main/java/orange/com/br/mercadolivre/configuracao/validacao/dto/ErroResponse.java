package orange.com.br.mercadolivre.configuracao.validacao.dto;

public class ErroResponse {

    private String campo;
    private String erro;

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

    public ErroResponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public ErroResponse(String erro) {
        this.erro = erro;
    }
}
