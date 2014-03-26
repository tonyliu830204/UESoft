package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasPartsPositionVo;
import com.syuesoft.model.BasPartsPosition;

/**
 * 基础资料-->配件性质-->配件部位Dao接口
 * 
 * @author SuMing
 */
public interface BasPartsPositionDAO extends BaseDaoI<BasPartsPosition>
{

    // 配件库存量查询模块 配件部位信息加载
    @SuppressWarnings("unchecked")
    public List pnc_searchPartsPosition() throws Exception;

    // 基础资料-->配件部位 删除
    public void delete(BasPartsPosition persistentInstance);

    // 基础资料-->配件部位 修改
    public BasPartsPosition merge(BasPartsPosition detachedInstance);

    // 基础资料-->配件部位 按ID查询
    public BasPartsPosition findById(java.lang.Short id);

    // 基础资料-->配件部位 全查
    public List<BasPartsPosition> findAll(final BasPartsPositionVo basPartsPositionVo);

    // 基础资料-->配件部位 分页
    public List<BasPartsPosition> getAllByPage(final BasPartsPositionVo basPartsPositionVo);
}