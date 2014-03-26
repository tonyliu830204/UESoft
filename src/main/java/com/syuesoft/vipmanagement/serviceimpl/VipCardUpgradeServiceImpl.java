package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.syuesoft.util.Msg;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipLevelDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasVipUpInfo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipLevel;
import com.syuesoft.model.BasVipUpgrade;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.dao.VipCardUpgradeDao;
import com.syuesoft.vipmanagement.dao.VipRechargeDao;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.service.VipCardUpgradeService;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.VipCardUpgradeVo;
/**
 * 会员卡升级
* @ClassName: VipCardUpgradeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:48:41 
* @version 1.0
 */
@Service("vipCardUpgradeService")
public class VipCardUpgradeServiceImpl extends BaseLogServiceImpl implements VipCardUpgradeService {

	@Autowired
	private VipCardUpgradeDao vipCardUpgradeDao;
	@Autowired
    private VipRechargeDao vipRechargeDao;
	@Autowired
	private BasVipLevelDao basVipLevelDao;
	@Autowired
    private VipRecordMessageDao vipRecordMessageDao;
	@Autowired
    private BaseService baseService;
	@Autowired
    private VipRecordMessageService vipRecordMessageService;
	/**
     * 会员卡升级汇总查询
     */
    
    public Json doVipUpCollectFind(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user) throws Exception {
        Json json = new Json();
        List<VipCardUpgradeVo> list = new ArrayList<VipCardUpgradeVo>();
        String sql = "select A.*,B.AUDIT_SITUATIONVALUE from vip_card_up_collect A "+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue AS AUDIT_SITUATIONVALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.AUDIT_TAG.AUDITKEY+"') B ON (A.AUDIT_SITUATION = B.dataKey)"+
        " where 1 = 1 ";
        //" where 1 = 1 and A.enterprise_id2 ='"+vipRecordMessageService.getParentEnterpriseId(user)+"'";
        if(vipCardUpgradeVo.getUpgrade_Date()!=null && !vipCardUpgradeVo.getUpgrade_Date().equals("")){
            sql += " and A.Upgrade_Date >= '"+FormatDate.getStartDataString(vipCardUpgradeVo.getUpgrade_Date(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipCardUpgradeVo.getUpgrade_Date2()!=null && !vipCardUpgradeVo.getUpgrade_Date2().equals("")){
            sql += " and A.Upgrade_Date <= '"+FormatDate.getEndDataString(vipCardUpgradeVo.getUpgrade_Date2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipCardUpgradeVo.getAudit_Situation()!=null && !vipCardUpgradeVo.getAudit_Situation().equals("")){
            sql += " and A.audit_Situation = '"+vipCardUpgradeVo.getAudit_Situation()+"'";
        }
        int total = vipCardUpgradeDao.getCountBySQL(sql);
        List<Object[]> rlist = vipCardUpgradeDao.createSQLQuery(sql, vipCardUpgradeVo.getPage(), vipCardUpgradeVo.getRows());
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                 obj = (Object[])rlist.get(i);
                 VipCardUpgradeVo vo = new VipCardUpgradeVo();
                 if(obj[0]!=null){vo.setUpgrade_Id(obj[0].toString());}
                 if(obj[1]!=null){vo.setUpgrade_Date(FormatTime.timestamp2Str((Timestamp) obj[1]));}
                 if(obj[2]!=null){vo.setAudit_Situation(obj[2].toString());}
                 if(obj[3]!=null){vo.setAudit_Date(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                 if(obj[4]!=null){vo.setAudit_Managers(obj[4].toString());}
                 if(obj[5]!=null){vo.setAudit_ManagersName(obj[5].toString());}
                 if(obj[6]!=null){vo.setManagers(obj[6].toString());}
                 if(obj[7]!=null){vo.setManagersName(obj[7].toString());}
                 if(obj[8]!=null){vo.setMemo(obj[8].toString());}
                 if(obj[9]!=null){vo.setAudit_SituationValue(obj[9].toString());}
                 list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    public Json doFindVipUpInfo(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user) throws Exception {
        Json json = new Json();
        List<VipCardUpgradeVo> list = new ArrayList<VipCardUpgradeVo>();
        String sql = " SELECT A.*,B.dataValue FROM vipcard_up_details A"+
                     " LEFT JOIN (SELECT b.dataKey, b.dataValue FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
                     " where 1 = 1 ";
                     //" where 1 = 1 and A.enterprise_id2 ='"+vipRecordMessageService.getParentEnterpriseId(user)+"'";
        if(vipCardUpgradeVo.getEnd_Time()!=null && !vipCardUpgradeVo.getEnd_Time().equals("")){
            sql += " and A.END_TIME >= '"+FormatDate.getStartDataString(vipCardUpgradeVo.getEnd_Time(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipCardUpgradeVo.getEnd_Time2()!=null && !vipCardUpgradeVo.getEnd_Time2().equals("")){
            sql += " and A.END_TIME <= '"+FormatDate.getEndDataString(vipCardUpgradeVo.getEnd_Time2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipCardUpgradeVo.getVip_Number() != null && !vipCardUpgradeVo.getVip_Number().equals("")){
            sql += " and A.VIP_NUMBER like '%"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Number().trim()) +"%'";
        }
        if(vipCardUpgradeVo.getCar_License() != null && !vipCardUpgradeVo.getCar_License().equals("")){
            sql += " and A.CAR_LICENSE like '%"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getCar_License().trim()) +"%'";
        }
        if(vipCardUpgradeVo.getCar_Vin() != null && !vipCardUpgradeVo.getCar_Vin().equals("")){
            sql += " and A.CAR_VIN like '%"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getCar_Vin().trim()) +"%'";
        }
        if(vipCardUpgradeVo.getVip_Age() != null && !vipCardUpgradeVo.getVip_Age().equals("")){
            sql += " and A.vip_Age ='"+ vipCardUpgradeVo.getVip_Age() +"'";
        }
        if(vipCardUpgradeVo.getVip_name() != null && !vipCardUpgradeVo.getVip_name().equals("")){
            sql += " and A.VIP_NAME like '%"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_name().trim()) +"%'";
        }
        if(vipCardUpgradeVo.getVip_Level_Id() != null && !vipCardUpgradeVo.getVip_Level_Id().equals("")){
            sql += " and A.VIP_LEVEL_ID = '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Level_Id().trim()) +"'";
        }
        if(vipCardUpgradeVo.getVip_Group_Id() != null && !vipCardUpgradeVo.getVip_Group_Id().equals("")){
            sql += " and A.VIP_GRUOP_ID = '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Group_Id().trim()) +"'";
        }
        if(vipCardUpgradeVo.getVip_Status() != null && !vipCardUpgradeVo.getVip_Status().equals("")){
            sql += " and A.VIP_STATUS='"+ vipCardUpgradeVo.getVip_Status() +"'";
        }
        if(vipCardUpgradeVo.getVip_Tel() != null && !vipCardUpgradeVo.getVip_Tel().equals("")){
            sql += " and A.VIP_TEL like '%"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Tel().trim()) +"%'";
        }
        if(vipCardUpgradeVo.getVip_Total_Integral()!= null && !vipCardUpgradeVo.getVip_Total_Integral().equals("")){
            sql += " and A.VIP_TOTAL_INTEGRAL >= '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Total_Integral().trim()) +"'";
        }
        if(vipCardUpgradeVo.getVip_Total_Integral2()!= null && !vipCardUpgradeVo.getVip_Total_Integral2().equals("")){
            sql += " and A.VIP_TOTAL_INTEGRAL <= '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Total_Integral2().trim()) +"'";
        }
        if(vipCardUpgradeVo.getVip_Integral()!= null && !vipCardUpgradeVo.getVip_Integral().equals("")){
            sql += " and A.VIP_INTEGRAL >= '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Integral().trim()) +"'";
        }
        if(vipCardUpgradeVo.getVip_Integral2()!= null && !vipCardUpgradeVo.getVip_Integral2().equals("")){
            sql += " and A.VIP_INTEGRAL <= '"+ StringEscapeUtils.escapeSql(vipCardUpgradeVo.getVip_Integral2().trim()) +"'";
        }
        int total = vipCardUpgradeDao.getCountBySQL(sql);
        List<Object[]> rlist = vipCardUpgradeDao.createSQLQuery(sql, vipCardUpgradeVo.getPage(), vipCardUpgradeVo.getRows());
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                VipCardUpgradeVo vo = new VipCardUpgradeVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setCar_License(obj[1].toString());}
                if(obj[2]!=null){vo.setCar_Vin(obj[2].toString());}
                if(obj[3]!=null){vo.setJion_Time(obj[3].toString());}
                if(obj[4]!=null){vo.setVip_Number(obj[4].toString());}
                if(obj[5]!=null){vo.setOldVip_Level_Id(obj[5].toString());}
                if(obj[6]!=null){vo.setOldVip_Level_Name(obj[6].toString());}
                if(obj[7]!=null){vo.setVip_Group_Id(obj[7].toString());}
                if(obj[8]!=null){vo.setVip_Group_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Status(obj[9].toString());}
                if(obj[10]!=null){vo.setEnd_Time(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Balance(obj[11].toString());}
                if(obj[12]!=null){vo.setVip_Birthday(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Tel(obj[13].toString());}
                if(obj[14]!=null){vo.setVip_Integral(obj[14].toString());}
                if(obj[15]!=null){vo.setVip_Total_Integral(obj[15].toString());}
                if(obj[16]!=null){vo.setVip_Age(obj[16].toString());}
                if(obj[17]!=null){vo.setVip_name(obj[17].toString());}
                //obj[18]
                //obj[19]
                if(obj[20]!=null){vo.setVip_StatusName(obj[20].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    /**
     * 通过升级单号 查询 该升级单号下的所有 升级后会员记录
     */
    
    public Json findInfoByVipUpId(VipCardUpgradeVo vipCardUpgradeVo) throws Exception {
        Json json = new Json();
        List<VipCardUpgradeVo> list = new ArrayList<VipCardUpgradeVo>();
        String sql = " SELECT A.*,B.dataValue FROM vipcard_up_details_byid A"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
        " where 1 = 1 AND A.UPGRADE_ID='"+vipCardUpgradeVo.getUpgrade_Id()+"'";
        int total = vipCardUpgradeDao.getCountBySQL(sql);
        List<Object[]> rlist = vipCardUpgradeDao.createSQLQuery(sql, vipCardUpgradeVo.getPage(), vipCardUpgradeVo.getRows());
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                VipCardUpgradeVo vo = new VipCardUpgradeVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setCar_License(obj[1].toString());}
                if(obj[2]!=null){vo.setCar_Vin(obj[2].toString());}
                if(obj[3]!=null){vo.setJion_Time(obj[3].toString());}
                if(obj[4]!=null){vo.setVip_Number(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Level_Id(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Level_Name(obj[6].toString());}
                if(obj[7]!=null){vo.setVip_Group_Id(obj[7].toString());}
                if(obj[8]!=null){vo.setVip_Group_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Status(obj[9].toString());}
                if(obj[10]!=null){vo.setEnd_Time(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Balance(obj[11].toString());}
                if(obj[12]!=null){vo.setVip_Birthday(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Tel(obj[13].toString());}
                if(obj[14]!=null){vo.setVip_Integral(obj[14].toString());}
                if(obj[15]!=null){vo.setVip_Total_Integral(obj[15].toString());}
                if(obj[16]!=null){vo.setVip_Age(obj[16].toString());}
                if(obj[17]!=null){vo.setVip_name(obj[17].toString());}
                //obj[18]
                //obj[19]
                if(obj[20]!=null){vo.setUpgrade_Id(obj[20].toString());}
                if(obj[21]!=null){vo.setUpgrade_Date(obj[21].toString());}
                if(obj[22]!=null){vo.setUpgrade_detail_Id(obj[22].toString());}
                if(obj[23]!=null){vo.setOldVip_Level_Id(obj[23].toString());}
                if(obj[24]!=null){vo.setOldVip_Level_Name(obj[24].toString());}
                if(obj[25]!=null){vo.setNewVip_Level_Id(obj[25].toString());}
                if(obj[26]!=null){vo.setNewVip_Level_Name(obj[26].toString());}
                if(obj[27]!=null){vo.setDeduction_Integration(obj[27].toString());}
                if(obj[28]!=null){vo.setGift_Points(obj[28].toString());}
                if(obj[29]!=null){vo.setVip_StatusName(obj[29].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
    
    @Log(moduleName="会员管理",content="新增会员卡升级、降级明细记录",opertype="会员卡升级、降级")
    public Msg doAddCardUpgrade(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)
            throws Exception {
        String state = null;
        Msg msg = new Msg();
        BasVipUpgrade bvu = new BasVipUpgrade();
        String inserted = vipCardUpgradeVo.getInserted();
        String updateed = vipCardUpgradeVo.getUpdated();
        String deleteed = vipCardUpgradeVo.getDeleted();
        List<VipCardUpgradeVo> insertList = JSON.parseArray(inserted,VipCardUpgradeVo.class);
        List<VipCardUpgradeVo> updateList = JSON.parseArray(updateed,VipCardUpgradeVo.class);
        List<VipCardUpgradeVo> deleteList = JSON.parseArray(deleteed,VipCardUpgradeVo.class);
        if(insertList != null && insertList.size() > 0 || updateList != null && updateList.size() > 0 || deleteList != null && deleteList.size() > 0){
            String sjNumber = CreateID.createId("BasVipUpgrade", "SJ");
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
            bvu.setUpgradeId(sjNumber);
            bvu.setUpgradeDate(new Date());
            bvu.setManagers(user.getBasStuff().getStfId().toString());
            bvu.setAuditSituation(state);
            bvu.setMemo(vipCardUpgradeVo.getMemo());
            bvu.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString());
            if(insertList != null && insertList.size() > 0){
                for(VipCardUpgradeVo vo : insertList){
                    BasVipUpInfo bu = new BasVipUpInfo();
                    parBasVipUpgradeInsert(msg, vo, bvu, bu);
                }
            }
            if(updateList != null && updateList.size() > 0){
                for(VipCardUpgradeVo vo : updateList){
                    BasVipUpInfo bu = new BasVipUpInfo();
                    parBasVipUpgradeInsert(msg, vo, bvu, bu);
                }
            }
            if(deleteList != null && deleteList.size() > 0){
                for(VipCardUpgradeVo vo : deleteList){
                }
            }
            vipCardUpgradeDao.saveOrUpdate(bvu);
            msg.setMsg("保存成功");
            msg.setSuccess(true);
            setContent("会员卡升级、降级  - 新增会员卡升级、降级明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipCardUpgradeVo.getVip_Number());
        }else{
            msg.setMsg("请选择要升级的会员");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    @Log(moduleName="会员管理",content="删除会员卡升级、降级明细记录",opertype="会员卡升级、降级")
    public Msg doRemoveVipUp(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user) throws Exception {
        Msg msg = new Msg();
        if(vipCardUpgradeVo.getUpgrade_Id() != null && !"".equals(vipCardUpgradeVo.getUpgrade_Id())){
            BasVipUpgrade bvu = vipCardUpgradeDao.get("from BasVipUpgrade where upgradeId='"+vipCardUpgradeVo.getUpgrade_Id()+"'");
            if(bvu != null){
                vipCardUpgradeDao.delete(bvu);
                msg.setMsg("删除成功");
                msg.setSuccess(true);
                setContent("会员卡升级、降级  - 删除会员卡升级、降级明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipCardUpgradeVo.getVip_Number());
            }else{
                msg.setMsg("您选择要删除的会员升级信息不存在，请确认");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("请选择要删除的会员升级信息");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    /**
     * 
     * 会员卡升级 修改未审核的升级信息
     */
    @Log(moduleName="会员管理",content="修改会员卡升级、降级明细记录",opertype="会员卡升级、降级")
    public Msg doEditCardUpgrade(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)
            throws Exception {
        Msg msg = new Msg();
        BasVipUpgrade bvu = null;
        String inserted = vipCardUpgradeVo.getInserted();
        String updateed = vipCardUpgradeVo.getUpdated();
        String deleteed = vipCardUpgradeVo.getDeleted();
        List<VipCardUpgradeVo> insertList = JSON.parseArray(inserted,VipCardUpgradeVo.class);
        List<VipCardUpgradeVo> updateList = JSON.parseArray(updateed,VipCardUpgradeVo.class);
        List<VipCardUpgradeVo> deleteList = JSON.parseArray(deleteed,VipCardUpgradeVo.class);
        if(insertList != null && insertList.size() > 0 || updateList != null && updateList.size() > 0 || deleteList != null && deleteList.size() > 0){
            if(vipCardUpgradeVo.getUpgrade_Id() != null && !"".equals(vipCardUpgradeVo.getUpgrade_Id())){
                bvu = vipCardUpgradeDao.get("from BasVipUpgrade where upgradeId='"+vipCardUpgradeVo.getUpgrade_Id()+"'");
                if(insertList != null && insertList.size() > 0){
                    for(VipCardUpgradeVo vo : insertList){
                        BasVipUpInfo bu = new BasVipUpInfo();
                        parBasVipUpgradeInsert(msg, vo, bvu, bu);
                    }
                }
                if(updateList != null && updateList.size() > 0){
                    for(VipCardUpgradeVo vo : updateList){
                        parBasVipUpgradeUpdate(msg, vo, bvu);
                    }
                }
                if(deleteList != null && deleteList.size() > 0){
                    if(bvu != null){
                        for(VipCardUpgradeVo vo : deleteList){
                            parBasVipUpgradeDelete(vo, bvu);
                        }
                    }
                }
                vipCardUpgradeDao.saveOrUpdate(bvu);
                msg.setMsg("修改成功");
                msg.setSuccess(true);
                setContent("会员卡升级、降级  - 修改会员卡升级、降级明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipCardUpgradeVo.getVip_Number());
            }else{
                msg.setMsg("请选择要修改的会员升级信息");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("请选择要修改的会员升级信息");
            msg.setSuccess(false);
        }
        return msg;
    }

	/**
	 * 审核
	 */
    @Log(moduleName="会员管理",content="审核会员卡升级、降级明细记录",opertype="会员卡升级、降级")
	public Msg doAudit(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user) throws Exception {
	    Msg msg = new Msg();
	    String state = null;
	    List<BasVipInfor> vipInfors = new ArrayList<BasVipInfor>();
        if(vipCardUpgradeVo.getUpgrade_Id() != null && !"".equals(vipCardUpgradeVo.getUpgrade_Id())){
            BasVipUpgrade bvu = vipCardUpgradeDao.get("from BasVipUpgrade where upgradeId='"+vipCardUpgradeVo.getUpgrade_Id()+"'");
            if(bvu != null){
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                if(childs != null && childs.size() > 0){
                    for(int i=0; i<childs.size(); i++){
                        BasChilddictionary c = childs.get(i);
                        if(bvu.getAuditSituation().equals(Contstants.AUDIT_TAG.AUDITNOS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                            state = Contstants.AUDIT_TAG.AUDITYESS;
                            break;
                        }else if(bvu.getAuditSituation().equals(Contstants.AUDIT_TAG.AUDITYESS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                            msg.setMsg("该会员级别调整单已经审核，不需要重复审核");
                            msg.setSuccess(false);
                            return msg;
                        }
                    }
                }
                bvu.setAuditSituation(state);   //审核状态
                bvu.setAuditDate(new Date());
                bvu.setAuditManagers(user.getBasStuff().getStfId().toString());
                Set<BasVipUpInfo> set = bvu.getBasInfoUps();
                if(set != null){
                    for(BasVipUpInfo bu : set){
                        double kouchu = bu.getDeductionIntegration();
                        double zengsong = bu.getGiftPoints();
                        BasVipInfor bvi = bu.getBasVipInfor();
                        if(bvi != null){
                            if(bvi.getVipIntegral() + zengsong >= kouchu){
                                String hql = "from BasVipLevel where vipLevelId='"+ bu.getNewVipLevelId() + "'";
                                BasVipLevel bvl = basVipLevelDao.get(hql);
                                bvi.setBasVipLevel(bvl);
                                bvi.setVipIntegral(bvi.getVipIntegral() + zengsong - kouchu );
                                vipInfors.add(bvi);
                            }else{
                                msg.setMsg("会员可用积分不够,无法扣除积分");
                                msg.setSuccess(false);
                                return msg;
                            }
                        }else{
                            msg.setMsg("会员不存在");
                            msg.setSuccess(false);
                            return msg;
                        }
                    }
                }
                vipCardUpgradeDao.saveOrUpdate(bvu);
                if(vipInfors.size() > 0){ 
                    vipRecordMessageDao.saveOrUpdates(vipInfors);
                }
                msg.setMsg("审核成功");
                msg.setSuccess(true);
                setContent("会员卡升级、降级  - 审核会员卡升级、降级明细记录   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipCardUpgradeVo.getVip_Number());
            }else{
                msg.setMsg("您选择要审核的会员升级信息不存在，请确认");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("请选择要审核的会员升级信息");
            msg.setSuccess(false);
        }
        return msg;
	}
	
	private void parBasVipUpgradeInsert(Msg msg, VipCardUpgradeVo vo, BasVipUpgrade bvu, BasVipUpInfo bu) throws Exception{
        if(vo.getVip_Id() != null && !"".equals(vo.getVip_Id()) && vo.getVip_Number() != null && !"".equals(vo.getVip_Number())){
            if(vo.getNewVip_Level_Id() != null && !"".equals(vo.getNewVip_Level_Id())){
                BasVipInfor bvi = vipRechargeDao.getByVipNumber(vo.getVip_Number()); 
                if(bvi != null){
                    String hql = "from BasVipLevel where vipLevelId='"+ vo.getNewVip_Level_Id() + "'";
                    BasVipLevel bvl = basVipLevelDao.get(hql);
                    if(bvl != null){
                        bu.setBasVipInfor(bvi);
                        bu.setBasVipUpgrade(bvu);
                        bu.setDeductionIntegration(vo.getDeduction_Integration() != null && !"".equals(vo.getDeduction_Integration()) ? Double.parseDouble(vo.getDeduction_Integration()) : 0.0d);
                        bu.setGiftPoints(vo.getGift_Points() != null && !"".equals(vo.getGift_Points()) ? Double.parseDouble(vo.getGift_Points()) : 0.0d);
                        bu.setNewVipLevelId(bvl.getVipLevelId().toString());
                        bu.setOldVipLevelId(bvi.getBasVipLevel().getVipLevelId().toString());
                        bvu.getBasInfoUps().add(bu);
                    }else{
                        msg.setMsg("请确认【"+vo.getVip_Number()+"】会员,所升级级别的数据是否完整");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("请确认【"+vo.getVip_Number()+"】会员不存在");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("请确认【"+vo.getVip_Number()+"】会员,是否要升级");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("会员卡号不存在,请确认");
            msg.setSuccess(false);
        }
    }
	
	private void parBasVipUpgradeUpdate(Msg msg, VipCardUpgradeVo vo, BasVipUpgrade bvu) throws Exception{
	    Set<BasVipUpInfo> set = bvu.getBasInfoUps();
        if(set != null){
            for(BasVipUpInfo bu : set){
                if(bu.getUpgradeDetailId().toString().equals(vo.getUpgrade_detail_Id().toString())){
                    if(vo.getVip_Id() != null && !"".equals(vo.getVip_Id()) && vo.getVip_Number() != null && !"".equals(vo.getVip_Number())){
                        if(vo.getNewVip_Level_Id() != null && !"".equals(vo.getNewVip_Level_Id())){
                            BasVipInfor bvi = vipRechargeDao.getByVipNumber(vo.getVip_Number()); 
                            if(bvi != null){
                                String hql = "from BasVipLevel where vipLevelId='"+ vo.getNewVip_Level_Id() + "'";
                                BasVipLevel bvl = basVipLevelDao.get(hql);
                                if(bvl != null){
                                    bu.setBasVipInfor(bvi);
                                    bu.setBasVipUpgrade(bvu);
                                    bu.setDeductionIntegration(vo.getDeduction_Integration() != null && !"".equals(vo.getDeduction_Integration()) ? Double.parseDouble(vo.getDeduction_Integration()) : 0.0d);
                                    bu.setGiftPoints(vo.getGift_Points() != null && !"".equals(vo.getGift_Points()) ? Double.parseDouble(vo.getGift_Points()) : 0.0d);
                                    bu.setNewVipLevelId(bvl.getVipLevelId().toString());
                                    bu.setOldVipLevelId(vo.getOldVip_Level_Id());
                                }else{
                                    msg.setMsg("请确认【"+vo.getVip_Number()+"】会员,所升级级别的数据是否完整");
                                    msg.setSuccess(false);
                                }
                            }else{
                                msg.setMsg("请确认【"+vo.getVip_Number()+"】会员不存在");
                                msg.setSuccess(false);
                            }
                        }else{
                            msg.setMsg("请确认【"+vo.getVip_Number()+"】会员,是否要升级");
                            msg.setSuccess(false);
                        }
                    }else{
                        msg.setMsg("会员卡号不存在,请确认");
                        msg.setSuccess(false);
                    }
                }
            }
        }
    }
	
	private void parBasVipUpgradeDelete(VipCardUpgradeVo vo, BasVipUpgrade bvu) throws Exception{
	    Set<BasVipUpInfo> set = bvu.getBasInfoUps();
        if(set != null){
            for(BasVipUpInfo bu : set){
                if(bu.getUpgradeDetailId().toString().equals(vo.getUpgrade_detail_Id().toString())){
                    set.remove(bu);
                }
            }
	    }
    }
}