package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.DistrubtPurviewVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.util.ComboBoxJson;
/**
 * 分布点管理Service
 * */
public interface DistributpurviewService {
	/**分布点权限管理datagrid*/
	public Datagrid distributpurviewDataGrid(DistrubtPurviewVo dpVo)throws Exception;
   /**分布点权限管理treegrid*/
	public Datagrid distributpurviewTreeGrid(DistrubtPurviewVo dpVo)throws Exception;
	/**分布点权限管理treegrid子级数据*/
	public List<DistrubtPurviewVo> distributpurviewTreeGridChild(DistrubtPurviewVo dpVo)throws Exception;
	/**分布点权限管理用户数据*/
	public Datagrid findDistrubtPurviewUsers(DistrubtPurviewVo dpVo)throws Exception;
	/**获取集团的所有角色*/
	public List<ComboBoxJson> findEnterpriseInfoRoles(DistrubtPurviewVo dpVo)throws Exception;
	/**指定角色的系统类型与指定的系统类型是否一致*/
	public Boolean isAccordSystemType(DistrubtPurviewVo dpVo)throws Exception;
	/**当前用户可访问的所有企业信息*/
	public Datagrid findAllStoreOfUser(DistrubtPurviewVo dpVo)throws Exception;
	/**查询员工系统级别（除超级管理员，集团管理员）*/
	public List<ComboBoxJson> findSystemLevel(DistrubtPurviewVo dpVo)throws Exception;
}