package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;

public class QuestionByTopicBean {
	
	private String topicid = null;
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public QuestionByTopicBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public void topic_filter_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.topicid   = (String) params.get("topicIdFilter");
		//get all the questions by foreign key for topic.
	}
	
	public List getQuestionsByTopic() {
		DB.begintx();
		DB.txcommit();
		System.out.println("getting questions by topic in qtbean");
		return DB.getSession().createQuery("from Question i where i.ftopic = '" + this.topicid + "'").list();
	}

	public String getTopicid() {
		return topicid;
	}
}
