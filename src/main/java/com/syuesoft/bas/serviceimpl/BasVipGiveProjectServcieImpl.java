package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipGiveProjectDao;
import com.syuesoft.bas.service.BasVipGiveProjectService;
import com.syuesoft.model.BasVipGiveProject;

@Service("basVipGiveProjectServcie")
/**
 * 会员赠送项目
 * @author mulangtao
 * */
public class BasVipGiveProjectServcieImpl extends BaseLogServiceImpl implements
        BasVipGiveProjectService
{
    @Autowired
    BasVipGiveProjectDao basVipGiveProjectDao;

    /**
     * 添加会员这送项目
     * 
     * @basVipGiveProject 会员赠送项目实体
     * */
    
    @Log(moduleName = "基础资料", opertype = "新增会员赠送项目", content = "基础资料-->新增会员赠送项目")
    public void add(BasVipGiveProject basVipGiveProject) throws Exception
    {
        basVipGiveProjectDao.add(basVipGiveProject);
        setContent("基础资料-->新增会员赠送项目,会员赠送项目名称:"
                + basVipGiveProject.getVipGpName());
    }

    /**
     * 删除会员赠送项目
     * 
     * @basVipGiveProject 会员赠送项目实体
     * */
    
    @Log(moduleName = "基础资料", opertype = "删除会员赠送项目", content = "基础资料-->删除会员赠送项目")
    public void delete(BasVipGiveProject basVipGiveProject) throws Exception
    {
        basVipGiveProjectDao.delete(basVipGiveProject);
        setContent("基础资料-->删除会员赠送项目,会员赠送项目名称:"
                + basVipGiveProject.getVipGpName());
    }

    /**
     * 分页查询所有会员赠送项目
     * 
     * @page 当前页
     * @rows 每页显示记录数
     * @order 分页规则
     * @sort 分页字段
     * */
    
    public List<BasVipGiveProject> findAll(int page, int rows, String order,
            String sort,int enterprise_id  ) throws Exception
    {
        return basVipGiveProjectDao.findAll(page, rows, order, sort,enterprise_id);
    }

    /**
     * 获取所有记录数
     * */
    
    public int getTotle(int enterprise_id ) throws Exception
    {
        return basVipGiveProjectDao.getTotle(enterprise_id);
    }

    /**
     * 编辑会员信息
     * 
     * @basVipGiveProject 会员赠送项目实体
     * */
    
    @Log(moduleName = "基础资料", opertype = "修改会员赠送项目", content = "基础资料-->修改会员赠送项目")
    public void update(BasVipGiveProject basVipGiveProject) throws Exception
    {
        basVipGiveProjectDao.update(basVipGiveProject);
        setContent("基础资料-->修改会员赠送项目,会员赠送项目名称:"
                + basVipGiveProject.getVipGpName());
    }

    /**
     * 根据会员赠送项目名称获取会员赠送项目
     * */
    
    public boolean getByName(BasVipGiveProject basVipGiveProject)
            throws Exception
    {
        return basVipGiveProjectDao.getByName(basVipGiveProject);
    }

}
