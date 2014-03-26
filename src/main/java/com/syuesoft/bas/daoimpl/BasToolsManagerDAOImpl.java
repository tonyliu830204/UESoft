package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasToolsManagerDAO;
import com.syuesoft.base.vo.ToolsManagerVo;
import com.syuesoft.model.BasToolsManager;

@Repository("basToolsManagerDAO")
public class BasToolsManagerDAOImpl extends BaseDaoImpl<BasToolsManager>
        implements BasToolsManagerDAO
{

    
    public void delete(Short id)
    {
        BasToolsManager btm = this.getHibernateTemplate().get(
                BasToolsManager.class, id);
        this.getHibernateTemplate().delete(btm);
    }

    @SuppressWarnings("unchecked")
    
    public List<ToolsManagerVo> findAll(final String param, final int page,
            final int rows)
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<ToolsManagerVo>>()
        {
            
            public List<ToolsManagerVo> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                Query query = session
                        .createQuery(
                                "select new com.syuesoft.base.vo.ToolsManagerVo(btm.toolsId, btm.recordDate, btm.toolsName, btm.toolsType, btm.toolsUnit, btm.toolsState, btm.procurementPrice, btm.supplier, btm.linkman, btm.telphone, btm.buyer, bpu.punitName, brc.relcampName, bs.stfName) from BasToolsManager btm, BasPartsUnit bpu, BasStuff bs, BasRelationCampany brc where btm.toolsUnit = bpu.punitId and bs.stfId = btm.buyer and btm.supplier = brc.relcampId "
                                        + param).setFirstResult(
                                (page - 1) * rows).setMaxResults(rows);
                return query.list();
            }
        });
    }

    
    public void update(BasToolsManager btm)
    {
        this.getHibernateTemplate().merge(btm);
    }

    @SuppressWarnings("unchecked")
    
    public List<ToolsManagerVo> findAll(String param)
    {
        return this
                .getHibernateTemplate()
                .find(
                        "select new com.syuesoft.base.vo.ToolsManagerVo(btm.toolsId, btm.recordDate, btm.toolsName, btm.toolsType, btm.toolsUnit, btm.toolsState, btm.procurementPrice, btm.supplier, btm.linkman, btm.telphone, btm.buyer, bpu.punitName, brc.relcampName, bs.stfName) from BasToolsManager btm, BasPartsUnit bpu, BasStuff bs, BasRelationCampany brc where btm.toolsUnit = bpu.punitId and bs.stfId = btm.buyer and btm.supplier = brc.relcampId "
                                + param);
    }
}
