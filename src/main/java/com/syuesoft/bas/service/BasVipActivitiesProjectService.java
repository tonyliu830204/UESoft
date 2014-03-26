package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipActivitiesProjectVO;
import com.syuesoft.model.BasVipActivitiesProject;

public interface BasVipActivitiesProjectService
{
    public void add(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception; // 添加活动折扣

    public void delete(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception; // 删除活动折扣

    public void update(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception; // 编辑活动折扣

    public List<BasVipActivitiesProjectVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception; // 获取所有活动折扣

    public int getTotle(int enterprise_id) throws Exception; // 获取所有记录数

    public boolean getByName(BasVipActivitiesProjectVO basVipActivitiesProjectVO)
            throws Exception;
}
