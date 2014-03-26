package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasJudgmentWord;

public interface BasJudgmentWordService
{
    public void add(BasJudgmentWord basJudgmentWord) throws Exception;

    public void delete(BasJudgmentWord basJudgmentWord) throws Exception;

    public void update(BasJudgmentWord basJudgmentWord) throws Exception;

    public List<BasJudgmentWord> findAll(int page, int rows) throws Exception;

    public List<BasJudgmentWord> getTotle() throws Exception;

    public List<BasJudgmentWord> findByCondition(int page, int rows,
            BasJudgmentWord basJudgmentWord) throws Exception;
}
