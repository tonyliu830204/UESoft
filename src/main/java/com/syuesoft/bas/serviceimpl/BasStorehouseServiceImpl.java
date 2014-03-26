package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.service.BasStorehouseService;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.model.BasStorehouse;
import com.syuesoft.bas.dao.BasStorehouseDAO;

/**
 * 基础资料-->配件性质：仓别分类Service实现类
 * 
 * @author SuMing
 */
public class BasStorehouseServiceImpl extends BaseLogServiceImpl implements
        BasStorehouseService
{

    private BasStorehouseDAO dao = null;

    public boolean isExist(BasStorehouseVo basStorehouseVo) throws Exception
    {
        String param = "";
        if (basStorehouseVo.getStoreId() != null
                && !"".equals(basStorehouseVo.getStoreId()))
            param += " and bsh.storeId!=" + basStorehouseVo.getStoreId();
        if (basStorehouseVo.getStoreName() != null
                && !"".equals(basStorehouseVo.getStoreName()))
            param += " and bsh.storeName='" + basStorehouseVo.getStoreName()
                    + "'";
        List<BasStorehouse> list = dao.find("FROM BasStorehouse bsh WHERE bsh.enterpriseId="+basStorehouseVo.getEnterpriseId()+" "
                + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean isUsed(BasStorehouseVo basStorehouseVo) throws Exception
    {
        List resultList = dao.isUsed(basStorehouseVo);
        if (resultList.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->配件性质：仓别分类 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增仓别分类", content = "基础资料-->新增仓别分类")
    public void add(BasStorehouseVo basStorehouseVo) throws Exception
    {
        BasStorehouse bsh = new BasStorehouse();
        bsh.setStoreName(basStorehouseVo.getStoreName());
        if (basStorehouseVo.getRemark() != null
                && !basStorehouseVo.getRemark().equals(""))
            bsh.setRemark(basStorehouseVo.getRemark());
        else
            bsh.setRemark("");
        bsh.setEnterpriseId(basStorehouseVo.getEnterpriseId());
        Serializable s = dao.save(bsh);
        setContent("基础资料-->新增仓别分类,仓别分类编号:" + s);
    }

    /**
     * 基础资料-->配件性质：仓别分类 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除仓别分类", content = "基础资料-->删除仓别分类")
    public void delete(BasStorehouseVo basStorehouseVo) throws Exception
    {
        BasStorehouse bsh = new BasStorehouse();
        bsh.setStoreId(basStorehouseVo.getStoreId());
        dao.delete(bsh);
        setContent("基础资料-->删除仓别分类,仓别分类编号:" + basStorehouseVo.getStoreId());
    }

    /**
     * 基础资料-->配件性质：仓别分类 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改仓别分类", content = "基础资料-->修改仓别分类")
    public void update(BasStorehouseVo basStorehouseVo) throws Exception
    {
        BasStorehouse bsh = dao.get(BasStorehouse.class, basStorehouseVo.getStoreId());
        bsh.setStoreName(basStorehouseVo.getStoreName());
        if (basStorehouseVo.getRemark() != null
                && !basStorehouseVo.getRemark().equals(""))
            bsh.setRemark(basStorehouseVo.getRemark());
        dao.merge(bsh);
        setContent("基础资料-->修改仓别分类,仓别分类编号:" + basStorehouseVo.getStoreId());
    }

    /**
     * 基础资料-->配件性质：仓别分类 全部显示
     */
    public List<BasStorehouseVo> findAll(BasStorehouseVo bsvo) throws Exception
    {
        List<BasStorehouseVo> list = new ArrayList<BasStorehouseVo>();
        List<BasStorehouse> resultList = dao.findAll(bsvo);
        for (int i = 0; i < resultList.size(); i++)
        {
            BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
            basStorehouseVo.setRemark(resultList.get(i).getRemark());
            basStorehouseVo.setStoreId(resultList.get(i).getStoreId());
            basStorehouseVo.setStoreName(resultList.get(i).getStoreName());
            list.add(basStorehouseVo);
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：仓别分类 分页
     */
    public List<BasStorehouseVo> getAllByPage(BasStorehouseVo basStorehouseVo)
            throws Exception
    {
        List<BasStorehouseVo> list = new ArrayList<BasStorehouseVo>();
        List<BasStorehouse> resultList = dao.getAllByPage(basStorehouseVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasStorehouseVo bsvo = new BasStorehouseVo();
                bsvo.setRemark(resultList.get(i).getRemark());
                bsvo.setStoreId(resultList.get(i).getStoreId());
                bsvo.setStoreName(resultList.get(i).getStoreName());
                list.add(bsvo);
            }
        }
        return list;
    }

    public com.syuesoft.bas.dao.BasStorehouseDAO getDao()
    {
        return dao;
    }

    public void setDao(com.syuesoft.bas.dao.BasStorehouseDAO dao)
    {
        this.dao = dao;
    }

}
