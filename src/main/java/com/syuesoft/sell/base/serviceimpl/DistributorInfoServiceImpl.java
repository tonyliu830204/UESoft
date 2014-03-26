package com.syuesoft.sell.base.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.DistributorInfoDAO;
import com.syuesoft.sell.base.service.DistributorInfoService;
import com.syuesoft.sell.base.vo.DistributorInfoVo;
import com.syuesoft.sell.model.XsDistributorInfo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;



@Service("distributorInfoService")
public class DistributorInfoServiceImpl extends BaseLogServiceImpl implements DistributorInfoService {
	private DistributorInfoDAO distributorInfoDAO;
	
	public Datagrid getPager(DistributorInfoVo distributorInfoVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsDistributorInfo disInfo where 1=1 and " +
				"disInfo.enterprise_id="+distributorInfoVo.getEnterprise_id());
		String disName=distributorInfoVo.getDisName();
		if(disName!=null && !("".equals(disName))){
			hql.append(" and  disInfo.distributorName like '%"+StringEscapeUtils.escapeSql(disName.trim())+"%'");
		}
		List<XsDistributorInfo> lst=distributorInfoDAO.find(hql.toString(), distributorInfoVo.getPage(), distributorInfoVo.getRows());
		List<DistributorInfoVo> rows =new ArrayList<DistributorInfoVo>();
		if(lst!=null && lst.size()>0){
			for(XsDistributorInfo disInfo:lst){
				DistributorInfoVo disVo=new DistributorInfoVo();	
				BeanUtils.copyProperties(disInfo, disVo);
				rows.add(disVo);
			}	
		}
		int total = distributorInfoDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;	
	}
	
	@Log(systemName="销售系统", moduleName="分销商档案",opertype="删除")
	public void deleteDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception {	
		XsDistributorInfo distributorInfo=new XsDistributorInfo();
		BeanUtils.copyProperties(distributorInfoVo, distributorInfo);
		distributorInfoDAO.delete(distributorInfo);
		setContent("删除【分销商档案】编码为【"+distributorInfo.getDistributorCode()+"】," +
				"名称为【"+distributorInfo.getDistributorName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="分销商档案",opertype="新增")
	public void addDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception {
		XsDistributorInfo distributorInfo=new XsDistributorInfo();
		BeanUtils.copyProperties(distributorInfoVo, distributorInfo);
		distributorInfo.setDistributorCode(CreateID.createId("DistributorInfo", Contstants.SELL_BILLSDEPLOY.DISTRIBUTORINFOR ));
		distributorInfoDAO.save(distributorInfo);	
		setContent("新增【分销商档案】编码为【"+distributorInfo.getDistributorCode()+"】," +
				"名称为【"+distributorInfo.getDistributorName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="分销商档案",opertype="修改")
	public void updateDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception {
		XsDistributorInfo distributorInfo=new XsDistributorInfo();
		BeanUtils.copyProperties(distributorInfoVo, distributorInfo);
		distributorInfoDAO.merge(distributorInfo);	
		setContent("修改编码为【"+distributorInfo.getDistributorCode()+"】的【分销商档案】," +
				"名称为【"+distributorInfo.getDistributorName()+"】！");
	}
	
	public List<ComboBoxJson> findAllInfo(Integer enterprise_id)throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsDistributorInfo> bctList = distributorInfoDAO.findAllInfo(enterprise_id);
		if(bctList != null && bctList.size() > 0){
			for(XsDistributorInfo info : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(info.getDistributorId().toString());
				json.setText(info.getDistributorName());
				list.add(json);
			}
		}
		return list;
	}
	public Boolean  findDistributorTwo(String distributorCode,Integer enid) throws Exception{
		if(distributorCode!=null && !("".equals(distributorCode))){
			String hql="from XsDistributorInfo disInfo where  " +
					"disInfo.distributorName='"+distributorCode+"' and disInfo.enterprise_id="+enid;
			
			XsDistributorInfo  dis=distributorInfoDAO.get(hql);
			if(dis!=null){
				return true;
			}	
		}
		return false;		
	}
	public Boolean  findDistributor(String distributorCode,Integer distributorId,Integer enid) throws Exception{
		if(distributorCode!=null && !("".equals(distributorCode))){
			String hql="from XsDistributorInfo disInfo where 1=1 and disInfo.distributorName='"+distributorCode+"' and " +
					"disInfo.distributorId!='"+distributorId+"' and disInfo.enterprise_id="+enid;
			XsDistributorInfo  dis=distributorInfoDAO.get(hql);
			if(dis!=null){
				return true;
			}	
		}
		return false;		
	}
	public DistributorInfoDAO getDistributorInfoDAO() {
		return distributorInfoDAO;
	}
	@Autowired
	public void setDistributorInfoDAO(DistributorInfoDAO distributorInfoDAO) {
		this.distributorInfoDAO = distributorInfoDAO;
	}
	
	
}
