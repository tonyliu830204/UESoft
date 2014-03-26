package com.syuesoft.sell.sellwork.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

public interface CarFixService {
	
	//获取车辆档案信息（在库代销，新建档案）
	public Json getCarInfor(CarFixVo carFixVo)throws Exception;
	
	/** 
    *
    * @Title: getCarFixInfor 
    * @Description: TODO(获取加装汇总信息) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
	public Json getCarFixInforNoFinish(CarFixVo carFixVo)throws Exception;
	
	/** 
    *
    * @Title: getCarFixInfor 
    * @Description: TODO(获取审核汇总信息) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getCarFixInforExamine(CarFixVo carFixVo)throws Exception;
    
	/** 
    *
    * @Title: getCarFixInfor 
    * @Description: TODO(申请加装) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
	public void doApplyFix(CarFixVo carFixVo, BasUsers user)throws Exception;

    /** 
    *
    * @Title: getCarFixDetail 
    * @Description: TODO(获取加装明细) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getCarFixDetail(CarFixVo carFixVo)throws Exception;

    /** 
    *
    * @Title: examineCarFix 
    * @Description: TODO(修改加装审核状态) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Msg updateCarFixExamineState(CarFixVo carFixVo)throws Exception;

    /** 
    *
    * @Title: findDefaulePartStore 
    * @Description: TODO(查询默认的销售加装仓) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public String findDefaulePartStore(CarFixVo carFixVo)throws Exception;

    /** 
    *
    * @Title: save 
    * @Description: TODO(保存加装项目以及配件) 
    * @param @param carFixVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void save(CarFixVo carFixVo, BasUsers user)throws Exception;

    /** 
    *
    * @Title: findPart 
    * @Description: TODO(查询配件) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json findPart(CarFixVo carFixVo)throws Exception;

    /** 
    *
    * @Title: findItem 
    * @Description: TODO(查询项目) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json findItem(CarFixVo carFixVo)throws Exception;

    /** 
    *
    * @Title: updateCarFixFinishState 
    * @Description: TODO(修改加装完成情况) 
    * @param @param carFixVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void updateCarFixFinishState(CarFixVo carFixVo)throws Exception;
    
    /** 
    *
    * @Title: updateCarFixFinishState 
    * @Description: TODO(修改加装总金额) 
    * @param @param carFixVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void updateCarFixAmount(CarFixVo carFixVo)throws Exception;
    /** 
    *
    * @Title: totemoney 
    * @Description: TODO(计算加装总金额) 
    * @param @param carFixVo    设定文件 
    * @return Double    返回类型 
    * @throws 
    */
    public Double totemoney(CarFixVo carFixVo)throws Exception;
    
}	
