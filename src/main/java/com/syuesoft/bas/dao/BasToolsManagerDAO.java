package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.ToolsManagerVo;
import com.syuesoft.model.BasToolsManager;

public interface BasToolsManagerDAO extends BaseDaoI<BasToolsManager>
{

    public void delete(Short id);

    public void update(BasToolsManager btm);

    public List<ToolsManagerVo> findAll(String param);

    public List<ToolsManagerVo> findAll(String param, int page, int rows);
}
