package orange.com.br.mercadolivre.configuracao.validacao.anotacoes.nomerepetidolista;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {FIELD, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {NomeRepetidoListaValidator.class})
@Documented
public @interface NomeRepetidoListaValid {

        String message() default "Existem mais de uma característica com o mesmo nome.";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
