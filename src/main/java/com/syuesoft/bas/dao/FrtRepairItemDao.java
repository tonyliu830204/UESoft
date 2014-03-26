package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.model.FrtRepairItem;
import com.syuesoft.util.ComboBoxJson;

public interface FrtRepairItemDao extends BaseDaoI<FrtRepairItem>
{
    public void Add(FrtRepairItem frtRepairItem) throws Exception;

    public void Delete(FrtRepairItem frtRepairItem) throws Exception;

    public void Update(FrtRepairItem frtRepairItem) throws Exception;

    public List<FrtRepairItem> findAll(int page, int rows, String sort,
            String order) throws Exception;

    public List<FrtRepairItem> getTotle() throws Exception;

    public List<FrtRepairItem> findByCondition(FrtRepairItemVo frtRepairItemVo)
            throws Exception;

    public List<ComboBoxJson> getBasWorkhourSort(String q,Integer id) throws Exception;// 获取工时分类用于combox
}
