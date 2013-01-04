package com.vendhaq.handlers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.vendhaq.repos.LocalDbConfiguration;





public class DBLocalHelper {

	private static SessionFactory localDBSessionFactory = LocalDbConfiguration
			.getLocalDBSessionFactory();

	public static List readRecords(String modelname, String columnname,
			int startindex) {

		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		List entitys = null;
		try {
			transaction = session.beginTransaction();
			entitys = session.createQuery(
					"FROM " + modelname + " where " + columnname + " >= "
							+ String.valueOf(startindex)).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return entitys;

	}

	public static List readRecords(String modelname, String columnname,
			int startindex, int endindex) {
		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		List entitys = null;
		try {
			transaction = session.beginTransaction();
			entitys = session.createQuery(
					"FROM " + modelname + " where " + columnname + " >= "
							+ String.valueOf(startindex) + " and " + columnname
							+ " <=" + String.valueOf(endindex)).list();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return entitys;

	}

	public static List readRecord(String modelname, String columnname, int id) {

		return readRecord(modelname, columnname, String.valueOf(id));
	}

	public static List readRecord(String modelname, String columnname,
			String value) {
		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		List entitys = null;
		try {
			transaction = session.beginTransaction();
			entitys = session.createQuery(
					"FROM " + modelname + " where " + columnname + " = '"
							+ value+"'").list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return entitys;
	}

	public static List readRecords(String modelname) {
		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		List entitys = null;
		try {
			transaction = session.beginTransaction();
			entitys = session.createQuery("FROM " + modelname).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return entitys;
	}

	public static boolean saveRecord(String modelname, Object model) {
		Session session = localDBSessionFactory.openSession();
		Transaction tx = null;
		boolean status = true;
		try {
			tx = session.beginTransaction();
			session.save(model);
			tx.commit();
		} catch (HibernateException e) {
			status = false;
			if (tx != null)
				tx.rollback();
			status = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	public static boolean saveRecords(String modelname, List objects) {
		boolean status = false;
		for (int i = 0; i < objects.size(); i++) {
			status = saveRecord(modelname, objects.get(i));
		}
		return status;

	}

	public static boolean deleteRecord(String modelname, List objects) {
		boolean status = false;
		for (int i = 0; i < objects.size(); i++) {
			status = deleteRecord(modelname, objects.get(i));
		}
		return status;
	}

	public static boolean deleteRecord(String modelname, Object object) {
		Session session = localDBSessionFactory.openSession();
		Transaction tx = null;
		boolean status = true;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
		} catch (HibernateException e) {
			status = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	public static boolean updateRecord(String modelname, Object object) {
		Session session = localDBSessionFactory.openSession();
		Transaction tx = null;
		boolean status = true;
		try {
			tx = session.beginTransaction();
			session.update(object);
			session.flush();
			tx.commit();
			
		} catch (HibernateException e) {
			status = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	public static boolean updateRecord(String modelname, List objects) {
		boolean status = false;
		for (int i = 0; i < objects.size(); i++) {
			status = updateRecord(modelname, objects.get(i));
		}
		return status;

	}

	public static int getRowCount(String modelname) {
		Session openSession = localDBSessionFactory.openSession();
		int count = ((Integer) openSession.createQuery(
				"select count(*) from " + modelname).uniqueResult()).intValue();
		openSession.close();
		return count;
	}

	public static int deleteRecords(String modelname) {
		Session openSession = localDBSessionFactory.openSession();
		int result = openSession.createQuery("delete " + modelname)
				.executeUpdate();
		openSession.close();
		return result;

	}

	public static int excecuteQuery(String query) {
		Session openSession = localDBSessionFactory.openSession();
		int result = openSession.createQuery(query).executeUpdate();
		openSession.close();
		return result;
	}

	public static List executeHQuery(String query) {
		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		List entitys = null;
		try {
			transaction = session.beginTransaction();
			entitys = session.createQuery(query).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return entitys;

	}

	public static int executeHUpdateQuery(String query) {
		Session session = localDBSessionFactory.openSession();
		Transaction transaction = null;
		int status = 0;
		try {
			transaction = session.beginTransaction();
			Query createQuery = session.createQuery(query);
			status = createQuery.executeUpdate();
			
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return status;

	}
	
	public static Integer getMaxValue(Class modulename, String columnname) {
		Session session = localDBSessionFactory.openSession();
		Criteria criteria = session.createCriteria(modulename).setProjection(
				Projections.max(columnname));
		Integer maxAge = (Integer) criteria.uniqueResult();
		session.close();
		return maxAge;
	}

	public static Object getMax(Class modulename, String columnname) {
		Session session = localDBSessionFactory.openSession();
		Object oldest = session.createCriteria(modulename)
				.addOrder(Order.desc(columnname)).setMaxResults(1).uniqueResult();
		session.close();
		return oldest;

	}
	

}
