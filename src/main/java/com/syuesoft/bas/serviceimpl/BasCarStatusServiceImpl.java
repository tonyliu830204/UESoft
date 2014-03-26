package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarStatusDAO;
import com.syuesoft.bas.service.BasCarStatusService;
import com.syuesoft.base.vo.BasCarStatusVo;
import com.syuesoft.model.BasCarStatus;

/**
 * 基础资料-->车辆性质：流失去向Service实现类
 * 
 * @author SuMing
 */
public class BasCarStatusServiceImpl extends BaseLogServiceImpl implements
        BasCarStatusService
{

    BasCarStatusDAO dao = null;

    public boolean isExist(BasCarStatusVo basCarStatusVo) throws Exception
    {
        String param = "FROM BasCarStatus  bcs WHERE bcs.enterpriseId="+basCarStatusVo.getEnterpriseId();
        if (basCarStatusVo.getStatusId() != null
                && !"".equals(basCarStatusVo.getStatusId()))
            param += " and bcs.statusId!=" + basCarStatusVo.getStatusId();
        if (basCarStatusVo.getStatusName() != null
                && !"".equals(basCarStatusVo.getStatusName()))
            param += " and bcs.statusName='" + basCarStatusVo.getStatusName()
                    + "'";
        List<BasCarStatus> list = this.dao.find(param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    /**
     * 基础资料-->车辆性质：流失去向 添加
     */
    @Log(moduleName = "基础资料", opertype = "新增流失去向", content = "基础资料-->新增流失去向")
    public void add(BasCarStatusVo basCarStatusVo) throws Exception
    {
        BasCarStatus bcs = new BasCarStatus();
        bcs.setStatusName(basCarStatusVo.getStatusName());
        bcs.setEnterpriseId(basCarStatusVo.getEnterpriseId());
        Serializable dd = dao.save(bcs);
        setContent("基础资料-->新增流失去向 ,流失去向编号:" + dd);
    }

    /**
     * 基础资料-->车辆性质：流失去向 删除
     */
    @Log(moduleName = "基础资料", opertype = "删除流失去向", content = "基础资料-->删除流失去向")
    public void delete(BasCarStatusVo basCarStatusVo) throws Exception
    {
        BasCarStatus bcs = dao.get(BasCarStatus.class, basCarStatusVo.getStatusId());
        if(bcs!=null){
        	dao.delete(bcs);
        	setContent("基础资料-->删除流失去向 ,流失去向编号:" + basCarStatusVo.getStatusId());
        }
    }

    /**
     * 基础资料-->车辆性质：流失去向 修改
     */
    @Log(moduleName = "基础资料", opertype = "修改流失去向", content = "基础资料-->修改流失去向")
    public void update(BasCarStatusVo basCarStatusVo) throws Exception
    {
        BasCarStatus bcs =dao.get(BasCarStatus.class, basCarStatusVo.getStatusId());
        if(bcs!=null){
        	  bcs.setStatusName(basCarStatusVo.getStatusName());
              dao.merge(bcs);
              setContent("基础资料-->修改流失去向 ,流失去向编号:" + basCarStatusVo.getStatusId());
        }
    }

    /**
     * 基础资料-->车辆性质：流失去向 全部显示
     */
    @SuppressWarnings("unchecked")
    public List<BasCarStatusVo> findAll(BasCarStatusVo bcb) throws Exception
    {
        List<BasCarStatusVo> list = new ArrayList<BasCarStatusVo>();
        List<BasCarStatus> resultList = dao.findAll(bcb);
        if (resultList != null && resultList.size() > 0)
        {
            for (int i = 0; i < resultList.size(); i++){
                BasCarStatusVo bcsVo = new BasCarStatusVo();
                bcsVo.setStatusId(resultList.get(i).getStatusId());
                bcsVo.setStatusName(resultList.get(i).getStatusName());
                list.add(bcsVo);
            }
        }
        return list;
    }

    /**
     * 基础资料-->车辆性质：流失去向 分页
     */
    public List<BasCarStatusVo> getAllByPage(BasCarStatusVo basCarStatusVo)
            throws Exception
    {
        List<BasCarStatusVo> list = new ArrayList<BasCarStatusVo>();
        List<BasCarStatus> resultList = dao.getAllByPage(basCarStatusVo);
        if (resultList != null && resultList.size() > 0){
            for (int i = 0; i < resultList.size(); i++){
                BasCarStatusVo bcsvo = new BasCarStatusVo();
                bcsvo.setStatusId(resultList.get(i).getStatusId());
                bcsvo.setStatusName(resultList.get(i).getStatusName());
                list.add(bcsvo);
            }
        }
        return list;
    }

    public BasCarStatusDAO getDao()
    {
        return dao;
    }

    public void setDao(BasCarStatusDAO dao)
    {
        this.dao = dao;
    }

}
