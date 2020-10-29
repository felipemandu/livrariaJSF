package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();
	
	private DAO<Autor> dao = new DAO<Autor>(Autor.class);

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
 
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(autor.getId() == null) {
			dao.adiciona(this.autor);	
		} else {
			dao.atualiza(autor);
		}
		
		return "livro?faces-redirect=true";
	}
	
	public List<Autor> getAutores() {
		return dao.listaTodos();
	}
	
	public void excluir(Autor autor) {
		if(autor.getLivros().size() == 0) {
			dao.remove(autor);
		}		
	}
	
	public void alterar(Autor autor) {
		this.autor = autor;
	}
	
}
