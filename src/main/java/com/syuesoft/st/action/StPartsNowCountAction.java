package com.syuesoft.st.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.st.service.StGoodsStorageService;
import com.syuesoft.st.service.StPartsNowCountService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Msg;

@SuppressWarnings("serial")
@ParentPackage(value="basePackage")
@Action("StPartsNowCountAction")
public class StPartsNowCountAction extends BaseAction implements
ModelDriven<PartsNowCountVo>{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private PartsNowCountVo partsNowCountVo=new PartsNowCountVo();
	@Autowired StPartsNowCountService stPartsNowCountService;
	@Autowired StGoodsStorageService stGoodsStorageService;
	
	/**
	 * 库存量信息         综合查询
	 */
	public void searchByCondition(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.searchPartsNowCountByCondition(partsNowCountVo));
		} catch (Exception e) {
			logger.error("库存量信息         综合查询     异常", e);
		}
	}
	
	/**
	 * 配件汇总信息计算
	 */
	public void calcutePartsChangePrice(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.calcutePartsChangePrice(partsNowCountVo));
		} catch (Exception e) {
			logger.error("库存量信息         综合查询     异常", e);
		}
	}
	
	/**
	 * 配件品牌     预加载
	 */
	public void loadPartsBrand(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.loadPartsBrand(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件品牌     预加载    异常", e);
		}
	}

	/**
	 * 配件品牌         综合查询
	 */
	public void searchPartsBrandByCondition(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.serachPartsBrandByCondition(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件品牌         综合查询     异常", e);
		}
	}
	
	/**
	 * 配件名称     预加载
	 */
	public void loadPartsName(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.loadPartsName(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件名称     预加载    异常", e);
		}
	}
	
	/**
	 * 配件名称     综合查询
	 */
	public void searchPartsNameByCondition(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.searchPartsNameByCondition(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件名称     综合查询   异常", e);
		}
	}
	
	/**
	 * 配件型号    预加载
	 */
	public void loadPartsType(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.loadPartsType(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件型号    预加载     异常", e);
		}
	}
	
	/**
	 * 配件型号     综合查询
	 */
	public void searchPartsTypeByCondition(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.searchPartsTypeByCondition(partsNowCountVo));
		} catch (Exception e) {
			logger.error("配件型号     综合查询   异常", e);
		}
	}
	
	/**
	 * 配件部位    预加载
	 */
	public void posNameSearchByCondition(){
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPartsNowCountService.findAllPartsPosition(partsNowCountVo.getQ(),getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("配件部位    预加载    异常", e);
		}
	}
	
	/**
	 * 根据入库单号获取  入库明细配件 信息  
	 */
	public void loadPartsInfoByStorageId(){
		List<PartsNowCountVo> list=getSessionList();
		list.clear();
		try {
			partsNowCountVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			List<PartsNowCountVo> resultList=stGoodsStorageService.searchStStorageDetailByStorageId(partsNowCountVo);
			for (PartsNowCountVo partsNowCountVo : resultList) {
				list.add(partsNowCountVo);
			}
			super.writeJson(super.listConvertJson(list));
		} catch (Exception e) {
			logger.error("根据入库单号获取  入库明细配件 信息  异常", e);
		}
	}
	
	/**
	 * 获取datagrid变更后的值
	 */
	public void acceptChangesSelectedParts(){
		List<PartsNowCountVo> list = getSessionList();
		Msg msg = new Msg();
		List<PartsNowCountVo> acceptList = stPartsNowCountService.acceptChangesSelectedItem(partsNowCountVo);
		if(acceptList!=null)
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < acceptList.size(); j++) {
				if(list.get(i).getPartsId().equals(acceptList.get(j).getPartsId())
						&&list.get(i).getStoreName().equals(acceptList.get(j).getStoreName())){
					PartsNowCountVo partsNowCountVo=acceptList.get(j);
					list.set(i, partsNowCountVo);
				}
			}
		}
		msg.setSuccess(true);
		msg.setMsg("success");
		super.writeJson(msg);
	}
	
	/**
	 * 入库单明细配件价格     批量修改
	 */
	public void updateStRtGoodsDetailPrice() {
		List<PartsNowCountVo> list=getSessionList();
		try {
			stPartsNowCountService.updateList(list);
			Msg msg = new Msg();
			msg.setMsg("success");
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("入库单明细配件价格     批量修改  异常");
		}
	}
	
	/**
	 * 获取session中存放的退货单明细信息list集合
	 */
	@SuppressWarnings("unchecked")
	public List<PartsNowCountVo> getSessionList()
	{
		HttpSession session = super.getRequest().getSession();
		List<PartsNowCountVo> list = (List<PartsNowCountVo>) session.getAttribute("partsNowCountList");
		if (list == null) {
			list = new ArrayList<PartsNowCountVo>();
			session.setAttribute("partsNowCountList", list);
		}
		return list;
	}
	
	public void clearSession(){
		List<PartsNowCountVo> list=getSessionList();
		list.clear();
		super.writeJson(super.listConvertJson(list));
	}
	
	
	/**
	 * 配件价格    修改
	 */
	public void  updatePartsChangePrice(){
		try {
			stPartsNowCountService.update(partsNowCountVo);
			Msg msg = new Msg();
			msg.setMsg("success");
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("配件价格    修改   异常", e);
		}
	}
	
	/**
	 * 配件调价表   清空
	 */
	public void clearPratsChangePrice(){
		try {
			stPartsNowCountService.clearPratsChangePrice();
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("配件调价表     清空  异常", e);
		}
	}
	
	public PartsNowCountVo getModel() {
		return partsNowCountVo;
	}
}
