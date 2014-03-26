package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarColorVo;
import com.syuesoft.model.BasCarColor;

/**
 * 基础资料-->车辆性质-->车身颜色Dao接口
 * 
 * @author SuMing
 */
public interface BasCarColorDAO extends BaseDaoI<BasCarColor>
{

    // 基础资料-->车身颜色 按ID查询
    public BasCarColor findById(java.lang.Short id) throws Exception;

    // 基础资料-->车身颜色 全部查询
    public List<BasCarColor> findAll(final BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车身颜色 分页
    public List<BasCarColor> getAllByPage(final BasCarColorVo basCarColorVo) throws Exception;

}