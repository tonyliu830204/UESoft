package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.model.FrtResevation;
import com.syuesoft.model.FrtRushToRepair;

/**
 * 预约/保险估价单dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtResevationDao")
public class FrtResevationDaoImpl extends BaseDaoImpl<FrtResevation> implements
        FrtResevationDao
{

    
    public FrtRushToRepair findrushToRepair(String resvId) throws Exception
    {
        // TODO Auto-generated method stub
        FrtResevation fr = super.get(FrtResevation.class, resvId);
        return fr.getFrtRushToRepair();
    }

    
    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception
    {
        // TODO Auto-generated method stub
        FrtResevation fr = super.get(FrtResevation.class, frtRushToRepair
                .getFrtResevation().getResvId());
        fr.setFrtRushToRepair(frtRushToRepair);
    }

    public void updateFrtResevation(FrtResevation fr) throws Exception
    {
        this.getSessionFactory().getCurrentSession().flush();
        this.getSessionFactory().getCurrentSession().clear();
        super.merge(fr);
        this.getSessionFactory().getCurrentSession().clear();
    }

    public void clear() throws Exception
    {
        this.getSessionFactory().getCurrentSession().clear();
    }

    public void flush() throws Exception
    {
        this.getSessionFactory().getCurrentSession().flush();
    }
}
