package com.vendhaq.models;

// Generated Dec 13, 2012 3:37:45 PM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerContactroyality generated by hbm2java
 */
public class VtigerContactroyality implements java.io.Serializable {

	private int royalityid;
	private int contactid;
	private String royalitynumber;
	private int royalitycount;

	public VtigerContactroyality() {
	}

	public VtigerContactroyality(int royalityid, int contactid, String royalitynumber, int royalitycount) {
		this.royalityid = royalityid;
		this.contactid = contactid;
		this.royalitynumber = royalitynumber;
		this.royalitycount = royalitycount;
	}

	public int getRoyalityid() {
		return this.royalityid;
	}

	public void setRoyalityid(int royalityid) {
		this.royalityid = royalityid;
	}

	public int getContactid() {
		return this.contactid;
	}

	public void setContactid(int contactid) {
		this.contactid = contactid;
	}

	public String getRoyalitynumber() {
		return this.royalitynumber;
	}

	public void setRoyalitynumber(String royalitynumber) {
		this.royalitynumber = royalitynumber;
	}

	public int getRoyalitycount() {
		return this.royalitycount;
	}

	public void setRoyalitycount(int royalitycount) {
		this.royalitycount = royalitycount;
	}

}
