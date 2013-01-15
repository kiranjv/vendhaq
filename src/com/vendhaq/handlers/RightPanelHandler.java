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

import com.vendhaq.models.VtigerCategory;
import com.vendhaq.models.VtigerProducts;
import com.vendhaq.repos.LocalDbConfiguration;

public class RightPanelHandler {

	Connection connection = null;

	public RightPanelHandler() {
	//	connection = getConnection();
	}

	private Connection getConnection() {
		return LocalDbConfiguration.getConnection();
	}

	public List<JButton> readCategeroys() {
		List<JButton> buttons = null;
		try {
			List<VtigerCategory> categorys = (List<VtigerCategory>) DBLocalHelper
					.readRecords("VtigerCategory");

			buttons = prepareCategoryJButtons(categorys);
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buttons;

	}

	private List<JButton> prepareCategoryJButtons(List<VtigerCategory> categorys)
			throws SQLException {
		List<JButton> buttons = new ArrayList<JButton>();
		for (int i = 0; i < categorys.size(); i++) {
			VtigerCategory cate = categorys.get(i);
			String name = cate.getId().getCategoryname();
			JButton button = new JButton(name) {
				@Override
				public void paintComponent(Graphics g) {
					g.setColor(new Color(244, 244, 246));
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
			List<VtigerProducts> products = (List<VtigerProducts>) DBLocalHelper
					.readRecord("VtigerProducts", "productcategory",
							categoryname);

			buttons = prepareProductJButtons(products);
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buttons;

	}

	private List<JButton> prepareProductJButtons(List<VtigerProducts> products) {
		List<JButton> buttons = new ArrayList<JButton>();
		for (int i = 0; i < products.size(); i++) {
			VtigerProducts prod = products.get(i);
			String name = prod.getProductname();
			JButton button = new JButton(name) {
				@Override
				public void paintComponent(Graphics g) {
					g.setColor(new Color(244, 244, 246));
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

	public List<JButton> readAllProducts() {
		List<JButton> buttons = null;
		try {
			List<VtigerProducts> products = (List<VtigerProducts>) DBLocalHelper.readRecords("VtigerProducts");
			buttons = prepareProductJButtons(products);
			return buttons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buttons;

	}

	public Vector<String> readProductNames() {
		Vector<String> names = null;
		try {
			
			List<VtigerProducts> products = DBLocalHelper.readRecords("VtigerProducts");
			names = new Vector<String>();
			for (int i = 0; i < products.size(); i++) {
				names.add(products.get(i).getProductname());
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
