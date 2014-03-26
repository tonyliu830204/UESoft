package com.syuesoft.bas.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasRepairPackagePartsDao;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.model.BasRepairPackageParts;

/**
 * 维修套餐->维修配件
 * 
 * @author Liujian
 * 
 */
@Repository("basRepairPackagePartsDao")
public class BasRepairPackagePartsDaoImpl extends
        BaseDaoImpl<BasRepairPackageParts> implements BasRepairPackagePartsDao
{

    /**
     * 查询已选配件
     */
    @SuppressWarnings("unchecked")
    
    public List<PartsVo> getAllBasRepairPackageParts(BasRepairPackageVo brpVo)
    {
        return this
                .getHibernateTemplate()
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(
                        "select new com.syuesoft.base.vo.PartsVo(CONCAT(CONCAT(brpp.partsId, ','), bsh.storeId), brpp.partsId, brpp.partsName, brpp.punitId, bpu.punitName, brpp.partsNum, brpp.partsRepairPrice, brpp.partsAmount, brpp.storeId, bsh.storeName) from BasRepairPackageParts brpp, BasPartsUnit bpu, BasStorehouse bsh where brpp.punitId = bpu.punitId and brpp.storeId = bsh.storeId and brpp.basRepairPackage.rpId = "
                                + brpVo.getRpId()).setFirstResult(
                        (brpVo.getPage() - 1) * brpVo.getRows()).setMaxResults(
                        brpVo.getRows()).list();
    }

    
    public int getHqlCount(BasRepairPackageVo brpVo) throws Exception
    {
        String hql = "select new com.syuesoft.base.vo.PartsVo(CONCAT(CONCAT(brpp.partsId, ','), bsh.storeId), brpp.partsId, brpp.partsName, brpp.punitId, bpu.punitName, brpp.partsNum, brpp.partsRepairPrice, brpp.partsAmount, brpp.storeId, bsh.storeName) from BasRepairPackageParts brpp, BasPartsUnit bpu, BasStorehouse bsh where brpp.punitId = bpu.punitId and brpp.storeId = bsh.storeId and brpp.basRepairPackage.rpId = "
                + brpVo.getRpId();
        return getCount(hql);
    }

}
