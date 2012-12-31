package com.vendhaq.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Util {

	/**
	 * @param args
	 */

	public static void printMultiArray(String[][] resultset_array) {
		System.out.println("----------------- Displaying multi dimention array --------------------");
		for (int i = 0; i < resultset_array.length; i++) {
			String[] rowdata = resultset_array[i];
			System.out.println("\n");
			printRowArray(rowdata);
		}

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
	}

	public static void printRowArray(String[] rowdata) {
		for (int j = 0; j < rowdata.length; j++) {
			System.out.print(rowdata[j] + " , ");
		}
	}

	/**
	 * Calculate the number of columns of each row in {@link ResultSet}. This method use the
	 * {@link ResultSetMetaData} class to find total columns.
	 * 
	 * @param result
	 *            - {@link ResultSet} object maintains result of sql query to database.
	 * @return - Number of column's in the table
	 */
	public static int numOfCols(ResultSet result) {
		int numofcols = 0;
		try {
			ResultSetMetaData rsmd = result.getMetaData();
			numofcols = rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numofcols;
	}

	/**
	 * Calculate the number of rows in {@link ResultSet}. This method moves courser to the end and
	 * return the rowid.
	 * 
	 * @param rs
	 *            - {@link ResultSet} object maintains result of sql query to database.
	 * @return - Number of row's in the table
	 */
	public static int numOfRows(ResultSet rs) {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static float calculate_discount(String total, String percentage) {
		return calculate_discount(Float.parseFloat(total), Float.parseFloat(percentage));
	}

	public static float calculate_discount(float total, float percentage) {
		float discountamount = (percentage * total) / 100;
		System.out.println("Total: " + total + " percentage(%): " + percentage + " percentage amount: " + discountamount);
		return (float) discountamount;
	}

	public static float Round(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float) tmp / p;
	}

	public static String Round(String Rval, int Rpl) {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(Rpl);
		df.setMaximumFractionDigits(Rpl);
		return df.format(Rval);
	}

	public static String calcItemDiscount(String unitprice, String qty, String discount) {

		float unit_price = Float.valueOf(unitprice);
		float quatity = Float.valueOf(qty);
		float disc = Float.valueOf(discount);
		if (Float.parseFloat(discount) == 0.0) {
			return "0";
		}
		float discountamount = calculate_discount((unit_price * quatity), disc);
		return String.valueOf(discountamount);
	}

	public static String toDecimalTwo(String value) {
		return toDecimalTwo(Float.parseFloat(value));
	}

	public static String toDecimalTwo(float value) {
		return String.format("%.02f", value);
	}

	public static int calcRoyaltyPoints(String grandtotal, String royalityamount, String royalitycount) {
		float grand_total = Float.parseFloat(grandtotal);
		float royalty_amount = Float.parseFloat(royalityamount);
		float royalty_count = Float.parseFloat(royalitycount);

		return (int) Math.floor(grand_total * (royalty_amount / royalty_count));

	}

	public static void main(String[] args) {
		int f = 10;
		System.out.println(toDecimalTwo(f));
		System.out.println(toThreeDecimal(1, 3));

	}

	public static String toThreeDecimal(int num, int digits) {
		assert digits > 0 : "Invalid number of digits";

		// create variable length array of zeros
		char[] zeros = new char[digits];
		Arrays.fill(zeros, '0');
		// format number as String
		DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

		return df.format(num);
	}

	public static String toThreeDecimal(String quantity, int digits) {
		int qty = Integer.parseInt(quantity);
		return toThreeDecimal(qty, digits);
		
	}

	

}
