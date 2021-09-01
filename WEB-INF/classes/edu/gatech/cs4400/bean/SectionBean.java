package edu.gatech.cs4400.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import edu.gatech.cs4400.bl.DBSessionManager;
import edu.gatech.cs4400.bl.DataBackend;
import edu.gatech.cs4400.bo.Section;
import edu.gatech.cs4400.bo.Test;
import edu.gatech.cs4400.bo.QuestionSectionJoin;

public class SectionBean {

	private int    scoreperQ = 0;
	private String title     = "";
	private String testid    = null;
	private List   sectionlist = null;
	private String sectionid = null;
	
	
	private DBSessionManager sm = null;
	@SuppressWarnings("unused")
	private DataBackend      DB = null;
	
	public SectionBean() {
        this.sm                  = new DBSessionManager();
        this.DB                  = sm.fetchHandel();
	}
	
	public void test_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.testid   = (String) params.get("testid");
		System.out.println("The transfered testid is: " + this.testid);
	} 

	public void section_listener(javax.faces.event.ActionEvent actionEvent) {
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		Map params = facesContext.getExternalContext().getRequestParameterMap(); 
		this.sectionid   = (String) params.get("sectionid");
		Object[] sectionArray = DB.getSession().createQuery("from Section i where i.sectionID = '" + this.sectionid + "'").list().toArray();
		
		Object[] questionsectionArray = DB.getSession().createQuery("from QuestionSectionJoin i where i.section.sectionID = '" + this.sectionid + "'").list().toArray();
		
		for(int i=0; i<questionsectionArray.length;i++) {
			DB.begintx();
			DB.removeElement((QuestionSectionJoin)questionsectionArray[i]);
			DB.txcommit();
		}
		
		
		Section toremove = (Section)sectionArray[0];
	
		
		
		DB.begintx();
		DB.removeElement(toremove);
		DB.txcommit();
	}
	
	
	public List getSectionByTest() {
		DB.begintx();
		DB.txcommit();
		return DB.getSession().createQuery("from Section i where i.test.testID = '" + this.testid + "'").list();
	}
	
	public String add() {
		Object[] dataArray = DB.getSession().createQuery("from Test i where i.testID = '" + this.testid + "'").list().toArray();
		Section sectionToAdd = new Section();
		sectionToAdd.setScoreperQ(this.scoreperQ);
		sectionToAdd.setTitle(this.title);
		sectionToAdd.setTest((Test)dataArray[0]);
		DB.addElement(sectionToAdd);
		this.scoreperQ = 0;
		this.title = "";
		return null;
	}

	public int getScoreperQ() {
		return scoreperQ;
	}

	public void setScoreperQ(int scoreperQ) {
		this.scoreperQ = scoreperQ;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
