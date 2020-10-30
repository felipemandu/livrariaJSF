package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UserDAO;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	private UserDAO userDao = new UserDAO();
	
	public String login() {
		Usuario usuarioDB = userDao.login(this.usuario);
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		if (existeUsuario(usuarioDB)) {
			this.usuario = usuarioDB;
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		} else {
			return "login?faces-redirect=true";
		}
		
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	private boolean existeUsuario(Usuario usuarioDB) {
		return usuarioDB != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
