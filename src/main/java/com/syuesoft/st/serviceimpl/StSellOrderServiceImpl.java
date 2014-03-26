package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.model.FrtParts;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.model.FrtPreParts;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StRtPartsDetail;
import com.syuesoft.model.StSellOrder;
import com.syuesoft.model.StSellOrderitem;
import com.syuesoft.st.dao.FrtCarDAO;
import com.syuesoft.st.dao.FrtCustomDAO;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StRtPartsDetailDAO;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.dao.StSellOrderitemDAO;
import com.syuesoft.st.service.StSellOrderService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;
/**
 * 销售单管理Service实现类
 * @author SuMing
 */
@Service("stSellOrderService")
public class StSellOrderServiceImpl extends BaseServiceImpl implements StSellOrderService {

	@Autowired PartsChangePriceDAO partsChangePriceDAO;
	@Autowired FrtCustomDAO frtCustomDAO;
	@Autowired BasStuffDao basStuffDao;
	@Autowired FrtCarDAO frtCarDAO;
	@Autowired StSellOrderDAO stSellOrderDAO;
	@Autowired StSellOrderitemDAO stSellOrderitemDAO;
	@Autowired BasStorehouseDAO basStorehouseDAO;
	@Autowired FrtPartsDAO frtPartsDAO;
	@Autowired FrtPreClearingDao frtPreClearingDao;
    @Autowired StRtPartsDetailDAO stRtPartsDetailDAO;
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StSellOrderVo> jsonChangeDetailList(StSellOrderVo stSellOrderVo){
		List<StSellOrderVo> list =new ArrayList<StSellOrderVo>();
		if(stSellOrderVo.getDetailData()!=null&&!stSellOrderVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stSellOrderVo.getDetailData());
			List<StSellOrderVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StSellOrderVo.class);
			if(resultList.size() > 0){
				for (StSellOrderVo ssovo : resultList) {
					list.add(ssovo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 销售单汇总信息   综合查询
	 */
	public Json searchByCondition(StSellOrderVo stSellOrderVo)throws Exception{
		return stSellOrderDAO.searchByCondition(stSellOrderVo.getPage(), stSellOrderVo.getRows(), stSellOrderVo.getSort(), stSellOrderVo.getOrder(),stSellOrderVo.getSellmmDateStart(),stSellOrderVo.getSellmmDateEnd(),stSellOrderVo.getCarLicense(),stSellOrderVo.getSellmmId(),stSellOrderVo.getCustomName(),stSellOrderVo.getSellmmRemark(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 车牌照信息            预加载
	 */
	public Json loadCarLicense(StSellOrderVo stSellOrderVo) throws Exception {
		return frtCarDAO.loadCarLicense(stSellOrderVo.getPage(), stSellOrderVo.getRows(), stSellOrderVo.getSort(), stSellOrderVo.getOrder(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 车牌照信息          综合查询
	 */
	public Json searchCarLicenseByCondition(StSellOrderVo stSellOrderVo) throws Exception {
		return frtCarDAO.searchCarLicenseByCondition(stSellOrderVo.getCarLicense(),stSellOrderVo.getCarVin(),stSellOrderVo.getCarMotorId(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 客户信息    预加载
	 */
	public Json loadBasCustom(StSellOrderVo stSellOrderVo) throws Exception {
		return frtCustomDAO.loadBasCustom(stSellOrderVo.getPage(), stSellOrderVo.getRows(), stSellOrderVo.getSort(), stSellOrderVo.getOrder(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 客户信息     综合查询
	 */
	public Json searchBasCustomByCondition(StSellOrderVo stSellOrderVo) throws Exception {
		return frtCustomDAO.searchBasCustomByCondition(stSellOrderVo.getCustomId(), stSellOrderVo.getCustomName(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 领料员查询
	 */
	public Json loadPickingMember(StSellOrderVo stSellOrderVo) throws Exception {
		StringBuffer sb=new StringBuffer("select bs.stf_id,bs.stf_name from bas_stuff bs where bs.enterprise_id="+stSellOrderVo.getEnterpriseId()+" and bs.del_tag='n' " +
				" and bs.stf_id AND bs.stf_id IN (SELECT DISTINCT bsj.stf_id FROM bas_stuff_job bsj WHERE bsj.job_pro_id="+Contstants.BASJOBPROPERTY.JOBPROPERTY8+")");
		if(stSellOrderVo.getStfId()!=null&&stSellOrderVo.getStfId().length()>0)
			sb.append(" and bs.stf_id="+stSellOrderVo.getStfId());
		if(stSellOrderVo.getStfName()!=null&&stSellOrderVo.getStfName().length()>0)
			sb.append(" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(stSellOrderVo.getStfName().trim())+"%'");
		List<StSellOrderVo> rows=stSellOrderDAO.findPartsStockPerson(stSellOrderVo.getPage(),stSellOrderVo.getRows(), sb.toString());
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(basStuffDao.getSQLCount(sb.toString(),null));
		return json;
	}
	
	/**
	 * 退料员查询
	 * */
	
	public Json findPartsStorehousePerson(StSellOrderVo stSellOrderVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("select bs.stf_id,bs.stf_name from bas_stuff bs where bs.enterprise_id="+stSellOrderVo.getEnterpriseId()+" and bs.del_tag='n' " +
				" and bs.stf_id AND bs.stf_id IN (SELECT DISTINCT bsj.stf_id FROM bas_stuff_job bsj WHERE bsj.job_pro_id="+Contstants.BASJOBPROPERTY.JOBPROPERTY4+")");
		if(stSellOrderVo.getStfId()!=null&&stSellOrderVo.getStfId().length()>0)
			sb.append(" and bs.stf_id="+stSellOrderVo.getStfId());
		if(stSellOrderVo.getStfName()!=null&&stSellOrderVo.getStfName().length()>0)
			sb.append(" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(stSellOrderVo.getStfName().trim())+"%'");
		List<StSellOrderVo> rows=stSellOrderDAO.findPartsStockPerson(stSellOrderVo.getPage(),stSellOrderVo.getRows(), sb.toString());
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(basStuffDao.getSQLCount(sb.toString(),null));
		return json;
	}

	/**
	 * 采购员查询
	 * */
	
	public Json findPartsStockPerson(StSellOrderVo stSellOrderVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("select bs.stf_id,bs.stf_name from bas_stuff bs where bs.enterprise_id="+stSellOrderVo.getEnterpriseId()+" and bs.del_tag='n' " +
				" and bs.stf_id AND bs.stf_id IN (SELECT DISTINCT bsj.stf_id FROM bas_stuff_job bsj WHERE bsj.job_pro_id="+Contstants.BASJOBPROPERTY.JOBPROPERTY7+")");
		if(stSellOrderVo.getStfId()!=null&&stSellOrderVo.getStfId().length()>0)
			sb.append(" and bs.stf_id="+stSellOrderVo.getStfId());
		if(stSellOrderVo.getStfName()!=null&&stSellOrderVo.getStfName().length()>0)
			sb.append(" and bs.stf_name like '%"+StringEscapeUtils.escapeSql(stSellOrderVo.getStfName().trim())+"%'");
		List<StSellOrderVo> rows=stSellOrderDAO.findPartsStockPerson(stSellOrderVo.getPage(),stSellOrderVo.getRows(), sb.toString());
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(basStuffDao.getSQLCount(sb.toString(),null));
		return json;
	}

	/**
	 * 领料员信息        综合查询
	 */
	public Json searchPickingMemberByCondition(StSellOrderVo stSellOrderVo) throws Exception {
		return basStuffDao.searchPickingMemberByCondition(stSellOrderVo.getStfId(),stSellOrderVo.getStfName(),stSellOrderVo.getEnterpriseId());
	}
	
	/**
	 * 双击销售汇总单号获取相关销售明细信息
	 */
	public Json loadStSellOrderItemBySellmmId(StSellOrderVo stSellOrderVo ) throws Exception{
		return stSellOrderitemDAO.loadStSellOrderItemBySellmmId(stSellOrderVo.getSellmmId());
	}

	/**
	 * 销售单管理模块  （ 配件信息）     预加载
	 */
	public Json loadFrtParts(StSellOrderVo sovo)throws Exception {
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		List<PartsNowCountVo> resultList =frtPartsDAO.searchPartsNowCountByCondition(0,0,null,null,sovo.getPartsId(), sovo.getPartsName(), null, null, null, null, null, sovo.getStoreId(), null, null, null, null, null, null, null,sovo.getEnterpriseId());
		if (resultList != null&&resultList.size()>0) {
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo stSellOrderVo = new StSellOrderVo();
				PartsNowCountVo pncvo=resultList.get(i);
				sovo.setPriceType(new BaseAction().transCoding(sovo.getPriceType()));
				if (pncvo.getChangePriceId()!= null && !"".equals(pncvo.getChangePriceId())) {
					stSellOrderVo.setChangePriceId(pncvo.getChangePriceId());
				}
				if (pncvo.getPartsId()!= null && !"".equals(pncvo.getPartsId())) {
					stSellOrderVo.setPartsId(pncvo.getPartsId());
				}
				if (pncvo.getPartsName()!= null && !"".equals(pncvo.getPartsName())) {
					stSellOrderVo.setPartsName(pncvo.getPartsName());
				}
				if (pncvo.getPunitName() != null && !"".equals(pncvo.getPunitName())) {
					stSellOrderVo.setPunitName(pncvo.getPunitName());
				}
				if (pncvo.getFitPtype() != null && !"".equals(pncvo.getFitPtype())) {
					stSellOrderVo.setFitPtype(pncvo.getFitPtype());
				}
				if (pncvo.getPartsLatestTaxprice() != null && !"".equals(pncvo.getPartsLatestTaxprice())) {
					stSellOrderVo.setTaxCostPrice(pncvo.getPartsLatestTaxprice());
				}
				if (pncvo.getPartsLibrary() != null && !"".equals(pncvo.getPartsLibrary())) {
					stSellOrderVo.setPartsLibrary(pncvo.getPartsLibrary());
				}
				if (pncvo.getPartsNowCount() != null && !"".equals(pncvo.getPartsNowCount())) {
					stSellOrderVo.setPartsNowCount(pncvo.getPartsNowCount1());
				}
				if (pncvo.getStoreName() != null && !"".equals(pncvo.getStoreName())) {
					stSellOrderVo.setStoreName(pncvo.getStoreName());
				}
				if (pncvo.getStoreId() != null && !"".equals(pncvo.getStoreId())) {
					stSellOrderVo.setStoreId(pncvo.getStoreId());
				}
				if (pncvo.getFitPtype() != null && !"".equals(pncvo.getFitPtype())) {
					stSellOrderVo.setFitPtype(pncvo.getFitPtype());
				}
				if (sovo.getPriceType().equals(Contstants.SELLPRICETYPE.SERVICEPRICE)) {
					if (pncvo.getPartsRepairPrice() != null && !"".equals(pncvo.getPartsRepairPrice())) {
						stSellOrderVo.setSelldPrice(pncvo.getPartsRepairPrice());
					}
				} else if (sovo.getPriceType().equals(Contstants.SELLPRICETYPE.POINTPRICETYPE)) {
					if (pncvo.getPartsPointPrice() != null && !"".equals(pncvo.getPartsPointPrice())) {
						stSellOrderVo.setSelldPrice(pncvo.getPartsPointPrice());
					}
				} else if (sovo.getPriceType().equals(Contstants.SELLPRICETYPE.SPECIALPRICETYPE)) {
					if (pncvo.getPartsSpecialPrice() != null && !"".equals(pncvo.getPartsSpecialPrice())) {
						stSellOrderVo.setSelldPrice(pncvo.getPartsSpecialPrice());
					}
				} else if (sovo.getPriceType().equals(Contstants.SELLPRICETYPE.SELLPRICE)
						|| sovo.getPriceType() == null
						|| sovo.getPriceType().equals("")) {
					if (pncvo.getPartsSellPrice() != null && !"".equals(pncvo.getPartsSellPrice())) {// 选择销售价或没有选择价格分类时，默认为销售价
						stSellOrderVo.setSelldPrice(pncvo.getPartsSellPrice());
					}
				}
				if (stSellOrderVo.getPartsNowCount() != null
						&& Double.parseDouble(stSellOrderVo.getPartsNowCount()) > 0) {
					list.add(stSellOrderVo);
				}
			}
		}
		Json json = new Json();
		json.setTotal(list.size());
		json.setRows(list);
		return json;
	}
	
	/**
	 * 销售单汇总，明细添加
	 */
    @Log(moduleName = "销售单管理", opertype = "新增销售单", content = "销售单管理-->新增销售单")
	public void add(StSellOrderVo stSellOrderVo,List<StSellOrderVo> list) throws Exception {
		StSellOrder sso=new StSellOrder();
		sso.setSellmmId(CreateID.createId("StSellOrder", Contstants.STSELLORDER));
		sso=copyMainProperty(stSellOrderVo, sso);
		stSellOrderDAO.save(sso);//保存销售汇总信息
		if(list!=null&&list.size()>0)//保存销售明细信息
			for (StSellOrderVo ssvo:list) {
				StSellOrderitem ssoi=new StSellOrderitem();
				ssoi.setStSellOrder(sso);
				ssoi=copyDetailProperty(ssvo, ssoi);
				stSellOrderitemDAO.save(ssoi);//保存销售单明细信息	
				changPartsNowCount(ssoi, false);
			}
	}
	
	/**
	 * 删除销售汇总单信息
	 */
    @Log(moduleName = "销售单管理", opertype = "删除销售单", content = "销售单管理-->删除销售单")
	public void delete(StSellOrderVo stSellOrderVo) throws Exception
	{
		List<StSellOrderitem> list=stSellOrderitemDAO.find(" from StSellOrderitem sso where sso.stSellOrder.sellmmId='"+stSellOrderVo.getSellmmId()+"'");
		if(list!=null&&list.size()>0)
			for (StSellOrderitem stSellOrderitem:list) {
				stSellOrderitemDAO.delete(stSellOrderitem);//删除销售单明细相关信息
				changPartsNowCount(stSellOrderitem, true);
			}
		StSellOrder stSellOrder=stSellOrderDAO.get(" from StSellOrder ssp where ssp.sellmmId='"+stSellOrderVo.getSellmmId()+"'");
		if(stSellOrder!=null&&!stSellOrder.equals(""))
		   stSellOrderDAO.delete(stSellOrder);
	}

	/**
	 * 销售单汇总，销售单明细修改
	 */
    @Log(moduleName = "销售单管理", opertype = "修改销售单", content = "销售单管理-->修改销售单")
	public void update(StSellOrderVo stSellOrderVo,List<StSellOrderVo> list)throws Exception
	{
		List<StSellOrderitem> resultList=stSellOrderitemDAO.find(" from StSellOrderitem sso where sso.stSellOrder.sellmmId='"+stSellOrderVo.getSellmmId()+"'");
		if(resultList!=null&&resultList.size()>0){
			for (StSellOrderitem stSellOrderitem:resultList) {
				stSellOrderitemDAO.delete(stSellOrderitem);//删除原有配件信息
				changPartsNowCount(stSellOrderitem, true);
			}
		}
		StSellOrder sso=stSellOrderDAO.get(" from StSellOrder ssp where ssp.sellmmId='"+stSellOrderVo.getSellmmId()+"'");
		if(sso!=null&&!sso.equals("")){
			sso=copyMainProperty(stSellOrderVo, sso);
			stSellOrderDAO.merge(sso);//修改销售单汇总
		}
		if(list!=null&&list.size()>0)
			for (StSellOrderVo ssvo:list) {
				StSellOrderitem ssoi=new StSellOrderitem();
				ssoi.setStSellOrder(sso);
				ssoi=copyDetailProperty(ssvo, ssoi);
				stSellOrderitemDAO.save(ssoi);//保存销售单明细信息	
				changPartsNowCount(ssoi, false);
			}
	}
	
	public StSellOrder copyMainProperty(StSellOrderVo stSellOrderVo,StSellOrder sso) {
		if (stSellOrderVo.getSellmmDate() != null&& !stSellOrderVo.getSellmmDate().trim().equals(""))
			sso.setSellmmDate(Utils.dateFormat(stSellOrderVo.getSellmmDate()));
		if (stSellOrderVo.getSellcustomId() != null&& !stSellOrderVo.getSellcustomId().trim().equals(""))
			sso.setSellcustomId(stSellOrderVo.getSellcustomId().trim());
		if (stSellOrderVo.getSellmmCnt() != null&& !stSellOrderVo.getSellmmCnt().trim().equals(""))
			sso.setSellmmCnt(Double.parseDouble(stSellOrderVo.getSellmmCnt().trim()));
		if (stSellOrderVo.getSellmmSumAmount() != null&& !stSellOrderVo.getSellmmSumAmount().trim().equals(""))
			sso.setSellmmSumAmount(Double.parseDouble(stSellOrderVo.getSellmmSumAmount().trim()));
		if (stSellOrderVo.getSellmmSumCost() != null&& !stSellOrderVo.getSellmmSumCost().trim().equals(""))
			sso.setSellmmSumCost(Double.parseDouble(stSellOrderVo.getSellmmSumCost().trim()));
		sso.setSellmmClearingStatus("未付款"); // 结算状态 临时
		if (stSellOrderVo.getSellmmStfId() != null&& !stSellOrderVo.getSellmmStfId().trim().equals(""))
			sso.setSellmmStfId(Short.parseShort(stSellOrderVo.getSellmmStfId().trim()));
		if (stSellOrderVo.getSellmmRemark() != null&& !stSellOrderVo.getSellmmRemark().trim().equals(""))
			sso.setSellmmRemark(stSellOrderVo.getSellmmRemark().trim());
		if (stSellOrderVo.getCarLicense() != null&& !stSellOrderVo.getCarLicense().trim().equals(""))
			sso.setCarLicense(stSellOrderVo.getCarLicense().trim());
		if (stSellOrderVo.getBillType() != null&& !stSellOrderVo.getBillType().trim().equals(""))
			sso.setBillType(stSellOrderVo.getBillType().trim());
		if (stSellOrderVo.getPsellId() != null&& !stSellOrderVo.getPsellId().trim().equals(""))
			sso.setPsellId(Short.parseShort(stSellOrderVo.getPsellId().trim()));
		if (stSellOrderVo.getPriceType() != null&& !stSellOrderVo.getPriceType().trim().equals(""))
			sso.setPriceType(stSellOrderVo.getPriceType().trim());
		if (stSellOrderVo.getSellManager() != null&& !stSellOrderVo.getSellManager().trim().equals(""))
			sso.setSellManager(Short.parseShort(stSellOrderVo.getSellManager().trim()));
		if (stSellOrderVo.getPreclrInoiceType() != null&& !stSellOrderVo.getPreclrInoiceType().trim().equals(""))
			sso.setPreclrInoiceType(stSellOrderVo.getPreclrInoiceType() .trim());
		if (stSellOrderVo.getPriceQuotiety() != null&& !stSellOrderVo.getPriceQuotiety().trim().equals(""))
			if(isNum(stSellOrderVo.getPriceQuotiety().trim())&&Double.parseDouble(stSellOrderVo.getPriceQuotiety().trim())>0)
				sso.setPriceQuotiety(Double.parseDouble(stSellOrderVo.getPriceQuotiety().trim()));
		sso.setEnterpriseId(stSellOrderVo.getEnterpriseId());
		sso.setPreclrState("未结算"); //结算状态
		return sso;
	}
	
	public StSellOrderitem copyDetailProperty(StSellOrderVo ssvo,StSellOrderitem ssoi) throws Exception {
		if (ssvo.getPartsId() != null && !ssvo.getPartsId().trim().equals(""))
			ssoi.setPartsId(ssvo.getPartsId());
		if (ssvo.getSelldCnt() != null && !ssvo.getSelldCnt().trim().equals(""))
			ssoi.setSelldCnt(Double.parseDouble(ssvo.getSelldCnt()));
		if (ssvo.getSelldPrice() != null&& !ssvo.getSelldPrice().trim().equals(""))
			ssoi.setSelldPrice(Double.parseDouble(ssvo.getSelldPrice()));
		if (ssvo.getSelldAmount() != null&& !ssvo.getSelldAmount().trim().equals(""))
			ssoi.setSelldAmount(Double.parseDouble(ssvo.getSelldAmount()));
		if (ssvo.getTaxCostPrice() != null&& !ssvo.getTaxCostPrice().trim().equals(""))
			ssoi.setSelldCostPrice(Double.parseDouble(ssvo.getTaxCostPrice()));
		if (ssvo.getTaxCostPriceAmont() != null&& !ssvo.getTaxCostPriceAmont().trim().equals(""))
			ssoi.setSelldCostAmount(Double.parseDouble(ssvo.getTaxCostPriceAmont()));
		ssoi.setSelldCutRate(Double.parseDouble(0.1 + "")); // 折扣率 临时
		if (ssvo.getSellRemark() != null&& !ssvo.getSellRemark().trim().equals(""))
			ssoi.setSellRemark(ssvo.getSellRemark());
		if (ssvo.getStoreId() != null && !ssvo.getStoreId().trim().equals(""))
			ssoi.setStoreId(Short.parseShort(ssvo.getStoreId()));
		if (ssvo.getPartsNowCount() != null && !ssvo.getPartsNowCount().trim().equals(""))
			ssoi.setPartsNowCount(Double.parseDouble(ssvo.getPartsNowCount()));
		return ssoi;
	}
	
	/**
	 * 配件库存量调整
	 */
	public void changPartsNowCount(StSellOrderitem ssoi,boolean isno) throws Exception{
		if(ssoi.getPartsId()!=null&&ssoi.getStoreId()!=null){
			PartsChangePrice partsChangePrice=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+ssoi.getPartsId()+"' and pcp.storeId="+ssoi.getStoreId());
			if(partsChangePrice!=null&&!partsChangePrice.equals("")){
				if(isno)
					partsChangePrice.setPartsNowCount(partsChangePrice.getPartsNowCount()+ssoi.getSelldCnt());
				else
					partsChangePrice.setPartsNowCount(partsChangePrice.getPartsNowCount()-ssoi.getSelldCnt());
				partsChangePriceDAO.merge(partsChangePrice);//修改配件调价表库存量
			}
		}
	}
	
	/**
	 * 验证该销售单是否可删除修改操作
	 */
	public boolean doDeleteOrUpdate(StSellOrderVo ssvo)throws Exception{
		List<Object[]> list=stSellOrderDAO.createSQLQuery("SELECT st_sell_order.SELLMM_ID FROM st_sell_order WHERE st_sell_order.SELLMM_ID IN (SELECT st_rt_parts_main.RECEPTION_ID FROM st_rt_parts_main WHERE st_rt_parts_main.RECEPTION_ID LIKE '"+Contstants.STSELLORDER+"%') AND st_sell_order.SELLMM_ID='"+ssvo.getSellmmId()+"'");
		if(list!=null&&list.size()>0)
			return false;
		else 
		    return true;
	}
	
	/**
	 * 销售转结算
	 */                                                                                                                                              
	@SuppressWarnings("unchecked")
	public void modifyPreclr(StSellOrderVo ssvo)throws Exception{
		StSellOrder sso = stSellOrderDAO.get(StSellOrder.class, ssvo.getSellmmId());
		if(sso!=null&&!sso.equals("")){    
			sso.setPreclrState("已转结算");
			stSellOrderDAO.merge(sso);
			FrtPreClearing fpc=frtPreClearingDao.get(" from FrtPreClearing fpc where fpc.receptionId='"+sso.getSellmmId()+"'");
			List<StSellOrderitem> resultList=new ArrayList<StSellOrderitem>();
			if(sso.getSellmmId()!=null&&!sso.getSellmmId().equals(""))
				resultList=stSellOrderitemDAO.find(" from StSellOrderitem ssoi where ssoi.stSellOrder.sellmmId='"+sso.getSellmmId()+"'");
			if(fpc==null||fpc.equals("")){//修改
				fpc=new FrtPreClearing();
				fpc.setPreclrId(CreateID.createId("FrtPreClearing", "XSJS"));
			}
			fpc.getFrtPrePartses().clear();
			double sellmmSumAmount=0.0;
			if(resultList!=null&&resultList.size()>0){
				for (StSellOrderitem stSellOrderitem : resultList){
					FrtPreParts fpp=new FrtPreParts();
					fpp.setFrtPreClearing(fpc);
					fpp.setPartsId(stSellOrderitem.getPartsId());
					StRtPartsDetail srpd=stRtPartsDetailDAO.get(" from StRtPartsDetail srpd where srpd.indexId="+stSellOrderitem.getSelldIndex());
					double cnt=0.0;
					if(srpd!=null&&!srpd.equals(""))
						cnt=srpd.getStrtpdCnt();
					fpp.setPartsCount(stSellOrderitem.getSelldCnt()-cnt);
					fpp.setPartsPrice(stSellOrderitem.getSelldPrice());
					fpp.setPartsAmount(new BaseAction().doubleFormat(fpp.getPartsCount()*fpp.getPartsPrice()));
					if(srpd!=null&&!srpd.equals(""))
					    sellmmSumAmount+=fpp.getPartsAmount();
					FrtParts fp=frtPartsDAO.get(FrtParts.class,stSellOrderitem.getPartsId());
					if(fp!=null&&!fp.equals(""))
						fpp.setPartsName(fp.getPartsName());
					fpp.setPartsFlg(Short.parseShort("0"));
					fpp.setStoreId(stSellOrderitem.getStoreId());
					fpp.setPartsTaxCost(stSellOrderitem.getSelldCostPrice());
					fpp.setSettlementDiscount(0.0d);
					fpp.setPartsNoTaxCost(0.0d);
					fpp.setPartsRate(1d);
					fpp.setPartsRateAmount(0.0d);
					if(fpp.getPartsCount()>0)
					    fpc.getFrtPrePartses().add(fpp);
				}
			}
			fpc.setReceptionId(sso.getSellmmId());
			fpc.setPreclrInoiceType(sso.getPreclrInoiceType());
			fpc.setPreclrNo(sso.getBillType());
			if(sellmmSumAmount>0){
				fpc.setPreMprMatAmount(new BaseAction().doubleFormat(sso.getSellmmSumAmount()-sellmmSumAmount));//材料费用合计
				fpc.setPreclrSumAmount(new BaseAction().doubleFormat(sso.getSellmmSumAmount()-sellmmSumAmount));//总费用合计
			}else{
				fpc.setPreMprMatAmount(new BaseAction().doubleFormat(sso.getSellmmSumAmount()));//材料费用合计
				fpc.setPreclrSumAmount(new BaseAction().doubleFormat(sso.getSellmmSumAmount()));//总费用合计
			}
			fpc.setPreclrTaxCost(sso.getSellmmSumCost());//总成本合计
			fpc.setPreFlg(Contstants.DELETE_TAG.DELETENO);//删除标识位
			fpc.setPreclrToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
			fpc.setPreclrMaterialRate(1d);
			fpc.setPreclrWktimeAmount(0.0d);
			fpc.setPreclrWktimeRate(1d);
			fpc.setPreclrOtherAmount(0.0d);
			fpc.setPreclrSumRate(1d);
			fpc.setPreclrRealAmount(0.0d);
			fpc.setPreclrNoTaxCost(0.0d);
			fpc.setPreclrManagementFee(0.0d);
			if(fpc==null||fpc.equals(""))//修改
				frtPreClearingDao.save(fpc);
			else
				frtPreClearingDao.merge(fpc);
		}
	}
}
