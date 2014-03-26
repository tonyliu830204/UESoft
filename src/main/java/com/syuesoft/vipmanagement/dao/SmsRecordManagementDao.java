package com.syuesoft.vipmanagement.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasVipSendinfomation;
import com.syuesoft.vipmanagement.vo.SmsRecordManagementVo;

public interface SmsRecordManagementDao extends BaseDaoI<BasVipSendinfomation>{
	//短信记录删除
	public void doDelete(SmsRecordManagementVo smsRecordManagementVo)throws Exception;
}