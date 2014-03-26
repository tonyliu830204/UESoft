package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasCarSellersVo;

/**
 * 基础资料-->车辆性质：其他销售店Service接口
 * 
 * @author SuMing
 */
public interface BasCarSellersService
{

    public boolean isExist(BasCarSellersVo basCarSellersVo) throws Exception;

    // 基础资料-->车辆性质：其他销售店 添加
    public void add(BasCarSellersVo basCarSellersVo) throws Exception;

    // 基础资料-->车辆性质：其他销售店 删除
    public void delete(BasCarSellersVo basCarSellersVo) throws Exception;

    // 基础资料-->车辆性质：其他销售店 添加
    public void update(BasCarSellersVo basCarSellersVo) throws Exception;

    // 基础资料-->车辆性质：其他销售店 添加
    public List<BasCarSellersVo> findAll(BasCarSellersVo basCarSellersVo) throws Exception;

    // 基础资料-->车辆性质：其他销售店 添加
    public List<BasCarSellersVo> getAllByPage(BasCarSellersVo basCarSellersVo)
            throws Exception;
}
