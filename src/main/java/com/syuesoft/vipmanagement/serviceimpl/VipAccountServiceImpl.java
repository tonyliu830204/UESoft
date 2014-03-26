package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.service.EnterpriseInfoService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipInforAccount;
import com.syuesoft.model.BasVipInforAccountDetail;
import com.syuesoft.model.BasVipInforAccountMoneyDetail;
import com.syuesoft.model.BasVipInforDefray;
import com.syuesoft.model.BasVipRecharge;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.dao.VipAccountDao;
import com.syuesoft.vipmanagement.dao.VipInforDefrayDao;
import com.syuesoft.vipmanagement.dao.VipRechargeDao;
import com.syuesoft.vipmanagement.service.VipAccountService;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.VipAccountVo;

/** 
 * @ClassName: VipAccountServiceImpl 
 * @Description: TODO(会员对账单) 
 * @author HeXin 
 * @date 2013-12-19 下午03:22:48 
 * @version 1.0 
 */
@Service("vipAccountService")
public class VipAccountServiceImpl extends BaseLogServiceImpl implements VipAccountService
{
    @Autowired
    private VipAccountDao vipAccountDao;
    @Autowired
    private VipRecordMessageService vipRecordMessageService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private EnterpriseInfoService enterpriseInfoService;
    @Autowired
    private VipRechargeDao vipRechargeDao;
    @Autowired
    private VipInforDefrayDao vipInforDefrayDao;
    
