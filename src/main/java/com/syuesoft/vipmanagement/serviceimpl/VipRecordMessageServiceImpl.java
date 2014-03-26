package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipGruopDao;
import com.syuesoft.bas.dao.BasVipLevelDao;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasChilddictionary;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.BasVipGruop;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipInforDefray;
import com.syuesoft.model.BasVipInforNote;
import com.syuesoft.model.BasVipLevel;
import com.syuesoft.model.BasVipStock;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.GetDateByString;
import com.syuesoft.util.Json;
import com.syuesoft.util.MD5;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.dao.VipInforDefrayDao;
import com.syuesoft.vipmanagement.dao.VipRecordMessageDao;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;
/**
 * 会员档案
* @ClassName: VipRecordMessageServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-5 下午02:49:20 
* @version 1.0
 */
@Service("vipRecordMessageService")
public class VipRecordMessageServiceImpl extends BaseLogServiceImpl implements VipRecordMessageService {
	@Autowired
	private VipRecordMessageDao vipRecordMessageDao;
	@Autowired
	private BasVipGruopDao vipGruopDao;
	@Autowired
	private BaseService baseService;
	@Autowired
	private BasVipLevelDao basVipLevelDao;
	@Autowired
	private VipInforDefrayDao vipInforDefrayDao;
    
