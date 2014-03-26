package com.syuesoft.bas.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCustomerComplaintsDao;
import com.syuesoft.bas.service.BasCustomerComplaintsService;
import com.syuesoft.model.BasCustomerComplaints;
import com.syuesoft.util.Json;

/**
 * 客户投诉分类
 * @author HeXin
 *
 */
public class BasCustomerComplaintsServiceImpl extends BaseLogServiceImpl implements BasCustomerComplaintsService {
	private BasCustomerComplaintsDao dao;


	
	@Log(moduleName="基础资料",opertype="新增地区设定",content="基础资料-->新增地区设定")
	public boolean add(BasCustomerComplaints basCustomerComplaints) throws Exception{
		boolean tag = dao.Add(basCustomerComplaints);
		setContent("基础资料-->新增地区设定 ,地区设定名称:"+basCustomerComplaints.getComplaintsType());
		return tag;
	}

	
	@Log(moduleName="基础资料",opertype="删除地区设定",content="基础资料-->删除地区设定")
	public void delete(BasCustomerComplaints basCustomerComplaints) throws Exception{
		BasCustomerComplaints bcc=dao.get(BasCustomerComplaints.class, basCustomerComplaints.getComplaintsId());
		if(bcc!=null){
			dao.delete(bcc);
			setContent("基础资料-->删除地区设定 ,地区设定名称:"+bcc.getComplaintsType());
		}
	}

	
	@Log(moduleName="基础资料",opertype="更新地区设定",content="基础资料-->更新地区设定")
	public boolean update(BasCustomerComplaints basCustomerComplaints) throws Exception{
        boolean tag = dao.Update(basCustomerComplaints);
			setContent("基础资料-->更新地区设定 ,地区设定名称:"+basCustomerComplaints.getComplaintsType());
			return tag;
	}

	
	public Json findAll(int page, int rows ,String sort,String order,int enterprise_id)throws Exception {
		return dao.findAll(page, rows,sort ,order,enterprise_id);
	}

	
	public List<BasCustomerComplaints> findByCondition(int page, int rows,
			BasCustomerComplaints basCustomerComplaints) throws Exception{
		return dao.findByCondition(page, rows, basCustomerComplaints);
	}

	public BasCustomerComplaintsDao getDao() {
		return dao;
	}

	public void setDao(BasCustomerComplaintsDao dao) {
		this.dao = dao;
	}

	
	public List<BasCustomerComplaints> getTotle() throws Exception{
		return dao.getTotle();
	}

}
