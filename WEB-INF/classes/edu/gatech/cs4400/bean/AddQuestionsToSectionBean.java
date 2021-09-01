package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Course;
import edu.gatech.cs4400.bo.Question;
import edu.gatech.cs4400.bo.QuestionSectionJoin;
import edu.gatech.cs4400.bo.Section;
import edu.gatech.cs4400.bo.Test;

public class AddQuestionsToSectionBean {
	
	private String sectionid   = null;
	private String questionid  = "";
	private String questionid_toremove  = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public AddQuestionsToSectionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public void section_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.sectionid   = (String) params.get("sectionid");
	}
	
	public void question_remove_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.questionid_toremove   = (String) params.get("quesitonid");
		//Object[] questionArray = DB.getSession().createQuery("from Section i where i.sectionID = '" + this.sectionid + "'").list().toArray();
		
		System.out.println("question section question id: " + this.questionid_toremove);
		
		
		Object[] questionsectionArray = DB.getSession().createQuery("from QuestionSectionJoin i where i.question.questionID = '" + this.questionid_toremove + "'").list().toArray();
		
		//for(int i=0; i<questionsectionArray.length;i++) {
		//	DB.begintx();
		//	DB.removeElement((QuestionSectionJoin)questionsectionArray[i]);
		//	DB.txcommit();
		//}
		
		
		QuestionSectionJoin toremove = (QuestionSectionJoin)questionsectionArray[0];
	
		
		
		DB.begintx();
		DB.removeElement(toremove);
		DB.txcommit();
	}
	
	public void question_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.questionid = (String) params.get("questionid");
	}

	public String getQuestionid() {
		return questionid;
	} public void setQuestionid(String questionid) {
		this.questionid = questionid;
	} 
	
	public List getAllQuestionsForSection() { // Returns a list of QuestionSectionJoins
		return DB.getSession().createQuery("from QuestionSectionJoin i where i.section.sectionID = '" + this.sectionid + "'").list();
	}
	
	public String add() {
		DB.begintx();
		Object[] questionArray = DB.getSession().createQuery("from Question i where i.questionID = '" + this.questionid + "'").list().toArray();
		Object[] sectionArray  = DB.getSession().createQuery("from Section i where i.sectionID = '" + this.sectionid + "'").list().toArray();
		QuestionSectionJoin qsjToAdd = new QuestionSectionJoin();
		qsjToAdd.setQuestion((Question)questionArray[0]);
		qsjToAdd.setSection((Section)sectionArray[0]);
		DB.addElement(qsjToAdd);		
		this.questionid = "";
		DB.txcommit();
		return null;
	}
	
}
