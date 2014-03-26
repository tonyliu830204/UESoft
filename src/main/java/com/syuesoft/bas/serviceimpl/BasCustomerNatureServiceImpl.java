package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCustomerNatureDao;
import com.syuesoft.bas.service.BasCustomerNatureService;
import com.syuesoft.model.BasCustomNature;

/**
 * 客户性质
 * 
 * @author HeXin
 * 
 */
public class BasCustomerNatureServiceImpl extends BaseLogServiceImpl implements
        BasCustomerNatureService
{
    private BasCustomerNatureDao dao;

    
    @Log(moduleName = "基础资料", opertype = "新增客户性质", content = "基础资料-->新增客户性质")
    public boolean add(BasCustomNature basCustomNature) throws Exception{
        boolean tag = dao.Add(basCustomNature);
        setContent("基础资料-->新增地区设定 ,地区设定名称:" + basCustomNature.getNatureName());
        return tag;
    }

    public BasCustomerNatureDao getDao(){
        return dao;
    }

    public void setDao(BasCustomerNatureDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除客户性质", content = "基础资料-->删除客户性质")
    public void delete(BasCustomNature basCustomNature) throws Exception
    {
    	BasCustomNature bcn=dao.get(BasCustomNature.class, basCustomNature.getNatureId());
        if(bcn!=null){
        	dao.delete(bcn);
            setContent("基础资料-->删除地区设定 ,地区设定名称:" + bcn.getNatureName());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "更新客户性质", content = "基础资料-->更新客户性质")
    public boolean update(BasCustomNature basCustomNature) throws Exception
    {
        boolean tag = dao.Update(basCustomNature);
        setContent("基础资料-->更新地区设定 ,地区设定名称:" + basCustomNature.getNatureName());
        return tag;
    }

    
    public List<BasCustomNature> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterprise_id);
    }

    
    public List<BasCustomNature> getTotle(BasCustomNature basCustomNature) throws Exception
    {
        return dao.getTotle(basCustomNature);
    }

}
