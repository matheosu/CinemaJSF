package listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import annotations.InjectDAO;

public class InjectListener implements PhaseListener{

	private static final long serialVersionUID = 3247325966581444438L;

	@Override
	public void afterPhase(PhaseEvent event) {
		//TODO injectListener
		InjectDAO.doInjection(null);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
}
