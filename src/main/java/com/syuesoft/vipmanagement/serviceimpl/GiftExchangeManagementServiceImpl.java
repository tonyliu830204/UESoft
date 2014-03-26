package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.syuesoft.model.BasVipInfor;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipGiftexchange;
import com.syuesoft.model.BasVipGiftexchangeDetail;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.dao.GiftExchangeManagementDao;
import com.syuesoft.vipmanagement.dao.VipGiftexchangeDetailDao;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.service.GiftExchangeManagementService;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.GiftExchangeManagementVo;

/**
 * 礼品兑换
* @ClassName: GiftExchangeManagementServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:48:26 
* @version 1.0
 */
@Service("giftExchangeManagementService")
public class GiftExchangeManagementServiceImpl extends BaseLogServiceImpl implements GiftExchangeManagementService {
	@Autowired
	private GiftExchangeManagementDao giftExchangeManagementDao;
	@Autowired
	private VipRecordMessageService vipRecordMessageService;
	@Autowired
    private BaseService baseService;
	@Autowired
    private VipRecordMessageDao vipRecordMessageDao;
	@Autowired
    private VipGiftexchangeDetailDao vipGiftexchangeDetailDao;
	/**
     * 礼品兑换汇总信息查询
     */
    public Json getGiftExchanJsongeInfo(
            GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user) throws Exception {
        Json json= new Json();
        List<GiftExchangeManagementVo> list = new ArrayList<GiftExchangeManagementVo>();
        
        String sql = "select A.*, B.VIP_STATUS_VALUE, C.JIFENSHENHE_VALUE from vip_giftexchange A"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue AS VIP_STATUS_VALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue AS JIFENSHENHE_VALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.AUDIT_TAG.AUDITKEY+"') C ON (A.audit_situation = C.dataKey)"+
        " where 1 = 1 ";
        //" where 1 = 1 and A.enterprise_id2 ='"+vipRecordMessageService.getParentEnterpriseId(user)+"'";
        if(giftExchangeManagementVo.getExchange_Date() != null && !"".equals(giftExchangeManagementVo.getExchange_Date())){
            sql += " and A.exchange_date >= '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getExchange_Date())+"'";
        }
        if(giftExchangeManagementVo.getExchange_Date2() != null && !"".equals(giftExchangeManagementVo.getExchange_Date2())){
            sql += " and A.exchange_date <= '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getExchange_Date2())+"'";
        }
        if(giftExchangeManagementVo.getEnd_Time() != null && !"".equals(giftExchangeManagementVo.getEnd_Time())){
            sql += " and A.END_TIME >= '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getEnd_Time())+"'";
        }
        if(giftExchangeManagementVo.getEnd_Time2() != null && !"".equals(giftExchangeManagementVo.getEnd_Time2())){
            sql += " and A.END_TIME <= '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getEnd_Time2())+"'";
        }
        if(giftExchangeManagementVo.getCar_License() != null && !"".equals(giftExchangeManagementVo.getCar_License())){
            sql += " and A.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getCar_License().trim())+"%'";
        }
        if(giftExchangeManagementVo.getCar_Vin() != null && !"".equals(giftExchangeManagementVo.getCar_Vin())){
            sql += " and A.CAR_VIN like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getCar_Vin().trim())+"%'";
        }
        if(giftExchangeManagementVo.getVip_Age() != null && !"".equals(giftExchangeManagementVo.getVip_Age())){
            sql += " and A.VIP_AGE = '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Age().trim())+"'";
        }
        if(giftExchangeManagementVo.getVip_Status() != null && !"".equals(giftExchangeManagementVo.getVip_Status())){
            sql += " and A.VIP_STATUS = '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Status().trim())+"'";
        }
        if(giftExchangeManagementVo.getVip_Name() != null && !"".equals(giftExchangeManagementVo.getVip_Name())){
            sql += " and A.VIP_NAME like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Name().trim())+"%'";
        }
        if(giftExchangeManagementVo.getVip_Tel() != null && !"".equals(giftExchangeManagementVo.getVip_Tel())){
            sql += " and A.VIP_TEL like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Tel().trim())+"%'";
        }
        if(giftExchangeManagementVo.getVip_Number() != null && !"".equals(giftExchangeManagementVo.getVip_Number())){
            sql += " and A.VIP_NUMBER like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Number().trim())+"%'";
        }
        if(giftExchangeManagementVo.getVip_Level_Id() != null && !"".equals(giftExchangeManagementVo.getVip_Level_Id())){
            sql += " and A.VIP_LEVEL_ID = '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Level_Id().trim())+"'";
        }
        if(giftExchangeManagementVo.getVip_Group_Id() != null && !"".equals(giftExchangeManagementVo.getVip_Group_Id())){
            sql += " and A.VIP_GRUOP_ID = '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getVip_Group_Id().trim())+"'";
        }
        if(giftExchangeManagementVo.getExchange_User() != null && !"".equals(giftExchangeManagementVo.getExchange_User())){
            sql += " and A.exchange_user like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getExchange_User().trim())+"%'";
        }
        if(giftExchangeManagementVo.getAudit_Situation() != null && !"".equals(giftExchangeManagementVo.getAudit_Situation())){
            sql += " and A.audit_situation = '"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getAudit_Situation().trim())+"'";
        }
        int total = giftExchangeManagementDao.getCountBySQL(sql);
        List<Object[]> rlist = giftExchangeManagementDao.createSQLQuery(sql, giftExchangeManagementVo.getPage(), giftExchangeManagementVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                GiftExchangeManagementVo vo = new GiftExchangeManagementVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setCar_License(obj[1].toString());}
                if(obj[2]!=null){vo.setCar_Vin(obj[2].toString());}
                if(obj[3]!=null){vo.setJoin_Time(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                if(obj[4]!=null){vo.setVip_Number(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Level_Id(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Level_Name(obj[6].toString());}
                if(obj[7]!=null){vo.setVip_Group_Id(obj[7].toString());}
                if(obj[8]!=null){vo.setVip_Group_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Status(obj[9].toString());}
                if(obj[10]!=null){vo.setEnd_Time(FormatTime.timestamp2Str((Timestamp) obj[10]));}
                if(obj[11]!=null){vo.setVip_Balance(obj[11].toString());}
                if(obj[12]!=null){vo.setVip_Birthday(FormatTime.timestamp2Str((Timestamp) obj[12]));}
                if(obj[13]!=null){vo.setVip_Tel(obj[13].toString());}
                if(obj[14]!=null){vo.setVip_Integral(obj[14].toString());}
                if(obj[15]!=null){vo.setVip_Total_Integral(obj[15].toString());}
                if(obj[16]!=null){vo.setVip_Age(obj[16].toString());}
                if(obj[17]!=null){vo.setVip_Name(obj[17].toString());}
                //obj[18]企业编号
                //obj[19]企业编号
                if(obj[20]!=null){vo.setExchange_Id(obj[20].toString());}
                if(obj[21]!=null){vo.setExchange_Date(FormatTime.timestamp2Str((Timestamp) obj[21]));}
                if(obj[22]!=null){vo.setAudit_Date(FormatTime.timestamp2Str((Timestamp) obj[22]));}
                if(obj[23]!=null){vo.setAudit_Situation(obj[23].toString());}
                if(obj[24]!=null){vo.setAmount(obj[24].toString());}
                if(obj[25]!=null){vo.setTotal_Score(obj[25].toString());}
                if(obj[26]!=null){vo.setAudit_Manager(obj[26].toString());}
                if(obj[27]!=null){vo.setAudit_ManagerName(obj[27].toString());}
                if(obj[28]!=null){vo.setOperator(obj[28].toString());}
                if(obj[29]!=null){vo.setOperatorName(obj[29].toString());}
                if(obj[30]!=null){vo.setExchange_User(obj[30].toString());}
                //obj[31]兑换企业编号
                if(obj[32]!=null){vo.setVip_Status_value(obj[32].toString());}
                if(obj[33]!=null){vo.setAudit_SituationName(obj[33].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    public Json getExchangeable(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user) throws Exception {
        Json json= new Json();
        List<GiftExchangeManagementVo> list = new ArrayList<GiftExchangeManagementVo>();
        String sql = "SELECT GIVE_INTE_PRO_ID,GIVE_INTE_PRO_NAME,GIVE_INTE_NUM,enterprise_id FROM bas_vip_give_integral_project WHERE 1 = 1";
        if(giftExchangeManagementVo.getProject_Name() != null && !"".equals(giftExchangeManagementVo.getProject_Name())){
            sql += " and GIVE_INTE_PRO_NAME like '%"+StringEscapeUtils.escapeSql(giftExchangeManagementVo.getProject_Name().trim())+"%'";
        }
        int total = giftExchangeManagementDao.getCountBySQL(sql);
        List<Object[]> rlist = giftExchangeManagementDao.createSQLQuery(sql, giftExchangeManagementVo.getPage(), giftExchangeManagementVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                GiftExchangeManagementVo vo = new GiftExchangeManagementVo();
                if(obj[0]!=null){vo.setProject_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setProject_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setProject_score(obj[2].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    @Log(moduleName="会员管理",content="新增会员礼品兑换明细记录",opertype="会员礼品兑换")
    public Message doAdd(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user) throws Exception {
        Message msg = new Message();
        String state = null;
        double totalScore=0.0;
        List<GiftExchangeManagementVo> inserted = null;
        List<BasVipGiftexchangeDetail> list = new ArrayList<BasVipGiftexchangeDetail>();
        if(giftExchangeManagementVo.getVip_Number() != null && !"".equals(giftExchangeManagementVo.getVip_Number())){
            BasVipInfor vipInfo = vipRecordMessageService.getBasVipInfor(giftExchangeManagementVo.getVip_Number(), vipRecordMessageService.getParentEnterpriseId(user));
            if(vipInfo != null){
                if(giftExchangeManagementVo.getData() != null && !"".equals(giftExchangeManagementVo.getData())){
                    JSONObject jsData = JSON.parseObject(giftExchangeManagementVo.getData());
                    inserted = JSON.parseArray(jsData.get("rows").toString(),GiftExchangeManagementVo.class);
                }
                if(inserted != null && inserted.size() > 0){
                    BasVipGiftexchange bvgc = new BasVipGiftexchange();
                    List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                    if(childs != null && childs.size() > 0){
                        for(int i=0; i<childs.size(); i++){
                            BasChilddictionary c = childs.get(i);
                            if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                state = Contstants.AUDIT_TAG.AUDITNOS;   //审核状态
                                break;
                            }
                        }
                    }
                    bvgc.setVipId(vipInfo.getVipId());
                    bvgc.setOperator(user.getBasStuff().getStfId().toString());
                    bvgc.setExchangeDate(new Date());
                    bvgc.setExchangeUser(giftExchangeManagementVo.getExchange_User());
                    bvgc.setAuditSituation(state);
                    bvgc.setEnterprise_id(user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString());
                    for(GiftExchangeManagementVo vo : inserted){
                        BasVipGiftexchangeDetail bvgd = new BasVipGiftexchangeDetail();
                        bvgd.setBasVipGiftexchange(bvgc);
                        bvgd.setExchangeQuantity(Double.parseDouble(vo.getExchange_Quantity()));
                        bvgd.setOneScore(Double.parseDouble(vo.getProject_score()));
                        bvgd.setProjectId(vo.getProject_Id());
                        bvgd.setTotalScore(Double.parseDouble(vo.getTotal_Score()));
                        totalScore += Double.parseDouble(vo.getTotal_Score());
                        list.add(bvgd);
                    }
                    bvgc.setTotal_score(totalScore);
                    double integral = vipInfo.getVipIntegral();
                    if(integral >= totalScore){
                        giftExchangeManagementDao.saveOrUpdate(bvgc);
                        if(list.size() > 0){
                            vipGiftexchangeDetailDao.saveOrUpdates(list);
                        }
                        msg.setSuccess(true);
                        msg.setMsg("新增礼品兑换信息成功");
                        setContent("会员礼品兑换  - 新增会员礼品兑换明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+giftExchangeManagementVo.getVip_Number());
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("您目前只有"+integral+"积分可用，不能兑换"); 
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("兑换项目不能为空"); 
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("该会员不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请先刷会员卡");
        }
        return msg;
    }
    
    /**
     * 通过兑换表的id 获取兑换明细信息
     */
    @SuppressWarnings("unchecked")
     public List getGiftExchangeDetail(
            GiftExchangeManagementVo giftExchangeManagementVo) throws Exception {
        List<GiftExchangeManagementVo> list = new ArrayList<GiftExchangeManagementVo>();
        String sql ="SELECT A.exchange_detail_id, A.PROJECT_ID, A.exchange_quantity, A.ONE_SCORE, A.total_SCORE, B.GIVE_INTE_PRO_NAME FROM bas_vip_giftexchange_detail A RIGHT JOIN bas_vip_give_integral_project B ON A.PROJECT_ID = B.GIVE_INTE_PRO_ID WHERE A.exchange_id='"+giftExchangeManagementVo.getExchange_Id()+"'";
        List<Object[]> flist = giftExchangeManagementDao.createSQLQuery(sql);
        if(flist != null && flist.size() > 0){
            for (int i=0; i<flist.size(); i++ ) {
                Object[] obj = flist.get(i);
                GiftExchangeManagementVo vo = new GiftExchangeManagementVo();
                vo.setExchange_Detail_Id(obj[0].toString());
                vo.setProject_Id(obj[1].toString());
                vo.setExchange_Quantity(obj[2].toString());
                vo.setProject_score(obj[3].toString());
                vo.setTotal_Score(obj[4].toString());
                vo.setProject_Name(obj[5].toString());
                list.add(vo);
            }
        }
        return list;
    }
    
    @Log(moduleName="会员管理",content="修改会员礼品兑换明细记录",opertype="会员礼品兑换")
    public Message doUpdate(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)
            throws Exception {
        Message msg = new Message();
        double totalScore=0.0;
        double tempTotalscore = 0.0;
        List<GiftExchangeManagementVo> inserted = null;
        List<BasVipGiftexchangeDetail> list = new ArrayList<BasVipGiftexchangeDetail>();
        if(giftExchangeManagementVo.getExchange_Id() != null && !"".equals(giftExchangeManagementVo)){
            if(giftExchangeManagementVo.getVip_Number() != null && !"".equals(giftExchangeManagementVo.getVip_Number())){
                BasVipInfor vipInfo = vipRecordMessageService.getBasVipInfor(giftExchangeManagementVo.getVip_Number(), vipRecordMessageService.getParentEnterpriseId(user));
                if(vipInfo != null){
                    if(giftExchangeManagementVo.getData() != null && !"".equals(giftExchangeManagementVo.getData())){
                        JSONObject jsData = JSON.parseObject(giftExchangeManagementVo.getData());
                        inserted = JSON.parseArray(jsData.get("rows").toString(),GiftExchangeManagementVo.class);
                    }
                    if(inserted != null && inserted.size() > 0){
                        BasVipGiftexchange bvgc = giftExchangeManagementDao.get("from BasVipGiftexchange where exchangeId='"+giftExchangeManagementVo.getExchange_Id()+"'");
                        if(bvgc != null){
                            if(giftExchangeManagementVo.getExchange_User() != null){
                                 bvgc.setExchangeUser(giftExchangeManagementVo.getExchange_User());
                            }
                            tempTotalscore = bvgc.getTotal_score();
                            for(GiftExchangeManagementVo vo : inserted){
                                BasVipGiftexchangeDetail bvgd = new BasVipGiftexchangeDetail();
                                bvgd.setBasVipGiftexchange(bvgc);
                                bvgd.setExchangeQuantity(Double.parseDouble(vo.getExchange_Quantity()));
                                bvgd.setOneScore(Double.parseDouble(vo.getProject_score()));
                                bvgd.setProjectId(vo.getProject_Id());
                                bvgd.setTotalScore(Double.parseDouble(vo.getTotal_Score()));
                                totalScore += Double.parseDouble(vo.getTotal_Score());
                                list.add(bvgd);
                            }
                            bvgc.setTotal_score(totalScore);
                            double integral = vipInfo.getVipIntegral();
                            if((integral + tempTotalscore) >= totalScore){
                                giftExchangeManagementDao.saveOrUpdate(bvgc);
                                vipGiftexchangeDetailDao.deleteByHql("DELETE FROM BasVipGiftexchangeDetail where basVipGiftexchange.exchangeId='"+giftExchangeManagementVo.getExchange_Id()+"'");
                                if(list.size() > 0){
                                    vipGiftexchangeDetailDao.saveOrUpdates(list);
                                }
                                msg.setSuccess(true);
                                msg.setMsg("修改礼品兑换信息成功");
                                setContent("会员礼品兑换  - 修改会员礼品兑换明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+giftExchangeManagementVo.getVip_Number());
                            }else{
                                msg.setSuccess(false);
                                msg.setMsg("您目前只有"+integral+"积分可用，不能兑换"); 
                            }
                        }else{
                            msg.setSuccess(false);
                            msg.setMsg("礼品兑换信息不存在");
                        }
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("兑换项目不能为空"); 
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("该会员不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员卡信息不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确实兑换礼品是否真实");
        }
        return msg;
    }
    
    @Log(moduleName="会员管理",content="删除会员礼品兑换明细记录",opertype="会员礼品兑换")
    public Message doDelete(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)
            throws Exception {
        Message msg = new Message();
        if(giftExchangeManagementVo.getExchange_Id() != null && !"".equals(giftExchangeManagementVo)){
            if(giftExchangeManagementVo.getVip_Number() != null && !"".equals(giftExchangeManagementVo.getVip_Number())){
                BasVipInfor vipInfo = vipRecordMessageService.getBasVipInfor(giftExchangeManagementVo.getVip_Number(), vipRecordMessageService.getParentEnterpriseId(user));
                if(vipInfo != null){
                    BasVipGiftexchange bvgc = giftExchangeManagementDao.get("from BasVipGiftexchange where exchangeId='"+giftExchangeManagementVo.getExchange_Id()+"'");
                    if(bvgc != null){
                        giftExchangeManagementDao.delete(bvgc);
                        msg.setSuccess(true);
                        msg.setMsg("删除礼品兑换信息成功");
                        setContent("会员礼品兑换  - 删除会员礼品兑换明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+giftExchangeManagementVo.getVip_Number());
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("礼品兑换信息不存在");
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("会员卡信息不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员卡信息不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确实兑换礼品是否真实");
        }
        return msg;
    }
	 
    @Log(moduleName="会员管理",content="审核会员礼品兑换明细记录",opertype="会员礼品兑换")
	public Message doAudit(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user) throws Exception {
	    Message msg = new Message();
	    String state = null;
        if(giftExchangeManagementVo.getExchange_Id() != null && !"".equals(giftExchangeManagementVo)){
            if(giftExchangeManagementVo.getVip_Number() != null && !"".equals(giftExchangeManagementVo.getVip_Number())){
                BasVipInfor vipInfo = vipRecordMessageService.getBasVipInfor(giftExchangeManagementVo.getVip_Number(), vipRecordMessageService.getParentEnterpriseId(user));
                BasVipGiftexchange bvgc = giftExchangeManagementDao.get("from BasVipGiftexchange where exchangeId='"+giftExchangeManagementVo.getExchange_Id()+"'");
                if(vipInfo != null){
                    if(bvgc != null){
                        double keyong = vipInfo.getVipIntegral();
                        double duihuan = bvgc.getTotal_score();
                        if(keyong >= duihuan){
                            List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                            if(childs != null && childs.size() > 0){
                                for(int i=0; i<childs.size(); i++){
                                    BasChilddictionary c = childs.get(i);
                                    if(bvgc.getAuditSituation().equals(Contstants.AUDIT_TAG.AUDITNOS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                        state = Contstants.AUDIT_TAG.AUDITYESS;
                                        break;
                                    }else if(bvgc.getAuditSituation().equals(Contstants.AUDIT_TAG.AUDITYESS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                                        msg.setSuccess(false);
                                        msg.setMsg("该礼品兑换单已经审核，不需要重复审核");
                                        return msg;
                                    }
                                }
                            }
                            bvgc.setAuditSituation(state);   //审核状态
                            bvgc.setAuditDate(new Date());
                            bvgc.setAuditManager(user.getBasStuff().getStfId().toString());
                            bvgc.setTotal_score(bvgc.getTotal_score() != null && !"".equals(bvgc.getTotal_score()) ? bvgc.getTotal_score() : 0.0d);
                            vipInfo.setVipIntegral(keyong - duihuan); 
                            giftExchangeManagementDao.saveOrUpdate(bvgc);
                            vipRecordMessageDao.update(vipInfo);
                            msg.setSuccess(true);
                            msg.setMsg("审核礼品兑换信息成功");
                            setContent("会员礼品兑换  - 审核会员礼品兑换明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+giftExchangeManagementVo.getVip_Number());
                        }
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("礼品兑换信息不存在");
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("会员卡信息不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员卡信息不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确实兑换礼品是否真实");
        }
        return msg;
	}
}