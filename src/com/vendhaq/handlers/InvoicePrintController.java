package com.vendhaq.handlers;



import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;

import com.vendhaq.layout.InvoiceScreen;
import com.vendhaq.utils.Util;


public class InvoicePrintController {

	HashMap<String, Object> consolidate_data = new HashMap<String, Object>();
	ArrayList<HashMap> productdata_list = new ArrayList<HashMap>();

	public void printInvoice(String[][] tabledata, float invoice_totalamount, int invoice_totalitemsdiscount, float invoice_royaltydiscount,
				float invoice_grandamount, String invoicenum_new, String royalitypointsearned) {
		// prepare consolidate data
		consolidate_data = prepareConsolidateData(tabledata, invoice_totalamount, invoice_totalitemsdiscount, invoice_royaltydiscount,
					invoice_grandamount, invoicenum_new, royalitypointsearned);
		// make print layout
		InvoicePagePrint pageprintdocumnet = new InvoicePagePrint(consolidate_data);

		// prepare printer
		//printDocument(pageprintdocumnet);

		// testing with default
		defaultPrintPage(pageprintdocumnet);
	}

	
	private void defaultPrintPage(InvoicePagePrint pageprintdocumnet)
	{
		PrintService[] service = PrinterJob.lookupPrintServices(); // list
		// of
		// printers
		int count = service.length;
		PrintService printSvc = null;
		for (int i = 0; i < count; i++) {
			System.out.println("Service name: " + service[i].getName());
			if (service[i].getName().indexOf("PDFcamp") != -1) {
				printSvc = service[i];
				i = count;
			}
		}
		
		//  TSP650  
		PageFormat format = new PageFormat();
		   Paper paper = new Paper();

		   double paperWidth = 3.25;
		   double paperHeight = 11.69;
		   double leftMargin = 0.19;
		   double rightMargin = 0.25;
		   double topMargin = 0;
		   double bottomMargin = 0.01;

		   paper.setSize(paperWidth * 72.0, paperHeight * 72.0);
		   paper.setImageableArea(leftMargin * 72.0, topMargin * 72.0,
		        (paperWidth - leftMargin - rightMargin) * 72.0,
		        (paperHeight - topMargin - bottomMargin) * 72.0);

		   format.setPaper(paper);

		   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		   aset.add(OrientationRequested.PORTRAIT);

		   PrinterJob printerJob = PrinterJob.getPrinterJob();
		   try {
			printerJob.setPrintService(printSvc);
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   //Printable printable = new ReceiptPrintTest();
		   format = printerJob.validatePage(format);
		   //printerJob.setPrintable(printable, format);
		   printerJob.setPrintable(pageprintdocumnet, format);
		   try {
		      printerJob.print(aset);
		   }
		   catch (Exception e) {
		       e.printStackTrace();
		   }
	}
	
	
	
	private void printDocument(InvoicePagePrint pageprintdocumnet) {
		PrintService[] service = PrinterJob.lookupPrintServices(); // list
		// of
		// printers
		int count = service.length;
		PrintService printSvc = null;
		for (int i = 0; i < count; i++) {
			System.out.println("Service name: " + service[i].getName());
			if (service[i].getName().indexOf("PDFcamp") != -1) {
				printSvc = service[i];
				i = count;
			}
		}

		// --- Create a new PrinterJob object and set predefined print service.
		try {

			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setPrintService(printSvc);

			PageFormat pf = printJob.defaultPage();
			// Paper paper = pf.getPaper();
			// paper.setSize(8.5 * 72, 11 * 72);
			// paper.setSize(79, 78);

			// paper.setImageableArea(0.5 * 72, 0.0 * 72, 7.5 * 72, 11.5 * 72);
			// paper.setImageableArea(0.5 , 0.0 , 79, 78);
			// pf.setPaper(paper);

			// --- Create a new book to add pages to
			Book book = new Book();

			// --- Add the cover page using the default page format for this
			// print
			// job
			// book.append(pageprintdocumnet, printJob.defaultPage());
			book.append(pageprintdocumnet, pf);

			// Paper pg = new Paper();
			// pg.setSize(1000, 900);
			// book.append(pageprintdocumnet, printJob..defaultPage();

			// --- Add the document page using a landscape page format
			/*
			 * PageFormat documentPageFormat = new PageFormat();
			 * documentPageFormat.setOrientation(PageFormat.LANDSCAPE); book.append(new Document(),
			 * documentPageFormat);
			 */
			// --- Add a third page using the same painter

			// --- Tell the printJob to use the book as the pageable object
			printJob.setPageable(book);

			// --- Show the print dialog box. If the user click the
			// --- print button we then proceed to print else we cancel
			// --- the process.
			// if (printJob.printDialog()) {

			printJob.print();
		} catch (Exception PrintException) {
			PrintException.printStackTrace();
		}

	}

	/**
	 * <p>
	 * Prepares the consolidate data to make invoice print.
	 * </p>
	 * 
	 * @param invoceproduct_list
	 *            - Contains invoices as {@link List}
	 * @param netprice_list
	 *            - Contains netprice of each product invoice.
	 * @param invoice_totalamount
	 *            - Represent total of all netprice.
	 * @param invoice_totaldiscount
	 *            - Represent total discount amount over all products
	 * @param invoice_grandamount
	 *            - Represent grand total amount
	 * @param invoicenum_new
	 *            - Represent the invoice transaction CRM id.
	 * @param royalitypointsearned 
	 */
	private HashMap<String, Object> prepareConsolidateData(String[][] tabledata, float invoice_totalamount, int invoice_totalitemsdiscount,
				float invoice_royaltydiscount, float invoice_grandamount, String invoicenum_new, String royalitypointsearned) {
		// company details
		consolidate_data.put("invoiceno", "INV1");
		consolidate_data.put("grandtotalinword", "Only");
		consolidate_data.put("companyname", "Treewalker Technologies");
		consolidate_data.put("tin", "tin");
		consolidate_data.put("address1", "*******  Sector 2 *************");
		consolidate_data.put("address2", "HSR Layout , Bangalore");
		consolidate_data.put("phone", "Ph: 8971855771");
		consolidate_data.put("salesman", "kiran");
		consolidate_data.put("royaltydiscount", String.valueOf(invoice_royaltydiscount));
		consolidate_data.put("grandtotal", String.valueOf(invoice_grandamount));
		consolidate_data.put("totaldiscount", String.valueOf(invoice_totalitemsdiscount));
		consolidate_data.put("royalitypointsearned", royalitypointsearned);
		// product data details
		for (int i = 0; i < tabledata.length; i++) {

			String invoice[] = tabledata[i];
			HashMap<String, String> product = new HashMap<String, String>();
			for (int j = 0; j < invoice.length; j++) {
				
				product.put("productName", invoice[InvoiceScreen.productname_index]);
				product.put("qty", invoice[InvoiceScreen.qty_index]);
				product.put("price", invoice[InvoiceScreen.rate_index]);
				product.put("netprice", invoice[InvoiceScreen.mrp_index]);

			}

			productdata_list.add(product);

		}
		consolidate_data.put("productsdata", productdata_list);
		return consolidate_data;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
