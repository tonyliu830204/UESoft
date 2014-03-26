package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.StMoveStorehouseMain;
import com.syuesoft.st.dao.StMoveStorehouseMainDAO;
import com.syuesoft.st.vo.StMoveStorehouseVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
/**
 * 移仓单汇总DaoImpl
 * @author SuMing
 */
@Repository("stMoveStorehouseMainDAO")
public class StMoveStorehouseMainDAOImpl extends BaseDaoImpl<StMoveStorehouseMain> implements
		StMoveStorehouseMainDAO {
	
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 *  移仓调拨单汇总      综合查询
	 */
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String msdmDateStart,final String msdmDateEnd,final String msdmManager,final String msdmId,final String msdmRemark,final int enterpriseId)throws Exception
	{
		List<StMoveStorehouseVo> list=new ArrayList<StMoveStorehouseVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM ("+
		" SELECT B.MSDM_ID AS msdmId,B.MSDM_DATE AS msdmDateView,B.MSDM_SUM_CNT AS msdmSumCnt,B.MSDM_SUM_AMONT AS msdmSumAmont,B.STF_NAME AS msdmManagerName,B.STF_ID,B.msdmRemark AS msdmRemark,"+
		" BS2.STORE_NAME AS msdmOutStorehouseName,B.STN1 AS msdmInStorehouseName,BS2.STORE_ID,B.ST1,B.EXAMINE_STATE AS examine  FROM (SELECT A.MSDM_ID,A.MSDM_DATE,A.MSDM_SUM_CNT,"+
		" A.MSDM_SUM_AMONT,A.STF_NAME,A.STF_ID,A.MSDM_REMARK as msdmRemark,BS1.STORE_NAME AS STN1,BS1.STORE_ID AS ST1,A.EXAMINE_STATE"+
		" FROM (SELECT st_move_storehouse_main.MSDM_ID,st_move_storehouse_main.MSDM_DATE,st_move_storehouse_main.MSDM_SUM_CNT,"+
		" st_move_storehouse_main.MSDM_SUM_AMONT,bas_stuff.STF_NAME,bas_stuff.STF_ID,st_move_storehouse_main.MSDM_REMARK,st_move_storehouse_main.EXAMINE_STATE"+
		" FROM st_move_storehouse_main,bas_stuff WHERE st_move_storehouse_main.MSDM_MANAGER=bas_stuff.STF_ID"+
		" AND st_move_storehouse_main.EXAMINE_STATE=1 AND st_move_storehouse_main.enterprise_id="+enterpriseId+") AS A,st_move_storehouse_main,bas_storehouse AS BS1 WHERE A.MSDM_ID=st_move_storehouse_main.MSDM_ID"+
		" AND st_move_storehouse_main.MSDM_IN_STOREHOUSE_ID=BS1.STORE_ID) AS B,st_move_storehouse_main,bas_storehouse AS BS2 "+
		" WHERE  B.MSDM_ID=st_move_storehouse_main.MSDM_ID AND st_move_storehouse_main.MSDM_OUT_STOREHOUSE_ID=BS2.STORE_ID ) AS e where 1=1 ");
		if(msdmManager!=null&&!msdmManager.trim().equals(""))
			sb.append(" and msdmManagerName like '%"+StringEscapeUtils.escapeSql(msdmManager.trim())+"%'");
		if(msdmId!=null&&!msdmId.trim().equals(""))
			sb.append(" and msdmId like '%"+StringEscapeUtils.escapeSql(msdmId.trim())+"%'");
		if(msdmRemark!=null&&!msdmRemark.trim().equals(""))
			sb.append(" and msdmRemark like '%"+StringEscapeUtils.escapeSql(msdmRemark.trim())+"%'");
		if(msdmDateStart!=null&&!msdmDateStart.trim().equals("")){
			if(msdmDateEnd!=null&&!msdmDateEnd.trim().equals(""))
				sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d') BETWEEN '"+msdmDateStart.trim()+"' AND '"+msdmDateEnd.trim()+"'");
			else
				sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d') >= '"+msdmDateStart.trim()+"'");
		}else{
			 if(msdmDateEnd!=null&&!msdmDateEnd.trim().equals(""))
				sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d') <= '"+msdmDateEnd.trim()+"'");
		}
		if(msdmDateStart == null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(value!=null)
				sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value))+ "'");
			else
				sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+""))+ "'");
		}
			
		if(msdmDateEnd == null)
			sb.append(" and DATE_FORMAT(msdmDateView,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
		if (sort != null || order != null) 
		    sb.append(" order by " + sort+ " " + order);
		int count=getSQLCount(sb.toString(), null);
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList=createSQLQuery(sb.toString(),page,rows);
		else
			resultList=createSQLQuery(sb.toString());
		list=copyObjectVoToListVo(resultList, list);
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	
	/**
	 *  移仓调拨单未审核汇总      预加载
	 */
	public Json loadUnExamineStMoveStorehouse(final int page, final int rows, final String sort,final String order,final int enterpriseId)throws Exception
	{
		List<StMoveStorehouseVo> list=new ArrayList<StMoveStorehouseVo>();
		StringBuffer sb=new StringBuffer("SELECT B.MSDM_ID,B.MSDM_DATE,B.MSDM_SUM_CNT,B.MSDM_SUM_AMONT,B.STF_NAME,B.STF_ID,B.MSDM_REMARK,"+
				" BS2.STORE_NAME,B.STN1,BS2.STORE_ID,B.ST1,B.EXAMINE_STATE  FROM (SELECT A.MSDM_ID,A.MSDM_DATE,A.MSDM_SUM_CNT,"+
				" A.MSDM_SUM_AMONT,A.STF_NAME,A.STF_ID,A.MSDM_REMARK,BS1.STORE_NAME AS STN1,BS1.STORE_ID AS ST1,A.EXAMINE_STATE"+
				" FROM (SELECT st_move_storehouse_main.MSDM_ID,st_move_storehouse_main.MSDM_DATE,st_move_storehouse_main.MSDM_SUM_CNT,"+
				" st_move_storehouse_main.MSDM_SUM_AMONT,bas_stuff.STF_NAME,bas_stuff.STF_ID,st_move_storehouse_main.MSDM_REMARK,st_move_storehouse_main.EXAMINE_STATE"+
				" FROM st_move_storehouse_main,bas_stuff WHERE st_move_storehouse_main.MSDM_MANAGER=bas_stuff.STF_ID"+
				" AND st_move_storehouse_main.EXAMINE_STATE=0 AND st_move_storehouse_main.enterprise_id="+enterpriseId+") AS A,st_move_storehouse_main,bas_storehouse AS BS1 WHERE A.MSDM_ID=st_move_storehouse_main.MSDM_ID"+
				" AND st_move_storehouse_main.MSDM_IN_STOREHOUSE_ID=BS1.STORE_ID) AS B,st_move_storehouse_main,bas_storehouse AS BS2 "+
				" WHERE  B.MSDM_ID=st_move_storehouse_main.MSDM_ID AND st_move_storehouse_main.MSDM_OUT_STOREHOUSE_ID=BS2.STORE_ID");
		if (sort != null || order != null) {
			String sortValue="";
			if(sort.trim().equals("msdmId"))
				sortValue="B.MSDM_ID";
			if(sort.equals("msdmDate"))
				sortValue="B.MSDM_DATE";
			if(sort.equals("msdmSumCnt"))
				sortValue="B.MSDM_SUM_CNT";
			if(sort.equals("msdmSumAmont"))
				sortValue="B.MSDM_SUM_AMONT";
			if(sort.equals("msdmManagerName"))
				sortValue="B.STF_NAME";
			if(sort.equals("msdmOutStorehouseName"))
				sortValue="B.STN1";
			if(sort.equals("msdmInStorehouseName"))
				sortValue="BS2.STORE_NAME";
			if(sort.equals("msdmRemark"))
				sortValue="B.MSDM_REMARK";
		    sb.append(" order by " + sortValue + " " + order);
		}
		int count=getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString(), page, rows);
		list=copyObjectVoToListVo(resultList, list);
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	
	/**
	 *  移仓调拨单汇总      综合查询
	 */
	public Json searchUnExamineByCondition(final String msdmDateStart,final String msdmDateEnd,final String msdmManager,final String msdmId,final String msdmRemark,final int enterpriseId)throws Exception
	{
		List<StMoveStorehouseVo> list=new ArrayList<StMoveStorehouseVo>();
		StringBuffer sb=new StringBuffer("SELECT B.MSDM_ID,B.MSDM_DATE,B.MSDM_SUM_CNT,B.MSDM_SUM_AMONT,B.STF_NAME,B.STF_ID,B.MSDM_REMARK,"+
				" BS2.STORE_NAME,B.STN1,BS2.STORE_ID,B.ST1,B.EXAMINE_STATE  FROM (SELECT A.MSDM_ID,A.MSDM_DATE,A.MSDM_SUM_CNT,"+
				" A.MSDM_SUM_AMONT,A.STF_NAME,A.STF_ID,A.MSDM_REMARK,BS1.STORE_NAME AS STN1,BS1.STORE_ID AS ST1,A.EXAMINE_STATE"+
				" FROM (SELECT st_move_storehouse_main.MSDM_ID,st_move_storehouse_main.MSDM_DATE,st_move_storehouse_main.MSDM_SUM_CNT,"+
				" st_move_storehouse_main.MSDM_SUM_AMONT,bas_stuff.STF_NAME,bas_stuff.STF_ID,st_move_storehouse_main.MSDM_REMARK,st_move_storehouse_main.EXAMINE_STATE"+
				" FROM st_move_storehouse_main,bas_stuff WHERE st_move_storehouse_main.MSDM_MANAGER=bas_stuff.STF_ID"+
				" AND st_move_storehouse_main.EXAMINE_STATE=0 AND st_move_storehouse_main.enterprise_id="+enterpriseId+") AS A,st_move_storehouse_main,bas_storehouse AS BS1 WHERE A.MSDM_ID=st_move_storehouse_main.MSDM_ID"+
				" AND st_move_storehouse_main.MSDM_IN_STOREHOUSE_ID=BS1.STORE_ID) AS B,st_move_storehouse_main,bas_storehouse AS BS2 "+
				" WHERE  B.MSDM_ID=st_move_storehouse_main.MSDM_ID AND st_move_storehouse_main.MSDM_OUT_STOREHOUSE_ID=BS2.STORE_ID");
		if(msdmManager!=null&&!msdmManager.trim().equals(""))
			sb.append(" and B.STF_NAME like '%"+StringEscapeUtils.escapeSql(msdmManager.trim())+"%'");
		if(msdmId!=null&&!msdmId.trim().equals(""))
			sb.append(" and B.MSDM_ID like '%"+StringEscapeUtils.escapeSql(msdmId.trim())+"%'");
		if(msdmRemark!=null&&!msdmRemark.trim().equals(""))
			sb.append(" and B.MSDM_REMARK like '%"+StringEscapeUtils.escapeSql(msdmRemark.trim())+"%'");
		if(msdmDateStart!=null&&!msdmDateStart.trim().equals("")){
			if(msdmDateEnd!=null&&!msdmDateEnd.trim().equals(""))
				sb.append(" and DATE_FORMAT(B.MSDM_DATE,'%Y-%m-%d') BETWEEN '"+msdmDateStart.trim()+"' AND '"+msdmDateEnd.trim()+"'");
			else
				sb.append(" and DATE_FORMAT(B.MSDM_DATE,'%Y-%m-%d') >= '"+msdmDateStart.trim()+"'");
		}else{
			 if(msdmDateEnd!=null&&!msdmDateEnd.trim().equals(""))
				sb.append(" and DATE_FORMAT(B.MSDM_DATE,'%Y-%m-%d') <= '"+msdmDateEnd.trim()+"'");
		}
		int count=getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		list=copyObjectVoToListVo(resultList, list);
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	public List<StMoveStorehouseVo> copyObjectVoToListVo(List<Object[]> resultList,List<StMoveStorehouseVo> list){
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StMoveStorehouseVo StMoveStorehouseVo=new StMoveStorehouseVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					StMoveStorehouseVo.setMsdmId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals("")){
					StMoveStorehouseVo.setMsdmDateView(obj[1].toString().substring(0,10));
					StMoveStorehouseVo.setMsdmDate(obj[1].toString().substring(0,19));
				}
				if(obj[2]!=null&&!obj[2].equals(""))
					StMoveStorehouseVo.setMsdmSumCnt(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					StMoveStorehouseVo.setMsdmSumAmont(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					StMoveStorehouseVo.setMsdmManagerName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					StMoveStorehouseVo.setMsdmManager(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					StMoveStorehouseVo.setMsdmRemark(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					StMoveStorehouseVo.setMsdmOutStorehouseName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					StMoveStorehouseVo.setMsdmInStorehouseName(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					StMoveStorehouseVo.setOutStoreId(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					StMoveStorehouseVo.setInStoreId(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals("")){
					if(Short.parseShort(obj[11].toString())==0)
						StMoveStorehouseVo.setExamine(new BaseAction().changeColor("未审核", "red"));
					if(Short.parseShort(obj[11].toString())==1)
						StMoveStorehouseVo.setExamine("已审核");
				}
				list.add(StMoveStorehouseVo);
			}
		}
		return list;
	}
	
}