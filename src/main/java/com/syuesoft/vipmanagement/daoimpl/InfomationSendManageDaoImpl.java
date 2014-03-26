package com.syuesoft.vipmanagement.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipSendinfomation;
import com.syuesoft.vipmanagement.dao.InfomationSendManageDao;

@Repository("infomationSendManageDao")
public class InfomationSendManageDaoImpl extends BaseDaoImpl<BasVipSendinfomation> implements
		InfomationSendManageDao {
}