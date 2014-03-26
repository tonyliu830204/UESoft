package com.syuesoft.util;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormatTime {
	   public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";//Timestamp format must be 
	   public static final String DEFAULT_FORMAT2 = "yyyy-MM-dd";//Timestamp format must be 
	   public  Date addDate(Date d, long day) throws Exception {  
		      
           long time = d.getTime();  
           day = day * 30 * 24 * 60 * 60 * 1000;
           time += day;
           return new java.sql.Date(time);  
  
       }
	   public long plusDate(Date oldDate, java.util.Date nowDate) throws Exception {  
		      
           long time = oldDate.getTime(); 
           long time2 = nowDate.getTime();
           long day = (long) ((time2 - time)/86400000); 
           return day;  
     
       }  
	   
	   /** 日期转换为字符串
	    * @param date 日期
	    * @param format 日期格式
	    * @return 字符串
	    */
	   public static String date2Str(Date date, String format) {
	       if (null == date) {
	            return null;
	       }
	       SimpleDateFormat sdf = new SimpleDateFormat(format);
	       return sdf.format(date);
	   }
	   
	   /**
	    * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	    * @param str 字符串
	    * @param format 日期格式
	    * @return 日期
	    * @throws java.text.ParseException
	    */
	   public static Date str2Date(String str, String format){
	       if (null == str || "".equals(str)) {
	            return null;
	       }
	       // 如果没有指定字符串转换的格式，则用默认格式进行转换
	       if (null == format || "".equals(format)) {
	            format = DEFAULT_FORMAT;
	       }
	       SimpleDateFormat sdf = new SimpleDateFormat(format);
	       Date date = null;
	       try {
	            date = sdf.parse(str);
	            return date;
	       } catch (ParseException e) {
	            e.printStackTrace();
	       }
	       return null;
	   }
	   
	   /**
	    * 时间戳转换为字符串
	    * @param time
	    * @return
	    */
	   public static String timestamp2Str(Timestamp time) {
	       Date date = null;
	       if(null != time){
	            date = new Date(time.getTime());
	       }
	       return date2Str(date, DEFAULT_FORMAT);
	   }
       
	   public static String timestamp2Str1(Timestamp time, String format ) {
	       Date date = null;
	       if(null != time){
	            date = new Date(time.getTime());
	       }
	       return date2Str(date, format);
	   }
	   
	   /** 字符串转换时间戳
	    * @param str
	    * @return
	    */
	   public static Timestamp str2Timestamp(String str) {
	      Date date = str2Date(str, DEFAULT_FORMAT);
	      return new Timestamp(date.getTime());
	   }
	   
	   public static String dateToStringTwo(Date date)
	    {
	    	 if(date!=null)
	    	 {
	    	 SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
	    	 String sDate = format.format(date);
	    	    return sDate;
	    	 }else{
	    		return null;
	    	 }
	    }
	   
	   public static String yesTady(String format){
		   Date date=new Date();//取时间
		   Calendar calendar = new GregorianCalendar();
		   calendar.setTime(date);
		   calendar.add(calendar.DATE,0);//把日期往后增加一天.整数往后推,负数往前移动
		   date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		   SimpleDateFormat formatter = new SimpleDateFormat(format);
		   String dateString = formatter.format(date);
           return dateString;
	   }
	   
	   public static String yesTempTady(String format, Object num){	
		   int nums = 6;
		   if(num != null &&!num.equals("")){
			   nums = Integer.parseInt(num.toString());
		   }
		   Date date=new Date();//取时间
		   Calendar calendar = new GregorianCalendar();
		   calendar.setTime(date);
		   calendar.add(calendar.DATE,-nums);//把日期往后增加一天.整数往后推,负数往前移动
		   date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		   SimpleDateFormat formatter = new SimpleDateFormat(format);
		   String dateString = formatter.format(date);
           return dateString;
	   }
	   
	   public static String monYear(String format){
	       Date date=new Date();//取时间
           Calendar calendar = new GregorianCalendar();
           calendar.setTime(date);
           calendar.add(calendar.YEAR,1);//把日期往后增加一天.整数往后推,负数往前移动
           date=calendar.getTime(); //这个时间就是日期往后推一天的结果
           SimpleDateFormat formatter = new SimpleDateFormat(format);
           String dateString = formatter.format(date);
           return dateString;
       }
	   
	   public static void main(String[] arg){
		    System.out.println(FormatTime.yesTady(FormatTime.DEFAULT_FORMAT));
		    System.out.println(FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2));
		    System.out.println(FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, 5));
		    System.out.println(FormatTime.monYear(FormatTime.DEFAULT_FORMAT));
		}
}