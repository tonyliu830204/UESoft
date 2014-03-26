package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsReportPriceClassficationDAO;
import com.syuesoft.bas.service.BasPartsReportPriceClassficationService;
import com.syuesoft.base.vo.BasPartsReportpriceclassficationVo;
import com.syuesoft.model.BasPartsReportpriceclassfication;

/**
 * 基础资料-->配件性质：配件报价分类Service实现类
 * 
 * @author SuMing
 */
public class BasPartsReportPriceClassficationServiceImpl extends
        BaseLogServiceImpl implements BasPartsReportPriceClassficationService
{

    BasPartsReportPriceClassficationDAO dao = null;

    /**
     *基础资料-->配件性质：配件报价分类 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增配件报价分类", content = "基础资料-->新增配件报价分类")
    public void add(
            BasPartsReportpriceclassficationVo bp)
            throws Exception
    {
        BasPartsReportpriceclassfication bpbpc = new BasPartsReportpriceclassfication();
        if(bp.getPartsBrand()!=null&&!bp.getPartsBrand().equals(""))
        	bpbpc.setPartsBrand(bp.getPartsBrand());
        if(bp.getPartsInviteBid()!=null&&!bp.getPartsInviteBid().equals(""))	
        	bpbpc.setPartsInviteBid(bp.getPartsInviteBid());
        if(bp.getPartsName()!=null&&!bp.getPartsName().equals(""))
        	bpbpc.setPartsName(bp.getPartsName());
        if(bp.getPartsSuccessfulBid()!=null&&!bp.getPartsSuccessfulBid().equals(""))
        	bpbpc.setPartsSuccessfulBid(bp.getPartsSuccessfulBid());
        if(bp.getPartsType()!=null&&!bp.getPartsType().equals(""))
            bpbpc.setPartsType(bp.getPartsType());
        if(bp.getPartsUnitMeasurement()!=null&&!bp.getPartsUnitMeasurement().equals(""))
            bpbpc.setPartsUnitMeasurement(bp.getPartsUnitMeasurement());
        bpbpc.setEnterpriseId(bp.getEnterpriseId());
        Serializable bb = dao.save(bpbpc);
        setContent("基础资料-->新增配件报价分类,配件报价分类编号:" + bb);
    }

    /**
     * 基础资料-->配件性质：配件报价分类 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除配件报价分类", content = "基础资料-->删除配件报价分类")
    public void delete(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception
    {
        BasPartsReportpriceclassfication bpbpc = new BasPartsReportpriceclassfication();
        bpbpc.setPartsId(basPartsReportpriceclassficationVo.getPartsId());
        dao.delete(bpbpc);
        setContent("基础资料-->删除配件报价分类,配件报价分类编号:"
                + basPartsReportpriceclassficationVo.getPartsId());
    }

    /**
     * 基础资料-->配件性质：配件报价分类 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改配件报价分类", content = "基础资料-->修改配件报价分类")
    public void update(
            BasPartsReportpriceclassficationVo bp)
            throws Exception
    {
        BasPartsReportpriceclassfication bpbpc = dao.get(BasPartsReportpriceclassfication.class,bp.getPartsId());
        if(bpbpc!=null){
        	 if(bp.getPartsBrand()!=null&&!bp.getPartsBrand().equals(""))
             	bpbpc.setPartsBrand(bp.getPartsBrand());
             if(bp.getPartsInviteBid()!=null&&!bp.getPartsInviteBid().equals(""))	
             	bpbpc.setPartsInviteBid(bp.getPartsInviteBid());
             if(bp.getPartsName()!=null&&!bp.getPartsName().equals(""))
             	bpbpc.setPartsName(bp.getPartsName());
             if(bp.getPartsSuccessfulBid()!=null&&!bp.getPartsSuccessfulBid().equals(""))
             	bpbpc.setPartsSuccessfulBid(bp.getPartsSuccessfulBid());
             if(bp.getPartsType()!=null&&!bp.getPartsType().equals(""))
                 bpbpc.setPartsType(bp.getPartsType());
             if(bp.getPartsUnitMeasurement()!=null&&!bp.getPartsUnitMeasurement().equals(""))
                 bpbpc.setPartsUnitMeasurement(bp.getPartsUnitMeasurement());
             dao.merge(bpbpc);
             setContent("基础资料-->修改配件报价分类,配件报价分类编号:"+ bp.getPartsId());
        }
    }

    /**
     * 基础资料-->配件性质：配件报价分类 全部显示
     */
    public List<BasPartsReportpriceclassficationVo> findAll(BasPartsReportpriceclassficationVo bcbvp) throws Exception
    {
        List<BasPartsReportpriceclassficationVo> list = new ArrayList<BasPartsReportpriceclassficationVo>();
        List<BasPartsReportpriceclassfication> resultList = dao.findAll(bcbvp);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsReportpriceclassficationVo bp = new BasPartsReportpriceclassficationVo();
                bp.setPartsBrand(resultList.get(i).getPartsBrand());
                bp.setPartsId(resultList.get(i).getPartsId());
                bp.setPartsInviteBid(resultList.get(i).getPartsInviteBid());
                bp.setPartsName(resultList.get(i).getPartsName());
                bp.setPartsType(resultList.get(i).getPartsType());
                bp.setPartsUnitMeasurement(resultList.get(i).getPartsUnitMeasurement());
                list.add(bp);
            }
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：配件报价分类 分页
     */
    public List<BasPartsReportpriceclassficationVo> getAllByPage(
            BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
            throws Exception
    {
        List<BasPartsReportpriceclassficationVo> list = new ArrayList<BasPartsReportpriceclassficationVo>();
        List<BasPartsReportpriceclassfication> resultList = dao.getAllByPage(basPartsReportpriceclassficationVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo1 = new BasPartsReportpriceclassficationVo();
                basPartsReportpriceclassficationVo1.setPartsBrand(resultList
                        .get(i).getPartsBrand());
                basPartsReportpriceclassficationVo1.setPartsId(resultList
                        .get(i).getPartsId());
                basPartsReportpriceclassficationVo1
                        .setPartsInviteBid(resultList.get(i)
                                .getPartsInviteBid());
                basPartsReportpriceclassficationVo1.setPartsName(resultList
                        .get(i).getPartsName());
                basPartsReportpriceclassficationVo1.setPartsType(resultList
                        .get(i).getPartsType());
                basPartsReportpriceclassficationVo1
                        .setPartsUnitMeasurement(resultList.get(i)
                                .getPartsUnitMeasurement());
                list.add(basPartsReportpriceclassficationVo1);
            }
        }
        return list;
    }

    public BasPartsReportPriceClassficationDAO getDao()
    {
        return dao;
    }

    public void setDao(BasPartsReportPriceClassficationDAO dao)
    {
        this.dao = dao;
    }

}
