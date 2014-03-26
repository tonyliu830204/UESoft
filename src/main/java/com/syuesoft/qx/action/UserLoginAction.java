package com.syuesoft.qx.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.BasUserVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.qx.service.BasUserService;
import com.syuesoft.util.Msg;

/*
 *   Login(登陆)  Action
 *   @author wucuancuan
 */
public class UserLoginAction extends DefaultAction implements
        ModelDriven<BasUserVO>
{
    /**
     * 属性区域
     */
    private BasUserVO entity = new BasUserVO();

    private static final long serialVersionUID = 2975362535897981372L;

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private BasUserService basUserService;

    private String loginMessageN;

    /**
     * 用户登录 认证
     * 
     * @return
     */
    public void doLogin()
    {
        try{
            writeJson(basUserService.savefindByUser(entity, getSession()));
        }catch(Exception e){
            logger.error("登陆失败", e);
        }
    }

    /**
     * 当用户进入默认页面(index)时,判断用户是否登录,如果用户以登录,直接转向到首页,否则转向到登录页面
     * 
     * @return
     */
    public String main()
    {
        BasUsers entitys = (BasUsers) getSession().getAttribute(
                Contstants.CUSTOMER);
        if (entitys == null)
        {
            return LOGIN; // 转向登录页面
        }
        return SUCCESS; // 转向用户页面
    }

    /**
     * 退出用户登录,清空session，转向到login.jsp页面
     * 
     * @return
     */
    public String dologout()
    {
        try
        {
            basUserService.removeUser(getSession());
        }
        catch(Exception e)
        {
            logger.error("退出失败", e);
        }
        return "relogin";
    }

    /**
     * 获取session用户员工级别
     */
    public void getSessionUser()
    {
        try
        {
            BasUsers entitys = (BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
            writeJson(entitys.getLeavl()+","+entitys.getSystemId());
        }
        catch(Exception e)
        {
            logger.error("查询失败", e);
        }
    }

    /**
     * main.jsp获取当前用户名称
     */
    public void getLoginUser()
    {
        BasUsers entitys = (BasUsers) getSession().getAttribute(
                Contstants.CUSTOMER);
        Msg msg = new Msg();
        if (entitys != null)
        {
            msg.setSuccess(true);
            msg.setMsg(entitys.getBasStuff().getStfName() + "*"
                    + entitys.getSystemId() + "*" + getSession().getId());
        }
        else
        {
            msg.setSuccess(false);
            msg.setMsg("session过期，请重新登录");
        }
        this.writeJson(msg);
    }

    /**
     * 修改密码
     */
    public void updatePassWord()
    {
        try
        {
        	entity.setEnterpriseId(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getEnterpriseInfo().getEnterpriseId());
            this.writeJson(basUserService.updatePassWord(entity, getSession()));
        }
        catch(Exception e)
        {
            logger.error("更新密码失败", e);
        }
    }
    /**
     * 切换企业
     * */
    public void reStart(){
    	Msg msg=new Msg();
    	try {
    		entity.setStfId(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId());
    		entity.setLeavl(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getLeavl());
    		entity.setUserId(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getUserId());
    		if(entity.getEnterpriseId()!=null)
    			if(entity.getEnterpriseId().toString().equals(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getEnterpriseInfo().getEnterpriseId().toString())){
    				entity.setStoreTag(true);
    			}
    		Boolean flag=basUserService.reStart(entity,getSession());
    		if(flag==null){
    			msg.setSuccess(false);
    			msg.setMsg("切换企业失败,请重新登录后再试！");
    		}else if(flag){
    			msg.setSuccess(true);
    			msg.setMsg("切换企业成功！");
    		}else{
    			msg.setSuccess(false);
    			msg.setMsg("对不起，缺少权限，无法访问！");
    		}
		} catch (Exception e) {
			logger.error("切换企业失败！", e);
			msg.setSuccess(false);
			msg.setMsg("切换企业失败,请重新登录后再试！");
		}finally{
			super.writeJson(msg);
		}
    }
    
    public BasUserVO getModel()
    {
        return entity;
    }

    public String getLoginMessageN()
    {
        return loginMessageN;
    }

    public void setLoginMessageN(String loginMessageN)
    {
        this.loginMessageN = loginMessageN;
    }
}