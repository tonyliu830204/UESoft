package com.syuesoft.st.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.bas.dao.BasCarBrandDAO;
import com.syuesoft.bas.dao.BasPartsBrandDAO;
import com.syuesoft.bas.dao.BasPartsPositionDAO;
import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.st.dao.BasCarTypeDAO;
import com.syuesoft.st.dao.BasPartsTypeDAO;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.service.StPartsNowCountService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;

@Service("stPartsNowCountService")
public class StPartsNowCountServiceImpl extends BaseServiceImpl implements StPartsNowCountService {

	@Autowired FrtPartsDAO frtPartsDAO;	//配件档案DAO
	@Autowired BasStorehouseDAO basStorehouseDAO;	//仓库DAO 
	@Autowired BasCarTypeDAO basCarTypeDAO;	//配件类型DAO
	@Autowired BasCarBrandDAO basCarBrandDAO;	//配件品牌DAO
	@Autowired BasPartsBrandDAO basPartsBrandDAO;	//配件品牌DAO
	@Autowired BasPartsPositionDAO basPartsPositionDAO;	//配件部位DAO
	@Autowired BasPartsTypeDAO basPartsTypeDAO;//配件型号DAO
	@Autowired PartsChangePriceDAO partsChangePriceDAO;//配件调价DAO
	
	/**
	 * 反序列化明细JSON数据
	 */
	public List<PartsNowCountVo> acceptChangesSelectedItem(PartsNowCountVo partsNowCountVo) {
		if(partsNowCountVo.getInserted() != null && !"".equals(partsNowCountVo.getInserted().trim())){
			List<PartsNowCountVo> insertedList = JSON.parseArray(partsNowCountVo.getInserted(), PartsNowCountVo.class);
			if(insertedList.size() > 0){
				return insertedList;
			}
		}
		if(partsNowCountVo.getDeleted() != null && !"".equals(partsNowCountVo.getDeleted().trim())){
			List<PartsNowCountVo> deletedList = JSON.parseArray(partsNowCountVo.getDeleted(), PartsNowCountVo.class);
			if(deletedList.size() > 0){
				return deletedList;
			}
		}
		if(partsNowCountVo.getUpdated() != null && !"".equals(partsNowCountVo.getUpdated().trim())){
			List<PartsNowCountVo> updatedList = JSON.parseArray(partsNowCountVo.getUpdated(), PartsNowCountVo.class);
			if(updatedList.size() > 0){
				return updatedList;
			}
		}
		return null;
	}
	
	/**
	 * 库存量信息     综合查询
	 */
	public Json searchPartsNowCountByCondition(PartsNowCountVo partsNowCountVo)throws Exception{
		return frtPartsDAO.searchByCondition(partsNowCountVo.getPage(), partsNowCountVo.getRows(),partsNowCountVo.getSort(), partsNowCountVo.getOrder(),partsNowCountVo.getPartsId(), partsNowCountVo.getPartsName(), partsNowCountVo.getPosName(), partsNowCountVo.getPartsLibrary(), partsNowCountVo.getPartsId2(), partsNowCountVo.getPbrdName(), partsNowCountVo.getPtypeName(), partsNowCountVo.getStoreName(), partsNowCountVo.getPartsNowCount(), partsNowCountVo.getSearchStyle(),partsNowCountVo.getPnc_strtgmDateStart(),partsNowCountVo.getPnc_strtgmDateEnd(),partsNowCountVo.getPnc_storageDateStart(),partsNowCountVo.getPnc_storageDateEnd(),null,partsNowCountVo.getEnterpriseId());
	}
	
	
	/**
	 * 配件汇总信息计算
	 */
	public Json calcutePartsChangePrice(PartsNowCountVo partsNowCountVo)throws Exception{
		return frtPartsDAO.searchByCondition(0, 0,null, null,partsNowCountVo.getPartsId(), partsNowCountVo.getPartsName(), partsNowCountVo.getPosName(), partsNowCountVo.getPartsLibrary(), partsNowCountVo.getPartsId2(), partsNowCountVo.getPbrdName(), partsNowCountVo.getPtypeName(), partsNowCountVo.getStoreName(), partsNowCountVo.getPartsNowCount(), partsNowCountVo.getSearchStyle(),partsNowCountVo.getPnc_strtgmDateStart(),partsNowCountVo.getPnc_strtgmDateEnd(),partsNowCountVo.getPnc_storageDateStart(),partsNowCountVo.getPnc_storageDateEnd(),null,partsNowCountVo.getEnterpriseId());
	}
	
