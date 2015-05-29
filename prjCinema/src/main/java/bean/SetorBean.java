package bean;

import model.Setor;

public class SetorBean extends BaseBean<Setor>{

	public SetorBean() {
		super();
	}

	@Override
	protected Setor newInstance() {
		return new Setor();
	}

}
