package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.VipServiceProjectVo;
import com.syuesoft.model.VipMeal;
import com.syuesoft.util.Json;

public interface VipServiceProjectService
{
    // 查询会员服务项目汇总信息
    public Json doFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 新增
    public void doAdd(VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 删除
    public void doDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 修改
    public void doUpdate(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 查询会员服务项目明细信息
    public Json doDetailFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 新增
    public void doDetailAdd(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 删除
    public void doDetailDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 修改
    public void doDetailUpdate(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 获取套餐名称
    public List<VipMeal> getMealName(int enterprise_id) throws Exception;
}
