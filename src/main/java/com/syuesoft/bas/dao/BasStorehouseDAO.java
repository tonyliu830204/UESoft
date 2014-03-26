package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.model.BasStorehouse;
/**
 * 基础资料-->配件性质-->仓别分类Dao接口
 * @author SuMing
 */
public interface BasStorehouseDAO extends BaseDaoI<BasStorehouse>{
	
	@SuppressWarnings("unchecked")
	public List isUsed(final BasStorehouseVo basStorehouseVo)throws Exception;
	
	//配件库存量查询模块    仓库名称信息全查    
	@SuppressWarnings("unchecked")
	public List pnc_searchBasStorehouseByCondition()throws Exception;
	
	//基础资料-->仓别分类   删除
	public void delete(BasStorehouse persistentInstance) ;
	
	//基础资料-->仓别分类   修改
	public BasStorehouse merge(BasStorehouse detachedInstance);
	
	//基础资料-->仓别分类   按ID查询
	public BasStorehouse findById(java.lang.Short id);
	
	//基础资料-->仓别分类   全部查询
	public List<BasStorehouse> findAll(final BasStorehouseVo basStorehouseVo);
	
    // 基础资料-->仓别分类   分页
	public List<BasStorehouse> getAllByPage(final BasStorehouseVo basStorehouseVo);
	
}