package com.vendhaq.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.vendhaq.repos.LocalDbConfiguration;

public class RightPanelHandler {

	Connection connection = null;

	public RightPanelHandler() {
		connection = getConnection();
	}

	private Connection getConnection() {
		return LocalDbConfiguration.getConnection();
	}

	public List<JButton> readCategeroys() {
		List<JButton> buttons = null;
		try {
			String category_sql = "select * from vtiger_category";
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(category_sql);
			buttons = prepareJButtons(result, "categoryname");
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return buttons;
		
	}

	private List<JButton> prepareJButtons(ResultSet result, String columnName)
			throws SQLException {
		List<JButton> buttons = new ArrayList<JButton>();
		while (result.next()) {
			String name = result.getString(columnName);
			buttons.add(new JButton(name));
		}
		return buttons;
	}

	
	public List<JButton> readProducts(String categoryname) {
		List<JButton> buttons = null;
		try {
			String category_sql = "select * from vtiger_products where productcategory = '"+categoryname+"'";
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(category_sql);
			buttons = prepareJButtons(result, "productname");
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return buttons;
		
	}
	
	public List<JButton> readAllProducts() {
		List<JButton> buttons = null;
		try {
			String category_sql = "select * from vtiger_products";
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(category_sql);
			buttons = prepareJButtons(result, "productname");
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return buttons;
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
