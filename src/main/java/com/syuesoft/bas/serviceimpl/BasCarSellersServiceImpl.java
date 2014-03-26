package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarSellersDAO;
import com.syuesoft.bas.service.BasCarSellersService;
import com.syuesoft.base.vo.BasCarSellersVo;
import com.syuesoft.model.BasCarSellers;

/**
 * 基础资料-->车辆性质：其他销售店Service实现类
 * 
 * @author SuMing
 */
public class BasCarSellersServiceImpl extends BaseLogServiceImpl implements
        BasCarSellersService
{

    BasCarSellersDAO dao = null;

    public boolean isExist(BasCarSellersVo basCarSellersVo) throws Exception
    {
        String param = "FROM BasCarSellers  bcs WHERE bcs.enterpriseId="+basCarSellersVo.getEnterpriseId();
        if (basCarSellersVo.getSlsId() != null&& basCarSellersVo.getSlsId()!=0)
            param += " and bcs.slsId!=" + basCarSellersVo.getSlsId();
        if (basCarSellersVo.getSlsName() != null&& !"".equals(basCarSellersVo.getSlsName()))
            param += " and bcs.slsName='" + basCarSellersVo.getSlsName() + "'";
        List<BasCarSellers> list = dao.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：其他销售店 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增其他销售店", content = "基础资料-->新增其他销售店")
    public void add(BasCarSellersVo basCarSellersVo) throws Exception
    {
        BasCarSellers bcs = new BasCarSellers();
        bcs.setSlsName(basCarSellersVo.getSlsName());
        if (basCarSellersVo.getRemark() != null
                && !basCarSellersVo.getRemark().equals(""))
            bcs.setRemark(basCarSellersVo.getRemark());
        bcs.setEnterpriseId(basCarSellersVo.getEnterpriseId());
        Serializable dd = dao.save(bcs);
        setContent("基础资料-->新增其他销售店 ,其他销售店 编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：其他销售店 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除其他销售店", content = "基础资料-->删除其他销售店")
    public void delete(BasCarSellersVo basCarSellersVo) throws Exception
    {
        BasCarSellers bcs = dao.get(BasCarSellers.class, basCarSellersVo.getSlsId());
        if(bcs!=null){
        	dao.delete(bcs);
        	setContent("基础资料-->删除其他销售店 ,其他销售店 编号:" + basCarSellersVo.getSlsId());
        }
    }

    /**
     * 基础资料-->车辆性质：其他销售店 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改其他销售店", content = "基础资料-->修改其他销售店")
    public void update(BasCarSellersVo basCarSellersVo) throws Exception
    {
        BasCarSellers bcs = dao.get(BasCarSellers.class, basCarSellersVo.getSlsId());
        if(bcs!=null){
        	if (basCarSellersVo.getRemark() != null&& !basCarSellersVo.getRemark().equals(""))
        		bcs.setRemark(basCarSellersVo.getRemark());
        	dao.merge(bcs);
        	setContent("基础资料-->修改其他销售店 ,其他销售店 编号:" + basCarSellersVo.getSlsId());
        }
    }

    /**
     * 基础资料-->车辆性质：其他销售店 全部显示
     */
    public List<BasCarSellersVo> findAll(BasCarSellersVo bcs) throws Exception
    {
        List<BasCarSellersVo> list = new ArrayList<BasCarSellersVo>();
        List<BasCarSellers> resultList = dao.findAll(bcs);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarSellersVo basCarSellersVo = new BasCarSellersVo();
                basCarSellersVo.setSlsId(resultList.get(i).getSlsId());
                basCarSellersVo.setSlsName(resultList.get(i).getSlsName());
                basCarSellersVo.setRemark(resultList.get(i).getRemark());
                list.add(basCarSellersVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：其他销售店 分页
     */
    public List<BasCarSellersVo> getAllByPage(BasCarSellersVo basCarSellersVo)
            throws Exception
    {
        List<BasCarSellersVo> list = new ArrayList<BasCarSellersVo>();
        List<BasCarSellers> resultList = dao.getAllByPage(basCarSellersVo);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++)
            {
                BasCarSellersVo bcsvo = new BasCarSellersVo();
                bcsvo.setSlsId(resultList.get(i).getSlsId());
                bcsvo.setSlsName(resultList.get(i).getSlsName());
                bcsvo.setRemark(resultList.get(i).getRemark());
                list.add(bcsvo);
            }
        }
        return list;
    }

    public BasCarSellersDAO getDao()
    {
        return dao;
    }

    public void setDao(BasCarSellersDAO dao)
    {
        this.dao = dao;
    }

}
