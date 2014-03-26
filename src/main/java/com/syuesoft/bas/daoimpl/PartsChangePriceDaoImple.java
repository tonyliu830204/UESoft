package com.syuesoft.bas.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.PartsChangePriceDao;
import com.syuesoft.model.PartsChangePrice;

/**
 * 配件调价dao
 * 
 * @author Liujian
 * 
 */
@Repository("partsChangePriceDao")
public class PartsChangePriceDaoImple extends BaseDaoImpl<PartsChangePrice>
        implements PartsChangePriceDao
{

    
    public void update(PartsChangePrice pcp)
    {
        this.getHibernateTemplate().merge(pcp);
    }
}
