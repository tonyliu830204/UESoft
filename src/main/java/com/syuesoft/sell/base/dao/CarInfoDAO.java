package com.syuesoft.sell.base.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.vo.CarInfoVo;

public interface CarInfoDAO extends BaseDaoI<BaseBean> {
	//车辆上报管理
	public Datagrid queryCarUpInfor(CarInfoVo carInfoVo) throws Exception;
	//车辆附件管理
	public Datagrid getCarAttachment(CarInfoVo carInfoVo) throws Exception;
	//车辆附件管理 明细
	public Datagrid getAttachmentDel(CarInfoVo carInfoVo) throws Exception;
}
