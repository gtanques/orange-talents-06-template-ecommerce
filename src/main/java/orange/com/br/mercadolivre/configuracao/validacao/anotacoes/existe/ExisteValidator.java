package orange.com.br.mercadolivre.configuracao.validacao.anotacoes.existe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteValidator implements ConstraintValidator<ExisteValid, Object> {

    private Class<?> entidade;
    private String campo;

    @PersistenceContext
    private final EntityManager entityManager;

    public ExisteValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(ExisteValid valid) {
        entidade = valid.entidade();
        campo = valid.propriedade();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {

        if(obj == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + entidade.getName() + " where " + campo + "=:value");
        query.setParameter("value", obj);
        List<?> list = query.getResultList();

        return !list.isEmpty();

    }

}
