/**
 * 
 */
package com.vendhaq.models;

/**
 * @author treewalker
 * 
 */
public class VtigerModentitynum implements java.io.Serializable {

	int numId;
	String semodule;
	String prefix;
	String startId;
	String curId;
	String active;

	public VtigerModentitynum() {
	}

	public VtigerModentitynum(int numId, String semodule, String prefix,
			String startId, String curId, String active) {
		this.numId = numId;
		this.semodule = semodule;
		this.prefix = prefix;
		this.startId = startId;
		this.curId = curId;
		this.active = active;
	}

	/**
	 * @return the numId
	 */
	public int getNumId() {
		return numId;
	}

	/**
	 * @param numId
	 *            the numId to set
	 */
	public void setNumId(int numId) {
		this.numId = numId;
	}

	/**
	 * @return the semodule
	 */
	public String getSemodule() {
		return semodule;
	}

	/**
	 * @param semodule
	 *            the semodule to set
	 */
	public void setSemodule(String semodule) {
		this.semodule = semodule;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the startId
	 */
	public String getStartId() {
		return startId;
	}

	/**
	 * @param startId
	 *            the startId to set
	 */
	public void setStartId(String startId) {
		this.startId = startId;
	}

	/**
	 * @return the curId
	 */
	public String getCurId() {
		return curId;
	}

	/**
	 * @param curId
	 *            the curId to set
	 */
	public void setCurId(String curId) {
		this.curId = curId;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

}
