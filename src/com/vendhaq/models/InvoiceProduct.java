package com.vendhaq.models;

public class InvoiceProduct {
	
	int invoiceid;
	int productid;
	String productname;
	String qty;
	String mrp;
	String vat;
	String bcode;
	
	
	/**
	 * @return the invoiceid
	 */
	public int getInvoiceid() {
		return invoiceid;
	}
	/**
	 * @param invoiceid the invoiceid to set
	 */
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}
	/**
	 * @param productid the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}
	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * @return the qty
	 */
	public String getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}
	/**
	 * @return the mrp
	 */
	public String getMrp() {
		return mrp;
	}
	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
	}
	/**
	 * @return the bcode
	 */
	public String getBcode() {
		return bcode;
	}
	/**
	 * @param bcode the bcode to set
	 */
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	
	
	@Override
	public String toString() {
		super.toString();
		String string = "Product id:" + productid + " barcode:" + bcode
				+ " product name:" + productname + " unitprice:" + mrp
				+ " qty:" + qty + " vat:" +vat; 
		return string;
	}
	
	

}
