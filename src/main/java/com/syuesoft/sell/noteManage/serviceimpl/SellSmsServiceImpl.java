package com.syuesoft.sell.noteManage.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsSellReceiveSms;
import com.syuesoft.sell.noteManage.dao.SellSmsDao;
import com.syuesoft.sell.noteManage.service.SellSmsService;
import com.syuesoft.sell.noteManage.vo.SellSmsVo;
import com.syuesoft.util.GetDateByString;
import com.syuesoft.util.Message;
import com.syuesoft.util.SMSSendUtil;

@Service("sellSmsService")
public class SellSmsServiceImpl implements SellSmsService {
	private SellSmsDao sellSmsDao;

	public SellSmsDao getSellSmsDao() {
		return sellSmsDao;
	}

	@Autowired
	public void setSellSmsDao(SellSmsDao sellSmsDao) {
		this.sellSmsDao = sellSmsDao;
	}

	
	public void smsSend(SellSmsVo smsSendVo) throws Exception {
		sellSmsDao.smsSend(smsSendVo);

	}

	
	public void saveSmsState(String id, Integer state) throws Exception {
		List<BaseBean> list=sellSmsDao.find("from XsSellReceiveSms where custom_id in("+id+")");
		if(list!=null){	
			for (BaseBean baseBean : list) {
				XsSellReceiveSms sms=(XsSellReceiveSms) baseBean;
				sms.setReceive_state(state);
	    		sellSmsDao.saveOrUpdate(sms);
    		
			
		}
		}
		
	}

	
	public Datagrid getSmsInfo(SellSmsVo smsSendVo) throws Exception {
		// TODO Auto-generated method stub
		return sellSmsDao.getSmsInfo(smsSendVo);
	}

	
	public Message saveSmsSend(SellSmsVo smsVo) throws Exception {
		Message msg = new Message();
		GetDateByString date = new GetDateByString();
        String phonecode ="";
		String d_date = "";//定时
		String sms_date = "";
        String noteContent = "";//短信内容
        String SmsId="";
        try {
        	noteContent = new String(smsVo.getSms().getBytes("ISO-8859-1"),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			 msg.setSuccess(true);
	            msg.setMsg("短信内容字符编码转换失败！");
	      return msg;
		}
		
		if(noteContent!=null&&!"".equals(noteContent)){
				//判断是否是定时发送
			if(smsVo.getD_date()!=null && !smsVo.getD_date().equals("")){
				d_date = smsVo.getD_date();
				sms_date = date.getNowTime();
			}else{
				sms_date = date.getNowTime();
			}
		}
		
		
        if(smsVo.getInserted() != null && !"".equals(smsVo.getInserted())){

        	String insert = smsVo.getInserted().substring(smsVo.getInserted().indexOf("["), smsVo.getInserted().lastIndexOf("]")+1);
        	//获取前台传来的短信内容，和客户电话号码
        	List<SellSmsVo> arylist = JSON.parseArray(insert,SellSmsVo.class);
        	try {
			//循环获取所有用户的电话号码
        		for (SellSmsVo sellSmsVo : arylist) {
					smsVo.setCustomId(sellSmsVo.getCustomId());
					smsVo.setSms(noteContent);
					smsVo.setD_date(d_date);
					smsVo.setSms_date(sms_date);
					smsVo.setPhonecode(sellSmsVo.getPhonecode());
					if(sellSmsVo.getCustomId()!=null&&!"".equals(sellSmsVo.getCustomId())){
						smsVo.setSms_type(0);//常规客户
					}else{
						smsVo.setSms_type(1);//来电客户
					}
					
					//调用service方法将记录存入数据库
					sellSmsDao.smsSend(smsVo);
					SmsId=sellSmsVo.getCustomId()+",";
					phonecode += ";"+sellSmsVo.getPhonecode();
				}
					phonecode = phonecode.substring(1);
					//将测试号码追加到号码组后面
					if(smsVo.getTest_Number()!=null && !smsVo.getTest_Number().equals("")){
						phonecode = phonecode +";"+smsVo.getTest_Number();
					}
					SMSSendUtil send = new SMSSendUtil();
					//调用发送接口
					
					int r = send.sendSms(phonecode, noteContent, d_date);
					
					if(SmsId!=null && !("".equals(SmsId))){
						SmsId=SmsId.substring(0,SmsId.length()-1);
					}
					//保存接收状态
					this.saveSmsState(SmsId,r);
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
					
				} catch (Exception e) {
					msg.setSuccess(false);
					e.printStackTrace();
				}
		      }else{
		    	  msg.setSuccess(false);
		          msg.setMsg("短信发送内容不能为空！");
		      }
		
		return msg;
	}

}
