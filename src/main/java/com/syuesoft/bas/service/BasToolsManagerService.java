package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.ToolsManagerVo;
import com.syuesoft.util.Json;

public interface BasToolsManagerService
{

    public void save(ToolsManagerVo tmVo) throws Exception;

    public void delete(Short id) throws Exception;

    public void update(ToolsManagerVo tmVo) throws Exception;

    public Json findAll(ToolsManagerVo tmVo) throws Exception;

    public List findAllCampany() throws Exception; // 查询供应商

    public List findAllStuff() throws Exception; // 查找采购员

}
