package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasUsedPhrasesDao;
import com.syuesoft.bas.service.BasUsedPhrasesService;
import com.syuesoft.model.BasUsedPhrases;
import com.syuesoft.util.Json;

/**
 * 常用短语
 * 
 * @author HeXin
 * 
 */
public class BasUsedPhrasesServiceImpl extends BaseLogServiceImpl implements
        BasUsedPhrasesService
{
    private BasUsedPhrasesDao dao;

    public BasUsedPhrasesDao getDao()
    {
        return dao;
    }

    public void setDao(BasUsedPhrasesDao dao)
    {
        this.dao = dao;
    }

    @Log(moduleName = "基础资料", opertype = "新增常用短语", content = "基础资料-->新增常用短语")
    public boolean add(BasUsedPhrases basUsedPhrases) throws Exception
    {
        boolean tag = dao.Add(basUsedPhrases);
        setContent("基础资料-->新增常用短语,常用短语名称:" + basUsedPhrases.getPhrassesName());
        return tag;
    }

    @Log(moduleName = "基础资料", opertype = "删除常用短语", content = "基础资料-->删除常用短语")
    public void delete(BasUsedPhrases basUsedPhrases) throws Exception
    {
    	BasUsedPhrases bud=dao.get(BasUsedPhrases.class,basUsedPhrases.getPhrasesId());
        dao.delete(bud);
        setContent("基础资料-->删除常用短语,常用短语名称:" + bud.getPhrassesName());
    }

    @Log(moduleName = "基础资料", opertype = "更新常用短语", content = "基础资料-->更新常用短语")
    public boolean update(BasUsedPhrases basUsedPhrases) throws Exception
    {
        boolean tag = dao.Update(basUsedPhrases);
        setContent("基础资料-->更新常用短语,常用短语名称:" + basUsedPhrases.getPhrassesName());
        return tag;
    }

    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception{
        return dao.findAll(page, rows, sort, order,enterprise_id);
    }

    
    public List<BasUsedPhrases> getTotle(BasUsedPhrases basUsedPhrases) throws Exception
    {
        return dao.getTotle(basUsedPhrases);
    }

    
    public Json findByCondition(BasUsedPhrases basUsedPhrases,
            int page, int rows) throws Exception
    {
        return dao.findByCondition(basUsedPhrases, page, rows);
    }

}
