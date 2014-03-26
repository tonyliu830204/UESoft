package com.syuesoft.sell.noteManage.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.noteManage.vo.NoteManageVo;

public interface NoteManageService {
	//查询客户信息
	public Datagrid getCustomInfo( NoteManageVo  noteManageVo) throws Exception;

}
