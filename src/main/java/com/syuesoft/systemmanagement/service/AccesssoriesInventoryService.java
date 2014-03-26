package com.syuesoft.systemmanagement.service;

import java.util.List;
import com.syuesoft.fbk.vo.ComboxidVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.systemmanagement.vo.AccesssoriesInventoryVo;
import com.syuesoft.util.Json;

public interface AccesssoriesInventoryService {
	//查询盘点汇总信息
	public Json doFind(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;
	
	//查询配件信息
	public Json doFindAccessInfor(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;
	
	//配件新增
	public void	doAddFather(AccesssoriesInventoryVo accesssoriesInventoryVo, BasUsers user)throws Exception;
	
	//通过盘点单号查询盘点明细
	public Json getStInventoryDetailById(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;
	
	//更新字表盘点配件信息
	public void	doUpdateFather(AccesssoriesInventoryVo accesssoriesInventoryVo, BasUsers user)throws Exception;
	
	//删除盘点配件信息
	public void	doDelete(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;

    /**
     * @throws Exception  
    *
    * @Title: doUpdateState 
    * @Description: TODO(审核盘点单) 
    * @param @param accesssoriesInventoryVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Object doUpdateState(AccesssoriesInventoryVo accesssoriesInventoryVo) throws Exception;
}