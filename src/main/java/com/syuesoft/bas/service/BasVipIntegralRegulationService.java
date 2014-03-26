package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipIntegralRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipLevel;

public interface BasVipIntegralRegulationService
{
    public void add(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public void delete(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public void update(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public List<BasVipIntegralRegulationVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception;

    public int getTotle(final int enterprise_id) throws Exception;

    public BasVipLevel getVipLevelByID(int id) throws Exception;

    public BasRepairType getRepairTypeByID(int id) throws Exception;

    public List<BasVipLevelVO> findAllVipLevel(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public List<ReptypeVo> findAllRepairType(BasVipIntegralRegulationVO bvrVO) throws Exception;

    public boolean getByLevelAndReptye(BasVipIntegralRegulationVO bvrVO)
            throws Exception;
}
