package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.ReptypeDao;
import com.syuesoft.bas.service.ReptypeService;
import com.syuesoft.model.Reptype;

@Service("reptypeService")
public class ReptypeServiceImpl implements ReptypeService
{
    @Autowired
    private ReptypeDao reptypeDao;

    
    public boolean add(Reptype reptype) throws Exception
    {
    	Reptype r=reptypeDao.get("from Reptype where reptName='" + reptype.getReptName()+"' and enterpriseId="+reptype.getEnterpriseId());
    	if(r!=null)
    		return true;
    	if(r==null){
    		r=new Reptype();
			if(reptype.getReptName()!=null&&!reptype.getReptName().trim().equals(""))
				r.setReptName(reptype.getReptName().trim());
			if(reptype.getWorkCreditsRate()!=null)	
				r.setWorkCreditsRate(reptype.getWorkCreditsRate());
			if(reptype.getPartCreditsRate()!=null)
				r.setPartCreditsRate(reptype.getPartCreditsRate());
			if(reptype.getSumCreditsRate()!=null)
				r.setSumCreditsRate(reptype.getSumCreditsRate());
			if(reptype.getReptClass()!=null)
				r.setReptClass(reptype.getReptClass());
			if(reptype.getMemo()!=null&&!reptype.getMemo().trim().equals(""))
		        r.setMemo(reptype.getMemo());
			r.setEnterpriseId(reptype.getEnterpriseId());
			reptypeDao.save(r);
    	}
        return false;
    }

    
    public void delete(Reptype reptype) throws Exception
    {
    	Reptype r=reptypeDao.get(Reptype.class, reptype.getReptId());
        if(r!=null){
        	reptypeDao.delete(r);
        }
    }

    
    public void update(Reptype reptype) throws Exception
    {
    	Reptype r=reptypeDao.get(Reptype.class, reptype.getReptId());
    	if(r!=null){
			if(reptype.getReptName()!=null&&!reptype.getReptName().trim().equals(""))
				r.setReptName(reptype.getReptName().trim());
			if(reptype.getWorkCreditsRate()!=null)	
				r.setWorkCreditsRate(reptype.getWorkCreditsRate());
			if(reptype.getPartCreditsRate()!=null)
				r.setPartCreditsRate(reptype.getPartCreditsRate());
			if(reptype.getSumCreditsRate()!=null)
				r.setSumCreditsRate(reptype.getSumCreditsRate());
			if(reptype.getReptClass()!=null)
				r.setReptClass(reptype.getReptClass());
			if(reptype.getMemo()!=null&&!reptype.getMemo().trim().equals(""))
		        r.setMemo(reptype.getMemo());
			reptypeDao.merge(r);
    	}
    }

    
    public List<Reptype> findAll(int page, int rows, String sort, String order,int enterpriseId)
            throws Exception
    {
        return reptypeDao.findAll(page, rows, sort, order,enterpriseId);
    }

    
    public List<Reptype> findByCondition(int page, int rows, Reptype Reptype)
            throws Exception
    {
        return reptypeDao.findByCondition(page, rows, Reptype);
    }

}
