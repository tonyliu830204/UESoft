package com.syuesoft.vipmanagement.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipGruop;
import com.syuesoft.model.BasVipLevel;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;

public interface VipRecordMessageDao extends BaseDaoI<BasVipInfor>{
	public List<VipRecordMessageVo> getInfomatinoByCar(VipRecordMessageVo vipRecordMessageVo)throws Exception;
	public List<BasVipGruop> getBasVipGruop(BasUsers user)throws Exception;
	public List<BasVipLevel> getBasVipLevel(BasUsers user)throws Exception;
}
