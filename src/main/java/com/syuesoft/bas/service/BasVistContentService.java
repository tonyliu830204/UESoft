package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVistContent;

public interface BasVistContentService
{
    public void add(BasVistContent basVistContent) throws Exception;

    public void delete(BasVistContent basVistContent) throws Exception;

    public void update(BasVistContent basVistContent) throws Exception;

    public List<BasVistContent> findAll(int page, int rows) throws Exception;

    public List<BasVistContent> getTotle() throws Exception;

    public List<BasVistContent> findByCondition(int page, int rows,
            BasVistContent basVistContent) throws Exception;
}
