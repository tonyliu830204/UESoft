package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BaseCarTypeDAO;
import com.syuesoft.base.vo.BasCarTypeInfoVo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.BasPartsType;

/**
 * 车辆型号资料dao
 * @author Liujian
 *
 */
@Repository("basCarTypeDAO")
public class BasCarTypeDAOImpl extends BaseDaoImpl<BasCarType> implements BaseCarTypeDAO {

	
	public void delete(Short id) {
		BasCarType ct = this.getHibernateTemplate().get(BasCarType.class, id);
		this.getHibernateTemplate().delete(ct);
	}

	@SuppressWarnings("unchecked")
	
	public List<BasCarTypeInfoVo> findAll(String param) {
        return this
                .getHibernateTemplate().find(
				"select new com.syuesoft.base.vo.BasCarTypeInfoVo(ct.ctypeId, ct.ctypeName, cb.cbrdId, cb.cbrdName, ct.ctypePrice) from BasCarType ct, BasCarBrand cb where ct.basCarBrand.cbrdId = cb.cbrdId " + param);
	}

	@SuppressWarnings("unchecked")
	
	public List<BasCarTypeInfoVo> findAll(final String param, final int page,
			final int rows) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<BasCarTypeInfoVo>>() {
			
			public List<BasCarTypeInfoVo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select new com.syuesoft.base.vo.BasCarTypeInfoVo(ct.ctypeId, ct.ctypeName, cb.cbrdId, cb.cbrdName, ct.ctypePrice) from BasCarType ct, BasCarBrand cb where ct.basCarBrand.cbrdId = cb.cbrdId " + param).setFirstResult(
						(page - 1) * rows).setMaxResults(rows);
				return query.list();
			}
		});
	}
	
	
	public void update(BasCarType ct) {
		this.getHibernateTemplate().merge(ct);
	}
	
	
	public Short getMaxId(Short cbrdId) {
		return (Short) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select MAX(bct.ctypeId) from BasCarType bct where bct.basCarBrand.cbrdId = " + cbrdId).uniqueResult();
	}
	
}