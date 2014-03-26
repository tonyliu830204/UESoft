package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCountassociationTypeDao;
import com.syuesoft.bas.service.BasCountassociationTypeService;
import com.syuesoft.model.BasCountassociationType;
import com.syuesoft.util.Json;

public class BasCountassociationTypeServiceImpl extends BaseLogServiceImpl
        implements BasCountassociationTypeService
{

    private BasCountassociationTypeDao dao;

    
    @Log(moduleName = "基础资料", opertype = "新增技协费分类", content = "基础资料-->新增技协费分类")
    public boolean add(BasCountassociationType basCountassociationType)
            throws Exception
    {
        boolean tag = dao.Add(basCountassociationType);
        setContent("基础资料-->新增技协费分类 ,技协费分类名称:"
                + basCountassociationType.getTypeName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除技协费分类", content = "基础资料-->删除技协费分类")
    public void delete(BasCountassociationType basCountassociationType)
            throws Exception
    {
    	BasCountassociationType bct=dao.get(BasCountassociationType.class, basCountassociationType.getTypeId());
        if(bct!=null){
        	dao.delete(bct);
            setContent("基础资料-->删除技协费分类 ,技协费分类名称:"+ bct.getTypeName());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "更新技协费分类", content = "基础资料-->更新技协费分类")
    public boolean update(BasCountassociationType basCountassociationType)
            throws Exception
    {
        boolean tag = dao.Update(basCountassociationType);
        setContent("基础资料-->更新技协费分类 ,技协费分类名称:"
                + basCountassociationType.getTypeName());
        return tag;
    }

    
    public Json findAll(int page, int rows,
            String sort, String order,int enterprise_id) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterprise_id);
    }

    
    public List<BasCountassociationType> findByCondition(int page, int rows,
            BasCountassociationType basCountassociationType) throws Exception
    {
        return dao.findByCondition(page, rows, basCountassociationType);
    }

    public BasCountassociationTypeDao getDao()
    {
        return dao;
    }

    public void setDao(BasCountassociationTypeDao dao)
    {
        this.dao = dao;
    }

    
    public List<BasCountassociationType> getTotle() throws Exception
    {
        return dao.getTotle();
    }

}
