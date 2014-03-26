package com.syuesoft.qx.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.base.vo.BasUserVO;
import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.listener.EventManager;
import com.syuesoft.listener.SessionListener;
import com.syuesoft.menu.service.IMenuService;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.LoginError;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.qx.dao.LoginErrorDao;
import com.syuesoft.qx.service.BasUserService;
import com.syuesoft.role.service.IRoleService;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.MD5;
import com.syuesoft.util.Msg;

/*
 *  用户ServiceImpl  
 */
@Service
@Transactional
public class BasUserServiceImpl implements BasUserService, Serializable
{

    private static final long serialVersionUID = -8966016470596380279L;
    @Autowired public BasUsersDao basUsersDao;
    @Autowired private IRoleService roleService;
    @Autowired private IMenuService menuService = null;
    @Autowired private LoginErrorDao loginErrorDao;
    @Autowired private EnterpriseInfoDAO enterpriseInfoDAO;
    @Autowired private BasCompanyInformationSetService cfs;

    public Msg savefindByUser(BasUserVO basUsers, HttpSession session)
            throws Exception
    {
        Msg msg = new Msg();
        String mssage = null;
        LoginError error = null;
        boolean lockState = false;
        String dayString = FormatTime.date2Str(new Date(), "yyyy-MM-dd");
        List<LoginError> list = getLoginError(basUsers.getUserName());
        if (list != null && list.size() > 0)
        {
            error = list.get(0);
            if (error != null){
                Long lock = error.getLock();
                String errorDateString = error.getDate();
                if (dayString.equals(errorDateString)&&lock == Long.parseLong(Contstants.CLOSELODIN.CLOSELODINYES))
                {
                    lockState = true;
                    mssage = "您的账号今天被封锁";
                    msg.setMsg(mssage);
                    msg.setSuccess(false);
                }
            }
        }
        if (!lockState)
        {
            basUsers.setUserPasswd(MD5.MD5Encode(basUsers.getUserPasswd()));
            BasUsers user = basUsersDao.findBasUsersByNameAndPassword(basUsers);
            if (user == null)
            {
                BasCompanyInformationSet passWordLimitNum = cfs.getBasCompanyInformationSet(Contstants.PARAMETER_SET.PASSWORDSE,Contstants.PASSWORDSE.PASSWORDLIMITNUM);
                if(passWordLimitNum == null){
                    msg.setMsg("系统参数数据不全");
                    msg.setSuccess(false);
                    return msg;
                }
                int number = !"".equals(passWordLimitNum.getCiValue()) ? Integer.parseInt(passWordLimitNum.getCiValue()) : 1;
                BasUsers loginuser = getUserName(basUsers);
                if (loginuser != null)
                {
                    if (number != 0)
                    {
                        if (error != null)
                        {
                            int errorNumber = error.getErrorNumber();
                            String errorDateString = error.getDate();
                            if (dayString.equals(errorDateString))
                            {
                                if (errorNumber < number - 1)
                                {
                                    mssage = "用户名或者密码错误,您还有"
                                            + (number - errorNumber - 1)
                                            + "次机会";
                                    error.setErrorNumber(errorNumber + 1);
                                }
                                else if (errorNumber == number - 1)
                                {
                                    mssage = "用户名或者密码错误,您的账号今天被封锁";
                                    error.setErrorNumber(errorNumber + 1);
                                    error
                                            .setLock(Long
                                                    .parseLong(Contstants.CLOSELODIN.CLOSELODINYES));
                                }
                            }
                            else
                            {
                                mssage = "用户名或者密码错误,您还有" + (number - 1) + "次机会";
                                error.setDate(FormatTime.date2Str(new Date(),
                                        "yyyy-MM-dd"));
                                error.setErrorNumber(1);
                                error.setLock(Long.parseLong(Contstants.CLOSELODIN.CLOSELODINNO));
                            }
                        }
                        else
                        {
                            mssage = "用户名或者密码错误,您还有" + (number - 1) + "次机会";
                            error = new LoginError();
                            error.setUserName(basUsers.getUserName());
                            error.setDate(FormatTime.date2Str(new Date(),
                                    "yyyy-MM-dd"));
                            error.setErrorNumber(1);
                            error.setLock(Long.parseLong(Contstants.CLOSELODIN.CLOSELODINNO));
                        }
                        saveOrUpdateLoginError(error);
                    }
                    else
                    {
                        mssage = "用户名错误";
                    }
                }
                else
                {
                    mssage = "登陆用户不存在";
                }
                msg.setMsg(mssage);
                msg.setSuccess(false);
            }
            else
            {
                if (user.getBasStuff().getStfYes().equals(Contstants.SYSTEMUSER.STFYES))
                {
                    HttpSession otherSession = SessionListener.isAlreadyEnter(user);
                    if (otherSession == null)
                    {
                        login(user, msg, session);
                    }
                    else
                    {
                        String eventName = "/qiangbi/xixian/";
                        String mesg = "'很抱歉！帐号为:【" + user.getUserName()+ "】已经在别处登录," + otherSession.getId() + "'";
                        EventManager enent = EventManager.getInstance();
                        enent.createEvent(eventName, mesg, 1);
                        login(user, msg, session);
                    }
                }
                else
                {
                    mssage = "【" + user.getUserName() + "】不是系统用户，禁止登录";
                    msg.setMsg(mssage);
                    msg.setSuccess(false);
                }
            }
        }
        return msg;
    }

