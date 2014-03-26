package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StRtPartsDetail;
import com.syuesoft.model.StRtPartsMain;
import com.syuesoft.st.dao.FrtRcptPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StRtPartsDetailDAO;
import com.syuesoft.st.dao.StRtPartsMainDAO;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.dao.StSellOrderitemDAO;
import com.syuesoft.st.service.StRtPartsService;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

/**
 * 退料单SeviceImpl
 * @author SuMing
 */
@Service("stRtPartsService")
public class StRtPartsServiceImpl implements StRtPartsService {

	@Autowired FrtReceptionDao frtReceptionDao;
	@Autowired FrtRcptPartsDAO frtRcptPartsDAO;
	@Autowired BasStorehouseDAO basStorehouseDAO;
	@Autowired StRtPartsMainDAO stRtPartsMainDAO;
	@Autowired StRtPartsDetailDAO stRtPartsDetailDAO;
	@Autowired StSellOrderDAO stSellOrderDAO;
	@Autowired StSellOrderitemDAO stSellOrderitemDAO;
	@Autowired PartsChangePriceDAO partsChangePriceDAO;
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StRtPartsVo> jsonChangeDetailList(StRtPartsVo srpvo){
		List<StRtPartsVo> list =new ArrayList<StRtPartsVo>();
		if(srpvo.getDetailData()!=null&&!srpvo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(srpvo.getDetailData());
			List<StRtPartsVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StRtPartsVo.class);
			if(resultList.size() > 0){
				for (StRtPartsVo stRtPartsVo : resultList) {
					if(stRtPartsVo.getStrtpdCnt()!=null&&!stRtPartsVo.getStrtpdCnt().trim().equals("")&&Double.parseDouble(stRtPartsVo.getStrtpdCnt())>0)
						list.add(stRtPartsVo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 工单退料单汇总信息      综合查询
	 */
	public Json searchRecptByCondition(StRtPartsVo srpvo)throws Exception{
	    return stRtPartsMainDAO.searchByCondition(srpvo.getPage(),srpvo.getRows(),srpvo.getSort(),srpvo.getOrder(),srpvo.getStrtpmDateStart(), srpvo.getStrtpmDateqiEnd(),srpvo.getReceptionId(), srpvo.getCarLicense(), srpvo.getStrtpmId(),srpvo.getEnterpriseId());
	}
	
	/**
	 * 销售退料单汇总信息       预加载
	 */
	public Json loadSellStRtPartsMain(StRtPartsVo srpvo)throws Exception{
	    return  stRtPartsMainDAO.loadSellStRtPartsMain(srpvo.getPage(),srpvo.getRows(),srpvo.getSort(),srpvo.getOrder(),srpvo.getEnterpriseId());
	}
	
	/**
	 * 销售退料单汇总信息       综合查询
	 */
	public Json searchSellStRtPartsMainByCondition(StRtPartsVo srpvo)throws Exception{
		return stRtPartsMainDAO.searchSellStRtPartsMainByCondition(srpvo.getStrtpmDateStart(), srpvo.getStrtpmDateqiEnd(),srpvo.getReceptionId(), srpvo.getCarLicense(), srpvo.getStrtpmId(),srpvo.getEnterpriseId());
	}
	
	/**
	 * 双击退料单汇总单信息获取      退料单明细信息     预加载
	 */
	public List<StRtPartsVo> searchRecptStRtPartsDetailByStrtpmId(StRtPartsVo stRtPartsVo)throws Exception{
		return stRtPartsDetailDAO.searchRecptStRtPartsDetailByStrtpmId(stRtPartsVo.getStrtpmId());
	}
	
	/**
	 * 双击销售退料单汇总信息获取    销售退料单明细信息    预加载
	 */
	public List<StRtPartsVo> searchSellStRtPartsDetailByStrtpmId(StRtPartsVo stRtPartsVo)throws Exception{
		return stRtPartsDetailDAO.searchSellStRtPartsDetailByStrtpmId(stRtPartsVo.getStrtpmId());
	}
	
	/**
     * 工单信息       预加载
	 */
	public Json loadFrtReception(StRtPartsVo srpvo) throws Exception {
		return frtReceptionDao.loadFrtReception(srpvo.getPage(),srpvo.getRows(),srpvo.getSort(),srpvo.getOrder(),"StRtParts",srpvo.getEnterpriseId());
	}
	
	/**
     * 工单信息       综合查询
	 */
	public Json searchFrtReceptionByCondition(StRtPartsVo srpvo) throws Exception {
		return frtReceptionDao.searchFrtReceptionByCondition(srpvo.getReceptionId(), srpvo.getCarLicense(), srpvo.getCustomName(), srpvo.getCarVan(),srpvo.getEnterpriseId());
	}
	
	/**
	 * 根据前台接车工单号获取     相关计划用料信息     预加载
	 */
	public Json loadParts(StRtPartsVo srpVo)throws Exception {
		return  frtRcptPartsDAO.searchRecpPartsByReceptionId(srpVo.getReceptionId());
	}
	
	/**
	 * 根据销售单单号获取     相关销售单明细信息       预加载
	 */
	public Json loadSelledParts(StRtPartsVo stRtPartsVo)throws Exception {
		return stSellOrderitemDAO.searchByCondition(stRtPartsVo.getSellmmId());
	}
	
	/**
	 * 销售单信息       预加载
	 */
	public Json loadStSellOrder(StRtPartsVo stRtPartsVo)throws Exception {
		return stSellOrderitemDAO.loadSellOrderInfo(stRtPartsVo.getPage(), stRtPartsVo.getRows(), stRtPartsVo.getSort(), stRtPartsVo.getOrder(),stRtPartsVo.getEnterpriseId());
	}
	
	 /**
	 * 销售单信息     综合查询
	 */
    public Json searchStSellOrderByCondition(StRtPartsVo stRtPartsVo)throws Exception{
    	return stSellOrderitemDAO.searchStSellOrderByCondition(stRtPartsVo.getSellmmId(),stRtPartsVo.getEnterpriseId());
    }
	
	/**
	 * 工单退料汇总明细信息     新增
	 */
    @Log(moduleName = "工单/销售退料单管理", opertype = "新增工单/销售退料单", content = "工单/销售退料单管理-->新增工单/销售退料单")
	public String add(StRtPartsVo stRtPartsVo, List<StRtPartsVo> list)throws Exception {
		StRtPartsMain srpm = new StRtPartsMain();
		if(stRtPartsVo.getReceptionId()!=null&&!stRtPartsVo.getReceptionId().trim().equals("")){
			if(stRtPartsVo.getReceptionId().trim().substring(0, 1).equals("J")){
				srpm.setStrtpmId(CreateID.createId("StRtPartsMain", Contstants.QUITPARTS_TAG.SERVICEQUEITID));
				srpm.setStrtpmType("工单退料");
			}else{
				srpm.setStrtpmId(CreateID.createId("StRtPartsMain", Contstants.QUITPARTS_TAG.SELLQUITID));
				srpm.setStrtpmType("销售退料");
			}
		}
		srpm=copyMainProperty(stRtPartsVo, srpm);
	    String id = stRtPartsMainDAO.save(srpm).toString();//销售单汇总  保存
		if(list!=null&&list.size()>0){
			for (StRtPartsVo srpvo:list) {
				if (srpvo.getStrtpdCnt() != null&& !srpvo.getStrtpdCnt().trim().equals("")&&Double.parseDouble(srpvo.getStrtpdCnt().trim())>0){
					StRtPartsDetail srpd = new StRtPartsDetail();
					srpd.setStRtPartsMain(srpm);
					srpd=copyDetailProperty(srpvo, srpd);
					stRtPartsDetailDAO.save(srpd);//销售单明细  保存
					PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+srpvo.getStoreId()+" and pcp.partsId='"+srpvo.getPartsId()+"'");
					if(pcp!=null&&!pcp.equals("")){
						pcp.setPartsNowCount(pcp.getPartsNowCount()+Double.parseDouble(srpvo.getStrtpdCnt()));
						partsChangePriceDAO.merge(pcp);
					}
				}
			}
		}
		return id;
	}
	
	/**
	 * 工单退料单汇总明细信息         删除
	 */
    @Log(moduleName = "工单/销售退料单管理", opertype = "删除工单/销售退料单", content = "工单/销售退料单管理-->删除工单/销售退料单")
	public void delete(StRtPartsVo stRtPartsVo)throws Exception{
		List<StRtPartsDetail> list=stRtPartsDetailDAO.find("from StRtPartsDetail srpd where srpd.stRtPartsMain.strtpmId='"+stRtPartsVo.getStrtpmId()+"'");
		if(list!=null&&list.size()>0){
			for(StRtPartsDetail srpd:list){
				stRtPartsDetailDAO.delete(srpd);
				PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.storeId="+srpd.getStoreId()+" and pcp.partsId='"+srpd.getPartsId()+"'");
				if(pcp!=null&&!"".equals(pcp)){
					pcp.setPartsNowCount(pcp.getPartsNowCount()-srpd.getStrtpdCnt());
					partsChangePriceDAO.merge(pcp);
				}
			}
		}
		StRtPartsMain stRtPartsMain=stRtPartsMainDAO.get(" from StRtPartsMain srpm where srpm.strtpmId='"+stRtPartsVo.getStrtpmId()+"'");
		if(stRtPartsMain!=null&&!stRtPartsMain.equals(""))
		   stRtPartsMainDAO.delete(stRtPartsMain);
	}
	
    /**
     * 汇总信息拷贝
     */
	public StRtPartsMain copyMainProperty(StRtPartsVo stRtPartsVo,StRtPartsMain srpm){
		if(stRtPartsVo.getStrtpmDate()!=null&&!stRtPartsVo.getStrtpmDate().trim().equals(""))
		  srpm.setStrtpmDate(Utils.dateFormat(stRtPartsVo.getStrtpmDate().trim()));
		if(stRtPartsVo.getReceptionId()!=null&&!stRtPartsVo.getReceptionId().trim().equals(""))
		  srpm.setReceptionId(stRtPartsVo.getReceptionId().trim());
		if(stRtPartsVo.getStfId()!=null&&!stRtPartsVo.getStfId().trim().equals(""))
		  srpm.setStfId(Short.parseShort(stRtPartsVo.getStfId().trim()));
		if(stRtPartsVo.getManager()!=null&&!stRtPartsVo.getManager().trim().equals(""))
		  srpm.setManager(Short.parseShort(stRtPartsVo.getManager().trim()));
		if(stRtPartsVo.getStrtpmSumCnt()!=null&&!stRtPartsVo.getStrtpmSumCnt().trim().equals(""))
		  srpm.setStrtpmSumCnt(Double.parseDouble(stRtPartsVo.getStrtpmSumCnt().trim()));
		if(stRtPartsVo.getStrtpmAmont()!=null&&!stRtPartsVo.getStrtpmAmont().trim().equals(""))
		  srpm.setStrtpmAmont(Double.parseDouble(stRtPartsVo.getStrtpmAmont().trim()));
		if(stRtPartsVo.getStrtpmCostAmont()!=null&&!stRtPartsVo.getStrtpmCostAmont().trim().equals(""))
		  srpm.setStrtpmCostAmont(Double.parseDouble(stRtPartsVo.getStrtpmCostAmont().trim()));
		if(stRtPartsVo.getStrtpmRemark()!=null&&!stRtPartsVo.getStrtpmRemark().trim().equals(""))
		  srpm.setStrtpmRemark(stRtPartsVo.getStrtpmRemark().trim());
		srpm.setEnterpriseId(stRtPartsVo.getEnterpriseId());
		return srpm;
	}
	
	/**
	 * 明细信息拷贝
	 */
	public StRtPartsDetail copyDetailProperty(StRtPartsVo srpvo,StRtPartsDetail srpd) {
		if (srpvo.getPartsId() != null && !srpvo.getPartsId().trim().equals(""))
			srpd.setPartsId(srpvo.getPartsId());
		if (srpvo.getStrtpdCnt() != null&& !srpvo.getStrtpdCnt().trim().equals(""))
			srpd.setStrtpdCnt(Double.parseDouble(srpvo.getStrtpdCnt()));
		if (srpvo.getStoreId() != null && !srpvo.getStoreId().trim().equals(""))
			srpd.setStoreId(Short.parseShort(srpvo.getStoreId()));
		if (srpvo.getStrtpdRemark() != null&& !srpvo.getStrtpdRemark().trim().equals(""))
			srpd.setStrtpdRemark(srpvo.getStrtpdRemark());
		if (srpvo.getIndexId()!= null&& !srpvo.getIndexId().trim().equals(""))
			srpd.setIndexId(Integer.parseInt(srpvo.getIndexId()));
		return srpd; 
	}
}
