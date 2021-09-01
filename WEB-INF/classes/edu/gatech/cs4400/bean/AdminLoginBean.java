package edu.gatech.cs4400.bean;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Admin;
import edu.gatech.cs4400.bo.User;

public class AdminLoginBean {

	private String username = "";
	private String password = "";
	private Integer adminid = null;
	private String nextpage = null;
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public AdminLoginBean() {
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
		
		if(null != adminid) {
			return this.nextpage;
		}
		
		Object[] userArray = DB.getSession().createQuery("from User i where i.username = '" + this.username + "'" + " and " +  "i.password = '" + this.password + "'").list().toArray();
		Object[] adminArray= null;
		if(userArray.length >= 1) {
			adminArray = DB.getSession().createQuery("from Admin i where i.userID = '" + ((User)userArray[0]).getId() +"'").list().toArray();
			
			if(adminArray.length >= 1) {
				adminid = ((Admin)adminArray[0]).getUserID();
				System.out.println("login -> adminid: " + adminid );
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
		this.adminid = null;
		this.username = "";
		this.password = "";
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	
	
	
}
