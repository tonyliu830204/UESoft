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
import com.syuesoft.sell.base.dao.ZsProjectDAO;
import com.syuesoft.sell.base.service.ZsProjectService;
import com.syuesoft.sell.base.vo.ZsProjectVo;
import com.syuesoft.sell.model.XsZsProject;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;

@Service("zsProjectService")
public class ZsProjectServiceImpl extends BaseLogServiceImpl implements ZsProjectService {
	private ZsProjectDAO zsProjectDAO;
	
	public Datagrid getPager(ZsProjectVo xsZsProject)throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsZsProject zsPro where 1=1 and zsPro.enterpriseId="+xsZsProject.getEnterpriseId());
		if(xsZsProject.getZsProCode()!=null &&!("".equals(xsZsProject.getZsProCode()))){
			hql.append(" and zsPro.projectCode like '%"+StringEscapeUtils.escapeSql(xsZsProject.getZsProCode().trim())+"%'");
		}
		if(xsZsProject.getZsProName()!=null &&!("".equals(xsZsProject.getZsProName()))){
			hql.append(" and zsPro.projectName like '%"+StringEscapeUtils.escapeSql(xsZsProject.getZsProName().trim())+"%'");
		}
		List<XsZsProject> lst=zsProjectDAO.find(hql.toString(), xsZsProject.getPage(), xsZsProject.getRows());
		List<ZsProjectVo> rows =new ArrayList<ZsProjectVo>();
		if(lst!=null && lst.size()>0){
			for(XsZsProject zsPro:lst){
				ZsProjectVo proVo=new ZsProjectVo();
				BeanUtils.copyProperties(zsPro, proVo);
				rows.add(proVo);
			}
		}
		int total = zsProjectDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public Msg deleteZsProject(ZsProjectVo xsZsProject) throws Exception{
		Msg msg=new Msg();
		List list=zsProjectDAO.find(" from XsSellZhProject where zhProject="+xsZsProject.getZsprojectId()+" " +
				"and xsCarSellInfo.enterpriseId="+xsZsProject.getEnterpriseId());
		if(list!=null&&list.size()>0){
			msg.setMsg("该项目已被装潢(销售及赠送)使用，不可以删除！");
			msg.setSuccess(false);
		}else{
			XsZsProject zsPro=new XsZsProject();
			BeanUtils.copyProperties(xsZsProject, zsPro);
			zsProjectDAO.delete(zsPro);
			setContent("删除父级菜单为【赠送项目】编码为【"+zsPro.getProjectCode()+"】," +
					"名称为【"+zsPro.getProjectName()+"】的信息！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addZsProject(ZsProjectVo xsZsProject)throws Exception{
		XsZsProject zsPro=new XsZsProject();
		BeanUtils.copyProperties(xsZsProject, zsPro);
		zsPro.setProjectCode(CreateID.createId("zsPro",  Contstants.SELL_BILLSDEPLOY.ZSPROJECT));
		zsProjectDAO.save(zsPro);
		setContent("给父级菜单【赠送项目】新增信息，编码为【"+zsPro.getProjectCode()+"】," +
				"名称为【"+zsPro.getProjectName()+"】,成本价为【"+zsPro.getProjectCostamount()+"】," +
				"销售价为【"+zsPro.getProjectSellamount()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateZsProject(ZsProjectVo xsZsProject) throws Exception{
		XsZsProject zsPro=new XsZsProject();
		BeanUtils.copyProperties(xsZsProject, zsPro);
		zsProjectDAO.merge(zsPro);
		setContent("修改父级菜单为【赠送项目】编码为【"+zsPro.getProjectCode()+"】的信息," +
				"修改名称为【"+zsPro.getProjectName()+"】,成本价为【"+zsPro.getProjectCostamount()+"】," +
				"销售价为【"+zsPro.getProjectSellamount()+"】！");

	}
	public boolean findZsProTwo(String proCode ,Integer enid) throws Exception{
		if(proCode!=null && !("".equals(proCode))){
			String hql="from XsZsProject zsPro where " +
					" zsPro.projectName='"+proCode+"' and zsPro.enterpriseId="+enid;
			XsZsProject zsPro=zsProjectDAO.get(hql);
			if(zsPro!=null){
				return true;
			}
		}
		return false;
	}
	public boolean findZsPro(String proCode,int id,Integer enid) throws Exception{
		if(proCode!=null && !("".equals(proCode))){
			String hql="from XsZsProject zsPro where 1=1 and zsPro.projectName='"+proCode+"' " +
					"and zsPro.zsprojectId!='"+id+"' and zsPro.enterpriseId= "+enid;
			XsZsProject zsPro=zsProjectDAO.get(hql);
			if(zsPro!=null){
				return true;
			}
		}
		return false;
	}
	public ZsProjectDAO getZsProjectDAO() {
		return zsProjectDAO;
	}
	@Autowired
	public void setZsProjectDAO(ZsProjectDAO zsProjectDAO) {
		this.zsProjectDAO = zsProjectDAO;
	}



}
