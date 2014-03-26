package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarColorVo;

/**
 * 基础资料-->车辆性质：车辆颜色Service接口
 * 
 * @author SuMing
 */
public interface BasCarColorService
{

    public boolean isExist(BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车辆性质：车辆颜色 添加
    public void add(BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车辆性质：车辆颜色 删除
    public void delete(BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车辆性质：车辆颜色 修改
    public void update(BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车辆性质：车辆颜色 全部显示
    public List<BasCarColorVo> findAll(BasCarColorVo basCarColorVo) throws Exception;

    // 基础资料-->车辆性质：车辆颜色 分页
    public List<BasCarColorVo> getAllByPage(BasCarColorVo basCarColorVo)
            throws Exception;
}