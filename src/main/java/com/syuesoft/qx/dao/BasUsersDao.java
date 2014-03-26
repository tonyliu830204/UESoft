package com.syuesoft.qx.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.base.vo.BasUserVO;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.EnterpriseInfo;
import com.syuesoft.qx.vo.DeptVo;

/*
 * 系统 用户的Dao   
 */

public interface BasUsersDao extends BaseDaoI<BasUsers>
{

    // 拿到某个部门下的系统用户
    public List<DeptVo> findAllUsersByDept() throws Exception;

    public BasUsers findBasUsersByNameAndPassword(BasUserVO basUsers)
            throws Exception;

    public BasUsers getUser(String id) throws Exception;

    public void Update(BasUsers basUsers) throws Exception;

    public List<BasUsers> findAll() throws Exception;

    public void UpdatePassWord(BasUserVO entity) throws Exception;

    public BasUsers getUserName(BasUserVO basUsers) throws Exception;
    /**系统用户中是否存在集团管理员*/
    public boolean isExistsCombineAdmin()throws Exception;
}