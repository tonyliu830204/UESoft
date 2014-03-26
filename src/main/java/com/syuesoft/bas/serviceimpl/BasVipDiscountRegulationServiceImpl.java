package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipDiscountRegulationDao;
import com.syuesoft.bas.service.BasVipDiscountRegulationService;
import com.syuesoft.base.vo.BasVipDiscountRegulationVO;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.BasVipDiscountRegulation;

/**
 * 会员折扣规则
 * @author HeXin
 *
 */
@Service("basVipDiscountRegulationService")
public class BasVipDiscountRegulationServiceImpl extends BaseLogServiceImpl implements
		BasVipDiscountRegulationService {
	@Autowired
	private BasVipDiscountRegulationDao basVipDiscountRegulationDao;

	
	@Log(moduleName="基础资料",opertype="新增会员折扣规则",content="基础资料-->新增会员折扣规则")
	public void add(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
            throws Exception
    {
        BasVipDiscountRegulation bvr = new BasVipDiscountRegulation();
        bvr.setVipDisRegId(basVipDiscountRegulationVO.getVipDisRegId());
        bvr.setBasVipLevel(basVipDiscountRegulationDao.getVipLevelByID(basVipDiscountRegulationVO.getVipLevelId()));
        if (basVipDiscountRegulationVO.getReptypId() == null)
            bvr.setReptypId(null);
        else
            bvr.setReptypId((int) basVipDiscountRegulationVO.getReptypId());
        bvr.setWorkRegDiscount(basVipDiscountRegulationVO.getWorkRegDiscount());
        bvr.setMaterialRegDiscount(basVipDiscountRegulationVO.getMaterialRegDiscount());
        bvr.setTotalRegDiscount(basVipDiscountRegulationVO.getTotalRegDiscount());
        bvr.setEnterpriseId(basVipDiscountRegulationVO.getEnterpriseId());
        basVipDiscountRegulationDao.add(bvr);
        setContent("基础资料-->新增会员折扣规则,会员折扣规则名称:"+ basVipDiscountRegulationVO.getReptypName());
    }

	
	@Log(moduleName="基础资料",opertype="删除会员折扣规则",content="基础资料-->删除会员折扣规则")
	public void delete(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
			throws Exception {
		if(basVipDiscountRegulationVO.getReptypName()!=null)
			basVipDiscountRegulationVO.setReptypName(new String(basVipDiscountRegulationVO.getReptypName().getBytes("ISO-8859-1"),"UTF-8"));
		basVipDiscountRegulationDao.delte(basVipDiscountRegulationVO);
		setContent("基础资料-->删除会员折扣规则,会员折扣规则名称:"+basVipDiscountRegulationVO.getReptypName());
	}

	
	public List<BasVipDiscountRegulationVO> findAll(int page, int rows,String order,String sort,int enterprise_id)
			throws Exception {
		return basVipDiscountRegulationDao.findAll(page, rows,order,sort,enterprise_id);
	}

	
	public int getTotle(int enterprise_id) throws Exception {
		return basVipDiscountRegulationDao.getTotle(enterprise_id);
	}

	
	@Log(moduleName="基础资料",opertype="更新会员折扣规则",content="基础资料-->更新会员折扣规则")
	public void update(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
			throws Exception {
		BasVipDiscountRegulation bvr = new BasVipDiscountRegulation();
		bvr.setVipDisRegId(basVipDiscountRegulationVO.getVipDisRegId());
		bvr.setBasVipLevel(basVipDiscountRegulationDao.getVipLevelByID(basVipDiscountRegulationVO.getVipLevelId()));
		if(basVipDiscountRegulationVO.getReptypId() == null){
			bvr.setReptypId(null);
		}else{
			bvr.setReptypId((int)basVipDiscountRegulationVO.getReptypId());
		}
		bvr.setWorkRegDiscount(basVipDiscountRegulationVO.getWorkRegDiscount());
		bvr.setMaterialRegDiscount(basVipDiscountRegulationVO.getMaterialRegDiscount());
		bvr.setTotalRegDiscount(basVipDiscountRegulationVO.getTotalRegDiscount());
		bvr.setEnterpriseId(basVipDiscountRegulationVO.getEnterpriseId());
		basVipDiscountRegulationDao.merge(bvr);
		setContent("基础资料-->更新会员折扣规则,会员折扣规则名称:"+basVipDiscountRegulationVO.getReptypName());
	}

	
	public List<ReptypeVo> findAllRepairType(BasVipDiscountRegulationVO basVipDiscountRegulationVO) throws Exception {
		return basVipDiscountRegulationDao.findAllRepairType(basVipDiscountRegulationVO);
	}

	
	public List<BasVipLevelVO> findAllVipLevel(BasVipDiscountRegulationVO basVipDiscountRegulationVO) throws Exception {
		return basVipDiscountRegulationDao.findAllVipLevel(basVipDiscountRegulationVO);
	}

	
	public boolean getByLevelAndReptype(BasVipDiscountRegulationVO basVipDiscountRegulationVO)
			throws Exception {
		return basVipDiscountRegulationDao.getByLevelAndReptype(basVipDiscountRegulationVO);
	}
}
