package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.util.Msg;

public interface RepayService {
	public Datagrid getPager(RepayVo  xsRepay)throws Exception;
	public void addRepay(RepayVo  xsRepay)throws Exception;
	public Msg deleteRepay(RepayVo  xsRepay)throws Exception;
	public void updateRepay(RepayVo  xsRepay)throws Exception;
	public boolean findRepayTwo(String repayCode,Integer enid) throws Exception;
	public boolean findRepay(String repayCode,Integer id,Integer enid) throws Exception;
}
