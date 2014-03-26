package com.syuesoft.bas.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipRechargeRegulationDao;
import com.syuesoft.bas.service.BasVipRechargeRegulationService;
import com.syuesoft.model.BasVipRechargeRegulation;

/**
 * 储值规则
 * 
 * @author HeXin
 * 
 */
@Service("basVipRechargeRegulationService")
public class BasVipRechargeRegulationServiceImpl extends BaseLogServiceImpl
        implements BasVipRechargeRegulationService
{

    @Autowired
    private BasVipRechargeRegulationDao basVipRechargeRegulationDao;

    
    @Log(moduleName = "基础资料", opertype = "新增储值规则", content = "基础资料-->新增储值规则")
    public void add(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception
    {
        basVipRechargeRegulationDao.add(basVipRechargeRegulation);
        setContent("基础资料-->新增储值规则,储值规则名称:"
                + basVipRechargeRegulation.getGivInteRatio());
    }

    
    @Log(moduleName = "基础资料", opertype = "删除储值规则", content = "基础资料-->删除储值规则")
    public void delete(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception
    {
        basVipRechargeRegulationDao.delte(basVipRechargeRegulation);
        setContent("基础资料-->删除储值规则,储值规则名称:"
                + basVipRechargeRegulation.getGivInteRatio());
    }

    
    public List<BasVipRechargeRegulation> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception
    {
        return basVipRechargeRegulationDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    public int getTotle(int enterprise_id) throws Exception
    {
        return basVipRechargeRegulationDao.getTotle(enterprise_id);
    }

    
    @Log(moduleName = "基础资料", opertype = "更新储值规则", content = "基础资料-->更新储值规则")
    public void update(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception
    {
        basVipRechargeRegulationDao.update(basVipRechargeRegulation);
        setContent("基础资料-->更新储值规则,储值规则名称:"
                + basVipRechargeRegulation.getGivInteRatio());
    }

    /**
     * 根据开始和结束金额获取会员充值规则
     * 
     * @recRulName 开始金额
     * @recRulNameEnd 结束金额
     * */
    
    public boolean getByRecRu(BasVipRechargeRegulation bvr) throws Exception{
        return basVipRechargeRegulationDao.getByRecRu(bvr);
    }
}
