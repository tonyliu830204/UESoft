package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;
/**
 * 部门设置Dao
 * */
public interface BasDeptDao extends BaseDaoI<BasDept>
{
    /**
     * 部门设置修改方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此部门已存在，修改失败
     * */
    public boolean basDeptUpdate(BasDept basDept) throws Exception;
    /**
     * 获取所有的部门对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
    public List<BasDept> findAll() throws Exception;
    /**
     * 获取指定部门对象
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return BasDept 返回部门实体
     * */
    public BasDept getBasDept(Short deptId)throws Exception;
    /**
     * 获取指定部门的公司信息
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return EnterpriseInfo 返回公司实体
     * */
    public EnterpriseInfo getDeptOfEnterpriseInfoByDeptId(Short deptId)throws Exception;
    /**
     * 获取指定部门下的所有人员
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return List<BasStuff> 返回包含员工实体的集合
     * */
    public List<BasStuff> findDeptOfBasStuffsByDeptId(Short deptId)throws Exception;
    /**
     * 获取指定部门下的所有人员的工作属性
     * @param deptId 部门序号
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
     * */
    public List<BasJobProperty> findDeptOfBasJobProeprtysByDeptId(Short deptId) throws Exception;
    /**
     * 获取指定部门下的指定人员的工作属性
     * @param deptId 部门序号
     * @param stfId 员工序号
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性实体的集合
     * */
    public List<BasJobProperty> findDeptOfBasJobProeprtyByDeptId(Short deptId,Long stfId) throws Exception;
}
