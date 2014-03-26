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
import com.syuesoft.st.service.StRtGoodsService;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
/**
 * 退货单Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StRtGoodsAction")
public class StRtGoodsAction extends BaseAction implements ModelDriven<StRtGoodsVo> {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StRtGoodsVo stRtGoodsVo=new StRtGoodsVo();
	@Autowired StRtGoodsService stRtGoodsService;
	
	/**
	 * 退货单汇总信息   预加载/ 综合查询
	 */
	public void searchByCondition(){
		try {
			stRtGoodsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stRtGoodsService.searchByCondition(stRtGoodsVo));
		} catch (Exception e) {
			logger.error("退货单汇总信息    综合查询    异常", e);
		}
	}

	/**
	 * 入库单明细配件信息     综合查询
	 */
	public void searchStGoodsStorageByCondition(){
		try {
			super.writeJson(super.listConvertJson(stRtGoodsService.searchStGoodsStorageByStorageId(stRtGoodsVo)));
		} catch (Exception e) {
			logger.error("根据入库单号获取入库单明细信息   异常", e);
		}
	}
	
	/**
	 * 退货单模块   根据入库单号查询入库单信息
	 */
	public void searchByStorageId(){
		try {
			super.writeJson(super.listConvertJson(stRtGoodsService.searchstGoodsStorageByStorageId(stRtGoodsVo)));
		} catch (Exception e) {
			logger.error("退货单模块   根据入库单号查询入库单信息    异常", e);
		}
	}
	
	/**
	 * 根据供应商ID选择相应入库单信息
	 */
	public void searchStGoodsStorageByRelcampId(){
		try {
			List<StRtGoodsVo> list=new ArrayList<StRtGoodsVo>();
			stRtGoodsVo.setEnterpriseId(getNowEnterpriseId());
			List<StRtGoodsVo> rlist=stRtGoodsService.searchStGoodsStorageByRelcampId(stRtGoodsVo);//获取对应供应商下的入库单汇总信息
			if(rlist!=null&&rlist.size()>0){
				for (StRtGoodsVo srgvo:rlist) {
					srgvo.setEnterpriseId(getNowEnterpriseId());
					List<StRtGoodsVo> resultList=stRtGoodsService.searchStGoodsStorageByStorageId(srgvo);//刷选入库单明细入库数量不为零的入库汇总信息
					if(resultList.size()>0)
						list.add(srgvo);
				}
			}
			super.writeJson(super.listConvertJson(list));
		} catch (Exception e) {
			logger.error("根据供应商ID选择相应入库单信息    异常", e);
		}
	}
	
	/**
	 * 根据入库单号获取入库单明细信息
	 */
	public void searchStGoodsStorageByStorageId(){
		try {
			stRtGoodsVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(super.listConvertJson(stRtGoodsService.searchStGoodsStorageByStorageId(stRtGoodsVo)));
		} catch (Exception e) {
			logger.error("根据入库单号获取入库单明细信息   异常", e);
		}
	}
	
	/**
	 * 根据退货单编号获取退货单明细信息
	 */
	public void searchStRtGoodsDetailByStrtgmId()
	{
		try {
			super.writeJson(super.listConvertJson(stRtGoodsService.searchStRtGoodsDetailByStrtgmId(stRtGoodsVo)));
		} catch (Exception e) {
			logger.error("根据退货单编号获取退货单明细信息    异常", e);
		}
	}
	
	/**
	 * 退货单汇总单信息     添加
	 */
	public void addStRtGoodsMain(){
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stRtGoodsVo.setManagerId(user.getBasStuff().getStfId()+"");
		try {
			stRtGoodsVo.setEnterpriseId(getNowEnterpriseId());
			stRtGoodsService.add(stRtGoodsVo,stRtGoodsService.jsonChangeDetailList(stRtGoodsVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("退货单汇总单信息     添加  异常", e);
		}
	}
	
	/**
	 * 退货单汇总单信息       修改
	 */
	public void updateStRtGoodsMain(){
		try {
			stRtGoodsVo.setEnterpriseId(getNowEnterpriseId());
			stRtGoodsService.update(stRtGoodsVo,stRtGoodsService.jsonChangeDetailList(stRtGoodsVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("退货单汇总单信息       修改    异常", e);
		}
	}
	
	/**
	 * 退货单汇总单信息      删除
	 */
	public void deleteStRtGoodsMain(){
		try {
			stRtGoodsService.delete(stRtGoodsVo);
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("退货单汇总单信息       修改    异常", e);
		}
	}
	
	public StRtGoodsVo getModel() {
		return stRtGoodsVo;
	}
}
