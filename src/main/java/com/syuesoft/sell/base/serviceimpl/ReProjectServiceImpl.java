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
import com.syuesoft.sell.base.dao.ReProjectDAO;
import com.syuesoft.sell.base.dao.RepayDAO;
import com.syuesoft.sell.base.service.ReProjectService;
import com.syuesoft.sell.base.vo.RepayProjectVo;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.sell.model.XsRepay;
import com.syuesoft.sell.model.XsRepayProject;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;
import com.syuesoft.util.Msg;

@Service("reProjectService")
public class ReProjectServiceImpl extends BaseLogServiceImpl implements ReProjectService {
	private ReProjectDAO reProjectDAO;
	private RepayDAO repayDAO;
	
	public Datagrid getPager(RepayProjectVo xsRepayProject) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsRepayProject repro where 1=1 and repro.enterpriseId="+xsRepayProject.getEnterpriseId());
		if(xsRepayProject.getReProCode()!=null &&!("".equals(xsRepayProject.getReProCode()))){
			hql.append(" and repro.projectCode like '%"+StringEscapeUtils.escapeSql(xsRepayProject.getReProCode().trim())+"%'");
		}
		if(xsRepayProject.getReProName()!=null &&!("".equals(xsRepayProject.getReProName()))){
			hql.append(" and repro.projectName like '%"+StringEscapeUtils.escapeSql(xsRepayProject.getReProName().trim())+"%'");
		}
		List<XsRepayProject> lst=reProjectDAO.find(hql.toString(), xsRepayProject.getPage(), xsRepayProject.getRows());
		List<RepayProjectVo> rows =new ArrayList<RepayProjectVo>();
		if(lst!=null && lst.size()>0){
			for(XsRepayProject repayProject:lst){
				RepayProjectVo  repayPro=new RepayProjectVo();
				BeanUtils.copyProperties(repayProject, repayPro);
				if(repayDAO.get(XsRepay.class,repayProject.getProjectType())!=null){
					repayPro.setRepayName(repayDAO.get(XsRepay.class,repayProject.getProjectType()).getRepayName());
				}
				rows.add(repayPro);
			}
		}
		int total = reProjectDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public Msg deleteReProject(RepayProjectVo xsRepayProject) throws Exception{	
		Msg msg = new Msg();
		List list=reProjectDAO.createSQLQuery(" select con.project_id  from xs_sell_cover_content con," +
				" xs_repay_project pro where con.project_id=pro.xs_project_id " +
				"and pro.enterprise_id="+xsRepayProject.getEnterpriseId()+" " +
					"and con.project_id= "+xsRepayProject.getProjectId());
		if(list!=null&&list.size()>0){
			msg.setMsg("该项目存在于回访调查表中不能删除！");
			msg.setSuccess(false);
		}else{
			XsRepayProject repayProject=new XsRepayProject();
			BeanUtils.copyProperties(xsRepayProject, repayProject);
			reProjectDAO.delete(repayProject);
			setContent("删除父级菜单为【客户回访项目】编码为【"+repayProject.getProjectCode()+"】," +
					"名称为【"+repayProject.getProjectName()+"】的信息！");
			msg.setMsg("删除成功！");
			msg.setSuccess(true);
		}
		return msg;
		
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addReProject(RepayProjectVo xsRepayProject) throws Exception{
		XsRepayProject repayProject=new XsRepayProject();
		BeanUtils.copyProperties(xsRepayProject, repayProject);
		repayProject.setProjectCode(CreateID.createId("rePro", Contstants.SELL_BILLSDEPLOY.REPAYPROJECT));
		reProjectDAO.save(repayProject);
		XsRepay re=(XsRepay)repayDAO.get(" from XsRepay where xs_repay_id="+repayProject.getProjectType());
		setContent("给父级菜单【客户回访项目】新增信息,编码为【"+repayProject.getProjectCode()+"】," +
				"项目名称为【"+repayProject.getProjectName()+"】,回访进度【"+re.getRepayName()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateReProject(RepayProjectVo xsRepayProject) throws Exception{
		XsRepayProject repayProject=new XsRepayProject();
		BeanUtils.copyProperties(xsRepayProject, repayProject);
		reProjectDAO.merge(repayProject);		
		XsRepay re=(XsRepay)repayDAO.get(" from XsRepay where xs_repay_id="+repayProject.getProjectType());
		setContent("修改父级菜单为【客户回访项目】编码为【"+repayProject.getProjectCode()+"】的信息," +
				"修改项目名称为【"+repayProject.getProjectName()+"】,回访进度【"+re.getRepayName()+"】！");
	}
	public boolean findReProTwo(String proCode,Integer enid) throws Exception{
		if(proCode!=null && !("".equals(proCode))){
			String hql="from XsRepayProject repro where " +
					" repro.projectName='"+proCode+"' and repro.enterpriseId="+enid;
			XsRepayProject rePro=reProjectDAO.get(hql);
			if(rePro!=null ){
				return true;
			}		
		}
		return false;
	}
	public boolean findRePro(String proCode,Integer id,Integer enid) throws Exception{
		if(proCode!=null && !("".equals(proCode))){
			String hql="from XsRepayProject repro where 1=1 and repro.projectName='"+proCode+"' " +
					"and repro.projectId!='"+id+"' and repro.enterpriseId="+enid;
			XsRepayProject rePro=reProjectDAO.get(hql);
			if(rePro!=null ){
				return true;
			}		
		}
		return false;
	}
	
	public List<ComboBoxJson> findAllRepay(Integer enterpriseId) throws Exception{
		List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
		List<XsRepay> bctList = repayDAO.findAllRepay(enterpriseId);
		if(bctList != null && bctList.size() > 0){
			for(XsRepay xsRepay : bctList){
				ComboBoxJson json = new ComboBoxJson();
				json.setId(xsRepay.getRepayId().toString());
				json.setText(xsRepay.getRepayName());
				list.add(json);
			}
		}
		return list;
	}
	public ReProjectDAO getReProjectDAO() {
		return reProjectDAO;
	}
	@Autowired
	public void setReProjectDAO(ReProjectDAO reProjectDAO) {
		this.reProjectDAO = reProjectDAO;
	}
	public RepayDAO getRepayDAO() {
		return repayDAO;
	}
	@Autowired
	public void setRepayDAO(RepayDAO repayDAO) {
		this.repayDAO = repayDAO;
	}

}
