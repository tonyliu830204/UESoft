package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasPartsUnitVo;
import com.syuesoft.model.BasPartsUnit;

/**
 * 基础资料-->配件性质-->配件单位Dao接口
 * 
 * @author SuMing
 */
public interface BasPartsUnitDAO extends BaseDaoI<BasPartsUnit>
{

    // 基础资料-->配件单位 删除
    public void delete(BasPartsUnit persistentInstance);

    // 基础资料-->配件单位 修改
    public BasPartsUnit merge(BasPartsUnit detachedInstance);

    // 基础资料-->配件单位 按ID查询
    public BasPartsUnit findById(java.lang.Short id);
    public BasPartsUnit findByName(String unitName)throws Exception ;

    // 基础资料-->配件单位 全部查询
    public List<BasPartsUnit> findAll(final BasPartsUnitVo basPartsUnitVo);

    // 基础资料-->配件单位 分页
    public List<BasPartsUnit> getAllByPage(final BasPartsUnitVo basPartsUnitVo);

}