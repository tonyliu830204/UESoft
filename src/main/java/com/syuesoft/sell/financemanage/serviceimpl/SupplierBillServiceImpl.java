package com.syuesoft.sell.financemanage.serviceimpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasStuff;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.financemanage.dao.SupplierBillDao;
import com.syuesoft.sell.financemanage.service.SupplierBillService;
import com.syuesoft.sell.financemanage.vo.SupplierBillVo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellAllocatel;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSuppliertraderAccount;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.SystemUser;

@Service("supplierBillService")
public class SupplierBillServiceImpl extends BaseLogServiceImpl implements SupplierBillService {

	@Resource
	private SupplierBillDao supplierBillDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	/**
	 * 应付款查询
	 */
	
	public Json getDueFromInfor(SupplierBillVo supplierBillVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取对账单明细父节点信息
	 */
	
	public List getSupplierBillDetailPrentInfor(SupplierBillVo supplierBillVo)
			throws Exception {
		StringBuffer buf = new StringBuffer("SELECT a.instorehouse_id,a.instorehouse_code,COUNT(*), a.tax FROM xs_sell_instorehouse a where 1 = 1 ");
		if(supplierBillVo.getInstorehouseCode()!=null && !supplierBillVo.getInstorehouseCode().equals("")){
			String[] str =  supplierBillVo.getInstorehouseCode().split(",");
			String str2 = "";
			for (int i = 0; i < str.length; i++) {
				str2 += ",'"+str[i]+"'";
			}
			buf.append(" and a.instorehouse_code IN ("+str2.substring(1)+") ");
		}
		buf.append("GROUP BY a.instorehouse_id");
		List list = supplierBillDao.createSQLQuery(buf.toString());
		List<SupplierBillVo> rlist = new ArrayList();
		Object[] obj = null;
		if(list!=null && list.size()>0){
			SupplierBillVo vo = null;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[])list.get(i);
			vo = new SupplierBillVo();
			
			if(obj[0]!=null){vo.setInstorehouseId(obj[0].toString());}
			if(obj[1]!=null){vo.setInstorehouseCode(obj[1].toString());}
			if(obj[2]!=null){vo.setNumber(obj[2].toString());}
			if(obj[3]!=null){vo.setTax(obj[3].toString());}
			vo.setState("closed");
			vo.setIconCls("icon-blank");
			rlist.add(vo);
			}
		//汇总
		vo = new SupplierBillVo();
		vo.setInstorehouseCode("汇总");
		vo.setState("open");
		vo.setIconCls("icon-blank");
		int count = 0;
		Double tax = 0.0;
		for (SupplierBillVo supplierBillVo2 : rlist) {
			count += Integer.parseInt(supplierBillVo2.getNumber());
			tax += Double.parseDouble(supplierBillVo2.getTax());
		}
		vo.setNumber(count+"");
		vo.setTax(tax+"");
		rlist.add(vo);
		}
		return rlist;
	}
	
	/**
	 * 获取供应商对账单明细子节点信息
	 */
	
	public List getSupplierBillDetailChildInfor(SupplierBillVo supplierBillVo)
			throws Exception {
		StringBuffer buf = new StringBuffer("SELECT c.xs_car_id, c.xs_car_code, c.xs_car_vin_number, c.xs_car_licenseName,COUNT(*),b.vehicle_cost " +
		"FROM xs_sell_instorehouse_del b, xs_car_info c " +
		"WHERE  b.xs_car_id = c.xs_car_id " +
		"and b.instorehouse_id="+supplierBillVo.getInstorehouseId()+" " +
		"GROUP BY c.xs_car_id ");
		List list = supplierBillDao.createSQLQuery(buf.toString());
		List rlist = new ArrayList();
		Object[] obj = null;
		if(list!=null && list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[])list.get(i);
			SupplierBillVo vo = new SupplierBillVo();
			if(obj[0]!=null){vo.setXsCarId(obj[0].toString());}
			if(obj[1]!=null){vo.setXsCarCode(obj[1].toString());}
			if(obj[2]!=null){vo.setXsCarVinNumber(obj[2].toString());}
			if(obj[3]!=null){vo.setXsCarLicenseName(obj[3].toString());}
			if(obj[4]!=null){vo.setNumber(obj[4].toString());}
			if(obj[5]!=null){vo.setVehicleCost(obj[5].toString());}
			vo.setInstorehouseCode("");
			vo.setState("open");
			vo.setIconCls("icon-blank");
			rlist.add(vo);
			}
		}
		return rlist;
	}

	/**
	 * 获取供应商对账单汇总信息
	 */
	
	public Json getSupplierBillInfor(SupplierBillVo supplierBillVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 		
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		
				Contstants.COLLIGATES.DEFAULTSHOWDAY,supplierBillVo.getEnterpriseId()).getCiValue()));
		String hql = "from XsSuppliertraderAccount a where 1 = 1 and a.accountType="+
			baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPES, Contstants.BASE_SELL.SUPPLIERTYPES,supplierBillVo.getEnterpriseId())+"";
		
		if(supplierBillVo.getAccountDate()!=null&&!"".equals(supplierBillVo.getAccountDate())){
			hql += " and DATE(a.accountDate) >='"+supplierBillVo.getAccountDate()+"'";
			}
		if(supplierBillVo.getAccountDate2()!=null&&!"".equals(supplierBillVo.getAccountDate2())){
			hql += " and DATE(a.accountDate) <='"+supplierBillVo.getAccountDate2()+"'";
			
		}
		if((supplierBillVo.getAccountDate()==null||"".equals(supplierBillVo.getAccountDate()))&&
				(supplierBillVo.getAccountDate2()==null||"".equals(supplierBillVo.getAccountDate2()))	){
			hql += " and DATE(a.accountDate) BETWEEN '"+sdate+"' AND '"+edate+"'";
		}
		if(supplierBillVo.getAccountCode()!=null && !supplierBillVo.getAccountCode().equals("")){
			hql += " and a.accountCode like '%"+StringEscapeUtils.escapeSql(supplierBillVo.getAccountCode().trim())+"%'";
		}
		List<XsSuppliertraderAccount> rlsit = supplierBillDao.find(hql,supplierBillVo.getPage(),supplierBillVo.getRows());
		List list = new ArrayList();
		if(rlsit!=null && rlsit.size()>0){
			for (XsSuppliertraderAccount xsSuppliertraderAccount : rlsit) {
				SupplierBillVo vo = new SupplierBillVo();
				vo.setAccountBalance(xsSuppliertraderAccount.getAccountBalance()!=null ? xsSuppliertraderAccount.getAccountBalance().toString() : "");
				vo.setAccountMoney(xsSuppliertraderAccount.getAccountMoney()!=null ? xsSuppliertraderAccount.getAccountMoney().toString() : "");
				BasStuff basStuff = (BasStuff)supplierBillDao.get("from BasStuff WHERE stfId="+xsSuppliertraderAccount.getAccountPerson()+"");
				vo.setAccountPerson(basStuff.getStfId()!=null ? basStuff.getStfId().toString() : "");
				vo.setAccountPersonName(basStuff.getStfName()!=null ? basStuff.getStfName().toString() : "");
				vo.setAccountSun(xsSuppliertraderAccount.getAccountSun()!=null ? xsSuppliertraderAccount.getAccountSun().toString() : "");
				XsSellInstorehouse xsSellInstorehouse2 = null;
				if(xsSuppliertraderAccount.getInstorehouseId()!=null && !xsSuppliertraderAccount.getInstorehouseId().equals("")){
					xsSellInstorehouse2 = (XsSellInstorehouse)supplierBillDao.get("from XsSellInstorehouse WHERE instorehouseId="+xsSuppliertraderAccount.getInstorehouseId()+"");
					if(xsSellInstorehouse2!=null){
						vo.setInstorehouseCode(xsSellInstorehouse2.getInstorehouseCode()!=null ? xsSellInstorehouse2.getInstorehouseCode().toString() : "");
						vo.setNumber(xsSellInstorehouse2.getNumber()!=null ? xsSellInstorehouse2.getNumber().toString() : "");
						vo.setState1(xsSellInstorehouse2.getState1()!=null ? xsSellInstorehouse2.getState1()+"" : "");
						XsChilddictionary xsChilddictionary = null;
						if(xsSellInstorehouse2.getState1()!=null && !xsSellInstorehouse2.getState1().equals("")){
							xsChilddictionary = (XsChilddictionary)supplierBillDao.get("from XsChilddictionary WHERE childId="+xsSellInstorehouse2.getState1()+""+"");
							vo.setState2(xsChilddictionary!=null ? xsChilddictionary.getDataValue()+"" : "");
						}
						
						vo.setRemark(xsSellInstorehouse2!=null ? xsSellInstorehouse2.getRemark()+"" :"");
						
					}
				}
				
				vo.setAccountId(xsSuppliertraderAccount.getAccountId()!=null ? xsSuppliertraderAccount.getAccountId().toString() : "");
				vo.setAccountDate(xsSuppliertraderAccount.getAccountDate()!=null ? xsSuppliertraderAccount.getAccountDate().toString() : "");
				vo.setAccountCode(xsSuppliertraderAccount.getAccountCode()!=null ? xsSuppliertraderAccount.getAccountCode() : "");
				vo.setRemark(xsSuppliertraderAccount.getRemark());
				list.add(vo);
				}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(supplierBillDao.getCount(hql));
		return json;
	}

	/**
	 * 添加供应商对账信息
	 */
	@Log(systemName="销售系统", moduleName="供应商对账单管理",opertype="新增")
	
	public void addSupplierBillInfor(SupplierBillVo supplierBillVo)
			throws Exception {
		XsSuppliertraderAccount xsSupplierTraderAccount = new XsSuppliertraderAccount();
		if(supplierBillVo.getInstorehouseId()!=null && !supplierBillVo.getInstorehouseId().equals("")){
			
			xsSupplierTraderAccount.setAccountCode(CreateID.createId("XsSuppliertraderAccount", Contstants.SELL_BILLSDEPLOY.SUPPLIERTRADERAMOUNT));
			xsSupplierTraderAccount.setAccountDate(new java.util.Date());
			xsSupplierTraderAccount.setInstorehouseId(supplierBillVo.getInstorehouseId()!=null && !supplierBillVo.getInstorehouseId().equals("") ? Integer.parseInt(supplierBillVo.getInstorehouseId()): 0);
			xsSupplierTraderAccount.setAccountPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
			xsSupplierTraderAccount.setAccountSun(supplierBillVo.getTax()!=null && !supplierBillVo.getTax().equals("") ? Double.parseDouble(supplierBillVo.getTax()) : 0.00);
			xsSupplierTraderAccount.setAccountMoney(supplierBillVo.getNowtax()!=null && !supplierBillVo.getNowtax().equals("") ? Double.parseDouble(supplierBillVo.getNowtax()) : 0.00);
			//金额 - 本次收款 = 应收款（余额）
			Double AccountBalance = Double.parseDouble(supplierBillVo.getTax()!=null && !supplierBillVo.getTax().equals("") ? supplierBillVo.getTax() : "0.00") - Double.parseDouble(supplierBillVo.getNowtax()!=null && !supplierBillVo.getNowtax().equals("") ? supplierBillVo.getNowtax() : "0.00");
			xsSupplierTraderAccount.setAccountBalance(AccountBalance!=null ? AccountBalance : 0.00);
			xsSupplierTraderAccount.setAccountType(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPES, Contstants.BASE_SELL.SUPPLIERTYPES,supplierBillVo.getEnterpriseId()));
			xsSupplierTraderAccount.setRemark(supplierBillVo.getRemark()+"");
			//修改入库单 付讫状态
			XsSellInstorehouse xsSellInstorehouse = (XsSellInstorehouse)supplierBillDao.get("from XsSellInstorehouse WHERE instorehouseId="+supplierBillVo.getInstorehouseId()+"");
			xsSellInstorehouse.setState1(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE, Contstants.BASE_SELL.PAYMENTSTATE1,supplierBillVo.getEnterpriseId()));
			supplierBillDao.merge(xsSellInstorehouse);
			//添加企业编号
			xsSupplierTraderAccount.setEnterpriseId(supplierBillVo.getEnterpriseId());
			supplierBillDao.save(xsSupplierTraderAccount);
			setContent(SystemUser.getUser().getBasStuff().getStfName()+"新增单号为"+xsSupplierTraderAccount.getAccountCode()+"的对账信息！");
		}
		
	} 

	/**
	 * 获取入库信息
	 */
	

	public Datagrid getInterStore(SupplierBillVo supplierBillVo) throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer buf = new StringBuffer("from XsSellInstorehouse hose where " +
				" hose.enterprise_id="+supplierBillVo.getEnterpriseId()+"");
		if(supplierBillVo.getInstorehouseDate()!=null&&!"".equals(supplierBillVo.getInstorehouseDate())){
			buf.append(" and  Date(hose.instorehouseDate)>='"+supplierBillVo.getInstorehouseDate()+"'");
		}

		if(supplierBillVo.getInstorehouseDate2()!=null&&!"".equals(supplierBillVo.getInstorehouseDate2())){
			buf.append(" and  Date(hose.instorehouseDate)<='"+supplierBillVo.getInstorehouseDate2()+"'");
		}
		List<XsSellInstorehouse>  xsSellInstorehouselist =  supplierBillDao.find(buf.toString(),supplierBillVo.getPage(),supplierBillVo.getRows());
		List<SupplierBillVo> list = new ArrayList<SupplierBillVo> ();
		if(xsSellInstorehouselist!=null&&xsSellInstorehouselist.size()>0){
			for (XsSellInstorehouse xsSellInstorehouse2 : xsSellInstorehouselist) {
				SupplierBillVo vo = new SupplierBillVo();
				vo.setInstorehouseCode(xsSellInstorehouse2.getInstorehouseCode()!=null ? xsSellInstorehouse2.getInstorehouseCode() : "");
				vo.setInstorehouseId(xsSellInstorehouse2.getInstorehouseId()!=null ? xsSellInstorehouse2.getInstorehouseId().toString() : "");
				vo.setInstorehouseDate(xsSellInstorehouse2.getInstorehouseDate()!=null ? xsSellInstorehouse2.getInstorehouseDate().toString() : "");
				vo.setNumber(xsSellInstorehouse2.getNumber()!=null ? xsSellInstorehouse2.getNumber().toString() : "");
				vo.setTax(xsSellInstorehouse2.getTax()!=null ? xsSellInstorehouse2.getTax().toString() : "");
				list.add(vo);
			}
		}
		
	
		int total = supplierBillDao.getCount(buf.toString());
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 删除供应商对款单汇总记录
	 */
	@Log(systemName="销售系统", moduleName="供应商对账单管理",opertype="删除")
	
	public void doDeleteInfor(SupplierBillVo supplierBillVo)throws Exception {
		XsSuppliertraderAccount xsSupplierTraderAccount = (XsSuppliertraderAccount)supplierBillDao.get("from XsSuppliertraderAccount where accountId="+supplierBillVo.getAccountId()+"");
		supplierBillDao.delete(xsSupplierTraderAccount);
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"删除单号为"+xsSupplierTraderAccount.getAccountCode()+"的对账信息！");
		
	}

	/**
	 * 修改供应商对款单记录
	 */
	@Log(systemName="销售系统", moduleName="供应商对账单管理",opertype="修改")
	
	public void doEditInfor(SupplierBillVo supplierBillVo) throws Exception {
		XsSuppliertraderAccount xsSupplierTraderAccount = (XsSuppliertraderAccount)supplierBillDao.get("from XsSuppliertraderAccount where accountId="+supplierBillVo.getAccountId()+"");
		xsSupplierTraderAccount.setRemark(supplierBillVo.getRemark()!=null ? supplierBillVo.getRemark() : "");
		supplierBillDao.merge(xsSupplierTraderAccount);
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"修改单号为"+xsSupplierTraderAccount.getAccountCode()+"的对账信息！");
		
	}

	/**
	 * 获取供应商应付款查询父节点记录信息
	 */
	
	public List getDuePrentInfor(SupplierBillVo supplierBillVo) throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 		
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		
				Contstants.COLLIGATES.DEFAULTSHOWDAY,supplierBillVo.getEnterpriseId()).getCiValue()));
		StringBuffer buf = new StringBuffer("SELECT a.instorehouse_id, a.instorehouse_code, a.instorehouse_date, b.account_money, b.account_balance " +
				"FROM  xs_sell_instorehouse a, xs_supplierTrader_account b " +
				"WHERE a.instorehouse_id = b.instorehouse_id ");
		
		if(supplierBillVo.getEnterpriseId()!=null && !supplierBillVo.getEnterpriseId().equals("")){
			buf.append(" and a.enterprise_id = "+supplierBillVo.getEnterpriseId()+"");
		}
		if(supplierBillVo.getInstorehouseDate()!=null && !supplierBillVo.getInstorehouseDate().equals("")){
			buf.append(" and Date(a.instorehouse_date) >= '"+supplierBillVo.getInstorehouseDate()+"'");
		}
		if(supplierBillVo.getInstorehouseDate2()!=null && !supplierBillVo.getInstorehouseDate2().equals("")){
			buf.append(" and Date(a.instorehouse_date) <= '"+supplierBillVo.getInstorehouseDate2()+"'");
		}
		if((supplierBillVo.getInstorehouseDate()==null ||supplierBillVo.getInstorehouseDate().equals(""))&&
				(supplierBillVo.getInstorehouseDate2()==null ||supplierBillVo.getInstorehouseDate2().equals(""))){

			buf.append(" and Date(a.instorehouse_date) between '"+sdate+"' and '"+edate+"'");
		}
		buf.append("GROUP BY a.instorehouse_id");
		List list = supplierBillDao.createSQLQuery(buf.toString());
		List<SupplierBillVo> rlist = new ArrayList();
		Object[] obj = null;
		if(list!=null && list.size()>0){
			SupplierBillVo vo = null;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[])list.get(i);
			vo = new SupplierBillVo();
			
			if(obj[0]!=null){vo.setInstorehouseId(obj[0].toString());}
			if(obj[1]!=null){vo.setInstorehouseCode(obj[1].toString());}
			if(obj[2]!=null){vo.setInstorehouseDate(obj[2].toString());}
			if(obj[3]!=null){vo.setAccountMoney(obj[3].toString());}
			if(obj[3]!=null){vo.setAccountBalance(obj[3].toString());}
			vo.setState("closed");
			vo.setIconCls("icon-blank");
			rlist.add(vo);
			}
		vo = new SupplierBillVo();
		vo.setInstorehouseCode("汇总");
		Double accountMoney =0.0;
		Double accountBalance =0.0;
		for (SupplierBillVo supplierBillVo2 : rlist) {
			accountMoney += Double.parseDouble(supplierBillVo2.getAccountMoney());
			accountBalance += Double.parseDouble(supplierBillVo2.getAccountBalance());
		}
		vo.setAccountMoney(accountMoney+"");
		vo.setAccountBalance(accountBalance+"");
		vo.setState("open");
		vo.setIconCls("icon-blank");
		rlist.add(vo);
		}
		return rlist;
	}

	/**
	 *  获取供应商应付款查询子节点记录信息
	 */
	
	public List getDueChildInfor(SupplierBillVo supplierBillVo)
			throws Exception {
		StringBuffer buf = new StringBuffer("SELECT c.xs_car_id, c.xs_car_vin_number," +
				" (SELECT e.datavalue FROM xs_childdictionary  e WHERE e.child_id = c.xs_car_brand) AS xs_car_brand," +
				" d.xs_model_name" +
				" FROM xs_sell_instorehouse_del b, xs_car_info c, xs_car_model d" +
				" WHERE  b.xs_car_id = c.xs_car_id" +
				" AND c.xs_car_model_id = d.xs_model_id" +
				" and b.instorehouse_id="+supplierBillVo.getInstorehouseId()+"" +
				" GROUP BY c.xs_car_id ");
		
		List list = supplierBillDao.createSQLQuery(buf.toString());
		List rlist = new ArrayList();
		Object[] obj = null;
		if(list!=null && list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[])list.get(i);
			SupplierBillVo vo = new SupplierBillVo();
			if(obj[0]!=null){vo.setXsCarId(obj[0].toString());}
			if(obj[1]!=null){vo.setXsCarVinNumber(obj[1].toString());}
			if(obj[2]!=null){vo.setXsCarBrand(obj[2].toString());}
			if(obj[3]!=null){vo.setXsCarModel(obj[3].toString());}
			vo.setInstorehouseCode("");
			vo.setState("open");
			vo.setIconCls("icon-blank");
			rlist.add(vo);
			}
		}
		return rlist;
	}

	/**
	 * 获取分销商对账记录
	 */
	
	public Datagrid getDisBill(SupplierBillVo supplierBillVo)
			throws Exception {

		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, 		
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		
				Contstants.COLLIGATES.DEFAULTSHOWDAY,supplierBillVo.getEnterpriseId()).getCiValue()));
		Datagrid dg = new Datagrid();
		List<SupplierBillVo> list = new ArrayList<SupplierBillVo>();
		StringBuffer sql = new StringBuffer(" SELECT A.account_id,A.account_code,A.account_date,A.instorehouse_id" +
				",b.xs_distributor_id,C.xs_distributor_name, b.allocate_code, A.account_sun," +
				"A.account_money,a.account_balance,a.account_person,D.STF_NAME,G.is_invoice," +
				"(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =G.is_invoice) AS invoice ,a.remark," +
				"b.payment_state,(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.payment_state) AS state," +
				" (SELECT  COUNT(*) FROM  xs_sell_allocatel_detail  del WHERE del.allocate_id=  b.allocate_id)  _sum " +
				"FROM xs_supplierTrader_account A " +
				"JOIN xs_sell_allocatel B ON b.allocate_id=A.instorehouse_id  " +
				"JOIN xs_distributor_info C ON C.xs_distributor_id=b.xs_distributor_id " +
				"JOIN bas_stuff D ON d.STF_ID=a.account_person " +
				" LEFT JOIN xs_sell_allocatel_detail E ON e.allocate_id=b.allocate_id" +
				" LEFT JOIN xs_sell_instorehouse_del F ON f.details_id=e.details_id " +
				"LEFT JOIN xs_car_sell_info G ON g.xs_car_id=f.xs_car_id  where 1=1" +
				" AND A.account_type=(" +
				"SELECT child.child_id FROM xs_childdictionary child , xs_parentdictionary part WHERE child.parent_id = part.parent_id AND part.dataKey='"+Contstants.BASE_SELL.ACCOUNTTYPES+"' AND child.datakey='"+Contstants.BASE_SELL.SALETYPES+"' " +
				")");
		
		if(supplierBillVo.getEnterpriseId()!=null && !supplierBillVo.getEnterpriseId().equals("")){
			sql.append(" AND A.enterprise_id="+supplierBillVo.getEnterpriseId()+"");
		}
		
		if(supplierBillVo.getAccountDate()!=null && !supplierBillVo.getAccountDate().equals("")){
			String[] str = supplierBillVo.getAccountDate().split(",");
			if(str.length>1){
				sql.append( " and DATE(a.account_Date) BETWEEN '"+str[0]+"' AND '"+str[1]+"'");
			}else{
				if(supplierBillVo.getAccountDate().length()>10){
					sql.append( " DATE(and a.account_Date) > '"+str[0]+"'");
				}else{
					sql.append (" DATE(and a.account_Date) < '"+str[0]+"'");
				}
			} 
		
		}else{
			sql.append(" and DATE(a.account_Date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if(supplierBillVo.getAccountCode()!=null && !supplierBillVo.getAccountCode().equals("")){
			sql.append(" and a.account_Code like '%"+StringEscapeUtils.escapeSql(supplierBillVo.getAccountCode().trim())+"%'");
		}
		sql.append(" group by b.allocate_id");
		List<Object[]> resultList = supplierBillDao.createSQLQuery(sql.toString(),supplierBillVo.getPage(),supplierBillVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SupplierBillVo sell = new SupplierBillVo();
				obj = resultList.get(i);
				sell.setAccountId(obj[0] != null ?obj[0].toString(): null);
				sell.setAccountCode(obj[1] != null ?obj[1].toString(): null);
				sell.setAccountDate(obj[2] != null ?fmt.format(obj[2]): null);
				sell.setInstorehouseId(obj[3] != null ?obj[3].toString(): null);
				sell.setDistributor_id(obj[4] != null ?obj[4].toString(): null);
				sell.setDistributor_name(obj[5] != null ?obj[5].toString(): null);
				sell.setAllocatecode(obj[6] != null ?obj[6].toString(): null);
				sell.setAccountSun(obj[7] != null ?new BigDecimal((Double)obj[7]).toString(): null);
				sell.setAccountMoney(obj[8] != null ?new BigDecimal((Double)obj[8]).toString(): null);
				sell.setAccountBalance(obj[9] != null ?new BigDecimal((Double)obj[9]).toString(): null);
				sell.setAccountPerson(obj[10] != null ?obj[10].toString(): null);
				sell.setAccountPersonName(obj[11] != null ?obj[11].toString(): null);
				sell.setIs_invoice(obj[12] != null ?obj[12].toString(): null);
				sell.setInvoice(obj[13] != null ?obj[13].toString(): null);
				sell.setRemark(obj[14] != null ?obj[14].toString(): null);
				sell.setState1(obj[15] != null ?obj[15].toString(): null);
				sell.setState2(obj[16] != null ?obj[16].toString(): null);
				sell.setNum(obj[17] != null ?obj[17].toString(): null);
				
				list.add(sell);
			}
		}
		
		int total =supplierBillDao.getSQLCount(sql.toString(),null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	
	}
	/**
	 * 新增分销商对款记录
	 */
	@Log(systemName="销售系统", moduleName="供应商对账单管理",opertype="新增")
	
	public void addDistributorBill(SupplierBillVo supplierBillVo)
			throws Exception {
		XsSuppliertraderAccount xsSupplierTraderAccount = new XsSuppliertraderAccount();
		if(supplierBillVo.getInstorehouseId()!=null && !supplierBillVo.getInstorehouseId().equals("")){
			
			xsSupplierTraderAccount.setAccountCode(CreateID.createId("XsSuppliertraderAccount", Contstants.SELL_BILLSDEPLOY.SUPPLIERTRADERAMOUNT));
			xsSupplierTraderAccount.setAccountDate(new java.util.Date());
			xsSupplierTraderAccount.setInstorehouseId(supplierBillVo.getInstorehouseId()!=null && !supplierBillVo.getInstorehouseId().equals("") ? Integer.parseInt(supplierBillVo.getInstorehouseId()): 0);
			xsSupplierTraderAccount.setAccountPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
			xsSupplierTraderAccount.setAccountSun(supplierBillVo.getAccountSun()!=null && !supplierBillVo.getAccountSun().equals("") ? Double.parseDouble(supplierBillVo.getAccountSun()) : 0.00);
			xsSupplierTraderAccount.setAccountMoney(supplierBillVo.getAccountMoney()!=null && !supplierBillVo.getAccountMoney().equals("") ? Double.parseDouble(supplierBillVo.getAccountMoney()) : 0.00);
			//金额 - 本次收款 = 应收款（余额）
			Double AccountBalance = Double.parseDouble(supplierBillVo.getAccountSun()!=null && !supplierBillVo.getAccountSun().equals("") ? supplierBillVo.getAccountSun() : "0.00") - Double.parseDouble(supplierBillVo.getAccountMoney()!=null && !supplierBillVo.getAccountMoney().equals("") ? supplierBillVo.getAccountMoney() : "0.00");
			xsSupplierTraderAccount.setAccountBalance(AccountBalance!=null ? AccountBalance : 0.00);
			xsSupplierTraderAccount.setAccountType(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPES, Contstants.BASE_SELL.SALETYPES,supplierBillVo.getEnterpriseId()));
			xsSupplierTraderAccount.setRemark(supplierBillVo.getRemark()+"");
			//修改入库单 付讫状态
			XsSellAllocatel xsSellAllocatel = (XsSellAllocatel)supplierBillDao.get("from XsSellAllocatel WHERE allocateId="+supplierBillVo.getInstorehouseId()+"");
			xsSellAllocatel.setPaymentState(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE, Contstants.BASE_SELL.PAYMENTSTATE1,supplierBillVo.getEnterpriseId()));
			supplierBillDao.merge(xsSellAllocatel);
			//
			xsSupplierTraderAccount.setEnterpriseId(supplierBillVo.getEnterpriseId());
			supplierBillDao.save(xsSupplierTraderAccount);
			setContent(SystemUser.getUser().getBasStuff().getStfName()+"新增单号为"+xsSupplierTraderAccount.getAccountCode()+"的分销商对账信息！");
	}
	}
	
}
