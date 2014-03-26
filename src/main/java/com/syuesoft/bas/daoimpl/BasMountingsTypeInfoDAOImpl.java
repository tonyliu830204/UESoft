package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasMountingsTypeInfoDAO;
import com.syuesoft.base.vo.BasMountingsTypeInfo;
import com.syuesoft.model.BasPartsType;

/**
 * 配件型号资料DAO
 * @author SUMING
 */
@Repository("basMountingsTypeInfoDAO")
public class BasMountingsTypeInfoDAOImpl extends BaseDaoImpl<BasPartsType> implements BasMountingsTypeInfoDAO {

	
	public void delete(Short id) {
		BasPartsType pt = this.getHibernateTemplate().get(BasPartsType.class, id);
		this.getHibernateTemplate().delete(pt);
	}

	@SuppressWarnings("unchecked")
	
	public List<BasMountingsTypeInfo> findAll(String param) {
		return this
				.getHibernateTemplate()
				.find("select new com.syuesoft.base.vo.BasMountingsTypeInfo(pt.ptypeId, pb.pbrdId, pb.pbrdName, pt.ptypeName, pt.repairRate, pt.sellRate, pt.pointRate, pt.specialRate, pt.claimRate) " +
						"from BasPartsBrand pb, BasPartsType pt " +
						"where pb.pbrdId = pt.basPartsBrand.pbrdId "+ param);
	}

    @SuppressWarnings("unchecked")
	
	public List<BasMountingsTypeInfo> findAll(final String param, final int page,
			final int rows) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<BasMountingsTypeInfo>>() {
			
			public List<BasMountingsTypeInfo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery(
								"select new com.syuesoft.base.vo.BasMountingsTypeInfo(pt.ptypeId, pb.pbrdId, pb.pbrdName, pt.ptypeName, pt.repairRate, pt.sellRate, pt.pointRate, pt.specialRate, pt.claimRate) from BasPartsBrand pb, BasPartsType pt where pb.pbrdId = pt.basPartsBrand.pbrdId "
										+ param).setFirstResult(
								(page - 1) * rows).setMaxResults(rows);
				return query.list();
			}
		});
	}

	
	public void update(BasPartsType pt) {
		this.getHibernateTemplate().merge(pt);
	}
	
	
	public Short getMaxId(Short pbrdId) {
		return (Short) this.getSessionFactory().getCurrentSession().createQuery("select MAX(bpt.ptypeId) from BasPartsType bpt where bpt.basPartsBrand.pbrdId = " + pbrdId).uniqueResult();
	}
	
	public BasPartsType findPartsByType(String type) throws Exception {
		String hql = "from  BasPartsType t where t.ptypeName='"+type+"'";
		List<BasPartsType> basTypes=this.find(hql);
		if(basTypes!=null && basTypes.size()>0){
			return basTypes.get(0);
		}
		return null;
	}

}