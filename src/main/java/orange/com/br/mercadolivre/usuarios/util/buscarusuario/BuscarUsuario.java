package orange.com.br.mercadolivre.usuarios.util.buscarusuario;

import orange.com.br.mercadolivre.configuracao.validacao.exceptions.ExcecaoPersonalizada;
import orange.com.br.mercadolivre.usuarios.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BuscarUsuario implements VerificarUsuario{

    @PersistenceContext
    private EntityManager entityManager;

    public BuscarUsuario(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void verificaSeUsuarioPossuiProdutosCadastrados(Usuario usuario) {
        Query query = entityManager.createQuery("select 1 from Produto where usuario.login=:login");
        query.setParameter("login", usuario.getEmail());

        List<?> list = query.getResultList();
        if (!list.isEmpty()){
            throw new ExcecaoPersonalizada("usuário já possui 1 produto cadastrado.", HttpStatus.FORBIDDEN);
        }
    }

}
