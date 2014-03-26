package com.syuesoft.sell.base.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.model.XsCarModel;

@Repository("carModelDAO")
public class CarModelDAOImpl  extends BaseDaoImpl<XsCarModel> implements CarModelDAO{

	
	public Integer findModelIdByName(String name) throws Exception {
		String hql="from  XsCarModel model where model.modelName='"+name+"'";
		XsCarModel model=this.get(hql);
		if(model!=null ){
			return model.getModelId();
		}
		return null;
	}

}
