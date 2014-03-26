package com.syuesoft.st.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtParts;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;

public interface FrtPartsDAO extends BaseDaoI<FrtParts>{
	
	/**获取配件库存量*/
	@SuppressWarnings("unchecked")
	public List getPartsNowCount(String partsId)throws Exception;
	
	 /**配件库存量      综合查询*/
	public List<PartsNowCountVo> searchPartsNowCountByCondition(final int page, final int rows, final String sort,final String order,final String partsId,final String  partsName,final String posName,final String  partsLibrary,final String  partsId2,final String  pbrdName,final String  ptypeName,final String  storeName,final String partsNowCount,final String searchStyle,final String pnc_strtgmDateStart,final String pnc_strtgmDateEnd,final String storageDateStart ,final String storageDateEnd,final String sgs_partsId,final int enterpriseId)throws Exception;	
	
	 /**配件库存量      综合查询*/
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String partsId,final String  partsName,final String posName,final String  partsLibrary,final String  partsId2,final String  pbrdName,final String  ptypeName,final String  storeName,final String partsNowCount,final String searchStyle,final String pnc_strtgmDateStart,final String pnc_strtgmDateEnd,final String storageDateStart ,final String storageDateEnd,final String sgs_partsId,final int enterpriseId)throws Exception;	


	
	/**配件名称信息      预加载*/
	public Json loadPartsName(final int page, final int rows, final String sort,final String order,final int enterpriseId)throws Exception;
	
	/**配件名称信息      综合查询*/
	public Json searchPartsNameByCondition(final String partsId,final String partsName,final int enterpriseId)throws Exception;
	
    /**配件信息)  配件编号，名称条件查询,加载*/
	public Json searchByPartsIdAndPartsName(final String partsId,final String partsId2,final String partsName,final String pbrdId,final String ptypeName, final String sort,
			final String order,final int enterpeiseId) throws Exception;
	
}