package orange.com.br.mercadolivre.imagens.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UploadImagem {

    Set<String> enviar(List<MultipartFile> imagens);

}
