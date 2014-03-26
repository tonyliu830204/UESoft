package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.VipServiceProjectDao;
import com.syuesoft.bas.service.VipServiceProjectService;
import com.syuesoft.base.vo.VipServiceProjectVo;
import com.syuesoft.model.VipMeal;
import com.syuesoft.model.VipMealD;
import com.syuesoft.util.Json;

@Service("vipServiceProjectService")
public class VipServiceProjectServiceImpl implements VipServiceProjectService
{

    @Autowired
    private VipServiceProjectDao vipServiceProjectDao;

    public VipServiceProjectDao getVipServiceProjectDao()
    {
        return vipServiceProjectDao;
    }

    public void setVipServiceProjectDao(
            VipServiceProjectDao vipServiceProjectDao)
    {
        this.vipServiceProjectDao = vipServiceProjectDao;
    }

    
    public void doAdd(VipServiceProjectVo vipServiceProjectVo) throws Exception
    {
        vipServiceProjectDao.doAdd(vipServiceProjectVo);

    }

    
    public void doDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception
    {
        vipServiceProjectDao.doDelete(vipServiceProjectVo);

    }

    
    public Json doFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception
    {
        return vipServiceProjectDao.doFind(vipServiceProjectVo);
    }

    
    public void doUpdate(VipServiceProjectVo vipServiceProjectVo)
            throws Exception{
    	VipMeal vip=vipServiceProjectDao.get(VipMeal.class, Integer.parseInt(vipServiceProjectVo.getMeal_Id()));
    	if(vipServiceProjectVo.getMeal_Name()!=null&&!vipServiceProjectVo.getMeal_Name().trim().equals(""))
    		vip.setMealName(vipServiceProjectVo.getMeal_Name().trim());
    	if(vipServiceProjectVo.getNote()!=null)
    		vip.setNote(vipServiceProjectVo.getNote().trim());
    	vip.setEnterpriseId(vipServiceProjectVo.getEnterpriseId());
        vipServiceProjectDao.merge(vip);

    }

    
    public void doDetailAdd(VipServiceProjectVo vipServiceProjectVo)
            throws Exception
    {
        if(vipServiceProjectVo.getMeal_Id()!=null&&!vipServiceProjectVo.getMeal_Id().equals("")){
            VipMeal vm=vipServiceProjectDao.get(" from VipMeal vm  where vm.mealId="+vipServiceProjectVo.getMeal_Id());
            if( vm != null){
                VipMealD vmp=new VipMealD();
                vmp.setMealContext(vipServiceProjectVo.getMeal_Context());
                vmp.setMemo(vipServiceProjectVo.getMemo());
                vmp.setVipMeal(vm);
                vm.getVipMealDs().add(vmp);
                vipServiceProjectDao.save(vm);
            }
        }
    }

    
    public void doDetailDelete(VipServiceProjectVo vipServiceProjectVo)
            throws Exception
    {
        vipServiceProjectDao.doDitailDelete(vipServiceProjectVo);

    }

    
    public Json doDetailFind(
            VipServiceProjectVo vipServiceProjectVo) throws Exception
    {
        return vipServiceProjectDao.doDitailFind(vipServiceProjectVo);
    }

    
    public void doDetailUpdate(VipServiceProjectVo vipServiceProjectVo)
            throws Exception
    {
        vipServiceProjectDao.doDitailUpdate(vipServiceProjectVo);

    }

    
    public List<VipMeal> getMealName(int enterprise_id) throws Exception
    {

        return vipServiceProjectDao.getMealName(enterprise_id);
    }

}
