package com.syuesoft.st.service;

import java.util.List;
import com.syuesoft.st.vo.StRtPartsVo;
import com.syuesoft.util.Json;
/**
 * 退料单Sevice
 * @author SuMing
 */
public interface StRtPartsService {
	
	/** 明细数据由JSON转换为List集合*/
	public List<StRtPartsVo> jsonChangeDetailList(StRtPartsVo srpvo);
	
	/**工单退料单模块    条件综合查询,加载*/
	public Json searchRecptByCondition(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**销售退料单汇总信息       预加载*/
	public Json loadSellStRtPartsMain(StRtPartsVo srpvo)throws Exception;
	
	/**销售退料单汇总信息       预加载，条件综合查询*/
	public Json searchSellStRtPartsMainByCondition(StRtPartsVo srpvo)throws Exception;
	
	/**退料单明细信息加载*/
	public List<StRtPartsVo> searchRecptStRtPartsDetailByStrtpmId(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**退料单删除（明细，汇总）*/
	public void delete(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**销售单信息       预加载*/
	public Json loadSelledParts(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**销售单信息     综合查询*/
	public Json searchStSellOrderByCondition(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**销售退料单明细信息（根据销售退料单汇总单号获取）    预加载*/
	public List<StRtPartsVo> searchSellStRtPartsDetailByStrtpmId(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**加载工单号对应配件数据*/
	public Json loadParts(StRtPartsVo stRtPartsVo)throws Exception;
	
	/**工单信息加载*/
	public Json loadFrtReception(StRtPartsVo stRtPartsVo) throws Exception;
	
	/**工单信息       综合查询*/
	public Json searchFrtReceptionByCondition(StRtPartsVo srpvo) throws Exception;
	
	/**退料单汇总明细添加*/
	public String add(StRtPartsVo stRtPartsVo,List<StRtPartsVo> list) throws Exception;
	
	/** 销售单信息       预加载 */
	public Json loadStSellOrder(StRtPartsVo stRtPartsVo)throws Exception ;
	
}
