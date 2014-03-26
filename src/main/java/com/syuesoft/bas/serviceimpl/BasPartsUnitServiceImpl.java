package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.service.BasPartsUnitService;
import com.syuesoft.base.vo.BasPartsUnitVo;
import com.syuesoft.model.BasPartsUnit;

/**
 * 基础资料-->配件性质：计量单位Service实现类
 * 
 * @author SuMing
 */
public class BasPartsUnitServiceImpl extends BaseLogServiceImpl implements
        BasPartsUnitService
{

    @Autowired BasPartsUnitDAO basPartsUnitDAO;

    public boolean isExist(BasPartsUnitVo basPartsUnitVo) throws Exception
    {
        String param = "";
        if (basPartsUnitVo.getPunitId() != null
                && !"".equals(basPartsUnitVo.getPunitId()))
            param += " and bpu.punitId!=" + basPartsUnitVo.getPunitId();
        if (basPartsUnitVo.getPunitName() != null
                && !"".equals(basPartsUnitVo.getPunitName()))
            param += " and bpu.punitName='" + basPartsUnitVo.getPunitName()
                    + "'";
        List<BasPartsUnit> list = basPartsUnitDAO.find("FROM BasPartsUnit bpu WHERE bpu.enterpriseId="+basPartsUnitVo.getEnterpriseId()+""
                + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->配件性质：计量单位 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增计量单位", content = "基础资料-->新增计量单位")
    public void add(BasPartsUnitVo basPartsUnitVo) throws Exception
    {
        BasPartsUnit bpu = new BasPartsUnit();
        bpu.setPunitName(basPartsUnitVo.getPunitName());
        if (basPartsUnitVo.getRemark() != null
                && !basPartsUnitVo.getRemark().equals(""))
            bpu.setRemark(basPartsUnitVo.getRemark());
        else
            bpu.setRemark("");
        bpu.setEnterpriseId(basPartsUnitVo.getEnterpriseId());
        Serializable bb = basPartsUnitDAO.save(bpu);
        setContent("基础资料-->新增计量单位,计量单位编号:" + bb);
    }

    /**
     * 基础资料-->配件性质：计量单位 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除计量单位", content = "基础资料-->删除计量单位")
    public void delete(BasPartsUnitVo basPartsUnitVo) throws Exception
    {
        BasPartsUnit bpu = new BasPartsUnit();
        bpu.setPunitId(basPartsUnitVo.getPunitId());
        basPartsUnitDAO.delete(bpu);
        setContent("基础资料-->删除计量单位,计量单位编号:" + basPartsUnitVo.getPunitId());
    }

    /**
     * 基础资料-->配件性质：计量单位 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改计量单位", content = "基础资料-->修改计量单位")
    public void update(BasPartsUnitVo basPartsUnitVo) throws Exception
    {
        BasPartsUnit bpu = basPartsUnitDAO.get(BasPartsUnit.class, basPartsUnitVo.getPunitId());
        bpu.setPunitName(basPartsUnitVo.getPunitName());
        if (basPartsUnitVo.getRemark() != null
                && !basPartsUnitVo.getRemark().equals(""))
            bpu.setRemark(basPartsUnitVo.getRemark());
        else
            bpu.setRemark("");
        basPartsUnitDAO.merge(bpu);
        setContent("基础资料-->修改计量单位,计量单位编号:" + basPartsUnitVo.getPunitId());
    }

    /**
     * 基础资料-->配件性质：计量单位 全部显示
     */
    public List<BasPartsUnitVo> findAll(BasPartsUnitVo basPartsUnitVo) throws Exception
    {
        List<BasPartsUnitVo> list = new ArrayList<BasPartsUnitVo>();
        List<BasPartsUnit> resultList = basPartsUnitDAO.findAll(basPartsUnitVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsUnitVo BasPartsUnitVo = new BasPartsUnitVo();
                BasPartsUnitVo.setPunitId(resultList.get(i).getPunitId());
                BasPartsUnitVo.setPunitName(resultList.get(i).getPunitName());
                BasPartsUnitVo.setRemark(resultList.get(i).getRemark());
                list.add(BasPartsUnitVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：计量单位 分页
     */
    public List<BasPartsUnitVo> getAllByPage(BasPartsUnitVo basPartsUnitVo)
            throws Exception
    {
        List<BasPartsUnitVo> list = new ArrayList<BasPartsUnitVo>();
        List<BasPartsUnit> resultList = basPartsUnitDAO.getAllByPage(basPartsUnitVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsUnitVo BasPartsUnitVo = new BasPartsUnitVo();
                BasPartsUnitVo.setPunitId(resultList.get(i).getPunitId());
                BasPartsUnitVo.setPunitName(resultList.get(i).getPunitName());
                BasPartsUnitVo.setRemark(resultList.get(i).getRemark());
                list.add(BasPartsUnitVo);
            }
        }
        return list;
    }
}
