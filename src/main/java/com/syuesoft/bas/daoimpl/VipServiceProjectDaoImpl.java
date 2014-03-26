package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.VipMealdDao;
import com.syuesoft.bas.dao.VipServiceProjectDao;
import com.syuesoft.base.vo.VipServiceProjectVo;
import com.syuesoft.model.VipMeal;
import com.syuesoft.model.VipMealD;
import com.syuesoft.util.Json;

@Repository("vipServiceProjectDao")
public class VipServiceProjectDaoImpl extends BaseDaoImpl<VipMeal> implements
		VipServiceProjectDao {

	@Autowired VipMealdDao vipMealdDao;
	
	@SuppressWarnings("unchecked")
	
	public void doAdd(final VipServiceProjectVo vipServiceProjectVo) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "insert into vip_meal(meal_name,NOTE,enterprise_id) values ('"+vipServiceProjectVo.getMeal_Name()+"','"+vipServiceProjectVo.getNote()+"',"+vipServiceProjectVo.getEnterpriseId()+")";
				session.createSQLQuery(sql).executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	
	public void doDelete(final VipServiceProjectVo vipServiceProjectVo)
			throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete from vip_meal  where meal_id = "+vipServiceProjectVo.getMeal_Id()+" ";
				//删除子表
                String sql2 = "delete from vip_meal_d  where meal_Id = "+vipServiceProjectVo.getMeal_Id()+" ";
				
				session.createSQLQuery(sql2).executeUpdate();
				
				session.createSQLQuery(sql).executeUpdate();
				
				return null;
			}
		});
	}

	
	public Json doFind(final VipServiceProjectVo vipServiceProjectVo)
			throws Exception {
		List<VipServiceProjectVo> list = new ArrayList<VipServiceProjectVo>();
		String sql = "select * from vip_meal a where  a.enterprise_id="
				+ vipServiceProjectVo.getEnterpriseId();
		if (vipServiceProjectVo.getMeal_Id() != null
				&& !vipServiceProjectVo.getMeal_Id().toString().trim().equals(
						"")) {
			sql += "	and a.meal_id =" + vipServiceProjectVo.getMeal_Id() + "";
		}
		if (vipServiceProjectVo.getMeal_Name() != null
				&& !vipServiceProjectVo.getMeal_Name().equals("")) {
			sql += "	and a.meal_name like '%"
					+ StringEscapeUtils.escapeSql(vipServiceProjectVo
							.getMeal_Name().trim()) + "%'";
		}
		if (vipServiceProjectVo.getOrder() != null
				&& vipServiceProjectVo.getSort() != null)
			sql += " order by " + vipServiceProjectVo.getSort() + " "
					+ vipServiceProjectVo.getOrder();
		int count = getSQLCount(sql, null);
		Object[] obj = null;
		List<Object[]> resultList = createSQLQuery(sql);
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				obj = (Object[]) resultList.get(i);
				VipServiceProjectVo vo = new VipServiceProjectVo();
				if (obj[0] != null)
					vo.setMeal_Id(obj[0].toString());
				if (obj[1] != null)
					vo.setMeal_Name(obj[1].toString());
				if (obj[2] != null)
					vo.setNote(obj[2].toString());
				list.add(vo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}

	/**
	 * 会员卡服务项目明细删除
	 */
	@SuppressWarnings("unchecked")
	
	public void doDitailDelete(final VipServiceProjectVo vipServiceProjectVo)
			throws Exception {

		this.getHibernateTemplate().execute(new HibernateCallback() {

			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//删除子表
				String sql = "delete from vip_meal_d  where d_Id = "+vipServiceProjectVo.getD_Id()+" ";
				
				session.createSQLQuery(sql).executeUpdate();
				
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	
	public Json doDitailFind(
			final VipServiceProjectVo vipServiceProjectVo) throws Exception {
		List<VipServiceProjectVo> list=new ArrayList<VipServiceProjectVo>();
		String sql = "SELECT b.*, a.meal_name FROM vip_meal a, vip_meal_d b WHERE a.meal_id = b.meal_id ";
		if(vipServiceProjectVo.getMeal_Context()!=null && !vipServiceProjectVo.getMeal_Context().toString().trim().equals("")){
			sql += "and b.Meal_Context like '%"+StringEscapeUtils.escapeSql(vipServiceProjectVo.getMeal_Context().trim())+"%'";
		}
		if(vipServiceProjectVo.getMeal_Id()!=null && !vipServiceProjectVo.getMeal_Id().equals("")){
			sql += " and a.meal_id="+vipServiceProjectVo.getMeal_Id()+"";
		}
		if (vipServiceProjectVo.getOrder() != null
				&& vipServiceProjectVo.getSort() != null)
			sql += " order by " + vipServiceProjectVo.getSort() + " "
					+ vipServiceProjectVo.getOrder();
		List<Object[]> resultList=createSQLQuery(sql);
		int count =getSQLCount(sql, null);
		if(resultList!=null&&resultList.size()>0){
			    Object[] obj = null;
	            for (int i = 0; i < resultList.size(); i++){
	                obj = (Object[]) resultList.get(i);
	                VipServiceProjectVo vo = new VipServiceProjectVo();
	                if (obj[0] != null)
	                    vo.setD_Id(obj[0].toString());
	                if (obj[1] != null)
	                    vo.setMeal_Id(obj[1].toString());
	                if (obj[2] != null)
	                    vo.setMeal_Context(obj[2].toString());
	                if (obj[3] != null)
	                    vo.setMemo(obj[3].toString());
	                if (obj[4] != null)
	                    vo.setMeal_Name(obj[4].toString());
	                list.add(vo);
	            }
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}

	@SuppressWarnings("unchecked")
	
	public void doDitailUpdate(final VipServiceProjectVo vipServiceProjectVo)
			throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "update vip_meal_d set memo='"+vipServiceProjectVo.getMemo()+"', meal_Context = '"+vipServiceProjectVo.getMeal_Context()+"', meal_id ='"+vipServiceProjectVo.getMeal_Id()+"' where d_id = "+vipServiceProjectVo.getD_Id()+" ";
				session.createSQLQuery(sql).executeUpdate();
				return null;
				}
			});
		}

	
	public List<VipMeal> getMealName(int enterprise_id) throws Exception {
		return find("from VipMeal where enterpriseId="+enterprise_id);
	}

    /** (非 Javadoc) 
    * <p>Title: getVipMeal</p> 
    * <p>Description: </p> 
    * @param vipMealId
    * @return
    * @throws Exception 
    * @see com.syuesoft.bas.dao.VipServiceProjectDao#getVipMeal(java.lang.String) 
    */
    
    public VipMeal getVipMeal(String vipMealId) throws Exception
    {
        return this.get("from VipMeal where mealId = '"+vipMealId+"'");
    }
}
