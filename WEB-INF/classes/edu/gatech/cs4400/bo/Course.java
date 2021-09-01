package edu.gatech.cs4400.bo;

public class Course {

	private String     section      = null;
	private String     year         = null;
	private String     courseNumber = null;
	private String     semester     = null;
	private String     title        = null;
	private Integer    courseID     = null;
	private Instructor instructor   = null;
	
	public Integer getCourseID() {
		return courseID;
	}
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public String toString () {
		return this.section      + " " + this.year      + " " + 
		       this.courseNumber + " " + this.semester  + " " + 
		       this.title        + " " + instructor;   
	}
	
}

