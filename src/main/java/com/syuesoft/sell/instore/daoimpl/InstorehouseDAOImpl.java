package com.syuesoft.sell.instore.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.instore.dao.InstorehouseDAO;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSellInstorehouseDel;

@Repository("InstorehouseDAO")
public class InstorehouseDAOImpl extends BaseDaoImpl<BaseBean> implements InstorehouseDAO{
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
	
	public List<Object[]> queryInstore(int page, int rows) throws Exception{
		String sql="SELECT		(SELECT   a.xs_model_name  FROM   xs_car_model	 a    WHERE   a.xs_model_id = car.xs_car_model_id)  AS   model_name,"+
		" car.xs_car_code, car.xs_car_vin_number, car.xs_car_motor_number,  car.xs_car_ocn,  car.xs_car_color, car.xs_supplier_id,"+
		"(SELECT  c.dataValue   FROM   xs_childdictionary   c   WHERE   c.child_id = car.xs_car_color)  AS  color_name,"+
		"(SELECT   s.xs_supplier_name   FROM   xs_supplier_info   s  WHERE   s.xs_supplier_id = car.xs_supplier_id)   AS   supplier_name,"+
		"(SELECT   a.xs_model_salesPrice   FROM   xs_car_model   a  WHERE   a.xs_model_id = car.xs_car_model_id)   AS   model_name,"+
		" instore.instorehouse_code,  instore.instorehouse_date,  del.tax,  del.notax"+
		" FROM   xs_sell_instorehouse instore,  xs_sell_instorehouse_del del,  xs_car_info car"+
		" WHERE   instore.instorehouse_id = del.instorehouse_id   AND   car.xs_car_id = del.xs_car_id";
		List<Object[]> objs=this.createSQLQuery(sql, page, rows);
		if(objs!=null && objs.size()>0){
			return objs;
		}
		return null;
	}
	
	public Float GetInstoreAge(Date instoreAge)throws Exception{
		List obj=this.createSQLQuery("SELECT DATEDIFF(NOW(),'"+new SimpleDateFormat("yyyy-MM-dd").format(instoreAge)+"') FROM DUAL");
		return Float.valueOf(String.valueOf(obj.get(0)));
	}
}
