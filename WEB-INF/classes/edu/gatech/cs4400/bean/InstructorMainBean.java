package edu.gatech.cs4400.bean;

import java.util.Map;

import javax.faces.context.FacesContext;

public class InstructorMainBean {

	private String instructorid = null;
	
	public void instructor_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.instructorid   = (String) params.get("instructorid");
		System.out.println("main -> Instructorid: " + instructorid );
	}

	public String getInstructorid() {
		return instructorid;
	} public void setInstructorid(String instructorid) {
		this.instructorid = instructorid;
	} 
	
	
}
