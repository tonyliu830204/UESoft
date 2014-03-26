package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipDiscountRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipDiscountRegulation;
import com.syuesoft.model.BasVipLevel;

public interface BasVipDiscountRegulationDao extends
        BaseDaoI<BasVipDiscountRegulation>
{
    public void add(BasVipDiscountRegulation basVipDiscountRegulation)
            throws Exception;

    public void delte(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception;

    public void update(BasVipDiscountRegulation basVipDiscountRegulation)
            throws Exception;

    public List<BasVipDiscountRegulationVO> findAll(int page, int rows,
            String order, String sort,final int enterprise_id) throws Exception;

    public int getTotle(final int enterprise_id) throws Exception;

    public BasVipLevel getVipLevelByID(int id) throws Exception;

    public BasRepairType getRepairTypeByID(int id) throws Exception;

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
