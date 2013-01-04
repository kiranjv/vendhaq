/**
 * 
 */
package com.vendhaq.handlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.vendhaq.models.InvoiceProduct;
import com.vendhaq.models.VtigerCashreceipt;
import com.vendhaq.models.VtigerCashreceiptId;
import com.vendhaq.models.VtigerContactdetails;
import com.vendhaq.models.VtigerContactroyality;
import com.vendhaq.models.VtigerContactscf;
import com.vendhaq.models.VtigerInventoryproductrel;
import com.vendhaq.models.VtigerInventorytransaction;
import com.vendhaq.models.VtigerInvoice;
import com.vendhaq.models.VtigerInvoicebillads;
import com.vendhaq.models.VtigerInvoicecf;
import com.vendhaq.models.VtigerInvoiceshipads;
import com.vendhaq.models.VtigerModentitynum;
import com.vendhaq.models.VtigerProducts;
import com.vendhaq.models.VtigerRoyality;
import com.vendhaq.models.VtigerWarehouseStock;
import com.vendhaq.models.VtigerWarehousestoreInventorytransaction;
import com.vendhaq.repos.HCrmEntityRepository;
import com.vendhaq.utils.DateUtils;
import com.vendhaq.utils.InvoiceUtil;
import com.vendhaq.utils.Util;


/**
 * @author treewalker
 * 
 */
public class InvoiceController extends InvoiceUtil {

	private int royalitpointsearned;

