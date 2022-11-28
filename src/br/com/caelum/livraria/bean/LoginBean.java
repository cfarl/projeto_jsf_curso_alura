package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	//-----------------------------------------------------------------
	/** Faz o login na aplicacao */
	//-----------------------------------------------------------------
	public String efetuaLogin() {
		System.out.println("Fazendo login de " + usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance() ;
		
		// Verifica se existe o usuario no banco de dados
		boolean existeUsuario = new UsuarioDAO().existeUsuario(this.usuario) ;
		if(existeUsuario) {
			// Se existe, guarda o usuario na sessao...			
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario) ;
			
			// E volta para a página livro.xhtml
			return "livro?faces-redirect=true" ; 
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true); // Sem isso o faces-redirect=true "come" a mensagem
		//context.addMessage("login:email", new FacesMessage("Usuário não encontrado")); // Mensagem para h:message do email 
		context.addMessage(null, new FacesMessage("Usuário não encontrado")); // Mensagem para h:messages
		
		return "login?faces-redirect=true" ;
	}
	
	//-----------------------------------------------------------------
	/** Faz o login na aplicacao */
	//-----------------------------------------------------------------	
	public String deslogar() {
		// Remove o usuario logado da sessao
		FacesContext context = FacesContext.getCurrentInstance() ;		
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		// Encaminha para a pagina de login
		return "login?faces-redirect=true" ;
	}
	
}
