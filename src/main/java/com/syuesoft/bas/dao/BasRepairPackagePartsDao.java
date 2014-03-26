package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.model.BasRepairPackageParts;

public interface BasRepairPackagePartsDao extends
        BaseDaoI<BasRepairPackageParts>
{

    public List<PartsVo> getAllBasRepairPackageParts(BasRepairPackageVo brpVo); // 查询已选配件

    public int getHqlCount(BasRepairPackageVo brpVo) throws Exception;
}
