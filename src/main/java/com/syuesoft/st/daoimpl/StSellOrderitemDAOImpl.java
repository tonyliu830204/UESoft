package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StSellOrderitem;
import com.syuesoft.st.dao.StSellOrderitemDAO;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.st.vo.StSellOrderStRtSellOrderSearchVo;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;
@Repository("stSellOrderitemDAO")
public class StSellOrderitemDAOImpl extends BaseDaoImpl<StSellOrderitem> implements
		StSellOrderitemDAO {

	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 仓库模块：双击销售汇总单号获取相关销售明细信息
	 * 财务模块：销售结算单明细模块    通过销售汇总单单号获取销售单明细信息
	 */
	public Json loadStSellOrderItemBySellmmId(final String sellmmId)
			throws Exception {
		Json json=new Json();
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		StringBuffer sb=new StringBuffer("SELECT bas_parts_type.PTYPE_NAME,frt_parts.PARTS_ID,frt_parts.PARTS_NAME"+
		" ,bas_parts_unit.PUNIT_NAME,st_sell_orderitem.SELLD_COST_PRICE,st_sell_orderitem.SELLD_PRICE,"+
		" st_sell_orderitem.SELLD_CNT,st_sell_orderitem.SELLD_COST_AMOUNT,st_sell_orderitem.SELLD_AMOUNT,"+
		" st_sell_orderitem.SELLD_CUT_RATE,(st_sell_orderitem.SELLD_AMOUNT-st_sell_orderitem.SELLD_AMOUNT*st_sell_orderitem.SELLD_CUT_RATE) AS A,"+
		" frt_parts.PARTS_LIBRARY,st_sell_orderitem.PARTS_NOW_COUNT,bas_storehouse.STORE_NAME,"+
        " st_sell_orderitem.SELL_REMARK,st_sell_orderitem.SELLD_INDEX,bas_storehouse.STORE_ID,frt_parts.FIT_PTYPE"+
		" FROM st_sell_orderitem left join frt_parts on st_sell_orderitem.PARTS_ID=frt_parts.PARTS_ID " +
		" left join  bas_parts_unit on bas_parts_unit.PUNIT_ID=frt_parts.PUNIT_NAME"+
		" left join  parts_change_price on st_sell_orderitem.PARTS_ID=parts_change_price.PARTS_ID and st_sell_orderitem.STORE_ID=parts_change_price.STORE_ID" +
		" left join  bas_storehouse on st_sell_orderitem.STORE_ID=bas_storehouse.STORE_ID"+
		" left join  bas_parts_type on frt_parts.PTYPE_ID=bas_parts_type.PTYPE_ID where 1=1 ");
		if(sellmmId!=null&&!sellmmId.equals(""))
			sb.append(" AND st_sell_orderitem.SELLMM_ID='"+sellmmId+"'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
        if(resultList!=null&&resultList.size()>0){
        	Object[]obj=null;
     	    for (int i = 0; i <resultList.size(); i++) {
     	    	obj=(Object[]) resultList.get(i);
     	    	StSellOrderVo sovo=new StSellOrderVo();
     	    	if(obj[0]!=null&&!"".equals(obj[0]))
     	    		sovo.setCtypeName(obj[0].toString());
     			if(obj[1]!=null&&!"".equals(obj[1]))
     				sovo.setPartsId(obj[1].toString());	
     			if(obj[2]!=null&&!"".equals(obj[2]))
     				sovo.setPartsName(obj[2].toString());
     			if(obj[3]!=null&&!"".equals(obj[3]))
     				sovo.setPunitName(obj[3].toString());
     			if(obj[4]!=null&&!"".equals(obj[4]))
     				sovo.setTaxCostPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[4].toString()));
     			if(obj[5]!=null&&!"".equals(obj[5]))
     				sovo.setSelldPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SELLDECIMAL,obj[5].toString()));
     			if(obj[6]!=null&&!"".equals(obj[6]))
     				sovo.setSelldCnt(obj[6].toString());
     			if(obj[7]!=null&&!"".equals(obj[7]))
     				sovo.setTaxCostPriceAmont( basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString()));
     			if(obj[8]!=null&&!"".equals(obj[8]))
     				sovo.setSelldAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SELLDECIMAL,obj[8].toString()));
     			if(obj[9]!=null&&!"".equals(obj[9]))
     				sovo.setSelldCutRate(obj[9].toString());
     			if(obj[10]!=null&&!"".equals(obj[10]))
     				sovo.setSelldCutPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SELLDECIMAL,obj[10].toString()));
     			if(obj[11]!=null&&!"".equals(obj[11]))
     				sovo.setPartsLibrary(obj[11].toString());
     			if(obj[12]!=null&&!"".equals(obj[12]))
     				sovo.setPartsNowCount(obj[12].toString());
     			if(obj[13]!=null&&!"".equals(obj[13]))
     				sovo.setStoreName(obj[13].toString());
     			if(obj[14]!=null&&!"".equals(obj[14]))
     				sovo.setSellRemark(obj[14].toString());
     			if(obj[15]!=null&&!"".equals(obj[15]))
     				sovo.setSelldIndex(obj[15].toString());
     			if(obj[16]!=null&&!"".equals(obj[16]))
     				sovo.setStoreId(obj[16].toString());
     			if(obj[17]!=null&&!"".equals(obj[17]))
     				sovo.setFitPtype(obj[17].toString());
     			if(Integer.parseInt(sovo.getSelldCnt())>0)
     				list.add(sovo);
     		}
        }
        json.setRows(list);
        json.setTotal(count);
        return json;
	}
	
	/**
	 * 销售退料单模块，根据退料单号加载销售退料单明细信息
	 */
	public Json searchByCondition(final String sellmmId)throws Exception
	{
		Json json=new Json();
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT st_sell_orderitem.SELLD_INDEX,st_sell_orderitem.PARTS_ID,frt_parts.PARTS_NAME,"+
		" (CASE WHEN "+
		" (SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail,"+
		" st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID"+
		" AND st_rt_parts_detail.INDEX_ID=st_sell_orderitem.SELLD_INDEX"+
		" AND st_rt_parts_detail.PARTS_ID=st_sell_orderitem.PARTS_ID"+
		" AND st_rt_parts_detail.STORE_ID=st_sell_orderitem.STORE_ID"+
		" ) IS NOT NULL THEN st_sell_orderitem.SELLD_CNT-(SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail,"+
		" st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID"+
		" AND st_rt_parts_detail.INDEX_ID=st_sell_orderitem.SELLD_INDEX"+
		" AND st_rt_parts_detail.PARTS_ID=st_sell_orderitem.PARTS_ID"+
		" AND st_rt_parts_detail.STORE_ID=st_sell_orderitem.STORE_ID"+
		" )"+
		" WHEN (SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail,"+
		" st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID"+
		" AND st_rt_parts_detail.INDEX_ID=st_sell_orderitem.SELLD_INDEX"+
		" AND st_rt_parts_detail.PARTS_ID=st_sell_orderitem.PARTS_ID"+
		" AND st_rt_parts_detail.STORE_ID=st_sell_orderitem.STORE_ID"+
		" ) IS NULL"+
		" THEN st_sell_orderitem.SELLD_CNT END) AS A,"+
		" bas_parts_unit.PUNIT_NAME,st_sell_orderitem.SELLD_COST_PRICE,st_sell_orderitem.SELLD_PRICE,"+
		" bas_storehouse.STORE_ID,bas_storehouse.STORE_NAME FROM st_sell_orderitem ,frt_parts,bas_parts_unit,"+
		" bas_storehouse WHERE st_sell_orderitem.PARTS_ID=frt_parts.PARTS_ID"+
		" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_sell_orderitem.STORE_ID=bas_storehouse.STORE_ID");
		if(sellmmId!=null&&!sellmmId.trim().equals(""))
			sb.append(" AND st_sell_orderitem.SELLMM_ID='"+sellmmId.trim()+"'");
		int count=getSQLCount(sb.toString(), null);
		json.setTotal(count);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtPartsVo srpvo=new StRtPartsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					srpvo.setIndexId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					srpvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					srpvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					srpvo.setPartsNum(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					srpvo.setPunitName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					srpvo.setSelldCostPrice(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					srpvo.setSelldPrice(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					srpvo.setStoreId(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					srpvo.setStoreName(obj[8].toString());
				if(Double.parseDouble(srpvo.getPartsNum())>0){
					list.add(srpvo);
				}
			}
		}
		json.setRows(list);
		return json;
	}
	
	/***
	 * 销售退料模块   销售单信息预加载
	 */
	public Json loadSellOrderInfo(final int page, final int rows,
			final String sort, final String order,final int enterprise_id)throws Exception{
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT T_M.SELLMM_ID,T_M.CAR_LICENSE,T_M.CUSTOM_NAME,T_M.SELLMM_DATE,T_M.enterprise_id " +
		" FROM (SELECT st_sell_order.SELLMM_ID,st_sell_order.CAR_LICENSE,frt_custom.CUSTOM_NAME," +
		" st_sell_order.SELLMM_DATE,st_sell_order.enterprise_id FROM st_sell_order LEFT JOIN frt_custom ON st_sell_order.SELLCUSTOM_ID" +
		" =frt_custom.CUSTOM_ID) AS T_M WHERE T_M.enterprise_id="+enterprise_id+" AND T_M.SELLMM_ID IN (SELECT T_F1.SELLMM_ID FROM " +
		" (SELECT st_sell_orderitem.SELLMM_ID,st_sell_orderitem.SELLD_INDEX,st_sell_orderitem.PARTS_ID," +
		" frt_parts.PARTS_NAME,SUM(CASE WHEN (st_sell_orderitem.SELLD_CNT-(SELECT st_rt_parts_detail.STRTPD_CNT" +
		" FROM st_rt_parts_detail,st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID" +
		" AND st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID " +
		" AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) IS NULL THEN st_sell_orderitem.SELLD_CNT " +
		" WHEN (st_sell_orderitem.SELLD_CNT-(SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail," +
		" st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID AND " +
		" st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID " +
		" AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) IS NOT NULL THEN (st_sell_orderitem.SELLD_CNT-" +
		" (SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail,st_rt_parts_main WHERE " +
		" st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID AND st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID " +
		" AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) END) AS A," +
		" bas_parts_unit.PUNIT_NAME,st_sell_orderitem.SELLD_COST_PRICE,st_sell_orderitem.SELLD_PRICE,bas_storehouse.STORE_ID,bas_storehouse.STORE_NAME " +
		" FROM st_sell_orderitem ,frt_parts,bas_parts_unit,bas_storehouse WHERE st_sell_orderitem.PARTS_ID=frt_parts.PARTS_ID " +
		" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_sell_orderitem.STORE_ID=bas_storehouse.STORE_ID " +
		" GROUP BY st_sell_orderitem.SELLMM_ID) AS T_F1 WHERE T_F1.A>0) ");
		if (sort != null || order != null) {
			String sortValue="";
			if(sort.trim().equals("sellmmId"))
				sortValue="SELLMM_ID";
			if(sort.trim().equals("carLicense"))
				sortValue="CAR_LICENSE";
			if(sort.trim().equals("customName"))
				sortValue="CUSTOM_NAME";
			if(sort.trim().equals("sellmmDate"))
				sortValue="SELLMM_DATE";
		    sb.append(" order by " + sortValue + " " + order);
        }
		int count =getSQLCount(sb.toString(),null);
		List<Object[]> resultList=createSQLQuery(sb.toString(), page, rows);
		if(resultList!=null&&!resultList.equals("")){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				obj=resultList.get(i);
				StRtPartsVo stRtPartsVo=new StRtPartsVo();
				if(obj[0]!=null&&!obj[0].equals(""))
					stRtPartsVo.setSellmmId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					stRtPartsVo.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					stRtPartsVo.setCustomName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					stRtPartsVo.setSellmmDate(obj[3].toString());
				list.add(stRtPartsVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
		
	}
	
	/***
	 * 销售退料模块   销售单信息  综合查询
	 */
	public Json searchStSellOrderByCondition(final String sellmmId,final int enterprise_id)throws Exception{
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT T_M.SELLMM_ID,T_M.CAR_LICENSE,T_M.CUSTOM_NAME,T_M.SELLMM_DATE,T_M.enterprise_id " +
		" FROM (SELECT st_sell_order.SELLMM_ID,st_sell_order.CAR_LICENSE,frt_custom.CUSTOM_NAME," +
		" st_sell_order.SELLMM_DATE,st_sell_order.enterprise_id FROM st_sell_order LEFT JOIN frt_custom ON st_sell_order.SELLCUSTOM_ID" +
		" =frt_custom.CUSTOM_ID) AS T_M WHERE T_M.enterprise_id="+enterprise_id+" AND T_M.SELLMM_ID IN (SELECT T_F1.SELLMM_ID FROM " +
		" (SELECT st_sell_orderitem.SELLMM_ID,st_sell_orderitem.SELLD_INDEX,st_sell_orderitem.PARTS_ID," +
		" frt_parts.PARTS_NAME,SUM(CASE WHEN (st_sell_orderitem.SELLD_CNT-(SELECT st_rt_parts_detail.STRTPD_CNT" +
		" FROM st_rt_parts_detail,st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID" +
		" AND st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID " +
		" AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) IS NULL THEN st_sell_orderitem.SELLD_CNT " +
		" WHEN (st_sell_orderitem.SELLD_CNT-(SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail," +
		" st_rt_parts_main WHERE st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID AND " +
		" st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID " +
		" AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) IS NOT NULL THEN (st_sell_orderitem.SELLD_CNT-" +
		" (SELECT st_rt_parts_detail.STRTPD_CNT FROM st_rt_parts_detail,st_rt_parts_main WHERE " +
		" st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID AND st_sell_orderitem.PARTS_ID=st_rt_parts_detail.PARTS_ID " +
		" AND st_sell_orderitem.STORE_ID=st_rt_parts_detail.STORE_ID AND st_rt_parts_main.RECEPTION_ID=st_sell_orderitem.SELLMM_ID )) END) AS A," +
		" bas_parts_unit.PUNIT_NAME,st_sell_orderitem.SELLD_COST_PRICE,st_sell_orderitem.SELLD_PRICE,bas_storehouse.STORE_ID,bas_storehouse.STORE_NAME " +
		" FROM st_sell_orderitem ,frt_parts,bas_parts_unit,bas_storehouse WHERE st_sell_orderitem.PARTS_ID=frt_parts.PARTS_ID " +
		" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_sell_orderitem.STORE_ID=bas_storehouse.STORE_ID " +
		" GROUP BY st_sell_orderitem.SELLMM_ID) AS T_F1 WHERE T_F1.A>0) ");
		if(sellmmId!=null&&!sellmmId.trim().equals(""))
			sb.append(" and T_M.SELLMM_ID='"+sellmmId.trim()+"'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&!resultList.equals("")){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				obj=resultList.get(i);
				StRtPartsVo stRtPartsVo=new StRtPartsVo();
				if(obj[0]!=null&&!obj[0].equals(""))
					stRtPartsVo.setSellmmId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					stRtPartsVo.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					stRtPartsVo.setCustomName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					stRtPartsVo.setSellmmDate(obj[3].toString());
				list.add(stRtPartsVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
		
	}
	
	/**
	 * 消退单查询模块  消退单信息预加载
	 */
	public Json loadStSellOrderStRtSellOrderSearchInfo(final String startTime,String endTime,final String pbrdId,final String ptypeId,final String partsId,final String partsName,final String storeId,final int enterpriseId)throws Exception{
		List<StSellOrderStRtSellOrderSearchVo> list=new ArrayList<StSellOrderStRtSellOrderSearchVo>();
		StringBuffer sb=new StringBuffer("call STSELLORDER_STRTSELLORDER_SEARCH_PRO(");
		if(startTime!=null&&!startTime.trim().equals(""))
			sb.append("'"+startTime.trim()+"',");
		else
			sb.append("'',");
		if(endTime!=null&&!endTime.trim().equals(""))
			sb.append("'"+endTime.trim()+"',");
		else
			sb.append("'',");
		if(pbrdId!=null&&!pbrdId.trim().equals(""))
			sb.append("'"+pbrdId.trim()+"',");
		else
			sb.append("'',");
		if(ptypeId!=null&&!ptypeId.trim().equals(""))
			sb.append("'"+ptypeId.trim()+"',");
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
		sb.append(""+enterpriseId+"");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =0;
        Object[] obj=null;
        if(resultList!=null&&resultList.size()>0){
        	count=resultList.size();
        	for (int i = 0; i < resultList.size(); i++) {
        		StSellOrderStRtSellOrderSearchVo sgsvo=new StSellOrderStRtSellOrderSearchVo();
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
					sgsvo.setSelldCnt(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					sgsvo.setSelldPrice(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					sgsvo.setSelldAmount(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					sgsvo.setSelldCostPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					sgsvo.setSelldCostAmount(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					sgsvo.setStoreId(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					sgsvo.setFitType(obj[12].toString());
				list.add(sgsvo);
			}
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
		return json;
	}
	
	
	
}