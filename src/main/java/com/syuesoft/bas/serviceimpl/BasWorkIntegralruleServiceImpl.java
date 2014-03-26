package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasWorkIntegralruleDao;
import com.syuesoft.bas.service.BasWorkIntegralruleService;
import com.syuesoft.model.BasWorkIntegralrule;

/**
 * 工时积分规则
 * 
 * @author HeXin
 * 
 */
public class BasWorkIntegralruleServiceImpl extends BaseLogServiceImpl
        implements BasWorkIntegralruleService
{
    private BasWorkIntegralruleDao dao;

    public BasWorkIntegralruleDao getDao()
    {
        return dao;
    }

    public void setDao(BasWorkIntegralruleDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增工时积分规则", content = "基础资料-->新增工时积分规则")
    public void add(BasWorkIntegralrule basWorkIntegralrule)
    {
        dao.Add(basWorkIntegralrule);
        setContent("基础资料-->新增工时积分规则,会员工时积分规则:"
                + basWorkIntegralrule.getWorkIntegralruleManner());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除工时积分规则", content = "基础资料-->删除工时积分规则")
    public void delete(BasWorkIntegralrule basWorkIntegralrule)
    {
        dao.Delete(basWorkIntegralrule);
        setContent("基础资料-->删除工时积分规则,会员工时积分规则:"
                + basWorkIntegralrule.getWorkIntegralruleManner());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新工时积分规则", content = "基础资料-->更新工时积分规则")
    public void update(BasWorkIntegralrule basWorkIntegralrule)
    {
        dao.Update(basWorkIntegralrule);
        setContent("基础资料-->更新工时积分规则,会员工时积分规则:"
                + basWorkIntegralrule.getWorkIntegralruleManner());
    }

    
    public List<BasWorkIntegralrule> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasWorkIntegralrule> findByCondition(int page, int rows,
            BasWorkIntegralrule basWorkIntegralrule)
    {
        return dao.findByCondition(page, rows, basWorkIntegralrule);
    }

    
    public List<BasWorkIntegralrule> getTotle()
    {
        return dao.getTotle();
    }

}
