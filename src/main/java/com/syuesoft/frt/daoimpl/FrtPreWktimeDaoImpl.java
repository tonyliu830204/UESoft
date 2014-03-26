package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtPreWktimeDao;
import com.syuesoft.model.FrtPreWktime;

/**
 * 交车结算->项目清单dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtPreWktimeDao")
public class FrtPreWktimeDaoImpl extends BaseDaoImpl<FrtPreWktime> implements
        FrtPreWktimeDao
{

}
