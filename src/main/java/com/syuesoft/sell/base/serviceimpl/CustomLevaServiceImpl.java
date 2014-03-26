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
import com.syuesoft.sell.base.dao.CustomLevaDAO;
import com.syuesoft.sell.base.service.CustomLevaService;
import com.syuesoft.sell.base.vo.CustomLevaVo;
import com.syuesoft.sell.model.XsCustomLeva;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;

@Service("customLevaService")
public class CustomLevaServiceImpl extends BaseLogServiceImpl implements CustomLevaService {
	private CustomLevaDAO customLevaDAO ;
	
	public Datagrid getPager(CustomLevaVo leva) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsCustomLeva custom where 1=1 " +
				"and custom.enterpriseId= "+leva.getEnterpriseId());
		if(leva.getLevaC()!=null &&!("".equals(leva.getLevaC()))){
			hql.append(" and custom.levaCode like'%"+StringEscapeUtils.escapeSql(leva.getLevaC().trim())+"%'");
		}
		if(leva.getLevaN()!=null &&!("".equals(leva.getLevaN()))){
			hql.append(" and custom.levaName like'%"+StringEscapeUtils.escapeSql(leva.getLevaN().trim())+"%'");
		}
		List<XsCustomLeva> lst=customLevaDAO.find(hql.toString(), leva.getPage(), leva.getRows());
		List<CustomLevaVo> rows =new ArrayList<CustomLevaVo>();
		if(lst!=null && lst.size()>0){
			for(XsCustomLeva custom:lst){
				CustomLevaVo cunstomVo=new CustomLevaVo();
				BeanUtils.copyProperties(custom, cunstomVo);
				rows.add(cunstomVo);
			}
		}
		int total = customLevaDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public void deleteCustom(CustomLevaVo leva)  throws Exception{	
		XsCustomLeva xLeva=new XsCustomLeva();
		BeanUtils.copyProperties(leva, xLeva);
		 customLevaDAO.delete(xLeva);
		 setContent("删除父级菜单为【潜在客户等级】编码为【"+xLeva.getLevaCode()+"】," +
					"名称为【"+xLeva.getLevaName()+"】的信息！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addCustom(CustomLevaVo leva) throws Exception {
		XsCustomLeva xLeva=new XsCustomLeva();
		BeanUtils.copyProperties(leva, xLeva);
		xLeva.setLevaCode(CreateID.createId("lev", Contstants.SELL_BILLSDEPLOY.CUSTOMLEVA));
		customLevaDAO.save(xLeva);
		setContent("给父级菜单【潜在客户等级】新增信息,编码为【"+xLeva.getLevaCode()+"】," +
				"名称为【"+xLeva.getLevaName()+"】,间隔天数为【"+xLeva.getJianGe()+"】的子项！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateCustom(CustomLevaVo leva) throws Exception {
		XsCustomLeva xLeva=new XsCustomLeva();
		BeanUtils.copyProperties(leva, xLeva);
		customLevaDAO.merge(xLeva);	
		setContent("修改父级菜单为【潜在客户等级】编码为【"+xLeva.getLevaCode()+"】的信息," +
				"修改名称为【"+xLeva.getLevaName()+"】,间隔天数为【"+xLeva.getJianGe()+"】！");
	}
	public Boolean  findLevaTwo(String levaCode,String name,Integer enid) throws Exception{
		String  hql="";
		if(levaCode!=null && !("".equals(levaCode))){
			  hql="from XsCustomLeva custom where  custom.levaCode ='"+levaCode+"'" +
					" or custom.levaName='"+name+"'";
		}else if(name!=null && !("".equals(name))){
			hql="from XsCustomLeva custom where   custom.levaName='"+name+"'";
		}
			
		if(enid!=null&&!"".equals(enid)){
				hql+=" and custom.enterpriseId="+enid;
				
			}
			XsCustomLeva leva=customLevaDAO.get(hql);
			if(leva!=null ){
				return true;
			}		
		
		return false;
	}
	public Boolean  findLeva(String levaCode,String name,Integer id,Integer enid) throws Exception{
		if(levaCode!=null && !("".equals(levaCode))){
			String  hql="from XsCustomLeva custom where 1=1 and ( custom.levaCode ='"+levaCode+"' " +
					" or custom.levaName='"+name+"')" +
					"and custom.id!='"+id+"'" +
					" and  custom.enterpriseId="+enid;
			XsCustomLeva leva=customLevaDAO.get(hql);
			if(leva!=null ){
				return true;
			}		
		}
		return false;
	}
	public CustomLevaDAO getCustomLevaDAO() {
		return customLevaDAO;
	}
	@Autowired
	public void setCustomLevaDAO(CustomLevaDAO customLevaDAO) {
		this.customLevaDAO = customLevaDAO;
	}
	
	public List<ComboBoxJson> findAllLeva(CustomLevaVo leva1) throws Exception {
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsCustomLeva> bctList = customLevaDAO.findAllLeva(leva1);
		if(bctList != null && bctList.size() > 0){
			for(XsCustomLeva leva : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(leva.getLevaId().toString());
				json.setText(leva.getLevaName());
				list.add(json);
			}
		}
		return list;
	}
}
