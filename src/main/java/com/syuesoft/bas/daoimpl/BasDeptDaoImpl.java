package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasDeptDao;
import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasStuffJob;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.MyBeanUtils;
/**
 * 部门设置DAO实现
 * @author LWJ
 * */
@Repository("basDeptDao")
public class BasDeptDaoImpl extends BaseDaoImpl<BasDept> implements BasDeptDao
{
	@Autowired
	private EnterpriseInfoDAO enterpriseInfoDAO; 

	/**
	 * 部门设置新增方法
	 * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此部门已存在，新增失败
	 * */
    @SuppressWarnings("deprecation")
    public boolean add(BasDeptVo basDeptVo) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find("from BasDept bd where bd.deptName='" + basDeptVo.getDeptName() + "' and bd.enterpriseId="+basDeptVo.getNowEnterpriseId());
        if (list.size() > 0)
            return false;
        else
        {
        	BasDept basDept=new BasDept();
        	MyBeanUtils.getInstance().copyBeans(basDeptVo, basDept);
        	basDept.setEnterpriseId(basDeptVo.getEnterpriseId());
        	basDept.setEnterpriseId(basDeptVo.getEnterpriseId());

            this.getHibernateTemplate().save(basDept);
            return true;
        }
    }
    /**
     * 部门设置修改方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此部门已存在，修改失败
     * */
	
    public boolean basDeptUpdate(BasDept basDept)throws Exception
    {
        List list = find("from BasDept bd where bd.deptName='" + basDept.getDeptName()+ "' and bd.enterpriseId="+basDept.getEnterpriseId()+"  and bd.deptId not in (" + basDept.getDeptId()+ ")");
        if (list!=null&&list.size() > 0){
            return false;
        }else{
            this.getHibernateTemplate().merge(basDept);
            return true;
        }
    }
    /**
     * 获取所有的部门对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
    
    public List<BasDept> findAll(){
        return this.getHibernateTemplate().find("from BasDept");
    }
    /**
     * 获取指定部门对象
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return BasDept 返回部门实体
     * */
    
    public BasDept getBasDept(Short deptId)throws Exception{
        return get(BasDept.class,deptId);
    }
    /**
     * 获取指定部门的公司信息
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return EnterpriseInfo 返回公司实体
     * */
	
	public EnterpriseInfo getDeptOfEnterpriseInfoByDeptId(Short deptId)
			throws Exception {
		// TODO Auto-generated method stub
		return enterpriseInfoDAO.getEnterpriseInfo(getBasDept(deptId).getEnterpriseId());
	}
	/**
     * 获取指定部门下的所有人员
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return List<BasStuff> 返回包含员工实体的集合
     * */
	
	public List<BasStuff> findDeptOfBasStuffsByDeptId(Short deptId)
			throws Exception {
		List<BasStuff> list=new ArrayList();
		Set<BasStuff> set= getBasDept(deptId).getBasStuffs();
		if(set!=null&&set.size()>0)
		for (BasStuff basStuff : set) {
			list.add(basStuff);
		}
		return list;
	}
	/**
     * 获取指定部门下的所有人员的工作属性
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
     * */
	
	public List<BasJobProperty> findDeptOfBasJobProeprtysByDeptId(Short deptId)
			throws Exception {
		List<BasJobProperty> list=new ArrayList();
		HashMap<Short,BasJobProperty> hashMap=new HashMap();
		Set<BasStuff> set= getBasDept(deptId).getBasStuffs();
		Set<BasStuffJob> set2=null;
		if(set!=null&&set.size()>0)
		for (BasStuff basStuff : set) {
			set2=basStuff.getBasStuffJobs();
			if(set2!=null&&set2.size()>0)
			for (BasStuffJob basStuffJob : set2) {
				hashMap.put(basStuffJob.getId().getBasJobProperty().getJobProId(),basStuffJob.getId().getBasJobProperty());
			}
		}
		Iterator<Short> it=hashMap.keySet().iterator();
		while(it.hasNext()){
			list.add(hashMap.get(it.next()));
		}
		return list;
	}
	 /**
     * 获取指定部门下的指定人员的工作属性
     * @param deptId 部门序号
     * @param stfId 员工序号
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
     * */
	
	public List<BasJobProperty> findDeptOfBasJobProeprtyByDeptId(Short deptId,
			Long stfId) throws Exception {
		List<BasJobProperty> list=new ArrayList();
		Set<BasStuff> set= getBasDept(deptId).getBasStuffs();
		if(set!=null&&set.size()>0)
		for (BasStuff basStuff : set) {
			if(basStuff.getStfId().toString().equals(stfId.toString())){
				Set<BasStuffJob> set2=basStuff.getBasStuffJobs();
				if(set2!=null&&set2.size()>0)
				for (BasStuffJob basStuffJob : set2) {
					list.add(basStuffJob.getId().getBasJobProperty().getJobProId(),basStuffJob.getId().getBasJobProperty());
				}				
			}
		}
		return list;
	}
    
}