package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerCategorygrouprel generated by hbm2java
 */
public class VtigerCategorygrouprel implements java.io.Serializable {

	private int categoryid;
	private String groupname;

	public VtigerCategorygrouprel() {
	}

	public VtigerCategorygrouprel(int categoryid) {
		this.categoryid = categoryid;
	}

	public VtigerCategorygrouprel(int categoryid, String groupname) {
		this.categoryid = categoryid;
		this.groupname = groupname;
	}

	public int getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
