package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCustomTypeDao;
import com.syuesoft.bas.service.BasCustomTypeService;
import com.syuesoft.base.vo.BasCustomTypeVo;
import com.syuesoft.model.BasCustomType;

/**
 * 客户类型
 * 
 * @author HeXin
 * 
 */
public class BasCustomTypeServiceImpl extends BaseLogServiceImpl implements
        BasCustomTypeService
{
    public BasCustomTypeDao getDao()
    {
        return dao;
    }

    public void setDao(BasCustomTypeDao dao)
    {
        this.dao = dao;
    }

    private BasCustomTypeDao dao;

    
    @Log(moduleName = "基础资料", opertype = "新增客户类型", content = "基础资料-->新增客户类型")
    public boolean add(BasCustomType basCustomType)
    {
        boolean tag = dao.Add(basCustomType);
        setContent("基础资料-->新增客户类型 ,客户类型名称:" + basCustomType.getCstName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除客户类型", content = "基础资料-->删除客户类型")
    public void delete(BasCustomType basCustomType)throws Exception
    {
    	BasCustomType bct=dao.get(BasCustomType.class, basCustomType.getCstId());
        if(bct!=null){
        	dao.delete(bct);
            setContent("基础资料-->删除客户类型 ,客户类型名称:" + bct.getCstName());
        }
    }

    @Log(moduleName = "基础资料", opertype = "更新客户类型", content = "基础资料-->更新客户类型")
    public boolean update(BasCustomType basCustomType) throws Exception
    {
        boolean tag = dao.Update(basCustomType);
        setContent("基础资料-->更新客户类型 ,客户类型名称:" + basCustomType.getCstName());
        return tag;
    }

    
    public List<BasCustomType> findAll(int page, int rows,int enterprise_id)
    {
        return dao.findAll(page, rows,enterprise_id);
    }

    
    public List<BasCustomType> findByCondition(int page, int rows,
            BasCustomType basCustomType)
    {
        return dao.findByCondition(page, rows, basCustomType);
    }

    
    public List<BasCustomType> getTotle(BasCustomTypeVo basCustomTypeVo)
    {
        return dao.getTotle(basCustomTypeVo);
    }

}
