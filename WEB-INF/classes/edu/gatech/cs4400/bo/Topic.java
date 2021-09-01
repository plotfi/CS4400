package edu.gatech.cs4400.bo;

import edu.gatech.cs4400.bl.*;

public class Topic{
	private Integer    id      = null;
	private String     name 	= null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getTotal() {
		DBSessionManager sm = new DBSessionManager();
		DataBackend      DB = sm.fetchHandel();
		Object[] dataArray = DB.getSession().createQuery("from Question i where i.ftopic = '" + this.id + "'").list().toArray();
		DB.close_session();
		return dataArray.length;
	}
}
