package com.syuesoft.bas.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasDeptService;
import com.syuesoft.base.vo.BasDeptVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;
/**
 * 部门设置Action
 * @author LWJ
 * */
@ParentPackage(value = "basePackage")
@Action("basDeptAction")
public class BasDeptAction extends BaseAction implements ModelDriven<BasDeptVo>
{
    private static final long serialVersionUID = 9114523477519758538L;
    Logger logger=Logger.getLogger(this.getClass());
    private BasDeptVo basDeptVo = new BasDeptVo();
    @Autowired
    private BasDeptService basDeptService;
    /**
     * 部门设置新增
     * */ 
    public void doAdd(){
        Message msg = new Message();
        try{
        	basDeptVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            basDeptService.add(basDeptVo);
        	msg.setSuccess(true);
        	msg.setMsg("新增部门成功！");
        }catch(Exception e){
        	logger.error("新增部门失败！", e);
        	msg.setSuccess(false);
        	msg.setMsg("新增部门失败！");
        }finally{
        	super.writeJson(msg);        	
        }
    }

    /**
     * 部门设置删除
     * */ 
    public void doDelete(){
        Message msg = new Message();
        try{
           basDeptService.delete(basDeptVo);
    	   msg.setSuccess(true);
    	   msg.setMsg("删除部门成功！");
        }catch(Exception e){
        	logger.error("删除部门失败！", e);
        	msg.setSuccess(false);
        	msg.setMsg("删除部门失败！");  	
        }finally{
        	super.writeJson(msg);        	
        }
    }

    /**
     * 部门设置修改
     * */ 
    public void doUpdate(){
        Message msg = new Message();
        try{
            boolean bool = basDeptService.update(basDeptVo);
            if (bool){
            	msg.setSuccess(true);
            	msg.setMsg("修改部门成功！");
            }else{
            	msg.setSuccess(false);
            	msg.setMsg("对不起，您输入的部门名称已存在，请重新输入！");
            }
        }catch(Exception e){
        	logger.error("修改部门失败！", e);
        	msg.setSuccess(false);
        	msg.setMsg("修改部门失败！");
        }finally{
        	super.writeJson(msg);        	
        }
    }

    /**
     * 部门设置查询
     * */
    public void doFindAll(){
    	try {
    			basDeptVo.setNowEnterpriseId(getNowEnterpriseId());
			super.writeJson(basDeptService.basDeptDatagrid(basDeptVo));
		} catch (Exception e) {
			logger.error("查询部门失败！", e);
		}
    }
    /**
     * 获取当前用户所属的公司
     * */
    public void findBasStuffOfEnterpriseInfo(){
    	Msg msg=new Msg();
    	try {
			BasDeptVo basDept=new BasDeptVo();
			basDept.setEnterpriseId(getNowEnterpriseId());
			basDept.setEnterpriseName(this.getSession().getAttribute(Contstants.ENTERPRISENAME).toString());
			msg.setSuccess(true);
			msg.setObj(basDept);
		} catch (Exception e) {
			logger.error("获取当前用户所属公司失败!", e);
			msg.setSuccess(false);
			msg.setMsg("获取当前用户所属公司失败!");
		}finally{
			super.writeJson(msg);
		}
    }
    
    public BasDeptVo getModel()
    {
        return basDeptVo;
    }

}
