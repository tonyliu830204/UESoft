package com.syuesoft.vipmanagement.service;

import java.util.List;

import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;

public interface VipRecordMessageService {
    /**
     * 
    *查询方法
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param vipRecordMessageVo
    * @param @return
    * @param @throws Exception              设定文件 
    * @return Json                          返回类型 
    * @throws
     */
	public Json findAll(VipRecordMessageVo vipRecordMessageVo, BasUsers user)throws Exception;
	
	/**
	 * 
	*会员入会 添加 方法
	* @Title: add 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param vipRecordMessageVo
	* @param @param user
	* @param @return
	* @param @throws Exception             设定文件 
	* @return Message                      返回类型 
	* @throws
	 */
	public Message add(VipRecordMessageVo vipRecordMessageVo, BasUsers user)throws Exception;
	
	/**
	 * 
	*修改会员信息
	* @Title: update 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param vipRecordMessageVo
	* @param @return
	* @param @throws Exception             设定文件 
	* @return Message                      返回类型 
	* @throws
	 */
	public Message update(VipRecordMessageVo vipRecordMessageVo, BasUsers user)throws Exception;
	
	/**
	 * 
	*会员分组
	* @Title: getBasVipGruop 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return
	* @param @throws Exception             设定文件 
	* @return List<ComboxVo>               返回类型 
	* @throws
	 */
	public List<ComboxVo> getBasVipGruop(BasUsers user)throws Exception;
	
	/**
	 * 
	*会员级别
	* @Title: getBasVipLevel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return
	* @param @throws Exception             设定文件 
	* @return List<ComboxVo>               返回类型 
	* @throws
	 */
	public List<ComboxVo> getBasVipLevel(BasUsers user)throws Exception;
    /**
     * @throws Exception  
    * @Description: TODO(会员登陆) 
    * @param @param vipRecordMessageVo
    * @param @return                       设定文件 
    * @return Object                       返回类型 
    * @throws 
    */
    public Message findVip(VipRecordMessageVo vipRecordMessageVo) throws Exception;
    /** 
    * @Title: doUpdateVipState 
    * @Description: TODO(修改会员卡状态) 
    * @param @param vipRecordMessageVo
    * @param @return                       设定文件 
    * @return Object                       返回类型 
    * @throws 
    */
    public Message updateVipState(VipRecordMessageVo vipRecordMessageVo, BasUsers user)throws Exception;
    /** 
    *
    * @Title: doLossOfUpdate 
    * @Description: TODO(会员卡挂失) 
    * @param @param vipRecordMessageVo
    * @param @return                       设定文件 
    * @return Object                       返回类型 
    * @throws 
    */
    public Message updateLoss(VipRecordMessageVo vipRecordMessageVo, BasUsers user)throws Exception;
    
