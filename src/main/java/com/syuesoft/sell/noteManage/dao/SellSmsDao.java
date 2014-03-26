package com.syuesoft.sell.noteManage.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.noteManage.vo.SellSmsVo;


public interface SellSmsDao extends BaseDaoI<BaseBean>{

	//发送短信
	public void smsSend(SellSmsVo smsSendVo)throws Exception;
	//发送成功的短信列表
	public Datagrid getSmsInfo( SellSmsVo  smsSendVo) throws Exception;

}
