package bean;

import model.Genero;

public class GeneroBean extends BaseBean<Genero>{

	public GeneroBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Genero newInstance() {
		return new Genero();
	}

}
