package com.vendhaq.handlers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import sun.font.Font2DHandle;

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
			JButton button = new JButton(name){
				 @Override
		            public void paintComponent(Graphics g)
		            {
		                g.setColor(Color.LIGHT_GRAY );
		                g.fillRect(0, 0, getSize().width, getSize().height);
		                super.paintComponent(g);
		            }
			};
			button.setContentAreaFilled(false);
			button.setBorder(new LineBorder(Color.GRAY, 1, false));
			button.setPreferredSize(new Dimension(100, 30));
			button.setFont(new Font("Arial", Font.BOLD, 12));
			buttons.add(button);
		}
		return buttons;
	}

	public List<JButton> readProducts(String categoryname) {
		List<JButton> buttons = null;
		try {
			String category_sql = "select * from vtiger_products where productcategory = '"
					+ categoryname + "'";
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

	public Vector<String> readProductNames() {
		Vector<String> names = null;
		try {
			String category_sql = "select * from vtiger_products";
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(category_sql);
			names = new Vector<String>();
			while (result.next()) {
				names.add(result.getString("productname"));

			}
			return names;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
