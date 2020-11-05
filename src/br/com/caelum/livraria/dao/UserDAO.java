package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.tx.Transacional;

public class UserDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Transacional
	public boolean login(Usuario usuario) {

		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " +
										     "WHERE u.email = :email " +
										     "AND u.senha = :senha", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		try {
			query.getSingleResult();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
