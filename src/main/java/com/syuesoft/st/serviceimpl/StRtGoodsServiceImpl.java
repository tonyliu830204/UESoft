package com.syuesoft.st.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StRtGoodsDetail;
import com.syuesoft.model.StRtGoodsMain;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.dao.StRtGoodsDetailDAO;
import com.syuesoft.st.dao.StRtGoodsMainDAO;
import com.syuesoft.st.service.StRtGoodsService;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

/**
 * 退货单service接口
 * @author SuMing
 */
@Service("stRtGoodsService")
public class StRtGoodsServiceImpl implements StRtGoodsService {

	@Autowired StGoodsStorageDAO stGoodsStorageDAO;            //入库单汇总dao
	@Autowired BasStorehouseDAO basStorehouseDAO;              //仓库dao
	@Autowired PartsChangePriceDAO partsChangePriceDAO;        //配件调价表dao
	@Autowired StRtGoodsMainDAO stRtGoodsMainDAO;              //退货单汇总dao
	@Autowired StRtGoodsDetailDAO stRtGoodsDetailDAO;          //退货明细dao
	
	/**
	 * 明细数据由JSON转换为List集合
	 */
	public List<StRtGoodsVo> jsonChangeDetailList(StRtGoodsVo stRtGoodsVo){
		List<StRtGoodsVo> list =new ArrayList<StRtGoodsVo>();
		if(stRtGoodsVo.getDetailData()!=null&&!stRtGoodsVo.getDetailData().equals("")){
			JSONObject jsDetailData=JSON.parseObject(stRtGoodsVo.getDetailData());
			List<StRtGoodsVo> resultList = JSON.parseArray(jsDetailData.get("rows").toString(), StRtGoodsVo.class);
			if(resultList.size() > 0){
				for (StRtGoodsVo ssovo : resultList) {
					if(ssovo.getStrtgdCnt()!=null&&!ssovo.getStrtgdCnt().equals(""))
					   list.add(ssovo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 退货单汇总    综合查询
	 */
	public  Json searchByCondition(StRtGoodsVo stRtGoodsVo)throws Exception{
		 return  stRtGoodsMainDAO.searchByCondition(stRtGoodsVo.getSort(),stRtGoodsVo.getOrder(),stRtGoodsVo.getPage(),stRtGoodsVo.getRows(),stRtGoodsVo.getStrtgmDateStart(), 
					stRtGoodsVo.getStrtgmDateEnd(), stRtGoodsVo.getStrtgmId(), stRtGoodsVo.getRelcampName(), stRtGoodsVo.getStoreId(),stRtGoodsVo.getEnterpriseId());
	}
	
	/**
	 * 退货单模块   根据入库单号查询入库单信息
	 */
	public List<StRtGoodsVo> searchstGoodsStorageByStorageId(StRtGoodsVo stRtGoodsVo)throws Exception{
		 return  stGoodsStorageDAO.searchstGoodsStorageByStorageId(stRtGoodsVo.getStorageId());
	}
	
	/**
	 * 通过退货单号获取相对应退货明细信息
	 */
	public List<StRtGoodsVo> searchStRtGoodsDetailByStrtgmId(StRtGoodsVo stRtGoodsVo)throws Exception{
		return stRtGoodsDetailDAO.srg_searchStRtGoodsDetailByStrtgmId(stRtGoodsVo.getStrtgmId(),stRtGoodsVo.getStorageId());
	}
	
	/**
	 * 根据供应商ID获取相对应入库单信息
	 */
	public List<StRtGoodsVo> searchStGoodsStorageByRelcampId(StRtGoodsVo stRtGoodsVo) throws Exception
	{
		return stGoodsStorageDAO.srg_searchByCondition(stRtGoodsVo.getRelcampId(),stRtGoodsVo.getStorageId(),stRtGoodsVo.getStoreId(),stRtGoodsVo.getEnterpriseId());
	}
	
	/**
	 * 点击入库单加载相关入库单明细信息
	 */
    public List<StRtGoodsVo> searchStGoodsStorageByStorageId(StRtGoodsVo stRtGoodsVo) throws Exception
    {
		return stRtGoodsDetailDAO.srg_searchStRtGoodsDetailByStorageId(stRtGoodsVo.getPartsId(),stRtGoodsVo.getPartsName(),stRtGoodsVo.getStorageId(),stRtGoodsVo.getRelcampId(),stRtGoodsVo.getEnterpriseId());
	}
	
	/**
	 * 退货单添加
	 */
    @Log(moduleName = "退货单管理", opertype = "新增退货单", content = "退货单管理-->新增退货单")
	public void add(StRtGoodsVo stRtGoodsVo,List<StRtGoodsVo> list) throws Exception {
		StRtGoodsMain stRtGoodsMain=new StRtGoodsMain();
		stRtGoodsMain.setStrtgmId(CreateID.createId("StRtGoodsMain", "THD"));
		stRtGoodsMain=copyMainProperty(stRtGoodsVo, stRtGoodsMain);
		stRtGoodsMainDAO.save(stRtGoodsMain);//退货单汇总添加
	    for (StRtGoodsVo srVo:list) {
	    	if(srVo.getStrtgdCnt()!=null&&!srVo.getStrtgdCnt().trim().equals("")&&Double.parseDouble(srVo.getStrtgdCnt().trim())>0){
	    		PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+srVo.getPartsId()+"' and pcp.storeId="+stRtGoodsVo.getStoreId());
	    		if(pcp!=null){
		    		StRtGoodsDetail stRtGoodsDetail=new StRtGoodsDetail();
		    		stRtGoodsDetail.setStRtGoodsMain(stRtGoodsMain);
		    		stRtGoodsDetail=copyDetailProperty(srVo, stRtGoodsDetail);
			    	stRtGoodsDetailDAO.save(stRtGoodsDetail);//退货单明细添加
			    	pcp.setPartsNowCount(pcp.getPartsNowCount()-Double.parseDouble(srVo.getStrtgdCnt()));
			    	partsChangePriceDAO.merge(pcp);
	    		}
	    	}
		}
	 }
	
	/**
	 * 退货单修改
	 */
    @Log(moduleName = "退货单管理", opertype = "修改退货单", content = "退货单管理-->修改退货单")
	public void update(StRtGoodsVo stRtGoodsVo,List<StRtGoodsVo> list) throws Exception
	{
		StRtGoodsMain stRtGoodsMain=stRtGoodsMainDAO.get(" from StRtGoodsMain srgm where srgm.strtgmId='"+stRtGoodsVo.getStrtgmId()+"'");
		stRtGoodsMain=copyMainProperty(stRtGoodsVo, stRtGoodsMain);
		stRtGoodsMainDAO.merge(stRtGoodsMain);//退货汇总修改
		
		List<StRtGoodsDetail> resultList=stRtGoodsDetailDAO.find(" from StRtGoodsDetail stRtGoodsDetail where stRtGoodsDetail.stRtGoodsMain.strtgmId='"+stRtGoodsVo.getStrtgmId()+"'");
		for (StRtGoodsDetail stRtGoodsDetail:resultList) {
			PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+stRtGoodsDetail.getPartsId()+"' and pcp.storeId="+stRtGoodsVo.getStoreId());
			if(pcp!=null&&!pcp.equals("")){
				pcp.setPartsNowCount(pcp.getPartsNowCount()+stRtGoodsDetail.getStrtgdCnt());
		    	partsChangePriceDAO.merge(pcp);
			}
			stRtGoodsDetailDAO.delete(stRtGoodsDetail);
		}
	    for (StRtGoodsVo srgvo:list) {
	    	if(srgvo.getStrtgdCnt()!=null&&!srgvo.getStrtgdCnt().trim().equals("")&&Double.parseDouble(srgvo.getStrtgdCnt().trim())>0){
		    	PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+srgvo.getPartsId()+"' and pcp.storeId="+stRtGoodsVo.getStoreId());
	    		if(pcp!=null){
	    			StRtGoodsDetail stRtGoodsDetail=new StRtGoodsDetail();
		    		stRtGoodsDetail.setStRtGoodsMain(stRtGoodsMain);
		    		stRtGoodsDetail=copyDetailProperty(srgvo, stRtGoodsDetail);
		    		stRtGoodsDetailDAO.save(stRtGoodsDetail);//退货单明细添加
		    		pcp.setPartsNowCount(pcp.getPartsNowCount()-Double.parseDouble(srgvo.getStrtgdCnt()));
		    		partsChangePriceDAO.merge(pcp);
	    		}
	    	}
		}
	 }
	
	/**
	 * 退货单删除
	 */
    @Log(moduleName = "退货单管理", opertype = "删除退货单", content = "退货单管理-->删除退货单")
	public void delete(StRtGoodsVo stRtGoodsVo) throws Exception
	{
		StRtGoodsMain stRtGoodsMain=stRtGoodsMainDAO.get(StRtGoodsMain.class,stRtGoodsVo.getStrtgmId());
		List<StRtGoodsDetail> resultList=stRtGoodsDetailDAO.find("from StRtGoodsDetail stRtGoodsDetail where stRtGoodsDetail.stRtGoodsMain.strtgmId='"+stRtGoodsVo.getStrtgmId()+"'");
		if(resultList!=null&&resultList.size()>0){
			for (StRtGoodsDetail stRtGoodsDetail:resultList) {
				PartsChangePrice pcp=partsChangePriceDAO.get(" from PartsChangePrice pcp where pcp.partsId='"+stRtGoodsDetail.getPartsId()+"' and pcp.storeId="+stRtGoodsMain.getStoreId());
				if(pcp!=null&&!pcp.equals("")){
					pcp.setPartsNowCount(pcp.getPartsNowCount()+stRtGoodsDetail.getStrtgdCnt());
			    	partsChangePriceDAO.merge(pcp);
				}
				stRtGoodsDetailDAO.delete(stRtGoodsDetail);//退货明细删除
			}
		}
		if(stRtGoodsMain!=null&&!stRtGoodsMain.equals("")){
			stRtGoodsMainDAO.delete(stRtGoodsMain);//退货汇总删除
		}
	 }
	
	 public StRtGoodsMain copyMainProperty(StRtGoodsVo stRtGoodsVo,StRtGoodsMain stRtGoodsMain)throws Exception{
    	if(stRtGoodsVo.getStrtgmDate()!=null&&!stRtGoodsVo.getStrtgmDate().trim().equals(""))
    		stRtGoodsMain.setStrtgmDate(Utils.dateFormat(stRtGoodsVo.getStrtgmDate().trim()));	
    	if(stRtGoodsVo.getRelcampId()!=null&&!stRtGoodsVo.getRelcampId().trim().equals(""))
    		stRtGoodsMain.setRelcampId(Short.parseShort(stRtGoodsVo.getRelcampId().trim()));
    	if(stRtGoodsVo.getStorageId()!=null&&!stRtGoodsVo.getStorageId().trim().equals(""))
    		stRtGoodsMain.setStorageId(stRtGoodsVo.getStorageId().trim());//入库单号
    	if(stRtGoodsVo.getManagerId()!=null&&!stRtGoodsVo.getManagerId().trim().equals(""))
    		stRtGoodsMain.setStfId(Short.parseShort(stRtGoodsVo.getManagerId().trim()));
    	if(stRtGoodsVo.getStoreId()!=null&&!stRtGoodsVo.getStoreId().trim().equals(""))
    		stRtGoodsMain.setStoreId(Short.parseShort(stRtGoodsVo.getStoreId().trim()));
    	if(stRtGoodsVo.getTotalNum()!=null&&!stRtGoodsVo.getTotalNum().trim().equals(""))
    		stRtGoodsMain.setNumTotal(Double.parseDouble(stRtGoodsVo.getTotalNum().trim()));//获取退货数量
    	if(stRtGoodsVo.getStrtgmSumNoCost()!=null&&!stRtGoodsVo.getStrtgmSumNoCost().trim().equals(""))
    		stRtGoodsMain.setStrtgmSumNoCost(Double.parseDouble(stRtGoodsVo.getStrtgmSumNoCost().trim()));
    	if(stRtGoodsVo.getStrtgmSumCost()!=null&&!stRtGoodsVo.getStrtgmSumCost().trim().equals(""))
    		stRtGoodsMain.setStrtgmSumCost(Double.parseDouble(stRtGoodsVo.getStrtgmSumCost().trim()));
    	if(stRtGoodsVo.getStrtgmRemark()!=null&&!stRtGoodsVo.getStrtgmRemark().trim().equals(""))
    		stRtGoodsMain.setStrtgmRemark(stRtGoodsVo.getStrtgmRemark().trim());//获取退货汇总备注
    	stRtGoodsMain.setEnterpriseId(stRtGoodsVo.getEnterpriseId());
    	stRtGoodsMain.setStrtgmPaymentStatus(true);	//付讫状态 //临时
		stRtGoodsMain.setStrtgmCheckStatus(true);	//审核状态  //临时
		stRtGoodsMain.setStrtgmStatus(true);		//退货状态 //临时
		stRtGoodsMain.setEnterpriseId(stRtGoodsVo.getEnterpriseId());
		return stRtGoodsMain;
    }
	    
    public StRtGoodsDetail copyDetailProperty(StRtGoodsVo srVo,StRtGoodsDetail stRtGoodsDetail)throws Exception{
    	if(srVo.getPartsId()!=null&&!srVo.getPartsId().trim().equals(""))
    		stRtGoodsDetail.setPartsId(srVo.getPartsId());
    	if(Double.parseDouble(srVo.getStrtgdCnt().trim())>0)
			stRtGoodsDetail.setStrtgdCnt(Double.parseDouble(srVo.getStrtgdCnt()));
    	if(srVo.getStrtgdCostPrice()!=null&&!srVo.getStrtgdCostPrice().trim().equals(""))
    		stRtGoodsDetail.setStrtgdCostPrice(Double.parseDouble(srVo.getStrtgdCostPrice()));
    	if(srVo.getStrtgdNoCostPrice()!=null&&!srVo.getStrtgdNoCostPrice().trim().equals(""))
    		stRtGoodsDetail.setStrtgdNoCostPrice(Double.parseDouble(srVo.getStrtgdNoCostPrice()));
    	if(srVo.getStrtgdCostAmount()!=null&&!srVo.getStrtgdCostAmount().trim().equals(""))
    		stRtGoodsDetail.setStrtgdCostAmount(Double.parseDouble(srVo.getStrtgdCostAmount()));
    	if(srVo.getStrtgdNoCostAmount()!=null&&!srVo.getStrtgdNoCostAmount().trim().equals(""))
    		stRtGoodsDetail.setStrtgdNoCostAmount(Double.parseDouble(srVo.getStrtgdNoCostAmount()));
		if(srVo.getStrtgdRemark()!=null&&!srVo.getStrtgdRemark().trim().equals(""))
			stRtGoodsDetail.setStrtgdRemark(srVo.getStrtgdRemark());
		return stRtGoodsDetail;
    }
}
