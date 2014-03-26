package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.EnterpriseInfoService;
import com.syuesoft.base.vo.EnterpriseInfoVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
/**
 * 企业信息管理Action
 * */
@ParentPackage(value="basePackage")
@Action("enterpriseInfoAction")
public class EnterpriseInfoAction extends BaseAction implements ModelDriven<EnterpriseInfoVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private EnterpriseInfoVo enterpriseInfoVo=new EnterpriseInfoVo();
	private EnterpriseInfoService enterpriseInfoService;
	/**
	 * 导入企业信息
	 * */
	public void modifyImportEnterprise(){
    	try {
    		enterpriseInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(enterpriseInfoService.modifyImportEnterpriseInfo(enterpriseInfoVo, this.getUsers()));
		} catch (Exception e) {
			logger.error("导入企业信息失败", e);
		}
	}
	/**
	* @Title: getPager 
	* @Description: TODO(查询企业信息) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getPager(){
		try {
			enterpriseInfoVo.setEnterpriseId(Integer.parseInt(getNowEnterpriseId().toString()));
			super.writeJson(enterpriseInfoService.getPager(enterpriseInfoVo));
		} catch (Exception e) {
			logger.error("查询企业信息失败", e);
		}
	}
	/**
    * @Title: getPager 
    * @Description: TODO(查询企业信息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void getEnterpriseWork(){
        try {
            super.writeJson(enterpriseInfoService.getEnterpriseWork(enterpriseInfoVo));
        } catch (Exception e) {
            logger.error("查询企业信息失败", e);
        }
    }
	/**
	* @Title: saveEnterprise 
	* @Description: TODO(保存企业信息) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveEnterprise(){     
		Msg msg = new Msg();
		try {
			enterpriseInfoService.addEnterprise(enterpriseInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存企业信息失败", e);
		}
		super.writeJson(msg);
	}
	
	/**
    * @Title: saveEnterprise 
    * @Description: TODO(保存企业业务信息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveEnterpriseWork(){     
        Msg msg = new Msg();
        try {
            enterpriseInfoService.addEnterpriseWork(enterpriseInfoVo);
            msg.setSuccess(true);
            msg.setMsg("success");
        } catch (Exception e) {
            logger.error("保存企业信息失败", e);
        }
        super.writeJson(msg);
    }
	    
	/**
	* @Title: deleteEnterprise 
	* @Description: TODO(删除企业信息) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteEnterprise(){
		Msg msg = new Msg();
		try {
			boolean flag=enterpriseInfoService.deleteEnterprise(enterpriseInfoVo);
			if(flag){
				msg.setSuccess(true);
				msg.setMsg("删除企业信息成功！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("该企业下有用户，不能删除！");
			}
		} catch (Exception e) {
			logger.error("删除企业信息失败！", e);
			msg.setMsg("删除企业信息失败！");
		}finally{
			super.writeJson(msg);			
		}
	}
	
	/**
    * @Title: deleteEnterprise 
    * @Description: TODO(删除企业信息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteEnterpriseWork(){
        Msg msg = new Msg();
        try {
            enterpriseInfoService.deleteEnterpriseWork(enterpriseInfoVo);
            msg.setSuccess(true);
            msg.setMsg("success");
        } catch (Exception e) {
            logger.error("删除企业信息失败", e);
        }
        super.writeJson(msg);
    }
	    
	/**
	* @Title: updateEnterprise 
	* @Description: TODO(更新企业信息) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateEnterprise(){
		Msg msg = new Msg();
		try {
			enterpriseInfoService.updateEnterprise(enterpriseInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新企业信息失败", e);
		}
		super.writeJson(msg);
	}
	
	/**
    * @Title: updateEnterprise 
    * @Description: TODO(更新企业信息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void updateEnterpriseWork(){
        Msg msg = new Msg();
        try {
            enterpriseInfoService.updateEnterpriseWork(enterpriseInfoVo);
            msg.setSuccess(true);
            msg.setMsg("success");
        } catch (Exception e) {
            logger.error("更新企业信息失败", e);
        }
        super.writeJson(msg);
    }
	    
    /**
    * @Title: findPEnterprise 
    * @Description: TODO(查询企业信息) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
	public void  findPEnterprise(){
        try {
        	enterpriseInfoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(enterpriseInfoService.findPEnterprise(enterpriseInfoVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("查询企业信息失败!", e);
        }
    }
	/**
	  * @Title: enterpriseIsTwoLevel 
      * @Description: TODO(校验企业结构) 
      * @param enterpriseId     
      * @return boolean     
      * @throws  Exception
	  * */
	public void enterpriseIsTwoLevel(){
		Msg msg=new Msg();
		try {
			boolean flag=enterpriseInfoService.enterpriseIsTwoLevel();
			if(flag==true){
				msg.setSuccess(true);
				msg.setMsg("子级目录不允许再有子级！");
			}else{
				msg.setSuccess(false);
				msg.setObj("true");
				msg.setMsg("可以继续添加子级目录!");
			}
		} catch (Exception e) {
			logger.error("校验企业结构失败！", e);
			msg.setSuccess(false);
			msg.setMsg("校验企业结构失败！");
		}finally{
			super.writeJson(msg);
		}
	}
	public EnterpriseInfoService getEnterpriseInfoService() {
		return enterpriseInfoService;
	}
	@Autowired
	public void setEnterpriseInfoService(EnterpriseInfoService enterpriseInfoService) {
		this.enterpriseInfoService = enterpriseInfoService;
	}
	
	public EnterpriseInfoVo getModel() {
		return enterpriseInfoVo;
	}

}
