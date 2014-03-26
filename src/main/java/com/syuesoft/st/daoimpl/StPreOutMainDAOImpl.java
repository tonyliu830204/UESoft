package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StPreOutMain;
import com.syuesoft.st.dao.StPreOutMainDAO;
import com.syuesoft.st.vo.StPreOutVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Repository("stPreOutMainDAO")
public class StPreOutMainDAOImpl extends BaseDaoImpl<StPreOutMain> implements
		StPreOutMainDAO {
	
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 预出库汇总信息      综合查询
	 */
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String stpremTimeStart,final String stpremTimeEnd,final String receptionId,final String stpremId,final String stpremFlg,final int enterpriseId)throws Exception
	{
		List<StPreOutVo> list=new ArrayList<StPreOutVo>();
		StringBuffer sb=new StringBuffer("select * from (SELECT b.STPREM_ID as stpremId,b.STPREM_TIME as stpremTimeView,b.CUSTOM_NAME as customName,b.RECEPTION_ID as receptionId,b.STPREM_SUM_AMOUNT as stpremSumAmount,b.STPREM_SUM_COST as stpremSumCost,"+
		" b.STF_NAME1 as manager,b.dataValue as stpremFlg,b.STOM_REMARK,BS2.STF_ID,BS2.STF_NAME as stfName,b.NUM_TOTAL,b.CAR_LICENSE,"+
		" b.REPT_NAME,b.CTYPE_NAME,b.CAR_VIN,b.REPGRP_NAME as repgrpName,b.child_id FROM(SELECT a.STPREM_ID,a.STPREM_TIME,a.CUSTOM_NAME,"+
		" a.RECEPTION_ID,a.STPREM_SUM_AMOUNT,a.STPREM_SUM_COST,BS1.STF_ID AS STF_ID1,BS1.STF_NAME AS STF_NAME1,"+
		" a.dataValue,a.STOM_REMARK,a.STF_ID,a.NUM_TOTAL,a.CAR_LICENSE,a.REPT_NAME,a.CTYPE_NAME,a.CAR_VIN,"+
		" a.REPGRP_NAME ,a.child_id FROM (SELECT st_pre_out_main.STPREM_ID,st_pre_out_main.STPREM_TIME,frt_custom.CUSTOM_NAME,"+
		" frt_reception.RECEPTION_ID,st_pre_out_main.STPREM_SUM_AMOUNT,st_pre_out_main.STPREM_SUM_COST,"+
		" st_pre_out_main.STPREM_STF_ID,bas_childdictionary.dataValue,st_pre_out_main.STOM_REMARK,st_pre_out_main.STF_ID,"+
		" st_pre_out_main.NUM_TOTAL,frt_car.CAR_LICENSE,reptype.REPT_NAME,bas_car_type.CTYPE_NAME,frt_car.CAR_VIN,"+
		" bas_repair_group.REPGRP_NAME ,bas_childdictionary.child_id FROM st_pre_out_main,frt_reception,frt_custom,frt_car,reptype,bas_car_type,bas_repair_group ,bas_childdictionary"+
		" WHERE st_pre_out_main.enterprise_id="+enterpriseId+" AND st_pre_out_main.RECEPTION_ID=frt_reception.RECEPTION_ID AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"+
		" AND frt_car.CAR_ID=frt_reception.CAR_ID AND reptype.REPT_ID=frt_reception.REPT_ID AND bas_car_type.CTYPE_ID=frt_car.CTYPE_ID"+
		" AND bas_childdictionary.child_id=st_pre_out_main.STPREM_FLG GROUP BY st_pre_out_main.STPREM_ID ) AS a, bas_stuff AS BS1 WHERE a.STPREM_STF_ID=BS1.STF_ID ) AS b ,bas_stuff AS BS2"+
		" WHERE b.STF_ID=BS2.STF_ID) as e where 1=1 ");
		if(receptionId!=null&&!receptionId.equals(""))
			sb.append(" AND receptionId LIKE '%"+StringEscapeUtils.escapeSql(receptionId.trim())+"%'");
		if(stpremId!=null&&!stpremId.equals(""))
			sb.append(" AND stpremId LIKE '%"+StringEscapeUtils.escapeSql(stpremId.trim())+"%'");
		if(stpremFlg!=null&&!stpremFlg.equals(""))
			sb.append(" AND b.child_id="+stpremFlg);
		if(stpremTimeStart!=null&&!stpremTimeStart.equals("")){
			if(stpremTimeEnd!=null&&!stpremTimeEnd.equals(""))
				sb.append(" AND DATE_FORMAT(stpremTimeView,'%Y-%m-%d') between '"+stpremTimeStart+"'  AND  '"+stpremTimeEnd+"' ");
			else
				sb.append(" AND DATE_FORMAT(stpremTimeView,'%Y-%m-%d') >= '"+stpremTimeStart+"'");
		}else{
			if(stpremTimeEnd!=null&&!stpremTimeEnd.equals(""))
				sb.append(" AND DATE_FORMAT(stpremTimeView,'%Y-%m-%d') <= '"+stpremTimeEnd+"'");
		}
		if(stpremTimeStart == null){
			String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(time!=null)
				sb.append(" and DATE_FORMAT(stpremTimeView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
		}
		if(stpremTimeEnd == null)
			sb.append(" and DATE_FORMAT(stpremTimeView,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
		
		if (sort != null || order != null) 
		    sb.append(" order by " + sort + " " + order);
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList=createSQLQuery(sb.toString(),page,rows);
		else
			resultList=createSQLQuery(sb.toString());
		list=copyObjectVoToListVo(resultList, list);
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	public List<StPreOutVo> copyObjectVoToListVo(List<Object[]> resultList,List<StPreOutVo> list){
		 if(resultList!=null&&resultList.size()>0){
			 Object[] obj=null;
			    for (int i = 0; i < resultList.size(); i++) {
					obj=(Object[]) resultList.get(i);
					StPreOutVo spovo=new StPreOutVo();
					if(obj[0]!=null&&!obj[0].equals(""))
						spovo.setStpremId(obj[0].toString());
					if(obj[1]!=null&&!obj[1].equals("")){
						spovo.setStpremTimeView(obj[1].toString().substring(0, 10));
						spovo.setStpremTime(obj[1].toString().substring(0, 19));
					}
					if(obj[2]!=null&&!obj[2].equals(""))
						spovo.setCustomName(obj[2].toString());
					if(obj[3]!=null&&!obj[3].equals(""))
						spovo.setReceptionId(obj[3].toString());
					if(obj[4]!=null&&!obj[4].equals(""))
						spovo.setStpremSumAmount(obj[4].toString());
					if(obj[5]!=null&&!obj[5].equals(""))
						spovo.setStpremSumCost(obj[5].toString());
					if(obj[6]!=null&&!obj[6].equals(""))
						spovo.setManager(obj[6].toString());
					if(obj[7]!=null&&!obj[7].equals(""))
						spovo.setStpremFlg(obj[7].toString());
					if(obj[8]!=null&&!obj[8].equals(""))
						spovo.setStomRemark(obj[8].toString());
					if(obj[9]!=null&&!obj[9].equals(""))
						spovo.setStfId(obj[9].toString());
					if(obj[10]!=null&&!obj[10].equals(""))
						spovo.setStfName(obj[10].toString());
					if(obj[11]!=null&&!obj[11].equals(""))
						spovo.setNumTotal(obj[11].toString());
					if(obj[12]!=null&&!obj[12].equals(""))
						spovo.setCarLicense(obj[12].toString());
					if(obj[13]!=null&&!obj[13].equals(""))
						spovo.setReptName(obj[13].toString());
					if(obj[14]!=null&&!obj[14].equals(""))
						spovo.setCtypeName(obj[14].toString());
					if(obj[15]!=null&&!obj[15].equals(""))
						spovo.setCarVan(obj[15].toString());
					if(obj[16]!=null&&!obj[16].equals(""))
						spovo.setRepgrpName(obj[16].toString());
					if(obj[17]!=null&&!obj[17].equals(""))
						spovo.setStpremFlgId(obj[17].toString());
					list.add(spovo);
				}
		 }
		 return list;
	}
}