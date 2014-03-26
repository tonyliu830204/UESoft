package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasPartsPositionVo;

/**
 * 基础资料-->配件性质：配件部位Service接口
 * 
 * @author SuMing
 */
public interface BasPartsPositionService
{

    public boolean isExist(BasPartsPositionVo basPartsPositionVo)
            throws Exception;

    // 基础资料-->配件性质：配件部位 添加
    public void add(BasPartsPositionVo basPartsPositionVo) throws Exception;

    // 基础资料-->配件性质：配件部位 删除
    public void delete(BasPartsPositionVo basPartsPositionVo) throws Exception;

    // 基础资料-->配件性质：配件部位 修改
    public void update(BasPartsPositionVo basPartsPositionVo) throws Exception;

    // 基础资料-->配件性质：配件部位 全部显示
    public java.util.List<BasPartsPositionVo> findAll(BasPartsPositionVo bpbvo) throws Exception;

    // 基础资料-->配件性质：配件部位 分页
    public java.util.List<BasPartsPositionVo> getAllByPage(
            BasPartsPositionVo basPartsPositionVo) throws Exception;

}
