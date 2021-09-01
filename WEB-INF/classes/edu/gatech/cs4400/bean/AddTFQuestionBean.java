package edu.gatech.cs4400.bean;

import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Question;
import edu.gatech.cs4400.bo.TFQuestion;
import edu.gatech.cs4400.bo.Topic;


public class AddTFQuestionBean {
	private String question = "";
	private Integer answer  = 0;
	private String topicid = null;
	
	protected DBSessionManager sm = null;
	@SuppressWarnings("unused")
	protected DataBackend      DB = null;

	@SuppressWarnings("unchecked")
	public AddTFQuestionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
        
	}
	
	public void topic_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.topicid   = (String) params.get("topicid");
		//get all the questions by foreign key for topic.
	}
	
	// PROPERTY answer 
	public Integer getAnswer() {
		return answer;
	} public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	// PROPERTY question
	public String getQuestion() {
		return question;
	} public void setQuestion(String question) {
		this.question = question;
	}
	
	public String add() {
		DB.begintx();
		Object[] dataArray = DB.getSession().createQuery("from Topic i where i.id = '" + this.topicid + "'").list().toArray();

		//FakeMCQuestion questionToAdd = new FakeMCQuestion();
		//questionToAdd.setAnswer(this.answer);
		//questionToAdd.setQtext(this.question);
		//questionToAdd.setWrong(this.wrongAnswers);
		//questionToAdd.setTopic((Topic)dataArray[0]);
		
		//questionToAdd.commitToDatabase(DB);
		
		// start cp
		Question     qToAdd      = new Question();
		TFQuestion   tfToAdd     = new TFQuestion();
		
		qToAdd.setQtext(this.question);
		qToAdd.setFtopic((Topic)dataArray[0]);
		tfToAdd.setAnswer(this.answer);
		tfToAdd.setFquestion(qToAdd);
		
		DB.addElement(qToAdd);
		DB.addElement(tfToAdd);
		
		DB.txcommit();
		return "goback";
	}
	
}
