package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarPhonetrackerresultVo;

/**
 * 基础资料-->车辆性质：电话跟踪结果Service接口
 * 
 * @author SuMing
 */
public interface BasCarPhoneTrackerResultService
{

    public boolean isExist(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

    // 基础资料-->车辆性质：电话跟踪结果 添加
    public void add(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

    // 基础资料-->车辆性质：电话跟踪结果 删除
    public void delete(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

    // 基础资料-->车辆性质：电话跟踪结果 修改
    public void update(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

    // 基础资料-->车辆性质：电话跟踪结果 全部显示
    public java.util.List<BasCarPhonetrackerresultVo> findAll(BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

    // 基础资料-->车辆性质：电话跟踪结果 分页
    public List<BasCarPhonetrackerresultVo> getAllByPage(
            BasCarPhonetrackerresultVo basCarPhonetrackerresultVo)
            throws Exception;

}
