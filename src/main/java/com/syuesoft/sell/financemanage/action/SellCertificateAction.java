package com.syuesoft.sell.financemanage.action;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.SellCertificateService;
import com.syuesoft.sell.financemanage.vo.SellCertificateVo;
import com.syuesoft.util.Msg;
@ParentPackage(value="basePackage")
@Action("sellCertificateAction") 
public class SellCertificateAction extends BaseAction implements ModelDriven<SellCertificateVo> {
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellCertificateVo sellCertificateVo = new SellCertificateVo();
	
	public SellCertificateVo getModel() {
		return sellCertificateVo;
	}
	@Resource
	private SellCertificateService  sellCertificateService;
	
	/**
	 * 合格证管理查询汇总信息
	 */
	public void getPage(){
		try {
				sellCertificateVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCertificateService.getPager(sellCertificateVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getReceipt(){
		try {
				sellCertificateVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCertificateService.getReceipt(sellCertificateVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveCertificate(){     
		Msg msg = new Msg();
		try {
			sellCertificateService.addCertificate(sellCertificateVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 删除合格证信息
	 */
	public void deleteCertificate(){
		Msg msg = new Msg();
		try {
			sellCertificateService.deleteCertificate(sellCertificateVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除合格证信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateCertificate(){
		Msg msg = new Msg();
		try {
			sellCertificateService.updateCertificate(sellCertificateVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新合格证信息失败", e);
		}
		super.writeJson(msg);
	}
	public void modifyCertificate(){
		Msg msg = new Msg();
		try {
			sellCertificateService.modifyCertificate(sellCertificateVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新合格证信息失败", e);
		}
		super.writeJson(msg);
	}
}
