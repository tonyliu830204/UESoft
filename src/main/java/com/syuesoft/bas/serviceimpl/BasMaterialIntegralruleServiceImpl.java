package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasMaterialIntegralruleDao;
import com.syuesoft.bas.service.BasMaterialIntegralruleService;
import com.syuesoft.model.BasMaterialIntegralrule;

/**
 * 材料积分规则
 * 
 * @author HeXin
 * 
 */
@Service("basMaterialIntegralruleService")
public class BasMaterialIntegralruleServiceImpl extends BaseLogServiceImpl
        implements BasMaterialIntegralruleService
{
    @Autowired
    private BasMaterialIntegralruleDao basMaterialIntegralruleDao;

    public BasMaterialIntegralruleDao getbasMaterialIntegralruleDao()
    {
        return basMaterialIntegralruleDao;
    }

    public void setbasMaterialIntegralruleDao(
            BasMaterialIntegralruleDao basMaterialIntegralruleDao)
    {
        this.basMaterialIntegralruleDao = basMaterialIntegralruleDao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增材料积分规则", content = "基础资料-->新增材料积分规则")
    public boolean add(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception
    {
        boolean tag = basMaterialIntegralruleDao.Add(basMaterialIntegralrule);
        setContent("基础资料-->新增材料积分规则,材料积分规则名称:"
                + basMaterialIntegralrule.getMaterialIntegralruleManner());
        return tag;
    }

    
    @Log(moduleName = "基础资料", opertype = "删除材料积分规则", content = "基础资料-->删除材料积分规则")
    public void delete(BasMaterialIntegralrule basMaterialIntegralrule)
    {
        basMaterialIntegralruleDao.Delete(basMaterialIntegralrule);
        setContent("基础资料-->删除材料积分规则,材料积分规则名称:"
                + basMaterialIntegralrule.getMaterialIntegralruleManner());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新材料积分规则", content = "基础资料-->更新材料积分规则")
    public void update(BasMaterialIntegralrule basMaterialIntegralrule)
    {
        basMaterialIntegralruleDao.Update(basMaterialIntegralrule);
        setContent("基础资料-->更新材料积分规则,材料积分规则名称:"
                + basMaterialIntegralrule.getMaterialIntegralruleManner());
    }

    
    public List<BasMaterialIntegralrule> findAll(int page, int rows)
    {
        return basMaterialIntegralruleDao.findAll(page, rows);
    }

    
    public List<BasMaterialIntegralrule> findByCondition(int page, int rows,
            BasMaterialIntegralrule basMaterialIntegralrule)
    {
        return basMaterialIntegralruleDao.findByCondition(page, rows,
                basMaterialIntegralrule);
    }

    
    public List<BasMaterialIntegralrule> getTotle()
    {
        return basMaterialIntegralruleDao.getTotle();
    }

}
