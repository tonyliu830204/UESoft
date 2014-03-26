package com.syuesoft.bas.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.bas.dao.FrtCarAllowCarTypeDao;
import com.syuesoft.bas.service.FrtCarAllowCarTypeService;
import com.syuesoft.base.vo.FrtCarAllowCarTypeVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FrtCarAllowCarType;

/**
 * 准驾车型
 * */
@Service("frtCarAllowCarTypeService")
public class FrtCarAllowCarTypeServiceImpl implements FrtCarAllowCarTypeService
{
    @Autowired
    private FrtCarAllowCarTypeDao frtCarAllowCarTypeDao;

    /**
     * 增加
     * */
    
    public void add(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo) throws Exception
    {
        // TODO Auto-generated method stub
        FrtCarAllowCarType fcact = new FrtCarAllowCarType();
        fcact.setAllowCarTypeName(frtCarAllowCarTypeVo.getAllowCarTypeName());
        fcact.setEnterpriseId(frtCarAllowCarTypeVo.getEnterpriseId());
        frtCarAllowCarTypeDao.save(fcact);
    }

    /**
     * 删除
     * */
    
    public void delete(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        frtCarAllowCarTypeDao.delete(frtCarAllowCarTypeDao.get(
                FrtCarAllowCarType.class, frtCarAllowCarTypeVo
                        .getAllowCarTypeId()));
    }

    /**
     * 修改
     * */
    
    public void update(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception
    {
        FrtCarAllowCarType fcact = frtCarAllowCarTypeDao.get(FrtCarAllowCarType.class, frtCarAllowCarTypeVo.getAllowCarTypeId());
        fcact.setAllowCarTypeName(frtCarAllowCarTypeVo.getAllowCarTypeName());
        frtCarAllowCarTypeDao.merge(fcact);
    }

    /**
     * 查询
     * */
    
    public Datagrid findAll(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception
    {
        List<FrtCarAllowCarTypeVo> frtCarAllowCarTypeVoList = new ArrayList();
        List<FrtCarAllowCarType> frtCarAllowCarTypeList = frtCarAllowCarTypeDao
                .find("from FrtCarAllowCarType bcb ",frtCarAllowCarTypeVo.getPage(), frtCarAllowCarTypeVo
                                .getRows());
        if (frtCarAllowCarTypeList != null && frtCarAllowCarTypeList.size() > 0)
            for (int i = 0; i < frtCarAllowCarTypeList.size(); i++)
            {
                FrtCarAllowCarTypeVo bpsVo = new FrtCarAllowCarTypeVo();
                bpsVo.setAllowCarTypeId(frtCarAllowCarTypeList.get(i)
                        .getAllowCarTypeId());
                bpsVo.setAllowCarTypeName(frtCarAllowCarTypeList.get(i)
                        .getAllowCarTypeName());
                frtCarAllowCarTypeVoList.add(bpsVo);
            }
        int total = frtCarAllowCarTypeDao.getCount("from FrtCarAllowCarType bcb ");
        Datagrid dg = new Datagrid();
        dg.setRows(frtCarAllowCarTypeVoList);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 判断是否重复
     * */
    
    public Boolean isExist(FrtCarAllowCarTypeVo frtCarAllowCarTypeVo)
            throws Exception
    {
        List<FrtCarAllowCarType> frtCarAllowCarTypeList = frtCarAllowCarTypeDao
                .find("from FrtCarAllowCarType fcact where  fcact.allowCarTypeName='"
                        + frtCarAllowCarTypeVo.getAllowCarTypeName() + "'");
        if (frtCarAllowCarTypeList != null && frtCarAllowCarTypeList.size() > 0)
        {
            return true;
        }
        return false;
    }

}
