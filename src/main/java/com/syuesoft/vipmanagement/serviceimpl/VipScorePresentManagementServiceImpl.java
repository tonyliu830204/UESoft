package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.dao.BasVipGiveIntegralProjectDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipGiveIntegral;
import com.syuesoft.model.BasVipGiveIntegralProject;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipRelationship16;
import com.syuesoft.model.BasVipRelationship16Id;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Msg;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.BasVipGiveIntegralProjectVo;
import com.syuesoft.vipmanagement.dao.VipRechargeDao;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.dao.VipRelationshipDao;
import com.syuesoft.vipmanagement.dao.VipScorePresentManagementDao;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.service.VipScorePresentManagementService;
import com.syuesoft.vipmanagement.vo.VipScorePresentManagementVo;
/**
 * 会员积分赠送
* @ClassName: VipScorePresentManagementServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:49:42 
* @version 1.0
 */
@Service("vipScorePresentManagementService")
public class VipScorePresentManagementServiceImpl extends BaseLogServiceImpl implements VipScorePresentManagementService {
	
	@Autowired
	private VipScorePresentManagementDao vipScorePresentManagementDao;
	@Autowired
    private BaseService baseService;
	@Autowired
    private VipRechargeDao vipRechargeDao;
	@Autowired
	private BasVipGiveIntegralProjectDao basVipGiveIntegralProjectDao;
	@Autowired
    private VipRecordMessageDao vipRecordMessageDao;
	@Autowired
	private VipRelationshipDao vipRelationshipDao;
	@Autowired
    private VipRecordMessageService vipRecordMessageService;
	/**
	 * 获取赠送积分汇总信息
	 * 
	 */
	
