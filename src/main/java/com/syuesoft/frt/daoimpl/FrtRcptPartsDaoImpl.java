package com.syuesoft.frt.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtRcptPartsDao;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtRcptParts;

/**
 * 前台接车->维修配件dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtRcptPartsDao")
public class FrtRcptPartsDaoImpl extends BaseDaoImpl<FrtRcptParts> implements
        FrtRcptPartsDao
{

    @SuppressWarnings("unchecked")
    
    public List<FrtReceptionVo> findAll(String param)
    {
        return this
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(
                        "select new com.syuesoft.frt.vo.FrtReceptionVo(frp.rcptpartsIndex, frp.partsId, frp.partsName, frp.rcptpartsCnt, frp.punitId, frp.rcptpartsPrice, frp.rcptpartsAmount, frp.charge, frp.claimsType, frp.partsRemark, fre.receptionId) from FrtRcptParts frp,FrtReception fre where frp.frtReception.receptionId = fre.receptionId order by frp.frtReception.receptionId "
                                + param).list();
    }

    @SuppressWarnings("unchecked")
    
    public List<FrtReceptionVo> findAll(String param, int page, int rows)
    {
        return this
                .getSessionFactory()
                .getCurrentSession()
                .createQuery(
                        "select new com.syuesoft.frt.vo.FrtReceptionVo(frp.rcptpartsIndex, frp.partsId, frp.partsName, frp.rcptpartsCnt, frp.punitId, frp.rcptpartsPrice, frp.rcptpartsAmount, frp.charge, frp.claimsType, frp.partsRemark, fre.receptionId) from FrtRcptParts frp,FrtReception fre where frp.frtReception.receptionId = fre.receptionId order by frp.frtReception.receptionId "
                                + param).setFirstResult((page - 1) * rows)
                .setMaxResults(rows).list();
    }
}
