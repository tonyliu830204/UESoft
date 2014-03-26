package com.syuesoft.qx.service;

import javax.servlet.http.HttpSession;
import com.syuesoft.base.vo.BasUserVO;
import com.syuesoft.util.Msg;

/*
 * 
 */
public interface BasUserService
{
    /**
     * 登录
     * 
     * @param basUsers
     * @return
     * @throws Exception
     */
    public Msg savefindByUser(BasUserVO basUsers, HttpSession session)
            throws Exception;

    /**
     * 更新员工密码
     * 
     * @param entity
     * @return
     */
    public Msg updatePassWord(BasUserVO entity, HttpSession session)
            throws Exception;

    /**
     * 注销 退出
     * 
     * @param session
     * @return
     * @throws Exception
     */
    public void removeUser(HttpSession session) throws Exception;
    /**
     * 切换企业
     * 
     * @param basUsers
     * @return Boolean
     * @throws Exception
     */
    public Boolean reStart(BasUserVO basUsers, HttpSession session) throws Exception;
}