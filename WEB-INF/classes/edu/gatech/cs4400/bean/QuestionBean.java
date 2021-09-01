package edu.gatech.cs4400.bean;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;

public class QuestionBean {
	
	protected DBSessionManager sm = null;
	@SuppressWarnings("unused")
	protected DataBackend      DB = null;
	protected String beanType = "";
	protected List   listTuples = null;
	
	public QuestionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public List getAllQuestionAndAnswer() {
		List mcquestions = DB.DBTableToListObject("MCQuestion");
		List tfquestions = DB.DBTableToListObject("TFQuestion");
		List allquestions = new ArrayList();
		
		allquestions.addAll(mcquestions);
		allquestions.addAll(tfquestions);
		
		return allquestions;
	}
	
}
