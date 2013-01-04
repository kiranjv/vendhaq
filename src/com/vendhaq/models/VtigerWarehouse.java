package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerWarehouse generated by hbm2java
 */
public class VtigerWarehouse implements java.io.Serializable {

	private int warehouseid;
	private String warehouseName;
	private String address1;
	private String address2;
	private String street;
	private String city;
	private String state;
	private String country;
	private String contactPersonName;
	private String mobile;
	private String landline;
	private String identifier;
	private String linkto;
	private String code;

	public VtigerWarehouse() {
	}

	public VtigerWarehouse(int warehouseid) {
		this.warehouseid = warehouseid;
	}

	public VtigerWarehouse(int warehouseid, String warehouseName,
			String address1, String address2, String street, String city,
			String state, String country, String contactPersonName,
			String mobile, String landline, String identifier, String linkto,
			String code) {
		this.warehouseid = warehouseid;
		this.warehouseName = warehouseName;
		this.address1 = address1;
		this.address2 = address2;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.contactPersonName = contactPersonName;
		this.mobile = mobile;
		this.landline = landline;
		this.identifier = identifier;
		this.linkto = linkto;
		this.code = code;
	}

	public int getWarehouseid() {
		return this.warehouseid;
	}

	public void setWarehouseid(int warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLinkto() {
		return this.linkto;
	}

	public void setLinkto(String linkto) {
		this.linkto = linkto;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}