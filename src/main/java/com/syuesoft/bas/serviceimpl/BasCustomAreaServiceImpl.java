package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCustomAreaDao;
import com.syuesoft.bas.service.BasCustomAreaService;
import com.syuesoft.model.BasCustomArea;

public class BasCustomAreaServiceImpl extends BaseLogServiceImpl implements
        BasCustomAreaService
{

    private BasCustomAreaDao dao;

    public BasCustomAreaDao getDao()
    {
        return dao;
    }

    public void setDao(BasCustomAreaDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增地区设定", content = "基础资料-->新增地区设定")
    public boolean add(BasCustomArea basCustomArea) throws Exception
    {
        boolean tag = dao.Add(basCustomArea);
        setContent("基础资料-->新增地区设定 ,地区设定名称:" + basCustomArea.getPareaName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除地区设定", content = "基础资料-->删除地区设定")
    public void delete(BasCustomArea basCustomArea) throws Exception
    {
    	BasCustomArea bcr=dao.get(BasCustomArea.class, basCustomArea.getPareaId());
        if(bcr!=null){
        	dao.delete(bcr);
            setContent("基础资料-->删除地区设定 ,地区设定名称:" + bcr.getPareaName());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "更新地区设定", content = "基础资料-->更新地区设定")
    public boolean update(BasCustomArea basCustomArea) throws Exception
    {
        boolean tag = dao.Update(basCustomArea);
        setContent("基础资料-->更新地区设定 ,地区设定名称:" + basCustomArea.getPareaName());
        return tag;
    }

    
    public List<BasCustomArea> findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception{
        return dao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    public List<BasCustomArea> getTotle(BasCustomArea basCustomArea) throws Exception
    {
        return dao.getTotle(basCustomArea);
    }

}
