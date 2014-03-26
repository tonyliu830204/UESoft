package com.syuesoft.systemmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.systemmanagement.dao.CustomerPerformanceStatisticsDao;
import com.syuesoft.systemmanagement.vo.CustomerPerformanceStatisticsVo;
import com.syuesoft.vipmanagement.vo.IntegralIntegratedQueryVo;

@Repository("customerPerformanceStatisticsDao")
public class CustomerPerformanceStatisticsDaoImpl extends BaseDaoImpl<CustomerPerformanceStatisticsVo>
		implements CustomerPerformanceStatisticsDao {

	
	public List<BasRepairGroup> getBasRepairGroup() throws Exception {
		return this.getHibernateTemplate().find("from BasRepairGroup");
	}

}
