package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCustomTypeVo;
import com.syuesoft.model.BasCustomType;

public interface BasCustomTypeDao extends BaseDaoI<BasCustomType>
{

    public boolean Add(BasCustomType basCustomType);

    public void Delete(BasCustomType basCustomType);

    public boolean Update(BasCustomType basCustomType);

    public List<BasCustomType> findAll(int page, int rows,int enterprise_id);

    public List<BasCustomType> getTotle(BasCustomTypeVo basCustomTypeVo);

    public List<BasCustomType> findByCondition(int page, int rows,
            BasCustomType basCustomType);

}
