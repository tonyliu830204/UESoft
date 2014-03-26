package com.syuesoft.sell.control.service;


import com.syuesoft.sell.control.vo.XsSellFlowControlVo;
import com.syuesoft.util.Msg;

public interface XsSellFlowControlService{
	/**增加流程控制信息*/
	public Msg save(XsSellFlowControlVo sxfcVo)throws Exception;
	/**删除流程控制信息*/
	public Msg delete(XsSellFlowControlVo sxfcVo)throws Exception;
}
