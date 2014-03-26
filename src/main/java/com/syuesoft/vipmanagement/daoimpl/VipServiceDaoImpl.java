package com.syuesoft.vipmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.VipcardMealR;
import com.syuesoft.vipmanagement.dao.VipServiceDao;
import com.syuesoft.vipmanagement.vo.VipServiceTreeGridVo;

@Repository("vipServiceDao")
public class VipServiceDaoImpl extends BaseDaoImpl<VipcardMealR> implements VipServiceDao {

	//通过会员编号查找套餐名称
	@SuppressWarnings("unchecked")
	
	public List<?> getMealNameById(final String vipid) throws Exception {
		String sql = "SELECT meal_NAME FROM vip_meal WHERE meal_id =(SELECT meal_id FROM vipcard_meal_r WHERE bas_vip_id ='"+vipid+"')";
		return this.createSQLQuery(sql);
	}
}