package com.syuesoft.fin.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.StSellChargeDao;
import com.syuesoft.fin.dao.StSellPreclrDetailDao;
import com.syuesoft.fin.dao.StSellPreclrMainDao;
import com.syuesoft.fin.service.StSellPreclrService;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.StSellPreclrVo;
import com.syuesoft.model.StSellCharge;
import com.syuesoft.model.StSellPreclrDetail;
import com.syuesoft.model.StSellPreclrMain;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.st.dao.StSellOrderitemDAO;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

/**
 * 销售结算ServiceImpl SuMing
 */
@Service("stSellPreclrService")
public class StSellPreclrServiceImpl implements StSellPreclrService
{

    @Autowired
    StSellOrderitemDAO stSellOrderitemDAO;

    @Autowired
    StSellPreclrMainDao stSellPreclrMainDao;

    @Autowired
    StSellPreclrDetailDao stSellPreclrDetailDao;

    @Autowired
    StSellOrderDAO stSellOrderDAO;

    @Autowired
    StSellChargeDao stSellChargeDao;

    /**
     * 销售单信息 预加载
     */
    public Json loadStSellOrderItemBySellmmId(
            StSellPreclrVo sspvo) throws Exception{
        return stSellOrderitemDAO
                .loadStSellOrderItemBySellmmId(sspvo.getSellmmId());
    }

    /**
     * 销售结算单 添加
     */
    public void addStSellPreclr(StSellPreclrVo sspvo, List<StSellPreclrVo> list)
            throws Exception
    {
        // 添加销售结算汇总信息
        StSellPreclrMain sspm = new StSellPreclrMain();
        sspm.setPreclrId(CreateID.createId("StSellPreclrMain", "SHJSHZ"));
        sspm.setPreclrDate(Utils.dateFormat(sspvo.getPreclrDate()));
        sspm.setCerNo(sspvo.getCerNo());
        sspm.setCustomId(sspvo.getCustomId());
        sspm.setPreclrNum(Integer.parseInt(sspvo.getPreclrNum()));
        sspm.setPreclrAmount(Double.parseDouble(sspvo.getPreclrAmount()));
        sspm.setPreclrCostAmount(Double
                .parseDouble(sspvo.getPreclrCostAmount()));
        sspm.setClassfication(sspvo.getClassfication());
        sspm.setRemark(sspvo.getRemark());
        sspm.setState(false);
        stSellPreclrMainDao.save(sspm);
        // 添加销售结算明细信息
        for (StSellPreclrVo stSellPreclrVo:list)
        {
            StSellPreclrDetail sspd = new StSellPreclrDetail();
            sspd.setStSellPreclrMain(sspm);
            sspd.setPartId(stSellPreclrVo.getPartsId());
            sspd.setPrice(Double.parseDouble(stSellPreclrVo.getSelldPrice()));
            sspd.setPartsnum(Integer.parseInt(stSellPreclrVo.getSelldCnt()));
            sspd.setPartamount(Double.parseDouble(stSellPreclrVo.getSelldAmount()));
            sspd.setCostprice(Double.parseDouble(stSellPreclrVo.getSelldCostPrice()));
            sspd.setCostamount(Double.parseDouble(stSellPreclrVo.getSelldCostAmount()));
            stSellPreclrDetailDao.save(sspd);
        }
    }

