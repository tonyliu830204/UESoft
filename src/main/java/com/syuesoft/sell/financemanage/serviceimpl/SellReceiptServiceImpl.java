package com.syuesoft.sell.financemanage.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.financemanage.dao.SellReceiptDao;
import com.syuesoft.sell.financemanage.service.SellReceiptService;
import com.syuesoft.sell.financemanage.vo.SellReceiptVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellCertificate;
import com.syuesoft.sell.model.XsSellReceipt;
import com.syuesoft.util.FormatTime;

@Service("sellReceiptService")
public class SellReceiptServiceImpl  extends BaseLogServiceImpl implements SellReceiptService {
	
	@Resource
	private SellReceiptDao receiptDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 获取票据管理汇总查询
	 */
	
	public Datagrid getPager(SellReceiptVo sellReceiptVo) throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		Contstants.COLLIGATES.DEFAULTSHOWDAY,sellReceiptVo.getEnterpriseId()).getCiValue()));
		String edate = new java.sql.Date(new java.util.Date().getTime()).toString();
		Datagrid dg = new Datagrid();
		StringBuffer hql=new StringBuffer("from XsSellReceipt receipt where 1=1");
		if(sellReceiptVo.getEnterpriseId()!=null && !("".equals(sellReceiptVo.getEnterpriseId()))){
			hql.append("  and  receipt.enterpriseId = "+sellReceiptVo.getEnterpriseId()+"");
		}
		if(sellReceiptVo.getReceiptCode()!=null && !("".equals(sellReceiptVo.getReceiptCode()))){
			hql.append("  and  receipt.receiptCode like '%"+StringEscapeUtils.escapeSql(sellReceiptVo.getReceiptCode().trim())+"%'");
		}
		
		if(sellReceiptVo.getQueryStartDate()!=null  && !("".equals(sellReceiptVo.getQueryStartDate()))){
			
			hql.append( " and receipt.receiptStartDate >= '"+sellReceiptVo.getQueryStartDate()+"'" );
		}
		if(sellReceiptVo.getQueryStartDate2()!=null  && !("".equals(sellReceiptVo.getQueryStartDate2()))){
			
			hql.append( " and receipt.receiptStartDate <= '"+sellReceiptVo.getQueryStartDate2()+"'" );
		}
		
		if((sellReceiptVo.getQueryStartDate()==null||"".equals(sellReceiptVo.getQueryStartDate()))&&
				(sellReceiptVo.getQueryStartDate2()==null||"".equals(sellReceiptVo.getQueryStartDate2()))){
			hql.append( " and receipt.receiptStartDate BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		
		if(sellReceiptVo.getQueryEndDate()!=null  && !("".equals(sellReceiptVo.getQueryEndDate()))){
			
			hql.append( " and receipt.receiptEndDate >= '"+sellReceiptVo.getQueryEndDate()+"'" );
		}
		if(sellReceiptVo.getQueryEndDate2()!=null  && !("".equals(sellReceiptVo.getQueryEndDate2()))){
			
			hql.append( " and receipt.receiptEndDate <= '"+sellReceiptVo.getQueryEndDate2()+"'" );
		}
		
		List<Object> lst=receiptDao.find(hql.toString(), sellReceiptVo.getPage(), sellReceiptVo.getRows());
		List<SellReceiptVo> rows =new ArrayList<SellReceiptVo>();
		if(lst!=null && lst.size()>0){
			for(Object b:lst){
				XsSellReceipt Receipt=(XsSellReceipt)b;
				SellReceiptVo rVo=new SellReceiptVo();
				BeanUtils.copyProperties(Receipt, rVo);	
				if(Receipt.getReceiptBank()!=null  && !("".equals(Receipt.getReceiptBank()))){
					XsChilddictionary child=baseTagDAO.findById(Receipt.getReceiptBank());
					if(child!=null){
						rVo.setBankName(child.getDataValue());
					}
				}
				rows.add(rVo);
			}
		}
		int total = receiptDao.getCount(hql.toString());
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 保存票据信息
	 */
	@Log(systemName="销售系统", moduleName="财务管理",opertype="删除")
	
	public Serializable addReceipt(SellReceiptVo sellReceiptVo) throws Exception {
		XsSellReceipt xsSellReceipt=new XsSellReceipt();
		BeanUtils.copyProperties(sellReceiptVo, xsSellReceipt);
		xsSellReceipt.setEnterpriseId(sellReceiptVo.getEnterpriseId());
		Serializable id = receiptDao.save(xsSellReceipt);	
		setContent("新增编号为"+xsSellReceipt.getReceiptCode()+"的票据信息！");
		return id;
	}

	/**
	 * 删除票据信息
	 */
	@Log(systemName="销售系统", moduleName="财务管理",opertype="删除")
	
	public void deleteReceipt(SellReceiptVo sellReceiptVo) throws Exception {
		XsSellReceipt xsSellReceipt=new XsSellReceipt();
		BeanUtils.copyProperties(sellReceiptVo, xsSellReceipt);
		receiptDao.delete(xsSellReceipt);	
		setContent("删除编号为"+xsSellReceipt.getReceiptCode()+"的票据信息！");
	}

	/**
	 * 修改票据信息
	 */
	@Log(systemName="销售系统", moduleName="财务管理",opertype="修改")
	
	public void updateReceipt(SellReceiptVo sellReceiptVo) throws Exception {
//		XsSellReceipt xsSellReceipt=new XsSellReceipt();
//		BeanUtils.copyProperties(sellReceiptVo, xsSellReceipt);
		XsSellReceipt xsSellReceipt = (XsSellReceipt)receiptDao.get("FROM XsSellReceipt T WHERE T.receiptId="+sellReceiptVo.getReceiptId()+"");
		xsSellReceipt.setReceiptBank(sellReceiptVo.getReceiptBank());
		xsSellReceipt.setReceiptCode(sellReceiptVo.getReceiptCode());
		xsSellReceipt.setReceiptEndDate(sellReceiptVo.getReceiptEndDate());
		xsSellReceipt.setReceiptMoney(sellReceiptVo.getReceiptMoney());
		xsSellReceipt.setReceiptStartDate(sellReceiptVo.getReceiptStartDate());
		xsSellReceipt.setReceiptState(sellReceiptVo.getReceiptState());
		xsSellReceipt.setRemark(sellReceiptVo.getRemark());
		
		receiptDao.merge(xsSellReceipt);	
		setContent("修改编号为"+xsSellReceipt.getReceiptCode()+"的票据信息！");
	}
	public  Boolean  findReceiptById(SellReceiptVo sellReceiptVo)throws Exception{
		XsSellReceipt certificate = (XsSellReceipt)receiptDao.get("FROM XsSellReceipt T WHERE T.receiptId="+sellReceiptVo.getReceiptId()+"");
		if(certificate==null){
			return true;
		}else{
			return false;
		}
	}

}
