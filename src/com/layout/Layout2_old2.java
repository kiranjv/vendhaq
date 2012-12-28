package com.layout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Layout2_old2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel RightPanel;
	private JPanel LeftPanel;
	private BufferedImage[] images;
	private JTextField Field_SearchProduct;
	private JTextField field_customersearch;
	private JLabel label_productimage;
	private JLabel label_productname;
	private JLabel label_customername_label;
	private JLabel label_tax_label;
	private JSpinner spinner_quantity;
	private JTable jTable0;
	private JScrollPane tabel_invoices;
	private JButton button_addqty;
	private JButton button_addcustomer;
	private JLabel label_subtotal_label;
	private JLabel Label_total_label;
	private JLabel label_subtotal_amount;
	private JLabel label_tax_amount;
	private JLabel label_total_amount;
	private JLabel label_topay_label;
	private JLabel label_topay_amount;
	private JSeparator jSeperater0;
	private JButton button_void;
	private JButton button_pay;
	private JButton button_discount;
	private JButton button_notes;
	private JButton button_park;
	private JPanel ProductsPanel;
	private JPanel jPanel0;
	private String[] productnames = {"Chinese", "Italian", "Indian", "French",};
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public Layout2_old2() {
		images = loadImages();
		// new BackgroundImageComponent(images[0]);
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		add(getLeftPanel());
		add(getRightPanel());
		setSize(986, 561);
	}

	
	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			//jPanel0.add(getJLabel2(), new Constraints(new Leading(2, 106, 10, 10), new Leading(2, 18, 10, 10)));
		}
		return jPanel0;
	}

	private JButton getButton_pay() {
		if(button_pay == null) {
			button_pay = new JButton("Pay(F4)");
			button_pay.setBounds(356,505,75,35);
		}
		return button_pay;
			
	}
	private JButton getButton_discount() {
		if(button_discount == null) {
			button_discount = new JButton("Discount");
			button_discount.setBounds(272,505,75,35);
		}
		return button_discount;
			
	}private JButton getButton_notes() {
		if(button_notes == null) {
			button_notes = new JButton("Notes");
			button_notes.setBounds(186,505,75,35);
		}
		return button_notes;
			
	}private JButton getButton_park() {
		if(button_park == null) {
			button_park = new JButton("Park(F5)");
			button_park.setBounds(99,505,75,35);
		}
		return button_park;
			
	}
	private JButton getButton_void() {
		if(button_void == null) {
			button_void = new JButton("Void");
			button_void.setBounds(9,505, 75, 35);
		}
		return button_void;
			
	}
	private JSeparator getJSeperator() {
		if(jSeperater0 == null) {
			jSeperater0 = new JSeparator(SwingConstants.HORIZONTAL);
			jSeperater0.setBounds(201,459,239,11);
			jSeperater0.setBackground(Color.BLACK);
		}
		return jSeperater0;
		
	}
	
	private JLabel getLabel_topay_amount() {
		if (label_topay_amount == null) {
			label_topay_amount = new JLabel();
			label_topay_amount.setText("$25.00");
			label_topay_amount.setBounds(324,467,93,26);
		}
		return label_topay_amount;
	}
	
	private JLabel getLabel_topay_label() {
		if (label_topay_label == null) {
			label_topay_label = new JLabel();
			label_topay_label.setText("TO PAY");
			label_topay_label.setBounds(204,468,83,18);
		}
		return label_topay_label;
	}
	
	private JLabel getLabel_total_amount() {
		if (label_total_amount == null) {
			label_total_amount = new JLabel();
			label_total_amount.setText("$25.00");
			label_total_amount.setBounds(325,433,93,23);
		}
		return label_total_amount;
	}
	
	private JLabel getLabel_tax_amount() {
		if (label_tax_amount == null) {
			label_tax_amount = new JLabel();
			label_tax_amount.setText("0.00");
			label_tax_amount.setBounds(323,396,95,26);
		}
		return label_tax_amount;
	}
	private JLabel getLabel_subtotal_amount() {
		if (label_subtotal_amount == null) {
			label_subtotal_amount = new JLabel();
			label_subtotal_amount.setText("$25.50");
			label_subtotal_amount.setBounds(326,366,94,25);
		}
		return label_subtotal_amount;
	}
	
	private JLabel getLabel_customer_name() {
		if (label_customername_label == null) {
			label_customername_label = new JLabel();
			label_customername_label.setText("No Customer");
			label_customername_label.setBounds(16,441,112,28);
		}
		return label_customername_label;
	}
	
	private JLabel getLabel_total_label() {
		if (Label_total_label == null) {
			Label_total_label = new JLabel();
			Label_total_label.setText("TOTAL");
			Label_total_label.setBounds(209, 430, 55, 18);
		}
		return Label_total_label;
	}

	private JLabel getLabel_tax_label() {
		if (label_tax_label == null) {
			label_tax_label = new JLabel();
			label_tax_label.setText("Tax");
			label_tax_label.setBounds(210, 402, 75, 18);
		}
		return label_tax_label;
	}

	private JLabel getSubtotalLabel() {
		if (label_subtotal_label == null) {
			label_subtotal_label = new JLabel();
			label_subtotal_label.setText("Subtotal");
			label_subtotal_label.setBounds(212, 369, 74, 17);
		}
		return label_subtotal_label;
	}

	private JButton getButton_addcustomer() {
		if (button_addcustomer == null) {
			button_addcustomer = new JButton();
			button_addcustomer.setIcon(new ImageIcon(getClass().getResource(
					"/com/layout/images/add-icon.jpg")));
			button_addcustomer.setRolloverEnabled(false);
			button_addcustomer.setBounds(160, 387, 26, 24);
		}
		return button_addcustomer;
	}

	private JTextField getField_customersearch() {
		if (field_customersearch == null) {
			field_customersearch = new JTextField();
			field_customersearch.setToolTipText("Customer search");
			field_customersearch.setBounds(9, 384, 142, 31);
		}
		return field_customersearch;
	}

	private JScrollPane getTabel_invoices() {
		if (tabel_invoices == null) {
			tabel_invoices = new JScrollPane();
			tabel_invoices.setBounds(4, 112, 435, 244);
			tabel_invoices.setViewportView(getJTable0());
		}
		return tabel_invoices;
	}

	private JScrollPane getTabelInvoice() {
		if (tabel_invoices == null) {
			tabel_invoices = new JScrollPane();
			tabel_invoices.setBounds(4, 112, 435, 186);
			tabel_invoices.setViewportView(getJTable0());
		}
		return tabel_invoices;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] {
					{ "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] {
					"Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };

				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

	private JSpinner getSpinner_quantity() {
		if (spinner_quantity == null) {
			spinner_quantity = new JSpinner();
			spinner_quantity.setBounds(239, 58, 107, 23);
		}
		return spinner_quantity;
	}

	private JButton getButton_addqty() {
		if (button_addqty == null) {
			button_addqty = new JButton();
			button_addqty.setIcon(new ImageIcon(getClass().getResource(
					"/com/layout/images/add-icon.jpg")));
			button_addqty.setRolloverEnabled(false);
			button_addqty.setBounds(369, 57, 26, 26);
		}
		return button_addqty;
	}

	private JLabel getLabel_productname() {
		if (label_productname == null) {
			label_productname = new JLabel();
			label_productname.setText("Coffe Demo");
			label_productname.setBounds(89, 56, 134, 22);
		}
		return label_productname;
	}

	private JLabel getLabel_productimage() {
		if (label_productimage == null) {
			label_productimage = new JLabel();
			label_productimage.setIcon(new ImageIcon(getClass().getResource(
					"/com/layout/images/empty_image1.png")));
			label_productimage.setToolTipText("product image");
			label_productimage.setOpaque(true);
			label_productimage.setBounds(11, 58, 50, 44);
		}
		return label_productimage;
	}

	private JTextField getField_SearchProduct() {
		if (Field_SearchProduct == null) {
			Field_SearchProduct = new JTextField();
			Field_SearchProduct.setToolTipText("Search products");
			Field_SearchProduct.setBounds(19, 10, 179, 27);
		}
		return Field_SearchProduct;
	}

	private JPanel getLeftPanel() {
		if (LeftPanel == null) {
			LeftPanel = new BackgroundImageComponent(images[0]);
			LeftPanel.setBorder(new LineBorder(Color.black, 1, false));
			LeftPanel.setBounds(6, 6, 441, 547);
			LeftPanel.setLayout(null);
			LeftPanel.add(getField_SearchProduct());
			LeftPanel.add(getLabel_productimage());
			LeftPanel.add(getLabel_productname());
			LeftPanel.add(getSpinner_quantity());
			LeftPanel.add(getButton_addqty());
			LeftPanel.add(getField_customersearch());
			LeftPanel.add(getButton_addcustomer());
			LeftPanel.add(getLabel_tax_label());
			LeftPanel.add(getLabel_total_label());
			LeftPanel.add(getSubtotalLabel());
			LeftPanel.add(getTabel_invoices());
			LeftPanel.add(getLabel_customer_name());
			LeftPanel.add(getLabel_subtotal_amount());
			LeftPanel.add(getLabel_tax_amount());
			LeftPanel.add(getLabel_total_amount());
			LeftPanel.add(getLabel_topay_label());
			LeftPanel.add(getLabel_topay_amount());
			LeftPanel.add(getJSeperator());
			LeftPanel.add(getButton_void());
			LeftPanel.add(getButton_park());
			LeftPanel.add(getButton_notes());
			LeftPanel.add(getButton_discount());
			LeftPanel.add(getButton_pay());

		}
		return LeftPanel;
	}

	private JPanel getProductsPanel() {
		if(ProductsPanel == null) {
			ProductsPanel = new JPanel();
			ProductsPanel.setLayout(new FlowLayout(SwingConstants.HORIZONTAL, 2, 3));
			ProductsPanel.add(new JButton("One"));
			ProductsPanel.add(new JButton("Two"));
			ProductsPanel.add(new JButton("Three"));
			ProductsPanel.add(new JButton("Four"));
			ProductsPanel.add(new JButton("Five"));
			ProductsPanel.add(new JButton("Six"));
			ProductsPanel.setBounds(4,38, 505, 350);
			ProductsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
		}
		return ProductsPanel;
		
	}
	
	
	private JPanel getRightPanel() {
		if (RightPanel == null) {
			RightPanel = new JPanel();
			RightPanel.setBorder(new LineBorder(Color.black, 1, false));
			RightPanel.setBounds(462, 7, 513, 546);
			RightPanel.setLayout(new GroupLayout());
			RightPanel.add(getProductsPanel(),new Constraints(new Leading(3, 505, 10, 10), new Leading(37, 350, 10, 10)));
		
		}
		return RightPanel;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Layout2_old2 frame = new Layout2_old2();
				frame.setDefaultCloseOperation(Layout2_old2.EXIT_ON_CLOSE);
				frame.setTitle("Layout2");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private BufferedImage[] loadImages() {
		String prefix = "images/";
		String[] fileNames = { "leftbackground.png", "rightbackground.png" };
		BufferedImage[] images = new BufferedImage[fileNames.length];
		for (int j = 0; j < images.length; j++)
			try {
				URL url = getClass().getResource(prefix + fileNames[j]);
				images[j] = ImageIO.read(url);
			} catch (MalformedURLException mue) {
				System.err.println("url: " + mue.getMessage());
			} catch (IOException ioe) {
				System.err.println("read: " + ioe.getMessage());
			}
		return images;
	}

}
