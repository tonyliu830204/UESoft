package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasScoreContentsetDao;
import com.syuesoft.bas.service.BasScoreContentsetService;
import com.syuesoft.model.BasScoreContentset;

/**
 * 积分赠送内容设定
 * 
 * @author HeXin
 * 
 */
public class BasScoreContentsetServiceImpl extends BaseLogServiceImpl implements
        BasScoreContentsetService
{
    private BasScoreContentsetDao dao;

    public BasScoreContentsetDao getDao()
    {
        return dao;
    }

    public void setDao(BasScoreContentsetDao dao)
    {
        this.dao = dao;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增积分赠送内容设定", content = "基础资料-->新增积分赠送内容设定")
    public void add(BasScoreContentset basScoreContentset)
    {
        dao.Add(basScoreContentset);
        setContent("基础资料-->新增积分赠送内容设定,积分赠送内容设定名称:"
                + basScoreContentset.getScoreContentsetName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除积分赠送内容设定", content = "基础资料-->删除积分赠送内容设定")
    public void delete(BasScoreContentset basScoreContentset)
    {
        dao.Delete(basScoreContentset);
        setContent("基础资料-->删除积分赠送内容设定,积分赠送内容设定名称:"
                + basScoreContentset.getScoreContentsetName());
    }

    
    @Log(moduleName = "基础资料", opertype = "修改积分赠送内容设定", content = "基础资料-->修改积分赠送内容设定")
    public void update(BasScoreContentset basScoreContentset)
    {
        dao.Update(basScoreContentset);
        setContent("基础资料-->修改积分赠送内容设定,积分赠送内容设定名称:"
                + basScoreContentset.getScoreContentsetName());
    }

    
    public List<BasScoreContentset> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasScoreContentset> findByCondition(int page, int rows,
            BasScoreContentset basScoreContentset)
    {
        return dao.findByCondition(page, rows, basScoreContentset);
    }

    
    public List<BasScoreContentset> getTotle()
    {
        return dao.getTotle();
    }

}
