package edu.gatech.cs4400.bean;

import java.util.List;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Topic;

@SuppressWarnings("unused")
public class TopicBean {

	private String  name = "";
	private List topiclist = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public TopicBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public List getListTuples() {
		DB.begintx();
		DB.txcommit();
		return DB.DBTableToListObject("Topic");
	} 
	
	public String add() {
		Topic topicToAdd = new Topic();
		topicToAdd.setName(this.name);
		DB.addElement(topicToAdd);
		this.name = "";
		return null;
	}

	// PROPERTY name
	public String getName() {
		return name;
	} public void setName(String name) {
		this.name = name;
	}
	
	
	
}
