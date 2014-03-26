package com.syuesoft.vipmanagement.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.InfomationSendManageVo;

public interface InfomationSendManageService {
	//短信发送管理查询
	public Json doFind(InfomationSendManageVo infomationSendManageVo)throws Exception;
	
	//发送短信
	public Message saveSmsSend(InfomationSendManageVo infomationSendManageVo, BasUsers user)throws Exception;
}