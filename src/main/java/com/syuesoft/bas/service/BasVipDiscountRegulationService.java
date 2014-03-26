package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipDiscountRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;

public interface BasVipDiscountRegulationService
{
    public void add(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public void delete(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public void update(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public List<BasVipDiscountRegulationVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public List<BasVipLevelVO> findAllVipLevel(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public List<ReptypeVo> findAllRepairType(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public boolean getByLevelAndReptype(
            BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;
}
