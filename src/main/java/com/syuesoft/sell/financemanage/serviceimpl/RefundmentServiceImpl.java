package com.syuesoft.sell.financemanage.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.financemanage.dao.RefundmentDao;
import com.syuesoft.sell.financemanage.service.RefundmentService;
import com.syuesoft.sell.financemanage.vo.RefundmentVo;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.MyBeanUtils;

@Service("refundmentService")
public class RefundmentServiceImpl extends BaseLogServiceImpl implements
		RefundmentService {

	@Resource
	private RefundmentDao refundmentDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	/**
	 * 获取收款记录
	 */
	
	public Json getRefundmentInfo(RefundmentVo refundmentVo) throws Exception {

		//获取初始化时间
		String edate = new java.sql.Date(new java.util.Date().getTime())
				.toString();
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2,
				basCompanyInformationSetDAO.getBasCompanyInformationSet(
						PARAMETER_SET.COLLIGATES,
						Contstants.COLLIGATES.DEFAULTSHOWDAY,
						refundmentVo.getEnterpriseId()).getCiValue());
		
		StringBuffer sb2=new StringBuffer("SELECT bb.backCar_id,bb.backCar_code,bb.backCar_Document,bb.backCar_sunmoney,bb.backCar_money,");
		sb2.append(" (bb.backCar_sunmoney-bb.backCar_money) AS noBackCar_money,");
		sb2.append(" bb.dataValue,bb.invoice_num,bb.backCar_date,bb.temp,");
		sb2.append(" '' as stf_id,'' as stf_name,");
		sb2.append(" bb.backMoney_date,bb.aduitId,bb.aduitName,bb.child_id");
		sb2.append(" FROM ( SELECT xbl.backCar_id,xbl.backCar_code,xbl.backCar_Document,xbl.backCar_sunmoney,");
		sb2.append(" (SELECT SUM(temp.backCar_money) FROM xs_backcar_log temp INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_document=temp.backCar_document WHERE xbl.backCar_Document=temp.backCar_Document GROUP BY temp.backCar_document) AS backCar_money,");
		sb2.append(" aa.child_id,aa.dataValue,xbl.invoice_num,xbl.backCar_date,bs.stf_name as temp,xbl.backMoney_date,xbl.backMoney_person,xx.aduitId,xx.aduitName,etc.enterprise_id");
		sb2.append(" FROM xs_backcar_log xbl");
		if(refundmentVo.getPrefix()!=null&&refundmentVo.getPrefix().equals(Contstants.SELL_BILLSDEPLOY.BACKTOCAR)){
			sb2.append(" INNER JOIN xs_sell_exitCar etc ON etc.exitCar_code = xbl.backCar_document");
		}else{
			sb2.append(" INNER JOIN xs_sell_car_reserve etc ON etc.reserve_code = xbl.backCar_document");
		}
		sb2.append(" INNER JOIN bas_stuff bs ON bs.stf_id=xbl.backCar_person");
		sb2.append(" INNER JOIN (SELECT xc.child_id as aduitId,xc.dataValue as aduitName from  xs_childdictionary xc inner join xs_parentdictionary xp on xp.parent_id=xc.parent_id and xp.dataKey='"+Contstants.ADUIT.ADUIT+"') xx");
		sb2.append(" ON xx.aduitId=xbl.examine");
		sb2.append(" LEFT OUTER JOIN (");
		sb2.append(" SELECT xc.child_id,xc.dataValue FROM xs_childdictionary xc");
		sb2.append(" ) aa ON aa.child_id=xbl.invoice ");
		sb2.append(" ) bb ");
		
		StringBuffer sb1=new StringBuffer("SELECT bb.backCar_id,bb.backCar_code,bb.backCar_Document,bb.backCar_sunmoney,bb.backCar_money,'' as noBackCar_money,");
		sb1.append(" bb.dataValue,bb.invoice_num,bb.backCar_date,bb.temp,");
		sb1.append(" bs.stf_id,bs.stf_name,");
		sb1.append(" bb.backMoney_date,bb.aduitId,bb.aduitName,bb.child_id");
		sb1.append(" FROM ( SELECT xbl.backCar_id,xbl.backCar_code,xbl.backCar_Document,xbl.backCar_sunmoney,xbl.backCar_money,");
		sb1.append(" aa.child_id,aa.dataValue,xbl.invoice_num,xbl.backCar_date,bs.stf_name as temp,xbl.backMoney_date,xbl.backMoney_person,xx.aduitId,xx.aduitName,etc.enterprise_id");
		sb1.append(" FROM xs_backcar_log xbl INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_document=xbl.backCar_document");
		if(refundmentVo.getPrefix()!=null&&refundmentVo.getPrefix().equals(Contstants.SELL_BILLSDEPLOY.BACKTOCAR)){
			sb1.append(" INNER JOIN xs_sell_exitCar etc ON etc.exitCar_code = xbl.backCar_document");
		}else{
			sb1.append(" INNER JOIN xs_sell_car_reserve etc ON etc.reserve_code = xbl.backCar_document");
		}
		sb1.append(" INNER JOIN bas_stuff bs ON bs.stf_id=xbl.backCar_person");
		sb1.append(" INNER JOIN (SELECT xc.child_id as aduitId,xc.dataValue as aduitName from  xs_childdictionary xc inner join xs_parentdictionary xp on xp.parent_id=xc.parent_id and xp.dataKey='"+Contstants.ADUIT.ADUIT+"') xx");
		sb1.append(" ON xx.aduitId=xbl.examine");
		sb1.append(" LEFT OUTER JOIN (");
		sb1.append(" SELECT xc.child_id,xc.dataValue FROM xs_childdictionary xc");
		sb1.append(" ) aa ON aa.child_id=xbl.invoice ");
		sb1.append(" ) bb ");
		sb1.append(" INNER JOIN bas_stuff bs ON bs.stf_id=bb.backMoney_person ");
		StringBuffer sb=new StringBuffer();
		if(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true)
			sb.append(sb1);
		else
			sb.append(sb2);
		sb.append(" WHERE 1=1");
		if(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))
			sb.append(" and bb.backMoney_person is null");
		
		sb.append(" and bb.backCar_Document like '%"+StringEscapeUtils.escapeSql(refundmentVo.getPrefix().trim())+"%'");
		
		if(refundmentVo.getInvoice_Num()!=null && !refundmentVo.getInvoice_Num().equals("")){
			sb.append(" and bb.invoice_num like '%"+StringEscapeUtils.escapeSql(refundmentVo.getInvoice_Num().trim())+"%'");
		} 
		if(refundmentVo.getBackCar_Code()!=null && !refundmentVo.getBackCar_Code().equals("")){
			sb.append(" and bb.backCar_code like '%"+StringEscapeUtils.escapeSql(refundmentVo.getBackCar_Code().trim())+"%'");
		}
		if(refundmentVo.getBackCar_Document()!=null && !refundmentVo.getBackCar_Document().equals("")){
			sb.append(" and bb.backCar_Document= '"+StringEscapeUtils.escapeSql(refundmentVo.getBackCar_Document().trim())+"'");
		}

		if (refundmentVo.getBackMoney_Date() != null&&(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))
				&& !refundmentVo.getBackMoney_Date().equals("")) {
			sb.append( " and DATE(bb.BackMoney_Date) >= '" +refundmentVo.getBackMoney_Date()+ "'");
		}
		if (refundmentVo.getBackMoney_Date2() != null&&(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))
				&& !refundmentVo.getBackMoney_Date2().equals("")) {
			sb.append( " and DATE(bb.BackMoney_Date)<= '" + refundmentVo.getBackMoney_Date2() + "'");
		}
		/*if((refundmentVo.getBackMoney_Date() == null
				||refundmentVo.getBackMoney_Date().equals(""))&&(refundmentVo.getBackMoney_Date2() == null
						||refundmentVo.getBackMoney_Date2().equals(""))){
				sb.append(" and DATE(bb.BackMoney_Date) BETWEEN '" + sdate + "' AND '"
				+ edate + "'");
			}*/
		if (refundmentVo.getBackCar_Date() != null&& !refundmentVo.getBackCar_Date().equals("")
				&&(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))) {
			sb.append( " and DATE(bb.backCar_date) >= '" +refundmentVo.getBackCar_Date()+ "'");
		}
		if (refundmentVo.getBackCar_Date2() != null&& !refundmentVo.getBackCar_Date2().equals("")
				&&(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))) {
			sb.append( " and DATE(bb.backCar_date)<= '" + refundmentVo.getBackCar_Date2() + "'");
		}
		if((refundmentVo.getBackCar_Date() == null||refundmentVo.getBackCar_Date().equals(""))&&(refundmentVo.getBackCar_Date2() == null
						||refundmentVo.getBackCar_Date2().equals(""))&&(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true))){
				sb.append(" and DATE(bb.backCar_date) BETWEEN '" + sdate + "' AND '"
				+ edate + "'");
			}
		//企业编号 
		if(refundmentVo.getEnterpriseId()!=null && !refundmentVo.getEnterpriseId().equals("")){
			sb.append(" and bb.Enterprise_Id= "+refundmentVo.getEnterpriseId()+"");
		}
		List<Object[]> list = refundmentDao.createSQLQuery(sb.toString(), refundmentVo.getPage(), refundmentVo.getRows());
		List<RefundmentVo> rows = new ArrayList();
		List lst = new ArrayList();
		Json json = new Json();
		RefundmentVo vo=null;
		if(list!=null && list.size()>0)
			for (Object[] obj : list) {
				vo=new RefundmentVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					vo.setBackCar_Id(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					vo.setBackCar_Code(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					vo.setBackCar_Document(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					vo.setBackCar_Sunmoney(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					vo.setBackCar_Money(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					vo.setNoBackCar_Money(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					vo.setInvoice(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					vo.setInvoice_Num(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					vo.setBackCar_Date(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					vo.setBackCar_Person(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					vo.setBackMoney_Person(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					vo.setStfName(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					vo.setBackMoney_Date(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					vo.setAduitId(Integer.parseInt(obj[13].toString()));
				if(obj[14]!=null&&obj[14].toString().length()>0)
					vo.setAduitName(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					vo.setInvoiceId(obj[15].toString());
				rows.add(vo);
			}
		int total = refundmentDao.getSQLCount(sb.toString(), null);
		json.setRows(rows);
		json.setTotal(total);
		return json;
	
	}

	/**
	 * 保存退款记录
	 */
	
	public void saveRefundmentAmount(RefundmentVo refundmentVo)
			throws Exception {
		XsBackCarLog xbcl=new XsBackCarLog();
		XsBackCarLog temp=refundmentDao.get("from XsBackCarLog xbcl where xbcl.backCarDocument='"+refundmentVo.getBackCar_Document()+"'");
		MyBeanUtils.getInstance().copyBeans(temp, xbcl);
		xbcl.setBackCarId(null);
		xbcl.setBackCarCode(null);
		xbcl.setBackMoneyPerson(null);
		xbcl.setInvoice(null);
		xbcl.setInvoiceNum(null);
		if(Contstants.SELL_BILLSDEPLOY.BACKTOCAR.equals(refundmentVo.getPrefix()))
			xbcl.setBackCarCode(CreateID.createId("xsBackCarLog_back", Contstants.SELL_BILLSDEPLOY.BACKTOCARREFUNDMENT));
		else if(Contstants.SELL_BILLSDEPLOY.CARRESERVE.equals(refundmentVo.getPrefix()))
			xbcl.setBackCarCode(CreateID.createId("xsBackCarLog_before", Contstants.SELL_BILLSDEPLOY.CARRESERVEREFUNDMENT));
		xbcl.setBackMoneyPerson(Integer.parseInt(refundmentVo.getBackMoney_Person()));
		xbcl.setBackCarSunmoney(Double.parseDouble(refundmentVo.getBackCar_Sunmoney()));
		xbcl.setBackCarMoney(Double.parseDouble(refundmentVo.getBackCar_Money()));
		if(refundmentVo.getInvoiceId()!=null&&refundmentVo.getInvoiceId().length()>0){
			xbcl.setInvoice(Integer.parseInt(refundmentVo.getInvoiceId()));
			xbcl.setInvoiceNum(refundmentVo.getInvoice_Num());
		}
		xbcl.setBackMoneyDate(refundmentVo.getBackMoney_Date());
		xbcl.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,refundmentVo.getEnterpriseId()));
		refundmentDao.save(xbcl);
	}
	
	/**
	 * 查找未退金额
	 * */
	
	public String findNoBackMoney(RefundmentVo refundmentVo) throws Exception {
		
		return refundmentDao.findNoBackMoney(refundmentVo);
	}
	
	/**
	 * 审核
	 * */
	
	public void  updateExamine(RefundmentVo refundmentVo) throws Exception{
		String sql1="from XsBackCarLog xbcl where xbcl.backCarDocument=(select temp.backCarDocument from XsBackCarLog temp where temp.backCarId="+refundmentVo.getBackCar_Id()+")";
		String sql2="from XsBackCarLog xbcl where xbcl.backCarId="+refundmentVo.getBackCar_Id();
		XsBackCarLog xbcl=refundmentDao.get(sql2);
		List<XsBackCarLog> tempList=refundmentDao.find((refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true)?sql1:sql2);
		
		int noAudit = baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,refundmentVo.getEnterpriseId());
		int yesAudit = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,refundmentVo.getEnterpriseId());
		
		int examine = noAudit;
		if(xbcl.getExamine().equals(examine)){
			examine = yesAudit;
		}
		if(tempList!=null&&tempList.size()>0)
		for (XsBackCarLog temp : tempList) {
			temp.setExamine(examine);
			refundmentDao.merge(temp);
			refundmentDao.flush();
			if(!(refundmentVo.getEditTag()!=null&&refundmentVo.getEditTag()==true)){
				validateExamine(refundmentVo.getBackCar_Id(),noAudit,yesAudit);
			}
		}
	}
	private void validateExamine(String backCarId,int noAudit,int yesAudit) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT * FROM xs_backcar_log temp2 INNER JOIN (");
		sb.append(" SELECT temp.backCar_id,temp.backCar_document FROM xs_backCar_log temp WHERE temp.backCar_document IN (");
		sb.append(" SELECT xbl.backCar_document FROM xs_backcar_log xbl WHERE xbl.backCar_id="+backCarId+")");		
		sb.append(" AND temp.backMoney_person IS NULL ");		
		sb.append(" ) temp3 ON temp3.backCar_document=temp2.backCar_document AND temp3.backCar_id!=temp2.backCar_id");		
		sb.append(" AND temp2.examine="+noAudit);
		boolean tag=false;
		List list=refundmentDao.createSQLQuery(sb.toString());		
		if(!(list!=null&&list.size()>0)){
			tag=true;
		}
		StringBuffer sb2=new StringBuffer("select temp from XsBackCarLog temp where temp.backCarDocument =");
		sb2.append(" (select xbl.backCarDocument from XsBackCarLog xbl where xbl.backCarId="+backCarId+")");
		sb2.append(" and temp.backMoneyPerson is null");
		XsBackCarLog xbl=refundmentDao.get(sb2.toString());		 
		if(xbl!=null){
			if(xbl.getExamine()==noAudit&&tag){
				xbl.setExamine(yesAudit);
				refundmentDao.merge(xbl);
			}else if(xbl.getExamine()==yesAudit&&tag==false){
				xbl.setExamine(noAudit);
				refundmentDao.merge(xbl);
			}
		}
	}
	/**
	 * 判断是否已审核
	 * */
	
	public Boolean isRefundment(RefundmentVo refundmentVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,refundmentVo.getEnterpriseId());
		if(refundmentVo.getAduitId().equals(examine))
			return true;
		return false;
	}
	/**
	 * 修改退款单
	 * */
	
	public void updateRefundmentAmount(RefundmentVo refundmentVo)
			throws Exception {
		XsBackCarLog xbcl=refundmentDao.get("from XsBackCarLog xbcl where xbcl.backCarCode='"+refundmentVo.getBackCar_Code()+"'");
		xbcl.setBackMoneyPerson(Integer.parseInt(refundmentVo.getBackMoney_Person()));
		xbcl.setBackCarSunmoney(Double.parseDouble(refundmentVo.getBackCar_Sunmoney()));
		xbcl.setBackCarMoney(Double.parseDouble(refundmentVo.getBackCar_Money()));
		if(refundmentVo.getInvoiceId()!=null&&refundmentVo.getInvoiceId().length()>0){
			xbcl.setInvoice(Integer.parseInt(refundmentVo.getInvoiceId()));
			xbcl.setInvoiceNum(refundmentVo.getInvoice_Num());
		}
		xbcl.setBackMoneyDate(refundmentVo.getBackMoney_Date());
		xbcl.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,refundmentVo.getEnterpriseId()));
		refundmentDao.merge(xbcl);
	}
	
}
