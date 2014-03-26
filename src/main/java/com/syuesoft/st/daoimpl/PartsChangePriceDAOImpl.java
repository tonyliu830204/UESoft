package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Json;
/**
 * 配件调价DAO实现类
 * @author SuMing
 */
@Repository("partsChangePriceDAO")
public class PartsChangePriceDAOImpl extends BaseDaoImpl<PartsChangePrice> implements
		PartsChangePriceDAO {

	/**
	 * 出库单管理          配件信息 按配件编号  ，名称查询   加载
	 */
	public Json searchByCondition(final String partsId,final String partsName,final String storeId,final int enterprise_id)throws Exception
	{
		List<StOutVo> list = new ArrayList<StOutVo>();
		StringBuffer sb=new StringBuffer("SELECT DISTINCT parts_change_price.CHANGE_PRICE_ID,frt_parts.PARTS_ID,frt_parts.PARTS_NAME,"+
		" bas_parts_unit.PUNIT_NAME,frt_parts.FIT_PTYPE,parts_change_price.PARTS_LATEST_TAXPRICE,"+
		" parts_change_price.PARTS_LATEST_NOTAXPRICE,frt_parts.PARTS_LIBRARY,parts_change_price.PARTS_NOW_COUNT,"+
		" bas_storehouse.STORE_NAME,parts_change_price.PARTS_SELL_PRICE,bas_storehouse.STORE_ID"+
		" ,parts_change_price.PARTS_REPAIR_PRICE,parts_change_price.PARTS_POINT_PRICE,"+
		" parts_change_price.PARTS_SPECIAL_PRICE FROM parts_change_price,frt_parts," +
		" bas_parts_unit,bas_storehouse,bas_relation_campany WHERE frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID " +
		" and parts_change_price.enterprise_id="+enterprise_id+""+
		" and frt_parts.PARTS_FLAG="+Contstants.ONOROFF.ONOROFFYES+
		" AND parts_change_price.PARTS_ID=frt_parts.PARTS_ID AND bas_storehouse.STORE_ID=parts_change_price.STORE_ID ");
		if(partsId!=null&&!partsId.trim().equals(""))
		    sb.append(" AND frt_parts.PARTS_ID like '%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(partsName!=null&&!partsName.trim().equals(""))
			sb.append(" AND frt_parts.PARTS_NAME like '%"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");
		if(storeId!=null&&!storeId.trim().equals(""))
			sb.append(" AND parts_change_price.STORE_ID='"+storeId.trim()+"'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
				Object[] obj = null;
				for (int i = 0; i < resultList.size(); i++) {
					obj = (Object[]) resultList.get(i);
					StOutVo stOutVo = new StOutVo();
					if (obj[0] != null && !"".equals(obj[0])) 
						stOutVo.setChangePriceId(obj[0].toString());
					if (obj[1] != null && !"".equals(obj[1])) 
						stOutVo.setPartsId(obj[1].toString());
					if (obj[2] != null && !"".equals(obj[2])) 
						stOutVo.setPartsName(obj[2].toString());
					if (obj[3] != null && !"".equals(obj[3])) 
						stOutVo.setPunitName(obj[3].toString());
					if (obj[4] != null && !"".equals(obj[4])) 
						stOutVo.setFitPtype(obj[4].toString());
					if (obj[5] != null && !"".equals(obj[5])) 
						stOutVo.setTaxCast(obj[5].toString());
					if (obj[6] != null && !"".equals(obj[6])) 
						stOutVo.setNotaxCast(obj[6].toString());
					if (obj[7] != null && !"".equals(obj[7])) 
						stOutVo.setPartsLibrary(obj[7].toString());
					if (obj[9] != null && !"".equals(obj[9]))
						stOutVo.setStoreName(obj[9].toString());
					if (obj[10] != null && !"".equals(obj[10])) 
						stOutVo.setItemPrice(obj[10].toString());
					if (obj[11] != null && !"".equals(obj[11])) 
						stOutVo.setStoreId(obj[11].toString());
					if (obj[8] != null && !"".equals(obj[8])) {
						stOutVo.setPartsNowCount(obj[8].toString());
						if(Double.parseDouble(stOutVo.getPartsNowCount())>0)
							list.add(stOutVo);
					}
				}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
}