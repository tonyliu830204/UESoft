package com.syuesoft.fin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.fin.dao.StVendorAccountDao;
import com.syuesoft.fin.service.RelcampBalanceOfAccountService;
import com.syuesoft.fin.vo.RelcampBalanceOfAccountVo;
import com.syuesoft.model.StVendorAccount;
import com.syuesoft.st.dao.BasRelationCampanyDAO;
import com.syuesoft.util.Json;
import com.syuesoft.util.Utils;

@Service("relcampBalanceOfAccountService")
public class RelcampBalanceOfAccountServiceImpl extends BaseServiceImpl
        implements RelcampBalanceOfAccountService
{

    @Autowired
    BasRelationCampanyDAO basRelationCampanyDAO;

    @Autowired
    StVendorAccountDao stVendorAccountDao;

    /**
     * 财务模块 供应商对账 入退单汇总信息加载
     */
    public Json loadStRtGoods(
            RelcampBalanceOfAccountVo rboavo) throws Exception{
        return basRelationCampanyDAO.loadStRtGoods(rboavo.getRelcampId());
    }

    /**
     * 点击选择供应商加载对应供应商对账汇总信息
     */
    public RelcampBalanceOfAccountVo loadRelcampMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception
    {
        RelcampBalanceOfAccountVo vo = new RelcampBalanceOfAccountVo();
        List<RelcampBalanceOfAccountVo> resultList = this
                .loadStVendorAccountMain(rboavo);
        for (RelcampBalanceOfAccountVo relcampBalanceOfAccountVo : resultList)
        {
            if (rboavo.getRelcampId().equals(
                    relcampBalanceOfAccountVo.getRelcampId()))
            {
                vo.setSumTotalNum(relcampBalanceOfAccountVo.getSumTotalNum());
                vo.setSumTotalAmont(relcampBalanceOfAccountVo
                        .getSumTotalAmont());
                vo.setSumTaxCount(relcampBalanceOfAccountVo.getSumTaxCount());
                vo.setSumRecAmont(relcampBalanceOfAccountVo.getSumRecAmont());
                vo.setSumPaidAmont(relcampBalanceOfAccountVo.getSumPaidAmont());
                vo.setSumBalance(relcampBalanceOfAccountVo.getSumBalance());
            }
        }
        return vo;
    }

    /**
     * 根据入退单号获取供应商应付账款及剩余账款
     */
    @SuppressWarnings("unchecked")
    public RelcampBalanceOfAccountVo searchByStVendorAccount(
            RelcampBalanceOfAccountVo rboavo) throws Exception{
        RelcampBalanceOfAccountVo rbvo = new RelcampBalanceOfAccountVo();
        StringBuffer sb=new StringBuffer("SELECT SUM(st_vendor_account.NOW_PAID_AMOUNT) FROM st_vendor_account where 1=1 ");
        if(rboavo.getReceiptId()!=null&&!rboavo.getReceiptId().trim().equals(""))
        	sb.append(" AND st_vendor_account.RECEIPT_ID='"+rboavo.getReceiptId().trim()+"'");
        sb.append(" GROUP BY st_vendor_account.RELCAMP_ID"); 
        List list = stVendorAccountDao.createSQLQuery(sb.toString());
        if (list != null && list.size() == 1){// 供应商对账单中存在该入退单信息
            double paidAmount = new BaseAction().doubleFormat(Double.parseDouble(list.get(0).toString()));// 已付金额
            double recAmount = new BaseAction().doubleFormat(Double.parseDouble(rboavo.getTaxCount())- paidAmount);// 计算得到应付款
            rbvo.setPaidAmount(paidAmount + "");
            rbvo.setRecAmount(recAmount + "");
        }
        else{// 供应商对账单中不存在该入退单信息
        	rbvo.setPaidAmount(0.0 + "");
        	rbvo.setRecAmount(rboavo.getTaxCount());
        }
        return rbvo;
    }

    /**
     * 供应商对账单添加
     */
    @SuppressWarnings("unchecked")
    public void add(RelcampBalanceOfAccountVo rboavo) throws Exception
    {
        StVendorAccount sva = new StVendorAccount();
        StringBuffer sb=new StringBuffer("SELECT SUM(st_vendor_account.NOW_PAID_AMOUNT) FROM st_vendor_account WHERE 1=1 ");
        if(rboavo.getReceiptId()!= null&& !rboavo.getReceiptId().trim().equals(""))
        	sb.append(" AND st_vendor_account.RECEIPT_ID='"+rboavo.getReceiptId().trim()+"'");
        if(rboavo.getRelcampId()!= null&& !rboavo.getRelcampId().trim().equals(""))
            sb.append(" AND st_vendor_account.RELCAMP_ID="+rboavo.getRelcampId().trim());
    	sb.append(" GROUP BY st_vendor_account.RECEIPT_ID");
        List resultList = stVendorAccountDao.createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() == 1)
            sva.setPaidAmount(Double.parseDouble(resultList.get(0).toString()));// 已付金额合计
        else
            sva.setPaidAmount(0.0);
        sva.setAccountDate(Utils.dateFormat(rboavo.getAccountDate()));
        sva.setAccountReceipt(rboavo.getAccountReceipt());
        sva.setReceiptId(rboavo.getReceiptId());
        sva.setRelcampId(Short.parseShort(rboavo.getRelcampId()));
        sva.setOperType(Short.parseShort(rboavo.getOperType()));
        sva.setRecAmount(Double.parseDouble(rboavo.getRecAmount()));
        sva.setVendorBalance(new BaseAction().doubleFormat(Double.parseDouble(rboavo.getRecAmount())- Double.parseDouble(rboavo.getNowPaidAmount())));
        sva.setNowPaidAmount(Double.parseDouble(rboavo.getNowPaidAmount()));
        sva.setStfId(Short.parseShort(rboavo.getStfId()));
        sva.setVendorRemark(rboavo.getVendorRemark());
        stVendorAccountDao.save(sva);
    }

    /**
     * 供应商对账单明细信息 预加载
     */
    public Json loadStVendorAccount(
            RelcampBalanceOfAccountVo rboavo) throws Exception{
        return stVendorAccountDao.loadStVendorAccount(rboavo.getRelcampId());
    }

    /**
     * 供应商对账单汇总 预加载
     */
    @SuppressWarnings("unchecked")
    public List<RelcampBalanceOfAccountVo> loadStVendorAccountMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception
    {
        List<RelcampBalanceOfAccountVo> list = stVendorAccountDao.loadStVendorAccountMain(rboavo.getRelcampId());
        if (list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                String sql = "SELECT SUM(st_vendor_account.NOW_PAID_AMOUNT) FROM st_vendor_account WHERE st_vendor_account.RELCAMP_ID="+ list.get(i).getRelcampId();
                List resultList = stVendorAccountDao.createSQLQuery(sql);
                if (resultList != null && resultList.size() == 1){
                    list.get(i).setSumPaidAmont(resultList.get(0).toString());
                    list.get(i).setSumBalance(new BaseAction().doubleFormat(Double.parseDouble(list.get(i).getSumTaxCount())- Double.parseDouble(list.get(i).getSumPaidAmont()))+"");
                }
            }
        }
        return list;
    }

    /**
     * 供应商未对账汇总 预加载
     */
    public Json loadNoPaidStVendorAccountMain(
            RelcampBalanceOfAccountVo rboavo) throws Exception
    {
        return stVendorAccountDao.loadNoPaidStVendorAccountMain(rboavo
                .getRelcampId(),rboavo.getEnterpriseId());
    }

    /**
     * 供应商对账单信息 删除
     */
    public void delete(RelcampBalanceOfAccountVo rboavo) throws Exception
    {
    	if(rboavo.getAccountIndex()!=null&&!rboavo.getAccountIndex().trim().equals(""))
            stVendorAccountDao.delete(
            		stVendorAccountDao.get(" from StVendorAccount sva " +
            				"where sva.accountIndex="+ rboavo.getAccountIndex().trim()));
    }

}
