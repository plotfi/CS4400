package edu.gatech.cs4400.bo;

public class Question {

	private Integer questionID = null;
	private String  qtext      = null;
	private Topic   ftopic     = null;
	
	public Integer getQuestionID() {
		return questionID;
	}
	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}
	public String getQtext() {
		return qtext;
	}
	public void setQtext(String qtext) {
		this.qtext = qtext;
	}
	
	public Topic getFtopic() {
		return ftopic;
	}
	public void setFtopic(Topic ftopic) {
		this.ftopic = ftopic;
	}
	
	public String toString () {
		return this.qtext;
	}
}
