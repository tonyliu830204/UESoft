package com.syuesoft.bas.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasChilddictionaryDao;
import com.syuesoft.model.BasChilddictionary;

/**
 * 子级数据Dao
 * */
@Repository("basChilddictionaryDaoI")
public class BasChilddictionaryDaoImpl extends BaseDaoImpl<BasChilddictionary>
        implements BasChilddictionaryDao
{

}
