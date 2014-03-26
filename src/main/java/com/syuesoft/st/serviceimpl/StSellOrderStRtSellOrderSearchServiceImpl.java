/**
 * 
 */
package com.syuesoft.st.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.st.dao.StSellOrderitemDAO;
import com.syuesoft.st.service.StSellOrderStRtSellOrderSearchService;
import com.syuesoft.st.vo.StSellOrderStRtSellOrderSearchVo;
import com.syuesoft.util.Json;
/**
 * @author SuMing
 * 消退单查询ServiceImpl
 */
@Service("stSellOrderStRtSellOrderSearchService")
public class StSellOrderStRtSellOrderSearchServiceImpl implements
		StSellOrderStRtSellOrderSearchService {
	@Autowired StSellOrderitemDAO stSellOrderitemDAO;
	
	/**
	 *  消退单查询模块  消退单信息预加载
	 */
	public Json loadStSellOrderStRtSellOrderSearchInfo(StSellOrderStRtSellOrderSearchVo ssosVo)throws Exception{
		return stSellOrderitemDAO.loadStSellOrderStRtSellOrderSearchInfo(ssosVo.getStartTime(), ssosVo.getEndTime(), ssosVo.getPbrdId(), ssosVo.getPtypeId(), ssosVo.getPartsId(), ssosVo.getPartsName(), ssosVo.getStoreId(),ssosVo.getEnterpriseId());
	}
}
