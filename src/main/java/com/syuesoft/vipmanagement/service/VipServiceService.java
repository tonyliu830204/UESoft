package com.syuesoft.vipmanagement.service;

import java.util.List;

import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.vipmanagement.vo.VipServiceTreeGridVo;

public interface VipServiceService {
    
    //查询会员卡服务项目父数据
    public Json findVipMeal(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user)throws Exception;
    
    //通过meal_id查询判断该父项里面是否有子项
    public List<VipServiceTreeGridVo> getChildMenu(VipServiceTreeGridVo vipServiceTreeGridVo)throws Exception;
    
    //获取会员编号(下拉框)
    public Json getVipInfo(VipServiceTreeGridVo vipServiceTreeGridVo)throws Exception;
    
    //获取会员编号(下拉框)
    public List<ComboxVo> getVipId(VipServiceTreeGridVo vipServiceTreeGridVo)throws Exception;
    
  //查询服务套餐名称用户combox
    public List<ComboxVo> getMealName(int enterprise_id)throws Exception;
    
    //新增会员卡额外服务项目
    public Msg doAdd(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user)throws Exception;
    
	//修改会员套餐
    public Msg doUpdate(VipServiceTreeGridVo vipServiceTreeGridVo, BasUsers user)throws Exception;
    
	//删除会员卡服务套餐
	public Msg doDelete(VipServiceTreeGridVo vipServiceTreeGridVo)throws Exception;
	
	//根据会员编号查询套餐名称
    public List getMealNameById(String vipid)throws Exception;
}