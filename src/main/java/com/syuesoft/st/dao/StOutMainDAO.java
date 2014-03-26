package com.syuesoft.st.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.model.StOutMain;
import com.syuesoft.util.Json;

public interface StOutMainDAO extends BaseDaoI<StOutMain> {
	
	/**出库单汇总信息      综合查询*/
	public Json  searchByCondition(final String sort,final String order,final int page, final int rows,final String stomDateStart,final String stomDateEnd, final String stomId,
			final String receptionId, final String carLicense,final String customName, final String carVan,final int enterpriseId) throws Exception;

	/**财务模块   工单配件查询    已结算配件出库退料详细信息查询*/
	public List<WorkOrderPartsQueryVo> loadStOutAndStRtPartsDetail(String receptionId,WorkOrderPartsQueryVo wopVo) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getStOutMainByCerNo(String cerNo) ;

	/** 财务模块    配件动态变化   出库退料配件信息   预加载 */
	public List<PartsTrendsChangeSearchVo> loadStOutAndRtPartsInfo(PartsTrendsChangeSearchVo ptcsvo) throws Exception;

	/** 财务模块    配件动态变化   出库退料配件信息    综合查询 */
	List<PartsTrendsChangeSearchVo> searchStOutAndRtPartsInfoByCondition(PartsTrendsChangeSearchVo ptcsvo)
			throws Exception;
}