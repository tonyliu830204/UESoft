package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasScoreContentset;

public interface BasScoreContentsetDao extends BaseDaoI<BasScoreContentset>
{
    public void Add(BasScoreContentset basScoreContentset);

    public void Delete(BasScoreContentset basScoreContentset);

    public void Update(BasScoreContentset basScoreContentset);

    public List<BasScoreContentset> findAll(int page, int rows);

    public List<BasScoreContentset> getTotle();

    public List<BasScoreContentset> findByCondition(int page, int rows,
            BasScoreContentset basScoreContentset);
}
