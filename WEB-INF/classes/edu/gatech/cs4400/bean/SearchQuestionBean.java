package edu.gatech.cs4400.bean;

import java.util.List;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;

public class SearchQuestionBean {
	private String  questionType = "both"; //tf, mult, or both
	private String  keyword      = "";
	private Integer questionid   = 0;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public SearchQuestionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public String getKeyword() {
		return keyword;
	} public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Integer getQuestionid() {
		return questionid;
	} public void setQuestionid(Integer questionid) {
		this.questionid = questionid;
	}
	
	public String getQuestionType() {
		return questionType;
	} public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	public List getSearchResults() {
		if(0 == questionid && !(this.keyword.equals(""))) {
			if(questionType.equals("both")) {
				return DB.getSession().createQuery("from Question i where i.Qtext like '%" + this.keyword +"%'").list();
			} else if (questionType.equals("tf")) {
				return DB.getSession().createQuery("from TFQuestion i where i.fquestion.Qtext like '%" + this.keyword +"%'").list();
			} else if (questionType.equals("mult")) {
				return DB.getSession().createQuery("from MCQuestion i where i.fquestion.Qtext like '%" + this.keyword +"%'").list();
			} 
			return DB.getSession().createQuery("from Question i where i.Qtext like '%" + this.keyword +"%'").list();
			
		} else if (questionid > 0) {
			if(questionType.equals("both")) {
				return DB.getSession().createQuery("from Question i where i.questionID = '" + this.questionid + "'").list();
			} else if (questionType.equals("tf")) {
				return DB.getSession().createQuery("from TFQuestion i where i.fquestion.questionID = '" + this.questionid + "'").list();
			} else if (questionType.equals("mult")) {
				return DB.getSession().createQuery("from MCQuestion i where i.fquestion.questionID = '" + this.questionid + "'").list();
			} 
			return DB.getSession().createQuery("from Question i where i.questionID = '" + this.questionid + "'").list();
		} else {
			return null;
		}
	}
}
