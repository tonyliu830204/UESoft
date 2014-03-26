package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.Json;

/**
 * 退货单管理service接口
 * @author SuMing
 */
public interface StRtGoodsService {
	
	/** 明细数据由JSON转换为List集合 */
	public List<StRtGoodsVo> jsonChangeDetailList(StRtGoodsVo stRtGoodsVo);
	
	/**退货单汇总    综合查询*/
	public Json searchByCondition(StRtGoodsVo stRtGoodsVo)throws Exception;
	
	/**根据退货单编号获取退货单明细信息*/
	public List<StRtGoodsVo> searchStRtGoodsDetailByStrtgmId(StRtGoodsVo stRtGoodsVo)throws Exception;
	
	/**退货单模块   根据入库单号查询入库单信息*/
	public List<StRtGoodsVo> searchstGoodsStorageByStorageId(StRtGoodsVo stRtGoodsVo)throws Exception;

	/**入库单明细信息加载*/
	public List<StRtGoodsVo> searchStGoodsStorageByStorageId(StRtGoodsVo stRtGoodsVo) throws Exception;
	
	/**根据供应商ID获取入库单信息*/
	public List<StRtGoodsVo> searchStGoodsStorageByRelcampId(StRtGoodsVo stRtGoodsVo) throws Exception;
	
	/**退货单新增*/
	public void add(StRtGoodsVo stRtGoodsVo,List<StRtGoodsVo> list) throws Exception;
	
	/**退货单删除*/
	public void delete(StRtGoodsVo stRtGoodsVo) throws Exception;
	
	/**退货单修改*/
	public void update(StRtGoodsVo stRtGoodsVo,List<StRtGoodsVo> list) throws Exception;
	
}
