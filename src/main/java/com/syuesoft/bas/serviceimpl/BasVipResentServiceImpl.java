package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipResentDao;
import com.syuesoft.bas.service.BasVipResentService;
import com.syuesoft.base.vo.BasVipResentVO;
import com.syuesoft.model.BasVipResent;

/**
 * 兑换礼品名称
 * 
 * @author HeXin
 * 
 */
@Service("basVipResentService")
public class BasVipResentServiceImpl extends BaseLogServiceImpl implements
        BasVipResentService
{
    @Autowired
    private BasVipResentDao basVipResentDao;

    
    @Log(moduleName = "基础资料", opertype = "新增兑换礼品", content = "基础资料-->新增兑换礼品")
    public void add(BasVipResentVO basVipResentVO) throws Exception
    {
        BasVipResent vbp = new BasVipResent();
        vbp.setPstId(basVipResentVO.getPstId());
        vbp.setPstName(basVipResentVO.getPstName());
        vbp.setPstCount(basVipResentVO.getPstCount());
        vbp.setPstUnit(basVipResentVO.getPstUnit());
        vbp.setPstInte(basVipResentVO.getPstInte());
        basVipResentDao.add(vbp);
        setContent("基础资料-->新增兑换礼品,兑换礼品名称:" + basVipResentVO.getPstName());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除兑换礼品", content = "基础资料-->删除兑换礼品")
    public void delete(BasVipResentVO basVipResentVO) throws Exception
    {
        BasVipResent vbp = new BasVipResent();
        vbp.setPstId(basVipResentVO.getPstId());
        vbp.setPstName(basVipResentVO.getPstName());
        vbp.setPstCount(basVipResentVO.getPstCount());
        vbp.setPstUnit(basVipResentVO.getPstUnit());
        vbp.setPstInte(basVipResentVO.getPstInte());
        basVipResentDao.delte(vbp);
        setContent("基础资料-->删除兑换礼品,兑换礼品编号:" + basVipResentVO.getPstId());
    }

    
    public List<BasVipResentVO> findAll(int page, int rows, String order,
            String sort) throws Exception
    {
        return basVipResentDao.findAll(page, rows, order, sort);
    }

    
    public int getTotle() throws Exception
    {
        return basVipResentDao.getTotle();
    }

    
    @Log(moduleName = "基础资料", opertype = "更新兑换礼品", content = "基础资料-->更新兑换礼品")
    public void update(BasVipResentVO basVipResentVO) throws Exception
    {
        BasVipResent vbp = new BasVipResent();
        vbp.setPstId(basVipResentVO.getPstId());
        vbp.setPstName(basVipResentVO.getPstName());
        vbp.setPstCount(basVipResentVO.getPstCount());
        vbp.setPstUnit(basVipResentVO.getPstUnit());
        vbp.setPstInte(basVipResentVO.getPstInte());
        basVipResentDao.update(vbp);
        setContent("基础资料-->更新兑换礼品,兑换礼品编号:" + basVipResentVO.getPstId());
    }

    
    public boolean getByName(String name) throws Exception
    {
        return basVipResentDao.getByName(name);
    }
}
