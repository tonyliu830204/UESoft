package com.syuesoft.systemmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.FrtParts;
import com.syuesoft.systemmanagement.dao.InventoryFindDao;

@Repository("inventoryFindDao")
public class InventoryFindDaoImpl extends BaseDaoImpl<Object> implements
		InventoryFindDao {
	
	/**
	 * 查询配件档案 获取配件名称
	 */
	@SuppressWarnings("unchecked")
	
	public List<FrtParts> getPartsName() throws Exception {
		List list = this.getHibernateTemplate().execute(new HibernateCallback<List<FrtParts>>() {

			
			public List<FrtParts> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery("select parts_name from Frt_Parts").list();
			}
			
		});
		return list;
		
	}

}
