package com.syuesoft.sell.noteManage.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.noteManage.service.NoteManageService;
import com.syuesoft.sell.noteManage.vo.NoteManageVo;

@ParentPackage(value="basePackage")
@Action("noteManageAction")
public class NoteManageAction extends BaseAction implements ModelDriven<NoteManageVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private NoteManageService noteManageService;
	private NoteManageVo note=new NoteManageVo();
	
	
	public NoteManageVo getModel() {
		// TODO Auto-generated method stub
		return note;
	}
	/**
	 * 查询客户信息
	 */
	public void getSellBackInfo() {
		try {
			note.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(noteManageService.getCustomInfo(note));
		} catch (Exception e) {
			logger.debug("查询客户信息失败！");
		}
	}

}
