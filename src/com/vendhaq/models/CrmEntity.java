/**
 * 
 */
package com.vendhaq.models;

import java.util.Date;

/**
 * @author treewalker
 * 
 */
public class CrmEntity {
	private int crmid;
	private int smcreatorid;
	private int smownerid;
	private int modifiedby;
	private String setype;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private Date viewedtime;
	private String status;
	private int version;
	private int presence;
	private int deleted;

	public CrmEntity(String data[]) {

	}

	public CrmEntity() {
	}

	/**
	 * @return the crmid
	 */
	public int getCrmid() {
		return crmid;
	}

	/**
	 * @param crmid
	 *            the crmid to set
	 */
	public void setCrmid(int crmid) {
		this.crmid = crmid;
	}

	/**
	 * @return the smcreatorid
	 */
	public int getSmcreatorid() {
		return smcreatorid;
	}

	/**
	 * @param smcreatorid
	 *            the smcreatorid to set
	 */
	public void setSmcreatorid(int smcreatorid) {
		this.smcreatorid = smcreatorid;
	}

	/**
	 * @return the smownerid
	 */
	public int getSmownerid() {
		return smownerid;
	}

	/**
	 * @param smownerid
	 *            the smownerid to set
	 */
	public void setSmownerid(int smownerid) {
		this.smownerid = smownerid;
	}

	/**
	 * @return the modifiedby
	 */
	public int getModifiedby() {
		return modifiedby;
	}

	/**
	 * @param modifiedby
	 *            the modifiedby to set
	 */
	public void setModifiedby(int modifiedby) {
		this.modifiedby = modifiedby;
	}

	/**
	 * @return the setype
	 */
	public String getSetype() {
		return setype;
	}

	/**
	 * @param setype
	 *            the setype to set
	 */
	public void setSetype(String setype) {
		this.setype = setype;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createdtime
	 */
	public Date getCreatedtime() {
		return createdtime;
	}

	/**
	 * @param createdtime
	 *            the createdtime to set
	 */
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	/**
	 * @return the modifiedtime
	 */
	public Date getModifiedtime() {
		return modifiedtime;
	}

	/**
	 * @param modifiedtime
	 *            the modifiedtime to set
	 */
	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * @return the viewedtime
	 */
	public Date getViewedtime() {
		return viewedtime;
	}

	/**
	 * @param viewedtime
	 *            the viewedtime to set
	 */
	public void setViewedtime(Date viewedtime) {
		this.viewedtime = viewedtime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the presence
	 */
	public int getPresence() {
		return presence;
	}

	/**
	 * @param presence
	 *            the presence to set
	 */
	public void setPresence(int presence) {
		this.presence = presence;
	}

	/**
	 * @return the deleted
	 */
	public int getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}
