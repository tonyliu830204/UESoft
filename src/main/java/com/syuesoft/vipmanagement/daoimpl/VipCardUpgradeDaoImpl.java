package com.syuesoft.vipmanagement.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipUpgrade;
import com.syuesoft.vipmanagement.dao.VipCardUpgradeDao;

@Repository("vipCardUpgradeDao")
public class VipCardUpgradeDaoImpl extends BaseDaoImpl<BasVipUpgrade> implements VipCardUpgradeDao {

}