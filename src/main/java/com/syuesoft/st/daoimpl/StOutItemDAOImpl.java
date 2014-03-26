package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StOutItem;
import com.syuesoft.st.dao.StOutItemDAO;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
/**
 * 出库单明细DAOImpl
 * @author SuMing
 */
@Repository("stOutItemDAO")
public class StOutItemDAOImpl extends BaseDaoImpl<StOutItem> implements
		StOutItemDAO {
	
	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 根据出库单号预加载出库单明细
	 */
	@SuppressWarnings("unchecked")
	public List serachStOutItemByCondition(final String stomId)throws Exception
	{
		List<StOutVo> list=new ArrayList<StOutVo>();
		StringBuffer sb=new StringBuffer("SELECT M.PARTS_ID,M.PARTS_NAME,M.PUNIT_NAME,M.FIT_PTYPE,M.PARTS_LATEST_TAXPRICE,M.PARTS_LATEST_NOTAXPRICE,"+
		" M.PARTS_SELL_PRICE,M.ITEM_COUNT,M.TAX_CASTAMONT,M.NOTAX_CASTAMONT,M.AMOUNT,M.PARTS_LIBRARY,M.CLAIMS_NAME,"+
		" M.PARTS_NOW_COUNT,M.OUT_ITEM_REMARK,M.STORE_NAME,M.CHANGE_PRICE_ID,M.ITEM_COUNT,M.REPTYP_NAME,M.STORE_ID  "+
		" ,M.STOM_ID,M.INDEX_ID,M.CLAIMS_ID,M.REPTYP_ID FROM (SELECT F.*,bas_repair_type.REPTYP_NAME,bas_repair_type.REPTYP_ID FROM (SELECT E.*,bas_storehouse.STORE_NAME FROM (SELECT D.*,"+
		" bas_claims_type.CLAIMS_NAME FROM (SELECT C.*, parts_change_price.PARTS_LATEST_TAXPRICE,parts_change_price.PARTS_LATEST_NOTAXPRICE,"+
		" parts_change_price.PARTS_SELL_PRICE,parts_change_price.PARTS_NOW_COUNT,parts_change_price.CHANGE_PRICE_ID  FROM "+
		" (SELECT B.*,bas_parts_unit.PUNIT_NAME FROM (SELECT A.*,frt_parts.PARTS_NAME,frt_parts.PARTS_LIBRARY,frt_parts.FIT_PTYPE,"+
		" frt_parts.PUNIT_NAME AS PUNIT_ID FROM (SELECT st_out_item.STOM_ID,st_out_item.INDEX_ID,st_out_item.PARTS_ID,st_out_item.ITEM_COUNT,st_out_item.TAX_CASTAMONT,"+
		" st_out_item.NOTAX_CASTAMONT,st_out_item.AMOUNT,st_out_item.CLAIMS_TYPE AS CLAIMS_ID ,st_out_item.OUT_ITEM_REMARK,"+
		" st_out_item.ITEM_CHARGE,st_out_item.STORE_ID FROM st_out_item) AS A LEFT JOIN frt_parts ON A.PARTS_ID=frt_parts.PARTS_ID)AS B "+
		" LEFT JOIN bas_parts_unit ON B.PUNIT_ID=bas_parts_unit.PUNIT_ID)AS C LEFT JOIN parts_change_price ON parts_change_price.PARTS_ID=C.PARTS_ID"+
		" AND parts_change_price.STORE_ID=C.STORE_ID)AS D LEFT JOIN bas_claims_type ON D.CLAIMS_ID=bas_claims_type.CLAIMS_ID)AS E"+
		" LEFT JOIN bas_storehouse ON E.STORE_ID=bas_storehouse.STORE_ID) AS F LEFT JOIN bas_repair_type ON F.ITEM_CHARGE=bas_repair_type.REPTYP_ID)AS M WHERE 1=1 ");
		if(stomId!=null&&!"".equals(stomId))
			sb.append(" AND M.STOM_ID='"+stomId+"'");
		sb.append(" GROUP BY M.INDEX_ID");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
		  Object[] obj=null;
		  for (int i = 0; i < resultList.size(); i++) {
			StOutVo stOutVo=new StOutVo();
			obj=(Object[]) resultList.get(i);
			if(obj[0]!=null&&!"".equals(obj[0]))
				stOutVo.setPartsId(obj[0].toString()); 
			if(obj[1]!=null&&!"".equals(obj[1]))
				stOutVo.setPartsName(obj[1].toString());	
			if(obj[2]!=null&&!"".equals(obj[2]))
				stOutVo.setPunitName(obj[2].toString());
			if(obj[3]!=null&&!"".equals(obj[3]))
				stOutVo.setFitPtype(obj[3].toString());
			if(obj[4]!=null&&!"".equals(obj[4]))
				stOutVo.setTaxCast(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[4].toString()));
			if(obj[5]!=null&&!"".equals(obj[5]))
				stOutVo.setNotaxCast(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[5].toString()));
			if(obj[6]!=null&&!"".equals(obj[6]))
				stOutVo.setItemPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[6].toString()));
			if(obj[7]!=null&&!"".equals(obj[7]))
				stOutVo.setItemCount(obj[7].toString());
			if(obj[8]!=null&&!"".equals(obj[8]))
				stOutVo.setTaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[8].toString()));
			if(obj[9]!=null&&!"".equals(obj[9]))
				stOutVo.setNotaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[9].toString()));
			if(obj[10]!=null&&!"".equals(obj[10]))
				stOutVo.setAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[10].toString()));
			if(obj[11]!=null&&!"".equals(obj[11]))
				stOutVo.setPartsLibrary(obj[11].toString());
			if(obj[12]!=null&&!"".equals(obj[12]))
				stOutVo.setClaimsType(obj[12].toString()); 
			if(obj[13]!=null&&!"".equals(obj[13]))
				stOutVo.setPartsNowCount(obj[13].toString());
			if(obj[14]!=null&&!"".equals(obj[14]))
				stOutVo.setOutItemRemark(obj[14].toString());
			if(obj[15]!=null&&!"".equals(obj[15]))
				stOutVo.setStoreName(obj[15].toString());
			if(obj[16]!=null&&!"".equals(obj[16]))
				stOutVo.setChangePriceId(obj[16].toString());
			if(obj[17]!=null&&!"".equals(obj[17]))
				stOutVo.setItemCount1(obj[17].toString());
			if(obj[18]!=null&&!"".equals(obj[18]))
				stOutVo.setItemCharge(obj[18].toString());
			if(obj[19]!=null&&!"".equals(obj[19]))
				stOutVo.setStoreId(obj[19].toString());
			if(obj[22]!=null&&!"".equals(obj[22]))
				stOutVo.setClaimsId(Short.parseShort(obj[22].toString()));
			if(obj[23]!=null&&!"".equals(obj[23]))
				stOutVo.setItemChargeId(Short.parseShort(obj[23].toString()));
			list.add(stOutVo);
		 }
	   }
	   return list;
	}
	
	/**
	 * 出退单查询模块   出退单信息预加载
	 */
	@SuppressWarnings("unchecked")
	public Json loadStOutAndStRtPartsSearchInfo(final String startTime,String endTime,final String pbrdId,final String typeId,final String partsId,final String partsName,
			final String storeId,final String claimsName,final String outItemRemark,final int enterpriseId)throws Exception{
		List<StOutVo> list=new ArrayList<StOutVo>();
		StringBuffer sb=new StringBuffer("call stout_strtparts_search_pro(");
		if(startTime!=null&&!startTime.trim().equals(""))
			sb.append("'"+startTime.trim()+"',");
		else if(startTime==null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(value!=null)
			    sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value)) +"',");
			else
				sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+"")) +"',");
		}else
			sb.append("'',");
		if(endTime!=null&&!endTime.trim().equals(""))
			sb.append("'"+endTime.trim()+"',");
		else if(endTime==null){
			sb.append("'"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"',");
		}else
			sb.append("'',");
		if(pbrdId!=null&&!pbrdId.trim().equals(""))
			sb.append(""+pbrdId.trim()+",");
		else
			sb.append("'',");
		if(typeId!=null&&!typeId.trim().equals(""))
			sb.append(""+typeId.trim()+",");
		else
			sb.append("'',");
		if(partsId!=null&&!partsId.trim().equals(""))
			sb.append("'"+partsId.trim()+"',");
		else
			sb.append("'',");
		if(partsName!=null&&!partsName.trim().equals(""))
			sb.append("'"+partsName.trim()+"',");
		else
			sb.append("'',");
		if(storeId!=null&&!storeId.trim().equals(""))
			sb.append(""+storeId.trim()+",");
		else
			sb.append("'',");
		if(claimsName!=null&&!claimsName.trim().equals(""))
			sb.append("'"+claimsName.trim()+"',");
		else
			sb.append("'',");
		if(outItemRemark!=null&&!outItemRemark.trim().equals(""))
			sb.append("'"+outItemRemark.trim()+"',");
		else
			sb.append("'',");
		sb.append(""+enterpriseId+"");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =0;
        Object[] obj=null;
        if(resultList!=null&&resultList.size()>0){
        	count=resultList.size();
        	for (int i = 0; i < resultList.size(); i++) {
        		StOutVo sgsvo=new StOutVo();
        		obj=resultList.get(i);
        		if(obj[0]!=null&&!obj[0].equals(""))
        			sgsvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sgsvo.setPartsName(obj[1].toString());		
				if(obj[2]!=null&&!obj[2].equals(""))
					sgsvo.setPartsLibrary(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sgsvo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sgsvo.setPbrdName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sgsvo.setStoreName(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					sgsvo.setItemCount(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					sgsvo.setTaxCastamont(new BaseAction().doubleFormat(Double.parseDouble(obj[7].toString()))+"");
				if(obj[8]!=null&&!obj[8].equals(""))
					sgsvo.setPartsLatestTaxprice(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					sgsvo.setStoreId(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					sgsvo.setFitPtype(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					sgsvo.setPtypeName(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					sgsvo.setTaxPrice(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					sgsvo.setClaimsName(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					sgsvo.setOutItemRemark(obj[14].toString());
				if(startTime!=null&&!startTime.trim().equals(""))
					sgsvo.setStartTime(startTime.trim());
				if(endTime!=null&&!endTime.trim().equals(""))
					sgsvo.setEndTime(endTime.trim());
				if(obj[0]!=null&&!obj[0].equals("")&&obj[9]!=null&&!obj[9].equals("")){
					List resultList1=createSQLQuery("SELECT SUM(st_out_item.ITEM_COUNT) FROM st_out_item "+
					" WHERE st_out_item.PARTS_ID='"+obj[0].toString()+"'"+
					" AND st_out_item.STORE_ID="+obj[9].toString()+
					" GROUP BY st_out_item.PARTS_ID,st_out_item.STORE_ID");
				    if(resultList1!=null&&resultList1.size()>0)
				    	sgsvo.setOutPartsCount(resultList1.get(0).toString());
				    else
				    	sgsvo.setOutPartsCount(0+"");
				    List resultList2=createSQLQuery("SELECT SUM(st_rt_parts_detail.STRTPD_CNT) FROM st_rt_parts_detail "+
			    	" WHERE st_rt_parts_detail.STRTPM_ID LIKE '"+Contstants.QUITPARTS_TAG.SERVICEQUEITID+"%'"+
	    		    " AND st_rt_parts_detail.PARTS_ID='"+obj[0].toString()+"'"+
	    		    " AND st_rt_parts_detail.STORE_ID="+obj[9].toString()+
				    " GROUP BY st_rt_parts_detail.PARTS_ID,st_rt_parts_detail.STORE_ID ");
				    if(resultList2!=null&&resultList2.size()>0){
				    	sgsvo.setRtOutPartsCount(resultList2.get(0).toString());
				    }else
				    	sgsvo.setRtOutPartsCount(0+"");
				}
				list.add(sgsvo);
			}
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
		return json;
	}
	
	/**
	 * 出退单明细信息 预加载
	 */
	public Json loadStOutAndStRtPartsDetailSearchInfo(final String startTime,String endTime,final String partsId,
			final String storeId,final int enterpriseId)throws Exception{
		List<StOutVo> list=new ArrayList<StOutVo>();
		StringBuffer sb=new StringBuffer("call stout_strtparts_detail_search_pro(");
		if(startTime!=null&&!startTime.trim().equals("")&&!startTime.trim().equals("undefined"))
			sb.append("'"+startTime.trim()+"',");
		else if(startTime == null){
			sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue())) +"',");
		}else
			sb.append("'',");
		if(endTime!=null&&!endTime.trim().equals("")&&!endTime.trim().equals("undefined"))
			sb.append("'"+endTime.trim()+"',");
		else if(endTime == null){
			sb.append("'"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"',");
		}else
			sb.append("'',");
		
		if(partsId !=null && !partsId.trim().equals(""))
			sb.append("'"+partsId.trim()+"',");
		else
			sb.append("'',");
		
		if(storeId !=null && !storeId.trim().equals(""))
			sb.append(""+storeId.trim()+"");
		else
			sb.append("''");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =0;
        Object[] obj=null;
        if(resultList!=null&&resultList.size()>0){
        	count=resultList.size();
        	for (int i = 0; i < resultList.size(); i++) {
        		StOutVo sgsvo=new StOutVo();
        		obj=resultList.get(i);
        		if(obj[0]!=null&&!obj[0].equals(""))
        			sgsvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sgsvo.setPartsName(obj[1].toString());		
				if(obj[2]!=null&&!obj[2].equals(""))
					sgsvo.setPartsLibrary(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sgsvo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sgsvo.setPbrdName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sgsvo.setStoreName(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					sgsvo.setItemCount(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					sgsvo.setTaxCastamont(new BaseAction().doubleFormat(Double.parseDouble(obj[7].toString()))+"");
				if(obj[8]!=null&&!obj[8].equals(""))
					sgsvo.setPartsLatestTaxprice(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					sgsvo.setStoreId(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					sgsvo.setFitPtype(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					sgsvo.setPtypeName(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					sgsvo.setTaxPrice(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					sgsvo.setClaimsName(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					sgsvo.setOutItemRemark(obj[14].toString());
				if(obj[16]!=null&&!obj[16].equals(""))
					sgsvo.setTypeName(obj[16].toString());
				if(obj[18]!=null&&!obj[18].equals(""))
					sgsvo.setStomId(obj[18].toString());
				list.add(sgsvo);
			}
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
		return json;
	}
	
	
	
}