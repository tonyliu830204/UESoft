package com.syuesoft.vipmanagement.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipInforAccount;
import com.syuesoft.vipmanagement.dao.VipAccountDao;

/** 
 * @ClassName: VipAccountDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-12-19 下午03:25:00 
 * @version 1.0 
 */
@Repository("vipAccountDao")
public class VipAccountDaoImpl extends BaseDaoImpl<BasVipInforAccount> implements VipAccountDao
{

}
