package com.syuesoft.bas.service;

import com.syuesoft.base.vo.FrtCarAllowCarTypeVo;
import com.syuesoft.fin.vo.Datagrid;

public interface FrtCarAllowCarTypeService
{
    // 判断是否重复
    public Boolean isExist(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception;

    // 基础资料-->车辆性质：准驾车型 添加
    public void add(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo) throws Exception;

    // 基础资料-->车辆性质：准驾车型 删除
    public void delete(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception;

    // 基础资料-->车辆性质：准驾车型 修改
    public void update(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception;

    // 基础资料-->车辆性质：准驾车型 分页
    public Datagrid findAll(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception;
}
