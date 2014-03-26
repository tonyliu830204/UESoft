package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasUsedPhrases;
import com.syuesoft.util.Json;

public interface BasUsedPhrasesService
{
    public boolean add(BasUsedPhrases basUsedPhrases) throws Exception;

    public void delete(BasUsedPhrases basUsedPhrases) throws Exception;

    public boolean update(BasUsedPhrases basUsedPhrases) throws Exception;

    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception;

    public List<BasUsedPhrases> getTotle(BasUsedPhrases basUsedPhrases) throws Exception;

    public Json findByCondition(BasUsedPhrases basUsedPhrases,
            int page, int rows) throws Exception;
}
