package com.syuesoft.sell.noteManage.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.noteManage.vo.NoteManageVo;


public interface NoteManageDao extends BaseDaoI<BaseBean>{
	//查询客户信息
	public Datagrid getCustomInfo( NoteManageVo  noteManageVo) throws Exception;

}
