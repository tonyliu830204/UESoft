package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipmaterialDiscountDao;
import com.syuesoft.bas.service.BasVipmaterialDiscountService;
import com.syuesoft.model.BasVipmaterialDiscount;

/**
 * 会员材料折扣
 * 
 * @author HeXin
 * 
 */
public class BasVipmaterialDiscountServiceImpl extends BaseLogServiceImpl
        implements BasVipmaterialDiscountService
{
    private BasVipmaterialDiscountDao dao;

    public BasVipmaterialDiscountDao getDao()
    {
        return dao;
    }

    public void setDao(BasVipmaterialDiscountDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增会员材料折扣", content = "基础资料-->新增会员材料折扣")
    public void add(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        dao.Add(basVipmaterialDiscount);
        setContent("基础资料-->新增会员材料折扣,会员材料折扣名称:"
                + basVipmaterialDiscount.getDiscountRate());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除会员材料折扣", content = "基础资料-->删除会员材料折扣")
    public void delete(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        dao.Delete(basVipmaterialDiscount);
        setContent("基础资料-->删除会员材料折扣,会员材料折扣名称:"
                + basVipmaterialDiscount.getDiscountRate());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新会员材料折扣", content = "基础资料-->更新会员材料折扣")
    public void update(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        dao.Update(basVipmaterialDiscount);
        setContent("基础资料-->更新会员材料折扣,会员材料折扣名称:"
                + basVipmaterialDiscount.getDiscountRate());
    }

    
    public List<BasVipmaterialDiscount> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasVipmaterialDiscount> findByCondition(int page, int rows,
            BasVipmaterialDiscount basVipmaterialDiscount)
    {
        return dao.findByCondition(page, rows, basVipmaterialDiscount);
    }

    
    public List<BasVipmaterialDiscount> getTotle()
    {
        return dao.getTotle();
    }

}
