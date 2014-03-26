package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.base.vo.BasCarStyleVo;
import com.syuesoft.util.ComboBoxJson;

public interface BasCarStyleService
{

    public boolean isExist(BasCarStyleVo basCarStyleVo) throws Exception;

    public boolean isUsed(BasCarStyleVo basCarStyleVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 添加
    public void add(BasCarStyleVo basCarStyleVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 删除
    public void delete(BasCarStyleVo basCarStyleVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 修改
    public void update(BasCarStyleVo basCarStyleVo) throws Exception;

    // 基础资料-->车辆性质：流失去向 全部显示
    public List<BasCarStyleVo> findAll(BasCarStyleVo basCarStyleVo) throws Exception;

    // findAllCarType
    public List<ComboBoxJson> findAllCarType(BasCarStyleVo basCarStyleVo)
            throws Exception;

    // 基础资料-->车辆性质：流失去向 分页
    public List<BasCarStyleVo> getAllByPage(BasCarStyleVo basCarStyleVo)
            throws Exception;
}
