package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.vipmanagement.dao.SmsRecordManagementDao;
import com.syuesoft.vipmanagement.service.SmsRecordManagementService;
import com.syuesoft.vipmanagement.vo.SmsRecordManagementVo;

/**
 * 
* @ClassName: SmsRecordManagementServiceImpl 
* @Description: TODO(短信发送查询) 
* @author HeXin 
* @date 2013-12-25 上午11:15:45 
* @version 1.0
 */
@Service("smsRecordManagementService")
public class SmsRecordManagementServiceImpl implements
		SmsRecordManagementService {
	
	@Autowired
	private SmsRecordManagementDao smsRecordManagementDao;
	        
	
	public Json getSmsSended(
			SmsRecordManagementVo smsRecordManagementVo) throws Exception {
		
	    List<SmsRecordManagementVo> list = new ArrayList<SmsRecordManagementVo>();
        Json json = new Json();
        int total = 0;
        String sql = "select * from vip_smsRecord_Management A where 1 = 1 ";
        if(smsRecordManagementVo.getNow_Send_Date()!=null && !smsRecordManagementVo.getNow_Send_Date().equals("")){
            sql += " and A.now_Send_Date > '"+smsRecordManagementVo.getNow_Send_Date()+"'";
        }
        if(smsRecordManagementVo.getNow_Send_Date1()!=null && !smsRecordManagementVo.getNow_Send_Date1().equals("")){
            sql += " and A.now_Send_Date < '"+smsRecordManagementVo.getNow_Send_Date1()+"'";
        }
        if(smsRecordManagementVo.getCar_License()!=null && !smsRecordManagementVo.getCar_License().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(smsRecordManagementVo.getCar_License().trim())+"%'";
        }
        if(smsRecordManagementVo.getCar_Vin()!=null && !smsRecordManagementVo.getCar_Vin().equals("")){
            sql += " and A.Car_Vin like '%"+StringEscapeUtils.escapeSql(smsRecordManagementVo.getCar_Vin().trim())+"%'";
        }
        total = smsRecordManagementDao.getCountBySQL(sql);
        List<Object[]> rlist = smsRecordManagementDao.createSQLQuery(sql, smsRecordManagementVo.getPage(), smsRecordManagementVo.getRows());
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                SmsRecordManagementVo vo = new SmsRecordManagementVo();
                if(obj[0]!=null){vo.setInfo_detailId(obj[0].toString());}
                if(obj[1]!=null){vo.setSmsState(obj[1].toString());}
                if(obj[2]!=null){vo.setSend_Content(obj[2].toString());}
                if(obj[3]!=null){vo.setNow_Send_Date(FormatTime.timestamp2Str((Timestamp) obj[3]));}
                //obj[4]定时发送日期
                if(obj[5]!=null){vo.setCar_Id(obj[5].toString());}
                if(obj[6]!=null){vo.setCar_License(obj[6].toString());}
                if(obj[7]!=null){vo.setCar_Vin(obj[7].toString());}
                if(obj[8]!=null){vo.setCustom_Name(obj[8].toString());}
                if(obj[9]!=null){vo.setCustom_Tel1(obj[9].toString());}
                if(vo.getCustom_Name() == null || "".equals(vo.getCustom_Name())){
                    vo.setCustom_Name("短信发送测试用户");
                }
                if(vo.getCustom_Tel1() == null || "".equals(vo.getCustom_Tel1())){
                    if(obj[10]!=null){vo.setCustom_Tel1(obj[10].toString());}
                }
                if(obj[11]!=null){vo.setInfo_Id(obj[11].toString());}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
	
	@Log(moduleName="会员管理",content="删除短信发送记录",opertype="删除")
	public Msg doDelete(SmsRecordManagementVo smsRecordManagementVo)
			throws Exception {
	    Msg msg = new Msg();
        try {
            if(smsRecordManagementVo != null ){
                if(smsRecordManagementVo.getInfo_Id() != null && !"".equals(smsRecordManagementVo.getInfo_Id())){
                    smsRecordManagementDao.doDelete(smsRecordManagementVo);
                    msg.setSuccess(true);
                    msg.setMsg("删除成功");
                }
            }
        } catch (Exception e) {
            msg.setSuccess(false);
            msg.setMsg("删除失败");
        }
        return msg;
	}
}