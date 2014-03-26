package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipRechargeRegulation;

public interface BasVipRechargeRegulationDao extends
        BaseDaoI<BasVipRechargeRegulation>
{
    public void add(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception;

    public void delte(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception;

    public void update(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception;

    public List<BasVipRechargeRegulation> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByRecRu(BasVipRechargeRegulation bvr) throws Exception;
}
