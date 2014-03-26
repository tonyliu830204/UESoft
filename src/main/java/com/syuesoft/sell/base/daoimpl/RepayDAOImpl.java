package com.syuesoft.sell.base.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.RepayDAO;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.sell.model.XsRepay;

@Repository("repayDAO")
public class RepayDAOImpl extends BaseDaoImpl<XsRepay> implements RepayDAO{
	
	public List<XsRepay> findAllRepay(Integer enterpriseId) throws Exception {
		return find("from XsRepay repro where 1=1 and repro.enterpriseId="+enterpriseId);
	}
	
}
