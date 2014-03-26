package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasDept;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;

/**
 * 部门设置Service
 * @author LWJ
 * */
public interface BasDeptService
{
	/**
	 * 部门设置新增方法
	 * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此部门已存在，新增失败
	 * */
    public void add(BasDeptVo basDeptVo) throws Exception;	
    /**
     * 部门设置删除方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为删除完成，false为此部门拥有用户，删除失败
     * */
    public void delete(BasDeptVo basDeptVo) throws Exception;
    /**
     * 部门设置修改方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此部门已存在，修改失败
     * */
    public boolean update(BasDeptVo basDeptVo) throws Exception;
    /**
     * 部门设置查询方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
    public List<BasDept> findAll(BasDeptVo basDeptVo)throws Exception;
    /**
     * 部门设置查询方法
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return List<BasDept> 返回包含部门实体的集合
     * */
    public Datagrid basDeptDatagrid(BasDeptVo basDeptVo)throws Exception;
    /**
     * 获取当前用户所属公司Id和Name
     * @param basDeptVo 部门视图对象
	 * @exception Exception 总异常
	 * @return BasDeptVo 返回部门视图对象
     * */
    public BasDeptVo findBasStuffOfEnterpriseInfo(BasDeptVo basDeptVo)throws Exception;
}
