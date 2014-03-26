package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.st.service.StOutService;
import com.syuesoft.st.service.StPreOutService;
import com.syuesoft.st.vo.StPreOutVo;
import com.syuesoft.util.Msg;

@SuppressWarnings("serial")
@ParentPackage(value="basePackage")
@Action("StPreOutAction")
public class StPreOutAction extends BaseAction implements
ModelDriven<StPreOutVo>{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StPreOutVo stPreOutVo=new StPreOutVo(); 
	@Autowired StPreOutService stPreOutService;    
	@Autowired StOutService stOutService;
	
	/**
	 * 预出库管理模块     预出库汇总信息     综合查询
	 */
	public void searchByCondition() {
		try {
			stPreOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPreOutService.searchByCondition(stPreOutVo));
		} catch (Exception e) {
			logger.error("预出库管理模块     预出库汇总信息     综合查询    异常", e);
		}
	}
	
	/**
     * 开票方式     预加载
     */
	public void loadOpenBillStyle(){
		try {
			super.writeJson(stOutService.loadDataByChildId(Contstants.DEALSTYLE.OUTPER));
		} catch (Exception e) {
			logger.error(" 票据类型     预加载    异常", e);
		}
	}
	
	/**
	 * 领用人信息    预加载
	 */
	public void loadBasStuff(){
		try {
			stPreOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPreOutService.loadBasStuff(stPreOutVo));
		} catch (Exception e) {
			logger.error("领用人信息    预加载    异常", e);
		}
	}
	
	/**
	 * 领用人信息       综合查询
	 */
	public void searchBasStuffByCondition(){
		try {
			stPreOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPreOutService.searchBasStuffByCondition(stPreOutVo));
		} catch (Exception e) {
			logger.error("领用人信息       综合查询    异常", e);
		}
	}
	
	/**
	 * 配件信息     查询
	 */
	public void loadFrtParts(){
		try {
			super.writeJson(stOutService.loadFrtParts(stPreOutVo.getPartsId(), stPreOutVo.getPartsName(),stPreOutVo.getStoreId(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("配件信息     查询  异常", e);
		}
	}
	
	/**
	 * 预出库管理模块     预出库汇总信息    添加
	 */
	public void addStPreOut(){
		try {
			BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
			if(user!=null&&!user.equals(""))
				stPreOutVo.setStpremStfId(user.getBasStuff().getStfId()+"");
			stPreOutVo.setEnterpriseId(getNowEnterpriseId());
			stPreOutService.add(stPreOutVo,stPreOutService.jsonChangeDetailList(stPreOutVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("预出库管理模块     预出库汇总信息    添加  异常", e);
		}
	}
	
	/**
	 * 预出库管理模块     预出库汇总信息     删除
	 */
	public void deleteStPreOut() {
		try {
			stPreOutService.delete(stPreOutVo);
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("预出库管理模块     预出库汇总信息     删除   异常", e);
		}
	}
	
	/**
	 *  预出库管理模块     预出库汇总信息      修改
	 */
	public void updateStPreOut() {
		try {
			stPreOutVo.setEnterpriseId(getNowEnterpriseId());
			stPreOutService.update(stPreOutVo,stPreOutService.jsonChangeDetailList(stPreOutVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("预出库管理模块     预出库汇总信息      修改    异常", e);
		}
	}
	
	/**
	 * 根据预出库单号获取预出库明细信息
	 */
	public void searchStPreOutDetailByStpremId() {
		try {
			super.writeJson(super.listConvertJson(stPreOutService.searchStPreOutDetailByStpremId(stPreOutVo)));
		} catch (Exception e) {
			logger.error("根据预出库单号获取预出库明细信息   异常", e);
		}
	}
	
	public StPreOutVo getModel() {
		return stPreOutVo;
	}
	
}
