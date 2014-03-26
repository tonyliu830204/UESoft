package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarPartsDAO;
import com.syuesoft.bas.service.BasCarPartsService;
import com.syuesoft.base.vo.BasCarPartsVo;
import com.syuesoft.model.BasCarParts;

/**
 * 基础资料-->车辆性质：车辆部位Service实现类
 * 
 * @author SuMing
 */
public class BasCarPartsServiceImpl extends BaseLogServiceImpl implements
        BasCarPartsService
{

    BasCarPartsDAO dao = null;

    public boolean isExist(BasCarPartsVo basCarPartsVo) throws Exception
    {
        String param = "FROM BasCarParts  bcp WHERE bcp.enterpriseId="+basCarPartsVo.getEnterpriseId();
        if (basCarPartsVo.getCarPartId() != null&& !"".equals(basCarPartsVo.getCarPartId()))
            param += " and bcp.carPartId!=" + basCarPartsVo.getCarPartId();
        if (basCarPartsVo.getCarPartName() != null&& !"".equals(basCarPartsVo.getCarPartName()))
            param += " and bcp.carPartName='" + basCarPartsVo.getCarPartName()+ "'";
        List<BasCarParts> list = this.dao.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：车辆部位 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增车辆部位", content = "基础资料-->新增车辆部位")
    public void add(BasCarPartsVo basCarPartsVo) throws Exception
    {
        BasCarParts bcp = new BasCarParts();
        bcp.setCarPartName(basCarPartsVo.getCarPartName());
        if (basCarPartsVo.getCarPartRemark() != null
                && !basCarPartsVo.getCarPartRemark().equals(""))
            bcp.setCarPartRemark(basCarPartsVo.getCarPartRemark());
        else
            bcp.setCarPartRemark("");
        bcp.setEnterpriseId(basCarPartsVo.getEnterpriseId());
        Serializable dd = dao.save(bcp);
        setContent("基础资料-->新增车辆部位,车辆部位编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：车辆部位 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除车辆部位", content = "基础资料-->删除车辆部位")
    public void delete(BasCarPartsVo basCarPartsVo) throws Exception
    {
        BasCarParts bcp =dao.get(BasCarParts.class, basCarPartsVo.getCarPartId());
        if(bcp!=null){
        	dao.delete(bcp);
            setContent("基础资料-->删除车辆部位,车辆部位编号:" + basCarPartsVo.getCarPartId());
        }
    }

    /**
     * 基础资料-->车辆性质：车辆部位 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改车辆部位", content = "基础资料-->修改车辆部位")
    public void update(BasCarPartsVo basCarPartsVo) throws Exception
    {
        BasCarParts bcp = dao.get(BasCarParts.class, basCarPartsVo.getCarPartId());
        if(bcp!=null){
        	bcp.setCarPartName(basCarPartsVo.getCarPartName());
            if (basCarPartsVo.getCarPartRemark() != null&& !basCarPartsVo.getCarPartRemark().equals(""))
                bcp.setCarPartRemark(basCarPartsVo.getCarPartRemark());
            dao.merge(bcp);
            setContent("基础资料-->修改车辆部位,车辆部位编号:" + basCarPartsVo.getCarPartId());
        }
    }

    /**
     * 基础资料-->车辆性质：车辆部位 全部显示
     */
    public List<BasCarPartsVo> findAll(BasCarPartsVo basCarPartsVo) throws Exception
    {
        List<BasCarPartsVo> list = new ArrayList<BasCarPartsVo>();
        List<BasCarParts> resultList = dao.findAll(basCarPartsVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarPartsVo bcb = new BasCarPartsVo();
                bcb.setCarPartId(resultList.get(i).getCarPartId());
                bcb.setCarPartRemark(resultList.get(i).getCarPartRemark());
                bcb.setCarPartName(resultList.get(i).getCarPartName());
                list.add(bcb);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：车辆部位 分页
     */
    public List<BasCarPartsVo> getAllByPage(BasCarPartsVo basCarPartsVo)
            throws Exception
    {
        List<BasCarPartsVo> list = new ArrayList<BasCarPartsVo>();
        List<BasCarParts> resultList = dao.getAllByPage(basCarPartsVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarPartsVo bcpvo = new BasCarPartsVo();
                bcpvo.setCarPartId(resultList.get(i).getCarPartId());
                bcpvo.setCarPartRemark(resultList.get(i).getCarPartRemark());
                bcpvo.setCarPartName(resultList.get(i).getCarPartName());
                list.add(bcpvo);
            }
        }
        return list;
    }

    public BasCarPartsDAO getDao()
    {
        return dao;
    }

    public void setDao(BasCarPartsDAO dao)
    {
        this.dao = dao;
    }
}
