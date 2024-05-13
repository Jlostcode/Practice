package com.example.practice.melonBot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateContainer {

	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		return formatter.format(c.getTime());
	}
	
	public static String getNowMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM");
		Calendar c = Calendar.getInstance();
		return formatter.format(c.getTime());
	}
	
	// 어제 날짜 (리포트 배치용)
	public static String getYesterdayStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return formatter.format(c.getTime());
	}

	// 이전달 1일
	public static String getBeforeMonthStart(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, addMonth);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}
	
	// 작년도 1월 1일
	public static String getBeforeYearStart(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		yyyy = yyyy - 1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, Calendar.JANUARY, 1);

		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	// 이전달 마지막 날짜
	public static String getBeforeMonthEnd(String yyyymmdd, int addMonth) {
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String setDate = "";

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, mm, dd);

		calendar.add(calendar.MONTH, addMonth);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // 해당 월의 1일로 변경
		setDate = formatter.format(calendar.getTime());
		return setDate;
	}

	public static String getFormatDate(String yyyymmdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
		yyyymmdd = yyyymmdd.replaceAll("-", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6))-1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
 		Calendar c = Calendar.getInstance();
 		c.set(yyyy, mm, dd);
 		return formatter.format(c.getTime());
	}

	public static String getFormatDate2(String yyyymmdd) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		yyyymmdd = yyyymmdd.replaceAll("/", "");
		int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.substring(4, 6))-1;
		int dd = Integer.parseInt(yyyymmdd.substring(6, 8));
		
		Calendar c = Calendar.getInstance();
		c.set(yyyy, mm, dd);
		return formatter.format(c.getTime());
	}
	
}
