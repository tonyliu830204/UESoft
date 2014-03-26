package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.COLLIGATES;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.st.dao.FrtRcptPartsDAO;
import com.syuesoft.st.dao.StRtPartsDetailDAO;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.Json;
/**
 * 前台接车配件表dao实现类
 * @author Suming
 */
@Repository("frtRcptPartsDAO")
public class FrtRcptPartsDAOImpl extends BaseDaoImpl<Object> implements FrtRcptPartsDAO{
	
	@Autowired StRtPartsDetailDAO stRtPartsDetailDAO;
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 工单退料模块  根据相对应工单获取工单对应计划用料信息
	 */
	public Json searchRecpPartsByReceptionId(final String receptionId)throws Exception
	{
		List<StRtPartsVo> list = new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (SELECT INDEX_ID,PARTS_ID,PARTS_NAME,"+
		" CASE WHEN "+
		" (SELECT STRTPD_CNT FROM ("+
		" SELECT SUM(srpd.STRTPD_CNT) AS STRTPD_CNT,srpm.RECEPTION_ID,srpd.INDEX_ID,srpd.PARTS_ID,srpd.STORE_ID "+
		" FROM st_rt_parts_detail AS srpd,st_rt_parts_main AS srpm "+
		" WHERE srpd.STRTPM_ID=srpm.STRTPM_ID GROUP BY srpd.INDEX_ID) AS A_1"+
		" WHERE A_1.INDEX_ID=g.INDEX_ID AND A_1.PARTS_ID=g.PARTS_ID"+
		" AND A_1.STORE_ID=g.STORE_ID AND A_1.RECEPTION_ID=g.CER_NO) "+
		" IS NULL THEN ITEM_COUNT"+
		" WHEN "+
		" (SELECT STRTPD_CNT FROM ("+
		" SELECT SUM(srpd.STRTPD_CNT) AS STRTPD_CNT,srpm.RECEPTION_ID,srpd.INDEX_ID,srpd.PARTS_ID,srpd.STORE_ID "+
		" FROM st_rt_parts_detail AS srpd,st_rt_parts_main AS srpm "+
		" WHERE srpd.STRTPM_ID=srpm.STRTPM_ID GROUP BY srpd.INDEX_ID) AS A_1"+
		" WHERE A_1.INDEX_ID=g.INDEX_ID AND A_1.PARTS_ID=g.PARTS_ID"+
		" AND A_1.STORE_ID=g.STORE_ID AND A_1.RECEPTION_ID=g.CER_NO)  IS NOT NULL "+
		" THEN "+
		" g.ITEM_COUNT-(SELECT STRTPD_CNT FROM ("+
		" SELECT SUM(srpd.STRTPD_CNT) AS STRTPD_CNT,srpm.RECEPTION_ID,srpd.INDEX_ID,srpd.PARTS_ID,srpd.STORE_ID "+
		" FROM st_rt_parts_detail AS srpd,st_rt_parts_main AS srpm "+
		" WHERE srpd.STRTPM_ID=srpm.STRTPM_ID GROUP BY srpd.INDEX_ID) AS A_1"+
		" WHERE A_1.INDEX_ID=g.INDEX_ID AND A_1.PARTS_ID=g.PARTS_ID"+
		" AND A_1.STORE_ID=g.STORE_ID AND A_1.RECEPTION_ID=g.CER_NO) "+
		" END"+
		" AS PASRT_NUM,"+
		" PUNIT_NAME,PARTS_LATEST_TAXPRICE,PARTS_SELL_PRICE,"+
		" REPTYP_NAME,CLAIMS_NAME,STORE_NAME,STORE_ID,g.CER_NO FROM ("+
		" SELECT f.*,bas_repair_type.REPTYP_NAME FROM ("+
		" SELECT e.*,bas_claims_type.CLAIMS_NAME FROM ("+
		" SELECT d.*,bas_storehouse.STORE_NAME FROM ("+
		" SELECT c.*, pcp.PARTS_LATEST_TAXPRICE, pcp.PARTS_SELL_PRICE FROM ("+
		" SELECT b.*,bas_parts_unit.PUNIT_NAME FROM ("+
		" SELECT a.*,frt_parts.PARTS_NAME,frt_parts.PUNIT_NAME AS PUNIT_ID FROM ("+
		" SELECT st_out_item.*,st_out_main.CER_NO FROM st_out_item "+
		" LEFT JOIN st_out_main ON st_out_main.STOM_ID=st_out_item.STOM_ID) AS a"+
		" LEFT JOIN frt_parts ON a.PARTS_ID=frt_parts.PARTS_ID) AS b"+
		" LEFT JOIN bas_parts_unit ON b.PUNIT_ID = bas_parts_unit.PUNIT_ID) AS c"+
		" LEFT JOIN parts_change_price AS pcp ON c.PARTS_ID= pcp.PARTS_ID AND c.STORE_ID= pcp.STORE_ID) AS d"+
		" LEFT JOIN bas_storehouse ON d.STORE_ID=bas_storehouse.STORE_ID) AS e"+
		" LEFT JOIN bas_claims_type ON e.CLAIMS_TYPE=bas_claims_type.CLAIMS_ID) AS f"+
		" LEFT JOIN bas_repair_type ON f.ITEM_CHARGE=bas_repair_type.REPTYP_ID ) AS g "+
		" ) AS h WHERE PASRT_NUM>0");
		if(receptionId!=null&&!receptionId.trim().equals(""))
		    sb.append(" AND CER_NO='"+receptionId.trim()+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		int count=getSQLCount(sb.toString(), null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtPartsVo stRtPartsVo=new StRtPartsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					stRtPartsVo.setIndexId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					stRtPartsVo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					stRtPartsVo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					stRtPartsVo.setPartsNum(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					stRtPartsVo.setPunitName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					stRtPartsVo.setPartsLatestTaxprice(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					stRtPartsVo.setSelldPrice(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					stRtPartsVo.setReptypName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					stRtPartsVo.setClaimsName(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					stRtPartsVo.setStoreName(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					stRtPartsVo.setStoreId(obj[10].toString());
				list.add(stRtPartsVo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}

	/**
	 * 根据工单号加载计划用料信息
	 */
	public Json searchByReceptionId(final String receptionId,final int page, final int rows,final int enterpriceId)throws Exception
	{
		List<StOutVo> list=new ArrayList<StOutVo>();
		StringBuffer sql=new StringBuffer("");
		if(receptionId!=null&&!receptionId.trim().equals(""))
		   sql.append("CALL recpparts_by_receptionid('"+receptionId.trim()+"',"+enterpriceId+")");
		int count=0;
		List<Object[]> resultList=createSQLQuery(sql.toString());
		if(resultList!=null&&resultList.size()>0){
			count =resultList.size();
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StOutVo sovo=new StOutVo();
				obj=(Object[])resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					sovo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sovo.setPartsName(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					sovo.setPunitId(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sovo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sovo.setFitPtype(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sovo.setTaxCast(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[5].toString()));
				if(obj[6]!=null&&!obj[6].equals(""))
					sovo.setNotaxCast(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[6].toString()));
				if(obj[7]!=null&&!obj[7].equals("")){
					sovo.setItemCount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString()));
					sovo.setItemCountHidden(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString()));
				}
				if(obj[8]!=null&&!obj[8].equals(""))
					sovo.setItemPrice(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[8].toString()));
				if(obj[9]!=null&&!obj[9].equals(""))
					sovo.setAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,obj[9].toString()));
				if(obj[10]!=null&&!obj[10].equals(""))
					sovo.setTaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[10].toString()));
				if(obj[11]!=null&&!obj[11].equals(""))
					sovo.setNotaxCastamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[11].toString()));
				if(obj[12]!=null&&!obj[12].equals(""))
					sovo.setPartsLibrary(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					sovo.setClaimsType(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals("")){
					if(Double.parseDouble(obj[14].toString())==0){
						BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,COLLIGATES.ZEROPARTSHOW,enterpriceId);
						if(bcinfo!=null&&!bcinfo.equals("")){
							String ciValue=bcinfo.getCiValue();
							if(ciValue.equals(Contstants.STOCKZEROPART.STOCKZEROPARTSHOW))//显示
								sovo.setPartsNowCount(obj[14].toString());
						}
					}else{
						sovo.setPartsNowCount(obj[14].toString());
					}
				}
				if(obj[15]!=null&&!obj[15].equals(""))
					sovo.setItemCharge(obj[15].toString());
				if(obj[17]!=null&&!obj[17].equals(""))
					sovo.setStoreId(obj[17].toString());
				if(obj[18]!=null&&!obj[18].equals(""))
					sovo.setStoreName(obj[18].toString());
				if(obj[19]!=null&&!obj[19].equals(""))
					sovo.setRcptpartsIndex(obj[19].toString());
				if(obj[20]!=null&&!obj[20].equals(""))
					sovo.setClaimsId(Short.parseShort(obj[20].toString()));
				if(obj[21]!=null&&!obj[21].equals(""))
					sovo.setItemChargeId(Short.parseShort(obj[21].toString()));
				list.add(sovo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
}