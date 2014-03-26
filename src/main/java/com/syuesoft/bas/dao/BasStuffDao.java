package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.Json;

public interface BasStuffDao extends BaseDaoI<BasStuff> {
	/*************/
	@SuppressWarnings("unchecked")
	public List findDepart()throws Exception;
	
	/**领料员信息        预加载*/
	public Json loadPickingMember(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;
	
	/**领料员信息       综合查询*/
	public Json searchPickingMemberByCondition(final String stfId,final String stfName,final int enterprise_id)throws Exception;
	/**获取指定用户所属公司Id*/
    public String findEnterpriseInfoOfIdByStfId(String stfId) throws Exception;
	/**获取指定用户所属公司信息*/
    public EnterpriseInfo findEnterpriseInfoByStfId(String stfId) throws Exception;
}
