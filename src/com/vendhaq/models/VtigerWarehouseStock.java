package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerWarehouseStock generated by hbm2java
 */
public class VtigerWarehouseStock implements java.io.Serializable {

	private int warehousestockid;
	private Integer warehouseid;
	private Integer productid;
	private Integer qty;
	private Integer reorder;

	public VtigerWarehouseStock() {
	}

	public VtigerWarehouseStock(int warehousestockid) {
		this.warehousestockid = warehousestockid;
	}

	public VtigerWarehouseStock(int warehousestockid, Integer warehouseid,
			Integer productid, Integer qty, Integer reorder) {
		this.warehousestockid = warehousestockid;
		this.warehouseid = warehouseid;
		this.productid = productid;
		this.qty = qty;
		this.reorder = reorder;
	}

	public int getWarehousestockid() {
		return this.warehousestockid;
	}

	public void setWarehousestockid(int warehousestockid) {
		this.warehousestockid = warehousestockid;
	}

	public Integer getWarehouseid() {
		return this.warehouseid;
	}

	public void setWarehouseid(Integer warehouseid) {
		this.warehouseid = warehouseid;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getReorder() {
		return this.reorder;
	}

	public void setReorder(Integer reorder) {
		this.reorder = reorder;
	}

}
