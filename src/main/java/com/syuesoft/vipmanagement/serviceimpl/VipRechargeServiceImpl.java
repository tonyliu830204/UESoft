package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipRecharge;
import com.syuesoft.model.BasVipRechargeRegulation;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.dao.VipRechargeDao;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.service.VipRechargeService;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;
/**
 * 会员卡充值
* @ClassName: VipRechargeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:48:58 
* @version 1.0
 */
@Service("vipRechargeService")
public class VipRechargeServiceImpl extends BaseLogServiceImpl implements VipRechargeService{
	@Autowired
	private VipRechargeDao vipRechargeDao;
	@Autowired
    private BaseService baseService;
	@Autowired
    private VipRecordMessageDao vipRecordMessageDao;
	@Autowired
    private VipRecordMessageService vipRecordMessageService;
	/**
     * 查询所有充值汇总记录
     * @vrmVO 查询条件
     * */
    public Json findAll(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
        Json json = new Json();
        List<VipRecordMessageVo> list = new ArrayList<VipRecordMessageVo>();
        Object[] obj = null;
        String sql = "SELECT a.*, d.VIP_STATUS_VALUE, g.REC_AUDIT_STATUS_VALUE, j.REC_PAYTYPE_VALUE FROM v_vip_recharge a" 
             +" LEFT JOIN (SELECT b.dataKey, b.dataValue AS VIP_STATUS_VALUE FROM bas_parentdictionary c, bas_childdictionary b WHERE c.parent_id = b.parent_id AND c.dataKey = '"+Contstants.HYKZT.HYKZT+"') d ON a.VIP_STATUS = d.dataKey"
             +" LEFT JOIN (SELECT e.dataKey, e.dataValue AS REC_AUDIT_STATUS_VALUE FROM bas_parentdictionary f, bas_childdictionary e WHERE f.parent_id = e.parent_id AND f.dataKey = '"+Contstants.AUDIT_TAG.AUDITKEY+"') g ON a.REC_AUDIT_STATUS = g.dataKey" 
             +" LEFT JOIN (SELECT h.dataKey, h.dataValue AS REC_PAYTYPE_VALUE FROM bas_parentdictionary i, bas_childdictionary h WHERE i.parent_id = h.parent_id AND i.dataKey = '"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"') j ON a.REC_PAYTYPE = j.dataKey" 
             +" where 1 = 1 ";
             //+" where 1 = 1 and vrm.enterprise_id2 ='"+vipRecordMessageService.getParentEnterpriseId(user)+"'";
        if(vrmVO.getVip_Rec_Date() != null && !"".equals(vrmVO.getVip_Rec_Date())){
            sql += " and a.VIP_REC_DATE >= '"+ FormatDate.getStartDataString(vrmVO.getVip_Rec_Date(),FormatTime.DEFAULT_FORMAT2) +"'";
        }
        if(vrmVO.getVip_Rec_Date2() != null && !"".equals(vrmVO.getVip_Rec_Date2())){
            sql += " and a.VIP_REC_DATE <= '"+ FormatDate.getEndDataString(vrmVO.getVip_Rec_Date2(),FormatTime.DEFAULT_FORMAT2) +"'";
        }
        if(vrmVO.getEnd_Time() != null && !"".equals(vrmVO.getEnd_Time())){
            sql += " and a.END_TIME >= '"+ FormatDate.getStartDataString(vrmVO.getEnd_Time(),FormatTime.DEFAULT_FORMAT2) +"'";
        }
        if(vrmVO.getEnd_Time2() != null && !"".equals(vrmVO.getEnd_Time2())){
            sql += " and a.END_TIME <= '"+ FormatDate.getEndDataString(vrmVO.getEnd_Time2(),FormatTime.DEFAULT_FORMAT2) +"'";
        }
        if(vrmVO.getCar_License() != null && !"".equals(vrmVO.getCar_License())){
            sql += " and a.CAR_LICENSE like '%"+ StringEscapeUtils.escapeSql(vrmVO.getCar_License().trim()) +"%'";
        }
        if(vrmVO.getCar_Vin() != null && !"".equals(vrmVO.getCar_Vin())){
            sql += " and a.CAR_VIN like '%"+StringEscapeUtils.escapeSql(vrmVO.getCar_Vin().trim()) +"%'";
        }
        if(vrmVO.getVip_Status()!= null && !"".equals(vrmVO.getVip_Status())){
            sql += " and a.Vip_Status ='"+ vrmVO.getVip_Status() +"'";    
        }else{
            sql += " AND a.VIP_STATUS ='"+Contstants.HYKZT.ZC+"'";
        }
        if(vrmVO.getRec_Audit_Status() != null && !"".equals(vrmVO.getRec_Audit_Status())){
            sql += " and a.REC_AUDIT_STATUS='"+StringEscapeUtils.escapeSql(vrmVO.getRec_Audit_Status())+"'";   
        }
        if(vrmVO.getVip_Name() != null && !"".equals(vrmVO.getVip_Name())){
            sql += " and a.VIP_NAME like '%"+StringEscapeUtils.escapeSql(vrmVO.getVip_Name().trim())+"%'";
        }
        if(vrmVO.getVip_Tel() != null && !"".equals(vrmVO.getVip_Tel())){
            sql += " and a.VIP_TEL like '%"+ StringEscapeUtils.escapeSql(vrmVO.getVip_Tel().trim()) +"%'";
        }
        if(vrmVO.getVip_Number() != null && !"".equals(vrmVO.getVip_Number())){
            sql += " and a.VIP_NUMBER like '%"+ StringEscapeUtils.escapeSql(vrmVO.getVip_Number().trim()) +"%'";
        }
        if(vrmVO.getVip_Level_Id() != null && !"".equals(vrmVO.getVip_Level_Id())){
            sql += " and a.VIP_LEVEL_ID like '%"+ StringEscapeUtils.escapeSql(vrmVO.getVip_Level_Id().trim()) +"%'";
        }
        if((vrmVO.getOrder() != null && !"".equals(vrmVO.getOrder()) && (vrmVO.getSort() != null && !"".equals(vrmVO.getSort())))){
            sql += " order by a."+ vrmVO.getSort() +" a."+vrmVO.getOrder();
        }
        int totle = vipRechargeDao.getCountBySQL(sql);
        List<Object[]> list1 = vipRechargeDao.createSQLQuery(sql, vrmVO.getPage(), vrmVO.getRows());
        if(list1 != null && list1.size() > 0){
            for(int i = 0; i < list1.size() ; i++){
                obj = (Object[])list1.get(i);
                VipRecordMessageVo vrm = new VipRecordMessageVo();
                if(obj[0] != null){vrm.setCar_License(obj[0].toString());}
                if(obj[1] != null){vrm.setCar_Vin(obj[1].toString());}
                if(obj[2] != null){vrm.setVip_Name(obj[2].toString());}
                if(obj[3] != null){vrm.setVip_Birthday(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                if(obj[4] != null){vrm.setVip_Tel(obj[4].toString());}
                if(obj[5] != null){vrm.setVip_Number(obj[5].toString());}
                //obj[6]会员等级id
                if(obj[7] != null){vrm.setVip_Level_Name(obj[7].toString());}
                //obj[8]会员分组id
                if(obj[9] != null){vrm.setVip_Group_Name(obj[9].toString());}
                if(obj[10] != null){vrm.setVip_Status(obj[10].toString());}
                if(obj[11] != null){vrm.setJoin_Time(FormatTime.timestamp2Str((Timestamp) obj[11]));}
                if(obj[12] != null){vrm.setEnd_Time(FormatTime.timestamp2Str((Timestamp) obj[12]));}
                if(obj[13] != null){vrm.setVip_Age(obj[13].toString());}
                if(obj[14] != null){vrm.setVip_Balance(obj[14].toString());}
                if(obj[15] != null){vrm.setVip_Integral(obj[15].toString());}
                if(obj[16] != null){vrm.setVip_Hobby(obj[16].toString());}
                if(obj[17] != null){vrm.setVip_Id(obj[17].toString());}
                if(obj[18] != null){vrm.setVip_Total_Integral(obj[18].toString());}
                if(obj[19] != null){vrm.setVip_Rec_Id(obj[19].toString());}
                if(obj[20] != null){vrm.setRec_Amount(obj[20].toString());}
                if(obj[21] != null){vrm.setGive_Amount(obj[21].toString());}
                if(obj[22] != null){vrm.setGive_Inte(obj[22].toString());}
                if(obj[23] != null){vrm.setOperator(obj[23].toString());}
                if(obj[24] != null){vrm.setVip_Rec_Note(obj[24].toString());}
                if(obj[25] != null){vrm.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[25]));}
                if(obj[26] != null){vrm.setRec_Audit_Oper(obj[26].toString());}
                if(obj[27] != null){vrm.setRec_Audit_Date(FormatTime.timestamp2Str((Timestamp) obj[27]));}
                if(obj[28] != null){vrm.setRec_Audit_Status(obj[28].toString());}
                if(obj[29] != null){vrm.setRec_PayType(obj[29].toString());}
                if(obj[30] != null){vrm.setStf_Name(obj[30].toString());}
                if(obj[31] != null){vrm.setRec_Audit_OperValue(obj[31].toString());}
                //obj[32] 企业编号
                //obj[33] 企业编号
                if(obj[34] != null){vrm.setVip_Status_value(obj[34].toString());}
                if(obj[35] != null){vrm.setRec_Audit_StatusValue(obj[35].toString());}
                if(obj[36] != null){vrm.setPayMent_Name(obj[36].toString());}
                list.add(vrm);
            }
        }
        json.setRows(list);
        json.setTotal(totle);
        return json;
    }
    
