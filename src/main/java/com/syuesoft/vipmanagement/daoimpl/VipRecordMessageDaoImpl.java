package com.syuesoft.vipmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipGruop;
import com.syuesoft.model.BasVipLevel;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.util.GetDateByString;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;

@Repository("vipRecordMessageDao")
public class VipRecordMessageDaoImpl extends BaseDaoImpl<BasVipInfor> implements VipRecordMessageDao {
	
	GetDateByString gt = new GetDateByString();//工具类 通过字符串获取时间格式

	//通过车辆牌照查询其他相关信息
	@SuppressWarnings("unchecked")
	
	public List<VipRecordMessageVo> getInfomatinoByCar(final VipRecordMessageVo vipRecordMessageVo)
			throws Exception {
		List list = this.getHibernateTemplate().execute(new HibernateCallback<List<VipRecordMessageVo>>() {
			
			public List<VipRecordMessageVo> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "SELECT * FROM getinformation_by_car A where A.CAR_LICENSE='"+vipRecordMessageVo.getCar_License()+"'";
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
    
	public List<BasVipGruop> getBasVipGruop(BasUsers user) throws Exception {
		//return this.getHibernateTemplate().find("from BasVipGruop where enterpriseId='"+user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString()+"'");
		return this.getHibernateTemplate().find("from BasVipGruop ");
	}

	@SuppressWarnings("unchecked")
    
	public List<BasVipLevel> getBasVipLevel(BasUsers user) throws Exception {
		//return this.getHibernateTemplate().find("from BasVipLevel where enterpriseId='"+user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString()+"'");
		return this.getHibernateTemplate().find("from BasVipLevel ");
	}
}