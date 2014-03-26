package com.syuesoft.bas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.syuesoft.model.BasUsers;

public interface BaseDaoI<T>
{

    public Serializable save(T o) throws Exception; // 新增

    public void delete(T o) throws Exception; // 删除

    public void deleteByHql(String hql) throws Exception; // 删除

    public void deleteBySql(String sql) throws Exception; // 删除

    public void update(T o) throws Exception; // 更新

    public void saveOrUpdate(T o) throws Exception; // 新增或更新
    
    public void saveOrUpdates(List<T> objects) throws Exception; //批量新增或更新
    
    public T merge(T o) throws Exception; // 合并

    public T get(Class<T> c, Serializable id) throws Exception; // 根据id得到一个对象

    public T get(String hql) throws Exception; // 查询一个对象

    public T get(String hql, Object[] params) throws Exception; // 查询一个对象，？问号占位符方式传参

    public T get(String hql, Map<String, Object> params) throws Exception; // 带条件查询一个对象，名称占位符方式传参
    
    public List<T> getList(String hql) throws Exception; // 查询一个对象
    
    public List<T> find(String hql) throws Exception; // 查询一组对象(List)

    public List<T> find(String hql, Object[] params) throws Exception; // 带条件查询一组对象(List)，？问号占位符方式传参

    public List<T> find(String hql, List<Object> params) throws Exception; // 带条件查询一组对象(List)，？问号占位符方式传参

    public List<T> find(String hql, Map<String, Object> params)
            throws Exception; // 查询一组对象(List),名称占位符方式

    public List<T> find(String hql, int page, int rows) throws Exception; // 查询一组对象(List)带分页

    public List<T> find(String hql, Object[] params, int page, int rows)
            throws Exception; // 带条件查询一组对象(List)，？问号占位符方式传参,带分页

    public List<T> find(String hql, Map<String, Object> params, int page,
            int rows) throws Exception; // 带条件查询一组对象(List)，名称占位符方式传参，带分页

    public List<Object[]> createSQLQuery(String sql, Map<String, Object> params)
            throws Exception; // 带条件的sql语句查询，名称占位符传参

    public List<Object[]> createSQLQuery(String sql,
            Map<String, Object> params, int page, int rows) throws Exception; // 带条件的sql语句查询，名称占位符传参，带分页

    public List<Object[]> createSQLQuery(String sql) throws Exception; // 带条件的sql语句查询，名称占位符传参

    public List<Object[]> createSQLQuery(String sql, int page, int rows)
            throws Exception; // 带条件的sql语句查询，名称占位符传参，带分页

    public int getCount(String hql) throws Exception; // 查询一组对象(List)的记录数

    public int getCount(String hql, Object[] params) throws Exception; // 带条件查询一组对象(List)的记录数，？问号占位符方式传参

    public int getCount(String hql, Map<String, Object> params)
            throws Exception; // 带条件查询一组对象(List)的记录数，名称占位符方式传参

    public int getSQLCount(String sql, Map<String, Object> params)
            throws Exception; // 带条件的sql语句查询，名称占位符传参

    public int getCountBySQL(String sql) throws Exception;
    
    public void createSQLQueryOutFind(String sql) throws Exception;
    
    public List<Object[]> getProcedureQuery(String procedureName,List<Object> params) throws Exception;   //调用存储过程
    
    //初始化数据库
    public Integer executeSQL(String sql) throws Exception;//执行sql语句
    
    public void initDb(BasUsers user) throws Exception;//执行sql语句
}