package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtResvItemDao;
import com.syuesoft.model.FrtResvItem;

/**
 * 预约/保险估价单-维修项目
 * 
 * @author Liujian
 * 
 */
@Repository("frtResvItemDao")
public class FrtResvItemDaoImpl extends BaseDaoImpl<FrtResvItem> implements
        FrtResvItemDao
{

}
