package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.PopulaBanco;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	private DAO<Autor> autorDao = new DAO<Autor>(Autor.class);
	private DAO<Livro> livroDao = new DAO<Livro>(Livro.class);
	
	static PopulaBanco pb = new PopulaBanco();


	private Livro livro = new Livro();
	private Integer autorId; 
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void carregaById() {
		Integer id = this.livro.getId();
		this.livro = livroDao.buscaPorId(id);
		if(this.livro == null) {
			this.livro = new Livro();
		}
	}
	
	
	public List<Livro> getLivros() {
		return livroDao.listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}
		
		if (this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
		} else {
			livroDao.atualiza(livro);
		}

	}
	
	public void excluir(Livro livro) {
		livroDao.remove(livro);
	}
	
	public void excluirAutor(Autor autor) {
		List<Autor> autores = livro.getAutores();
		if (autores.size() > 1) {
			autores.remove(autor);
			if (livro.getId() != null) {
				livroDao.atualiza(livro);
			}
		}
	}
	
	public void alterar(Livro livro) {
		this.livro = livro;
		livroDao.atualiza(livro);
		
	}
	
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
	}

}
