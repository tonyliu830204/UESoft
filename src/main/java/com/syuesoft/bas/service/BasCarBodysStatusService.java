package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarBodysStatusVo;

/**
 * 基础资料-->车辆性质：车身状态Service接口
 * 
 * @author SuMing
 */
public interface BasCarBodysStatusService
{

    public boolean isExist(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception;

    // 基础资料-->车辆性质：车身状态 添加
    public void add(BasCarBodysStatusVo basCarBodysStatusVo) throws Exception;

    // 基础资料-->车辆性质：车身状态 删除
    public void delete(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception;

    // 基础资料-->车辆性质：车身状态 修改
    public void update(BasCarBodysStatusVo basCarBodysStatusVo)
            throws Exception;

    // 基础资料-->车辆性质：车身状态 全部显示
    public List<BasCarBodysStatusVo> findAll(BasCarBodysStatusVo basCarBodysStatusVo) throws Exception;

    // 基础资料-->车辆性质：车身状态 分页
    public List<BasCarBodysStatusVo> getAllByPage(
            BasCarBodysStatusVo basCarBodysStatusVo) throws Exception;

}
