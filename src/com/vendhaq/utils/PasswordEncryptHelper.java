package com.vendhaq.utils;

public class PasswordEncryptHelper {

	private String username = null;
	private String password = null;
	private String salt = null;
	private String crypt_type;
	private String encrypted_password = null;
	private static final String DEFAULT_PASSWORD_CRYPT_TYPE = "MD5";

	public PasswordEncryptHelper() {
	}

	public PasswordEncryptHelper(String username, String password,
			String crypt_type) {
		this.username = username;
		this.password = password;
		this.crypt_type = crypt_type;
		this.encrypted_password = encrypt_password(username, password,
				crypt_type);
	}

	public String encrypt_password(String username, String user_password,
			String crypt_type) {
		encrypted_password = crypt(user_password,
				generateSalt(username, user_password, crypt_type));
		return encrypted_password;
	}

	/**
	 * 
	 * @param username
	 * @param user_password
	 * @param crypt_type
	 * @return String generated salt value.
	 */
	public String generateSalt(String username, String user_password,
			String crypt_type) {

		// encrypt the password.
		salt = username.substring(0, 2);

		// Fix for: http://trac.vtiger.com/cgi-bin/trac.cgi/ticket/4923
		if (crypt_type == null || crypt_type == "" || crypt_type.isEmpty()) {
			// Try to get the crypt_type which is in database for the user
			crypt_type = DEFAULT_PASSWORD_CRYPT_TYPE;
		}

		// For more details on salt format look at: http://in.php.net/crypt
		if (crypt_type == "MD5" || crypt_type.equalsIgnoreCase("MD5")) {
			salt = "$1$" + salt + "$";
		} else if (crypt_type == "BLOWFISH"
				|| crypt_type.equalsIgnoreCase("BLOWFISH")) {
			salt = "$2$" + salt + "$";
		} else if (crypt_type == "PHP5.3MD5"
				|| crypt_type.equalsIgnoreCase("PHP5.3MD5")) {
			// only change salt for php 5.3 or higher version for backward
			// compactibility.
			// crypt API is lot stricter in taking the value for salt.
			salt = "$1$" + salt + "000000000"; // str_pad ( $salt, 9, '0' );
		}
		return salt;
	}

	private String crypt(String user_password, String salt) {
		return MD5Crypt.crypt(user_password, salt);
	}

	public String getCrypt_type() {
		return crypt_type;
	}

	public void setCrypt_type(String crypt_type) {
		this.crypt_type = crypt_type;
	}

	public String getEncrypted_password() {
		return encrypted_password;
	}

	public void setEncrypted_password(String encrypted_password) {
		this.encrypted_password = encrypted_password;
	}

}
