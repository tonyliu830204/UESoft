package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasWorkhourSort;

public interface BasWorkhourSortService
{

    public boolean doAdd(BasWorkhourSort basWorkhourSort) throws Exception;

    public void doDelete(BasWorkhourSort basWorkhourSort) throws Exception;

    public void doUpdate(BasWorkhourSort basWorkhourSort) throws Exception;

    public List<BasWorkhourSort> doFindAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

    public BasWorkhourSort findById(String whSortId) throws Exception;
}
