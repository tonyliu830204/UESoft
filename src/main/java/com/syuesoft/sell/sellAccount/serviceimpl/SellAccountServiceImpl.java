package com.syuesoft.sell.sellAccount.serviceimpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.financemanage.service.GatheringManageService;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellDbProject;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.model.XsSellInsurance;
import com.syuesoft.sell.model.XsSellZhProject;
import com.syuesoft.sell.sellAccount.dao.SellAccountListDao;
import com.syuesoft.sell.sellAccount.service.SellAccountService;
import com.syuesoft.sell.sellAccount.vo.SellAccountVo;
import com.syuesoft.sell.sellInsurance.dao.SellInsuranceListDao;
import com.syuesoft.sell.sellZhProject.dao.SellZhProjectDao;
import com.syuesoft.sell.sellwork.dao.CarSellManageDao;
import com.syuesoft.sell.sellwork.dao.SellDbProjectDAO;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Msg;
import com.syuesoft.util.SystemUser;

@Service("sellAccountService")
public class SellAccountServiceImpl extends BaseLogServiceImpl implements SellAccountService {
	private SellAccountListDao sellAccountListDao;
	private SellDbProjectDAO sellDbProjectDAO;
	private BaseTagDAO baseTagDAO;
	private SellZhProjectDao sellZhProjectDao;
	private SellInsuranceListDao sellInsuranceListDao;
	private CarSellManageDao carSellManageDao;
	private GatheringManageService gatheringManageService;
	private XsSellFlowControlDao xsSellFlowControlDao ;
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO ;
	
	public XsSellFlowControlDao getXsSellFlowControlDao() {
		return xsSellFlowControlDao;
	}
	@Autowired
	public void setXsSellFlowControlDao(XsSellFlowControlDao xsSellFlowControlDao) {
		this.xsSellFlowControlDao = xsSellFlowControlDao;
	}
	public CarSellManageDao getCarSellManageDao() {
		return carSellManageDao;
	}
	@Autowired
	public void setCarSellManageDao(CarSellManageDao carSellManageDao) {
		this.carSellManageDao = carSellManageDao;
	}
	public SellInsuranceListDao getSellInsuranceListDao() {
		return sellInsuranceListDao;
	}
	@Autowired
	public void setSellInsuranceListDao(SellInsuranceListDao sellInsuranceListDao) {
		this.sellInsuranceListDao = sellInsuranceListDao;
	}

	/*private static String insuranceType="是";
	private static String type="代办";
	private static String type1="保险";
	private static String type2="装潢";
	private static String type3="销售";*/

