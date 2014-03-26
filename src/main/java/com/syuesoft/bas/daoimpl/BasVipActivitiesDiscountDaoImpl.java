package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipActivitiesDiscountDao;
import com.syuesoft.model.BasVipActivitiesDiscount;
@Repository("basVipActivitiesDiscountDao")
public class BasVipActivitiesDiscountDaoImpl extends BaseDaoImpl<BasVipActivitiesDiscount> implements BasVipActivitiesDiscountDao {

	
	/**
	 * 添加活动折扣
	 * @basVipActivitiesDiscount 要删除的会员活动实体
	 * */
	public void add(BasVipActivitiesDiscount basVipActivitiesDiscount)
			throws Exception {
		try{
			this.getHibernateTemplate().save(basVipActivitiesDiscount);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * 删除活动折扣
	 * @basVipActivitiesDiscount 要删除的活动折扣实体
	 * */
	public void delte(BasVipActivitiesDiscount basVipActivitiesDiscount)
			throws Exception {
		try{
			this.getHibernateTemplate().delete(basVipActivitiesDiscount);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	
    /**
     * 分页获取所有活动折扣
     * @page 当前页
     * @rows 每页显示条数
     * @erturn 活动折扣集合
     * */
	public List<BasVipActivitiesDiscount> findAll(final int page, final int rows,final String order,final String sort,final int enterprise_id )
			throws Exception {
		List<BasVipActivitiesDiscount> list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//String hql = "from BasVipActivitiesDiscount bva where bva.enterpriseId="+enterprise_id;
				String hql = "from BasVipActivitiesDiscount bva ";
				if(order != null && sort != null){
					hql += " order by "+sort+" "+order+"";
				}
				Query query = session.createQuery(hql);
				//计算起始行
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}
		});
		return list;
	}

	
	/**
	 * 获取总记录数
	 * */
	public int getTotle(int enterprise_id) throws Exception {
		//String hql = "from BasVipActivitiesDiscount bva where bva.enterpriseId="+enterprise_id;
		String hql = "from BasVipActivitiesDiscount bva ";
		List list = this.getHibernateTemplate().find(hql);
		int number = 0;
		if(list != null){
			number = list.size();
		}
		return number;
	}
	/**
	 * 根据名称查询活动折扣是否存在
	 * @name 活动名称
	 * */
	
	public boolean getByName(BasVipActivitiesDiscount basVipActivitiesDiscount) throws Exception {
		String hql = "from BasVipActivitiesDiscount where vipActDisNane='"+ basVipActivitiesDiscount.getVipActDisNane() +"' and enterpriseId="+basVipActivitiesDiscount.getEnterpriseId();
		if(basVipActivitiesDiscount.getVipActDisId() != null){
			hql += "  and vipActDisId  not in('"+basVipActivitiesDiscount.getVipActDisId()+"')";
		}
		try {
			List list = this.getHibernateTemplate().find(hql);
			if(list != null && list.size() > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
