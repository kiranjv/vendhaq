package com.vendhaq;

import java.util.List;

import com.vendhaq.handlers.DBLocalHelper;
import com.vendhaq.models.VtigerContactdetails;
import com.vendhaq.repos.HCrmEntityRepository;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		List<VtigerContactdetails> records_list = DBLocalHelper.readRecords(VtigerContactdetails.class.getSimpleName());
//		for (VtigerContactdetails vtigerContact : records_list) {
//			System.out.println(vtigerContact.getFirstname()+" "+vtigerContact.getLastname());
//		}
//		
//		VtigerContactdetails contact = new VtigerContactdetails();
//		contact.setFirstname("kiran");
//		contact.setLastname("jv3");
//		contact.setMobile("123456");
//		contact.setEmail("D@fsdfsf");
//		contact.setContactNo("2001");
//		contact.setContactid(2356);
//		DBLocalHelper.saveRecord(VtigerContactdetails.class.getSimpleName(), contact);
		
		
		HCrmEntityRepository crmrepo = new HCrmEntityRepository();
		int crmid = crmrepo.generateCrmId("CON");
		System.out.println("crmid: "+crmid);
		System.out.println("-------- Local From all -----------------");
		crmrepo.readLocalEntitys();
		System.out.println("-------- Local From 25 - 50 -----------------");
		crmrepo.readLocalEntitys(25, 50);
		System.out.println("-------- Local From 25  -----------------");
		crmrepo.readLocalEntitys(25);

	}

}
