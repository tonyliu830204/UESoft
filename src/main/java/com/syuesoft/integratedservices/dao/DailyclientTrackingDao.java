package com.syuesoft.integratedservices.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fbk.vo.DailyclientTrackingVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FbkDailyclientTracking;

public interface DailyclientTrackingDao extends
        BaseDaoI<FbkDailyclientTracking>
{
    public void doAdd(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception;

    public void doDelete(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception;

    public void doUpdate(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception;

    public Datagrid doFind(DailyclientTrackingVo dailyclientTrackingVo)
            throws Exception;

    public List<FbkDailyclientTracking> doFindByCondition(
            FbkDailyclientTracking fbkDailyclientTracking, int page, int rows)
            throws Exception;

}
