package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasCustomType;
import com.syuesoft.model.BasJudgmentWord;

public interface BasJudgmentWordDao extends BaseDaoI<BasJudgmentWord>
{
    public void Add(BasJudgmentWord basJudgmentWord);

    public void Delete(BasJudgmentWord basJudgmentWord);

    public void Update(BasJudgmentWord basJudgmentWord);

    public List<BasJudgmentWord> findAll(int page, int rows);

    public List<BasJudgmentWord> getTotle();

    public List<BasJudgmentWord> findByCondition(int page, int rows,
            BasJudgmentWord basJudgmentWord);
}
