package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCustomGroupDao;
import com.syuesoft.bas.service.BasCustomGroupService;
import com.syuesoft.base.vo.BasCustomGroupVo;
import com.syuesoft.model.BasCustomGroup;

@Service("basCustomGroupService")
public class BasCustomGroupServiceImpl extends BaseLogServiceImpl implements
        BasCustomGroupService
{

    @Autowired
    private BasCustomGroupDao basCustomGroupDao;

    
    @Log(moduleName = "基础资料", opertype = "新增客户分类", content = "基础资料-->新增客户分类")
    public boolean add(BasCustomGroup basCustomGroup) throws Exception
    {
        boolean tag = basCustomGroupDao.Add(basCustomGroup);
        setContent("基础资料-->新增客户分类 ,客户分类名称:" + basCustomGroup.getCstgName());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除客户分类", content = "基础资料-->删除客户分类")
    public void delete(BasCustomGroup basCustomGroup) throws Exception
    {
    	BasCustomGroup bcg=basCustomGroupDao.get(BasCustomGroup.class, basCustomGroup.getCstgId());
        if(bcg!=null){
        	basCustomGroupDao.delete(bcg);
            setContent("基础资料-->删除客户分类 ,客户分类名称:" + bcg.getCstgName());
        }
    }

    
    @Log(moduleName = "基础资料", opertype = "更新客户分类", content = "基础资料-->更新客户分类")
    public boolean update(BasCustomGroup basCustomGroup) throws Exception
    {
        boolean tag = basCustomGroupDao.Update(basCustomGroup);
        setContent("基础资料-->更新客户分类 ,客户分类名称:" + basCustomGroup.getCstgName());
        return tag;
    }

    
    public List<BasCustomGroup> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception
    {
        return basCustomGroupDao.findAll(page, rows, sort, order,enterprise_id);
    }

    
    public List<BasCustomGroup> findByCondition(int page, int rows,
            BasCustomGroup basCustomGroup) throws Exception
    {
        return basCustomGroupDao.findByCondition(page, rows, basCustomGroup);
    }

    
    public List<BasCustomGroup> getTotle(BasCustomGroupVo basCustomGroupVo)
    {
        return basCustomGroupDao.getTotle(basCustomGroupVo.getEnterpriseId());
    }

}
