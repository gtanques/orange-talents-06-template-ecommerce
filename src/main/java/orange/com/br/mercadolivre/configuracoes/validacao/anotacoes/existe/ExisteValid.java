package orange.com.br.mercadolivre.configuracoes.validacao.anotacoes.existe;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExisteValidator.class)
public @interface ExisteValid {

    String message() default "Não encontrado(a).";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String propriedade();

    Class<?> entidade();

}
