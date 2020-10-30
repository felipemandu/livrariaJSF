package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@Named
@ViewScoped
public class LivroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorDao autorDao; 
	
	@Inject
	private LivroDao livroDao;	

	private Livro livro = new Livro();
	
	private Integer autorId;
	
	private List<Livro> livros; 
	
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
		if (livros == null) {
			this.livros = livroDao.listaTodos();
		}
		return livros;
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
			livros.add(livro);
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
