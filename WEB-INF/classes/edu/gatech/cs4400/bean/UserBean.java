package edu.gatech.cs4400.bean;

import java.util.List;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Instructor;
import edu.gatech.cs4400.bo.User;


public class UserBean {
	
	private String  username = "";
	private String  password = "";
	private String  firstname = "";
	private String  lastname = "";

	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public UserBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public List getListTuples() {
		DB.begintx();
		DB.txcommit();
		//DB.flush_objects();
		return DB.DBTableToListObject("Instructor");
	} 
	
	
	public String add() {
		DB.begintx();
		Instructor userToAdd = new Instructor();
		User       userInfo  = new User();

		userInfo.setPassword(this.password);
		userInfo.setUsername(this.username);
		
		
		userToAdd.setFirstname(this.firstname);
		userToAdd.setLastname(this.lastname);
		
		userToAdd.setFuser(userInfo);
		
		DB.addElement(userInfo);
		DB.addElement(userToAdd);

		this.username  = "";
		this.password  = "";

		DB.txcommit();
		
		return null;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	
}