    private void login(BasUsers user, Msg msg, HttpSession session)
            throws Exception
    {
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            getAdminUserRole(user, msg, session);
        }
        else
        {
            if (user.getBasStuff().getStfZxqk().equals(
                    Contstants.ZXQKF.LOGGEDOFF))
            {
                msg.setMsg("很抱歉！您是系统注销用户，请联系管理员");
                msg.setSuccess(false);
            }
            else if (user.getBasStuff().getStfZxqk().equals(
                    Contstants.ZXQKF.INSERVICE))
            {
                if (user.getBasStuff().getStfYes().equals(
                        Contstants.SYSTEMUSER.STFYES))
                {
                    getCommonUserRole(user, msg, session);
                }
                else
                {
                    msg.setMsg("很抱歉！您不是系统用户，请联系管理员升级为系统用户");
                    msg.setSuccess(false);
                }
            }
            else
            {
                msg.setMsg("很抱歉！登录信息与原注册信息不匹配,请联系管理员");
                msg.setSuccess(false);
            }
        }
    }

    /**
     * 查询超级管理员的权限
     * 
     * @param user
     * @param msg
     * @throws Exception
     */
    private void getAdminUserRole(BasUsers user, Msg msg, HttpSession session)
            throws Exception
    {
        session.setAttribute(Contstants.CUSTOMER, user);
        msg.setMsg("恭喜您，登录成功");
        msg.setSuccess(true);
        session.setAttribute(Contstants.CURRUSERAUTH, getAdminRoleMenu());//当前登录用户权限
        session.setAttribute(Contstants.ENTERPRISEID, user.getBasStuff().getEnterpriseInfo().getEnterpriseId());//当前系统所属公司序号
        session.setAttribute(Contstants.ENTERPRISENAME,user.getBasStuff().getEnterpriseInfo().getEnterpriseName());//当前系统所属公司名称
    }

    /**
     * 一般用户的权限
     * @param user
     * @param msg
     * @throws Exception
     */
    private void getCommonUserRole(BasUsers user, Msg msg, HttpSession session)
            throws Exception
    {
        session.setAttribute(Contstants.CUSTOMER, user);
        msg.setMsg("恭喜您，登录成功");
        msg.setSuccess(true);
        session.setAttribute(Contstants.ENTERPRISEID, user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
        session.setAttribute(Contstants.ENTERPRISENAME,user.getBasStuff().getEnterpriseInfo().getEnterpriseName());
        session.setAttribute(Contstants.CURRUSERAUTH, getRoleMenu(user
        		.getBasStuff().getStfId()));
    }

    /**
     * 检测系统中是否有这个帐号
     * 
     * @return
     * @throws Exception
     */
    private BasUsers getUserName(BasUserVO basUsers) throws Exception
    {
        return basUsersDao.getUserName(basUsers);
    }

    /*
     * (non-Javadoc) 获取唯一的用户
     * 
     * @see com.syuesoft.qx.service.BasUserService#getUser(java.io.Serializable)
     */
    private BasUsers getUser(String id) throws Exception
    {
        BasUsers users = basUsersDao.getUser(id);
        return users;
    }

    public Msg updatePassWord(BasUserVO entity, HttpSession session)
            throws Exception
    {

    	Msg msg = new Msg();
        BasUsers entitys = (BasUsers) session.getAttribute(Contstants.CUSTOMER);
        String password_ = entity.getNewuserPasswd();
        BasCompanyInformationSet passWordLength = cfs
                .getBasCompanyInformationSet(Contstants.PARAMETER_SET.PASSWORDSE,
                        Contstants.PASSWORDSE.PASSWORDLENGTH,entity.getEnterpriseId());
        BasCompanyInformationSet passWordLeval = cfs
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.PASSWORDSE,
                        Contstants.PASSWORDSE.PWCOMPLEXITY,entity.getEnterpriseId());
        String password = MD5.MD5Encode(entity.getUserPasswd());
        if (entitys.getUserPasswd().equals(password))
        {
        	if(passWordLength.getCiValue()==null||passWordLength.getCiValue().equals(""))
        		passWordLength.setCiValue(6+"");
            Integer num = Integer.parseInt(passWordLength.getCiValue());
            if (num <= password_.length())
            {
                if ((Contstants.PWCOMPLEXITY.MEDKEY).equals(passWordLeval
                        .getCiValue()))
                {
                    if (entity.getPassWordLeval().equals(
                            Contstants.PWCOMPLEXITY.LOWKEY))
                    {
                        msg.setMsg("新密码强度太低,请确认");
                        msg.setSuccess(false);
                    }
                    else
                    {
                        updatePassWord_(entity, entitys, msg, session);
                    }
                }
                else if ((Contstants.PWCOMPLEXITY.HIKEY).equals(passWordLeval
                        .getCiValue()))
                {
                    if (entity.getPassWordLeval().equals(
                            Contstants.PWCOMPLEXITY.LOWKEY))
                    {
                        msg.setMsg("新密码强度太低,请确认");
                        msg.setSuccess(false);
                    }
                    else if (entity.getPassWordLeval().equals(
                            Contstants.PWCOMPLEXITY.MEDKEY))
                    {
                        msg.setMsg("新密码强度太低,请确认");
                        msg.setSuccess(false);
                    }
                    else
                    {
                        updatePassWord_(entity, entitys, msg, session);
                    }
                }
                else
                {
                    updatePassWord_(entity, entitys, msg, session);
                }
            }
            else
            {
                msg.setMsg("新密码不得小于" + num + "位,请确认");
                msg.setSuccess(false);
            }
        }
        else
        {
            msg.setMsg("原密码错误,请确认");
            msg.setSuccess(false);
        }
        return msg;
    }

    private void updatePassWord_(BasUserVO entity, BasUsers entitys, Msg msg,
            HttpSession session) throws Exception
    {
        if (entity.getCheckuserPasswd().equals(entity.getNewuserPasswd()))
        {
            if (entity.getUserId() != null)
            {
                entity.setUserPasswd(MD5.MD5Encode(entity.getNewuserPasswd()));
                basUsersDao.UpdatePassWord(entity);
                entitys = getUser(entity.getUserId().toString());
                session.setAttribute(Contstants.CUSTOMER, entitys);
                msg.setMsg("修改成功");
                msg.setSuccess(true);
            }
            else
            {
                msg.setMsg("请重新登录,请确认");
                msg.setSuccess(false);
            }
        }
        else
        {
            msg.setMsg("新密码与确认密码不匹配,请确认");
            msg.setSuccess(false);
        }
    }

    /**
     * 通过员工编号查询员工权限菜单
     * 
     * @param stfId
     */
    private Set<String> getRoleMenu(Long stfId) throws Exception
    {
//        Set<String> set = new HashSet<String>();
//        Set<MenuVo> auth = roleService.getMenuByRole(stfId);
//        if (null != auth)
//        {
//            for (MenuVo menu : auth)
//            {
//                if (menu.getMenuPid() != null)
//                {
//                    getpmenu(set, menu.getMenuPid(),auth);
//                }
//                set.add(menu.getMenuCode());
//            }
//        }
//        return set;
    	return roleService.getMenuCodeByRole(stfId);
    }

    private void getpmenu(Set<String> set, Long menuid,Set<MenuVo> auth) throws Exception
    {
    		for (MenuVo menuVo : auth) {
				if(menuVo.getMenuId()==menuid){
					if(!set.contains(menuVo.getMenuCode()))
						set.add(menuVo.getMenuCode());
					if(menuVo.getMenuPid()!=null)
						getpmenu(set, menuVo.getMenuPid(),auth);
				}
			}
//        BasMenuInfo pmenu = menuService.getBasMenuInfo(menuid);
//        if (pmenu != null && !set.contains(pmenu.getMenuCode()))
//        {
//            set.add(pmenu.getMenuCode());
//        }
//        if (pmenu.getMenuPid() != null)
//        {
//            getpmenu(set, pmenu.getMenuPid());
//        }
    }

    /**
     * 超级管理员的权限菜单
     * 
     * @param stfId
     */
    private Set<String> getAdminRoleMenu() throws Exception
    {
        Set<String> set = new HashSet<String>();
        List<BasMenuInfo> list = menuService.getMenu();
        if (list != null && list.size() > 0)
        {
            for (BasMenuInfo menu : list)
            {
                set.add(menu.getMenuCode());
            }
        }
        return set;
    }

    public void removeUser(HttpSession session) throws Exception
    {
        Enumeration<String> names = session.getAttributeNames();
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            session.removeAttribute(name);
        }
        session.invalidate();
    }

    /**
     * 查询用户登录异常信息
     * @param UserName
     * @return
     * @throws Exception
     */
    private List<LoginError> getLoginError(String UserName) throws Exception
    {
        String hql = "from LoginError where 1=1 and userName ='" + UserName+ "'";
        return loginErrorDao.find(hql);
    }

    /**
     * 如果登录异常保存异常信息
     * 
     * @param error
     * @throws Exception
     */
    private void saveOrUpdateLoginError(LoginError error) throws Exception
    {
        loginErrorDao.saveOrUpdate(error);
    }
    /**
     * 切换企业
     * @param basUers
     * @return boolean
     * @throws Exception
     * */
	
	public Boolean reStart(BasUserVO basUsers, HttpSession session) throws Exception {
		if(basUsers.getStfId()!=null&&basUsers.getEnterpriseId()!=0){
			if(basUsers.getStoreTag()!=null&&basUsers.getStoreTag()==true){
				session.setAttribute(Contstants.CUSTOMER, session.getAttribute(Contstants.CUSTOMER));
		        session.setAttribute(Contstants.ENTERPRISEID, basUsers.getEnterpriseId());
		        session.setAttribute(Contstants.ENTERPRISENAME,enterpriseInfoDAO.getEnterpriseInfo(basUsers.getEnterpriseId()).getEnterpriseName());
		        session.setAttribute(Contstants.CURRUSERAUTH, getRoleMenu(basUsers.getStfId()));
		        return true;
			}
			BasUsers bu=basUsersDao.getUser(basUsers.getUserId().toString());
			session.setAttribute(Contstants.CUSTOMER, bu);
			session.setAttribute(Contstants.CURRUSERAUTH, getDistributeMenuCode(basUsers.getStfId().toString(),basUsers.getEnterpriseId().toString()));
			session.setAttribute(Contstants.ENTERPRISEID, basUsers.getEnterpriseId());
			session.setAttribute(Contstants.ENTERPRISENAME, enterpriseInfoDAO.getEnterpriseInfo(basUsers.getEnterpriseId()).getEnterpriseName());
			return true;
		}
		return null;
	}
	private Set<String> getDistributeMenuCode(String stfId,String enterpriseId) throws Exception{
		String sql="SELECT bmi.menu_code FROM bas_menu_stuff bms INNER JOIN bas_menu_info bmi ON bms.menu_id=bmi.menu_id"
				+" WHERE bms.stf_id="+stfId+" AND bms.enterprise_id="+enterpriseId;
		List list=basUsersDao.createSQLQuery(sql);
		Set<String> set=new HashSet();
		if(list!=null&&list.size()>0)
			for (Object obj : list) {
				if(obj!=null)
					set.add(obj.toString());
			}
		return set;
	}
	 /**
     * 查找指定角色的菜单
     * 
     * @param stfId
     */
    private Set<String> getMenuCodeByRoleId(Long roleId) throws Exception
    {
        Set<String> set = new HashSet<String>();
        Set<MenuVo> auth = roleService.getMenuCodeByRoleId(roleId);
        if (null != auth){
            for (MenuVo menu : auth){
                if (menu.getMenuPid() != null){
                    getpmenu(set, menu.getMenuPid(),auth);
                }
                set.add(menu.getMenuCode());
            }
        }
        return set;
    }
}