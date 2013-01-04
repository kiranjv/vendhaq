/**
 * 
 */
package com.vendhaq.repos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.vendhaq.handlers.DBLocalHelper;
import com.vendhaq.models.VtigerCrmentity;
import com.vendhaq.models.VtigerCrmentitySeq;
import com.vendhaq.utils.DateUtils;

/**
 * @author treewalker
 * 
 */
public class HCrmEntityRepository {

	private SessionFactory serverDBSessionFactory = null;
	private SessionFactory localDBSessionFactory = null;
	private String modelname = "VtigerCrmentity";
	private String columnname = "crmid";

	public HCrmEntityRepository() {

		localDBSessionFactory = LocalDbConfiguration.getLocalDBSessionFactory();
	}

	public List<VtigerCrmentity> readLocalEntitys(int startindex) {
		return DBLocalHelper.readRecords(modelname, columnname, startindex);

	}

	public List readLocalEntitys() {
		return DBLocalHelper.readRecords(modelname);

	}

	public List readLocalEntitys(int startindex, int endindex) {
		return DBLocalHelper.readRecords(modelname, columnname, startindex,
				endindex);

	}

	public boolean storeLocalEntitys(List entitys) {

		return DBLocalHelper.saveRecord(modelname, entitys);
	}

	public boolean storeLocalEntitys(Object entity) {
		return DBLocalHelper.saveRecord("VtigerCrmentity", entity);
	}

	/**
	 * Generate new CrmId in table
	 */

	public int generateCrmId(String module) {
		int crmid = 0;
		String sql = "SELECT id FROM vtiger_crmentity_seq";
		List<VtigerCrmentitySeq> seq_list = DBLocalHelper
				.readRecords(VtigerCrmentitySeq.class.getSimpleName());
		VtigerCrmentitySeq seq_object = seq_list.get(0);
		crmid = seq_object.getId();
		System.out.println("Previous crm id: " + crmid);
		crmid = crmid + 1;
		System.out.println("Current crm id: " + crmid);
		updateCrmSeq(crmid);

		// "INSERT INTO
		// vtiger_crmentity(crmid,smcreatorid,smownerid,modifiedby,setype,createdtime,modifiedtime,viewedtime,`VERSION`,presence,deleted)
		String now_time = DateUtils.getDateTime(System.currentTimeMillis());
		System.out.println("date: " + now_time);
		VtigerCrmentity crm_entiry = new VtigerCrmentity(crmid, 1, 1, 0,
				module, now_time, now_time, now_time, 0, 1, 0);
		storeLocalEntitys(crm_entiry);
		return crmid;
	}

	private void updateCrmSeq(int crmid) {

		Session session = localDBSessionFactory.openSession();
		try {
			Transaction t = session.beginTransaction();

			Query query = session
					.createQuery("update VtigerCrmentitySeq so set so.id="
							+ crmid + " where so.id= " + (crmid - 1));
			int update = query.executeUpdate();
			if (update == 0 || update == 1) {
				System.out.println(update + " row affected");
			} else
				System.out.println(update + " rows affected");

			System.out.println("Successfully updated");
			t.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}

	}

	/**
	 * @return the serverDBSessionFactory
	 */
	public SessionFactory getServerDBSessionFactory() {
		return serverDBSessionFactory;
	}

	/**
	 * @param serverDBSessionFactory
	 *            the serverDBSessionFactory to set
	 */
	public void setServerDBSessionFactory(SessionFactory serverDBSessionFactory) {
		this.serverDBSessionFactory = serverDBSessionFactory;
	}

	/**
	 * @return the localDBSessionFactory
	 */
	public SessionFactory getLocalDBSessionFactory() {
		return localDBSessionFactory;
	}

	/**
	 * @param localDBSessionFactory
	 *            the localDBSessionFactory to set
	 */
	public void setLocalDBSessionFactory(SessionFactory localDBSessionFactory) {
		this.localDBSessionFactory = localDBSessionFactory;
	}

	public boolean deleteLocalEntitys(List entitys) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteLocalEntitys(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
