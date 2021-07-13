package orange.com.br.mercadolivre.imagens.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeUpload implements  UploadImagem{

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
          return imagens.stream()
                  .map(imagem -> "https://firebasestorage.googleapis.com/" + imagem.getOriginalFilename())
                  .collect(Collectors.toSet());
    }

}
