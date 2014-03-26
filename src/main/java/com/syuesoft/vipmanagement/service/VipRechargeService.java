package com.syuesoft.vipmanagement.service;

import java.util.List;

import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipRechargeRegulation;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;

public interface VipRechargeService {
    /**
    * @Title: findAll 
    * @Description: TODO(获取所有充值记录) 
    * @param @param vrmVO
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Json    返回类型 
    * @throws
     */
    public Json findAll(VipRecordMessageVo vrmVO, BasUsers user) throws Exception;
    /**
    * @Title: readCard 
    * @Description: TODO(读卡) 
    * @param @param vrmVO
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<VipRecordMessageVo>    返回类型 
    * @throws
     */
	public Message readCard(VipRecordMessageVo vrmVO, BasUsers user)throws Exception;
	/**
	* @Title: readVipRedInfo 
	* @Description: TODO(获取充值信息) 
	* @param @param vrmVO
	* @param @return
	* @param @throws Exception    设定文件 
	* @return VipRecordMessageVo    返回类型 
	* @throws
	 */
	public VipRecordMessageVo readVipRedInfo(VipRecordMessageVo vrmVO)throws Exception;  
	/**
	* @Title: add 
	* @Description: TODO(会员充值) 
	* @param @param vrmVO
	* @param @param user
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Message    返回类型 
	* @throws
	 */
	public Message add(VipRecordMessageVo vrmVO, BasUsers user) throws Exception;
	/**
    * @Title: update 
    * @Description: TODO(更新会员充值) 
    * @param @param vrmVO
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Message    返回类型 
    * @throws
     */
    public Message update(VipRecordMessageVo vrmVO, BasUsers user) throws Exception;
	/**
	* @Title: delete 
	* @Description: TODO(删除会员储值) 
	* @param @param vrmVO
	* @param @return
	* @param @throws Exception    设定文件 
	* @return Message    返回类型 
	* @throws
	 */
	public Message delete(VipRecordMessageVo vrmVO, BasUsers user)throws Exception;
	/**
	 * @Title: updateVipRecharge 
	 * @Description: TODO(审核) 
	 * @param @param vrmVO
	 * @param @throws Exception    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public Message updateVipRecharge(VipRecordMessageVo vrmVO, BasUsers user) throws Exception;
	/**
	* @Title: getByRecAmount 
	* @Description: TODO(获取充值规则) 
	* @param @param recAmount
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BasVipRechargeRegulation    返回类型 
	* @throws
	 */
	public BasVipRechargeRegulation getByRecAmount(Integer recAmount) throws Exception;
}