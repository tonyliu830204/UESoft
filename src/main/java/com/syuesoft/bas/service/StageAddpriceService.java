package com.syuesoft.bas.service;

import com.syuesoft.base.vo.StageAddpriceVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 剃度加价模块Service
 * 
 * @author SuMing
 */
public interface StageAddpriceService
{

    /** 基础资料 配件性质 梯度信息 增加 */
    public void add(StageAddpriceVo stageAddpriceVo) throws Exception;

    /** 基础资料 配件性质 梯度信息 删除 */
    public void delete(StageAddpriceVo stageAddpriceVo) throws Exception;

    /** 基础资料 配件性质 梯度信息 修改 */
    public void update(StageAddpriceVo stageAddpriceVo) throws Exception;

    /** 基础资料 配件性质 梯度信息 分页 */
    public Json findAll(StageAddpriceVo stageAddpriceVo)
            throws Exception;
    
    public Msg isOld(StageAddpriceVo stageAddpriceVo)throws Exception;
}
