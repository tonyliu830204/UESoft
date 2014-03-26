package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasPartsStateVo;

public interface BasPartsStateService
{

    public boolean isExist(BasPartsStateVo basPartsStateVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 添加
    public void add(BasPartsStateVo basPartsStateVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 删除
    public void delete(BasPartsStateVo basPartsStateVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 修改
    public void update(BasPartsStateVo basPartsStateVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 全部显示
    public java.util.List<BasPartsStateVo> findAll(BasPartsStateVo basPartsStateVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 分页
    public java.util.List<BasPartsStateVo> getAllByPage(
            BasPartsStateVo basPartsStateVo) throws Exception;
}
