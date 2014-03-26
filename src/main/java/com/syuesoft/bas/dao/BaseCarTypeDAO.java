package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarTypeInfoVo;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.FrtParts;

public interface BaseCarTypeDAO extends BaseDaoI<BasCarType>
{

    public void delete(Short id);

    public void update(BasCarType ct);

    public Short getMaxId(Short cbrdId);

    public List<BasCarTypeInfoVo> findAll(String param);

    public List<BasCarTypeInfoVo> findAll(String param, int page, int rows);
  
}