package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipGiveProject;

public interface BasVipGiveProjectService
{
    public void add(BasVipGiveProject basVipGiveProject) throws Exception; // 添加会员赠送项目

    public void delete(BasVipGiveProject basVipGiveProject) throws Exception; // 删除
                                                                              // 会员赠送项目

    public void update(BasVipGiveProject basVipGiveProject) throws Exception; // 编辑会员赠送项目

    public List<BasVipGiveProject> findAll(int page, int rows, String order,
            String sort,int enterprise_id  ) throws Exception; // 分页查询赠送项目

    public int getTotle(int enterpriseId) throws Exception; // 获取总记录数

    public boolean getByName(BasVipGiveProject basVipGiveProject)
            throws Exception;
}
