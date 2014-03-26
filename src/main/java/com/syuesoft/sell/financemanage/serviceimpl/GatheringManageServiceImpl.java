package com.syuesoft.sell.financemanage.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.financemanage.dao.GatheringManageDao;
import com.syuesoft.sell.financemanage.service.GatheringManageService;
import com.syuesoft.sell.financemanage.vo.GatheringManageVo;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.model.XsSellCollections;
import com.syuesoft.sell.model.XsSellCollectionsDetail;
import com.syuesoft.sell.model.XsSellReceivablesDetail;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Json;
import com.syuesoft.util.SystemUser;

@Service("gatheringManageService")
public class GatheringManageServiceImpl extends BaseLogServiceImpl implements GatheringManageService {
	@Resource
	private GatheringManageDao gatheringManageDao;
	@Resource
	private BaseTagDAO baseTagDAO;

	/**
	 * 保存应收款收款记录
	 */
	
	public void saveAmonunt(GatheringManageVo gatheringManageVo)
			throws Exception {
		
		XsSellReceivablesDetail xsSellReceivablesDetail = new XsSellReceivablesDetail();
		XsSellCollections xsSellCollections = new XsSellCollections();
		//保存应收收款记录
		xsSellReceivablesDetail.setDate(new java.sql.Date(new java.util.Date().getTime())+"");
		xsSellReceivablesDetail.setDetailType(baseTagDAO.getChildId(Contstants.BASE_SELL.KXLX, Contstants.BASE_SELL.SR,gatheringManageVo.getEnterpriseId()));
		xsSellReceivablesDetail.setInvoice(gatheringManageVo.getInvoice()!=null && !gatheringManageVo.getInvoice().equals("")?Integer.parseInt(gatheringManageVo.getInvoice()) : null);
		xsSellReceivablesDetail.setInvoiceNum(gatheringManageVo.getInvoice_Num());
		xsSellReceivablesDetail.setReceivedMoney(gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("")?Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00);
		xsSellReceivablesDetail.setReceivedWay(gatheringManageVo.getReceived_Way()!=null && !gatheringManageVo.getReceived_Way().equals("")?Integer.parseInt(gatheringManageVo.getReceived_Way()) : null);
		xsSellReceivablesDetail.setRemark(gatheringManageVo.getRemark());
		xsSellReceivablesDetail.setStfId(gatheringManageVo.getStf_Id()!=null && !gatheringManageVo.getStf_Id().equals("")?Integer.parseInt(gatheringManageVo.getStf_Id()) : null);
		xsSellReceivablesDetail.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,gatheringManageVo.getEnterpriseId()));
		xsSellCollections.setCollectionsId(gatheringManageVo.getCollections_Id()!=null && !gatheringManageVo.getCollections_Id().equals("")?Integer.parseInt(gatheringManageVo.getCollections_Id()) : null);
		xsSellReceivablesDetail.setXsSellCollections(xsSellCollections);
		gatheringManageDao.save(xsSellReceivablesDetail);
		
		//获取收款类型为结算收款的应收记录
		XsSellCollections xsSellCollectionsr = (XsSellCollections)gatheringManageDao.get("from XsSellCollections a where a.collectionsId="+gatheringManageVo.getCollections_Id()+" and a.xsSellCollectionType.childId="+baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.JSSK,gatheringManageVo.getEnterpriseId())+"");
		//本次收款金额
		Double receivedmoney = gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("")?Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00;//本次收款金额
		//上次累计金额
		Double nowmoney= xsSellCollectionsr.getAccountCumulative();
		//当前累计金额
		Double accountCumulative= receivedmoney + nowmoney;
		xsSellCollectionsr.setAccountCumulative(accountCumulative);
		//余额 = 应收金额   - 本次收款金额
		//应收金额（该处从前台获得的欠款金额 作为应收金额）
		Double accountReceivables = gatheringManageVo.getAccount_Arrears() != null ? Double.parseDouble(gatheringManageVo.getAccount_Arrears()) : 0.00;
		//累计预收金额
		Double summoney = gatheringManageVo.getSum_Money()!=null ? Double.parseDouble(gatheringManageVo.getSum_Money()) : 0.00;
		//余额/欠款
		Double accountAmount = null;
		//判断是否是第一次收款
		XsSellReceivablesDetail xsSellReceivablesDetailinfor = (XsSellReceivablesDetail)gatheringManageDao.get("from XsSellReceivablesDetail where xsSellCollections.collectionsId="+gatheringManageVo.getCollections_Id()+"");
		if(xsSellReceivablesDetailinfor!=null){
			accountAmount =  accountReceivables  - receivedmoney;
		}else{
			accountAmount =  accountReceivables  - receivedmoney - summoney;
		}
		xsSellCollectionsr.setAccountAmount(accountAmount);
		//欠款
		xsSellCollectionsr.setAccountArrears(accountAmount);
		
		if(accountReceivables > (accountCumulative + receivedmoney)){
			XsChilddictionary finish=baseTagDAO.findById(baseTagDAO.getChildId(Contstants.BASE_SELL.SHIFOUFUQING, Contstants.BASE_SELL.WFQ,gatheringManageVo.getEnterpriseId()));
			xsSellCollectionsr.setXsSellCollectionUnfinished(finish);
		}else{
			XsChilddictionary finish=baseTagDAO.findById(baseTagDAO.getChildId(Contstants.BASE_SELL.SHIFOUFUQING, Contstants.BASE_SELL.YFQ,gatheringManageVo.getEnterpriseId()));
			xsSellCollectionsr.setXsSellCollectionUnfinished(finish);
		}
		gatheringManageDao.merge(xsSellCollectionsr);
		//如果累计预收金额大于0将预收款使用记录改为抵扣
		if(summoney>0){
			gatheringManageDao.createSQLQueryOutFind("UPDATE xs_sell_collections_detail SET detail_type="+
					baseTagDAO.getChildId(Contstants.BASE_SELL.KXLX, Contstants.BASE_SELL.DK,gatheringManageVo.getEnterpriseId())+" WHERE collections_id=(SELECT b.collections_id FROM xs_sell_collections b  WHERE b.account_id = (SELECT a.reserve_code FROM xs_sell_car_reserve a  WHERE a.xs_car_id = "+gatheringManageVo.getXs_Car_Id()+"))");
		}
	}
	/**
	 * 获取预收款记录
	 */
	
	public Json findBillInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		return gatheringManageDao.findBillInfor(gatheringManageVo,baseTagDAO);
	}

	/**
	 * 该获取预收款信息方法未用（有改动）
	 */
	
	public Json findAccountInfor(GatheringManageVo gatheringManageVo)
			throws Exception {

		String hql ="SELECT a.Account_Code,a.Account_Date,a.Account_Amount,a.Account_Arrears,a.Account_Arrears_Age," +
				"a.Account_Cumulative,a.Account_Person,a.Account_Receivables,a.Account_Unfinished,a.Collections_Id,a.Account_Type , " +
				"b.custom_id,b.xs_custom_name,c.xs_car_id, c.xs_car_code, c.xs_car_vin_number" +
				" FROM xs_sell_collections a, xs_custom_info b, xs_car_info c" +
				" WHERE a.custom_id = b.custom_id" +
				" AND a.xs_car_id = c.xs_car_id and account_TYPE="+baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.YSK,gatheringManageVo.getEnterpriseId())+"";
		//企业编号
		if(gatheringManageVo.getEnterpriseId()!=null && !gatheringManageVo.getEnterpriseId().equals("")){
			hql += " AND b.enterprise_id="+gatheringManageVo.getEnterpriseId()+"";
		}
		if(gatheringManageVo.getXs_Custom_Name()!=null && !gatheringManageVo.getXs_Custom_Name().equals("")){
			hql += " and b.xs_custom_name like '%"+StringEscapeUtils.escapeSql(gatheringManageVo.getXs_Custom_Name().trim())+"%'";
		}
		if(gatheringManageVo.getXs_Car_Vin_Number()!=null && !gatheringManageVo.getXs_Car_Vin_Number().equals("")){
			hql += " and c.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(gatheringManageVo.getXs_Car_Vin_Number().trim())+"%'";
		}
		
		List rlist = gatheringManageDao.createSQLQuery(hql,gatheringManageVo.getPage(),gatheringManageVo.getRows());

		List list2 = new ArrayList();
		if(rlist != null && rlist.size()>0){
			Object[] obj = null; 
		for (int i = 0; i < rlist.size(); i++){
			obj = (Object[])rlist.get(i);
			GatheringManageVo vo = new GatheringManageVo();
			vo.setAccount_Code(obj[0]+"");
			vo.setAccount_Date(obj[1]+"");
			vo.setAccount_Amount(obj[2]+"");
			vo.setAccount_Arrears(obj[3]+"");
			vo.setAccount_Arrears_Age(obj[4]+"");
			vo.setAccount_Cumulative(obj[5]+"");
			vo.setAccount_Person(SystemUser.getUser().getBasStuff().getStfName());
			vo.setAccount_Receivables(obj[7]+"");
			
			List datalist = gatheringManageDao.createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id="+obj[8]+"");
			vo.setAccount_Unfinished(datalist.get(0).toString());
			vo.setCollections_Id(obj[9]+"");
			
			//获取数据字典中的对应的类型
			List clist = gatheringManageDao.createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id="+obj[10]+"");
			if(clist!=null && clist.size()>0){
				vo.setAccount_Type(clist.get(0)+"");
			}
			vo.setCustom_Id(obj[11]+"");
			vo.setXs_Custom_Name(obj[12]+"");
			vo.setXs_Car_Id(obj[13]+"");
			vo.setXs_Car_Code(obj[14]+"");
			vo.setXs_Car_Vin_Number(obj[15]+"");
			list2.add(vo);
			}
		}
		Json json = new Json();
		json.setRows(list2);
		json.setTotal(gatheringManageDao.getCountBySQL(hql));
		return json;
	
	}

	/**
	 * 获取收款记录
	 */
	
	public Json getAmonunt(GatheringManageVo gatheringManageVo)
			throws Exception {
		return gatheringManageDao.getAmonunt(gatheringManageVo);
	}

	
	/***
	 *  转收银  
	 *  xsSellAccount (结算单记录)
	 */
	public void addChangeRegister(XsSellAccount xsSellAccount)
			throws Exception {
		XsSellCollections xsSellCollections = new XsSellCollections();
		xsSellCollections.setAccountId(xsSellAccount.getAccountCode());
		xsSellCollections.setAccountCode(CreateID.createId("SYCOLLECTIONS", Contstants.SELL_BILLSDEPLOY.COLLECTIONS));
		xsSellCollections.setAccountReceivables(xsSellAccount.getAccountMoney());
		xsSellCollections.setAccountCumulative(0.0);
		xsSellCollections.setAccountAmount(xsSellAccount.getAccountMoney());
		xsSellCollections.setAccountArrears(xsSellAccount.getAccountMoney());
		//List list = gatheringManageDao.createSQLQuery("SELECT child_id FROM xs_childdictionary WHERE datakey='Wfq'");
		Integer fin=baseTagDAO.getChildId(Contstants.BASE_SELL.SHIFOUFUQING, Contstants.BASE_SELL.WFQ,xsSellAccount.getEnterpriseId());
		XsChilddictionary fq=null;
		if(fin!=null){
		 fq=baseTagDAO.findById(fin);
		}
		xsSellCollections.setXsSellCollectionUnfinished(fq); //付清、 未付清
		xsSellCollections.setAccountArrearsAge(0.0);
		//List list2 = gatheringManageDao.createSQLQuery("SELECT child_id FROM xs_childdictionary WHERE datakey='"+Contstants.BASE_SELL.JSSK+"'");
		Integer type=baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.JSSK,xsSellAccount.getEnterpriseId());
		XsChilddictionary ty=null;
		if(type!=null){
			ty=baseTagDAO.findById(type);
		}
		xsSellCollections.setXsSellCollectionType(ty);   //结算收款、  预收款 、   调拨收款
		xsSellCollections.setAccountDate(new java.sql.Date(new java.util.Date().getTime()).toString());
		xsSellCollections.setAccountPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
		xsSellCollections.setCustomId(xsSellAccount.getXsCarSellInfo().getCustomId());
		xsSellCollections.setXsCarId(xsSellAccount.getXsCarSellInfo().getXsCarId());
		XsChilddictionary classfy=null;
		if((xsSellAccount.getAccountType())!=null){
			classfy=baseTagDAO.findById(xsSellAccount.getAccountType());
		}
		xsSellCollections.setXsSellCollectionClassify(classfy);
		gatheringManageDao.save(xsSellCollections);
	}

	
	/**
	 * 新增预收记录
	 */
	
	public void addYamount(GatheringManageVo gatheringManageVo)
			throws Exception {
		
		XsSellCollections XsSellCollections = (XsSellCollections)gatheringManageDao.get("from  XsSellCollections where accountId='"+gatheringManageVo.getReserve_Code()+"'");
		//查询预收记录如果有该记录则证明该车辆已预收过 即 修改累计金额即可  （累加预收款金额）
		//如果有此记录则只需修改相应的金额即可
		if(XsSellCollections!=null){
			//累加 累计金额
			//累计收取金额
			Double AccountCumulative = gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("") ? Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00;
			XsSellCollections.setAccountCumulative(AccountCumulative + XsSellCollections.getAccountCumulative());
			gatheringManageDao.merge(XsSellCollections);
		
			//添加预收款使用记录
			XsSellCollections  xsSellCollections = new XsSellCollections();
			XsSellCollectionsDetail xsSellCollectionsDetail = new XsSellCollectionsDetail();
			xsSellCollectionsDetail.setDate(new java.sql.Date(new java.util.Date().getTime()).toString());
			Integer childIds=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,gatheringManageVo.getEnterpriseId());
			XsChilddictionary examines=baseTagDAO.findById(childIds);
			xsSellCollectionsDetail.setXsSellCollectionsDetailExamine(examines);
			Integer typeId=baseTagDAO.getChildId(Contstants.BASE_SELL.KXLX,Contstants.BASE_SELL.SR,gatheringManageVo.getEnterpriseId());
			XsChilddictionary type=baseTagDAO.findById(typeId);
			xsSellCollectionsDetail.setXsSellCollectionsDetailDetailType(type);
			XsChilddictionary invoice=null;
			if(gatheringManageVo.getInvoice()!=null && !gatheringManageVo.getInvoice().equals("")){
				 invoice=baseTagDAO.findById(Integer.parseInt(gatheringManageVo.getInvoice()));
			}
		
			xsSellCollectionsDetail.setXsSellCollectionsDetailInvoice(invoice);
			xsSellCollectionsDetail.setInvoiceNum(gatheringManageVo.getInvoice_Num());
			xsSellCollectionsDetail.setReceivedMoney(gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("")?Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00);
			XsChilddictionary way=null;
			if(gatheringManageVo.getReceived_Way()!=null && !gatheringManageVo.getReceived_Way().equals("")){
				way=baseTagDAO.findById(Integer.parseInt(gatheringManageVo.getReceived_Way()));
			}
			xsSellCollectionsDetail.setXsSellCollectionsDetailWay(way);
			xsSellCollectionsDetail.setRemark(gatheringManageVo.getRemark());
			xsSellCollectionsDetail.setStfId(gatheringManageVo.getStf_Id()!=null && !gatheringManageVo.getStf_Id().equals("")?Integer.parseInt(gatheringManageVo.getStf_Id()) : null);
			//cllid
			xsSellCollections.setCollectionsId(Integer.parseInt(XsSellCollections.getCollectionsId().toString()));
			xsSellCollectionsDetail.setXsSellCollections(xsSellCollections);
			gatheringManageDao.save(xsSellCollectionsDetail);
		
		}else{
			
			XsSellCollections xsSellCollections = new XsSellCollections();
			//预收款时 此AccountId 为预定单号reserve_Code
			xsSellCollections.setAccountId(gatheringManageVo.getReserve_Code()!=null && !gatheringManageVo.getReserve_Code().equals("") ? gatheringManageVo.getReserve_Code() : null);
			String code = CreateID.createId("SYCOLLECTIONS", Contstants.SELL_BILLSDEPLOY.COLLECTIONS);
			xsSellCollections.setAccountCode(code);
			//累计收取金额
			Double AccountCumulative = gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("") ? Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00;
			xsSellCollections.setAccountCumulative(AccountCumulative);
			Integer typeId=baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.YSK,gatheringManageVo.getEnterpriseId());
			//收款类型
			XsChilddictionary type=null;
			if(typeId!=null){
				type=baseTagDAO.findById(typeId);
			}
			xsSellCollections.setXsSellCollectionType(type);   //结算收款、  预收款 、   调拨收款
			xsSellCollections.setAccountDate(new java.sql.Date(new java.util.Date().getTime()).toString());
			
			xsSellCollections.setCustomId(gatheringManageVo.getCustom_Id()!=null && !gatheringManageVo.getCustom_Id().equals("") ? Integer.parseInt(gatheringManageVo.getCustom_Id()) : null);
			xsSellCollections.setXsCarId(gatheringManageVo.getXs_Car_Id()!=null && !gatheringManageVo.getXs_Car_Id().equals("") ? Integer.parseInt(gatheringManageVo.getXs_Car_Id()) : null);
			
			xsSellCollections.setAccountPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
			
			Serializable collectionsId = gatheringManageDao.save(xsSellCollections);
			
			//添加预收款使用记录
			XsSellCollectionsDetail xsSellCollectionsDetail = new XsSellCollectionsDetail();
			xsSellCollectionsDetail.setDate(new java.sql.Date(new java.util.Date().getTime()).toString());
			Integer childIds=baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,gatheringManageVo.getEnterpriseId());
			XsChilddictionary examines=baseTagDAO.findById(childIds);
			xsSellCollectionsDetail.setXsSellCollectionsDetailExamine(examines);
			//xsSellCollectionsDetail.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,gatheringManageVo.getEnterpriseId()));
			XsChilddictionary invoice=null;
			if(gatheringManageVo.getInvoice()!=null && !gatheringManageVo.getInvoice().equals("")){
				 invoice=baseTagDAO.findById(Integer.parseInt(gatheringManageVo.getInvoice()));
			}
		
			xsSellCollectionsDetail.setXsSellCollectionsDetailInvoice(invoice);
			//xsSellCollectionsDetail.setInvoice(gatheringManageVo.getInvoice()!=null && !gatheringManageVo.getInvoice().equals("")?Integer.parseInt(gatheringManageVo.getInvoice()) : null);
			xsSellCollectionsDetail.setInvoiceNum(gatheringManageVo.getInvoice_Num());
			xsSellCollectionsDetail.setReceivedMoney(gatheringManageVo.getReceived_Money()!=null && !gatheringManageVo.getReceived_Money().equals("")?Double.parseDouble(gatheringManageVo.getReceived_Money()) : 0.00);
			XsChilddictionary way=null;
			if(gatheringManageVo.getReceived_Way()!=null && !gatheringManageVo.getReceived_Way().equals("")){
				way=baseTagDAO.findById(Integer.parseInt(gatheringManageVo.getReceived_Way()));
			}
			xsSellCollectionsDetail.setXsSellCollectionsDetailWay(way);
			//xsSellCollectionsDetail.setReceivedWay(gatheringManageVo.getReceived_Way()!=null && !gatheringManageVo.getReceived_Way().equals("")?Integer.parseInt(gatheringManageVo.getReceived_Way()) : null);
			xsSellCollectionsDetail.setRemark(gatheringManageVo.getRemark());
			xsSellCollectionsDetail.setStfId(gatheringManageVo.getStf_Id()!=null && !gatheringManageVo.getStf_Id().equals("")?Integer.parseInt(gatheringManageVo.getStf_Id()) : null);
			Integer typeIds=baseTagDAO.getChildId(Contstants.BASE_SELL.KXLX,Contstants.BASE_SELL.SR,gatheringManageVo.getEnterpriseId());
			XsChilddictionary types=baseTagDAO.findById(typeIds);
			xsSellCollectionsDetail.setXsSellCollectionsDetailDetailType(types);
			//xsSellCollectionsDetail.setDetailType(baseTagDAO.getChildId(Contstants.BASE_SELL.KXLX,Contstants.BASE_SELL.SR,gatheringManageVo.getEnterpriseId()));
			//获取收款编号
			xsSellCollections.setCollectionsId(collectionsId!=null ? Integer.parseInt(collectionsId.toString()) : null );
			xsSellCollectionsDetail.setXsSellCollections(xsSellCollections);
			gatheringManageDao.save(xsSellCollectionsDetail);
			
		}
		
	}

	/**
	 * 删除预收款记录
	 */
	
	public void deleteYamount(GatheringManageVo gatheringManageVo)
			throws Exception {
		gatheringManageDao.deleteYamount(gatheringManageVo);
		
	}

	/**
	 * 修改预收款记录
	 */
	
	public void updateYamount(GatheringManageVo gatheringManageVo)
			throws Exception {
		gatheringManageDao.updateYamount(gatheringManageVo);
		
	}

	/**
	 * 获取应收款信息
	 */
	
	public Json findShouldAccountInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		return gatheringManageDao.findShouldAccountInfor(gatheringManageVo,baseTagDAO);
	}
	/**
	 * 获取预收使用记录
	 */
	
	public List getUseRecord(GatheringManageVo gatheringManageVo)
			throws Exception {
		return gatheringManageDao.getUseRecord(gatheringManageVo,baseTagDAO);
	}
	/**
	 * 预订单是否已结算转收银
	 * */
	
	public Boolean isToBalance(GatheringManageVo gatheringManageVo)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("SELECT ( CASE  WHEN xsa.account_state=");
		sb.append(" (SELECT xc.child_id FROM xs_childdictionary xc INNER JOIN xs_parentdictionary xp ON xp.parent_id=xc.parent_id");
		sb.append(" WHERE xp.datakey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND xc.datakey='"+Contstants.BASE_SELL.PAYMENTSTATE1+"') THEN 'true' ELSE 'false' END ) AS temp");	 
		sb.append(" FROM xs_sell_car_reserve xscr INNER JOIN xs_car_sell_info xcsi ON xcsi.reserve_id=xscr.reserve_id");	
		sb.append(" INNER JOIN xs_sell_account xsa ON xsa.xs_car_sel_id=xcsi.xs_car_sel_id WHERE xscr.reserve_id='"+gatheringManageVo.getReserve_Id()+"'");
		List list=gatheringManageDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0)
		  return Boolean.parseBoolean(list.get(0).toString());
		return false;
	}
	/**
	 * 查找预订单最大可预交金额
	 * */
	
	public Double findMaxCouldToBalance(GatheringManageVo gatheringManageVo)
			throws Exception {
		List list=gatheringManageDao.createSQLQuery("SELECT xscr.payment_money FROM xs_sell_car_reserve xscr WHERE xscr.reserve_id='"+gatheringManageVo.getReserve_Id()+"' AND xscr.enterprise_id="+gatheringManageVo.getEnterpriseId()+"");
		if(list!=null&&list.size()>0)
			return (Double)list.get(0);
		return 0d;
	}
	/**
	 * 收取预收款的时候判断改单子是否已经通知退款
	 */
	
	public boolean dowhetheradvice(GatheringManageVo gatheringManageVo)throws Exception{
		XsSellCarReserve xsBackCarLog = (XsSellCarReserve)gatheringManageDao.get("from XsSellCarReserve WHERE xsChilddictionary7.childId="+ baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE,Contstants.BASE_SELL.QUXIAO,gatheringManageVo.getEnterpriseId())+" and   reserveCode='"+gatheringManageVo.getReserve_Code()+"'");
		if(xsBackCarLog!=null){
			return false;
		}else{
			return true;
		}
		
	}
	
	public Datagrid getReadyBillInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		// TODO Auto-generated method stub
		return gatheringManageDao.getReadyBillInfor(gatheringManageVo);
	}
	
	public Datagrid getYuEBillInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		// TODO Auto-generated method stub
		return gatheringManageDao.getYuEBillInfor(gatheringManageVo);
	}
	
	public Datagrid getQkBillInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		// TODO Auto-generated method stub
		return gatheringManageDao.getQkBillInfor(gatheringManageVo);
	}
	
	public Datagrid getShouldBillInfor(GatheringManageVo gatheringManageVo)
			throws Exception {
		// TODO Auto-generated method stub
		return gatheringManageDao.getShouldBillInfor(gatheringManageVo);
	}
}
