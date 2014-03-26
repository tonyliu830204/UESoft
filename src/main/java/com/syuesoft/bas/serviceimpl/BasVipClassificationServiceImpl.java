package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipClassificationDao;
import com.syuesoft.bas.service.BasVipClassificationService;
import com.syuesoft.model.BasVipClassification;

/**
 * 会员卡分类
 * 
 * @author HeXin
 * 
 */
public class BasVipClassificationServiceImpl extends BaseLogServiceImpl
        implements BasVipClassificationService
{
    private BasVipClassificationDao dao;

    public BasVipClassificationDao getDao()
    {
        return dao;
    }

    public void setDao(BasVipClassificationDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增会员卡分类", content = "基础资料-->新增会员卡分类")
    public void add(BasVipClassification basVipClassification) throws Exception
    {
        dao.Add(basVipClassification);
        setContent("基础资料-->新增会员卡分类,会员卡分类名称:"
                + basVipClassification.getVipClassificationName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除会员卡分类", content = "基础资料-->删除会员卡分类")
    public void delete(BasVipClassification basVipClassification)
            throws Exception
    {
        dao.Delete(basVipClassification);
        setContent("基础资料-->删除会员卡分类,会员卡分类名称:"
                + basVipClassification.getVipClassificationName());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新会员卡分类", content = "基础资料-->更新会员卡分类")
    public void update(BasVipClassification basVipClassification)
            throws Exception
    {
        dao.Update(basVipClassification);
        setContent("基础资料-->更新会员卡分类,会员卡分类名称:"
                + basVipClassification.getVipClassificationName());
    }

    
    public List<BasVipClassification> findAll(int page, int rows)
            throws Exception
    {

        return dao.findAll(page, rows);
    }

    
    public List<BasVipClassification> findByCondition(int page, int rows,
            BasVipClassification basVipClassification) throws Exception
    {

        return dao.findByCondition(page, rows, basVipClassification);
    }

    
    public List<BasVipClassification> getTotle() throws Exception
    {
        return dao.getTotle();
    }

}
