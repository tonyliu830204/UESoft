package com.syuesoft.sell.base.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.SellTargetDAO;

@Repository("sellTargetDAO")
public class SellTargetDAOImpl extends BaseDaoImpl<BaseBean> implements SellTargetDAO{

	public static Integer jobId=Contstants.BASJOBPROPERTY.JOBPROPERTY12;
	public List<Object[]> findAllDept(Integer enterprise_id) throws Exception{
		String sql="SELECT DISTINCT s.DEPT_ID,dept.DEPT_NAME FROM bas_users u,bas_stuff  s,bas_stuff_job job,bas_job_property jobPro," +
				"bas_dept dept WHERE u.STF_ID=s.STF_ID AND job.STF_ID=s.STF_ID AND jobPro.JOB_PRO_ID=job.JOB_PRO_ID " +
				"AND dept.DEPT_ID=s.DEPT_ID  AND jobPro.JOB_PRO_ID='"+jobId+"'";
		if(enterprise_id!=null&&!"".equals(enterprise_id)){
			sql+=" and s.enterprise_id="+enterprise_id;
		}
		return this.createSQLQuery(sql);
	}
	public List<Object[]> findStuffByDept(Short deptId,Integer enterprise_id) throws Exception{
		 String sql="SELECT s.STF_ID,s.STF_NAME FROM bas_users u,bas_stuff  s,bas_stuff_job job,bas_job_property jobPro WHERE u.STF_ID=s.STF_ID AND"+
		 " job.STF_ID=s.STF_ID AND jobPro.JOB_PRO_ID=job.JOB_PRO_ID  AND jobPro.JOB_PRO_ID='"+jobId+"' AND s.DEPT_ID='"+deptId+"'and s.enterprise_id="+enterprise_id;
		 return this.createSQLQuery(sql);
	}
}
