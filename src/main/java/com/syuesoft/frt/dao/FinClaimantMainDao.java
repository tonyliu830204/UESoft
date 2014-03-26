package com.syuesoft.frt.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FinClaimantMain;

public interface FinClaimantMainDao extends BaseDaoI<FinClaimantMain>
{

    public void flushCache() throws Exception;

    public List isExist(String receptionId) throws Exception;
    
    public Integer executeSQL(String sql)throws Exception;
}
