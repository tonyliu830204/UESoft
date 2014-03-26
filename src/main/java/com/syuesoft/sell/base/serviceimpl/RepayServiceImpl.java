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
import com.syuesoft.sell.base.dao.RepayDAO;
import com.syuesoft.sell.base.service.RepayService;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.sell.model.XsRepay;
import com.syuesoft.sell.model.XsRepayProject;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;

@Service("repayService")
public class RepayServiceImpl extends BaseLogServiceImpl implements RepayService {
	private RepayDAO repayDAO;
	
	public Datagrid getPager(RepayVo xsRepay) throws Exception{
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsRepay repay where 1=1 and repay.enterpriseId="+xsRepay.getEnterpriseId());
		if(xsRepay.getRepayC()!=null &&!("".equals(xsRepay.getRepayC()))){
			hql.append(" and repay.repayCode like '%"+StringEscapeUtils.escapeSql(xsRepay.getRepayC().trim())+"%'");
		}
		if(xsRepay.getRepayN()!=null &&!("".equals(xsRepay.getRepayN()))){
			hql.append(" and repay.repayName like '%"+StringEscapeUtils.escapeSql(xsRepay.getRepayN().trim())+"%'");
		}
		List<XsRepay> lst=repayDAO.find(hql.toString(), xsRepay.getPage(), xsRepay.getRows());
		List<RepayVo> rows =new ArrayList<RepayVo>();
		if(lst!=null && lst.size()>0){
			for(XsRepay repay:lst){
				RepayVo repayVo=new RepayVo();
				BeanUtils.copyProperties(repay, repayVo);
				rows.add(repayVo);
			}
		}
		int total = repayDAO.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="删除")
	public Msg deleteRepay(RepayVo xsRepay) throws Exception{	
		Msg msg=new Msg();
		List pro=repayDAO.find(" from XsRepayProject where projectType="+xsRepay.getRepayId()+"" +
				" and enterpriseId="+xsRepay.getEnterpriseId());
		List cover=repayDAO.find(" from XsSellCover where consultRate="+xsRepay.getRepayId()+" " +
				" and enterpriseId="+xsRepay.getEnterpriseId());
		if(pro!=null&&pro.size()>0){
			msg.setSuccess(false);
			msg.setMsg("该回访进度存在于客户回访项目表中不能删除！");
		}else if(cover!=null&&cover.size()>0){
				msg.setSuccess(false);
				msg.setMsg("该回访进度存在于售后回访表中不能删除！");
		}else{
			XsRepay repay=new XsRepay();
			BeanUtils.copyProperties(xsRepay, repay);
			repayDAO.delete(repay);
			setContent("删除父级菜单为【回访进度】编码为【"+repay.getRepayCode()+"】," +
					"名称为【"+repay.getRepayName()+"】的信息！");
			msg.setSuccess(true);
			msg.setMsg("删除成功！");
			}
		return msg;
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="新增")
	public void addRepay(RepayVo xsRepay) throws Exception{
		XsRepay repay=new XsRepay();
		BeanUtils.copyProperties(xsRepay, repay);
		repay.setRepayCode(CreateID.createId("rep", Contstants.SELL_BILLSDEPLOY.REPAY));
		repayDAO.save(repay);
		setContent("给父级菜单【回访进度】新增信息,编码为【"+repay.getRepayCode()+"】," +
				"名称为【"+repay.getRepayName()+"】,间隔天数为【"+repay.getRepayDay()+"】！");
	}
	
	@Log(systemName="销售系统", moduleName="基础数据设置",opertype="修改")
	public void updateRepay(RepayVo xsRepay) throws Exception{
		XsRepay repay=new XsRepay();
		BeanUtils.copyProperties(xsRepay, repay);
		repayDAO.merge(repay);
		setContent("修改父级菜单为【回访进度】编码为【"+repay.getRepayCode()+"】的信息," +
				"修改名称为【"+repay.getRepayName()+"】,间隔天数为【"+repay.getRepayDay()+"】！");
	}
	public boolean findRepayTwo(String repayCode,Integer enid) throws Exception{
		if(repayCode!=null && !("".equals(repayCode))){
			String hql="from XsRepay repay where " +
					" repay.repayName='"+repayCode+"' and repay.enterpriseId="+enid;
			XsRepay repay=repayDAO.get(hql);
			if(repay!=null){
				return true;
			}
		}
		return false;
	}
	public boolean findRepay(String repayCode,Integer id,Integer enid) throws Exception{
		if(repayCode!=null && !("".equals(repayCode))){
			String hql="from XsRepay repay where 1=1  and repay.repayName='"+repayCode+"' " +
					"and repay.repayId!='"+id+"' and repay.enterpriseId="+enid;
			XsRepay repay=repayDAO.get(hql);
			if(repay!=null){
				return true;
			}
		}
		return false;
	}
	public RepayDAO getRepayDAO() {
		return repayDAO;
	}
	@Autowired
	public void setRepayDAO(RepayDAO repayDAO) {
		this.repayDAO = repayDAO;
	}

}
