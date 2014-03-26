package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.GatheringService;
import com.syuesoft.frt.vo.GatheringVo;

/**
 * 收银action
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@ParentPackage(value="basePackage")@Action("gatheringAction")
public class GatheringAction extends BaseAction implements ModelDriven<GatheringVo>{
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(this.getClass());
	GatheringVo gtVo = new GatheringVo();
	private GatheringService gatheringServiceImpl;
	
	public GatheringVo getGtVo() {
		return gtVo;
	}


	public void setGtVo(GatheringVo gtVo) {
		this.gtVo = gtVo;
	}


	public GatheringService getGatheringServiceImpl() {
		return gatheringServiceImpl;
	}

	@Autowired
	public void setGatheringServiceImpl(GatheringService gatheringServiceImpl) {
		this.gatheringServiceImpl = gatheringServiceImpl;
	}
	
	/**
	 * 公共批量收款
	 * */
	public void saveBatchGathering(){
		try {
			if(gtVo.getPreclrId()!=null&&gtVo.getPreclrId().length()>0){
				StringBuffer sb=new StringBuffer();
				for (int i = 0; i < gtVo.getPreclrId().length(); i++) {
					if(!(gtVo.getPreclrId().charAt(i)>='0'&&gtVo.getPreclrId().charAt(i)<='9')){
						sb.append(gtVo.getPreclrId().charAt(i));
					}
				}
				if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)){
					savePreclearingBatchGathering();
				}else if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)){
					saveClaimantBatchGathering();
				}else if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)){
					saveSellBatchGathering();
				}
			}else if(gtVo.getBbgId()!=null&&gtVo.getBbgId().length()>0){
				StringBuffer sb=new StringBuffer();
				for (int i = 0; i < gtVo.getBbgId().length(); i++) {
					if(!(gtVo.getBbgId().charAt(i)>='0'&&gtVo.getBbgId().charAt(i)<='9')){
						sb.append(gtVo.getBbgId().charAt(i));
					}
				}
				if(sb.toString().equals(Contstants.SERVICEBATCHGATHERINGCOLLECT)){
					savePreclearingBatchGathering();
				}else if(sb.toString().equals(Contstants.CLAIMSBATCHGATHERINGCOLLECT)){
					saveClaimantBatchGathering();
				}else if(sb.toString().equals(Contstants.SELLBATCHGATHERINGCOLLECT)){
					saveSellBatchGathering();
				}
			}
		} catch (Exception e) {
			logger.error("批量收款失败！", e);
		}
	}
	/**
	 * 查找收款信息
	 * */
	public void datagridGathering(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找收款信息失败！", e);
		}
	}
	/**
	 * 查找未收款信息
	 * */
	public void datagridNoGathering(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridNoGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找未收款信息失败！", e);
		}
	}
	/**
	 * 查找批量未收款信息
	 * */
	public void datagridNoBatchGathering(){
		try {
			super.writeJson(gatheringServiceImpl.datagridNoBatchGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找批量未收款信息失败！", e);
		}
	}
	/**
	 * 查找批量收款汇总信息
	 * */
	public void datagridBatchGatheringCollect(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchGatheringCollect(gtVo));			
		} catch (Exception e) {
			logger.error("查找批量收款汇总信息失败！", e);
		}
	}
	
	/**
	 * 公共收款
	 * */
	public void saveGathering(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			if(gtVo.getPreclrId()!=null){
				StringBuffer sb=new StringBuffer();
				for (int i = 0; i < gtVo.getPreclrId().length(); i++) {
					if(!(gtVo.getPreclrId().charAt(i)>='0'&&gtVo.getPreclrId().charAt(i)<='9')){
						sb.append(gtVo.getPreclrId().charAt(i));
					}
				}
				if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)){//维修结算单号 
					savePreclearingGathering();
				}else if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)){//索赔结算单号 
					saveClaimantGathering();
				}else if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)){//销售结算单号 
					saveSellGathering();
				}
			}			
		} catch (Exception e) {
			logger.error("收款失败！", e);
		}
	}
	
	/**
	 * 增加索赔收款
	 * */
	public void saveClaimantGathering(){
		try {
			super.writeJson(gatheringServiceImpl.saveClaimantGathering(gtVo));			
		} catch (Exception e) {
			logger.error("增加索赔收款失败！", e);
		}
	}
	/**
	 * 增加索赔批量收款
	 * */
	public void saveClaimantBatchGathering(){
		try {
			super.writeJson(gatheringServiceImpl.saveClaimantBatchGathering(gtVo));			
		} catch (Exception e) {
			logger.error("增加索赔批量收款失败！", e);
		}
	}
	/**
	 * 增加维修收款
	 * */
	public void savePreclearingGathering() {
		try {
			super.writeJson(gatheringServiceImpl.savePreclearingGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加维修收款失败！", e);
		}
	}
	/**
	 * 增加维修批量收款
	 * */
	public void savePreclearingBatchGathering() {
		try {
			super.writeJson(gatheringServiceImpl.savePreclearingBatchGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加维修批量收款失败！", e);
		}
	}
	/**
	 * 增加销售收款
	 * */
	public void saveSellGathering() {
		try {
			super.writeJson(gatheringServiceImpl.saveSellGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加维修收款失败！", e);
		}
	}
	/**
	 * 增加销售批量收款
	 * */
	public void saveSellBatchGathering() {
		try {
			super.writeJson(gatheringServiceImpl.saveSellBatchGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加销售批量收款失败！", e);
		}
	}
	/**
	 * 查找收款相关数据
	 * */
	public void datagridGatheringByPreclrId(){
		try {
			super.writeJson(gatheringServiceImpl.datagridGatheringByPreclrId(gtVo));
		} catch (Exception e) {
			logger.error("查找收款相关数据失败！", e);
		}
	}
	/**
	 * 查找批量收款相关数据
	 * */
	public void datagridBatchGatheringByPreclrId(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchGatheringByPreclrId(gtVo));
		} catch (Exception e) {
			logger.error("查找批量收款相关数据失败！", e);
		}
	}
	/**
	 * 查找收款记录
	 * */
	public void datagridGatheringOld(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridGatheringOld(gtVo));
		} catch (Exception e) {
			logger.error("查找收款记录失败！", e);
		}
	}
	/**
	 * 查找批量收款记录
	 * */
	public void datagridBatchGatheringOld(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchGatheringOld(gtVo));
		} catch (Exception e) {
			logger.error("查找批量收款记录失败！", e);	
		}
	}
	/*****************************************代付****************************/
	/**
	 * 公共代付收款
	 * */
	public void saveSubstituteGathering(){
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < gtVo.getSspId().length(); i++) {
				if(!(gtVo.getSspId().charAt(i)>='0'&&gtVo.getSspId().charAt(i)<='9')){
					sb.append(gtVo.getSspId().charAt(i));
				}
			}
			if(sb.toString().equals(Contstants.SERVICESUITSUBTITUTEGATHERING)){
				savePreclearingSubstituteGathering();
			}else if(sb.toString().equals(Contstants.CLAIMSSUITSUBTITUTEGATHERING)){
				saveClaimantSubstituteGathering();
			}else if(sb.toString().equals(Contstants.SELLSUITSUBTITUTEGATHERING)){
				saveSellSubstituteGathering();
			}else if(sb.toString().equals(Contstants.SERVICESUITSUBTITUTEBATCHGATHERING)){
				savePreclearingSubstituteGatheringAsBatch();
			}else if(sb.toString().equals(Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING)){
				saveClaimantSubstituteGatheringAsBatch();
			}else if(sb.toString().equals(Contstants.SELLSUITSUBTITUTEBATCHGATHERING)){
				saveSellSubstituteGatheringAsBatch();
			}
		}
	}
	/**
	 * 公共批量代付收款
	 * */
	public void saveBatchSubstituteGathering(){
		try {
			if(gtVo.getPreclrId()!=null&&gtVo.getPreclrId().length()>0){
				StringBuffer sb=new StringBuffer();
				for (int i = 0; i < gtVo.getPreclrId().length(); i++) {
					if(!(gtVo.getPreclrId().charAt(i)>='0'&&gtVo.getPreclrId().charAt(i)<='9')){
						sb.append(gtVo.getPreclrId().charAt(i));
					}
				}
				if(sb.toString().equals(Contstants.SERVICESUITSUBTITUTEGATHERING)){
					savePreclearingBatchSubstituteGathering(Contstants.SERVICESUITSUBTITUTEGATHERING,false);
				}else if(sb.toString().equals(Contstants.CLAIMSSUITSUBTITUTEGATHERING)){
					saveClaimantBatchSubstituteGathering(Contstants.CLAIMSSUITSUBTITUTEGATHERING,false);
				}else if(sb.toString().equals(Contstants.SELLSUITSUBTITUTEGATHERING)){
					saveSellBatchSubstituteGathering(Contstants.SELLSUITSUBTITUTEGATHERING,false);
				}else if(sb.toString().equals(Contstants.SERVICESUITSUBTITUTEBATCHGATHERING)){
					savePreclearingBatchSubstituteGathering(Contstants.SERVICESUITSUBTITUTEBATCHGATHERING,true);
				}else if(sb.toString().equals(Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING)){
					saveClaimantBatchSubstituteGathering(Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING,true);
				}else if(sb.toString().equals(Contstants.SELLSUITSUBTITUTEBATCHGATHERING)){
					saveSellBatchSubstituteGathering(Contstants.SELLSUITSUBTITUTEBATCHGATHERING,true);
				}
			}else if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){
				StringBuffer sb=new StringBuffer();
				for (int i = 0; i < gtVo.getSspId().length(); i++) {
					if(!(gtVo.getSspId().charAt(i)>='0'&&gtVo.getSspId().charAt(i)<='9')){
						sb.append(gtVo.getSspId().charAt(i));
					}
				}
				if(sb.toString().equals(Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT)){
					savePreclearingBatchSubstituteGathering(Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT,false);
				}else if(sb.toString().equals(Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT)){
					saveClaimantBatchSubstituteGathering(Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT,false);
				}else if(sb.toString().equals(Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT)){
					saveSellBatchSubstituteGathering(Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT,false);
				}else if(sb.toString().equals(Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)){
					savePreclearingBatchSubstituteGathering(Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT,true);
				}else if(sb.toString().equals(Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)){
					saveClaimantBatchSubstituteGathering(Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT,true);
				}else if(sb.toString().equals(Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)){
					saveSellBatchSubstituteGathering(Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT,true);
				}
			}
		} catch (Exception e) {
			logger.error("批量代付收款失败！", e);
		}
	}
	/**
	 * 查找代付收款汇总信息
	 * */
	public void datagridSubstituteGatheringMain(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridSubstituteGatheringMain(gtVo));			
		} catch (Exception e) {
			logger.error("查找代付收款汇总信息失败！", e);
		}
	}
	/**
	 * 查找代付收款信息
	 * */
	public void datagridSubstituteGathering(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridSubstituteGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找代付收款信息失败！", e);
		}
	}
	/**
	 * 查找代付未收款汇总信息
	 * */
	public void datagridNoSubstituteGatheringMain(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridNoSubstituteGatheringMain(gtVo));			
		} catch (Exception e) {
			logger.error("查找代付未收款汇总信息失败！", e);
		}
	}
	/**
	 * 查找代付未收款信息
	 * */
	public void datagridNoSubstituteGathering(){
		try {
			gtVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(gatheringServiceImpl.datagridNoSubstituteGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找代付未收款信息失败！", e);
		}
	}
	/**
	 * 查找代付批量未收款信息
	 * */
	public void datagridNoSubstituteBatchGathering(){
		try {
			super.writeJson(gatheringServiceImpl.datagridNoSubstituteBatchGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找代付批量未收款信息失败！", e);
		}
	}
	/**
	 * 增加代付索赔收款
	 * */
	public void saveClaimantSubstituteGathering(){
		try {
			super.writeJson(gatheringServiceImpl.saveClaimantSubstituteGathering(gtVo));			
		} catch (Exception e) {
			logger.error("增加代付索赔收款失败！", e);
		}
	}
	/**
	 * 增加代付批量索赔收款
	 * */
	public void saveClaimantSubstituteGatheringAsBatch(){
		try {
			super.writeJson(gatheringServiceImpl.saveClaimantSubstituteGatheringAsBatch(gtVo));			
		} catch (Exception e) {
			logger.error("增加代付批量索赔收款失败！", e);
		}
	}
	/**
	 * 增加批量代付索赔收款
	 * */
	public void saveClaimantBatchSubstituteGathering(String type,Boolean flag){
		try {
			super.writeJson(gatheringServiceImpl.saveClaimantBatchSubstituteGathering(gtVo,type,flag));			
		} catch (Exception e) {
			logger.error("增加代付索赔批量收款失败！", e);
		}
	}
	/**
	 * 增加代付维修收款
	 * */
	public void savePreclearingSubstituteGathering() {
		try {
			super.writeJson(gatheringServiceImpl.savePreclearingSubstituteGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加代付维修收款失败！", e);
		}
	}
	/**
	 * 增加代付批量维修收款
	 * */
	public void savePreclearingSubstituteGatheringAsBatch(){
		try {
			super.writeJson(gatheringServiceImpl.savePreclearingSubstituteGatheringAsBatch(gtVo));			
		} catch (Exception e) {
			logger.error("增加代付维修收款失败！", e);
		}
	}
	/**
	 * 增加批量代付维修收款
	 * */
	public void savePreclearingBatchSubstituteGathering(String type,Boolean flag) {
		try {
			super.writeJson(gatheringServiceImpl.savePreclearingBatchSubstituteGathering(gtVo,type,flag));
		} catch (Exception e) {
			logger.error("增加批量代付维修收款失败！", e);
		}
	}
	/**
	 * 增加代付销售收款
	 * */
	public void saveSellSubstituteGathering() {
		try {
			super.writeJson(gatheringServiceImpl.saveSellSubstituteGathering(gtVo));
		} catch (Exception e) {
			logger.error("增加代付销售收款失败！", e);
		}
	}
	/**
	 * 增加批量代付销售收款
	 * */
	public void saveSellSubstituteGatheringAsBatch() {
		try {
			super.writeJson(gatheringServiceImpl.saveSellSubstituteGatheringAsBatch(gtVo));
		} catch (Exception e) {
			logger.error("增加批量代付销售收款失败！", e);
		}
	}
	/**
	 * 增加批量代付销售收款
	 * */
	public void saveSellBatchSubstituteGathering(String type,Boolean flag) {
		try {
			super.writeJson(gatheringServiceImpl.saveSellBatchSubstituteGathering(gtVo,type,flag));
		} catch (Exception e) {
			logger.error("增加代付维修收款失败！", e);
		}
	}
	/**
	 * 查找代付收款相关数据
	 * */
	public void datagridSubstituteGatheringByPreclrId(){
		try {
			super.writeJson(gatheringServiceImpl.datagridSubstituteGatheringByPreclrId(gtVo));
		} catch (Exception e) {
			logger.error("查找代付收款相关数据失败！", e);
		}
	}
	/**
	 * 查找代付批量收款相关数据
	 * */
	public void datagridSubstituteGatheringBySspId(){
		try {
			super.writeJson(gatheringServiceImpl.datagridSubstituteGatheringBySspId(gtVo));
		} catch (Exception e) {
			logger.error("查找代付批量收款相关数据失败！", e);
		}
	}
	/**
	 * 查找代付收款记录
	 * */
	public void datagridSubstituteGatheringOld(){
		try {
			super.writeJson(gatheringServiceImpl.datagridSubstituteGatheringOld(gtVo));
		} catch (Exception e) {
			logger.error("查找代付收款记录失败！", e);
		}
	}
	/**
	 * 查找批量代付收款记录
	 * */
	public void datagridBatchSubstituteGatheringOld(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchSubstituteGatheringOld(gtVo));
		} catch (Exception e) {
			logger.error("查找代付批量收款记录失败！", e);	
		}
	}
	/**
	 * 查找批量代付收款相关数据
	 * */
	public void datagridBatchSubstituteGatheringBySspId(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchSubstituteGatheringBySspId(gtVo));
		} catch (Exception e) {
			logger.error("查找代付批量收款相关数据失败！", e);
		}
	}
	/**
	 * 查找批量代付收款汇总信息
	 * */
	public void datagridBatchSubstituteGatheringCollect(){
		try {
			super.writeJson(gatheringServiceImpl.datagridBatchSubstituteGatheringCollect(gtVo));			
		} catch (Exception e) {
			logger.error("查找未收款信息失败！", e);
		}
	}
	/**
	 * 查找批量代付未收款信息
	 * */
	public void datagridNoBatchSubstituteGathering(){
		try {
			super.writeJson(gatheringServiceImpl.datagridNoBatchSubstituteGathering(gtVo));			
		} catch (Exception e) {
			logger.error("查找批量未收款信息失败！", e);
		}
	}
	/**********************************************/
	/**
	 * 查找结算单是否已转索赔单
	 * */
	public void findIsClaims(){
		try {
			super.writeJson(gatheringServiceImpl.findIsClaims(gtVo));
		} catch (Exception e) {
			logger.error("查找结算单是否已转索赔单失败！", e);
		}
	}
	/**
	 * 收银转结算
	 * */
	public void modifyTransBalance(){
		try {
			super.writeJson(gatheringServiceImpl.modifyTransBalance(gtVo));
		} catch (Exception e) {
			logger.error("收银转结算失败！", e);
		}
	}
	/**
	 * 查找维修预收款
	 * */
	public void findReceptionBeforeBalance(){
		try {
			super.writeJson(gatheringServiceImpl.findReceptionBeforeBalance(gtVo));
		} catch (Exception e) {
			logger.error("查找维修预收款失败！", e);
		}
	}
	
	public GatheringVo getModel() {
		// TODO Auto-generated method stub
		return gtVo;
	}
}

