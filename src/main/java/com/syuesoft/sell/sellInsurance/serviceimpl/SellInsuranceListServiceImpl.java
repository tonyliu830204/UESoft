package com.syuesoft.sell.sellInsurance.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellInsurance;
import com.syuesoft.sell.model.XsSellInsurdetail;
import com.syuesoft.sell.sellInsurance.dao.SellInsuranceListDao;
import com.syuesoft.sell.sellInsurance.service.SellInsuranceListService;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.sell.util.dao.SellAccountDao;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.SystemUser;

@Service("sellInsuranceListService")
public class SellInsuranceListServiceImpl extends BaseLogServiceImpl implements
		SellInsuranceListService {
	private SellInsuranceListDao sellInsuranceListDao;
	private BaseTagDAO baseTagDAO;
	private SellAccountDao accountDao;

	/*
	 * private static String examineState="未审核";//185 private static String
	 * examineState1="已审核";//186 private static String insuranceType="是";
	 * private static String insuranceType1="否";//215 private static String
	 * type="保险";
	 */
	public SellAccountDao getAccountDao() {
		return accountDao;
	}

	@Autowired
	public void setAccountDao(SellAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}

	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}

	public SellInsuranceListDao getSellInsuranceListDao() {
		return sellInsuranceListDao;
	}

	@Autowired
	public void setSellInsuranceListDao(
			SellInsuranceListDao sellInsuranceListDao) {
		this.sellInsuranceListDao = sellInsuranceListDao;
	}

	
	public Datagrid getSellInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {

		return sellInsuranceListDao.getSellInsurance(sellInsuranceListVo);
	}

	
	public List getInfo(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		return sellInsuranceListDao.getinfo(sellInsuranceListVo);

	}

	
	public List getInsurance(SellInsuranceListVo sellInsuranceListVo) throws Exception {
		List<SellInsuranceListVo> list = new ArrayList<SellInsuranceListVo>();
		StringBuffer sql = new StringBuffer(
				"SELECT  child.dataKey,child.dataValue"
						+ "	FROM xs_childdictionary child"
						+ "  JOIN xs_parentdictionary parent ON parent.parent_id = child.parent_id"
						+ " WHERE parent.dataKey ='"+Contstants.BASE_SELL.INSURANCETYPE+"'");
		if(sellInsuranceListVo.getEnterpriseId()!=null&& !"".equals(sellInsuranceListVo.getEnterpriseId())){
			sql.append(" and  child.enterprise_Id = '"+sellInsuranceListVo.getEnterpriseId()+"'");
		}
		List<Object[]> resultList = sellInsuranceListDao.createSQLQuery(sql
				.toString());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellInsuranceListVo sell = new SellInsuranceListVo();
				obj = resultList.get(i);
				sell
						.setInsurancetype(obj[0] != null ? obj[0].toString()
								: null);
				sell
						.setInsuranceName(obj[1] != null ? obj[1].toString()
								: null);
				list.add(sell);
			}
		}
		return list;
	}

	// 新增
	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.syuesoft.sell.sellInsurance.service.SellInsuranceListService#
	 * addSellInsurance(com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo)
	 */
	
	@Log(systemName = "销售系统", moduleName = "车辆保单管理", opertype = "新增")
	public void addSellInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		SellInsuranceListVo insurance = JSON.parseObject(sellInsuranceListVo
				.getDateGridForm(), SellInsuranceListVo.class);
		List<XsSellInsurdetail> research = JSON.parseArray(sellInsuranceListVo
				.getDetaildateGrid(), XsSellInsurdetail.class);
		XsCarSellInfo sellInfo = (XsCarSellInfo) sellInsuranceListDao
				.get("from  XsCarSellInfo sell where sell.xsCarSelId="
						+ insurance.getXs_Car_Sel_Id());
		sellInfo.setIsinsurance(baseTagDAO.getChildId(
				Contstants.BASE_SELL.PAYMENTSTATE,
				Contstants.BASE_SELL.PAYMENTSTATE1,sellInsuranceListVo.getEnterpriseId()));
		sellInsuranceListDao.merge(sellInfo);
		XsSellInsurance in = new XsSellInsurance();
		/* BeanUtils.copyProperties(insurance, in); */
		in.setBuysnessCost(insurance.getBuysnessCost());
		in.setCustomCost(insurance.getCustomCost());
		in.setCustomReturncost(insurance.getCustomReturncost());
		in.setDafeDate(insurance.getDafeDate());
		in.setDistance(insurance.getDistance());
		in.setExtract(insurance.getExtract());
		/* in.setInsuranceAgent(insurance.getInsuranceAgent()); */
		in.setInsuranceId(insurance.getInsuranceId());
		in.setInsurancePolicy(insurance.getInsurancePolicy());
		in.setInsurer(insurance.getInsurer());
		in.setInsurerEndDate(insurance.getInsurerEndDate());
		in.setInsurerStartDate(insurance.getInsurerStartDate());
		in.setNumbers(insurance.getNumbers());
		in.setPerson(insurance.getPerson());
		in.setPrimeCost(insurance.getPrimeCost());
		in.setProfit(insurance.getProfit());
		in.setqCost(insurance.getInCost());
		in.setRecordDate(insurance.getRecordDate());
		in.setSafeAmount(insurance.getSafeAmount());
		in.setSafeCost(insurance.getSafeCost());
		in.setSafeType(insurance.getSafeType());
		in.setSum(insurance.getSum());
		in.setTrafficCharge(insurance.getTrafficCharge());
		in.setVehiclevesselTax(insurance.getVehiclevesselTax());
		in.setRemark(insurance.getRemark());
		in.setInsuranceAgent(insurance.getInsuranceAgent());
		in.setXsCarSellInfo(sellInfo);
		in.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,
				Contstants.BASE_SELL.ADUIT2,sellInsuranceListVo.getEnterpriseId()));// 未审核
		in.setInvoiceReckoning(baseTagDAO.getChildId(
				Contstants.BASE_SELL.PAYMENTSTATE,
				Contstants.BASE_SELL.PAYMENTSTATE2,sellInsuranceListVo.getEnterpriseId()));
		sellInsuranceListDao.save(in);
		if (research != null && research.size() > 0) {
			for (XsSellInsurdetail xsSellInsurdetail : research) {
				xsSellInsurdetail.setInsuranceId(in.getInsuranceId());
				sellInsuranceListDao.saveOrUpdate(xsSellInsurdetail);
			}
		}
		setContent("" + SystemUser.getUser().getBasStuff().getStfName()
				+ "给销售单号【" + sellInfo.getSellCode() + "】新增了单号为【"
				+ in.getInsurancePolicy() + "】的保险单！");

	}

	// 修改
	
	@Log(systemName = "销售系统", moduleName = "车辆保单管理", opertype = "修改")
	public void modifyInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		SellInsuranceListVo insurance = JSON.parseObject(sellInsuranceListVo
				.getDateGridForm(), SellInsuranceListVo.class);
		XsCarSellInfo sellInfo = (XsCarSellInfo) sellInsuranceListDao
				.get("from  XsCarSellInfo sell where sell.xsCarSelId="
						+ insurance.getXs_Car_Sel_Id());
		/*
		 * BaseBean sell =sellInsuranceListDao.get(
		 * "from XsSellInsurance  cus where cus.insuranceId='" +
		 * insurance.getInsuranceId() + "'");
		 */
		XsSellInsurance sells = new XsSellInsurance();
		sells.setRecordDate(insurance.getRecordDate());
		sells.setNumbers(insurance.getNumbers());
		sells.setDafeDate(insurance.getDafeDate());
		sells.setInsuranceId(insurance.getInsuranceId());
		sells.setInsurancePolicy(insurance.getInsurancePolicy());
		sells.setRemark(insurance.getRemark());
		sells.setInsurerStartDate(insurance.getInsurerStartDate());
		sells.setInsurerEndDate(insurance.getInsurerEndDate());
		sells.setBuysnessCost(insurance.getBuysnessCost());
		sells.setqCost(insurance.getInCost());
		sells.setSafeCost(insurance.getSafeCost());
		sells.setSafeAmount(insurance.getSafeAmount());
		sells.setSum(insurance.getSum());
		sells.setCustomCost(insurance.getCustomCost());
		sells.setExtract(insurance.getExtract());
		sells.setCustomReturncost(insurance.getCustomReturncost());
		sells.setPerson(insurance.getPerson());
		sells.setProfit(insurance.getProfit());
		sells.setSafeType(insurance.getSafeType());
		sells.setDistance(insurance.getDistance());
		sells.setInsurer(insurance.getInsurer());
		sells.setTrafficCharge(insurance.getTrafficCharge());
		sells.setVehiclevesselTax(insurance.getVehiclevesselTax());
		sells.setPrimeCost(insurance.getPrimeCost());
		sells.setXsCarSellInfo(sellInfo);
		sells.setInsuranceAgent(insurance.getInsuranceAgent());
		sells.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,
				Contstants.BASE_SELL.ADUIT2,sellInsuranceListVo.getEnterpriseId()));// 未审核
		sells.setInvoiceReckoning(baseTagDAO.getChildId(
				Contstants.BASE_SELL.PAYMENTSTATE,
				Contstants.BASE_SELL.PAYMENTSTATE2,sellInsuranceListVo.getEnterpriseId()));

		sellInsuranceListDao.merge(sells);
		List<XsSellInsurdetail> research = JSON.parseArray(sellInsuranceListVo
				.getDetaildateGrid(), XsSellInsurdetail.class);
		if (research != null && research.size() > 0) {
			for (XsSellInsurdetail xsSellInsurdetail : research) {
				sellInsuranceListDao.saveOrUpdate(xsSellInsurdetail);
			}
		}
		setContent("" + SystemUser.getUser().getBasStuff().getStfName()
				+ "修改了单号为【" + sells.getInsurancePolicy() + "】的保险单！");

	}

	// 删除
	
	@Log(systemName = "销售系统", moduleName = "车辆保单管理", opertype = "删除")
	public void deleteInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		XsSellInsurance xsi = (XsSellInsurance) sellInsuranceListDao
				.get("from XsSellInsurance xsi where xsi.insurancePolicy='"
						+ sellInsuranceListVo.getInsurancePolicy() + "'");
		List<BaseBean> tempList = sellInsuranceListDao
				.find(" from XsSellInsurdetail xsid where xsid.insuranceId="
						+ xsi.getInsuranceId());
		for (BaseBean baseBean : tempList) {
			sellInsuranceListDao.delete(baseBean);
		}
		sellInsuranceListDao.delete(xsi);
		List<BaseBean> list = sellInsuranceListDao
				.find("from XsSellInsurance  xsi where xsi.xsCarSellInfo.xsCarSelId="
						+ sellInsuranceListVo.getXs_Car_Sel_Id());
		if (!(list != null && list.size() > 0)) {
			XsCarSellInfo sellInfo = (XsCarSellInfo) sellInsuranceListDao
					.get("from  XsCarSellInfo sell where sell.xsCarSelId="
							+ sellInsuranceListVo.getXs_Car_Sel_Id());
			sellInfo.setIsinsurance(null);
			sellInsuranceListDao.merge(sellInfo);
		}
		setContent("" + SystemUser.getUser().getBasStuff().getStfName()
				+ "删除了单号为【" + xsi.getInsurancePolicy() + "】的保险单！");
		// XsSellInsurance in=(XsSellInsurance)
		// sellInsuranceListDao.get("from XsSellInsurance where insuranceId="+sellInsuranceListVo.getInsuranceId());
		// sellInsuranceListDao.delete(in);
		// XsSellAccount account=(XsSellAccount)
		// sellInsuranceListDao.get("from  XsSellAccount account where account.accountTypeId='"+sellInsuranceListVo.getInsurancePolicy()+"'");
		// int
		// state=baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1);
		// if(account!=null&&account.getAccountState()!=state){
		// sellInsuranceListDao.delete(account);
		// }

	}

	// 审核
	@Log(systemName = "销售系统", moduleName = "车辆保单管理", opertype = "审核")
	public void updateExamine(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		XsSellInsurance allocatel = (XsSellInsurance) sellInsuranceListDao
				.get("from XsSellInsurance where insuranceId="
						+ sellInsuranceListVo.getInsuranceId());
		if(allocatel!=null){
			if (Integer.parseInt(allocatel.getExamine().toString()) == Integer
					.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,
							Contstants.BASE_SELL.ADUIT2,sellInsuranceListVo.getEnterpriseId()).toString())) {
				allocatel.setExamine(baseTagDAO.getChildId(
						Contstants.BASE_SELL.ADUIT, Contstants.BASE_SELL.ADUIT1,sellInsuranceListVo.getEnterpriseId()));
				setContent("" + SystemUser.getUser().getBasStuff().getStfName()
						+ "审核了单号为【" + allocatel.getInsurancePolicy() + "】的保险单！");
	
			} else {
				allocatel.setExamine(baseTagDAO.getChildId(
						Contstants.BASE_SELL.ADUIT, Contstants.BASE_SELL.ADUIT2,sellInsuranceListVo.getEnterpriseId()));
				setContent("" + SystemUser.getUser().getBasStuff().getStfName()
						+ "取消审核了单号为【" + allocatel.getInsurancePolicy() + "】的保险单！");
			}
		}
			sellInsuranceListDao.update(allocatel);
			
	}

	// 转结算
	
	@Log(systemName = "销售系统", moduleName = "车辆保单管理", opertype = "转结算")
	public void updateSum(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		List<BaseBean> list = sellInsuranceListDao
				.find("from XsSellInsurance where insuranceId="
						+ sellInsuranceListVo.getInsuranceId());
		double sum = 0;
		if (list != null && list.size() > 0) {
			for (BaseBean baseBean : list) {
				XsSellInsurance sellPro = (XsSellInsurance) baseBean;
				sellPro.setInvoiceReckoning(baseTagDAO.getChildId(
						Contstants.BASE_SELL.PAYMENTSTATE,
						Contstants.BASE_SELL.PAYMENTSTATE1,sellInsuranceListVo.getEnterpriseId()));
				sellInsuranceListDao.merge(sellPro);
				sum += sellPro.getSum();

			}
			XsSellAccount account = (XsSellAccount) sellInsuranceListDao
					.get(" from  XsSellAccount account where account.accountTypeId='"
							+ sellInsuranceListVo.getInsurancePolicy() + "'");
			if (account == null) {
				int types = (baseTagDAO.getChildId(
						Contstants.BASE_SELL.ACCOUNTTYPE,
						Contstants.BASE_SELL.ACCOUNTTYPE1,sellInsuranceListVo.getEnterpriseId()));
				accountDao.saveSellAccount(sellInsuranceListVo
						.getXs_Car_Sel_Id(), sellInsuranceListVo
						.getInsurancePolicy(), types, sum, sellInsuranceListVo
						.getRemark(),sellInsuranceListVo.getEnterpriseId());
			} else {
				account.setAccountMoney(sum);
				// account.setAccountGyration(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2));
				account.setRemark(sellInsuranceListVo.getRemark());
				sellInsuranceListDao.merge(account);
			}
			setContent("" + SystemUser.getUser().getBasStuff().getStfName()
					+ "结算了单号为【" + sellInsuranceListVo.getInsurancePolicy()
					+ "】的保险单！");

		}

	}

	
	public List getInsuranceDetails(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		if (sellInsuranceListVo.getCustomName() != null)
			sellInsuranceListVo.setCustomName(new String(sellInsuranceListVo
					.getCustomName().getBytes("ISO-8859-1"), "UTF-8"));
		
		return sellInsuranceListDao.getInsuranceDetails(sellInsuranceListVo);
	}
	//车辆保险模块：车辆保险父查询
	
	public Datagrid getSellInsuranceF(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		/*if (sellInsuranceListVo.getCustomName() != null)
			sellInsuranceListVo.setCustomName(new String(sellInsuranceListVo
					.getCustomName().getBytes("ISO-8859-1"), "UTF-8"));*/
		return sellInsuranceListDao.getSellInsuranceF(sellInsuranceListVo);
	}

	
	public List getCarBrand(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		// TODO Auto-generated method stub
		return sellInsuranceListDao.getCarBrand(sellInsuranceListVo);
	}

	
	public List getCarModel(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		// TODO Auto-generated method stub
		return sellInsuranceListDao.getCarModel(sellInsuranceListVo);
	}

	
	public Datagrid getCarInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		
		return sellInsuranceListDao.getCarInsurance(sellInsuranceListVo);
	}

	
	public Boolean isRefundment(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE,
				Contstants.BASE_SELL.ADUIT,sellInsuranceListVo.getEnterpriseId());
		if (sellInsuranceListVo.getExamine().equals(examine))
			return true;
		return false;
	}

	
	public Boolean isUse(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		String sql = "SELECT * FROM Xs_Sell_Account a, xs_sell_insurance B,Xs_Sell_Flow_Control c "
				+ " where a.account_type_id=b.insurance_policy and a.account_code = c.xsfcontrol_document  "
				+ " and a.account_type_id ='"
				+ sellInsuranceListVo.getInsurancePolicy() + "'";
		List list = sellInsuranceListDao.createSQLQuery(sql);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	
	public Boolean isNotRefundment(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {

		Integer examine = baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE,
				Contstants.BASE_SELL.ADUIT,sellInsuranceListVo.getEnterpriseId());
		if (sellInsuranceListVo.getExamine().equals(examine))
			return true;
		return false;
	}

	
	public List getInsuranceDel(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		if (sellInsuranceListVo.getCustomName() != null)
			sellInsuranceListVo.setCustomName(new String(sellInsuranceListVo
					.getCustomName().getBytes("ISO-8859-1"), "UTF-8"));
		return sellInsuranceListDao.getInsuranceDel(sellInsuranceListVo);
	}

	
	public DatagridAnalyze getInsuranceInfor(SellInsuranceListVo sellInsuranceListVo)
			throws Exception {
		if (sellInsuranceListVo.getCustomName() != null)
			sellInsuranceListVo.setCustomName(new String(sellInsuranceListVo
					.getCustomName().getBytes("ISO-8859-1"), "UTF-8"));
		return sellInsuranceListDao.getInsuranceInfor(sellInsuranceListVo);
	}
}
