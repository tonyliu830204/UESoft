package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasPartsSellVo;

/**
 * 基础资料-->配件性质：配件销售分类Service接口
 * 
 * @author SuMing
 */
public interface BasPartsSellService
{

    public boolean isExist(BasPartsSellVo basPartsSellVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 添加
    public void add(BasPartsSellVo basPartsSellVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 删除
    public void delete(BasPartsSellVo basPartsSellVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 修改
    public void update(BasPartsSellVo basPartsSellVo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 全部显示
    public java.util.List<BasPartsSellVo> findAll(BasPartsSellVo bpsvo) throws Exception;

    // 基础资料-->配件性质：配件销售分类 分页
    public java.util.List<BasPartsSellVo> getAllByPage(
            BasPartsSellVo basPartsSellVo) throws Exception;

}
