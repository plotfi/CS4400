package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.QuestionSectionJoin;
import edu.gatech.cs4400.bo.Section;
import edu.gatech.cs4400.bo.Topic;
import edu.gatech.cs4400.util.WrappedString;

public class ReportBean {
	
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	private String testid    = null;
	
	public ReportBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public void test_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.testid   = (String) params.get("testid");
	} 
	
	public List getReport() {

		
		return null;
	}
}
