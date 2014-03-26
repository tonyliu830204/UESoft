package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.Reptype;

public interface ReptypeService
{
    public boolean add(Reptype reptype) throws Exception;

    public void delete(Reptype reptype) throws Exception;

    public void update(Reptype reptype) throws Exception;

    public List<Reptype> findAll(int page, int rows, String sort, String order,int enterpriseId)
            throws Exception;

    public List<Reptype> findByCondition(int page, int rows, Reptype reptype)
            throws Exception;
}
