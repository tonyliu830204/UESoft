package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasSumscoreRegulation;

public interface BasSumscoreRegulationDao extends
        BaseDaoI<BasSumscoreRegulation>
{
    public void Add(BasSumscoreRegulation basSumscoreRegulation);

    public void Delete(BasSumscoreRegulation basSumscoreRegulation);

    public void Update(BasSumscoreRegulation basSumscoreRegulation);

    public List<BasSumscoreRegulation> findAll(int page, int rows);

    public List<BasSumscoreRegulation> getTotle();

    public List<BasSumscoreRegulation> findByCondition(int page, int rows,
            BasSumscoreRegulation basSumscoreRegulation);
}
