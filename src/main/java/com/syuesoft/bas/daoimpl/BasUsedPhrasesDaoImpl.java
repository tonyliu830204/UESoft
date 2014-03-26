package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.syuesoft.bas.dao.BasUsedPhrasesDao;
import com.syuesoft.model.BasUsedPhrases;
import com.syuesoft.util.Json;

public class BasUsedPhrasesDaoImpl extends BaseDaoImpl<BasUsedPhrases>
        implements BasUsedPhrasesDao
{

    private BasUsedPhrasesDao dao;

    public BasUsedPhrasesDao getDao()
    {
        return dao;
    }

    public void setDao(BasUsedPhrasesDao dao)
    {
        this.dao = dao;
    }

    public boolean Add(BasUsedPhrases basUsedPhrases) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasUsedPhrases where phrassesName='"
                                + basUsedPhrases.getPhrassesName() + "' and enterpriseId="+basUsedPhrases.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().save(basUsedPhrases);
            return false;
        }

    }

    public void Delete(BasUsedPhrases basUsedPhrases) throws Exception
    {
        this.getHibernateTemplate().delete(basUsedPhrases);
    }

    public boolean Update(BasUsedPhrases basUsedPhrases) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasUsedPhrases where phrassesName='"
                                + basUsedPhrases.getPhrassesName()
                                + "' and phrasesId not in ("
                                + basUsedPhrases.getPhrasesId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basUsedPhrases);
            return false;
        }
    }

    public Json findAll(final int page, final int rows,
            final String sort, final String order,final int enterprise_id) throws Exception
    {
    	StringBuffer sb=new StringBuffer("from BasUsedPhrases bup where bup.enterpriseId="+enterprise_id);
    	if (sort != null && !sort.equals("") && order != null&& !order.equals("")){
    		sb.append(" order by " + sort + " " + order+ "");
        }
    	List<BasUsedPhrases> resultList=find(sb.toString(), page, rows);
    	if(resultList==null)
    		resultList=new ArrayList();
    	int count=getCount(sb.toString());
    	Json json=new Json();
    	json.setRows(resultList);
    	json.setTotal(count);
        return json;
    }

    
    public List<BasUsedPhrases> getTotle(BasUsedPhrases basUsedPhrases) throws Exception
    {
        String hql = "from BasUsedPhrases bup where bup.enterpriseId="+basUsedPhrases.getEnterpriseId();
        return super.find(hql);
    }

    
    public Json findByCondition(final BasUsedPhrases basUsedPhrases, final int page, final int rows)
            throws Exception{
    	StringBuffer sb=new StringBuffer("from BasUsedPhrases bup where bup.enterpriseId="+basUsedPhrases.getEnterpriseId());
    	if (basUsedPhrases.getPhrasesId() != null)
    		sb.append(" and phrasesId = "+ basUsedPhrases.getPhrasesId());
        if (basUsedPhrases.getPhrassesName() != null&& !basUsedPhrases.getPhrassesName().trim().equals(""))
            sb.append(" and phrassesName like '%"+ StringEscapeUtils.escapeSql(basUsedPhrases.getPhrassesName().trim()) + "%'");
        List<BasUsedPhrases> resultList=find(sb.toString(), page, rows);
        int count=getCount(sb.toString());
        Json json=new Json();
        json.setRows(resultList);
        json.setTotal(count);
        return json;
    }
}