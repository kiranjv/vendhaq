package com.vendhaq.layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.metal.MetalComboBoxUI.MetalComboBoxLayoutManager;
import javax.swing.plaf.synth.SynthComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import sun.font.Font2DHandle;

import ch.rakudave.suggest.JSuggestField;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;
import com.vendhaq.handlers.RightPanelHandler;
import com.vendhaq.repos.ContactsRepository;
import com.vendhaq.repos.LocalDbConfiguration;
import com.vendhaq.utils.Util;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InvoiceScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel RightPanel;
	private JPanel LeftPanel;
	private BufferedImage[] images;
	private JTextField Field_SearchProduct;
	private JSuggestField field_customersearch;
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
	private String[] productnames = { "Chinese", "Italian", "Indian", "French", };
	private JTable table_invoice;
	private DefaultTableModel table_invoiceModel;
	private JTableHeader tabelheadder;
	public static String[] producttable_headder = { "Qty", "Item Name",
			"Rate ", "MRP", "Del" };
	private int cell_width[] = { 10, 150, 45, 50, 8 };
	private JPanel panel_products;

	private JLabel label_rightpanel_headder;
	private JLabel label_number_products;
	private JLabel label_forwardaction;
	private JLabel label_backwordaction;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private String[] product_list = {};
	private JTextField combo_textfield;
	private JComboBox cbo;
	private RightPanelHandler Rightpanel_Handler = null;
	private List<JButton> buttons_category;
	private List<JButton> buttons_products;
	private JSuggestField field_productsearch;
	private static final int MAX_BUTTONS = 16;

	private int qty_index = 0;
	private int productname_index = 1;
	private int rate_index = 2;
	private int mrp_index = 3;
	private int delete_index = 4;
	private String previous_quantity;
	private boolean CELLUPDATE;
	private String RIGHT_OFFSET = "  ";
	private ContactsRepository contactsRepo = null;

	private int itsRow;
	private int itsColumn;

	
	public InvoiceScreen() {
		images = loadImages();
		Rightpanel_Handler = new RightPanelHandler();
		// new BackgroundImageComponent(images[0]);
		initComponents();
	}

	private void initComponents() {
		try {
			setLayout(null);
			add(getLeftPanel());
			add(getRightPanel());
			setSize(986, 561);
			setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel getLabel_backwordaction() {
		if (label_backwordaction == null) {
			label_backwordaction = new JLabel();
			label_backwordaction.setText("");
			label_backwordaction.setBounds(11, 512, 34, 24);
			label_backwordaction.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Backword action");
					String current_status = label_rightpanel_headder.getText()
							.trim();
					if (current_status.equalsIgnoreCase("Category Items")) {
						System.out.println("Already in category section");
						JOptionPane.showMessageDialog(null,
								"Already in category section");
						return;
					}

					System.out.println("Clearing all panel items");
					panel_products.removeAll();

					buttons_category = Rightpanel_Handler.readCategeroys();
					int num_prod = buttons_category.size();
					for (int i = 0; i < num_prod; i++) {
						JButton button = buttons_category.get(i);
						button.addActionListener(new CategoryButton_ActionListener());
						panel_products.add(button);
					}
					label_number_products.setText(String.valueOf(num_prod));
					label_rightpanel_headder.setText("Category Items");
					panel_products.revalidate();
					panel_products.repaint();

				}
			});
		}
		return label_backwordaction;
	}

	private JLabel getLabel_forwardaction() {
		if (label_forwardaction == null) {
			label_forwardaction = new JLabel();
			label_forwardaction.setText("");
			label_forwardaction.setBounds(466, 515, 34, 21);
			label_forwardaction.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("forward action performed");
					String current_status = label_rightpanel_headder.getText()
							.trim();
					if (buttons_products == null) {
						System.out.println("No products available");
						JOptionPane.showMessageDialog(null,
								"No products available");
						return;
					} else if (current_status.equalsIgnoreCase("Products")) {
						System.out.println("Already in products section");
						JOptionPane.showMessageDialog(null,
								"Already in products section");
						return;
					} else {
						System.out.println("Clearing all panel items");
						panel_products.removeAll();
						for (int i = 0; i < buttons_products.size(); i++) {
							JButton button = buttons_products.get(i);
							button.setPreferredSize(new Dimension(120, 60));
							button.setBorder(new BevelBorder(BevelBorder.RAISED));
							button.addActionListener(new ProductButton_ActionListener());
							panel_products.add(button);
						}
						label_number_products.setText(String
								.valueOf(buttons_products.size()));
						label_rightpanel_headder.setText("Products");
						panel_products.revalidate();
						panel_products.repaint();
					}

				}
			});
		}
		return label_forwardaction;
	}

	private JLabel getLabel_number_products() {
		if (label_number_products == null) {
			label_number_products = new JLabel();
			label_number_products.setText("1");
			label_number_products.setBounds(204, 520, 34, 14);
			label_number_products.setFont(new Font("Arial", Font.BOLD, 12));
		}
		return label_number_products;
	}

	private JLabel getLabel_rightpanel_headder() {
		if (label_rightpanel_headder == null) {
			label_rightpanel_headder = new JLabel();
			label_rightpanel_headder.setText("Default");
			label_rightpanel_headder.setForeground(Color.WHITE);
			label_rightpanel_headder.setBounds(203, 12, 112, 14);
		}
		return label_rightpanel_headder;
	}

	private JPanel getPanel_products() throws SQLException {
		if (panel_products == null) {
			panel_products = new JPanel();
			panel_products.setBounds(6, 36, 503, 468);
			// panel_products.setLayout(new FlowLayout(SwingConstants.VERTICAL,
			// 1, 2));
			panel_products.setLayout(new GridLayout(0, 4));
			panel_products.setBorder(new LineBorder(Color.lightGray, 1, false));
			panel_products.setBackground(Color.WHITE);

			/* Adding panel category jbutton */
			buttons_category = Rightpanel_Handler.readCategeroys();

			addToPRoductsPanel(buttons_category,
					new CategoryButton_ActionListener());

			label_number_products.setText(String.valueOf(buttons_category
					.size()));
			label_rightpanel_headder.setText("Category Items");

		}
		return panel_products;
	}

	private void addToPRoductsPanel(List<JButton> buttons,
			ActionListener listener) {
		int num_prod = buttons.size();
		int i = 0;
		for (; i < num_prod; i++) {
			JButton button = buttons.get(i);
			button.setBackground(Color.LIGHT_GRAY);
			button.setOpaque(true);

			button.setPreferredSize(new Dimension(100, 50));

			// button.setIcon(new ImageIcon(getClass().getResource(
			// "/com/vendhaq/layout/images/empty_button.png")));
			// button.setVerticalTextPosition(SwingConstants.CENTER);
			// button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.addActionListener(listener);
			panel_products.add(button);
		}
		if (num_prod < MAX_BUTTONS) {
			int current_index = i;
			for (; current_index < MAX_BUTTONS; current_index++) {
				panel_products.add(new JLabel());
			}
		}

	}

	private JButton getButton_pay() {
		if (button_pay == null) {
			button_pay = new JButton();
			button_pay.setIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/pay_button.png")));
			button_pay.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/pay_rollover.png")));
			button_pay.setBounds(356, 505, 75, 35);
		}
		return button_pay;

	}

	private JButton getButton_discount() {
		if (button_discount == null) {
			button_discount = new JButton();
			button_discount.setIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/discount_button.png")));
			button_discount
					.setRolloverIcon(new ImageIcon(getClass().getResource(
							"/com/vendhaq/layout/images/discount_rollover.png")));
			button_discount.setBounds(272, 505, 75, 35);
		}
		return button_discount;

	}

	private JButton getButton_notes() {
		if (button_notes == null) {
			button_notes = new JButton();
			button_notes.setIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/note_button.png")));
			button_notes.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/note_rollover.png")));
			button_notes.setBounds(186, 505, 75, 35);
		}
		return button_notes;

	}

	private JButton getButton_park() {
		if (button_park == null) {
			button_park = new JButton();
			button_park.setIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/park_button.png")));
			button_park.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/park_rollover.png")));
			button_void.setBounds(9, 505, 75, 35);
			button_park.setBounds(99, 505, 75, 35);
		}
		return button_park;

	}

	private JButton getButton_void() {
		if (button_void == null) {
			button_void = new JButton();

			button_void.setIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/voidbutton.png")));
			button_void.setBounds(9, 505, 75, 35);
			button_void.setRolloverIcon(new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/void_rollover.png")));
			// button_void.setBorder(new LineBorder(Color.BLACK));
		}
		return button_void;

	}

	private JSeparator getJSeperator() {
		if (jSeperater0 == null) {
			jSeperater0 = new JSeparator(SwingConstants.HORIZONTAL);
			jSeperater0.setBounds(201, 459, 239, 11);
			jSeperater0.setBackground(Color.BLACK);
		}
		return jSeperater0;

	}

	private JLabel getLabel_topay_amount() {
		if (label_topay_amount == null) {
			label_topay_amount = new JLabel("$25.00" + RIGHT_OFFSET,
					SwingConstants.RIGHT);
			label_topay_amount.setFont(new Font("Arial", Font.BOLD, 12));
			label_topay_amount.setBounds(324, 467, 93, 26);
			label_topay_amount.setVerticalAlignment(SwingConstants.CENTER);
		}
		return label_topay_amount;
	}

	private JLabel getLabel_topay_label() {
		if (label_topay_label == null) {
			label_topay_label = new JLabel();
			label_topay_label.setText("TO PAY");
			label_topay_label.setFont(new Font("Arial", Font.BOLD, 12));
			label_topay_label.setBounds(204, 468, 83, 18);
		}
		return label_topay_label;
	}

	private JLabel getLabel_total_amount() {
		if (label_total_amount == null) {
			label_total_amount = new JLabel("$25.00" + RIGHT_OFFSET,
					SwingConstants.RIGHT);
			label_total_amount.setFont(new Font("Arial", Font.BOLD, 12));
			label_total_amount.setBounds(325, 433, 93, 23);
			label_total_amount.setVerticalAlignment(SwingConstants.CENTER);
		}
		return label_total_amount;
	}

	private JLabel getLabel_tax_amount() {
		if (label_tax_amount == null) {
			label_tax_amount = new JLabel("0.00" + RIGHT_OFFSET,
					SwingConstants.RIGHT);
			label_tax_amount.setVerticalAlignment(SwingConstants.CENTER);
			label_tax_amount.setBounds(323, 396, 95, 26);
		}
		return label_tax_amount;
	}

	private JLabel getLabel_subtotal_amount() {
		if (label_subtotal_amount == null) {
			label_subtotal_amount = new JLabel("$25.50" + RIGHT_OFFSET,
					SwingConstants.RIGHT);
			label_subtotal_amount.setBounds(326, 366, 94, 25);
			label_subtotal_amount.setVerticalAlignment(SwingConstants.CENTER);
		}
		return label_subtotal_amount;
	}

	private JLabel getLabel_customer_name() {
		if (label_customername_label == null) {
			label_customername_label = new JLabel();
			label_customername_label.setText("No Customer");
			label_customername_label.setBounds(16, 400, 250, 100);
		}
		return label_customername_label;
	}

	private JLabel getLabel_total_label() {
		if (Label_total_label == null) {
			Label_total_label = new JLabel();
			Label_total_label.setText("TOTAL");
			Label_total_label.setFont(new Font("Arial", Font.BOLD, 12));
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
					"/com/vendhaq/layout/images/add-icon.png")));
			button_addcustomer.setRolloverEnabled(false);
			button_addcustomer.setBounds(160, 387, 26, 24);
			button_addcustomer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							AddCustomerView inst = new AddCustomerView();
							inst.setLocationRelativeTo(null);
							inst.setVisible(true);
						}
					});	
				}
			});
		}
		return button_addcustomer;
	}

	private JScrollPane getTabel_invoices() {
		if (tabel_invoices == null) {
			tabel_invoices = new JScrollPane();
			tabel_invoices.setBounds(4, 112, 435, 240);
			tabel_invoices.setViewportView(getJTable0());
			tabel_invoices.setBackground(Color.WHITE);
			tabel_invoices.setOpaque(true);
			tabel_invoices.setAutoscrolls(true);

		}
		return tabel_invoices;
	}

	private JTable getJTable0() {
		if (table_invoice == null) {
			table_invoiceModel = new DefaultTableModel(new String[][] {},
					producttable_headder);

			table_invoice = new JTable(table_invoiceModel) {
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}

				public Component prepareRenderer(TableCellRenderer renderer,
						int row, int column) {
					Component c = super.prepareRenderer(renderer, row, column);
					JComponent jc = (JComponent) c;
					if (!isRowSelected(row))
						c.setBackground(Color.WHITE);
					return c;
				}

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

			};
			table_invoice.setLayout(null);
			// 2, 49, 940, 300
			table_invoice.setBounds(4, 112, 435, 240);
			table_invoice.setIntercellSpacing(new Dimension(10, 50));
			table_invoice.setForeground(Color.BLACK);
			table_invoice.setBackground(Color.white);
			table_invoice.setOpaque(true);
			table_invoice.setRowMargin(5);
			table_invoice.setRowHeight(30);
			table_invoice.setFont(new Font("Arial", Font.BOLD, 14));
			table_invoice.setTableHeader(null);

			table_invoice.setFont(new Font("Arail", Font.PLAIN, 12));
			// table_invoice.setPreferredSize(new java.awt.Dimension(435, 240));
			table_invoice.setBorder(new LineBorder(Color.LIGHT_GRAY));
			table_invoice.setShowHorizontalLines(true);
			table_invoice.setShowVerticalLines(false);
			
			/*setting cell render models */
			table_invoice.getColumnModel().getColumn(qty_index)
			.setCellRenderer(new QtyImageRenderer());
			table_invoice.getColumnModel().getColumn(rate_index)
			.setCellRenderer(new RateImageRenderer());
			table_invoice.getColumnModel().getColumn(delete_index)
					.setCellRenderer(new ImageRenderer());
			
			table_invoice.addMouseListener(new TableMouseActionLister());
			table_invoice
					.addMouseMotionListener(new InvoiceTableMouseMaotionAdapter());
			// table_invoice.setDefaultRenderer(Object.class, new
			// InvoiceAttributiveCellRenderer());

			// table_invoice.setBackground(new Color(151, 221, 251));
			/** Initialize table headder panle elements */

			// table_invoice.setCellSelectionEnabled(false);
			// ListSelectionModel cellSelectionModel =
			// table_invoice.getSelectionModel();
			// cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// cellSelectionModel.addListSelectionListener(new
			// SelectionListener());

			// tabelheadder = table_invoice.getTableHeader();
			// tabelheadder.setVisible(false);
			// tabelheadder.setBackground(Color.GRAY);

			// tabelheadder.setForeground(Color.BLACK);
			// tabelheadder.setFont(new Font("Arial", Font.BOLD, 12));
			// tabelheadder.setOpaque(true);
			// adding cell value change listener
			// addTableListener();

			// make table formatting
			for (int i = 0; i < table_invoiceModel.getColumnCount(); i++) {
				TableColumn column = table_invoice.getColumnModel()
						.getColumn(i);

				if (i == 3) {
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.RIGHT);
					column.setCellRenderer(centerRenderer);
				}
				formatTableCells(table_invoice.getColumnModel().getColumn(i),
						cell_width[i]);
			}

			// scrollpane.setBorder(new LineBorder(Color.BLACK, 2,
			// true));

		}
		return table_invoice;
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
					"/com/vendhaq/layout/images/add-icon.png")));
			button_addqty.setRolloverEnabled(false);
			button_addqty.setBounds(369, 57, 26, 26);
			button_addqty.addActionListener(new AddQuantity_ActionListener());
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
					"/com/vendhaq/layout/images/empty_image1.png")));
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

	private JComboBox getComboProductSearch() {
		cbo = new JComboBox(product_list);
		cbo.setEditable(true);
		cbo.setBounds(19, 10, 179, 27);
		cbo.setSelectedIndex(-1);
		cbo.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		// "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		// javax.swing.plaf.metal.MetalComboBoxUI()

		cbo.setUI(new javax.swing.plaf.metal.MetalComboBoxUI() {
			public void layoutComboBox(Container parent,
					MetalComboBoxLayoutManager manager) {
				super.layoutComboBox(parent, manager);
				arrowButton.setBounds(0, 0, 0, 0);
				if (editor != null) {
					Rectangle r = rectangleForCurrentValue();
					editor.setBounds(r);
				}
			}
		});

		cbo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("ID: " + e.getID() + " item name: "
						+ e.getItem().toString());

			}
		});

		combo_textfield = (JTextField) cbo.getEditor().getEditorComponent();
		combo_textfield.addKeyListener(new ProductListener());
		return cbo;
	}

	private Component getProductSuggestField() {
		Vector<String> productnames = Rightpanel_Handler.readProductNames();
		field_productsearch = new JSuggestField(this, productnames);
		field_productsearch.setToolTipText("Search product");
		field_productsearch
				.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		field_productsearch.setBounds(19, 10, 179, 27);
		field_productsearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String productname = field_productsearch.getText().trim();
				field_productsearch.setText("");
				if (productname.length() > 0) {
					try {
						addInvoiceTableRow(productname);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			}
		});
		
		field_productsearch.addFocusListener(new FocusListener() {
			String hint = "Search product";
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("Focus gain");
				 if(field_productsearch.getText().isEmpty()) {
					 field_productsearch.setText(hint);
			        }
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("Focus lost");
				 field_productsearch.setText("");
				/* if(field_productsearch.getText().isEmpty()) {
					 field_productsearch.setText("");
			        }*/
			}
		});
		return field_productsearch;
	}

	private Component getContactSuggestField() {
		contactsRepo = new ContactsRepository();
		ResultSet AllContacts = contactsRepo.readAllContacts();
		Vector<String> contactnames = contactsRepo
				.readContactNames(AllContacts);
		
		field_customersearch = new JSuggestField(this, contactnames);
		field_customersearch.setToolTipText("Search customer");
		field_customersearch.setText("Search customer");
		field_customersearch.setBounds(9, 384, 142, 31);
		field_customersearch.addActionListener(new CustomerSearchListener());
		field_customersearch.addFocusListener(new FocusListener() {
			String hint = "Search customer";
			@Override
			public void focusLost(FocusEvent e) {
				 if(field_customersearch.getText().isEmpty()) {
					 field_customersearch.setText(hint);
			        }
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				field_customersearch.setText("");
				/* if(field_productsearch.getText().isEmpty()) {
					 field_productsearch.setText("");
			        }*/
			}
		});
		return field_customersearch;
	}

	private JPanel getLeftPanel() {
		if (LeftPanel == null) {
			LeftPanel = new BackgroundImageComponent(images[0]);
			// LeftPanel.setBorder(new LineBorder(Color.black, 1, false));
			LeftPanel.setBounds(6, 6, 441, 547);
			LeftPanel.setLayout(null);
			// LeftPanel.add(getField_SearchProduct());
			LeftPanel.add(getProductSuggestField());
			// LeftPanel.add(getComboProductSearch());
			LeftPanel.add(getContactSuggestField());
			LeftPanel.add(getLabel_productimage());
			LeftPanel.add(getLabel_productname());
			LeftPanel.add(getSpinner_quantity());
			LeftPanel.add(getButton_addqty());
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

	private JPanel getRightPanel() throws SQLException {
		if (RightPanel == null) {
			RightPanel = new BackgroundImageComponent(images[1]);
			// RightPanel.setBorder(new LineBorder(Color.black, 1, false));
			RightPanel.setFocusable(true);
			RightPanel.setEnabled(true);
			RightPanel.setVisible(true);
			RightPanel.setDoubleBuffered(true);
			RightPanel.setVerifyInputWhenFocusTarget(true);
			RightPanel.setRequestFocusEnabled(true);
			RightPanel.setOpaque(true);
			RightPanel.setBounds(462, 7, 513, 546);
			RightPanel.setLayout(null);
			RightPanel.add(getLabel_rightpanel_headder());
			RightPanel.add(getLabel_number_products());
			RightPanel.add(getLabel_forwardaction());
			RightPanel.add(getLabel_backwordaction());
			JScrollPane jScrollPane = new JScrollPane(getPanel_products());
			jScrollPane.setAutoscrolls(true);
			jScrollPane.setBounds(6, 36, 503, 468);
			RightPanel.add(jScrollPane);
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
				InvoiceScreen frame = new InvoiceScreen();
				frame.setDefaultCloseOperation(InvoiceScreen.EXIT_ON_CLOSE);
				frame.setTitle("Vendhaq Invoice");
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

	private void formatTableCells(TableColumn column, int width) {
		column.setPreferredWidth(width);

	}

	private String[] readProducts(String name) throws SQLException {
		Connection conn = LocalDbConfiguration.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet result = stmt
				.executeQuery("select * from vtiger_products where productname like '"
						+ name + "%'");
		ArrayList<String> list = new ArrayList<String>();

		while (result.next()) {
			list.add(result.getString("productname"));
			System.out.println(result.getString("productname"));
		}

		return toArray(list);
	}

	private String[] toArray(ArrayList<String> list) {
		String data[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			data[i] = (String) list.get(i);

		}
		return data;
	}

	private class ProductListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Action performed");
			System.out.println("keycode " + e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // back button key
															// code = 8
				return;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) { // down button key code =
														// 40
				return;
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) { // down button key code = 90
				return;
			}

			String code = null;
			if (e.getKeyCode() == KeyEvent.VK_ENTER) // Enter button key code =
														// 10
			{
				System.out.println("Selected item: "
						+ cbo.getSelectedItem().toString());
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						cbo.hidePopup();
					}
				});
				return;
			}

			code = combo_textfield.getText();
			if (code != null && code.length() <= 2) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						cbo.hidePopup();
					}
				});
				return;
			}
			try {

				product_list = readProducts(code);
				// clear items
				cbo.removeAllItems();
				for (int i = 0; i < product_list.length; i++) {
					cbo.addItem(product_list[i]);
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (product_list.length != 0)
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						cbo.showPopup();
					}

				});

		}
	}

	private class CategoryButton_ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String selected_category = e.getActionCommand();
			System.out.println("Selected Category: " + selected_category);
			System.out.println("Clearing all panel items");
			panel_products.removeAll();

			buttons_products = Rightpanel_Handler
					.readProducts(selected_category);
			addToPRoductsPanel(buttons_products,
					new ProductButton_ActionListener());

			label_number_products.setText(String.valueOf(buttons_products
					.size()));
			label_rightpanel_headder.setText("Products");
			panel_products.revalidate();
			panel_products.repaint();

		}

	}

	private class ProductButton_ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String selected_product = e.getActionCommand().trim();
			System.out.println("Selected Product: " + selected_product);

			if (selected_product.length() > 0) {
				try {
					addInvoiceTableRow(selected_product);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		}

	}

	protected void addInvoiceTableRow(String productname) throws SQLException {
		String sql = "select * from vtiger_products where productname = '"
				+ productname + "'";

		Connection connection = LocalDbConfiguration.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet result_product = stmt.executeQuery(sql);
		if (result_product == null) {
			JOptionPane.showMessageDialog(null, "No product available");
		} else {
			boolean acept = true;
			if (acept) {
				int exist_rownum = isProductAlreadyInTable(result_product,
						productname);
				System.out.println("is row exist row number: " + exist_rownum);
				if (exist_rownum == -1) {

					// validate product quantity in store
					boolean status = true;// validateProductQuantity(product,
											// 1);
					if (status) {
						addProductTable(result_product);
					} else {
						// JOptionPane.showMessageDialog(null,
						// productvalidity_errormsg);
					}
				} else {
					int existquantity = Integer.parseInt((String) table_invoice
							.getValueAt(exist_rownum, qty_index));
					boolean status = true; // validateProductQuantity(product,existquantity
											// + 1);
					if (status) {
						updateProductTable(result_product, exist_rownum);
					} else {
						// JOptionPane.showMessageDialog(null,
						// productvalidity_errormsg);
					}
				}
			} else {
				System.out.println("validateProduct failed");
				// JOptionPane.showMessageDialog(null,
				// productvalidity_errormsg);
			}
		}

	}

	private void updateProductTable(ResultSet product, int exist_rownum) {
		String prev_qty = (String) table_invoiceModel.getValueAt(exist_rownum,
				qty_index);
		int qty = Integer.valueOf(prev_qty);
		qty = qty + 1;

		String productname = null, rate = null;
		try {
			productname = product.getString("productname");
			rate = product.getString("netprice");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String rowData[] = calculateInvoiceData(productname, rate,
				String.valueOf(qty));
		if (rowData.length != 0) {
			CELLUPDATE = true;
			// Update the row net amount field & quatity field only
			table_invoiceModel.setValueAt(rowData[qty_index], exist_rownum,
					qty_index);
			table_invoiceModel.setValueAt(rowData[mrp_index], exist_rownum,
					mrp_index);

		}
		updateTotalLabels();
		CELLUPDATE = false;
	}

	/**
	 * Add a new invoice to the product invoice table.
	 * 
	 * @param products
	 *            - Invoice product to add table.
	 */
	private void addProductTable(ResultSet product) {

		String productname = null, rate = null;
		try {
			productname = product.getString("productname");
			rate = product.getString("netprice");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String rowData[] = calculateInvoiceData(productname, rate, "1");
		// add product to table dynamically
		if (rowData.length != 0) {

			table_invoiceModel.addRow(rowData);
			ImageIcon imageIcon = new ImageIcon(getClass().getResource(
					"/com/vendhaq/layout/images/button-icon-remove.png"));
			table_invoiceModel.setValueAt(imageIcon,
					table_invoiceModel.getRowCount() - 1, delete_index);

		}
		updateTotalLabels();

	}

	/**
	 * Update label values
	 */
	private void updateTotalLabels() {
		int rows = table_invoiceModel.getRowCount();
		float totalgrand = 0;
		for (int i = 0; i < rows; i++) {
			// grand total amount
			float gamount = Float.parseFloat((String) table_invoiceModel
					.getValueAt(i, mrp_index));
			totalgrand += gamount;
		}
		label_subtotal_amount.setText(Util.toDecimalTwo(totalgrand)
				+ RIGHT_OFFSET);
		float tax_amount = Float.parseFloat(label_tax_amount.getText().trim());
		float topay_amount = totalgrand - tax_amount;
		label_total_amount.setText(Util.toDecimalTwo(topay_amount)
				+ RIGHT_OFFSET);
		label_topay_amount.setText(String.valueOf(Math.round(topay_amount))
				+ RIGHT_OFFSET);
	}

	/**
	 * Check product invoice table any existing invoice available.
	 * 
	 * @param product
	 *            - product to check already exist.
	 * @param barcode
	 *            - barcode of already existing invoice.
	 * @return true when invoice already exist otherwise return false.
	 */
	private int isProductAlreadyInTable(ResultSet products, String productname) {

		int exist_rownum = -1;
		int rows_num = table_invoiceModel.getRowCount();
		int barcode_colnum = 1;
		if (rows_num != 0) {
			for (int i = 0; i < rows_num; i++) {
				String row_productname = (String) table_invoiceModel
						.getValueAt(i, productname_index);
				if (row_productname.equalsIgnoreCase(productname)) {
					System.out.println("row_productname: " + row_productname
							+ " productname:" + productname
							+ " exist row number: " + i);
					exist_rownum = i;
					return exist_rownum;
				}
			}
		}
		return exist_rownum;

	}

	private String[] calculateInvoiceData(String productname,
			String rate_netprice, String quantity) {
		String rowData[] = new String[table_invoiceModel.getColumnCount()];

		rowData[qty_index] = quantity; // vtigerProducts.getBarcode();
		previous_quantity = rowData[qty_index]; // Maintain prev quantity value
		rowData[productname_index] = productname; // vtigerProducts.getProductname();
		rowData[rate_index] = Util.toDecimalTwo(rate_netprice); // vtigerProducts.getUnitPrice();

		float unit_price = Float.parseFloat(rowData[rate_index]);
		float qty = Float.parseFloat(rowData[qty_index]);
		float mrp_price = unit_price * qty;
		rowData[mrp_index] = Util.toDecimalTwo(mrp_price); // vtigerProducts.getDiscount();

		return rowData;
	}

	private class ImageRenderer implements TableCellRenderer {
		JLabel lbl = new JLabel();

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (row == itsRow && column == itsColumn) {
				String aStr = "Row " + row + "Column" + column + "Focus: "
						+ hasFocus;
				System.out.println(aStr);
				ImageIcon imageIcon = new ImageIcon(
						getClass()
								.getResource(
										"/com/vendhaq/layout/images/button-icon-remove-new.png"));
				lbl.setIcon(imageIcon);
			} else

				lbl.setIcon((ImageIcon) value);
			return lbl;
		}
	}

	private class QtyImageRenderer implements TableCellRenderer {

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
String OFFSET = "   ";
			JLabel lbl;
			if (row == itsRow && column == itsColumn) {
				String aStr = "Row " + row + "Column" + column + "Focus: "
						+ hasFocus;
				System.out.println(aStr);

				final ImageIcon imageIcon = new ImageIcon(
						getClass()
								.getResource(
										"/com/vendhaq/layout/images/qtyback_rolloverbutton.png"));
				lbl = new JLabel() {
					public void paintComponent(Graphics g) {
						g.drawImage(imageIcon.getImage(), 0, 0, null);
						super.paintComponent(g);
					}
				};

				lbl.setText(OFFSET + value.toString());
				// lbl.setIcon(imageIcon);

			} else
				lbl = new JLabel() {
					final ImageIcon imageIcon = new ImageIcon(
							getClass()
									.getResource(
											"/com/vendhaq/layout/images/qtyback_button.png"));

					public void paintComponent(Graphics g) {
						g.drawImage(imageIcon.getImage(), 0, 0, null);
						super.paintComponent(g);
					}
				};

			lbl.setText(OFFSET + value.toString());
			// lbl.setIcon(imageIcon);
			return lbl;
		}
	}

	private class RateImageRenderer extends DefaultTableCellRenderer {

		public void setHorizontalAlignment(int alignment) {
			super.setHorizontalAlignment(JLabel.RIGHT);
		}
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			JLabel lbl;
			String OFFSET = "   ";
			
			if (row == itsRow && column == itsColumn) {
				String aStr = "Row " + row + "Column" + column + "Focus: "
						+ hasFocus;
				System.out.println(aStr);

				final ImageIcon imageIcon = new ImageIcon(
						getClass()
								.getResource(
										"/com/vendhaq/layout/images/rate_rollbackground.png"));
				lbl = new JLabel() {
					public void paintComponent(Graphics g) {
						g.drawImage(imageIcon.getImage(), 0, 0, null);
						super.paintComponent(g);
					}
				};

				lbl.setText(OFFSET +"@"+ value.toString());
				// lbl.setIcon(imageIcon);

			} else
				lbl = new JLabel() {
					final ImageIcon imageIcon = new ImageIcon(
							getClass()
									.getResource(
											"/com/vendhaq/layout/images/rate_background.png"));

					public void paintComponent(Graphics g) {
						g.drawImage(imageIcon.getImage(), 0, 0, null);
						super.paintComponent(g);
					}
				};

			lbl.setText(OFFSET+"@"+ value.toString());
			// lbl.setIcon(imageIcon);
			return lbl;
		}
	}
	
	private class TableMouseActionLister extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();
				// do some action if appropriate column
				if (column == delete_index) {
					System.out.println("row: " + row + " col:" + column);
					table_invoiceModel.removeRow(row);
					updateTotalLabels();
				}
			}
		}
	}

	private class AddQuantity_ActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String quantity = spinner_quantity.getValue().toString().trim();
			int selected_row = table_invoice.getSelectedRow();
			if (selected_row != -1 && !quantity.equalsIgnoreCase("0")) {
				String productname = table_invoiceModel
						.getValueAt(selected_row, productname_index).toString()
						.trim();
				String rate = table_invoiceModel
						.getValueAt(selected_row, rate_index).toString().trim();
				System.out.println("selected row: " + selected_row
						+ " productname: " + productname + " rate:" + rate
						+ " qyt:" + quantity);

				String rowData[] = calculateInvoiceData(productname, rate,
						quantity);
				if (rowData.length != 0) {
					CELLUPDATE = true;
					// Update the row net amount field & quatity field only
					table_invoiceModel.setValueAt(rowData[qty_index],
							selected_row, qty_index);
					table_invoiceModel.setValueAt(rowData[mrp_index],
							selected_row, mrp_index);

				}
				updateTotalLabels();
				CELLUPDATE = false;

			}

		}

	}

	private class CustomerSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String selected_contact = e.getActionCommand();
			if (selected_contact.isEmpty()
					|| selected_contact.equalsIgnoreCase("")) {
				System.out.println("Select contact");
				return;
			}
			System.out.println("Selected contact: " + selected_contact);
			/* read contact id from vtiger_contadetails */
			ResultSet ContactResult = contactsRepo
					.readContact(selected_contact);

			try {
				String firstname = ContactResult.getString("firstname");
				String lastname = ContactResult.getString("lastname");
				String mobileno = ContactResult.getString("mobile");
				String contactid = ContactResult.getString("contactid");
				/* Read contact royalty point */
				String sql = "select * from vtiger_contactroyality where contactid = "
						+ contactid;
				System.out.println("Contact royality query: " + sql);
				ResultSet royality_result = contactsRepo.executeQuery(sql);
				String earnedpoint = royality_result.getString("royalitycount");
				String sText = "<html>" + (firstname + lastname) + " <br/>"
						+ mobileno
						+ " <br/> Earned points &nbsp&nbsp&nbsp&nbsp&nbsp "
						+ earnedpoint + "</html>";
				System.out.println("label text: " + sText);
				label_customername_label.setText(sText);
				ImageIcon imageIcon = new ImageIcon(getClass().getResource(
						"/com/vendhaq/layout/images/delete-icon.png"));
				label_customername_label.setIcon(imageIcon);

				label_customername_label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int clicks = e.getClickCount();
						if (clicks == 2) {
							label_customername_label.setIcon(null);
							label_customername_label.setText("No Customer");
							label_customername_label.removeMouseListener(this);
						}
					}
				});

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class InvoiceTableMouseMaotionAdapter extends MouseMotionAdapter // extendsMouseAdapter
	{

		public void mouseMoved(MouseEvent e) {
			JTable aTable = (JTable) e.getSource();
			itsRow = aTable.rowAtPoint(e.getPoint());
			itsColumn = aTable.columnAtPoint(e.getPoint());
			aTable.repaint();
		}
	}

	

}
