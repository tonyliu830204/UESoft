package com.syuesoft.systemmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.systemmanagement.dao.CarLostAnalysisDao;
import com.syuesoft.vipmanagement.vo.CarLostAnalysisVo;

@Repository("carLostAnalysisDao")
public class CarLostAnalysisDaoImpl extends BaseDaoImpl<Object> implements
		CarLostAnalysisDao {

	@SuppressWarnings("unchecked")
	
	public List<CarLostAnalysisVo> getCollectinfor(
			final CarLostAnalysisVo carLostAnalysisVo) throws Exception {
		List<CarLostAnalysisVo> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session hsession)
					throws HibernateException, SQLException {
				String sql = "select * from car_lost A where 1 = 1 ";
				if(carLostAnalysisVo.getTimes()!=null && carLostAnalysisVo.getTimes().trim().equals("A") || carLostAnalysisVo.getTimes().trim().equals("准流失车辆")){
					sql += " AND A.CAR_LOST ='F'";
				}else if(carLostAnalysisVo.getTimes()!=null && carLostAnalysisVo.getTimes().trim().equals("B") || carLostAnalysisVo.getTimes().trim().equals("其他")){
					sql += " AND A.GROUP_NAME!='流失回访'";
				}else{
					String[] times = carLostAnalysisVo.getTimes().split("~"); 
					sql += " AND DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)>"+times[0]+" AND  DATEDIFF(CURDATE(),A.CAR_LAST_REPAIR_DATE)<"+times[1]+"";
				}
				if(carLostAnalysisVo.getCar_License()!=null && !carLostAnalysisVo.getCar_License().equals("")){
					sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getCar_License().trim())+"%'";
				}
				if(carLostAnalysisVo.getCar_Vin()!=null && !carLostAnalysisVo.getCar_Vin().equals("")){
					sql += " and A.vin like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getCar_Vin().trim())+"%'";
				}
				if(carLostAnalysisVo.getStf_Name()!=null && !carLostAnalysisVo.getStf_Name().equals("")){
					sql += " and A.Stf_Name like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getStf_Name().trim())+"%'";
				}
				if(carLostAnalysisVo.getCtype_Name()!=null && !carLostAnalysisVo.getCtype_Name().equals("")){
					sql += " and A.Ctype_Name like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getCtype_Name().trim())+"%'";
				}
				if(carLostAnalysisVo.getCbrd_Name()!=null && !carLostAnalysisVo.getCbrd_Name().equals("")){
					sql += " and A.Cbrd_Name like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getCbrd_Name().trim())+"%'";
				}
				if(carLostAnalysisVo.getStf_Name()!=null && !carLostAnalysisVo.getStf_Name().equals("")){
					sql += " and A.Stf_Name like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getStf_Name().trim())+"%'";
				}
				if(carLostAnalysisVo.getCst_Name()!=null && !carLostAnalysisVo.getCst_Name().equals("")){
					sql += " and A.Cst_Name like '%"+StringEscapeUtils.escapeSql(carLostAnalysisVo.getCst_Name().trim())+"%'";
				}
				if(carLostAnalysisVo.getSort()!=null && !carLostAnalysisVo.getSort().equals("") && carLostAnalysisVo.getOrder()!=null && !carLostAnalysisVo.getOrder().equals("")){
					sql += " order by A."+carLostAnalysisVo.getSort()+" "+carLostAnalysisVo.getOrder()+"";
				}
				Query query = hsession.createSQLQuery(sql);
				//获取http  Session  
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("size", query.list().size());
				//query.setFirstResult((carLostAnalysisVo.getPage()-1)*carLostAnalysisVo.getRows()).setMaxResults(carLostAnalysisVo.getRows());
				return query.list();
			}
		
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	
	public List<CarLostAnalysisVo> getDetailsInforById(
			final CarLostAnalysisVo carLostAnalysisVo) throws Exception {

		List<CarLostAnalysisVo> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			
			public Object doInHibernate(Session hsession)
					throws HibernateException, SQLException {
				String sql = "select * from car_lost_detial A where 1 = 1 and A.CAR_ID='"+carLostAnalysisVo.getCar_Id()+"' ";
				
//				if(carLostAnalysisVo.getSort()!=null && !carLostAnalysisVo.getSort().equals("") && carLostAnalysisVo.getOrder()!=null && !carLostAnalysisVo.getOrder().equals("")){
//					sql += " order by A."+carLostAnalysisVo.getSort()+" "+carLostAnalysisVo.getOrder()+"";
//				}
				Query query = hsession.createSQLQuery(sql);
				return query.list();
			}
		
		});
		return list;
	}

	/**
	 * 判断父节点下是否有 维修项目
	 */
	
	public List getchild(CarLostAnalysisVo carLostAnalysisVo) throws Exception {
		
		return this.getHibernateTemplate().find("from FrtRcptItem A WHERE A.frtReception.receptionId='"+carLostAnalysisVo.getReception_Id()+"' ");
	}

	/**
	 * 车辆流失情况分析 统计 已回访 和未回访
	 */
	@SuppressWarnings("unchecked")
	
	public List finishvisite(final String sql) throws Exception {
		List list = this.getHibernateTemplate().execute(new HibernateCallback<List>() {

			
			public List doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hsql = "SELECT COUNT(*) FROM " +
				"(SELECT COUNT(*) " +
				"FROM frt_car a, frt_custom b, bas_car_color c, bas_car_brand d, bas_car_type e," +
				//"frt_reception f, bas_stuff g, bas_custom_area h , fbk_collect j, bas_custom_type k," +
				"frt_reception f, bas_stuff g, bas_custom_area h , bas_custom_type k," +
				"fbk_car_group L, fbk_tx_group M " +
				"WHERE a.CUSTOM_ID = b.CUSTOM_ID " +
				"AND a.COLOR_ID = c.COLOR" +
				"	AND a.CTYPE_ID = e.CTYPE_ID" +
				"	AND e.CBRD_ID = d.CBRD_ID" +
				"	AND a.car_id = f.car_id" +
				"	AND f.receptor = g.stf_id" +
				"	AND b.PAREA_ID = h.PAREA_ID" +
				"	AND b.CST_ID = k.CST_ID" +
				"	AND A.CAR_ID = L.carId" +
				"	AND L.G_ID = M.G_ID" +
				//"	AND j.car_id = A.CAR_ID" +
				"	"+sql+ "" +
				"	GROUP BY a.car_id) AS N";
				Query query = session.createSQLQuery(hsql);
				return query.list();
			}
		});
		
		return list;
	}

}
