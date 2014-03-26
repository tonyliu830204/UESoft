package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;

public interface FrtPartsService
{

    public Datagrid datagridFrtParts(FrtPartsVo fpVo) throws Exception; // 前台配件查询

    public List findAllParts(FrtPartsVo fpVo) throws Exception;// 查询未选配件
}
