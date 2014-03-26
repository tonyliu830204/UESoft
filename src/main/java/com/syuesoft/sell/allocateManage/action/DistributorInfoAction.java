package com.syuesoft.sell.allocateManage.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.allocateManage.service.DistributorInfoService;
import com.syuesoft.sell.allocateManage.vo.DistributorInfoVo;
import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")@Action("distributorInfoAction")
public class DistributorInfoAction extends BaseAction implements ModelDriven<DistributorInfoVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private DistributorInfoService disService;
	private DistributorInfoVo distributorInfoVo=new DistributorInfoVo();
	
	public DistributorInfoVo getModel() {
		
		return distributorInfoVo;
	}
	/**
	 * 查询分销商信息
	 */
	public void getDistributorInfo(){
		try {
				distributorInfoVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(disService.getDistributorInfo(distributorInfoVo));
		} catch (Exception e) {
			logger.debug("查询分销商信息失败！");
		}	
	}

}
