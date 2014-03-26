package com.syuesoft.bas.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipActivitiesDiscountDao;
import com.syuesoft.bas.service.BasVipActivitiesDiscountService;
import com.syuesoft.model.BasVipActivitiesDiscount;

/**
 * 活动名称
 */
@Service("basVipActivitiesDiscountService")
public class BasVipActivitiesDiscountServiceImpl extends BaseLogServiceImpl
        implements BasVipActivitiesDiscountService
{
    @Autowired
    private BasVipActivitiesDiscountDao basVipActivitiesDiscountDao;

    
    @Log(moduleName = "基础资料", opertype = "新增活动名称", content = "基础资料-->新增活动名称")
    public void add(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception
    {
        if (basVipActivitiesDiscount.getWorkDis() == null)
        {
            basVipActivitiesDiscount.setWorkDis(100);
        }
        if (basVipActivitiesDiscount.getMaterialDis() == null)
        {
            basVipActivitiesDiscount.setMaterialDis(100);
        }
        if (basVipActivitiesDiscount.getTotalDis() == null)
        {
            basVipActivitiesDiscount.setTotalDis(100);
        }
        basVipActivitiesDiscountDao.add(basVipActivitiesDiscount);
        setContent("基础资料-->新增动名称,动名称名称:"
                + basVipActivitiesDiscount.getVipActDisNane());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除活动名称", content = "基础资料-->删除活动名称")
    public void delete(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception
    {
    	BasVipActivitiesDiscount bva=basVipActivitiesDiscountDao.get(BasVipActivitiesDiscount.class, basVipActivitiesDiscount.getVipActDisId());
        if(bva!=null&&!bva.equals("")){
        	basVipActivitiesDiscountDao.delte(bva);
            setContent("基础资料-->删除动名称,动名称名称:"+ bva.getVipActDisNane());
        }
    }

    
    public List<BasVipActivitiesDiscount> findAll(int page, int rows,
            String order, String sort,int enterprise_id ) throws Exception
    {
        return basVipActivitiesDiscountDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    @Log(moduleName = "基础资料", opertype = "更新活动名称", content = "基础资料-->更新活动名称")
    public void update(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception
    {
        basVipActivitiesDiscountDao.update(basVipActivitiesDiscount);
        setContent("基础资料-->更新动名称,动名称名称:"
                + basVipActivitiesDiscount.getVipActDisNane());
    }

    
    public int getTotle(int enterprise_id ) throws Exception
    {
        return basVipActivitiesDiscountDao.getTotle(enterprise_id);
    }

    
    public boolean getByName(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception
    {
        return basVipActivitiesDiscountDao.getByName(basVipActivitiesDiscount);
    }
}
