package edu.gatech.cs4400.bean;

import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;

import edu.gatech.cs4400.bo.User;

public class InstructorPasswdBean {
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	private String oldpassword = "";
	private String newpassword1 = "";
	private String newpassword2 = "";
	
	private String instructorid = null;
	
	public InstructorPasswdBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	
	public void instructor_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.instructorid   = (String) params.get("instructorid");
	}

	public String changePassword() {
		Object[] instructorArray = DB.getSession().createQuery("from User i where i.id = '" + this.instructorid + "'").list().toArray();
		User thisInstructor = (User)instructorArray[0];
		if(thisInstructor.getPassword().equals(this.oldpassword) && this.newpassword1.equals(this.newpassword2) && !this.newpassword1.equals("")) {
			DB.begintx();
			thisInstructor.setPassword(this.newpassword1);
			DB.txcommit();
			return "back";
		} else {
			return null;
		}
	}
	
	
	
	
	public String getNewpassword1() {
		return newpassword1;
	}


	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}


	public String getNewpassword2() {
		return newpassword2;
	}


	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}


	public String getOldpassword() {
		return oldpassword;
	}


	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	
	
	
	
}
