package br.com.caelum.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	
	EntityManager em;
	
	private final Class<T> classe;

	public DAO(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	public void remove(T t) {
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
	}

	public void atualiza(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		return em.createQuery(query).getResultList();
	}

	public T buscaPorId(Integer id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		em.getTransaction();
		long result = (Long) em.createQuery("select count(n) from livro n").getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		return em.createQuery(query)
					.setFirstResult(firstResult)
					.setMaxResults(maxResults)
					.getResultList();
	}

}
