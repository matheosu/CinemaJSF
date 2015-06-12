package bean;

import java.util.List;

import model.BaseModel;

public interface IBean<T extends BaseModel> {
	
	/* Getters and Setters */
	public abstract T getInstance();

	public abstract void setInstance(T instance);

	public abstract List<T> getInstances();

	
	/* CRUD */
	public abstract String list();
	
	public abstract String create();

	public abstract String save();
	
	public abstract String edit();
	
	public abstract String delete();
	
	public abstract String back();
	

}
