package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.MCQuestion;
import edu.gatech.cs4400.bo.TFQuestion;

public class EditQuestionBean {
	
	private String type = null;
	
	private String questionid = null;
	
	private List tfarray    = null;
	private List multiarray = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;

	@SuppressWarnings("unchecked")
	public EditQuestionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	
	public void question_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.questionid   = (String) params.get("questionid");
		
		tfarray    = DB.getSession().createQuery("from TFQuestion i where i.fquestion = '" + this.questionid + "'").list();
		multiarray = DB.getSession().createQuery("from MCQuestion i where i.fquestion = '" + this.questionid + "'").list();
		
		
		System.out.println(this.questionid + " " + multiarray.size()  + " " + tfarray.size() );
		
		if (multiarray.size() >= 1) {
			type = "multiquestion";
		} else if (tfarray.size() >= 1) {
			type = "truefalsequestion";
		} 
		
	}


	public String getAnswer() {
		if(type.equals("truefalsequestion")) {
			return ((TFQuestion)tfarray.get(0)).getAnswer().toString();
		} else if (type.equals("multiquestion")) {
			return ((MCQuestion)multiarray.get(0)).getAnswer();
		} else {
			return null;
		}
	} public void setAnswer(String answer) {
		try {
			DB.begintx();
			if(type.equals("truefalsequestion")) {
				((TFQuestion)tfarray.get(0)).setAnswer(new Integer(answer));
			} else if (type.equals("multiquestion")) {
				((MCQuestion)multiarray.get(0)).setAnswer(answer);
			} 
			System.out.println("Trying to edit the answer: " + answer);
			DB.txcommit();
		} catch (Exception e) {
			//TODO
		}
		
	}

	public String getQuestion() {
		if(type.equals("truefalsequestion")) {
			return ((TFQuestion)tfarray.get(0)).getFquestion().getQtext();
		} else if (type.equals("multiquestion")) {
			return ((MCQuestion)multiarray.get(0)).getFquestion().getQtext();
		} else {
			return null;
		}
	} public void setQuestion(String question) {
		try {
			DB.begintx();
			if(type.equals("truefalsequestion")) {
				((TFQuestion)tfarray.get(0)).getFquestion().setQtext(question);
			} else if (type.equals("multiquestion")) {
				((MCQuestion)multiarray.get(0)).getFquestion().setQtext(question);
			} 
			System.out.println("Trying to edit the question: " + question);
			DB.txcommit();
		} catch (Exception e) {
			//TODO
		}
	}

	public List getWrongAnswers() {
		DB.begintx();
		List toRet =  DB.getSession().createQuery("from WrongAnswer i where i.fmcq.id = '" + this.questionid + "'").list();
		DB.txcommit();
		return toRet;
	} /*public void setWrongAnswers(List wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	} */
	
	public boolean getIsmultiq() {
		if(this.type.equals("multiquestion")) {
			return true;
		} else {
			return false;
		}
	}
}
