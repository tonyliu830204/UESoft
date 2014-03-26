package com.syuesoft.systemmanagement.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasUsers;
import com.syuesoft.systemmanagement.vo.DataBackupVo;

public interface DBBackUpLoadDao extends BaseDaoI<Object>{
	//数据库备份
    public void saveBackup(String address, String filename, BasUsers user ) throws Exception;
    
    //数据库还原 通过编号查询文件路径和文件名称
    public List<Object[]> findBackUpPath(DataBackupVo dataBackupVo) throws Exception;
    
}
