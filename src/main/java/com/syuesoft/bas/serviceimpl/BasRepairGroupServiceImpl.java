package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasRepairGroupDao;
import com.syuesoft.bas.service.BasRepairGroupService;
import com.syuesoft.model.BasRepairGroup;

/**
 * 车间部门
 * 
 * @author HeXin
 * 
 */
public class BasRepairGroupServiceImpl extends BaseLogServiceImpl implements
        BasRepairGroupService
{
    private BasRepairGroupDao dao;

    public BasRepairGroupDao getDao()
    {
        return dao;
    }

    public void setDao(BasRepairGroupDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增车间部门", content = "基础资料-->新增车间部门")
    public boolean add(BasRepairGroup BasRepairGroup,Integer  nowEnterpriseId) throws Exception
    {
        boolean tag = dao.Add(BasRepairGroup,nowEnterpriseId);
        setContent("基础资料-->新增车间部门,车间部门名称:" + BasRepairGroup.getRepgrpName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除车间部门", content = "基础资料-->删除车间部门")
    public void delete(BasRepairGroup basRepairGroup) throws Exception
    {
    	BasRepairGroup br=dao.get(BasRepairGroup.class, basRepairGroup.getRepgrpId());
    	if(br!=null){
    		 dao.delete(br);
    		 setContent("基础资料-->删除车间部门,车间部门名称:" + br.getRepgrpName());
    	}
    }

    
    @Log(moduleName = "基础资料", opertype = "修改车间部门", content = "基础资料-->修改车间部门")
    public boolean update(BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception
    {
        boolean tag = dao.Update(basRepairGroup, nowEnterpriseId);
        setContent("基础资料-->修改车间部门,车间部门名称:" + basRepairGroup.getRepgrpName());
        return tag;
    }

    
    public List<BasRepairGroup> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception
    {
        return dao.findAll(page, rows, sort, order,enterpriseId);
    }

    
    public List<BasRepairGroup> findByCondition(int page, int rows,
    		BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception
    {
        return dao.findByCondition(page, rows,basRepairGroup, nowEnterpriseId);
    }

}
