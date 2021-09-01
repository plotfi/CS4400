package edu.gatech.cs4400.bean;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;

import edu.gatech.cs4400.bo.Instructor;
import edu.gatech.cs4400.bo.User;

public class InstructorLoginBean {

	private String username = "";
	private String password = "";
	private Integer instructorid = null;
	private String nextpage = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public InstructorLoginBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String tryLogin() {
		
		if(null != instructorid) {
			return this.nextpage;
		}
		
		Object[] userArray = DB.getSession().createQuery("from User i where i.username = '" + this.username + "'" + " and " +  "i.password = '" + this.password + "'").list().toArray();
		Object[] instructorArray = null;
		if(userArray.length >= 1) {
			instructorArray = DB.getSession().createQuery("from Instructor i where i.userID = '" + ((User)userArray[0]).getId() +"'").list().toArray();
			
			if(instructorArray.length >= 1) {
				instructorid = ((Instructor)instructorArray[0]).getUserID();
				System.out.println("login -> Instructorid: " + instructorid );
				this.nextpage = "loginsuccess";
			} else {
				this.nextpage = "loginfailure";
			}
		
		} else {
			this.nextpage = "loginfailure";
		}
		

		
		return null;
	}

	public void clear_login() {
		this.instructorid = null;
		this.username = "";
		this.password = "";
	}
	
	public Integer getInstructorid() {
		return instructorid;
	}

	public void setInstructorid(Integer instructorid) {
		this.instructorid = instructorid;
	}
	
	
	
}
