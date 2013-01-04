package com.vendhaq.models;

// Generated Dec 13, 2012 3:37:45 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

/**
 * VtigerSalesman generated by hbm2java
 */
public class VtigerSalesman implements java.io.Serializable {

	private int id;
	private int userid;
	private int invoiceid;
	private BigDecimal amount;
	private Date date;

	public VtigerSalesman() {
	}

	public VtigerSalesman(int id, int userid, int invoiceid, BigDecimal amount) {
		this.id = id;
		this.userid = userid;
		this.invoiceid = invoiceid;
		this.amount = amount;
	}

	public VtigerSalesman(int id, int userid, int invoiceid, BigDecimal amount, Date date) {
		this.id = id;
		this.userid = userid;
		this.invoiceid = invoiceid;
		this.amount = amount;
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getInvoiceid() {
		return this.invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
