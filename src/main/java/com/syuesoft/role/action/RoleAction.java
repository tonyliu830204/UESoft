package com.syuesoft.role.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.RoleVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.role.service.IRoleService;
import com.syuesoft.util.Msg;

/**
 * 角色管理
 * 
 * @author HeXin
 * 
 */
@ParentPackage(value = "basePackage")
@Action("roleAction")
public class RoleAction extends BaseAction implements ModelDriven<RoleVo>
{
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private RoleVo roleVo = new RoleVo();
    @Autowired
    private IRoleService roleService;

    public RoleVo getModel()
    {
        return roleVo;
    }

    /**
     * 角色列表
     */
    public void datagrid()
    {
        try
        {
        	roleVo.setEnterpriseId(getNowEnterpriseId().toString());
            this.writeJson(roleService.getDatagrid(roleVo, this.getUsers()));
        }
        catch(Exception e)
        {
            logger.error("加载角色异常", e);
        }
    }

    /**
     * 查询没有分配角色的用户
     */
    public void getnoselectedStuff()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
            	roleVo.setEnterpriseId(getNowEnterpriseId().toString());
                this.writeJson(roleService.findnoselectedBasStuff(roleVo,user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("加载异常", e);
        }
    }

    /**
     * 查询当前角色的用户
     */
    public void getselectedStuff()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
            	roleVo.setEnterpriseId(getNowEnterpriseId().toString());
                this.writeJson(roleService.findselectedBasStuff(roleVo, user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("加载异常", e);
        }
    }

    /**
     * 加载权限树
     */
    public void menuRoleTree()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
            	if(user.getLeavl()!=null)
            	if(user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
            		this.writeJson(roleService.menuRoleTree(roleVo, user));
            	}else{
            		roleVo.setEnterpriseId(getNowEnterpriseId().toString());
            		this.writeJson(roleService.dynamicMenuRoleTree(roleVo, user));            		
            	}
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("加载权限树异常", e);
        }
    }

    public void savaOrUpdata()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(roleService.saveAndUpdata(roleVo, user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("角色保存异常", e);
        }
    }

    /**
     * 删除
     */
    public void delete()
    {
        try
        {
            this.writeJson(roleService.delete(roleVo));
        }
        catch(Exception e)
        {
            logger.error("加载角色异常", e);
        }
    }

    /**
     * 加载用户拥有的菜单树
     */
    public void UserRoleMenuTree()
    {
        try
        {
            BasUsers user = this.getUsers();
            this.writeJson(roleService.userRoleMenuTree(roleVo, user));
        }
        catch(Exception e)
        {
            logger.error("加载权限树异常", e);
        }
    }

    /**
     * 查询用户角色
     */
    public void userRoleInfo()
    {
        try
        {
            this.writeJson(roleService.userRoleInfo(roleVo));
        }
        catch(Exception e)
        {
            logger.error("加载权限树异常", e);
        }
    }
    /**
     * 分布点权限管理菜单树
     * */
    public void distributePurviewMenuRoleTree(){
    	try {
			this.writeJson(roleService.distributePurviewMenuRoleTree(roleVo, this.getUsers()));
		} catch (Exception e) {
			logger.error("查找分布点权限管理菜单树失败！", e);
			e.printStackTrace();
		}
    }
    /**
     * 设置系统默认角色
     * */
    public void modifyDefaultRole(){
    	Msg msg=new Msg();
    	try {
    		roleVo.setEnterpriseId(getNowEnterpriseId().toString());
    		roleVo.setSystemLevel(this.getUsers().getLeavl());
    		boolean flag=roleService.modifyDefaultRole(roleVo);
    		if(flag){
    			msg.setSuccess(true);
    			msg.setMsg("设置系统默认角色成功！");
    		}else{
    			msg.setSuccess(false);
    			msg.setMsg("信息不完整，设置系统默认角色失败！");
    		}
		} catch (Exception e) {
			logger.error("设置系统默认角色失败!", e);
			msg.setSuccess(false);
			msg.setMsg("设置系统默认角色失败！");
		}finally{
			super.writeJson(msg);
		}
    }
    /**
     * 保存分布点权限信息
     * */
    public void saveDistributePurviewMenuRoleTree(){
    	Msg msg=new Msg();
    	try {
    		boolean flag=roleService.saveDistributePurviewMenuRoleTree(roleVo);
    		if(flag){
    			msg.setSuccess(true);
    			msg.setMsg("保存分布点权限信息成功！");
    		}else{
    			msg.setSuccess(false);
    			msg.setMsg("信息不完整，保存分布点权限信息失败！");
    		}
		} catch (Exception e) {
			logger.error("保存分布点权限信息失败!", e);
			msg.setSuccess(false);
			msg.setMsg("保存分布点权限信息失败！");
		}finally{
			super.writeJson(msg);
		}
    }
}