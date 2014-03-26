package com.syuesoft.frt.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtRcptItemDao;
import com.syuesoft.model.FrtRcptItem;

/**
 * 前台接车->维修项目dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtRcptItemDao")
public class FrtRcptItemDaoImpl extends BaseDaoImpl<FrtRcptItem> implements
        FrtRcptItemDao
{

    
    public List findAll(String param)
    {
        return this
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(
                        "select new com.syuesoft.frt.vo.FrtReceptionVo(fri.charge, fri.claimsType, fri.repitemId, fri.rcptitemName, fri.rcptitemTime, fri.rcptitemInnerTime, fri.rcptitemAmout, fri.stfId, fri.planStartTime, fri.planComplTime, fri.receptionRemark, fre.receptionId) from FrtRcptItem fri,FrtReception fre where fri.frtReception.receptionId = fre.receptionId order by fri.frtReception.receptionId "
                                + param).list();
    }

    @SuppressWarnings("unchecked")
    
    public List findAll(String param, int page, int rows)
    {
        return this
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(
                        "select new com.syuesoft.frt.vo.FrtReceptionVo(fri.charge, fri.claimsType, fri.repitemId, fri.rcptitemName, fri.rcptitemTime, fri.rcptitemInnerTime, fri.rcptitemAmout, fri.stfId, fri.planStartTime, fri.planComplTime, fri.receptionRemark, fre.receptionId) from FrtRcptItem fri,FrtReception fre where fri.frtReception.receptionId = fre.receptionId order by fri.frtReception.receptionId "
                                + param).setFirstResult((page - 1) * rows)
                .setMaxResults(rows).list();
    }
}
