package com.syuesoft.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBeanUtils{
	private static MyBeanUtils mbu=new MyBeanUtils();
	private MyBeanUtils(){
	}
	public static MyBeanUtils getInstance(){
		return mbu;
	}
	
	public  void copyBeans(Object source,Object target) throws IllegalArgumentException, IllegalAccessException{
		Class cls1=source.getClass();
		Class cls2=target.getClass();
		Field [] fields1=cls1.getDeclaredFields();
		Field [] fields2=cls2.getDeclaredFields();
		for (Field field2 : fields2) {
			for (Field field1 : fields1) {
				field1.setAccessible(true);
				field2.setAccessible(true);
				if(field1.getName().equals(field2.getName())){
					opinionType(field1,source,field2,target);
				}
			}
		}
	}
	private void opinionType(Field field1,Object obj1,Field field2,Object obj2) throws IllegalArgumentException, IllegalAccessException{
		field1.setAccessible(true);
		field2.setAccessible(true);
		String type2=field2.getType().getSimpleName();
			if(field1.get(obj1) instanceof String){
				if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString()));
				}else if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString()));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Long.parseLong(field1.get(obj1).toString()));
				}else if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else if(type2.equals("Date")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,getDate(field1.get(obj1).toString()));
				}else if(type2.equals("Timestamp")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,new Timestamp(getDate(field1.get(obj1).toString()).getTime()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Integer){
				if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString()));
				}else if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString()));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Long.parseLong(field1.get(obj1).toString()));
				}else if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Short){
				if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString()));
				}else if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString()));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Long.parseLong(field1.get(obj1).toString()));
				}else if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Long){
				if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,(Long)field1.get(obj1));
				}if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString()));
				}else if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString()));
				}else if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else if(type2.equals("Date")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,new Date((Long)field1.get(obj1)));
				}else if(type2.equals("Timestamp")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,new Timestamp(getDate(field1.get(obj1).toString()).getTime()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Float){
				if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Long.parseLong(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Double){
				if(type2.equals("Double")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Double.parseDouble(field1.get(obj1).toString()));
				}else if(type2.equals("Short")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Short.parseShort(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("Integer")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Integer.parseInt(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Long.parseLong(field1.get(obj1).toString().substring(0, field1.get(obj1).toString().indexOf("."))));
				}else if(type2.equals("Float")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,Float.parseFloat(field1.get(obj1).toString()));
				}else if(type2.equals("String")){
					if(obj1!=null&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,field1.get(obj1).toString());
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else if(field1.get(obj1) instanceof Date){
				if(type2.equals("Date")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,(Date)field1.get(obj1));
				}else if(type2.equals("String")){
					if(obj1!=null)
					field2.set(obj2,getString((Date)field1.get(obj1)));
				}else if(type2.equals("Long")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,((Date)field1.get(obj1)).getTime());
				}else if(type2.equals("Timestamp")){
					if(obj1!=null&&obj1.toString().trim().length()>0&&field1.get(obj1)!=null&&field1.get(obj1).toString().length()>0)
						field2.set(obj2,new Timestamp(getDate(field1.get(obj1).toString()).getTime()));
				}else{
					field2.set(obj2,field1.get(obj1));
				}
			}else{
				field2.set(obj2,field1.get(obj1));
			}
	}
	//将字符串形式的时间 转换成 时间日期格式
	public Date getDate(String str){
		String format="yyyy-MM-dd HH:mm:ss";
		if(str.length()==10){
			format="yyyy-MM-dd";
		}
		SimpleDateFormat simp = new SimpleDateFormat(format);
		Date udate=null;
		try {
			 udate= simp.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return udate;
	}
	//将字符串形式的时间 转换成 时间日期格式
	public Date getDate(String str,String format){
		SimpleDateFormat simp = new SimpleDateFormat(format);
		Date udate=null;
		try {
			 udate= simp.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return udate;
	}
	//将时间日期格式 转换成 字符串形式的时间 
	public String getString(Date date){
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simp.format(date);
	}
	//将时间日期格式 转换成 字符串形式的时间 
	public String getString(Date date,String format){
		SimpleDateFormat simp = new SimpleDateFormat(format);
		return simp.format(date);
	}
	//格式化时间日期字符串 2013-02-02 02:02:02.0 ->2013-02-02 02:02:02
	public String formatString(String source){
		return getString(getDate(source));
	}
	//格式化时间日期字符串2013-02-02 ->2013-02-02 00:00:00
	public String formatStringAsTime(String source){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return getString(sdf.parse(source));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	//将指定时间增加一天
	public String DateAddOneDay(String source){
		Date date=getDate(source,"yyyy-MM-dd");
		date.setDate(date.getDate()+1);
		return getString(date);
	}
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
//		Aa aa=new Aa();
//		aa.setId(10l);
//		aa.setName("zs");
//		aa.setDate("2013-5-29 12:12:12");
//		Bb bb=new Bb();
//		MyBeanUtils.getInstance().copyBeans(aa, bb);
//		System.out.println(bb.getId()+":"+bb.getName()+":"+bb.getDate());
		
	}
}
class Bb{
	private Integer id;
	private String name;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
class Aa{
	private Long id;
	private String name;
	private String date;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}