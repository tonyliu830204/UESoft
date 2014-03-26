package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarStatusVo;
import com.syuesoft.model.BasCarStatus;

/**
 * 基础资料-->车辆性质-->车辆流失Dao接口
 * 
 * @author SuMing
 */
public interface BasCarStatusDAO extends BaseDaoI<BasCarStatus>
{

    // 基础资料-->车辆流失 按ID查询
    public BasCarStatus findById(java.lang.Short id);

    // 基础资料-->车辆流失 全不查询
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarStatusVo basCarStatusVo);

    // 基础资料-->车辆流失 分页
    public List<BasCarStatus> getAllByPage(final BasCarStatusVo basCarStatusVo);

}