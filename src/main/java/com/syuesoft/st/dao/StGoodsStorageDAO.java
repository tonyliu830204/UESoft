package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.model.StGoodsStorage;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.Json;
/**
 * 货品入库单DAO
 * @author SuMing
 */
public interface StGoodsStorageDAO extends BaseDaoI<StGoodsStorage>{

	 /**入库单汇总信息     预加载*/
	public Json loadStGoodsStorage(final int page, final int rows, final String sort,final String order,final int enterpriceId)throws Exception;
	
	 /**入库单汇总信息     综合查询*/
	public Json searchStGoodsStorageByCondition(final String storageDateStart,final String storageDateEnd, final String relcampName,final String storageId,final String orderId,final int enterpriceId)throws Exception;

	/**退货单模块 根据入库单号查询入库单信息*/
	public List<StRtGoodsVo> searchstGoodsStorageByStorageId(final String storageId) throws Exception;

	/**退货单模块     入库单信息加载，条件查询*/
	public List<StRtGoodsVo> srg_searchByCondition(final String relcampId,final String storageId,final String storeId,final int enterpriceId)throws Exception;
	
	// 下一条记录
	public List<StGoodsStorage> findBystorageId(String storageId);

	// 上一条记录
	public List<StGoodsStorage> findBystorageId1(String storageId) ;
	
	/**财务模块   配件动态变化   入库退货配件信息   预加载*/
	List<PartsTrendsChangeSearchVo> loadStStorageAndStRtGoods()
			throws Exception;
	
	/**财务模块   配件动态变化   入库退货配件信息   综合查询*/
	List<PartsTrendsChangeSearchVo> searchStStorageAndStRtGoodsByCondition(PartsTrendsChangeSearchVo ptcsvo) throws Exception;

	/**财务模块   配件动态变化   配件动态信息   综合查询*/
    public Json loadPartsDynamicDate(PartsTrendsChangeSearchVo ptcsvo) throws Exception;
}