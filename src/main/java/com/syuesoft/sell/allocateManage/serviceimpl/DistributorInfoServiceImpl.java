package com.syuesoft.sell.allocateManage.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.dao.DistributorInfoDao;
import com.syuesoft.sell.allocateManage.service.DistributorInfoService;
import com.syuesoft.sell.allocateManage.vo.DistributorInfoVo;
import com.syuesoft.sell.model.XsDistributorInfo;

@Service("disService")
public class DistributorInfoServiceImpl implements DistributorInfoService {
	private DistributorInfoDao distributorInfoDao;


	public DistributorInfoDao getDistributorInfoDao() {
		return distributorInfoDao;
	}

	@Autowired
	public void setDistributorInfoDao(DistributorInfoDao distributorInfoDao) {
		this.distributorInfoDao = distributorInfoDao;
	}

	/**
	 * 查询分销商信息
	 */
	
	public Datagrid getDistributorInfo(DistributorInfoVo disVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsDistributorInfo info where info.enterprise_id="+disVo.getEnterprise_id());
		if(disVo.getDistributorName()!=null&&!("".equals(disVo.getDistributorName()))){
			hql.append(" and info.distributorName like '%"+StringEscapeUtils.escapeSql(disVo.getDistributorName().trim())+"%'");
						
		}
		List<XsDistributorInfo> dis=distributorInfoDao.find(hql.toString(),disVo.getPage(),disVo.getRows());
		List<DistributorInfoVo> rows = new ArrayList<DistributorInfoVo>();
		if (dis != null && dis.size() > 0) {
			for (XsDistributorInfo xs : dis) {
				DistributorInfoVo dvo = new DistributorInfoVo();
				BeanUtils.copyProperties(xs, dvo);
				rows.add(dvo);
			}

		}
		int total=distributorInfoDao.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

}
