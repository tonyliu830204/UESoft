package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.bas.dao.BasVipServiceDao;
import com.syuesoft.bas.service.BasVipServiceService;
import com.syuesoft.model.BasVipService;

public class BasVipServiceServiceImpl implements BasVipServiceService
{
    private BasVipServiceDao dao;

    public BasVipServiceDao getDao()
    {
        return dao;
    }

    public void setDao(BasVipServiceDao dao)
    {
        this.dao = dao;
    }

    
    public void add(BasVipService basVipService) throws Exception
    {
        dao.Add(basVipService);
    }

    
    public void delete(BasVipService basVipService) throws Exception
    {
        dao.Delete(basVipService);
    }

    
    public void update(BasVipService basVipService) throws Exception
    {
        dao.Update(basVipService);
    }

    
    public List<BasVipService> findAll(int page, int rows) throws Exception
    {

        return dao.findAll(page, rows);
    }

    
    public List<BasVipService> getTotle() throws Exception
    {
        return dao.getTotle();
    }

}
