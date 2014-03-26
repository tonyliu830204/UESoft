package com.syuesoft.vipmanagement.service;

import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.vipmanagement.vo.SmsRecordManagementVo;

public interface SmsRecordManagementService {
	//短信发送记录查询
	public Json getSmsSended(SmsRecordManagementVo smsRecordManagementVo)throws Exception;
	
	//短信记录删除
	public Msg doDelete(SmsRecordManagementVo smsRecordManagementVo)throws Exception;
}