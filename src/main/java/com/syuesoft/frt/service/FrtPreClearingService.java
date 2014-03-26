package com.syuesoft.frt.service;

import java.io.Serializable;
import java.util.List;

import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.CostVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtPreClearingVo;
import com.syuesoft.util.Msg;

public interface FrtPreClearingService
{

    public Datagrid datagridFrtPreClearing(FrtPreClearingVo fpcVo)
            throws Exception; // 交车结算datagrid

    public Msg remove(String preclrId) throws Exception; // 删除交车结算单

    public Msg edit(FrtPreClearingVo fpcVo) throws Exception; // 更新交车结算单

    public Msg modifyBack(FrtPreClearingVo fpcVo) throws Exception; // 返工至车间

    public Datagrid findPrePartsById(String preclrId) throws Exception; // 根据交车结算单id查询材料清单

    public Datagrid findPreItemById(String preclrId) throws Exception; // 根据交车结算单id查询维修清单

    public Datagrid findPreCostById(String preclrIdt) throws Exception; // 根据结算单号查找其他费用信息

    public Msg modifyTransformMoney(FrtPreClearingVo fpcVo) throws Exception;// 前台结算转收银

    public Msg modifyTransFormReceptionState(FrtPreClearingVo fpcVo)
            throws Exception;// 转洗车或待交车

    public List totemoney(FrtPreClearingVo fpcVo) throws Exception;// 计算费用总和
    
    /** 判断结算单是否已收银*/
    public boolean doIsExist(FrtPreClearingVo fpcVo)throws Exception;
}
