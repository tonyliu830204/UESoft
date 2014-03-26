package com.syuesoft.st.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasChilddictionaryDao;
import com.syuesoft.bas.dao.BasMountingsTypeInfoDAO;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.model.BasPartsUnit;
import com.syuesoft.model.FrtParts;
import com.syuesoft.model.StPurOrder;
import com.syuesoft.model.StPurOrderitem;
import com.syuesoft.st.dao.BasPartsTypeDAO;
import com.syuesoft.st.dao.BasRelationCampanyDAO;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.dao.StPurOrderDAO;
import com.syuesoft.st.dao.StPurOrderitemDAO;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.service.StPurOrderService;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.util.Utils;
/**
 * 采购单管理Service接口实现类
 * @author SuMing
 */
@Service("stPurOrderService")
public class StPurOrderServiceImpl extends BaseServiceImpl implements
		StPurOrderService {

	@Autowired StPurOrderDAO stPurOrderDAO;          // 采购单汇总DAO
	@Autowired StPurOrderitemDAO stPurOrderitemDAO;  // 采购单明细DAO
	@Autowired BasRelationCampanyDAO basRelationCampanyDAO; // 相关单位DAO
	@Autowired FrtPartsDAO frtPartsDAO;              // 配件档案表DAO
	@Autowired BasStuffDao basStuffDao;              // 员工表DAO
	@Autowired StGoodsStorageDAO stGoodsStorageDAO;
	@Autowired BasChilddictionaryDao basChilddictionaryDaoI;
	@Autowired BasMountingsTypeInfoDAO basMountingsTypeInfoDAO;
	@Autowired BasPartsUnitDAO basPartsUnitDAO;
	@Autowired StSellOrderDAO stSellOrderDAO;
	@Autowired BasPartsTypeDAO basPartsTypeDAO;
	
	/**
	 * 采购员查询
	 */
	public Json loadPurOrderMember(StPurOrderVo spoVo) throws Exception {
		StringBuffer sb=new StringBuffer("select bs.stf_id,bs.stf_name from bas_stuff bs where bs.enterprise_id="+spoVo.getEnterpriseId()+" and bs.del_tag='n' " +
				" and bs.stf_id AND bs.stf_id IN (SELECT DISTINCT bsj.stf_id FROM bas_stuff_job bsj WHERE bsj.job_pro_id="+Contstants.BASJOBPROPERTY.JOBPROPERTY7+")");
		if(spoVo.getStfId()!=null&&spoVo.getStfId().length()>0)
			sb.append(" and bs.stf_id="+spoVo.getStfId());
		if(spoVo.getStfName()!=null&&spoVo.getStfName().length()>0)
			sb.append(" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(spoVo.getStfName().trim())+"%'");
		List<StSellOrderVo> rows=stSellOrderDAO.findPartsStockPerson(spoVo.getPage(),spoVo.getRows(), sb.toString());
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(basStuffDao.getSQLCount(sb.toString(),null));
		return json;
	}
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StPurOrderVo> jsonChangeDetailList(StPurOrderVo stPurOrderVo){
		List<StPurOrderVo> list =new ArrayList<StPurOrderVo>();
		if(stPurOrderVo.getDetailData()!=null&&!stPurOrderVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stPurOrderVo.getDetailData());
			if(jsDetailData!=null&&!jsDetailData.equals("")){
				List<StPurOrderVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StPurOrderVo.class);
				if(resultList!=null&&resultList.size() > 0){
					for (StPurOrderVo spovo : resultList) {
						list.add(spovo);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 采购单汇总信息     综合查询
	 */
	public Json searchByCondition(StPurOrderVo spoVo)throws Exception {
		return stPurOrderDAO.searchByCondition(spoVo.getOrderDateStart(),spoVo.getOrderDateEnd(),
				spoVo.getDeliveryDateStart(),spoVo.getDeliveryDateEnd(),spoVo.getOrderId(), spoVo.getRelcampName(),
				spoVo.getTransitToState(),spoVo.getStorageId(),spoVo.getClassification(),spoVo.getExamine(),spoVo.getPaid(),"StPurOrder",spoVo.getEnterpriseId());
	}
	
	/**
	 * 配件信息      预加载
	 */
	public Json searchByPartsIdAndPartsName(StPurOrderVo stPurOrderVo) throws Exception{
		return frtPartsDAO.searchByPartsIdAndPartsName(stPurOrderVo.getPartsId(),stPurOrderVo.getPartsId2(), stPurOrderVo.getPartsName(),stPurOrderVo.getPbrdId(),stPurOrderVo.getPtypeName(),stPurOrderVo.getSort() , stPurOrderVo.getOrder(),stPurOrderVo.getEnterpriseId());
	}
	
	/**
	 * 点击采购单汇总记录获取采购单明细信息    预加载
	 */
	public List<StPurOrderVo> searchStOrderItemByOrderId(String orderId)throws Exception {
		return stPurOrderitemDAO.searchStOrderItemByOrderId(orderId);
	}
	
	/**
	 * 采购单汇总，明细信息   新增
	 */
	@Log(moduleName = "采购单管理", opertype = "新增采购单", content = "采购单管理-->新增采购单")
	public void add(StPurOrderVo spovo, List<StPurOrderVo> list)throws Exception {
	    StPurOrder spo = new StPurOrder();
		spo.setOrderId(CreateID.createId("StPurOrder", "CG")); // 获取采购单编号
		spo=copyMainProperty(spo,spovo);
		stPurOrderDAO.save(spo); //采购单汇总保存
		for (StPurOrderVo stPurOrderVo: list) {
			StPurOrderitem spoi = new StPurOrderitem();
			spoi=copyDetailProperty(spoi, stPurOrderVo);
			spoi.setStPurOrder(spo);
			stPurOrderitemDAO.save(spoi); // 保存采购单明细
		}	
	}

	/**
	 * 采购单汇总，明细信息     删除
	 */
	@Log(moduleName = "采购单管理", opertype = "删除采购单", content = "采购单管理-->删除采购单")
	public void delete(StPurOrderVo stPurOrderVo) throws Exception{
		List<StPurOrderitem> resultList = stPurOrderitemDAO.find(" from StPurOrderitem spoi where spoi.stPurOrder.orderId='"+stPurOrderVo.getOrderId()+"'");
		if(resultList!=null&&resultList.size()>0)
			for(StPurOrderitem spoi:resultList){
				stPurOrderitemDAO.delete(spoi); // 删除明细先关联记录
			}
		StPurOrder spo = stPurOrderDAO.get("from StPurOrder spo where spo.orderId='"+stPurOrderVo.getOrderId()+"'");
		stPurOrderDAO.delete(spo); // 删除汇总记录
	}

	/**
	 * 采购单汇总，明细信息    修改
	 */
	@Log(moduleName = "采购单管理", opertype = "修改采购单", content = "采购单管理-->修改采购单")
	public void update(StPurOrderVo spovo,List<StPurOrderVo> list) throws Exception {
		// 1：首先修改采购单汇总信息
		StPurOrder spo = stPurOrderDAO.get("from StPurOrder spo where spo.orderId='"+spovo.getOrderId()+"'");
		spo=copyMainProperty(spo,spovo);
		stPurOrderDAO.merge(spo);
		// 2:修改采购单明细相关记录
		List<StPurOrderitem> resultList = stPurOrderitemDAO.find(" from StPurOrderitem spoi where spoi.stPurOrder.orderId='"+spovo.getOrderId()+"'");
		if(resultList!=null&&resultList.size()>0)
			for (StPurOrderitem spoi:resultList) {
				stPurOrderitemDAO.delete(spoi);// A:首先清空原有采购单明细记录
			}
		for (StPurOrderVo stPurOrderVo:list) {// B:将list集合中数据新添加到原有数据库中
			StPurOrderitem spoi = new StPurOrderitem();
			spoi=copyDetailProperty(spoi, stPurOrderVo);
			spoi.setStPurOrder(spo);
			stPurOrderitemDAO.save(spoi); // 保存采购单明细
		}
	}
	
	/**
	 * 修改采购单审核状态
	 */
	public void updateExamine(String orderId) throws Exception {
		StPurOrder stPurOrder = stPurOrderDAO.get(" from StPurOrder spo where spo.orderId='"+orderId+"'");
		if(stPurOrder.getExamine()!=null&&!stPurOrder.getExamine().equals("")){
			String examine=stPurOrder.getExamine();
			if(examine.equals(Contstants.AUDIT_TAG.AUDITYESS))
				stPurOrder.setExamine(Contstants.AUDIT_TAG.AUDITNOS);
			else if(examine.equals(Contstants.AUDIT_TAG.AUDITNOS))
				stPurOrder.setExamine(Contstants.AUDIT_TAG.AUDITYESS);
			stPurOrderDAO.merge(stPurOrder);
		}
	}
	
	public StPurOrder copyMainProperty(StPurOrder spo,StPurOrderVo spovo)throws Exception{
		if(spovo.getRelcampId()!=null&&!spovo.getRelcampId().trim().equals(""))
			spo.setBasRelationCampany(basRelationCampanyDAO.get(" from BasRelationCampany brc where brc.relcampId="+spovo.getRelcampId())); // 获取单位编号
		if(spovo.getManager()!=null&&!spovo.getManager().trim().equals(""))
		   spo.setManager(Short.parseShort(spovo.getManager().trim())); // 获取经办人ID
		if(spovo.getBuyer()!=null&&!spovo.getBuyer().trim().equals(""))
		   spo.setBuyer(Short.parseShort(spovo.getBuyer().trim())); // 获取采购员ID
		if(spovo.getOrderDate()!=null&&!spovo.getOrderDate().trim().equals(""))
		   spo.setOrderDate(Utils.dateFormat(spovo.getOrderDate().trim())); // 获取订货时间
		if(spovo.getOrderDate()!=null&&!spovo.getOrderDate().trim().equals(""))
		   spo.setDeliveryDate(Utils.dateFormat(spovo.getOrderDate().trim())); // 获取交货时间
		if(spovo.getNumTotal()!=null&&!spovo.getNumTotal().trim().equals(""))
		   spo.setNumTotal(Double.parseDouble(spovo.getNumTotal().trim())); // 获取采购数量
		if(spovo.getTotalAmount()!=null&&!spovo.getTotalAmount().trim().equals(""))
		   spo.setTotalAmount(Double.parseDouble(spovo.getTotalAmount().trim())); // 获取采购金额
		if(spovo.getOrderRemark()!=null&&!spovo.getOrderRemark().trim().equals(""))
		   spo.setOrderRemark(spovo.getOrderRemark().trim()); // 获取采购订单备注
		if(spovo.getNotesType()!=null&&!spovo.getNotesType().trim().equals(""))
		   spo.setNotesType(spovo.getNotesType().trim()); // 获取票据类型
		if(spovo.getClassification()!=null&&!spovo.getClassification().trim().equals(""))
			spo.setClassification(spovo.getClassification().trim()); // 获取订单类型
		spo.setExamine(Contstants.AUDIT_TAG.AUDITNOS); // 审核状态为未审核状态
		spo.setPaid(Contstants.PAYMENTSTATE.UNPAID);//付款状态为未付款
		spo.setTransitToState(Contstants.TRANSITTOSTATE.ONPASSAGE);//在途状态
		if(spovo.getTaxRate()!=null&&!spovo.getTaxRate().trim().equals("")){
			double taxRate=Double.parseDouble(spovo.getTaxRate().trim())/100;
			spo.setTaxRate(taxRate); // 获取税率                                         
		}else
			spo.setTaxRate(0.17); // 获取税率        
		if(spovo.getTaxCount()!=null&&!spovo.getTaxCount().trim().equals(""))
		   spo.setTaxCount(Double.parseDouble(spovo.getTaxCount().trim())); // 获取税额
		if(spovo.getNotaxTotalamont()!=null&&!spovo.getNotaxTotalamont().trim().equals(""))
		   spo.setNotaxTotalamont(Double.parseDouble(spovo.getNotaxTotalamont().trim())); // 获取未税额
		spo.setEnterpriseId(spovo.getEnterpriseId());
		return spo;
	}
	
	public StPurOrderitem copyDetailProperty(StPurOrderitem spoi,StPurOrderVo stPurOrderVo){
		if(stPurOrderVo.getPartsId()!=null&&!stPurOrderVo.getPartsId().trim().equals(""))
		   spoi.setPartsId(stPurOrderVo.getPartsId().trim()); 
		if(stPurOrderVo.getPartsNum()!=null&&!stPurOrderVo.getPartsNum().trim().equals(""))
		   spoi.setPartsCount(Double.parseDouble(stPurOrderVo.getPartsNum().trim()));
		if(stPurOrderVo.getNotaxPrice()!=null&&!stPurOrderVo.getNotaxPrice().trim().equals(""))
		   spoi.setNotaxPrice(Double.parseDouble(stPurOrderVo.getNotaxPrice().trim()));
		if(stPurOrderVo.getNotaxTotalamont()!=null&&!stPurOrderVo.getNotaxTotalamont().trim().equals(""))
		   spoi.setNotaxTotalamont(Double.parseDouble(stPurOrderVo.getNotaxTotalamont().trim()));
		if(stPurOrderVo.getTaxPrice()!=null&&!stPurOrderVo.getTaxPrice().trim().equals(""))
		   spoi.setTaxPrice(Double.parseDouble(stPurOrderVo.getTaxPrice().trim()));
		if(stPurOrderVo.getTaxTotalamont()!=null&&!stPurOrderVo.getTaxTotalamont().trim().equals(""))
		   spoi.setTaxTotalamont(Double.parseDouble(stPurOrderVo.getTaxTotalamont().trim()));
		if(stPurOrderVo.getItemRemark()!=null&&!stPurOrderVo.getItemRemark().trim().equals(""))
			   spoi.setItemRemark(stPurOrderVo.getItemRemark().trim());
		return spoi;
	}	
	/**
	 * 采购单汇总，明细信息   导入
	 */
	@Log(moduleName = "采购单管理", opertype = "采购单导入", content = "采购单管理-->采购单导入")
	public Msg addItem(StPurOrderVo spovo, List<StPurOrderVo> list)throws Exception {
		Msg msg=new Msg();
	    StPurOrder spo = new StPurOrder();
		spo.setOrderId(CreateID.createId("StPurOrder", "CG")); // 获取采购单编号
		spo=copyMainProperty(spo,spovo);
		stPurOrderDAO.save(spo); //采购单汇总保存
		Serializable s=null;
		for (StPurOrderVo stPurOrderVo: list) {
			String partId=stPurOrderVo.getPartsId();
			FrtParts p=frtPartsDAO.get(FrtParts.class,partId);
			//保存配件档案
			if(p==null){
				FrtParts frtpart=new FrtParts();
				String partsModel=stPurOrderVo.getPtypeName();
				if(partsModel!=null && !("".equals(partsModel))){
					BasPartsType partType=basMountingsTypeInfoDAO.findPartsByType(partsModel);
					if(partType!=null){
						frtpart.setBasPartsType(partType);
					}else{
						  msg.setSuccess(false);
                          msg.setMsg("系统找不到配件型号信息，请确认是否已经录入了型号为【"+stPurOrderVo.getPtypeName()+"】的型号信息");
                          break;
					}
						
				}
				frtpart.setPartsName(stPurOrderVo.getPartsName());
				if(stPurOrderVo.getPunitName()!=null && !("".equals(stPurOrderVo.getPunitName()))){
					BasPartsUnit unit=basPartsUnitDAO.findByName(stPurOrderVo.getPunitName());
					if(unit!=null){
						frtpart.setPunitName(unit.getPunitId().toString());
					}else{
						frtpart.setPunitName(Contstants.DEFAULTPARTSPROPERTY.PARTSUNIT.toString());
					}
				}
				frtpart.setPartsFlag(true);
				frtpart.setPartsId(partId);
				frtpart.setEnterpriseId(spovo.getEnterpriseId());
				s=frtPartsDAO.save(frtpart);
			}
			StPurOrderitem spoi = new StPurOrderitem();
			spoi=copyDetailProperty(spoi, stPurOrderVo);
			if(p==null)
				spoi.setPartsId(s.toString());
			else
				spoi.setPartsId(p.getPartsId());
			spoi.setStPurOrder(spo);
			stPurOrderitemDAO.save(spoi); // 保存采购单明细
		}
			msg.setSuccess(true);
			msg.setMsg("采购单导入成功!");
			return msg;	
	}
	
}
