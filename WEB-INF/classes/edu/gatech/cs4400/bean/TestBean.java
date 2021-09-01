package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Course;
import edu.gatech.cs4400.bo.Test;
import edu.gatech.cs4400.bo.Section;

public class TestBean {

	private String title= "";
	private String courseid = null;
	private List testlist = null;

	
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public TestBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	
	public void course_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.courseid   = (String) params.get("courseid");
		System.out.println("The transfered courseid is: " + this.courseid);
	} 
	


	public List getTestByCourse() {
		DB.begintx();
		DB.txcommit();
		return DB.getSession().createQuery("from Test i where i.course.courseID = '" + this.courseid + "'").list();
	}
	
	public String add() {
		DB.begintx();
		Object[] dataArray = DB.getSession().createQuery("from Course i where i.courseID = '" + this.courseid + "'").list().toArray();
		Test testToAdd = new Test();
		testToAdd.setTitle(this.title);
		testToAdd.setCourse((Course)dataArray[0]);
		DB.addElement(testToAdd);		
		this.title = "";
		DB.txcommit();
		return null;
	}

	public String getTitle() {
		return title;
	} 
	public void setTitle(String title) {
		this.title = title;
	}	
}
