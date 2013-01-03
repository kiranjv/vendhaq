package com.vendhaq.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import sun.swing.MenuItemLayoutHelper.ColumnAlignment;

public class ContactsRepository {

	String TABLE_NAME = "vtiger_contactdetails";
	Connection connection = null;

	public ContactsRepository() {
		if (connection == null) {
			connection = getConnction();
		}

	}

	private Connection getConnction() {
		return LocalDbConfiguration.getConnection();
	}

	public ResultSet readAllContacts() {
		String sql = "select * from " + TABLE_NAME;
		return executeQuery(sql);
	}

	public ResultSet readContact(String contactname) {
		String[] fl_name = parseContact(contactname);
		if (fl_name.length == 1) {
			return readContact("firstname", fl_name[0], true);
		} else {
			return readContact(fl_name[0], fl_name[1]);
		}

	}

	public ResultSet readContact(String firstname, String lastname) {
		String sql = "select * from " + TABLE_NAME + " where firstname = '"
				+ firstname + "' and lastname = '" + lastname + "'";
		return executeQuery(sql);
	}

	public ResultSet readContact(int contactid) {
		String sql = "select * from " + TABLE_NAME + " where contactid = "
				+ contactid;
		return executeQuery(sql);
	}

	public ResultSet readContact(String columnname, String columnvalue,
			boolean isString) {
		String sql = null;
		if (isString) {
			sql = "select * from " + TABLE_NAME + " where " + columnname
					+ " = '" + columnvalue + "'";
		} else {
			sql = "select * from " + TABLE_NAME + " where " + columnname
					+ " = " + columnvalue;
		}
		return executeQuery(sql);
	}

	public ResultSet executeQuery(String query) {
		Statement stmt;
		ResultSet result = null;
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public Vector<String> readContactNames(ResultSet result) {
		Vector<String> names = new Vector<String>();
		try {
			while (result.next()) {
				String name = result.getString("firstname") + " "
						+ result.getString("lastname");
				names.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;

	}

	public String[] parseContact(String contactname) {

		StringTokenizer tokens = new StringTokenizer(contactname, " ");
		String fl_names[] = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreElements()) {
			String value = tokens.nextElement().toString();
			fl_names[i++] = value;
			System.out.println("Value: " + value);
		}
		return fl_names;
	}

	public static void main(String[] args) {

	}

}
