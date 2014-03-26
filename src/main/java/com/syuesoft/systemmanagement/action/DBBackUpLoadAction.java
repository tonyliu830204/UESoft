package com.syuesoft.systemmanagement.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.systemmanagement.service.DBBackUpLoadService;
import com.syuesoft.systemmanagement.vo.DataBackupVo;

@ParentPackage(value="basePackage")
@Action("dBBackUpLoadAction")
public class DBBackUpLoadAction extends BaseAction implements ModelDriven<DataBackupVo>{
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    private DataBackupVo dataBackupVo = new DataBackupVo();
	@Autowired
	private DBBackUpLoadService dBBackUpLoadService;
	
	/**
	 * 数据备份
	 */
    public void backUp(){
		try
        {
			dataBackupVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(dBBackUpLoadService.saveBackUp(dataBackupVo, this.getUsers()));
        }
        catch(Exception e)
        {
            logger.error("数据备份失败！", e);
        }
	}
    
	/**
	 * 还原
	 */
	public void restore(){
	    try
        {
            super.writeJson(dBBackUpLoadService.findBackUp(dataBackupVo, this.getUsers()));
        }
        catch(Exception e)
        {
            logger.error("数据备份失败！", e);
        }
	}
	
	/**
	 * 获取备份信息  备份时间  备份名称
	 */
	public void getBackupInfor(){
		try {
		    super.writeJson(dBBackUpLoadService.getBackupInfor(dataBackupVo, getUsers()));
		} catch (Exception e) {
		    logger.error("获取备份信息失败！", e);
		}
	}

	public DataBackupVo getModel() {
		return dataBackupVo;
	}
}