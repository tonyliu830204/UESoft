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
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Msg;
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StOutAction")
public class StOutAction extends BaseAction implements ModelDriven<StOutVo>  {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StOutVo stOutVo=new StOutVo();
	@Autowired StOutService stOutService;

	/**
	 * 出库单汇总信息      预加载/  综合查询
	 */
	public void searchByCondition(){
       try {
    	    stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.searchByCondition(stOutVo));
       } catch (Exception e) {
    	    logger.error("出库单汇总信息       综合查询     异常", e);
		}
	}
	
	/**
	 * 通过出库单汇总信息预加载出库单明细信息
	 */
	public void serachStOutItemByStomId(){
		try {
			super.writeJson(super.listConvertJson(stOutService.serachStOutItemByCondition(stOutVo)));
		}catch (Exception e) {
			logger.error("通过出库单汇总信息获取出库单明细信息     异常", e);
		}
	}
	
	/**
	 * 工单信息加载   预加载
	 */
	public void loadFrtReception()
	{
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.loadFrtReception(stOutVo));
		} catch (Exception e) {
			logger.error("工单信息加载   预加载   异常", e);
		}
	}
	
	/**
	 * 工单信息加载   综合查询
	 */
	public void searchFrtReceptInfoByCondition()
	{
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.searchFrtReceptInfoByCondition(stOutVo));
		} catch (Exception e) {
			logger.error("工单信息加载   综合查询    异常", e);
		}
	}
	
	/**
	 * 索赔分类信息   预加载
	 */
	public void loadClaimsType()
	{
		try {
			super.writeJson(stOutService.findAllClaimsType(stOutVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("索赔分类信息   预加载     异常", e);
		}
	}
	
	/**
	 * 收费性质信息   预加载
	 */
	public void loadBasRepairType()
	{
		try {
			super.writeJson(stOutService.findAllRepairType(stOutVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("收费性质信息   预加载     异常", e);
		}
	}
	
	
	/**
	 * 车辆牌照信息    预加载
	 */
	public void findAllCarLicense()
	{
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.findAllCarLicense(stOutVo.getQ(),stOutVo.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("索赔分类信息   预加载     异常", e);
		}
	}
	
	/**
	 * 客户名称信息  预加载
	 */
	public void findAllCustom(){
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.findAllCustom(stOutVo.getQ(),stOutVo.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("客户名称信息  预加载     异常", e);
		}
	}
	
	/**
	 * 配件信息加载   预加载查询
	 */
	public void loadFrtParts()
	{
		try {
			super.writeJson(stOutService.loadFrtParts(stOutVo.getPartsId(), stOutVo.getPartsName(),stOutVo.getStoreId(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("配件信息加载   查询     异常", e);
		}
	}
	
	/**
	 * 工单号加载计划用料信息
	 */
	public void loadFrtReceptParts(){
	    try {
	    	stOutVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stOutService.reFrtReceptParts(stOutVo));
		} catch (Exception e) {
			logger.error("工单号加载计划用料信息     异常", e);
		}
	}
	
	/**
	 * 出库单汇总，明细        添加
	 */
	public void add()
	{
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stOutVo.setManager(user.getBasStuff().getStfId()+"");
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			stOutService.add(stOutVo,stOutService.jsonChangeDetailList(stOutVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("出库单汇总，明细        添加     异常", e);
		}
	}
	
	/**
	 * 出库单汇总，明细        删除
	 */
	public void del(){
		try {
		    stOutService.delete(stOutVo);
		    Msg msg = new Msg();
		    msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("出库单汇总，明细        删除     异常", e);
		}
	}
	
	/**
	 * 出库单汇总，明细        修改
	 */
	public void update(){
		try {
			stOutVo.setEnterpriseId(getNowEnterpriseId());
			stOutService.update(stOutVo,stOutService.jsonChangeDetailList(stOutVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("出库单汇总，明细        修改     异常", e);
		}
	}
	
	/**
	 * 判断出库单中工单号是否在工单退料单中存在，若存在，不能进行删除修改操作，若不存在，可进行删除修改操作
	 */
	public void doDeleteOrUpdate(){
		try {
			Msg msg = new Msg();
			boolean isno= stOutService.doDeleteOrUpdate(stOutVo);
			if(isno)
			    msg.setSuccess(true);
			else
				msg.setSuccess(false);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("出库单汇总，明细        删除    异常", e);
		}
	}
	
	public StOutVo getModel() {
		return stOutVo;
	}
	
}
