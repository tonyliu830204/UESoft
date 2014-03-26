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
import com.syuesoft.contstants.Contstants.PARTSTOCESEARCH;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.FrtParts;
import com.syuesoft.st.dao.FrtPartsDAO;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;
@Repository("frtPartsDAO")
public class FrtPartsDAOImpl extends BaseDaoImpl<FrtParts> implements FrtPartsDAO {

	@Autowired
	BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 配件库存量      综合查询
	 */
	public List<PartsNowCountVo> searchPartsNowCountByCondition(final int page, final int rows, final String sort,final String order,final String partsId,final String  partsName,final String posName,final String  partsLibrary,final String  partsId2,final String  pbrdName,final String  ptypeName,final String  storeName,final String partsNowCount,final String searchStyle,final String pnc_strtgmDateStart,final String pnc_strtgmDateEnd,final String storageDateStart ,final String storageDateEnd,final String sgs_partsId,final int enterpriseId)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		StringBuffer sb=new StringBuffer(" ");
		sb.append(" select * from (SELECT CHANGE_PRICE_ID AS changePriceId,PARTS_ID AS partsId,PARTS_ID2 AS partsId2,");
		sb.append(" PARTS_NAME AS partsName,STORE_ID AS storeId,STORE_NAME AS storeName,PUNIT_NAME AS punitName");
		sb.append(" ,PTYPE_NAME AS ptypeName,PBRD_NAME AS pbrdName,");
		sb.append(" PARTS_REPAIR_PRICE AS partsRepairPrice,PARTS_SELL_PRICE AS partsSellPrice");
		sb.append(" ,PARTS_POINT_PRICE AS partsPointPrice,PARTS_SPECIAL_PRICE AS partsSpecialPrice");
		sb.append(" ,PARTS_CLAIMANT_PRICE AS partsClaimantPrice,PARTS_LATEST_TAXPRICE AS partsLatestTaxprice,");
		sb.append(" PARTS_LATEST_NOTAXPRICE AS partsLatestNotaxprice,PARTS_NOW_COUNT AS partsNowCount");
		sb.append(" ,STOCK_UPPER AS stockUpper,STOCK_LOWER AS stockLower,POS_NAME AS posName");
		if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals("")))
			sb.append(",ST_OUT.ST_OUT_INTERVAL_DAY,ST_OUT.ST_OUT_NEAR_DATE");
		if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals("")))
			sb.append(",ST_STORAGE.ST_GOODS_INTERVAL_DAY,ST_STORAGE.ST_GOODS_NEAR_DATE");
		sb.append(" ,PARTS_LIBRARY  AS partsLibrary,POS_ID AS posId,fitPtype,enterpriseId FROM (");
		sb.append(" SELECT e.*,bas_parts_position.POS_NAME FROM");
		sb.append(" (SELECT d.*,bas_parts_brand.PBRD_NAME FROM");
		sb.append(" (SELECT c.*,bas_parts_type.PTYPE_NAME,bas_parts_type.PBRD_ID FROM ");
		sb.append(" (SELECT b.*,bas_parts_unit.PUNIT_NAME FROM ");
		sb.append(" (SELECT a.*,bas_storehouse.STORE_NAME FROM ");
		sb.append(" (SELECT parts_change_price.*,frt_parts.PARTS_ID2,frt_parts.PARTS_NAME,frt_parts.PTYPE_ID,");
		sb.append(" frt_parts.PUNIT_NAME AS PUNIT_ID,frt_parts.FIT_PTYPE AS fitPtype,frt_parts.POS_NAME AS POS_ID,");
		sb.append(" frt_parts.PARTS_LIBRARY,frt_parts.enterprise_id as enterpriseId FROM parts_change_price,frt_parts ");
		sb.append(" where parts_change_price.PARTS_ID=frt_parts.PARTS_ID and frt_parts.PARTS_FLAG=TRUE  )AS a");
		sb.append(" LEFT JOIN bas_storehouse ON a.STORE_ID=bas_storehouse.STORE_ID)AS b ");
		sb.append(" LEFT JOIN bas_parts_unit ON b.PUNIT_ID=bas_parts_unit.PUNIT_ID)AS c ");
		sb.append(" LEFT JOIN bas_parts_type ON c.PTYPE_ID=bas_parts_type.PTYPE_ID)AS d ");
		sb.append(" LEFT JOIN bas_parts_brand ON d.PBRD_ID=bas_parts_brand.PBRD_ID)AS e ");
		sb.append(" LEFT JOIN bas_parts_position ON bas_parts_position.POS_ID=e.POS_ID) as f");
		if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))){
			sb.append(",(SELECT soi.PARTS_ID,soi.STORE_ID,MAX(som.STOM_DATE)AS ST_OUT_NEAR_DATE,DATEDIFF(NOW(),MAX(som.STOM_DATE))AS ST_OUT_INTERVAL_DAY ");
			sb.append(" FROM st_out_item AS soi,st_out_main AS som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)AS ST_OUT");
		}
	    if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))){
        	sb.append(",(SELECT DISTINCT ssi.PARTS_ID,sgs.STORE_ID,");
			sb.append(" MAX(sgs.STORAGE_DATE)AS ST_GOODS_NEAR_DATE,DATEDIFF(NOW(),MAX(sgs.STORAGE_DATE))AS ST_GOODS_INTERVAL_DAY ");
			sb.append(" FROM st_storage_item ssi,st_goods_storage sgs WHERE ssi.STORAGE_ID=sgs.STORAGE_ID AND (");
			sb.append(" ssi.PARTS_ID NOT IN(SELECT soi.PARTS_ID FROM st_out_item soi,st_out_main som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)");
			sb.append(" OR sgs.STORE_ID NOT IN(SELECT soi.STORE_ID FROM st_out_item soi,st_out_main som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)");
			sb.append(" )GROUP BY ssi.PARTS_ID, sgs.STORE_ID)AS ST_STORAGE ");
		}
	    sb.append(" ) as g where enterpriseId="+enterpriseId);
	    if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))){
		    sb.append(" AND partsId=ST_OUT.PARTS_ID AND storeId=ST_OUT.STORE_ID");
		    sb.append(" AND partsNowCount>0 AND ST_OUT.ST_OUT_INTERVAL_DAY>0");
		}
		if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))){
			sb.append(" AND partsId=ST_STORAGE.PARTS_ID AND storeId=ST_STORAGE.STORE_ID");
			sb.append(" AND partsNowCount>0");
			sb.append(" AND ST_STORAGE.ST_GOODS_INTERVAL_DAY>0");
		}
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" and partsId like '%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(sgs_partsId!=null&&!sgs_partsId.equals(""))
			sb.append(" and partsId='"+sgs_partsId.trim()+"'");
		if(partsName!=null&&!partsName.equals(""))
			sb.append(" and partsName like '%"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");	
		if(posName!=null&&!posName.equals(""))
			sb.append(" and posId like '%"+StringEscapeUtils.escapeSql(posName.trim())+"%'");
		if(partsLibrary!=null&&!partsLibrary.equals(""))
			sb.append(" and partsLibrary like '%"+StringEscapeUtils.escapeSql(partsLibrary.trim())+"%'");
		if(partsId2!=null&&!partsId2.equals(""))
			sb.append(" and partsId2 like '%"+StringEscapeUtils.escapeSql(partsId2.trim())+"%'");
		if(pbrdName!=null&&!pbrdName.equals(""))
			sb.append(" and pbrdName like '%"+StringEscapeUtils.escapeSql(pbrdName.trim())+"%'");
		if(ptypeName!=null&&!ptypeName.equals(""))
			sb.append(" and ptypeName like '%"+StringEscapeUtils.escapeSql(ptypeName.trim())+"%'");
		if(storeName!=null&&!storeName.equals(""))
			sb.append(" and  storeId like '%"+StringEscapeUtils.escapeSql(storeName.trim())+"%'");
		
		if(searchStyle!=null&&!searchStyle.equals("")){
			if(searchStyle.equals("低下限库存"))
				sb.append(" and  partsNowCount < stockLower");
			if(searchStyle.equals("超上限库存"))
				sb.append(" and  partsNowCount > stockUpper");
			if(searchStyle.equals("库存量为零"))
				sb.append(" and  partsNowCount=0");
		}
		if(pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals("")){
			if(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))
				sb.append(" and ST_OUT.ST_OUT_NEAR_DATE BETWEEN '"+pnc_strtgmDateStart.trim()+"' AND '"+pnc_strtgmDateEnd.trim()+"'");
			else
				sb.append(" and ST_OUT.ST_OUT_NEAR_DATE >= '"+pnc_strtgmDateStart.trim()+"'");
		}else{
			 if(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.equals(""))
				 sb.append(" and ST_OUT.ST_OUT_NEAR_DATE <= '"+pnc_strtgmDateEnd.trim()+"'");
		}
		
		if(storageDateStart!=null&&!storageDateStart.trim().equals("")){
			if(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE BETWEEN '"+storageDateStart.trim()+"' AND '"+storageDateEnd.trim()+"'");
			else
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE >= '"+storageDateStart.trim()+"'");
		}else{
			if(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE <= '"+storageDateEnd.trim()+"'");
		}
		if (sort != null || order != null) {
		    sb.append(" order by " + sort + " " + order);
		}
		int count=getSQLCount(sb.toString(), null);
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList=createSQLQuery(sb.toString(),page,rows);
		else
			resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsNowCountVo pncvo=new PartsNowCountVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					pncvo.setChangePriceId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					pncvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					pncvo.setPartsId2(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					pncvo.setPartsName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					pncvo.setStoreId(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					pncvo.setStoreName(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					pncvo.setPunitName(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					pncvo.setPtypeName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					pncvo.setPbrdName(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					pncvo.setPartsRepairPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					pncvo.setPartsSellPrice(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					pncvo.setPartsPointPrice(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					pncvo.setPartsSpecialPrice(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					pncvo.setPartsClaimantPrice(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					pncvo.setPartsLatestTaxprice(obj[14].toString());
				if(obj[15]!=null&&!obj[15].equals(""))
					pncvo.setPartsLatestNotaxprice(obj[15].toString());
				if(obj[16]!=null&&!obj[16].equals("")){
					double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
					if(a1 <= 0){
						BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.ZEROALARMCOLOR,enterpriseId);
						String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
						pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
					}else
						pncvo.setPartsNowCount(obj[16].toString());
					pncvo.setPartsNowCount1(obj[16].toString());
				}
				if(obj[17]!=null&&!obj[17].equals("")){
					pncvo.setStockUpper(obj[17].toString());
					if(obj[16]!=null&&!obj[16].equals("")){
						double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
						double a2=obj[17] != null ? Double.parseDouble(obj[17].toString()) : 0.0d;
						if(a1 > a2){
							BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.UPPERALARMCOLOR,enterpriseId);
							String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
							pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
						}
					}
				}
				if(obj[18]!=null&&!obj[18].equals("")){
					pncvo.setStockLower(obj[18].toString());
					if(obj[16]!=null&&!obj[16].equals("")){
						double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
						double a2=obj[18] != null ? Double.parseDouble(obj[18].toString()) : 0.0d;
						if(a1 < a2 && a1 > 0){
							BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.FLOORALARMCOLOR,enterpriseId);
							String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
							pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
						}
					}
				}
				if(obj[19]!=null&&!obj[19].equals(""))
					pncvo.setPosName(obj[19].toString());
				if(pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.equals("")||
					pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.equals("")||
					storageDateStart!=null&&!storageDateStart.equals("")||
					storageDateEnd!=null&&!storageDateEnd.equals("")){
					if(obj[20]!=null&&!obj[20].equals(""))
						pncvo.setSpaceTime(obj[20].toString());
					if(obj[21]!=null&&!obj[21].equals(""))
						pncvo.setInAndOutTime(obj[21].toString());
					if(obj[22]!=null&&!obj[22].equals(""))
						   pncvo.setPartsLibrary(obj[22].toString());
					if(obj[24]!=null&&!obj[24].equals(""))
						pncvo.setFitPtype(obj[24].toString());
				}else{
					if(obj[20]!=null&&!obj[20].equals(""))
						   pncvo.setPartsLibrary(obj[20].toString());
					if(obj[22]!=null&&!obj[22].equals(""))
						pncvo.setFitPtype(obj[22].toString());
				}
				
				if(obj[16]!=null&&!obj[16].equals("")){
					double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
					if(a1 <= 0){
						BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.SHOWZEROPART,enterpriseId);
					    String value=bcinfo.getCiValue();
					    if(value!=null&&value.equals("checked"))
					    	list.add(pncvo);
					}
				    list.add(pncvo);
				}
			}
		}
		return list;
	}
	
	/**
	 * 配件库存量      综合查询
	 */
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String partsId,final String  partsName,final String posName,final String  partsLibrary,final String  partsId2,final String  pbrdName,final String  ptypeName,final String  storeName,final String partsNowCount,final String searchStyle,final String pnc_strtgmDateStart,final String pnc_strtgmDateEnd,final String storageDateStart ,final String storageDateEnd,final String sgs_partsId,final int enterpriseId)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		StringBuffer sb=new StringBuffer(" ");
		sb.append(" select * from (SELECT CHANGE_PRICE_ID AS changePriceId,PARTS_ID AS partsId,PARTS_ID2 AS partsId2,");
		sb.append(" PARTS_NAME AS partsName,STORE_ID AS storeId,STORE_NAME AS storeName,PUNIT_NAME AS punitName");
		sb.append(" ,PTYPE_NAME AS ptypeName,PBRD_NAME AS pbrdName,");
		sb.append(" PARTS_REPAIR_PRICE AS partsRepairPrice,PARTS_SELL_PRICE AS partsSellPrice");
		sb.append(" ,PARTS_POINT_PRICE AS partsPointPrice,PARTS_SPECIAL_PRICE AS partsSpecialPrice");
		sb.append(" ,PARTS_CLAIMANT_PRICE AS partsClaimantPrice,PARTS_LATEST_TAXPRICE AS partsLatestTaxprice,");
		sb.append(" PARTS_LATEST_NOTAXPRICE AS partsLatestNotaxprice,PARTS_NOW_COUNT AS partsNowCount");
		sb.append(" ,STOCK_UPPER AS stockUpper,STOCK_LOWER AS stockLower,POS_NAME AS posName");
		if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals("")))
			sb.append(",ST_OUT.ST_OUT_INTERVAL_DAY,ST_OUT.ST_OUT_NEAR_DATE");
		if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals("")))
			sb.append(",ST_STORAGE.ST_GOODS_INTERVAL_DAY,ST_STORAGE.ST_GOODS_NEAR_DATE");
		sb.append(" ,PARTS_LIBRARY  AS partsLibrary,POS_ID AS posId FROM (");
		sb.append(" SELECT e.*,bas_parts_position.POS_NAME FROM");
		sb.append(" (SELECT d.*,bas_parts_brand.PBRD_NAME FROM");
		sb.append(" (SELECT c.*,bas_parts_type.PTYPE_NAME,bas_parts_type.PBRD_ID FROM ");
		sb.append(" (SELECT b.*,bas_parts_unit.PUNIT_NAME FROM ");
		sb.append(" (SELECT a.*,bas_storehouse.STORE_NAME FROM ");
		sb.append(" (SELECT parts_change_price.*,frt_parts.PARTS_ID2,frt_parts.PARTS_NAME,frt_parts.PTYPE_ID,");
		sb.append(" frt_parts.PUNIT_NAME AS PUNIT_ID,frt_parts.FIT_PTYPE,frt_parts.POS_NAME AS POS_ID,");
		sb.append(" frt_parts.PARTS_LIBRARY FROM parts_change_price INNER JOIN frt_parts ");
		sb.append(" ON parts_change_price.PARTS_ID=frt_parts.PARTS_ID AND frt_parts.PARTS_FLAG=TRUE" +
				" AND frt_parts.enterprise_id="+enterpriseId+")AS a ");
		sb.append(" LEFT JOIN bas_storehouse ON a.STORE_ID=bas_storehouse.STORE_ID)AS b ");
		sb.append(" LEFT JOIN bas_parts_unit ON b.PUNIT_ID=bas_parts_unit.PUNIT_ID)AS c ");
		sb.append(" LEFT JOIN bas_parts_type ON c.PTYPE_ID=bas_parts_type.PTYPE_ID)AS d ");
		sb.append(" LEFT JOIN bas_parts_brand ON d.PBRD_ID=bas_parts_brand.PBRD_ID)AS e ");
		sb.append(" LEFT JOIN bas_parts_position ON bas_parts_position.POS_ID=e.POS_ID) as f");
		if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))){
			sb.append(",(SELECT soi.PARTS_ID,soi.STORE_ID,MAX(som.STOM_DATE)AS ST_OUT_NEAR_DATE,DATEDIFF(NOW(),MAX(som.STOM_DATE))AS ST_OUT_INTERVAL_DAY ");
			sb.append(" FROM st_out_item AS soi,st_out_main AS som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)AS ST_OUT");
		}
	    if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))){
        	sb.append(",(SELECT DISTINCT ssi.PARTS_ID,sgs.STORE_ID,");
			sb.append(" MAX(sgs.STORAGE_DATE)AS ST_GOODS_NEAR_DATE,DATEDIFF(NOW(),MAX(sgs.STORAGE_DATE))AS ST_GOODS_INTERVAL_DAY ");
			sb.append(" FROM st_storage_item ssi,st_goods_storage sgs WHERE ssi.STORAGE_ID=sgs.STORAGE_ID AND (");
			sb.append(" ssi.PARTS_ID NOT IN(SELECT soi.PARTS_ID FROM st_out_item soi,st_out_main som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)");
			sb.append(" OR sgs.STORE_ID NOT IN(SELECT soi.STORE_ID FROM st_out_item soi,st_out_main som WHERE soi.STOM_ID=som.STOM_ID GROUP BY soi.PARTS_ID)");
			sb.append(" )GROUP BY ssi.PARTS_ID, sgs.STORE_ID)AS ST_STORAGE ");
		}
	    sb.append(" ) as g where 1=1 ");
	    if((pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals(""))||(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))){
		    sb.append(" AND partsId=ST_OUT.PARTS_ID AND storeId=ST_OUT.STORE_ID");
		    sb.append(" AND partsNowCount>0 AND ST_OUT.ST_OUT_INTERVAL_DAY>0");
		}
		if((storageDateStart!=null&&!storageDateStart.trim().equals(""))||(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))){
			sb.append(" AND partsId=ST_STORAGE.PARTS_ID AND storeId=ST_STORAGE.STORE_ID");
			sb.append(" AND partsNowCount>0");
			sb.append(" AND ST_STORAGE.ST_GOODS_INTERVAL_DAY>0");
		}
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" and partsId like '%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(sgs_partsId!=null&&!sgs_partsId.equals(""))
			sb.append(" and partsId='"+sgs_partsId.trim()+"'");
		if(partsName!=null&&!partsName.equals(""))
			sb.append(" and partsName like '%"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");	
		if(posName!=null&&!posName.equals(""))
			sb.append(" and posId like '%"+StringEscapeUtils.escapeSql(posName.trim())+"%'");
		if(partsLibrary!=null&&!partsLibrary.equals(""))
			sb.append(" and partsLibrary like '%"+StringEscapeUtils.escapeSql(partsLibrary.trim())+"%'");
		if(partsId2!=null&&!partsId2.equals(""))
			sb.append(" and partsId2 like '%"+StringEscapeUtils.escapeSql(partsId2.trim())+"%'");
		if(pbrdName!=null&&!pbrdName.equals(""))
			sb.append(" and pbrdName like '%"+StringEscapeUtils.escapeSql(pbrdName.trim())+"%'");
		if(ptypeName!=null&&!ptypeName.equals(""))
			sb.append(" and ptypeName like '%"+StringEscapeUtils.escapeSql(ptypeName.trim())+"%'");
		if(storeName!=null&&!storeName.equals(""))
			sb.append(" and  storeId like '%"+StringEscapeUtils.escapeSql(storeName.trim())+"%'");
		
		if(searchStyle!=null&&!searchStyle.equals("")){
			if(searchStyle.equals("低下限库存"))
				sb.append(" and  partsNowCount < stockLower");
			if(searchStyle.equals("超上限库存"))
				sb.append(" and  partsNowCount > stockUpper");
			if(searchStyle.equals("库存量为零"))
				sb.append(" and  partsNowCount=0");
		}
		if(pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.trim().equals("")){
			if(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.trim().equals(""))
				sb.append(" and ST_OUT.ST_OUT_NEAR_DATE BETWEEN '"+pnc_strtgmDateStart.trim()+"' AND '"+pnc_strtgmDateEnd.trim()+"'");
			else
				sb.append(" and ST_OUT.ST_OUT_NEAR_DATE >= '"+pnc_strtgmDateStart.trim()+"'");
		}else{
			 if(pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.equals(""))
				 sb.append(" and ST_OUT.ST_OUT_NEAR_DATE <= '"+pnc_strtgmDateEnd.trim()+"'");
		}
		
		if(storageDateStart!=null&&!storageDateStart.trim().equals("")){
			if(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE BETWEEN '"+storageDateStart.trim()+"' AND '"+storageDateEnd.trim()+"'");
			else
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE >= '"+storageDateStart.trim()+"'");
		}else{
			if(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))
				sb.append(" and ST_STORAGE.ST_GOODS_NEAR_DATE <= '"+storageDateEnd.trim()+"'");
		}
		if (sort != null || order != null) {
		    sb.append(" order by " + sort + " " + order);
		}
		int count=getSQLCount(sb.toString(), null);
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
			resultList=createSQLQuery(sb.toString(),page,rows);
		else
			resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				PartsNowCountVo pncvo=new PartsNowCountVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					pncvo.setChangePriceId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					pncvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					pncvo.setPartsId2(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					pncvo.setPartsName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					pncvo.setStoreId(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					pncvo.setStoreName(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					pncvo.setPunitName(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					pncvo.setPtypeName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					pncvo.setPbrdName(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					pncvo.setPartsRepairPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					pncvo.setPartsSellPrice(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					pncvo.setPartsPointPrice(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					pncvo.setPartsSpecialPrice(obj[12].toString());
				if(obj[13]!=null&&!obj[13].equals(""))
					pncvo.setPartsClaimantPrice(obj[13].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					pncvo.setPartsLatestTaxprice(obj[14].toString());
				if(obj[15]!=null&&!obj[15].equals(""))
					pncvo.setPartsLatestNotaxprice(obj[15].toString());
				if(obj[16]!=null&&!obj[16].equals("")){
					double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
					if(a1 <= 0){
						BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.ZEROALARMCOLOR,enterpriseId);
						String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
						pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
					}else
						pncvo.setPartsNowCount(obj[16].toString());
					pncvo.setPartsNowCount1(obj[16].toString());
				}
				if(obj[17]!=null&&!obj[17].equals("")){
					pncvo.setStockUpper(obj[17].toString());
					if(obj[16]!=null&&!obj[16].equals("")){
						double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
						double a2=obj[17] != null ? Double.parseDouble(obj[17].toString()) : 0.0d;
						if(a1 > a2){
							BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.UPPERALARMCOLOR,enterpriseId);
							String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
							pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
						}
					}
				}
				if(obj[18]!=null&&!obj[18].equals("")){
					pncvo.setStockLower(obj[18].toString());
					if(obj[16]!=null&&!obj[16].equals("")){
						double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
						double a2=obj[18] != null ? Double.parseDouble(obj[18].toString()) : 0.0d;
						if(a1 < a2 && a1 > 0){
							BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.FLOORALARMCOLOR,enterpriseId);
							String[] arrs=bcinfo.getCiValue().substring(4, bcinfo.getCiValue().length()-1).split(",");
							pncvo.setPartsNowCount(new BaseAction().changeColor(obj[16].toString(),Utils.getColorInHexFromRGB(Integer.parseInt(arrs[0].trim()),Integer.parseInt(arrs[1].trim()),Integer.parseInt(arrs[2].trim()))));
						}
					}
				}
				if(obj[19]!=null&&!obj[19].equals(""))
					pncvo.setPosName(obj[19].toString());
				if(pnc_strtgmDateStart!=null&&!pnc_strtgmDateStart.equals("")||
					pnc_strtgmDateEnd!=null&&!pnc_strtgmDateEnd.equals("")||
					storageDateStart!=null&&!storageDateStart.equals("")||
					storageDateEnd!=null&&!storageDateEnd.equals("")){
					if(obj[20]!=null&&!obj[20].equals(""))
						pncvo.setSpaceTime(obj[20].toString());
					if(obj[21]!=null&&!obj[21].equals(""))
						pncvo.setInAndOutTime(obj[21].toString());
					if(obj[22]!=null&&!obj[22].equals(""))
						   pncvo.setPartsLibrary(obj[22].toString());
				}else{
					if(obj[20]!=null&&!obj[20].equals(""))
						   pncvo.setPartsLibrary(obj[20].toString());
				}
				if(obj[16]!=null&&!obj[16].equals("")){
					double a1=obj[16] != null ? Double.parseDouble(obj[16].toString()) : 0.0d;
					if(a1 <= 0){
						BasCompanyInformationSet bcinfo=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.PARTSTOCESEARCH, PARTSTOCESEARCH.SHOWZEROPART,enterpriseId);
					    String value=bcinfo.getCiValue();
					    if(value!=null&&value.equals("checked"))
					    	list.add(pncvo);
					}
				    list.add(pncvo);
				}
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 配件名称信息      预加载
	 */
	public Json loadPartsName(final int page, final int rows, final String sort,final String order,final int enterpriseId)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		String sb = "SELECT frt_parts.PARTS_ID, frt_parts.PARTS_NAME FROM frt_parts WHERE frt_parts.enterprise_id="+enterpriseId;
		int count =getSQLCount(sb, null);
		List<Object[]> resultList=createSQLQuery(sb, page, rows);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
			   PartsNowCountVo pncvo=new PartsNowCountVo();
			   obj=(Object[]) resultList.get(i);
			   if(obj[0]!=null&&!obj[0].equals(""))
				   pncvo.setPartsId(obj[0].toString());
			   if(obj[1]!=null&&!obj[1].equals(""))
				   pncvo.setPartsName(obj[1].toString());
			   list.add(pncvo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 配件名称信息    综合查询
	 */
	public Json searchPartsNameByCondition(final String partsId,final String partsName,final int enterpriseId)throws Exception
	{
		List<PartsNowCountVo> list=new ArrayList<PartsNowCountVo>();
		StringBuffer sb = new StringBuffer("SELECT frt_parts.PARTS_ID,frt_parts.PARTS_NAME FROM frt_parts WHERE frt_parts.enterprise_id="+enterpriseId);
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" AND frt_parts.PARTS_ID LIKE '%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(partsName!=null&&!partsName.equals(""))
			sb.append(" AND frt_parts.PARTS_NAME LIKE '%"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
			   PartsNowCountVo pncvo=new PartsNowCountVo();
			   obj=(Object[]) resultList.get(i);
			   if(obj[0]!=null&&!obj[0].equals(""))
				   pncvo.setPartsId(obj[0].toString());
			   if(obj[1]!=null&&!obj[1].equals(""))
				   pncvo.setPartsName(obj[1].toString());
			   list.add(pncvo);
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 获取配件库存量
	 */
	@SuppressWarnings("unchecked")
	public List getPartsNowCount(String partsId)throws Exception
	{
		try{
			String queryString="SELECT SUM(partsChangePrice.partsNowCount)AS A FROM FrtParts frtParts,PartsChangePrice partsChangePrice "+
	           "WHERE  partsChangePrice.partsId=frtParts.partsId AND frtParts.partsId="+Short.parseShort(partsId)+" GROUP BY frtParts.partsId ";
			return this.getHibernateTemplate().find(queryString);
		}catch(Exception e){
			throw e;
		}
	}
	
   /**
    *配件信息     配件编号，名称条件查询,加载
    */ 
	public Json searchByPartsIdAndPartsName(final String partsId,final String partsId2,final String partsName,final String pbrdId,final String ptypeName, final String sort,
			final String order,final int enterpeiseId) throws Exception{
		List<StPurOrderVo> list = new ArrayList<StPurOrderVo>();
		StringBuffer sb=new StringBuffer("select * from (SELECT PARTS_ID as partsId,PARTS_NAME as partsName,PBRD_NAME as pbrdName,PTYPE_NAME as ptypeName,FIT_PTYPE as fitPtype,"+
		" STATE_NAME1 as stateName,PUNIT_NAME1 as punitName,PARTS_LIBRARY as partsLibrary,PARTS_ID2 as partsId2,PBRD_ID,enterprise_id as enterpeiseId FROM ("+
		" SELECT c.*,bas_parts_state.STATE_NAME AS STATE_NAME1 FROM ("+
		" SELECT b.*,bas_parts_brand.PBRD_NAME FROM ("+
		" SELECT a.*,bas_parts_unit.PUNIT_NAME AS PUNIT_NAME1 FROM("+
		" SELECT frt_parts.*,bas_parts_type.PTYPE_NAME,bas_parts_type.PBRD_ID"+
		" FROM frt_parts INNER JOIN bas_parts_type"+
		" ON  frt_parts.PTYPE_ID=bas_parts_type.PTYPE_ID and frt_parts.PARTS_FLAG="+Contstants.ONOROFF.ONOROFFYES+") AS a"+
		" INNER JOIN bas_parts_unit ON a.PUNIT_NAME=bas_parts_unit.PUNIT_ID) AS b"+
		" LEFT OUTER JOIN bas_parts_brand ON b.PBRD_ID = bas_parts_brand.PBRD_ID) AS c"+
		" LEFT OUTER JOIN bas_parts_state ON c.STATE_NAME=bas_parts_state.STATE_ID) AS d"+
		" ) as e WHERE 1=1 and enterpeiseId="+enterpeiseId);
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" and partsId like "+"'%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(partsId2!=null&&!partsId2.equals(""))
			sb.append(" and partsId2 like "+"'%"+StringEscapeUtils.escapeSql(partsId2.trim())+"%'");
		if(partsName!=null&&!partsName.equals(""))
			sb.append(" and partsName like "+"'"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");
		if(pbrdId!=null&&!pbrdId.equals(""))
			sb.append(" and PBRD_ID like "+"'"+Short.parseShort(pbrdId)+"%'");
		if(ptypeName!=null&&!ptypeName.equals(""))
			sb.append(" and ptypeName like "+"'"+StringEscapeUtils.escapeSql(ptypeName.trim())+"%'");
		sb.append(" GROUP BY partsId ");
		if (sort != null || order != null) 
			sb.append(" ORDER BY " + sort + " " + order);
		sb.append(" LIMIT 500");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StPurOrderVo spovo = new StPurOrderVo();
				obj = resultList.get(i);
				if (obj[0] != null && !obj[0].equals(""))
					spovo.setPartsId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) 
					spovo.setPartsName(obj[1].toString());
				if (obj[2] != null && !obj[2].equals(""))
					spovo.setPbrdName(obj[2].toString());
				if (obj[3] != null && !obj[3].equals(""))
					spovo.setPtypeName(obj[3].toString());
				if (obj[4] != null && !obj[4].equals(""))
					spovo.setFitPtype(obj[4].toString());
				if (obj[5] != null && !obj[5].equals(""))
					spovo.setStateName(obj[5].toString());
				if (obj[6] != null && !obj[6].equals("")) 
					spovo.setPunitName(obj[6].toString());
				if (obj[7] != null && !obj[7].equals("")) 
					spovo.setPartsLibrary(obj[7].toString());
				if (obj[8] != null && !obj[8].equals("")) 
					spovo.setPartsId2(obj[8].toString());
				list.add(spovo);
			}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
}