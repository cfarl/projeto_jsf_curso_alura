package br.com.caelum.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {

	//----------------------------------------------------------------------------
	/** Verifica se existe usuario com email e senha informados */
	//----------------------------------------------------------------------------
	public boolean existeUsuario(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		List<Usuario> resultado1 = em.createQuery("select u from Usuario u ", Usuario.class)
				.getResultList();
		System.out.println("----------->" + resultado1.size());
		
		
		List<Usuario> resultado = em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha ", Usuario.class)
				.setParameter("email", usuario.getEmail())
				.setParameter("senha", usuario.getSenha())
				.getResultList();
		em.close();

		return resultado.size() > 0 ;
	}
}