	/**
	 * 读卡
	 * @vrmVO 会员管理VO
	 * */
	
	public Message readCard(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
	    Message msg = new Message();
	    List<VipRecordMessageVo> list = new ArrayList<VipRecordMessageVo>();
	    String sql = "SELECT a.VIP_ID,a.VIP_LEVEL_NAME,a.VIP_GRUOP_NAME,a.VIP_STATUS,DATE_FORMAT(a.END_TIME,'%Y-%m-%d %H:%m:%s'),a.VIP_BALANCE,a.VIP_INTEGRAL,a.VIP_TOTAL_INTEGRAL,a.CAR_LICENSE,a.CAR_VIN," +
        "a.VIP_NAME,a.VIP_TEL,a.VIP_BIRTHDAY,DATE_FORMAT(a.JOIN_TIME,'%Y-%m-%d %H:%m:%s'),a.VIP_NUMBER,c.dataValue FROM vip_readcard a"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue FROM bas_parentdictionary c, bas_childdictionary b WHERE c.parent_id = b.parent_id AND c.dataKey = '"+Contstants.HYKZT.HYKZT+"') c ON a.VIP_STATUS = c.dataKey"+ 
        " WHERE VIP_NUMBER='"+vrmVO.getVip_Number()+"' AND a.VIP_STATUS ='"+Contstants.HYKZT.ZC+"'";
	    //" WHERE VIP_NUMBER='"+vrmVO.getVip_Number()+"' AND a.VIP_STATUS ='"+Contstants.HYKZT.ZC+"' AND a.enterprise_id2="+vipRecordMessageService.getParentEnterpriseId(user);
	    List<Object[]> list1 = vipRechargeDao.createSQLQuery(sql);
	    Object[] obj = null;
        if(list1 != null && list1.size() > 0){
            for (int i = 0; i < list1.size(); i++) {
                obj = (Object[])list1.get(i);
                VipRecordMessageVo vrmVO1 = new VipRecordMessageVo();
                if(obj[0] != null){vrmVO1.setVip_Id(obj[0].toString());}
                if(obj[1] != null){vrmVO1.setVip_Level_Name(obj[1].toString());}
                if(obj[2] != null){vrmVO1.setVip_Group_Name(obj[2].toString());}
                if(obj[3] != null){vrmVO1.setVip_Status(obj[3].toString());}
                if(obj[4] != null){vrmVO1.setEnd_Time(obj[4].toString());}
                if(obj[5] != null){vrmVO1.setVip_Balance(obj[5].toString());}
                if(obj[6] != null){vrmVO1.setVip_Integral(obj[6].toString());}
                if(obj[7] != null){vrmVO1.setVip_Total_Integral(obj[7].toString());}
                if(obj[8] != null){vrmVO1.setCar_License(obj[8].toString());}
                if(obj[9] != null){vrmVO1.setCar_Vin(obj[9].toString());}
                if(obj[10] != null){vrmVO1.setVip_Name(obj[10].toString());}
                if(obj[11] != null){vrmVO1.setVip_Tel(obj[11].toString());}
                if(obj[12] != null){vrmVO1.setVip_Birthday(obj[12].toString());}
                if(obj[13] != null){vrmVO1.setJoin_Time(obj[13].toString());}
                if(obj[14] != null){vrmVO1.setVip_Number(obj[14].toString());}
                if(obj[15] != null){vrmVO1.setVip_Status_value(obj[15].toString());}
                list.add(vrmVO1);
            }
            msg.setObj(list);
            msg.setSuccess(true);
            msg.setMsg("成功");
        }else{
            msg.setObj(list);
            msg.setSuccess(false);
            msg.setMsg("会员不存在");
        }
		return msg;
	}
	
	/**
     * 会员充值
     * @vrmVO 会员管理VO
     * */
	@Log(moduleName="会员管理",content="新增储值明细记录",opertype="会员卡储值")
    public Message add(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
        Message msg = new Message();
        if(vrmVO.getVip_Number() != null && !"".equals(vrmVO.getVip_Number())){
            if(vrmVO.getRec_Amount() != null && !"".equals(vrmVO.getRec_Amount()) && !"0".equals(vrmVO.getRec_Amount()) && !"0.0".equals(vrmVO.getRec_Amount())){
                BasVipInfor bvi = vipRechargeDao.getByVipNumber(vrmVO.getVip_Number());   //根据会员卡号获取会员信息
                if(bvi != null){
                    BasVipRecharge bvr = new BasVipRecharge();  //实力还会员充值实体
                    bvr.setRecAmount(Double.parseDouble(vrmVO.getRec_Amount()));   //充值金额
                    bvr.setVipRecDate(new Date());      //充值日期
                    if(vrmVO.getGive_Amount() == null || "".equals(vrmVO.getGive_Amount())){
                        bvr.setGiveAmount(0.0d);
                    }else{
                        bvr.setGiveAmount(Double.parseDouble(vrmVO.getGive_Amount())); //赠送金额
                    }
                    if(vrmVO.getGive_Inte() == null || "".equals(vrmVO.getGive_Inte())){
                        bvr.setGiveInte(0.0d);
                    }else{
                        bvr.setGiveInte(Double.parseDouble(vrmVO.getGive_Inte()));     //赠送积分
                    }
                    bvr.setRecPayType(vrmVO.getRec_PayType());                   //付款方式
                    bvr.setOperator(user.getBasStuff().getStfId().toString());   //经办人
                    bvr.setVipRecNote(vrmVO.getVip_Rec_Note());                  //充值备注
                    bvr.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                    bvr.setParentEnterpriseId(vipRecordMessageService.getParentEnterpriseId(user));
                    List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                    if(childs != null && childs.size() > 0){
                        for(int i=0; i<childs.size(); i++){
                            BasChilddictionary c = childs.get(i);
                            if(c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                bvr.setRecAuditStatus(Contstants.AUDIT_TAG.AUDITNOS);   //审核状态
                                break;
                            }
                        }
                    }
                    bvr.setBasVipInfor(bvi);
                    vipRechargeDao.saveOrUpdate(bvr);
                    msg.setSuccess(true);
                    msg.setMsg("会员充值成功");
                    setContent("会员卡储值  - 新增储值明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vrmVO.getVip_Number());
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("请确认,该会员不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("请确认,储值金额不能为空，或者0元");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确认,会员卡号不能为空");
        }
        return msg;
    }
    
	public VipRecordMessageVo readVipRedInfo(VipRecordMessageVo vrmVO) throws Exception
    {
	    String sql = "SELECT VIP_REC_ID,REC_AMOUNT,GIVE_AMOUNT,GIVE_INTE,OPERATOR,VIP_REC_NOTE,VIP_REC_DATE,REC_AUDIT_OPER,REC_AUDIT_DATE,REC_AUDIT_STATUS,REC_PAYTYPE FROM bas_vip_recharge WHERE VIP_REC_ID='"+vrmVO.getVip_Rec_Id()+"' AND VIP_ID='"+vrmVO.getVip_Id()+"'";
	    List<Object[]> list= vipRechargeDao.createSQLQuery(sql);
	    VipRecordMessageVo vrmVO1 = new VipRecordMessageVo();
	    if(list.size() > 0){
	        Object[] obj= list.get(0);
	        if(obj[0] != null){vrmVO1.setVip_Rec_Id(obj[0].toString());}
	        if(obj[1] != null){vrmVO1.setRec_Amount(obj[1].toString());}
	        if(obj[2] != null){vrmVO1.setGive_Amount(obj[2].toString());}
	        if(obj[3] != null){vrmVO1.setGive_Inte(obj[3].toString());}
	        if(obj[4] != null){vrmVO1.setOperator(obj[4].toString());}
	        if(obj[5] != null){vrmVO1.setVip_Rec_Note(obj[5].toString());}
	        if(obj[6] != null){vrmVO1.setVip_Rec_Date(obj[6].toString());}
	        if(obj[7] != null){vrmVO1.setRec_Audit_Oper(obj[7].toString());}
	        if(obj[8] != null){vrmVO1.setRec_Audit_Date(obj[8].toString());}
	        if(obj[9] != null){vrmVO1.setRec_Audit_Status(obj[9].toString());}
	        if(obj[10] != null){vrmVO1.setRec_PayType(obj[10].toString());}
	    }
        return vrmVO1;
    }
	
	/**
     * 更新会员充值信息
     * @vrmVO 会员管理VO
     */
	@Log(moduleName="会员管理",content="修改储值明细记录",opertype="会员卡储值")
    public Message update(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
        Message msg = new Message();
        if(vrmVO.getVip_Rec_Id() != null && !"".equals(vrmVO.getVip_Rec_Id())){
            if(vrmVO.getRec_Amount() != null && !"".equals(vrmVO.getRec_Amount())  && !"0".equals(vrmVO.getRec_Amount())  && !"0.0".equals(vrmVO.getRec_Amount())){
                BasVipRecharge bvr = vipRechargeDao.getById(vrmVO.getVip_Rec_Id().toString());
                if(bvr != null){
                    bvr.setRecAmount(Double.parseDouble(vrmVO.getRec_Amount()));   //充值金额
                    if(vrmVO.getGive_Amount() == null || "".equals(vrmVO.getGive_Amount())){
                        bvr.setGiveAmount(0.0d);
                    }else{
                        bvr.setGiveAmount(Double.parseDouble(vrmVO.getGive_Amount())); //赠送金额
                    }
                    if(vrmVO.getGive_Inte() == null || "".equals(vrmVO.getGive_Inte())){
                        bvr.setGiveInte(0.0d);
                    }else{
                        bvr.setGiveInte(Double.parseDouble(vrmVO.getGive_Inte()));     //赠送积分
                    }
                    bvr.setRecPayType(vrmVO.getRec_PayType());                   //付款方式
                    bvr.setVipRecNote(vrmVO.getVip_Rec_Note());                  //充值备注
                    vipRechargeDao.saveOrUpdate(bvr);
                    msg.setSuccess(true);
                    msg.setMsg("更新会员储值信息成功");
                    setContent("会员卡储值  - 修改储值明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vrmVO.getVip_Number());
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("会员储值信息不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("储值金额不能为空或者0元");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("要修改信息不全，请确认");
        }
        return msg;
    }
    
    /**
     * 删除会员充值
     * @vrmVO 会员管理VO
     * */
	@Log(moduleName="会员管理",content="删除储值明细记录",opertype="会员卡储值")
    public Message delete(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
        Message msg = new Message();
        if(vrmVO.getVip_Rec_Id() != null && !"".equals(vrmVO.getVip_Rec_Id())){
            BasVipRecharge bvr = vipRechargeDao.getById(vrmVO.getVip_Rec_Id().toString());  //根据充值编号获取充值信息 
            if(bvr != null){
                vipRechargeDao.delete(bvr);
                msg.setSuccess(true);
                msg.setMsg("删除会员储值信息成功");
                setContent("会员卡储值  - 删除储值明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vrmVO.getVip_Number());
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员储值信息不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("要修改信息不全，请确认");
        }
        return msg;
    }
    
    /**
     * 审核会员充值
     */
	@Log(moduleName="会员管理",content="审核储值明细记录",opertype="会员卡储值")
    public Message updateVipRecharge(VipRecordMessageVo vrmVO, BasUsers user) throws Exception {
        Message msg = new Message();
        String state = null;
        if(vrmVO.getVip_Rec_Id() != null && !"".equals(vrmVO.getVip_Rec_Id())){
            BasVipRecharge bvr = vipRechargeDao.getById(vrmVO.getVip_Rec_Id().toString());
            if(bvr != null){
                vrmVO.setGive_Inte(vrmVO.getGive_Inte() != null && !"".equals(vrmVO.getGive_Inte()) ? vrmVO.getGive_Inte() : "0.0");
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                if(childs != null && childs.size() > 0){
                    for(int i=0; i<childs.size(); i++){
                        BasChilddictionary c = childs.get(i);
                        if(bvr.getRecAuditStatus().equals(Contstants.AUDIT_TAG.AUDITNOS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                            state = Contstants.AUDIT_TAG.AUDITYESS;
                            break;
                        }else if(bvr.getRecAuditStatus().equals(Contstants.AUDIT_TAG.AUDITYESS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                            msg.setSuccess(false);
                            msg.setMsg("会员储值信息不存在");
                            return msg;
                        }
                    }
                }
                bvr.setRecAuditStatus(state);   //审核状态
                bvr.setRecAuditDate(new Date());
                bvr.setRecAuditOper(user.getBasStuff().getStfId().toString());
                BasVipInfor bvi = vipRechargeDao.getByVipNumber(vrmVO.getVip_Number());   //根据会员卡号获取会员信息
                if(bvi != null){
                    //计算会员余额(=会员余额+赠送金额+充值金额)
                    bvr.setRecAmount(bvr.getRecAmount() != null && !"".equals(bvr.getRecAmount()) ? bvr.getRecAmount() : 0.0d);
                    bvr.setGiveAmount(bvr.getGiveAmount() != null && !"".equals(bvr.getGiveAmount()) ? bvr.getGiveAmount() : 0.0d);
                    double vipBalance = bvi.getVipBalance() + bvr.getRecAmount() + bvr.getGiveAmount();           //计算充值总金额
                    bvi.setVipBalance(vipBalance);   //会员卡余额
                    //计算会员可用积分（=会员积分+赠送积分）
                    vrmVO.setGive_Inte(vrmVO.getGive_Inte() != null && !"".equals(vrmVO.getGive_Inte()) ? vrmVO.getGive_Inte() : "0.0");
                    bvi.setVipIntegral(bvi.getVipIntegral() + Double.parseDouble(vrmVO.getGive_Inte()));  //会员可用积分
                    //计算会员累计积分（=会员累计积分+赠送积分）
                    bvi.setVipTotalIntegral(bvi.getVipTotalIntegral() + Double.parseDouble(vrmVO.getGive_Inte())); //会员累计积分
                    vipRechargeDao.saveOrUpdate(bvr);
                    vipRecordMessageDao.update(bvi);
                    msg.setSuccess(true);
                    msg.setMsg("审核会员储值信息成功");
                    setContent("会员卡储值  - 审核储值明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vrmVO.getVip_Number());
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("请确认,该会员不存在");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员储值信息不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("要修改信息不全，请确认");
        }
        return msg;
    }
    
	/**
	 * 获取充值规则
	 * @recAmount 充值金额
	 * */
	
	public BasVipRechargeRegulation getByRecAmount(Integer recAmount)
			throws Exception {
		return vipRechargeDao.getByRecAmount(recAmount);
	}
}