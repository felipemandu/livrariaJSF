package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.caelum.livraria.modelo.Usuario;

public class UserDAO implements Serializable{
	
	EntityManagerFactory emf = JPAUtil.emf;
	
	public Usuario login(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> rt = query.from(Usuario.class);
		query.select(rt).where(cb.equal(rt.get("email"), usuario.getEmail()), cb.equal(rt.get("senha"), usuario.getSenha()));
		
		Usuario usuarioDB = null;
		try {
			usuarioDB = em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			
		}
		return usuarioDB;
	}

}
