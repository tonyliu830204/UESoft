package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipGiveIntegralProjectVO;
import com.syuesoft.model.BasVipGiveIntegralProject;

public interface BasVipGiveIntegralProjectService
{
    public void add(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception; // 添加赠送积分项目

    public void delete(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception; // 删除赠送积分项目

    public void update(BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception; // 编辑赠送积分项目

    public List<BasVipGiveIntegralProjectVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id  ) throws Exception; // 获取所有赠送积分项目

    public int getTotle(int enterprise_id  ) throws Exception; // 获取所有记录数

    public BasVipGiveIntegralProject getById(int id) throws Exception; // 根据ID获取赠送积分项目

    public boolean getByName(
            BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception;
}
