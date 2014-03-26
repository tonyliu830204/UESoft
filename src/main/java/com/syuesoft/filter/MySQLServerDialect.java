package com.syuesoft.filter;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.type.NullableType;

public class MySQLServerDialect extends MySQL5Dialect
{
  public MySQLServerDialect()
  {
    registerHibernateType(-1, Hibernate.TEXT.getName());
  }
}