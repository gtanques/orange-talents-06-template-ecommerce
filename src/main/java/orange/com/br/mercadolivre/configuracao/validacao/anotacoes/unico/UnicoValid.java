package orange.com.br.mercadolivre.configuracao.validacao.anotacoes.unico;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnicoValidator.class)
public @interface UnicoValid {

    String message() default "já cadastrado(a).";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campo();

    Class<?> entidade();

}