    /**
     * 销售结算单 修改
     */
    public void updateStSellPreclr(StSellPreclrVo sspvo,
            List<StSellPreclrVo> list) throws Exception
    {
        // 添加销售结算汇总信息
        StSellPreclrMain sspm = stSellPreclrMainDao
                .get("from StSellPreclrMain sspm where sspm.preclrId='"
                        + sspvo.getPreclrId() + "'");
        sspm.setPreclrDate(Utils.dateFormat(sspvo.getPreclrDate(), false));
        sspm.setCerNo(sspvo.getCerNo());
        sspm.setCustomId(sspvo.getCustomId());
        sspm.setPreclrNum(Integer.parseInt(sspvo.getPreclrNum()));
        sspm.setPreclrAmount(Double.parseDouble(sspvo.getPreclrAmount()));
        sspm.setPreclrCostAmount(Double.parseDouble(sspvo.getPreclrCostAmount()));
        sspm.setClassfication(sspvo.getClassfication());
        sspm.setRemark(sspvo.getRemark());
        stSellPreclrMainDao.merge(sspm);

        // 删除原有销售结算明细信息
        List<StSellPreclrDetail> resultList = stSellPreclrDetailDao
                .find(" from StSellPreclrDetail sspd where sspd.stSellPreclrMain.preclrId='"
                        + sspvo.getPreclrId() + "'");
        for (StSellPreclrDetail sspd:resultList){
            stSellPreclrDetailDao.delete(sspd);
        }
        // 添加销售结算明细信息
        for (StSellPreclrVo stSellPreclrVo:list)
        {
            StSellPreclrDetail sspd = new StSellPreclrDetail();
            sspd.setStSellPreclrMain(sspm);
            sspd.setPartId(stSellPreclrVo.getPartsId());
            sspd.setPrice(Double.parseDouble(stSellPreclrVo.getSelldPrice()));
            sspd.setPartsnum(Integer.parseInt(stSellPreclrVo.getSelldCnt()));
            sspd.setPartamount(Double.parseDouble(stSellPreclrVo.getSelldAmount()));
            sspd.setCostprice(Double.parseDouble(stSellPreclrVo.getSelldCostPrice()));
            sspd.setCostamount(Double.parseDouble(stSellPreclrVo.getSelldCostAmount()));
            stSellPreclrDetailDao.save(sspd);
        }
    }

    /**
     * 销售结算单汇总信息 删除
     */
    public void deleteStSellPercharge(StSellPreclrVo sspvo) throws Exception
    {
        List<StSellPreclrDetail> list = stSellPreclrDetailDao
                .find("from StSellPreclrDetail sspd where sspd.stSellPreclrMain.preclrId='"
                        + sspvo.getPreclrId() + "'");
        for (StSellPreclrDetail sspd : list){
            stSellPreclrDetailDao.delete(sspd);
        }
        StSellPreclrMain sspm = stSellPreclrMainDao
                .get("from StSellPreclrMain sspm where sspm.preclrId='"
                        + sspvo.getPreclrId() + "'");
        stSellPreclrMainDao.delete(sspm);
    }

    /**
     * 转收银
     */
    public void changePaid(StSellPreclrVo sspvo) throws Exception
    {
        // 添加销售结算汇总信息
        StSellPreclrMain sspm = stSellPreclrMainDao
                .get("from StSellPreclrMain sspm where sspm.preclrId='"
                        + sspvo.getPreclrId() + "'");
        sspm.setState(true);
        stSellPreclrMainDao.merge(sspm);
        if (sspvo.getState().equals("否"))
        {
            // 添加销售应收款汇总信息
            StSellCharge ssc = new StSellCharge();
            ssc.setChargeId(CreateID.createId("StSellCharge", "XSYSK"));
            ssc.setPreclrId(sspm.getPreclrId());
            ssc.setStockOut(false);
            ssc.setPaidAmount(Double.parseDouble(sspvo.getPreclrAmount()));
            ssc.setTotalAmount(0.0);
            ssc.setPaidBalance(Double.parseDouble(sspvo.getPreclrAmount()));
            ssc.setVerifyState("未审核");
            ssc.setState(true);// 删除标志位 默认true为不删除
            ssc.setChargeUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
            ssc.setChargeSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
            ssc.setChargeBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
            stSellChargeDao.save(ssc);
        }
    }

    /**
     * 销售结算单汇总信息 预加载
     */
    
    public Datagrid loadStSellPreclrMain(StSellPreclrVo sspvo)
            throws Exception
    {
        return stSellPreclrMainDao.loadStSellPreclrMain(sspvo.getPage(), sspvo
                .getRows(),sspvo.getPreclrDateStart(),sspvo.getPreclrDateEnd(),sspvo.getEnterpriseId());
    }

    /**
     * 销售结算单汇总信息 综合查询
     */
    public List<StSellPreclrVo> searchStSellPreclrMainByCondition(
            StSellPreclrVo sspvo) throws Exception
    {
        return stSellPreclrMainDao.searchStSellPreclrMainByCondition(sspvo
                .getPreclrDateStart(), sspvo.getPreclrDateEnd(), sspvo
                .getPreclrId(), sspvo.getCustomId(), sspvo.getClassfication(),
                sspvo.getCerNo(), sspvo.getState());
    }

    /**
     * 根据客户名称查找销售单信息
     */
	public Json loadSellOrderInfo(StSellPreclrVo spvo)
			throws Exception {
		return stSellOrderDAO.searchByCondition(0, 0,
				null, null,null,null, null,null, spvo.getCustomId(),null,spvo.getEnterpriseId());
	}

}
