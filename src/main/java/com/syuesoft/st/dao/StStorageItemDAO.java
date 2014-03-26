package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StStorageItem;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;
/**
 * 入库单明细DAO接口
 * @author SuMing
 */
public interface StStorageItemDAO extends BaseDaoI<StStorageItem>{
	
	/**根据入库单号获取入库明细信息*/
	public Json searchStStorageItemByStorageId(final String storageId) throws Exception;
	
	/**根据入库汇总单单号获取入库单明细信息 */
	public List<PartsNowCountVo> searchStStorageDetailByStorageId(final String storageId) throws Exception;

}