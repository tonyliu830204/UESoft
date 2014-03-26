package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.FrtParts;
import com.syuesoft.util.Msg;

public interface BasPartsArchivesService
{

    public Datagrid datagridPartsArchives(FrtPartsVo fpVo) throws Exception; // 配件档案datagrid

    public void save(FrtPartsVo fpVo) throws Exception; // 保存配件档案

    public void remove(String partsId) throws Exception; // 删除配件档案

    public void edit(FrtPartsVo fpVo) throws Exception; // 修改配件档案

    public boolean changePartsId(FrtPartsVo fpVo) throws Exception; // 变更配件编号

    public List<FrtParts> getPartsId(FrtPartsVo fpVo) throws Exception; // 查询配件编号是否存在
    
    public Boolean isExistsJoinCompany(FrtPartsVo fpVo) throws Exception; // 查询配件有无入仓信息
    
    public Boolean modifyPartsFlag(FrtPartsVo fpVo) throws Exception; // 更改配件使用状态
    
    public Double findPartsCount(FrtPartsVo fpVo) throws Exception; // 查询配件库存量
    
    public Msg modifyImportPartsArchives(FrtPartsVo fpVo, BasUsers user)throws Exception;//导入配件档案
    
    /**判断配件是否已入库*/
    public boolean isExist(FrtPartsVo fpVo)throws Exception;

	public boolean isExistsPartsIdEdit(FrtPartsVo fpVo)throws Exception;
}
