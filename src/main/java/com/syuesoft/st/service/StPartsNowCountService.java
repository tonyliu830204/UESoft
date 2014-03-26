package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;

public interface StPartsNowCountService extends BaseService {
	
	/** 反序列化明细JSON数据*/
	public List<PartsNowCountVo> acceptChangesSelectedItem(PartsNowCountVo partsNowCountVo) ;
	
	/**库存量信息     综合查询*/
	public Json searchPartsNowCountByCondition(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/** 配件汇总信息计算*/
	public Json calcutePartsChangePrice(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件品牌     预加载*/
	public Json loadPartsBrand(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件品牌     综合查询*/
	public Json serachPartsBrandByCondition(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件名称     预加载*/
	public Json loadPartsName(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件名称     综合查询*/
	public Json searchPartsNameByCondition(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件型号信息    预加载*/
	public Json loadPartsType(PartsNowCountVo pncvo)throws Exception;
	
	/**配件型号信息    综合查询*/
	public Json searchPartsTypeByCondition(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**配件价格修改*/
	public void update(PartsNowCountVo partsNowCountVo)throws Exception;
	
	/**入库单明细配件价格     批量修改*/
	public void updateList(List<PartsNowCountVo> list)throws Exception;
	
	/** 配件调价表清空*/
	public void clearPratsChangePrice()throws Exception;
	
}
