package com.vendhaq.handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;

import com.vendhaq.utils.DateUtils;
import com.vendhaq.utils.Util;



/**
 * Class: IntroPage
 * <p>
 * This class defines the painter for the cover page by implementing the Printable interface.
 * <p>
 * 
 * @see Printable
 */
public class InvoicePagePrint implements Printable {

	private static final int POINTS_PER_INCH = 72;
	HashMap<String, Object> consolidate_data = null;

	public InvoicePagePrint(HashMap<String, Object> consolidate_data) {
		this.consolidate_data = consolidate_data;
	}

	/**
	 * Method: print
	 * <p>
	 * 
	 * @param g
	 *            a value of type Graphics
	 * @param pageFormat
	 *            a value of type PageFormat
	 * @param page
	 *            a value of type int
	 * @return a value of type int
	 */
	public int print(Graphics g, PageFormat pageFormat, int page) {
		if (page < 0 || page >= 1) {
			return Printable.NO_SUCH_PAGE;
		}

		// width: 220.0 height: 841.68
		int MAX_WIDTH = (int) 220.0;
		int MAX_HEIGHT = (int) 841.68;
		System.out.println("MAX_WIDTH: " + MAX_WIDTH + " MAX_HEIGHT: " + MAX_HEIGHT);

		int WIDTH_MIDDLE = (int) (MAX_WIDTH / 2);

		float royalitypointsearned = Float.parseFloat((String) consolidate_data.get("royalitypointsearned"));
		int yaxis = 140;
		int yoffset = 10;

		// --- Create the Graphics2D object
		Graphics2D g2d = (Graphics2D) g;

		// PageFormat size

		// --- Translate the origin to 0,0 for the top left corner
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		// --- Set the default drawing color to black
		g2d.setPaint(Color.black);

		// --- Draw a border arround the page
		// Rectangle2D.Double border = new Rectangle2D.Double(0, 0, pageFormat.getImageableWidth(),
		// pageFormat.getImageableHeight());
		// g2d.draw(border);

		// --- Print the title

		Font superbold = new Font("Arail", Font.BOLD, 10);
		Font bold = new Font("Arail", Font.BOLD, 8);
		Font normal = new Font("Arail", Font.PLAIN, 8);

		g2d.setFont(superbold);
		String companyname = (String) consolidate_data.get("companyname");
		int offset = companyname.length();
		int xval = (150 / 2) - offset;
		g2d.drawString(companyname, xval, 20);
		String address1 = (String) consolidate_data.get("address1");
		offset = address1.length();
		xval = (180 / 2) - offset;
		g2d.setFont(normal);
		g2d.drawString(address1, xval, 35);
		String address2 = (String) consolidate_data.get("address2");
		offset = address1.length();
		xval = (180 / 2) - offset;
		g2d.drawString((String) consolidate_data.get("address2"), xval, 45);
		String phone = (String) consolidate_data.get("phone");
		offset = phone.length();
		xval = (180 / 2) - offset;
		g2d.drawString((String) consolidate_data.get("phone"), xval, 55);

		g2d.drawString("Tax Invoice No. " + (String) consolidate_data.get("invoiceno"), 3, 75);
		g2d.drawString(DateUtils.currentTime(), 150, 75);
		g2d.setFont(bold);
		g2d.drawString("User: " + consolidate_data.get("salesman"), 3, 85);
		g2d.drawString(DateUtils.getDate(System.currentTimeMillis()), 150, 85);

		g2d.setFont(bold);
		g2d.drawString(
					"---------------------------------------------------------------------------------------------------------------------------------------",
					3, 100);
		// Header starts from here
		g2d.setFont(normal);
		g2d.drawString("Item Name", 3, 110);
		g2d.drawString("Price", 90, 110);
		g2d.drawString("Qty.", 130, 110);
		g2d.drawString("Total", 165, 110);
		g2d.setFont(bold);
		g2d.drawString(
					"---------------------------------------------------------------------------------------------------------------------------------------",
					3, 125);
		g2d.setFont(normal);
		/* Royalty points earned by the customer */

		if (royalitypointsearned > 0) {
			g2d.setFont(bold);
			yaxis = 140;
			g2d.drawString("Congratulations!! you have earned royalty points", 3, yaxis);
			yaxis = yaxis + 10;
			g2d.drawString(String.valueOf(royalitypointsearned), 140, yaxis);
		}

		yaxis = yaxis + yoffset;

		/* each product details print */
		ArrayList<HashMap> productdata_list = (ArrayList<HashMap>) consolidate_data.get("productsdata");
		for (int i = 0; i < productdata_list.size(); i++) {
			HashMap<String, String> product = productdata_list.get(i);
			g2d.setFont(normal);
			int sno = i + 1;
			
			String pname = product.get("productName");
			String quantity = product.get("qty");
			float price = Float.parseFloat(product.get("price"));
			float total = Util.Round(Float.parseFloat(product.get("netprice")), 2);

			g2d.drawString(pname.trim(), 3, yaxis);
			g2d.drawString(String.format("%.02f", price), 90, yaxis);
			g2d.drawString(quantity, 130, yaxis);
			g2d.drawString(String.format("%.02f", total), 165, yaxis);
			//float itemdiscount = Float.parseFloat(product.get("itemdiscount"));
			float itemdiscount = 0;
			if (itemdiscount > 0) {
				
				g2d.setFont(bold);
				yaxis = yaxis + 10;

				float dis = Util.Round(itemdiscount, 2);
				g2d.drawString("Fantastic Discount", 20, yaxis);
				g2d.drawString(" " + String.format("%.02f", dis), 140, yaxis);
			}

			yaxis = yaxis + yoffset;
		} // products completed

		// print the products total amount part
		yaxis = yaxis + yoffset;
		g2d.setFont(bold);
		g2d.drawString(
					"---------------------------------------------------------------------------------------------------------------------------------------",
					3, yaxis);
		float royaltydiscount = Float.parseFloat((String) consolidate_data.get("royaltydiscount"));
		if (royaltydiscount > 0) {
			yaxis = yaxis + yoffset;
			g2d.drawString("Royalty Discount", 3, yaxis);
			royaltydiscount = Util.Round(royaltydiscount, 2);
			g2d.drawString(String.format("%.02f", royaltydiscount), 140, yaxis);
		}

		yaxis = yaxis + yoffset;
		g2d.drawString("Grand Total", 3, yaxis);
		float gtotal = Util.Round(Float.parseFloat((String) consolidate_data.get("grandtotal")), 2);
		g2d.drawString(String.format("%.02f", gtotal), 140, yaxis);

		float totaldiscount = Float.parseFloat((String) consolidate_data.get("totaldiscount"));
		if (totaldiscount > 0) {
			yaxis = yaxis + yoffset;
			g2d.drawString("Saved Total", 3, yaxis);
			String.format("%.02f", totaldiscount);
			totaldiscount = Util.Round(totaldiscount, 2);
			g2d.drawString(String.format("%.02f", totaldiscount), 140, yaxis);
		}

		yaxis = yaxis + yoffset;
		g2d.setFont(bold);
		g2d.drawString(
					"---------------------------------------------------------------------------------------------------------------------------------------",
					3, yaxis);

		/* start footer print */
		yaxis = yaxis + yoffset;
		g2d.drawString("Terms & Conditions", 30, yaxis);

		g2d.setFont(new Font("Arail", Font.PLAIN, 7));
		yaxis = yaxis + 15;
		g2d.drawString("NO CASH REFUND IN ANY CASE", 3, yaxis);

		yoffset = 10;
		yaxis = yaxis + yoffset;
		g2d.drawString("YOU CAN EXCHANGE THE GOODS (AFTERNOON 12:00 PM)", 3, yaxis);
		yaxis = yaxis + yoffset;
		g2d.drawString("WITHIN THREE DAYS FROM THE DATE", 10, yaxis);
		yaxis = yaxis + yoffset;
		g2d.drawString("OF THE PURCHASE.", 10, yaxis);

		yaxis = yaxis + yoffset;
		g2d.drawString("INVOICE MUST FOR EXCHANGE", 3, yaxis);

		yaxis = yaxis + yoffset;
		g2d.drawString("THERE IS NO GUARANTEE FOR SILK, JARI,", 3, yaxis);
		yaxis = yaxis + yoffset;
		g2d.drawString("READYMADE AND OTHER FANCY ITEMS.", 13, yaxis);

		yaxis = yaxis + yoffset;
		g2d.setFont(bold);
		g2d.drawString("-------------------------------------------------------------------------------------------------", 3, yaxis);
		g2d.setFont(normal);
		g2d.setFont(bold);
		yaxis = yaxis + yoffset;
		g2d.drawString("THANKS YOU FOR SHOPPING @ PREM TEXTILE", 10, yaxis);

		yaxis = yaxis + yoffset;
		g2d.drawString("VAT RegNo.: " + consolidate_data.get("tin"), 10, yaxis);

		yaxis = yaxis + yoffset;
		g2d.setFont(bold);
		g2d.drawString("-------------------------------", 80, yaxis);

		/* End of footer print */

		return (PAGE_EXISTS);
	}
}
