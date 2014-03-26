package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarPhonetrackerresultVo;
import com.syuesoft.model.BasCarPhonetrackerresult;

/**
 * 基础资料-->车辆性质-->电话跟踪结果Dao接口
 * 
 * @author SuMing
 */
public interface BasCarPhoneTrackerResultDAO extends
        BaseDaoI<BasCarPhonetrackerresult>
{

    // 基础资料-->电话跟踪结果 按ID查找
    public BasCarPhonetrackerresult findById(java.lang.Short id);

    // 基础资料-->电话跟踪结果 全部查询
    public List<BasCarPhonetrackerresult> findAll(final BasCarPhonetrackerresultVo bcpvo);

    // 基础资料-->电话跟踪结果 分页
    public List<BasCarPhonetrackerresult> getAllByPage(final BasCarPhonetrackerresultVo bcpvo);

}