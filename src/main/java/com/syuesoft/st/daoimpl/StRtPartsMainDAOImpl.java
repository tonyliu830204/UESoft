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
import com.syuesoft.model.StRtPartsMain;
import com.syuesoft.st.dao.StRtPartsMainDAO;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
@Repository("stRtPartsMainDAO")
public class StRtPartsMainDAOImpl extends BaseDaoImpl<StRtPartsMain> implements StRtPartsMainDAO{
	
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 工单退料信息    综合查询
	 */
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String strtpmDateStart,final String strtpmDateqiEnd,final String receptionId,final String carLicense,final String strtpmId,final int interpriseId)throws Exception{
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (SELECT b.STRTPM_ID as strtpmId,b.STRTPM_DATE as strtpmDate,b.STF_ID1,b.STF_NAME1 as stfName,b.CUSTOM_NAME as customName,b.RECEPTION_ID as receptionId,b.STRTPM_TYPE as strtpmType,"+
				" b.CAR_LICENSE as carLicense,b.STRTPM_SUM_CNT as strtpmSumCnt,b.STRTPM_AMONT as rcptpartsSumPrice,b.STRTPM_COST_AMONT as partsLatestTaxpriceAmont,bs2.STF_NAME as manager,b.STRTPM_REMARK as strtpmRemark FROM "+
				" (SELECT a.STRTPM_ID,a.STRTPM_DATE,a.CUSTOM_NAME,bs1.STF_ID AS STF_ID1,bs1.STF_NAME AS STF_NAME1,"+
				" a.RECEPTION_ID,a.STRTPM_TYPE,a.STRTPM_SUM_CNT,a.CAR_LICENSE,a.STRTPM_AMONT,a.MANAGER,a.STRTPM_COST_AMONT,"+
				" a.STRTPM_REMARK FROM (SELECT st_rt_parts_main.STRTPM_ID,st_rt_parts_main.STRTPM_DATE,frt_custom.CUSTOM_NAME,"+
				" st_rt_parts_main.STF_ID,st_rt_parts_main.RECEPTION_ID,st_rt_parts_main.STRTPM_TYPE,st_rt_parts_main.STRTPM_SUM_CNT,"+
				" frt_car.CAR_LICENSE,st_rt_parts_main.STRTPM_AMONT,st_rt_parts_main.MANAGER,st_rt_parts_main.STRTPM_COST_AMONT,"+
				" st_rt_parts_main.STRTPM_REMARK FROM st_rt_parts_main,frt_reception,frt_custom,frt_car WHERE st_rt_parts_main.enterprise_id="+interpriseId+""+
				" AND st_rt_parts_main.RECEPTION_ID=frt_reception.RECEPTION_ID AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"+
				" AND frt_reception.CAR_ID=frt_car.CAR_ID GROUP BY st_rt_parts_main.STRTPM_ID) AS a,bas_stuff AS bs1 "+
				" WHERE a.STF_ID=bs1.STF_ID) AS b ,bas_stuff AS bs2 WHERE b.MANAGER=bs2.STF_ID) AS e where 1=1");
		if(receptionId!=null&&!receptionId.trim().equals(""))
			sb.append(" AND receptionId like '%"+StringEscapeUtils.escapeSql(receptionId.trim())+"%'");
		if(carLicense!=null&&!carLicense.trim().equals(""))
			sb.append(" AND carLicense like '%"+StringEscapeUtils.escapeSql(carLicense.trim())+"%'");
		if(strtpmId!=null&&!strtpmId.trim().equals(""))
			sb.append(" AND strtpmId like '%"+StringEscapeUtils.escapeSql(strtpmId.trim())+"%'");
		if(strtpmDateStart!=null&&!strtpmDateStart.trim().equals("")){
			if(strtpmDateqiEnd!=null&&!strtpmDateqiEnd.trim().equals(""))
				sb.append(" AND strtpmDate BETWEEN '"+strtpmDateStart.trim()+"' AND '"+strtpmDateqiEnd.trim()+"'");
			else
				sb.append(" AND strtpmDate >= '"+strtpmDateStart.trim()+"'");
		}else{
			 if(strtpmDateqiEnd!=null&&!strtpmDateqiEnd.trim().equals(""))
				sb.append(" AND strtpmDate <= '"+strtpmDateqiEnd.trim()+"'");
		}
		if(strtpmDateStart == null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,interpriseId).getCiValue();
			if(value!=null)
				sb.append(" and DATE_FORMAT(strtpmDate,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value))+ "'");
			else
				sb.append(" and DATE_FORMAT(strtpmDate,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+""))+ "'");
		}
		if(strtpmDateqiEnd == null)
			sb.append(" and DATE_FORMAT(strtpmDate,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
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

	public List<StRtPartsVo> copyObjectVoToListVo(List<Object[]> resultList,List<StRtPartsVo> list){
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtPartsVo stRtPartsVo=new StRtPartsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					stRtPartsVo.setStrtpmId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					stRtPartsVo.setStrtpmDate(obj[1].toString().substring(0,10));
				if(obj[2]!=null&&!obj[2].equals(""))
					stRtPartsVo.setStfId(obj[2].toString());	
				if(obj[3]!=null&&!obj[3].equals(""))
					stRtPartsVo.setStfName(obj[3].toString());	
				if(obj[4]!=null&&!obj[4].equals(""))
					stRtPartsVo.setCustomName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					stRtPartsVo.setReceptionId(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					stRtPartsVo.setStrtpmType(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					stRtPartsVo.setCarLicense(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					stRtPartsVo.setStrtpmSumCnt(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					stRtPartsVo.setRcptpartsSumPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					stRtPartsVo.setPartsLatestTaxpriceAmont(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					stRtPartsVo.setManager(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					stRtPartsVo.setStrtpmRemark(obj[12].toString());
			    list.add(stRtPartsVo);
			}
		}
		return list;
	}
	
	/**
	 * 销售退料汇总信息      预 加载
	 */
	public Json loadSellStRtPartsMain(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception{
		Json json=new Json();
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("select * from (SELECT b.STRTPM_ID as strtpmId,b.STRTPM_DATE as strtpmDate,b.STF_ID1,b.STF_NAME1 as stfName,b.CUSTOM_NAME as customName,b.RECEPTION_ID as receptionId,b.STRTPM_TYPE as strtpmType,"+
		" b.CAR_LICENSE as carLicense,b.STRTPM_SUM_CNT as strtpmSumCnt,b.STRTPM_AMONT as rcptpartsSumPrice,b.STRTPM_COST_AMONT as partsLatestTaxpriceAmont,bs2.STF_NAME as manager,b.STRTPM_REMARK as strtpmRemark,b.enterprise_id FROM(SELECT a.STRTPM_ID,a.STRTPM_DATE,"+
		" bs1.STF_ID AS STF_ID1,bs1.STF_NAME AS STF_NAME1,a.CUSTOM_NAME,a.RECEPTION_ID,a.STRTPM_TYPE"+
		" ,a.CAR_LICENSE,a.STRTPM_SUM_CNT,a.STRTPM_AMONT,a.STRTPM_COST_AMONT,a.MANAGER,a.STRTPM_REMARK,a.enterprise_id FROM"+
		" (SELECT st_rt_parts_main.STRTPM_ID,st_rt_parts_main.STRTPM_DATE,st_rt_parts_main.STF_ID,frt_custom.CUSTOM_NAME"+
		" ,st_rt_parts_main.RECEPTION_ID,st_rt_parts_main.STRTPM_TYPE,st_sell_order.CAR_LICENSE,st_rt_parts_main.STRTPM_SUM_CNT "+
		" ,st_rt_parts_main.STRTPM_AMONT,st_rt_parts_main.STRTPM_COST_AMONT,st_rt_parts_main.MANAGER,st_rt_parts_main.STRTPM_REMARK"+
		" ,st_rt_parts_main.enterprise_id FROM st_rt_parts_main,st_sell_order,frt_custom WHERE st_sell_order.SELLMM_ID=st_rt_parts_main.RECEPTION_ID"+
		" AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID " +
		" and st_sell_order.enterprise_id="+enterprise_id+"" +
		" GROUP BY st_rt_parts_main.STRTPM_ID) AS a,bas_stuff AS bs1 "+
		" WHERE a.STF_ID=bs1.STF_ID)AS b,bas_stuff AS bs2 WHERE b.MANAGER=bs2.STF_ID) as f where enterprise_id="+enterprise_id);
		if (sort != null || order != null) 
		    sb.append(" order by " + sort + " " + order);
		int count=getSQLCount(sb.toString(), null);
		json.setTotal(count);
		List<Object[]> resultList=createSQLQuery(sb.toString(), page, rows);
		list =copy_Rt_ObjectVoToListVo(resultList, list);
		json.setRows(list);
		return json;
	}
	/**
	 * 销售退料模块   销售退料汇总单信息综合查询，加载
	 */
	public Json searchSellStRtPartsMainByCondition(final String strtpmDateStart,final String strtpmDateqiEnd,final String receptionId,final String carLicense,final String strtpmId,final int enterprise_id)throws Exception{
		Json json=new Json();
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT b.STRTPM_ID,b.STRTPM_DATE,b.STF_ID1,b.STF_NAME1,b.CUSTOM_NAME,b.RECEPTION_ID,b.STRTPM_TYPE,"+
		" b.CAR_LICENSE,b.STRTPM_SUM_CNT,b.STRTPM_AMONT,b.STRTPM_COST_AMONT,bs2.STF_NAME,b.STRTPM_REMARK,b.enterprise_id FROM(SELECT a.STRTPM_ID,a.STRTPM_DATE,"+
		" bs1.STF_ID AS STF_ID1,bs1.STF_NAME AS STF_NAME1,a.CUSTOM_NAME,a.RECEPTION_ID,a.STRTPM_TYPE"+
		" ,a.CAR_LICENSE,a.STRTPM_SUM_CNT,a.STRTPM_AMONT,a.STRTPM_COST_AMONT,a.MANAGER,a.STRTPM_REMARK,a.enterprise_id FROM"+
		" (SELECT st_rt_parts_main.STRTPM_ID,st_rt_parts_main.STRTPM_DATE,st_rt_parts_main.STF_ID,frt_custom.CUSTOM_NAME"+
		" ,st_rt_parts_main.RECEPTION_ID,st_rt_parts_main.STRTPM_TYPE,st_sell_order.CAR_LICENSE,st_rt_parts_main.STRTPM_SUM_CNT "+
		" ,st_rt_parts_main.STRTPM_AMONT,st_rt_parts_main.STRTPM_COST_AMONT,st_rt_parts_main.MANAGER,st_rt_parts_main.STRTPM_REMARK,st_rt_parts_main.enterprise_id"+
		" FROM st_rt_parts_main,st_sell_order,frt_custom WHERE st_sell_order.SELLMM_ID=st_rt_parts_main.RECEPTION_ID"+
		" AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID " +
		" GROUP BY st_rt_parts_main.STRTPM_ID) AS a,bas_stuff AS bs1 "+
		" WHERE a.STF_ID=bs1.STF_ID)AS b,bas_stuff AS bs2 WHERE b.MANAGER=bs2.STF_ID and b.enterprise_id="+enterprise_id);
		if(receptionId!=null&&!receptionId.equals(""))
			sb.append(" AND b.RECEPTION_ID like '%"+StringEscapeUtils.escapeSql(receptionId.trim())+"%'");
		if(carLicense!=null&&!carLicense.equals(""))
			sb.append(" AND b.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(carLicense.trim())+"%'");
		if(strtpmId!=null&&!strtpmId.equals(""))
			sb.append(" AND b.STRTPM_ID like '%"+StringEscapeUtils.escapeSql(strtpmId.trim())+"%'");
		if(strtpmDateStart!=null&&!strtpmDateStart.equals("")){
			if(strtpmDateqiEnd!=null&&!strtpmDateqiEnd.equals(""))
				sb.append(" AND DATE_FORMAT(b.STRTPM_DATE,'%Y-%m-%d') BETWEEN '"+strtpmDateStart+"' AND '"+strtpmDateqiEnd+"'");
			else
				sb.append(" AND DATE_FORMAT(b.STRTPM_DATE,'%Y-%m-%d') >= '"+strtpmDateStart+"'");
		}else{
			if(strtpmDateqiEnd!=null&&!strtpmDateqiEnd.equals(""))
				sb.append(" AND DATE_FORMAT(b.STRTPM_DATE,'%Y-%m-%d') <= '"+strtpmDateqiEnd+"'");
		}
		sb.append(" GROUP BY b.STRTPM_ID");
		int count=getSQLCount(sb.toString(), null);
		json.setTotal(count);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		list =copy_Rt_ObjectVoToListVo(resultList, list);
		json.setRows(list);
		return json;
	}
	
	public List<StRtPartsVo> copy_Rt_ObjectVoToListVo(List<Object[]> resultList,List<StRtPartsVo> list){
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i <resultList.size(); i++) {
				StRtPartsVo stRtPartsVo=new StRtPartsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					stRtPartsVo.setStrtpmId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals("")){
					stRtPartsVo.setStrtpmDate(obj[1].toString().substring(0, 19));
					stRtPartsVo.setStrtpmDateView(obj[1].toString().substring(0, 10));
				}
				if(obj[2]!=null&&!obj[2].equals(""))
					stRtPartsVo.setStfId(obj[2].toString());	
				if(obj[3]!=null&&!obj[3].equals(""))
					stRtPartsVo.setStfName(obj[3].toString());	
				if(obj[4]!=null&&!obj[4].equals(""))
					stRtPartsVo.setCustomName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					stRtPartsVo.setReceptionId(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					stRtPartsVo.setStrtpmType(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					stRtPartsVo.setCarLicense(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					stRtPartsVo.setStrtpmSumCnt(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					stRtPartsVo.setRcptpartsSumPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					stRtPartsVo.setPartsLatestTaxpriceAmont(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					stRtPartsVo.setManager(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					stRtPartsVo.setStrtpmRemark(obj[12].toString());
			    list.add(stRtPartsVo);
			}
		}
		return list;
	}
	
}