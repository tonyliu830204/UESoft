package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StStorageItem;
import com.syuesoft.st.dao.StStorageItemDAO;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.st.vo.StStorageVo;
import com.syuesoft.util.Json;

@Repository("stStorageItemDAO")
public class StStorageItemDAOImpl extends BaseDaoImpl<StStorageItem> implements
		StStorageItemDAO {

	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 根据入库汇总单单号获取入库单明细信息
	 */
	public Json searchStStorageItemByStorageId(final String storageId) throws Exception{
		List<StStorageVo> list = new ArrayList<StStorageVo>();
		StringBuffer sb=new StringBuffer("SELECT st_storage_item.ITEM_INDEX,bas_parts_type.PTYPE_NAME,st_storage_item.PARTS_ID,"+
		"frt_parts.PARTS_NAME,bas_parts_unit.PUNIT_NAME,st_storage_item.PARTS_COUNT,"+
		"st_storage_item.TAX_PRICE,st_storage_item.NOTAX_PRICE,st_storage_item.TAX_TOTALAMONT,"+
		"st_storage_item.NOTAX_TOTALAMONT FROM st_storage_item,frt_parts,bas_parts_unit,bas_parts_type"+
		" WHERE st_storage_item.PARTS_ID=frt_parts.PARTS_ID"+
		" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID");
		if(storageId!=null&&!storageId.equals(""))
			sb.append(" AND st_storage_item.STORAGE_ID='"+storageId+"'");
		int count =getSQLCount(sb.toString(),null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
		    for (int i = 0; i <resultList.size(); i++) {
		    	 StStorageVo svo=new StStorageVo();
		    	 obj=(Object[]) resultList.get(i);
		    	 if(obj[0]!=null&&!obj[0].equals(""))
		    		 svo.setStpredIndex(obj[0].toString()); 
		    	 if(obj[1]!=null&&!obj[1].equals(""))
		    		 svo.setPtypeName(obj[1].toString()); 
		    	 if(obj[2]!=null&&!obj[2].equals(""))
		    		 svo.setPartsId(obj[2].toString()); 
		    	 if(obj[3]!=null&&!obj[3].equals(""))
		    		 svo.setPartsName(obj[3].toString()); 
		    	 if(obj[4]!=null&&!obj[4].equals(""))
		    		 svo.setPunitName(obj[4].toString()); 
		    	 if(obj[5]!=null&&!obj[5].equals(""))
		    		 svo.setPartsNum(obj[5].toString()); 
		    	 if(obj[6]!=null&&!obj[6].equals(""))
		    		 svo.setTaxPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[6].toString())); 
		    	 if(obj[7]!=null&&!obj[7].equals(""))
		    		 svo.setNotaxPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString())); 
		    	 if(obj[8]!=null&&!obj[8].equals(""))
		    		 svo.setTaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[8].toString())); 
		    	 if(obj[9]!=null&&!obj[9].equals(""))
		    		 svo.setNotaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[9].toString())); 
		    	 list.add(svo);
			}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
	    return json;
	}
	
	/**
	 * 根据入库汇总单单号获取入库单明细信息
	 */
	public List<PartsNowCountVo> searchStStorageDetailByStorageId(final String storageId) throws Exception{
		List<PartsNowCountVo> list = new ArrayList<PartsNowCountVo>();
		StringBuffer sb=new StringBuffer(
		" SELECT frt_parts.PARTS_ID,frt_parts.PARTS_ID2,frt_parts.PARTS_NAME,frt_parts.PARTS_LIBRARY,bas_storehouse.STORE_NAME,"+
		" bas_parts_unit.PUNIT_NAME,bas_parts_type.PTYPE_NAME,parts_change_price.PARTS_REPAIR_PRICE,"+
		" parts_change_price.PARTS_SELL_PRICE,parts_change_price.PARTS_POINT_PRICE,parts_change_price.PARTS_SPECIAL_PRICE,"+
		" parts_change_price.PARTS_CLAIMANT_PRICE,st_storage_item.TAX_PRICE,st_storage_item.NOTAX_PRICE,"+
		" parts_change_price.PARTS_NOW_COUNT ,parts_change_price.CHANGE_PRICE_ID " +
		" FROM st_goods_storage,st_storage_item,frt_parts,bas_parts_unit,bas_parts_type,"+
		" parts_change_price,bas_storehouse WHERE 1=1 " +
		" AND st_storage_item.PARTS_ID=frt_parts.PARTS_ID " +
		" AND st_goods_storage.STORAGE_ID=st_storage_item.STORAGE_ID "+
		" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID "+
		" AND st_storage_item.PARTS_ID=parts_change_price.PARTS_ID " +
		" AND st_goods_storage.STORE_ID=parts_change_price.STORE_ID"+
		" AND bas_storehouse.STORE_ID=st_goods_storage.STORE_ID");
		if(storageId!=null&&!storageId.equals(""))
			sb.append(" AND st_goods_storage.STORAGE_ID like '%"+storageId+"%'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
		    for (int i = 0; i <resultList.size(); i++) {
		    	PartsNowCountVo svo=new PartsNowCountVo();
		    	 obj=(Object[]) resultList.get(i);
					if(obj[0]!=null&&!obj[0].equals(""))
						svo.setPartsId(obj[0].toString());
					if(obj[1]!=null&&!obj[1].equals(""))
						svo.setPartsId2(obj[1].toString());
					if(obj[2]!=null&&!obj[2].equals(""))
						svo.setPartsName(obj[2].toString());
					if(obj[3]!=null&&!obj[3].equals(""))
						svo.setPartsLibrary(obj[3].toString());
					if(obj[4]!=null&&!obj[4].equals(""))
						svo.setStoreName(obj[4].toString());
					if(obj[5]!=null&&!obj[5].equals(""))
						svo.setPunitName(obj[5].toString());
					if(obj[6]!=null&&!obj[6].equals(""))
						svo.setPtypeName(obj[6].toString());
					if(obj[7]!=null&&!obj[7].equals(""))
						svo.setPartsRepairPrice(obj[7].toString());
					if(obj[8]!=null&&!obj[8].equals(""))
						svo.setPartsSellPrice(obj[8].toString());
					if(obj[9]!=null&&!obj[9].equals(""))
						svo.setPartsPointPrice(obj[9].toString());
					if(obj[10]!=null&&!obj[10].equals(""))
						svo.setPartsSpecialPrice(obj[10].toString());
					if(obj[11]!=null&&!obj[11].equals(""))
						svo.setPartsClaimantPrice(obj[11].toString());
					if(obj[12]!=null&&!obj[12].equals(""))
						svo.setPartsLatestTaxprice(obj[12].toString());
					if(obj[13]!=null&&!obj[13].equals(""))
						svo.setPartsLatestNotaxprice(obj[13].toString());
					if(obj[14]!=null&&!obj[14].equals(""))
						svo.setPartsNowCount(obj[14].toString());
					if(obj[15]!=null&&!obj[15].equals(""))
						svo.setChangePriceId(obj[15].toString());
					list.add(svo);
			}
		}
	    return list;
	}
}