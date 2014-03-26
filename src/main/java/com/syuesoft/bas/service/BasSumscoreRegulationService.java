package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasSumscoreRegulation;

public interface BasSumscoreRegulationService
{
    public void add(BasSumscoreRegulation basSumscoreRegulation)
            throws Exception;

    public void delete(BasSumscoreRegulation basSumscoreRegulation)
            throws Exception;

    public void update(BasSumscoreRegulation basSumscoreRegulation)
            throws Exception;

    public List<BasSumscoreRegulation> findAll(int page, int rows)
            throws Exception;

    public List<BasSumscoreRegulation> getTotle() throws Exception;

    public List<BasSumscoreRegulation> findByCondition(int page, int rows,
            BasSumscoreRegulation basSumscoreRegulation) throws Exception;
}
