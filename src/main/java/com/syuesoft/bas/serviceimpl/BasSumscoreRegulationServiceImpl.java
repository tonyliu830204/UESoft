package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasSumscoreRegulationDao;
import com.syuesoft.bas.service.BasSumscoreRegulationService;
import com.syuesoft.model.BasSumscoreRegulation;

public class BasSumscoreRegulationServiceImpl extends BaseLogServiceImpl
        implements BasSumscoreRegulationService
{
    private BasSumscoreRegulationDao dao;

    public BasSumscoreRegulationDao getDao()
    {
        return dao;
    }

    public void setDao(BasSumscoreRegulationDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增积分规则", content = "基础资料-->新增积分规则")
    public void add(BasSumscoreRegulation basSumscoreRegulation)
    {
        dao.Add(basSumscoreRegulation);
        setContent("基础资料-->新增积分规则,积分规则名称:"
                + basSumscoreRegulation.getSumscoreRegulationManner());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除积分规则", content = "基础资料-->删除积分规则")
    public void delete(BasSumscoreRegulation basSumscoreRegulation)
    {
        dao.Delete(basSumscoreRegulation);
        setContent("基础资料-->删除积分规则,积分规则名称:"
                + basSumscoreRegulation.getSumscoreRegulationManner());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新积分规则", content = "基础资料-->更新积分规则")
    public void update(BasSumscoreRegulation basSumscoreRegulation)
    {
        dao.Update(basSumscoreRegulation);
        setContent("基础资料-->更新积分规则,积分规则名称:"
                + basSumscoreRegulation.getSumscoreRegulationManner());
    }

    
    public List<BasSumscoreRegulation> findAll(int page, int rows)
    {

        return dao.findAll(page, rows);
    }

    
    public List<BasSumscoreRegulation> findByCondition(int page, int rows,
            BasSumscoreRegulation basSumscoreRegulation)
    {

        return dao.findByCondition(page, rows, basSumscoreRegulation);
    }

    
    public List<BasSumscoreRegulation> getTotle()
    {

        return dao.getTotle();
    }

}
