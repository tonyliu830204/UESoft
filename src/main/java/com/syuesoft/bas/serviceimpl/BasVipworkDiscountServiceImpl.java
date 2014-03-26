package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasVipworkDiscountDao;
import com.syuesoft.bas.service.BasVipworkDiscountService;
import com.syuesoft.model.BasVipworkDiscount;

/**
 * 会员工时折扣
 * @author HeXin
 *
 */
public class BasVipworkDiscountServiceImpl extends BaseLogServiceImpl implements BasVipworkDiscountService {
	private BasVipworkDiscountDao dao;


	public BasVipworkDiscountDao getDao() {
		return dao;
	}

	public void setDao(BasVipworkDiscountDao dao) {
		this.dao = dao;
	}

	
	@Log(moduleName="基础资料",opertype="新增会员工时折扣",content="基础资料-->新增会员工时折扣")
	public void add(BasVipworkDiscount basVipworkDiscount) {
			dao.Add(basVipworkDiscount);
			setContent("基础资料-->新增会员工时折扣,会员工时折扣名称:"+basVipworkDiscount.getServiceType());
	}

	
	@Log(moduleName="基础资料",opertype="删除会员工时折扣",content="基础资料-->删除会员工时折扣")
	public void delete(BasVipworkDiscount basVipworkDiscount) {
			dao.Delete(basVipworkDiscount);
			setContent("基础资料-->删除会员工时折扣,会员工时折扣名称:"+basVipworkDiscount.getServiceType());
	}

	
	@Log(moduleName="基础资料",opertype="更新会员工时折扣",content="基础资料-->更新会员工时折扣")
    public void update(BasVipworkDiscount basVipworkDiscount)
    {
        dao.Update(basVipworkDiscount);
        setContent("基础资料-->更新会员工时折扣,会员工时折扣名称:"
                + basVipworkDiscount.getServiceType());
    }

	
	public List<BasVipworkDiscount> findAll(int page, int rows) {
		return dao.findAll(page, rows);
	}

	
	public List<BasVipworkDiscount> findByCondition(int page, int rows,
			BasVipworkDiscount basVipworkDiscount) {
		return dao.findByCondition(page, rows, basVipworkDiscount);
	}

	
	public List<BasVipworkDiscount> getTotle() {
		return dao.getTotle();
	}

}
