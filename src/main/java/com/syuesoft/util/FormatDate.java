package com.syuesoft.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormatDate {
	/**
	 * 将时间转换为时间戳（未完）
	 * */
	public Date formateToDate(Object obj){
		Timestamp ts=(Timestamp)obj;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.format(ts);
		return null;
	}
	
	/**
	 * 
	*计算差多少年
	* @Title: dateDiff 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param startDate
	* @param @param endDate
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws
	 */
	public static Integer dateDiffYear(Date startDate, Date endDate) {
		Integer year = 0;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long nd = 1000*24*60*60;//一天的毫秒数
		long diff;
		try {
			SimpleDateFormat format_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime=null,endTime = null;
			
			if(startDate != null && endDate != null){
				startTime = format_.format(startDate);
				endTime = format_.format(endDate);
				diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
				long day = diff/nd;//计算差多少天
				year = Integer.parseInt(String.valueOf(day/365));//计算差多少年
			}else{
			    return 0;
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return year;
	}
	
	/**
	 * 
	*计算差多少天
	* @Title: dateDiffDay 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param startDate
	* @param @param endDate
	* @param @return    设定文件 
	* @return Long    返回类型 
	* @throws
	 */
	public static Long dateDiffDay(Date startDate, Date endDate) {
	    long day = 0;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long nd = 1000*24*60*60;//一天的毫秒数
        long diff;
        try {
            SimpleDateFormat format_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime=null,endTime = null;
            
            if(startDate != null && endDate != null){
                startTime = format_.format(startDate);
                endTime = format_.format(endDate);
                diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
                day = diff/nd;//计算差多少天
            }else{
                return new Long(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }
	
	/**
     * 
    *计算差多少分钟
    * @Title: dateDiffDay 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param startDate
    * @param @param endDate
    * @param @return    设定文件 
    * @return Long    返回类型 
    * @throws
     */
	public static Long dateDiffMinute(Date startDate, Date endDate) {
        long minute = 0;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long nd = 1000*60;//一分钟的毫秒数
        long diff;
        try {
            SimpleDateFormat format_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime=null,endTime = null;
            
            if(startDate != null && endDate != null){
                startTime = format_.format(startDate);
                endTime = format_.format(endDate);
                diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
                minute = diff/nd;//计算差多少分钟
            }else{
                return new Long(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minute;
    }
	
	// 本月的第一天
	public static String getFistDay(){
	    Calendar calendar = new GregorianCalendar();
	    SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    calendar.set( Calendar.DATE,  1);
	    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
	    return simpleFormate.format(calendar.getTime());
	}
	
	// 本月的最后一天
	public static String getEndDay(){
	    Calendar calendar = new GregorianCalendar();
	    SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    calendar.set( Calendar.DATE,1);
	    calendar.roll(Calendar.DATE,-1);
	    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);
        return simpleFormate.format(calendar.getTime());
    }
	
	// 当前日期
	@SuppressWarnings("deprecation")
    public static String GetNowDate(){  
	    Date date = new Date();
	    date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return sdf.format(date);  
	}  
	
	public static String getStartDataString(String str, String format)
    {
	    if (str != null || !"".equals(str))
	    {
    	    Date date = FormatTime.str2Date(str, format);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat f = new SimpleDateFormat(pattern);
            return f.format(date); 
	    }else{
	        return "";
	    }
    }
    
    public static String getEndDataString(String str, String format)
    {
        if (str != null || !"".equals(str))
        {
            Date date = FormatTime.str2Date(str, format);
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat f = new SimpleDateFormat(pattern);
            return f.format(date); 
        }else{
            return "";
        }
    }
    
    public static Date getStartData(String str)
    {
        if (str != null || !"".equals(str))
        {
            Date date = FormatTime.str2Date(str, FormatTime.DEFAULT_FORMAT2);
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date; 
        }else{
            return null;
        }
    }
    
    public static Date getEndData(String str)
    {
        if (str != null || !"".equals(str))
        {
            Date date = FormatTime.str2Date(str, FormatTime.DEFAULT_FORMAT2);
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
            return date; 
        }else{
            return null;
        }
    }
    
	public static void main(String[] arg){
	    System.out.println(FormatDate.getFistDay());
	    System.out.println(FormatDate.getEndDay());
	}
}