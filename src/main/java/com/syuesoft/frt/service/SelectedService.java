package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.base.vo.ItemVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.model.FrtRushToRepair;

public interface SelectedService
{

    public List<PartsVo> acceptChangesParts(String inserted, String deleted,
            String updated) throws Exception; // 提交增删改的维修配件

    public List<PartsVo> acceptChangesParts(PartsVo parts) throws Exception; // 提交增删改的维修配件

    public List<ItemVo> acceptChangesItem(String inserted, String deleted,
            String updated) throws Exception; // 提交增删改的维修项目

    public void removeParts(String id, List<PartsVo> selectedPartsList)
            throws Exception; // 从session中移除配件

    public void removeItem(String id, List<ItemVo> selectedItemList)
            throws Exception; // 从session中移除项目

    public FrtRushToRepair findrushToRepair(String resvId) throws Exception;// 查找抢修信息

    public void updaterushToRepair(FrtRushToRepair frtRushToRepair)
            throws Exception;// 更新抢修信息
}
