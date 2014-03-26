package com.syuesoft.sell.util.daoimpl;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.util.dao.SellUtilDao;

@Repository("sellUtilDao")
public class SellUtilDaoImpl extends BaseDaoImpl implements SellUtilDao {


	/**
	 * 获取员工姓名
	 */
	
	public List findStfName(String q) throws Exception{
		String hql = "from BasStuff bs";
		if(q!=null&&q.length()>0)
			hql+="where bs.stfId="+q;
		List list = find(hql);
		return list;
		
	}
	
	/**
	 * 获取客户姓名
	 */
	
	public List findCustom( String q) throws Exception{
		String hql = "from XsCustomInfo where 1=1";
		if(q!=null&&q.length()>0)
			hql+="and xsCustomName like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
		List list = find(hql);
		return list;
		
	}

	/**
	 * 获取车辆型号
	 */
	
	public List findCarModel() throws Exception {
		List list = null;
		String hql = "";
		String car_Brand_Id = ServletActionContext.getRequest().getParameter("car_Brand_Id");
		if(car_Brand_Id!=null && !car_Brand_Id.equals("")){
			int carBrandid = Integer.parseInt(car_Brand_Id);
			hql = "from XsCarModel a where a.carBrand="+carBrandid;
			list = find(hql);
		}
		return list;
	}
	

	/**
	 * 获取银行信息
	 */
	
	public List findBank() throws Exception {
		String hql = "from XsProvidebank";
		List list = find(hql);
		return list;
	}

	/**
	 * 获取分销商
	 */
	
	public List findBussness() throws Exception {
		String hql = "from XsDistributorInfo";
		List list = find(hql);
		return list;
	}

	/**
	 * 获取车辆编号
	 */
	
	public List findCarId() throws Exception {
		String hql = "from XsCarInfo";
		List list = find(hql);
		return list;
	}

	/**
	 * 获取经办人（所有可以登录系统的登录用户）
	 */
	
	public List findUsers() throws Exception {
		String sql = "SELECT m.stf_id,m.STF_NAME FROM bas_stuff m,bas_users n WHERE m.stf_id = n.stf_id and SYSTEMID ='"+Contstants.SYSTEMTYPE.XIAOSHOU+"'";
		List list = createSQLQuery(sql);
		return list;
	}
	/**
	 * 经办人
	 * */
	
	public List findUsers(String q,String enterpriseId) throws Exception {
		String sql = "SELECT m.stf_id,m.STF_NAME FROM bas_stuff m,bas_users n WHERE m.stf_id = n.stf_id and m.enterprise_id="+enterpriseId;
		if(q!=null&&q.length()>0)
			sql+="and m.stf_name like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
		List list = createSQLQuery(sql);
		return list;
	}
}
