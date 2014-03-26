package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarColorDAO;
import com.syuesoft.bas.service.BasCarColorService;
import com.syuesoft.base.vo.BasCarColorVo;
import com.syuesoft.model.BasCarColor;

/**
 * 基础资料-->车辆性质：车辆颜色Service实现类
 * 
 * @author SuMing
 */
public class BasCarColorServiceImpl extends BaseLogServiceImpl implements
        BasCarColorService
{

    BasCarColorDAO dao = null;

    public boolean isExist(BasCarColorVo basCarColorVo) throws Exception
    {
        String param = "FROM BasCarColor  bcc WHERE 1=1   and  bcc.enterpriseId="+basCarColorVo.getEnterpriseId() ;
        if (basCarColorVo.getColor() != null
                && !"".equals(basCarColorVo.getColor()))
            param += " and bcc.color not in (" + basCarColorVo.getColor() + ")";
        if (basCarColorVo.getColorName() != null&& !"".equals(basCarColorVo.getColorName()))
            param += " and bcc.colorName='" + basCarColorVo.getColorName()+ "'";
        List<BasCarColor> list = this.dao.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增车辆颜色", content = "基础资料-->新增车辆颜色")
    public void add(BasCarColorVo basCarColorVo) throws Exception
    {
        BasCarColor bcc = new BasCarColor();
        bcc.setColorName(basCarColorVo.getColorName());
        if (basCarColorVo.getRemark() != null
                && !basCarColorVo.getRemark().equals(""))
            bcc.setRemark(basCarColorVo.getRemark());
        bcc.setEnterpriseId(basCarColorVo.getEnterpriseId());
        Serializable dd = dao.save(bcc);
        setContent("基础资料-->新增车辆品牌,车辆品牌编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除车辆颜色", content = "基础资料-->删除车辆颜色")
    public void delete(BasCarColorVo basCarColorVo) throws Exception
    {
    	BasCarColor bcc=dao.get(BasCarColor.class, basCarColorVo.getColor());
    	if(bcc!=null){
    		 dao.delete(bcc);
    	     setContent("基础资料-->删除车辆颜色,车辆颜色编号:" + basCarColorVo.getColor());
    	}
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改车辆颜色", content = "基础资料-->修改车辆颜色")
    public void update(BasCarColorVo basCarColorVo) throws Exception
    {
        BasCarColor bcc = dao.get(BasCarColor.class, basCarColorVo.getColor());
        if(bcc!=null){
    	  if(bcc.getColorName()!=null&&!bcc.getColorName().equals(""))
             bcc.setColorName(basCarColorVo.getColorName());
          if (basCarColorVo.getRemark() != null&& !basCarColorVo.getRemark().equals(""))
             bcc.setRemark(basCarColorVo.getRemark());
          dao.merge(bcc);
          setContent("基础资料-->修改车辆颜色,车辆颜色编号:" + basCarColorVo.getColor());
        }
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 全部显示
     */
    public List<BasCarColorVo> findAll(BasCarColorVo basCarColorVo) throws Exception
    {
        List<BasCarColorVo> list = new ArrayList<BasCarColorVo>();
        List<BasCarColor> resultList = dao.findAll(basCarColorVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarColorVo bcb = new BasCarColorVo();
                bcb.setColor(resultList.get(i).getColor());
                bcb.setColorName(resultList.get(i).getColorName());
                bcb.setRemark(resultList.get(i).getRemark());
                list.add(bcb);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 分页
     */
    public List<BasCarColorVo> getAllByPage(BasCarColorVo basCarColorVo)
            throws Exception
    {
        List<BasCarColorVo> list = new ArrayList<BasCarColorVo>();
        List<BasCarColor> resultList = dao.getAllByPage(basCarColorVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarColorVo bccVo = new BasCarColorVo();
                bccVo.setColor(resultList.get(i).getColor());
                bccVo.setColorName(resultList.get(i).getColorName());
                bccVo.setRemark(resultList.get(i).getRemark());
                list.add(bccVo);
            }
        }
        return list;
    }

    public BasCarColorDAO getDao()
    {
        return dao;
    }

    public void setDao(BasCarColorDAO dao)
    {
        this.dao = dao;
    }
}
