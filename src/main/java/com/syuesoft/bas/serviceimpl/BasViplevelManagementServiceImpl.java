package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasViplevelManagementDao;
import com.syuesoft.bas.service.BasViplevelManagementService;
import com.syuesoft.model.BasViplevelManagement;

/**
 * 会员等级管理
 * 
 * @author HeXin
 * 
 */
public class BasViplevelManagementServiceImpl extends BaseLogServiceImpl
        implements BasViplevelManagementService
{
    private BasViplevelManagementDao dao;

    public BasViplevelManagementDao getDao()
    {
        return dao;
    }

    public void setDao(BasViplevelManagementDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增会员等级管理", content = "基础资料-->新增会员等级管理")
    public void add(BasViplevelManagement basViplevelManagement)
    {
        dao.Add(basViplevelManagement);
        setContent("基础资料-->新增会员等级管理,会员等级管理名称:"
                + basViplevelManagement.getViptypeName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除会员等级管理", content = "基础资料-->删除会员等级管理")
    public void delete(BasViplevelManagement basViplevelManagement)
    {
        dao.Delete(basViplevelManagement);
        setContent("基础资料-->删除会员等级管理,会员等级管理名称:"
                + basViplevelManagement.getViptypeName());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新会员等级管理", content = "基础资料-->更新会员等级管理")
    public void update(BasViplevelManagement basViplevelManagement)
    {
        dao.Update(basViplevelManagement);
        setContent("基础资料-->更新会员等级管理,会员等级管理名称:"
                + basViplevelManagement.getViptypeName());
    }

    
    public List<BasViplevelManagement> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasViplevelManagement> findByCondition(int page, int rows,
            BasViplevelManagement basViplevelManagement)
    {
        return dao.findByCondition(page, rows, basViplevelManagement);
    }

    
    public List<BasViplevelManagement> getTotle()
    {
        // TODO Auto-generated method stub
        return dao.getTotle();
    }

}
