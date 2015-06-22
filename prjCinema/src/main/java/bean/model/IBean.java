package bean.model;

import java.util.List;

import model.BaseModel;

public interface IBean<M extends BaseModel> {
	
	/* Getters and Setters */
	public abstract M getInstance();

	public abstract void setInstance(M instance);

	public abstract List<M> getInstances();

	
	/* CRUD */
	public abstract String list();
	
	public abstract String create();

	public abstract String save();
	
	public abstract String edit();
	
	public abstract String delete();
	
	public abstract String back();
	

}
