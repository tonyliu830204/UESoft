package com.syuesoft.sell.control.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsSellFlowControl;

public interface XsSellFlowControlDao extends BaseDaoI<XsSellFlowControl>{
	/**执行sql语句*/
	public void executeSql(String sql)throws Exception;
}