	public Json getHzGiveIntegral(
			VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)
			throws Exception {
	    List<VipScorePresentManagementVo> list = new ArrayList<VipScorePresentManagementVo>();
        Json json = new Json();
        String sql = "select A.*, B.VIP_STATUS_VALUE, C.JIFENSHENHE_VALUE from vip_zs_score A"+
                     " LEFT JOIN (SELECT b.dataKey, b.dataValue AS VIP_STATUS_VALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
                     " LEFT JOIN (SELECT b.dataKey, b.dataValue AS JIFENSHENHE_VALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.AUDIT_TAG.AUDITKEY+"') C ON (A.SHENHE_QINGKUANG = C.dataKey)"+
                     " where 1 = 1 ";
        if(vipScorePresentManagementVo.getGive_Inte_Date()!=null && !vipScorePresentManagementVo.getGive_Inte_Date().trim().equals("")){
            sql += " and A.GIVE_INTE_DATE >= '"+FormatDate.getStartDataString(vipScorePresentManagementVo.getGive_Inte_Date(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipScorePresentManagementVo.getGive_Inte_Date2()!=null && !vipScorePresentManagementVo.getGive_Inte_Date2().trim().equals("")){
            sql += " and A.GIVE_INTE_DATE <= '"+FormatDate.getEndDataString(vipScorePresentManagementVo.getGive_Inte_Date2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipScorePresentManagementVo.getEnd_Time()!=null && !vipScorePresentManagementVo.getEnd_Time().trim().equals("")){
            sql += " and A.END_TIME >= '"+FormatDate.getStartDataString(vipScorePresentManagementVo.getEnd_Time(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipScorePresentManagementVo.getEnd_Time2()!=null && !vipScorePresentManagementVo.getEnd_Time2().trim().equals("")){
            sql += " and A.END_TIME <= '"+FormatDate.getEndDataString(vipScorePresentManagementVo.getEnd_Time2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipScorePresentManagementVo.getCar_License()!=null && !vipScorePresentManagementVo.getCar_License().trim().equals("")){
            sql += " and A.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getCar_License().trim())+"%'";
        }
        if(vipScorePresentManagementVo.getCar_Vin()!=null && !vipScorePresentManagementVo.getCar_Vin().trim().equals("")){
            sql += " and A.CAR_VIN like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getCar_Vin().trim())+"%'";
        }
        if(vipScorePresentManagementVo.getVip_Age()!=null && !vipScorePresentManagementVo.getVip_Age().trim().equals("")){
            sql += " and A.Vip_Age = "+vipScorePresentManagementVo.getVip_Age()+"";
        }
        if(vipScorePresentManagementVo.getShenhe_Qingkuang()!=null && !vipScorePresentManagementVo.getShenhe_Qingkuang().trim().equals("")){
            sql += " and A.SHENHE_QINGKUANG = '"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getShenhe_Qingkuang().trim())+"'";
        }
        if(vipScorePresentManagementVo.getVip_Name()!=null && !vipScorePresentManagementVo.getVip_Name().trim().equals("")){
            sql += " and A.VIP_NAME like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Name().trim())+"%'";
        }
        if(vipScorePresentManagementVo.getVip_Tel()!=null && !vipScorePresentManagementVo.getVip_Tel().trim().equals("")){
            sql += " and A.VIP_Tel like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Tel().trim())+"%'";
        }
        if(vipScorePresentManagementVo.getVip_Number()!=null && !vipScorePresentManagementVo.getVip_Number().trim().equals("")){
            sql += " and A.VIP_NUMBER like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Number().trim())+"%'";
        }
        if(vipScorePresentManagementVo.getVip_Status()!=null && !vipScorePresentManagementVo.getVip_Status().trim().equals("")){
            sql += " and A.VIP_STATUS = '"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Status().trim())+"'";
        }
        if(vipScorePresentManagementVo.getVip_Level_Id()!=null && !vipScorePresentManagementVo.getVip_Level_Id().trim().equals("")){
            sql += " and A.VIP_LEVEL_ID = '"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Level_Id().trim())+"'";
        }
        if(vipScorePresentManagementVo.getVip_Group_Id()!=null && !vipScorePresentManagementVo.getVip_Group_Id().trim().equals("")){
            sql += " and A.VIP_GRUOP_ID = '"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getVip_Group_Id().trim())+"'";
        }
        if(vipScorePresentManagementVo.getGive_Inte_Id()!=null && !vipScorePresentManagementVo.getGive_Inte_Id().trim().equals("")){
            sql += " and A.GIVE_INTE_ID like '%"+StringEscapeUtils.escapeSql(vipScorePresentManagementVo.getGive_Inte_Id().trim())+"%'";
        }
        int totle = vipScorePresentManagementDao.getCountBySQL(sql);
        List<Object[]> volist = vipScorePresentManagementDao.createSQLQuery(sql, vipScorePresentManagementVo.getPage(), vipScorePresentManagementVo.getRows());
        
        Object[] obj = null;
        if(volist != null && volist.size() > 0){
            for (int i = 0; i < volist.size(); i++) {
                obj = (Object[])volist.get(i);
                VipScorePresentManagementVo vo = new VipScorePresentManagementVo();
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
                if(obj[20]!=null){vo.setGive_Inte_Num(obj[20].toString());}
                if(obj[21]!=null){vo.setGive_Inte_Id(obj[21].toString());}
                if(obj[22]!=null){vo.setGive_Inte_Date(FormatTime.timestamp2Str((Timestamp) obj[22]));}
                if(obj[23]!=null){vo.setShenhe_Qingkuang(obj[23].toString());}
                if(obj[24]!=null){vo.setShenhe_Riqi(FormatTime.timestamp2Str((Timestamp) obj[24]));}
                if(obj[25]!=null){vo.setShenhe_Yuan(obj[25].toString());}
                if(obj[26]!=null){vo.setShenhe_YuanName(obj[26].toString());}
                if(obj[27]!=null){vo.setOperator(obj[27].toString());}
                if(obj[28]!=null){vo.setOperatorName(obj[28].toString());}
                if(obj[29]!=null){vo.setGive_Inte_Note(obj[29].toString());}
                if(obj[30]!=null){vo.setVip_Status_value(obj[30].toString());}
                if(obj[31]!=null){vo.setShenhe_QingkuangName(obj[31].toString());}
                list.add(vo);
            }
        }
        json.setRows(list);
        json.setTotal(totle);
        return json;
	}
	
	public Json getBasVipGiveIntegralProject() throws Exception {
        String sql = "SELECT GIVE_INTE_PRO_ID,GIVE_INTE_PRO_NAME,GIVE_INTE_NUM FROM bas_vip_give_integral_project";
        List<BasVipGiveIntegralProjectVo> list = new ArrayList<BasVipGiveIntegralProjectVo>();
        Json json = new Json();
        List<Object[]> vlist = vipScorePresentManagementDao.createSQLQuery(sql);
        if(vlist != null && vlist.size() > 0){
            for (Object[] objs : vlist) {
                BasVipGiveIntegralProjectVo vo = new BasVipGiveIntegralProjectVo();
                if(objs[0] != null){vo.setGive_Inte_Pro_Id(objs[0].toString());};
                if(objs[0] != null){vo.setGive_Inte_Pro_Name(objs[1].toString());};
                if(objs[0] != null){vo.setGive_Inte_Num(objs[2].toString());vo.setShiji_Zengfen(objs[2].toString());}
                list.add(vo);
            }
            json.setRows(list);
            json.setTotal(list.size());
        }
        return json;
    }
	
	/**
	 * 
	 * 将实际赠分 与 赠送积分表的id 和 赠送项目表的id 存入中间表中 bas_vip_relationship16
	 */
	@Log(moduleName="会员管理",content="新增积分赠送明细记录",opertype="会员积分赠送")
	public Msg addRelationTable(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user) throws Exception {
	    Msg msg = new Msg();
        String state = null;
        List<BasVipRelationship16> list = null;
        if(vipScorePresentManagementVo.getVip_Number() != null && !"".equals(vipScorePresentManagementVo.getVip_Number())){
            BasVipGiveIntegral bvg = new BasVipGiveIntegral();
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
            BasVipInfor bvi = vipRechargeDao.getByVipNumber(vipScorePresentManagementVo.getVip_Number()); 
            if(bvi != null){
                double totle = vipScorePresentManagementVo.getSum() != null && !"".equals(vipScorePresentManagementVo.getSum()) ? Double.parseDouble(vipScorePresentManagementVo.getSum()) : 0;
                bvg.setBasVipInfor(bvi);
                bvg.setGiveInteNum(totle);
                bvg.setGiveInteDate(new Date());
                bvg.setOperator(user.getBasStuff().getStfId().toString());
                bvg.setShenheQingkuang(state);
                bvg.setGiveInteNote(vipScorePresentManagementVo.getGive_Inte_Note());
                bvg.setEnterprise_id(user.getBasStuff().getEnterpriseInfo().getEnterpriseId().toString());
                JSONObject jsData = JSON.parseObject(vipScorePresentManagementVo.getProjectItem());
                List<VipScorePresentManagementVo> fcList = JSON.parseArray(jsData.get("rows").toString(),VipScorePresentManagementVo.class);
                if(fcList != null && fcList.size() > 0){
                    list = new ArrayList<BasVipRelationship16>();
                    for (int i = 0; i < fcList.size(); i++) {
                        VipScorePresentManagementVo spm = fcList.get(i);
                        if(spm != null){
                            int gipId = Integer.parseInt(spm.getGive_Inte_Pro_Id());
                            Double sjf = 0.0d;
                            if(spm.getShiji_Zengfen()!=null){
                                sjf = Double.parseDouble(spm.getShiji_Zengfen());
                            }
                            BasVipGiveIntegralProject vgp = basVipGiveIntegralProjectDao.get("from BasVipGiveIntegralProject where giveInteProId="+gipId);
                            if(vgp != null){
                                BasVipRelationship16Id vri = new BasVipRelationship16Id();
                                vri.setBasVipGiveIntegral(bvg);
                                vri.setBasVipGiveIntegralProject(vgp);
                                BasVipRelationship16 vr = new BasVipRelationship16();
                                vr.setId(vri);
                                vr.setShijiZengfen(sjf);
                                list.add(vr);
                            }else{
                                msg.setSuccess(false);
                                msg.setMsg("请确认,赠送的积分项目不存在");
                                break;
                            }
                        }
                    }
                    vipScorePresentManagementDao.saveOrUpdate(bvg);
                    vipRelationshipDao.saveOrUpdates(list);
                    msg.setSuccess(true);
                    msg.setMsg("保存成功");
                    setContent("会员积分赠送  - 新增积分赠送明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipScorePresentManagementVo.getVip_Number());
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("请确认,赠送积分项目为空");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("请确认,该会员不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确认,会员卡号不能为空");
        }
	    return msg;
	}
	
	/**
	 * 
	 * 通过vipid获取赠送项目及赠送积分
	 */
	
	public Json getGiveIntegralByVipId(
			VipScorePresentManagementVo vipScorePresentManagementVo)
			throws Exception {
	    Json json = new Json();
	    List<VipScorePresentManagementVo> list = new ArrayList<VipScorePresentManagementVo>();
	    List<Object[]> volist = vipScorePresentManagementDao.getGiveIntegralByVipId(vipScorePresentManagementVo);
	    if(volist != null && volist.size() > 0){
    	    Object[] obj = null;
            for (int i = 0; i < volist.size(); i++) {
                obj = (Object[])volist.get(i);
                VipScorePresentManagementVo vo = new VipScorePresentManagementVo();
                if(obj[0]!=null){vo.setGive_Inte_Pro_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setGive_Inte_Pro_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setGive_Inte_Num(obj[2].toString());}
                if(obj[3]!=null){vo.setShiji_Zengfen(obj[3].toString());}
                if(obj[4]!=null){vo.setGive_Inte_Id(obj[4].toString());}
                list.add(vo);
            }
            json.setRows(list);
            json.setTotal(list.size());
	    }
		return json;
	}
	
	@Log(moduleName="会员管理",content="修改积分赠送明细记录",opertype="会员积分赠送")
    public Msg updateRelationTable(
            VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)
            throws Exception {
	    Msg msg = new Msg();
	    List<BasVipRelationship16> list = null;
        if(vipScorePresentManagementVo.getVip_Number() != null && !"".equals(vipScorePresentManagementVo.getVip_Number()) && vipScorePresentManagementVo.getGive_Inte_Id() != null && !"".equals(vipScorePresentManagementVo.getGive_Inte_Id())){
            BasVipGiveIntegral bvg = vipScorePresentManagementDao.get("from BasVipGiveIntegral where giveInteId="+vipScorePresentManagementVo.getGive_Inte_Id());
            BasVipInfor bvi = vipRechargeDao.getByVipNumber(vipScorePresentManagementVo.getVip_Number()); 
            if(bvi != null){
                double totle = vipScorePresentManagementVo.getSum() != null && !"".equals(vipScorePresentManagementVo.getSum()) ? Double.parseDouble(vipScorePresentManagementVo.getSum()) : 0;
                bvg.setBasVipInfor(bvi);
                bvg.setGiveInteNum(totle);
                bvg.setGiveInteNote(vipScorePresentManagementVo.getGive_Inte_Note());
                
                JSONObject jsData = JSON.parseObject(vipScorePresentManagementVo.getProjectItem());
                List<VipScorePresentManagementVo> fcList = JSON.parseArray(jsData.get("rows").toString(),VipScorePresentManagementVo.class);
                
                if(fcList != null && fcList.size() > 0){
                    list = new ArrayList<BasVipRelationship16>();
                    for (int i = 0; i < fcList.size(); i++) {
                        VipScorePresentManagementVo spm = fcList.get(i);
                        if(spm != null){
                            int gipId = Integer.parseInt(spm.getGive_Inte_Pro_Id());
                            Double sjf = 0.0d;
                            if(spm.getShiji_Zengfen()!=null){
                                sjf = Double.parseDouble(spm.getShiji_Zengfen());
                            }
                            BasVipGiveIntegralProject vgp = basVipGiveIntegralProjectDao.get("from BasVipGiveIntegralProject where giveInteProId="+gipId);
                            if(vgp != null){
                                BasVipRelationship16Id vri = new BasVipRelationship16Id();
                                vri.setBasVipGiveIntegral(bvg);
                                vri.setBasVipGiveIntegralProject(vgp);
                                BasVipRelationship16 vr = new BasVipRelationship16();
                                vr.setId(vri);
                                vr.setShijiZengfen(sjf);
                                list.add(vr);
                            }else{
                                msg.setSuccess(false);
                                msg.setMsg("请确认,赠送的积分项目不存在");
                                break;
                            }
                        }
                    }
                    vipScorePresentManagementDao.saveOrUpdate(bvg);
                    vipRelationshipDao.createSQLQueryOutFind("DELETE FROM bas_vip_relationship_16 WHERE GIVE_INTE_ID = "+vipScorePresentManagementVo.getGive_Inte_Id());
                    if(list != null && list.size() > 0){
                        for(int i = 0; i<list.size(); i++){
                            BasVipRelationship16 v = list.get(i);
                            vipRelationshipDao.createSQLQueryOutFind("INSERT INTO bas_vip_relationship_16 (GIVE_INTE_ID,GIVE_INTE_PRO_ID,SHIJI_ZENGFEN)VALUES('"+v.getId().getBasVipGiveIntegral().getGiveInteId()+"','"+v.getId().getBasVipGiveIntegralProject().getGiveInteProId()+"','"+v.getShijiZengfen()+"')");
                        }
                    }
                    msg.setSuccess(true);
                    msg.setMsg("保存成功");
                    setContent("会员积分赠送  - 修改积分赠送明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipScorePresentManagementVo.getVip_Number());
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("请确认,赠送积分项目为空");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("请确认,改会员不存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("请确认,会员卡号不能为空");
        }
        return msg;
    }
	
	@Log(moduleName="会员管理",content="删除积分赠送明细记录",opertype="会员积分赠送")
    public Msg delete(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user) throws Exception {
	    Msg msg = new Msg();
	    BasVipGiveIntegral bvg = vipScorePresentManagementDao.get("from BasVipGiveIntegral where giveInteId="+vipScorePresentManagementVo.getGive_Inte_Id());
	    if(bvg != null){
    	    String sql1 ="delete from bas_vip_relationship_16 where GIVE_INTE_ID="+vipScorePresentManagementVo.getGive_Inte_Id();
            vipScorePresentManagementDao.createSQLQueryOutFind(sql1);
            String sql2 ="delete from bas_vip_give_integral where GIVE_INTE_ID="+vipScorePresentManagementVo.getGive_Inte_Id();
            vipScorePresentManagementDao.createSQLQueryOutFind(sql2);
            msg.setSuccess(true);
            msg.setMsg("删除成功");
            setContent("会员积分赠送  - 删除积分赠送明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipScorePresentManagementVo.getVip_Number());
	    }else{
	        msg.setSuccess(false);
	        msg.setMsg("赠送积分不存在");
	    }
        return msg;
    }
	
	//审核
	@Log(moduleName="会员管理",content="审核积分赠送明细记录",opertype="会员积分赠送")
	public Msg updateShenhe(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)
			throws Exception {
	    Msg msg = new Msg();
	    String state = null;
	    if(vipScorePresentManagementVo.getGive_Inte_Id() != null && !"".equals(vipScorePresentManagementVo.getGive_Inte_Id())){
	        if(vipScorePresentManagementVo.getVip_Number() != null && !"".equals(vipScorePresentManagementVo.getVip_Number())){
        	    BasVipGiveIntegral bvg = vipScorePresentManagementDao.get("from BasVipGiveIntegral where giveInteId="+vipScorePresentManagementVo.getGive_Inte_Id());
        	    if(bvg != null){
            	    List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.AUDIT_TAG.AUDITKEY);
                    if(childs != null && childs.size() > 0){
                        for(int i=0; i<childs.size(); i++){
                            BasChilddictionary c = childs.get(i);
                            if(bvg.getShenheQingkuang().equals(Contstants.AUDIT_TAG.AUDITNOS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITNOS)){
                                state = Contstants.AUDIT_TAG.AUDITYESS;
                                break;
                            }else if(bvg.getShenheQingkuang().equals(Contstants.AUDIT_TAG.AUDITYESS) && c.getDataKey().equals(Contstants.AUDIT_TAG.AUDITYESS)){
                                msg.setSuccess(false);
                                msg.setMsg("该积分赠送单已经审核通过，不需要重复审核");
                                return msg;
                            }
                        }
                    }
                    BasVipInfor bvi = vipRechargeDao.getByVipNumber(vipScorePresentManagementVo.getVip_Number()); 
                    if(bvi != null){
                        bvg.setShenheQingkuang(state);   //审核状态
                        bvg.setShenheRiqi(new Date());
                        bvg.setShenheYuan(user.getBasStuff().getStfId().toString());
                        bvg.setGiveInteNum(bvg.getGiveInteNum() != null && !"".equals(bvg.getGiveInteNum()) ? bvg.getGiveInteNum() : 0.0d);
                        bvi.setVipIntegral(bvi.getVipIntegral() + bvg.getGiveInteNum());
                        bvi.setVipTotalIntegral(bvi.getVipTotalIntegral() + bvg.getGiveInteNum());
                        vipScorePresentManagementDao.update(bvg);
                        vipRecordMessageDao.update(bvi);
                        msg.setSuccess(true);
                        msg.setMsg("审核成功");
                        setContent("会员积分赠送  - 审核积分赠送明细   日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipScorePresentManagementVo.getVip_Number());
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("请确认,该会员不存在");
                    }
        	    }else{
        	        msg.setSuccess(false);
                    msg.setMsg("赠送积分不存在");
        	    }
	        }else{
                msg.setSuccess(false);
                msg.setMsg("请选择要审核的记录");
            }
	    }else{
            msg.setSuccess(false);
            msg.setMsg("请选择要审核的记录");
        }
		return msg;
	}
}