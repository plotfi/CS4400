package edu.gatech.cs4400.bo;

public class User{
	private Integer    id           = null;
	private String     username 	= null;
	private String     password 	= null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String toString () {
		return this.id      + " " + this.username      + " " + 
		       this.password;   
	}
	
}
