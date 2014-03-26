package com.syuesoft.sell.sellwork.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.model.XsCustomLeva;
import com.syuesoft.sell.sellwork.service.PossibleCustomFollowService;
import com.syuesoft.sell.sellwork.vo.PossibleCustomFollowVo;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;

@ParentPackage(value="basePackage")
@Action("possibleCustomFollowAction")
public class PossibleCustomFollowAction extends BaseAction implements
		ModelDriven<PossibleCustomFollowVo> {

	private PossibleCustomFollowVo possibleCustomFollowVo = new PossibleCustomFollowVo();
	
	
	public PossibleCustomFollowVo getModel() {
		return possibleCustomFollowVo;
	}
	
	@Resource
	private PossibleCustomFollowService possibleCustomFollowService;
	
	
	/**
	 * 获取客户档案信息的 级别和统计数量
	 */
	public void findCustomInforCount(){
		try {
				possibleCustomFollowVo.setEnterpriseId(getNowEnterpriseId());
			List rlist = possibleCustomFollowService.findCustomInforCount(possibleCustomFollowVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  获取客户档案信息
	 */
	public void findCustomInfor(){
		try {
				possibleCustomFollowVo.setEnterpriseId(getNowEnterpriseId());
			List rlist = possibleCustomFollowService.findCustomInfor(possibleCustomFollowVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过客户编号 
	 */
	public void findCustomById(){
		try {
			List rlist = possibleCustomFollowService.findCustomById(possibleCustomFollowVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存一条潜在客户记录
	 */
	public void addCustomRecord(){
		Message msg = new Message(); 
		try {
				possibleCustomFollowVo.setEnterpriseId(getUserEnterpriseId());
			possibleCustomFollowService.addCustomRecord(possibleCustomFollowVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 删除一条潜在客户记录
	 */
	public void deleteCustomRecord(){
		Message msg = new Message(); 
		try {
			possibleCustomFollowService.deleteCustomRecord(possibleCustomFollowVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 修改一条潜在客户记录
	 */
	public void updateCustomRecord(){

		Message msg = new Message(); 
		try {
			possibleCustomFollowService.updateCustomRecord(possibleCustomFollowVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	
	}
	
	/**
	 * 审核
	 */
	public void doAudit(){

		Msg msg = new Msg(); 
		try {
			msg=possibleCustomFollowService.doAudit(possibleCustomFollowVo);
			
		} catch (Exception e) {
			 msg.setMsg("审批信息失败！");
			msg.setSuccess(false);
		}
		super.writeJson(msg);
	
	}
	
	/**
	 *  获取查询跟踪客户档案信息
	 */
	public void getTzCustom(){
		try {
				possibleCustomFollowVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson( possibleCustomFollowService.getTzCustom(possibleCustomFollowVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  查询潜在客户跟踪信息
	 */
	public void getTzCustomTrack(){
		try {
				possibleCustomFollowVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson( possibleCustomFollowService.getTzCustomTrack(possibleCustomFollowVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  通过潜在客户级别的编号获取级别的间隔天数
	 */
	public void getLevelDays(){
		Message msg = new Message(); 
		try {
			msg.setSuccess(true);
			XsCustomLeva xsCustomLeva = (XsCustomLeva)possibleCustomFollowService.getLevelDays(possibleCustomFollowVo);
			msg.setObj(xsCustomLeva);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
}
