package bean;

import model.Sessao;

public class SessaoBean extends BaseBean<Sessao>{

	public SessaoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Sessao newInstance() {
		return new Sessao();
	}

}
