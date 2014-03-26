package com.syuesoft.sell.instore.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;
import com.syuesoft.sell.instore.dao.InstoreOutDAO;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.instore.vo.SellServicingVo;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSellInstorehouseDel;

@Repository("instoreOutDAO")
public class InstoreOutDAOImpl extends BaseDaoImpl<BaseBean> implements InstoreOutDAO{
	public List<XsSellInstorehouseDel> findByInstoreId(Integer instoreId) throws Exception{
		String hql="from XsSellInstorehouseDel instoreDel where 1=1 and  instoreDel.instorehouse.instorehouseId="+instoreId;
		List<BaseBean> bas=find(hql);
		List<XsSellInstorehouseDel>instoreDels=new ArrayList<XsSellInstorehouseDel>();
		if(bas!=null && bas.size()>0){
			for (BaseBean b:bas) {
				XsSellInstorehouseDel  instoreDel=(XsSellInstorehouseDel)b;
				instoreDels.add(instoreDel);
			}
		}
		return instoreDels;	
	}
	public XsSellInstorehouse findById(Integer instoreId) throws Exception{
		return (XsSellInstorehouse) this.get("from XsSellInstorehouse instore where 1=1 and  instore.instorehouseId="+instoreId);
	}
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception{
		return (XsSellInstorehouseDel) this.get("from XsSellInstorehouseDel del where 1=1 and  detailsId="+delId);
	}
	
	public List<SellRetreatVo> findByCarId(Integer carId) throws Exception {
		List<SellRetreatVo> list_ = new ArrayList<SellRetreatVo>();
		
		StringBuffer sql = new StringBuffer("SELECT a.xs_car_id,a.xs_car_brand," +
				"(SELECT  k.datavalue FROM xs_childdictionary k WHERE a.xs_car_brand = k.child_id) AS brand,	" +
				"a.xs_car_color," +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE a.xs_car_color = k.child_id) AS color," +
				"	a.xs_car_model_id,b.xs_model_name,a.xs_car_code,a.xs_car_vin_number, a.xs_car_motor_number,	a.xs_car_ocn " +
				"FROM xs_car_info a ,xs_car_model b WHERE a.xs_car_model_id=b.xs_model_id " +
				"AND a.xs_car_id="+carId);
		List<Object[]> resultList = createSQLQuery(sql.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellRetreatVo wo = new SellRetreatVo();
				obj = resultList.get(i);
				wo.setCarId(obj[0] != null ? Integer.parseInt(obj[0].toString()) : null);
				wo.setCarBrand(obj[1] != null ? Integer.parseInt(obj[1].toString()) : null);
				wo.setCarBrandName(obj[2] != null ? obj[2].toString() :null);
				wo.setCarColor(obj[3] != null ? Integer.parseInt(obj[3].toString()) : null);
				wo.setColorName(obj[4] != null ? obj[4].toString() : null);
				wo.setCarModelId(obj[5] != null ? Integer.parseInt(obj[5].toString()) : null);
				wo.setCarModelName(obj[6] != null ? obj[6].toString() : null);
				wo.setCarCode(obj[7] != null ? obj[7].toString() : null);
				wo.setCarVinNumber(obj[8] != null ? obj[8].toString() : null);
				wo.setCarMotorNumber(obj[9] != null ? obj[9].toString() : null);
				wo.setCarOcn(obj[10] != null ? obj[10].toString() : null);
				list_.add(wo);
			}
		}
				
				
		return list_;
	}
	
}
