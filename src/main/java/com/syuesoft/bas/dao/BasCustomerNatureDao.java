package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasCustomNature;

public interface BasCustomerNatureDao extends BaseDaoI<BasCustomNature>
{

    public boolean Add(BasCustomNature basCustomNature) throws Exception;

    public void Delete(BasCustomNature basCustomNature) throws Exception;

    public boolean Update(BasCustomNature basCustomNature) throws Exception;

    public List<BasCustomNature> findAll(int page, int rows, String sort,
            String order,final int enterprse_id) throws Exception;

    public List<BasCustomNature> getTotle(BasCustomNature basCustomNature) throws Exception;
}
