package edu.gatech.cs4400.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.tombrus.util.DB;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.MCQuestion;
import edu.gatech.cs4400.bo.Question;
import edu.gatech.cs4400.bo.Topic;
import edu.gatech.cs4400.bo.WrongAnswer;
import edu.gatech.cs4400.util.WrappedString;

public class AddMultiQuestionBean {
	private String question = "";
	private String answer   = "";
	private String topicid = null;
	
	private List wrongAnswers = new ArrayList<WrappedString>();
	
	
	protected DBSessionManager sm = null;
	@SuppressWarnings("unused")
	protected DataBackend      DB = null;

	@SuppressWarnings("unchecked")
	public AddMultiQuestionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
        
        for(int i=0;i<8; i++) {
        	wrongAnswers.add(new WrappedString(""));
        }
	}
	
	public void topic_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.topicid   = (String) params.get("topicid");
		//get all the questions by foreign key for topic.
	}
	
	
	// PROPERTY answer 
	public String getAnswer() {
		return answer;
	} public void setAnswer(String answer) {
		this.answer = answer;
	}
	// PROPERTY question
	public String getQuestion() {
		return question;
	} public void setQuestion(String question) {
		this.question = question;
	}
	// PROPERTY wrongAnswers
	public List getWrongAnswers() {
		return wrongAnswers;
	} 
	
	public String dropWrongAnswer() {
		if(wrongAnswers.size() > 1) {
			wrongAnswers.remove(wrongAnswers.size() - 1);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String addWrongAnswer() {
		wrongAnswers.add(new WrappedString(""));
		return null;
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
		MCQuestion   mcqToAdd    = new MCQuestion();
		
		qToAdd.setQtext(this.question);
		qToAdd.setFtopic((Topic)dataArray[0]);
		mcqToAdd.setAnswer(this.answer);
		mcqToAdd.setFquestion(qToAdd);
		
		DB.addElement(qToAdd);
		DB.addElement(mcqToAdd);
		
		for(int i=0; i<this.wrongAnswers.size(); i++) {
			System.out.println("Adding Wrong Answer in the bean.");
			WrongAnswer  wrongToAdd  = new WrongAnswer();
			wrongToAdd.setWrongtext((String)((WrappedString)wrongAnswers.get(i)).getWrapped());
			wrongToAdd.setFmcq(mcqToAdd);
			DB.addElement(wrongToAdd);
		}
		DB.txcommit();
		
		return "goback";
	}
	
}
