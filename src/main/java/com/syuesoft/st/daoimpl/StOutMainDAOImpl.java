package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.model.StOutMain;
import com.syuesoft.st.dao.StOutMainDAO;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.MyBeanUtils;

/**
 * 出库单汇总DAOImpl
 * @author SuMing
 */
@Repository("stOutMainDAO")
public class StOutMainDAOImpl extends BaseDaoImpl<StOutMain> implements StOutMainDAO {

	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 出库单汇总信息      综合查询
	 */
	public Json searchByCondition(final String sort,final String order,final int page, final int rows,
			final String stomDateStart,final String stomDateEnd, final String stomId,
			final String receptionId, final String carLicense,
			final String customName, final String carVan,final int enterpriseId) throws Exception{
		List<StOutVo> list=new ArrayList<StOutVo>();
		StringBuffer sb=new StringBuffer(
		" SELECT * FROM (SELECT c.STOM_DATE AS stomDateView,c.STOM_ID AS stomId,c.CAR_LICENSE AS carLicense,c.RECEPTION_ID AS receptionId,c.REPT_NAME AS reptName,c.CTYPE_NAME AS ctypeName,"+
		" c.CAR_LAST_REPAIR_DISTANCE AS carLastRepairDistance,c.CAR_VIN AS carVan,c.CUSTOM_NAME AS customName,c.STF_ID1,c.STF_NAME1 AS managerName,c.STF_ID2,"+
		" c.STF_NAME2 AS pickingMemberName,c.REPGRP_NAME AS repgrpName,c.STOM_REMARK AS stomRemark,c.INTER_DATE AS resvRealTime,c.TOTAL_NUM AS totalNum,c.STOM_SUM_AMOUNT AS stomSumAmount,"+
		" c.TAX_CASTAMONT AS taxCastamont,c.NOTAX_CASTAMONT AS notaxCastamont,bs3.STF_NAME AS stfName FROM (SELECT b.STOM_DATE,b.STOM_ID,"+
		" b.CAR_LICENSE,b.RECEPTION_ID,b.REPT_NAME,b.CTYPE_NAME,b.CAR_LAST_REPAIR_DISTANCE,b.CAR_VIN,"+
		" b.CUSTOM_NAME,b.STF_ID1,b.STF_NAME1,BS2.STF_ID AS STF_ID2,BS2.STF_NAME AS STF_NAME2,b.REPGRP_NAME,"+
		" b.STOM_REMARK,b.INTER_DATE,b.TOTAL_NUM,b.STOM_SUM_AMOUNT,b.TAX_CASTAMONT,b.NOTAX_CASTAMONT,"+
		" b.RECEPTOR FROM (SELECT a.STOM_DATE,a.STOM_ID,a.CAR_LICENSE,a.RECEPTION_ID,a.REPT_NAME,a.CTYPE_NAME,"+
		" a.CAR_LAST_REPAIR_DISTANCE,a.CAR_VIN,a.CUSTOM_NAME,BS1.STF_ID AS STF_ID1,BS1.STF_NAME AS STF_NAME1,"+
		" a.MANAGER,a.REPGRP_NAME,a.STOM_REMARK,a.INTER_DATE,a.TOTAL_NUM,a.STOM_SUM_AMOUNT,a.TAX_CASTAMONT,"+
		" a.NOTAX_CASTAMONT,a.RECEPTOR FROM (SELECT st_out_main.STOM_DATE,st_out_main.STOM_ID,frt_car.CAR_LICENSE,"+
		" frt_reception.RECEPTION_ID,reptype.REPT_NAME,bas_car_type.CTYPE_NAME,frt_car.CAR_LAST_REPAIR_DISTANCE,"+
		" frt_car.CAR_VIN,frt_custom.CUSTOM_NAME,st_out_main.PICKING_MEMBER,st_out_main.MANAGER,bas_repair_group.REPGRP_NAME,"+
		" st_out_main.STOM_REMARK,frt_reception.INTER_DATE,st_out_main.TOTAL_NUM,st_out_main.STOM_SUM_AMOUNT,"+
		" st_out_main.TAX_CASTAMONT,st_out_main.NOTAX_CASTAMONT,frt_reception.RECEPTOR FROM  st_out_main,"+
		" frt_reception,frt_car,reptype,bas_car_type,frt_custom,bas_repair_group WHERE st_out_main.CER_NO=frt_reception.RECEPTION_ID"+
		" AND frt_car.CAR_ID=frt_reception.CAR_ID AND reptype.REPT_ID=frt_reception.REPT_ID AND bas_car_type.CTYPE_ID=frt_car.CTYPE_ID"+
		" AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID AND bas_repair_group.REPGRP_ID=frt_reception.REPGRP_ID AND st_out_main.enterprise_id="+enterpriseId+") AS a"+
		" ,bas_stuff AS BS1 WHERE a.PICKING_MEMBER=bs1.STF_ID) AS b,bas_stuff AS BS2 WHERE b.MANAGER=bs2.STF_ID) AS c,"+
		" bas_stuff AS bs3 WHERE c.RECEPTOR=bs3.STF_ID ) e WHERE 1=1");
		if(stomDateStart!=null&&!stomDateStart.equals("")){
			if(stomDateEnd!=null&&!stomDateEnd.equals(""))
				sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d') BETWEEN '"+stomDateStart+"' AND '"+stomDateEnd+"'");
			else
				sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d') >= '"+stomDateStart+"'");
	    }else{
			if(stomDateEnd!=null&&!stomDateEnd.equals(""))
				sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d') <= '"+stomDateEnd+"'");
	    }
		if(stomDateStart == null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(value!=null)
				sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value))+ "'");
			else
				sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+""))+ "'");
		}
		if(stomDateEnd == null)
			sb.append(" and DATE_FORMAT(stomDateView,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
		if(stomId!=null&&!stomId.equals(""))
			sb.append(" and stomId like '%"+StringEscapeUtils.escapeSql(stomId.trim())+"%'");
		if(receptionId!=null&&!receptionId.equals(""))
			sb.append(" and receptionId like '%"+StringEscapeUtils.escapeSql(receptionId.trim())+"%'");
		if(carLicense!=null&&!carLicense.equals(""))
			sb.append(" and carLicense like '%"+StringEscapeUtils.escapeSql(carLicense.trim())+"%'");
		if(customName!=null&&!customName.equals(""))
			sb.append(" and customName like '%"+StringEscapeUtils.escapeSql(customName.trim())+"%'");
		if(carVan!=null&&!carVan.equals(""))
			sb.append(" and carVan like '%"+StringEscapeUtils.escapeSql(carVan.trim())+"%'");
		if (sort != null || order != null) 
		    sb.append(" order by " + sort + " " + order);
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList=createSQLQuery(sb.toString(), page, rows);
		else
			resultList=createSQLQuery(sb.toString());
		list=copyObjectVoToListVo(resultList, list);
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	public List<StOutVo> copyObjectVoToListVo(List<Object[]> resultList,List<StOutVo> list){
		if(resultList!=null&&resultList.size()>0){
			 Object[] obj=null;
			    for (int i = 0; i < resultList.size(); i++) {
			    	StOutVo sovo=new StOutVo();
			    	obj=(Object[]) resultList.get(i);
			    	if(obj[0]!=null&&!obj[0].equals("")){
			    		sovo.setStomDate(obj[0].toString());
			    		sovo.setStomDateView(obj[0].toString().substring(0, 10));
			    	}
			    	if(obj[1]!=null&&!obj[1].equals(""))
			    		sovo.setStomId(obj[1].toString());
			    	if(obj[2]!=null&&!obj[2].equals(""))
			    		sovo.setCarLicense(obj[2].toString());
			    	if(obj[3]!=null&&!obj[3].equals(""))
			    		sovo.setReceptionId(obj[3].toString());
			    	if(obj[4]!=null&&!obj[4].equals(""))
			    		sovo.setReptName(obj[4].toString());
			    	if(obj[5]!=null&&!obj[5].equals(""))
			    		sovo.setCtypeName(obj[5].toString());
			    	if(obj[6]!=null&&!obj[6].equals(""))
			    		sovo.setCarLastRepairDistance(obj[6].toString());
			    	if(obj[7]!=null&&!obj[7].equals(""))
			    		sovo.setCarVan(obj[7].toString());
			    	if(obj[8]!=null&&!obj[8].equals(""))
			    		sovo.setCustomName(obj[8].toString());
			    	if(obj[9]!=null&&!obj[9].equals(""))
			    		sovo.setPickingMember(obj[9].toString());
			    	if(obj[10]!=null&&!obj[10].equals(""))
			    		sovo.setPickingMemberName(obj[10].toString());
			    	if(obj[11]!=null&&!obj[11].equals(""))
			    		sovo.setManager(obj[11].toString());
			    	if(obj[12]!=null&&!obj[12].equals(""))
			    		sovo.setManagerName(obj[12].toString());
			    	if(obj[13]!=null&&!obj[13].equals(""))
			    		sovo.setRepgrpName(obj[13].toString());
			    	if(obj[14]!=null&&!obj[14].equals(""))
			    		sovo.setStomRemark(obj[14].toString());
			    	if(obj[15]!=null&&!obj[15].equals(""))
			    		sovo.setResvRealTime(obj[15].toString());
			    	if(obj[16]!=null&&!obj[16].equals(""))
			    		sovo.setTotalNum(obj[16].toString());
			    	if(obj[17]!=null&&!obj[17].equals(""))
			    		sovo.setStomSumAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[17].toString()));
			    	if(obj[18]!=null&&!obj[18].equals(""))
			    		sovo.setTaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[18].toString()));
			    	if(obj[19]!=null&&!obj[19].equals(""))
			    		sovo.setNotaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[19].toString()));
			    	if(obj[20]!=null&&!obj[20].equals(""))
			    		sovo.setStfName(obj[20].toString());
			    	list.add(sovo);
				}
			}
		return list;
	}
	
	/**
	 * 财务模块   工单配件查询    已结算配件出库退料详细信息查询
	 */
	public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetail(String receptionId,WorkOrderPartsQueryVo wopVo) throws Exception
	{
		if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
    		wopVo.setCustomName(new String(wopVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
    		wopVo.setCarLicense(new String(wopVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
    	if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
    		wopVo.setPartsName(new String(wopVo.getPartsName().getBytes("ISO-8859-1"),"UTF-8"));
		List<WorkOrderPartsQueryVo> list=new ArrayList<WorkOrderPartsQueryVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (");
		 sb.append(" (");
		sb.append(" SELECT st_out_main.STOM_ID,st_out_main.STOM_DATE, st_out_main.CER_NO,");
		sb.append(" st_out_item.PARTS_ID,frt_parts.PARTS_NAME, bas_parts_unit.PUNIT_NAME,");		 
		sb.append(" frt_parts.PARTS_LIBRARY,st_out_item.ITEM_COUNT, st_out_item.ITEM_PRICE AS A,");	
		sb.append(" (st_out_item.ITEM_COUNT*st_out_item.ITEM_PRICE) AS sumAmount,");
		sb.append(" st_out_item.ITEM_PRICE AS B,st_out_item.TAX_CASTAMONT,");
		sb.append(" ROUND(st_out_item.TAX_CASTAMONT/st_out_item.ITEM_COUNT,2) AS C,");		  
		sb.append(" st_out_item.STORE_ID,bas_claims_type.CLAIMS_NAME ");		  
		sb.append(" FROM frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,");		 
		sb.append(" st_out_main,st_out_item, frt_parts,bas_parts_brand,bas_parts_type,bas_parts_unit,bas_claims_type");		 
		sb.append(" WHERE 	frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID ");		  
		sb.append(" AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND frt_reception.CAR_ID=frt_car.CAR_ID");		 
		sb.append(" AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID");		 
		sb.append(" AND st_out_main.STOM_ID=st_out_item.STOM_ID AND st_out_main.CER_NO=frt_reception.RECEPTION_ID");		 
		sb.append(" AND st_out_item.PARTS_ID=frt_parts.PARTS_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID");		 
		sb.append(" AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);		 
		sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID  AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE");	
		 if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
		sb.append(" )");		 
		sb.append(" UNION");		  
		sb.append(" (");	 
		sb.append(" SELECT st_rt_parts_main.STRTPM_ID,st_rt_parts_main.STRTPM_DATE,");		 
		sb.append(" st_rt_parts_main.RECEPTION_ID, st_rt_parts_detail.PARTS_ID,");
		sb.append(" frt_parts.PARTS_NAME, bas_parts_unit.PUNIT_NAME,frt_parts.PARTS_LIBRARY,");		  
		sb.append(" st_rt_parts_detail.STRTPD_CNT,parts_change_price.PARTS_SELL_PRICE AS A, ");
		sb.append(" (st_rt_parts_detail.STRTPD_CNT*parts_change_price.PARTS_SELL_PRICE) AS sumAmount, ");
		sb.append(" parts_change_price.PARTS_SELL_PRICE AS B,");		  
		sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT,2) AS C,");		  
		sb.append(" ROUND(parts_change_price.PARTS_LATEST_TAXPRICE*st_rt_parts_detail.STRTPD_CNT/st_rt_parts_detail.STRTPD_CNT,2) AS D,");		  
		sb.append(" st_rt_parts_detail.STORE_ID,bas_claims_type.CLAIMS_NAME ");		 
		sb.append(" FROM st_rt_parts_main,st_rt_parts_detail,frt_parts,bas_parts_unit,parts_change_price,");		  
		sb.append(" frt_pre_clearing,frt_reception,frt_car,bas_car_type,frt_custom,");		 
		sb.append(" st_out_main,st_out_item,bas_parts_brand,bas_parts_type,bas_claims_type");		 
		sb.append(" WHERE 	frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID");		  
		sb.append(" AND frt_pre_clearing.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND frt_reception.CAR_ID=frt_car.CAR_ID");		 
		sb.append(" AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID AND frt_custom.CUSTOM_ID=frt_reception.CUSTOM_ID");		 
		sb.append(" AND st_out_main.STOM_ID=st_out_item.STOM_ID AND st_out_main.CER_NO=frt_reception.RECEPTION_ID");		 
		sb.append(" AND st_out_item.PARTS_ID=frt_parts.PARTS_ID AND bas_parts_type.PTYPE_ID=frt_parts.PTYPE_ID");		 
		sb.append(" AND bas_parts_brand.PBRD_ID=bas_parts_type.PBRD_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);		 
		sb.append(" AND st_rt_parts_detail.INDEX_ID=st_out_item.INDEX_ID  AND bas_claims_type.CLAIMS_ID=st_out_item.CLAIMS_TYPE");		
		sb.append(" AND  st_rt_parts_main.STRTPM_ID=st_rt_parts_detail.STRTPM_ID");		  
		sb.append(" AND st_rt_parts_main.RECEPTION_ID=frt_reception.RECEPTION_ID AND st_rt_parts_detail.PARTS_ID=frt_parts.PARTS_ID");		  
		sb.append(" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID AND st_rt_parts_detail.PARTS_ID=parts_change_price.PARTS_ID");		  
		sb.append(" AND st_rt_parts_detail.STORE_ID=parts_change_price.STORE_ID");	
		 if(wopVo.getPreclrTimeStart()!=null&&wopVo.getPreclrTimeStart().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME>='"+wopVo.getPreclrTimeStart()+"'");
         if(wopVo.getPreclrTimeEnd()!=null&&wopVo.getPreclrTimeEnd().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_TIME<='"+wopVo.getPreclrTimeEnd()+"'");
         if(wopVo.getPreclrInoiceType()!=null&&wopVo.getPreclrInoiceType().length()>0)
        	 sb.append(" AND frt_pre_clearing.PRECLR_INOICE_TYPE='"+wopVo.getPreclrInoiceType()+"'");
         if(wopVo.getRcptBranch()!=null&&wopVo.getRcptBranch().length()>0)
        	 sb.append(" AND frt_reception.RCPT_BRANCH='"+wopVo.getRcptBranch()+"'");
         if(wopVo.getCustomName()!=null&&wopVo.getCustomName().length()>0)
        	 sb.append(" AND frt_custom.CUSTOM_NAME LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getCustomName().trim())+"%'");
         if(wopVo.getCarLicense()!=null&&wopVo.getCarLicense().length()>0)
        	 sb.append(" AND frt_car.CAR_LICENSE LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getCarLicense().trim())+"%'");
         if(wopVo.getCarBrandId()!=null&&wopVo.getCarBrandId().length()>0)
        	 sb.append(" AND bas_car_type.CBRD_ID="+wopVo.getCarBrandId());
         if(wopVo.getCarTypeId()!=null&&wopVo.getCarTypeId().length()>0)
        	 sb.append(" AND bas_car_type.CTYPE_ID="+wopVo.getCarTypeId());
         if(wopVo.getStoreId()!=null&&wopVo.getStoreId().length()>0)
        	 sb.append(" AND st_out_item.STORE_ID="+wopVo.getStoreId());
         if(wopVo.getClaimsType()!=null&&wopVo.getClaimsType().length()>0)
        	 sb.append(" AND st_out_item.CLAIMS_TYPE in("+wopVo.getClaimsType()+")");
         if(wopVo.getPartsBrandId()!=null&&wopVo.getPartsBrandId().length()>0)
        	 sb.append(" AND bas_parts_brand.PBRD_ID="+wopVo.getPartsBrandId());
         if(wopVo.getPartsName()!=null&&wopVo.getPartsName().length()>0)
        	 sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+ StringEscapeUtils.escapeSql(wopVo.getPartsName().trim())+"%'");
         if(wopVo.getReptId()!=null&&wopVo.getReptId().length()>0)
        	 sb.append(" AND frt_reception.REPT_ID="+wopVo.getReptId());
		sb.append(" )");		 
		sb.append(" )AS A");	  
		sb.append(" WHERE A.CER_NO ='"+receptionId+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				WorkOrderPartsQueryVo wopq=new WorkOrderPartsQueryVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					wopq.setStomId(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					wopq.setStomDate(MyBeanUtils.getInstance().formatString(obj[1].toString()));
				if(obj[2]!=null&&!"".equals(obj[2]))
					wopq.setCerNo(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					wopq.setPartsId(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					wopq.setPartsName(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					wopq.setPunitName(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					wopq.setPartsLibrary(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					wopq.setItemCount(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					wopq.setItemPrice(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					wopq.setItemAmount(Double.parseDouble(obj[9].toString()));
				if(obj[10]!=null&&!"".equals(obj[10]))
					wopq.setItemPriceAvage(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11]))
					wopq.setTaxCastamont(obj[11].toString());
				if(obj[12]!=null&&!"".equals(obj[12]))
					wopq.setTaxCastamontAvage(obj[12].toString());
				if(obj[13]!=null&&!"".equals(obj[13]))
					wopq.setStoreId(obj[13].toString());
				if(obj[14]!=null&&!"".equals(obj[14]))
					wopq.setClaimsType(obj[14].toString());
				wopq.setCtypeName("");
				wopq.setState("open");
				wopq.set_parentId(receptionId);
				list.add(wopq);
			}
		}
		return list;
	}
	
	/**
	 * 财务模块    配件动态变化   出库退料配件信息  预加载
	 */
	 
	public List<PartsTrendsChangeSearchVo> loadStOutAndRtPartsInfo(PartsTrendsChangeSearchVo ptcsvo) throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		String sql= " SELECT * FROM StOutAndRtPartsSearchTable st where st.enterpriseId="+ptcsvo.getEnterpriseId();
		List<Object[]> resultList=createSQLQuery(sql);
		if(resultList!=null&&!"".equals(resultList)){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsTrendsChangeSearchVo stcsvo=new PartsTrendsChangeSearchVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					stcsvo.setIndexId(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					stcsvo.setPartsId(obj[1].toString());	
				if(obj[2]!=null&&!"".equals(obj[2]))
					stcsvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					stcsvo.setPartsLibrary(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					stcsvo.setPunitName(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					stcsvo.setItemCount(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					stcsvo.setAmount(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					stcsvo.setAvagePrice(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					stcsvo.setCastAmount(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					stcsvo.setAvageCastAmount(obj[9].toString());
				if(obj[10]!=null&&!"".equals(obj[10]))
					stcsvo.setStoreName(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11]))
					stcsvo.setStomDate(obj[11].toString().substring(0, 10));
				if(obj[12]!=null&&!"".equals(obj[12]))
					stcsvo.setCbrdName(obj[12].toString());
				if(obj[13]!=null&&!"".equals(obj[13]))
					stcsvo.setStypeName(obj[13].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}
	
	/**
	 * 财务模块    配件动态变化   出库退料配件信息   综合查询
	 */
	 
	public List<PartsTrendsChangeSearchVo> searchStOutAndRtPartsInfoByCondition(PartsTrendsChangeSearchVo ptcsvo) throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		StringBuffer sql=new StringBuffer("SELECT * FROM StOutAndRtPartsSearchTable AS A WHERE A.enterpriseId="+ptcsvo.getEnterpriseId());
		if(ptcsvo.getStomDateStart()!=null&&!"".equals(ptcsvo.getStomDateStart())){
				sql.append(" and DATE_FORMAT(A.STOM_DATE,'%Y-%m-%d') >='"+ptcsvo.getStomDateStart()+"'");
		}
		if(ptcsvo.getStomDateEnd()!=null&&!"".equals(ptcsvo.getStomDateEnd())){
		        sql.append(" and DATE_FORMAT(A.STOM_DATE,'%Y-%m-%d') <='"+ptcsvo.getStomDateEnd()+"'");
		}
		if(ptcsvo.getCbrdName()!=null&&!"".equals(ptcsvo.getCbrdName()))
			sql.append(" and A.CBRD_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getCbrdName().trim())+"%'");
		if(ptcsvo.getStypeName()!=null&&!"".equals(ptcsvo.getStypeName()))
			sql.append(" and A.CTYPE_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getStypeName().trim())+"%'");	
		if(ptcsvo.getPartsId()!=null&&!"".equals(ptcsvo.getPartsId()))
			sql.append(" and A.PARTS_ID  LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getPartsId().trim())+"%'");
		if(ptcsvo.getPartsName()!=null&&!"".equals(ptcsvo.getPartsName()))
			sql.append(" and A.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getPartsName().trim())+"%'");
		if(ptcsvo.getStoreName()!=null&&!"".equals(ptcsvo.getStoreName()))
			sql.append(" and A.STORE_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getStoreName().trim())+"%'");
		List<Object[]> resultList=createSQLQuery(sql.toString());
		if(resultList!=null&&!"".equals(resultList)){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsTrendsChangeSearchVo stcsvo=new PartsTrendsChangeSearchVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					stcsvo.setIndexId(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					stcsvo.setPartsId(obj[1].toString());	
				if(obj[2]!=null&&!"".equals(obj[2]))
					stcsvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					stcsvo.setPartsLibrary(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					stcsvo.setPunitName(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					stcsvo.setItemCount(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					stcsvo.setAmount(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					stcsvo.setAvagePrice(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					stcsvo.setCastAmount(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					stcsvo.setAvageCastAmount(obj[9].toString());
				if(obj[10]!=null&&!"".equals(obj[10]))
					stcsvo.setStoreName(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11]))
					stcsvo.setStomDate(obj[11].toString().substring(0, 10));
				if(obj[12]!=null&&!"".equals(obj[12]))
					stcsvo.setCbrdName(obj[12].toString());
				if(obj[13]!=null&&!"".equals(obj[13]))
					stcsvo.setStypeName(obj[13].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List getStOutMainByCerNo(String cerNo) {
		return this.getHibernateTemplate().find("select new StOutMain(stomId, cerNo, stomDate, pickingMember, stomRelationStf, totalNum, stomSumAmount, stomRemark, notaxCastamont, taxCastamont, reducepriceCoefficient) from StOutMain som where som.cerNo='" + cerNo + "'");
	}

	
}