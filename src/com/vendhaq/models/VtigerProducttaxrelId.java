package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerProducttaxrelId generated by hbm2java
 */
public class VtigerProducttaxrelId implements java.io.Serializable {

	private int productid;
	private int taxid;
	private String taxpercentage;

	public VtigerProducttaxrelId() {
	}

	public VtigerProducttaxrelId(int productid, int taxid) {
		this.productid = productid;
		this.taxid = taxid;
	}

	public VtigerProducttaxrelId(int productid, int taxid, String taxpercentage) {
		this.productid = productid;
		this.taxid = taxid;
		this.taxpercentage = taxpercentage;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getTaxid() {
		return this.taxid;
	}

	public void setTaxid(int taxid) {
		this.taxid = taxid;
	}

	public String getTaxpercentage() {
		return this.taxpercentage;
	}

	public void setTaxpercentage(String taxpercentage) {
		this.taxpercentage = taxpercentage;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VtigerProducttaxrelId))
			return false;
		VtigerProducttaxrelId castOther = (VtigerProducttaxrelId) other;

		return (this.getProductid() == castOther.getProductid())
				&& (this.getTaxid() == castOther.getTaxid())
				&& ((this.getTaxpercentage() == castOther.getTaxpercentage()) || (this
						.getTaxpercentage() != null
						&& castOther.getTaxpercentage() != null && this
						.getTaxpercentage()
						.equals(castOther.getTaxpercentage())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProductid();
		result = 37 * result + this.getTaxid();
		result = 37
				* result
				+ (getTaxpercentage() == null ? 0 : this.getTaxpercentage()
						.hashCode());
		return result;
	}

}
