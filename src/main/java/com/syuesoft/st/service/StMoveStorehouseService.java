package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.st.vo.StMoveStorehouseVo;
import com.syuesoft.util.Json;

/**
 * 移仓调拨单 Service接口
 * @author SuMing
 */
public interface StMoveStorehouseService  extends BaseService{
	
	/**反序列化明细JSON数据**/
	public List<StMoveStorehouseVo> acceptChangesSelectedItem(StMoveStorehouseVo stMoveStorehouseVo) ;
	
	/**移仓调拨单 汇总        综合查询**/
	public Json searchByCondition(StMoveStorehouseVo stMoveStorehouseVo)throws Exception;
	
	/**移仓调拨单未审核 汇总    预加载**/
	public Json loadUnExamineStMoveStorehouse(StMoveStorehouseVo stMoveStorehouseVo)throws Exception;
	
	/**移仓调拨单未审核 汇总      综合查询**/
	public Json searchUnExamineByCondition(StMoveStorehouseVo stMoveStorehouseVo)throws Exception;

	/**通过移仓单单号    预加载 移仓单明细信息**/
	public  List<StMoveStorehouseVo>  searchStMoveStorehouseDetailByMsdmId(StMoveStorehouseVo stMoveStorehouseVo) throws Exception;
	
	/** 根据移仓单单号获取 移出仓配件明细信息 */
	public List<StMoveStorehouseVo> loadMoveOutPartsDetail(StMoveStorehouseVo smsvo)throws Exception;
	
	/** 根据移仓单单号获取 移入仓配件明细信息*/
	public List<StMoveStorehouseVo> loadMoveInPartsDetail(StMoveStorehouseVo smsvo)throws Exception;
	
	/**移仓单汇总信息    审核 */
	public void updateExamine(StMoveStorehouseVo smsvo)throws Exception;
	
	/**移仓单汇总 ，明细      添加**/
	public void add(StMoveStorehouseVo stMoveStorehouseVo,List<StMoveStorehouseVo> list)throws Exception;
	
	/**移仓单汇总 ，明细       删除**/
	public void delete(StMoveStorehouseVo stMoveStorehouseVo)throws Exception;
	
	/** 移仓单汇总 ，明细     修改**/
	public void update(StMoveStorehouseVo stMoveStorehouseVo,List<StMoveStorehouseVo> list)throws Exception;
	
}
