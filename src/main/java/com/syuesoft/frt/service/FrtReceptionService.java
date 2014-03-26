package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.CostVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

public interface FrtReceptionService
{
	/**工单管理汇总datagrid*/
    public Datagrid loadFrtReception(FrtReceptionVo freVo) throws Exception;
    
    public Datagrid datagridReception(FrtReceptionVo freVo) throws Exception; // 前台接车datagrid

    public Msg remove(String receptionId); // 删除接车单

    public Datagrid findPartsByRcptId(String rcptId) throws Exception; // 从数据库中查询维修配件

    public Datagrid findItemByRcptId(String rcptId) throws Exception; // 从数据库中查询维修项目

    public Datagrid findCostByRcptId(String rcptId) throws Exception; // 从数据库中查询其他费用明细

    public Msg save(FrtReceptionVo freVo) throws Exception; // 保存前台接车单

    public Msg edit(FrtReceptionVo freVo) throws Exception; // 修改前台接车单

    public List<CostVo> addFrtReceptionCost(FrtReceptionVo freVo)
            throws Exception; // 添加其他费用

    public Datagrid findVehicleStructure(FrtReceptionVo freVo) throws Exception;// 查找车辆结构数据

    public Msg modifyTransFormPlant(FrtReceptionVo freVo) throws Exception;// 将前台接车单转到车间

    public Msg modifyTransFormBalanace(FrtReceptionVo freVo) throws Exception;// 将前台接车单转到结算

    public Msg addFrtResvAdvice(FrtReceptionVo freVo) throws Exception;// 增加维修建议

    public Msg updateFrtResvAdvice(FrtReceptionVo freVo) throws Exception;// 更新维修建议

    public Msg deleteFrtResvAdvice(FrtReceptionVo freVo) throws Exception;// 删除维修建议

    public Datagrid findFrtResvAdviceByCarId(FrtReceptionVo freVo)
            throws Exception;// 查找维修建议

    public List totemoney(FrtReceptionVo freVo) throws Exception;// 计算费用总和

    public Datagrid findvehicleStructureListByResvId(FrtReceptionVo freVo)
            throws Exception;// 根据预约/保险估价单编号查找相应的车辆信息

    public List<FrtItemVo> addFrtReceptionItem(FrtReceptionVo freVo)
            throws Exception;// 增加自定义维修项目

    public Datagrid serviceRecord(FrtReceptionVo freVo) throws Exception;// 查找维修履历

    public Datagrid selectServiceWeave(FrtReceptionVo freVo) throws Exception;// 查找维修套餐

    public List<FrtItemVo> findItemListByRpId(FrtReceptionVo freVo)
            throws Exception; // 查询套餐项目并添加到接车单中

    public List<PartsVo> findPartsListByRpId(FrtReceptionVo freVo)
            throws Exception; // 查询套餐配件并添加到接车单中

    public Msg modifyDistance(FrtReceptionVo freVo) throws Exception;// 更改里程数

    public Msg findCarDistance(FrtReceptionVo freVo) throws Exception;// 获取车档案里程数

    public Boolean isDistanceTrue(FrtReceptionVo freVo) throws Exception;// 判断里程数是否符合
    
    public Msg findLastService(FrtReceptionVo freVo) throws Exception;// 查询前台接车提醒信息
    
    public Msg bespeakClew(FrtReceptionVo freVo) throws Exception;// 查询车辆预约信息
    
    public Datagrid completeAnalyse(FrtReceptionVo freVo) throws Exception;// 完工分析查询
    
    public Datagrid rcptBranchDatagrid(FrtReceptionVo freVo) throws Exception;// 接车分部管理查询
    
    public Msg editRcptBranch(FrtReceptionVo freVo) throws Exception;// 接车分部管理修改
    
    public String getSetFinishTime(FrtReceptionVo freVo) throws Exception;// 设置前台预计完工时间
    
    /**前台接车接待员业务统计 */
    public Json loadSearchReceptorWork(FrtReceptionVo frtReceptionVo)throws Exception;
    
}
