package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipIntegralRegulationDao;
import com.syuesoft.bas.service.BasVipIntegralRegulationService;
import com.syuesoft.base.vo.BasVipIntegralRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.BasVipIntegralRegulation;
import com.syuesoft.model.BasVipLevel;

@Service("basVipIntegralRegulationService")
/**
 * 会员积分规则
 * @author mulangtao
 * */
public class BasVipIntegralRegulationServiceImpl extends BaseLogServiceImpl
        implements BasVipIntegralRegulationService
{
    @Autowired
    private BasVipIntegralRegulationDao basVipIntegralRegulationDao;

    
    /**
     * 添加 会员积分规则
     * @bvrVo  会员积分规则VO
     * */
    @Log(moduleName = "基础资料", opertype = "新增会员积分规则", content = "基础资料-->新增会员积分规则")
    public void add(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        // 实例化会员积分规则实体
        BasVipIntegralRegulation bvr = new BasVipIntegralRegulation();
        // 给会员积分规则属性赋值
        bvr.setVipInteReg(bvrVO.getVipInteReg()); // 会员积分折扣规则编号
        bvr.setBasVipLevel(basVipIntegralRegulationDao.getVipLevelByID(bvrVO
                .getVipLevelId())); // 会员等级
        // 维修分类编号
        if (bvrVO.getReptypId() == null)
        {
            bvr.setReptypId(null);
        }
        else
        {
            bvr.setReptypId((int) bvrVO.getReptypId());
        }
        bvr.setBeginAmount(bvrVO.getBeginAmount()); // 起始金额
        bvr.setJfAmont(bvrVO.getJfAmont()); // 积分金额
        bvr.setScore(bvrVO.getScore()); // 积分数
        bvr.setEnterpriseId(bvrVO.getEnterpriseId());
        basVipIntegralRegulationDao.add(bvr);
        setContent("基础资料-->新增会员积分规则,会员积分规则编号:" + bvrVO.getVipInteReg());
    }

    
    /**
     * 删除会员积分规则
     * @bvrVo 会员积分规则VO
     * */
    @Log(moduleName = "基础资料", opertype = "删除会员积分规则", content = "基础资料-->删除会员积分规则")
    public void delete(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        basVipIntegralRegulationDao.delte(bvrVO);
        setContent("基础资料-->删除会员积分规则,会员积分规则编号:" + bvrVO.getVipInteReg());
    }

    
    public List<BasVipIntegralRegulationVO> findAll(int page, int rows,
            String order, String sort,int enterprise_id) throws Exception{
        return basVipIntegralRegulationDao.findAll(page, rows, order, sort,enterprise_id);
    }

    
    public List<ReptypeVo> findAllRepairType(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        return basVipIntegralRegulationDao.findAllRepairType(bvrVO);
    }

    
    public List<BasVipLevelVO> findAllVipLevel(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        return basVipIntegralRegulationDao.findAllVipLevel(bvrVO);
    }

    
    public BasRepairType getRepairTypeByID(int id) throws Exception
    {
        return basVipIntegralRegulationDao.getRepairTypeByID(id);
    }

    
    public int getTotle(int enterprise_id) throws Exception
    {
        return basVipIntegralRegulationDao.getTotle(enterprise_id);
    }

    
    public BasVipLevel getVipLevelByID(int id) throws Exception
    {
        return basVipIntegralRegulationDao.getVipLevelByID(id);
    }

    
    @Log(moduleName = "基础资料", opertype = "修改会员积分规则", content = "基础资料-->修改会员积分规则")
    public void update(BasVipIntegralRegulationVO bvrVO) throws Exception
    {
        // 实例化会员积分规则实体
        BasVipIntegralRegulation bvr = new BasVipIntegralRegulation();
        // 给会员积分规则属性赋值
        bvr.setVipInteReg(bvrVO.getVipInteReg()); // 会员积分折扣规则编号
        bvr.setBasVipLevel(basVipIntegralRegulationDao.getVipLevelByID(bvrVO
                .getVipLevelId())); // 会员等级
        // 维修分类编号
        if (bvrVO.getReptypId() == null)
        {
            bvr.setReptypId(null);
        }
        else
        {
            bvr.setReptypId((int) bvrVO.getReptypId());
        }
        bvr.setBeginAmount(bvrVO.getBeginAmount()); // 起始金额
        bvr.setJfAmont(bvrVO.getJfAmont()); // 积分金额
        bvr.setScore(bvrVO.getScore()); // 积分数
        bvr.setEnterpriseId(bvrVO.getEnterpriseId());
        basVipIntegralRegulationDao.merge(bvr);
        setContent("基础资料-->修改会员积分规则,会员积分规则编号:" + bvrVO.getVipInteReg());
    }

    /**
     * 根据会员等级和维修类别获取会员积分规则
     * 
     * @vipLevelId 会员等级编号
     * @reptypId 维修类别编号
     * */
    
    public boolean getByLevelAndReptye(BasVipIntegralRegulationVO bvrVO)
            throws Exception
    {
        return basVipIntegralRegulationDao.getByLevelAndReptye(bvrVO);
    }
}
