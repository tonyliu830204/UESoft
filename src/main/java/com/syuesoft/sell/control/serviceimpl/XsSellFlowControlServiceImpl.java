package com.syuesoft.sell.control.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.control.service.XsSellFlowControlService;
import com.syuesoft.sell.control.vo.XsSellFlowControlVo;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;
/**
 * 流程控制service
 * */
@Service("xsSellFlowControlService")
public class XsSellFlowControlServiceImpl implements XsSellFlowControlService {
	@Autowired
	private XsSellFlowControlDao xsSellFlowControlDao;
	/**
	 * 删除流程控制信息
	 * */
	
	public Msg delete(XsSellFlowControlVo sxfcVo) throws Exception {
		// TODO Auto-generated method stub
		Msg msg=new Msg();
		try {
			XsSellFlowControl XsSellFlowControl=new XsSellFlowControl();
			MyBeanUtils.getInstance().copyBeans(sxfcVo, XsSellFlowControl);
			xsSellFlowControlDao.delete(XsSellFlowControl);
			msg.setSuccess(true);
			msg.setMsg("删除流程控制信息成功！");
		} catch (Exception e) {
			// TODO: handle exception
			msg.setMsg("删除流程控制信息失败！");
		}
		return msg;
	}
	/**
	 * 增加流程控制信息
	 * */
	
	public Msg save(XsSellFlowControlVo sxfcVo) throws Exception {
		Msg msg=new Msg();
		try {
			XsSellFlowControl XsSellFlowControl=new XsSellFlowControl();
			MyBeanUtils.getInstance().copyBeans(sxfcVo, XsSellFlowControl);
			xsSellFlowControlDao.save(XsSellFlowControl);
			msg.setSuccess(true);
			msg.setMsg("增加流程控制信息成功！");
		} catch (Exception e) {
			// TODO: handle exception
			msg.setMsg("增加流程控制信息失败！");
		}
		return msg;
	}
	
}
