package edu.gatech.cs4400.bo;

public class WrongAnswer {

	private Integer    id        = null;
	private MCQuestion fmcq      = null;
	private String     wrongtext = null;
	
	// PROPERTY id
	public Integer getId() {
		return id;
	} public void setId(Integer id) {
		this.id = id;
	}
	
	// PROPERTY fmcq
	public MCQuestion getFmcq() {
		return fmcq;
	} public void setFmcq(MCQuestion fmcq) {
		this.fmcq = fmcq;
	}
	
	// PROPERTY wrongtext
	public String getWrongtext() {
		return wrongtext;
	} public void setWrongtext(String wrongtext) {
		this.wrongtext = wrongtext;
	}
}
