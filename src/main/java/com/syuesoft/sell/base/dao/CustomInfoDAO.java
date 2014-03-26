package com.syuesoft.sell.base.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsCustomInfo;

public interface CustomInfoDAO extends BaseDaoI<XsCustomInfo> {
	public List<XsCustomInfo> findByNumber (String customNumber,Integer enid)throws Exception ;
	public List<XsCustomInfo> findByNumber(String customNumber,Integer customId,Integer enid) throws Exception;
}
