package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasPartsRate;

/**
 * 基础资料-->配件性质-->配件加价率Dao接口
 * 
 * @author SuMing
 */
public interface BasPartsRateDAO extends BaseDaoI<BasPartsRate>
{

    // 基础资料-->配件加价率 删除
    public void delete(BasPartsRate persistentInstance);

    // 基础资料-->配件加价率 修改
    public BasPartsRate merge(BasPartsRate detachedInstance);

    // 基础资料-->配件加价率 按ID查询
    public BasPartsRate findById(java.lang.Short id);

    // 基础资料-->配件加价率 全部查询
    public List<BasPartsRate> findAll();

    // 基础资料-->配件加价率 分页
    public List<BasPartsRate> getAllByPage(final int page, final int rows,
            String sort, String order);

}