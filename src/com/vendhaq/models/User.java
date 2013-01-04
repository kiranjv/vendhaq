package com.vendhaq.models;

public class User {

	String username;
	String encrypted_password;
	String conform_password;
	String user_hash;

	String acesskey;
	String crypt_type;
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCrypt_type() {
		return crypt_type;
	}

	public void setCrypt_type(String crypt_type) {
		this.crypt_type = crypt_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncrypted_password() {
		return encrypted_password;
	}

	public void setEncrypted_password(String encrypted_password) {
		this.encrypted_password = encrypted_password;
	}

	public String getConform_password() {
		return conform_password;
	}

	public void setConform_password(String conform_password) {
		this.conform_password = conform_password;
	}

	public String getUser_hash() {
		return user_hash;
	}

	public void setUser_hash(String user_hash) {
		this.user_hash = user_hash;
	}

	public String getAcesskey() {
		return acesskey;
	}

	public void setAcesskey(String acesskey) {
		this.acesskey = acesskey;
	}

	public String toString() {
		String data = "username: " + getUsername() + " , password: "
				+ getEncrypted_password() + " , conform password"
				+ getConform_password() + " , status: " + getStatus() + "\n"
				+ " , user hash: " + getUser_hash() + " , accesskey: "
				+ getAcesskey() + " , crypt_type: " + getCrypt_type();
		return data;
	}
}
