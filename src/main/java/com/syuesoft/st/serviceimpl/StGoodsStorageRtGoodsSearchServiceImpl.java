package com.syuesoft.st.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.st.dao.StRtGoodsDetailDAO;
import com.syuesoft.st.service.StGoodsStorageRtGoodsSearchService;
import com.syuesoft.st.vo.StGoodsStorageRtGoodsSearchVo;
import com.syuesoft.util.Json;

@Service("stGoodsStorageRtGoodsSearchService")
public class StGoodsStorageRtGoodsSearchServiceImpl extends BaseServiceImpl implements
		StGoodsStorageRtGoodsSearchService {

	@Autowired StRtGoodsDetailDAO stRtGoodsDetailDAO;
	
	/**
	 * 入退单查询模块   入退单信息预加载
	 */
	public Json loadStGoodsStorageRtGoodsSearchInfo(StGoodsStorageRtGoodsSearchVo sgsvo)throws Exception{
		return stRtGoodsDetailDAO.loadStGoodsStorageRtGoodsSearchInfo(sgsvo.getStartTime(), sgsvo.getEndTime(), sgsvo.getPbrdName(), sgsvo.getTypeName(), sgsvo.getPartsId(), sgsvo.getPartsName(), sgsvo.getStoreId(),sgsvo.getEnterprise_id());
	}
	
	/**
	 * 入退单明细   通过入退时间获取
	 */
	public Json loadStGoodsStorageRtGoodsDetailSearchInfo(StGoodsStorageRtGoodsSearchVo sgsvo)throws Exception{
		return stRtGoodsDetailDAO.loadStGoodsStorageRtGoodsDetailSearchInfo(sgsvo.getStartTime(), sgsvo.getEndTime(), sgsvo.getPartsId(), sgsvo.getStoreId());
	}
	
	
}