	public Json findAll(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception {
        Json json = new Json();
        String sql = "SELECT vrm.*, c.dataValue FROM vip_record_message vrm"+
                     " LEFT JOIN (SELECT b.dataKey, b.dataValue FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') c ON (vrm.VIP_STATUS = c.dataKey)"+
                     " where 1 = 1 AND vrm.carenterprise_id ="+user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        if(vipRecordMessageVo.getJoin_Time()!=null && !vipRecordMessageVo.getJoin_Time().trim().equals("")){
            sql += " and vrm.join_time >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getJoin_Time(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getJoin_Time2()!=null && !vipRecordMessageVo.getJoin_Time2().trim().equals("")){
            sql += " and vrm.join_time <= '"+FormatDate.getEndDataString(vipRecordMessageVo.getJoin_Time2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        
        if(vipRecordMessageVo.getEnd_Time()!=null && !vipRecordMessageVo.getEnd_Time().trim().equals("")){
            sql += " and vrm.End_Time >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getEnd_Time(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        
        if(vipRecordMessageVo.getEnd_Time2()!=null && !vipRecordMessageVo.getEnd_Time2().trim().equals("")){
            sql += " and vrm.End_Time <= '"+FormatDate.getEndDataString(vipRecordMessageVo.getEnd_Time2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getVip_Birthday()!=null && !vipRecordMessageVo.getVip_Birthday().trim().equals("")){
            sql += " and vrm.vip_Birthday >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getVip_Birthday(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getVip_Birthday2()!=null && !vipRecordMessageVo.getVip_Birthday2().trim().equals("")){
            sql += " and vrm.vip_Birthday >= '"+FormatDate.getEndDataString(vipRecordMessageVo.getVip_Birthday2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getCar_License()!=null && !vipRecordMessageVo.getCar_License().trim().equals("")){
            sql += " and vrm.Car_License like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCar_License().trim())+"%'";
        }
        if(vipRecordMessageVo.getCar_Vin()!=null && !vipRecordMessageVo.getCar_Vin().trim().equals("")){
            sql += " and vrm.Car_vin like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCar_Vin().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Name()!=null && !vipRecordMessageVo.getVip_Name().trim().equals("")){
            sql += " and vrm.VIP_NAME like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Name().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Tel()!=null && !vipRecordMessageVo.getVip_Tel().trim().equals("")){
            sql += " and vrm.VIP_TEL like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Tel().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Number()!=null && !vipRecordMessageVo.getVip_Number().trim().equals("")){
            sql += " and vrm.Vip_Number like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Number().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Status()!=null && !vipRecordMessageVo.getVip_Status().trim().equals("")){
            sql += " and vrm.Vip_Status = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Status().trim())+"'";
        }
        
        if(vipRecordMessageVo.getVip_Level_Id()!=null && !vipRecordMessageVo.getVip_Level_Id().trim().equals("")){
            sql += " and vrm.VIP_LEVEL_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Level_Id().trim())+"'";
        }
        if(vipRecordMessageVo.getVip_Group_Id()!=null && !vipRecordMessageVo.getVip_Group_Id().trim().equals("")){
            sql += " and vrm.VIP_GRUOP_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Group_Id().trim())+"'";
        }
        if(vipRecordMessageVo.getVip_Age()!=null && !vipRecordMessageVo.getVip_Age().trim().equals("")){
            sql += " and vrm.Vip_Age = "+vipRecordMessageVo.getVip_Age()+"";
        }
        if(vipRecordMessageVo.getCtype_Name()!=null && !vipRecordMessageVo.getCtype_Name().trim().equals("")){
            sql += " and vrm.CTYPE_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCtype_Name().trim())+"'";
        }
        if(vipRecordMessageVo.getCbrd_Name()!=null && !vipRecordMessageVo.getCbrd_Name().trim().equals("")){
            sql += " and vrm.CBRD_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCbrd_Name().trim())+"'";
        }
        if(vipRecordMessageVo.getCbrl_Name()!=null && !vipRecordMessageVo.getCbrl_Name().trim().equals("")){
            sql += " and vrm.car_cstl_name = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCbrl_Name().trim())+"'";
        }
        
        if(vipRecordMessageVo.getParea_Name()!=null && !vipRecordMessageVo.getParea_Name().trim().equals("")){
            sql += " and vrm.Parea_Name like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getParea_Name().trim())+"%'";
        }
        //判断是否是会员
        if(vipRecordMessageVo.getCar_Flag()!=null ){
            if(vipRecordMessageVo.getCar_Flag().trim().equals("fhy")){
                sql += " and (vrm.VIP_NUMBER IS NULL OR vrm.VIP_NUMBER = '')";
            }
            if(vipRecordMessageVo.getCar_Flag().equals("hy")){
                sql += " and (vrm.VIP_NUMBER IS NOT NULL OR vrm.VIP_NUMBER <> '')";
            }
        }
        if(vipRecordMessageVo.getVip_Integral()!=null && !vipRecordMessageVo.getVip_Integral().trim().equals("")){
            String[] str = vipRecordMessageVo.getVip_Integral().split(",");
            sql += " and vrm.Vip_Integral between "+str[0]+" and "+str[1]+"";
        }
        if(vipRecordMessageVo.getSort()!=null && !vipRecordMessageVo.getSort().equals("") && vipRecordMessageVo.getOrder()!=null && !vipRecordMessageVo.getOrder().equals("")){
            sql += " order by vrm."+vipRecordMessageVo.getSort()+" "+vipRecordMessageVo.getOrder()+"";
        }
        List<Object[]> volist = vipRecordMessageDao.createSQLQuery(sql, vipRecordMessageVo.getPage(), vipRecordMessageVo.getRows());
        int total = vipRecordMessageDao.getCountBySQL(sql);
        Object[] obj = null;
        List<VipRecordMessageVo> list = new ArrayList<VipRecordMessageVo>();
        if(volist != null && volist.size() > 0){
            for (int i = 0; i < volist.size(); i++) {
                obj = (Object[])volist.get(i);
                VipRecordMessageVo vo = new VipRecordMessageVo();
                if(obj[0]!=null){vo.setCar_Id(obj[0]+"");}
                if(obj[1]!=null){vo.setCar_License(obj[1]+"");}
                if(obj[2]!=null){vo.setCar_Vin(obj[2]+"");}
                //obj[3]是型号id
                if(obj[4]!=null){vo.setCbrd_Name(obj[4]+"");}
                //obj[5]是品牌id
                if(obj[6]!=null){vo.setCtype_Name(obj[6]+"");}
                //obj[7]是颜色id
                if(obj[8]!=null){vo.setColor_Name(obj[8]+"");}
                if(obj[9]!=null){vo.setCustom_Name(obj[9]+"");}
                if(obj[10]!=null){
                    if(obj[10].toString().trim().toUpperCase().equals("S")){vo.setCustom_Sex("男");}
                    else if(obj[10].toString().trim().toUpperCase().equals("M")){vo.setCustom_Sex("女");}
                    else{vo.setCustom_Sex("");}
                }
                if(obj[11]!=null){vo.setCustom_Birthday(FormatTime.timestamp2Str((Timestamp) obj[11]));}
                if(obj[12]!=null){vo.setCustom_Tel1(obj[12]+"");}
                if(obj[13]!=null){vo.setCustom_Tel2(obj[13]+"");}
                //obj[14]是区域id
                if(obj[15]!=null){vo.setParea_Name(obj[15]+"");}
                if(obj[16]!=null){vo.setCustom_Addr(obj[16]+"");}
                if(obj[17]!=null){vo.setVip_Number(obj[17]+"");}
                if(obj[18]!=null){vo.setVip_Level_Id(obj[18]+"");}
                if(obj[19]!=null){vo.setVip_Level_Name(obj[19]+"");}
                if(obj[22]!=null){vo.setVip_Group_Id(obj[20]+"");}
                if(obj[21]!=null){vo.setVip_Group_Name(obj[21]+"");}
                if(obj[22]!=null){vo.setVip_Status(obj[22]+"");}
                if(obj[23]!=null){vo.setJoin_Time(FormatTime.timestamp2Str((Timestamp) obj[23]));}
                if(obj[24]!=null){vo.setEnd_Time(FormatTime.timestamp2Str((Timestamp) obj[24]));}
                if(obj[25]!=null){vo.setVip_Age(obj[25]+"");}
                if(obj[26]!=null){vo.setVip_Balance(obj[26]+"");}
                if(obj[27]!=null){vo.setVip_Integral(obj[27]+"");}
                if(obj[28]!=null){vo.setVip_Hobby(obj[28]+"");}
                if(obj[29]!=null){vo.setCar_Flag(obj[29]+"");}
                if(obj[30]!=null){vo.setCustom_Id(obj[30]+"");}
                if(obj[31]!=null){vo.setCar_Motor_Id(obj[31]+"");}
                if(obj[32]!=null){vo.setCar_Buy_Date(FormatTime.timestamp2Str((Timestamp) obj[32]));}
                if(obj[33]!=null){vo.setCar_License_Date(FormatTime.timestamp2Str((Timestamp) obj[33]));}
                if(obj[34]!=null){vo.setSls_Id(obj[34]+"");}
                if(obj[35]!=null){vo.setCar_Basinsurance_Date(FormatTime.timestamp2Str((Timestamp) obj[35]));}
                if(obj[36]!=null){vo.setCar_Businsurance_Date(FormatTime.timestamp2Str((Timestamp) obj[36]));}
                if(obj[37]!=null){vo.setCar_Last_Repair_Date(FormatTime.timestamp2Str((Timestamp) obj[37]));}
                if(obj[38]!=null){vo.setCar_Last_Maint_Date(FormatTime.timestamp2Str((Timestamp) obj[38]));}
                if(obj[39]!=null){vo.setCst_Name(obj[39]+"");}
                if(obj[40]!=null){vo.setCstg_Name(obj[40]+"");}
                //obj[41]是车辆款式id
                if(obj[42]!=null){vo.setCbrl_Name(obj[42]+"");}
                if(obj[43]!=null){vo.setVip_Id(obj[43]+"");}
                if(obj[44]!=null){vo.setVip_Total_Integral(obj[44]+"");}
                if(obj[45]!=null){vo.setEnterprise_id(obj[45]+"");}
                if(obj[46]!=null){vo.setEnterprise_name(obj[46]+"");}
                if(obj[47]!=null){vo.setEnterprise_id2(obj[47]+"");}
                if(obj[48]!=null){vo.setEnterprise_name2(obj[48]+"");}
                if(obj[49]!=null){vo.setVip_Name(obj[49]+"");}
                if(obj[50]!=null){vo.setVip_Birthday(FormatTime.timestamp2Str((Timestamp) obj[50]));}
                if(obj[51]!=null){vo.setVip_Tel(obj[51]+"");}
                if(obj[52]!=null){vo.setEnterprise_id3(obj[52]+"");}
                if(obj[53]!=null){vo.setEnterprise_name3(obj[53]+"");}
                if(obj[54]!=null){vo.setVip_Status_value(obj[54]+"");}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
	
	@Log(moduleName="会员管理",opertype="会员档案",content="会员入会")
    public Message add(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception {
	    Message msg = new Message();
        BasVipInfor bvi = new BasVipInfor();
        BasVipLevel bvl = null;
        BasVipGruop bvg = null;
        if(getBasCarInfor(vipRecordMessageVo.getCar_License(), vipRecordMessageVo.getCar_Vin(), user)){
            if(getBasVipInfor(vipRecordMessageVo.getVip_Number(), getParentEnterpriseId(user)) == null){
                if(getBasVipInfor2(vipRecordMessageVo.getCar_License(), vipRecordMessageVo.getCar_Vin(), getParentEnterpriseId(user)) == null){
                    List<BasVipLevel> list1 = basVipLevelDao.find("from BasVipLevel where vipLevelId='"+ vipRecordMessageVo.getVip_Level_Id()+"'");
                    if(list1 != null && list1.size() > 0){
                        bvl = list1.get(0);
                        bvi.setBasVipLevel(bvl);
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("会员等级不存在");
                        return msg;
                    }
                    List<BasVipGruop> list2 = vipGruopDao.find("from BasVipGruop where vipGruopId='"+ vipRecordMessageVo.getVip_Group_Id()+"'");
                    if(list2 != null && list2.size() > 0){
                        bvg = list2.get(0);
                        bvi.setBasVipGruop(bvg);
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("会员分组不存在");
                        return msg;
                    }
                    EnterpriseInfo en = user.getBasStuff().getEnterpriseInfo();
                    if(en != null){
                        if(vipRecordMessageVo.getVip_Balance()!=null && !vipRecordMessageVo.getVip_Balance().trim().equals("")){
                            bvi.setVipBalance(Double.parseDouble(vipRecordMessageVo.getVip_Balance()));
                        }else{
                            bvi.setVipBalance(0.0d);
                        }
                        if(vipRecordMessageVo.getVip_Birthday()!=null && !vipRecordMessageVo.getVip_Birthday().equals("")){
                            bvi.setVipBirthday(FormatTime.str2Date(vipRecordMessageVo.getVip_Birthday(), FormatTime.DEFAULT_FORMAT));
                        }else{
                            bvi.setVipBirthday(FormatTime.str2Date(vipRecordMessageVo.getCustom_Birthday(), FormatTime.DEFAULT_FORMAT));
                        }
                        bvi.setVipHobby(vipRecordMessageVo.getVip_Hobby());
                        //调用工具类自动生成会员id
                        if(vipRecordMessageVo.getVip_Id() != null && !"".equals(vipRecordMessageVo.getVip_Id())){
                            bvi.setVipId(vipRecordMessageVo.getVip_Id());
                        }else{
                            bvi.setVipId(CreateID.createId("BasVipInfor", "HY"));
                        }
                        if(vipRecordMessageVo.getVip_Integral()!=null && !vipRecordMessageVo.getVip_Integral().trim().equals("")){
                            bvi.setVipIntegral(Double.parseDouble(vipRecordMessageVo.getVip_Integral()));
                            bvi.setVipTotalIntegral(Double.parseDouble(vipRecordMessageVo.getVip_Integral()));
                        }
                        bvi.setCarLicense(vipRecordMessageVo.getCar_License());
                        bvi.setCarVin(vipRecordMessageVo.getCar_Vin());
                        bvi.setVipName(vipRecordMessageVo.getCustom_Name());
                        bvi.setVipNumber(vipRecordMessageVo.getVip_Number());
                        if(vipRecordMessageVo.getVip_Status() != null && !"".equals(vipRecordMessageVo.getVip_Status())){
                            bvi.setVipStatus(vipRecordMessageVo.getVip_Status());
                        }else{
                            List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.HYKZT.HYKZT);
                            if(childs != null && childs.size() > 0){
                                for(int i=0; i<childs.size(); i++){
                                    BasChilddictionary c = childs.get(i);
                                    if(c.getDataKey().equals(Contstants.HYKZT.ZC)){
                                        bvi.setVipStatus(Contstants.HYKZT.ZC);
                                        break;
                                    }
                                }
                            }else{
                                msg.setSuccess(false);
                                msg.setMsg("会员卡状态不存在");
                                return msg;
                            }
                        }
                        if(vipRecordMessageVo.getVip_Password() != null && !"".equals(vipRecordMessageVo.getVip_Password())){
                            bvi.setVipPassword(MD5.MD5Encode(vipRecordMessageVo.getVip_Password()));
                        }
                        if(vipRecordMessageVo.getGb_Fee() != null && !"".equals(vipRecordMessageVo.getGb_Fee())){
                            bvi.setVipGbfee(Double.parseDouble(vipRecordMessageVo.getGb_Fee()));
                        }else{
                            bvi.setVipGbfee(0.0d);
                        }
                        bvi.setMakeUser(user.getBasStuff().getStfId().toString());
                        bvi.setJoinTime(new Date());
                        bvi.setVipTel(vipRecordMessageVo.getCustom_Tel1());
                        if(vipRecordMessageVo.getVip_Integral() != null && !"".equals(vipRecordMessageVo.getVip_Integral())){
                            bvi.setVipIntegral(Double.parseDouble(vipRecordMessageVo.getVip_Integral()));
                        }else{
                            bvi.setVipIntegral(0.0d);
                        }
                        if(vipRecordMessageVo.getVip_Total_Integral() != null && !"".equals(vipRecordMessageVo.getVip_Total_Integral())){
                            bvi.setVipTotalIntegral(Double.parseDouble(vipRecordMessageVo.getVip_Total_Integral()));
                        }else{
                            bvi.setVipTotalIntegral(0.0d);
                        }
                        bvi.setMemo(vipRecordMessageVo.getMemo());
                        if(vipRecordMessageVo.getEnd_Time()!=null && !vipRecordMessageVo.getEnd_Time().equals("")){
                            bvi.setEndTime(FormatTime.str2Date(vipRecordMessageVo.getEnd_Time(), FormatTime.DEFAULT_FORMAT));
                        }else{
                            bvi.setEndTime(FormatTime.str2Date(FormatTime.monYear(FormatTime.DEFAULT_FORMAT), FormatTime.DEFAULT_FORMAT));
                        }
                        bvi.setEnterprise_id(en.getEnterpriseId());
                        bvi.setEnterprise_id2(getParentEnterpriseId(user));
                        vipRecordMessageDao.save(bvi);
                        msg.setSuccess(true);
                        msg.setMsg("会员入会成功");
                        setContent("会员管理- 会员新增 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipRecordMessageVo.getVip_Number());
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("请确认您是否有公司组织结构");
                        return msg;
                    }
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("车牌照【"+vipRecordMessageVo.getCar_License()+"】车架号【"+vipRecordMessageVo.getCar_Vin()+"】已经是会员,不需要重复入会");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员卡号已经存在");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("车牌照为："+vipRecordMessageVo.getCar_License()+",车架号为："+ vipRecordMessageVo.getCar_Vin()+"的车不属于本企业的");
        }
        return msg;
    }
	
    @Log(moduleName="会员管理",opertype="会员档案",content="会员资料修改")
	public Message update(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception {
	    Message msg = new Message();
	    GetDateByString gt = new GetDateByString();//工具类 通过字符串获取时间格式
	    BasVipLevel bvl = null;
        BasVipGruop bvg = null;
        if(getBasCarInfor(vipRecordMessageVo.getCar_License(), vipRecordMessageVo.getCar_Vin(), user)){
            BasVipInfor bvi = vipRecordMessageDao.get("from BasVipInfor where vipId ='"+vipRecordMessageVo.getVip_Id()+"' and vipNumber ='"+vipRecordMessageVo.getVip_Number()+"' and carLicense ='"+vipRecordMessageVo.getCar_License()+"' and carVin='"+vipRecordMessageVo.getCar_Vin()+"'");//更改了，通过车辆编号查询会员信息
            if(bvi != null){
                List<BasVipLevel> list1 = basVipLevelDao.find("from BasVipLevel where vipLevelId="+ vipRecordMessageVo.getVip_Level_Id());
                if(list1 != null && list1.size() > 0){
                     bvl = list1.get(0);
                     bvi.setBasVipLevel(bvl);
                }
                List<BasVipGruop> list2 = vipGruopDao.find("from BasVipGruop where vipGruopId="+ vipRecordMessageVo.getVip_Group_Id());
                if(list2 != null && list2.size() > 0){
                    bvg = list2.get(0);
                    bvi.setBasVipGruop(bvg);
                }
                if(vipRecordMessageVo.getEnd_Time() != null){
                    bvi.setEndTime(gt.getDate(vipRecordMessageVo.getEnd_Time()));
                }else{
                    bvi.setEndTime(new Date());
                }
                bvi.setVipName(vipRecordMessageVo.getVip_Name());
                bvi.setVipTel(vipRecordMessageVo.getVip_Tel());
                bvi.setVipHobby(vipRecordMessageVo.getVip_Hobby());
                vipRecordMessageDao.update(bvi);
                msg.setSuccess(true);
                msg.setMsg("修改会员记录成功");
                setContent("会员管理- 会员资料修改 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipRecordMessageVo.getVip_Number());
            }else{
                msg.setSuccess(false);
                msg.setMsg("修改会员记录失败");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("车牌照为："+vipRecordMessageVo.getCar_License()+",车架号为："+ vipRecordMessageVo.getCar_Vin()+"的车不属于本企业的");
        }
        return msg;
	}
    
	public Message findVip(VipRecordMessageVo vipRecordMessageVo) throws Exception
    {
	    Message msg = new Message();
	    if(vipRecordMessageVo.getVip_Password() != null && !"".equals(vipRecordMessageVo.getVip_Password())){
	        vipRecordMessageVo.setVip_Password(MD5.MD5Encode(vipRecordMessageVo.getVip_Password()));
        }
	    BasVipInfor bvi = vipRecordMessageDao.get("from BasVipInfor where vipId ='"+vipRecordMessageVo.getVip_Id()+"' and vipNumber ='"+vipRecordMessageVo.getVip_Number()+"' and carLicense ='"+vipRecordMessageVo.getCar_License()+"' and carVin='"+vipRecordMessageVo.getCar_Vin()+"' and vipPassword='"+vipRecordMessageVo.getVip_Password()+"'");//更改了，通过车辆编号查询会员信息
        if(bvi != null){
            msg.setSuccess(true);
            msg.setMsg("会员登陆成功");
        }else{
            msg.setSuccess(false);
            msg.setMsg("会员信息不存在");
        }
        return msg;
    }
	
	@Log(moduleName="会员管理",opertype="会员档案",content="会员状态修改")
	public Message updateVipState(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception
    {
	    Message msg = new Message();
	    String result = "old修改为new";
	    if(getBasCarInfor(vipRecordMessageVo.getCar_License(), vipRecordMessageVo.getCar_Vin(), user)){
    	    BasVipInfor bvi = vipRecordMessageDao.get("from BasVipInfor where vipId ='"+vipRecordMessageVo.getVip_Id()+"' and vipNumber ='"+vipRecordMessageVo.getVip_Number()+"' and carLicense ='"+vipRecordMessageVo.getCar_License()+"' and carVin='"+vipRecordMessageVo.getCar_Vin()+"'");//更改了，通过车辆编号查询会员信息
            if(bvi != null){
                BasVipInforNote bvin = new BasVipInforNote();
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.HYKZT.HYKZT);
                if(vipRecordMessageVo.getVip_Status() != null && "解冻".equals(vipRecordMessageVo.getVip_Status())){
                    if(bvi.getVipStatus() != null && Contstants.HYKZT.DJ.equals(bvi.getVipStatus())){
                        if(childs != null && childs.size() > 0){
                            for(int i=0; i<childs.size(); i++){
                                BasChilddictionary c = childs.get(i);
                                if(c.getDataKey().equals(Contstants.HYKZT.ZC)){
                                    result = result.replace("old", "冻结").replace("new", "解冻--正常");
                                    bvi.setVipStatus(Contstants.HYKZT.ZC);
                                    bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE4);
                                    break;
                                }
                            }
                        }
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("会员非冻结状态，不能解冻");
                        return msg;
                    }
                }else{
                    if(vipRecordMessageVo.getVip_Status() != null && "注销".equals(vipRecordMessageVo.getVip_Status())){
                        if(childs != null && childs.size() > 0){
                            for(int i=0; i<childs.size(); i++){
                                BasChilddictionary c = childs.get(i);
                                if(c.getDataKey().equals(Contstants.HYKZT.ZX)){
                                    bvi.setVipStatus(Contstants.HYKZT.ZX);
                                    bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE1);
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.DJ.equals(bvi.getVipStatus())){
                                         result = result.replace("old", "冻结").replace("new", "注销");
                                         break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.DQ.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "到期").replace("new", "注销");
                                        break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.GS.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "挂失").replace("new", "注销");
                                        break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.JD.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "解冻").replace("new", "注销");
                                        break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.WX.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "无效").replace("new", "注销");
                                        break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.ZC.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "正常").replace("new", "注销");
                                        break;
                                    }
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.ZX.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "注销").replace("new", "注销");
                                        break;
                                    }
                                }
                            }
                        }
                    }else if(vipRecordMessageVo.getVip_Status() != null && "冻结".equals(vipRecordMessageVo.getVip_Status())){
                        if(childs != null && childs.size() > 0){
                            for(int i=0; i<childs.size(); i++){
                                BasChilddictionary c = childs.get(i);
                                if(c.getDataKey().equals(Contstants.HYKZT.DJ)){
                                    bvi.setVipStatus(Contstants.HYKZT.DJ);
                                    bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE3);
                                    if(bvi.getVipStatus() != null && Contstants.HYKZT.DJ.equals(bvi.getVipStatus())){
                                        result = result.replace("old", "冻结").replace("new", "冻结");
                                        break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.DQ.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "到期").replace("new", "冻结");
                                       break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.GS.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "挂失").replace("new", "冻结");
                                       break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.JD.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "解冻").replace("new", "冻结");
                                       break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.WX.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "无效").replace("new", "冻结");
                                       break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.ZC.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "正常").replace("new", "冻结");
                                       break;
                                   }
                                   if(bvi.getVipStatus() != null && Contstants.HYKZT.ZX.equals(bvi.getVipStatus())){
                                       result = result.replace("old", "注销").replace("new", "冻结");
                                       break;
                                   }
                                }
                            }
                        }
                    }
                }
                bvin.setAdjournmentDate(new Date());
                bvin.setAdjournmentManage(user.getBasStuff().getStfId().toString());
                bvin.setAdjournmentRep(0.0d);
                bvin.setBasVipInfor(bvi);
                bvi.getBasVipInforNotes().add(bvin);
                vipRecordMessageDao.update(bvi);
                msg.setSuccess(true);
                msg.setMsg("会员卡状态修改成功");
                setContent("会员管理- 会员状态修改 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipRecordMessageVo.getVip_Number());
            }else{
                msg.setSuccess(false);
                msg.setMsg("会员信息不存在");
            }
	    }else{
            msg.setSuccess(false);
            msg.setMsg("车牌照为："+vipRecordMessageVo.getCar_License()+",车架号为："+ vipRecordMessageVo.getCar_Vin()+"的车不属于本企业的");
        }
        return msg;
    }
	
