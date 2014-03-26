package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarPartsVo;

/**
 * 基础资料-->车辆性质：车辆部位Service接口
 * 
 * @author SuMing
 */
public interface BasCarPartsService
{

    public boolean isExist(BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆性质：车辆部位 添加
    public void add(BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆性质：车辆部位 删除
    public void delete(BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆性质：车辆部位 修改
    public void update(BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆性质：车辆部位 全部显示
    public List<BasCarPartsVo> findAll(BasCarPartsVo basCarPartsVo) throws Exception;

    // 基础资料-->车辆性质：车辆部位 分页
    public java.util.List<BasCarPartsVo> getAllByPage(
            BasCarPartsVo basCarPartsVo) throws Exception;
}