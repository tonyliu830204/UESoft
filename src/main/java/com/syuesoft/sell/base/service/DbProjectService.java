package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.DbProjectVo;
import com.syuesoft.util.Msg;

public interface DbProjectService {
	public Datagrid getPager(DbProjectVo xsDbProject) throws Exception;
	public void addDbProject(DbProjectVo xsDbProject) throws Exception;
	public Msg deleteDbProject(DbProjectVo xsDbProject) throws Exception;
	public void updateDbProject(DbProjectVo xsDbProject) throws Exception;
	public boolean findDbProTwo(String proCode,String dname,Integer enid) throws Exception;
	public boolean findDbPro(String proCode,String name,Integer id,Integer enid) throws Exception;
}
