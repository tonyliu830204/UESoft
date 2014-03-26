package com.syuesoft.bas.daoimpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.sql.*;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasUsers;

@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDaoI<T>
{

    /**
     * Logger for this class
     */
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);

    @Resource(name = "sessionFactory")
    public void setHibernateTemplate(SessionFactory sessionFactory)
            throws Exception
    {
        super.setSessionFactory(sessionFactory);
    }

    
    public Serializable save(T o) throws Exception
    {
        return this.getHibernateTemplate().save(o);
    }

    
    public void delete(T o) throws Exception
    {
        this.getHibernateTemplate().delete(o);
    };

    public void deleteByHql(String hql) throws Exception
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createQuery(hql).executeUpdate();
    }

    public void deleteBySql(String sql) throws Exception
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql).executeUpdate();
    }

    
    public void update(T o) throws Exception
    {
        this.getHibernateTemplate().update(o);
    };

    
    public void saveOrUpdate(T o) throws Exception
    {
        this.getHibernateTemplate().saveOrUpdate(o);
    };
    
    public void saveOrUpdates(List<T> objects) throws Exception
    {  
        for(Object obj : objects){  
            getHibernateTemplate().saveOrUpdate(obj);  
            if(objects.size()%100 == 0){
                getHibernateTemplate().flush();  
                getHibernateTemplate().clear();//后添加  
            }
        }  
    }
    
    public T merge(T o) throws Exception
    {
        return this.getHibernateTemplate().merge(o);
    };

    
    public T get(Class<T> c, Serializable id) throws Exception
    {
        return (T) this.getHibernateTemplate().get(c, id);
    }

    /**
     * 字符串拼接sql语句方式
     */
    
    public T get(String hql) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        List<T> list = query.list();
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * ？问号占位符方式
     */
    
    public T get(String hql, Object[] params) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0)
        {
            for (int i = 0; i < params.length; i++)
            {
                query.setParameter(i, params[i]);
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 名称占位符方式
     */
    
    public T get(String hql, Map<String, Object> params) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
    
    public List<T> getList(String hql) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        List<T> list = query.list();
        if (list != null && list.size() > 0)
            return list;
        return null;
    }
    
    /**
     * 查询一组对象(List)
     * @param hql
     *            hql语句
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql) throws Exception
    {
        List<T> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 带条件查询一组对象(List)，？问号占位符方式传参
     * 
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数数组
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql, Object[] params) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0)
        {
            for (int i = 0; i < params.length; i++)
            {
                query.setParameter(i, params[i]);
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 带条件查询一组对象(List)，？问号占位符方式传参
     */
    
    public List<T> find(String hql, List<Object> params) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0)
        {
            for (int i = 0; i < params.size(); i++)
            {
                query.setParameter(i, params.get(i));
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 查询一组对象(List),名称占位符方式
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数集合
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql, Map<String, Object> params)
            throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.size() > 0)
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
        List<T> list = query.list();
        if (list != null && list.size() > 0)
            return list;
        return null;
    }

    /**
     * 查询一组对象(List)带分页
     * 
     * @param hql
     *            hql语句
     * @param page
     *            分页参数，当前第几页
     * @param rows
     *            分页参数，每页显示多少条记录数
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql, int page, int rows) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        List<T> list = query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
        if (list != null && list.size() > 0)
            return list;
        return null;
    }

    /**
     * 带条件查询一组对象(List)，？问号占位符方式传参,带分页
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数数组
     * @param page
     *            分页参数，当前第几页
     * @param rows
     *            分页参数，每页显示多少条记录数
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql, Object[] params, int page, int rows)
            throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0)
        {
            for (int i = 0; i < params.length; i++)
            {
                query.setParameter(i, params[i]);
            }
        }
        query.setFirstResult((page - 1) * rows).setMaxResults(rows);
        List<T> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数集合
     * @param page
     *            分页参数，当前第几页
     * @param rows
     *            分页参数，每页显示多少条记录数
     * @return 返回一个list的对象集合
     */
    
    public List<T> find(String hql, Map<String, Object> params, int page,
            int rows) throws Exception
    {
        try
        {
            Query query = this.getHibernateTemplate().getSessionFactory()
                    .getCurrentSession().createQuery(hql);
            if (params != null && !params.isEmpty())
            {
                for (String key : params.keySet())
                {
                    query.setParameter(key, params.get(key));
                }
            }
            List<T> list = query.setFirstResult((page - 1) * rows)
                    .setMaxResults(rows).list();
            if (list != null && list.size() > 0)
            {
                return list;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @param sql
     *            sql语句
     * @param params
     *            传递的参数集合
     * @param page
     *            分页参数，当前第几页
     * @param rows
     *            分页参数，每页显示多少条记录数
     * @return 返回一个list的Object数据集合
     */
    @SuppressWarnings("unchecked")
    
    public List<Object[]> createSQLQuery(String sql,
            Map<String, Object> params, int page, int rows) throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
        List<Object[]> list = query.setFirstResult((page - 1) * rows)
                .setMaxResults(rows).list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 
     * @param sql
     *            sql语句
     * @param params
     *            传递的参数集合
     * @param page
     *            分页参数，当前第几页
     * @return 返回一个list的Object数据集合
     */
    
    public List<Object[]> createSQLQuery(String sql, Map<String, Object> params)
            throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
        List<Object[]> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 
     * @param sql
     *            sql语句
     * @param params
     *            传递的参数集合
     * @param page
     *            分页参数，当前第几页
     * @param rows
     *            分页参数，每页显示多少条记录数
     * @return 返回一个list的Object数据集合
     */
    @SuppressWarnings("unchecked")
    
    public List<Object[]> createSQLQuery(String sql, int page, int rows)
            throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.setFirstResult((page - 1) * rows)
                .setMaxResults(rows).list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 
     * @param sql
     *            sql语句
     * @param params
     *            传递的参数集合
     * @param page
     *            分页参数，当前第几页
     * @return 返回一个list的Object数据集合
     */
    
    public List<Object[]> createSQLQuery(String sql) throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

    /**
     * 查询一组对象(List)的记录数
     * 
     * @param hql
     *            hql语句
     * @return 返回一个唯一的记录数
     */
    
    public int getCount(String hql) throws Exception
    {
    	 QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql,
    	           Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor)this.getHibernateTemplate().getSessionFactory());
    	        queryTranslator.compile(Collections.EMPTY_MAP, false);
    	 return getSQLCount( queryTranslator.getSQLString(), null);
    }

    /**
     * 带条件查询一组对象(List)的记录数，？问号占位符方式传参
     * 
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数数组
     * @return 返回一个唯一的记录数
     */
    
    public int getCount(String hql, Object[] params) throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && params.length > 0)
        {
            for (int i = 0; i < params.length; i++)
            {
                query.setParameter(i, params[i]);
            }
        }
        if (query.list() != null)
        {
            return query.list().size();
        }
        return 0;
    }

    /**
     * 带条件查询一组对象(List)的记录数，名称占位符方式传参
     * 
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数集合
     * @return 返回一个唯一的记录数
     */
    
    public int getCount(String hql, Map<String, Object> params)
            throws Exception
    {
        Query query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }

        if (query.list() != null)
        {
            return query.list().size();
        }
        return 0;
    }

    /**
     * 带条件的sql语句查询，名称占位符传参
     * 
     * @param hql
     *            hql语句
     * @param params
     *            传递的参数集合
     * @return 返回一个唯一的记录数
     */
    
    public int getSQLCount(String sql, Map<String, Object> params)
            throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery("select count(*) from ("+sql+") a");
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                query.setParameter(key, params.get(key));
            }
        }
        if (query.list() != null)
        {
            return Integer.parseInt(query.list().get(0).toString());
        }
        return 0;
    }

    
    public int getCountBySQL(String sql) throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql);
        if (query.list() != null)
        {
            return query.list().size();
        }
        return 0;
    }
    
    /**
     * @param sql
     * sql语句
     */
    public void createSQLQueryOutFind(String sql) throws Exception
    {
        this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createSQLQuery(sql).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getProcedureQuery(String procedureName, List<Object> params) throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(procedureName); 
        if (params != null && !params.isEmpty())
        {
            int i = 0;
            for (Object str : params) {  
                query.setString(i, str!=null ?str.toString():null);
                i++;
            }  
        }
        List<Object[]> list = query.list();
        if (list != null && list.size() > 0)
        {
            return list;
        }
        return null;
    }

	
	public Integer executeSQL(String sql) throws Exception {
		Integer ser = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
		return ser;
	}

    @SuppressWarnings("unchecked")
    public void initDb(final BasUsers user) throws Exception
    {
        //维修系统参数
        final String sql = "SELECT CI_ID,CI_LABLE,CI_NAME,CI_VALUE,CI_REMARK,CI_INPUT_CONTROL,CI_TYPE FROM companyinformationtemp";
        final Integer enterpriseId = user.getBasStuff().getEnterpriseInfo().getEnterpriseId();
        this.getHibernateTemplate().execute(      
                new HibernateCallback(){ 
                    @SuppressWarnings("deprecation")
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {      
                        Connection conn = session.connection();      
                        PreparedStatement ps = conn.prepareStatement(sql);
                        String insertSql = "INSERT INTO bas_company_information_set(CI_ID,CI_LABLE,CI_NAME,CI_VALUE,CI_REMARK,CI_INPUT_CONTROL,CI_TYPE,enterprise_id) values (?,?,?,?,?,?,?,?)";
                        ResultSet rs = ps.executeQuery(); 
                        conn.setAutoCommit(false);
                        PreparedStatement ps1 = conn.prepareStatement(insertSql);
                        int i = 0;
                         try{
                             while(rs.next()){
                                 String CI_ID = rs.getString("CI_ID");
                                 String CI_LABLE = rs.getString("CI_LABLE");
                                 String CI_NAME = rs.getString("CI_NAME");
                                 String CI_VALUE = rs.getString("CI_VALUE");
                                 String CI_REMARK = rs.getString("CI_REMARK");
                                 String CI_INPUT_CONTROL = rs.getString("CI_INPUT_CONTROL");
                                 String CI_TYPE = rs.getString("CI_TYPE");
                                 
                                 ps1.setString(1, CI_ID);  
                                 ps1.setString(2, CI_LABLE);  
                                 ps1.setString(3, CI_NAME);  
                                 ps1.setString(4, CI_VALUE);  
                                 ps1.setString(5, CI_REMARK);
                                 ps1.setString(6, CI_INPUT_CONTROL);  
                                 ps1.setString(7, CI_TYPE);
                                 ps1.setInt(8, enterpriseId);
                                 ps1.addBatch(); 
                                 if(i % 5 == 0 || rs.isLast()){
                                     ps1.executeBatch(); 
                                     try{
                                         conn.commit();
                                     }catch(SQLException exc){
                                         conn.rollback();
                                     }
                                 }
                                 i++;
                             }
                          }catch(SQLException e){
                              if(conn!=null)   
                                  try{conn.close();   
                              }catch(SQLException esql){  
                                  esql.printStackTrace();
                              };  
                             e.printStackTrace();
                          }
                          finally { 
                              try {
                                  if(rs!=null) rs.close(); 
                                  if(ps!=null) ps.close();  
                                  if(ps1!=null) ps1.close();  
                                  if(conn!=null) conn.close();
                                  session.flush();      
                                  session.close();
                              } catch (SQLException e) {
                                  e.printStackTrace();
                              }
                          }
                          return null;
                     }
                 }      
         );   
        
        //销售系统参数
        final String sql1 = "SELECT CI_ID,CI_LABLE,CI_NAME,CI_VALUE,CI_INPUT_CONTROL,CI_TYPE,CI_REMARK FROM xssellparametertemp";      
        this.getHibernateTemplate().execute(      
                new HibernateCallback(){ 
                    @SuppressWarnings("deprecation")
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {      
                        Connection conn = session.connection();      
                        PreparedStatement ps = conn.prepareStatement(sql1);
                        String insertSql = "INSERT INTO xs_sell_parameter(CI_ID,CI_LABLE,CI_NAME,CI_VALUE,CI_INPUT_CONTROL,CI_TYPE,CI_REMARK,enterprise_id) VALUES (?,?,?,?,?,?,?,?)";
                        ResultSet rs = ps.executeQuery(); 
                        conn.setAutoCommit(false);
                        PreparedStatement ps1 = conn.prepareStatement(insertSql);
                        int i = 0;
                         try{
                             while(rs.next()){
                                 String CI_ID = rs.getString("CI_ID");
                                 String CI_LABLE = rs.getString("CI_LABLE");
                                 String CI_NAME = rs.getString("CI_NAME");
                                 String CI_VALUE = rs.getString("CI_VALUE");
                                 String CI_REMARK = rs.getString("CI_REMARK");
                                 String CI_INPUT_CONTROL = rs.getString("CI_INPUT_CONTROL");
                                 String CI_TYPE = rs.getString("CI_TYPE");
                                 
                                 ps1.setString(1, CI_ID);  
                                 ps1.setString(2, CI_LABLE);  
                                 ps1.setString(3, CI_NAME);  
                                 ps1.setString(4, CI_VALUE);  
                                 ps1.setString(5, CI_REMARK);
                                 ps1.setString(6, CI_INPUT_CONTROL);  
                                 ps1.setString(7, CI_TYPE);
                                 ps1.setInt(8, enterpriseId);
                                 ps1.addBatch(); 
                                 if(i % 5 == 0 || rs.isLast()){
                                     ps1.executeBatch(); 
                                     try{
                                         conn.commit();
                                     }catch(SQLException exc){
                                         conn.rollback();
                                     }
                                 }
                                 i++;
                             }
                          }catch(SQLException e){
                              if(conn!=null)   
                                  try{conn.close();   
                              }catch(SQLException esql){  
                                  esql.printStackTrace();
                              };  
                             e.printStackTrace();
                          }
                          finally { 
                              try {
                                  if(rs!=null) rs.close(); 
                                  if(ps!=null) ps.close();  
                                  if(ps1!=null) ps1.close();  
                                  if(conn!=null) conn.close();
                                  session.flush();      
                                  session.close();
                              } catch (SQLException e) {
                                  e.printStackTrace();
                              }
                          }
                          return null;
                     }
                 }      
         );
        
        //维修业务权限
        final String sql2 = "SELECT CONTROL_NAME,CONTROL_LABLE,CONTROL_PUT_TYPE,CONTROL_TYPE FROM wxcontrolTemp";      
        this.getHibernateTemplate().execute(      
                new HibernateCallback(){ 
                    @SuppressWarnings("deprecation")
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {      
                        Connection conn = session.connection();      
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        String insertSql = "INSERT INTO wx_control(CONTROL_NAME,CONTROL_LABLE,CONTROL_PUT_TYPE,CONTROL_TYPE,enterprise_id) VALUES (?,?,?,?,?)";
                        ResultSet rs = ps.executeQuery(); 
                        conn.setAutoCommit(false);
                        PreparedStatement ps1 = conn.prepareStatement(insertSql);
                        int i = 0;
                         try{
                             while(rs.next()){
                                 String CONTROL_NAME = rs.getString("CONTROL_NAME");
                                 String CONTROL_LABLE = rs.getString("CONTROL_LABLE");
                                 String CONTROL_PUT_TYPE = rs.getString("CONTROL_PUT_TYPE");
                                 String CONTROL_TYPE = rs.getString("CONTROL_TYPE");
                                 
                                 ps1.setString(1, CONTROL_NAME);  
                                 ps1.setString(2, CONTROL_LABLE);  
                                 ps1.setString(3, CONTROL_PUT_TYPE);  
                                 ps1.setString(4, CONTROL_TYPE);  
                                 ps1.setInt(5, enterpriseId);
                                 ps1.addBatch(); 
                                 if(i % 5 == 0 || rs.isLast()){
                                     ps1.executeBatch(); 
                                     try{
                                         conn.commit();
                                     }catch(SQLException exc){
                                         conn.rollback();
                                     }
                                 }
                                 i++;
                             }
                          }catch(SQLException e){
                              if(conn!=null)   
                                  try{conn.close();   
                              }catch(SQLException esql){  
                                  esql.printStackTrace();
                              };  
                             e.printStackTrace();
                          }
                          finally { 
                              try {
                                  if(rs!=null) rs.close(); 
                                  if(ps!=null) ps.close();  
                                  if(ps1!=null) ps1.close();  
                                  if(conn!=null) conn.close();
                                  session.flush();      
                                  session.close();
                              } catch (SQLException e) {
                                  e.printStackTrace();
                              }
                          }
                          return null;
                     }
                 }      
         );
        
         //销售数据字典字表
        final String sql3 = "SELECT parent_id,createUser,createTime,dataKey,dataValue,remark FROM xschilddictionarytemp";      
        this.getHibernateTemplate().execute(      
                new HibernateCallback(){ 
                    @SuppressWarnings("deprecation")
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {      
                        Connection conn = session.connection();      
                        PreparedStatement ps = conn.prepareStatement(sql3);
                        String insertSql = "INSERT INTO xs_childdictionary(parent_id,createUser,createTime,dataKey,dataValue,remark,enterprise_id)VALUES(?,?,?,?,?,?,?)";
                        ResultSet rs = ps.executeQuery(); 
                        conn.setAutoCommit(false);
                        PreparedStatement ps1 = conn.prepareStatement(insertSql);
                        int i = 0;
                         try{
                             while(rs.next()){
                                 String parent_id = rs.getString("parent_id");
                                 String createUser = rs.getString("createUser");
                                 String createTime = rs.getString("createTime");
                                 String dataKey = rs.getString("dataKey");
                                 String dataValue = rs.getString("dataValue");
                                 String remark = rs.getString("remark");
                                 
                                 ps1.setString(1, parent_id);  
                                 ps1.setString(2, createUser);  
                                 ps1.setString(3, createTime);  
                                 ps1.setString(4, dataKey);
                                 ps1.setString(5, dataValue);
                                 ps1.setString(6, remark);
                                 ps1.setInt(7, enterpriseId);
                                 ps1.addBatch(); 
                                 if(i % 5 == 0 || rs.isLast()){
                                     ps1.executeBatch(); 
                                     try{
                                         conn.commit();
                                     }catch(SQLException exc){
                                         conn.rollback();
                                     }
                                 }
                                 i++;
                             }
                          }catch(SQLException e){
                              if(conn!=null)   
                                  try{conn.close();   
                              }catch(SQLException esql){  
                                  esql.printStackTrace();
                              };  
                             e.printStackTrace();
                          }
                          finally { 
                              try {
                                  if(rs!=null) rs.close(); 
                                  if(ps!=null) ps.close();  
                                  if(ps1!=null) ps1.close();  
                                  if(conn!=null) conn.close();
                                  session.flush();      
                                  session.close();
                              } catch (SQLException e) {
                                  e.printStackTrace();
                              }
                          }
                          return null;
                     }
                 }      
         );
    }
}