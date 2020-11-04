package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	@Inject
	private AutorDao dao;

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
 
	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(autor.getId() == null) {
			dao.adiciona(this.autor);	
		} else {
			dao.atualiza(autor);
		}
		
		this.autor = new Autor();
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
