package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasCarTypeInfoVo;
import com.syuesoft.model.BasCarBrand;
import com.syuesoft.util.Json;

public interface BasCarTypeInfoService
{

    public boolean isExist(BasCarTypeInfoVo bctiVo) throws Exception;

    public void save(BasCarTypeInfoVo bctiVo) throws Exception;

    public void delete(Short id) throws Exception;

    public void update(BasCarTypeInfoVo bctiVo) throws Exception;

    public Json findAll(BasCarTypeInfoVo bctiVo) throws Exception;

    public List findAllCarBrand(BasCarTypeInfoVo bctiVo) throws Exception; // 查询车辆品牌

    public List getCarTypeInfo(BasCarTypeInfoVo bctiVo) throws Exception;// 获取具体车辆品牌信息

}