	public int saveInvoice(List<InvoiceProduct> invoceproduct_list, String[] netprice_list, float invoice_totalamount, float invoice_totaldiscount,
				float invoice_grandamount, HashMap<String, String> royalityhash) {
		String royalityno = null;
		int redeem_points = 0;
		String customername = null;
		String mobile = null;
		if (royalityhash != null && !royalityhash.isEmpty()) {

			royalityno = royalityhash.get("royalityno");
			String redeempoints = royalityhash.get("redeempoints");
			if (redeempoints != null && !redeempoints.equalsIgnoreCase(" ") && !redeempoints.isEmpty())
				redeem_points = Integer.parseInt(redeempoints);
			customername = royalityhash.get("customername");
			mobile = royalityhash.get("customermobileno");
		}

		String grandtotal = String.valueOf(invoice_grandamount);
		String discountamount = String.valueOf(invoice_totaldiscount);
		String paidamount = String.valueOf(invoice_grandamount);
		String warehouseid = "2";
		String credit_amount = "100.0";
		String pendingamount = "0";

		// create new crmid to invoice
		HCrmEntityRepository crm_repo = new HCrmEntityRepository();
		int new_crmid = crm_repo.generateCrmId("Invoice");
		System.out.println("new crm id: " + new_crmid);
		// create new invoice number
		String invoicenum_new = generateNewInvoiceNumber();
		System.out.println("new invoice id: " + invoicenum_new);
		// retrive user, bank, warehouse id other details

		// prepare invoice insert query to local db
		String invoicedate = DateUtils.getDateTime(System.currentTimeMillis());
		String type = "";
		String adjustment = "0";
		String subtotal = grandtotal;
		String total = grandtotal;
		String discount_amount = discountamount;
		String invoicestatus = "Paid";
		String invoice_no = invoicenum_new;
		String salesorderno = "";
		String email = "kiran@treewalker.in";
		String cash = grandtotal;
		String credit = "";
		String card = "";
		String bankname = "";
		String chq_date = "";
		String cheque_no = "";
		String cheque_amt = "";
		String storeid = warehouseid;

		String insert_sql = "insert IGNORE into vtiger_invoice set invoiceid= " + new_crmid + ",invoicedate =" + invoicedate + " ,type=" + type
					+ " ,adjustment=" + adjustment + " ,subtotal =" + subtotal + " ,total =" + total + " ,discount_amount=" + discount_amount
					+ " ,invoicestatus=" + invoicestatus + " ,invoice_no=" + invoice_no + " ,customername=" + customername + " ,mobile=" + mobile
					+ " ,salesorderno=" + salesorderno + ", email=" + email + " ,cash=" + cash + " ,credit=" + credit + " ,card=" + card
					+ " ,bankname=" + bankname + " ,chq_date=" + chq_date + " ,cheque_no=" + cheque_no + " ,cheque_amt=" + cheque_amt + " ,storeid="
					+ storeid;

		System.out.println("vitger_invoice query: " + insert_sql);

		VtigerInvoice invoice = new VtigerInvoice(new_crmid, invoicedate, type, adjustment, subtotal, total, discount_amount, invoicestatus,
					invoice_no, customername, mobile, salesorderno, email, cash, credit, card, bankname, chq_date, cheque_no, cheque_amt, storeid);

		// insert invoice into database
		DBLocalHelper.saveRecord("VtigerInvoice", invoice);
		// DBLocalHelper.excecuteQuery(insert_sql);
		System.out.println("vtiger_invoice sucess");

		// Add new VtigerInvoicecf to local database
		System.out.println("vitger_invoicecf query");
		VtigerInvoicecf invoicecf = new VtigerInvoicecf(new_crmid);
		DBLocalHelper.saveRecord(VtigerInvoicecf.class.getSimpleName(), invoicecf);
		System.out.println("vitger_invoicecf query completed");

		// royalty points handler
		if (royalityno != null && !royalityno.isEmpty()) {

			System.out.println("Royality number exists");
			List royaltycontacts = DBLocalHelper.readRecord(VtigerContactroyality.class.getSimpleName(), "royalitynumber", royalityno);
			VtigerContactroyality contactroyalty = null;
			if (royaltycontacts.size() > 0) {
				contactroyalty = (VtigerContactroyality) royaltycontacts.get(0);
				System.out.println("contact royalty:" + contactroyalty.toString());
			}

			/* Fetch the royality points formula */
			String current_date = DateUtils.getDate(System.currentTimeMillis());
			/*
			 * String H_RoyaltyUpdate = "From " + VtigerRoyality.class.getSimpleName() + " WHERE ("
			 * + current_date + " >= from_date AND " + current_date + " <= to_date) AND deleted=0";
			 */
			String H_RoyaltyUpdate = "From " + VtigerRoyality.class.getSimpleName() + " WHERE deleted=0";
			System.out.println("query: " + H_RoyaltyUpdate);
			List<VtigerRoyality> vtigerroyalitys = DBLocalHelper.executeHQuery(H_RoyaltyUpdate);
			System.out.println("Number of royaltys: " + vtigerroyalitys.size());
			VtigerRoyality vtigerroyality = vtigerroyalitys.get(0);
			String royalityamount = vtigerroyality.getRoyalityamount();
			int royalitycount = vtigerroyality.getRoyalityCount();

			/*
			 * print_r($record); exit;
			 */

			/* Calculate the royality points earned in this Invoice */

			royalitpointsearned = Util.calcRoyaltyPoints(grandtotal, royalityamount, String.valueOf(royalitycount));

			if (contactroyalty != null) {
				int contact_royalitycount = contactroyalty.getRoyalitycount();
				int contactid = contactroyalty.getContactid();

				int remainingpoints = 0;
				if (redeem_points > 0) {
					remainingpoints = contact_royalitycount - redeem_points;
				}

				int totalroyalitypoints = royalitpointsearned + remainingpoints;

				/* Update the total royality points */
				String updatecontactroyalty = "UPDATE VtigerContactroyality so SET so.royalitycount = " + totalroyalitypoints
							+ " WHERE so.contactid = " + contactid + " AND royalitynumber=" + royalityno;

				System.out.println("update sql: " + updatecontactroyalty);
				DBLocalHelper.executeHUpdateQuery(updatecontactroyalty);

			} else {

				/**************** CREATE A NEW CONTACT/CUSTOMER ***********************/
				int cust_id = crm_repo.generateCrmId("Contacts");

				/* Query to fetch last contact number */
				
				String hsql = "From " + VtigerModentitynum.class.getSimpleName() + " WHERE semodule='Contacts'";
				System.out.println("query: " + hsql);
				List<VtigerModentitynum> modentitynums = DBLocalHelper.executeHQuery(hsql);
				System.out.println("Number of modentitys: " + modentitynums.size());
				
//				List<VtigerModentitynum> modentitynums = DBLocalHelper.readRecord(VtigerModentitynum.class.getSimpleName(), "semodule", "Contacts");
				String cur_id = modentitynums.get(0).getCurId();
				String con_no = "CON" + cur_id;

				/* Query ends */
				String updatecontactroyalty = "UPDATE VtigerModentitynum so SET so.curId = " + (cust_id + 1) + " WHERE so.prefix='CON'";
				System.out.println("update sql: " + updatecontactroyalty);
				DBLocalHelper.executeHUpdateQuery(updatecontactroyalty);

				/* Create the customer */

				VtigerContactdetails contactdetails = new VtigerContactdetails(cust_id, con_no, Integer.valueOf(0), "--None--", customername, "", "",
							"", mobile, "", "", "", "0", null, null, null, null, "", "0", "0", "", "0", "0");
				DBLocalHelper.saveRecord(VtigerContactdetails.class.getSimpleName(), contactdetails);

				VtigerContactscf contactscf = new VtigerContactscf(cust_id);
				DBLocalHelper.saveRecord(VtigerContactscf.class.getSimpleName(), contactscf);

				/* Creating contact royalty */
				VtigerContactroyality maxroyalt = (VtigerContactroyality) DBLocalHelper.getMax(VtigerContactroyality.class, "id");
				// System.out.println("max royal:"+maxroyalt.toString());
				int royalityid = 0;
				if (maxroyalt != null)
					royalityid = maxroyalt.getRoyalityid() + 1;
				VtigerContactroyality insertcontactroyalty = new VtigerContactroyality(royalityid, cust_id, royalityno, royalitpointsearned);
				DBLocalHelper.saveRecord(VtigerContactroyality.class.getSimpleName(), insertcontactroyalty);

			}
		}

		/*
		 * Customer create while creating Invoice - Tables used vtiger_contactdetails
		 * ,vtiger_contactscf,vtiger_crmentity,vtiger_crmentity_seq
		 * ,vtiger_modentity_num,vtiger_contactaddress
		 */

		// Allow invoice to crm_entity only credit amount is not empty
		int cusid = 0;
		if (credit_amount != null && credit_amount.equalsIgnoreCase("0.00") && !credit_amount.isEmpty()) {

			if (mobile != null && !mobile.isEmpty()) {
				// Check contact already exist based on customer mobile number
				System.out.println("Checking user already exist");
				List<VtigerContactdetails> contact_details = DBLocalHelper.readRecord(VtigerContactdetails.class.getSimpleName(), "mobile", mobile);
				if (contact_details.size() == 0) { // No contact exist. Add new
													// contact
					System.out.println("Add new contact");
				} else {
					// Contact already exist
					cusid = contact_details.get(0).getContactid();
					System.out.println("Contact already exist - " + cusid);

				}
			} else {
				// mobile number empty case
				System.out.println("User mobile empty case");
				cusid = 0;
			}

			// Create invoicebill ads
			System.out.println("vitger_invoicebill query ");
			VtigerInvoicebillads invoicebill = new VtigerInvoicebillads(new_crmid, "bangalore", "123456", "india", "karnataka", "hsr layout",
						"532001");
			DBLocalHelper.saveRecord(VtigerInvoicebillads.class.getSimpleName(), invoicebill);
			System.out.println("vitger_invoicebill completed");
			// Create invoiceship ads
			System.out.println("vitger_invoiceads query ");
			VtigerInvoiceshipads invoiceshipads = new VtigerInvoiceshipads(new_crmid, "bangalore", "123456", "india", "karnataka", "hsr layout",
						"532001");
			DBLocalHelper.saveRecord(VtigerInvoiceshipads.class.getSimpleName(), invoiceshipads);
			System.out.println("vitger_invoiceads completed");

			// create cash receipt add vtiger_cashreceipt

			if (Float.parseFloat(credit_amount) > 0) {
				int cash_id = crm_repo.generateCrmId("Cashreceipt");
				System.out.println("new cash receipt crmid: " + cash_id);
				String datetime = DateUtils.getDateTime(System.currentTimeMillis());
				System.out.println("vitger_cashrecipt query ");
				VtigerCashreceiptId cashReceiptId = new VtigerCashreceiptId(cash_id, new_crmid, cusid, grandtotal, credit_amount, paidamount,
							pendingamount, datetime, datetime);
				VtigerCashreceipt cashReceipt = new VtigerCashreceipt(cashReceiptId);
				DBLocalHelper.saveRecord(VtigerCashreceipt.class.getSimpleName(), cashReceipt);
				System.out.println("vitger_cashreceipt completed");
			}

		}

		// Add each invoice vtiger_inventoryproductrel
		System.out.println("-------------vitger_inventoryproductrel query's --------------");
		int max_lineitem = DBLocalHelper.getMaxValue(VtigerInventoryproductrel.class, "lineitemId");
		int next_lineitem = max_lineitem + 1;
		for (int i = 0; i < invoceproduct_list.size(); i++) {
			int productid = invoceproduct_list.get(i).getProductid();
			String bcode = invoceproduct_list.get(i).getBcode();
			String productName = invoceproduct_list.get(i).getProductname();
			String mrp = invoceproduct_list.get(i).getMrp();
			String qty = invoceproduct_list.get(i).getQty();
			String vat = invoceproduct_list.get(i).getVat();
			String netprice = netprice_list[i];

			float taxAmount = ((Float.parseFloat(mrp) * Float.parseFloat(qty)) * (Float.parseFloat(vat) / 100));
			String query = "INSERT IGNORE into vtiger_inventoryproductrel (id,productid,sequence_no,quantity,listprice,discount_amount,incrementondel,lineitem_id,vat,taxamount,netprice) value("
						+ new_crmid
						+ " ,"
						+ productid
						+ ", 1,"
						+ qty
						+ " ,"
						+ mrp
						+ " ,"
						+ discount_amount
						+ ",0,"
						+ next_lineitem
						+ ","
						+ vat
						+ ","
						+ String.valueOf(taxAmount) + " ," + netprice + ")";
			System.out.println("vitger_inventoryproductrel query " + i + " is : " + query);
			// int result = DBLocalHelper.excecuteQuery(query);
			VtigerInventoryproductrel inventory_procedural = new VtigerInventoryproductrel(new_crmid, productid, "1", qty, mrp, discount_amount, 0,
						next_lineitem, vat, String.valueOf(taxAmount), netprice);
			DBLocalHelper.saveRecord("VtigerInventoryproductrel", inventory_procedural);
			System.out.println("vitger_inventoryproductrel query " + i + " is completed.");

			// add vtiger_inventorytransaction
			next_lineitem++;
			String hsql = "From " + VtigerInventorytransaction.class.getSimpleName() + " WHERE productid=" + productid;
			System.out.println("vitger_inventorytransaction query " + i + " is : " + hsql);
			List<VtigerInventorytransaction> transactions = DBLocalHelper.executeHQuery(hsql);

			int max_transactionid = 0;
			if (transactions.size() != 0) {
				int index = transactions.size() - 1;
				max_transactionid = transactions.get(index).getTransactionId();
			}
			System.out.println("vitger_inventorytransaction query " + i + " is completed ");

			// SELECT warehousestore_transaction_id AS MAX, opening_stock_qty,
			// closing_stock FROM vtiger_warehousestore_inventorytransaction
			// WHERE productid= 18 AND warehousestore_id = 3 ORDER BY
			// warehousestore_transaction_id DESC LIMIT 1

			hsql = "From " + VtigerWarehousestoreInventorytransaction.class.getSimpleName() + " WHERE productid = " + productid
						+ " AND warehousestore_id = " + warehouseid + " ORDER BY warehousestore_transaction_id DESC LIMIT 1";
			System.out.println("VtigerWarehousestoreInventorytransaction query " + i + " is : " + hsql);
			VtigerWarehousestoreInventorytransaction transWS = (VtigerWarehousestoreInventorytransaction) DBLocalHelper.executeHQuery(hsql).get(0);
			System.out.println("VtigerWarehousestoreInventorytransaction query " + i + " is completed ");

			String getTransDetails = "FROM " + VtigerInventorytransaction.class.getSimpleName() + " WHERE transactionId=" + max_transactionid;
			System.out.println("vtiger_inventorytransaction query " + i + " is : " + getTransDetails);
			VtigerInventorytransaction transInvTransaction = (VtigerInventorytransaction) DBLocalHelper.executeHQuery(getTransDetails).get(0);
			System.out.println("vtiger_inventorytransaction query " + i + " is completed ");

			// "SELECT cost FROM vtiger_products WHERE productid='" . $productid
			// . "'";
			List<VtigerProducts> productResultSet = DBLocalHelper.readRecord(VtigerProducts.class.getSimpleName(), "productid", productid);
			float rowCost = Float.parseFloat(productResultSet.get(0).getCost());

			int opening_stock = (int) transInvTransaction.getClosingStock();
			int newclosing_stock = opening_stock - Integer.parseInt(qty);
			int opening_cost = Math.round(rowCost);

			/******* Fetch Qty in stock from warehouse/store ********/
			String hquery_WS = "FROM " + VtigerWarehouseStock.class.getSimpleName() + " WHERE warehouseid = " + warehouseid + " AND productid = "
						+ productid;
			System.out.println("VtigerWarehouseStock query " + i + " is : " + hquery_WS);
			List<VtigerWarehouseStock> resultSetWS = DBLocalHelper.executeHQuery(hquery_WS);
			/***************/

			String hqueryIT = "FROM " + VtigerInventorytransaction.class.getSimpleName() + " WHERE productid =" + productid
						+ " AND final_stock > 0 ORDER BY transaction_id";
			System.out.println("VtigerInventorytransaction query " + i + " is : " + hqueryIT);
			List<VtigerInventorytransaction> selectTransactionOfTheProductResultSet = DBLocalHelper.executeHQuery(hqueryIT);

			String selectTransactionOfTheWHProduct = "FROM " + VtigerWarehousestoreInventorytransaction.class.getSimpleName() + " WHERE productid = "
						+ productid + " AND warehousestore_id = " + warehouseid + "AND final_stock > 0 ORDER BY warehousestore_transaction_id";
			System.out.println("VtigerWarehousestoreInventorytransaction query " + i + " is : " + selectTransactionOfTheWHProduct);
			List<VtigerWarehousestoreInventorytransaction> selectTransactionOfTheWHProductResultSet = DBLocalHelper
						.executeHQuery(selectTransactionOfTheWHProduct);

			if (transWS.getOpeningStockQty() > 0) {
				int opening_stock_WS = transWS.getClosingStock();
				int newclosing_stock_WS = opening_stock_WS - Integer.parseInt(qty);

				// For Inventory Transaction.
				int reqQty = (int) Integer.parseInt(qty);
				for (Iterator iterator = selectTransactionOfTheWHProductResultSet.iterator(); iterator.hasNext();) {
					VtigerWarehousestoreInventorytransaction selectTransactionOfTheProductArray = (VtigerWarehousestoreInventorytransaction) iterator
								.next();

					if (reqQty == 0) {
						System.out.println("quntity is zero");
						break;
					}
					if (reqQty <= (int) selectTransactionOfTheProductArray.getFinalStock()) {
						int rows = DBLocalHelper.getRowCount("VtigerInventorytransaction");
						System.out.println(rows + " rows in VtigerInventorytransaction");
						rows = rows +1;
						System.out.println("Current transaction id: "+rows);
						
						
						String date = DateUtils.getDateTime(System.currentTimeMillis());
						VtigerInventorytransaction transaction = new VtigerInventorytransaction(rows, date, productid, productName, opening_stock,
									opening_cost, new_crmid, invoice_no, reqQty, warehouseid, mrp, newclosing_stock, 0, mrp, bcode);
						System.out.println("VtigerInventorytransaction primarykey: " + transaction.getTransactionId());
						DBLocalHelper.saveRecord(VtigerInventorytransaction.class.getSimpleName(), transaction);

						// update VtigerCrmentitySeq so set so.id="+ crmid+"
						// where so.id= "+(crmid-1)
						String updateFinalStock = "UPDATE VtigerInventorytransaction so SET so.finalStock = so.finalStock - " + reqQty
									+ " WHERE so.transactionId = " + transInvTransaction.getTransactionId();
						System.out.println("vtiger_inventorytransaction: " + updateFinalStock);
						DBLocalHelper.excecuteQuery(updateFinalStock);
						reqQty = 0;
					} else {
						int rows = DBLocalHelper.getRowCount("VtigerInventorytransaction");
						System.out.println(rows + " rows in VtigerInventorytransaction");
						rows = rows +1;
						System.out.println("Current transaction id: "+rows);
						String date = DateUtils.getDateTime(System.currentTimeMillis());
						VtigerInventorytransaction transaction = new VtigerInventorytransaction(rows, date, productid, productName, opening_stock,
									opening_cost, new_crmid, invoice_no, reqQty, warehouseid, mrp, newclosing_stock, 0, mrp, bcode);
						System.out.println("VtigerInventorytransaction primarykey: " + transaction.getTransactionId());
						DBLocalHelper.saveRecord(VtigerInventorytransaction.class.getSimpleName(), transaction);

						// update VtigerCrmentitySeq so set so.id="+ crmid+"
						// where so.id= "+(crmid-1)
						String updateFinalStock = "UPDATE VtigerInventorytransaction so SET so.finalStock = so.finalStock - " + reqQty
									+ " WHERE so.transactionId = " + transInvTransaction.getTransactionId();
						System.out.println("vtiger_inventorytransaction: " + updateFinalStock);
						DBLocalHelper.excecuteQuery(updateFinalStock);
						reqQty = reqQty - selectTransactionOfTheProductArray.getFinalStock();

					}
				}

				// For Warehouse Transaction.
				int wreqQty = Integer.parseInt(qty);
				for (Iterator iterator2 = selectTransactionOfTheWHProductResultSet.iterator(); iterator2.hasNext();) {
					VtigerWarehousestoreInventorytransaction selectTransactionOfTheWHProductArray = (VtigerWarehousestoreInventorytransaction) iterator2
								.next();
					if (wreqQty == 0)
						break;
					if (wreqQty <= selectTransactionOfTheWHProductArray.getFinalStock()) {
						int rows = DBLocalHelper.getRowCount("VtigerWarehousestoreInventorytransaction");
						System.out.println(rows + " rows in VtigerWarehousestoreInventorytransaction");
						VtigerWarehousestoreInventorytransaction transaction = new VtigerWarehousestoreInventorytransaction((rows + 1),
									DateUtils.getDateTime(System.currentTimeMillis()), Integer.parseInt(warehouseid), productid, productName,
									opening_stock_WS, selectTransactionOfTheWHProductArray.getOpeningCost(), new_crmid, invoice_no,
									Integer.parseInt(qty), mrp, newclosing_stock_WS, mrp);
						System.out.println("primary key value: " + transaction.getWarehousestoreTransactionId());
						DBLocalHelper.saveRecord("VtigerWarehousestoreInventorytransaction", transaction);
						System.out.println("VtigerWarehousestoreInventorytransaction saved");

						String updateFinalStock = "UPDATE VtigerWarehousestoreInventorytransaction so SET so.finalStock = so.finalStock - " + wreqQty
									+ " WHERE so.warehousestoreTransactionId = " + transInvTransaction.getTransactionId();

						System.out.println("updateFinalStock: " + updateFinalStock);
						DBLocalHelper.executeHUpdateQuery(updateFinalStock);
						System.out.println("updateFinalStock completed");
						wreqQty = 0;
					}

					else {
						int rows = DBLocalHelper.getRowCount("VtigerWarehousestoreInventorytransaction");
						System.out.println(rows + " rows in VtigerWarehousestoreInventorytransaction");
						VtigerWarehousestoreInventorytransaction transaction = new VtigerWarehousestoreInventorytransaction((rows + 1),
									DateUtils.getDateTime(System.currentTimeMillis()), Integer.parseInt(warehouseid), productid, productName,
									opening_stock_WS, selectTransactionOfTheWHProductArray.getOpeningCost(), new_crmid, invoice_no,
									Integer.parseInt(qty), mrp, newclosing_stock_WS, mrp);
						DBLocalHelper.saveRecord("VtigerWarehousestoreInventorytransaction", transaction);
						System.out.println("VtigerWarehousestoreInventorytransaction saved");

						String updateFinalStock = "UPDATE VtigerWarehousestoreInventorytransaction so SET so.finalStock = 0 WHERE so.warehousestoreTransactionId = "
									+ transInvTransaction.getTransactionId();
						System.out.println("updateFinalStock: " + updateFinalStock);
						DBLocalHelper.executeHUpdateQuery(updateFinalStock);
						System.out.println("updateFinalStock completed");
						wreqQty = wreqQty - selectTransactionOfTheWHProductArray.getFinalStock();

					}
				}
			} // end of if (transWS.getOpeningStockQty() > 0)

			/*****
			 * Query TO decrease sales quantity from main product table and from store table Girish
			 * dated 27 jan 2012 $qty is sales quantity
			 *****/
			String update_vtiger_products = "UPDATE vtiger_products SET qtyinstock = qtyinstock - " + qty + " WHERE productid = " + productid;

			System.out.println("Updating products quantity in stock");
			System.out.println("query: " + update_vtiger_products);
			List<VtigerProducts> products = DBLocalHelper.readRecord("VtigerProducts", "productid", productid);
			VtigerProducts product = products.get(0);
			float qunty = Float.parseFloat(product.getQtyinstock()) - Float.parseFloat(qty);
			product.setQtyinstock(String.valueOf(qunty));
			DBLocalHelper.updateRecord("VtigerProducts", product);
			System.out.println("Updating products quantity in stock completed");

			// for store

			String update_vtiger_warehouse_stock = "UPDATE vtiger_warehouse_stock SET qty = qty - " + qty + " WHERE productid = " + productid
						+ " AND warehouseid = " + warehouseid;
			String HSQL = "From " + VtigerWarehouseStock.class.getSimpleName() + " WHERE productid = " + productid + " AND warehouseid = "
						+ warehouseid;

			System.out.println("Updating warehouse stock quantity");
			System.out.println("query: " + HSQL);

			List<VtigerWarehouseStock> WarehouseStocks = DBLocalHelper.executeHQuery(HSQL);
			VtigerWarehouseStock warehousestock = WarehouseStocks.get(0);
			qunty = warehousestock.getQty() - Integer.parseInt(qty);
			warehousestock.setQty(Math.round(qunty));
			DBLocalHelper.updateRecord("VtigerWarehouseStock", warehousestock);
			System.out.println("Updating warehousestock quantity in stock completed");
			/***********/

		}

		return new_crmid;

	}

	public int getEarnedRoyaltyPoints() {
		
		return royalitpointsearned;
	}
}
