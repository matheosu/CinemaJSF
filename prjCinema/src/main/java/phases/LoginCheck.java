package phases;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginCheck implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5477914621530878816L;

	@Override
	public void afterPhase(PhaseEvent event) {
		//TODO Fazer a lógica de autenticação
	}

	@Override
	public void beforePhase(PhaseEvent event) {}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
