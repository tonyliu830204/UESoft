package com.syuesoft.systemmanagement.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.systemmanagement.vo.DataBackupVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

public interface DBBackUpLoadService {
    
    public Msg saveBackUp(DataBackupVo dataBackupVo, BasUsers user) throws Exception;
    
    public Msg findBackUp(DataBackupVo dataBackupVo, BasUsers user) throws Exception;
    
    //获取备份信息  备份时间  备份名称
    public Json getBackupInfor(DataBackupVo dataBackupVo, BasUsers user )throws Exception;
}