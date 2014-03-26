package com.syuesoft.frt.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FinClaimantMainVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.model.FinClaimantMainCost;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.Msg;

public interface FinClaimantMainService
{

    public Msg save(FinClaimantMainVo fcm) throws Exception;// 增加索赔信息

    public Msg edit(FinClaimantMainVo fcm) throws Exception;// 增加索赔信息

    public Msg delete(FinClaimantMainVo fcm) throws Exception;

    public List isExist(String receptionId) throws Exception;

    public Datagrid datagridFinClaimantMain(FinClaimantMainVo fcmVo)
            throws Exception;// 查找索理赔结算单

    public Datagrid findItemByReceptionId(FinClaimantMainVo fcmVo) throws Exception;// 根据维修工单号查找未索理赔项目

    public Datagrid findPartsByReceptionId(FinClaimantMainVo fcmVo) throws Exception;// 根据维修工单号查找未索赔配件

    public Datagrid findItemByFcmId(FinClaimantMainVo fcmVo) throws Exception;// 查找项目清单

    public Datagrid findPartsByFcmId(FinClaimantMainVo cmVo) throws Exception;// 查找材料清单

    public Datagrid findCostByReceptionId(FinClaimantMainVo fcmVo) throws Exception;//根据维修工单号查找其他费用

    public Datagrid findCostByFcmId(FinClaimantMainVo fcmVo) throws Exception;// 查找其他费用清单

    public List<FinClaimantMainCost> addFinClaimantMainCost(
            FinClaimantMainVo fcmVo) throws Exception; // 添加其他费用

    public Datagrid removeFinClaimantMainCost(List<FinClaimantMainCost> list,
            FinClaimantMainVo fcmVo) throws Exception; // 移除其他费用

    public Datagrid findIdeaByStatus(FinClaimantMainVo fcmVo) throws Exception;// 查找未确认的索赔结算单

    public Msg modifyTransformMoney(FinClaimantMainVo fcmVo) throws Exception;// 转索赔应收款

    public List totemoney(FinClaimantMainVo fcmVo) throws Exception;// 计算费用总和

    public List<FrtItemVo> addFinClaimantMainItem(FinClaimantMainVo fcmVo)
            throws Exception;// 增加自定义维修项目

    public Msg modifyClaimsValidateAsTrue(FinClaimantMainVo fcmVo)
            throws Exception;// 审核确认操作

    public Msg modifyClaimsValidateAsFalse(FinClaimantMainVo fcmVo)
            throws Exception;// 取消审核操作

    public Msg isClaimsValidate(FinClaimantMainVo fcmVo) throws Exception;// 校验审核操作
    
    public DatagridAnalyze claimCostContrastAnalyse(FinClaimantMainVo fcmVo) throws Exception;// 查找索赔成本对比分析数据
    
    public List claimCostContrastAnalyseChild(FinClaimantMainVo fcmVo) throws Exception;// 查找索赔成本对比分析-子项信息

}
