package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasPartsReportpriceclassficationVo;

/**
 * 基础资料-->配件性质：配件报价分类Service接口
 * 
 * @author SuMing
 */
public interface BasPartsReportPriceClassficationService
{

    // 基础资料-->配件性质：配件报价分类 添加
    public void add(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception;

    // 基础资料-->配件性质：配件报价分类 删除
    public void delete(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception;

    // 基础资料-->配件性质：配件报价分类 修改
    public void update(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception;

    // 基础资料-->配件性质：配件报价分类 全部显示
    public java.util.List<BasPartsReportpriceclassficationVo> findAll(BasPartsReportpriceclassficationVo bcbvp)
            throws Exception;

    // 基础资料-->配件性质：配件报价分类 分页
    public java.util.List<BasPartsReportpriceclassficationVo> getAllByPage(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception;

}
