package com.syuesoft.frt.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtRcptItem;

public interface FrtRcptItemDao extends BaseDaoI<FrtRcptItem>
{

    public List findAll(String param);

    public List findAll(String param, int page, int rows);
}
