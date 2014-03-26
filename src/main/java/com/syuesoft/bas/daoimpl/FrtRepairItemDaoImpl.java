package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.FrtRepairItemDao;
import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.model.BasWorkhourSort;
import com.syuesoft.model.FrtRepairItem;
import com.syuesoft.util.ComboBoxJson;
/**
 * 标准项目工时Dao
 * */
@Repository("frtRepairItemDao")
public class FrtRepairItemDaoImpl extends BaseDaoImpl<FrtRepairItem> implements FrtRepairItemDao {

	
	public void Add(FrtRepairItem frtRepairItem)throws Exception {
		this.getHibernateTemplate().save(frtRepairItem);
	}

	
	public void Delete(FrtRepairItem frtRepairItem) {
		this.getHibernateTemplate().delete(frtRepairItem);
	}

	
	public void Update(FrtRepairItem frtRepairItem) {
		this.getHibernateTemplate().update(frtRepairItem);
	}

	@SuppressWarnings("unchecked")
	
	public List<FrtRepairItem> findAll(final int page, final int rows,final String sort,final String order) {
		List<FrtRepairItem> list = this.getHibernateTemplate().execute(new HibernateCallback<List<FrtRepairItem>>(){
			
                    public List<FrtRepairItem> doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				String queryString = "from FrtRepairItem";
				if(sort!=null && !sort.equals("") && order!=null && !order.equals("")){
					queryString += " order by "+sort+" "+order+"";
				}
				org.hibernate.Query query = session.createQuery(queryString);
				HttpSession sesion = ServletActionContext.getRequest().getSession();
				sesion.setAttribute("size", query.list().size());
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}
		});
		return list;
	}
	/*
	 * (non-Javadoc)
	 * 条件查询
	 * @see com.syuesoft.DataBase.Dao.FrtRepairItemDao#findByCondition(int, int, com.syuesoft.model.FrtRepairItem)
	 */
	@SuppressWarnings("unchecked")
	
	public List<FrtRepairItem> findByCondition(final FrtRepairItemVo frtRepairItemVo) {

		List<FrtRepairItem> list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				String queryString = "select a from FrtRepairItem a where 1=1 ";//
				if(frtRepairItemVo.getRepitemId() != null ){
					queryString += " and a.repitemId like '%"+StringEscapeUtils.escapeSql(frtRepairItemVo.getRepitemId().toString().trim())+"%'";
				}
				if(frtRepairItemVo.getFitCar()!=null && !frtRepairItemVo.getFitCar().trim().equals("")){
					queryString += " and a.fitCar like '%"+StringEscapeUtils.escapeSql(frtRepairItemVo.getFitCar().trim())+"%' ";
				}
				if(frtRepairItemVo.getRepitemName()!=null && !frtRepairItemVo.getRepitemName().trim().equals("")){
					queryString += " and (a.repitemName like '%"+StringEscapeUtils.escapeSql(frtRepairItemVo.getRepitemName().trim())+"%' or a.repitemCode like '%"+StringEscapeUtils.escapeSql(frtRepairItemVo.getRepitemName().trim())+"%')";
				}
				if(frtRepairItemVo.getRepitemSeries()!=null && !frtRepairItemVo.getRepitemSeries().equals("")){
					queryString += " and a.repitemSeries ="+frtRepairItemVo.getRepitemSeries()+" ";
				}
				org.hibernate.Query query = session.createQuery(queryString);
				int size =query.list().size();
				HttpSession sesion = ServletActionContext.getRequest().getSession();
				sesion.setAttribute("size", size);
				int beginRow = (frtRepairItemVo.getPage()-1)*frtRepairItemVo.getRows();
				query.setFirstResult(beginRow);
				query.setMaxResults(frtRepairItemVo.getRows());
				return query.list();
			}

		});
		return list;
	
	}

	@SuppressWarnings("unchecked")
	
	public List<FrtRepairItem> getTotle() throws Exception {
		return this.getHibernateTemplate().find("from FrtRepairItem");
	}

	//获取工时分类用于combox
	
	public List<ComboBoxJson> getBasWorkhourSort(String q,Integer id) throws Exception {
		String hql ="from BasWorkhourSort bws  where 1=1 and bws.enterpriseId="+id;
		if(q!=null){
			hql += "and bws.whSortName like '%"+StringEscapeUtils.escapeSql(q.trim())+"%'";
		}
		List<ComboBoxJson> list=new ArrayList();
		ComboBoxJson cbj=null;
		List<BasWorkhourSort> lt=this.getHibernateTemplate().find(hql);
		if(lt!=null&&lt.size()>0)
		for (BasWorkhourSort bws : lt) {
			cbj=new ComboBoxJson();
			cbj.setId(bws.getWhSortId().toString());
			cbj.setText(bws.getWhSortName());
			list.add(cbj);
		}
		
		return list;
	}

}