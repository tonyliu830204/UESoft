package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.VipServiceProjectVo;
import com.syuesoft.model.VipMeal;
import com.syuesoft.util.Json;

public interface VipServiceProjectDao extends BaseDaoI<VipMeal>
{
    // 查询会员服务项目汇总信息
    public Json doFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 新增
    public void doAdd(VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 删除
    public void doDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;


    // 查询会员服务项目明细信息
    public Json doDitailFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception;

    // 删除
    public void doDitailDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 修改
    public void doDitailUpdate(VipServiceProjectVo vipServiceProjectVo)
            throws Exception;

    // 获取套餐名称
    public List<VipMeal> getMealName(int enterprise_id) throws Exception;
    
    //通过套餐编号查询
    public VipMeal getVipMeal(String vipMealId) throws Exception;
}