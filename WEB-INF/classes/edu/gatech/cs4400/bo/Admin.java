package edu.gatech.cs4400.bo;

public class Admin {

	private Integer userID = null;
	private User    fuser   = null;
	
	public User getFuser() {
		return fuser;
	}
	public void setFuser(User fuser) {
		this.fuser = fuser;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
}
