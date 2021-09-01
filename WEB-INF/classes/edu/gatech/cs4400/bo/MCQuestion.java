package edu.gatech.cs4400.bo;

public class MCQuestion {
	private Integer  id        = null;
	private Question fquestion = null;
	private String   answer    = null;
	
	// PROPERTY answer
	public String getAnswer() {
		return answer;
	} public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Integer getId() {
		return id;
	} public void setId(Integer id) {
		this.id = id;
	}
	
	// PROPERTY fquestion
	public Question getFquestion() {
		return fquestion;
	} public void setFquestion(Question fquestion) {
		this.fquestion = fquestion;
	}
	
	public String toString () {
		return this.answer;
	}
	
	
	
	
	public String getQtext() {
		return this.fquestion.getQtext();
	}
	public void setQtext(String qtext) {
		this.fquestion.setQtext(qtext);
	}
	
	
	public Integer getQuestionID() {
		return this.fquestion.getQuestionID();
	}
	
	
}
