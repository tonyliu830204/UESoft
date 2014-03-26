package com.syuesoft.bas.service;

import com.syuesoft.st.vo.BasPartsBrandVo;

/**
 * 基础资料-->配件性质：配件品牌Service接口
 * 
 * @author SuMing
 */
public interface BasPartsBrandService
{

    public boolean isExist(BasPartsBrandVo baspartsbrandvo) throws Exception;

    // 基础资料-->配件性质：配件品牌Service 添加
    public void add(BasPartsBrandVo baspartsbrandvo) throws Exception;

    // 基础资料-->配件性质：配件品牌Service 删除
    public void delete(BasPartsBrandVo baspartsbrandvo) throws Exception;

    // 基础资料-->配件性质：配件品牌Service 修改
    public void update(BasPartsBrandVo baspartsbrandvo) throws Exception;

    // 基础资料-->配件性质：配件品牌Service 全部显示
    public java.util.List<BasPartsBrandVo> findAll(BasPartsBrandVo bpbvo) throws Exception;

    // 基础资料-->配件性质：配件品牌Service 分页
    public java.util.List<BasPartsBrandVo> getAllByPage(
            BasPartsBrandVo baspartsbrandvo) throws Exception;

}
