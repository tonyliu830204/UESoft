package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipActivitiesProjectDao;
import com.syuesoft.base.vo.BasVipActivitiesProjectVO;
import com.syuesoft.model.BasVipActivitiesProject;

@Repository("basVipActivitiesProjectDao")
public class BasVipActivitiesProjectDaoImpl extends BaseDaoImpl<BasVipActivitiesProject> implements BasVipActivitiesProjectDao {

	
	/**
	 * 添加会员活动项目
	 * */
	public void add(BasVipActivitiesProject basVipActivitiesProject)
			throws Exception {
		try{
			this.getHibernateTemplate().save(basVipActivitiesProject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * 删除会员活动项目
	 * */
	public void delte(BasVipActivitiesProject basVipActivitiesProject)
			throws Exception {
		try{
			this.getHibernateTemplate().delete(basVipActivitiesProject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	
	/**
	 * 分页查询所有会员活动项目
	 * */
	public List<BasVipActivitiesProjectVO> findAll(final int page, final int rows,final String order,final String sort,final int enterprise_id  )
			throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipActivitiesProjectVO>>(){

			
			public List<BasVipActivitiesProjectVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				//String hql = "select new com.syuesoft.base.vo.BasVipActivitiesProjectVO(bvap.vipActProId,bvap.vipActProName,bvap.vipActProDate,bvap.vipActJoinPcount,bvap.vipActProNote,bvap.vipActReward) from BasVipActivitiesProject bvap where bvap.enterpriseId="+enterprise_id;
				String hql = "select new com.syuesoft.base.vo.BasVipActivitiesProjectVO(bvap.vipActProId,bvap.vipActProName,bvap.vipActProDate,bvap.vipActJoinPcount,bvap.vipActProNote,bvap.vipActReward) from BasVipActivitiesProject bvap";
                if(order != null && sort != null){
					hql += " order by "+sort+" "+order+"";
				}
				Query query = session.createQuery(hql);
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}
		});
	}

	
	/**
	 * 获得总记录数
	 * */
	public int getTotle(int enterprise_id) throws Exception {
		// TODO Auto-generated method stub
		//String hql = "from BasVipActivitiesProject where enterpriseId="+enterprise_id;
		String hql = "from BasVipActivitiesProject ";
		try{
			List list = this.getHibernateTemplate().find(hql);
			if(list != null){
				return list.size();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 根据ID获取活动项目
	 * @id 活动项目编号
	 * */
	public BasVipActivitiesProject getById(int id) throws Exception{
		String hql = "from BasVipActivitiesProject where vipActProId='"+id+"'";
		try {
			List list = this.getHibernateTemplate().find(hql);
			if(list.size() > 0){
				return (BasVipActivitiesProject)list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据会员活动名称获取会员活动信息
	 * @name 会员活动名称
	 * */
	
	public boolean getByName(BasVipActivitiesProjectVO basVipActivitiesProjectVO) throws Exception {
		String hql = "from BasVipActivitiesProject where vipActProName='"+basVipActivitiesProjectVO.getVipActProName()+"' and enterpriseId="+basVipActivitiesProjectVO.getEnterpriseId();
		if(basVipActivitiesProjectVO.getVipActProId() != null){
			hql += "  and vipActProId not in('"+ basVipActivitiesProjectVO.getVipActProId() +"')";
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
