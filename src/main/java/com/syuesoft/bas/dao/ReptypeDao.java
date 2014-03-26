package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.Reptype;

public interface ReptypeDao extends BaseDaoI<Reptype>
{
    public boolean Add(Reptype reptype) throws Exception;

    public void Delete(Reptype reptype) throws Exception;

    public boolean Update(Reptype reptype) throws Exception;

    public List<Reptype> findAll(int page, int rows, String sort, String order, final int enterpriseId)
            throws Exception;

    public List<Reptype> findByCondition(int page, int rows, Reptype reptype)
            throws Exception;
}
