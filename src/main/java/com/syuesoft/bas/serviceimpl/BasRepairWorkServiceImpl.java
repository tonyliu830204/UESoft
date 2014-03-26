package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRepairWorkDao;
import com.syuesoft.bas.service.BasRepairWorkService;
import com.syuesoft.model.BasRepairWork;

/**
 * 维修工位
 * 
 * @author HeXin
 * 
 */
public class BasRepairWorkServiceImpl extends BaseLogServiceImpl implements
        BasRepairWorkService
{
    private BasRepairWorkDao dao;

    
    @Log(moduleName = "基础资料", opertype = "新增维修工位", content = "基础资料-->新增维修工位")
    public boolean add(BasRepairWork basRepairWork) throws Exception
    {
        boolean tag = dao.Add(basRepairWork);
        setContent("基础资料-->新增维修工位,维修工位名称:" + basRepairWork.getRepwkName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除维修工位", content = "基础资料-->删除维修工位")
    public void delete(BasRepairWork basRepairWork) throws Exception
    {
    	BasRepairWork br=dao.get(BasRepairWork.class, basRepairWork.getRepwkId());
        if(br!=null){
        	dao.delete(br);
            setContent("基础资料-->删除维修工位,维修工位名称:" + br.getRepwkName());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "更新维修工位", content = "基础资料-->更新维修工位")
    public void update(BasRepairWork basRepairWork) throws Exception
    {
    	BasRepairWork br=dao.get(BasRepairWork.class, basRepairWork.getRepwkId());
    	if(br!=null){
    		if(basRepairWork.getRepwkName()!=null&&!basRepairWork.getRepwkName().trim().equals(""))
    			br.setRepwkName(basRepairWork.getRepwkName().trim());
    		if(basRepairWork.getMemo()!=null&&!basRepairWork.getMemo().trim().equals(""))
    			br.setMemo(basRepairWork.getMemo().trim());
    		dao.merge(br);
    		setContent("基础资料-->更新维修工位,维修工位名称:" + br.getRepwkName());
    	}
    }

    public BasRepairWorkDao getDao()
    {
        return dao;
    }

    public void setDao(BasRepairWorkDao dao)
    {
        this.dao = dao;
    }

    
    public List<BasRepairWork> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterpriseId);
    }

    
    public List<BasRepairWork> findByCondition(int page, int rows,
            BasRepairWork BasRepairWork) throws Exception
    {
        return dao.findByCondition(page, rows, BasRepairWork);
    }

}
