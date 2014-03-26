package com.syuesoft.vipmanagement.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.model.BasVipSendinfomation;
import com.syuesoft.model.BasVipSendinfomations;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.SMSSendUtil;
import com.syuesoft.vipmanagement.dao.InfomationSendManageDao;
import com.syuesoft.model.BasUsers;
import com.syuesoft.vipmanagement.service.InfomationSendManageService;
import com.syuesoft.vipmanagement.vo.InfomationSendManageVo;
/**
 * 
* @ClassName: InfomationSendManageServiceImpl 
* @Description: TODO(发送短信) 
* @author HeXin 
* @date 2013-12-25 上午11:08:31 
* @version 1.0
 */
@Service("infomationSendManageService")
public class InfomationSendManageServiceImpl implements
		InfomationSendManageService {
	@Autowired
	private InfomationSendManageDao infomationSendManageDao;
	@Autowired
	private FrtCarDao frtCarDao;

	public Json doFind(InfomationSendManageVo infomationSendManageVo) throws Exception {
	    Json json = new Json();
	    List<InfomationSendManageVo> list = new ArrayList<InfomationSendManageVo>();
	    int total =0;
	    String sql = "select * from infomation_send_manage A WHERE 1 = 1 ";
        if(infomationSendManageVo.getCar_Annual_Date()!=null && !infomationSendManageVo.getCar_Annual_Date().equals("")){
              sql += " and A.Car_Annual_Date >= '"+infomationSendManageVo.getCar_Annual_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Annual_Date1()!=null && !infomationSendManageVo.getCar_Annual_Date1().equals("")){
            sql += " and A.Car_Annual_Date <= '"+infomationSendManageVo.getCar_Annual_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Basinsurance_Date()!=null && !infomationSendManageVo.getCar_Basinsurance_Date().equals("")){
            sql += " and A.Car_Basinsurance_Date >= '"+infomationSendManageVo.getCar_Basinsurance_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Basinsurance_Date1()!=null && !infomationSendManageVo.getCar_Basinsurance_Date1().equals("")){
            sql += " and A.Car_Basinsurance_Date <= '"+infomationSendManageVo.getCar_Basinsurance_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Businsurance_Date()!=null && !infomationSendManageVo.getCar_Businsurance_Date().equals("")){
            sql += " and A.Car_Businsurance_Date >= '"+infomationSendManageVo.getCar_Businsurance_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Businsurance_Date1()!=null && !infomationSendManageVo.getCar_Businsurance_Date1().equals("")){
            sql += " and A.Car_Businsurance_Date <= '"+infomationSendManageVo.getCar_Businsurance_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Buy_Date()!=null && !infomationSendManageVo.getCar_Buy_Date().equals("")){
            sql += " and A.Car_Buy_Date >= '"+infomationSendManageVo.getCar_Buy_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Buy_Date1()!=null && !infomationSendManageVo.getCar_Buy_Date1().equals("")){
            sql += " and A.Car_Buy_Date <= '"+infomationSendManageVo.getCar_Buy_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Exanined_Date()!=null && !infomationSendManageVo.getCar_Exanined_Date().equals("")){
            sql += " and a.Car_Examined_Date >= '"+infomationSendManageVo.getCar_Exanined_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Exanined_Date1()!=null && !infomationSendManageVo.getCar_Exanined_Date1().equals("")){
            sql += " and a.Car_Examined_Date <= '"+infomationSendManageVo.getCar_Exanined_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Last_Maint_Date()!=null && !infomationSendManageVo.getCar_Last_Maint_Date().equals("")){
            sql += " and a.Car_Last_Maint_Date >= '"+infomationSendManageVo.getCar_Last_Maint_Date()+"'";
        }
        if(infomationSendManageVo.getCar_Last_Maint_Date1()!=null && !infomationSendManageVo.getCar_Last_Maint_Date1().equals("")){
            sql += " and a.Car_Last_Maint_Date <= '"+infomationSendManageVo.getCar_Last_Maint_Date1()+"'";
        }
        if(infomationSendManageVo.getCar_Last_Repair_Distance()!=null && !infomationSendManageVo.getCar_Last_Repair_Distance().equals("")){
            String[] date = infomationSendManageVo.getCar_Last_Repair_Distance().split(",");
            sql += " and a.Car_Last_Repair_Distance between "+date[0]+" and "+date[1]+" ";
        }
        if(infomationSendManageVo.getCar_License()!=null && !infomationSendManageVo.getCar_License().equals("")){
            sql += " and a.Car_License like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCar_License().trim())+"%'";
        }
        if(infomationSendManageVo.getCar_Vin()!=null && !infomationSendManageVo.getCar_Vin().equals("")){
            sql += " and a.Car_Vin like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCar_Vin().trim())+"%'";
        }
        if(infomationSendManageVo.getCbrd_Name()!=null && !infomationSendManageVo.getCbrd_Name().equals("")){
            sql += " and a.Cbrd_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCbrd_Name().trim())+"%'";
        }
        if(infomationSendManageVo.getCtype_Name()!=null && !infomationSendManageVo.getCtype_Name().equals("")){
            sql += " and a.Ctype_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCtype_Name().trim())+"%'";
        }
        if(infomationSendManageVo.getCustom_Name()!=null && !infomationSendManageVo.getCustom_Name().equals("")){
            sql += " and a.Custom_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCustom_Name().trim())+"%'";
        }
        if(infomationSendManageVo.getCustom_Tel1()!=null && !infomationSendManageVo.getCustom_Tel1().equals("")){
            sql += " and a.Custom_Tel1 like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getCustom_Tel1().trim())+"%'";
        }
        if(infomationSendManageVo.getVip_Group_Name()!=null && !infomationSendManageVo.getVip_Group_Name().equals("")){
            sql += " and a.Vip_Group_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getVip_Group_Name().trim())+"%'";
        }
        if(infomationSendManageVo.getVip_Level_Name()!=null && !infomationSendManageVo.getVip_Level_Name().equals("")){
            sql += " and a.Vip_Level_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getVip_Level_Name().trim())+"%'";
        }
        if(infomationSendManageVo.getParea_Name()!=null && !infomationSendManageVo.getParea_Name().equals("")){
            sql += " and a.Parea_Name like '%"+StringEscapeUtils.escapeSql(infomationSendManageVo.getParea_Name().trim())+"%'";
        }
        total = infomationSendManageDao.getCountBySQL(sql);
	    List<Object[]> rlist = infomationSendManageDao.createSQLQuery(sql, infomationSendManageVo.getPage(), infomationSendManageVo.getRows());
	    Object[] obj = null;
        for (int i = 0; i < rlist.size(); i++) {
            obj = (Object[])rlist.get(i);
            InfomationSendManageVo vo = new InfomationSendManageVo();
            if(obj[0]!=null){vo.setCar_License(obj[0].toString());}
            if(obj[1]!=null){vo.setCar_Vin(obj[1].toString());}
            if(obj[2]!=null){vo.setCar_Buy_Date(FormatTime.timestamp2Str((Timestamp) obj[2]));}
            if(obj[3]!=null){vo.setCbrd_Name(obj[3].toString());}
            if(obj[4]!=null){vo.setCtype_Name(obj[4].toString());}
            if(obj[5]!=null){vo.setCustom_Name(obj[5].toString());}
            if(obj[6]!=null){vo.setParea_Name(obj[6].toString());}
            if(obj[7]!=null){vo.setCustom_Tel1(obj[7].toString());}
            if(obj[8]!=null){vo.setCar_Last_Maint_Date(FormatTime.timestamp2Str((Timestamp) obj[8]));}
            if(obj[9]!=null){vo.setCar_Annual_Date(FormatTime.timestamp2Str((Timestamp) obj[9]));}
            if(obj[10]!=null){vo.setCar_Exanined_Date(FormatTime.timestamp2Str((Timestamp) obj[10]));}
            if(obj[11]!=null){vo.setCar_Businsurance_Date(FormatTime.timestamp2Str((Timestamp) obj[11]));}
            if(obj[12]!=null){vo.setCar_Basinsurance_Date(FormatTime.timestamp2Str((Timestamp) obj[12]));}
            if(obj[13]!=null){vo.setCar_Last_Repair_Distance(obj[13].toString());}
            if(obj[14]!=null){vo.setCustom_Id(obj[14].toString());}
            if(obj[15]!=null){vo.setCar_Id(obj[15].toString());}
            list.add(vo);
        }
        json.setRows(list);
        json.setTotal(total);
		return json;
	}
	
	@Log(moduleName="会员管理",content="发送短信",opertype="发送短信")
	public Message saveSmsSend(InfomationSendManageVo infomationSendManageVo, BasUsers user) throws Exception {
	    Message msg = new Message();
        String customTel1 ="";
        String customTel2 ="";
        String carId = "";
        Date otherSendDate = null;
        Date nowSendDate = null;
        String sendContent = "";
        BasVipSendinfomation isf = new BasVipSendinfomation();
        try {
            sendContent = new String(infomationSendManageVo.getSend_Content().getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            msg.setSuccess(true);
            msg.setMsg("短信内容字符编码转换失败！");
            return msg;
        }
        if(sendContent != null && !"".equals(sendContent)){
            //判断是否是定时发送
            nowSendDate = new Date();
            if(infomationSendManageVo.getOther_Send_Date()!=null && !infomationSendManageVo.getOther_Send_Date().equals("")){
                otherSendDate = FormatTime.str2Date(infomationSendManageVo.getOther_Send_Date(), FormatTime.DEFAULT_FORMAT);
                if(FormatDate.dateDiffMinute(otherSendDate, nowSendDate) < 1){
                    msg.setSuccess(false);
                    msg.setMsg("短信定时发送日期不能小于当前日期！");
                    return msg;
                }
            }
            if(infomationSendManageVo.getInserted() != null && !"".equals(infomationSendManageVo.getInserted())){
                //获取前台传来的短信内容，和客户电话号码
                JSONObject jsData = JSON.parseObject(infomationSendManageVo.getInserted());
                List<InfomationSendManageVo> arylist = JSON.parseArray(jsData.get("rows").toString(),InfomationSendManageVo.class);
                //循环获取所有用户的电话号码
                for (InfomationSendManageVo vo : arylist) {
                    if(vo.getCar_Id() != null && !"".equals(vo.getCar_Id())){
                        if(!"".equals(carId))
                            carId += ";"+vo.getCar_Id();
                        else
                            carId = vo.getCar_Id();
                    }
                    if(vo.getCustom_Tel1() != null && !"".equals(vo.getCustom_Tel1())){
                        if(!"".equals(customTel1))
                            customTel1 += ";"+vo.getCustom_Tel1();
                        else
                            customTel1 = vo.getCustom_Tel1();
                    }
                }
            }else{
                msg.setSuccess(true);
                msg.setMsg("短信发送成功！");
            }
            //将测试号码追加到号码组后面
            if(infomationSendManageVo.getTest_Number()!=null && !infomationSendManageVo.getTest_Number().equals("")){
                customTel2 = infomationSendManageVo.getTest_Number();
            }
            if(customTel1 != null && !"".equals(customTel1)){
                String[] carIds = carId.split(";");
                String[] customTels = customTel1.split(";");
                if(carIds.length == customTels.length){
                    SMSSendUtil send = new SMSSendUtil();
                    //判断号码不为空
                    //调用发送接口
                    Date temp = otherSendDate != null && !"".equals(otherSendDate) ? otherSendDate : nowSendDate;
                    int r = send.sendSms(customTel1+";"+customTel2, sendContent, FormatTime.date2Str(temp, FormatTime.DEFAULT_FORMAT));
                    if(r==0){
                        msg.setSuccess(true);
                        msg.setMsg("短信发送成功！");
                    }else if(r==1){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，用户名或密码错误！");
                    }else if(r==2){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，余额不足！");
                    }else if(r==3){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，超过发送量1000条！");
                    }else if(r==4){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，此用户不允许发送！");
                    }else if(r==5){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，手机号或发送信息不能为空！");
                    }else if(r==7){
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，超过限制字数，请修改后发送！");
                    }else{
                        msg.setSuccess(false);
                        msg.setMsg("短信发送失败，网络连接异常！");
                    }
                    if(carIds != null && carIds.length > 0){
                        isf.setInfoId(CreateID.createId("BasVipSendinfomation", "SF"));
                        isf.setNowSendDate(nowSendDate);
                        isf.setOtherSendDate(otherSendDate);
                        isf.setSendContent(sendContent);
                        isf.setSendNumber(null);
                        isf.setEnterpriseId(user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
                        for(int i=0; i<carIds.length; i++){
                            if(carIds[i] != null && !"".equals(carIds[i]) ){
                                BasVipSendinfomations vsf = new BasVipSendinfomations();
                                vsf.setBasVipSendinfomation(isf);
                                List<FrtCar> frtCars = frtCarDao.find("from FrtCar where carId='"+carIds[i]+"'");
                                if(frtCars != null && frtCars.size() > 0){
                                    vsf.setFrtCar(frtCars.get(0));
                                }else{
                                    msg.setSuccess(false);
                                    msg.setMsg("车辆不存在！");
                                    return msg;
                                }
                                vsf.setSmsState(String.valueOf(r));
                                vsf.setSmsMobile(customTels[i]);
                                isf.getBasVipSendinfomations().add(vsf);
                            }
                        }
                    }
                    customTels = null;
                    customTels = customTel2.split(";");
                    if(customTels != null && customTels.length > 0){
                        for(int i=0; i<customTels.length; i++){
                            if(!"".equals(customTels[i])){
                                BasVipSendinfomations vsf = new BasVipSendinfomations();
                                vsf.setBasVipSendinfomation(isf);
                                vsf.setSmsState(String.valueOf(r));
                                vsf.setSmsMobile(customTels[i]);
                                isf.getBasVipSendinfomations().add(vsf);
                            }
                        }
                    }
                    infomationSendManageDao.saveOrUpdate(isf);
                    msg.setSuccess(true);
                    msg.setMsg("短信发送成功");
                }else{
                    msg.setSuccess(false);
                    msg.setMsg("短信接收号码不能为空");
                }
            }else{
                msg.setSuccess(false);
                msg.setMsg("短信发送号码不能为空！");
            }
        }else{
            msg.setSuccess(false);
            msg.setMsg("短信发送内容不能为空！");
        }
        return msg;
	}
}