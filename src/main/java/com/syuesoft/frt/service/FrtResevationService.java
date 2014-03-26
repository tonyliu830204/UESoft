package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.frt.vo.FrtResevationVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.FrtResevation;
import com.syuesoft.model.FrtResvVehicleStructure;
import com.syuesoft.model.FrtRushToRepair;
import com.syuesoft.util.Msg;

public interface FrtResevationService
{

    public Datagrid datagridFrtResevation(Boolean flag, FrtResevationVo freVo)
            throws Exception; // 预约申请/保险估价单汇总datagrid

    public Datagrid addVehicleStructure(List<FrtResvVehicleStructure> list,
            FrtResevationVo freVo) throws Exception; // 添加车身状况(结构)

    public List<FrtResvVehicleStructure> removeVehicleStructure(
            FrtResevationVo freVo) throws Exception; // 删除车身状况(结构)

    public FrtCarVo getFrtCar(String carId) throws Exception;// 根据车档案编号查询车档案

    public Msg save(Boolean flag, FrtResevationVo freVo) throws Exception; // 保存预约申请/保险估价单

    public Msg edit(Boolean flag, FrtResevationVo freVo) throws Exception; // 修改预约申请/保险估价单

    public Msg remove(Boolean flag, String id) throws Exception; // 删除预约申请/保险估价单

    public Datagrid findPartsByResvId(String resvId,Integer enterpriseId) throws Exception; // 从数据库中查询维修配件

    public Datagrid findItemByResvId(String resvId,Integer id) throws Exception; // 从数据库中查询维修项目

    public FrtRushToRepair findServiceByResvId(String resvId) throws Exception; // 从数据库中抢修信息

    public Datagrid findVehicleStructure(FrtResevationVo freVo)
            throws Exception;// 查找车辆结构数据

    public Msg saveSwitchFrt(Boolean flag, FrtResevationVo freVo)
            throws Exception;// 转换预约申请/保险估价单为前台接车

    public List<FrtItemVo> addFrtResevationItem(FrtResevationVo freVo)
            throws Exception;// 增加自定义维修项目

    public List<FrtItemVo> findItemListByRpId(FrtResevationVo freVo)
            throws Exception; // 查询套餐项目并添加到预约/保险估价单中

    public List<PartsVo> findPartsListByRpId(FrtResevationVo freVo)
            throws Exception; // 查询套餐配件并添加到预约/估价单中

    public List<FrtResvVehicleStructure> findAllCarFrameWorks(
            FrtResevationVo freVo) throws Exception; // 添加车身状况(结构)

    public void isGoFactory(FrtResevation frv) throws Exception;// 更改预约申请/保险估价单状态

    public void modifyIsBespeakOff(FrtResevationVo freVo) throws Exception;// 判断预约申请/保险估价单是否为取消

    public Msg modifyIsBespeakOffById(FrtResevationVo freVo) throws Exception;// 取消预约申请/保险估价单
    
    public Msg findBespeakOffById(FrtResevationVo freVo) throws Exception;   // 判断某个预约申请/保险估价单是否取消

    public String getPrintHtml(Boolean tag,FrtResevationVo freVo, BasUsers user) throws Exception;//套打模版
   /* public FrtResevation findResevationById(String resvId)throws Exception;*/
}
