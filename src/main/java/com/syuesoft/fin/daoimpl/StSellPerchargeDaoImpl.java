package com.syuesoft.fin.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.dao.StSellPerchargeDao;
import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.model.StSellPercharge;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

/**
 * 维修预收款dao
 * @author SuMing
 */
@Repository("stSellPerchargeDao")
public class StSellPerchargeDaoImpl extends BaseDaoImpl<StSellPercharge>
        implements StSellPerchargeDao
{
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    /**
     * 维修储备金 车辆信息 预加载
     */
    public Json loadFrtCarInfo(int page, int rows,int enterprise_id)
            throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        String sql = "SELECT * FROM (SELECT frt_car.CAR_ID,frt_car.CAR_LICENSE,bas_car_brand.CBRD_NAME,bas_car_type.CTYPE_NAME,"+
		" frt_custom.CUSTOM_NAME,frt_car.CAR_RELATION_PERSON,"+
        " frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.enterprise_id FROM "+
		" frt_car LEFT JOIN bas_car_type ON frt_car.CTYPE_ID=bas_car_type.CTYPE_ID"+
		" LEFT JOIN bas_car_brand ON bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"+
		" LEFT JOIN frt_custom ON frt_car.CUSTOM_ID=frt_custom.CUSTOM_ID) AS a where 1=1 and enterprise_id="+enterprise_id;
        List<Object[]> resultList=new ArrayList<Object[]>();
        int count =getSQLCount(sql, null);
        if(page!=0&&rows!=0)
        	resultList=createSQLQuery(sql, page, rows);
        else
        	resultList=createSQLQuery(sql);
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setCarId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCbrdName(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCtypeName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCustomName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCarRelationPerson(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationTel1(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel2(obj[7].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修储备金 车辆信息    综合查询
     */
    public Json searchFrtCarInfoByCondition(
            String carLicense,int enterprise_id) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        String sql = "SELECT * FROM (SELECT frt_car.CAR_ID,frt_car.CAR_LICENSE,bas_car_brand.CBRD_NAME,bas_car_type.CTYPE_NAME,"+
		" frt_custom.CUSTOM_NAME,frt_car.CAR_RELATION_PERSON,"+
        " frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.enterprise_id FROM "+
		" frt_car LEFT JOIN bas_car_type ON frt_car.CTYPE_ID=bas_car_type.CTYPE_ID"+
		" LEFT JOIN bas_car_brand ON bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"+
		" LEFT JOIN frt_custom ON frt_car.CUSTOM_ID=frt_custom.CUSTOM_ID) AS a where 1=1 and enterprise_id="+enterprise_id;
        if(carLicense!=null&&!carLicense.trim().equals(""))
        	sql+=" and CAR_LICENSE like '%"+ StringEscapeUtils.escapeSql(carLicense.trim()) + "%'";
        int count =getSQLCount(sql, null);
        List<Object[]> resultList=createSQLQuery(sql);
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setCarId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCbrdName(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCtypeName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCustomName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCarRelationPerson(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationTel1(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel2(obj[7].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修储备金信息 预加载
     */
    public Json loadStSellPercharge(int page, int rows,String beginDate,String endDate,int enterpriseId)
            throws Exception{
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer(""+
            " SELECT PERCHARGE_ID,CAR_ID,CAR_LICENSE,CBRD_NAME,CTYPE_NAME,CAR_RELATION_PERSON as temp1,dataValue,"+
			" STF_NAME,PERCHARGE_DATE,CUR_PERCHARGE,CHARGE_REMARK,CUSTOM_NAME,child_id,"+
			" dataValue as temp2,CAR_RELATION_PERSON,CAR_RELATION_TEL1,CAR_RELATION_TEL2,"+
			" PRECLR_INOICE_TYPE,PRECLR_NO,CAR_VIN  FROM ("+
			" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
			" SELECT e.*,bas_stuff.STF_NAME FROM ("+
			" SELECT d.*,bc.dataValue FROM ("+
			" SELECT c.*,bas_car_brand.CBRD_NAME FROM ("+
			" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+
			" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CTYPE_ID"+
			" ,frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.CAR_VIN"+
			" ,frt_car.CUSTOM_ID FROM ("+
			" SELECT st_sell_percharge.*,st_recharge.CAR_ID,st_recharge.PERCHARGE_TYPE"+
			" FROM st_sell_percharge LEFT JOIN  st_recharge"+
			" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
			" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
			" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
			" LEFT JOIN bas_car_brand ON c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
			" LEFT JOIN bas_childdictionary AS bc ON d.CHILD_ID= bc.child_id) AS e"+
			" LEFT JOIN bas_stuff ON e.STF_ID=bas_stuff.STF_ID) AS f"+
			" LEFT JOIN frt_custom ON f.CUSTOM_ID =frt_custom.CUSTOM_ID"+
			" ) AS g WHERE FLAG=0 AND PERCHARGE_TYPE='储备金'");
        if(beginDate == null  && endDate == null ){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	
        	sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
		sb.append(" GROUP BY PERCHARGE_ID");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList =null;
        if(page!=0&&rows!=0)
            resultList =createSQLQuery(sb.toString(), page, rows);
        else
        	resultList =createSQLQuery(sb.toString());
        Object[] obj = null;
        if(resultList!=null&&resultList.size()>0){
	        for (int i = 0; i < resultList.size(); i++) {
	            StSellPerchargeVo sspvo = new StSellPerchargeVo();
	            obj = (Object[]) resultList.get(i);
	            if (obj[0] != null && !obj[0].equals(""))
	                sspvo.setPerchargeId(obj[0].toString());
	            if (obj[1] != null && !obj[1].equals(""))
	                sspvo.setCarId(obj[1].toString());
	            if (obj[2] != null && !obj[2].equals(""))
	                sspvo.setCarLicense(obj[2].toString());
	            if (obj[3] != null && !obj[3].equals(""))
	                sspvo.setCbrdName(obj[3].toString());
	            if (obj[4] != null && !obj[4].equals(""))
	                sspvo.setCtypeName(obj[4].toString());
	            if (obj[5] != null && !obj[5].equals(""))
	                sspvo.setCarRelationPerson(obj[5].toString());
	            if (obj[6] != null && !obj[6].equals(""))
	                sspvo.setPaymentName(obj[6].toString());
	            if (obj[7] != null && !obj[7].equals(""))
	                sspvo.setStfName(obj[7].toString());
	            if (obj[8] != null && !obj[8].equals(""))
	                sspvo.setPerchargeDate(Utils.dateFormat(obj[8].toString(), true));
	            if (obj[9] != null && !obj[9].equals(""))
	                sspvo.setCurPercharge(Double.parseDouble(obj[9].toString()));
	            if (obj[10] != null && !obj[10].equals(""))
	                sspvo.setChargeRemark(obj[10].toString());
	            if (obj[11] != null && !obj[11].equals(""))
	                sspvo.setCustomName(obj[11].toString());
	            if (obj[12] != null && !obj[12].equals(""))
	                sspvo.setPaymentId(obj[12].toString());
	            if (obj[13] != null && !obj[13].equals(""))
	                sspvo.setPaymentName(obj[13].toString());
	            if (obj[14] != null && !obj[14].equals(""))
	                sspvo.setCarRelationPerson(obj[14].toString());
	            if (obj[15] != null && !obj[15].equals(""))
	                sspvo.setCarRelationTel1(obj[15].toString());
	            if (obj[16] != null && !obj[16].equals(""))
	                sspvo.setCarRelationTel2(obj[16].toString());
	            if (obj[17] != null && !obj[17].equals(""))
	                sspvo.setPreclrInoiceType(obj[17].toString());
	            if (obj[18] != null && !obj[18].equals(""))
	                sspvo.setPreclrNo(obj[18].toString());
	            if (obj[19] != null && !obj[19].equals(""))
	                sspvo.setVin(obj[19].toString());
	            list.add(sspvo);
	        }
        }
       Json json=new Json();
       json.setRows(list);
       json.setTotal(count);
       return json;
    }

    /**
     * 维修储备金信息 综合查询
     */
    @SuppressWarnings("unchecked")
    public Json searchStSellPerchargeByCondition(
            String perchargeDateBegin, String perchargeDateEnd,
            String carLicense, String vin,Integer enterpriseId) throws Exception
    {
    	Json json=new Json();
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer(""+
                " SELECT PERCHARGE_ID,CAR_ID,CAR_LICENSE,CBRD_NAME,CTYPE_NAME,CAR_RELATION_PERSON,dataValue AS dataValue1,"+
    			" STF_NAME,PERCHARGE_DATE,CUR_PERCHARGE,CHARGE_REMARK,CUSTOM_NAME,child_id,"+
    			" dataValue AS dataValue2 ,CAR_RELATION_PERSON,CAR_RELATION_TEL1,CAR_RELATION_TEL2,"+
    			" PRECLR_INOICE_TYPE,PRECLR_NO,CAR_VIN  FROM ("+
    			" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
    			" SELECT e.*,bas_stuff.STF_NAME FROM ("+
    			" SELECT d.*,bc.dataValue FROM ("+
    			" SELECT c.*,bas_car_brand.CBRD_NAME FROM ("+
    			" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+
    			" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CTYPE_ID"+
    			" ,frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.CAR_VIN"+
    			" ,frt_car.CUSTOM_ID FROM ("+
    			" SELECT st_sell_percharge.*,st_recharge.CAR_ID,st_recharge.PERCHARGE_TYPE"+
    			" FROM st_sell_percharge LEFT JOIN  st_recharge"+
    			" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
    			" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
    			" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
    			" LEFT JOIN bas_car_brand ON c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
    			" LEFT JOIN bas_childdictionary AS bc ON d.CHILD_ID= bc.child_id) AS e"+
    			" LEFT JOIN bas_stuff ON e.STF_ID=bas_stuff.STF_ID) AS f"+
    			" LEFT JOIN frt_custom ON f.CUSTOM_ID =frt_custom.CUSTOM_ID"+
    			" ) AS g WHERE FLAG=0  AND PERCHARGE_TYPE='储备金'");
        if (perchargeDateBegin != null && !"".equals(perchargeDateBegin))
            if (perchargeDateEnd != null && !"".equals(perchargeDateEnd))
                sb.append(" AND PERCHARGE_DATE BETWEEN '"
                        + perchargeDateBegin.trim() + "' AND '"
                        + perchargeDateEnd.trim() + "'");
            else
            	sb.append(" AND PERCHARGE_DATE>='"
                        + perchargeDateBegin.trim() + "'");
        else
            if (perchargeDateEnd != null && !"".equals(perchargeDateEnd))
            	sb.append(" AND PERCHARGE_DATE<='"
                        + perchargeDateEnd.trim() + "'");
        if (carLicense != null && !"".equals(carLicense))
        	sb.append(" AND CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%'");
        if (vin != null && !"".equals(vin))
        	sb.append(" AND CAR_VIN like '%" + StringEscapeUtils.escapeSql(vin.trim()) + "%'");
        sb.append(" GROUP BY recharge_ID");
        int count=this.getCountBySQL(sb.toString());
        List resultList = createSQLQuery(sb.toString());
        Object[] obj = null;
        if(resultList!=null&&resultList.size()>0)
        for (int i = 0; i < resultList.size(); i++){
            StSellPerchargeVo sspvo = new StSellPerchargeVo();
            obj = (Object[]) resultList.get(i);
            if (obj[0] != null && !obj[0].equals(""))
                sspvo.setPerchargeId(obj[0].toString());
            if (obj[1] != null && !obj[1].equals(""))
                sspvo.setCarId(obj[1].toString());
            if (obj[2] != null && !obj[2].equals(""))
                sspvo.setCarLicense(obj[2].toString());
            if (obj[3] != null && !obj[3].equals(""))
                sspvo.setCbrdName(obj[3].toString());
            if (obj[4] != null && !obj[4].equals(""))
                sspvo.setCtypeName(obj[4].toString());
            if (obj[5] != null && !obj[5].equals(""))
                sspvo.setCarRelationPerson(obj[5].toString());
            if (obj[6] != null && !obj[6].equals(""))
                sspvo.setPaymentName(obj[6].toString());
            if (obj[7] != null && !obj[7].equals(""))
                sspvo.setStfName(obj[7].toString());
            if (obj[8] != null && !obj[8].equals(""))
                sspvo.setPerchargeDate(Utils
                        .dateFormat(obj[8].toString(), true));
            if (obj[9] != null && !obj[9].equals(""))
                sspvo.setCurPercharge(Double.parseDouble(obj[9].toString()));
            if (obj[10] != null && !obj[10].equals(""))
                sspvo.setChargeRemark(obj[10].toString());
            if (obj[11] != null && !obj[11].equals(""))
                sspvo.setCustomName(obj[11].toString());
            if (obj[12] != null && !obj[12].equals(""))
                sspvo.setPaymentId(obj[12].toString());
            if (obj[13] != null && !obj[13].equals(""))
                sspvo.setPaymentName(obj[13].toString());
            if (obj[14] != null && !obj[14].equals(""))
                sspvo.setCarRelationPerson(obj[14].toString());
            if (obj[15] != null && !obj[15].equals(""))
                sspvo.setCarRelationTel1(obj[15].toString());
            if (obj[16] != null && !obj[16].equals(""))
                sspvo.setCarRelationTel2(obj[16].toString());
            if (obj[17] != null && !obj[17].equals(""))
                sspvo.setPreclrInoiceType(obj[17].toString());
            if (obj[18] != null && !obj[18].equals(""))
                sspvo.setPreclrNo(obj[18].toString());
            if (obj[19] != null && !obj[19].equals(""))
                sspvo.setVin(obj[19].toString());
            list.add(sspvo);
        }
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修储备金信息 预加载
     */
    public Json loadPreStSellPercharge(int page, int rows,String perchargeDateBegin,String perchargeDateEnd,int enterpriseId)
            throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer(""+
                " SELECT PERCHARGE_ID,CAR_ID,CAR_LICENSE,CBRD_NAME,CTYPE_NAME,CAR_RELATION_PERSON as temp1,dataValue ,"+
    			" STF_NAME,PERCHARGE_DATE,CUR_PERCHARGE,CHARGE_REMARK,CUSTOM_NAME,child_id,"+
    			" dataValue as temp2,CAR_RELATION_PERSON,CAR_RELATION_TEL1,CAR_RELATION_TEL2,"+
    			" PRECLR_INOICE_TYPE,PRECLR_NO,CAR_VIN  FROM ("+
    			" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
    			" SELECT e.*,bas_stuff.STF_NAME FROM ("+
    			" SELECT d.*,bc.dataValue FROM ("+
    			" SELECT c.*,bas_car_brand.CBRD_NAME FROM ("+
    			" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+
    			" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CTYPE_ID"+
    			" ,frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.CAR_VIN"+
    			" ,frt_car.CUSTOM_ID FROM ("+
    			" SELECT st_sell_percharge.*,st_recharge.CAR_ID,st_recharge.PERCHARGE_TYPE"+
    			" FROM st_sell_percharge LEFT JOIN  st_recharge"+
    			" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID  where st_sell_percharge.enterprise_id="+enterpriseId +") AS a"+
    			" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
    			" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
    			" LEFT JOIN bas_car_brand ON c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
    			" LEFT JOIN bas_childdictionary AS bc ON d.CHILD_ID= bc.child_id) AS e"+
    			" LEFT JOIN bas_stuff ON e.STF_ID=bas_stuff.STF_ID) AS f"+
    			" LEFT JOIN frt_custom ON f.CUSTOM_ID =frt_custom.CUSTOM_ID"+
    			" ) AS g WHERE FLAG=0 AND PERCHARGE_TYPE='预收款'");
        if(perchargeDateBegin == null  && perchargeDateEnd == null){
        	String param=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANCETIMESECT,enterpriseId).getCiValue();
        	if(param!=null && !("".equals(param))){
        		sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(param))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	sb.append(" and DATE_FORMAT(PERCHARGE_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
    	
		sb.append(" GROUP BY PERCHARGE_ID");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList =new ArrayList<Object[]>();
        if(page!=0&&rows!=0)
            resultList =createSQLQuery(sb.toString(), page, rows);
        else
        	resultList =createSQLQuery(sb.toString());
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
        	 for (int i = 0; i < resultList.size(); i++){
                 StSellPerchargeVo sspvo = new StSellPerchargeVo();
                 obj = (Object[]) resultList.get(i);
                 if (obj[0] != null && !obj[0].equals(""))
                     sspvo.setPerchargeId(obj[0].toString());
                 if (obj[1] != null && !obj[1].equals(""))
                     sspvo.setCarId(obj[1].toString());
                 if (obj[2] != null && !obj[2].equals(""))
                     sspvo.setCarLicense(obj[2].toString());
                 if (obj[3] != null && !obj[3].equals(""))
                     sspvo.setCbrdName(obj[3].toString());
                 if (obj[4] != null && !obj[4].equals(""))
                     sspvo.setCtypeName(obj[4].toString());
                 if (obj[5] != null && !obj[5].equals(""))
                     sspvo.setCarRelationPerson(obj[5].toString());
                 if (obj[6] != null && !obj[6].equals(""))
                     sspvo.setPaymentName(obj[6].toString());
                 if (obj[7] != null && !obj[7].equals(""))
                     sspvo.setStfName(obj[7].toString());
                 if (obj[8] != null && !obj[8].equals(""))
                     sspvo.setPerchargeDate(Utils.dateFormat(obj[8].toString(), true));
                 if (obj[9] != null && !obj[9].equals(""))
                     sspvo.setCurPercharge(Double.parseDouble(obj[9].toString()));
                 if (obj[10] != null && !obj[10].equals(""))
                     sspvo.setChargeRemark(obj[10].toString());
                 if (obj[11] != null && !obj[11].equals(""))
                     sspvo.setCustomName(obj[11].toString());
                 if (obj[12] != null && !obj[12].equals(""))
                     sspvo.setPaymentId(obj[12].toString());
                 if (obj[13] != null && !obj[13].equals(""))
                     sspvo.setPaymentName(obj[13].toString());
                 if (obj[14] != null && !obj[14].equals(""))
                     sspvo.setCarRelationPerson(obj[14].toString());
                 if (obj[15] != null && !obj[15].equals(""))
                     sspvo.setCarRelationTel1(obj[15].toString());
                 if (obj[16] != null && !obj[16].equals(""))
                     sspvo.setCarRelationTel2(obj[16].toString());
                 if (obj[17] != null && !obj[17].equals(""))
                     sspvo.setPreclrInoiceType(obj[17].toString());
                 if (obj[18] != null && !obj[18].equals(""))
                     sspvo.setPreclrNo(obj[18].toString());
                 if (obj[19] != null && !obj[19].equals(""))
                     sspvo.setVin(obj[19].toString());
                 list.add(sspvo);
             }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修储备金信息    综合查询
     */
    public Json searchPreStSellPerchargeByCondition(
            String perchargeDateBegin, String perchargeDateEnd,
            String carLicense, String vin) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer(""+
		" SELECT PERCHARGE_ID,CAR_ID,CAR_LICENSE,CBRD_NAME,CTYPE_NAME,CAR_RELATION_PERSON as temp1,dataValue ,"+
		" STF_NAME,PERCHARGE_DATE,CUR_PERCHARGE,CHARGE_REMARK,CUSTOM_NAME,child_id,"+
		" dataValue as temp2,CAR_RELATION_PERSON,CAR_RELATION_TEL1,CAR_RELATION_TEL2,"+
		" PRECLR_INOICE_TYPE,PRECLR_NO,CAR_VIN  FROM ("+
		" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
		" SELECT e.*,bas_stuff.STF_NAME FROM ("+
		" SELECT d.*,bc.dataValue FROM ("+
		" SELECT c.*,bas_car_brand.CBRD_NAME FROM ("+
		" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+
		" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CTYPE_ID"+
		" ,frt_car.CAR_RELATION_TEL1,frt_car.CAR_RELATION_TEL2,frt_car.CAR_VIN"+
		" ,frt_car.CUSTOM_ID FROM ("+
		" SELECT st_sell_percharge.*,st_recharge.CAR_ID,st_recharge.PERCHARGE_TYPE"+
		" FROM st_sell_percharge LEFT JOIN  st_recharge"+
		" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
		" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
		" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
		" LEFT JOIN bas_car_brand ON c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
		" LEFT JOIN bas_childdictionary AS bc ON d.CHILD_ID= bc.child_id) AS e"+
		" LEFT JOIN bas_stuff ON e.STF_ID=bas_stuff.STF_ID) AS f"+
		" LEFT JOIN frt_custom ON f.CUSTOM_ID =frt_custom.CUSTOM_ID"+
		" ) AS g WHERE FLAG=0 AND PERCHARGE_TYPE='预收款'");
        if (perchargeDateBegin != null && !"".equals(perchargeDateBegin))
            if (perchargeDateEnd != null && !"".equals(perchargeDateEnd))
                sb.append(" AND PERCHARGE_DATE BETWEEN '"
                        + perchargeDateBegin.trim() + "' AND '"
                        + perchargeDateEnd.trim() + "'");
            else
            	sb.append(" AND PERCHARGE_DATE>='"
                        + perchargeDateBegin.trim() + "'");
        else
            if (perchargeDateEnd != null && !"".equals(perchargeDateEnd))
            	sb.append(" AND PERCHARGE_DATE<='"
                        + perchargeDateEnd.trim() + "'");
        if (carLicense != null && !"".equals(carLicense))
        	sb.append(" AND CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%'");
        if (vin != null && !"".equals(vin))
        	sb.append(" AND CAR_VIN like '%" + StringEscapeUtils.escapeSql(vin.trim()) + "%'");
        sb.append(" GROUP BY PERCHARGE_ID");
        int count=getSQLCount(sb.toString(), null);
        List<Object[]> resultList =createSQLQuery(sb.toString());
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setPerchargeId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarId(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCarLicense(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCbrdName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCtypeName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCarRelationPerson(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setPaymentName(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setStfName(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    sspvo.setPerchargeDate(Utils.dateFormat(obj[8].toString(), true));
                if (obj[9] != null && !obj[9].equals(""))
                    sspvo.setCurPercharge(Double.parseDouble(obj[9].toString()));
                if (obj[10] != null && !obj[10].equals(""))
                    sspvo.setChargeRemark(obj[10].toString());
                if (obj[11] != null && !obj[11].equals(""))
                    sspvo.setCustomName(obj[11].toString());
                if (obj[12] != null && !obj[12].equals(""))
                    sspvo.setPaymentId(obj[12].toString());
                if (obj[13] != null && !obj[13].equals(""))
                    sspvo.setPaymentName(obj[13].toString());
                if (obj[14] != null && !obj[14].equals(""))
                    sspvo.setCarRelationPerson(obj[14].toString());
                if (obj[15] != null && !obj[15].equals(""))
                    sspvo.setCarRelationTel1(obj[15].toString());
                if (obj[16] != null && !obj[16].equals(""))
                    sspvo.setCarRelationTel2(obj[16].toString());
                if (obj[17] != null && !obj[17].equals(""))
                    sspvo.setPreclrInoiceType(obj[17].toString());
                if (obj[18] != null && !obj[18].equals(""))
                    sspvo.setPreclrNo(obj[18].toString());
                if (obj[19] != null && !obj[19].equals(""))
                    sspvo.setVin(obj[19].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 根据储备金汇总编号获取储备金明细信息
     */
    public Json findStSellPerchargeById(String rechargeId)
            throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb =new StringBuffer( "SELECT PERCHARGE_TYPE,PERCHARGE_ID,PERCHARGE_DATE,"+
		" CUR_PERCHARGE,PRECLR_NO,dataValue FROM ("+
		" SELECT a.*,bas_childdictionary.dataValue FROM ("+
		" SELECT st_sell_percharge.*,st_recharge.PERCHARGE_TYPE"+
		" FROM st_sell_percharge LEFT JOIN st_recharge "+
		" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID)AS a"+
		" LEFT JOIN bas_childdictionary ON a.CHILD_ID=bas_childdictionary.child_id) AS b"+
		" WHERE PERCHARGE_TYPE='储备金' AND FLAG=0");
        if(rechargeId!=null&&!rechargeId.equals(""))
            sb.append(" and recharge_ID='" + rechargeId + "'");
        List<Object[]> resultList=createSQLQuery(sb.toString());
        int count =getSQLCount(sb.toString(), null);
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
        	for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setPerchargeType(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setPerchargeId(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setPerchargeDate(Utils.dateFormat(obj[2].toString(), true));
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCurPercharge(Double.parseDouble(obj[3].toString()));
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setPreclrNo(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setPaymentName(obj[5].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 根据预收款汇总编号获取预收款明细信息
     */
    public Json findPreStSellPerchargeById(String rechargeId)
            throws Exception{
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb =new StringBuffer( "SELECT PERCHARGE_TYPE,PERCHARGE_ID,PERCHARGE_DATE,"+
        		" CUR_PERCHARGE,PRECLR_NO,dataValue FROM ("+
        		" SELECT a.*,bas_childdictionary.dataValue FROM ("+
        		" SELECT st_sell_percharge.*,st_recharge.PERCHARGE_TYPE"+
        		" FROM st_sell_percharge LEFT JOIN st_recharge "+
        		" ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID)AS a"+
        		" LEFT JOIN bas_childdictionary ON a.CHILD_ID=bas_childdictionary.child_id) AS b"+
        		" WHERE PERCHARGE_TYPE='预收款' AND FLAG=0");
        if(rechargeId!=null&&!rechargeId.equals(""))
                sb.append(" AND recharge_ID='" + rechargeId + "'");
        List<Object[]> resultList=createSQLQuery(sb.toString());
        int count=getSQLCount(sb.toString(), null);
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
        	for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setPerchargeType(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setPerchargeId(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setPerchargeDate(Utils.dateFormat(obj[2].toString(), true));
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCurPercharge(Double.parseDouble(obj[3].toString()));
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setPreclrNo(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setPaymentName(obj[5].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    
    
    /**
     * 数据加载
     */
    @SuppressWarnings("unchecked")
    public List<ComboBoxJson> loadData(final String key) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        String sql = "SELECT bas_childdictionary.child_id,bas_childdictionary.dataValue"
                + " FROM bas_childdictionary,bas_parentdictionary"
                + " WHERE bas_childdictionary.parent_id=bas_parentdictionary.parent_id";
        if (key != null && !key.equals(""))
            sql += "  AND bas_parentdictionary.dataKey='" + key + "'";
        List resultList = this.getSessionFactory().getCurrentSession()
                .createSQLQuery(sql).list();
        Object[] obj = null;
        for (int i = 0; i < resultList.size(); i++)
        {
            ComboBoxJson cbj = new ComboBoxJson();
            obj = (Object[]) resultList.get(i);
            if (obj[0] != null && !obj[0].equals(""))
            {
                cbj.setId(obj[0].toString());
            }
            if (obj[1] != null && !obj[1].equals(""))
            {
                cbj.setText(obj[1].toString());
            }
            list.add(cbj);
        }
        return list;
    }

    
    /**
     * 储备金使用记录信息 预加载
     */
    public Json loadStUsePercharge(int page, int rows,String start,String end,String rechargeId,int enterpriseId)
            throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb=new StringBuffer(" SELECT  INTER_DATE,t5.RECEPTION_ID, t4.PRECLR_ID,t6.CAR_LICENSE,t7.CUSTOM_NAME,t2.FCS_DATE,t1.back_amount,t1.use_amount" +
        		",t2.payment_id,(SELECT c.dataValue FROM bas_childdictionary c WHERE c.dataKey=t2.payment_id) FROM st_use_percharge t1 LEFT JOIN fin_collection_schedule t2"+
		  "  ON t1.REPCOLL_ID=t2.FCS_ID LEFT JOIN  fin_maintenance_receivables t3  ON t3.MR_ID=t2.MR_ID  LEFT JOIN frt_pre_clearing t4 ON t4.PRECLR_ID=t3.PRECLR_ID"+
		  " LEFT JOIN frt_reception t5 ON t5.RECEPTION_ID=t4.RECEPTION_ID LEFT JOIN frt_car t6 ON t6.CAR_ID=t5.CAR_ID LEFT JOIN  frt_custom t7 ON t7.CUSTOM_ID=t6.CUSTOM_ID"+
		  " LEFT JOIN st_recharge t8 ON t8.recharge_ID=t1.recharge_ID WHERE t8.PERCHARGE_TYPE='储备金'");
        if(rechargeId!=null && !("".equals(rechargeId))){
        	sb.append(" and  t8.recharge_id=' "+rechargeId+"'");
        }
        if(start == null  && end == null){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANCETIMESECT,enterpriseId).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	
        	sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
        	resultList=createSQLQuery(sb.toString(), page, rows);
        else
        	resultList=createSQLQuery(sb.toString());
       int count =getSQLCount(sb.toString(), null);
       if(resultList!=null&&resultList.size()>0){
    	   Object[] obj = null;
           for (int i = 0; i < resultList.size(); i++){
               StSellPerchargeVo sspvo = new StSellPerchargeVo();
               obj = (Object[]) resultList.get(i);
               if (obj[0] != null && !obj[0].equals(""))
                   sspvo.setResvRealTime(Utils.dateFormat(obj[0].toString(),true));
               if (obj[1] != null && !obj[1].equals(""))
                   sspvo.setReceptionId(obj[1].toString());
               if (obj[2] != null && !obj[2].equals(""))
                   sspvo.setRepcollId(obj[2].toString());
               if (obj[3] != null && !obj[3].equals(""))
                   sspvo.setCarLicense(obj[3].toString());
               if (obj[4] != null && !obj[4].equals(""))
                   sspvo.setCustomName(obj[4].toString());
               if (obj[5] != null && !obj[5].equals(""))
                   sspvo.setRepcollTime(Utils.dateFormat(obj[5].toString(), true));
               if (obj[6] != null && !obj[6].equals(""))
                   sspvo.setRepcollAmount(Double.parseDouble(obj[6].toString()));
               if (obj[7] != null && !obj[7].equals(""))
                   sspvo.setDiscountAmont(Double.parseDouble(obj[7].toString()));
               if (obj[8] != null && !obj[8].equals(""))
                   sspvo.setPaymentId(obj[8].toString());
               if (obj[9] != null && !obj[9].equals(""))
                   sspvo.setPaymentName(obj[9].toString());
               list.add(sspvo);
           }
       }
       Json json=new Json();
       json.setRows(list);
       json.setTotal(count);
       return json;
    }

    /**
     * 预收款使用记录使用记录信息      预加载
     */
    public Json loadPreStUsePercharge(int page, int rows,String start,String end,String rechargeId,int enterpriseId)
            throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb=new StringBuffer(" SELECT  INTER_DATE,t5.RECEPTION_ID, t4.PRECLR_ID,t6.CAR_LICENSE,t7.CUSTOM_NAME,t2.FCS_DATE,t1.back_amount,t1.use_amount" +
        		" ,t2.payment_id,(SELECT c.dataValue FROM bas_childdictionary c WHERE c.dataKey=t2.payment_id)FROM st_use_percharge t1 LEFT JOIN fin_collection_schedule t2"+
      		  "  ON t1.REPCOLL_ID=t2.FCS_ID LEFT JOIN  fin_maintenance_receivables t3  ON t3.MR_ID=t2.MR_ID  LEFT JOIN frt_pre_clearing t4 ON t4.PRECLR_ID=t3.PRECLR_ID"+
      		  " LEFT JOIN frt_reception t5 ON t5.RECEPTION_ID=t4.RECEPTION_ID LEFT JOIN frt_car t6 ON t6.CAR_ID=t5.CAR_ID LEFT JOIN  frt_custom t7 ON t7.CUSTOM_ID=t6.CUSTOM_ID"+
      		  " LEFT JOIN st_recharge t8 ON t8.recharge_ID=t1.recharge_ID WHERE t8.PERCHARGE_TYPE='预收款'   and  t1.enterprise_id="+enterpriseId );
        if(rechargeId!=null && !("".equals(rechargeId))){
        	sb.append(" and  t8.recharge_id=' "+rechargeId+"'");
        }
        if(start == null &&  end == null){
        	String param= basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANCETIMESECT,enterpriseId).getCiValue();
        	if(param!=null && !("".equals(param))){
        		sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(param))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	sb.append(" and DATE_FORMAT(t2.FCS_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
		List<Object[]> resultList=new ArrayList<Object[]>();
		if(page!=0&&rows!=0)
        	resultList=createSQLQuery(sb.toString(), page, rows);
        else
        	resultList=createSQLQuery(sb.toString());
       int count =getSQLCount(sb.toString(), null);
       if(resultList!=null&&resultList.size()>0){
    	    Object[] obj = null;
	        for (int i = 0; i < resultList.size(); i++){
	            StSellPerchargeVo sspvo = new StSellPerchargeVo();
	            obj = (Object[]) resultList.get(i);
	            if (obj[0] != null && !obj[0].equals(""))
	                sspvo.setResvRealTime(Utils.dateFormat(obj[0].toString(),true));
	            if (obj[1] != null && !obj[1].equals(""))
	                sspvo.setReceptionId(obj[1].toString());
	            if (obj[2] != null && !obj[2].equals(""))
	                sspvo.setRepcollId(obj[2].toString());
	            if (obj[3] != null && !obj[3].equals(""))
	                sspvo.setCarLicense(obj[3].toString());
	            if (obj[4] != null && !obj[4].equals(""))
	                sspvo.setCustomName(obj[4].toString());
	            if (obj[5] != null && !obj[5].equals(""))
	                sspvo.setRepcollTime(Utils.dateFormat(obj[5].toString(), true));
	            if (obj[6] != null && !obj[6].equals(""))
	                sspvo.setRepcollAmount(Double.parseDouble(obj[6].toString()));
	            if (obj[7] != null && !obj[7].equals(""))
	                sspvo.setDiscountAmont(Double.parseDouble(obj[7].toString()));
	            if (obj[8] != null && !obj[8].equals(""))
	                   sspvo.setPaymentId(obj[8].toString());
               if (obj[9] != null && !obj[9].equals(""))
                   sspvo.setPaymentName(obj[9].toString());
	            list.add(sspvo);
	        }
       }
       Json json=new Json();
       json.setRows(list);
       json.setTotal(count);
       return json;
    }

    /**
     * 储备金使用记录信息      综合查询
     */
    public Json serachStUsePerchargeByCondition(
            String repcollTimeStart, String repcollTimeEnd, String carLicense,
            String customName) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb=new StringBuffer( "SELECT RESV_REAL_TIME,RECEPTION_ID,REPCOLL_ID,CAR_LICENSE,"+
		" CUSTOM_NAME,REPCOLL_TIME,REPCOLL_AMOUNT,DISCOUNT_AMONT FROM ("+
		" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
		" SELECT e.*,frt_car.CUSTOM_ID,frt_car.CAR_LICENSE FROM ("+
		" SELECT d.*,st_recharge.PERCHARGE_TYPE,st_recharge.CAR_ID FROM ("+
		" SELECT c.*,frt_resevation.RESV_REAL_TIME FROM ("+
		" SELECT b.*,frt_reception.RESV_ID FROM ("+
		" SELECT a.*,frt_pre_clearing.RECEPTION_ID FROM ("+
		" SELECT st_use_percharge.*,fin_rep_collection.REPCOLL_TIME,fin_rep_collection.PRECLR_ID,"+
		" fin_rep_collection.REPCOLL_AMOUNT,fin_rep_collection.DISCOUNT_AMONT"+
		" FROM st_use_percharge LEFT JOIN fin_rep_collection "+
		" ON st_use_percharge.REPCOLL_ID=fin_rep_collection.REPCOLL_ID) AS a"+
		" LEFT JOIN frt_pre_clearing ON a.PRECLR_ID=frt_pre_clearing.PRECLR_ID) AS b"+
		" LEFT JOIN frt_reception ON b.RECEPTION_ID=frt_reception.RECEPTION_ID) AS c"+
		" LEFT JOIN frt_resevation ON c.RESV_ID=frt_resevation.RESV_ID) AS d"+
		" LEFT JOIN st_recharge ON d.recharge_ID=st_recharge.recharge_ID) AS e"+
		" LEFT JOIN frt_car ON e.CAR_ID=frt_car.CAR_ID) AS f"+
		" LEFT JOIN frt_custom ON f.CUSTOM_ID=frt_custom.CUSTOM_ID) AS g"+
		" WHERE PERCHARGE_TYPE='储备金'");
        if (repcollTimeStart != null && !repcollTimeStart.equals(""))
            if (repcollTimeEnd != null && !repcollTimeEnd.equals(""))
                sb.append(" and REPCOLL_TIME BETWEEN '"+ repcollTimeStart.trim() + "' AND '"+ repcollTimeEnd.trim() + "'");
            else
                sb.append(" and REPCOLL_TIME>='"+ repcollTimeStart.trim() + "'");
        else
            if (repcollTimeEnd != null && !repcollTimeEnd.equals(""))
                sb.append(" and REPCOLL_TIME<='"+ repcollTimeEnd.trim() + "'");
        if (carLicense != null && !"".equals(carLicense))
            sb.append(" AND CAR_LICENSE='" + carLicense + "'");
        if (customName != null && !"".equals(customName))
            sb.append(" AND CUSTOM_NAME='" + customName + "'");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList=createSQLQuery(sb.toString());
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setResvRealTime(Utils.dateFormat(obj[0].toString(),
                                    true));
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setReceptionId(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setRepcollId(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCarLicense(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCustomName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setRepcollTime(Utils.dateFormat(obj[5].toString(), true));
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setRepcollAmount(Double.parseDouble(obj[6].toString()));
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setDiscountAmont(Double.parseDouble(obj[7].toString()));
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 预收款使用记录信息 综合查询
     */
    public Json serachPreStUsePerchargeByCondition(
            String repcollTimeStart, String repcollTimeEnd, String carLicense,
            String customName) throws Exception{
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb=new StringBuffer( "SELECT RESV_REAL_TIME,RECEPTION_ID,REPCOLL_ID,CAR_LICENSE,"+
        		" CUSTOM_NAME,REPCOLL_TIME,REPCOLL_AMOUNT,DISCOUNT_AMONT FROM ("+
        		" SELECT f.*,frt_custom.CUSTOM_NAME FROM ("+
        		" SELECT e.*,frt_car.CUSTOM_ID,frt_car.CAR_LICENSE FROM ("+
        		" SELECT d.*,st_recharge.PERCHARGE_TYPE,st_recharge.CAR_ID FROM ("+
        		" SELECT c.*,frt_resevation.RESV_REAL_TIME FROM ("+
        		" SELECT b.*,frt_reception.RESV_ID FROM ("+
        		" SELECT a.*,frt_pre_clearing.RECEPTION_ID FROM ("+
        		" SELECT st_use_percharge.*,fin_rep_collection.REPCOLL_TIME,fin_rep_collection.PRECLR_ID,"+
        		" fin_rep_collection.REPCOLL_AMOUNT,fin_rep_collection.DISCOUNT_AMONT"+
        		" FROM st_use_percharge LEFT JOIN fin_rep_collection "+
        		" ON st_use_percharge.REPCOLL_ID=fin_rep_collection.REPCOLL_ID) AS a"+
        		" LEFT JOIN frt_pre_clearing ON a.PRECLR_ID=frt_pre_clearing.PRECLR_ID) AS b"+
        		" LEFT JOIN frt_reception ON b.RECEPTION_ID=frt_reception.RECEPTION_ID) AS c"+
        		" LEFT JOIN frt_resevation ON c.RESV_ID=frt_resevation.RESV_ID) AS d"+
        		" LEFT JOIN st_recharge ON d.recharge_ID=st_recharge.recharge_ID) AS e"+
        		" LEFT JOIN frt_car ON e.CAR_ID=frt_car.CAR_ID) AS f"+
        		" LEFT JOIN frt_custom ON f.CUSTOM_ID=frt_custom.CUSTOM_ID) AS g"+
        		" WHERE PERCHARGE_TYPE='预收款'");
        if (repcollTimeStart != null && !repcollTimeStart.equals(""))
            if (repcollTimeEnd != null && !repcollTimeEnd.equals(""))
                sb.append(" and REPCOLL_TIME BETWEEN '"
                        + repcollTimeStart.trim() + "' AND '"
                        + repcollTimeEnd.trim() + "'");
            else
                sb.append(" and REPCOLL_TIME>='"
                        + repcollTimeStart.trim() + "'");
        else
            if (repcollTimeEnd != null && !repcollTimeEnd.equals(""))
                sb.append(" and REPCOLL_TIME<='"
                        + repcollTimeEnd.trim() + "'");
        if (carLicense != null && !"".equals(carLicense))
            sb.append(" AND CAR_LICENSE='" + carLicense + "'");
        if (customName != null && !"".equals(customName))
            sb.append(" AND CUSTOM_NAME='" + customName + "'");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList=createSQLQuery(sb.toString());
        if(resultList!=null&&resultList.size()>0){
        	Object[] obj = null;
	        for (int i = 0; i < resultList.size(); i++){
	            StSellPerchargeVo sspvo = new StSellPerchargeVo();
	            obj = (Object[]) resultList.get(i);
	            if (obj[0] != null && !obj[0].equals(""))
	                sspvo.setResvRealTime(Utils.dateFormat(obj[0].toString(),true));
	            if (obj[1] != null && !obj[1].equals(""))
	                sspvo.setReceptionId(obj[1].toString());
	            if (obj[2] != null && !obj[2].equals(""))
	                sspvo.setRepcollId(obj[2].toString());
	            if (obj[3] != null && !obj[3].equals(""))
	                sspvo.setCarLicense(obj[3].toString());
	            if (obj[4] != null && !obj[4].equals(""))
	                sspvo.setCustomName(obj[4].toString());
	            if (obj[5] != null && !obj[5].equals(""))
	                sspvo.setRepcollTime(Utils.dateFormat(obj[5].toString(), true));
	            if (obj[6] != null && !obj[6].equals(""))
	                sspvo.setRepcollAmount(Double.parseDouble(obj[6].toString()));
	            if (obj[7] != null && !obj[7].equals(""))
	                sspvo.setDiscountAmont(Double.parseDouble(obj[7].toString()));
	            list.add(sspvo);
	        }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 判断车辆编号是否已存在于维修收款表中
     * 
     * @throws Exception
     */
    public boolean isExist(String carId) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("s.frtCar.carId", carId);
        int count = getCount(" from StRecharge s where s.frtCar.carId='"
                + carId + "'");
        if (count > 0)
            return true;
        return false;
    }

   
}
