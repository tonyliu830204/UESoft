package com.syuesoft.sell.sellAccount.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;


import com.syuesoft.bas.daoimpl.BaseDaoImpl;

import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.sellAccount.dao.SellAccountListDao;
import com.syuesoft.sell.sellAccount.vo.SellAccountVo;
import com.syuesoft.util.FormatTime;

@Repository("sellAccountListDao")
public class sellAccountDaoImpl extends BaseDaoImpl<BaseBean> implements SellAccountListDao{


}
