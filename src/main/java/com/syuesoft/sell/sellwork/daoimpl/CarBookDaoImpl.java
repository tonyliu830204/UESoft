package com.syuesoft.sell.sellwork.daoimpl;


import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.base.daoimpl.BaseTagDAOImpl;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.sellwork.dao.CarBookDao;
import com.syuesoft.sell.sellwork.vo.CarBookVo;
import com.syuesoft.util.CreateID;

@Repository("carBookDao")
public class CarBookDaoImpl extends BaseDaoImpl implements CarBookDao {


	/**
	 * 删除
	 */
	
	public int deleteCarBookInfor(CarBookVo carBookVo) throws Exception {
		return 0;
	}


	/**
	 * 修改
	 */
	@SuppressWarnings("unchecked")
	
	public void updateCarBook(XsSellCarReserve xsSellCarReserve) throws Exception {
		
		 merge(xsSellCarReserve);
	}

	/**
	 * 取消客户预定
	 */
	
	@SuppressWarnings("unchecked")
	
	public void doCancelBook(XsSellCarReserve xsSellCarReserve) throws Exception {
		merge(xsSellCarReserve);
	}

	/**
	 * 审核
	 */
	@SuppressWarnings("unchecked")
	
	public void doAudit(XsSellCarReserve xsSellCarReserve) throws Exception {
		merge(xsSellCarReserve);
	}
	
}
