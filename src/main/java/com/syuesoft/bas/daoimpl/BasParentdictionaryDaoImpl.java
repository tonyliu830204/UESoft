package com.syuesoft.bas.daoimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasParentdictionaryDao;
import com.syuesoft.model.BasParentdictionary;

/**
 * 父级数据Dao
 * */
@Repository("basParentdictionaryDao")
public class BasParentdictionaryDaoImpl extends
        BaseDaoImpl<BasParentdictionary> implements BasParentdictionaryDao
{

}
