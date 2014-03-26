package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasWorkhourSort;

public interface BasWorkhourSortDao extends BaseDaoI<BasWorkhourSort>
{

    public boolean doAdd(BasWorkhourSort basWorkhourSort) throws Exception;

    public void doDelete(BasWorkhourSort basWorkhourSort) throws Exception;

    public boolean doUpdate(BasWorkhourSort basWorkhourSort) throws Exception;

    public List<BasWorkhourSort> doFindAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

}
