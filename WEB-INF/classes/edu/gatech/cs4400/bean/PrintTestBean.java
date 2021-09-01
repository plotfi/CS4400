package edu.gatech.cs4400.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Question;
import edu.gatech.cs4400.bo.QuestionSectionJoin;
import edu.gatech.cs4400.bo.Section;
import edu.gatech.cs4400.bo.Test;
import edu.gatech.cs4400.util.WrappedString;

public class PrintTestBean {
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	private String testid = null;

	
	public PrintTestBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	
	public void test_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.testid   = (String) params.get("testid");
	} 
	
	
	public List getTestPrintOut() {
		
		List testPrintOut = new ArrayList();
		Object[] sectionArray = DB.getSession().createQuery("from Section i where i.test.testID = '" + this.testid + "'").list().toArray();
		Object[] testArray = DB.getSession().createQuery("from Test i where i.testID = '" + this.testid + "'").list().toArray();
		testPrintOut.add(new WrappedString("-- Test: " + ((Test)testArray[0]).getTitle() + "--"));
		
		for(int i=0; i< sectionArray.length; i++) {
			System.out.println(((Section)sectionArray[i]).getTitle());
			testPrintOut.add(new WrappedString("-- Section: " + ((Section)sectionArray[i]).getTitle() + "--" ));
			Object[] qsjArray = DB.getSession().createQuery("from QuestionSectionJoin i where i.section = '" + ((Section)sectionArray[i]).getSectionID() + "'").list().toArray();
			//testPrintOut.add(new WrappedString("added" + qsjArray.length + "questions"));
			for(int k=0; k<qsjArray.length; k++) {
				System.out.println(((Section)sectionArray[i]).getTitle());
				testPrintOut.add(new WrappedString("* Question " + (k+1) + " " + ((QuestionSectionJoin)qsjArray[k]).getQuestion().getQtext()));
			}
		}
		
		return testPrintOut;
	}
	
}
