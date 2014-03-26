package com.syuesoft.bas.dao;

import com.syuesoft.model.PartsChangePrice;

public interface PartsChangePriceDao extends BaseDaoI<PartsChangePrice>
{

    public void update(PartsChangePrice pcp);// 更新配件调价信息
}
