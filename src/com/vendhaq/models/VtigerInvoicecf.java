package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerInvoicecf generated by hbm2java
 */
public class VtigerInvoicecf implements java.io.Serializable {

	private int invoiceid;
	private String cf637;
	private String cf638;
	private String cf639;
	private String cf640;

	public VtigerInvoicecf() {
	}

	public VtigerInvoicecf(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public VtigerInvoicecf(int invoiceid, String cf637, String cf638,
			String cf639, String cf640) {
		this.invoiceid = invoiceid;
		this.cf637 = cf637;
		this.cf638 = cf638;
		this.cf639 = cf639;
		this.cf640 = cf640;
	}

	public int getInvoiceid() {
		return this.invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getCf637() {
		return this.cf637;
	}

	public void setCf637(String cf637) {
		this.cf637 = cf637;
	}

	public String getCf638() {
		return this.cf638;
	}

	public void setCf638(String cf638) {
		this.cf638 = cf638;
	}

	public String getCf639() {
		return this.cf639;
	}

	public void setCf639(String cf639) {
		this.cf639 = cf639;
	}

	public String getCf640() {
		return this.cf640;
	}

	public void setCf640(String cf640) {
		this.cf640 = cf640;
	}

}
