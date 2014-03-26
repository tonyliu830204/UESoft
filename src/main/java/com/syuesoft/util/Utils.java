package com.syuesoft.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;

public class Utils {
	
	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	public static java.util.Date dateFormat(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date d = null;
		try {
			d = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	public static java.util.Date dateFormat(String dateStr, Boolean boo){
		SimpleDateFormat format = null;
		if(boo){
			format = new SimpleDateFormat("yyyy-MM-dd");
		}else{
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		java.util.Date d = null;
		try {
			d = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	public static String getBeginDataString(java.util.Date date, Boolean boo)
	{
		if (date == null)
		{
			return "";
		}
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		String pattern = "yyyy-MM-dd HH:mm:ss";
		if(boo){
			pattern = "yyyy-MM-dd";
		}else{
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		return f.format(date); 
	}
	
	public static String getEndDataString(java.util.Date date, Boolean boo)
	{
		if (date == null)
		{
			return "";
		}
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		String pattern = "yyyy-MM-dd HH:mm:ss";
		if(boo){
			pattern = "yyyy-MM-dd";
		}else{
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat f = new SimpleDateFormat(pattern);
		return f.format(date); 
	}
	
	public static String dateToString(java.util.Date date)
    {
   	 if(date!=null)
   	 {
   	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   	 String sDate = format.format(date);
   	    return sDate;
   	 }else{
   		return null;
   	 }
    }

	/**
	 * 获取系统当前时间
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCurrentTime()
	{
		Calendar c =Calendar.getInstance();
	    java.util.Date d = c.getTime();
		return d.toLocaleString();
	}
	
	/**
	 * 判断一个字符串是否是数字
	 */
	public static boolean isNumber(String str){
		final String number = "123456789.";
		for (int i = 0; i < str.length(); i++) {
            if (number.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
	}
	
	public static String getColorInHexFromRGB(int r, int g, int b) {
		return vali(getHexNum(r)) + vali(getHexNum(g)) + vali(getHexNum(b));
	}

	private static String vali(String s) {
		if (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}

	private static String getHexNum(int num) {
		int result = num / 16;
		int mod = num % 16;
		StringBuilder s = new StringBuilder();
		hexHelp(result, mod, s);
		return s.toString();
	}

	private static void hexHelp(int result, int mod, StringBuilder s) {
		char[] H = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		if (result > 0) {
			hexHelp(result / 16, result % 16, s);
		}
		s.append(H[mod]);
	}
	 /**
     * 精度精确到小数点后两位
     */
    public static double doubleFormat(double value)
    {
        BigDecimal b = new BigDecimal(value);
        double changeValue = b.setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        return changeValue;
    }
	
}