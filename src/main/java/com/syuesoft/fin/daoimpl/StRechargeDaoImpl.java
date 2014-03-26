package com.syuesoft.fin.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.dao.StRechargeDao;
import com.syuesoft.fin.vo.StSellPerchargeVo;
import com.syuesoft.model.StRecharge;
import com.syuesoft.util.Json;

/**
 * 维修预收款余额DaoImpl
 * 
 * @author SuMing
 */
@Repository("stRechargeDao")
public class StRechargeDaoImpl extends BaseDaoImpl<StRecharge> implements
        StRechargeDao
{

    /**
     * 维修储备金余额信息 预加载
     */
    public Json loadStRecharge() throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        String sql = "SELECT recharge_ID,CAR_LICENSE,CAR_ID,CBRD_NAME,CTYPE_NAME,CUSTOM_NAME,CAR_RELATION_PERSON,"+
        " CAR_RELATION_TEL1,CAR_RELATION_TEL2,SUM(CUR_PERCHARGE),surplus_money FROM ("+
        " SELECT d.*,frt_custom.CUSTOM_NAME FROM ("+
        " SELECT c.*,bas_car_brand.CBRD_NAME FROM  ("+
        " SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+   
        " SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CAR_RELATION_TEL1,"+
        " frt_car.CAR_RELATION_TEL2,frt_car.CTYPE_ID,frt_car.CUSTOM_ID FROM ("+
        " SELECT st_recharge.PERCHARGE_TYPE,st_sell_percharge.*,"+
        " st_recharge.surplus_money,st_recharge.CAR_ID FROM st_sell_percharge "+
        " LEFT JOIN st_recharge ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
        " LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
        " LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
        " LEFT JOIN bas_car_brand ON  c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
        " LEFT JOIN frt_custom ON d.CUSTOM_ID=frt_custom.CUSTOM_ID) AS e"+
        " WHERE PERCHARGE_TYPE='储备金' AND FLAG=0 GROUP BY CAR_ID";
        List<Object[]> resultList=createSQLQuery(sql);
        int count =getSQLCount(sql, null);
        if (resultList != null && !resultList.equals("") && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setRechargeId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCarId(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCbrdName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCtypeName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCustomName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationPerson(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel1(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    sspvo.setCarRelationTel2(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    sspvo.setAmontMoney(Double.parseDouble(obj[9].toString()));
                if (obj[10] != null && !obj[10].equals(""))
                    sspvo.setSurplusMoney(obj[10].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }
    
    /**
     * 维修储备金余额信息 综合查询
     */
    public Json searchStRechargeByCondition(String carLicense, String customName) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer("SELECT recharge_ID,CAR_LICENSE,CAR_ID,CBRD_NAME,CTYPE_NAME,CUSTOM_NAME,CAR_RELATION_PERSON,"+
        " CAR_RELATION_TEL1,CAR_RELATION_TEL2,SUM(CUR_PERCHARGE),surplus_money FROM ("+
        " SELECT d.*,frt_custom.CUSTOM_NAME FROM ("+
        " SELECT c.*,bas_car_brand.CBRD_NAME FROM  ("+
        " SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM ("+   
        " SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CAR_RELATION_TEL1,"+
        " frt_car.CAR_RELATION_TEL2,frt_car.CTYPE_ID,frt_car.CUSTOM_ID FROM ("+
        " SELECT st_recharge.PERCHARGE_TYPE,st_sell_percharge.*,"+
        " st_recharge.surplus_money,st_recharge.CAR_ID FROM st_sell_percharge "+
        " LEFT JOIN st_recharge ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
        " LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
        " LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
        " LEFT JOIN bas_car_brand ON  c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
        " LEFT JOIN frt_custom ON d.CUSTOM_ID=frt_custom.CUSTOM_ID) AS e"+
        " WHERE PERCHARGE_TYPE='储备金' AND FLAG=0 ");
        if (carLicense != null && !"".equals(carLicense))
            sb.append(" AND CAR_LICENSE LIKE '%" + StringEscapeUtils.escapeSql(carLicense.trim()) + "%'");
        if (customName != null && !"".equals(customName))
            sb.append(" AND CUSTOM_NAME LIKE '%" + StringEscapeUtils.escapeSql(customName.trim())+ "%'");
        sb.append(" GROUP BY CAR_ID");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList=createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setRechargeId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCarId(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCbrdName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCtypeName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCustomName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationPerson(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel1(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    sspvo.setCarRelationTel2(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    sspvo.setAmontMoney(Double.parseDouble(obj[9].toString()));
                if (obj[10] != null && !obj[10].equals(""))
                    sspvo.setSurplusMoney(obj[10].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修预收款余额信息 预加载
     */
    public Json loadPreStRecharge(StSellPerchargeVo spvo) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        String sql = "SELECT recharge_ID,CAR_LICENSE,CAR_ID,CBRD_NAME,CTYPE_NAME,CUSTOM_NAME,CAR_RELATION_PERSON,"+
		" CAR_RELATION_TEL1,CAR_RELATION_TEL2,SUM(CUR_PERCHARGE),surplus_money FROM ( "+
		" SELECT d.*,frt_custom.CUSTOM_NAME FROM ( "+
		" SELECT c.*,bas_car_brand.CBRD_NAME FROM  ("+
		" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM (    "+    
		" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CAR_RELATION_TEL1,"+
		" frt_car.CAR_RELATION_TEL2,frt_car.CTYPE_ID,frt_car.CUSTOM_ID FROM (        "+
		" SELECT st_recharge.PERCHARGE_TYPE,st_sell_percharge.*,"+
		" st_recharge.surplus_money,st_recharge.CAR_ID FROM st_sell_percharge "+
		" LEFT JOIN st_recharge ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID WHERE  st_sell_percharge.enterprise_id="+spvo.getEnterpriseId()+") AS a"+
		" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
		" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
		" LEFT JOIN bas_car_brand ON  c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
		" LEFT JOIN frt_custom ON d.CUSTOM_ID=frt_custom.CUSTOM_ID) AS e"+
		" WHERE PERCHARGE_TYPE='预收款' AND FLAG=0 GROUP BY CAR_ID";
        int count =getSQLCount(sql, null);
        List<Object[]> resultList=createSQLQuery(sql);
        if (resultList != null && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setRechargeId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCarId(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCbrdName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCtypeName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCustomName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationPerson(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel1(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    sspvo.setCarRelationTel2(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    sspvo.setAmontMoney(Double.parseDouble(obj[9].toString()));
                if (obj[10] != null && !obj[10].equals(""))
                    sspvo.setSurplusMoney(obj[10].toString());
                list.add(sspvo);
            }
        }
        Json json=new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 维修预收款余额信息 综合查询
     */
    public Json searchStPreRechargeByCondition(
            String carLicense, String customName) throws Exception
    {
        List<StSellPerchargeVo> list = new ArrayList<StSellPerchargeVo>();
        StringBuffer sb = new StringBuffer("SELECT recharge_ID,CAR_LICENSE,CAR_ID,CBRD_NAME,CTYPE_NAME,CUSTOM_NAME,CAR_RELATION_PERSON,"+
		" CAR_RELATION_TEL1,CAR_RELATION_TEL2,SUM(CUR_PERCHARGE),surplus_money FROM ( "+
		" SELECT d.*,frt_custom.CUSTOM_NAME FROM ( "+
		" SELECT c.*,bas_car_brand.CBRD_NAME FROM  ("+
		" SELECT b.*,bas_car_type.CTYPE_NAME,bas_car_type.CBRD_ID FROM (    "+    
		" SELECT a.*,frt_car.CAR_LICENSE,frt_car.CAR_RELATION_PERSON,frt_car.CAR_RELATION_TEL1,"+
		" frt_car.CAR_RELATION_TEL2,frt_car.CTYPE_ID,frt_car.CUSTOM_ID FROM (        "+
		" SELECT st_recharge.PERCHARGE_TYPE,st_sell_percharge.*,"+
		" st_recharge.surplus_money,st_recharge.CAR_ID FROM st_sell_percharge "+
		" LEFT JOIN st_recharge ON st_sell_percharge.recharge_ID=st_recharge.recharge_ID) AS a"+
		" LEFT JOIN frt_car ON a.CAR_ID=frt_car.CAR_ID) AS b"+
		" LEFT JOIN bas_car_type ON b.CTYPE_ID=bas_car_type.CTYPE_ID) AS c"+
		" LEFT JOIN bas_car_brand ON  c.CBRD_ID=bas_car_brand.CBRD_ID) AS d"+
		" LEFT JOIN frt_custom ON d.CUSTOM_ID=frt_custom.CUSTOM_ID) AS e"+
		" WHERE PERCHARGE_TYPE='预收款' AND FLAG=0 ");
        if (carLicense != null && !"".equals(carLicense))
            sb.append(" AND CAR_LICENSE LIKE '%" + StringEscapeUtils.escapeSql(carLicense.trim()) + "%'");
        if (customName != null && !"".equals(customName))
            sb.append(" AND CUSTOM_NAME LIKE '%" + StringEscapeUtils.escapeSql(customName.trim())+ "%'");
        sb.append(" GROUP BY CAR_ID");
        int count =getSQLCount(sb.toString(), null);
        List<Object[]> resultList=createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                StSellPerchargeVo sspvo = new StSellPerchargeVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    sspvo.setRechargeId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    sspvo.setCarLicense(obj[1].toString());
                if (obj[2] != null && !obj[2].equals(""))
                    sspvo.setCarId(obj[2].toString());
                if (obj[3] != null && !obj[3].equals(""))
                    sspvo.setCbrdName(obj[3].toString());
                if (obj[4] != null && !obj[4].equals(""))
                    sspvo.setCtypeName(obj[4].toString());
                if (obj[5] != null && !obj[5].equals(""))
                    sspvo.setCustomName(obj[5].toString());
                if (obj[6] != null && !obj[6].equals(""))
                    sspvo.setCarRelationPerson(obj[6].toString());
                if (obj[7] != null && !obj[7].equals(""))
                    sspvo.setCarRelationTel1(obj[7].toString());
                if (obj[8] != null && !obj[8].equals(""))
                    sspvo.setCarRelationTel2(obj[8].toString());
                if (obj[9] != null && !obj[9].equals(""))
                    sspvo.setAmontMoney(Double.parseDouble(obj[9].toString()));
                if (obj[10] != null && !obj[10].equals(""))
                    sspvo.setSurplusMoney(obj[10].toString());
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
     */
    public boolean isExist(String carId) throws Exception{
    	StringBuffer sb= new StringBuffer("SELECT * FROM st_recharge WHERE 1=1 ");
    	if(carId!=null&&!carId.trim().equals(""))
    	   sb.append(" and st_recharge.CAR_ID='"+carId.trim()+"'");
    	List<Object[]> resultList=createSQLQuery(sb.toString());
        if (resultList!=null&&resultList.size() > 0)
            return true;
        return false;
    }

    /**
     * 判断车辆编号是否已存在于维修收款表中
     */
    public boolean isPreExist(String carId) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("s.frtCar.carId", carId);
        int count = getCount(" from StRecharge s where s.perchargeType!='储备金' and s.frtCar.carId='"
                + carId + "'");
        if (count > 0)
            return true;
        return false;
    }

}