	@Log(moduleName="会员管理",opertype="会员档案",content="会员挂失")
	public Message updateLoss(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception
    {
	    Message msg = new Message();
	    if(vipRecordMessageVo.getVip_Number() != null && !"".equals(vipRecordMessageVo.getVip_Number())){
	        if(getBasCarInfor(vipRecordMessageVo.getCar_License(), vipRecordMessageVo.getCar_Vin(), user)){
    	        if(getBasVipInfor(vipRecordMessageVo.getVip_Number(), getParentEnterpriseId(user)) == null){
                    BasVipInfor bvi = vipRecordMessageDao.get("from BasVipInfor where vipId ='"+vipRecordMessageVo.getVip_Id()+"' and vipNumber ='"+vipRecordMessageVo.getVip_OldNumber()+"' and carLicense ='"+vipRecordMessageVo.getCar_License()+"' and carVin='"+vipRecordMessageVo.getCar_Vin()+"'");//更改了，通过车辆编号查询会员信息
                    if(bvi != null){
                        BasVipInforNote bvin = new BasVipInforNote();
                        if(vipRecordMessageVo.getGb_Fee() != null && !"".equals(vipRecordMessageVo.getGb_Fee())){
                            bvi.setVipGbfee(bvi.getVipGbfee() + Double.parseDouble(vipRecordMessageVo.getGb_Fee())) ;
                        }
                        bvi.setVipNumber(vipRecordMessageVo.getVip_Number());
                        bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE2);
                        bvin.setAdjournmentDate(new Date());
                        bvin.setAdjournmentManage(user.getBasStuff().getStfId().toString());
                        bvin.setAdjournmentRep(0.0d);
                        bvin.setBasVipInfor(bvi);
                        bvi.getBasVipInforNotes().add(bvin);
                        vipRecordMessageDao.update(bvi);
                        msg.setSuccess(true);
                        msg.setMsg("修改会员记录成功");
                        setContent("会员管理- 会员挂失/补办 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 原会员卡号："+vipRecordMessageVo.getVip_OldNumber()+" 新会员卡号："+vipRecordMessageVo.getVip_Number());
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("修改会员记录失败");
                    }
    	        }else{
    	            msg.setSuccess(false);
    	            msg.setMsg("会员卡号已经存在");
    	        }
	        }else{
	            msg.setSuccess(false);
	            msg.setMsg("车牌照为："+vipRecordMessageVo.getCar_License()+",车架号为："+ vipRecordMessageVo.getCar_Vin()+"的车不属于本企业的");
	        }
	    }else{
	        msg.setSuccess(false);
            msg.setMsg("会员卡号不能为空");
	    }
        return msg;
    }
	
