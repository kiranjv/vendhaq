package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerUsertype generated by hbm2java
 */
public class VtigerUsertype implements java.io.Serializable {

	private int usertypeid;
	private String usertype;
	private int sortorderid;
	private int presence;

	public VtigerUsertype() {
	}

	public VtigerUsertype(int usertypeid, String usertype, int sortorderid,
			int presence) {
		this.usertypeid = usertypeid;
		this.usertype = usertype;
		this.sortorderid = sortorderid;
		this.presence = presence;
	}

	public int getUsertypeid() {
		return this.usertypeid;
	}

	public void setUsertypeid(int usertypeid) {
		this.usertypeid = usertypeid;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public int getSortorderid() {
		return this.sortorderid;
	}

	public void setSortorderid(int sortorderid) {
		this.sortorderid = sortorderid;
	}

	public int getPresence() {
		return this.presence;
	}

	public void setPresence(int presence) {
		this.presence = presence;
	}

}
