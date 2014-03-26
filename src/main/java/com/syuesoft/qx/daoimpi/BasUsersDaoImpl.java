package com.syuesoft.qx.daoimpi;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.base.vo.BasUserVO;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.qx.vo.DeptVo;

/**
 * 系统用户 DaoImpl (non-Javadoc) wucuancuan
 * 
 * @see com.syuesoft.qx.dao.BasUsersDao
 */
@Repository
public class BasUsersDaoImpl extends BaseDaoImpl<BasUsers> implements
        BasUsersDao, Serializable
{

    private static final long serialVersionUID = -3692375323986536075L;

    /**
     * 拿到归属于部门下的用户 (non-Javadoc)
     * 
     * @see com.syuesoft.qx.dao.BasUsersDao#findAllByDept(com.syuesoft.model.BasStuff)
     * */
    @SuppressWarnings("unchecked")
    public List<DeptVo> findAllUsersByDept() throws Exception
    {
        List<DeptVo> deptVos = this.getHibernateTemplate().find("select new com.syuesoft.qx.vo.DeptVo(d.deptId,d.deptName,u.userId,u.userName)  from BasDept as d ,BasStuff  as s, BasUsers as u where d=s.basDept and s=u.basStuff ");
        return deptVos;
    }
    
    /**
     * 登陆的时候 需要获取的用户名 (non-Javadoc)
     * 
     * @see com.syuesoft.qx.dao.BasUsersDao#getBasUser(com.syuesoft.model.BasStuff)
     * */
    @SuppressWarnings("unchecked")
    public BasUsers findBasUsersByNameAndPassword(BasUserVO user)
            throws Exception
    {
        BasUsers u = null;
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("from BasUsers user where user.userName='"+ user.getUserName()+ "' and user.userPasswd='"+ user.getUserPasswd() + "'");
        List<BasUsers> list = query.list();
        if (list != null && list.size() > 0)
        {
            u = list.get(0);
        }
        return u;
    }

    public BasUsers getUser(String id) throws Exception
    {
        BasUsers user = (BasUsers) this.getHibernateTemplate().get( BasUsers.class, Long.parseLong(id));
        return user;
    }

    public void Update(BasUsers basUsers) throws Exception
    {
        this.getSession().merge(basUsers);
    }

    @SuppressWarnings("unchecked")
    public List<BasUsers> findAll() throws Exception
    {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("from BasUsers").list();
    }

    public void UpdatePassWord(BasUserVO entity) throws Exception
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("UPDATE bas_users SET USER_PASSWD = '"+ entity.getUserPasswd()+ "' WHERE `USER_ID` = '" + entity.getUserId()+ "'").executeUpdate();
    }

    public BasUsers getUserName(BasUserVO user) throws Exception
    {
        BasUsers u = null;
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery( "from BasUsers user where user.userName='"+ user.getUserName() + "'");
        List<BasUsers> list = query.list();
        if (list != null && list.size() > 0)
        {
            u = list.get(0);
        }
        return u;
    }
    /**
     * 系统用户中是否存在集团管理员
     * */
	public boolean isExistsCombineAdmin() throws Exception {
		List list=createSQLQuery("select * from bas_users where leval_='"+Contstants.EMPLOYEELEVEL.ADMIN+"'");
		if(list!=null&&list.size()>0)
			return true;
		return false;
	}
    
}