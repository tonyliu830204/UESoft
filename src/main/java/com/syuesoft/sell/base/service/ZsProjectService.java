package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.ZsProjectVo;
import com.syuesoft.util.Msg;

public interface ZsProjectService {
	public Datagrid getPager(ZsProjectVo xsZsProject)throws Exception;
	public void addZsProject(ZsProjectVo xsZsProject)throws Exception;
	public Msg deleteZsProject(ZsProjectVo xsZsProject)throws Exception;
	public void updateZsProject(ZsProjectVo xsZsProject)throws Exception;
	public boolean findZsProTwo(String proCode ,Integer enid) throws Exception;
	public boolean findZsPro(String proCode,int id,Integer enid) throws Exception;
}
