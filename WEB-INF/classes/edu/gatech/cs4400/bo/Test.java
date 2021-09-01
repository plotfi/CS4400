package edu.gatech.cs4400.bo;

public class Test {

	private String  title   = null;
	private Integer testID = null;
	private Course  course  = null;
	
	public Integer getTestID() {
		return testID;
	}
	public void setTestID(Integer testID) {
		this.testID = testID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Integer getScore() {
		return null; // Get this as an aggregate of the questions in this test.
	}
	
	public String toString () {
		return this.title + this.course;
	}
}

