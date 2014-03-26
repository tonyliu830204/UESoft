package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipIntegralRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipIntegralRegulation;
import com.syuesoft.model.BasVipLevel;

public interface BasVipIntegralRegulationDao extends
        BaseDaoI<BasVipIntegralRegulation>
{
    public void add(BasVipIntegralRegulation bvr) throws Exception;

    public void delte(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public List<BasVipIntegralRegulationVO> findAll(int page, int rows,
            String order, String sort,final int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public BasVipLevel getVipLevelByID(int id) throws Exception;

    public BasRepairType getRepairTypeByID(int id) throws Exception;

    public List<BasVipLevelVO> findAllVipLevel(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public List<ReptypeVo> findAllRepairType(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public boolean getByLevelAndReptye(BasVipIntegralRegulationVO bvrVO)
            throws Exception;
}
