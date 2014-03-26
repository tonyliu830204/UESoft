package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.StRtPartsDetail;
import com.syuesoft.st.dao.StRtPartsDetailDAO;
import com.syuesoft.st.vo.StRtPartsVo;
/**
 * 退料单明细DAO
 * @author SuMing
 */
@Repository("stRtPartsDetailDAO")
public class StRtPartsDetailDAOImpl extends BaseDaoImpl<StRtPartsDetail> implements StRtPartsDetailDAO{
	
	/**
	 * 通过工单退料单汇总编号获取退料单明细数据
	 */
	public List<StRtPartsVo> searchRecptStRtPartsDetailByStrtpmId(final String strtpmId) throws Exception {
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer(""+
		" SELECT STRTPD_INDEX,PARTS_ID,PARTS_NAME,ITEM_COUNT,STRTPD_CNT,PUNIT_NAME,PARTS_LATEST_TAXPRICE"+
		" ,PARTS_LATEST_TAXPRICE*STRTPD_CNT AS '成本额',PARTS_SELL_PRICE,PARTS_SELL_PRICE*STRTPD_CNT AS '销售额'"+
		" ,CLAIMS_NAME,REPTYP_NAME,STORE_NAME,STRTPD_REMARK,INDEX_ID,STRTPM_ID FROM ("+
		" SELECT g.*,bas_parts_unit.PUNIT_NAME FROM ("+
		" SELECT f.*,bas_repair_type.REPTYP_NAME FROM ("+
		" SELECT e.*,bas_claims_type.CLAIMS_NAME FROM ("+
		" SELECT d.*,bas_storehouse.STORE_NAME FROM ("+
		" SELECT c.*,parts_change_price.PARTS_LATEST_TAXPRICE,parts_change_price.PARTS_SELL_PRICE FROM ("+
		" SELECT b.*,st_out_item.ITEM_COUNT,st_out_item.CLAIMS_TYPE,st_out_item.ITEM_CHARGE FROM ("+
		" SELECT a.*,frt_parts.PARTS_NAME,frt_parts.PUNIT_NAME AS PUNIT_ID FROM ("+
		" SELECT st_rt_parts_detail.*,st_rt_parts_main.RECEPTION_ID FROM st_rt_parts_detail "+
		" LEFT JOIN st_rt_parts_main ON st_rt_parts_main.STRTPM_ID=st_rt_parts_detail.STRTPM_ID) AS a"+
		" LEFT JOIN frt_parts ON a.PARTS_ID=frt_parts.PARTS_ID) AS b"+
		" LEFT JOIN st_out_item ON b.INDEX_ID=st_out_item.INDEX_ID ) AS c"+
		" LEFT JOIN parts_change_price ON c.STORE_ID=parts_change_price.STORE_ID"+
		" AND c.PARTS_ID=parts_change_price.PARTS_ID) AS d"+
		" LEFT JOIN bas_storehouse ON d.STORE_ID=bas_storehouse.STORE_ID) AS e"+
		" LEFT JOIN bas_claims_type ON e.CLAIMS_TYPE= bas_claims_type.CLAIMS_ID) AS f"+
		" LEFT JOIN bas_repair_type ON f.ITEM_CHARGE=bas_repair_type.REPTYP_ID) AS g"+
		" LEFT JOIN bas_parts_unit ON g.PUNIT_ID=bas_parts_unit.PUNIT_ID) AS h where 1=1 ");
		if(strtpmId!=null&&!"".equals(strtpmId))
			sb.append(" AND STRTPM_ID='"+strtpmId+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				obj=(Object[]) resultList.get(i);
				StRtPartsVo srpvo=new StRtPartsVo();
				if(obj[0]!=null&&!"".equals(obj[0]))
					srpvo.setStrtpdIndex(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					srpvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!"".equals(obj[2]))
					srpvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					srpvo.setPartsNum(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					srpvo.setStrtpdCnt(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					srpvo.setPunitName(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					srpvo.setPartsLatestTaxprice(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					srpvo.setPartsLatestTaxpriceAmont(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					srpvo.setSelldPrice(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					srpvo.setRcptpartsAmount(obj[9].toString());
				if(obj[10]!=null&&!"".equals(obj[10]))
					srpvo.setReptypName(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11]))
					srpvo.setClaimsName(obj[11].toString());
				if(obj[12]!=null&&!"".equals(obj[12]))
					srpvo.setStoreName(obj[12].toString());
				if(obj[13]!=null&&!"".equals(obj[13]))
					srpvo.setStrtpdRemark(obj[13].toString());
				if(obj[14]!=null&&!"".equals(obj[14]))
					srpvo.setRcptpartsIndex(obj[14].toString());
				list.add(srpvo);
			}
		}
		return list;
	}
	/**
	 * 通过销售退料单汇总编号获取销售退料单明细数据
	 */
	public List<StRtPartsVo> searchSellStRtPartsDetailByStrtpmId(final String strtpmId) throws Exception {
		List<StRtPartsVo> list=new ArrayList<StRtPartsVo>();
		StringBuffer sb=new StringBuffer("SELECT st_rt_parts_detail.STRTPD_INDEX,"+
				" st_rt_parts_detail.PARTS_ID, frt_parts.PARTS_NAME,"+
				" st_sell_orderitem.SELLD_CNT, st_rt_parts_detail.STRTPD_CNT,"+
				" bas_parts_unit.PUNIT_NAME, st_sell_orderitem.SELLD_COST_PRICE,"+
				" st_sell_orderitem.SELLD_COST_AMOUNT, st_sell_orderitem.SELLD_PRICE,"+
				" st_sell_orderitem.SELLD_AMOUNT, bas_storehouse.STORE_NAME,"+
				" st_rt_parts_detail.STRTPD_REMARK,st_sell_orderitem.SELLD_INDEX"+
				" FROM st_rt_parts_detail , frt_parts,bas_storehouse,st_rt_parts_main,"+
				" st_sell_order, st_sell_orderitem, bas_parts_unit"+
				" WHERE st_rt_parts_detail.PARTS_ID=frt_parts.PARTS_ID"+
				" AND st_rt_parts_detail.STORE_ID=bas_storehouse.STORE_ID"+
				" AND st_rt_parts_detail.STRTPM_ID=st_rt_parts_main.STRTPM_ID"+
				" AND st_sell_order.SELLMM_ID=st_rt_parts_main.RECEPTION_ID"+
				" AND st_sell_order.SELLMM_ID=st_sell_orderitem.SELLMM_ID"+
				" AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID"+
				" AND st_rt_parts_detail.PARTS_ID=st_sell_orderitem.PARTS_ID"+
				" AND st_rt_parts_detail.STORE_ID=st_sell_orderitem.STORE_ID");
		if(strtpmId!=null&&!"".equals(strtpmId))
			sb.append(" AND st_rt_parts_main.STRTPM_ID='"+strtpmId+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtPartsVo srpvo=new StRtPartsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					srpvo.setStrtpdIndex(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					srpvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!"".equals(obj[2]))
					srpvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					srpvo.setPartsNum(obj[3].toString());
				if(obj[4]!=null&&!"".equals(obj[4]))
					srpvo.setStrtpdCnt(obj[4].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					srpvo.setPunitName(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					srpvo.setSelldCostPrice(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					srpvo.setSelldCostAmount(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					srpvo.setSelldPrice(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					srpvo.setRcptpartsAmount(obj[9].toString());
				if(obj[10]!=null&&!"".equals(obj[10]))
					srpvo.setStoreName(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11]))
					srpvo.setStrtpdRemark(obj[11].toString());
				if(obj[12]!=null&&!"".equals(obj[12]))
					srpvo.setRcptpartsIndex(obj[12].toString());
				list.add(srpvo);
			}
		}
		return list;
	}

}