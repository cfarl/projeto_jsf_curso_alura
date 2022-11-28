package br.com.caelum.livraria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/** Esse listener está cadastrado no faces-config.xml */
public class LogPhaseListener implements PhaseListener {
	
	@Override
	public void afterPhase(PhaseEvent event) { }

	//-----------------------------------------------------------------
	/** Mostra qual fase está sendo executada */
	//-----------------------------------------------------------------	
	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("FASE :" + event.getPhaseId());
	}

	//-----------------------------------------------------------------
	/** Executa a autorizacao para todas as fases do ciclo de vida */
	//-----------------------------------------------------------------	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
