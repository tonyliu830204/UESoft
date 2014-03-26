package com.syuesoft.st.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.model.StSellOrder;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;

@Repository("stSellOrderDAO")
public class StSellOrderDAOImpl extends BaseDaoImpl<StSellOrder> implements StSellOrderDAO {
	
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 销售单汇总信息       综合查询
	 */
	public Json searchByCondition(final int page, final int rows,
			final String sort, final String order,final String sellmmDateStart,
			final String sellmmDateEnd, final String carLicense,
			final String sellmmId, final String customId,
			final String sellmmRemark,final int enterpriceId) throws Exception {
		List<StSellOrderVo> list = new ArrayList<StSellOrderVo>();
		StringBuffer sb = new StringBuffer("SELECT * FROM (SELECT k.SELLMM_ID AS sellmmId,k.SELLMM_DATE AS sellmmDateView,k.CAR_LICENSE AS carLicense,k.CUSTOM_ID,k.CUSTOM_NAME AS sellcustomName,"+
		" k.SELLMM_CNT AS sellmmCnt,k.SELLMM_SUM_AMOUNT AS sellmmSumAmount,k.SELLMM_SUM_COST AS sellmmSumCost,k.SELL_MANAGER,k.manager_name AS sellManagerName,"+
		" k.SELLMM_STF_ID,k.sell_stf_name AS sellmmStfName,k.BILL_TYPE AS billType,k.PSELL_ID,k.PSELL_NAME AS psellName,k.SELLMM_REMARK AS sellmmRemark,"+
		" k.SELLMM_CLEARING_STATUS AS sellmmClearingStatus,k.dataKey,k.dataValue AS priceType ,k.PRICE_QUOTIETY AS priceQuotiety,PRECLR_INOICE_TYPE_NAME,PRECLR_INOICE_TYPE,PRECLR_STATE,enterprise_id FROM("+
		" SELECT e.* ,bc2.dataValue AS PRECLR_INOICE_TYPE_NAME FROM("+
		" SELECT d.*,bs2.STF_NAME AS sell_stf_name ,bs2.STF_ID AS sell_stf_id FROM ("+
		" SELECT c.*,bs1.STF_NAME AS manager_name ,bs1.STF_ID AS manager_id FROM ("+
		" SELECT b.*,bas_childdictionary.dataKey,bas_childdictionary.dataValue FROM ("+
		" SELECT a.*,bas_parts_sell.PSELL_NAME FROM ("+
		" SELECT st_sell_order.*,frt_custom.CUSTOM_ID,frt_custom.CUSTOM_NAME"+
		" FROM st_sell_order LEFT JOIN frt_custom"+
		" ON st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID) AS a"+
		" LEFT JOIN bas_parts_sell  ON a.PSELL_ID=bas_parts_sell.PSELL_ID) AS b"+
		" LEFT JOIN  bas_childdictionary  ON b.PRICE_TYPE=bas_childdictionary.dataKey) AS c"+
		" LEFT JOIN bas_stuff AS bs1 ON c.SELL_MANAGER=bs1.STF_ID) AS d"+
		" LEFT JOIN bas_stuff AS bs2 ON d.SELLMM_STF_ID=bs2.STF_ID) AS e "+
		" LEFT JOIN bas_childdictionary bc2 ON e.PRECLR_INOICE_TYPE=bc2.dataKey) AS k"+
		" ) AS f WHERE enterprise_id="+enterpriceId);
		if (carLicense != null && !carLicense.equals("")) 
			sb.append(" and carLicense like '%" + StringEscapeUtils.escapeSql(carLicense.trim())+ "%'");
		if (sellmmId != null && !sellmmId.equals("")) 
			sb.append(" and sellmmId like '%" + StringEscapeUtils.escapeSql(sellmmId.trim()) + "%'");
		if (customId != null && !customId.equals("")) 
			sb.append(" and CUSTOM_ID ='" + StringEscapeUtils.escapeSql(customId.trim())+ "'");
		if (sellmmRemark != null && !sellmmRemark.equals("")) 
			sb.append(" and sellmmRemark like '%" + StringEscapeUtils.escapeSql(sellmmRemark.trim())+ "%'");
		if (sellmmDateStart != null && !sellmmDateStart.equals("")) {
			if (sellmmDateEnd != null && !sellmmDateEnd.equals(""))
				sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d') BETWEEN '"+ sellmmDateStart + "' AND '" + sellmmDateEnd + "'");
			else
				sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d') >= '"+ sellmmDateStart + "'");
		} else {
			if (sellmmDateEnd != null && !sellmmDateEnd.equals(""))
				sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d') <= '"+ sellmmDateEnd + "'");
		}
		if(sellmmDateStart == null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriceId).getCiValue();
			if(value!=null)
				sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value))+ "'");
			else
				sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+""))+ "'");
		}
		if(sellmmDateEnd == null)
			sb.append(" and DATE_FORMAT(sellmmDateView,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
		if (sort != null || order != null)
		    sb.append(" order by " + sort + " " + order);
		List<Object[]> resultList = new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList = createSQLQuery(sb.toString(), page, rows);
		else
			resultList = createSQLQuery(sb.toString());
		int count = getSQLCount(sb.toString(), null);
		list=copyObjectVoToListVo(resultList, list);
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	public List<StSellOrderVo> copyObjectVoToListVo(List<Object[]> resultList,List<StSellOrderVo> list){
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo ssovo = new StSellOrderVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					ssovo.setSellmmId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) {
					ssovo.setSellmmDate(obj[1].toString());
					ssovo.setSellmmDateView(obj[1].toString().substring(0, 10));
				}
				if (obj[2] != null && !obj[2].equals("")) 
					ssovo.setCarLicense(obj[2].toString());
				if (obj[3] != null && !obj[3].equals("")) 
					ssovo.setSellcustomId(obj[3].toString());
				if (obj[4] != null && !obj[4].equals("")) 
					ssovo.setSellcustomName(obj[4].toString());
				if (obj[5] != null && !obj[5].equals("")) 
					ssovo.setSellmmCnt(obj[5].toString());
				if (obj[6] != null && !obj[6].equals("")) 
					ssovo.setSellmmSumAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[6].toString()));
				if (obj[7] != null && !obj[7].equals("")) 
					ssovo.setSellmmSumCost(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString()));
				if (obj[8] != null && !obj[8].equals("")) 
					ssovo.setSellManager(obj[8].toString());
				if (obj[9] != null && !obj[9].equals("")) 
					ssovo.setSellManagerName(obj[9].toString());
				if (obj[10] != null && !obj[10].equals("")) 
					ssovo.setSellmmStfId(obj[10].toString());
				if (obj[11] != null && !obj[11].equals("")) 
					ssovo.setSellmmStfName(obj[11].toString());
				if (obj[12] != null && !obj[12].equals("")) 
					ssovo.setBillType(obj[12].toString());
				if (obj[13] != null && !obj[13].equals("")) 
					ssovo.setPsellId(obj[13].toString());
				if (obj[14] != null && !obj[14].equals(""))
					ssovo.setPsellName(obj[14].toString());
				if (obj[15] != null && !obj[15].equals(""))
					ssovo.setSellmmRemark(obj[15].toString());
				if (obj[16] != null && !obj[16].equals(""))
					ssovo.setSellmmClearingStatus(obj[16].toString());
				if (obj[17] != null && !obj[17].equals(""))
					ssovo.setPriceTypeId(obj[17].toString());
				if (obj[18] != null && !obj[18].equals(""))
					ssovo.setPriceType(obj[18].toString());
				if (obj[19] != null && !obj[19].equals(""))
					ssovo.setPriceQuotiety(obj[19].toString());
				if (obj[20] != null && !obj[20].equals(""))
					ssovo.setPreclrInoiceType(obj[20].toString());
				if (obj[21] != null && !obj[21].equals(""))
					ssovo.setPreclrInoiceTypeId(obj[21].toString());
				if (obj[22] != null && !obj[22].equals(""))
					ssovo.setPreclrState(obj[22].toString());
				list.add(ssovo);
			}
		}
		return list;
	}
	
	/**
	 * 财务模块   配件动态变化    消退单配件信息      预加载  
	 */
	 
	public List<PartsTrendsChangeSearchVo> loadSellPartsAndStRtParts()throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		String sql="SELECT * FROM SellPartsAndSellRtPartsTable";
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
					stcsvo.setStrtpmId(obj[12].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}
	
	/**
	 * 财务模块    配件动态变化   消退单配件信息   综合查询
	 */
	 
	public List<PartsTrendsChangeSearchVo> searchSellPartsAndStRtPartsByCondition(PartsTrendsChangeSearchVo ptcsvo) throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		StringBuffer sql=new StringBuffer("SELECT * FROM SellPartsAndSellRtPartsTable AS A where 1=1");
		if(ptcsvo.getStomDateStart()!=null&&!"".equals(ptcsvo.getStomDateStart())){
				sql.append(" and DATE_FORMAT(A.SELLMM_DATE,'%Y-%m-%d') >='"+ptcsvo.getStomDateStart()+"'");
		}
		if(ptcsvo.getStomDateEnd()!=null&&!"".equals(ptcsvo.getStomDateEnd())){
		        sql.append(" and DATE_FORMAT(A.SELLMM_DATE,'%Y-%m-%d') <='"+ptcsvo.getStomDateEnd()+"'");
		}
		if(ptcsvo.getPartsId()!=null&&!"".equals(ptcsvo.getPartsId()))
			sql.append(" and A.PARTS_ID  LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getPartsId().trim())+"%'");
		if(ptcsvo.getPartsName()!=null&&!"".equals(ptcsvo.getPartsName()))
			sql.append(" and A.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getPartsName().trim())+"%'");
		if(ptcsvo.getStoreName()!=null&&!"".equals(ptcsvo.getStoreName()))
			sql.append(" and A.STORE_NAME LIKE '%"+StringEscapeUtils.escapeSql(ptcsvo.getStoreName().trim())+"%'");
		List<Object[]> resultList=createSQLQuery(sql.toString());
		if(resultList!=null&&resultList.size()>0){
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
					stcsvo.setStrtpmId(obj[12].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}
	/**
	 * 采购员查询
	 * */
	
	public List<StSellOrderVo> findPartsStockPerson(int page,int rows,
			 String sb) throws Exception {
		List<StSellOrderVo> rowsList=new ArrayList();
		int nowPage=(rows*(page-1));
        PreparedStatement ps=this.getSession().connection().prepareStatement(sb+" limit "+nowPage+","+rows);
        ResultSet rs=ps.executeQuery();
        StSellOrderVo bs=null;
        while(rs.next()){
    		bs=new StSellOrderVo();
   			bs.setStfId(rs.getInt(1)+"");
   			bs.setStfName(rs.getString(2));
   			rowsList.add(bs);
        }
		return rowsList;
	}

}