package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasStorehouseVo;

/**
 * 基础资料-->配件性质：仓别分类Service接口
 * 
 * @author SuMing
 */
public interface BasStorehouseService
{

    public boolean isExist(BasStorehouseVo basStorehouseVo) throws Exception;

    public boolean isUsed(BasStorehouseVo basStorehouseVo) throws Exception;

    // 基础资料-->配件性质：仓别分类 添加
    public void add(BasStorehouseVo basStorehouseVo) throws Exception;

    // 基础资料-->配件性质：仓别分类 删除
    public void delete(BasStorehouseVo basStorehouseVo) throws Exception;

    // 基础资料-->配件性质：仓别分类 修改
    public void update(BasStorehouseVo basStorehouseVo) throws Exception;

    // 基础资料-->配件性质：仓别分类 全部显示
    public java.util.List<BasStorehouseVo> findAll(BasStorehouseVo bsvo) throws Exception;

    // 基础资料-->配件性质：仓别分类 分页
    public java.util.List<BasStorehouseVo> getAllByPage(
            BasStorehouseVo basStorehouseVo) throws Exception;

}
