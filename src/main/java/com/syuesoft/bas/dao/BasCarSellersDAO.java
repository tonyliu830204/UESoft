package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarSellersVo;
import com.syuesoft.model.BasCarSellers;

/**
 * 基础资料-->车辆性质-->其他销售店Dao接口
 * @author SuMing
 */
public interface BasCarSellersDAO extends BaseDaoI<BasCarSellers>
{

    // 基础资料-->其他销售点 按ID查询
    public BasCarSellers findById(java.lang.Short id);

    // 基础资料-->其他销售点 全部查询
    public List<BasCarSellers> findAll(final BasCarSellersVo basCarSellersVo);

    // 基础资料-->其他销售点 分页
    public List<BasCarSellers> getAllByPage(final BasCarSellersVo basCarSellersVo);

}