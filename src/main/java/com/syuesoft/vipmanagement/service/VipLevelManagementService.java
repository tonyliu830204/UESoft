package com.syuesoft.vipmanagement.service;

import java.util.List;

import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;

public interface VipLevelManagementService {
	public List findVipInfo(VipRecordMessageVo vrmVO) throws Exception;
}
