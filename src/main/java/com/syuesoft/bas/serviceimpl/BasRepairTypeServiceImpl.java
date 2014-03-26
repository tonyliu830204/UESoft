package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRepairTypeDao;
import com.syuesoft.bas.service.BasRepairTypeService;
import com.syuesoft.model.BasRepairType;

/**
 * 收费性质
 * 
 * @author HeXin
 * 
 */
public class BasRepairTypeServiceImpl extends BaseLogServiceImpl implements
        BasRepairTypeService
{
    private BasRepairTypeDao dao;

    public BasRepairTypeDao getDao()
    {
        return dao;
    }

    public void setDao(BasRepairTypeDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增收费性质", content = "基础资料-->新增收费性质")
    public boolean add(BasRepairType basRepairType) throws Exception
    {
        boolean tag = dao.Add(basRepairType);
        setContent("基础资料-->新增收费性质,收费性质名称:" + basRepairType.getReptypName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除收费性质", content = "基础资料-->删除收费性质")
    public void delete(BasRepairType basRepairType) throws Exception
    {
    	BasRepairType br=dao.get(BasRepairType.class, basRepairType.getReptypId());
    	if(br!=null){
    		 dao.delete(br);
    	     setContent("基础资料-->删除收费性质,收费性质名称:" + br.getReptypName());
    	}
    }

    
    @Log(moduleName = "基础资料", opertype = "更新收费性质", content = "基础资料-->更新收费性质")
    public void update(BasRepairType basRepairType) throws Exception
    {
    	BasRepairType br=dao.get(BasRepairType.class, basRepairType.getReptypId());
    	if(br!=null){
    		if(basRepairType.getReptypName()!=null&&!basRepairType.getReptypName().trim().equals(""))
    			br.setReptypName(basRepairType.getReptypName().trim());
    		if(basRepairType.getMemo()!=null)
    			br.setMemo(basRepairType.getMemo().trim());
    		dao.merge(br);
    		setContent("基础资料-->更新收费性质,收费性质名称:" + basRepairType.getReptypName());
    	}
    }

    
    public List<BasRepairType> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterpriseId);
    }

    
    public List<BasRepairType> findByCondition(int page, int rows,
            BasRepairType BasRepairType) throws Exception
    {
        return dao.findByCondition(page, rows, BasRepairType);
    }

}
