package com.syuesoft.sell.carAllocate.daoimpl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.dao.SellCarReserveDao;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;
import com.syuesoft.util.FormatTime;

@Repository("sellCarReserveDao")
public class SellCarReserveDaoImpl extends BaseDaoImpl<Object>
		implements SellCarReserveDao {

	
	public void deleteSellCarReserve(SellCarReserveVo sellCarReserveVo)
			throws Exception {

	}


	/**
	 * 
	 * 预交提醒清单
	 */
	
	public Datagrid queryCarReserveExpire(
			SellCarReserveVo sellCarReserveVo) throws Exception {
		Datagrid dg = new Datagrid();
		List<SellCarReserveVo> list_ = new ArrayList<SellCarReserveVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT A.reserve_code,A.reserve_dete,A.vin_code,A.decorate_money,"
						+ "A.first_pay_money,A.remark,B.STF_NAME,C.xs_custom_name, A.predict_take_date, D.xs_model_name," +
						"A.car_brand ,A.car_color,A.car_model,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = A.level ) AS LEVEL ,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = A.car_color ) AS Cc, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = A.car_brand ) AS Cb,A.STF_ID, A.reserve_id   "
						+ "FROM  xs_sell_car_reserve A " +
						 " join xs_sell_flow_control flow on flow.xsfcontrol_document=A.reserve_code and" +
						" flow.xsfcontrol_code='"+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+"'" +
						" LEFT OUTER JOIN  bas_stuff B ON A.STF_ID = B.STF_ID" +
						" JOIN xs_custom_info C ON A.custom_id = C.custom_id " +
						" LEFT OUTER JOIN   xs_car_model D ON A.car_model = D.xs_model_id " +
						" where A.predict_take_date >=  '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'" +
						" AND A.examine=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'  and child.enterprise_id="+sellCarReserveVo.getEnterpriseId()+"" +
						")  ");
		
		//企业编号
		if (sellCarReserveVo.getEnterpriseId()!= null
				&& !"".equals(sellCarReserveVo.getEnterpriseId())) {
			sql.append(" and A.enterprise_id=" + sellCarReserveVo.getEnterpriseId()
					+ "");
		}
		List<Object[]> resultList = createSQLQuery(sql.toString(),sellCarReserveVo.getPage(),sellCarReserveVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellCarReserveVo wopq = new SellCarReserveVo();
				obj = resultList.get(i);
				wopq.setReserveCode(obj[0] != null ? obj[0].toString() : null);

				wopq.setReserveDete(obj[1] != null ?  obj[1].toString() : null);
				wopq.setVinCode(obj[2] != null ? obj[2].toString() : null);
				wopq.setDecorateMoney(obj[3] != null ? Double
						.parseDouble(obj[3].toString()) : null);
				wopq.setFirstPayMoney(obj[4] != null ? Double
						.parseDouble(obj[4].toString()) : null);
				wopq.setRemark(obj[5] != null ? obj[5].toString() : null);
				wopq.setStfName(obj[6] != null ? obj[6].toString() : null);
				wopq.setCustomName(obj[7] != null ? obj[7].toString() : null);
				wopq.setPredictTakeDate(obj[8] != null ? obj[8].toString() : null);
				wopq.setCarModelName(obj[9] != null ? obj[9].toString() : null);
				wopq.setCarBrand(obj[10] != null ? Integer.parseInt(obj[10].toString()) : null);
				wopq.setCarColor(obj[11] != null ? Integer.parseInt(obj[11].toString()) : null);
				wopq.setCarModel(obj[12] != null ? Integer.parseInt(obj[12].toString()) : null);
				wopq.setLevelName(obj[13] != null ? obj[13].toString() : null);
				wopq.setCarColorName(obj[14] != null ? obj[14].toString(): null);
				wopq.setCarBrandName(obj[15] != null ? obj[15].toString(): null);
				wopq.setStfId(obj[16] != null ? Integer.parseInt( obj[16].toString()): null);
				wopq.setReserveId(obj[17] != null ? Integer.parseInt( obj[17].toString()): null);
				list_.add(wopq);
			}
		}
		int total =this.getSQLCount(sql.toString(),null);
		dg.setRows(list_);
		dg.setTotal(total);
		return dg;
	}
}