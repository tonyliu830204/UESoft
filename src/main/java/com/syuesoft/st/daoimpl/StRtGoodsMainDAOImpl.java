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
import com.syuesoft.model.StRtGoodsMain;
import com.syuesoft.st.dao.StRtGoodsMainDAO;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
/**
 * 退货单dao实现类
 * @author SuMing
 */
@Repository("stRtGoodsMainDAO")
public class StRtGoodsMainDAOImpl extends BaseDaoImpl<StRtGoodsMain> implements StRtGoodsMainDAO {
	
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 退货单汇总信息    综合查询
	 */
	public Json searchByCondition(final String sort,final String order,final int page, final int rows,final String strtgmDateStart,
			final String strtgmDateEnd, final String strtgmId,
			final String relcampName, final String storeId,final int enterpriseId) throws Exception {
		List<StRtGoodsVo> list = new ArrayList<StRtGoodsVo>();
		 StringBuffer sb=new StringBuffer("select * from (SELECT f.STRTGM_ID AS strtgmId,f.STRTGM_DATE AS strtgmDate,f.STORAGE_ID AS storageId"+
		 " ,f.RELCAMP_ID,f.RELCAMP_NAME AS relcampName,f.STORAGE_DATE AS storageDate,f.NUM_TOTAL AS numTotal,f.STRTGM_SUM_COST AS strtgmSumCost,"+
		 " f.STF_ID,f.MANAGER_NAME AS manager,f.BUYER_NAME AS buyer,f.STORE_NAME AS storeName,f.STRTGM_REMARK AS strtgmRemark,f.STORE_ID,f.STRTGM_SUM_NO_COST,f.enterprise_id FROM ("+
		 " SELECT e.*,bs2.STF_NAME AS BUYER_NAME FROM ("+
		 " SELECT d.*,bs1.STF_NAME AS MANAGER_NAME FROM ("+
		 " SELECT c.*,bas_storehouse.STORE_NAME FROM ("+
		 " SELECT b.*,st_pur_order.BUYER FROM ("+
		 " SELECT a.*,bas_relation_campany.RELCAMP_NAME FROM ("+
		 " SELECT st_rt_goods_main.*,st_goods_storage.STORAGE_DATE,st_goods_storage.CER_NO FROM st_rt_goods_main "+
		 " LEFT JOIN st_goods_storage ON st_goods_storage.STORAGE_ID=st_rt_goods_main.STORAGE_ID ) AS a"+
		 " LEFT JOIN bas_relation_campany ON a.RELCAMP_ID=bas_relation_campany.RELCAMP_ID) AS b"+
		 " LEFT JOIN  st_pur_order ON b.CER_NO=st_pur_order.ORDER_ID ) AS c"+
		 " LEFT JOIN bas_storehouse ON c.STORE_ID=bas_storehouse.STORE_ID )AS d"+
		 " LEFT JOIN bas_stuff AS  bs1 ON d.STF_ID=bs1.STF_ID) AS e"+
		 " LEFT JOIN bas_stuff AS bs2 ON e.BUYER=bs2.STF_ID) AS f) as g where enterprise_id="+enterpriseId);
		if (strtgmDateStart != null && !strtgmDateStart.equals("")) {
			if (strtgmDateEnd != null && !strtgmDateEnd.equals("")) 
				sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d') BETWEEN '"+ strtgmDateStart + "' AND '" + strtgmDateEnd + "'");
			else
				sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d')>='" + strtgmDateStart+ "'");
		}else{
			if (strtgmDateEnd != null && !strtgmDateEnd.equals("")) 
				sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d')<='" + strtgmDateEnd+ "'");
		}
		if(strtgmDateStart == null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
			if(value!=null)
				sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value))+ "'");
			else
				sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+""))+ "'");
			
		}
		if(strtgmDateEnd == null)
			sb.append(" and DATE_FORMAT(strtgmDate,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
		if (strtgmId != null && !strtgmId.equals(""))
			sb.append(" and strtgmId like '%" + StringEscapeUtils.escapeSql(strtgmId.trim()) + "%'");
		if (relcampName != null && !relcampName.equals(""))
			sb.append(" and relcampName like '"+ StringEscapeUtils.escapeSql(relcampName.trim()) + "%'");
		if (storeId != null && !storeId.equals(""))
			sb.append(" and STORE_ID='" + storeId + "'");
		if (sort != null || order != null) 
			    sb.append(" order by " + sort + " " + order);
	    int count = getSQLCount(sb.toString(), null);
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

	public List<StRtGoodsVo> copyObjectVoToListVo(List<Object[]> resultList,List<StRtGoodsVo> list){
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtGoodsVo srgvo = new StRtGoodsVo();
				obj = (Object[]) resultList.get(i);
				if (obj[0] != null && !obj[0].equals("")) 
					srgvo.setStrtgmId(obj[0].toString());
				if (obj[1] != null && !obj[1].equals("")) {
					srgvo.setStrtgmDate(obj[1].toString());
					srgvo.setStorageDateView(obj[1].toString().substring(0, 10));
				}
				if (obj[2] != null && !obj[2].equals(""))
					srgvo.setStorageId(obj[2].toString());
				if (obj[3] != null && !obj[3].equals("")) 
					srgvo.setRelcampId(obj[3].toString());
				if (obj[4] != null && !obj[4].equals("")) 
					srgvo.setRelcampName(obj[4].toString());
				if (obj[5] != null && !obj[5].equals("")) {
					String time = obj[5].toString();
					time = time.substring(0, 10);
					srgvo.setStorageDate(obj[5].toString());
				}
				if (obj[6] != null && !obj[6].equals("")) 
					srgvo.setNumTotal(obj[6].toString());
				if (obj[7] != null && !obj[7].equals("")) 
					srgvo.setStrtgmSumCost(obj[7].toString());
				if (obj[8] != null && !obj[8].equals("")) 
					srgvo.setManagerId(obj[8].toString());
				if (obj[9] != null && !obj[9].equals("")) 
					srgvo.setManager(obj[9].toString());
				if (obj[10] != null && !obj[10].equals("")) 
					srgvo.setBuyer(obj[10].toString());
				if (obj[11] != null && !obj[11].equals("")) 
					srgvo.setStoreName(obj[11].toString());
				if (obj[12] != null && !obj[12].equals("")) 
					srgvo.setStrtgmRemark(obj[12].toString());
				if (obj[13] != null && !obj[13].equals("")) 
					srgvo.setStoreId(obj[13].toString());
				if (obj[14] != null && !obj[14].equals("")) 
					srgvo.setStrtgmSumNoCost(obj[14].toString());
				list.add(srgvo);
			}
		}
		return list;
	}
	
}