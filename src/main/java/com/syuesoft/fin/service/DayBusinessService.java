package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.fin.vo.DayBusinessVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface DayBusinessService
{

    /** 财务模块 日收款查询 父节点（结算日期信息加载） */
    public DatagridAnalyze loadPreclrDate(DayBusinessVo dayBusinessVo)
            throws Exception;

    /** 财务模块 日收款查询 二级节点（收款分类信息加载 */
    public List<DayBusinessVo> loadPaidResource(DayBusinessVo dayBusinessVo)
            throws Exception;

    /** 财务模块 日收款查询 三级节点信息加载（收款分类信息加载） */
    public List<DayBusinessVo> loadDayPaid(DayBusinessVo dayBusinessVo)
            throws Exception;

}
