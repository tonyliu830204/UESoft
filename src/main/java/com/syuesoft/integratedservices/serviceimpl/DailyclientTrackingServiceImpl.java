package com.syuesoft.integratedservices.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.fbk.vo.DailyclientTrackingVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.DailyclientTrackingDao;
import com.syuesoft.integratedservices.service.DailyclientTrackingService;
import com.syuesoft.model.FbkDailyclientTracking;

@Service("dailyclientTrackingService")
public class DailyclientTrackingServiceImpl implements
        DailyclientTrackingService
{
    @Autowired
    private DailyclientTrackingDao dailyclientTrackingDao;

    public DailyclientTrackingDao getDailyclientTrackingDao()
    {
        return dailyclientTrackingDao;
    }

    public void setDailyclientTrackingDao(
            DailyclientTrackingDao dailyclientTrackingDao)
    {
        this.dailyclientTrackingDao = dailyclientTrackingDao;
    }

    @Log(moduleName = "客户服务", content = "新增客户跟踪信息", opertype = "新增")
    public void doAdd(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        dailyclientTrackingDao.doAdd(fbkDailyclientTracking);
    }

    @Log(moduleName = "客户服务", content = "删除客户跟踪信息", opertype = "删除")
    public void doDelete(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        dailyclientTrackingDao.doDelete(fbkDailyclientTracking);
    }

    
    public Datagrid doFind(DailyclientTrackingVo dailyclientTrackingVo)
            throws Exception
    {
        return dailyclientTrackingDao.doFind(dailyclientTrackingVo);
    }

    @Log(moduleName = "客户服务", content = "修改客户跟踪信息", opertype = "修改")
    public void doUpdate(FbkDailyclientTracking fbkDailyclientTracking)
            throws Exception
    {
        dailyclientTrackingDao.doUpdate(fbkDailyclientTracking);
    }

    
    public List<FbkDailyclientTracking> doFindByCondition(
            FbkDailyclientTracking fbkDailyclientTracking, int page, int rows)
            throws Exception
    {
        return dailyclientTrackingDao.doFindByCondition(fbkDailyclientTracking,
                page, rows);
    }

}
