package com.vendhaq.repos;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class LocalDbConfiguration {

	private static Connection connection = null;
	
	private LocalDbConfiguration() {
		System.out.println("Creating database connection");
		connection = createDBConnection();
		System.out.println("Creating database completed");

	}

	public static Connection getConnection() {
		if (connection == null) {
			new LocalDbConfiguration();
		}
		return connection;
	}

	
	
	private Connection createDBConnection() {
		String jdbcDriver;
		String connectionURLThin;
		String DbName;
		String DbUserId;
		String DbUserpassword;
		String DbUrl;
		String DbPath;

		Properties props = new Properties();

		try {

			System.out.println("Reading properties..");
			props.load(new FileInputStream("dbConnection.properties"));
			jdbcDriver = props.getProperty("jdbcDriver");
			System.out.println("Driver: " + jdbcDriver);

			DbName = props.getProperty("databasename");
			System.out.println("Database Name: " + DbName);

			DbPath = props.getProperty("databasePath");
			System.out.println("DB Path:" + DbPath);

			DbUrl = DbPath + DbName;
			System.out.println("Database URL:" + DbUrl);

			connectionURLThin = props.getProperty("connectionURLThin") + DbUrl;
			System.out.println("URL: " + (connectionURLThin));

			DbUserId = props.getProperty("DbUserId");
			System.out.println("UserID: " + DbUserId);

			DbUserpassword = props.getProperty("DbUserpassword");
			System.out.println("Password: " + DbUserpassword);

			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(connectionURLThin,
					DbUserId, DbUserpassword);
		} catch (Exception e) {
			System.err.println("Exception while creating connection");
			e.printStackTrace();
		} finally {
			return connection;
		}

	}

	private void classForName() {

		String jdbcDriver;
		String connectionURLThin;
		String DbName;
		String DbUserId;
		String DbUserpassword;
		String DbUrl;
		String DbPath;
		Connection con = null;

		Properties props = new Properties();

		try {

			System.out.println("Reading properties..");
			props.load(new FileInputStream("dbConnection.properties"));
			jdbcDriver = props.getProperty("jdbcDriver");
			System.out.println("Driver: " + jdbcDriver);

			DbName = props.getProperty("databasename");
			System.out.println("Database Name: " + DbName);

			DbPath = props.getProperty("databasePath");
			System.out.println("DB Path:" + DbPath);

			DbUrl = DbPath + DbName;
			System.out.println("Database URL:" + DbUrl);

			connectionURLThin = props.getProperty("connectionURLThin") + DbUrl;
			System.out.println("URL: " + (connectionURLThin));

			DbUserId = props.getProperty("DbUserId");
			System.out.println("UserID: " + DbUserId);

			DbUserpassword = props.getProperty("DbUserpassword");
			System.out.println("Password: " + DbUserpassword);

			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(connectionURLThin, DbUserId,
					DbUserpassword);

			// which will produce a legitimate Url for SqlLite JDBC :
			// jdbc:sqlite:hello.db
			int iTimeout = 30;
			String sMakeTable = "CREATE TABLE IF NOT EXISTS dummy (id numeric, response text)";
			String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
			String sMakeSelect = "SELECT response from dummy";

			try {
				Statement stmt = con.createStatement();
				try {
					stmt.setQueryTimeout(iTimeout);
					stmt.executeUpdate(sMakeTable);
					stmt.executeUpdate(sMakeInsert);
					ResultSet rs = stmt.executeQuery(sMakeSelect);
					try {
						while (rs.next()) {
							String sResult = rs.getString("response");
							System.out.println(sResult);
						}
					} finally {
						try {
							rs.close();
						} catch (Exception ignore) {
						}
					}
				} finally {
					try {
						stmt.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					con.close();
				} catch (Exception ignore) {
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args)
			throws java.lang.InterruptedException {

		LocalDbConfiguration dbc = new LocalDbConfiguration();
		dbc.classForName();
	}

}