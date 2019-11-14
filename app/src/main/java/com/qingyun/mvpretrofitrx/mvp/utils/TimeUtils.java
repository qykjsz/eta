package com.qingyun.mvpretrofitrx.mvp.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");
	public static final SimpleDateFormat DEFAULT_MIN_FORMAT = new SimpleDateFormat(
			"mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
			"yyyy/MM/dd");
	public static final SimpleDateFormat DEL_FORMAT_DATE = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
		public static final SimpleDateFormat DEL_FORMAT_DATE_mm = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
			public static final SimpleDateFormat DEL_FORMAT = new SimpleDateFormat(
			"yyyy-MM");

	public static final SimpleDateFormat INT_HOUR_FORMAT = new SimpleDateFormat(
			"HH");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM/dd HH:mm");
	private TimeUtils() {
		throw new AssertionError();
	}

	/**
	 * long time to int
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static int getCurrentHourInInt(long timeInMillis,
										  SimpleDateFormat dateFormat) {
		String date = dateFormat.format(new Date(timeInMillis));
		int time = 0;
		if (!TextUtils.isEmpty(date)) {
			time = Integer.parseInt(date);
		}
		return time;
	}

	/**
	 * long time to string
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
//		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		timeInMillis = timeInMillis*1000;
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to int
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static long getCurrentDateInLong(long timeInMillis,
											SimpleDateFormat dateFormat) {
		String date = dateFormat.format(new Date(timeInMillis));
		long time = 0;
		if (!TextUtils.isEmpty(date)) {
			time = Long.parseLong(date);
		}
		return time;
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEL_FORMAT_DATE);
	}




	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);

	}
}