	public List<ComboxVo> getBasVipGruop(BasUsers user) throws Exception {
	    List<ComboxVo> alist = new ArrayList<ComboxVo>();
	    List<BasVipGruop> list = vipRecordMessageDao.getBasVipGruop(user);
	    if(list != null && list.size() > 0){
    	    for (BasVipGruop basVipGruop : list) {
    	        ComboxVo vo = new ComboxVo();
                vo.setId(basVipGruop.getVipGruopId().toString());
                vo.setName(basVipGruop.getVipGruopName());
                alist.add(vo);
            }
	    }
	    return alist;
	}
	
	public List<ComboxVo> getBasVipLevel(BasUsers user) throws Exception {
	    List<ComboxVo> alist = new ArrayList<ComboxVo>();
	    List<BasVipLevel> list = vipRecordMessageDao.getBasVipLevel(user);
	    if(list != null && list.size() > 0){
    	    for (BasVipLevel basVipLevel : list) {
    	        ComboxVo vo = new ComboxVo();
                vo.setId(basVipLevel.getVipLevelId().toString());
                vo.setName(basVipLevel.getVipLevelName());
                alist.add(vo);
            }
	    }
	    return alist;
	}

    public Json getVipInfo(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception
    {
        Json json = new Json();
        List<VipRecordMessageVo> list = new ArrayList<VipRecordMessageVo>();
        String sql = "SELECT A.*, B.VIP_STATUSVALUE FROM vip_adjournment_summary A"+
        " LEFT JOIN (SELECT b.dataKey, b.dataValue VIP_STATUSVALUE FROM bas_parentdictionary a, bas_childdictionary b WHERE a.parent_id = b.parent_id AND a.dataKey='"+Contstants.HYKZT.HYKZT+"') B ON (A.VIP_STATUS = B.dataKey)"+
        " where 1 = 1 ";
        if(vipRecordMessageVo.getAdjournment_Date()!=null && !vipRecordMessageVo.getAdjournment_Date().trim().equals("")){
            sql += " and A.ADJOURNMENT_DATE >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getAdjournment_Date(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getAdjournment_Date2()!=null && !vipRecordMessageVo.getAdjournment_Date2().trim().equals("")){
            sql += " and A.ADJOURNMENT_DATE <= '"+FormatDate.getEndDataString(vipRecordMessageVo.getAdjournment_Date2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getExitmember_Date()!=null && !vipRecordMessageVo.getExitmember_Date().trim().equals("")){
            sql += " and A.EXITMEMBER_DATE >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getExitmember_Date(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getExitmember_Date2()!=null && !vipRecordMessageVo.getExitmember_Date2().trim().equals("")){
            sql += " and A.EXITMEMBER_DATE <= '"+FormatDate.getEndDataString(vipRecordMessageVo.getExitmember_Date2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getEnd_Time()!=null && !vipRecordMessageVo.getEnd_Time().trim().equals("")){
            sql += " and A.End_Time >= '"+FormatDate.getStartDataString(vipRecordMessageVo.getEnd_Time(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getEnd_Time2()!=null && !vipRecordMessageVo.getEnd_Time2().trim().equals("")){
            sql += " and A.End_Time <= '"+FormatDate.getEndDataString(vipRecordMessageVo.getEnd_Time2(),FormatTime.DEFAULT_FORMAT2)+"'";
        }
        if(vipRecordMessageVo.getCar_License()!=null && !vipRecordMessageVo.getCar_License().trim().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCar_License().trim())+"%'";
        }
        if(vipRecordMessageVo.getCar_Vin()!=null && !vipRecordMessageVo.getCar_Vin().trim().equals("")){
            sql += " and A.Car_vin like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getCar_Vin().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Age()!=null && !vipRecordMessageVo.getVip_Age().trim().equals("")){
            sql += " and A.Vip_Age = "+vipRecordMessageVo.getVip_Age()+"";
        }
        if(vipRecordMessageVo.getVip_Name()!=null && !vipRecordMessageVo.getVip_Name().trim().equals("")){
            sql += " and A.VIP_NAME like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Name().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Tel()!=null && !vipRecordMessageVo.getVip_Tel().trim().equals("")){
            sql += " and A.VIP_TEL like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Tel().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Number()!=null && !vipRecordMessageVo.getVip_Number().trim().equals("")){
            sql += " and A.Vip_Number like '%"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Number().trim())+"%'";
        }
        if(vipRecordMessageVo.getVip_Status()!=null && !vipRecordMessageVo.getVip_Status().trim().equals("")){
            sql += " and A.Vip_Status = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Status().trim())+"'";
        }
        
        if(vipRecordMessageVo.getVip_Level_Id()!=null && !vipRecordMessageVo.getVip_Level_Id().trim().equals("")){
            sql += " and A.VIP_LEVEL_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Level_Id().trim())+"'";
        }
        if(vipRecordMessageVo.getVip_Group_Id()!=null && !vipRecordMessageVo.getVip_Group_Id().trim().equals("")){
            sql += " and A.VIP_GRUOP_ID = '"+StringEscapeUtils.escapeSql(vipRecordMessageVo.getVip_Group_Id().trim())+"'";
        }
        int total = vipRecordMessageDao.getCountBySQL(sql);
        List<Object[]> volist = vipRecordMessageDao.createSQLQuery(sql, vipRecordMessageVo.getPage(), vipRecordMessageVo.getRows());
        if(volist != null && volist.size() > 0){
            for (int i = 0; i < volist.size(); i++) {
                Object[] obj = (Object[])volist.get(i);
                VipRecordMessageVo vo = new VipRecordMessageVo();
                if(obj[0]!=null){vo.setCar_License(obj[0]+"");}
                if(obj[1]!=null){vo.setCar_Vin(obj[1]+"");}
                if(obj[2]!=null){vo.setEnd_Time(obj[2]+"");}
                if(obj[3]!=null){vo.setVip_Name(obj[3]+"");}
                if(obj[4]!=null){vo.setVip_Tel(obj[4]+"");}
                if(obj[5]!=null){vo.setVip_Number(obj[5]+"");}
                if(obj[6]!=null){vo.setVip_Group_Id(obj[6]+"");}
                if(obj[7]!=null){vo.setVip_Group_Name(obj[7]+"");}
                if(obj[8]!=null){vo.setVip_Level_Id(obj[8]+"");}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9]+"");}
                if(obj[10]!=null){vo.setVip_Status(obj[10]+"");}
                if(obj[11]!=null){vo.setVip_Age(obj[11]+"");}
                if(obj[12]!=null){vo.setVip_Birthday(obj[12]+"");}
                if(obj[13]!=null){vo.setJoin_Time(obj[13]+"");}
                if(obj[14]!=null){vo.setVip_Balance(obj[14]+"");}
                if(obj[15]!=null){vo.setVip_Integral(obj[15]+"");}
                if(obj[16]!=null){vo.setVip_Hobby(obj[16]+"");}
                if(obj[17]!=null){vo.setVip_Id(obj[17]+"");}
                if(obj[18]!=null){vo.setVip_Total_Integral(obj[18]+"");}
                if(obj[19]!=null){vo.setVipGbfee(obj[19]+"");}
                if(obj[20]!=null){vo.setAdjournment_Date(obj[20]+"");}
                if(obj[21]!=null){vo.setAdjournment_Memo(obj[21]+"");}
                if(obj[22]!=null){vo.setAdjournment_Integral(obj[22]+"");}
                if(obj[23]!=null){vo.setAdjournment_Manage(obj[23]+"");}
                if(obj[24]!=null){vo.setExitmember_Date(obj[24]+"");}
                if(obj[25]!=null){vo.setExitmember_Deduct(obj[25]+"");}
                if(obj[26]!=null){vo.setExitmember_Memo(obj[26]+"");}
                if(obj[27]!=null){vo.setExitmember_Manage(obj[27]+"");}
                if(obj[28]!=null){vo.setEnterprise_id(obj[28]+"");}
                if(obj[29]!=null){vo.setEnterprise_id2(obj[29]+"");}
                if(obj[30]!=null){vo.setAdjournment_ManageName(obj[30]+"");}
                if(obj[31]!=null){vo.setExitmember_ManageName(obj[31]+"");}
                if(obj[32]!=null){vo.setVip_Status_value(obj[32]+"");}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }

    public Message updateVipAdjournment(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception
    {
        Message msg = new Message();
        double integral = 0.0d;
        if(vipRecordMessageVo != null && vipRecordMessageVo.getVip_Number() != null && !"".equals(vipRecordMessageVo.getVip_Number())){
            if(vipRecordMessageVo.getEnd_Time2() != null && !"".equals(vipRecordMessageVo.getEnd_Time2())){
                BasVipInfor vipInfo = getBasVipInfor(vipRecordMessageVo.getVip_Number(), getParentEnterpriseId(user));
                Date end = FormatTime.str2Timestamp(vipRecordMessageVo.getEnd_Time2());
                if(end != null){
                    if(FormatDate.dateDiffDay(vipInfo.getEndTime(), end) > 0){
                        vipInfo.setEndTime(end);
                        if(vipRecordMessageVo.getAdjournment_Integral() != null && !"0".equals(vipRecordMessageVo.getAdjournment_Integral()) && !"0.0".equals(vipRecordMessageVo.getAdjournment_Integral())){
                            integral = Double.parseDouble(vipRecordMessageVo.getAdjournment_Integral());
                        }
                        vipInfo.setVipIntegral(vipInfo.getVipIntegral() + integral);
                        vipInfo.setVipTotalIntegral(vipInfo.getVipTotalIntegral() + integral);
                        BasVipInforNote bvin = new BasVipInforNote();
                        bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE5);
                        bvin.setAdjournmentDate(new Date());
                        bvin.setAdjournmentManage(user.getBasStuff().getStfId().toString());
                        bvin.setAdjournmentRep(integral);
                        bvin.setBasVipInfor(vipInfo);
                        bvin.setAdjournmentMemo(vipRecordMessageVo.getAdjournment_Memo());
                        vipInfo.getBasVipInforNotes().add(bvin);
                        vipRecordMessageDao.saveOrUpdate(vipInfo);
                        msg.setMsg("恭喜你,续会成功");
                        msg.setSuccess(true);
                    }else{
                        msg.setMsg("很遗憾,续会日期不能小于原到期日期");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("很遗憾,续会日期不能为空");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("很遗憾,请认真确认数据是否完整");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("很遗憾,请认真确认数据是否完整");
            msg.setSuccess(false);
        }
        return msg;
    }

    public Message updateVipExitMember(VipRecordMessageVo vipRecordMessageVo, BasUsers user) throws Exception
    {
        Message msg = new Message();
        double integral = 0.0d;
        if(vipRecordMessageVo != null && vipRecordMessageVo.getVip_Number() != null && !"".equals(vipRecordMessageVo.getVip_Number())){
            BasVipInfor vipInfo = getBasVipInfor(vipRecordMessageVo.getVip_Number(), getParentEnterpriseId(user));
            if(vipRecordMessageVo.getExitmember_Deduct() != null && !"0".equals(vipRecordMessageVo.getExitmember_Deduct()) && !"0.0".equals(vipRecordMessageVo.getExitmember_Deduct())){
                integral = Double.parseDouble(vipRecordMessageVo.getExitmember_Deduct());
            }
            vipInfo.setVipIntegral(vipInfo.getVipIntegral() - integral);
            vipInfo.setVipTotalIntegral(vipInfo.getVipTotalIntegral() - integral);
            List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.HYKZT.HYKZT);
            if(childs != null && childs.size() > 0){
                for(int i=0; i<childs.size(); i++){
                    BasChilddictionary c = childs.get(i);
                    if(c.getDataKey().equals(Contstants.HYKZT.WX)){
                        vipInfo.setVipStatus(Contstants.HYKZT.WX);
                        break;
                    }
                }
            }
            BasVipInforNote bvin = new BasVipInforNote();
            bvin.setAdjournmentType(Contstants.VIPOPERATETYPE.OPERATE6);
            bvin.setAdjournmentDate(new Date());
            bvin.setAdjournmentManage(user.getBasStuff().getStfId().toString());
            bvin.setAdjournmentRep(integral);
            bvin.setBasVipInfor(vipInfo);
            bvin.setAdjournmentMemo(vipRecordMessageVo.getAdjournment_Memo());
            vipInfo.getBasVipInforNotes().add(bvin);
            vipRecordMessageDao.saveOrUpdate(vipInfo);
            msg.setMsg("恭喜你,退会成功");
            msg.setSuccess(true);
        }else{
            msg.setMsg("很遗憾,请认真确认数据是否完整");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    public BasVipInfor getBasVipInfor(String vipNumber, Integer parentEnterpriseId) throws Exception{
        //return vipRecordMessageDao.get("from BasVipInfor where vipNumber ='"+vipNumber+"' AND enterprise_id2 = "+parentEnterpriseId);
        return vipRecordMessageDao.get("from BasVipInfor where vipNumber ='"+vipNumber+"'");
    }
    
    public BasVipInfor getBasVipInfor2(String car_License, String car_Vin, Integer parentEnterpriseId) throws Exception{
        //return vipRecordMessageDao.get("from BasVipInfor where carLicense ='"+car_License+"' and carVin ='"+car_Vin+"' AND enterprise_id2 = "+parentEnterpriseId);
        return vipRecordMessageDao.get("from BasVipInfor where carLicense ='"+car_License+"' and carVin ='"+car_Vin+"'");
    }
    
    public BasVipInfor getBasVipInfor3(String vipId) throws Exception
    {
        return vipRecordMessageDao.get("from BasVipInfor where vipId ='"+vipId+"'");
    }
    
    public List<?> getBasVipInfors(BasUsers user) throws Exception
    {
        //return vipRecordMessageDao.createSQLQuery("SELECT VIP_ID FROM bas_vip_infor WHERE enterprise_id2 = " + getParentEnterpriseId(user));
        return vipRecordMessageDao.createSQLQuery("SELECT VIP_ID FROM bas_vip_infor ");
    }
    
    @Log(moduleName="会员管理",opertype="会员档案",content="会员档案更新")
    public Message updateVipInfor(BasVipInfor vipInfo, BasUsers user) throws Exception{
        Message msg = new Message();
        if(vipInfo != null){
            vipRecordMessageDao.update(vipInfo);
            setContent("会员管理- 通过接口修改会员 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+vipInfo.getVipNumber());
            msg.setMsg("更新成功");
            msg.setSuccess(true);
        }else{
            msg.setMsg("会员档案不能为空");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    @Log(moduleName="会员管理",opertype="会员档案",content="会员档案更新")
    public Message updateVipInfor(String carLicense, String carVin,
            String stockType, Double vipIntegral, String workId, String slipId, String stockRemark, BasUsers user) throws Exception
    {
        Message msg = new Message();
        if(carLicense != null && !carVin.equals("")&& carVin != null && !carVin.equals("")){
            if(stockType != null && !stockType.equals("")){
                boolean tag = false;
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.INTEGRALTYPE.INTEGRALTYPE);
                if(childs != null && childs.size() > 0){
                    for(int i=0; i<childs.size(); i++){
                        BasChilddictionary c = childs.get(i);
                        if(c.getDataKey().equals(stockType)){
                            tag = true;
                            break;
                        }
                    }
                }
                if(tag){
                    BasVipInfor bvi = getBasVipInfor2(carLicense, carVin, getParentEnterpriseId(user));
                    if(bvi != null){
                        BasVipStock bvs = new BasVipStock();
                        bvi.setVipIntegral(bvi.getVipIntegral() + vipIntegral);
                        bvi.setVipTotalIntegral(bvi.getVipTotalIntegral() + vipIntegral);
                        bvs.setBasVipInfor(bvi);
                        bvs.setStockType(stockType);
                        bvs.setVipIntegral(vipIntegral);
                        bvs.setWorkId(workId);
                        bvs.setSlipId(slipId);
                        bvs.setStockRemark(stockRemark);
                        bvs.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                        bvs.setParentEnterpriseId(getParentEnterpriseId(user));
                        bvi.getBasVipStocks().add(bvs);
                        vipRecordMessageDao.saveOrUpdate(bvi);
                        setContent("会员管理- 通过接口修改会员积分 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+bvi.getVipNumber());
                        msg.setMsg("更新成功");
                        msg.setSuccess(true);
                    }else{
                        msg.setMsg("该会员不存在");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("积分赠送类型不存在");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("积分赠送类型不能为空");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("车辆牌照或者车架号不能为空");
            msg.setSuccess(false);
        }
        return msg;
    }

    @Log(moduleName="会员管理",opertype="会员档案",content="会员付款")
    public Message saveVipInforDefray(String carLicense, String carVin,
            String defrayType, Double defrayAmount, String workId,
            String slipId, String remark, BasUsers user) throws Exception
    {
        Message msg = new Message();
        if(carLicense != null && !carVin.equals("")&& carVin != null && !carVin.equals("")){
            if(defrayType != null && !defrayType.equals("")){
                boolean tag = false;
                List<BasChilddictionary> childs = baseService.getBasChilddictionary(Contstants.BATCHBALANCETYPE_TAG.BATCHBALANCETYPE_TAG);
                if(childs != null && childs.size() > 0){
                    for(int i=0; i<childs.size(); i++){
                        BasChilddictionary c = childs.get(i);
                        if(c.getDataKey().equals(defrayType)){
                            tag = true;
                            break;
                        }
                    }
                }
                if(tag){
                    BasVipInfor bvi = getBasVipInfor2(carLicense, carVin, getParentEnterpriseId(user));
                    if(bvi != null){
                        Double vipBalance = bvi.getVipBalance();
                        if(defrayAmount <= vipBalance){
                            BasVipInforDefray bvs = new BasVipInforDefray();
                            bvs.setBasVipInfor(bvi);
                            bvs.setDefrayAmount(defrayAmount);
                            bvs.setDefrayDate(new Date());
                            bvs.setDefrayPerson(user.getBasStuff().getStfId().toString());
                            bvs.setDefrayType(defrayType);
                            bvs.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                            bvs.setParentEnterpriseId(getParentEnterpriseId(user));
                            bvs.setRemark(remark);
                            bvs.setSlipId(slipId);
                            bvs.setWorkId(workId);
                            vipInforDefrayDao.saveOrUpdate(bvs);
                            setContent("会员管理- 会员付款 日期："+new Date()+" 操作人："+user.getBasStuff().getStfId()+" - "+user.getBasStuff().getStfName()+" 会员卡号："+bvi.getVipNumber());
                            msg.setMsg("更新成功");
                            msg.setSuccess(true);
                        }else{
                            msg.setMsg("对不起，您目前会员卡余额只有"+vipBalance+"元，不能支付");
                            msg.setSuccess(false);
                        }
                    }else{
                        msg.setMsg("该会员不存在");
                        msg.setSuccess(false);
                    }
                }else{
                    msg.setMsg("支付类型不存在");
                    msg.setSuccess(false);
                }
            }else{
                msg.setMsg("付款类型不能为空");
                msg.setSuccess(false);
            }
        }else{
            msg.setMsg("车辆牌照或者车架号不能为空");
            msg.setSuccess(false);
        }
        return msg;
    }
    
    private boolean getBasCarInfor(String carLicense, String carVin, BasUsers user) throws Exception
    {
        List<Object[]> list = vipRecordMessageDao.createSQLQuery("SELECT CAR_ID FROM frt_car WHERE CAR_LICENSE='"+carLicense+"' AND CAR_VIN='"+carVin+"' AND enterprise_id='"+user.getBasStuff().getEnterpriseInfo().getEnterpriseId()+"'");
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }
    
    public Integer getParentEnterpriseId(BasUsers user) throws Exception
    {
        Integer parentEnterpriseId = null;
        if(user != null){
            if(user.getBasStuff().getEnterpriseInfo().getParentEnterpriseId() == -1){
                parentEnterpriseId = user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
            }else{
                parentEnterpriseId = user.getBasStuff().getEnterpriseInfo().getParentEnterpriseId();
            }
        }
        return parentEnterpriseId;
    }
    
    private String getBloodBrotherEnterpriseId(BasUsers user) throws Exception
    {
        String enterpriseId = ""; 
        Integer parentEnterpriseId = user.getBasStuff().getEnterpriseInfo().getParentEnterpriseId();
        if(parentEnterpriseId != null){
             List<?> list = vipRecordMessageDao.createSQLQuery("SELECT a.enterprise_id FROM enterprise_info a WHERE a.parentEnterprise_id='"+parentEnterpriseId+"'");
             if(list != null && list.size() > 0){
                 for(Object obj : list){
                     String enterpriseId_ = obj.toString();
                     if(!enterpriseId.equals("")){
                         enterpriseId += "," + enterpriseId_;
                     }else{
                         enterpriseId += enterpriseId_;
                     }
                 }
             }
        }
        return enterpriseId;
    }
    
    private String getChildEnterpriseId(BasUsers user) throws Exception
    {
        String enterpriseId = "";
        Integer parentEnterpriseId = user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        if(parentEnterpriseId != null){
            List<?> list = vipRecordMessageDao.createSQLQuery("SELECT a.enterprise_id FROM enterprise_info a WHERE a.parentEnterprise_id='"+parentEnterpriseId+"'");
            if(list != null && list.size() > 0){
                for(Object obj : list){
                    String enterpriseId_ = obj.toString();
                    if(!enterpriseId.equals("")){
                        enterpriseId += "," + enterpriseId_;
                    }else{
                        enterpriseId += enterpriseId_;
                    }
                }
            }
       }
       return enterpriseId;
    }
}