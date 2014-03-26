package com.syuesoft.st.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.st.dao.StOutItemDAO;
import com.syuesoft.st.service.StOutStRtPartsSearchService;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Json;

/**
 * 出退单查询ServiceImpl
 * @author SuMing
 */
@Service("stOutStRtPartsSearchService")
public class StOutStRtPartsSearchServiceImpl implements StOutStRtPartsSearchService {

	@Autowired StOutItemDAO stOutItemDAO;
	
	/** 
	 * 出退单查询模块   出退单信息预加载 
	 */
	public Json loadStOutAndStRtPartsSearchInfo(StOutVo stOutVo)throws Exception{
		return stOutItemDAO.loadStOutAndStRtPartsSearchInfo(stOutVo.getStartTime(), stOutVo.getEndTime(), stOutVo.getPbrdId(), stOutVo.getTypeId(), stOutVo.getPartsId(), stOutVo.getPartsName(), stOutVo.getStoreId(),stOutVo.getClaimsName(),stOutVo.getOutItemRemark(),stOutVo.getEnterpriseId());
	}
	
	/**
     * 出退单明细  根据出退单时间获取
     */
    public Json loadStOutAndStRtPartsDetailSearchInfo(StOutVo stOutVo)throws Exception{
		return stOutItemDAO.loadStOutAndStRtPartsDetailSearchInfo(stOutVo.getStartTime(), stOutVo.getEndTime(),  stOutVo.getPartsId(), stOutVo.getStoreId(),stOutVo.getEnterpriseId());
    }
}
