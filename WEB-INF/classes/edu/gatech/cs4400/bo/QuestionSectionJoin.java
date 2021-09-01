package edu.gatech.cs4400.bo;

public class QuestionSectionJoin {

	private Question question = null;
	private Section  section  = null;
	private Integer  sqjID    = null;
	
	
	
	public Integer getSqjID() {
		return sqjID;
	}
	public void setSqjID(Integer sqjID) {
		this.sqjID = sqjID;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	public String toString() {
		return this.question + " " + this.section;
	}
}
