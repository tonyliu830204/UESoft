package com.syuesoft.frt.service;

import java.io.Serializable;
import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.model.FrtCustom;

public interface FrtCustomService
{

    public Datagrid datagrid(FrtCustomVo fcVo) throws Exception; // 客户档案datagrid

    public Serializable save(FrtCustomVo fcVo) throws Exception; // 保存客户档案

    public Serializable saveCustom(FrtCustomVo fcVo) throws Exception; // 保存客户档案同步车档案

    public void remove(FrtCustomVo fcVo) throws Exception; // 删除客户档案

    public void edit(FrtCustomVo fcVo) throws Exception; // 更新客户
    
    public Boolean modifyCustomFlag(FrtCustomVo fcVo) throws Exception; // 更改客户使用状态

	public boolean changeCustomId(FrtCustomVo fcVo)throws Exception;//变更客户档案

	public List<FrtCustom> getCustomId(FrtCustomVo fcVo)throws Exception;//判断客户是否存在

	Datagrid datagrid_frt_custom(FrtCustomVo fcVo) throws Exception;

}
