package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.base.vo.BasMountingsTypeInfo;
import com.syuesoft.model.BasPartsType;

public interface BasMountingsTypeInfoDAO extends BaseDaoI<BasPartsType>
{

    public void delete(Short id);

    public void update(BasPartsType pt);

    public Short getMaxId(Short pbrdId);

    public List<BasMountingsTypeInfo> findAll(String param);

    public List<BasMountingsTypeInfo> findAll(String param, int page, int rows);
    /*根据配件型号查询配件型号表*/
	public BasPartsType findPartsByType(String type)throws Exception;
}
