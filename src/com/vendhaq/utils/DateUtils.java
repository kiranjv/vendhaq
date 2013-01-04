package com.vendhaq.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

	public DateUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss" - UTC format
	 * 
	 * @param dateString
	 * @return
	 */
	public static long dateInMillSecond(String dateString) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		java.util.Date date = null;
		try {

			date = simpleDateFormat.parse(dateString);

			// System.out.println("" + date.getTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();

	}

	/**
	 * "MMM dd',' yyyy hh:mm a"
	 * 
	 * @param timeInMillSecond
	 * @return
	 */
	public static String dateInString(long timeInMillSecond) {
		Date date = new Date(timeInMillSecond);
		SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd',' yyyy hh:mm a");
		String dateString = simpleDate.format(date);
		// Log.v("Safecell :"+"Date", dateString);
		return dateString;
	}

	/**
	 * "yyyy-MM-dd'T'HH:mm:ss'Z'"
	 * 
	 * @param timeInMillSecond
	 * @return
	 */
	public static String getTimeStamp(long timeInMillSecond) {
		// Log.v("Safecell :"+"timeInMillSecond",""+timeInMillSecond);
		Date date = new Date(timeInMillSecond);
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		simpleDate.setTimeZone(TimeZone.getTimeZone("UTC"));

		String dateString = simpleDate.format(date);

		return dateString;
	}

	/**
	 * dd-MM-yyyy
	 * 
	 * @param timeInMillSecond
	 * @return
	 */
	public static String getDate(long timeInMillSecond) {
		// Log.v("Safecell :"+"timeInMillSecond",""+timeInMillSecond);
		Date date = new Date(timeInMillSecond);
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd / MM / yyyy");
		String dateString = simpleDate.format(date);
		// Log.v("Safecell :"+"Date", dateString);
		return dateString;
	}

	/**
	 * 7/5/2012 12:37:59 PM format
	 * 
	 * @param args
	 */
	public static String getDateTime(long timeInMillSecond) {
		Date date = new Date(timeInMillSecond);
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String dateString = simpleDate.format(date);
		return dateString;

	}

		public static String currentDate() {
		int day, month, year;
		int second, minute, hour;
		GregorianCalendar date = new GregorianCalendar();

		day = date.get(Calendar.DAY_OF_MONTH);
		month = date.get(Calendar.MONTH);
		year = date.get(Calendar.YEAR);

		second = date.get(Calendar.SECOND);
		minute = date.get(Calendar.MINUTE);
		hour = date.get(Calendar.HOUR);

		String currentdate = day + "/" + (month + 1) + "/" + year;
		System.out.println("Current date is  " + currentdate);
		System.out.println("Current time is  " + hour + " : " + minute + " : " + second);
		return currentdate;
	}

	public static String currentTime() {
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
		System.out.println("Current Time: " + time);
		return time;
	}

	public static String toSqlDate(long currentTimeMillis) {
		java.sql.Date date = new java.sql.Date(currentTimeMillis);
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = simpleDate.format(date);
		return dateString;
	}

	public static void main(String[] args) {
		long timeInMillSeconds = System.currentTimeMillis();
		System.out.println(dateInString(timeInMillSeconds));

		System.out.println("getTimestamp: " + getTimeStamp(timeInMillSeconds));
		System.out.println("getDate: " + getDate(timeInMillSeconds));
		System.out.println("getDateTime: " + getDateTime(timeInMillSeconds));
	}
}
