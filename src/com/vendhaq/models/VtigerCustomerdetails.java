package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerCustomerdetails generated by hbm2java
 */
public class VtigerCustomerdetails implements java.io.Serializable {

	private int customerid;
	private String portal;
	private String supportStartDate;
	private String supportEndDate;

	public VtigerCustomerdetails() {
	}

	public VtigerCustomerdetails(int customerid) {
		this.customerid = customerid;
	}

	public VtigerCustomerdetails(int customerid, String portal,
			String supportStartDate, String supportEndDate) {
		this.customerid = customerid;
		this.portal = portal;
		this.supportStartDate = supportStartDate;
		this.supportEndDate = supportEndDate;
	}

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getPortal() {
		return this.portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getSupportStartDate() {
		return this.supportStartDate;
	}

	public void setSupportStartDate(String supportStartDate) {
		this.supportStartDate = supportStartDate;
	}

	public String getSupportEndDate() {
		return this.supportEndDate;
	}

	public void setSupportEndDate(String supportEndDate) {
		this.supportEndDate = supportEndDate;
	}

}
