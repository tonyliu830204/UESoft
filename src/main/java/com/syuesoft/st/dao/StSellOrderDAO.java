package com.syuesoft.st.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.model.StSellOrder;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;

public interface StSellOrderDAO extends BaseDaoI<StSellOrder>{

	/** 销售单汇总信息      综合查询 */
	public Json searchByCondition(final int page, final int rows,
			final String sort, final String order,final String sellmmDateStart,final String sellmmDateEnd,final String carLicense,final String sellmmId,final String customId,final String sellmmRemark,final int enterpriseId)throws Exception;
	
	/**财务模块    配件动态变化   消退退料信息    预加载*/
	List<PartsTrendsChangeSearchVo> loadSellPartsAndStRtParts()
	throws Exception;
	
	/**财务模块    配件动态变化   消退退料信息   综合查询*/
	List<PartsTrendsChangeSearchVo> searchSellPartsAndStRtPartsByCondition(PartsTrendsChangeSearchVo ptcsvo) throws Exception;
	
	/**采购员查询*/
	public List<StSellOrderVo> findPartsStockPerson(int page,int rows,
			 String sb) throws Exception;
}