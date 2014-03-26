package com.syuesoft.st.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.st.service.StGoodsStorageService;
import com.syuesoft.st.vo.StStorageVo;
import com.syuesoft.util.Msg;

/**
 * 入库单管理Action
 * @author SuMing
 */

import org.apache.struts2.convention.annotation.ParentPackage;@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StGoodsStorageAction")
public class StGoodsStorageAction extends BaseAction implements ModelDriven<StStorageVo> {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StStorageVo stStorageVo=new StStorageVo();
	@Autowired StGoodsStorageService stGoodsStorageService;
	
	/**
	 * 入库单汇总信息    综合查询/ 预加载
	 */
	public void searchStGoodsStorageByCondition(){
	    try {
	    	stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.searchStGoodsStorageByCondition(stStorageVo));
	    } catch (Exception e) {
	    	logger.error("入库单汇总信息    综合查询     异常", e);
		}
	}
	
	/**
     * 根据入库单编号预加载入库单明细信息
     */
    public void searchByIdStStorageItem(){
   	    try{
			super.writeJson(stGoodsStorageService.searchByIdStStorageItem(stStorageVo));
	    }catch(Exception e){
			logger.error("根据入库单ID获取入库单明细信息    预加载     异常", e);
	    }
    }
	
	/**
	 * 采购单信息        预加载
	 */
	public void loadStPurOrder(){
		try {
			stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.loadStPurOrder(stStorageVo));
		} catch (Exception e) {
			logger.error("采购单信息        预加载      异常", e);
		}
	}
	
	/**
	 * 采购单信息     综合查询
	 */
	public void searchStPurOrderByCondition(){
		try {
			stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.searchStPurOrderByCondition(stStorageVo));
		} catch (Exception e) {
			logger.error("采购单信息     综合查询     异常", e);
		}
	}
	
	/**
	 * 供应商信息   预加载
	 */
	public void loadBasRelationCampany()
	{
		try {
			stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.loadBasRelationCampany(stStorageVo));
		} catch (Exception e) {
			logger.error("供应商信息   预加载      异常", e);
		}
	}
	
	/**
	 * 供应商信息    综合查询
	 */
	public void searchBasRelationCampanyByCondition()
	{
		try{
			stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.searchBasRelationCampanyByCondition(stStorageVo));
		}catch (Exception e) {
			logger.error(" 供应商信息    综合查询      异常", e);
		}
	}
	
	/**
	 * 入库单号   预加载
	 */
	public  void loadStorageId(){
		try {
			stStorageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stGoodsStorageService.loadStorageId(stStorageVo.getQ(),stStorageVo.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("入库单号   预加载  异常", e);
		}
	}
	
	/**
	 * 选择完采购单后，根据采购单号获取采购明细明细
	 */
	public void searchStPurOrderItemByOrderId()
	{
		try {
			super.writeJson(super.listConvertJson(stGoodsStorageService.searchStPurOrderItemByOrderId(stStorageVo)));
		} catch (Exception e) {
			logger.error("选择完采购单后，根据采购单号获取采购明细明细      异常", e);
		}
	}
	
	/**
	 * 入库单汇总信息       添加
	 */
	public void add(){
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stStorageVo.setManager(user.getBasStuff().getStfId()+"");
		try {
			int result=stGoodsStorageService.addIdentify(stStorageVo);
			Msg msg=new Msg();
			if(result==0){
				stStorageVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
				stGoodsStorageService.add(stStorageVo,stGoodsStorageService.jsonChangeDetailList(stStorageVo));
				msg.setSuccess(true);
			}else if(result==1)
				msg.setMsg("noexist_1");//代表该配件含税成本价不在梯度调价范围内，系统无法调价
			else if(result==2)
				msg.setMsg("noexist_2");//代表该配件含税成本价不在默认调价范围内，系统无法调价
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("入库单汇总信息       添加      异常", e);
		}
	}
	
	/**
	 * 入库单汇总信息       删除
	 */
	public void del(){
		try {
			stStorageVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			stGoodsStorageService.delete(stStorageVo);
			Msg msg=new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("入库单汇总信息       删除    异常", e);
		}
	}
	
	/**
	 * 判断该入库单是否已月结
	 */
	public void doMonthlyStatemont(){
		try {
			boolean isno = stGoodsStorageService.doMonthlyStatemont(stStorageVo);
			Msg msg=new Msg();
			if(isno)
				msg.setSuccess(true);
			else
				msg.setSuccess(false);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("入库单汇总信息       删除    异常", e);
		}
	}
	
	/**
	 * 判断该入库单是否可以删除
	 */
	public void doDeleteAndUpdate(){
		try {
			boolean isno = stGoodsStorageService.doDeleteAndUpdate(stStorageVo);
			Msg msg=new Msg();
			if(isno)
				msg.setSuccess(true);
			else
				msg.setSuccess(false);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("入库单汇总信息       删除    异常", e);
		}
	}
	
    /**
     * 点击下一条记录
     */
     public void findDownByStorageId()
     {
    	 try {
    		List<StStorageVo>  list=new ArrayList<StStorageVo>();
    		StStorageVo stvo= stGoodsStorageService.findByStorageId(stStorageVo);
 			if(stvo.getStorageId()!=null&&!stvo.getStorageId().equals("")){
 				list.add(stvo);
     			super.writeJson(list);
 			}else{
 				Msg msg=new Msg();
 				msg.setMsg("noExist");
     			msg.setSuccess(false);
     			super.writeJson(msg);
 			}
		} catch (Exception e) {
			logger.error("点击下一条记录    异常     异常", e);
		}
     }
     
     /**
      * 点击上一条记录
      */
     public void findUpByStorageId()
     {
    	 try {
    		List<StStorageVo>  list=new ArrayList<StStorageVo>();
 			StStorageVo stvo= stGoodsStorageService.findByStorageId1(stStorageVo);
 			if(stvo.getStorageId()!=null&&!stvo.getStorageId().equals("")){
 				list.add(stvo);
     			super.writeJson(list);
 			}else{
 				Msg msg=new Msg();
 				msg.setMsg("noExist");
     			msg.setSuccess(false);
     			super.writeJson(msg);
 			}
		} catch (Exception e) {
			logger.error("点击上一条记录   异常", e);
		}
    }
	
	public StStorageVo getModel() {
		return stStorageVo;
	}
}
