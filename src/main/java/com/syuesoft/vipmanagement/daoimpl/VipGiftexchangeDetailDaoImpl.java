package com.syuesoft.vipmanagement.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipGiftexchangeDetail;
import com.syuesoft.vipmanagement.dao.VipGiftexchangeDetailDao;

@Repository("vipGiftexchangeDetailDaoImpl")
public class VipGiftexchangeDetailDaoImpl extends BaseDaoImpl<BasVipGiftexchangeDetail> implements
        VipGiftexchangeDetailDao
{
}