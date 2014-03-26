package com.syuesoft.st.service;

import java.util.List;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.st.vo.StStorageVo;
import com.syuesoft.util.Json;
/**
 * 入库单管理接口
 * @author SuMing
 */
public interface  StGoodsStorageService extends BaseService {
	
	/** 明细数据由JSON转换为List集合*/
	public List<StStorageVo> jsonChangeDetailList(StStorageVo stStorageVo);
	
	/**入库单汇总      综合查询*/
	public Json searchStGoodsStorageByCondition(StStorageVo stStorageVo)throws Exception;
	
	/** 采购单信息          综合查询*/
	public Json searchStPurOrderByCondition(StStorageVo  stStorageVo)throws Exception;
	
	/** 采购单信息         预加载*/
	public Json loadStPurOrder(StStorageVo  stStorageVo)throws Exception;
	
	/**供应商信息    预加载*/
	public Json loadBasRelationCampany(StStorageVo stStorageVo)throws Exception;
	
	/**供应商信息    综合查询*/
	public Json searchBasRelationCampanyByCondition(StStorageVo stStorageVo)throws Exception;
	
	/** 根据入库单号获取  入库明细配件 信息  */
	public List<PartsNowCountVo> searchStStorageDetailByStorageId(PartsNowCountVo partsNowCountVo) throws Exception;
	
	/**通过采购单号查找相对应采购明细信息 */
	public List<StPurOrderVo>  searchStPurOrderItemByOrderId(StStorageVo stStorageVo)throws Exception;
	
	/** 添加入库单数据前     加价率验证*/
	public int addIdentify(StStorageVo stStorageVo)throws Exception;
	
	/**入库单汇总添加 */
	public void add(StStorageVo stStorageVo,List<StStorageVo> list)throws Exception;
	
	/** 入库单模块  入库单删除 */
    public void delete(StStorageVo stStorageVo)throws Exception;
    
    /**判断该入库单是否已月结*/
	public boolean doMonthlyStatemont(StStorageVo stStorageVo)throws Exception;
    
    /**判断该入库单是否可以删除 */
	public boolean doDeleteAndUpdate(StStorageVo stStorageVo)throws Exception;
	
	/**根据入库单id查找库单明细信息 */
	public Json searchByIdStStorageItem(StStorageVo stStorageVo)throws Exception;

	/** 根据入库单ID得到下一条入库单记录*/
	public StStorageVo findByStorageId(StStorageVo stStorageVo)throws Exception;
	
	/** 根据入库单ID得到上一条入库单记录*/
	 public StStorageVo findByStorageId1(StStorageVo stStorageVo)throws Exception;
}
