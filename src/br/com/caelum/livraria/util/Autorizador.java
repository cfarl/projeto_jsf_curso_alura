package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

/** Esse listener está cadastrado no faces-config.xml */
public class Autorizador implements PhaseListener {

	//-----------------------------------------------------------------
	/** Faz a autorizacao apos a fase "Restore View" */
	//-----------------------------------------------------------------
	@Override
	public void afterPhase(PhaseEvent evento) {
		// Recupera a pagina que está sendo analisada
		FacesContext context = evento.getFacesContext() ;
		String nomePagina = context.getViewRoot().getViewId() ;
		System.out.println(nomePagina);
		
		// Se for a pagina de login, esta autorizada. Não há nada a se fazer.
		if("/login.xhtml".equals(nomePagina)) {
			return ;
		}
		
		// Se o usuario está logado, a pagina esta autorizada. Não há nada a se fazer
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado") ;
		if(usuarioLogado != null) {
			return ;
		}
		
		// Se não, encaminha para a página de login
		NavigationHandler handler = context.getApplication().getNavigationHandler() ;
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {	}

	//-----------------------------------------------------------------
	/** Executa a autorizacao somente na fase "Restore View" */
	//-----------------------------------------------------------------
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW ;
	}
}
