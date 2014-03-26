package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasPartsReportpriceclassficationVo;
import com.syuesoft.model.BasPartsReportpriceclassfication;
/**
 * 基础资料-->配件性质-->配件报价分类Dao接口
 * @author SuMing
 */
public interface BasPartsReportPriceClassficationDAO extends
        BaseDaoI<BasPartsReportpriceclassfication>
{

    // 基础资料-->配件报价分类 删除
    public void delete(BasPartsReportpriceclassfication persistentInstance);

    // 基础资料-->配件报价分类 修改
    public BasPartsReportpriceclassfication merge(
            BasPartsReportpriceclassfication detachedInstance);

    // 基础资料-->配件报价分类 按ID查询
    public BasPartsReportpriceclassfication findById(java.lang.Short id);

    // 基础资料-->配件报价分类 全部查询
    public List<BasPartsReportpriceclassfication> findAll(final BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo);

    // 基础资料-->配件报价分类 分页
    public List<BasPartsReportpriceclassfication> getAllByPage(final BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo);

}