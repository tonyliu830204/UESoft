package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.model.StRtGoodsDetail;
import com.syuesoft.st.dao.PartsChangePriceDAO;
import com.syuesoft.st.dao.StRtGoodsDetailDAO;
import com.syuesoft.st.vo.StGoodsStorageRtGoodsSearchVo;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.Json;
/**
 * 退货单明细dao实现类
 * @author SuMing
 */
@Repository("stRtGoodsDetailDAO")
public class StRtGoodsDetailDAOImpl extends BaseDaoImpl<StRtGoodsDetail> implements StRtGoodsDetailDAO{

	@Autowired PartsChangePriceDAO partsChangePriceDAO;
	/**
	 * 退货单模块     根据入库单号获取入库单明细信息
	 */
	public List<StRtGoodsVo> srg_searchStRtGoodsDetailByStorageId(final String partsId,final String partsName,final String storageId,final String relcampId,final int  enterprise_id)throws Exception
	{
		List<StRtGoodsVo> list=new ArrayList<StRtGoodsVo>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (SELECT frt_parts.PARTS_ID,frt_parts.PARTS_NAME,bas_parts_unit.PUNIT_NAME,"+
		 " (CASE WHEN (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) AS SUM_CNT FROM st_rt_goods_detail,st_rt_goods_main"+
		 " WHERE st_rt_goods_main.enterprise_id="+enterprise_id+" and st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		 " AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID GROUP BY st_rt_goods_detail.PARTS_ID) IS NULL THEN st_storage_item.PARTS_COUNT"+
		 " WHEN (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) AS SUM_CNT FROM st_rt_goods_detail,st_rt_goods_main"+
		 " WHERE st_rt_goods_main.enterprise_id="+enterprise_id+" and st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		 " AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID GROUP BY st_rt_goods_detail.PARTS_ID) IS NOT NULL THEN st_storage_item.PARTS_COUNT-"+
		 " (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) AS SUM_CNT FROM st_rt_goods_detail,st_rt_goods_main"+
		 " WHERE st_rt_goods_main.enterprise_id="+enterprise_id+" and st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		 " AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID GROUP BY st_rt_goods_detail.PARTS_ID) END) AS M,"+
		 " st_storage_item.NOTAX_PRICE,st_storage_item.NOTAX_TOTALAMONT,st_goods_storage.STORE_ID,st_goods_storage.STORAGE_ID"+
		 " ,st_goods_storage.RELCAMP_ID,st_storage_item.TAX_PRICE,st_storage_item.TAX_TOTALAMONT FROM st_storage_item,st_goods_storage,frt_parts,bas_parts_unit"+
		 " WHERE st_goods_storage.enterprise_id="+enterprise_id+" and st_goods_storage.STORAGE_ID=st_storage_item.STORAGE_ID AND st_storage_item.PARTS_ID=frt_parts.PARTS_ID "+
		 " AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID)AS A WHERE A.M>0");
		if(relcampId!=null&&!relcampId.equals(""))
			sb.append(" AND A.RELCAMP_ID ="+relcampId);
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" AND A.PARTS_ID like '%"+StringEscapeUtils.escapeSql(partsId.trim())+"%'");
		if(partsName!=null&&!partsName.equals(""))
			sb.append(" AND A.PARTS_NAME like '%"+StringEscapeUtils.escapeSql(partsName.trim())+"%'");
		if(storageId!=null&&!storageId.equals(""))
		    sb.append(" AND A.STORAGE_ID='"+storageId+"'");
		int count =getSQLCount(sb.toString(), null);
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtGoodsVo srgvo=new StRtGoodsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					srgvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					srgvo.setPartsName(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					srgvo.setPunitName(obj[2].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					srgvo.setStrtgdNoCostPrice(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					srgvo.setStrtgdNoCostAmount(0.00+"");
				
				if(obj[6]!=null&&!obj[6].equals("")){
					if(obj[3]!=null&&!obj[3].equals("")){
						//注意，此处配件的入库数量要与库存量挂钩，当入库的配件数量大于库存当前库存量时，则已当前库存为基准
						PartsChangePrice pcp=partsChangePriceDAO.get(" FROM PartsChangePrice pcp where pcp.storeId="+obj[6].toString()+" and pcp.partsId='"+srgvo.getPartsId()+"'");
						if(Double.parseDouble(obj[3].toString())>pcp.getPartsNowCount())
							srgvo.setPartsCount(pcp.getPartsNowCount()+"");
						else
							srgvo.setPartsCount(obj[3].toString());
					}
				}
				if(obj[9]!=null&&!obj[9].equals(""))
					srgvo.setStrtgdCostPrice(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					srgvo.setStrtgdCostAmount(0.00+"");//获取配件销售价
				if(Double.parseDouble(srgvo.getPartsCount())>0)
					list.add(srgvo);
			}
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
		return list;
	}
	
	/**
	 * 根据退货单号获取退货单明细信息
	 */
	public List<StRtGoodsVo> srg_searchStRtGoodsDetailByStrtgmId(final String strtgmId,final String storageId)throws Exception
	{
		List<StRtGoodsVo> list=new ArrayList<StRtGoodsVo>();
		StringBuffer sb= new StringBuffer("SELECT st_rt_goods_detail.STRTGD_INDEX,st_rt_goods_detail.PARTS_ID,frt_parts.PARTS_NAME,"+
		" bas_parts_unit.PUNIT_NAME,st_storage_item.PARTS_COUNT,st_rt_goods_detail.STRTGD_CNT,"+
		" st_rt_goods_detail.STRTGD_COST_PRICE,st_rt_goods_detail.STRTGD_NO_COST_PRICE,"+
		" st_rt_goods_detail.STRTGD_COST_AMOUNT,st_rt_goods_detail.STRTGD_NO_COST_AMOUNT,"+
		" st_rt_goods_detail.STRTGD_REMARK,st_rt_goods_main.STORE_ID,st_rt_goods_main.STORAGE_ID "+
		" FROM st_rt_goods_detail, st_rt_goods_main,st_storage_item,frt_parts,bas_parts_unit"+
		" WHERE st_rt_goods_detail.PARTS_ID=frt_parts.PARTS_ID AND frt_parts.PUNIT_NAME=bas_parts_unit.PUNIT_ID"+
		" AND st_rt_goods_main.STRTGM_ID=st_rt_goods_detail.STRTGM_ID AND st_rt_goods_main.STORAGE_ID=st_storage_item.STORAGE_ID "+
		" AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID");
		if(strtgmId!=null&&!strtgmId.trim().equals(""))
			sb.append(" AND st_rt_goods_main.STRTGM_ID='"+strtgmId.trim()+"'");
		sb.append(" GROUP BY st_rt_goods_detail.STRTGD_INDEX");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtGoodsVo srgvo=new StRtGoodsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!"".equals(obj[0]))
					srgvo.setStrtgdIndex(obj[0].toString());
				if(obj[1]!=null&&!"".equals(obj[1]))
					srgvo.setPartsId(obj[1].toString());
				if(obj[2]!=null&&!"".equals(obj[2]))
					srgvo.setPartsName(obj[2].toString());
				if(obj[3]!=null&&!"".equals(obj[3]))
					srgvo.setPunitName(obj[3].toString());
				if(obj[5]!=null&&!"".equals(obj[5]))
					srgvo.setStrtgdCnt(obj[5].toString());
				if(obj[6]!=null&&!"".equals(obj[6]))
					srgvo.setStrtgdCostPrice(obj[6].toString());
				if(obj[7]!=null&&!"".equals(obj[7]))
					srgvo.setStrtgdNoCostPrice(obj[7].toString());
				if(obj[8]!=null&&!"".equals(obj[8]))
					srgvo.setStrtgdCostAmount(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					srgvo.setStrtgdNoCostAmount(obj[9].toString());
				if(obj[10]!=null&&!"".equals(obj[10]))
					srgvo.setStrtgdRemark(obj[10].toString());
				if(obj[11]!=null&&!"".equals(obj[11])){
					srgvo.setStoreId(obj[11].toString());
					if(obj[4]!=null&&!"".equals(obj[4])&&obj[12]!=null&&!obj[12].equals("")){
						double num=Double.parseDouble(getNum(obj[12].toString(),obj[1].toString()));
						//注意，此处配件的入库数量要与库存量挂钩，当入库的配件数量大于库存当前库存量时，则已当前库存为基准
						PartsChangePrice pcp=partsChangePriceDAO.get(" FROM PartsChangePrice pcp where pcp.storeId="+obj[11].toString()+" and pcp.partsId='"+srgvo.getPartsId()+"'");
						if(Double.parseDouble(obj[4].toString())>pcp.getPartsNowCount())
						    srgvo.setPartsCount(pcp.getPartsNowCount()+"");
						else
							 srgvo.setPartsCount(num+"");
					}
				}
				list.add(srgvo);
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public String getNum(String storageId,String partsId)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT("+
		" CASE WHEN "+
		"  (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) FROM st_rt_goods_detail,st_rt_goods_main "+
		" WHERE st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID"+
		"  AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		"  AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID"+
		"  GROUP BY st_rt_goods_detail.PARTS_ID) IS NULL THEN st_storage_item.PARTS_COUNT"+
		" WHEN "+
		"  (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) FROM st_rt_goods_detail,st_rt_goods_main "+
		" WHERE st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID"+
		" AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		" AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID"+
		" GROUP BY st_rt_goods_detail.PARTS_ID)IS NOT NULL THEN "+
		" (st_storage_item.PARTS_COUNT- (SELECT SUM(st_rt_goods_detail.STRTGD_CNT) FROM st_rt_goods_detail,st_rt_goods_main "+
		" WHERE st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID"+
		" AND st_rt_goods_main.STORAGE_ID=st_goods_storage.STORAGE_ID"+
		" AND st_rt_goods_detail.PARTS_ID=st_storage_item.PARTS_ID"+
		" GROUP BY st_rt_goods_detail.PARTS_ID))END"+
		" ) AS A"+
		" FROM st_storage_item,st_goods_storage "+
		" WHERE st_storage_item.STORAGE_ID=st_goods_storage.STORAGE_ID");
		if(storageId!=null&&!storageId.equals(""))
			sb.append(" and st_storage_item.STORAGE_ID='"+storageId+"'");
		if(partsId!=null&&!partsId.equals(""))
			sb.append(" and st_storage_item.PARTS_ID='"+partsId+"'");
		List list=createSQLQuery(sb.toString());
		return list.get(0).toString();
	}
	
	/**
	 * 入退单查询模块   入退单信息预加载
	 */
	@SuppressWarnings("unchecked")
	public Json loadStGoodsStorageRtGoodsSearchInfo(final String startTime,final String endTime,final String pbrdName,final String fitPtype,final String partsId,final String partsName,final String storeId,final String enterprise_id)throws Exception{
		List<StGoodsStorageRtGoodsSearchVo> list=new ArrayList<StGoodsStorageRtGoodsSearchVo>();
		StringBuffer sb=new StringBuffer("call st_stgoodsstorage_strtgoods_search(");
		if(startTime!=null&&!startTime.trim().equals(""))
			sb.append("'"+startTime.trim()+"',");
		else
			sb.append("'',");
		if(endTime!=null&&!endTime.trim().equals(""))
			sb.append("'"+endTime.trim()+"',");
		else
			sb.append("'',");
		if(pbrdName!=null&&!pbrdName.trim().equals(""))
			sb.append("'"+pbrdName.trim()+"',");
		else
			sb.append("'',");
		if(fitPtype!=null&&!fitPtype.trim().equals(""))
			sb.append("'"+fitPtype.trim()+"',");
		else
			sb.append("'',");
		if(partsId!=null&&!partsId.trim().equals(""))
			sb.append("'"+partsId.trim()+"',");
		else
			sb.append("'',");
		if(partsName!=null&&!partsName.trim().equals(""))
			sb.append("'"+partsName.trim()+"',");
		else
			sb.append("'',");
		if(storeId!=null&&!storeId.trim().equals(""))
			sb.append(""+storeId.trim()+",");
		else
			sb.append("'',");
		if(enterprise_id!=null&&!enterprise_id.trim().equals(""))
			sb.append(""+enterprise_id.trim()+"");
		else
			sb.append("''");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =0;
        Object[] obj=null;
        if(resultList!=null&&resultList.size()>0){
        	count=resultList.size();
        	for (int i = 0; i < resultList.size(); i++) {
        		StGoodsStorageRtGoodsSearchVo sgsvo=new StGoodsStorageRtGoodsSearchVo();
        		obj=resultList.get(i);
        		if(obj[0]!=null&&!obj[0].equals(""))
        			sgsvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sgsvo.setPartsName(obj[1].toString());		
				if(obj[2]!=null&&!obj[2].equals(""))
					sgsvo.setPartsLibrary(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sgsvo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sgsvo.setStoreName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sgsvo.setPartsCount(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					sgsvo.setPtypeName(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					sgsvo.setPbrdName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					sgsvo.setFitType(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					sgsvo.setStoreId(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					sgsvo.setTaxPrice(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					sgsvo.setTaxTotalAmount(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					sgsvo.setPartsLatestTaxprice(obj[12].toString());
				if(startTime!=null&&!startTime.trim().equals(""))
					sgsvo.setStartTime(startTime.trim());
				if(endTime!=null&&!endTime.trim().equals(""))
					sgsvo.setEndTime(endTime.trim());
				if(obj[0]!=null&&!obj[0].equals("")&&obj[9]!=null&&!obj[9].equals("")){
					String sql1="SELECT SUM(PARTS_COUNT) AS PARTS_COUNT  FROM("+
					" SELECT st_storage_item.*,st_goods_storage.STORE_ID FROM st_storage_item"+
					" INNER JOIN st_goods_storage ON st_storage_item.STORAGE_ID=st_goods_storage.STORAGE_ID "+
					" AND st_storage_item.PARTS_ID='"+obj[0].toString()+"'"+
					" AND st_goods_storage.STORE_ID="+obj[9].toString()+
					" ) AS a GROUP BY STORE_ID,PARTS_ID";
					List resultList1=createSQLQuery(sql1);
				    if(resultList1!=null&&resultList1.size()>0)
				    	sgsvo.setStoragePartsCount(resultList1.get(0).toString());
				    else
				    	sgsvo.setStoragePartsCount(0+"");
				    String sql2="SELECT SUM(STRTGD_CNT) AS STRTGD_CNT FROM ("+
					" SELECT st_rt_goods_detail.*,st_rt_goods_main.STORE_ID"+
					" FROM st_rt_goods_detail INNER JOIN st_rt_goods_main" +
					" ON st_rt_goods_detail.STRTGM_ID=st_rt_goods_main.STRTGM_ID"+
					" AND st_rt_goods_detail.PARTS_ID='"+obj[0].toString()+"'"+
					" AND st_rt_goods_main.STORE_ID="+obj[9].toString()+
					" ) AS b GROUP BY STORE_ID,PARTS_ID";
				    List resultList2=createSQLQuery(sql2);
				    if(resultList2!=null&&resultList2.size()>0)
				    	sgsvo.setRtStoragePartsCount(resultList2.get(0).toString());
				    else
				    	sgsvo.setRtStoragePartsCount(0+"");
				}
				list.add(sgsvo);
			}
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
		return json;
	}
	
	/**
	 * 入退单明细   通过入退时间获取入退单明细
	 */
	public Json loadStGoodsStorageRtGoodsDetailSearchInfo(final String startTime,final String endTime,final String partsId,final String storeId)throws Exception{
		List<StGoodsStorageRtGoodsSearchVo> list=new ArrayList<StGoodsStorageRtGoodsSearchVo>();
		StringBuffer sb=new StringBuffer("call st_stgoodsstorage_strtgoods_detail_search(");
		if(startTime!=null&&!startTime.trim().equals("")&&!startTime.trim().equals("undefined"))
			sb.append("'"+startTime.trim()+"',");
		else
			sb.append("'',");
		if(endTime!=null&&!endTime.trim().equals("")&&!endTime.trim().equals("undefined"))
			sb.append("'"+endTime.trim()+"',");
		else
			sb.append("'',");
		if(partsId!=null&&!partsId.trim().equals(""))
			sb.append("'"+partsId.trim()+"',");
		else
			sb.append("'',");
		if(storeId!=null&&!storeId.trim().equals(""))
			sb.append(""+storeId.trim()+"");
		else
			sb.append("''");
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =0;
        Object[] obj=null;
        if(resultList!=null&&resultList.size()>0){
        	count=resultList.size();
	    	for (int i = 0; i < resultList.size(); i++) {
	    		StGoodsStorageRtGoodsSearchVo sgsvo=new StGoodsStorageRtGoodsSearchVo();
	    		obj=resultList.get(i);
	    		if(obj[0]!=null&&!obj[0].equals(""))
	    			sgsvo.setPartsId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sgsvo.setPartsName(obj[1].toString());		
				if(obj[2]!=null&&!obj[2].equals(""))
					sgsvo.setPartsLibrary(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sgsvo.setPunitName(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sgsvo.setStoreName(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sgsvo.setPartsCount(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					sgsvo.setPtypeName(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					sgsvo.setPbrdName(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					sgsvo.setFitType(obj[8].toString());
				if(obj[9]!=null&&!obj[9].equals(""))
					sgsvo.setStoreId(obj[9].toString());
				if(obj[10]!=null&&!obj[10].equals(""))
					sgsvo.setTaxPrice(obj[10].toString());
				if(obj[11]!=null&&!obj[11].equals(""))
					sgsvo.setTaxTotalAmount(obj[11].toString());
				if(obj[12]!=null&&!obj[12].equals(""))
					sgsvo.setPartsLatestTaxprice(obj[12].toString());
				if(obj[14]!=null&&!obj[14].equals(""))
					sgsvo.setTypeName(obj[14].toString());
				if(obj[15]!=null&&!obj[15].equals(""))
					sgsvo.setStorageId(obj[15].toString());
				list.add(sgsvo);
			}
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
		return json;
	}
	
	
}