    /**
     * 
    * @Title: getVipInfo 
    * @Description: TODO(查询会员信息) 
    * @param @param vipRecordMessageVo
    * @param @return
    * @param @throws Exception             设定文件 
    * @return Json                         返回类型 
    * @throws
     */
    public Json getVipInfo(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception;
    
    /**
     * 
    * @Title: updateVipAdjournment 
    * @Description: TODO(会员续会保存) 
    * @param @param vipRecordMessageVo
    * @param @param user
    * @param @return
    * @param @throws Exception             设定文件 
    * @return Message                      返回类型 
    * @throws
     */
    public Message updateVipAdjournment(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception;

    /**
     * 
    * @Title: updateVipExitMember 
    * @Description: TODO(会员退会保存) 
    * @param @param vipRecordMessageVo
    * @param @param user
    * @param @return
    * @param @throws Exception             设定文件 
    * @return Message                      返回类型 
    * @throws
     */
    public Message updateVipExitMember(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception;
    
    /**
     * 
    * @Title: getBasVipInfor 
    * @Description: TODO(通过会员卡号查询会员对象) 
    * @param @param vipNumber             会员卡号
    * @param @param parentEnterpriseId    集团企业编号
    * @param @return
    * @param @throws Exception            设定文件 
    * @return BasVipInfor                 返回类型 
    * @throws
     */
    public BasVipInfor getBasVipInfor(String vipNumber, Integer parentEnterpriseId) throws Exception;
    
    /**
     * 
    * @Title: getBasVipInfor2 
    * @Description: TODO(通过车辆牌照与车架号查询会员对象) 
    * @param @param car_License           车牌号
    * @param @param car_Vin               车架号
    * @param @param parentEnterpriseId    集团企业编号
    * @param @return
    * @param @throws Exception            设定文件 
    * @return BasVipInfor                 返回类型 
    * @throws
     */
    public BasVipInfor getBasVipInfor2(String car_License, String car_Vin, Integer parentEnterpriseId) throws Exception;
    
    /**
     * 
    *
    * @Title: getBasVipInfors 
    * @Description: TODO(当前登陆用户所在集团下的所以会员) 
    * @param @param user
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<BasVipInfor>    返回类型 
    * @throws
     */
    public List<?> getBasVipInfors(BasUsers user) throws Exception;
    
    /**
     * 
    * @Title: getBasVipInfor 
    * @Description: TODO(通过会员编号查询会员对象) 
    * @param @param vipNumber             会员卡号
    * @param @return
    * @param @throws Exception            设定文件 
    * @return BasVipInfor                 返回类型 
    * @throws
     */
    public BasVipInfor getBasVipInfor3(String vipId) throws Exception;
    
    /**
     * 
    * @Title: updateVipInfor 
    * @Description: TODO(更新会员对象) 
    * @param @param vipInfo               会员对象
    * @param @param user                  操作人    
    * @param @return
    * @param @throws Exception            设定文件 
    * @return boolean                     返回类型 
    * @throws
     */
    public Message updateVipInfor(BasVipInfor vipInfo, BasUsers user) throws Exception;
    
    /**
     * 
    * @Title: updateVipInfor 
    * @Description: TODO(会员维修、购买配件积分) 
    * @param @param car_License           车牌号
    * @param @param car_Vin               车架号
    * @param @param stockType             积分类型 （维修/销售） 父节点   Contstants.INTEGRALTYPE.INTEGRALTYPE
    * @param @param vipIntegral           积分
    * @param @param workId                维修单编号/销售单编号
    * @param @param slipId                维修结算单编号/销售结算单编号
    * @param @param stockRemark           备注
    * @param @param user                  登陆用户
    * @param @return
    * @param @throws Exception            设定文件 
    * @return boolean                     返回类型 
    * @throws
     */
    public Message updateVipInfor(String car_License, String car_Vin, String stockType, Double vipIntegral, String workId, String slipId, String stockRemark, BasUsers user) throws Exception;
    
    /**
     * 
    * @Title: saveVipInforDefray 
    * @Description: TODO(会员付款) 
    * @param @param car_License      车牌号
    * @param @param car_Vin          车架号
    * @param @param defrayType       支付类型 （维修/销售） 父节点   Contstants.BATCHBALANCETYPE_TAG.BATCHBALANCETYPE_TAG
    * @param @param defrayAmount     支付金额
    * @param @param workId           维修单编号/销售单编号
    * @param @param slipId           维修结算单编号/销售结算单编号
    * @param @param remark           备注
    * @param @param user             登陆用户
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Message    返回类型 
    * @throws
     */
    public Message saveVipInforDefray(String car_License, String car_Vin, String defrayType, Double defrayAmount, String workId, String slipId, String remark, BasUsers user) throws Exception;
    
    /**
     * 
    *
    * @Title: getParentEnterpriseId 
    * @Description: TODO(获取父企业编号) 
    * @param @param user
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Integer    返回类型 
    * @throws
     */
    public Integer getParentEnterpriseId(BasUsers user) throws Exception;
}