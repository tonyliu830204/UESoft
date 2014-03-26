package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasJobPropertyDao;
import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.fbk.vo.BasJobPropertyVO;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasStuffJob;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.MyBeanUtils;
/**
 * 工作属性Dao实现
 * @author LWJ
 * */
@Repository("basJobPropertyDao")
public class BasJobPropertyDaoImpl extends BaseDaoImpl<BasJobProperty>
        implements BasJobPropertyDao
{
	@Autowired
	private EnterpriseInfoDAO enterpriseInfoDAO; 
	/**
	 * 工作属性新增
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此工作属性已存在，新增失败
	 * */
    
    public boolean add(BasJobPropertyVO bjobPropertyVO) throws Exception
    {
    	 List list = this.getHibernateTemplate().getSessionFactory()
         .getCurrentSession().find("from BasJobProperty bjp where bjp.jobProName='" + bjobPropertyVO.getJobProName() + "' and bjp.sysType='"+bjobPropertyVO.getSysType()+"' and bjp.enterpriseId="+bjobPropertyVO.getEnterpriseId());
		 if (list.size() > 0){
		     return false;
		 }else{
			 BasJobProperty bjp = new BasJobProperty();
		 	MyBeanUtils.getInstance().copyBeans(bjobPropertyVO, bjp);
		 	bjp.setEnterpriseId(bjobPropertyVO.getEnterpriseId());
		     this.getHibernateTemplate().save(bjp);
		     return true;
		 }
    }
    /**
	 * 获取所有的工作属性对象
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
	 * */
    
	public List<BasJobProperty> findAll() throws Exception {
		// TODO Auto-generated method stub
    	return this.getHibernateTemplate().find("from BasJobProperty");
	}
    /**
	 * 工作属性修改
	 * @param bjobProperty 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此工作属性已存在，修改失败
	 * */
	
	public boolean basJobPropertyUpdate(BasJobProperty bjobProperty) throws Exception {
		  List list =find("from BasJobProperty bjp where bjp.jobProName='" + bjobProperty.getJobProName()+ "' and bjp.sysType='"+bjobProperty.getSysType()+"'  and bjp.enterpriseId="+bjobProperty.getEnterpriseId()+" and bjp.jobProId not in (" + bjobProperty.getJobProId() + ")");
		  if (list!=null&&list.size() > 0){
		      return false;
		  }else{
		      this.getHibernateTemplate().merge(bjobProperty);
		      return true;
		  }
	}
	 /**
	 * 获取指定的工作属性对象
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return BasJobProperty 返回工作属性实体
	 * */
	
	public BasJobProperty getBasJobProperty(Short jobProId) throws Exception {
		// TODO Auto-generated method stub
		return get(BasJobProperty.class, jobProId);
	}
	/**
	 * 获取指定工作属性所属公司信息
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return EnterpriseInfo 返回公司实体
	 * */
	
	public EnterpriseInfo getBasJobPropertyOfEnterpriseInfoByJobProId(
			Short jobProId) throws Exception {
		return enterpriseInfoDAO.getEnterpriseInfo(getBasJobProperty(jobProId).getEnterpriseId());
	}
	/**
	 * 获取指定工作属性下的所有人员
	 * @param jobProId 工作属性序号
	 * @exception Exception 总异常
	 * @return List<BasStuff> 返回包含员工实体的集合
	 * */
	
	public List<BasStuff> findBasJobPropertyOfBasStuffsByJobProId(Short jobProId)
			throws Exception {
		List<BasStuff> list=new ArrayList();
		HashMap<String,BasStuff> hashMap=new HashMap();
		Set<BasStuffJob> set= getBasJobProperty(jobProId).getBasStuffJobs();
		if(set!=null&&set.size()>0)
		for (BasStuffJob basStuffJob : set) {
				hashMap.put(basStuffJob.getId().getBasStuff().getStfId().toString(),basStuffJob.getId().getBasStuff());
		}
		Iterator<String> it=hashMap.keySet().iterator();
		while(it.hasNext()){
			list.add(hashMap.get(it.next()));
		}
		return list;
	}
	/**
	 * 获取指定工作属性下的指定人员的部门信息
	 * @param jobProId 工作属性序号
	 * @param stfId 员工序号
	 * @exception Exception 总异常
	 * @return BasDept 返回部门实体
	 * */
	
	public BasDept findBasJobPropertyOfBasDeptByJobProId(Short jobProId,Long stfId)
			throws Exception {
		Set<BasStuffJob> set= getBasJobProperty(jobProId).getBasStuffJobs();
		if(set!=null&&set.size()>0)
		for (BasStuffJob basStuffJob : set) {
			if(basStuffJob.getId().getBasStuff().getStfId().toString().equals(stfId.toString())){
				return basStuffJob.getId().getBasStuff().getBasDept();
			}
		}
		return null;
	}
    
}
