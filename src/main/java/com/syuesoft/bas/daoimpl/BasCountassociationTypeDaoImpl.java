package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasCountassociationTypeDao;
import com.syuesoft.model.BasCountassociationType;
import com.syuesoft.util.Json;

public class BasCountassociationTypeDaoImpl extends BaseDaoImpl<BasCountassociationType> implements BasCountassociationTypeDao {

	
	public boolean Add(BasCountassociationType basCountassociationType) {
		List list = this.getHibernateTemplate().find("from BasCountassociationType where typeName='"+basCountassociationType.getTypeName()+"'");
		if(list.size()>0){
			return true;
		}else{
			this.getHibernateTemplate().save(basCountassociationType);
			return false;
		}
	}

	
	public void Delete(BasCountassociationType basCountassociationType) {
		this.getHibernateTemplate().delete(basCountassociationType);
	}

	
    public boolean Update(BasCountassociationType basCountassociationType) {
		List list = this.getHibernateTemplate().find("from BasCountassociationType where typeName='"+basCountassociationType.getTypeName()+"'  and typeId not in ("+basCountassociationType.getTypeId()+")");
		if(list.size()>0){
			return true;
		}else{
			this.getHibernateTemplate().update(basCountassociationType);
			return false;
		}
	}

	
	public Json findAll(final int page, final int rows,final String sort , final String order,final int enterprise_id)throws Exception {
		List<BasCountassociationType> list=new ArrayList<BasCountassociationType>();
		String queryString = "SELECT * FROM (SELECT type_id AS typeId,type_name AS typeName,memo,enterprise_id AS enterpriseId FROM bas_countassociation_type) AS A WHERE enterpriseId="+enterprise_id;
		if(sort!=null && !sort.equals("") && order!=null && !order.equals("")){
			queryString += " order by "+sort+" "+order+"";
		}
		List<Object[]> resultList=createSQLQuery(queryString);
		int count =getSQLCount(queryString, null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				obj=resultList.get(i);
				BasCountassociationType bt=new BasCountassociationType();
				if(obj[0]!=null&&!obj[0].equals(""))
					bt.setTypeId(Integer.parseInt(obj[0].toString()));
				if(obj[1]!=null&&!obj[1].equals(""))
					bt.setTypeName(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					bt.setMemo(obj[2].toString());
				list.add(bt);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	/*
	 * (non-Javadoc)
	 * 条件查询
	 * @see com.syuesoft.DataBase.Dao.BasCountassociationTypeDao#findByCondition(int, int, com.syuesoft.model.BasCountassociationType)
	 */
	@SuppressWarnings("unchecked")
	
	public List<BasCountassociationType> findByCondition(final int page, final int rows,
			final BasCountassociationType basCountassociationType) {

		List<BasCountassociationType> list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				String queryString = "from BasCountassociationType ";//
				if(basCountassociationType.getTypeName()!=null && !basCountassociationType.getTypeName().trim().equals("")){
					queryString += "where typeName like '%"+StringEscapeUtils.escapeSql(basCountassociationType.getTypeName().trim())+"%'";
				}
				org.hibernate.Query query = session.createQuery(queryString);
				int totlesize =query.list().size();
				HttpSession sesion = ServletActionContext.getRequest().getSession();
				sesion.setAttribute("totlesize", totlesize);
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}

		});
		return list;
	
	}

	
	public List<BasCountassociationType> getTotle() {
		String hql ="from BasCountassociationType";
		return this.getHibernateTemplate().find(hql);
	}
}