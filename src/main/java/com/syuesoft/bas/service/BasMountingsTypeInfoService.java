package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasMountingsTypeInfo;
import com.syuesoft.util.Json;

public interface BasMountingsTypeInfoService
{

    public boolean isExist(BasMountingsTypeInfo mti) throws Exception;

    public void save(BasMountingsTypeInfo mti) throws Exception;

    public void delete(Short id) throws Exception;

    public void update(BasMountingsTypeInfo mti) throws Exception;

    public Json findAll(BasMountingsTypeInfo mti) throws Exception;

    public List findAllPartsBrand(BasMountingsTypeInfo mti) throws Exception; // 查询配件品牌

}
