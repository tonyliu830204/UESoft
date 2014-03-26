package com.syuesoft.fin.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.StSellPreclrVo;
import com.syuesoft.util.Json;

public interface StSellPreclrService
{

    /** 销售单信息 预加载 */
    public Json loadStSellOrderItemBySellmmId(
            StSellPreclrVo sspvo) throws Exception;

    /** 销售结算单 添加 */
    public void addStSellPreclr(StSellPreclrVo sspvo, List<StSellPreclrVo> list)
            throws Exception;

    /** 销售结算单汇总信息 删除 */
    public void deleteStSellPercharge(StSellPreclrVo sspvo) throws Exception;

    /** 销售结算单 修改 */
    public void updateStSellPreclr(StSellPreclrVo sspvo,
            List<StSellPreclrVo> list) throws Exception;

    /** 销售结算单汇总信息 预加载 */
    public Datagrid loadStSellPreclrMain(StSellPreclrVo sspvo)
            throws Exception;

    /** 销售结算单汇总信息 综合查询 */
    public List<StSellPreclrVo> searchStSellPreclrMainByCondition(
            StSellPreclrVo sspvo) throws Exception;

    /** 根据客户名称查找销售单信息 */
    public Json loadSellOrderInfo(StSellPreclrVo spvo)
            throws Exception;

    /** 转收银 */
    public void changePaid(StSellPreclrVo sspvo) throws Exception;

}
