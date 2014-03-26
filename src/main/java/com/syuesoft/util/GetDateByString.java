package com.syuesoft.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetDateByString {
	//将字符串形式的时间 转换成 日期格式
	public Date getDate(String str){
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sdate = null;
		try {
			java.util.Date udate = simp.parse(str);
			sdate = new Date(udate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdate;
	}
	//获取当前系统时间 不带时分秒
	public Date getNowDate(){
		Date date = new Date(new java.util.Date().getTime());
		return date;
	}
	//获取当前系统时间 带时分秒
	public String getNowTime(){
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String  date = simp.format(new java.util.Date().getTime());
		return date;
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
}