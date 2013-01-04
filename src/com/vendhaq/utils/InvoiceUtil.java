package com.vendhaq.utils;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.vendhaq.handlers.DBLocalHelper;
import com.vendhaq.models.VtigerInvoice;
import com.vendhaq.repos.LocalDbConfiguration;


public class InvoiceUtil {

	public int getMaxInvoiceNumber() {
		int max_val = 0;
		//int max_val = (int) DBLocalHelper.getMaxValue(VtigerInvoice.class, "invoiceid");
		VtigerInvoice invoice = (VtigerInvoice)DBLocalHelper.getMax(VtigerInvoice.class, "invoiceid");
		if(invoice != null)
		max_val = invoice.getInvoiceid();
		
		System.out.println("Maximum invoice value: " + max_val);

		return max_val;
	}

	public VtigerInvoice getLastInvoice() {
		return (VtigerInvoice) DBLocalHelper.getMax(VtigerInvoice.class, "invoiceid");
	}

	public String generateNewInvoiceNumber() {

		int max_num = getMaxInvoiceNumber();
		max_num = max_num + 1;
		int curid = max_num + 1;

//		String query = "update vtiger_modentity_num set cur_id= " + curid
//					+ " where semodule='Invoice' and num_id=10";
//		String hquery = "update "
//		+ VtigerModentitynum.class.getSimpleName() + "set cur_id= " + curid
//		+ " WHERE semodule='Invoice' and num_id=10";
		
//		List<VtigerModentitynum> records =DBLocalHelper.readRecord(VtigerModentitynum.class.getSimpleName(), "num_id", "10");
//		VtigerModentitynum record = records.get(0);
//		record.setCurId(String.valueOf(curid));
//		DBLocalHelper.saveRecord(VtigerModentitynum.class.getSimpleName(), record);
		Session session = (Session) LocalDbConfiguration.getLocalDBSessionFactory().openSession();
		String hql = "update VtigerModentitynum set cur_id = :newName where semodule = :name";
        Query query = session.createQuery(hql);
        query.setString("name","Invoice");
        query.setString("newName",String.valueOf(curid));
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
		
		return "INV" + max_num;
	}

}
