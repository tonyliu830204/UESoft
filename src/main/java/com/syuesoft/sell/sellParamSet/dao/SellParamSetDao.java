package com.syuesoft.sell.sellParamSet.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.sell.model.XsSellParameter;
import com.syuesoft.sell.sellParamSet.vo.SellParamSetVo;

public interface SellParamSetDao extends BaseDaoI<XsSellParameter>{
	/**
	 * 查询系统参数
	 * @param param
	 * @return
	 */
    public List<XsSellParameter> findAll(String param);
    
    public Object saveOrUpdate(List<XsSellParameter> cisVos);
}
