package orange.com.br.mercadolivre.configuracao.validacao.anotacoes.usuarioproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UsuarioPossuiProdutoValidator implements ConstraintValidator<UsuarioPossuiProdutoValid, Object> {

    @PersistenceContext
    private final EntityManager entityManager;

    public UsuarioPossuiProdutoValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from Produto where usuario.login=:value");
        query.setParameter("value", obj);

        List<?> list = query.getResultList();
        return list.isEmpty();
    }

}
