package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Course;
import edu.gatech.cs4400.bo.Instructor;

public class CourseBean {
	
	private String section       = "";
	private String year          = "";
	private String courseNumber  = "";
	private String semester      = "";
	private String title         = "";
	private List   courselist    = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	private String instructorid = null;
	
	public CourseBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	
	public void instructor_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.instructorid   = (String) params.get("instructorid");
	}
	
	public List getListTuples() {
		DB.begintx();
		DB.txcommit();
		return DB.getSession().createQuery("from Course i where i.instructor.userID = '" + this.instructorid + "'").list();
	} 
	
	public String add() {
		DB.begintx();
		System.out.println("InstructorID: " + this.instructorid);
		Object[] dataArray = DB.getSession().createQuery("from Instructor i where i.userID = '" + this.instructorid + "'").list().toArray();
		System.out.println("Instructor: " + ((Instructor)dataArray[0]));
		Course courseToAdd = new Course();
		courseToAdd.setSection(this.section);
		courseToAdd.setYear(this.year);
		courseToAdd.setCourseNumber(this.courseNumber);
		courseToAdd.setSemester(this.semester);
		courseToAdd.setTitle(this.title);
		courseToAdd.setInstructor(((Instructor)dataArray[0]));
		DB.addElement(courseToAdd);
		this.section        = "";
		this.year           = "";
		this.courseNumber   = "";
		this.semester       = "";
		this.title          = "";
		DB.txcommit();
		//DB.getSession().flush();
		return null;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
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
	
}