    public Json findAll(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Json json = new Json();
        List<VipAccountVo> list = new ArrayList<VipAccountVo>();
        //String sql = "SELECT A.* FROM vip_account A where A.parentEnterprise_Id="+vipRecordMessageService.getParentEnterpriseId(users)+
        String sql = "SELECT A.* FROM vip_account A where A.logout='"+Contstants.ZXQKF.INSERVICE+"'";
        if(vipAccountVo.getAccountStartDate() != null && !"".equals(vipAccountVo.getAccountStartDate())){
            sql += " and A.accountStart_Date>='"+StringEscapeUtils.escapeSql(vipAccountVo.getAccountStartDate())+"'";
        }
        if(vipAccountVo.getAccountEndDate() != null && !"".equals(vipAccountVo.getAccountEndDate())){
            sql += " and A.accountEnd_Date<='"+StringEscapeUtils.escapeSql(vipAccountVo.getAccountEndDate())+"'";
        }
        if(vipAccountVo.getAccountDate() != null && !"".equals(vipAccountVo.getAccountDate())){
            sql += " and A.account_Date>='"+StringEscapeUtils.escapeSql(vipAccountVo.getAccountDate())+"'";
        }
        if(vipAccountVo.getAccountDate2() != null && !"".equals(vipAccountVo.getAccountDate2())){
            sql += " and A.account_Date<='"+StringEscapeUtils.escapeSql(vipAccountVo.getAccountDate2())+"'";
        }
        if(vipAccountVo.getLogout() != null && !"".equals(vipAccountVo.getLogout())){
            if(!vipAccountVo.getLogout().equals(Contstants.ZXQKF.ALL))
                 sql +=" and A.parentStagedataKey ='"+Contstants.ZXQKF.ZXQKF+"'";
        }
        int total = vipAccountDao.getCountBySQL(sql);
        List<Object[]> flist = vipAccountDao.createSQLQuery(sql, vipAccountVo.getPage(), vipAccountVo.getRows());
        if(flist != null && flist.size() > 0){
            for(Object[] obj : flist){
                VipAccountVo vo = new VipAccountVo();
                if(obj[0] != null){vo.setAccountId(obj[0].toString());}
                if(obj[1] != null){vo.setAccountStartDate(FormatTime.timestamp2Str((Timestamp) obj[1]));}
                if(obj[2] != null){vo.setAccountEndDate(FormatTime.timestamp2Str((Timestamp) obj[2]));}
                if(obj[3] != null){vo.setAccountDate(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                if(obj[4] != null){vo.setAccountPerson(obj[4].toString());}
                if(obj[5] != null){vo.setEnterpriseId(obj[5].toString());}
                if(obj[6] != null){vo.setParentEnterpriseId(obj[6].toString());}
                if(obj[7] != null){vo.setIncomeAmount(obj[7].toString());}
                if(obj[8] != null){vo.setDefrayAmount(obj[8].toString());}
                if(obj[9] != null){vo.setRemark(obj[9].toString());}
                if(obj[10] != null){vo.setAccountPersonName(obj[10].toString());}
                if(obj[11] != null){vo.setLogout(obj[11].toString());}
                if(obj[12] != null){vo.setLogoutName(obj[12].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }

    public Json getAccountDetail(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Json json = new Json();
        List<VipAccountVo> list = new ArrayList<VipAccountVo>();
        //String sql = "SELECT A.* FROM vip_accountdetail A where A.parentEnterprise_Id="+vipRecordMessageService.getParentEnterpriseId(users)+
        String sql = "SELECT A.* FROM vip_accountdetail A where A.account_Id="+vipAccountVo.getAccountId()+
                     " and A.parentdataKey='"+Contstants.AUDIT_TAG.AUDITKEY+"'" +
                     " and A.parentStagedataKey='"+Contstants.SYSTEMUSER.PSTFYES+"'";
        int total = vipAccountDao.getCountBySQL(sql);
        List<Object[]> flist = vipAccountDao.createSQLQuery(sql, vipAccountVo.getPage(), vipAccountVo.getRows());
        if(flist != null && flist.size() > 0){
            for(Object[] obj : flist){
                VipAccountVo vo = new VipAccountVo();
                if(obj[0] != null){vo.setEnterpriseId(obj[0].toString());}
                if(obj[1] != null){vo.setParentEnterpriseId(obj[1].toString());}
                if(obj[2] != null){vo.setAccountDate(FormatTime.timestamp2Str((Timestamp) obj[2]));}
                if(obj[3] != null){vo.setAccountPerson(obj[3].toString());}
                if(obj[4] != null){vo.setAccountPersonName(obj[4].toString());}
                if(obj[5] != null){vo.setDetailId(obj[5].toString());}
                if(obj[6] != null){vo.setAccountId(obj[6].toString());}
                if(obj[7] != null){vo.setVipId(obj[7].toString());}
                if(obj[8] != null){vo.setStoredValueAmount(obj[8].toString());}
                if(obj[9] != null){vo.setExpenditureAmount(obj[9].toString());}
                if(obj[10] != null){vo.setRefundAmount(obj[10].toString());}
                if(obj[11] != null){vo.setReceiptAccount(obj[11].toString());}
                if(obj[12] != null){vo.setRefundedAmount(obj[12].toString());}
                if(obj[13] != null){vo.setReceiptedAccount(obj[13].toString());}
                if(obj[14] != null){vo.setFinalStage(obj[14].toString());}
                if(obj[15] != null){vo.setRecAuditOper(obj[15].toString());}
                if(obj[16] != null){vo.setRecAuditDate(obj[16].toString());}
                if(obj[17] != null){vo.setRecAuditStatus(obj[17].toString());}
                if(obj[18] != null){vo.setRecAuditOperName(obj[18].toString());}
                if(obj[19] != null){vo.setCarLicense(obj[19].toString());}
                if(obj[20] != null){vo.setCarVin(obj[20].toString());}
                if(obj[21] != null){vo.setVipNumber(obj[21].toString());}
                if(obj[22] != null){vo.setVipName(obj[22].toString());}
                if(obj[23] != null){vo.setRecAuditStatusName(obj[23].toString());}
                if(obj[24] != null){vo.setFinalStageValue(obj[24].toString());}
                //obj[25]审核状态
                //obj[26]使用情况
                if(obj[27] != null){vo.setLogout(obj[27].toString());}
                if(obj[28] != null){vo.setOpenterpriseId(obj[28].toString());}
                if(obj[29] != null){vo.setOpenterpriseName(obj[29].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    public Json getAccountMoneyDetail(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Json json = new Json();
        List<VipAccountVo> list = new ArrayList<VipAccountVo>();
        //String sql = "SELECT A.* FROM vip_accountmoneydetail A where A.parentEnterprise_Id="+vipRecordMessageService.getParentEnterpriseId(users)+
        String sql = "SELECT A.* FROM vip_accountmoneydetail A where A.detail_Id="+vipAccountVo.getDetailId()+" and A.parentdataKey='"+Contstants.ACCOUNTTYPE.ACCOUNTTYPE+"'"+
        " and A.parentWaydataKey='"+Contstants.PAIDWAY.PAIDWAYKRY+"'";
        int total = vipAccountDao.getCountBySQL(sql);
        List<Object[]> flist = vipAccountDao.createSQLQuery(sql, vipAccountVo.getPage(), vipAccountVo.getRows());
        if(flist != null && flist.size() > 0){
            for(Object[] obj : flist){
                VipAccountVo vo = new VipAccountVo();
                if(obj[0] != null){vo.setDetailMoneyId(obj[0].toString());}
                if(obj[1] != null){vo.setDetailId(obj[1].toString());}
                if(obj[2] != null){vo.setDetailgatheringAccount(obj[2].toString());}
                if(obj[3] != null){vo.setDetailaccountDate(FormatTime.timestamp2Str((Timestamp) obj[3]));}                
                if(obj[4] != null){vo.setDetailaccountPerson(obj[4].toString());}                
                if(obj[5] != null){vo.setDetailaccountPersonName(obj[5].toString());}
                if(obj[6] != null){vo.setDetailremark(obj[6].toString());}
                if(obj[7] != null){vo.setReceiptAccount(obj[7].toString());}
                if(obj[8] != null){vo.setRefundAmount(obj[8].toString());}
                if(obj[9] != null){vo.setEnterpriseId(obj[9].toString());}
                if(obj[10] != null){vo.setParentEnterpriseId(obj[10].toString());}
                if(obj[11] != null){vo.setLogout(obj[1].toString());}
                //obj[12]账目出入方式
                if(obj[13] != null){vo.setDetailaccountType(obj[13].toString());}
                if(obj[14] != null){vo.setDetailaccountTypeName(obj[14].toString());}
                //obj[15]收款方式
                if(obj[16] != null){vo.setDetailaccountWay(obj[16].toString());}
                if(obj[17] != null){vo.setDetailaccountWayName(obj[17].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    public Message getMaxAccountEndDate(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Message msg = new Message();
        //String hql = "SELECT new BasVipInforAccount(MAX(A.accountEndDate)) FROM BasVipInforAccount A where A.parentEnterpriseId ='"+vipRecordMessageService.getParentEnterpriseId(users)+"'"+
        String hql = "SELECT new BasVipInforAccount(MAX(A.accountEndDate)) FROM BasVipInforAccount A where A.logout='"+Contstants.ZXQKF.INSERVICE+"'";
        List<BasVipInforAccount> flist = vipAccountDao.find(hql);
        if(flist != null && flist.size() > 0){
            BasVipInforAccount obj = flist.get(0);
            if(obj != null){
                Date endDate = obj.getAccountEndDate();
                msg.setObj(FormatTime.dateToStringTwo(endDate));
                msg.setSuccess(true);
            }
        }
        return msg;
    }
    
    public Message saveAccount(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Message msg = new Message();
        if(vipAccountVo.getAccountStartDate() != null && !"".equals(vipAccountVo.getAccountStartDate()) && vipAccountVo.getAccountEndDate() != null && !"".equals(vipAccountVo.getAccountEndDate())){
            String accountStartDate = vipAccountVo.getAccountStartDate();
            String accountEndDate = vipAccountVo.getAccountEndDate();
            Integer parentEnterpriseId = users.getBasStuff().getEnterpriseInfo().getParentEnterpriseId();
            List<EnterpriseInfo> list = enterpriseInfoService.findEnterpriseInfoChild(parentEnterpriseId.toString());
            if(list != null){
                BasVipInforAccount account = new BasVipInforAccount();
                account.setEnterpriseId(users.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                account.setParentEnterpriseId(vipRecordMessageService.getParentEnterpriseId(users));
                account.setAccountStartDate(FormatDate.getStartData(accountStartDate));
                account.setAccountEndDate(FormatDate.getEndData(accountEndDate));
                account.setAccountDate(new Date());
                account.setAccountPerson(users.getBasStuff().getStfId().toString());
                account.setRemark(vipAccountVo.getRemark());
                recharge(list, account, msg);
                defray(list, account, msg);
                List<BasChilddictionary> childs1 = baseService.getBasChilddictionary(Contstants.ZXQKF.ZXQKF);
                if(childs1 != null && childs1.size() > 0){
                    for(int i=0; i<childs1.size(); i++){
                        BasChilddictionary c = childs1.get(i);
                        if(c.getDataKey().equals(Contstants.ZXQKF.INSERVICE)){
                            account.setLogout(Contstants.ZXQKF.INSERVICE);   //注销
                            break;
                        }
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("数据字典数据不完整");
                    return msg;
                }
                
                vipAccountDao.saveOrUpdate(account);
                msg.setSuccess(true);
                msg.setMsg("保存成功");
            }else{
                msg.setSuccess(false);
                msg.setMsg("没有可统计的企业");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("数据不完整");
        }
        return msg;
    }

    public Message deleteAccount(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        return null;
    }

    public Message savePayMent(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Message msg = new Message();
        if(vipAccountVo.getAccountId() != null && !"".equals(vipAccountVo.getAccountId())){
            if(vipAccountVo.getDetailId() != null && !"".equals(vipAccountVo.getDetailId())){
                if(vipAccountVo.getDetailaccountWay() != null && !"".equals(vipAccountVo.getDetailaccountWay())){
                    if(vipAccountVo.getDetailgatheringAccount() != null && !"".equals(vipAccountVo.getDetailgatheringAccount()) && !"0.0".equals(vipAccountVo.getDetailgatheringAccount())){
                         BasVipInforAccount account = vipAccountDao.get("from BasVipInforAccount where accountId ="+vipAccountVo.getAccountId());
                         Set<BasVipInforAccountDetail> set = account.getBasVipInforAccountDetails();
                         if(set != null){
                             for(BasVipInforAccountDetail detail : set){
                                 if(detail.getDetailId().toString().trim().equals(vipAccountVo.getDetailId().trim())){
                                     BasVipInforAccountMoneyDetail moneyDetail = new BasVipInforAccountMoneyDetail();
                                     double detailgatheringAccount = Double.parseDouble(vipAccountVo.getDetailgatheringAccount());
                                     moneyDetail.setAccountDate(new Date());
                                     moneyDetail.setAccountPerson(users.getBasStuff().getStfId().toString());
                                     moneyDetail.setReceiptAccount(detailgatheringAccount);
                                     moneyDetail.setRemark(vipAccountVo.getDetailremark());
                                     List<BasChilddictionary> childs1 = baseService.getBasChilddictionary(Contstants.PAIDWAY.PAIDWAYKRY);
                                     if(childs1 != null && childs1.size() > 0){
                                         for(int i=0; i<childs1.size(); i++){
                                             BasChilddictionary c = childs1.get(i);
                                             if(c.getDataKey().equals(vipAccountVo.getDetailaccountWay())){
                                                 moneyDetail.setAccountWay(vipAccountVo.getDetailaccountWay());   //付款方式
                                                 break;
                                             }
                                         }
                                     }else{
                                         msg.setSuccess(false);
                                         msg.setMsg("数据字典数据不完整");
                                         return msg;
                                     }
                                     
                                     List<BasChilddictionary> childs2 = baseService.getBasChilddictionary(Contstants.ACCOUNTTYPE.ACCOUNTTYPE);
                                     if(childs2 != null && childs2.size() > 0){
                                         for(int i=0; i<childs2.size(); i++){
                                             BasChilddictionary c = childs2.get(i);
                                             if(detail.getRefundAmount() != 0.0d){
                                                 if(c.getDataKey().equals(Contstants.ACCOUNTTYPE.ACCOUNTTYPE2)){
                                                     moneyDetail.setAccountType(c.getDataKey());   //应退款额   ----  支出
                                                     break;
                                                 }
                                             }else if(detail.getReceiptAccount() != 0.0d){
                                                 if(c.getDataKey().equals(Contstants.ACCOUNTTYPE.ACCOUNTTYPE1)){
                                                     moneyDetail.setAccountType(c.getDataKey());   //应收款额 ----  收入
                                                     break;
                                                 }
                                             }
                                         }
                                     }else{
                                         msg.setSuccess(false);
                                         msg.setMsg("数据字典数据不完整");
                                         return msg;
                                     }
                                     if(!vipAccountVo.getRefundAmount().equals("0.0")){
                                         detail.setRefundedAmount(detail.getRefundedAmount()+detailgatheringAccount);
                                     }
                                     if(!vipAccountVo.getReceiptAccount().equals("0.0")){
                                         detail.setReceiptedAccount(detail.getReceiptedAccount()+detailgatheringAccount);
                                     }
                                     moneyDetail.setBasVipInforAccountDetail(detail);
                                     detail.getBasVipInforAccountMoneyDetails().add(moneyDetail);
                                     baseService.saveOrUpdate(detail);
                                     msg.setMsg("收款成功");
                                     msg.setSuccess(true);
                                     break;
                                 }
                             }
                         }
                    }else{
                        msg.setMsg("请输入付款金额");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("请选择付款方式");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("数据不完整");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("数据不完整");
            msg.setSuccess(false);
        }
        return msg;
    }

    public Message doUpdateState(VipAccountVo vipAccountVo, BasUsers users) throws Exception
    {
        Message msg = new Message();
        if(vipAccountVo.getDetailId() != null && !"".equals(vipAccountVo.getDetailId())){
            BasVipInforAccountDetail detail = (BasVipInforAccountDetail) baseService.get("from BasVipInforAccountDetail where detailId ="+vipAccountVo.getDetailId());
            
            List<BasChilddictionary> childss = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
            if(childss != null && childss.size() > 0){
                for(int i=0; i<childss.size(); i++){
                    BasChilddictionary c = childss.get(i);
                    if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                        detail.setRecAuditStatus(Contstants.AUDIT_TAG.AUDITYESS);   //审核状态
                        break;
                    }
                }
                detail.setRecAuditDate(new Date());
                detail.setRecAuditOper(users.getBasStuff().getStfId().toString());
                baseService.saveOrUpdate(detail);
                msg.setMsg("审核成功");
                msg.setSuccess(true);
            }else{
                msg.setSuccess(false);
                msg.setMsg("数据字典数据不完整");
            }
        }else{
            msg.setMsg("数据不完整");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    /**
     * 
    *
    * @Title: recharge 
    * @Description: TODO(会员充值) 
    * @param @param list
    * @param @param account
    * @param @param msg
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
    private void recharge(List<EnterpriseInfo> list, BasVipInforAccount account, Message msg) throws Exception
    {
        Double incomeAmount = 0.0d;
        for(EnterpriseInfo enterprise : list){
            Integer enterpriseId = enterprise.getEnterpriseId();
            //List<BasVipRecharge> list1 = vipRechargeDao.getList("from BasVipRecharge where enterpriseId="+enterpriseId);
            List<BasVipRecharge> list1 = vipRechargeDao.getList("from BasVipRecharge ");
            if(list1 != null){
                for(BasVipRecharge recharge : list1){
                    BasVipInforAccountDetail detail = new BasVipInforAccountDetail();
                    Double storedValueAmount = recharge.getGiveAmount() + recharge.getRecAmount();
                    BasVipInfor basVipInfor = recharge.getBasVipInfor();
                    detail.setStoredValueAmount(storedValueAmount);
                    detail.setExpenditureAmount(0.0d);
                    detail.setBasVipInfor(basVipInfor);
                    detail.setBasVipInforAccount(account);
                    detail.setEnterpriseId(enterpriseId);
                    detail.setRefundAmount(storedValueAmount);
                    detail.setTradeBill(recharge.getVipRecId().toString());
                    detail.setTradeDate(recharge.getVipRecDate());
                    detail.setReceiptAccount(0.0d);
                    detail.setRefundedAmount(0.0d);
                    detail.setReceiptedAccount(0.0d);
                    List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.SYSTEMUSER.PSTFYES);
                    if(childs != null && childs.size() > 0){
                        for(int i=0; i<childs.size(); i++){
                            BasChilddictionary c = childs.get(i);
                            if(c.getDataKey().equals(Contstants.SYSTEMUSER.STFNO)){
                                detail.setFinalStage(Contstants.SYSTEMUSER.STFNO);
                                break;
                            }
                        }
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("数据字典数据不完整");
                        break;
                    }
                    List<BasChilddictionary> childss = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                    if(childss != null && childss.size() > 0){
                        for(int i=0; i<childss.size(); i++){
                            BasChilddictionary c = childss.get(i);
                            if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                detail.setRecAuditStatus(Contstants.AUDIT_TAG.AUDITNOS);   //审核状态
                                break;
                            }
                        }
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("数据字典数据不完整");
                        break;
                    }
                    account.getBasVipInforAccountDetails().add(detail);
                    incomeAmount += storedValueAmount;
                }
            }
        }
        account.setIncomeAmount(incomeAmount);
    }
    
    /**
     * 
    *
    * @Title: defray 
    * @Description: TODO(会员储值使用) 
    * @param @param list
    * @param @param account
    * @param @param msg
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
    private void defray(List<EnterpriseInfo> list, BasVipInforAccount account, Message msg) throws Exception{
        Double defrayAmount = 0.0d;
        for(EnterpriseInfo enterprise : list){
            Integer enterpriseId = enterprise.getEnterpriseId();
            //List<BasVipInforDefray> list1 = vipInforDefrayDao.getList("from BasVipInforDefray where enterpriseId="+enterpriseId);
            List<BasVipInforDefray> list1 = vipInforDefrayDao.getList("from BasVipInforDefray ");
            if(list1 != null){
                for(BasVipInforDefray defray : list1){
                    Double expenditureAmount = defray.getDefrayAmount();
                    BasVipInfor basVipInfor = defray.getBasVipInfor();
                    BasVipInforAccountDetail detail = null;
                    Set<BasVipInforAccountDetail> set = account.getBasVipInforAccountDetails();
                    if(set != null && set.size() > 0){
                        for(BasVipInforAccountDetail detail1 : set){
                            if(detail1.getBasVipInfor().getVipId().equals(basVipInfor.getVipId()) && detail1.getEnterpriseId() == enterpriseId){
                                detail = detail1;
                                detail.setExpenditureAmount(expenditureAmount);
                                
                                Double storedValueAmount =  detail1.getStoredValueAmount();
                                if(storedValueAmount > expenditureAmount){
                                    detail.setRefundAmount(storedValueAmount - expenditureAmount);
                                    detail.setReceiptAccount(0.0d);
                                }else if(storedValueAmount == expenditureAmount){
                                    detail.setRefundAmount(0.0d);
                                    detail.setReceiptAccount(0.0d);
                                }else{
                                    detail.setRefundAmount(0.0d);
                                    detail.setReceiptAccount(expenditureAmount - storedValueAmount);
                                }
                                break;
                            }
                        }
                    }
                    if(detail == null){
                        detail = new BasVipInforAccountDetail();
                        detail.setStoredValueAmount(0.0d);
                        detail.setExpenditureAmount(expenditureAmount);
                        detail.setBasVipInfor(basVipInfor);
                        detail.setBasVipInforAccount(account);
                        detail.setEnterpriseId(enterpriseId);
                        detail.setRefundAmount(0.0d);
                        detail.setReceiptAccount(expenditureAmount);
                        detail.setTradeBill(defray.getDefrayId().toString());
                        detail.setTradeDate(defray.getDefrayDate());
                        detail.setRefundedAmount(0.0d);
                        detail.setReceiptedAccount(0.0d);
                        List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.SYSTEMUSER.PSTFYES);
                        if(childs != null && childs.size() > 0){
                            for(int i=0; i<childs.size(); i++){
                                BasChilddictionary c = childs.get(i);
                                if(c.getDataKey().equals(Contstants.SYSTEMUSER.STFNO)){
                                    detail.setFinalStage(Contstants.SYSTEMUSER.STFNO);
                                    break;
                                }
                            }
                        }else{
                            msg.setSuccess(false);
                            msg.setMsg("数据字典数据不完整");
                            break;
                        }
                        List<BasChilddictionary> childss = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                        if(childss != null && childss.size() > 0){
                            for(int i=0; i<childss.size(); i++){
                                BasChilddictionary c = childss.get(i);
                                if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                    detail.setRecAuditStatus(Contstants.AUDIT_TAG.AUDITNOS);   //审核状态
                                    break;
                                }
                            }
                        }else{
                            msg.setSuccess(false);
                            msg.setMsg("数据字典数据不完整");
                            break;
                        }
                        account.getBasVipInforAccountDetails().add(detail);
                    }
                    defrayAmount += expenditureAmount;
                }
            }
        }
        account.setDefrayAmount(defrayAmount);
    }
}