	/**
	 * 配件品牌信息     预加载
	 */
	public Json loadPartsBrand(PartsNowCountVo partsNowCountVo) throws Exception{
		return basPartsBrandDAO.loadPartsBrand(partsNowCountVo.getPage(),partsNowCountVo.getRows(),partsNowCountVo.getSort(),partsNowCountVo.getOrder(),partsNowCountVo.getEnterpriseId());
	}
	
	/**
	 * 配件品牌信息    综合查询
	 */
	public Json serachPartsBrandByCondition(PartsNowCountVo partsNowCountVo)throws Exception{
		return basPartsBrandDAO.serachPartsBrandByCondition(partsNowCountVo.getPbrdId(),partsNowCountVo.getPbrdName(),partsNowCountVo.getEnterpriseId());
	}
	
	/**
	 * 配件名称     预加载
	 */
	public Json loadPartsName(PartsNowCountVo partsNowCountVo)throws Exception{
	   return frtPartsDAO.loadPartsName(partsNowCountVo.getPage(), partsNowCountVo.getRows() , partsNowCountVo.getSort(), partsNowCountVo.getOrder(),partsNowCountVo.getEnterpriseId());
	}
	
	/**
	 * 配件名称     综合查询
	 */
	public Json searchPartsNameByCondition(PartsNowCountVo partsNowCountVo)throws Exception{
		   return frtPartsDAO.searchPartsNameByCondition(partsNowCountVo.getPartsId(),partsNowCountVo.getPartsName(),partsNowCountVo.getEnterpriseId());
	}
	
	  /**
	 * 配件型号   预加载
	 */
	public Json loadPartsType(PartsNowCountVo pncvo)throws Exception{
		return basPartsTypeDAO.loadPartsType(pncvo.getPage(), pncvo.getRows(), pncvo.getSort(), pncvo.getOrder(),pncvo.getEnterpriseId());
	}
	
	/**
	 * 配件型号     综合查询
	 */
	public Json searchPartsTypeByCondition(PartsNowCountVo partsNowCountVo)throws Exception
	{
		return basPartsTypeDAO.searchPartsTypeByCondition(partsNowCountVo.getPtypeId(),partsNowCountVo.getPtypeName(),partsNowCountVo.getEnterpriseId());
	}
	
	/**
	 * 入库单明细配件价格     批量修改
	 */
	public void updateList(List<PartsNowCountVo> list)throws Exception
	{
		for (PartsNowCountVo partsNowCountVo : list) {
			PartsChangePrice partsChangePrice=partsChangePriceDAO.get("from PartsChangePrice pcp where pcp.changePriceId="+partsNowCountVo.getChangePriceId());
			if(partsChangePrice!=null&&!partsChangePrice.equals("")){
				if(partsNowCountVo.getPartsRepairPrice()!=null&&!partsNowCountVo.getPartsRepairPrice().equals(""))
					   partsChangePrice.setPartsRepairPrice(Double.parseDouble(partsNowCountVo.getPartsRepairPrice()));
					if(partsNowCountVo.getPartsSellPrice()!=null&&!partsNowCountVo.getPartsSellPrice().equals(""))
					   partsChangePrice.setPartsSellPrice(Double.parseDouble(partsNowCountVo.getPartsSellPrice()));
					if(partsNowCountVo.getPartsPointPrice()!=null&&!partsNowCountVo.getPartsPointPrice().equals(""))
					   partsChangePrice.setPartsPointPrice(Double.parseDouble(partsNowCountVo.getPartsPointPrice()));
					if(partsNowCountVo.getPartsSpecialPrice()!=null&&!partsNowCountVo.getPartsSpecialPrice().equals(""))
					   partsChangePrice.setPartsSpecialPrice(Double.parseDouble(partsNowCountVo.getPartsSpecialPrice()));
					if(partsNowCountVo.getPartsClaimantPrice()!=null&&!partsNowCountVo.getPartsClaimantPrice().equals(""))
					   partsChangePrice.setPartsClaimantPrice(Double.parseDouble(partsNowCountVo.getPartsClaimantPrice()));
					if(partsNowCountVo.getPartsLatestTaxprice()!=null&&!partsNowCountVo.getPartsClaimantPrice().equals(""))
					   partsChangePrice.setPartsLatestTaxprice(Double.parseDouble(partsNowCountVo.getPartsLatestTaxprice()));
					if(partsNowCountVo.getPartsLatestNotaxprice()!=null&&!partsNowCountVo.getPartsLatestNotaxprice().equals(""))
					   partsChangePrice.setPartsLatestNotaxprice(Double.parseDouble(partsNowCountVo.getPartsLatestNotaxprice()));
					partsChangePriceDAO.merge(partsChangePrice);
			}
		}
	}
	
