package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UserDAO;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	private UserDAO userDao;
	
	@Inject
	private FacesContext context;
	
	public String login() {
		Boolean isValidUser = userDao.login(this.usuario);
			
		if (isValidUser) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		} else {
			return "login?faces-redirect=true";
		}
		
	}
	
	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
