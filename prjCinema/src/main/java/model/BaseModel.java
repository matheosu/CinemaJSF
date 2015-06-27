package model;

import java.io.Serializable;

public interface BaseModel extends Serializable{

	public abstract Long getId();
	
	public abstract void setId(Long id);
}
