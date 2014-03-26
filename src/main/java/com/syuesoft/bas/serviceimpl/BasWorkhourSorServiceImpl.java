package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasWorkhourSortDao;
import com.syuesoft.bas.service.BasWorkhourSortService;
import com.syuesoft.model.BasWorkhourSort;

/**
 * 工时
 * 
 * @author HeXin
 * 
 */
@Service("basWorkhourSorService")
public class BasWorkhourSorServiceImpl extends BaseLogServiceImpl implements
        BasWorkhourSortService
{

    @Autowired
    private BasWorkhourSortDao basWorkhourSorDao;

    public BasWorkhourSortDao getBasWorkhourSorDao()
    {
        return basWorkhourSorDao;
    }

    public void setBasWorkhourSorDao(BasWorkhourSortDao basWorkhourSorDao)
    {
        this.basWorkhourSorDao = basWorkhourSorDao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增会员工时", content = "基础资料-->新增会员工时")
    public boolean doAdd(BasWorkhourSort basWorkhourSort) throws Exception
    {
        boolean tag = basWorkhourSorDao.doAdd(basWorkhourSort);
       setContent("基础资料-->新增会员工时,会员工时名称:" + basWorkhourSort.getWhSortName());
        return tag;
    }

    @Log(moduleName = "基础资料", opertype = "删除会员工时", content = "基础资料-->删除会员工时")
    public void doDelete(BasWorkhourSort basWorkhourSort) throws Exception
    {
    	BasWorkhourSort nw=basWorkhourSorDao.get(BasWorkhourSort.class, basWorkhourSort.getWhSortId());
        if(nw!=null){
        	basWorkhourSorDao.delete(nw);
            setContent("基础资料-->删除会员工时,会员工时名称:" + nw.getWhSortName());
        }
    }

    
    public List<BasWorkhourSort> doFindAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception
    {
        return basWorkhourSorDao.doFindAll(page, rows, sort, order,enterpriseId);
    }

    
    @Log(moduleName = "基础资料", opertype = "更新会员工时", content = "基础资料-->更新会员工时")
    public void doUpdate(BasWorkhourSort basWorkhourSort) throws Exception
    {
    	BasWorkhourSort nw=basWorkhourSorDao.get(BasWorkhourSort.class, basWorkhourSort.getWhSortId());
        if(nw!=null){
        	if(basWorkhourSort.getWhSortName()!=null&&!basWorkhourSort.getWhSortName().trim().equals(""))
        		nw.setWhSortName(basWorkhourSort.getWhSortName().trim());
        	if(basWorkhourSort.getWhMemo()!=null)
        		nw.setWhMemo(basWorkhourSort.getWhMemo().trim());
        	basWorkhourSorDao.merge(nw);
        	setContent("基础资料-->更新会员工时,会员工时名称:" + nw.getWhSortName());
        }
    }

    
    public BasWorkhourSort findById(String whSortId) throws Exception{
    	return basWorkhourSorDao.get("from BasWorkhourSort bws where bws.whSortId="+whSortId);
    }
    
}
