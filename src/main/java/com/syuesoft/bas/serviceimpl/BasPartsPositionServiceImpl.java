package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsPositionDAO;
import com.syuesoft.bas.service.BasPartsPositionService;
import com.syuesoft.base.vo.BasPartsPositionVo;
import com.syuesoft.model.BasPartsPosition;

/**
 * 基础资料-->配件性质：配件部位Service实现类
 * 
 * @author SuMing
 */
@Service("basPartsPositionService")
public class BasPartsPositionServiceImpl extends BaseLogServiceImpl implements
        BasPartsPositionService
{
	@Autowired
	private BasPartsPositionDAO basPartsPositionDAO;

    public boolean isExist(BasPartsPositionVo basPartsPositionVo)
            throws Exception
    {
        String param = "";
        if (basPartsPositionVo.getPosId() != null
                && !"".equals(basPartsPositionVo.getPosId()))
            param += " and bpp.posId!=" + basPartsPositionVo.getPosId();
        if (basPartsPositionVo.getPosName() != null
                && !"".equals(basPartsPositionVo.getPosName()))
            param += " and bpp.posName='" + basPartsPositionVo.getPosName()
                    + "'";
        List<BasPartsPosition> list = basPartsPositionDAO
                .find("FROM BasPartsPosition bpp WHERE bpp.enterpriseId="+basPartsPositionVo.getEnterpriseId()+" " + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->配件性质：配件部位 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增配件部位", content = "基础资料-->新增配件部位")
    public void add(BasPartsPositionVo basPartsPositionVo) throws Exception
    {
        BasPartsPosition bpp = new BasPartsPosition();
        bpp.setPosName(basPartsPositionVo.getPosName());
        if (basPartsPositionVo.getRemark() != null
                && !basPartsPositionVo.getRemark().equals(""))
            bpp.setRemark(basPartsPositionVo.getRemark());
        else
            bpp.setRemark("");
        bpp.setEnterpriseId(basPartsPositionVo.getEnterpriseId());
        Serializable bb = basPartsPositionDAO.save(bpp);
        setContent("基础资料-->新增配件部位,配件部位编号:" + bb);
    }

    /**
     * 基础资料-->配件性质：配件部位 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除配件部位", content = "基础资料-->删除配件部位")
    public void delete(BasPartsPositionVo basPartsPositionVo) throws Exception
    {
        BasPartsPosition bpp = new BasPartsPosition();
        bpp.setPosId(basPartsPositionVo.getPosId());
        basPartsPositionDAO.delete(bpp);
        setContent("基础资料-->删除配件部位,配件部位编号:" + basPartsPositionVo.getPosId());
    }

    /**
     * 基础资料-->配件性质：配件部位 修改
     */
    @Log(moduleName = "基础资料", opertype = "更新配件部位", content = "基础资料-->更新配件部位")
    public void update(BasPartsPositionVo basPartsPositionVo) throws Exception
    {
        BasPartsPosition bpp = basPartsPositionDAO.get(BasPartsPosition.class, basPartsPositionVo.getPosId());
        bpp.setPosName(basPartsPositionVo.getPosName());
        if (basPartsPositionVo.getRemark() != null
                && !basPartsPositionVo.getRemark().equals(""))
            bpp.setRemark(basPartsPositionVo.getRemark());
        else
            bpp.setRemark("");
        basPartsPositionDAO.merge(bpp);
        setContent("基础资料-->更新配件部位,配件部位编号:" + basPartsPositionVo.getPosId());
    }

    /**
     * 基础资料-->配件性质：配件部位 全部显示
     */
    public List<BasPartsPositionVo> findAll(BasPartsPositionVo bpbvo) throws Exception
    {
        List<BasPartsPositionVo> list = new ArrayList<BasPartsPositionVo>();
        List<BasPartsPosition> resultList = basPartsPositionDAO.findAll(bpbvo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsPositionVo basPartsPositionVo = new BasPartsPositionVo();
                basPartsPositionVo.setPosId(resultList.get(i).getPosId());
                basPartsPositionVo.setPosName(resultList.get(i).getPosName());
                basPartsPositionVo.setRemark(resultList.get(i).getRemark());
                list.add(basPartsPositionVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->配件性质：配件部位 分页
     */
    public List<BasPartsPositionVo> getAllByPage(
            BasPartsPositionVo basPartsPositionVo) throws Exception
    {
        List<BasPartsPositionVo> list = new ArrayList<BasPartsPositionVo>();
        List<BasPartsPosition> resultList = basPartsPositionDAO.getAllByPage(basPartsPositionVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasPartsPositionVo basPartsPositionVo1 = new BasPartsPositionVo();
                basPartsPositionVo1.setPosId(resultList.get(i).getPosId());
                basPartsPositionVo1.setPosName(resultList.get(i).getPosName());
                basPartsPositionVo1.setRemark(resultList.get(i).getRemark());
                list.add(basPartsPositionVo1);
            }
        }
        return list;
    }
    
}
