package com.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class InvoiceLayout_Old extends javax.swing.JFrame {
	private static final int ARC_WIDTH = 30;
	private static final int ARC_HEIGHT = 30;
	private JPanel mainPanel;
	private JPanel leftpanel;
	private JLabel jLabel3;
	private JPanel ItemsPanel_Box;
	private DefaultTableModel table_invoiceModel;
	private JTable table_invoice;
	private JTableHeader tabelheadder;
	private JPanel tableheaderpanel = new JPanel();
	private int cell_width[] = { 20, 200, 40, 40, 10 };
	private JLabel label_totalamount;
	private JLabel label_discount;
	private JLabel label_grandtotal;
	private JLabel totalamount_lable;
	private JLabel discountamount_lable;
	private JLabel grandtotal_lable;
	public static String[] producttable_headder = { "Qty", "Item Name",
			"Rate ", "MRP", "Delete" };
	private JPanel panel_searchproducts;
	private JButton button_categery5;
	private JButton button_categery4;
	private JButton button_categery3;
	private JButton button_categery2;
	private JButton button_categery1;
	private JButton label_addimage;
	private JSpinner spinner_quantity;
	private JTextField field_searchproducts;
	private JLabel label_customername;
	private JTextField filed_searchcustomer;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InvoiceLayout inst = new InvoiceLayout();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public InvoiceLayout_Old() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			{
				mainPanel = new JPanel();
				BorderLayout mainpanelLayout = new BorderLayout();
				mainPanel.setLayout(mainpanelLayout);
				getContentPane().add(mainPanel, BorderLayout.CENTER);
				mainPanel.setPreferredSize(new java.awt.Dimension(845, 480));

				{
					leftpanel = new JPanel();
					// leftpanel = new RoundJPanel(Color.BLUE, ARC_WIDTH,
					// ARC_HEIGHT);
					leftpanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
					leftpanel.setLayout(new FlowLayout(
							SwingConstants.HORIZONTAL));
					leftpanel.setBackground(Color.white);
					mainPanel.add(leftpanel, BorderLayout.EAST);
					leftpanel.setPreferredSize(new java.awt.Dimension(543, 816));

					{
						button_categery1 = new JButton();
						leftpanel.add(button_categery1);
						button_categery1.setText("Indian Style");

						button_categery1
								.setPreferredSize(new java.awt.Dimension(100,
										60));
					}
					{
						button_categery2 = new JButton();
						leftpanel.add(button_categery2);
						button_categery2.setText("Chinese ");
						button_categery2
								.setPreferredSize(new java.awt.Dimension(100,
										60));
					}
					{
						button_categery3 = new JButton();
						leftpanel.add(button_categery3);
						button_categery3.setText("South");
						button_categery3
								.setPreferredSize(new java.awt.Dimension(100,
										60));
					}
					{
						button_categery4 = new JButton();
						leftpanel.add(button_categery4);
						button_categery4.setText("North");
						button_categery4
								.setPreferredSize(new java.awt.Dimension(100,
										60));
					}
					{
						button_categery5 = new JButton();
						leftpanel.add(button_categery5);
						button_categery5.setText("Mexican");
						button_categery5
								.setPreferredSize(new java.awt.Dimension(100,
										60));
					}
				}
				{
					 ItemsPanel_Box = new JPanel();
//					ItemsPanel_Box = new RoundJPanel(Color.GRAY, ARC_WIDTH,
//							ARC_HEIGHT);
					ItemsPanel_Box.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
					ItemsPanel_Box.setBackground(Color.WHITE);
					mainPanel.add(ItemsPanel_Box, BorderLayout.WEST);
					ItemsPanel_Box.setLayout(null);
					ItemsPanel_Box.setBounds(25, 110, 429, 590);
					ItemsPanel_Box.setName("ItemsPanel_Box");
					ItemsPanel_Box.setPreferredSize(new java.awt.Dimension(684, 792));
					

				}

				/** Initialize table headder panle elements */
				table_invoiceModel = new DefaultTableModel(new String[][] {},
						producttable_headder);

				table_invoice = new JTable(table_invoiceModel) {
					public Class getColumnClass(int column) {
						return getValueAt(0, column).getClass();
					}

					public Component prepareRenderer(
							TableCellRenderer renderer, int row, int column) {
						Component c = super.prepareRenderer(renderer, row,
								column);
						JComponent jc = (JComponent) c;
						if (!isRowSelected(row))
							c.setBackground(Color.white);
						return c;
					}

					@Override
					public boolean isCellEditable(int row, int column) {

						return false;
					}

				};
				// table_invoice.setCellSelectionEnabled(false);
				// ListSelectionModel cellSelectionModel =
				// table_invoice.getSelectionModel();
				// cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// cellSelectionModel.addListSelectionListener(new
				// SelectionListener());
				
				table_invoice.setLayout(null);
				table_invoice.setBounds(2, 49, 940, 300);
				table_invoice.setIntercellSpacing(new Dimension(10, 50));
				table_invoice.setFont(new Font("Arail", Font.CENTER_BASELINE,
						12));
				table_invoice.setForeground(Color.BLACK);
				table_invoice.setBackground(Color.white);
				table_invoice.setOpaque(true);
				table_invoice.setRowMargin(5);
				table_invoice.setRowHeight(30);
				table_invoice.setFont(new Font("Arial", Font.BOLD, 14));

				table_invoice.setName("table_invoice");
				tabelheadder = table_invoice.getTableHeader();

				tabelheadder.setBackground(Color.GRAY);

				tabelheadder.setForeground(Color.BLACK);
				tabelheadder.setFont(new Font("Arial", Font.BOLD, 12));
				tabelheadder.setOpaque(true);
				// adding cell value change listener
				// addTableListener();

				// make table formatting
				for (int i = 0; i < table_invoiceModel.getColumnCount(); i++) {
					formatTableCells(table_invoice.getColumnModel()
							.getColumn(i), cell_width[i]);
				}

				JScrollPane scrollpane = new JScrollPane(table_invoice);
				//scrollpane.setBorder(new LineBorder(Color.BLACK, 2, true));
				scrollpane.setBounds(1, 2, 685, 359);
				tableheaderpanel.setLayout(null);
				tableheaderpanel.setBounds(2, 162, 675, 365);

				tableheaderpanel.setName("tableheaderpanel");
				tableheaderpanel.add(scrollpane);
				scrollpane.setName("scrollpane");

				scrollpane.setAutoscrolls(true);
				scrollpane.setBackground(Color.WHITE);
				scrollpane.setOpaque(true);
				scrollpane.setEnabled(false);
				{
					jLabel3 = new JLabel();
					tableheaderpanel.add(jLabel3);
					jLabel3.setText("jLabel3");
					jLabel3.setBounds(567, 222, 34, 14);
				}

				ItemsPanel_Box.add(tableheaderpanel);
				{
					label_totalamount = new JLabel("Total: ",
							SwingConstants.RIGHT);
					ItemsPanel_Box.add(label_totalamount);
					label_totalamount.setLayout(null);
					label_totalamount.setName("label_totalamount");
					label_totalamount
							.setVerticalAlignment(SwingConstants.CENTER);
					label_totalamount.setBounds(432, 549, 159, 56);
					label_totalamount.setText("Subtotal");
				}
				{
					label_discount = new JLabel("(-) Discount: ",
							SwingConstants.RIGHT);
					ItemsPanel_Box.add(label_discount);
					label_discount.setLayout(null);
					label_discount.setName("label_discount");
					label_discount.setVerticalAlignment(SwingConstants.CENTER);
					label_discount.setBounds(432, 604, 159, 56);
					label_discount.setText("Tax:");
				}
				{
					label_grandtotal = new JLabel("Grand Total: ",
							SwingConstants.RIGHT);
					ItemsPanel_Box.add(label_grandtotal);
					label_grandtotal.setLayout(null);
					label_grandtotal.setName("label_grandtotal");
					label_grandtotal
							.setVerticalAlignment(SwingConstants.CENTER);
					label_grandtotal.setBounds(432, 660, 159, 56);
					label_grandtotal.setText("TOTAL:");
				}
				{
					totalamount_lable = new JLabel("0", SwingConstants.CENTER);
					ItemsPanel_Box.add(totalamount_lable);
					totalamount_lable.setLayout(null);
					totalamount_lable
							.setVerticalAlignment(SwingConstants.CENTER);
					totalamount_lable.setBounds(597, 549, 109, 58);
					totalamount_lable.setName("totalamount_lable");
				}
				{
					discountamount_lable = new JLabel("0",
							SwingConstants.CENTER);
					ItemsPanel_Box.add(discountamount_lable);
					discountamount_lable.setLayout(null);
					discountamount_lable.setBounds(597, 602, 109, 58);
					discountamount_lable
							.setVerticalAlignment(SwingConstants.CENTER);
					discountamount_lable.setName("discountamount_lable");
				}
				{
					grandtotal_lable = new JLabel("0", SwingConstants.CENTER);
					ItemsPanel_Box.add(grandtotal_lable);
					grandtotal_lable.setLayout(null);
					grandtotal_lable.setBounds(597, 660, 109, 58);
					grandtotal_lable.setName("grandtotal_lable");
				}
				{
					filed_searchcustomer = new JTextField();
					ItemsPanel_Box.add(filed_searchcustomer);
					filed_searchcustomer.setText("Search customer");
					filed_searchcustomer.setBounds(42, 578, 169, 29);
				}
				{
					label_customername = new JLabel();
					ItemsPanel_Box.add(label_customername);
					label_customername.setText("No customer");
					label_customername.setBounds(42, 628, 94, 17);
				}
				{
					SpinnerListModel spinner_quantityModel = new SpinnerListModel(
							new String[] { "1", "2", "3", "4", "5", "6", "7",
									"8", "9", "10" });
					spinner_quantity = new JSpinner();
					ItemsPanel_Box.add(spinner_quantity);
					spinner_quantity.setModel(spinner_quantityModel);
					spinner_quantity.setBounds(444, 93, 147, 29);
				}
				{
					label_addimage = new JButton(new ImageIcon(
							"images/add-icon.jpg"));
					ItemsPanel_Box.add(label_addimage);
					label_addimage.setBounds(600, 97, 20, 20);
				}
				{
					panel_searchproducts = new JPanel();
					panel_searchproducts.setBackground(Color.LIGHT_GRAY);
					ItemsPanel_Box.add(panel_searchproducts);
					panel_searchproducts.setBounds(5, 6, 672, 63);
					panel_searchproducts.setLayout(null);
					{
						field_searchproducts = new JTextField();
						panel_searchproducts.add(field_searchproducts);
						field_searchproducts.setText("Search products");
						field_searchproducts.setBounds(18, 11, 204, 36);
					}
				}
				/** End of the table headder panel elements. */

				discountamount_lable
						.setVerticalAlignment(SwingConstants.CENTER);

			}
			pack();
			this.setSize(1250, 850);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void formatTableCells(TableColumn column, int width) {
		column.setPreferredWidth(width);
	}
	
	class BackgroundImageComponent extends JPanel
	{
	    BufferedImage image;
	 
	    public BackgroundImageComponent(BufferedImage image)
	    {
	        this.image = image;
	    }
	 
	    protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        int w = getWidth();
	        int h = getHeight();
	        int imageWidth = image.getWidth();
	        int imageHeight = image.getHeight();
	        int x = (w - imageWidth)/2;          // center the image
	        int y = (h - imageHeight)/2;         // in its container
	        g.drawImage(image, x, y, this);
	    }
	 
	    public Dimension getPreferredSize()
	    {
	        return new Dimension(image.getWidth(), image.getHeight());
	    }
	}
	
	
}
