package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasVistContent;

public interface BasVistContentDao extends BaseDaoI<BasVistContent>
{

    public void Add(BasVistContent basVistContent);

    public void Delete(BasVistContent basVistContent);

    public void Update(BasVistContent basVistContent);

    public List<BasVistContent> findAll(int page, int rows);

    public List<BasVistContent> getTotle();

    public List<BasVistContent> findByCondition(int page, int rows,
            BasVistContent basVistContent);
}
