package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasScoreContentset;

public interface BasScoreContentsetService
{
    public void add(BasScoreContentset basScoreContentset) throws Exception;

    public void delete(BasScoreContentset basScoreContentset) throws Exception;

    public void update(BasScoreContentset basScoreContentset) throws Exception;

    public List<BasScoreContentset> findAll(int page, int rows)
            throws Exception;

    public List<BasScoreContentset> getTotle() throws Exception;

    public List<BasScoreContentset> findByCondition(int page, int rows,
            BasScoreContentset basScoreContentset) throws Exception;
}
