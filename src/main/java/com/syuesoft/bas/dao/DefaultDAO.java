package com.syuesoft.bas.dao;

import java.util.List;

public interface DefaultDAO extends BaseDaoI<Object>
{
    public List getObjList(String hql);

    public Object getObj(String hql); // 查询一个对象

    public List getSQLObjList(String sql);
}