package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerCashreturndetails generated by hbm2java
 */
public class VtigerCashreturndetails implements java.io.Serializable {

	private int id;
	private int cashreceiptid;
	private String amountpaid;
	private String datereceived;
	private String paymentmethod;
	private String bankname;
	private Integer checkno;
	private String checkdate;

	public VtigerCashreturndetails() {
	}

	public VtigerCashreturndetails(int id, int cashreceiptid,
			String amountpaid, String paymentmethod) {
		this.id = id;
		this.cashreceiptid = cashreceiptid;
		this.amountpaid = amountpaid;
		this.paymentmethod = paymentmethod;
	}

	public VtigerCashreturndetails(int id, int cashreceiptid,
			String amountpaid, String datereceived, String paymentmethod,
			String bankname, Integer checkno, String checkdate) {
		this.id = id;
		this.cashreceiptid = cashreceiptid;
		this.amountpaid = amountpaid;
		this.datereceived = datereceived;
		this.paymentmethod = paymentmethod;
		this.bankname = bankname;
		this.checkno = checkno;
		this.checkdate = checkdate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCashreceiptid() {
		return this.cashreceiptid;
	}

	public void setCashreceiptid(int cashreceiptid) {
		this.cashreceiptid = cashreceiptid;
	}

	public String getAmountpaid() {
		return this.amountpaid;
	}

	public void setAmountpaid(String amountpaid) {
		this.amountpaid = amountpaid;
	}

	public String getDatereceived() {
		return this.datereceived;
	}

	public void setDatereceived(String datereceived) {
		this.datereceived = datereceived;
	}

	public String getPaymentmethod() {
		return this.paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Integer getCheckno() {
		return this.checkno;
	}

	public void setCheckno(Integer checkno) {
		this.checkno = checkno;
	}

	public String getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

}
