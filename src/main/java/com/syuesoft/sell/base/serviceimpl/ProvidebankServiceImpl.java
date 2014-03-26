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
import com.syuesoft.sell.base.dao.ProvidebankDAO;
import com.syuesoft.sell.base.service.ProvidebankService;
import com.syuesoft.sell.base.vo.ProvidebankVo;
import com.syuesoft.sell.model.XsProvidebank;
import com.syuesoft.util.CreateID;

@Service("providebankService")
public class ProvidebankServiceImpl extends BaseLogServiceImpl implements ProvidebankService {
	private ProvidebankDAO providebankDAO;
	
	public Datagrid getPager(ProvidebankVo   providebankVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsProvidebank pBank where 1=1 " +
				"and pBank.enterprise_id="+providebankVo.getEnterprise_id());
		if(providebankVo.getBankName()!=null &&  !("".equals(providebankVo.getBankName()))){
			hql.append(" and  pBank.providebankName like '%"+StringEscapeUtils.escapeSql(providebankVo.getBankName().trim())+"%'");
		}
		List<XsProvidebank> lst=providebankDAO.find(hql.toString(), providebankVo.getPage(), providebankVo.getRows());
		List<ProvidebankVo> rows =new ArrayList<ProvidebankVo>();
		if(lst!=null && lst.size()>0){
			for(XsProvidebank bank:lst){
				ProvidebankVo bankVo=new ProvidebankVo();
				BeanUtils.copyProperties(bank, bankVo);
				rows.add(bankVo);
			}	
		}
		int total = providebankDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;	
	}
	
	@Log(systemName="销售系统", moduleName="贷款银行档案",opertype="删除")
	public void deleteProvidebank(ProvidebankVo   providebankVo) throws Exception {	
		XsProvidebank xsProvidebank=new XsProvidebank();
		BeanUtils.copyProperties(providebankVo, xsProvidebank);
		providebankDAO.delete(xsProvidebank);
		setContent("删除【贷款银行档案】编码为【"+xsProvidebank.getProvidebankCode()+"】," +
				"名称为【"+xsProvidebank.getProvidebankName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="贷款银行档案",opertype="新增")
	public void addProvidebank(ProvidebankVo   providebankVo) throws Exception {
		XsProvidebank xsProvidebank=new XsProvidebank();
		BeanUtils.copyProperties(providebankVo, xsProvidebank);
		xsProvidebank.setProvidebankCode(CreateID.createId("Providebank", Contstants.SELL_BILLSDEPLOY.PROVIDEBANK));
		providebankDAO.save(xsProvidebank);	
		setContent("新增【贷款银行档案】编码为【"+xsProvidebank.getProvidebankCode()+"】," +
				"名称为【"+xsProvidebank.getProvidebankName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="贷款银行档案",opertype="修改")
	public void updateProvidebank(ProvidebankVo   providebankVo) throws Exception {
		XsProvidebank xsProvidebank=new XsProvidebank();
		BeanUtils.copyProperties(providebankVo, xsProvidebank);
		providebankDAO.merge(xsProvidebank);		
		setContent("修改编码为【"+xsProvidebank.getProvidebankCode()+"】的【贷款银行档案】," +
				"名称为【"+xsProvidebank.getProvidebankName()+"】！");
	}
	public  Boolean existBankTwo(String bankCode, Integer enid) throws Exception{
		if(bankCode!=null && !("".equals(bankCode))){
			String hql="from XsProvidebank pBank where " +
					" pBank.providebankName='"+bankCode+"' and pBank.enterprise_id="+enid;
			XsProvidebank bank=providebankDAO.get(hql);
			if(bank!=null){
				return true;
			}	
		}
		return false;
	}
	public  Boolean existBank(String bankCode,Integer id,Integer enid) throws Exception{
		if(bankCode!=null && !("".equals(bankCode))){
			String hql="from XsProvidebank pBank where 1=1 and pBank.providebankName='"+bankCode+"' and pBank.id!='"+id+"'" +
					" and pBank.enterprise_id="+enid;
			XsProvidebank bank=providebankDAO.get(hql);
			if(bank!=null){
				return true;
			}	
		}
		return false;
	}
	public ProvidebankDAO getProvidebankDAO() {
		return providebankDAO;
	}
	@Autowired
	public void setProvidebankDAO(ProvidebankDAO providebankDAO) {
		this.providebankDAO = providebankDAO;
	}
	

}
