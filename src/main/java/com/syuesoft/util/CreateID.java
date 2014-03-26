package com.syuesoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.contstants.Contstants.NUMBERFRME;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.contstants.Contstants.RECEIPTFORMAT;
import com.syuesoft.model.BasCompanyInformationSet;

/**
 * 自动生成编号
 * @author SuMing
 * Remark :
 *           直接调用CreateID.createId（String tableName,String abridge）返回要生成的编号
 *           1:tableName：表示表明      2:abridge：表示表明缩写
 */
public class CreateID {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CreateID.class);
	@Autowired static BasCompanyInformationSetService basCompanyInformationSetService;
    private static String result = null;
	/**
	 * 获取数据库连接
	 * Connection
	 */
	private static Connection getConn()
	{
		Connection conn=null;
		try {
			ResourceBundle bundleMessage = PropertyResourceBundle.getBundle("jdbc");
			String useuname = bundleMessage.getString("connection.username");
			String password = bundleMessage.getString("connection.password");
			String url = bundleMessage.getString("connection.url");
			String driver_class = bundleMessage.getString("connection.driver_class");
			Class.forName(driver_class);
			conn=DriverManager.getConnection(url,useuname,password);
		} catch (Exception e) {
			logger.error("创建数据库连接", e);
		}
		return conn;
	}
	
	/**
	 * 获取企业级编号
	 */
	public static String createId(String tableName,String abridge) throws Exception
	{
		createIdThread(tableName,abridge);
		return result;
	}
	
	private static String createIdThread(String tableName,String abridge)throws Exception
	{
		Connection conn=getConn();
		ResultSet rs=null;
		String time;
		if("".equals(abridge)){
			time=CreateID.getTime(false);
			rs=conn.createStatement().executeQuery("select *from create_id where tablename='"+tableName+"' AND abridge=''");
		}else{
			time=CreateID.getTime(true);
			rs=conn.createStatement().executeQuery("select *from create_id where tablename='"+tableName+"' AND abridge='"+abridge+"'");
		}
		String number="";
		if(rs.next())
		{
			String sdate=rs.getString(3);
			int snumber=rs.getInt(4);
			int sumNumber=rs.getInt(5);
			if(!"".equals(abridge)){
				if(!"personnelInformation".equals(tableName)){
					if(Integer.parseInt(time)==Integer.parseInt(sdate))
					{
						number=snumber+1+"";
						conn.createStatement().executeUpdate("update create_id set number=number+1,sunnumber=sunnumber+1 where tablename='"+tableName+"' AND abridge='"+abridge+"'");
					}
					else if(Integer.parseInt(time)>Integer.parseInt(sdate))
					{
						number=1+"";
						conn.createStatement().executeUpdate("update create_id set number=1, sunnumber=sunnumber+1,date='"+getTime(true)+"' where tablename='"+tableName+"' AND abridge='"+abridge+"'");
					}
					else if(Integer.parseInt(time)<Integer.parseInt(sdate))
					{
						number=sumNumber+1+"";
						conn.createStatement().executeUpdate("update create_id set sunnumber=sunnumber+1 where tablename='"+tableName+"' AND abridge='"+abridge+"'");
					}else{
					}
				}else{
					number=snumber+1+"";
					if(Integer.parseInt(time)==Integer.parseInt(sdate))
					{
						conn.createStatement().executeUpdate("update create_id set number=number+1, sunnumber=number where tablename='"+tableName+"' AND abridge='"+abridge+"'");
					}
					else
					{
						conn.createStatement().executeUpdate("update create_id set number=number+1, sunnumber=number,date='"+getTime(true)+"' where tablename='"+tableName+"' AND abridge='"+abridge+"'");
					}
				}
			}else{
				if(!"personnelInformation".equals(tableName)){
					if(Integer.parseInt(time)==Integer.parseInt(sdate))
					{
						number=snumber+1+"";
						conn.createStatement().executeUpdate("update create_id set number=number+1,sunnumber=sunnumber+1 where tablename='"+tableName+"' AND abridge=''");
					}
					else if(Integer.parseInt(time)>Integer.parseInt(sdate))
					{
						number=1+"";
						conn.createStatement().executeUpdate("update create_id set number=1, sunnumber=sunnumber+1,date='"+getTime(true)+"' where tablename='"+tableName+"' AND abridge=''");
					}
					else if(Integer.parseInt(time)<Integer.parseInt(sdate))
					{
						number=sumNumber+1+"";
						conn.createStatement().executeUpdate("update create_id set sunnumber=sunnumber+1 where tablename='"+tableName+"' AND abridge=''");
					}else{
					}
				}else{
					number=snumber+1+"";
					if(Integer.parseInt(time)==Integer.parseInt(sdate))
					{
						conn.createStatement().executeUpdate("update create_id set number=number+1, sunnumber=number where tablename='"+tableName+"' AND abridge=''");
					}
					else
					{
						conn.createStatement().executeUpdate("update create_id set number=number+1, sunnumber=number,date='"+getTime(true)+"' where tablename='"+tableName+"' AND abridge=''");
					}
				}
			}
		}else{
			number=1+"";
			if("".equals(abridge))
			{
				conn.createStatement().executeUpdate("insert into create_id (`tablename`, `abridge`, `date`, `number`, `sunnumber`) values('"+tableName+"','','"+getTime(false)+"',1,1)");
			}else
			{
				conn.createStatement().executeUpdate("insert into create_id (`tablename`, `abridge`, `date`, `number`, `sunnumber`) values('"+tableName+"','"+abridge+"','"+getTime(true)+"',1,1)");
			}
		}
		switch (number.length()) {
		case 1:number="000"+number;
			break;
		case 2:number="00"+number;
			break;
		case 3:number="0"+number;
			break;
		default:
			break;
		}
		if(!"personnelInformation".equals(tableName)){
			if("".equals(abridge)){
				result = time+ number;
			}else{
			    result = abridge+time+number;
			}
		}else{
			if("".equals(abridge)){
				result = number;
			}else{
			    result = abridge+number;
			}
		}
		return result;
	}
	
	/**
	 * 获取系统当前时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static String getTime(Boolean flag)
	{
		Connection conn=getConn();
		Calendar c=Calendar.getInstance();
		String y=c.get(c.YEAR)+"";
		String y2 = y.substring(y.length()-2, y.length());
		String m=c.get(c.MONTH)+1+"";
		String d=c.get(c.DATE)+"";
		if(m.length()!=2){
			m="0"+m;
		}
		if(d.length()!=2){
			d="0"+d;
		}
		if(flag){
			String sql="select * from bas_company_information_set cis where 1 = 1 and cis.CI_TYPE = '" + PARAMETER_SET.NUMBERFRME + "' and cis.CI_NAME = '"+NUMBERFRME.RECEIPTFORMAT + "'";
			try {
				ResultSet rs=conn.createStatement().executeQuery(sql);
				if(rs.next()){
					String ciValue=rs.getString("CI_VALUE");
					if(ciValue.equals(RECEIPTFORMAT.YEARADDMONTH)){
				    	return y+m;
				    }else if(ciValue.equals(RECEIPTFORMAT.YEARADDMONTHDAY)){
				    	return y+m+d;
				    }else{
				    	return y+m;
				    }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return y+m;
		}else{
			return y2 + m;
		}
	}
	
	public static class CreateIdThread extends Thread {
		private Logger logs = org.apache.log4j.Logger.getLogger(this.getClass());
		private String tableName;
		private String abridge;
		public CreateIdThread(String tableName,String abridge) {
			super();
			this.tableName = tableName;
			this.abridge = abridge;
		}
		public void run()  
	    {  
			try {
				createIdThread(tableName,abridge);
			} catch (Exception e) {
				logs.error("创建表ID出错",e);
			}
	    } 
		
	}
	
	
}
