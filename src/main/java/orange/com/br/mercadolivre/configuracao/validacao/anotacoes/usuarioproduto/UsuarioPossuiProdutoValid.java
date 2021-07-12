package orange.com.br.mercadolivre.configuracao.validacao.anotacoes.usuarioproduto;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioPossuiProdutoValidator.class)
public @interface UsuarioPossuiProdutoValid {

    String message() default "Usuário já possui um produto cadastrado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
