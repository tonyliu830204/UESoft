package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipActivitiesProjectVO;
import com.syuesoft.model.BasVipActivitiesProject;

public interface BasVipActivitiesProjectDao extends
        BaseDaoI<BasVipActivitiesProject>
{
    public void add(BasVipActivitiesProject basVipActivitiesProject)
            throws Exception; // 添加活动折扣

    public void delte(BasVipActivitiesProject basVipActivitiesProject)
            throws Exception; // 删除活动折扣

    public void update(BasVipActivitiesProject basVipActivitiesProject)
            throws Exception; // 编辑活动折扣

    public List<BasVipActivitiesProjectVO> findAll(int page, int rows,
            String order, String sort,final int enterprise_id) throws Exception; // 获取所有活动折扣

    public int getTotle(int enterprise_id) throws Exception; // 获取所有记录数

    public BasVipActivitiesProject getById(int id) throws Exception; // 根据ID获取活动项目信息

    public boolean getByName(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception;
}
