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
import com.syuesoft.sell.base.dao.DbProjectDAO;
import com.syuesoft.sell.base.service.DbProjectService;
import com.syuesoft.sell.base.vo.DbProjectVo;
import com.syuesoft.sell.model.XsDbProject;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;

@Service("dbProjectService")
public class DbProjectServiceImpl extends BaseLogServiceImpl implements DbProjectService {
	private DbProjectDAO dbProjectDAO;
	
	public Datagrid getPager(DbProjectVo xsDbProject)  throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsDbProject dbPro where 1=1 and dbPro.enterpriseId="+xsDbProject.getEnterpriseId());
		if(xsDbProject.getProCode()!=null &&!("".equals(xsDbProject.getProCode()))){
			hql.append(" and dbPro.projectCode like '%"+StringEscapeUtils.escapeSql(xsDbProject.getProCode().trim())+"%'");
		}
		if(xsDbProject.getProName()!=null &&!("".equals(xsDbProject.getProName()))){
			hql.append(" and dbPro.projectName like '%"+StringEscapeUtils.escapeSql(xsDbProject.getProName().trim())+"%'");
		}
		List<XsDbProject> lst=dbProjectDAO.find(hql.toString(), xsDbProject.getPage(), xsDbProject.getRows());
		List<DbProjectVo> rows =new ArrayList<DbProjectVo>();
		if(lst!=null && lst.size()>0){
			for(XsDbProject dbPro:lst){
				DbProjectVo dbProjectVo=new DbProjectVo();
				BeanUtils.copyProperties(dbPro, dbProjectVo);
				rows.add(dbProjectVo);
			}
		}
		int total = dbProjectDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public Msg deleteDbProject(DbProjectVo xsDbProject)  throws Exception{	
		Msg msg=new Msg();
		List list=dbProjectDAO.find(" from XsSellDbProject dbProjectId="+xsDbProject.getProjectId()+" and " +
				" xsCarSellInfo.enterpriseId="+xsDbProject.getEnterpriseId());
		if(list!=null&&list.size()>0){
			msg.setMsg("该代办项目已被销售代办项目使用，不可以删除");
			msg.setSuccess(false);
		}else{
			XsDbProject dbProject=new XsDbProject();
			BeanUtils.copyProperties(xsDbProject, dbProject);
			dbProjectDAO.delete(dbProject);
			setContent("删除父级菜单为【代办项目】编码为【"+dbProject.getProjectCode()+"】," +
					"名称为【"+dbProject.getProjectName()+"】的信息！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addDbProject(DbProjectVo xsDbProject)  throws Exception{
		XsDbProject dbProject=new XsDbProject();
		BeanUtils.copyProperties(xsDbProject, dbProject);
		dbProject.setProjectCode(CreateID.createId("db",Contstants.SELL_BILLSDEPLOY.DBPROJECT));
		dbProjectDAO.save(dbProject);
		setContent("给父级菜单为【代办项目】新增信息,编码为【"+dbProject.getProjectCode()+"】," +
				"名称为【"+dbProject.getProjectName()+"】,代办成本为【"+dbProject.getProjectMomay()+"】," +
						"收费金额为【"+dbProject.getProjectAmount()+"】," +
						"收费部分为【"+dbProject.getProjectDept()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateDbProject(DbProjectVo xsDbProject)  throws Exception{
		XsDbProject dbProject=new XsDbProject();
		BeanUtils.copyProperties(xsDbProject, dbProject);
		dbProjectDAO.merge(dbProject);	
		setContent("修改父级菜单为【代办项目】编码为【"+dbProject.getProjectCode()+"】的信息," +
				"修改名称为【"+dbProject.getProjectName()+"】,代办成本为【"+dbProject.getProjectMomay()+"】," +
						"收费金额为【"+dbProject.getProjectAmount()+"】," +
						"收费部分为【"+dbProject.getProjectDept()+"】！");
	}
	public boolean findDbProTwo(String proCode,String dname,Integer enid) throws Exception{
		String hql="";
		if(proCode!=null && !("".equals(proCode))){
			 hql="from XsDbProject dbPro where  dbPro.projectCode='"+proCode+"'" +
					" or dbPro.projectName='"+dname+"'";
		}else if(dname!=null && !"".equals(dname)){
			hql="from XsDbProject dbPro where  dbPro.projectName='"+dname+"'";
		}
		
		if(enid!=null&&!"".equals(enid)){
			hql+=" and dbPro.enterpriseId="+enid;
			}
			XsDbProject dbPro=dbProjectDAO.get(hql);
			if(dbPro!=null){
				return true;
			}
		
		return false;
	}
	public boolean findDbPro(String proCode,String name,Integer id,Integer enid) throws Exception{
		if(proCode!=null && !("".equals(proCode))){
			String hql="from XsDbProject dbPro where 1=1 and " +
					"(dbPro.projectCode='"+proCode+"' or dbPro.projectName='"+name+"') " +
							"and dbPro.projectId!='"+id+"' and dbPro.enterpriseId="+enid ;
			XsDbProject dbPro=dbProjectDAO.get(hql);
			if(dbPro!=null){
				return true;
			}
		}
		return false;
	}
	public DbProjectDAO getDbProjectDAO() {
		return dbProjectDAO;
	}
	@Autowired
	public void setDbProjectDAO(DbProjectDAO dbProjectDAO) {
		this.dbProjectDAO = dbProjectDAO;
	}


}
