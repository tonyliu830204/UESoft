package com.syuesoft.systemmanagement.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasUsers;
import com.syuesoft.systemmanagement.dao.DBBackUpLoadDao;
import com.syuesoft.systemmanagement.vo.DataBackupVo;

@Repository("bDBackUpLoadDao")
public class DBBackUpLoadDaoImpl extends BaseDaoImpl<Object> implements
		DBBackUpLoadDao {
    
	/**
	 * nowdate(当前时间)
	 * address(备份路径)
	 * filename(备份文件名称)
	 */
	public void saveBackup(String address, String filename, BasUsers user ) throws Exception {
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("insert into data_backup(data_backup_time,data_backup_address,data_back_name,systemId,enterprise_id) values(NOW(),'"+address+"','"+filename+"','"+user.getSystemId()+"','"+user.getBasStuff().getEnterpriseInfo().getEnterpriseId()+"')").executeUpdate();
	}


	/**
	 * 数据库还原 通过编号查询文件路径和文件名称
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findBackUpPath(DataBackupVo dataBackupVo) throws Exception {
		return this.getHibernateTemplate().find("select a.dataBackupAddress,a.dataBackName from DataBackup a where a.id="+dataBackupVo.getId());
	}
}