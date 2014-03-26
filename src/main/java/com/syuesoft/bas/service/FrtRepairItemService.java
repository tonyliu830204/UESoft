package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface FrtRepairItemService
{
    public List<ComboBoxJson> getBasWorkhourSort(String q,Integer id) throws Exception;// 获取工时分类用于combox

    public Datagrid findAll(FrtRepairItemVo friVo) throws Exception;// 查找标准项目工时

    public Msg save(FrtRepairItemVo friVo) throws Exception;// 增加标准项目工时

    public Msg update(FrtRepairItemVo friVo) throws Exception;// 修改标准项目工时

    public Msg delete(FrtRepairItemVo friVo) throws Exception;// 修改标准项目工时
}
