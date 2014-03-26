package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarStatusVo;

/**
 * 基础资料-->车辆性质：流失去向Service接口
 * 
 * @author SuMing
 */
public interface BasCarStatusService
{

    public boolean isExist(BasCarStatusVo basCarStatusVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 添加
    public void add(BasCarStatusVo basCarStatusVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 删除
    public void delete(BasCarStatusVo basCarStatusVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 修改
    public void update(BasCarStatusVo basCarStatusVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 全部显示
    public List<BasCarStatusVo> findAll(BasCarStatusVo bcb) throws Exception;

    // 基础资料-->车辆性质：流失去向 分页
    public List<BasCarStatusVo> getAllByPage(BasCarStatusVo basCarStatusVo)
            throws Exception;

}
