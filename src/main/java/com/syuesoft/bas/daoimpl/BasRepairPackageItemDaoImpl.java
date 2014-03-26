package com.syuesoft.bas.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasRepairPackageItemDao;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.ItemVo;
import com.syuesoft.model.BasRepairPackageItem;

/**
 * 维修套餐->维修项目
 * 
 * @author Liujian
 * 
 */
@Repository("basRepairPackageItemDao")
public class BasRepairPackageItemDaoImpl extends
        BaseDaoImpl<BasRepairPackageItem> implements BasRepairPackageItemDao
{

}
