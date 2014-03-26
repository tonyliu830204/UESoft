package com.syuesoft.sell.base.service;

import java.util.List;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.RepayProjectVo;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface ReProjectService {
	public Datagrid getPager(RepayProjectVo xsRepayProject)throws Exception;
	public void addReProject(RepayProjectVo xsRepayProject)throws Exception;
	public Msg deleteReProject(RepayProjectVo xsRepayProject)throws Exception;
	public void updateReProject(RepayProjectVo xsRepayProject)throws Exception;
	public List<ComboBoxJson> findAllRepay(Integer enterpriseId)throws Exception;
	public boolean findReProTwo(String proCode,Integer enid) throws Exception;
	public boolean findRePro(String proCode,Integer id,Integer enid) throws Exception;
}
