package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasPartsUnitVo;

/**
 * 基础资料-->配件性质：计量单位Service接口
 * 
 * @author SuMing
 */
public interface BasPartsUnitService
{

    public boolean isExist(BasPartsUnitVo basPartsUnitVo) throws Exception;

    // 基础资料-->配件性质：计量单位 添加
    public void add(BasPartsUnitVo basPartsUnitVo) throws Exception;

    // 基础资料-->配件性质：计量单位 删除
    public void delete(BasPartsUnitVo basPartsUnitVo) throws Exception;

    // 基础资料-->配件性质：计量单位 修改
    public void update(BasPartsUnitVo basPartsUnitVo) throws Exception;

    // 基础资料-->配件性质：计量单位 全部显示
    public java.util.List<BasPartsUnitVo> findAll(BasPartsUnitVo basPartsUnitVo) throws Exception;

    // 基础资料-->配件性质：计量单位 分页
    public java.util.List<BasPartsUnitVo> getAllByPage(
            BasPartsUnitVo basPartsUnitVo) throws Exception;

}