	/**
	 * 配件价格修改
	 */
	public void update(PartsNowCountVo pcpVo)throws Exception{
		PartsChangePrice pcp=partsChangePriceDAO.get("from PartsChangePrice pcp where pcp.changePriceId="+pcpVo.getChangePriceId());
		if(pcp!=null&&!pcp.equals("")){
			if(pcpVo.getPartsRepairPrice()!=null&&!pcpVo.getPartsRepairPrice().trim().equals(""))
			   pcp.setPartsRepairPrice(Double.parseDouble(pcpVo.getPartsRepairPrice().trim()));
			else
			   pcp.setPartsRepairPrice(1.0);
			if(pcpVo.getPartsSellPrice()!=null&&!pcpVo.getPartsSellPrice().trim().equals(""))
			   pcp.setPartsSellPrice(Double.parseDouble(pcpVo.getPartsSellPrice().trim()));
			else
			   pcp.setPartsSellPrice(1.0);
			if(pcpVo.getPartsPointPrice()!=null&&!pcpVo.getPartsPointPrice().trim().equals(""))
			   pcp.setPartsPointPrice(Double.parseDouble(pcpVo.getPartsPointPrice().trim()));
			else
			   pcp.setPartsPointPrice(1.0);
			if(pcpVo.getPartsSpecialPrice()!=null&&!pcpVo.getPartsSpecialPrice().trim().equals(""))
			   pcp.setPartsSpecialPrice(Double.parseDouble(pcpVo.getPartsSpecialPrice().trim()));
			else
			   pcp.setPartsSpecialPrice(1.0);
			if(pcpVo.getPartsClaimantPrice()!=null&&!pcpVo.getPartsClaimantPrice().trim().equals(""))
				pcp.setPartsClaimantPrice(Double.parseDouble(pcpVo.getPartsClaimantPrice().trim()));
			else
				 pcp.setPartsClaimantPrice(1.0);
			if(pcpVo.getPartsLatestTaxprice()!=null&&!pcpVo.getPartsLatestTaxprice().trim().equals(""))
				pcp.setPartsLatestTaxprice(Double.parseDouble(pcpVo.getPartsLatestTaxprice().trim()));
			else
			    pcp.setPartsLatestTaxprice(1.0);
			if(pcpVo.getPartsLatestNotaxprice()!=null&&!pcpVo.getPartsLatestNotaxprice().trim().equals(""))
			    pcp.setPartsLatestNotaxprice(Double.parseDouble(pcpVo.getPartsLatestNotaxprice().trim()));
			else
				pcp.setPartsLatestNotaxprice(1.0);
			if(pcpVo.getStockLower()!=null&&!pcpVo.getStockLower().trim().equals(""))
				pcp.setStockLower(Double.parseDouble(pcpVo.getStockLower().trim()));
			else
				pcp.setStockLower(1.0);
			if(pcpVo.getStockUpper()!=null&&!pcpVo.getStockUpper().trim().equals(""))
			    pcp.setStockUpper(Double.parseDouble(pcpVo.getStockUpper().trim()));
			else
				pcp.setStockUpper(1.0);
			partsChangePriceDAO.merge(pcp);
		}
	}
	
	/**
	 * 配件调价表清空
	 */
	public void clearPratsChangePrice()throws Exception{
		List<PartsChangePrice> list =partsChangePriceDAO.find(" from PartsChangePrice pcp");
		if(list!=null&& list.size()>0){
			for (PartsChangePrice partsChangePrice : list) {
				partsChangePriceDAO.delete(partsChangePrice);
			}
		}
	}
	
}
