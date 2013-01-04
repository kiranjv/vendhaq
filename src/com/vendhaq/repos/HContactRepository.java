package com.vendhaq.repos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.vendhaq.handlers.DBLocalHelper;
import com.vendhaq.models.VtigerContactdetails;

public class HContactRepository {
	
	
	private String modelname = VtigerContactdetails.class.getSimpleName();

	public List readAllContacts() {
		return DBLocalHelper.readRecords(VtigerContactdetails.class.getSimpleName());
		
		
	}

	public List readContact(String contactname) {
		String[] fl_name = parseContact(contactname);
		if (fl_name.length == 1) {
			return readContact( "firstname", fl_name[0], true);
		} else {
			return readContact( fl_name[0], fl_name[1]);
		}

	}

	public List readContact(String firstname, String lastname) {
		String sql = "select * from " + modelname + " where firstname = '"
				+ firstname + "' and lastname = '" + lastname + "'";
		return DBLocalHelper.executeHQuery(sql);
	}

	public List readContact(int contactid) {
		String sql = "from " + modelname + " where contactid = "
				+ contactid;
		return DBLocalHelper.executeHQuery(sql);
	}

	public List readContact(String columnname, String columnvalue,
			boolean isString) {
		String sql = null;
		if (isString) {
			sql = "from " + modelname + " where " + columnname
					+ " = '" + columnvalue + "'";
		} else {
			sql = "from " + modelname + " where " + columnname
					+ " = " + columnvalue;
		}
		return DBLocalHelper.executeHQuery(sql);
	}

	public boolean storeContact(VtigerContactdetails contact){
		return DBLocalHelper.saveRecord(modelname, contact);
	}

	public Vector<String> readContactNames(List<VtigerContactdetails> result) {
		Vector<String> names = new Vector<String>();
		for (int i = 0; i < result.size(); i++) {
			 VtigerContactdetails contact = result.get(i);
			 String name = contact.getFirstname() + " "
						+ contact.getLastname();
				names.add(name);
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

}
