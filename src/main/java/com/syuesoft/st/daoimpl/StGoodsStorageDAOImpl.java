package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.model.StGoodsStorage;
import com.syuesoft.st.dao.StGoodsStorageDAO;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.st.vo.StStorageVo;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
/**
 * 货品入库单DAO实现
 * @author SuMing
 */
@Repository("stGoodsStorageDAO")
public class StGoodsStorageDAOImpl extends BaseDaoImpl<StGoodsStorage> implements
		StGoodsStorageDAO {

	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 入库单汇总信息     预加载
	 */
	public Json loadStGoodsStorage(final int page, final int rows, final String sort,final String order,final int enterpriceId)throws Exception
	{
		List<StStorageVo> list=new ArrayList<StStorageVo>();
		StringBuffer sb= new StringBuffer("CALL st_goodsstorage_view(");
		if (sort != null && order != null) {
			 sb.append("'"+sort+"',");
			 sb.append("'"+order+"',");
        }else{
        	 sb.append("'',");
			 sb.append("'',");
        }
		sb.append(""+enterpriceId+"");
		sb.append(")");
		int count =0;
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			list=copyObjectVoToListVo(resultList, list);
			count=resultList.size();
		}
		Json json=new Json();
		json.setTotal(count);
		json.setRows(list);
		return json;
	}
	
	/**
	 * 入库单汇总信息     综合查询
	 */
	public Json searchStGoodsStorageByCondition(final String storageDateStart,final String storageDateEnd, 
			final String relcampName,final String storageId,final String orderId,final int enterpriceId)throws Exception
	{
		List<StStorageVo> list=new ArrayList<StStorageVo>();
		StringBuffer sb= new StringBuffer("CALL st_goodsstorage_search(");
		if(storageDateStart!=null&&!storageDateStart.trim().equals(""))
			sb.append("'"+storageDateStart.trim()+"',");
		else if(storageDateStart==null){
			String value=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriceId).getCiValue();
			if(value!=null)
				sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(value)) +"',");
			else
				sb.append("'"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(7+"")) +"',");
		}
		else
			sb.append("'',");
		if(storageDateEnd!=null&&!storageDateEnd.trim().equals(""))
			sb.append("'"+storageDateEnd.trim()+"',");
		else if(storageDateEnd==null)
			sb.append("'"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"',");
		else
			sb.append("'',");
		if(relcampName!=null&&!relcampName.trim().equals(""))
			sb.append("'"+relcampName.trim()+"',");
		else
			sb.append("'',");
		if(storageId!=null&&!storageId.trim().equals(""))
			sb.append("'"+storageId.trim()+"',");
		else
			sb.append("'',");
		if(orderId!=null&&!orderId.trim().equals(""))
			sb.append("'"+orderId.trim()+"',");
		else
			sb.append("'',");
		sb.append(""+enterpriceId+"");
		int count =0;
		sb.append(")");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			list=copyObjectVoToListVo(resultList, list);
			count=resultList.size();
		}
		Json json = new Json();
		json.setTotal(count);
		json.setRows(list);
	    return json;
	}
	
	public List<StStorageVo> copyObjectVoToListVo(List<Object[]> resultList,List<StStorageVo> list){
		if(resultList!=null&&resultList.size()>0){
		    Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StStorageVo svo=new StStorageVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					svo.setStorageId(obj[0].toString());
		    	if(obj[1]!=null&&!obj[1].equals(""))
					svo.setStorageDate(obj[1].toString());
					svo.setStorageDateView(obj[1].toString().substring(0, 10));
		    	if(obj[2]!=null&&!obj[2].equals(""))
		    		svo.setRelcampName(obj[2].toString());
		    	if(obj[3]!=null&&!obj[3].equals(""))
		    		svo.setOrderId(obj[3].toString());
		    	if(obj[4]!=null&&!obj[4].equals(""))
		    		svo.setNotesType(obj[4].toString());
		    	if(obj[5]!=null&&!obj[5].equals(""))
		    		svo.setTotalNum(obj[5].toString());
		    	if(obj[6]!=null&&!obj[6].equals(""))
		    		svo.setNotaxTotalamont(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[6].toString()));
		    	if(obj[7]!=null&&!obj[7].equals(""))
		    		svo.setTotalAmount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[7].toString()));
		    	if(obj[8]!=null&&!obj[8].equals(""))
		    		svo.setTaxCount(basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,obj[8].toString()));
		    	if(obj[9]!=null&&!obj[9].equals(""))
		    		svo.setManager(obj[9].toString());
		    	if(obj[10]!=null&&!obj[10].equals(""))
		    		svo.setBuyerName(obj[10].toString());
		    	if(obj[11]!=null&&!obj[11].equals(""))
		    		svo.setPaid(obj[11].toString());
		    	if(obj[12]!=null&&!obj[12].equals(""))
		    		svo.setBill(obj[12].toString());
		    	if(obj[13]!=null&&!obj[13].equals(""))
		    		svo.setStoreName(obj[13].toString());
		    	if(obj[14]!=null&&!obj[14].equals(""))
		    		svo.setRelcampId(obj[14].toString());
		    	if(obj[15]!=null&&!obj[15].equals(""))
		    		svo.setIdentifyman(obj[15].toString());
		    	if(obj[16]!=null&&!obj[16].equals(""))
		    		svo.setRemark(obj[16].toString());
		    	if(obj[17]!=null&&!obj[17].equals(""))
		    		svo.setTaxRate(obj[17].toString());
		    	if(obj[18]!=null&&!obj[18].equals(""))
		    		svo.setManagerId(obj[18].toString());
		    	if(obj[19]!=null&&!obj[19].equals(""))
		    		svo.setBuyer(obj[19].toString());
		    	if(obj[20]!=null&&!obj[20].equals(""))
		    		svo.setStoreId(obj[20].toString());
		    	if(obj[21]!=null&&!obj[21].equals(""))
		    		svo.setIdentifymanName(obj[21].toString());
		    	if(obj[22]!=null&&!obj[22].equals(""))
		    		svo.setAddpriceRate(obj[22].toString());
		    	if(obj[23]!=null&&!obj[23].equals(""))
		    		svo.setAddpriceRateId(obj[23].toString());
		    	list.add(svo);
			}
		}
		return list;
	}
	
	/**
	 * 退货单模块     入库单信息加载，条件查询
	 */
	public List<StRtGoodsVo> srg_searchByCondition(final String relcampId,final String storageId,final String storeId,final int enterprise_id)throws Exception
	{
		List<StRtGoodsVo> list=new ArrayList<StRtGoodsVo>();
		StringBuffer sb=new StringBuffer(" SELECT b.STORAGE_ID,b.BUYER,b.MANAGER,b.STF_NAME,s2.STF_NAME,b.STORE_NAME,b.RELCAMP_NAME,b.RELCAMP_ID, b.STORE_ID"+
		" FROM (SELECT a.STORAGE_ID,a.BUYER,a.MANAGER,s1.STF_NAME,a.STORE_NAME,a.RELCAMP_NAME,a.RELCAMP_ID,a.STORE_ID"+
		" FROM (SELECT DISTINCT st_goods_storage.STORAGE_ID,st_pur_order.BUYER,"+
		" st_pur_order.MANAGER,bas_storehouse.STORE_NAME,"+
		" bas_relation_campany.RELCAMP_NAME,"+
		" bas_relation_campany.RELCAMP_ID ,bas_storehouse.STORE_ID"+
		" FROM st_goods_storage,st_pur_order,bas_storehouse,bas_relation_campany"+
		" WHERE st_pur_order.ORDER_ID=st_goods_storage.CER_NO"+
		" AND bas_storehouse.STORE_ID=st_goods_storage.STORE_ID"+
		" AND bas_relation_campany.RELCAMP_ID=st_goods_storage.RELCAMP_ID AND st_goods_storage.enterprise_id="+enterprise_id+") AS a,bas_stuff AS s1"+
        " WHERE s1.STF_ID=a.BUYER)AS b ,bas_stuff AS s2 WHERE s2.STF_ID=b.MANAGER ");
		if(relcampId!=null&&!relcampId.equals(""))
			  sb.append(" AND b.RELCAMP_ID="+relcampId);
		if(storageId!=null&&!storageId.equals(""))
			  sb.append(" AND b.STORAGE_ID like '%"+StringEscapeUtils.escapeSql(storageId.trim())+"%'");
		if(storeId!=null&&!storeId.equals(""))
			  sb.append(" AND b.STORE_ID="+storeId);
		List<Object[]> resultList=createSQLQuery(sb.toString());
	    if(resultList!=null&&resultList.size()>0){
	    	Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StRtGoodsVo srgvo=new StRtGoodsVo();
				obj=resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					srgvo.setStorageId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					srgvo.setBuyerId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					srgvo.setManagerId(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					srgvo.setManager(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					srgvo.setBuyer(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					srgvo.setStoreName(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					srgvo.setRelcampName(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					srgvo.setRelcampId(obj[7].toString());
				if(obj[8]!=null&&!obj[8].equals(""))
					srgvo.setStoreId(obj[8].toString());
				list.add(srgvo);
			}
	    }
		return list;
	}
	
	/**
	 * 退货单模块 根据入库单号查询入库单信息
	 */
	public List<StRtGoodsVo> searchstGoodsStorageByStorageId(final String storageId) throws Exception {
		List<StRtGoodsVo> list=new ArrayList<StRtGoodsVo>();
		StringBuffer sb=new StringBuffer("SELECT STORAGE_ID,RELCAMP_ID,RELCAMP_NAME,MANAGER_NAME,MANAGER,"+
		" BUYER_NAME,BUYER,STORE_NAME FROM ("+
		" SELECT D.*,bas_storehouse.STORE_NAME FROM ( "+
		" SELECT C.*,bas_relation_campany.RELCAMP_NAME FROM ("+
		" SELECT B.*,bas_stuff.STF_NAME AS BUYER_NAME FROM ("+
		" SELECT A.*,bas_stuff.STF_NAME AS MANAGER_NAME FROM ("+
		" SELECT st_goods_storage.STORAGE_ID,st_goods_storage.STORE_ID,"+
		" st_pur_order.MANAGER,st_pur_order.BUYER,"+
		" st_pur_order.RELCAMP_ID FROM st_goods_storage INNER JOIN st_pur_order"+
		" ON st_goods_storage.CER_NO=st_pur_order.ORDER_ID) AS A"+
		" INNER JOIN bas_stuff ON A.MANAGER=bas_stuff.STF_ID) AS B"+
		" INNER JOIN bas_stuff ON B.BUYER=bas_stuff.STF_ID) AS C"+
		" INNER JOIN bas_relation_campany ON C.RELCAMP_ID=bas_relation_campany.RELCAMP_ID ) AS D"+
		" INNER JOIN bas_storehouse ON D.STORE_ID=bas_storehouse.STORE_ID ) AS E where 1=1 ");
		if(storageId!=null && !storageId.trim().equals(""))
			sb.append(" AND STORAGE_ID = '"+storageId.trim()+"'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		if(resultList!=null&&resultList.size()>0){
			 Object[] obj=null;
			  for (int i = 0; i < resultList.size(); i++) {
				StRtGoodsVo srgvo=new StRtGoodsVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					srgvo.setStorageId(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					srgvo.setRelcampId(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					srgvo.setRelcampName(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					srgvo.setManager(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					srgvo.setManagerId(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					srgvo.setBuyerId(obj[5].toString());
				if(obj[6]!=null&&!obj[6].equals(""))
					srgvo.setBuyer(obj[6].toString());
				if(obj[7]!=null&&!obj[7].equals(""))
					srgvo.setStoreName(obj[7].toString());
				list.add(srgvo);
			}
		}
		return list;
	}

	// 下一条记录
	@SuppressWarnings("unchecked")
	public List<StGoodsStorage> findBystorageId(String storageId) {
		String hql = "from StGoodsStorage WHERE storageId > '" + storageId
				+ "' ORDER BY storageId ASC limit 1";
		return this.getHibernateTemplate().find(hql);
	}

	// 上一条记录
	@SuppressWarnings("unchecked")
	public List<StGoodsStorage> findBystorageId1(String storageId) {
		String hql = "from StGoodsStorage WHERE storageId <'" + storageId
				+ "' ORDER BY storageId DESC limit 1";
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 财务模块   配件动态变化   入库退货配件信息   预加载
	 */
	 
	public List<PartsTrendsChangeSearchVo> loadStStorageAndStRtGoods()throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		String sql="SELECT * FROM loadStStorageAndStRtGoodsTable";
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
					stcsvo.setStoreName(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					stcsvo.setStomDate(obj[9].toString().substring(0, 10));
				if(obj[10]!=null&&!"".equals(obj[10]))
					stcsvo.setStrtpmId(obj[10].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}
	
	/**
	 * 财务模块   配件动态变化   入库退货配件信息   综合查询
	 */
	
	public List<PartsTrendsChangeSearchVo> searchStStorageAndStRtGoodsByCondition(PartsTrendsChangeSearchVo ptcsvo)throws Exception{
		List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
		StringBuffer sql=new StringBuffer("SELECT * FROM loadStStorageAndStRtGoodsTable AS A where 1=1");
		if(ptcsvo.getStomDateStart()!=null&&!"".equals(ptcsvo.getStomDateStart().trim())){
				sql.append(" and DATE_FORMAT(A.STORAGE_DATE,'%Y-%m-%d') >='"+ptcsvo.getStomDateStart().trim()+"'");
		}
		if(ptcsvo.getStomDateEnd()!=null&&!"".equals(ptcsvo.getStomDateEnd().trim())){  	
		        sql.append(" and DATE_FORMAT(A.STORAGE_DATE,'%Y-%m-%d') <='"+ptcsvo.getStomDateEnd().trim()+"'");
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
					stcsvo.setStoreName(obj[8].toString());
				if(obj[9]!=null&&!"".equals(obj[9]))
					stcsvo.setStomDate(obj[9].toString().substring(0, 10));
				if(obj[10]!=null&&!"".equals(obj[10]))
					stcsvo.setStrtpmId(obj[10].toString());
				list.add(stcsvo);
			}
		}
		return list;
	}

    public Json loadPartsDynamicDate(
            PartsTrendsChangeSearchVo ptcsvo) throws Exception
    {
        Json json = new Json();
        List<PartsTrendsChangeSearchVo> list=new ArrayList<PartsTrendsChangeSearchVo>();
        int total = 0;
        //企业编号,默认为1
        String company = ptcsvo.getEnterpriseId().toString();
        if(ptcsvo.getStomDateStart()==null || "".equals(ptcsvo.getStomDateStart()))
            ptcsvo.setStomDateStart(FormatDate.getFistDay());
        if(ptcsvo.getStomDateEnd()==null || "".equals(ptcsvo.getStomDateEnd()))
            ptcsvo.setStomDateEnd(FormatDate.getEndDay());
        if(ptcsvo.getPartsId()==null)
            ptcsvo.setPartsId("");
        if(ptcsvo.getStoreId()==null)
            ptcsvo.setStoreId("");
        if(ptcsvo.getCbrdId()==null)
            ptcsvo.setCbrdId("");
        if(ptcsvo.getStypeId()==null)//
            ptcsvo.setStypeId("");
        StringBuffer procedureName=new StringBuffer(" { CALL Invoicing2_stock(?,?,?,?,?,?,?,?,?) } ");
        List<Object> params = new ArrayList<Object>();
        params.add(0,ptcsvo.getStomDateStart());
        params.add(1,ptcsvo.getStomDateEnd());
        params.add(2,ptcsvo.getPartsId());
        params.add(3,ptcsvo.getStoreId());
        params.add(4,ptcsvo.getCbrdId());
        params.add(5,ptcsvo.getStypeId());
        params.add(6,company);
        params.add(7,ptcsvo.getPage());
        params.add(8,ptcsvo.getRows());
        List<Object[]> resultList=getProcedureQuery(procedureName.toString(), params);
        if(resultList!=null&&!"".equals(resultList)){
            Object[] obj=null;
            for (int i = 0; i < resultList.size(); i++) {
                PartsTrendsChangeSearchVo stcsvo=new PartsTrendsChangeSearchVo();
                obj=resultList.get(i);
                stcsvo.setStomDateStart(ptcsvo.getStomDateStart());
                stcsvo.setStomDateEnd(ptcsvo.getStomDateEnd());
                if(obj[0]!=null&&!"".equals(obj[0]))
                    stcsvo.setPartsId(obj[0].toString());
                if(obj[1]!=null&&!"".equals(obj[1]))
                    stcsvo.setPartsName(obj[1].toString());   
                if(obj[2]!=null&&!"".equals(obj[2]))
                    stcsvo.setStoreId(obj[2].toString());
                if(obj[3]!=null&&!"".equals(obj[3]))
                    stcsvo.setStoreName(obj[3].toString());
                if(obj[4]!=null&&!"".equals(obj[4]))
                    stcsvo.setPartsLibrary(obj[4].toString());
                if(obj[5]!=null&&!"".equals(obj[5]))
                    stcsvo.setCbrdName(obj[5].toString());
                if(obj[6]!=null&&!"".equals(obj[6]))
                    stcsvo.setStypeName(obj[6].toString());
                if(obj[7]!=null&&!"".equals(obj[7]))
                    stcsvo.setPriorPeriodCount(obj[7].toString());
                if(obj[8]!=null&&!"".equals(obj[8]))
                    stcsvo.setPriorPeriodSellAmount(obj[8].toString());
                if(obj[9]!=null&&!"".equals(obj[9]))
                    stcsvo.setPriorPeriodCostAmount(obj[9].toString());
                if(obj[10]!=null&&!"".equals(obj[10]))
                    stcsvo.setCurrentPeriodInCount(obj[10].toString());
                if(obj[11]!=null&&!"".equals(obj[11]))
                    stcsvo.setCurrentPeriodInSellAmount(obj[11].toString());
                if(obj[12]!=null&&!"".equals(obj[12]))
                    stcsvo.setCurrentPeriodInCostAmount(obj[12].toString());
                if(obj[13]!=null&&!"".equals(obj[13]))
                    stcsvo.setCurrentPeriodOutCount(obj[13].toString());
                if(obj[14]!=null&&!"".equals(obj[14]))
                    stcsvo.setCurrentPeriodOutSellAmount(obj[14].toString());
                if(obj[15]!=null&&!"".equals(obj[15]))
                    stcsvo.setCurrentPeriodOutCostAmount(obj[15].toString());
                if(obj[16]!=null&&!"".equals(obj[16]))
                    stcsvo.setCurrentCheckCount(obj[16].toString());
                if(obj[17]!=null&&!"".equals(obj[17]))
                    stcsvo.setCurrentCheckSellAmount(obj[17].toString());
                if(obj[18]!=null&&!"".equals(obj[18]))
                    stcsvo.setCurrentCheckCostAmount(obj[18].toString());
                if(obj[19]!=null&&!"".equals(obj[19]))
                    stcsvo.setSurplusCount(obj[19].toString());
                if(obj[20]!=null&&!"".equals(obj[20]))
                    stcsvo.setSurplusSellAmount(obj[20].toString());
                if(obj[21]!=null&&!"".equals(obj[21]))
                    stcsvo.setSurplusCostAmount(obj[21].toString());
                if(obj[22]!=null&&!"".equals(obj[22]))
                    total = obj[22] != null && !"".equals(obj[22].toString()) ? Integer.parseInt(obj[22].toString()) : 0;
                list.add(stcsvo);
            }
        }
        json.setRows(list);
        json.setTotal(total);
        return json;
    }
}
