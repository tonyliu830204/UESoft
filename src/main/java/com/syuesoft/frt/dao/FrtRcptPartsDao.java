package com.syuesoft.frt.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtRcptParts;

public interface FrtRcptPartsDao extends BaseDaoI<FrtRcptParts>
{

    public List<FrtReceptionVo> findAll(String param);

    public List<FrtReceptionVo> findAll(String param, int page, int rows);
}
