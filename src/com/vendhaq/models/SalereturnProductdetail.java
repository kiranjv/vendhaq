package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * SalereturnProductdetail generated by hbm2java
 */
public class SalereturnProductdetail implements java.io.Serializable {

	private int id;
	private int salesreturnId;
	private int productid;
	private String invoiceQty;
	private String unitprice;
	private String discount;
	private String vat;
	private String saleretQty;
	private String saleretPrice;

	public SalereturnProductdetail() {
	}

	public SalereturnProductdetail(int id, int salesreturnId, int productid,
			String invoiceQty, String unitprice, String saleretQty,
			String saleretPrice) {
		this.id = id;
		this.salesreturnId = salesreturnId;
		this.productid = productid;
		this.invoiceQty = invoiceQty;
		this.unitprice = unitprice;
		this.saleretQty = saleretQty;
		this.saleretPrice = saleretPrice;
	}

	public SalereturnProductdetail(int id, int salesreturnId, int productid,
			String invoiceQty, String unitprice, String discount, String vat,
			String saleretQty, String saleretPrice) {
		this.id = id;
		this.salesreturnId = salesreturnId;
		this.productid = productid;
		this.invoiceQty = invoiceQty;
		this.unitprice = unitprice;
		this.discount = discount;
		this.vat = vat;
		this.saleretQty = saleretQty;
		this.saleretPrice = saleretPrice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalesreturnId() {
		return this.salesreturnId;
	}

	public void setSalesreturnId(int salesreturnId) {
		this.salesreturnId = salesreturnId;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getInvoiceQty() {
		return this.invoiceQty;
	}

	public void setInvoiceQty(String invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	public String getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getVat() {
		return this.vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getSaleretQty() {
		return this.saleretQty;
	}

	public void setSaleretQty(String saleretQty) {
		this.saleretQty = saleretQty;
	}

	public String getSaleretPrice() {
		return this.saleretPrice;
	}

	public void setSaleretPrice(String saleretPrice) {
		this.saleretPrice = saleretPrice;
	}

}
