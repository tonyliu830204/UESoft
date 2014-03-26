package com.syuesoft.vipmanagement.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipRelationship16;
import com.syuesoft.vipmanagement.dao.VipRelationshipDao;

@Repository("vipRelationshipDaoImpl")
public class VipRelationshipDaoImpl extends BaseDaoImpl<BasVipRelationship16> implements
        VipRelationshipDao
{

}