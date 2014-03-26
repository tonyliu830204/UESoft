package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
/**
 * 采购单管理Service接口
 * @author SuMing
 */
public interface StPurOrderService extends BaseService{
	
	/**采购员查询*/
	public Json loadPurOrderMember(StPurOrderVo stPurOrderVo) throws Exception;

	/** 明细数据由JSON转换为List集合*/
	public List<StPurOrderVo> jsonChangeDetailList(StPurOrderVo stPurOrderVo);
	
	/**采购单汇总信息     综合查询*/
	public Json searchByCondition(StPurOrderVo stPurOrdervo)throws Exception;
	
	/**修改采购单审核状态*/
	public void updateExamine(String orderId)throws Exception;
	
	/**配件信息预加载*/
	public Json searchByPartsIdAndPartsName(StPurOrderVo stPurOrderVo) throws Exception;
	
	/**采购单新增*/
	public void add(StPurOrderVo stPurOrderVo,List<StPurOrderVo> list) throws Exception ;
	/**采购单导入*/
	public Msg addItem(StPurOrderVo stPurOrderVo,List<StPurOrderVo> list) throws Exception ;
	
	/**采购单信息删除*/
    public void delete(StPurOrderVo stPurOrderVo) throws Exception;
    
    /**采购单信息修改*/
    public void update(StPurOrderVo stPurOrderVo,List<StPurOrderVo> list) throws Exception;
    
	/**单击采购单记录获取采购单明细信息*/
    public List<StPurOrderVo> searchStOrderItemByOrderId(String orderId) throws Exception;
    
}