	public GatheringManageService getGatheringManageService() {
		return gatheringManageService;
	}
	@Autowired
	public void setGatheringManageService(
			GatheringManageService gatheringManageService) {
		this.gatheringManageService = gatheringManageService;
	}
	public SellZhProjectDao getSellZhProjectDao() {
		return sellZhProjectDao;
	}
	@Autowired
	public void setSellZhProjectDao(SellZhProjectDao sellZhProjectDao) {
		this.sellZhProjectDao = sellZhProjectDao;
	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public SellAccountListDao getSellAccountListDao() {
		return sellAccountListDao;
	}
	@Autowired
	public void setSellAccountListDao(SellAccountListDao sellAccountListDao) {
		this.sellAccountListDao = sellAccountListDao;
	}
	public SellDbProjectDAO getSellDbProjectDAO() {
		return sellDbProjectDAO;
	}
	@Autowired
	public void setSellDbProjectDAO(SellDbProjectDAO sellDbProjectDAO) {
		this.sellDbProjectDAO = sellDbProjectDAO;
	}

	/**
	 * 获取结算单列表
	 */
	
	public Datagrid getSellAccount(SellAccountVo sellAccountVo)
			throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellAccountVo.getEnterprise_id()).getCiValue()));
		String edate = new java.sql.Date(new java.util.Date().getTime()).toString();
		Datagrid dg = new Datagrid();
		List<SellAccountVo> list = new ArrayList<SellAccountVo>();
		StringBuffer sql = new StringBuffer("SELECT  A.account_id,  A.xs_car_sel_id,A.account_code," +
				" A.account_type_id,A.account_type, " +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE A.account_type = k.child_id) AS TYPES," +
				"A.account_money,A.account_date,A.account_person, B.STF_NAME,A.account_state," +
				" (SELECT k.datavalue FROM xs_childdictionary k WHERE   A.account_state= k.child_id) AS state," +
				"A.remark,A.account_gyration," +
				"(SELECT k.datavalue FROM xs_childdictionary k WHERE A.account_gyration= k.child_id) AS gy " +
				"FROM xs_sell_account A,bas_stuff B WHERE A.account_person=b.STF_ID");
		//企业编号
		if(sellAccountVo.getEnterprise_id()!=null&&!sellAccountVo.getEnterprise_id().equals("")){
			sql.append(" and A.enterprise_id='"+sellAccountVo.getEnterprise_id()+"'");
					
		}
	
		if( sellAccountVo.getAccountDate()!=null && !sellAccountVo.getAccountDate().equals("")){
			
					sql.append( " and date(A.account_date) >= '"+ sellAccountVo.getAccountDate()+"'" );
		}
		if( sellAccountVo.getAccountDate2()!=null && !sellAccountVo.getAccountDate2().equals("")){
			
			sql.append( " and date(A.account_date) <= '"+ sellAccountVo.getAccountDate2()+"'" );
		}
	
		if(( sellAccountVo.getAccountDate()==null ||sellAccountVo.getAccountDate().equals(""))&&
				( sellAccountVo.getAccountDate2()==null ||sellAccountVo.getAccountDate2().equals(""))){
			sql.append( " and date(A.account_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if(sellAccountVo.getAccountCode()!=null&&!sellAccountVo.getAccountCode().equals("")){
			sql.append(" and A.account_code like '%"+StringEscapeUtils.escapeSql(sellAccountVo.getAccountCode().trim())+"%'");
					
		}
		if(sellAccountVo.getAccountTypeId()!=null&&!sellAccountVo.getAccountTypeId().equals("")){
			sql.append(" and A.account_type_id like '%"+StringEscapeUtils.escapeSql(sellAccountVo.getAccountTypeId().trim())+"%'");
					
		}
		if(sellAccountVo.getAccountType()!=null&&!sellAccountVo.getAccountType().equals("")){
			sql.append(" and A.account_type='"+sellAccountVo.getAccountType()+"'");
					
		}
		sql.append(" order by A.account_id desc");
		List<Object[]> resultList = sellAccountListDao.createSQLQuery(sql.toString(),sellAccountVo.getPage(),sellAccountVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellAccountVo sell = new SellAccountVo();
				obj = resultList.get(i);
				sell.setAccountId(obj[0] != null ?Integer.parseInt( obj[0].toString()): null);
				sell.setXs_Car_Sel_Id(obj[1] != null ?Integer.parseInt( obj[1].toString()): null);
				sell.setAccountCode(obj[2] != null ?obj[2].toString(): null);
				sell.setAccountTypeId(obj[3] != null ?obj[3].toString(): null);
				sell.setAccountType(obj[4] != null ?Integer.parseInt( obj[4].toString()): null);
				sell.setAccountTypeName(obj[5] != null ?obj[5].toString(): null);
				sell.setAccountMoney(obj[6] != null ?Double.parseDouble(obj[6].toString()): null);
				sell.setAccountDate(obj[7] != null ?obj[7].toString(): null);
				sell.setAccountPerson(obj[8] != null ?Integer.parseInt( obj[8].toString()): null);
				sell.setPerson(obj[9] != null ?obj[9].toString(): null);
				sell.setAccountState(obj[10] != null ?Integer.parseInt( obj[10].toString()): null);
				sell.setState(obj[11] != null ?obj[11].toString(): null);
				sell.setRemark(obj[12] != null ?obj[12].toString(): null);
				sell.setAccountGyration(obj[13] != null ?Integer.parseInt( obj[13].toString()): null);
				sell.setGyration(obj[14] != null ?obj[14].toString(): null);
				list.add(sell);
			}
		}
		int total=sellAccountListDao.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	
	}
	//回转;删除
	
	@Log(systemName="销售系统", moduleName="结算单管理",opertype="删除")
	public void updateRedoSum(SellAccountVo sellAccountVo) throws Exception {
		BaseBean acc = sellAccountListDao
				.get("from XsSellAccount where accountId="
						+ sellAccountVo.getAccountId());
		XsSellAccount account = (XsSellAccount) acc;
		if(sellAccountVo.getAccountCode()!=null&&!"".equals(sellAccountVo.getAccountCode())){
		XsSellFlowControl flow=xsSellFlowControlDao.get("from XsSellFlowControl where xsfcontrolDocument='"+ sellAccountVo.getAccountCode()+"'");
		xsSellFlowControlDao.delete(flow);
		}
		
		//account.setAccountGyration(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1));
		if(Integer.parseInt(sellAccountVo.getAccountType().toString())==
			Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE2,sellAccountVo.getEnterprise_id()).toString())){//代办
		
			List<Object> db=sellDbProjectDAO.find("from XsSellDbProject  sellPro where sellPro.dbProjectCode='"+sellAccountVo.getAccountTypeId()+"'");
			 for (Object xs : db) {
				 XsSellDbProject dbsell=(XsSellDbProject)xs;
				//是否转结算:否	
				 dbsell.setDbProjectReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellAccountVo.getEnterprise_id()));
				 sellDbProjectDAO.merge(dbsell);
				
			}
			//sellDbProjectDAO.modifyPro(sellAccountVo.getAccountTypeId());	
		}else if(Integer.parseInt(sellAccountVo.getAccountType().toString())==
			Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE3,sellAccountVo.getEnterprise_id()).toString())){//装潢
			List<Object> zh=sellZhProjectDao.find("from XsSellZhProject where zhProjectCode='"+sellAccountVo.getAccountTypeId()+"'");
			for (Object object : zh) {
				XsSellZhProject sell=(XsSellZhProject)object; 
				XsChilddictionary ko=null;
				Integer koning=baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellAccountVo.getEnterprise_id());
				if(koning!=null){
					ko=baseTagDAO.findById(koning);
				}
				//是否转结算:否	
				sell.setXsChilddictionaryByZhProjectReckoning(ko);
				sellZhProjectDao.merge(sell);
			}
			//sellZhProjectDao.modifyZh(sellAccountVo.getAccountTypeId());
		}else if(Integer.parseInt(sellAccountVo.getAccountType().toString())==
			Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE1,sellAccountVo.getEnterprise_id()).toString())){//保险
			List<BaseBean> ins=sellInsuranceListDao.find("from 	XsSellInsurance where insurancePolicy='"+sellAccountVo.getAccountTypeId()+"'");
			for (BaseBean baseBean : ins) {
				XsSellInsurance sell=(XsSellInsurance)baseBean; 
				//是否转结算:否	
				sell.setInvoiceReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellAccountVo.getEnterprise_id()));//否
				sellInsuranceListDao.merge(sell);
			}
			
			
			//sellInsuranceListDao.modifyInsurance(sellAccountVo.getAccountTypeId());
		}else if(Integer.parseInt(sellAccountVo.getAccountType().toString())==
			Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE4,sellAccountVo.getEnterprise_id()).toString())){//销售
			XsCarSellInfo carsel=carSellManageDao.get("from XsCarSellInfo where xsCarSelId="+sellAccountVo.getXs_Car_Sel_Id());
			//是否转结算:否	
			carsel.setInvoiceReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellAccountVo.getEnterprise_id()));
				carSellManageDao.merge(carsel);
			
			//carSellManageDao.modifySell(sellAccountVo.getXs_Car_Sel_Id());
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除了单号为【"+sellAccountVo.getAccountCode()+"】的结算单！");

		sellAccountListDao.delete(account);
	
}
	/**
	 * 转收银:更新状态
	 */
	
	
	public void updateAccount(SellAccountVo sellAccountVo) throws Exception {
		BaseBean acc = sellAccountListDao
		.get("from XsSellAccount where accountId="+ sellAccountVo.getAccountId());
		XsSellAccount account = (XsSellAccount) acc;
		account.setAccountState(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1,sellAccountVo.getEnterprise_id()));
		sellAccountListDao.merge(account);
		
	}
	
	public XsCarSellInfo getSellCar(int id) throws Exception {
		return  (XsCarSellInfo) sellAccountListDao.get("from  XsCarSellInfo sell where sell.xsCarSelId="+id);
	}
	/**转收银*/
	
	@Log(systemName="销售系统", moduleName="结算单管理",opertype="转收银")
	public Msg modifyBalance(SellAccountVo account) throws Exception {
		Msg msg = new Msg();
		try {
			XsSellAccount ac=new XsSellAccount();
			ac.setAccountId(account.getAccountId());
			ac.setAccountMoney(account.getAccountMoney());
			XsCarSellInfo car=getSellCar(account.getXs_Car_Sel_Id());
			ac.setXsCarSellInfo(car);
			ac.setAccountCode(account.getAccountCode());
			ac.setAccountType(account.getAccountType());
			ac.setAccountTypeId(account.getAccountTypeId());
			ac.setEnterpriseId(account.getEnterprise_id());
			updateAccount(account);
			gatheringManageService.addChangeRegister(ac);
			
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"收银了单号为【"+account.getAccountCode()+"】的结算单！");

			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			msg.setMsg("转收银失败！");
		}
		return msg;
	}
	
}
