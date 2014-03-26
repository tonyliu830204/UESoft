package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.fbk.vo.BasJobPropertyVO;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasJobProperty;

/**
 * 工作属性Service
 * @author LWJ
 * */
public interface BasJobPropertyService
{
	/**
	 * 工作属性新增
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为新增完成，false为此工作属性已存在，新增失败
	 * */
    public boolean add(BasJobPropertyVO bjpVO) throws Exception;
    /**
	 * 工作属性删除
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为删除完成，false为此工作属性中拥有用户，删除失败
	 * */
    public boolean delete(BasJobPropertyVO bjpVO) throws Exception;
    /**
	 * 工作属性修改
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return boolean true为修改完成，false为此工作属性已存在，修改失败
	 * */
    public boolean update(BasJobPropertyVO bjpVO) throws Exception;
    /**
	 * 工作属性查询
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return List<BasJobProperty> 返回包含工作属性视图对象集合
	 * */
    public List<BasJobProperty> findAll(BasJobPropertyVO bjpVO)throws Exception;
    /**
	 * 工作属性查询
	 * @param bjpVO 工作属性视图对象
	 * @exception Exception 总异常
	 * @return Datagrid 返回数据表格对象
	 * */
    public Datagrid basJobPropertyDatagrid(BasJobPropertyVO bjpVO) throws Exception;
}
