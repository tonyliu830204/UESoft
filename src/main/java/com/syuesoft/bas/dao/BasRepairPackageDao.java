package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtPartsVo;
import com.syuesoft.model.BasRepairPackage;

public interface BasRepairPackageDao extends BaseDaoI<BasRepairPackage>
{

    public List<FrtPartsVo> findAllSelectionParts(String param); // 待选配件

    public List findAllSelectionItem(String param,BasRepairPackageVo brpVo)
            throws Exception; // 待选项目

}
