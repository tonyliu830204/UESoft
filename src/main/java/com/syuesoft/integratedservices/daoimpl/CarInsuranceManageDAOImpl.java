package com.syuesoft.integratedservices.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.integratedservices.dao.CarInsuranceManageDAO;
import com.syuesoft.model.CarInsuranceManage;
import com.syuesoft.model.CenterCarinInty;
import com.syuesoft.model.InsuranceType;

public class CarInsuranceManageDAOImpl extends BaseDaoImpl<CarInsuranceManage>
        implements CarInsuranceManageDAO
{

    
    public int doAdd(CarInsuranceManage carInsuranceManage) throws Exception
    {
        org.hibernate.Session hibernateSession = super.getSessionFactory()
                .openSession();
        org.hibernate.Transaction tr = hibernateSession.beginTransaction();
        hibernateSession.save(carInsuranceManage);
        tr.commit();
        hibernateSession.flush();
        hibernateSession.close();
        String hql = "select max(carInsuranceManageId) from CarInsuranceManage";
        HttpSession session = ServletActionContext.getRequest().getSession();
        String cid = this.getHibernateTemplate().find(hql).get(0).toString();
        session.setAttribute("cid", cid);
        return 1;
    }

    /*
     * 查询所有 (non-Javadoc)
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CarInsuranceManagerVoDAO#doFindAll
     * (int, int, com.syuesoft.model.CarInsuranceManagerVo)
     */
    @SuppressWarnings("unchecked")
    
    public List<CarInsuranceManage> doFindAll(final int page, final int rows,
            final String startime, final String endtime,
            final CarInsuranceManage carInsuranceManage) throws Exception
    {
        List<CarInsuranceManage> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = " from CarInsuranceManage cimg where 1 = 1 ";
                        if (startime != null && !startime.trim().equals("")
                                && endtime != null
                                && !endtime.trim().equals(""))
                        {
                            hql += " and cimg.insuranceDate between '"
                                    + startime + "' and '" + endtime + "' ";
                        }
                        if (carInsuranceManage.getCarBrand() != null
                                && !carInsuranceManage.getCarBrand().trim()
                                        .equals(""))
                        {
                            hql += " and cimg.carBrand like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getCarBrand().trim()) + "%'";
                        }
                        if (carInsuranceManage.getInsuranceNumber() != null
                                && !carInsuranceManage.getInsuranceNumber()
                                        .trim().equals(""))
                        {
                            hql += " and cimg.insuranceNumber like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getInsuranceNumber().trim())
                                    + "%'";
                        }
                        if (carInsuranceManage.getInsuranceCompany() != null
                                && !carInsuranceManage.getInsuranceCompany()
                                        .trim().equals(""))
                        {
                            hql += " and cimg.insuranceCompany like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getInsuranceCompany().trim())
                                    + "%'";
                        }
                        if (carInsuranceManage.getTheInsuredPerson() != null
                                && !carInsuranceManage.getTheInsuredPerson()
                                        .trim().equals(""))
                        {
                            hql += " and cimg.theInsuredPerson like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getTheInsuredPerson().trim())
                                    + "%'";
                        }
                        if (carInsuranceManage.getReceptor() != null
                                && !carInsuranceManage.getReceptor().trim()
                                        .equals(""))
                        {
                            hql += " and cimg.receptor like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getReceptor().trim()) + "%'";
                        }
                        if (carInsuranceManage.getInsuranceGroup() != null
                                && !carInsuranceManage.getInsuranceGroup()
                                        .trim().equals(""))
                        {
                            hql += " and cimg.insuranceGroup like '%"
                                    + StringEscapeUtils.escapeSql(carInsuranceManage.getInsuranceGroup().trim())
                                    + "%'";
                        }
                        Query query = session.createQuery(hql);
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("size", query.list().size());
                        query.setFirstResult((page - 1) * rows).setMaxResults(
                                rows);
                        return query.list();
                    }
                });
        return list;
    }

    
    public List<InsuranceType> findAllByInsuranceType() throws Exception
    {
        String hql = "from InsuranceType";
        return this.getHibernateTemplate().find(hql);
    }

    /*
     * 中间表的新增方法 (non-Javadoc)
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CarInsuranceManageDAO#doCenterTableAdd
     * (com.syuesoft.model.CenterCarinInty)
     */
    
    public int doCenterTableAdd(CenterCarinInty centerCarinInty)
            throws Exception
    {
        Session session = super.getSessionFactory().openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        session.save(centerCarinInty);
        tr.commit();
        session.flush();
        session.close();
        return 1;
    }

    /*
     * 获取cimgid对应的intpid (non-Javadoc)
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CarInsuranceManageDAO#doCenterTableDelete
     * (com.syuesoft.model.CenterCarinInty)
     */
    
    public List<CenterCarinInty> getID(CarInsuranceManage carInsuranceManage)
            throws Exception
    {
        List list = new ArrayList();
        HttpSession ssion = ServletActionContext.getRequest().getSession();
        String hql = "select citp.id.insuranceType.id from CenterCarinInty citp where citp.id.carInsuranceManage.carInsuranceManageId="
                + carInsuranceManage.getCarInsuranceManageId();
        list = this.getHibernateTemplate().find(hql);
        return list;
    }

    // 普通删除
    
    public void doCenterTableDelete(CenterCarinInty centerCarinInty)
            throws Exception
    {
        Session session = super.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.delete(centerCarinInty);
        tr.commit();
        session.flush();
        session.close();
        // this.getHibernateTemplate().delete(centerCarinInty);
    }

    
    public void doDelete(CarInsuranceManage carInsuranceManage)
            throws Exception
    {
        this.getHibernateTemplate().delete(carInsuranceManage);
    }

    /*
     * (non-Javadoc) 对车辆保单修改的方法
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CarInsuranceManageDAO#doUpdate(com
     * .syuesoft.model.CarInsuranceManage)
     */
    
    public void doUpdate(CarInsuranceManage carInsuranceManage)
            throws Exception
    {
        this.getHibernateTemplate().update(carInsuranceManage);
    }

    // 通过车辆保单id查询险种表的信息
    
    public List<InsuranceType> findByCarInsuranceManageid(
            CarInsuranceManage carInsuranceManage)
    {
        String hql = "select DISTINCT intp.id "
                + " from CarInsuranceManage cimg , CenterCarinInty cci , InsuranceType intp "
                + " where cci.id.insuranceType.id = intp.id and cci.id.carInsuranceManage.carInsuranceManageId = cimg.carInsuranceManageId and cimg.carInsuranceManageId="
                + carInsuranceManage.getCarInsuranceManageId();
        return this.getHibernateTemplate().find(hql);

    }

    // 对中间表进行修改
    
    public void doCenterTableUpdate(CenterCarinInty centerCarinInty)
            throws Exception
    {
        // 修改前 先对中间表的数据删除
        // this.doCenterTableDelete(centerCarinInty);
        // 调用本类中的doCenterTableAdd方法
        this.doCenterTableAdd(centerCarinInty);
    }

    
    public void deleteCenterTable(int id) throws Exception
    {
    }

    @SuppressWarnings("unchecked")
    
    public List onlyFind(final int page, final int rows) throws Exception
    {

        List<CarInsuranceManage> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = " from CarInsuranceManage ";

                        Query query = session.createQuery(hql);
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("sizes", query.list().size());
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }
                });
        return list;
    }

    // 通过id获取中间表的信息
    
    public List getCenterTableById(int id) throws Exception
    {
        String hql = "select cci.id.insuranceType.id from CenterCarinInty cci where cci.id.carInsuranceManage.carInsuranceManageId="
                + id;
        return this.getHibernateTemplate().find(hql);

    }

    /**
     * 获取员工名称 用于combox
     */
    
    public List getBasStuff() throws Exception
    {
        return this.getHibernateTemplate().find(
                "SELECT A.stfName from BasStuff A where A.delTag='n'");
    }

    /**
     * 获取部门名称 用于combox
     */
    
    public List getbasDept() throws Exception
    {
        return this.getHibernateTemplate().find(
                "SELECT A.deptName from BasDept A");
    }

}
