package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.model.StSellOrderitem;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;

/**
 * 销售单管理Service接口
 * @author SuMing
 */
public interface StSellOrderService extends BaseService {
	
	/**明细数据由JSON转换为List集合**/
	public List<StSellOrderVo> jsonChangeDetailList(StSellOrderVo stSellOrderVo);
	
	/**销售单汇总信息   综合查询*/
	public Json searchByCondition(StSellOrderVo stSellOrderVo)throws Exception;
	
	/**车牌照信息            预加载*/
	public Json loadCarLicense(StSellOrderVo stSellOrderVo) throws Exception;
	
	/**车牌照信息            综合查询*/
	public Json searchCarLicenseByCondition(StSellOrderVo stSellOrderVo) throws Exception;
	
	/** 客户信息    预加载*/
	public Json loadBasCustom(StSellOrderVo stSellOrderVo) throws Exception;
	
	/** 客户信息    综合查询*/
	public Json searchBasCustomByCondition(StSellOrderVo stSellOrderVo) throws Exception;
	
	/** 领料员查询*/
	public Json loadPickingMember(StSellOrderVo stSellOrderVo) throws Exception ;
	
	/** 退料员查询*/
	public Json findPartsStorehousePerson(StSellOrderVo stSellOrderVo) throws Exception ;
	
	/**采购员查询*/
	public Json findPartsStockPerson(StSellOrderVo stSellOrderVo) throws Exception ;
	/** 领料员信息       综合查询*/
	public Json searchPickingMemberByCondition(StSellOrderVo stSellOrderVo) throws Exception;
	
	/** 出库单管理模块  （ 配件信息）     预加载 */
	public Json loadFrtParts(StSellOrderVo stSellOrderVo) throws Exception;
	
	/**销售单汇总，明细信息添加*/
	public void add(StSellOrderVo stSellOrderVo,List<StSellOrderVo> list) throws Exception;
	
	/**销售汇总对应销售明细信息加载*/
	public Json loadStSellOrderItemBySellmmId(StSellOrderVo stSellOrderVo ) throws Exception;
	
	/**删除销售汇总单信息*/
	public void delete(StSellOrderVo stSellOrderVo) throws Exception;
	
	/**销售单汇总，销售单明细修改*/
	public void update(StSellOrderVo stSellOrderVo,List<StSellOrderVo> list)throws Exception;
	
	/** 验证该销售单是否可删除修改操作*/
	public boolean doDeleteOrUpdate(StSellOrderVo ssvo)throws Exception;
	
	/**配件库存量调整**/
	public void changPartsNowCount(StSellOrderitem ssvo,boolean isno) throws Exception;

	/**销售转结算 */
	public void modifyPreclr(StSellOrderVo ssvo)throws Exception;

}
