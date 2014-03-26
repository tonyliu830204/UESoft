package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtReceptionVehicleStructure;
import com.syuesoft.util.Msg;

public interface FrtWorkshopManagerService
{

    public Datagrid datagridFrtWorkshop(FrtReceptionVo freVo) throws Exception; // 车间管理datagrid

    public Msg remove(String receptionId) throws Exception; // 删除工单

    public Datagrid findPartsByRcptId(String rcptId) throws Exception; // 从数据库中查询维修配件

    public Datagrid findItemByRcptId(String rcptId) throws Exception; // 从数据库中查询维修项目

    public Msg edit(FrtReceptionVo freVo) throws Exception; // 修改工单

    public Datagrid findVehicleStructure(FrtReceptionVo freVo) throws Exception;// 查找车辆结构数据

    public List<FrtItemVo> addFrtWorkshopManagerItem(FrtReceptionVo freVo)
            throws Exception;// 增加自定义维修项目

    public Datagrid datagridEmerge(FrtReceptionVo freVo) throws Exception;// 出料查询

    public Msg modifyCastProcenium(FrtReceptionVo freVo) throws Exception;// 转前台
    
    public Msg modifyReceptionStatus(FrtReceptionVo freVo) throws Exception;// 更改工单状态

}
