package com.syuesoft.sell.base.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.InsurerInfoDAO;
import com.syuesoft.sell.base.service.InsurerInfoService;
import com.syuesoft.sell.base.vo.InsurerInfoVo;
import com.syuesoft.sell.model.XsInsurerInfo;
import com.syuesoft.util.CreateID;

@Service("insurerInfoService")
public class InsurerInfoServiceImpl extends BaseLogServiceImpl implements InsurerInfoService {
	private InsurerInfoDAO insurerInfoDAO;
	
	public Datagrid getPager(InsurerInfoVo insurerInfoVo) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsInsurerInfo InsurerInfo where 1=1 and " +
				"InsurerInfo.enterprise_id="+insurerInfoVo.getEnterprise_id());
		if(insurerInfoVo.getInsName()!=null && !("".equals(insurerInfoVo.getInsName()))){
			hql.append(" and InsurerInfo.insurerName like '%"+StringEscapeUtils.escapeSql(insurerInfoVo.getInsName().trim())+"%'");
		}
		if(insurerInfoVo.getInsurerName()!=null && !("".equals(insurerInfoVo.getInsurerName()))){
			hql.append(" and InsurerInfo.insurerName like '%"+StringEscapeUtils.escapeSql(insurerInfoVo.getInsurerName().trim())+"%'");
		}
		List<XsInsurerInfo> lst=insurerInfoDAO.find(hql.toString(), insurerInfoVo.getPage(), insurerInfoVo.getRows());
		List<InsurerInfoVo> rows =new ArrayList<InsurerInfoVo>();
		if(lst!=null && lst.size()>0){
			for(XsInsurerInfo insurerInfo:lst){
				InsurerInfoVo insurerVo=new InsurerInfoVo();
				BeanUtils.copyProperties(insurerInfo, insurerVo);
				rows.add(insurerVo);
			}
		}
		int total = insurerInfoDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="保险公司档案",opertype="删除")
	public void deleteInsurerInfo(InsurerInfoVo insurerInfoVo) throws Exception{	
		XsInsurerInfo xsInsurerInfo=new XsInsurerInfo();
		BeanUtils.copyProperties(insurerInfoVo, xsInsurerInfo);
		insurerInfoDAO.delete(xsInsurerInfo);
		setContent("删除【保险公司档案】编码为【"+xsInsurerInfo.getInsurerCode()+"】," +
				"名称为【"+xsInsurerInfo.getInsurerName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="保险公司档案",opertype="新增")
	public void addInsurerInfo(InsurerInfoVo insurerInfoVo) throws Exception{
		XsInsurerInfo xsInsurerInfo=new XsInsurerInfo();
		BeanUtils.copyProperties(insurerInfoVo, xsInsurerInfo);
		xsInsurerInfo.setInsurerCode(CreateID.createId("InsurerInfo", Contstants.SELL_BILLSDEPLOY.INSURERINFOR));
		insurerInfoDAO.save(xsInsurerInfo);	
		setContent("新增【保险公司档案】编码为【"+xsInsurerInfo.getInsurerCode()+"】," +
				"名称为【"+xsInsurerInfo.getInsurerName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="保险公司档案",opertype="修改")
	public void updateInsurerInfo(InsurerInfoVo insurerInfoVo) throws Exception{
		XsInsurerInfo xsInsurerInfo=new XsInsurerInfo();
		BeanUtils.copyProperties(insurerInfoVo, xsInsurerInfo);
		insurerInfoDAO.merge(xsInsurerInfo);
		setContent("修改编码为【"+xsInsurerInfo.getInsurerCode()+"】的【保险公司档案】," +
				"名称为【"+xsInsurerInfo.getInsurerName()+"】！");
	}
	public Boolean findInsurerTwo(String insurerCode,Integer enid) throws Exception{
		if(insurerCode!=null && !("".equals(insurerCode))){
			String hql="from XsInsurerInfo InsurerInfo where  " +
					"InsurerInfo.insurerName='"+insurerCode+"' and InsurerInfo.enterprise_id="+enid;
			XsInsurerInfo insurer=insurerInfoDAO.get(hql);
			if(insurer!=null){
				return true;
			}
		}
		return false;
	}
	public Boolean findInsurer(String insurerCode,Integer id,Integer enid) throws Exception{
		if(insurerCode!=null && !("".equals(insurerCode))){
			String hql="from XsInsurerInfo InsurerInfo where 1=1 and " +
					"InsurerInfo.insurerName='"+insurerCode+"' " +
							"and InsurerInfo.insurerId!='"+id+"'" +
							" and InsurerInfo.enterprise_id="+enid;
			XsInsurerInfo insurer=insurerInfoDAO.get(hql);
			if(insurer!=null){
				return true;
			}
		}
		return false;
	}
	public InsurerInfoDAO getInsurerInfoDAO() throws Exception{
		return insurerInfoDAO;
	}
	@Autowired
	public void setInsurerInfoDAO(InsurerInfoDAO insurerInfoDAO) {
		this.insurerInfoDAO = insurerInfoDAO;
	}


}
