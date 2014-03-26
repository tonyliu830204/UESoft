package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.daoimpl.BasPartsSellDAOImpl;
import com.syuesoft.bas.service.BasPartsSellService;
import com.syuesoft.base.vo.BasPartsSellVo;
import com.syuesoft.model.BasPartsSell;

/**
 * 基础资料-->配件性质：配件销售分类Service实现类
 * 
 * @author SuMing
 */
public class BasPartsSellServiceImpl extends BaseLogServiceImpl implements
        BasPartsSellService
{

    BasPartsSellDAOImpl dao = null;

    public boolean isExist(BasPartsSellVo basPartsSellVo) throws Exception
    {
        String param = "";
        if (basPartsSellVo.getPsellId() != null
                && !"".equals(basPartsSellVo.getPsellId()))
            param += " and bps.psellId!=" + basPartsSellVo.getPsellId();
        if (basPartsSellVo.getPsellName() != null
                && !"".equals(basPartsSellVo.getPsellName()))
            param += " and bps.psellName='" + basPartsSellVo.getPsellName()
                    + "'";
        List<BasPartsSell> list = dao.find("FROM BasPartsSell bps WHERE bps.enterpriseId="+basPartsSellVo.getEnterpriseId()+" "
                + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->配件性质：配件销售分类 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增配件销售分类", content = "基础资料-->新增配件销售分类")
    public void add(BasPartsSellVo basPartsSellVo) throws Exception
    {
        BasPartsSell bps = new BasPartsSell();
        bps.setPsellName(basPartsSellVo.getPsellName());
        bps.setPsellPoint(basPartsSellVo.getPsellPoint());
        if (basPartsSellVo.getRemark() != null
                && !basPartsSellVo.getRemark().equals(""))
            bps.setRemark(basPartsSellVo.getRemark());
        else
            bps.setRemark("");
        bps.setEnterpriseId(basPartsSellVo.getEnterpriseId());
        Serializable bb = dao.save(bps);
        setContent("基础资料-->新增配件销售分类,配件销售分类编号:" + bb);
    }

    /**
     * 基础资料-->配件性质：配件销售分类 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除配件销售分类", content = "基础资料-->删除配件销售分类")
    public void delete(BasPartsSellVo basPartsSellVo) throws Exception
    {
        BasPartsSell bps = new BasPartsSell();
        bps.setPsellId(basPartsSellVo.getPsellId());
        dao.delete(bps);
        setContent("基础资料-->删除配件销售分类,配件销售分类编号:" + basPartsSellVo.getPsellId());
    }

    /**
     * 基础资料-->配件性质：配件销售分类 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改配件销售分类", content = "基础资料-->修改配件销售分类")
    public void update(BasPartsSellVo basPartsSellVo) throws Exception
    {
        BasPartsSell bps = dao.get(BasPartsSell.class, basPartsSellVo.getPsellId());
        bps.setPsellName(basPartsSellVo.getPsellName());
        bps.setPsellPoint(basPartsSellVo.getPsellPoint());
        if (basPartsSellVo.getRemark() != null
                && !basPartsSellVo.getRemark().equals(""))
            bps.setRemark(basPartsSellVo.getRemark());
        dao.merge(bps);
        setContent("基础资料-->修改配件销售分类,配件销售分类编号:" + basPartsSellVo.getPsellId());
    }

    /**
     * 基础资料-->配件性质：配件销售分类 全部显示
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsSellVo> findAll(BasPartsSellVo bpsvo) throws Exception
    {
        List<BasPartsSellVo> list = new ArrayList<BasPartsSellVo>();
        List<BasPartsSell> resultList = dao.findAll(bpsvo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsSellVo basPartsSellVo = new BasPartsSellVo();
                basPartsSellVo.setPsellId(resultList.get(i).getPsellId());
                basPartsSellVo.setPsellName(resultList.get(i).getPsellName());
                basPartsSellVo.setPsellPoint(resultList.get(i).getPsellPoint());
                basPartsSellVo.setRemark(resultList.get(i).getRemark());
                list.add(basPartsSellVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：配件销售分类 分页
     */
    public List<BasPartsSellVo> getAllByPage(BasPartsSellVo basPartsSellVo)
            throws Exception
    {
        List<BasPartsSellVo> list = new ArrayList<BasPartsSellVo>();
        List<BasPartsSell> resultList = dao.getAllByPage(basPartsSellVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsSellVo bpsvo = new BasPartsSellVo();
                bpsvo.setPsellId(resultList.get(i).getPsellId());
                bpsvo.setPsellName(resultList.get(i).getPsellName());
                bpsvo.setPsellPoint(resultList.get(i).getPsellPoint());
                bpsvo.setRemark(resultList.get(i).getRemark());
                list.add(bpsvo);
            }
        }
        return list;
    }

    public BasPartsSellDAOImpl getDao()
    {
        return dao;
    }

    public void setDao(BasPartsSellDAOImpl dao)
    {
        this.dao = dao;
    }

}
