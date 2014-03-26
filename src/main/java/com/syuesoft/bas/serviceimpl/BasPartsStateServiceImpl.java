package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPartsStateDao;
import com.syuesoft.bas.service.BasPartsStateService;
import com.syuesoft.base.vo.BasPartsStateVo;
import com.syuesoft.model.BasPartsState;

/**
 * 配件产地
 * 
 * @author HeXin
 * 
 */
@Service("basPartsStateService")
public class BasPartsStateServiceImpl extends BaseLogServiceImpl implements
        BasPartsStateService
{

    BasPartsStateDao basPartsStateDao;

    public boolean isExist(BasPartsStateVo basPartsStateVo) throws Exception
    {
        String param = "";
        if (basPartsStateVo.getStateId() != null
                && !"".equals(basPartsStateVo.getStateId()))
            param += " and bps.stateId!=" + basPartsStateVo.getStateId();
        if (basPartsStateVo.getStateName() != null
                && !"".equals(basPartsStateVo.getStateName()))
            param += " and bps.stateName='" + basPartsStateVo.getStateName()
                    + "'";
        List<BasPartsState> list = basPartsStateDao
                .find("FROM BasPartsState bps WHERE bps.enterpriseId="+basPartsStateVo.getEnterpriseId()+" " + param);
        if (list != null && list.size() > 0)
            return true;
        return false;
    }

    
    @Log(moduleName = "基础资料", opertype = "新增配件产地", content = "基础资料-->新增配件产地")
    public void add(BasPartsStateVo basPartsStateVo) throws Exception
    {
        BasPartsState basPartsState = new BasPartsState();
        basPartsState.setStateName(basPartsStateVo.getStateName());
        basPartsState.setEnterpriseId(basPartsStateVo.getEnterpriseId());
        Serializable bb = basPartsStateDao.save(basPartsState);
        setContent("基础资料-->新增配件产地,配件产地编号:" + bb);
    }

    
    @Log(moduleName = "基础资料", opertype = "删除配件产地", content = "基础资料-->删除配件产地")
    public void delete(BasPartsStateVo basPartsStateVo) throws Exception
    {
        BasPartsState basPartsState = basPartsStateDao.get(BasPartsState.class,
                new Short(basPartsStateVo.getStateId()));
        basPartsStateDao.delete(basPartsState);
        setContent("基础资料-->删除配件产地,配件产地编号:" + basPartsStateVo.getStateId());
    }

    
    public List<BasPartsStateVo> findAll(BasPartsStateVo bpsVo) throws Exception
    {
        List<BasPartsStateVo> basPartsStateVoList = new ArrayList<BasPartsStateVo>();
        List<BasPartsState> basPartsStateList = basPartsStateDao
                .find("from  BasPartsState bcb where bcb.enterpriseId="+bpsVo.getEnterpriseId());
        if (basPartsStateList != null && basPartsStateList.size() > 0)
        {
            for (int i = 0; i < basPartsStateList.size(); i++)
            {
                BasPartsStateVo basPartsStateVo = new BasPartsStateVo();
                basPartsStateVo.setStateId(basPartsStateList.get(i)
                        .getStateId()
                        + "");
                basPartsStateVo.setStateName(basPartsStateList.get(i)
                        .getStateName());
                basPartsStateVoList.add(basPartsStateVo);
            }
        }
        return basPartsStateVoList;
    }

    
    public List<BasPartsStateVo> getAllByPage(BasPartsStateVo basPartsStateVo)
            throws Exception
    {
        List<BasPartsStateVo> basPartsStateVoList = new ArrayList<BasPartsStateVo>();
        List<BasPartsState> basPartsStateList = basPartsStateDao.find(
                "from  BasPartsState bcb where bcb.enterpriseId="+basPartsStateVo.getEnterpriseId(), basPartsStateVo.getPage(),
                basPartsStateVo.getRows());
        if (basPartsStateList!=null && basPartsStateList.size() > 0)
        {
            for (int i = 0; i < basPartsStateList.size(); i++)
            {
                BasPartsStateVo bpsVo = new BasPartsStateVo();
                bpsVo.setStateId(basPartsStateList.get(i).getStateId() + "");
                bpsVo.setStateName(basPartsStateList.get(i).getStateName());
                basPartsStateVoList.add(bpsVo);
            }
        }
        return basPartsStateVoList;
    }

    
    @Log(moduleName = "基础资料", opertype = "更新配件产地", content = "基础资料-->更新配件产地")
    public void update(BasPartsStateVo basPartsStateVo) throws Exception
    {
        BasPartsState basPartsState = basPartsStateDao.get(BasPartsState.class,
                new Short(basPartsStateVo.getStateId()));
        basPartsState.setStateName(basPartsStateVo.getStateName());
        basPartsStateDao.merge(basPartsState);
        setContent("基础资料-->更新配件产地,配件产地编号:" + basPartsStateVo.getStateId());
    }

    public BasPartsStateDao getbasPartsStateDao()
    {
        return basPartsStateDao;
    }
    @Autowired
    public void setbasPartsStateDao(BasPartsStateDao basPartsStateDao)
    {
        this.basPartsStateDao = basPartsStateDao;
    }

}
