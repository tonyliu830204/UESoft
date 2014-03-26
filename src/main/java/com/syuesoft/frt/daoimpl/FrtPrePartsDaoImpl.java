package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtPrePartsDao;
import com.syuesoft.model.FrtPreParts;

/**
 * 交车结算->配件清单
 * 
 * @author Liujian
 * 
 */
@Repository("frtPrePartsDao")
public class FrtPrePartsDaoImpl extends BaseDaoImpl<FrtPreParts> implements
        FrtPrePartsDao
{

}
