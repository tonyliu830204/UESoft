package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasCarBodysStatusVo;
import com.syuesoft.model.BasCarBodysStatus;

public interface BasCarBodysStatusDAO extends BaseDaoI<BasCarBodysStatus>
{

    public List<BasCarBodysStatus> getAllByPage(final BasCarBodysStatusVo basCarBodysStatusVo) throws Exception;

	public List<BasCarBodysStatus> findAll();
}