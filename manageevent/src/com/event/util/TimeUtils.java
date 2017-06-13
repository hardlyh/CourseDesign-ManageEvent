package com.event.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	private static String format1 = "yyyy-MM-dd";

	public static String DateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
		return dateFormat.format(date);
	}
	
	
	public static Date StringToDate(String str) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format1);
		return dateFormat.parse(str);
	}

}
