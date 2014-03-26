package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipGiveIntegralProjectVO;
import com.syuesoft.model.BasVipGiveIntegralProject;

public interface BasVipGiveIntegralProjectDao extends
        BaseDaoI<BasVipGiveIntegralProject>
{
    public void add(BasVipGiveIntegralProject basVipActivitiesProject)
            throws Exception; // 添加赠送积分项目

    public void delte(BasVipGiveIntegralProject basVipActivitiesProject)
            throws Exception; // 删除赠送积分项目

    public void update(BasVipGiveIntegralProject basVipActivitiesProject)
            throws Exception; // 编辑赠送积分项目

    public List<BasVipGiveIntegralProjectVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception; // 获取所有赠送积分项目

    public int getTotle(int enterprise_id) throws Exception; // 获取所有记录数

    public BasVipGiveIntegralProject getById(int id) throws Exception; // 根据ID获取赠送积分项目

    public boolean getByName(
            BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception;
}
