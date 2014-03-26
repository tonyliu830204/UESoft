package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtResvAdviceDao;
import com.syuesoft.model.FrtResvAdvice;

/**
 * 前台接车-维修 建议
 * 
 * @author LiWeijun
 * 
 */
@Repository("frtResvAdviceDao")
public class FrtResvAdviceDaoImpl extends BaseDaoImpl<FrtResvAdvice> implements
        FrtResvAdviceDao
{

}
