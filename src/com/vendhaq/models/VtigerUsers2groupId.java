package com.vendhaq.models;

// Generated Nov 17, 2012 10:58:25 AM by Hibernate Tools 3.4.0.CR1

/**
 * VtigerUsers2groupId generated by hbm2java
 */
public class VtigerUsers2groupId implements java.io.Serializable {

	private int groupid;
	private int userid;

	public VtigerUsers2groupId() {
	}

	public VtigerUsers2groupId(int groupid, int userid) {
		this.groupid = groupid;
		this.userid = userid;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VtigerUsers2groupId))
			return false;
		VtigerUsers2groupId castOther = (VtigerUsers2groupId) other;

		return (this.getGroupid() == castOther.getGroupid())
				&& (this.getUserid() == castOther.getUserid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getGroupid();
		result = 37 * result + this.getUserid();
		return result;
	}

}
