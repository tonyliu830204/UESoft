package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasDeptService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.base.vo.BasStuffClassVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;
/**
 * 员工分类Action
 * @author LWJ
 * */
@ParentPackage(value = "basePackage")
@Action("basStuffClassAction")
public class BasStuffClassAction extends BaseAction implements ModelDriven<BasStuffClassVo>
{
    private static final long serialVersionUID = 9114523477519758538L;
    Logger logger=Logger.getLogger(this.getClass());
    private BasStuffClassVo basStuffClassVo = new BasStuffClassVo();
    @Autowired
    private BaseService baseService;
    /**
     * 查询当前公司下的所有员工
     * */
    public void findAllStuffOfEnterpriseInfo(){
    	try {
			super.writeJson(baseService.findBasStuffClass(basStuffClassVo.getQ(), basStuffClassVo.getIdOrNameTag(),-1,getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询当前公司下的所有员工失败！", e);
			e.printStackTrace();
		}
    } 
    /**
	 * 管理人员查询
	 * */
	public void findManager(){
		try {
			super.writeJson(baseService.findManager(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("管理人员查询失败！", e);
		}
	}
	/**
	 * 采购员查询
	 * */
	public void findEnterpriseBuyer(){
		try {
			super.writeJson(baseService.findPartsStockPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("采购员查询失败！", e);
		}
	}
	/**
	 * 领料员查询
	 * */
	public void findEnterpriseMaterielPerson(){
		try {
			super.writeJson(baseService.findServiceMaterielPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("领料员查询失败！", e);
		}
	}
	/**
	 * 接待员查询
	 * */
	public void findEnterpriseReceivePerson(){
		try {
			super.writeJson(baseService.findServiceReceivePerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("接待员查询失败！", e);
		}
	}
	/**
	 * 维修员查询
	 * */
	public void findEnterpriseMaintainPerson(){
		try {
			super.writeJson(baseService.findAfterServiceMaintainPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("维修员查询失败！", e);
		}
	}
	/**
	 * 退料员查询
	 * */
	public void findEnterpriseRecedePerson(){
		try {
			super.writeJson(baseService.findPartsStorehousePerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("退料员查询失败！", e);
		}
	}
	/**
	 * 业务员查询(维修客户档案)
	 * */
	public void findEnterpriseOperationPerson(){
		try {
			super.writeJson(baseService.findServiceOperationPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("业务员查询(客户档案)失败！", e);
		}
	}
	/**
	 * 回访员查询
	 * */
	public void findEnterpriseVisitingPerson(){
		try {
			super.writeJson(baseService.findVisitingPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("回访员查询失败！", e);
		}
	}
	/**
	 * 三包员查询
	 * */
	public void findEnterpriseEgisAvailPerson(){
		try {
			super.writeJson(baseService.findServiceEgisAvailPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("三包员查询失败！", e);
		}
	}
	/**
	 * 维修技师查询
	 * */
	public void findEnterpriseMaintainArtificer(){
		try {
			super.writeJson(baseService.findAfterServiceMaintainPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("维修技师查询失败！", e);
		}
	}
	/**
	 * 检验员查询
	 * */
	public void findPDIProvePerson(){
		try {
			super.writeJson(baseService.findPDIProvePerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("检验员查询失败！", e);
		}
	}
	/**
	 * 销售系统：开票人，收款人查询
	 * */
	public void findFinanceicalPerson(){
		try {
			super.writeJson(baseService.findFinanceicalPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("销售系统：开票人，收款人查询失败！", e);
		}
	}
	/**
	 * 销售业务员查询
	 * */
	public void findSellOperationPerson(){
		try {
			super.writeJson(baseService.findSellOperationPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("销售业务员查询失败！", e);
		}
	}
	/**
	 * 销售采购员查询
	 * */
	public void findCarStockPerson(){
		try {
			super.writeJson(baseService.findCarStockPerson(basStuffClassVo.getQ(),  basStuffClassVo.getIdOrNameTag(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("销售采购员查询失败！", e);
		}
	}
    
    public BasStuffClassVo getModel()
    {
        return basStuffClassVo;
    }

}
