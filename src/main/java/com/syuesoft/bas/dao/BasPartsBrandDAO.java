package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasPartsBrand;
import com.syuesoft.st.vo.BasPartsBrandVo;
import com.syuesoft.util.Json;

/**
 * 基础资料-->配件性质：配件品牌Dao接口
 * 
 * @author SuMing
 */
public interface BasPartsBrandDAO extends BaseDaoI<BasPartsBrand>
{

    /** 配件库存量信息 预加载 */
    public Json loadPartsBrand(final int page, final int rows,
            final String sort, final String order,final int enterprise_id) throws Exception;

    /** 配件库存量信息 综合查询 */
    public Json serachPartsBrandByCondition(final String pbrdId,
            final String pbrdName,final int enterprise_id) throws Exception;

    // 基础资料-->配件品牌 删除
    public void delete(BasPartsBrand persistentInstance);

    // 基础资料-->配件品牌 修改
    public BasPartsBrand merge(BasPartsBrand detachedInstance);

    // 基础资料-->配件品牌 通过配件名称查询
    @SuppressWarnings("unchecked")
    public List searchByPdIdAndPdName(final String pbrdId, final String pbrdName)
            throws Exception;

    // 基础资料-->配件品牌 通过配件ID查询
    public BasPartsBrand findById(java.lang.Short id);

    // 基础资料-->配件品牌 全部查询
    public List<BasPartsBrand> findAll(final BasPartsBrandVo bpbvo);

    // 基础资料-->配件品牌 分页
    public List<BasPartsBrand> getAllByPage(final BasPartsBrandVo baspartsbrandvo);

    public List<BasPartsBrand> findAll(final String param, final int page,
            final int rows) throws Exception;

}