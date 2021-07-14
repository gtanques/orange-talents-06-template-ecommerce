package orange.com.br.mercadolivre.usuarios.util;

import orange.com.br.mercadolivre.usuarios.Usuario;

import javax.persistence.EntityManager;

public interface VerificarUsuario {

    void verificaSeUsuarioPossuiProdutosCadastrados(EntityManager entityManager, Usuario usuario);

}
