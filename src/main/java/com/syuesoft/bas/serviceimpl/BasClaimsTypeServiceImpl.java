package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasClaimsTypeDao;
import com.syuesoft.bas.service.BasClaimsTypeService;
import com.syuesoft.model.BasClaimsType;

public class BasClaimsTypeServiceImpl extends BaseLogServiceImpl implements
        BasClaimsTypeService
{
    private BasClaimsTypeDao dao;

    public BasClaimsTypeDao getDao()
    {
        return dao;
    }

    public void setDao(BasClaimsTypeDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增索赔性质", content = "基础资料-->新增索赔性质")
    public boolean add(BasClaimsType basClaimsType) throws Exception
    {
        boolean tag = dao.Add(basClaimsType);
        setContent("基础资料-->新增索赔性质 ,索赔性质名称:" + basClaimsType.getClaimsName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除索赔性质", content = "基础资料-->删除索赔性质")
    public void delete(BasClaimsType basClaimsType) throws Exception
    {
    	BasClaimsType bct=dao.get(BasClaimsType.class, basClaimsType.getClaimsId());
    	if(bct!=null){
    		dao.delete(bct);
            setContent("基础资料-->删除索赔性质 ,索赔性质名称:" + bct.getClaimsName());
    	}
    }

    
    @Log(moduleName = "基础资料", opertype = "修改索赔性质", content = "基础资料-->修改索赔性质")
    public void update(BasClaimsType basClaimsType) throws Exception
    {
    	if(basClaimsType!=null){
    		dao.Update(basClaimsType);
            setContent("基础资料-->修改索赔性质 ,索赔性质名称:" + basClaimsType.getClaimsName());
    	}
    }

    
    public List<BasClaimsType> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterpriseId);
    }

    
    public List<BasClaimsType> findByCondition(int page, int rows,
            BasClaimsType BasClaimsType) throws Exception
    {
        return dao.findByCondition(page, rows, BasClaimsType);
    }

}
