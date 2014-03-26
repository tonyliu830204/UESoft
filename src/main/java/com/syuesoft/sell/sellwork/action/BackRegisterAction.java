package com.syuesoft.sell.sellwork.action;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.base.service.CustomInfoService;
import com.syuesoft.sell.sellwork.service.BackRegisterService;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value="basePackage")
@Action("backRegisterAction")
public class BackRegisterAction extends BaseAction implements ModelDriven<BackRegisterVo> {

	private Logger log = Logger.getLogger(this.getClass());
	private BackRegisterVo backRegisterVo = new BackRegisterVo();
	
	public BackRegisterVo getModel() {
		return backRegisterVo;
	}
	
	@Resource
	private BackRegisterService backRegisterService;
	
	@Resource
	private CustomInfoService customInfoService;
	
	/**
	 * 保存登记表明细
	 */
	public void saveDetailAll(){
		Message msg = new Message();
		try {
			backRegisterVo.setEnterpriseId(getUserEnterpriseId());	
			backRegisterService.saveDetailAll(backRegisterVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("登记表明细保存失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 保存登记表汇总信息
	 */
	public void saveRecord(){
		Message msg = new Message();
		try {
			BasUsers user = this.getUsers();
			backRegisterVo.setStf_Id(user.getBasStuff().getStfId().toString());
			if(user != null){
				//从session中获取当前企业Id
					backRegisterVo.setEnterpriseId(getUserEnterpriseId());	
				backRegisterService.saveRecord(backRegisterVo);	
				msg.setSuccess(true);
				msg.setMsg("登记表汇总信息保存成功！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("登记表汇总信息保存失败session已过期！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("登记表汇总信息保存失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
		
	}
	/**
	 * 查询登记表汇总信息
	 */
	public void findRecord(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());	
			Json json = backRegisterService.findRecord(backRegisterVo);
			super.writeJson(json);
		} catch (Exception e) {
			log.error("查询登记表汇总信息失败",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过汇总编号 查询明细信息 
	 */
	public void findRecordById(){
		try {
			Json json = backRegisterService.findRecordById(backRegisterVo);
			super.writeJson(json);
		} catch (Exception e) {
			log.error("明细查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 删除明细信息  
	 */
	public void deleteDetailInfo(){
		Message msg = new Message();
		try {
			backRegisterService.deleteDetailInfo(backRegisterVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("删除明细信息失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 删除汇总信息
	 */
	public void deleteRecord(){
		Message msg = new Message();
		try {
			backRegisterService.deleteRecord(backRegisterVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("删除汇总信息失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 保存修改后的汇总信息
	 */
	public void saveEditRecord(){
		Message msg = new Message();
		try {
			backRegisterService.saveEditRecord(backRegisterVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("修改汇总信息失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 保存修改后的明细信息
	 */
	public void saveEditDetailAll(){
		Message msg = new Message();
		try {
			backRegisterService.saveEditDetailAll(backRegisterVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("修改明细信息失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 获取员工名称 用于combox
	 */
	public void getBasStuff(){
		List list = new ArrayList();
		try {
			List<BasStuff> rlist = backRegisterService.getBasStuff();
			for (BasStuff basStuff : rlist) {
				ComboxVo vo = new ComboxVo();
				vo.setId(basStuff.getStfId()+"");
				vo.setName(basStuff.getStfName());
				list.add(vo);
			}
			super.writeJson(list);
		} catch (Exception e) {
			log.error("获取员工名称失败",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 放弃跟踪 
	 */
	public void doAbandonAttention(){
		try {
			backRegisterVo.setEnterpriseId(getUserEnterpriseId());	
			backRegisterService.doAbandonAttention(backRegisterVo);
		} catch (Exception e) {
			log.error("放弃跟踪 操作失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 转入跟踪系统
	 */
	public void doTurnin(){
		Message msg = new Message();
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			backRegisterService.doTurnin(backRegisterVo,customInfoService);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			log.error("转入跟踪系统失败",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 來店客流量登记 浏览
	 */
	public void findRecordLook(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.findRecordLook(backRegisterVo));
		} catch (Exception e) {
			log.error("來店客流量登记信息查询失败",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 客流时间段分析 生成动态列  
	 */
	public void doTimeAssay(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			List list = backRegisterService.doTimeAssay(backRegisterVo);
			super.writeJson(list);
		} catch (Exception e) {
			log.error("客流时间段分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流时间段分析 统计指定时间段内的客流量
	 */
	public void getTimeAssay(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getTimeAssay(backRegisterVo));
		} catch (Exception e) {
			log.error("客流时间段分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流业务员分析 生成动态列  
	 */
	public void doRegisterWorker(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.doRegisterWorker(backRegisterVo));
		} catch (Exception e) {
			log.error("客流业务员分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流业务员分析 统计指定时间段内的客流量
	 */
	public void getRegisterWorker(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterWorker(backRegisterVo));
		} catch (Exception e) {
			log.error("客流业务员分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车型分析 生成动态列  
	 */
	public void doRegisterCarmodel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			List list = backRegisterService.doRegisterCarmodel(backRegisterVo);
			super.writeJson(list);
		} catch (Exception e) {
			log.error("客流车型分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车型分析 统计指定时间段内的客流量
	 */
	public void getRegisterCarmodel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterCarmodel(backRegisterVo));
		} catch (Exception e) {
			log.error("客流车型分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 业务员 车型分析 生成动态列  
	 */
	public void doRegisterUserCarmodel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			List list = backRegisterService.doRegisterUserCarmodel(backRegisterVo);
			super.writeJson(list);
		} catch (Exception e) {
			log.error("业务员车型分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 业务员车型分析 统计指定时间段内的客流量
	 */
	public void getRegisterUserCarmodel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterUserCarmodel(backRegisterVo));
		} catch (Exception e) {
			log.error("业务员车型分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车级别分析 生成动态列  
	 */
	public void doRegisterCarLevel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			List list = backRegisterService.doRegisterCarLevel(backRegisterVo);
			super.writeJson(list);
		} catch (Exception e) {
			log.error("客流车级别分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车级别分析  统计指定级别内的客流量
	 */
	public void getRegisterCarLevel(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterCarLevel(backRegisterVo));
		} catch (Exception e) {
			log.error("客流车级别分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车级别分析 生成动态列  
	 */
	public void doRegisterCustomSource(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			List list = backRegisterService.doRegisterCustomSource(backRegisterVo);
			super.writeJson(list);
		} catch (Exception e) {
			log.error("客流车来源分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 客流车来源分析  统计指定来源内的客流量
	 */
	public void getRegisterCustomSource(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterCustomSource(backRegisterVo));
		} catch (Exception e) {
			log.error("客流车来源分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 接待员车品牌分析 生成动态列  
	 */
	public void doRegisterCarBrand(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(backRegisterService.doRegisterCarBrand(backRegisterVo));
		} catch (Exception e) {
			log.error("接待员车品牌分析统计动态列生成失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 接待员车品牌分析  统计指定级别内的客流量
	 */
	public void getRegisterCarBrand(){
		try {
				backRegisterVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(backRegisterService.getRegisterCarBrand(backRegisterVo));
		} catch (Exception e) {
			log.error("接待员车品牌分析统计查询失败",e);
			e.printStackTrace();
		}
	}
	
}
