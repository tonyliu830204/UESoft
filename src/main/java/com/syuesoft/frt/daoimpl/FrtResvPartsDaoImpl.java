package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.FrtResvParts;

/**
 * 预约/保险估计单-维修配件
 * 
 * @author Liujian
 * 
 */
@Repository("frtResvPartsDao")
public class FrtResvPartsDaoImpl extends BaseDaoImpl<FrtResvParts> implements
        com.syuesoft.frt.dao.FrtResvPartsDao
{

}
