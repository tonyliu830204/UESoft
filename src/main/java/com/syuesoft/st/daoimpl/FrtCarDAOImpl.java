package com.syuesoft.st.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.FrtCar;
import com.syuesoft.st.dao.FrtCarDAO;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;
/**
 * 车辆档案DAO实现类
 * @author SuMing
 */
public class FrtCarDAOImpl extends BaseDaoImpl<FrtCar> implements FrtCarDAO {

	/**
	 * 销售单模块  车辆牌照信息      预加载
	 */
	public Json loadCarLicense(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception
	{
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		String queryString="SELECT DISTINCT frt_car.CAR_LICENSE,frt_car.CAR_VIN,frt_car.CAR_MOTOR_ID,frt_car.CAR_RELATION_TEL1,"+
							" frt_custom.CUSTOM_ID,frt_custom.CUSTOM_NAME FROM frt_car ,frt_custom"+
							" WHERE frt_car.CUSTOM_ID=frt_custom.CUSTOM_ID and frt_car.enterprise_id="+enterprise_id;
		List<Object[]> resultList=createSQLQuery(queryString, page, rows);
		int count=getSQLCount(queryString, null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo sovo=new StSellOrderVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					sovo.setCarLicense(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sovo.setCarVin(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					sovo.setCarMotorId(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sovo.setCarRelationTel1(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sovo.setCustomId(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sovo.setCustomName(obj[5].toString());
				list.add(sovo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
	/**
	 * 销售单模块  车辆牌照信息     综合 查询
	 */
	public Json searchCarLicenseByCondition(final String carLicense,final String carVin,final String carMotorId,final int enterprise_id)throws Exception
	{
		List<StSellOrderVo> list=new ArrayList<StSellOrderVo>();
		StringBuffer sb=new StringBuffer("SELECT DISTINCT frt_car.CAR_LICENSE,frt_car.CAR_VIN,"+
				" frt_car.CAR_MOTOR_ID,frt_car.CAR_RELATION_TEL1,frt_custom.CUSTOM_ID,frt_custom.CUSTOM_NAME"+
				" FROM frt_car ,frt_custom WHERE frt_custom.CUSTOM_FLAG="+Contstants.ONOROFF.ONOROFFYES+"  and frt_car.CUSTOM_ID=frt_custom.CUSTOM_ID and frt_car.enterprise_id="+enterprise_id+"");
		if(carLicense!=null&&!carLicense.trim().equals(""))
			sb.append(" AND frt_car.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(carLicense.trim())+"%'");
		if(carVin!=null&&!carVin.trim().equals(""))
			sb.append(" AND frt_car.CAR_VIN like '%"+StringEscapeUtils.escapeSql(carVin.trim())+"%'");
		if(carMotorId!=null&&!carMotorId.trim().equals(""))
			sb.append(" AND frt_car.CAR_MOTOR_ID like '%"+StringEscapeUtils.escapeSql(carMotorId.trim())+"%'");
		List<Object[]> resultList=createSQLQuery(sb.toString());
		int count=getSQLCount(sb.toString(), null);
		if(resultList!=null&&resultList.size()>0){
			Object[] obj=null;
			for (int i = 0; i < resultList.size(); i++) {
				StSellOrderVo sovo=new StSellOrderVo();
				obj=(Object[]) resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					sovo.setCarLicense(obj[0].toString());
				if(obj[1]!=null&&!obj[1].equals(""))
					sovo.setCarVin(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					sovo.setCarMotorId(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					sovo.setCarRelationTel1(obj[3].toString());
				if(obj[4]!=null&&!obj[4].equals(""))
					sovo.setCustomId(obj[4].toString());
				if(obj[5]!=null&&!obj[5].equals(""))
					sovo.setCustomName(obj[5].toString());
				list.add(sovo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}
	
}