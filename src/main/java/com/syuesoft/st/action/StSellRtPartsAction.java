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
import org.apache.struts2.convention.annotation.ParentPackage;
/**
 * 销售单退料Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StSellRtPartsAction")
public class StSellRtPartsAction  extends BaseAction implements
ModelDriven<StRtPartsVo> {

	private Logger logger = Logger.getLogger(this.getClass());
	private StRtPartsVo stRtPartsVo=new StRtPartsVo();
	@Autowired StRtPartsService stRtPartsService;
	
	/**
	 * 销售退料单汇总信息          预加载
	 */
	public void loadStRtPartsMain() {
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.loadSellStRtPartsMain(stRtPartsVo));
		} catch (Exception e) {
			logger.error("销售退料单汇总信息       预加载    异常", e);
		}
	}
	
	/**
	 * 销售退料单汇总信息          综合查询
	 */
	public void searchByCondition() {
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.searchSellStRtPartsMainByCondition(stRtPartsVo));
		} catch (Exception e) {
			logger.error("销售退料单汇总信息     综合查询    异常", e);
		}
	}
	
	/**
	 * 根据销售退料单号获取销售单明细配件信息
	 */
    public void loadSelledParts(){
    	try {
			super.writeJson(stRtPartsService.loadSelledParts(stRtPartsVo));
		} catch (Exception e) {
			logger.error("根据销售退料单号获取销售单明细配件信息   异常", e);
		}
    }
    
    /**
	 * 销售单信息       预加载
	 */
	public void loadStSellOrder(){
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.loadStSellOrder(stRtPartsVo));
		} catch (Exception e) {
			logger.error("销售单汇总信息    预加载    异常", e);
		}
	}
    
    /**
	 * 销售单信息     综合查询
	 */
    public void searchStSellOrderByCondition(){
    	try {
    		stRtPartsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtPartsService.searchStSellOrderByCondition(stRtPartsVo));
		} catch (Exception e) {
			logger.error("根据销售退料单号获取销售单明细配件信息   异常", e);
		}
    }
	
    /**
	 * 销售退料单明细信息（根据退料单汇总单号获取）    预加载
	 */
	public void searchStRtPartsDetailByStrtpmId(){
		try {
			super.writeJson(super.listConvertJson(stRtPartsService.searchSellStRtPartsDetailByStrtpmId(stRtPartsVo)));
		} catch (Exception e) {
			logger.error("退料单明细信息（根据退料单汇总单号获取）    预加载  异常", e);
		}
	}
    
	/**
	 * 销售退料单汇总明细信息     新增
	 */
	public void addStSellRtPartsMain(){
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stRtPartsVo.setManager(user.getBasStuff().getStfId()+"");
		try {
			stRtPartsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			stRtPartsService.add(stRtPartsVo, stRtPartsService.jsonChangeDetailList(stRtPartsVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		}catch(Exception e) {
			logger.error("工单退料汇总明细信息     新增    异常", e);
		}
	}
	
	/**
	 * 销售退料单汇总明细信息         删除
	 */
	public void delStSellRtpartsMain(){
		try {
			stRtPartsService.delete(stRtPartsVo);
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("工单退料单汇总明细信息         删除    异常", e);
		}
	}

	public StRtPartsVo getModel() {
		return stRtPartsVo;
	}
}
