package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasJudgmentWordDao;
import com.syuesoft.bas.service.BasJudgmentWordService;
import com.syuesoft.model.BasJudgmentWord;

public class BasJudgmentWordServiceImpl extends BaseLogServiceImpl implements
        BasJudgmentWordService
{
    public BasJudgmentWordDao getDao()
    {
        return dao;
    }

    public void setDao(BasJudgmentWordDao dao)
    {
        this.dao = dao;
    }

    private BasJudgmentWordDao dao;

    
    @Log(moduleName = "基础资料", opertype = "新增保养词信息", content = "基础资料-->新增保养词信息")
    public void add(BasJudgmentWord basJudgmentWord)
    {
        dao.Add(basJudgmentWord);
        setContent("基础资料-->新增保养词信息 ,保养词信息名称:" + basJudgmentWord.getWordName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除保养词信息", content = "基础资料-->删除保养词信息")
    public void delete(BasJudgmentWord basJudgmentWord)
    {
        dao.Delete(basJudgmentWord);
        setContent("基础资料-->删除保养词信息 ,保养词信息名称:" + basJudgmentWord.getWordName());
    }

    
    @Log(moduleName = "基础资料", opertype = "更新保养词信息", content = "基础资料-->更新保养词信息")
    public void update(BasJudgmentWord basJudgmentWord)
    {
        dao.Update(basJudgmentWord);
        setContent("基础资料-->更新保养词信息 ,保养词信息名称:" + basJudgmentWord.getWordName());
    }

    
    public List<BasJudgmentWord> findAll(int page, int rows)
    {
        return dao.findAll(page, rows);
    }

    
    public List<BasJudgmentWord> findByCondition(int page, int rows,
            BasJudgmentWord basJudgmentWord)
    {
        return dao.findByCondition(page, rows, basJudgmentWord);
    }

    
    public List<BasJudgmentWord> getTotle()
    {
        return dao.getTotle();
    }

}
