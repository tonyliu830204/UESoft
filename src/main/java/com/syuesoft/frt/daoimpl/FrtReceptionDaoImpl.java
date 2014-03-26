package com.syuesoft.frt.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtReception;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.Json;

/**
 * 前台接车dao
 * 
 * @author Liujian
 */
@Repository("frtReceptionDao")
public class FrtReceptionDaoImpl extends BaseDaoImpl<FrtReception> implements
        FrtReceptionDao
{

    public void updateFrtReception(FrtReception fr) throws Exception{
        this.getSessionFactory().getCurrentSession().flush();
        this.getSessionFactory().getCurrentSession().clear();
        super.merge(fr);
        this.getSessionFactory().getCurrentSession().clear();
    }

    public void clear() throws Exception{
        this.getSessionFactory().getCurrentSession().clear();
    }

    public void flush() throws Exception{
        this.getSessionFactory().getCurrentSession().flush();
    }

    /**
     * (预出库模块 工单退料模块 ) 工单信息查询
     */
    
    public Json loadFrtReception(final int page, final int rows,
            final String sort, final String order, final String state,final int enterprise_id)
            throws Exception{
        List<StOutVo> list = new ArrayList<StOutVo>();
        StringBuffer sb = new StringBuffer("SELECT frt_reception.RECEPTION_ID ,frt_car.CAR_LICENSE");
        sb.append(",bas_car_type.CTYPE_NAME,frt_custom.CUSTOM_NAME,bas_repair_group.REPGRP_NAME,bas_stuff.STF_NAME");
        sb.append(",frt_car.CAR_VIN,frt_car.CAR_LAST_REPAIR_DISTANCE,reptype.REPT_NAME,frt_reception.INTER_DATE FROM ");
        sb.append(" frt_reception,frt_car,bas_car_type,frt_custom,bas_repair_group,bas_stuff,reptype");
        sb.append(" WHERE frt_reception.CAR_ID=frt_car.CAR_ID AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID");
        sb.append(" AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID AND frt_reception.REPGRP_ID=bas_repair_group.REPGRP_ID AND frt_reception.enterprise_id="+enterprise_id);
        if (state != null && !state.equals("") && state.equals("StRtParts"))
            sb.append(" AND frt_reception.RECEPTION_ID IN (SELECT st_out_main.CER_NO FROM st_out_main)");
        sb.append(" AND frt_reception.RECEPTOR=bas_stuff.STF_ID AND frt_reception.REPT_ID=reptype.REPT_ID");
        sb.append(" AND frt_reception.RECEPTION_STATUS IN ("
                + Contstants.DOCUMENT_TAG.DOCUMENTState3 + ","
                + Contstants.DOCUMENT_TAG.DOCUMENTState4 + ","
                + Contstants.DOCUMENT_TAG.DOCUMENTState5 + ","
                + Contstants.DOCUMENT_TAG.DOCUMENTState6 + ")");
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString(), page, rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++) {
                obj = (Object[]) resultList.get(i);
                StOutVo stOutVo = new StOutVo();
                if (obj[1] != null && !obj[1].equals(""))
                    stOutVo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    stOutVo.setCtypeName(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    stOutVo.setCustomName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    stOutVo.setRepgrpName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    stOutVo.setStfName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    stOutVo.setCarVan(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    stOutVo.setCarLastRepairDistance(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    stOutVo.setReptName(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    stOutVo.setResvRealTime(obj[9].toString());
                boolean isno=true;
                if (obj[0] != null && !obj[0].equals("")){
                    stOutVo.setReceptionId(obj[0].toString());
	                List<StRtPartsVo> rList=this.searchRecpPartsByReceptionId(obj[0].toString());
	                if(rList!=null&&rList.size()>0)
		                for (StRtPartsVo stRtPartsVo : rList) {
							if(Double.parseDouble(stRtPartsVo.getPartsNum())<=0)
								isno=false;
						}
	                else{
	                	if (state != null && !state.equals("") && state.equals("StRtParts"))
	                		isno=false;
	                }
                }
                if(isno)
                   list.add(stOutVo);
            }
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }
    
    /**
	 * 工单退料模块  根据相对应工单获取工单对应计划用料信息
	 */
	public List<StRtPartsVo> searchRecpPartsByReceptionId(final String receptionId)throws Exception
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
		return list;
	}

    
    public Json searchFrtReceptionByCondition(final String receptionId,
            final String carLicense, final String customId, final String carVan,final int enterprise_id)
            throws Exception{
        List<StOutVo> list = new ArrayList<StOutVo>();
        StringBuffer sb = new StringBuffer(
		" SELECT frt_reception.RECEPTION_ID ,frt_car.CAR_LICENSE"
		+ ",bas_car_type.CTYPE_NAME,frt_custom.CUSTOM_NAME,bas_repair_group.REPGRP_NAME,bas_stuff.STF_NAME"
		+ ",frt_car.CAR_VIN,frt_car.CAR_LAST_REPAIR_DISTANCE,reptype.REPT_NAME,frt_reception.INTER_DATE FROM "
		+ " frt_reception,frt_car,bas_car_type,frt_custom,bas_repair_group,bas_stuff,reptype"
		+ " WHERE frt_reception.CAR_ID=frt_car.CAR_ID AND frt_car.CTYPE_ID=bas_car_type.CTYPE_ID"
		+ " AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID AND frt_reception.REPGRP_ID=bas_repair_group.REPGRP_ID AND frt_reception.enterprise_id="+enterprise_id+""
		+ " AND frt_reception.RECEPTOR=bas_stuff.STF_ID AND frt_reception.REPT_ID=reptype.REPT_ID"
		+ " AND frt_reception.RECEPTION_STATUS IN ("
        + Contstants.DOCUMENT_TAG.DOCUMENTState3 + ","
        + Contstants.DOCUMENT_TAG.DOCUMENTState4 + ","
        + Contstants.DOCUMENT_TAG.DOCUMENTState5 + ","
        + Contstants.DOCUMENT_TAG.DOCUMENTState6 + ")");
        if (receptionId != null && !receptionId.trim().equals(""))
            sb.append(" AND frt_reception.RECEPTION_ID like '%"
                    + StringEscapeUtils.escapeSql(receptionId.trim()) + "%'");
        if (carLicense != null && !carLicense.trim().equals(""))
            sb.append(" AND (frt_car.CAR_ID like '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%' OR frt_car.CAR_LICENSE LIKE '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%')");
        if (customId != null && !customId.trim().equals(""))
            sb.append(" AND (frt_custom.CUSTOM_ID like '%" + StringEscapeUtils.escapeSql(customId.trim())
                    + "%' OR frt_custom.CUSTOM_NAME like '%" + StringEscapeUtils.escapeSql(customId.trim())
                    + "%')");
        if (carVan != null && !carVan.trim().equals(""))
            sb.append(" AND frt_car.CAR_VIN like '%" + StringEscapeUtils.escapeSql(carVan.trim()) + "%'");
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                obj = (Object[]) resultList.get(i);
                StOutVo stOutVo = new StOutVo();
                if (obj[1] != null && !obj[1].equals(""))
                    stOutVo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    stOutVo.setCtypeName(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    stOutVo.setCustomName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    stOutVo.setRepgrpName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    stOutVo.setStfName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    stOutVo.setCarVan(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    stOutVo.setCarLastRepairDistance(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    stOutVo.setReptName(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    stOutVo.setResvRealTime(obj[9].toString());
                boolean isno=true;
                if (obj[0] != null && !obj[0].equals("")){
                    stOutVo.setReceptionId(obj[0].toString());
	                List<StRtPartsVo> rList=this.searchRecpPartsByReceptionId(obj[0].toString());
	                if(rList!=null&&rList.size()>0)
		                for (StRtPartsVo stRtPartsVo : rList) {
							if(Double.parseDouble(stRtPartsVo.getPartsNum())<=0)
								isno=false;
						}
                }
                if(isno)
                   list.add(stOutVo);
                list.add(stOutVo);
            }
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }
    
    /**
     * 前台接车接待员业务统计
     */
    public Json loadSearchReceptorWork(FrtReceptionVo frtReceptionVo)throws Exception{
    	List<FrtReceptionVo> list =new ArrayList<FrtReceptionVo>();
    	StringBuffer sbm=new StringBuffer("SELECT bas_stuff.STF_NAME,'' AS RECEPTION_ID,'' AS CAR_LICENSE,'' AS CUSTOM_NAME,'' AS INTER_DATE,");
    	sbm.append(" '' AS PRECLR_TIME,frt_pre_clearing.PRECLR_WKTIME_AMOUNT,frt_pre_clearing.PRE_MPR_MAT_AMOUNT,");
    	sbm.append(" SUM(frt_pre_clearing.PRECLR_OTHER_AMOUNT),SUM(frt_pre_clearing.PRECLR_SUM_AMOUNT),");
    	sbm.append(" SUM(frt_pre_clearing.PRECLR_REAL_AMOUNT),'','' FROM frt_reception,bas_stuff,frt_custom,frt_pre_clearing,frt_car,reptype ");
    	sbm.append(" WHERE frt_reception.RECEPTOR=bas_stuff.STF_ID AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID");
    	sbm.append(" AND frt_reception.RECEPTION_ID=frt_pre_clearing.RECEPTION_ID AND frt_reception.CAR_ID=frt_car.CAR_ID AND frt_reception.REPT_ID=reptype.REPT_ID");
    	if(frtReceptionVo.getStartPreclrTime()!=null&&!frtReceptionVo.getStartPreclrTime().equals("")&&frtReceptionVo.getEndPreclrTime()!=null&&!frtReceptionVo.getEndPreclrTime().equals(""))
    		sbm.append(" AND DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d') BETWEEN '"+frtReceptionVo.getStartPreclrTime()+"' AND '"+frtReceptionVo.getEndPreclrTime()+"' ");
    	if(frtReceptionVo.getStfName()!=null&&!frtReceptionVo.getStfName().trim().equals(""))
    		sbm.append(" AND bas_stuff.STF_ID ="+frtReceptionVo.getStfName().trim());
    	sbm.append(" GROUP BY bas_stuff.STF_NAME");
    	StringBuffer sbd=new StringBuffer("SELECT bas_stuff.STF_NAME,frt_reception.RECEPTION_ID ,frt_car.CAR_LICENSE");
		sbd.append(" ,frt_custom.CUSTOM_NAME,DATE_FORMAT(frt_reception.INTER_DATE,'%Y-%m-%d') AS INTER_DATE,");
		sbd.append(" DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d') AS PRECLR_TIME,frt_pre_clearing.PRECLR_WKTIME_AMOUNT,");
		sbd.append(" frt_pre_clearing.PRE_MPR_MAT_AMOUNT,frt_pre_clearing.PRECLR_OTHER_AMOUNT,");
		sbd.append(" frt_pre_clearing.PRECLR_SUM_AMOUNT,frt_pre_clearing.PRECLR_REAL_AMOUNT,reptype.REPT_NAME,bas_childdictionary.dataValue");
		sbd.append(" FROM frt_reception,bas_stuff,frt_custom,frt_pre_clearing,frt_car,reptype,bas_childdictionary");
		sbd.append(" WHERE frt_reception.RECEPTOR=bas_stuff.STF_ID AND frt_reception.CAR_ID=frt_car.CAR_ID");
		sbd.append(" AND frt_reception.REPT_ID=reptype.REPT_ID AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID");
		sbd.append(" AND frt_reception.RECEPTION_ID=frt_pre_clearing.RECEPTION_ID AND frt_reception.RCPT_BRANCH=bas_childdictionary.dataKey");
		if(frtReceptionVo.getStartPreclrTime()!=null&&!frtReceptionVo.getStartPreclrTime().equals("")&&frtReceptionVo.getEndPreclrTime()!=null&&!frtReceptionVo.getEndPreclrTime().equals(""))
			sbd.append(" AND DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d') BETWEEN '"+frtReceptionVo.getStartPreclrTime()+"' AND '"+frtReceptionVo.getEndPreclrTime()+"' ");
		if(frtReceptionVo.getStfName()!=null&&!frtReceptionVo.getStfName().trim().equals(""))
			sbd.append(" AND bas_stuff.STF_ID ="+frtReceptionVo.getStfName().trim());
		if(frtReceptionVo.getRcptBranch()!=null&&!frtReceptionVo.getRcptBranch().trim().equals(""))
			sbd.append(" AND bas_childdictionary.dataKey ='"+frtReceptionVo.getRcptBranch().trim()+"'");
    	if(frtReceptionVo.getCarLicense()!=null&&!frtReceptionVo.getCarLicense().trim().equals(""))
    		sbd.append(" AND frt_car.CAR_LICENSE ='"+frtReceptionVo.getCarLicense().trim()+"'");
    	if(frtReceptionVo.getReptId()!=null&&!frtReceptionVo.getReptId().equals(""))
    		sbd.append(" AND reptype.REPT_ID ="+frtReceptionVo.getReptId());
		List<Object[]> resultListm=createSQLQuery(sbm.toString());
    	if(resultListm!=null&&resultListm.size()>0){
    		Object[] objm=null;
    		for (int i = 0; i < resultListm.size(); i++) {
    			FrtReceptionVo frvom=new FrtReceptionVo();
    			objm=resultListm.get(i);
    			Object[] objd=null;
    			double preclrWktimeAmount=0;
    			double preMprMatAmount=0;
    			double preOtherAmount=0;
    			double preclrSumAmount=0;
    			double preRealAmount=0;
    			List<Object[]> resultListd=createSQLQuery(sbd.toString());
    			if(resultListd!=null&&resultListd.size()>0){
    				for (int j = 0; j < resultListd.size(); j++) {
    					FrtReceptionVo frvod=new FrtReceptionVo();
    					objd=resultListd.get(j);
    					if(objd[0].equals(objm[0])){
	    					if(objd[1]!=null&&!objd[1].equals("")){
	    						frvod.setReceptionId(objd[1].toString());
	    					}
	    					if(objd[2]!=null&&!objd[2].equals("")){
	    						frvod.setCarLicense(objd[2].toString());
	    					}
	    					if(objd[3]!=null&&!objd[3].equals("")){
	    						frvod.setCustomName(objd[3].toString());		
	    					}
	    					if(objd[4]!=null&&!objd[4].equals("")){
	    						frvod.setInterDate(objd[4].toString());
	    					}
	    					if(objd[5]!=null&&!objd[5].equals("")){
	    						frvod.setPreclrTime(objd[5].toString());
	    					}
	    					if(objd[6]!=null&&!objd[6].equals("")){
	    						frvod.setPreclrWktimeAmount(objd[6].toString());
	    						preclrWktimeAmount+=Double.parseDouble(objd[6].toString());
	    					}
	    					if(objd[7]!=null&&!objd[7].equals("")){
	    						frvod.setPreMprMatAmount(objd[7].toString());
	    						preMprMatAmount+=Double.parseDouble(objd[7].toString());
	    					}
	    					if(objd[8]!=null&&!objd[8].equals("")){
	    						frvod.setPreOtherAmount(objd[8].toString());
	    						preOtherAmount+=Double.parseDouble(objd[8].toString());
	    					}
	    					if(objd[9]!=null&&!objd[9].equals("")){
	    						frvod.setPreclrSumAmount(objd[9].toString());
	    						preclrSumAmount+=Double.parseDouble(objd[9].toString());
	    					}
	    					if(objd[10]!=null&&!objd[10].equals("")){
	    						frvod.setPreRealAmount(objd[10].toString());
	    						preRealAmount+=Double.parseDouble(objd[10].toString());
	    					}
	    					if(objd[11]!=null&&!objd[11].equals("")){
	    						frvod.setReptName(objd[11].toString());
	    					}
	    					if(objd[12]!=null&&!objd[12].equals("")){
	    						frvod.setRcptBranch(objd[12].toString());
	    					}
	    					list.add(frvod);
    					}
					}
    			}
    			if(objm[0]!=null&&!objm[0].equals("")){
    				frvom.setStfName(objm[0].toString());
        		}
    			frvom.setReceptionId("小计");
				frvom.setPreclrWktimeAmount(new BaseAction().doubleFormat(preclrWktimeAmount)+"");
				frvom.setPreMprMatAmount(new BaseAction().doubleFormat(preMprMatAmount)+"");
				frvom.setPreOtherAmount(new BaseAction().doubleFormat(preOtherAmount)+"");
				frvom.setPreclrSumAmount(new BaseAction().doubleFormat(preclrSumAmount)+"");
				frvom.setPreRealAmount(new BaseAction().doubleFormat(preRealAmount)+"");
    			if(preRealAmount!=0)
    			    list.add(frvom);
			}
    	}
    	Json json=new Json();
    	json.setRows(list);
    	if(list!=null&&list.size()>0)
    	    json.setTotal(list.size());
    	else
    		json.setTotal(0);
    	return json;
    }
    
}
