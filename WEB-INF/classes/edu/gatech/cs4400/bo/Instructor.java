package edu.gatech.cs4400.bo;

public class Instructor {
	private Integer userID = null;
	private User    fuser   = null;
	private String  firstname = null;
	private String  lastname  = null;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public User getFuser() {
		return fuser;
	}
	public void setFuser(User fuser) {
		this.fuser = fuser;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	
	public String toString() {
		return this.userID + " " + this.firstname + " " + this.lastname ;
	}
}
