package com.syuesoft.bas.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasRelationCampanyDao;
import com.syuesoft.model.BasRelationCampany;

/**
 * 相关单位dao-(包含供应商和保险公司等);
 * 
 * @author Liujian
 * 
 */
@Repository("basRelationCampanyDao")
public class BasRelationCampanyDaoImpl extends BaseDaoImpl<BasRelationCampany>
        implements BasRelationCampanyDao
{

}
