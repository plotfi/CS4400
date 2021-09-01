package edu.gatech.cs4400.bo;

import java.util.List;

public class Section {
	private Integer sectionID = null;
	private Integer scoreperQ = null;
	private String  title     = null;
	private Test    test      = null;
	
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public Integer getScoreperQ() {
		return scoreperQ;
	}
	public void setScoreperQ(Integer scoreperQ) {
		this.scoreperQ = scoreperQ;
	}
	
	public Integer getSectionID() {
		return sectionID;
	}
	public void setSectionID(Integer sectionID) {
		this.sectionID = sectionID;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Integer getScore() {
		return null; // Get this as an aggregate of the questions in this test.
	}	
	
	public String toString () {
		return this.scoreperQ + " " + this.test;
	}
}
