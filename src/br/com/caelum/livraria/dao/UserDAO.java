package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UserDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf = JPAUtil.emf;
	
	public Usuario login(Usuario usuario) {
		EntityManager em = emf.createEntityManager();

		Usuario usuarioDB = null;
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " +
											     "WHERE u.email = :email " +
											     "AND u.senha = :senha", Usuario.class);
			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
			
			usuarioDB = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioDB;
	}

}
