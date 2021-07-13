package orange.com.br.mercadolivre.produtos.imagens.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImagensRequest {

    @Size(min = 1)
    @NotNull
    private final List<MultipartFile> listaImagens;

    public ImagensRequest(List<MultipartFile> listaImagens) {
        this.listaImagens = listaImagens;
    }

    public List<MultipartFile> getListaImagens() {
        return listaImagens;
    }

}
