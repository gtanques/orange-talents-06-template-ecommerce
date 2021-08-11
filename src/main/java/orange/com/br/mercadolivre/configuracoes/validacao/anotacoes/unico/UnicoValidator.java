package orange.com.br.mercadolivre.configuracoes.validacao.anotacoes.unico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UnicoValidator implements ConstraintValidator<UnicoValid, Object> {

    private Class<?> entidade;
    private String campo;

    @PersistenceContext
    private final EntityManager entityManager;

    public UnicoValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(UnicoValid valid) {
        entidade = valid.entidade();
        campo = valid.campo();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + entidade.getName() + " where " + campo + "=:value");
        query.setParameter("value", obj);

        List<?> list = query.getResultList();
        return list.isEmpty();
    }

}
