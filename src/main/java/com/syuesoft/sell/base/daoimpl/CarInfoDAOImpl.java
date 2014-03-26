package com.syuesoft.sell.base.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.CarInfoDAO;
import com.syuesoft.sell.base.vo.CarInfoVo;
import com.syuesoft.util.FormatTime;

@Repository("carInfoDAO")
public class CarInfoDAOImpl extends BaseDaoImpl<BaseBean> implements CarInfoDAO{
	@Autowired BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	//车辆上报管理
	
	public Datagrid queryCarUpInfor(CarInfoVo carInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer("SELECT b.xs_car_sel_data,b.sell_code,j.retreat_date,j.retreat_code," +
				" a.xs_car_code,a.xs_car_vin_number,a.xs_car_brand, " +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.xs_car_brand) AS brand," +
				"a.xs_car_model_id,e.xs_model_name,a.xs_car_color," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.xs_car_color) AS color," +
				"a.upData,a.upPerson,g.STF_NAME,f.xs_custom_name,f.xs_custom_telephone,h.xs_supplier_id," +
				"i.xs_supplier_name,b.out_id" +
				" FROM xs_car_info a " +
				"JOIN xs_car_sell_info b ON a.xs_car_id=b.xs_car_id " +
				"JOIN xs_sell_instorehouse_del c ON  c.xs_car_id=a.xs_car_id " +
				"JOIN xs_sell_flow_control d ON d.xsfcontrol_document=a.xs_car_code" +
				" AND d.xsfcontrol_car_id=a.xs_car_id " +
				"JOIN xs_car_model e ON e.xs_model_id=a.xs_car_model_id " +
				"JOIN xs_custom_info f ON f.custom_id=b.custom_id " +
				"LEFT JOIN bas_stuff g ON g.STF_ID=a.upPerson " +
				" JOIN xs_sell_instorehouse h ON h.instorehouse_id=c.instorehouse_id " +
				"JOIN xs_supplier_info i ON i.xs_supplier_id=h.xs_supplier_id " +
				"JOIN xs_sell_retreat j ON j.details_id=c.details_id AND  j.retreat_id=b.out_id " +
				"WHERE a.xs_car_sell_state=(SELECT child.child_id FROM xs_childdictionary  child," +
				"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND " +
				"parent.dataKey ='"+Contstants.SELLSTATE.BASE_SELLSTATE+"' AND  " +
				"child.dataKey='"+Contstants.SELLSTATE.SELLOUT+"' and  " +
				"child.enterprise_id='"+carInfoVo.getEnterpriseId()+"') " +
						" and a.enterprise_id="+carInfoVo.getEnterpriseId());
		
		
		if (carInfoVo.getSellData() != null
				&& !"".equals(carInfoVo.getSellData())) {
			
					sql.append(" and DATE(b.xs_car_sel_data) >= '" +carInfoVo.getSellData() + "'");
				} 
		if (carInfoVo.getSellData2() != null
						&& !"".equals(carInfoVo.getSellData2())) {
					sql.append(" and DATE(b.xs_car_sel_data) <='" + carInfoVo.getSellData2() + "'");
				}
				
		if((carInfoVo.getSellData() == null
				||"".equals(carInfoVo.getSellData()))&&(carInfoVo.getSellData2() == null
						||"".equals(carInfoVo.getSellData2()))){
						
			sql.append(" and DATE(b.xs_car_sel_data) BETWEEN '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,carInfoVo.getEnterpriseId()).getCiValue()))

			                            						+ "' AND '" +FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
		}
		if (carInfoVo.getCarVinNumber() != null
				&& !"".equals(carInfoVo.getCarVinNumber())) {
			sql.append(" and a.xs_car_vin_number like '%"
					+ carInfoVo.getCarVinNumber() + "%'");
		}
		if (carInfoVo.getCarBrand()!= null
				&& !"".equals(carInfoVo.getCarBrand())) {
			sql.append(" and a.xs_car_brand = '"
					+ carInfoVo.getCarBrand() + "'");
		}
		
		if (carInfoVo.getCarModelId() != null
				&& !"".equals(carInfoVo.getCarModelId())) {
			sql.append(" and a.xs_car_model_id = '"
					+ carInfoVo.getCarModelId() + "'");
		}
		if (carInfoVo.getSupplierId() != null
				&& !"".equals(carInfoVo.getSupplierId())) {
			sql.append(" and h.xs_supplier_id = '"
					+ carInfoVo.getSupplierId() + "'");
		}
		
		if (carInfoVo.getRetreat_date() != null
				&& !"".equals(carInfoVo.getRetreat_date())) {
			String[] str = carInfoVo.getRetreat_date().split(",");
			if (str.length > 1) {
				sql.append(" and j.retreat_date BETWEEN '" + str[0]
						+ "' AND '" + str[1] + "'");
			} else {
				if (carInfoVo.getRetreat_date().length() > 10) {
					sql.append(" and j.retreat_date>= '" + str[0] + "'");
				} else {
					sql.append(" and j.retreat_date <='" + str[0] + "'");
				}

			}
		}
		if (carInfoVo.getRetreat_code() != null
				&& !"".equals(carInfoVo.getRetreat_code())) {
			sql.append(" and j.retreat_code like '%"
					+ carInfoVo.getRetreat_code() + "%'");
		}
		if (carInfoVo.getCustomName() != null
				&& !"".equals(carInfoVo.getCustomName())) {
			sql.append(" and f.xs_custom_name like '%"
					+ carInfoVo.getCustomName() + "%'");
		}
		if (carInfoVo.getUpType()!= null
				&& !"".equals(carInfoVo.getUpType())&&"up".equals(carInfoVo.getUpType())) {
			sql.append(" and a.upData is not null ");
		}else if(carInfoVo.getUpType()!= null
				&& !"".equals(carInfoVo.getUpType())&&"noUp".equals(carInfoVo.getUpType())){
			sql.append(" and a.upData is null ");
		}
		if (carInfoVo.getUpPerson() != null
				&& !"".equals(carInfoVo.getUpPerson())) {
			sql.append(" and a.upPerson = '"
					+ carInfoVo.getUpPerson() + "'");
		}
		
		
				List<CarInfoVo> list= new ArrayList<CarInfoVo>();
				List<Object[]> resultList = createSQLQuery(sql.toString(),carInfoVo.getPage(),carInfoVo.getRows());
				if (resultList != null && resultList.size() > 0) {
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					Object[] obj = null;
					for (int i = 0; i < resultList.size(); i++) {
						CarInfoVo car = new CarInfoVo();
						obj = resultList.get(i);
						car.setSellData(obj[0] != null ? obj[0].toString() : null);
						car.setSellCode(obj[1] != null ? obj[1].toString() : null);
						car.setRetreat_date(obj[2] != null ? fmt.format(obj[2]) : null);
						car.setRetreat_code(obj[3] != null ? obj[3].toString() : null);
						car.setCarCode(obj[4] != null ? obj[4].toString() : null);
						car.setCarVinNumber(obj[5] != null ? obj[5].toString() : null);
						car.setCarBrand(obj[6] != null ? Integer.parseInt(obj[6].toString()) : null);
						car.setCarBrandName(obj[7] != null ? obj[7].toString() : null);
						car.setCarModelId(obj[8] != null ? Integer.parseInt(obj[8].toString()) : null);
						car.setCarModelName(obj[9] != null ? obj[9].toString() : null);
						car.setCarColor(obj[10] != null ? Integer.parseInt(obj[10].toString()) : null);
						car.setColorName(obj[11] != null ? obj[11].toString() : null);
						car.setUpData(obj[12] != null ? obj[12].toString() : null);
						car.setUpPerson(obj[13] != null ? Integer.parseInt(obj[13].toString()) : null);
						car.setUpPersonName(obj[14] != null ? obj[14].toString() : null);
						car.setCustomName(obj[15] != null ? obj[15].toString() : null);
						car.setTel(obj[16] != null ? obj[16].toString() : null);
						car.setSupplierId(obj[17] != null ? Integer.parseInt(obj[17].toString()) : null);
						car.setSupplierName(obj[18] != null ? obj[18].toString() : null);
						car.setOutId(obj[19] != null ? Integer.parseInt(obj[19].toString()) : null);
					list.add(car);
					}
						
	}
					int total = this.getSQLCount(sql.toString(), null);
					dg.setRows(list);
					dg.setTotal(total);
					return dg;
	}

	/**
	 * 隨車附件管理
	 */
	
	public Datagrid getCarAttachment(CarInfoVo carInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(" SELECT zz.car1,zz.car2,zz.car3,zz.car4,zz.car5,zz.car6,zz.car7,zz.car8,zz.car9," +
				" (zz.sum1+zz.sum2-zz.sum3-zz.sum4)AS  sums,(zz.bsum1+zz.bsum2-zz.bsum3-zz.bsum4)AS bsums " +
				"FROM ( " +
				"SELECT ss.car1,ss.car2,ss.car3,ss.car4,ss.car5,ss.car6,ss.car7,ss.car8,ss.car9," +
				"(CASE WHEN ss.sum1 IS NOT NULL THEN ss.sum1 ELSE 0 END)AS sum1," +
				"	 (CASE WHEN ss.sum2 IS NOT NULL THEN ss.sum2 ELSE 0 END)AS sum2," +
				"	 (CASE WHEN ss.sum3 IS NOT NULL THEN ss.sum3 ELSE 0 END)AS sum3," +
				"	 (CASE WHEN ss.sum4 IS NOT NULL THEN ss.sum4 ELSE 0 END)AS sum4," +
				"	 (CASE WHEN ss.bsum1 IS NOT NULL THEN ss.bsum1 ELSE 0 END)AS bsum1," +
				"	 (CASE WHEN ss.bsum2 IS NOT NULL THEN ss.bsum2 ELSE 0 END)AS bsum2," +
				"	 (CASE WHEN ss.bsum3 IS NOT NULL THEN ss.bsum3 ELSE 0 END)AS bsum3," +
				"	 (CASE WHEN ss.bsum4 IS NOT NULL THEN ss.bsum4 ELSE 0 END)AS bsum4" +
				"	  FROM ( " +
				"	SELECT A.xs_car_id AS car1,a.xs_car_code AS car2,a.xs_car_vin_number AS car3, a.xs_car_brand AS car4 ," +
				"(SELECT dataValue FROM xs_childdictionary ch WHERE ch.child_id =a.xs_car_brand) AS car5,a.xs_car_model_id AS car6," +
				"	c.xs_model_name AS car7,a.xs_car_color AS car8,(SELECT dataValue FROM xs_childdictionary ch WHERE ch.child_id =a.xs_car_color)AS car9," +
				"(SELECT SUM(att.count1) FROM (" +
				"SELECT (CASE WHEN temp.count1 IS NOT NULL THEN temp.count1 ELSE 0 END)AS count1,temp.xs_car_id,temp.operator_type FROM xs_sell_attachment temp ) AS att " +
				"WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  " +
				"WHERE child.parent_id = parent.parent_id AND parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND " +
				"	child.dataKey='"+Contstants.ATTACHMENTTYPES.LURU+"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS sum1," +
				"(SELECT SUM(att.count1) FROM (SELECT (CASE WHEN temp.count1 IS NOT NULL THEN temp.count1 ELSE 0 END)AS count1,temp.xs_car_id,temp.operator_type " +
				"FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND " +
				" parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND 	child.dataKey='"+Contstants.ATTACHMENTTYPES.GUIHUAN+"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS sum2," +
				"(SELECT SUM(att.count1) FROM (SELECT (CASE WHEN temp.count1 IS NOT NULL THEN temp.count1 ELSE 0 END)AS count1,temp.xs_car_id,temp.operator_type " +
				"FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"	SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
				"AND parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND 	child.dataKey='"+Contstants.ATTACHMENTTYPES.LINGQU +"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS sum3," +
				"(SELECT SUM(att.count1) FROM (SELECT (CASE WHEN temp.count1 IS NOT NULL THEN temp.count1 ELSE 0 END)AS count1,temp.xs_car_id,temp.operator_type " +
				"FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND " +
				"parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND child.dataKey='"+Contstants.ATTACHMENTTYPES.JIECHU +"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS sum4," +
				"(SELECT SUM(att.count2) FROM (SELECT (CASE WHEN temp.count2 IS NOT NULL THEN temp.count2 ELSE 0 END)AS count2,temp.xs_car_id,temp.operator_type" +
				" FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id AND" +
				" parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND child.dataKey='"+Contstants.ATTACHMENTTYPES.LURU+"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS bsum1," +
				"(SELECT SUM(att.count2) FROM (SELECT (CASE WHEN temp.count2 IS NOT NULL THEN temp.count2 ELSE 0 END)AS count2,temp.xs_car_id,temp.operator_type " +
				"FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
				"AND parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND child.dataKey='"+Contstants.ATTACHMENTTYPES.GUIHUAN+"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS bsum2," +
				"(SELECT SUM(att.count2) FROM (SELECT (CASE WHEN temp.count2 IS NOT NULL THEN temp.count2 ELSE 0 END)AS count2,temp.xs_car_id,temp.operator_type" +
				" FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
				"AND parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND child.dataKey='"+Contstants.ATTACHMENTTYPES.LINGQU +"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS bsum3," +
				"(SELECT SUM(att.count2) FROM (SELECT (CASE WHEN temp.count2 IS NOT NULL THEN temp.count2 ELSE 0 END)AS count2,temp.xs_car_id,temp.operator_type " +
				"FROM xs_sell_attachment temp ) AS att WHERE att.xs_car_id=a.xs_car_id AND att.operator_type=(" +
				"SELECT child.child_id FROM xs_childdictionary  child,xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
				"AND parent.dataKey ='"+Contstants.ATTACHMENTTYPES.ATTACHMENTTYPE+"' AND child.dataKey='"+Contstants.ATTACHMENTTYPES.JIECHU +"' and child.enterprise_id="+carInfoVo.getEnterpriseId()+") ) AS bsum4" +
				" FROM  xs_car_info A " +
				"LEFT JOIN  xs_sell_attachment B ON b.xs_car_id=a.xs_car_id " +
				" JOIN xs_car_model C ON c.xs_model_id=a.xs_car_model_id where 1=1 " );
				
				if(carInfoVo.getEnterpriseId()!=null&&!"".equals(carInfoVo.getEnterpriseId())){
					sql.append(" and a.enterprise_id='"+carInfoVo.getEnterpriseId()+"'");
				}
				if(carInfoVo.getCarVinNumber()!=null&&!"".equals(carInfoVo.getCarVinNumber())){
					sql.append(" and a.xs_car_vin_number like '%"+carInfoVo.getCarVinNumber()+"%'");
				}
				if(carInfoVo.getCarBrand()!=null&&!"".equals(carInfoVo.getCarBrand())){
					sql.append(" and a.xs_car_brand='"+carInfoVo.getCarBrand()+"'");
				}
				if(carInfoVo.getCarModelId()!=null&&!"".equals(carInfoVo.getCarModelId())){
					sql.append(" and a.xs_car_model_id='"+carInfoVo.getCarModelId()+"'");
				}
				if(carInfoVo.getSend_persion()!=null&&!"".equals(carInfoVo.getSend_persion())){
					sql.append(" and B.send_persion like '%"+carInfoVo.getSend_persion()+"%'");
				}
				
				if(carInfoVo.getOperator_date()!=null&&!"".equals(carInfoVo.getOperator_date())){
						sql.append(" and B.operator_date >='"+carInfoVo.getOperator_date()+"'");
					}
				if(carInfoVo.getOperator_date2()!=null&&!"".equals(carInfoVo.getOperator_date2())){
						sql.append(" and B.operator_date<='"+carInfoVo.getOperator_date2()+"'");
					
				}
				/*if((carInfoVo.getOperator_date()==null||"".equals(carInfoVo.getOperator_date()))&&
						(carInfoVo.getOperator_date2()==null||"".equals(carInfoVo.getOperator_date2()))){
					sql.append(" and DATE(B.operator_date) between " +
					" '"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,carInfoVo.getEnterpriseId()).getCiValue())) +"'" +
					" and  '"+
					FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}*/
				sql.append(" GROUP BY  A.xs_car_id " +
				") AS ss" +
				") AS zz");
		List<CarInfoVo> list= new ArrayList<CarInfoVo>();
		List<Object[]> resultList = createSQLQuery(sql.toString(),carInfoVo.getPage(),carInfoVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarInfoVo car = new CarInfoVo();
				obj = resultList.get(i);
				car.setCarId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				car.setCarCode(obj[1] != null ? obj[1].toString() : null);
				car.setCarVinNumber(obj[2] != null ? obj[2].toString() : null);
				car.setCarBrand(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				car.setCarBrandName(obj[4] != null ? obj[4].toString() : null);
				car.setCarModelId(obj[5] != null ? Integer.parseInt(obj[5].toString()) : null);
				car.setCarModelName(obj[6] != null ? obj[6].toString() : null);
				car.setCarColor(obj[7] != null ? Integer.parseInt(obj[7].toString()) : null);
				car.setColorName(obj[8] != null ? obj[8].toString() : null);
				car.setDyCount(obj[9] != null ? Integer.parseInt(obj[9].toString()) : null);
				car.setYhCount(obj[10] != null ? Integer.parseInt(obj[10].toString()) : null);
				list.add(car);
			}
				
}
			int total = this.getSQLCount(sql.toString(), null);
			dg.setRows(list);
			dg.setTotal(total);
			return dg;
	}

	
	public Datagrid getAttachmentDel(CarInfoVo carInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer("SELECT a.xs_car_id,a.attachment_no,a.count1,a.count2," +
				"a.send_persion,a.operator_date,a.operator_persion,b.STF_NAME,a.operator_type," +
				"(SELECT dataValue FROM xs_childdictionary ch WHERE ch.child_id =a.operator_type)AS TYPES,a.remark " +
				"FROM xs_sell_attachment A JOIN bas_stuff B ON b.STF_ID=a.operator_persion " +
				" WHERE a.xs_car_id="+carInfoVo.getCarId());
		List<CarInfoVo> list= new ArrayList<CarInfoVo>();
		List<Object[]> resultList = createSQLQuery(sql.toString(),carInfoVo.getPage(),carInfoVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				CarInfoVo car = new CarInfoVo();
				obj = resultList.get(i);
				car.setCarId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				car.setAttachment_no(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
				car.setCount1(obj[2] != null ? Integer.parseInt(obj[2].toString()) : null);
				car.setCount2(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				car.setSend_persion(obj[4] != null ? obj[4].toString() : null);
				
				car.setOperator_date(obj[5] != null ? fmt.format(obj[5]) : null);
				car.setOperator_persion(obj[6] != null ? Integer.parseInt(obj[6].toString()) : null);
				car.setPerson(obj[7] != null ? obj[7].toString() : null);
				car.setOperator_type(obj[8] != null ? Integer.parseInt(obj[8].toString()): null);
				car.setTypes(obj[9] != null ? obj[9].toString() : null);
				car.setRemark(obj[10] != null ? obj[10].toString() : null);
				list.add(car);
			}
				
}
			int total = this.getSQLCount(sql.toString(), null);
			dg.setRows(list);
			dg.setTotal(total);
			return dg;
}
}
