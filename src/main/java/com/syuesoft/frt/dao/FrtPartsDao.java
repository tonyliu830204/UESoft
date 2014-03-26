package com.syuesoft.frt.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtParts;

public interface FrtPartsDao extends BaseDaoI<FrtParts>
{

    public boolean changePartsId(String partsId, String changedPartsId,int enterprise_Id)
            throws Exception; // 配件档案变更存储过程

    public void batchChangePrice(String sql) throws Exception;; // 批量更新配件调价信息

}
