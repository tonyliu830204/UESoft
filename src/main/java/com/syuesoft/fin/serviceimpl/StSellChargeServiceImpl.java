package com.syuesoft.fin.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.DefaultDAO;
import com.syuesoft.fin.dao.StSellChargeDao;
import com.syuesoft.fin.dao.StSellPreclrMainDao;
import com.syuesoft.fin.dao.StSellchargeItemDao;
import com.syuesoft.fin.service.StSellChargeService;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.StSellCharge;
import com.syuesoft.model.StSellChargeitem;
import com.syuesoft.util.Utils;

/**
 * 销售应收款ServiceImpl
 * 
 * @author SuMing
 */
public class StSellChargeServiceImpl implements StSellChargeService
{

    @Autowired
    StSellPreclrMainDao stSellPreclrMainDao;

    @Autowired
    StSellChargeDao stSellChargeDao;

    @Autowired
    StSellchargeItemDao stSellchargeItemDao;

    @Autowired
    DefaultDAO defaultDao;

    /**
     * 销售结算单信息 预加载
     */
    
    public List<StSellChargeVo> loadStSellPreclr() throws Exception
    {
        return stSellPreclrMainDao.load_ssc_StSellreclr();
    }

    /**
     * 销售结算单明细信息 添加
     */
    @SuppressWarnings("unchecked")
    
    public void addStSellChargeItem(StSellChargeVo stSellChargeVo)
            throws Exception
    {
        StSellCharge ssc = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        double totalAmont = ssc.getTotalAmount();
        totalAmont += new BaseAction().doubleFormat(Double
                .parseDouble(stSellChargeVo.getPaidAmount()));
        ssc.setTotalAmount(totalAmont);
        stSellChargeDao.merge(ssc);
        StSellChargeitem ssct = new StSellChargeitem();
        ssct.setStSellCharge(ssc);
        ssct.setSscDate(Utils.dateFormat(stSellChargeVo.getPaidDate(), false));
        List<BasChilddictionary> bcList = defaultDao
                .getObjList("from BasChilddictionary bc where bc.dataValue='"
                        + stSellChargeVo.getPatment() + "'");
        if (bcList != null && bcList.size() > 0)
        {
            ssct.setPaymentId(bcList.get(0).getChildId() + "");
        }
        ssct.setSscPaymentMoney(Double.parseDouble(stSellChargeVo
                .getPaidAmount()));
        List<BasStuff> bsList = defaultDao
                .getObjList("from BasStuff bs where bs.stfName='"
                        + stSellChargeVo.getPaidPerson() + "'");
        if (bsList != null && bsList.size() > 0)
        {
            ssct.setStfId(Short.parseShort(bsList.get(0).getStfId() + ""));
        }
        ssct.setSscRemark(stSellChargeVo.getRemark());
        stSellchargeItemDao.save(ssct);
    }

    /**
     * 销售应收款信息删除
     */
    public void deleteStSellCharge(StSellChargeVo stSellChargeVo)
            throws Exception
    {
        StSellCharge ssc = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        ssc.setState(false);
        stSellChargeDao.merge(ssc);
    }

    /**
     * 修改为审核
     */
    public void examine(StSellChargeVo stSellChargeVo) throws Exception
    {
        StSellCharge ssc = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        ssc.setVerifyState("已审核");
        ssc.setVerifyDate(Utils.dateFormat(Utils.getCurrentTime()));
        stSellChargeDao.merge(ssc);
    }

    /**
     * 获取销售应收款汇总已收款金额合计
     */
    public StSellChargeVo searchStSellChargeByChargerId(
            StSellChargeVo stSellChargeVo) throws Exception
    {
        StSellCharge stSellCharge = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        StSellChargeVo sscvo = new StSellChargeVo();
        sscvo.setTotalAmount(stSellCharge.getTotalAmount() + "");
        return sscvo;
    }

    /**
     * 修改为未审核
     */
    public void cancelExamine(StSellChargeVo stSellChargeVo) throws Exception
    {
        StSellCharge ssc = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        ssc.setVerifyState("未审核");
        stSellChargeDao.merge(ssc);
    }

    /**
     * 删除付款
     */
    public void deletePaid(StSellChargeVo stSellChargeVo) throws Exception
    {
        StSellChargeitem ssci = stSellchargeItemDao
                .get(" from StSellChargeitem ssc where ssc.collectId="
                        + stSellChargeVo.getCollectId() + "");
        stSellchargeItemDao.delete(ssci);
        StSellCharge ssc = stSellChargeDao
                .get("from StSellCharge ssc where ssc.chargeId='"
                        + stSellChargeVo.getChargeId() + "'");
        double totalAmount = ssc.getTotalAmount();
        totalAmount -= new BaseAction().doubleFormat(Double
                .parseDouble(stSellChargeVo.getPaidAmount()));
        ssc.setTotalAmount(totalAmount);
        stSellChargeDao.merge(ssc);
    }

    /**
     * 销售应收款明细 预加载
     */
    
    public List<StSellChargeVo> loadStSellchargeItemByChargeId(
            StSellChargeVo stSellChargeVo) throws Exception
    {
        return stSellchargeItemDao
                .loadStSellchargeItemByChargeId(stSellChargeVo.getChargeId());
    }

    /**
     * 销售营收款汇总信息 预加载
     */
    public List<StSellChargeVo> loadStSellCharge(StSellChargeVo stSellChargeVo)
            throws Exception
    {
        return stSellChargeDao.loadsStSellCharge(stSellChargeVo.getPage(),
                stSellChargeVo.getRows());
    }

    /**
     * 销售营收款汇总信息 综合查询
     */
    public List<StSellChargeVo> searchStSellChargeByCondition(
            StSellChargeVo stSellChargeVo) throws Exception
    {
        return stSellChargeDao.searchStSellChargeByCondition(stSellChargeVo
                .getVerifyDate(), stSellChargeVo.getChargeId(), stSellChargeVo
                .getPreclrId(), stSellChargeVo.getStockOut(), stSellChargeVo
                .getVerifyState());
    }

}
