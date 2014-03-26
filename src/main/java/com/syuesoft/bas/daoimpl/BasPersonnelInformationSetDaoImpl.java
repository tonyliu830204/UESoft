package com.syuesoft.bas.daoimpl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasPersonnelInformationSetDao;
import com.syuesoft.bas.service.BasCompanyInformationSetService;
import com.syuesoft.base.vo.BasStuffVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasJobProperty;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasStuffJob;
import com.syuesoft.model.BasStuffJobId;
import com.syuesoft.model.BasUsers;
import com.syuesoft.qx.dao.BasUsersDao;
import com.syuesoft.util.Json;
import com.syuesoft.util.MD5;

/**
 * 人事资料设定 Dao实现 
 */
@Repository("basPersonnelInformationSetDao")
public class BasPersonnelInformationSetDaoImpl extends BaseDaoImpl<BasStuff>
        implements BasPersonnelInformationSetDao
{
	@Autowired
	private BasUsersDao basUsersDao;
	@Autowired
	private BasCompanyInformationSetService basCompanyInformationSetService;
    public Json findByParam(final BasStuffVo basStuffVo, final BasUsers user)
            throws Exception
    {
        Json json = new Json();
        String queryString = null;
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
        	queryString = " from BasStuff s , BasUsers u where 1=1 and u.basStuff.stfId = s.stfId ";        		
        }else if(user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMIN)){
        	queryString = " from BasStuff s , BasUsers u where 1=1 and u.basStuff.stfId = s.stfId and (s.enterpriseInfo.enterpriseId="+
        	basStuffVo.getEnterpriseId()+" or s.enterpriseInfo.parentEnterpriseId="+basStuffVo.getEnterpriseId()+") and (u.leavl='"+
        					Contstants.EMPLOYEELEVEL.ADMIN+"' or s.basDept.enterpriseId="+basStuffVo.getEnterpriseId()+")";
        }else{
            queryString = " from BasStuff s , BasUsers u where 1=1 and u.basStuff.stfId = s.stfId "
                    + " and s.stfNo=0 and s.basDept.enterpriseId="+basStuffVo.getEnterpriseId()
                    +" and u.leavl!='"+Contstants.EMPLOYEELEVEL.ADMIN+"'";
        }
        queryString +=" and s.delTag='n' and s.enterpriseInfo.delTag='n'";
        // 查询 所匹配的基本条件
        if (basStuffVo.getStfYid() != null
                && !"".equals(basStuffVo.getStfYid().trim()))
        {
            queryString += " and s.stfYid like '%"
                    + StringEscapeUtils.escapeSql(basStuffVo.getStfYid().trim())
                    + "%'";
        }
        if (basStuffVo.getStfYes() != null
                && !"".equals(basStuffVo.getStfYes().trim()))
        {
            queryString += " and s.stfYes = '"
                    + StringEscapeUtils
                            .escapeSql(basStuffVo.getStfYes().trim()) + "'";
        }

        if (basStuffVo.getDeptId() != null
                && !"".equals(basStuffVo.getDeptId().trim()))
        {
            Short id = Short.parseShort(basStuffVo.getDeptId());
            queryString += " and s.basDept.deptId= " + id;
        }
        if (basStuffVo.getRepgrpId() != null
                && !"".equals(basStuffVo.getRepgrpId().trim()))
        {
            Short id = Short.parseShort(basStuffVo.getRepgrpId());
            queryString += " and s.repgrpId= " + id;
        }
        if (basStuffVo.getStfZxqk() != null && !"".equals(basStuffVo.getStfZxqk().trim()) && !basStuffVo.getStfZxqk().equals(Contstants.ZXQKF.ALL))
        {
            queryString += " and s.stfZxqk = '"+ StringEscapeUtils.escapeSql(basStuffVo.getStfZxqk().trim()) + "'";
        }else if(basStuffVo.getStfZxqk() == null || "".equals(basStuffVo.getStfZxqk().trim())){
            queryString += " and s.stfZxqk = '"+ Contstants.ZXQKF.INSERVICE + "'";
        }
        if (basStuffVo.getStfName() != null
                && !"".equals(basStuffVo.getStfName().trim()))
        {
            queryString += " and ( s.stfName like '%" + StringEscapeUtils.escapeSql(basStuffVo.getStfName().trim())
                    + "%'  or s.stfMark like'%"
                    + StringEscapeUtils.escapeSql(basStuffVo.getStfName().trim())
                    + "%')";
        }
        if (basStuffVo.getSort() != null && !basStuffVo.getSort().equals("")
                && basStuffVo.getOrder() != null
                && !basStuffVo.getOrder().equals(""))
        {
            if (basStuffVo.getSort().equals("deptId"))
            {
                queryString += " order by  s.basDept." + basStuffVo.getSort()
                        + " " + basStuffVo.getOrder();
            }
            else
            {
                queryString += " order by  s." + basStuffVo.getSort() + " "
                        + basStuffVo.getOrder();
            }
        }
        List basStuffList = super.find(queryString.toString(), basStuffVo
                .getPage(), basStuffVo.getRows());
        int num = super.getCount(queryString.toString());
        json.setTotal(num);
        json.setRows(basStuffList);
        return json;
    }

    @Autowired
    BasCompanyInformationSetDAO basCompanyInformationSetDAO;

    /*
     * 3> 获取 员工 List
     */
    @SuppressWarnings("unchecked")
    public List<BasStuff> getBasStuff()
    {
        String hql = "from BasStuff bs";
        return this.getHibernateTemplate().find(hql);
    }
    
    public Object getObject(Class c, Serializable id)
    {
        return this.getSession().get(c, id);
    }

    /*
     * 编辑的 方法
     */
    public void save(BasStuff basStuff, BasStuffVo stuffVo, BasUsers user) throws Exception
    {
        BasUsers basUsers = new BasUsers();
        Set<BasStuffJob> set = new HashSet<BasStuffJob>();
        if (stuffVo.getJobProId() != null
                && !"".equals(stuffVo.getJobProId().trim()))
        {
            String[] str1 = stuffVo.getJobProId().split(",");
            if (str1 != null)
            {
                if (str1.length == 1 && !str1[0].equals(","))
                {
                    BasStuffJob basStuffJob = new BasStuffJob();
                    BasStuffJobId basStuffJobId = new BasStuffJobId();
                    BasJobProperty basJobProperty = new BasJobProperty();
                    basJobProperty.setJobProId(Short.parseShort(stuffVo
                            .getJobProId()));
                    basStuffJobId.setBasStuff(basStuff);
                    basStuffJobId.setBasJobProperty(basJobProperty);
                    basStuffJob.setId(basStuffJobId);
                    set.add(basStuffJob);
                }
                if (str1.length > 1)
                {
                    for (int i = 0; i < str1.length; i++)
                    {
                        if (!str1[i].equals(","))
                        {
                            BasStuffJob basStuffJob = new BasStuffJob();
                            BasStuffJobId basStuffJobId = new BasStuffJobId();
                            BasJobProperty basJobProperty = new BasJobProperty();
                            basJobProperty.setJobProId(Short
                                    .parseShort(str1[i]));
                            basStuffJobId.setBasStuff(basStuff);
                            basStuffJobId.setBasJobProperty(basJobProperty);
                            basStuffJob.setId(basStuffJobId);
                            set.add(basStuffJob);
                        }
                    }
                }
            }
        }
        basStuff.setBasStuffJobs(set);
        if (stuffVo.getSystemId() != null && !"".equals(stuffVo.getSystemId()))
        {
            basUsers.setSystemId(stuffVo.getSystemId());
        }
        else
        {
            basUsers.setSystemId(user.getSystemId());
        }
        basUsers.setUserName(stuffVo.getStfYid());
        BasCompanyInformationSet fs = basCompanyInformationSetService
                .getBasCompanyInformationSet(
                        Contstants.PARAMETER_SET.COLLIGATES,
                        Contstants.COLLIGATES.COLPASSWORD,Integer.parseInt(stuffVo.getTempEnterpriseId()));
        String passWord=null;
        if(fs!=null&&fs.getCiValue()!=null&&fs.getCiValue().length()>0){
        	passWord=fs.getCiValue();
        }else{
        	passWord="123456";
        }
        basUsers.setUserPasswd(MD5.MD5Encode(passWord));
        basUsers.setBasStuff(basStuff);
        boolean tag = false;
        if(isExistsDisbritubteAdmin(stuffVo.getEnterpriseId())){
            basUsers.setLeavl(Contstants.EMPLOYEELEVEL.GENERAL);
        }else{
            basUsers.setLeavl(Contstants.EMPLOYEELEVEL.ADMIN);
            tag = true;
        }
        this.getHibernateTemplate().saveOrUpdate(basUsers);
        List list=createSQLQuery("select br.role_id from bas_role br where br.role_default_tag='y' and br.enterprise_id="+stuffVo.getEnterpriseId());
        if(list!=null&&list.size()>0){
        	executeSQL("insert into bas_user_role values("+basStuff.getStfId()+","+list.get(0).toString()+")");
        }
        if(tag){
            initDb(basUsers);   //初始化系统参数
        }
    }
    
    public void Update(BasStuff basStuff, BasStuffVo stuffVo) throws Exception
    {
    	BasUsers basUsers =basUsersDao.get("from BasUsers bu where bu.basStuff.stfId="+basStuff.getStfId());
        if (stuffVo.getSystemId() != null
                && !"".equals(stuffVo.getSystemId().trim()))
        {
            basUsers.setSystemId(stuffVo.getSystemId());
        }
        if(basUsers.getUserName() == null || "".equals(basUsers.getUserName())){
            basUsers.setUserName(stuffVo.getStfYid());
        }
        if (stuffVo.getLoginPassword() != null
                && !"".equals(stuffVo.getLoginPassword().trim()))
        {
            basUsers.setUserPasswd(stuffVo.getLoginPassword());
        }
        else
        {
        	BasCompanyInformationSet fs = basCompanyInformationSetService
            .getBasCompanyInformationSet(
                    Contstants.PARAMETER_SET.COLLIGATES,
                    Contstants.COLLIGATES.COLPASSWORD,Integer.parseInt(stuffVo.getTempEnterpriseId()));
        	String passWord=null;
            if(fs!=null&&fs.getCiValue()!=null&&fs.getCiValue().length()>0){
            	passWord=fs.getCiValue();
            }else{
            	passWord="123456";
            }
		    basUsers.setUserPasswd(MD5.MD5Encode(passWord));
        }
        if(stuffVo.getLeavl() != null && !"".equals(stuffVo.getLeavl())){
            basUsers.setLeavl(stuffVo.getLeavl());
        }
        if (stuffVo.getJobProId() != null
                && !"".equals(stuffVo.getJobProId().trim()))
        {
            String[] str1 = stuffVo.getJobProId().split(",");
            if (str1 != null)
            {
                if (str1.length == 1 && !str1[0].equals(","))
                {
                    merge(basStuff);
                	executeSQL("delete from bas_stuff_job where stf_id="+basStuff.getStfId());
                	basStuff=get("from BasStuff bs where bs.stfId="+basStuff.getStfId());
                	 executeSQL("insert into bas_stuff_job values("+str1[0].toString()+","+basStuff.getStfId()+")");
                }
                if (str1.length > 1)
                {
                	merge(basStuff);
                	executeSQL("delete from bas_stuff_job where stf_id="+basStuff.getStfId());
                	basStuff=get("from BasStuff bs where bs.stfId="+basStuff.getStfId());
                    for (int i = 0; i < str1.length; i++)
                    {
                        if (!str1[i].equals(","))
                        {
                            executeSQL("insert into bas_stuff_job values("+str1[i].toString()+","+basStuff.getStfId()+")");
                        }
                    }
                }
            }
        }
        boolean tag = false;
        if(basUsers.getLeavl().equals(Contstants.EMPLOYEELEVEL.GENERAL)){
        	if(isExistsDisbritubteAdmin(stuffVo.getEnterpriseId(),basStuff.getStfId().toString(),true)){
        		basUsers.setLeavl(Contstants.EMPLOYEELEVEL.GENERAL);
        	}else{
        		basUsers.setLeavl(Contstants.EMPLOYEELEVEL.ADMIN);
        		tag = true;
        	}
        }
        this.getHibernateTemplate().saveOrUpdate(basUsers);
        if(basStuff.getStfYes().equals(Contstants.SYSTEMUSER.STFYES)){
        	List list=createSQLQuery("select br.role_id from bas_role br where br.role_default_tag='y' and br.enterprise_id="+stuffVo.getEnterpriseId());
        	if(list!=null&&list.size()>0){
        		executeSQL("insert into bas_user_role values("+basStuff.getStfId()+","+list.get(0).toString()+")");
        	}
        }
        if(tag){
            initDb(basUsers);   //初始化系统参数
        }
    }
    
    public void Delete(String id)
    {
        BasStuffVo stuffVo = new BasStuffVo();
        stuffVo.setStfId(id);
        BasUsers user = findBasStuffById(stuffVo);
        if (user != null)
        {
            BasStuff basStuff = user.getBasStuff();
            if (basStuff.getBasStuffJobs() != null)
            {
                basStuff.getBasStuffJobs().clear();
            }
            this.getSession().delete(user);
        }
        else
        {
            BasStuff basStuff = findBasStuff(stuffVo);
            if (basStuff.getBasStuffJobs() != null)
            {
                basStuff.getBasStuffJobs().clear();
            }
            this.getSession().delete(basStuff);
        }
    }

    /*
     * 注销某个用户
     */
    public void updateBasStuff(BasStuffVo stuffVo) throws Exception
    {
            if (stuffVo.getStfZxqk().equals(Contstants.ZXQKF.LOGGEDOFF))
            {
                stuffVo.setStfZxqk(Contstants.ZXQKF.INSERVICE);
            }
            else
            {
                stuffVo.setStfZxqk(Contstants.ZXQKF.LOGGEDOFF);
            }
            String sql = "UPDATE bas_stuff SET STF_ZXQK='"+ stuffVo.getStfZxqk() +"' WHERE STF_ID = '"+ stuffVo.getStfId() +"'";
            this.createSQLQueryOutFind(sql);
    }

    // 通过用户编号获取唯一用户
    
    public BasStuff getObjectBystfYid(BasStuffVo stuffVo)
    {
        List<BasStuff> list = this.getHibernateTemplate().find(
                "from BasStuff as s where s.stfId=" + stuffVo.getPcstfId()
                        + " AND s.stfYid='" + stuffVo.getPcStfYid() + "'");
        BasStuff obj = list != null ? list.get(0) : null;
        return obj;
    }

    /*
     * 变更某个用户
     */
    public void changeUserStfYid(BasStuff basStuff)
    {
        this.getSession().update(basStuff);
    }

    /*
     * 获取员工所持有的工作属性 为修改做准备
     */
    @SuppressWarnings("unchecked")
    
    public List selectJobProId(final BasStuffVo basStuffVo)
    {
        String sql = " SELECT    JOB_PRO_ID   FROM   bas_stuff_job  WHERE STF_ID =?";
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        sqlQuery.setParameter(0, basStuffVo.getStfId());
        List list = sqlQuery.list();
        return list;
    }

    
	public List findStfYid(BasStuffVo stuffVo) {
    	 Query query = this.getSession().createQuery(
                 "from BasStuff s where s.stfYid='" + stuffVo.getStfYid() + "'");
		return query.list();
	}

	
    public List findUserName(BasStuffVo stuffVo, BasUsers user)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("FROM BasStuff stuff WHERE stuff.delTag='n' and stuff.stfName = '" + stuffVo.getStfName() + "' and enterpriseInfo.enterpriseId="+user.getBasStuff().getEnterpriseInfo().getEnterpriseId());
        if (stuffVo.getStfId() != null && !"".equals(stuffVo.getStfId().trim()))
        {
            sql.append("and stuff.stfId <> '" + stuffVo.getStfId() + "'");
        }
        Query sqlQuery = this.getSession().createQuery(sql.toString());
        List list = sqlQuery.list();
        return list;
    }

    
    public BasUsers findBasStuffByYid(BasStuffVo stuffVo)
    {
        Query query = this.getSession().createQuery(
                "from BasUsers user where user.basStuff.stfYid='"
                        + stuffVo.getStfYid() + "'");
        List<BasUsers> list = query.list();
        if (list == null)
        {
            return null;
        }
        if (list.size() < 1)
        {
            return null;
        }
        BasUsers u = list.get(0);
        return u;
    }

    private BasUsers findBasStuffById(BasStuffVo stuffVo)
    {
        Query query = this.getSession().createQuery(
                "from BasUsers user where user.basStuff.stfId='"
                        + stuffVo.getStfId() + "'");
        List<BasUsers> list = query.list();
        if (list == null)
        {
            return null;
        }
        if (list.size() < 1)
        {
            return null;
        }
        BasUsers u = list.get(0);
        return u;
    }

    private BasStuff findBasStuff(BasStuffVo stuffVo)
    {
        Query query = this.getSession().createQuery(
                "from BasStuff stu where stu.stfId='" + stuffVo.getStfId()
                        + "'");
        List<BasStuff> list = query.list();
        if (list == null)
        {
            return null;
        }
        if (list.size() < 1)
        {
            return null;
        }
        BasStuff u = list.get(0);
        return u;
    }
    
	/**
     * 指定企业中管理员是否已存在
     * @exception Exception 总异常
     * @return boolean  true为已存在，false为不存在
     * */
    private boolean isExistsDisbritubteAdmin(String enterpriseId)throws Exception {
    	return isExistsDisbritubteAdmin(enterpriseId,null,false);
    }
    private boolean isExistsDisbritubteAdmin(String enterpriseId,String stfId,boolean flag)
            throws Exception {
        String sql="SELECT * FROM bas_stuff bs WHERE bs.del_tag='n' and bs.enterprise_id="+enterpriseId;
        if(flag){
        	sql+=" and bs.stf_id!="+stfId;
        }
        List list=basUsersDao.createSQLQuery(sql);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
}