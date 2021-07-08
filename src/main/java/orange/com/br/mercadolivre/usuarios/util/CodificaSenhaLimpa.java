package orange.com.br.mercadolivre.usuarios.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodificaSenhaLimpa {

    public static String hash(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

}
