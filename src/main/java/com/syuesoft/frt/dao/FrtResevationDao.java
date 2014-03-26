package com.syuesoft.frt.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtResevation;
import com.syuesoft.model.FrtRushToRepair;

public interface FrtResevationDao extends BaseDaoI<FrtResevation>
{
    public FrtRushToRepair findrushToRepair(String resvId) throws Exception;// 查找抢修信息

    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception;// 更新抢修信息

    public void updateFrtResevation(FrtResevation fr) throws Exception;// 更新预约信息

    public void clear() throws Exception;

    public void flush() throws Exception;

}
