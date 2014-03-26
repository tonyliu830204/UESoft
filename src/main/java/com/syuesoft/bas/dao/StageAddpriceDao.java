package com.syuesoft.bas.dao;

import com.syuesoft.base.vo.StageAddpriceVo;
import com.syuesoft.model.StageAddprice;
import com.syuesoft.util.Json;

/**
 * 剃度加价模块Dao
 * 
 * @author SuMing
 */
public interface StageAddpriceDao extends BaseDaoI<StageAddprice>
{

    /** 基础资料 配件性质 梯度信息 分页 */
    public Json findAll(final StageAddpriceVo stageAddpriceVo) throws Exception;
}
