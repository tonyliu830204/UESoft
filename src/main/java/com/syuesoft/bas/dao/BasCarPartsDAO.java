package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarPartsVo;
import com.syuesoft.model.BasCarParts;

/**
 * 基础资料-->车辆性质-->车辆部位Dao接口
 * 
 * @author SuMing
 */
public interface BasCarPartsDAO extends BaseDaoI<BasCarParts>
{

    // 基础资料-->车辆部位 按ID查询
    public BasCarParts findById(java.lang.Short id) throws Exception;

    // 基础资料-->车辆部位 全部查询
    public List<BasCarParts> findAll(final BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆部位 分页
    public List<BasCarParts> getAllByPage(final BasCarPartsVo basCarPartsVo) throws Exception;

}