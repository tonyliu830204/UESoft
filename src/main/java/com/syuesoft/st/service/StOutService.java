package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.bas.service.BaseService;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Json;
/**
 * 出库单管理Service
 * @author SuMing
 */
public interface StOutService extends BaseService{
	
	/** 明细数据由JSON转换为List集合*/
	public List<StOutVo> jsonChangeDetailList(StOutVo stOutVo);
	
	public BasClaimsType searchByClaimsId(String claimsType)throws Exception;
	
	/**出库单汇总信息     综合查询*/
	public Json searchByCondition(StOutVo stOutVo) throws Exception;

	/**配件信息加载*/
	public Json loadFrtParts(String partsId,String partsName,String storeId,int enterprise_id) throws Exception;
	
	/**工单信息加载*/
	public Json loadFrtReception(StOutVo stOutVo) throws Exception;
	
	/** 判断出库单中工单号是否在工单退料单中存在，若存在，不能进行删除修改操作，若不存在，可进行删除修改操作 */
	public boolean doDeleteOrUpdate(StOutVo stOutVo)throws Exception;
	
	/** 出库单管理模块(工单信息)     综合查询 */
	public Json searchFrtReceptInfoByCondition(StOutVo sovo) throws Exception;
	
	/** 出库单添加*/
	public void add(StOutVo stOutVo,List<StOutVo> list)throws Exception;
	
	/** 通过出库汇总ID获取出库明细信息 */
	public List<StOutVo> serachStOutItemByCondition(StOutVo stOutVo) throws Exception;
	
	/** 删除汇总单*/
	public void delete(StOutVo stOutVo) throws Exception;
	
	/** 出库单汇总修改*/
	public void update(StOutVo stOutVo,List<StOutVo> list)throws Exception;
	
	/**通过为计算工单加载相关计划用料信息*/
	public Json reFrtReceptParts(StOutVo stOutVo)throws Exception;
}
