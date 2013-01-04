package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerAccountbillads generated by hbm2java
 */
public class VtigerAccountbillads implements java.io.Serializable {

	private int accountaddressid;
	private String billCity;
	private String billCode;
	private String billCountry;
	private String billState;
	private String billStreet;
	private String billPobox;

	public VtigerAccountbillads() {
	}

	public VtigerAccountbillads(int accountaddressid) {
		this.accountaddressid = accountaddressid;
	}

	public VtigerAccountbillads(int accountaddressid, String billCity,
			String billCode, String billCountry, String billState,
			String billStreet, String billPobox) {
		this.accountaddressid = accountaddressid;
		this.billCity = billCity;
		this.billCode = billCode;
		this.billCountry = billCountry;
		this.billState = billState;
		this.billStreet = billStreet;
		this.billPobox = billPobox;
	}

	public int getAccountaddressid() {
		return this.accountaddressid;
	}

	public void setAccountaddressid(int accountaddressid) {
		this.accountaddressid = accountaddressid;
	}

	public String getBillCity() {
		return this.billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillCode() {
		return this.billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getBillCountry() {
		return this.billCountry;
	}

	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}

	public String getBillState() {
		return this.billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillStreet() {
		return this.billStreet;
	}

	public void setBillStreet(String billStreet) {
		this.billStreet = billStreet;
	}

	public String getBillPobox() {
		return this.billPobox;
	}

	public void setBillPobox(String billPobox) {
		this.billPobox = billPobox;
	}

}
