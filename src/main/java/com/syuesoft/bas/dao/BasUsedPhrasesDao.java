package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasUsedPhrases;
import com.syuesoft.util.Json;

public interface BasUsedPhrasesDao extends BaseDaoI<BasUsedPhrases>
{
    public boolean Add(BasUsedPhrases basUsedPhrases) throws Exception;

    public void Delete(BasUsedPhrases basUsedPhrases) throws Exception;

    public boolean Update(BasUsedPhrases basUsedPhrases) throws Exception;

    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception;

    public List<BasUsedPhrases> getTotle(BasUsedPhrases basUsedPhrases) throws Exception;

    public Json findByCondition(BasUsedPhrases basUsedPhrases,
            int page, int rows) throws Exception;

}
