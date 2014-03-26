package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasPartsSellVo;
import com.syuesoft.model.BasPartsSell;

/**
 * 基础资料-->配件性质-->配件销售分类Dao接口
 * 
 * @author SuMing
 */
public interface BasPartsSellDAO extends BaseDaoI<BasPartsSell>
{

    // 基础资料-->配件销售分类 删除
    public void delete(BasPartsSell persistentInstance);

    // 基础资料-->配件销售分类 修改
    public BasPartsSell merge(BasPartsSell detachedInstance);

    // 基础资料-->配件销售分类 按ID查询
    public BasPartsSell findById(Short id);

    // 基础资料-->配件销售分类 全部查询
    public List<BasPartsSell> findAll(final BasPartsSellVo basPartsSellVo);

    // 基础资料-->配件销售分类 分页
    public List<BasPartsSell> getAllByPage(final BasPartsSellVo basPartsSellVo);

}