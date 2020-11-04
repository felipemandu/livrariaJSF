package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@AroundInvoke
	public Object executaTX(InvocationContext context) {
		
		em.getTransaction().begin();
		Object result = null;
		try {
			result = context.proceed();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		em.getTransaction().commit();
 		
		return result;
	}
	
}
