package com.syuesoft.sell.noteManage.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.noteManage.vo.SellSmsVo;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.InfomationSendManageVo;

public interface SellSmsService {
	//发送短信
	public void smsSend(SellSmsVo smsSendVo)throws Exception;
	public void saveSmsState(String id, Integer state)throws Exception;
	//发送成功的短信列表
	public Datagrid getSmsInfo( SellSmsVo  smsSendVo) throws Exception;
	public Message saveSmsSend(SellSmsVo smsSendVo) throws Exception;

}
