package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.st.service.StRtPartsService;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.Msg;

/**
 * 工单退料Action
 * @author SuMing
 */

import org.apache.struts2.convention.annotation.ParentPackage;@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StRtPartsAction")
public class StRtPartsAction extends BaseAction implements
          ModelDriven<StRtPartsVo> {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StRtPartsVo stRtPartsVo=new StRtPartsVo();
	@Autowired StRtPartsService stRtPartsService;
	
	/**
	 * 工单退料单汇总信息       条件综合查询
	 */
	public void searchByCondition(){
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.searchRecptByCondition(stRtPartsVo));
		} catch (Exception e) {
			logger.error("工单退料单汇总信息     条件综合查询异常", e);
		}
	}
	
	/**
	 * 工单信息       预加载
	 */
	public void loadFrtReception(){
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.loadFrtReception(stRtPartsVo));
		} catch (Exception e) {
			logger.error("工单信息       预加载", e);
		}
	}
	
	/**
	 * 工单信息       综合查询
	 */
	public void searchFrtReceptionByCondition(){
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.searchFrtReceptionByCondition(stRtPartsVo));
		} catch (Exception e) {
			logger.error("工单信息       预加载", e);
		}
	}
	
	/**
	 * 配件出库明细信息 （根据工单号获取）       预加载
	 */
	public void loadParts(){
		try {
			super.writeJson(stRtPartsService.loadParts(stRtPartsVo));
		} catch (Exception e) {
			logger.error("相关计划用料信息 （根据工单号获取）       预加载异常", e);
		}
	}
	
	/**
	 * 根据退料单汇总单号获取退料单明细信息    预加载
	 */
	public void searchStRtPartsDetailByStrtpmId(){
		try {
			super.writeJson(super.listConvertJson(stRtPartsService.searchRecptStRtPartsDetailByStrtpmId(stRtPartsVo)));
		} catch (Exception e) {
			logger.error("退料单明细信息（根据退料单汇总单号获取）    预加载异常", e);
		}
	}
	
	/**
	 * 工单退料汇总明细信息     新增
	 */
	public void addStRtPartsMain(){
		try {
			BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
			if(user!=null&&!user.equals(""))
				stRtPartsVo.setManager(user.getBasStuff().getStfId()+"");
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			String id = stRtPartsService.add(stRtPartsVo, stRtPartsService.jsonChangeDetailList(stRtPartsVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			msg.setObj(id);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("工单退料汇总明细信息     新增", e);
		}
	}
	
	/**
	 * 工单退料单汇总明细信息         删除
	 */
	public void delStRtpartsMain(){
		try {
			stRtPartsService.delete(stRtPartsVo);
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("工单退料单汇总明细信息         删除", e);
		}
	}
	
	public StRtPartsVo getModel() 
	{
		return stRtPartsVo;
	}

}
