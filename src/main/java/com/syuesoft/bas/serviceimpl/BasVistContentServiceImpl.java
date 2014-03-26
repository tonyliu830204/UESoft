package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.bas.dao.BasVistContentDao;
import com.syuesoft.bas.service.BasVistContentService;
import com.syuesoft.model.BasVistContent;

public class BasVistContentServiceImpl implements BasVistContentService
{

    private BasVistContentDao dao;

    
    public void add(BasVistContent basVistContent)
    {
        dao.Add(basVistContent);
    }

    
    public void delete(BasVistContent basVistContent)
    {
        dao.Delete(basVistContent);
    }

    
    public void update(BasVistContent basVistContent)
    {
        dao.Update(basVistContent);
    }

    
    public List<BasVistContent> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasVistContent> findByCondition(int page, int rows,
            BasVistContent basVistContent)
    {
        return dao.findByCondition(page, rows, basVistContent);
    }

    public BasVistContentDao getDao()
    {
        return dao;
    }

    public void setDao(BasVistContentDao dao)
    {
        this.dao = dao;
    }

    
    public List<BasVistContent> getTotle()
    {
        return dao.getTotle();
    }

}
