package com.syuesoft.sell.sellwork.serviceimpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarInfoDAO;
import com.syuesoft.sell.base.dao.CustomInfoDAO;
import com.syuesoft.sell.carAllocate.dao.SellCarReserveDao;
import com.syuesoft.sell.carAllocate.service.SellCarReserveService;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.financemanage.dao.RefundmentDao;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsPdiCheck;
import com.syuesoft.sell.model.XsRepay;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.model.XsSellCover;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.model.XsSellLoan;
import com.syuesoft.sell.sellwork.dao.CarSellManageDao;
import com.syuesoft.sell.sellwork.dao.XsSellLoanDao;
import com.syuesoft.sell.sellwork.service.CarSellManageService;
import com.syuesoft.sell.sellwork.vo.CarSellManageVo;
import com.syuesoft.sell.util.dao.SellAccountDao;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;
import com.syuesoft.util.SystemUser;

@Service("carSellManageService")
public class CarSellManageServiceImpl extends BaseLogServiceImpl implements
		CarSellManageService {

	@Resource
	private CarSellManageDao carSellManageDao;
	
	@Resource
	private SellAccountDao sellAccountDao;
	
	@Resource
	private XsSellFlowControlDao xsSellFlowControlDao;
	
	@Resource
	private BaseTagDAO baseTagDAO;
	@Resource
	private XsSellLoanDao xsSellLoanDao;
	@Resource
	private CustomInfoDAO customInfoDAO;
	@Resource
	private CarInfoDAO carInfoDAO;
	@Resource
	private SellCarReserveDao sellCarReserveDao;
	@Resource
	private RefundmentDao refundmentDao;
	@Resource
	private SellCarReserveService sellService;
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	

	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	/**
	 * 生成销售单记录
	 */
	@Log(systemName="销售系统", moduleName="车辆销售管理",opertype="新增")
	
	public Boolean addSellInfor(CarSellManageVo carSellManageVo) throws Exception {
			XsCarSellInfo xsCarSellInfo = new XsCarSellInfo();
			//企业编号
			if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
				xsCarSellInfo.setEnterpriseId(carSellManageVo.getEnterpriseId());
			}
			if(carSellManageVo.getXs_Car_Sel_Data()==null||carSellManageVo.getXs_Car_Sel_Data().length()==0)
				carSellManageVo.setXs_Car_Sel_Data(new Date().toLocaleString());
			xsCarSellInfo.setXsCarSelData(carSellManageVo.getXs_Car_Sel_Data());
			if(!(carSellManageVo.getXs_Car_Sel_Transaction_Money()!=null&&carSellManageVo.getXs_Car_Sel_Transaction_Money().length()>0))
				carSellManageVo.setXs_Car_Sel_Transaction_Money("0");
			xsCarSellInfo.setXsCarSelTransactionMoney(Double.parseDouble(carSellManageVo.getXs_Car_Sel_Transaction_Money()));
			if(!(carSellManageVo.getXs_Car_Stf_Id()!=null&&carSellManageVo.getXs_Car_Stf_Id().length()>0))
				return null;
			xsCarSellInfo.setXsCarStfId(Integer.parseInt(carSellManageVo.getXs_Car_Stf_Id()));
			xsCarSellInfo.setAudit(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,carSellManageVo.getEnterpriseId()));
			int  carid = -1;
			if(carSellManageVo.getXs_Car_Id()!=null && !carSellManageVo.getXs_Car_Id().equals("")){
			xsCarSellInfo.setXsCarId(Integer.parseInt(carSellManageVo.getXs_Car_Id()));
			carid = Integer.parseInt(carSellManageVo.getXs_Car_Id());
			}
			if(!(carSellManageVo.getXs_Custom_Id()!=null && !carSellManageVo.getXs_Custom_Id().equals(""))){
				return null;
			}
			xsCarSellInfo.setCustomId(Integer.parseInt(carSellManageVo.getXs_Custom_Id()));
			xsCarSellInfo.setInvoiceReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE, Contstants.BASE_SELL.PAYMENTSTATE2,carSellManageVo.getEnterpriseId()));
			int reserveId = 0;
			if(carSellManageVo.getReserve_Code()!=null && !carSellManageVo.getReserve_Code().equals("")){
				XsSellCarReserve xsSellCarReserve = new XsSellCarReserve();
				reserveId = Integer.parseInt(((Object)sellCarReserveDao
								.get("select reserveId from XsSellCarReserve where reserveCode='"+carSellManageVo.getReserve_Code()+"'"))
								.toString());
				xsSellCarReserve.setReserveId(reserveId);
				xsCarSellInfo.setXsSellCarReserve(xsSellCarReserve);
			}
			String sellcode = CreateID.createId("XsCarSellInfo", Contstants.SELL_BILLSDEPLOY.SELLCODE);
			xsCarSellInfo.setSellCode(sellcode);
			if(!(carSellManageVo.getApply_Sum()!=null&&carSellManageVo.getApply_Sum().length()>0))
				carSellManageVo.setApply_Sum("0");
			xsCarSellInfo.setApplySum( Double.parseDouble(carSellManageVo.getApply_Sum()));
			if(!(carSellManageVo.getCost_Sum()!=null&&carSellManageVo.getCost_Sum().length()>0))
				carSellManageVo.setCost_Sum("0");
			xsCarSellInfo.setCostSum( Double.parseDouble(carSellManageVo.getCost_Sum()));
			xsCarSellInfo.setDbProjectRemark(carSellManageVo.getRemark());
			xsCarSellInfo.setXsCustomReceiptor(carSellManageVo.getXs_Custom_Receiptor());
			xsCarSellInfo.setZhProjecRemark(carSellManageVo.getZhProjecRemark());
			if(!(carSellManageVo.getXs_Car_Sel_Type()!=null&&carSellManageVo.getXs_Car_Sel_Type().length()>0))
				return null;
			xsCarSellInfo.setXsCarSelType(Integer.parseInt(carSellManageVo.getXs_Car_Sel_Type()));
			xsCarSellInfo.setXsCarSelRemark(carSellManageVo.getXs_Car_Sel_Remark());
			xsCarSellInfo.setOutId(0);
			if(carid==-1){
				return null;
			}
	//		List list = carSellManageDao.createSQLQuery("SELECT xcsi.sell_code FROM xs_car_sell_info xcsi INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_document=xcsi.sell_code WHERE xcsi.xs_car_id="+carid);
	//		if(list!=null && list.size()>0){
	//			return false;
	//		}
			XsCustomInfo xci = customInfoDAO.get("from XsCustomInfo where customId="+carSellManageVo.getXs_Custom_Id());
			xci.setXsCustomDeal(baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.AS,carSellManageVo.getEnterpriseId()));
			customInfoDAO.merge(xci);
			carSellManageDao.save(xsCarSellInfo);
			//销售单保存成功后将车辆状态改为销售状态
			XsCarInfo xsCarInfo =(XsCarInfo) carInfoDAO.get("from XsCarInfo where carId="+carid+"");
			java.util.Date date = new java.util.Date();
			Calendar conld = Calendar.getInstance();
			conld.setTime(date);
			conld.add(Calendar.DAY_OF_MONTH, 90);
			date = conld.getTime();
			xsCarInfo.setCarFristInsuranceData(new Timestamp(date.getTime()));
			xsCarInfo.setSellStateChild(baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE, Contstants.SELLSTATE.NOTSOLD,carSellManageVo.getEnterpriseId()));
			carInfoDAO.merge(xsCarInfo);
			//销售单保存成功后将订单状态改为已交车状态
			if(carSellManageVo.getReserve_Code()!=null && !carSellManageVo.getReserve_Code().equals("")){
				XsSellCarReserve xsSellCarReserves = (XsSellCarReserve)sellCarReserveDao.get("from XsSellCarReserve where reserveId="+reserveId+"");
				xsSellCarReserves.setOrderState(baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.YIJIAOCHE,carSellManageVo.getEnterpriseId()));
			}
			carInfoDAO.merge(xsCarInfo);
			//添加流程控制记录
			XsSellFlowControl xsSellFlowControl = new XsSellFlowControl();
			xsSellFlowControl.setXsfcontrolCarId(carid);
			xsSellFlowControl.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE6);
			xsSellFlowControl.setXsfcontrolDocument(sellcode);
			xsSellFlowControlDao.save(xsSellFlowControl);
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"新增编号为["+sellcode+"]的销售单！");
			if((carSellManageVo.getLoan_Bank()!=null&&carSellManageVo.getLoan_Bank().length()>0)){
				XsSellLoan xsl=new XsSellLoan();
//			if(!(carSellManageVo.getBalance_()!=null&&carSellManageVo.getBalance_().length()>0))
//				return null;
				xsl.setBalance(Double.parseDouble(carSellManageVo.getBalance_()));
//			if(!(carSellManageVo.getLoan_Bank()!=null&&carSellManageVo.getLoan_Bank().length()>0))
//				return null;
				xsl.setLoanBank(Integer.parseInt(carSellManageVo.getLoan_Bank()));
//			if(!(carSellManageVo.getYear_()!=null&&carSellManageVo.getYear_().length()>0))
//				return null;
				xsl.setYear(Float.parseFloat(carSellManageVo.getYear_()));
//			if(!(carSellManageVo.getFirst_Payment()!=null&&carSellManageVo.getFirst_Payment().length()>0))
//				return null;
				xsl.setFirstPayment(Double.parseDouble(carSellManageVo.getFirst_Payment()));
				xsl.setXsCarSellInfo(xsCarSellInfo);				
				xsSellLoanDao.save(xsl);
			}
			return true;
		
	}

	/**
	 * 选择预订单号，并通过预定单号 获取 车辆 客户 财务 信息
	 */
	
	public Json getInforById(CarSellManageVo carSellManageVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT aa.reserve_id,aa.reserve_code,cc.xs_car_id,cc.xs_car_vin_number, cc.xs_car_motor_number,");
		sb.append(" (SELECT datavalue FROM xs_childdictionary g WHERE ff.xs_car_brand = g.child_id) AS xs_car_brand,");
		sb.append(" ff.xs_model_name,cc.xs_car_ocn,cc.xs_car_color,aa.car_trim,bb.xs_custom_name,"); 
		sb.append(" bb.xs_custom_phone,"); 
		sb.append(" (SELECT xll.account_CUMULATIVE FROM xs_sell_collections xll WHERE xll.account_id=aa.reserve_code ) AS payment_money,");		
		sb.append(" bb.custom_id,aa.STF_ID,cc.xs_car_licenseName,"); 
		sb.append(" cc.xs_car_licensePlate,bb.xs_custom_zipcode,bb.xs_custom_telephone,");
		sb.append(" bb.xs_custom_codeCard,bb.xs_custom_hide_level,bb.xs_custom_source,bb.xs_custom_credentials,"); 
		sb.append(" bb.xs_custom_property,bb.xs_custom_address,bb.xs_contacts_person,");
		sb.append(" (ff.xs_model_costPrice+(CASE WHEN dd.costprice!='' THEN dd.costprice ELSE 0 END)) AS xs_model_costPrice,");
		sb.append(" ff.xs_model_salesPrice,");
		sb.append(" ff.xs_model_salesLimitPrice,ff.xs_model_id ,");
		sb.append(" (aa.sellingprice+(CASE WHEN dd.sellprice!='' THEN dd.sellprice ELSE 0 END)) AS sellingprice");
		sb.append(" FROM xs_sell_car_reserve aa"); 
		sb.append(" INNER JOIN xs_custom_info bb ON aa.custom_id = bb.custom_id"); 
		sb.append(" INNER JOIN xs_car_info cc ON aa.xs_car_id = cc.xs_car_id"); 
		sb.append(" INNER JOIN xs_car_model ff ON cc.xs_car_model_id = ff.xs_model_id"); 
		sb.append(" LEFT OUTER JOIN ( SELECT adds.xs_car_id,adds.costprice,(adds.sellprice1+adds.sellprice2) AS sellprice"); 
		sb.append(" FROM ( SELECT xsc.xs_car_id,(SELECT SUM(parts.paets_case_money) FROM xs_sell_carInstallOut parts WHERE parts.Install_id=xsc.Install_id) AS costprice,");	    
		sb.append(" (SELECT SUM(parts.partsAmount) FROM xs_sell_carInstallOut parts WHERE parts.Install_id=xsc.Install_id) AS sellprice1,");	    
		sb.append(" (SELECT SUM(item.ITEM_MONEY )FROM xs_sell_carInstallProjectOut item WHERE item.Install_id=xsc.Install_id) AS sellprice2");	    
		sb.append(" FROM xs_sell_carInstall xsc ");	    
		sb.append(" ) adds ) dd ON dd.xs_car_id=cc.xs_car_id");	    
		sb.append(" WHERE aa.order_state="+baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.YIJIEZHUAN,carSellManageVo.getEnterpriseId()));
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sb.append(" AND aa.Enterprise_Id="+carSellManageVo.getEnterpriseId()+"");
		}
		List rlist = carSellManageDao.createSQLQuery(sb.toString(),carSellManageVo.getPage(),carSellManageVo.getRows());
		List list = new ArrayList();
		Object[] obj = null;
			if(rlist!=null && rlist.size()>0){
				for (int i = 0; i < rlist.size(); i++) {
					obj = (Object[])rlist.get(i);
					CarSellManageVo vo = new CarSellManageVo();
					if(obj[0]!=null&&obj[0].toString().length()>0){
						vo.setReserve_Id(obj[0].toString());
					}
					if(obj[1]!=null&&obj[1].toString().length()>0){
						vo.setReserve_Code(obj[1].toString());
					}
					if(obj[2]!=null&&obj[2].toString().length()>0){
						vo.setXs_Car_Id(obj[2].toString());
					}
					if(obj[3]!=null&&obj[3].toString().length()>0){
						vo.setXs_Car_Vin_Number(obj[3].toString());
					}
					if(obj[4]!=null&&obj[4].toString().length()>0){
						vo.setXs_Car_Motor_Number(obj[4].toString());
					}
					if(obj[5]!=null&&obj[5].toString().length()>0){
						vo.setCar_Brand_Id(obj[5].toString());
					}
					if(obj[6]!=null&&obj[6].toString().length()>0){
						vo.setXs_Model_Name(obj[6].toString());
					}
					if(obj[7]!=null&&obj[7].toString().length()>0){
						vo.setXs_Car_Ocn(obj[7].toString());
					}
					if(obj[8]!=null&&obj[8].toString().length()>0){
						vo.setCar_Color(obj[8].toString());
					}
					if(obj[9]!=null&&obj[9].toString().length()>0){
						vo.setXs_Car_InteriorColor(obj[9].toString());
					}
					if(obj[10]!=null&&obj[10].toString().length()>0){
						vo.setXs_Custom_Name(obj[10].toString());
					}
					if(obj[11]!=null&&obj[11].toString().length()>0){
						vo.setXs_Custom_Phone(obj[11].toString());
					}
					if(obj[12]!=null&&obj[12].toString().length()>0){
						vo.setPayment_Money(obj[12].toString());
					}
					if(obj[13]!=null&&obj[13].toString().length()>0){
						vo.setXs_Custom_Id(obj[13].toString());
					}
					if(obj[14]!=null&&obj[14].toString().length()>0){
						vo.setStf_Id(obj[14].toString());
					}
					if(obj[15]!=null&&obj[15].toString().length()>0){
						vo.setXs_Car_LicenseName(obj[15].toString());
					}
					if(obj[16]!=null&&obj[16].toString().length()>0){
						vo.setXs_Car_LicensePlate(obj[16].toString());
					}
					if(obj[17]!=null&&obj[17].toString().length()>0){
						vo.setXs_Custom_Zipcode(obj[17].toString());
					}
					if(obj[18]!=null&&obj[18].toString().length()>0){
						vo.setXs_Custom_Telephone(obj[18].toString());
					}
					if(obj[19]!=null&&obj[19].toString().length()>0){
						vo.setXs_Custom_Code_Card(obj[19].toString());
					}
					if(obj[20]!=null&&obj[20].toString().length()>0){
						vo.setXs_Custom_Hide_Level(obj[20].toString());
					}
					if(obj[21]!=null&&obj[21].toString().length()>0){
						vo.setXs_Custom_Source(obj[21].toString());
					}
					if(obj[22]!=null&&obj[22].toString().length()>0){
						vo.setXs_Custom_Credentials(obj[22].toString());
					}
					if(obj[23]!=null&&obj[23].toString().length()>0){
						vo.setXs_Custom_Property(obj[23].toString());
					}
					if(obj[24]!=null&&obj[24].toString().length()>0){
						vo.setXs_Custom_Address(obj[24].toString());
					}
					if(obj[25]!=null&&obj[25].toString().length()>0){
						vo.setXs_Contacts_Person(obj[25].toString());
					}
					if(obj[26]!=null&&obj[26].toString().length()>0){
						vo.setXs_Model_CostPrice(obj[26].toString());
					}
					if(obj[27]!=null&&obj[27].toString().length()>0){
						vo.setXs_Model_SalesPrice(obj[27].toString());
					}
					if(obj[28]!=null&&obj[28].toString().length()>0){
						vo.setXs_Model_SalesLimitPrice(obj[28].toString());
					}
					if(obj[29]!=null&&obj[29].toString().length()>0){
						vo.setXs_Car_Model_Id(obj[29].toString());
					}
					/*if(obj[30]!=null&&obj[30].toString().length()>0){
						vo.setCarMotor_Number(obj[30].toString());
					}*/
					if(obj[30]!=null&&obj[30].toString().length()>0){
						vo.setXs_Car_Sel_Transaction_Money(obj[30].toString());
					}
					list.add(vo);
				}
			}
			int total=carSellManageDao.getSQLCount(sb.toString(), null);
			Json json = new Json();
			json.setRows(list);
			json.setTotal(total);
		return json;
		
	}

	/**
	 * 获取预定单号
	 */
	
	public List getReserveCode(CarSellManageVo carSellManageVo) throws Exception {
		List list = new ArrayList();
		List rlist = carSellManageDao.getReserveCode(carSellManageVo);
		Object[] obj = null;
		for (int i = 0; i < rlist.size(); i++) {
			ComboxVo c = new ComboxVo();
			if(rlist.get(i)!=null && !rlist.get(i).equals("")){
				obj = (Object[])rlist.get(i);
				c.setId(obj[1].toString());
				c.setName(obj[1].toString());
			}
			list.add(c);
		}
		return list;
	}

	/**
	 *  查询销售单汇总信息
	 */
	
	public Json findSellInfor(CarSellManageVo carSellManageVo) throws Exception {
		Json json=new Json();
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,carSellManageVo.getEnterpriseId()).getCiValue()));
		StringBuffer sb=new StringBuffer("SELECT * FROM ( SELECT a.xs_car_sel_id,xsr.retreat_date,a.xs_car_sel_data,");
		sb.append(" d.xs_custom_name,e.STF_NAME,f.xs_car_vin_number, f.xs_car_ocn,");
		sb.append(" (SELECT k.datavalue FROM xs_childdictionary k WHERE f.xs_car_brand = k.child_id) AS xs_car_brand_name,");
		sb.append(" g.xs_model_name, a.xs_car_sel_transaction_money,bsf.STF_NAME AS temp1,");
		sb.append(" stuff.stf_Name AS temp2,");
		sb.append(" (SELECT k.datavalue FROM xs_childdictionary k WHERE a.audit = k.child_id) AS auditName,");
		sb.append(" b.limit_load_num, d.xs_custom_telephone,");
		sb.append(" a.out_id,b.reserve_code,a.xs_car_give_up,");
		sb.append(" a.xs_car_id,a.sell_code,a.xs_car_sel_type,");
		sb.append(" (SELECT k.datavalue FROM xs_childdictionary k WHERE a.xs_car_sel_type= k.child_id) AS sellClass,");
		sb.append(" a.xs_car_sel_remark,f.xs_car_brand,");
		sb.append(" (SELECT k.datavalue FROM xs_childdictionary k WHERE a.invoice_reckoning = k.child_id) AS balance");
		sb.append(" ,a.xs_car_stf_id,a.enterprise_id,a.audit FROM xs_car_sell_info a " +
				"INNER JOIN xs_custom_info d ON a.custom_id = d.custom_id");
		sb.append(" INNER JOIN xs_car_info f ON a.xs_car_id = f.xs_car_id");
		sb.append(" LEFT OUTER JOIN xs_sell_car_reserve b ON a.reserve_id = b.reserve_id");
		sb.append(" INNER JOIN bas_stuff e ON d.STF_ID = e.STF_ID");
		sb.append(" INNER JOIN bas_stuff bsf ON bsf.STF_ID=a.xs_car_stf_id");
		sb.append(" LEFT OUTER JOIN bas_stuff stuff ON stuff.STF_ID=a.audit_person");
		sb.append(" INNER JOIN xs_car_model g ON f.xs_car_model_id = g.xs_model_id");
		sb.append(" INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_document=a.sell_code");
		
		if(carSellManageVo.getIsCarOut()!=null&&carSellManageVo.getIsCarOut()==true)
			sb.append(" INNER JOIN xs_sell_retreat xsr ON a.out_id = xsr.retreat_id and xsr.examine="+baseTagDAO.getChildId(Contstants.ADUIT.ADUIT,Contstants.ADUIT.YISHENHE,carSellManageVo.getEnterpriseId())+" AND f.xs_car_sell_state="+
						baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.SELLOUT,carSellManageVo.getEnterpriseId()));
		else
			sb.append(" LEFT JOIN xs_sell_retreat xsr ON a.out_id = xsr.retreat_id");
		sb.append(" )AS INFOS   ");
		sb.append(" WHERE 1 = 1 ");
		if(carSellManageVo.getEnterpriseId()!=null&&!"".equals(carSellManageVo.getEnterpriseId())){
			sb.append(" and infos.enterprise_id ="+carSellManageVo.getEnterpriseId());
		}
		if(carSellManageVo.getXs_Car_Sel_Data()!=null && !carSellManageVo.getXs_Car_Sel_Data().trim().equals("")){
				sb.append(" and DATE(infos.Xs_Car_Sel_Data) >= '"+carSellManageVo.getXs_Car_Sel_Data()+"'");
		}
		if(carSellManageVo.getXs_Car_Sel_Data2()!=null && !carSellManageVo.getXs_Car_Sel_Data2().trim().equals("")){
				sb.append( " and DATE(infos.Xs_Car_Sel_Data) <= '"+carSellManageVo.getXs_Car_Sel_Data2()+"'");
		}
		if((carSellManageVo.getXs_Car_Sel_Data() == null
				|| carSellManageVo.getXs_Car_Sel_Data().equals("")) 
				&& (carSellManageVo.getXs_Car_Sel_Data2() == null
				|| carSellManageVo.getXs_Car_Sel_Data2().equals(""))){
			sb.append(" and DATE(infos.Xs_Car_Sel_Data) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if(carSellManageVo.getXs_Custom_Name()!=null && !carSellManageVo.getXs_Custom_Name().equals("")){
			sb.append(" and infos.Xs_Custom_Name like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Custom_Name().trim())+"%'");
		}
		if(carSellManageVo.getStf_Name()!=null && !carSellManageVo.getStf_Name().equals("")){
			sb.append(" and infos.xs_car_stf_id ="+carSellManageVo.getStf_Name()+"");
		}
		if(carSellManageVo.getExamine()!=null && !carSellManageVo.getExamine().equals("")){
			sb.append(" and infos.audit ="+carSellManageVo.getExamine()+" ");
		}
		if(carSellManageVo.getXs_Car_Vin_Number()!=null && !carSellManageVo.getXs_Car_Vin_Number().equals("")){
			sb.append(" and infos.Xs_Car_Vin_Number like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Car_Vin_Number().trim())+"%'");
		}
		if(carSellManageVo.getXs_Car_Ocn()!=null && !carSellManageVo.getXs_Car_Ocn().equals("")){
			sb.append(" and infos.Xs_Car_Ocn like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Car_Ocn().trim())+"%'");
		}
		if(carSellManageVo.getRemark()!=null && !carSellManageVo.getRemark().equals("")){
			sb.append(" and infos.xs_car_sel_remark like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getRemark().trim())+"%'");
		}
		if(carSellManageVo.getXs_Car_Sel_Id()!=null && !carSellManageVo.getXs_Car_Sel_Id().equals("")){
			sb.append(" and infos.xs_car_sel_id= '"+carSellManageVo.getXs_Car_Sel_Id()+"'");
		}
		if(carSellManageVo.getSell_Code()!=null && !carSellManageVo.getSell_Code().equals("")){
			sb.append(" and infos.sell_code= '"+carSellManageVo.getSell_Code()+"'");
		}
		sb.append(" ORDER BY INFOS.xs_car_sel_id DESC ");
		List list = new ArrayList();
		List<Object[]> rlist = carSellManageDao.createSQLQuery(sb.toString(), carSellManageVo.getPage(), carSellManageVo.getRows());
		
		if(rlist!=null && rlist.size()>0)
			for (Object[] obj : rlist) {
				CarSellManageVo vo = new CarSellManageVo();
				if(obj[0]!=null&&obj[0].toString().length()>0){
					vo.setXs_Car_Sel_Id(obj[0].toString());
				}
				if(obj[1]!=null&&obj[1].toString().length()>0){
					vo.setInstorehouse_Date(obj[1].toString());
				}
				if(obj[2]!=null&&obj[2].toString().length()>0){
					vo.setXs_Car_Sel_Data(obj[2].toString());
				}
				if(obj[3]!=null&&obj[3].toString().length()>0){
					vo.setXs_Custom_Name(obj[3].toString());
				}
				if(obj[4]!=null&&obj[4].toString().length()>0){
					vo.setStf_Name(obj[4].toString());
				}
				if(obj[5]!=null&&obj[5].toString().length()>0){
					vo.setXs_Car_Vin_Number(obj[5].toString());
				}
				if(obj[6]!=null&&obj[6].toString().length()>0){
					vo.setXs_Car_Ocn(obj[6].toString());
				}
				if(obj[7]!=null&&obj[7].toString().length()>0){
					vo.setXs_Car_Brand_Name(obj[7].toString());
				}
				if(obj[8]!=null&&obj[8].toString().length()>0){
					vo.setXs_Model_Name(obj[8].toString());
				}
				if(obj[9]!=null&&obj[9].toString().length()>0){
					vo.setXs_Car_Sel_Transaction_Money(obj[9].toString());
				}
				if(obj[10]!=null&&obj[10].toString().length()>0){
					vo.setXs_Car_Stf_Name(obj[10].toString());
				}
				if(obj[11]!=null&&obj[11].toString().length()>0){
					vo.setExaminePerson(obj[11].toString());
				}
				if(obj[12]!=null&&obj[12].toString().length()>0){
					vo.setExamine(obj[12].toString());
				}
				if(obj[13]!=null&&obj[13].toString().length()>0){
					vo.setLimit_load_num(obj[13].toString());
				}
				if(obj[14]!=null&&obj[14].toString().length()>0){
					vo.setMobilephone(obj[14].toString());
				}
				//出库单 用作判断是否出库
				if(obj[15]!=null&&obj[15].toString().length()>0){
					vo.setOut_Id(obj[15].toString());
				}
				if(obj[16]!=null&&obj[16].toString().length()>0){
					vo.setReserve_Code(obj[16].toString());
				}
				if(obj[17]!=null&&obj[17].toString().length()>0){
					vo.setXs_Car_Give_Up(obj[17].toString());
				}
				if(obj[18]!=null&&obj[18].toString().length()>0){
					vo.setXs_Car_Id(obj[18].toString());
				}
				if(obj[19]!=null&&obj[19].toString().length()>0){
					vo.setSell_Code(obj[19].toString());
				}
				if(obj[20]!=null&&obj[20].toString().length()>0){
					vo.setXs_Car_Sel_Type(obj[20].toString());
				}
				if(obj[21]!=null&&obj[21].toString().length()>0){
					vo.setXs_Car_Sel_TypeName(obj[21].toString());
				}
				if(obj[22]!=null&&obj[22].toString().length()>0){
					vo.setXs_Car_Sel_Remark(obj[22].toString());
				}
				if(obj[23]!=null&&obj[23].toString().length()>0){
					vo.setCar_Brand_Id(obj[23].toString());
				}
				if(obj[24]!=null&&obj[24].toString().length()>0){
					vo.setInvoice_reckoning(obj[24].toString());
				}
				if(obj[25]!=null&&obj[25].toString().length()>0){
					vo.setXs_Car_Stf_Id(obj[25].toString());
				}
				list.add(vo);
			}
		json.setRows(list);
		json.setTotal(carSellManageDao.getSQLCount(sb.toString(), null));
		return json;
	}

	/**
	 * 删除车辆销售信息
	 */
	@Log(systemName="销售系统", moduleName="车辆销售管理",opertype="删除")
	
	public boolean deleteSellInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		
		XsCarSellInfo xsCarSellInfo = (XsCarSellInfo)carSellManageDao.get("from XsCarSellInfo where xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id()+"");
		if(xsCarSellInfo.getIsdbProject()!=null)
			return false;
		if(xsCarSellInfo.getIsinsurance()!=null)
			return false;
		if(xsCarSellInfo.getIszhProject()!=null)
			return false;
		int carState=baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,carSellManageVo.getEnterpriseId());
		int customState=baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SS,carSellManageVo.getEnterpriseId());
		if(xsCarSellInfo.getXsSellCarReserve()!=null){
			carState=baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.RESERVE,carSellManageVo.getEnterpriseId());
			customState=baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SWA,carSellManageVo.getEnterpriseId());
		}			
		xsSellFlowControlDao.executeSql("update xs_car_info set xs_car_sell_state="+carState+" where xs_car_id="+xsCarSellInfo.getXsCarId());
		xsSellFlowControlDao.executeSql("update xs_custom_info set xs_custom_deal="+customState+" where custom_id="+xsCarSellInfo.getCustomId());
		if(xsCarSellInfo.getXsSellCarReserve()!=null)
			xsCarSellInfo.getXsSellCarReserve().setOrderState(baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.YIJIEZHUAN,carSellManageVo.getEnterpriseId()));
		carSellManageDao.merge(xsCarSellInfo);
		//删除流程控制表中的里程记录
		List<XsSellFlowControl> xsSellFlowControllist = xsSellFlowControlDao.find("from XsSellFlowControl BB where BB.xsfcontrolDocument='"+carSellManageVo.getSell_Code()+"'");
		for (XsSellFlowControl xsSellFlowControl2 : xsSellFlowControllist) {
			xsSellFlowControlDao.delete(xsSellFlowControl2);
		}
		setContent("删除编号为【"+carSellManageVo.getXs_Car_Sel_Id()+"】的销售单！");
		return true;
	}

	/**
	 * 修改车辆销售信息
	 */
	@Log(systemName="销售系统", moduleName="车辆销售管理",opertype="修改")
	
	public Boolean updateSellInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		XsCarSellInfo xsCarSellInfo = (XsCarSellInfo)carSellManageDao.get(" from XsCarSellInfo A WHERE A.xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id()+"");
		xsCarSellInfo.setXsCarSelData(carSellManageVo.getXs_Car_Sel_Data());
		if(!(carSellManageVo.getXs_Car_Sel_Type()!=null&&carSellManageVo.getXs_Car_Sel_Type().length()>0))
			return false;
		xsCarSellInfo.setXsCarSelType(Integer.parseInt(carSellManageVo.getXs_Car_Sel_Type()));
		if(!(carSellManageVo.getXs_Car_Sel_Transaction_Money()!=null&&carSellManageVo.getXs_Car_Sel_Transaction_Money().length()>0))
			carSellManageVo.setXs_Car_Sel_Transaction_Money("0");
		xsCarSellInfo.setXsCarSelTransactionMoney(Double.parseDouble(carSellManageVo.getXs_Car_Sel_Transaction_Money()));
		xsCarSellInfo.setXsCarSelRemark(carSellManageVo.getXs_Car_Sel_Remark());
		if(!(carSellManageVo.getXs_Car_Stf_Id()!=null&&carSellManageVo.getXs_Car_Stf_Id().length()>0))
			return false;
		xsCarSellInfo.setXsCarStfId(Integer.parseInt(carSellManageVo.getXs_Car_Stf_Id()));
		xsCarSellInfo.setInvoiceReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE, Contstants.BASE_SELL.PAYMENTSTATE2,carSellManageVo.getEnterpriseId()));
		XsSellLoan xsl=xsSellLoanDao.get("from XsSellLoan xsl where xsl.xsCarSellInfo.xsCarSelId="+xsCarSellInfo.getXsCarSelId());
		if(carSellManageVo.getLoan_Bank()!=null&&carSellManageVo.getLoan_Bank().length()>0){
			if(xsl==null)
				xsl=new XsSellLoan();
//			if(!(carSellManageVo.getBalance_()!=null&&carSellManageVo.getBalance_().toString().length()>0))
//				return false;
			xsl.setBalance(Double.parseDouble(carSellManageVo.getBalance_()));
			xsl.setLoanBank(Integer.parseInt(carSellManageVo.getLoan_Bank()));
//			if(!(carSellManageVo.getYear_()!=null&&carSellManageVo.getYear_().length()>0))
//				return false;
			xsl.setYear(Float.parseFloat(carSellManageVo.getYear_()));
//			if(!(carSellManageVo.getFirst_Payment()!=null&&carSellManageVo.getFirst_Payment().length()>0))
//				return false;
			xsl.setFirstPayment(Double.parseDouble(carSellManageVo.getFirst_Payment()));
			xsl.setXsCarSellInfo(xsCarSellInfo);
		}else{
			if(xsl!=null)
				xsSellLoanDao.delete(xsl);
		}
		carSellManageDao.merge(xsCarSellInfo);
		if(carSellManageVo.getLoan_Bank()!=null&&carSellManageVo.getLoan_Bank().length()>0){
			xsSellLoanDao.merge(xsl);
		}
		setContent("修改编号为【"+carSellManageVo.getXs_Car_Sel_Id()+"】的销售单！");
		return true;
	}

	/**
	 * 销售单审核
	 */
	@Log(systemName="销售系统", moduleName="车辆销售管理",opertype="审核")
	
	public Message doAuditSellInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		//关联流程控制表 查询结算单中是否有该车辆的结算记录  
		String sql = "SELECT * FROM Xs_Sell_Account a INNER JOIN Xs_Sell_Flow_Control b ON a.account_code = b.xsfcontrol_document" +
				" INNER JOIN xs_car_sell_info xcsi ON xcsi.xs_car_sel_id=a.xs_car_sel_id"+
				" WHERE b.xsfcontrol_car_id ="+
				carSellManageVo.getXs_Car_Id()+" and  xcsi.xs_car_sel_id="+carSellManageVo.getXs_Car_Sel_Id()+
				" AND a.account_type="+baseTagDAO.getChildId(Contstants.SELLAccount.ACCOUNTTYPE, Contstants.SELLAccount.ACCOUNTTYPE4,carSellManageVo.getEnterpriseId());
		List list = carSellManageDao.createSQLQuery(sql);
		Message msg = new Message();
		if(list!=null && list.size()>0){
			msg.setMsg("该销售单已转结算不可反审核！");
			msg.setSuccess(false);
		}else{
			
			if(carSellManageVo.getExamine()!=null && carSellManageVo.getExamine().equals("未审核")){
				setContent("审核编号为【"+carSellManageVo.getXs_Car_Sel_Id()+"】的销售单！");
			}else{
				setContent("取消审核编号为【"+carSellManageVo.getXs_Car_Sel_Id()+"】的销售单！");
			}
			
			XsCarSellInfo XsCarSellInfo = (XsCarSellInfo)carSellManageDao.get("from XsCarSellInfo where xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id()+"");
			int examine = baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.WEISHENHE,carSellManageVo.getEnterpriseId());
			if(carSellManageVo.getExamine()!=null && carSellManageVo.getExamine().equals("未审核")){
				examine = baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.YISHENHE,carSellManageVo.getEnterpriseId());
			}
			XsCarSellInfo.setAudit(examine);
			XsCarSellInfo.setAuditPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
			XsCarSellInfo.setAuditDate(new java.sql.Date(new java.util.Date().getTime())+"");
			carSellManageDao.merge(XsCarSellInfo);
			msg.setSuccess(true);
		}
		return msg;
		
	}

	/**
	 * 获取PDI检测内容
	 */
	
	public List getPdiCheck(CarSellManageVo carSellManageVo) throws Exception {
		List list = new ArrayList();
		List rlist = carSellManageDao.getPdiCheck(carSellManageVo);
		Object[] obj = null;
		if(rlist!=null && rlist.size()>0){
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				CarSellManageVo vo = new CarSellManageVo();
				if(obj[0]!=null){vo.setChild_Id(obj[0].toString());}
				if(obj[1]!=null){vo.setCheck_Comtent(obj[1].toString());}
				if(obj[2]!=null){vo.setStatus(obj[2].toString());}
				if(obj[3]!=null){vo.setRemark(obj[3].toString());}
				if(obj[4]!=null){vo.setPid(obj[4].toString());}
				if(obj[5]!=null){vo.setStatus(obj[5].toString());}
				if(obj[6]!=null){vo.setStatus_Name(obj[6].toString());}
				list.add(vo);
			}
		}else{
			List rlist2 = carSellManageDao.getPdiCheck2(carSellManageVo);
			for (int i = 0; i < rlist2.size(); i++) {
				obj = (Object[])rlist2.get(i);
				CarSellManageVo vo = new CarSellManageVo();
				if(obj[0]!=null){vo.setChild_Id(obj[0].toString());}
				if(obj[1]!=null){vo.setCheck_Comtent(obj[1].toString());}
				list.add(vo);
			}
		}
		return list;
	}

	/**
	 * 保存pdi检测信息
	 */
	
	public void savePDI(CarSellManageVo carSellManageVo) throws Exception {
		List<CarSellManageVo> rlist = JSON.parseArray(carSellManageVo.getUpdated(),CarSellManageVo.class);
		if(rlist!=null){
			for (CarSellManageVo vo : rlist) {
				XsPdiCheck p = new XsPdiCheck();
				if(vo.getPid()!=null&&vo.getPid().length()>0)
					p.setId(Integer.parseInt(vo.getPid()));
				p.setCheckComtent(vo.getChild_Id()!=null && !vo.getChild_Id().equals("") ? Integer.parseInt(vo.getChild_Id()) : null);
				p.setCheckDate(new java.sql.Date(new java.util.Date().getTime()));
				p.setCheckPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
				p.setRemark(vo.getRemark()!=null && !vo.getRemark().equals("")? vo.getRemark() : "");
				p.setStatus(vo.getStatus()!=null && !vo.getStatus().equals("") ? Integer.parseInt(vo.getStatus()) : null);
				p.setXsCarSelId(carSellManageVo.getXs_Car_Sel_Id()!=null && !carSellManageVo.getXs_Car_Sel_Id().equals("") ? Integer.parseInt(carSellManageVo.getXs_Car_Sel_Id()): null);
				p.setXsCarId(carSellManageVo.getXs_Car_Id()!=null && !carSellManageVo.getXs_Car_Id().equals("") ? Integer.parseInt(carSellManageVo.getXs_Car_Id()) : 0);
				sellCarReserveDao.saveOrUpdate(p);
			}
		}
		
	}

	/**
	 * 放弃购车
	 */
	@Log(systemName="销售系统", moduleName="车辆销售管理",opertype="放弃")
	
	public Msg doAabandon(CarSellManageVo carSellManageVo) throws Exception {
		Msg msg=new Msg();
		try {
			//carSellManageDao.doAabandon(carSellManageVo);
			XsCarSellInfo xcsi=carSellManageDao.get(" from XsCarSellInfo xcsi where xcsi.xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id());
			xcsi.setXsCarGiveUp("1");
			if(xcsi.getXsSellCarReserve()!=null){
				StringBuffer sb=new StringBuffer("SELECT  (SELECT CASE WHEN  xsc.account_CUMULATIVE!=''  THEN xsc.account_CUMULATIVE ELSE 0 END) AS temp2");
				sb.append(" FROM xs_sell_collections xsc INNER JOIN xs_sell_car_reserve xscr ON xscr.reserve_code=xsc.account_id");
				sb.append(" INNER JOIN xs_car_sell_info xcsi ON xcsi.reserve_id=xscr.reserve_id WHERE xcsi.xs_car_sel_id="+carSellManageVo.getXs_Car_Sel_Id());	
				List list=carSellManageDao.createSQLQuery(sb.toString());
				if(list!=null&&list.size()>0){
					carSellManageVo.setPayment_Money(list.get(0).toString());
					XsBackCarLog xsBackCarLog = new XsBackCarLog();
					xsBackCarLog.setBackCarCode(CreateID.createId("xsBackCarLog_before", Contstants.SELL_BILLSDEPLOY.CARRESERVEREFUNDMENT));
					
					xsBackCarLog.setBackCarDocument(xcsi.getXsSellCarReserve().getReserveCode());
					xsBackCarLog.setBackCarDate(new java.sql.Date(new java.util.Date().getTime())+"");
					//退款金额
					xsBackCarLog.setBackCarMoney(0d);
					//经办人
					xsBackCarLog.setBackCarPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
					//应退金额
					xsBackCarLog.setBackCarSunmoney(Double.parseDouble(carSellManageVo.getPayment_Money()));
					
					xsBackCarLog.setExamine(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT, Contstants.ADUIT.WEISHENHE,carSellManageVo.getEnterpriseId()));
					refundmentDao.save(xsBackCarLog);
				}					
			}
			int carState=baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,carSellManageVo.getEnterpriseId());
			int customState=baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SS,carSellManageVo.getEnterpriseId());			
			xsSellFlowControlDao.executeSql("update xs_car_info set xs_car_sell_state="+carState+" where xs_car_id="+xcsi.getXsCarId());
			xsSellFlowControlDao.executeSql("update xs_custom_info set xs_custom_deal="+customState+" where custom_id="+xcsi.getCustomId());
			carSellManageDao.merge(xcsi);
			SellCarReserveVo scrVo=new SellCarReserveVo();
			scrVo.setReserveId(xcsi.getXsSellCarReserve().getReserveId());
			scrVo.setVinCode(xcsi.getXsSellCarReserve().getVinCode());
			scrVo.setCarId(xcsi.getXsCarId());
			scrVo.setReserveCode(xcsi.getXsSellCarReserve().getReserveCode());
			scrVo.setVinNum(2);
			sellService.modifyReverd(scrVo);
			msg.setSuccess(true);
			msg.setMsg("放弃购车成功！");
			setContent(SystemUser.getUser().getBasStuff().getStfName()+"放弃编号为"+carSellManageVo.getSell_Code()+"的销售单");
		} catch (Exception e) {
			msg.setMsg("放弃购车操作失败！");
		}
		return msg;
	}

	/**
	 * 获取车辆档案
	 */
	
	public Json getCarInfor(CarSellManageVo carSellManageVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT xci.xs_car_id,carBrand.carBrandId,carBrand.carBrandName,");
		sb.append(" carColor.carColorId,carColor.carColorName,");
		sb.append(" ornamentColor.ornamentColorId,ornamentColor.ornamentColorName,");
		sb.append(" xci.xs_car_licenseName,xcm.xs_model_id,xcm.xs_model_name,");
		sb.append(" xci.xs_car_motor_number,xci.xs_car_ocn,xci.xs_car_vin_number,");
		sb.append(" xci.xs_car_rideLimit_number,xcm.xs_model_salesPrice,xcm.xs_model_salesLimitPrice,");
		sb.append(" (xcm.xs_model_costPrice+(CASE WHEN dd.costprice!='' THEN dd.costprice ELSE 0 END)) AS xs_model_costPrice,");
		sb.append(" (xcm.xs_model_salesPrice+(CASE WHEN dd.sellprice!='' THEN dd.sellprice ELSE 0 END)) AS xs_model_salesPrices");
		sb.append(" FROM xs_car_info xci INNER JOIN");
		sb.append(" (SELECT xc.child_id AS carBrandId,xc.dataValue AS carBrandName FROM xs_childdictionary xc) carBrand");
		sb.append("  ON xci.xs_car_brand=carBrand.carBrandId");
		sb.append(" LEFT OUTER JOIN (SELECT xc.child_id AS carColorId,xc.dataValue AS carColorName FROM xs_childdictionary xc) carColor");
		sb.append(" ON xci.xs_car_color=carColor.carColorId");
		sb.append(" LEFT OUTER JOIN (SELECT xc.child_id AS ornamentColorId,xc.dataValue AS ornamentColorName FROM xs_childdictionary xc) ornamentColor");
		sb.append(" ON xci.xs_car_InteriorColor=ornamentColor.ornamentColorId");
		sb.append(" INNER JOIN xs_car_model xcm ON xcm.xs_model_id=xci.xs_car_model_id");
		
		sb.append(" LEFT OUTER JOIN ( SELECT adds.xs_car_id,adds.costprice,(adds.sellprice1+adds.sellprice2) AS sellprice"); 
		sb.append(" FROM ( SELECT xsc.xs_car_id,(SELECT SUM(parts.paets_case_money) FROM xs_sell_carInstallOut parts WHERE parts.Install_id=xsc.Install_id) AS costprice,");	    
		sb.append(" (SELECT SUM(parts.partsAmount) FROM xs_sell_carInstallOut parts WHERE parts.Install_id=xsc.Install_id) AS sellprice1,");	    
		sb.append(" (SELECT SUM(item.ITEM_MONEY )FROM xs_sell_carInstallProjectOut item WHERE item.Install_id=xsc.Install_id) AS sellprice2");	    
		sb.append(" FROM xs_sell_carInstall xsc ");	    
		sb.append(" ) adds ) dd ON dd.xs_car_id=xci.xs_car_id " +
				" INNER JOIN xs_sell_instorehouse_del instorehouse ON xci.xs_car_id = instorehouse.xs_car_id " +
				"INNER  JOIN xs_sell_instorehouse sell ON sell.instorehouse_id = instorehouse.instorehouse_id");
		sb.append(" WHERE xci.xs_car_sell_state='"+baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,carSellManageVo.getEnterpriseId())+"' " +
				" AND instorehouse.details_id NOT IN (" +
				"SELECT del.details_id FROM xs_sell_instorehouse_del del INNER JOIN xs_sell_allocatel_detail de  ON de.details_id = del.details_id " +
				"INNER JOIN xs_sell_allocatel_back back    ON de.allocatel_detail_id = back.allocatel_detail_id " +
				"INNER JOIN xs_sell_back ba    ON back.back_id = ba.back_id " +
				"WHERE ba.examine=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT2+"' and child.enterprise_id="+carSellManageVo.getEnterpriseId()+"))" +
				
				"and sell.examine_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"' and child.enterprise_id="+carSellManageVo.getEnterpriseId()+")" +
										 "AND  instorehouse.details_id NOT IN(" +
										"SELECT details_id FROM xs_sell_retreat WHERE instorehouse_type=" +
										" (SELECT child.child_id FROM xs_childdictionary  child, " +
										"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
										"AND parent.dataKey='"+Contstants.INSTORESTYLE.PARENTINSTORE+"' AND " +
										"child.dataKey='"+Contstants.INSTORESTYLE.BACK+"' and child.enterprise_id="+carSellManageVo.getEnterpriseId()+"))");
		
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sb.append(" and xci.enterprise_id ="+carSellManageVo.getEnterpriseId()+"");	
		}
		List<CarSellManageVo> rows=new ArrayList();
		List<Object[]> list=carSellManageDao.createSQLQuery(sb.toString(), carSellManageVo.getPage(), carSellManageVo.getRows());
		CarSellManageVo xcmVo=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				xcmVo=new CarSellManageVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					xcmVo.setXs_Car_Id(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					xcmVo.setCar_Brand_Id(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					xcmVo.setCar_Brand_Name(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					xcmVo.setCar_Color(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					xcmVo.setCar_Color_Name(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					xcmVo.setXs_Car_InteriorColor(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					xcmVo.setXs_Car_InteriorColor_Name(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					xcmVo.setXs_Car_LicenseName(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					xcmVo.setXs_Car_Model_Id(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					xcmVo.setXs_Car_Model(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					xcmVo.setCarMotor_Number(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					xcmVo.setXs_Car_Ocn(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					xcmVo.setXs_Car_Vin_Number(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					xcmVo.setXs_Car_RideLimit_Number(obj[13].toString());
				if(obj[14]!=null&&obj[14].toString().length()>0)
					xcmVo.setXs_Model_SalesPrice(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					xcmVo.setXs_Model_SalesLimitPrice(obj[15].toString());
				if(obj[16]!=null&&obj[16].toString().length()>0)
					xcmVo.setXs_Model_CostPrice(obj[16].toString());
				if(obj[17]!=null&&obj[17].toString().length()>0)
					xcmVo.setXs_Car_Sel_Transaction_Money(obj[17].toString());
				rows.add(xcmVo);
			}
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(carSellManageDao.getSQLCount(sb.toString(), null));
		return json;
	}

	/**
	 * 获取客户信息
	 */
	
	public Json getcustomInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT aa.* FROM (SELECT xci.custom_id,xci.xs_custom_name,bs.stf_id,bs.stf_name,xci.xs_custom_zipcode,");
		sb.append(" xci.xs_custom_telephone,xci.xs_custom_codeCard,xcl.xs_leva_id,xcl.xs_leva_name,");
		sb.append(" xc2.child_id AS temp3,xc2.dataValue AS temp4,xci.xs_custom_credentials,");
		sb.append(" xc3.child_id AS temp5,xc3.dataValue AS temp6,xci.xs_custom_address,xci.xs_contacts_person,xci.enterprise_id");
		sb.append(" FROM xs_custom_info xci INNER JOIN bas_stuff bs ON bs.stf_id=xci.stf_id");
		sb.append(" and xci.xs_custom_deal="+baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.SS,carSellManageVo.getEnterpriseId()));
		sb.append(" LEFT OUTER JOIN xs_custom_leva xcl ON xcl.xs_leva_id=xci.xs_custom_hide_level "); 
		sb.append(" LEFT OUTER JOIN xs_childdictionary xc2 ON xc2.child_id=xci.xs_custom_source");
		sb.append(" LEFT OUTER JOIN xs_childdictionary xc3 ON xc3.child_id=xci.xs_custom_property) aa");
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sb.append(" WHERE aa.enterprise_id ="+carSellManageVo.getEnterpriseId()+"");	
		}
		List<CarSellManageVo> rows=new ArrayList();
		List<Object[]> list=carSellManageDao.createSQLQuery(sb.toString(), carSellManageVo.getPage(), carSellManageVo.getRows());
		CarSellManageVo csmVo=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				csmVo=new CarSellManageVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					csmVo.setXs_Custom_Id(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					csmVo.setXs_Custom_Name(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					csmVo.setStf_Id(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					csmVo.setStf_Name(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					csmVo.setXs_Custom_Zipcode(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					csmVo.setXs_Custom_Telephone(obj[5].toString());					
				if(obj[6]!=null&&obj[6].toString().length()>0)
					csmVo.setXs_Custom_Code_Card(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					csmVo.setXs_Custom_Hide_Level(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					csmVo.setXs_Custom_Hide_Level_Name(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					csmVo.setXs_Custom_Source(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					csmVo.setXs_Custom_Source_Name(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					csmVo.setXs_Custom_Credentials(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					csmVo.setXs_Custom_Property(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					csmVo.setXs_Custom_Property_Name(obj[13].toString());
				if(obj[14]!=null&&obj[14].toString().length()>0)
					csmVo.setXs_Custom_Address(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					csmVo.setXs_Contacts_Person(obj[15].toString());
				rows.add(csmVo);
			}
		Json json=new Json();
		json.setRows(rows);
		json.setTotal(carSellManageDao.getSQLCount(sb.toString(), null));
		return json;
	}
	/**
	 * 获取指定客户信息
	 * */
	
	public CarSellManageVo getCustomInforByCustomId(
			CarSellManageVo carSellManageVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT aa.* FROM (SELECT xci.custom_id,xci.xs_custom_name,bs.stf_id,bs.stf_name,xci.xs_custom_zipcode,");
		sb.append(" xci.xs_custom_telephone,xci.xs_custom_codeCard,xcl.xs_leva_id,xcl.xs_leva_name,");
		sb.append(" xc2.child_id AS temp3,xc2.dataValue AS temp4,xci.xs_custom_credentials,");
		sb.append(" xc3.child_id AS temp5,xc3.dataValue AS temp6,xci.xs_custom_address,xci.xs_contacts_person");
		sb.append(" FROM xs_custom_info xci INNER JOIN bas_stuff bs ON bs.stf_id=xci.stf_id");
		sb.append(" LEFT OUTER JOIN xs_custom_leva xcl ON xcl.xs_leva_id=xci.xs_custom_hide_level "); 
		sb.append(" LEFT OUTER JOIN xs_childdictionary xc2 ON xc2.child_id=xci.xs_custom_source");
		sb.append(" LEFT OUTER JOIN xs_childdictionary xc3 ON xc3.child_id=xci.xs_custom_property) aa ");
		sb.append(" where aa.custom_id="+carSellManageVo.getXs_Custom_Id());
		List<Object[]> list=carSellManageDao.createSQLQuery(sb.toString(), carSellManageVo.getPage(), carSellManageVo.getRows());
		CarSellManageVo csmVo=null;
		if(list!=null&&list.size()>0){
				Object [] obj=list.get(0);
				csmVo=new CarSellManageVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					csmVo.setXs_Custom_Id(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					csmVo.setXs_Custom_Name(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					csmVo.setStf_Id(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					csmVo.setStf_Name(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					csmVo.setXs_Custom_Zipcode(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					csmVo.setXs_Custom_Telephone(obj[5].toString());					
				if(obj[6]!=null&&obj[6].toString().length()>0)
					csmVo.setXs_Custom_Code_Card(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					csmVo.setXs_Custom_Hide_Level(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					csmVo.setXs_Custom_Hide_Level_Name(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					csmVo.setXs_Custom_Source(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					csmVo.setXs_Custom_Source_Name(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					csmVo.setXs_Custom_Credentials(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					csmVo.setXs_Custom_Property(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					csmVo.setXs_Custom_Property_Name(obj[13].toString());
				if(obj[14]!=null&&obj[14].toString().length()>0)
					csmVo.setXs_Custom_Address(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					csmVo.setXs_Contacts_Person(obj[15].toString());
			}
		return csmVo;
	}

	/**
	 * 转结算
	 */
	@Log( systemName="销售系统", moduleName="销售管理",opertype="转结算")
	
	public Boolean doCash(CarSellManageVo carSellManageVo) throws Exception {
		if(carSellManageVo.getXs_Car_Sel_Id()==null||carSellManageVo.getXs_Car_Sel_Id().length()==0)
			return false;
		XsCarSellInfo car=(XsCarSellInfo) sellAccountDao.get("from XsCarSellInfo where xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id());
		car.setInvoiceReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1,carSellManageVo.getEnterpriseId()));//修改是否转结算为是
		sellAccountDao.merge(car);
		//应收汇总
		Double costPrice = car.getXsCarSelTransactionMoney();
		int types=(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE4,carSellManageVo.getEnterpriseId()));
		sellAccountDao.saveSellAccount(car.getXsCarSelId(), carSellManageVo.getSell_Code(),types, costPrice,carSellManageVo.getXs_Car_Sel_Remark(),carSellManageVo.getEnterpriseId());
		//
		setContent(SystemUser.getUser().getBasStuff().getStfName()+"将编号为"+carSellManageVo.getSell_Code()+"的单子转结算");
		return true;
	}

	/**
	 * 添加客户信息
	 */
	
	public String addOutCustom(CarSellManageVo carSellManageVo) throws Exception {
		XsCustomInfo xsCustomInfo = new XsCustomInfo();
		xsCustomInfo.setXsCustomName(carSellManageVo.getXs_Custom_Name()!=null && !carSellManageVo.getXs_Custom_Name().equals("") ? carSellManageVo.getXs_Custom_Name() : "");
		xsCustomInfo.setStfId(carSellManageVo.getStf_Id()!=null && !carSellManageVo.getStf_Id().equals("") ? Integer.parseInt(carSellManageVo.getStf_Id()) : 0);
		xsCustomInfo.setXsCustomZipcode(carSellManageVo.getXs_Custom_Zipcode()!=null && !carSellManageVo.getXs_Custom_Zipcode().equals("") ? carSellManageVo.getXs_Custom_Zipcode() : "");
		xsCustomInfo.setXsCustomTelephone(carSellManageVo.getXs_Custom_Telephone()!=null && !carSellManageVo.getXs_Custom_Telephone().equals("") ? carSellManageVo.getXs_Custom_Telephone() : "");
		xsCustomInfo.setXsCustomCodeCard(carSellManageVo.getXs_Custom_Code_Card()!=null && !carSellManageVo.getXs_Custom_Code_Card().equals("") ? carSellManageVo.getXs_Custom_Code_Card() : "");
		xsCustomInfo.setXsCustomHideLevel(carSellManageVo.getXs_Custom_Hide_Level()!=null && !carSellManageVo.getXs_Custom_Hide_Level().equals("") ? Integer.parseInt(carSellManageVo.getXs_Custom_Hide_Level()) : 0);
		xsCustomInfo.setXsCustomCredentials(carSellManageVo.getXs_Custom_Credentials()!=null && !carSellManageVo.getXs_Custom_Credentials().equals("") ? carSellManageVo.getXs_Custom_Credentials() : "");
		xsCustomInfo.setXsCustomProperty(carSellManageVo.getXs_Custom_Property()!=null && !carSellManageVo.getXs_Custom_Property().equals("") ? Integer.parseInt(carSellManageVo.getXs_Custom_Property()) : 0);
		xsCustomInfo.setXsCustomAddress(carSellManageVo.getXs_Custom_Address()!=null && !carSellManageVo.getXs_Custom_Address().equals("") ? carSellManageVo.getXs_Custom_Address() : "");
		xsCustomInfo.setXsContactsPerson(carSellManageVo.getXs_Contacts_Person()!=null && !carSellManageVo.getXs_Contacts_Person().equals("") ? carSellManageVo.getXs_Contacts_Person() : "");
		int xsCustomDeal = baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.AS,carSellManageVo.getEnterpriseId());
		xsCustomInfo.setXsCustomDeal(xsCustomDeal);
		String xsCustomCode = CreateID.createId("XsCustomInfo", Contstants.SELL_BILLSDEPLOY.CUSTOMTNFOR);
		xsCustomInfo.setXsCustomCode(xsCustomCode);
		sellAccountDao.save(xsCustomInfo);
		return xsCustomCode;
	}

	/**
	 * 转售后
	 */
	
	public int doSellAfter(CarSellManageVo carSellManageVo) throws Exception {
		int yorn = 0;
		List rlist = sellAccountDao.find("FROM XsRepay where enterpriseId="+carSellManageVo.getEnterpriseId());
		List<XsRepay> list = rlist;
		if(list!=null && list.size()>0){
			
			String hql2 = "FROM XsSellCover where xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id()+"";
			List rlist2 = sellAccountDao.find(hql2);
			//判断是否已转入售后 未转入返回1   已转入返回2
			if(rlist2==null){
				for(XsRepay xsRepay : list){
				XsSellCover xsSellCover = new XsSellCover();
				xsSellCover.setXsCarSelId(carSellManageVo.getXs_Car_Sel_Id()!=null && !carSellManageVo.getXs_Car_Sel_Id().equals("") ? Integer.parseInt(carSellManageVo.getXs_Car_Sel_Id()) : 0);
				java.util.Date date = new java.util.Date();
				Calendar conld = Calendar.getInstance();
				conld.setTime(date);
				conld.add(Calendar.DAY_OF_MONTH, xsRepay.getRepayDay());
				date = conld.getTime();
				xsSellCover.setConsultPlanDate(new java.sql.Date(date.getTime()));
				xsSellCover.setConsultRate(xsRepay.getRepayId());
				xsSellCover.setEnterpriseId(carSellManageVo.getEnterpriseId());
				xsSellCover.setXsSellCoverDegree(baseTagDAO.getChild(Contstants.BASE_SELL.CONSULTDEGREE, Contstants.BASE_SELL.wu, carSellManageVo.getEnterpriseId()));
				sellAccountDao.save(xsSellCover);
				}
				
				//将销售单的状态改为 转入售后状态  0 是未转入， 1是转入
				XsCarSellInfo xsCarSellInfo = (XsCarSellInfo)sellAccountDao.get("FROM XsCarSellInfo where xsCarSelId="+carSellManageVo.getXs_Car_Sel_Id()+"");
				xsCarSellInfo.setSellafterStatus(1);
				sellAccountDao.merge(xsCarSellInfo);
				
				//修改车辆档案 的销售状态 为销售出库
				XsCarInfo xsCarInfo = (XsCarInfo)sellAccountDao.get("from XsCarInfo WHERE carId="+xsCarSellInfo.getXsCarId()+"");
				XsChilddictionary carSellState = baseTagDAO.getChild(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.SELLOUT,carSellManageVo.getEnterpriseId());
				xsCarInfo.setSellStateChild(carSellState);
				sellAccountDao.merge(xsCarInfo);
				yorn = 1;
			}else{
				yorn = 2;
			}
		}
		return yorn;
	}

	/**
	 * 获取PDI检测历史记录
	 */
	
	public Json findFactoryPdiCheck(CarSellManageVo carSellManageVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,carSellManageVo.getEnterpriseId()).getCiValue()));

		StringBuffer sql=new StringBuffer("SELECT a.check_date, " +
				" (SELECT datavalue FROM xs_childdictionary m WHERE m.child_id = a.check_comtent) AS check_comtent2," +
				" bs.stf_name, " +
				" b.xs_car_vin_number," +
				" b.xs_car_brand," +
				"(SELECT datavalue FROM xs_childdictionary m WHERE m.child_id = b.xs_car_brand) AS car_brand," +
				" b.xs_car_model_id," +
				"(SELECT datavalue FROM xs_childdictionary m WHERE m.child_id = b.xs_car_model_id) AS car_model," +
				"(SELECT datavalue FROM xs_childdictionary m WHERE m.child_id = a.status) AS status2,a.remark" +
				" FROM  xs_pdi_check a JOIN xs_car_info b ON a.xs_car_id = b.xs_car_id" +
				" INNER JOIN bas_stuff bs ON a.check_person=bs.stf_id"+
				" LEFT JOIN  xs_car_sell_info c ON a.xs_car_sel_id = c.xs_car_sel_id where 1 = 1 ");
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sql.append(" and c.enterprise_id ="+carSellManageVo.getEnterpriseId()+"");	
		}
		if(carSellManageVo.getXs_Car_Vin_Number()!=null && !carSellManageVo.getXs_Car_Vin_Number().equals("")){
			sql.append(" and b.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Car_Vin_Number().trim())+"%'");
		}
		if(carSellManageVo.getCheck_Person()!=null && !carSellManageVo.getCheck_Person().equals("")){
			sql.append(" and a.check_person = "+carSellManageVo.getCheck_Person()+"");
		}
		if(carSellManageVo.getCheck_Date()!=null && !carSellManageVo.getCheck_Date().trim().equals("")){
			sql.append(" and DATE(a.check_date) >= '"+carSellManageVo.getCheck_Date()+"'");
		}
		if(carSellManageVo.getCheck_Date2()!=null && !carSellManageVo.getCheck_Date2().trim().equals("")){
			sql.append( " and DATE(a.check_date) <= '"+carSellManageVo.getCheck_Date2()+"'");
		}
		if((carSellManageVo.getCheck_Date() == null
				|| carSellManageVo.getCheck_Date().equals("")) 
				&& (carSellManageVo.getCheck_Date2() == null
				|| carSellManageVo.getCheck_Date2().equals(""))){
			sql.append(" and DATE(a.check_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		
		List rlist = sellAccountDao.createSQLQuery(sql.toString(),carSellManageVo.getPage(),carSellManageVo.getRows());
		Object[] obj = null;
		List list = new ArrayList();
		if(rlist!=null && rlist.size()>0){
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[])rlist.get(i);
				CarSellManageVo vo = new CarSellManageVo();
				if(obj[0]!=null&&obj[0].toString().length()>0){vo.setCheck_Date(obj[0].toString());}
				if(obj[1]!=null&&obj[1].toString().length()>0){vo.setCheck_Comtent(obj[1].toString());}
				if(obj[2]!=null&&obj[2].toString().length()>0){vo.setCheck_Person(obj[2].toString());}
				if(obj[3]!=null&&obj[3].toString().length()>0){vo.setCar_Vin_Number(obj[3].toString());}
				if(obj[4]!=null&&obj[4].toString().length()>0){vo.setCar_Brand_Id(obj[4].toString());}
				if(obj[5]!=null&&obj[5].toString().length()>0){vo.setXs_Car_Brand(obj[5].toString());}
				if(obj[6]!=null&&obj[6].toString().length()>0){vo.setXs_Car_Model_Id(obj[6].toString());}
				if(obj[7]!=null&&obj[7].toString().length()>0){vo.setXs_Car_Model(obj[7].toString());}
				if(obj[8]!=null&&obj[8].toString().length()>0){vo.setStatus(obj[8].toString());}
				if(obj[9]!=null&&obj[9].toString().length()>0){vo.setRemark(obj[9].toString());}
				list.add(vo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(sellAccountDao.getCountBySQL(sql.toString()));
		return json;
	}
	
	/**
	 * pdi检测中formater中通过评价编号获取评价结果
	 */
	
	public XsChilddictionary getCheckResault(int childid) throws Exception{
		XsChilddictionary xsChilddictionary = (XsChilddictionary)sellCarReserveDao.get("from XsChilddictionary WHERE childId="+childid+"");
		return xsChilddictionary;
	}

	/**
	 * 车辆销售查询
	 */
	
	public Datagrid queryFatherInfor(CarSellManageVo carSellManageVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 		Contstants.COLLIGATES.DEFAULTSHOWDAY,carSellManageVo.getEnterpriseId()).getCiValue()));
		Datagrid dg = new Datagrid();
		List footer = new ArrayList();
		List<CarSellManageVo> list = new ArrayList<CarSellManageVo>();
		StringBuffer sql = new StringBuffer("" +
				" SELECT A.xs_car_sel_id,A.xs_car_id,b.xs_car_model_id,C.xs_model_name," +
				" COUNT(b.xs_car_model_id),SUM(A.xs_car_sel_transaction_money), SUM(c.xs_model_costPrice)," +
				" D.invoice_date,SUM(D.invoice_parce)	" +
				" FROM xs_car_sell_info A " +
				" JOIN xs_sell_flow_control flow on flow.xsfcontrol_document=a.sell_code " +
				" JOIN xs_car_info B ON a.xs_car_id=b.xs_car_id " +
				" JOIN xs_car_model C ON C.xs_model_id=b.xs_car_model_id " +
				" LEFT JOIN xs_sell_invoice D ON d.xs_car_sel_id=a.xs_car_sel_id " +
				" LEFT JOIN xs_sell_car_reserve E ON e.reserve_id=a.reserve_id " +
				" LEFT JOIN xs_distributor_info H ON h.xs_distributor_id=b.xs_distributor_id " +
				" JOIN xs_custom_info F ON f.custom_id=a.custom_id " +
				" LEFT JOIN xs_sell_retreat G ON G.retreat_id=a.out_id" +
				" JOIN bas_stuff Y ON Y.STF_ID=a.xs_car_stf_id where 1=1 " );
		
		//企业编号
		if(carSellManageVo.getEnterpriseId()!=null && !carSellManageVo.getEnterpriseId().equals("")){
			sql.append(" AND A.Enterprise_Id="+carSellManageVo.getEnterpriseId()+"");
		}
		//销售日期 
		if(carSellManageVo.getXs_Car_Sel_Data()!=null && !carSellManageVo.getXs_Car_Sel_Data().trim().equals("")){
			sql.append(" and DATE(A.Xs_Car_Sel_Data) >= '"+carSellManageVo.getXs_Car_Sel_Data()+"'");
		}
		if(carSellManageVo.getXs_Car_Sel_Data2()!=null && !carSellManageVo.getXs_Car_Sel_Data2().trim().equals("")){
			sql.append( " and DATE(A.Xs_Car_Sel_Data) <= '"+carSellManageVo.getXs_Car_Sel_Data2()+"'");
		}
		if((carSellManageVo.getXs_Car_Sel_Data()==null ||"".equals(carSellManageVo.getXs_Car_Sel_Data().trim()))&&
				(carSellManageVo.getXs_Car_Sel_Data2()==null ||"".equals(carSellManageVo.getXs_Car_Sel_Data2().trim()))){
			sql.append( " and DATE(A.Xs_Car_Sel_Data) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
	
		if(carSellManageVo.getRetreat_date()!=null && !"".equals(carSellManageVo.getRetreat_date().trim())){
			sql.append(" and DATE(G.retreat_date) >= '"+carSellManageVo.getRetreat_date()+"'");
		}
		if(carSellManageVo.getRetreat_date2()!=null && !"".equals(carSellManageVo.getRetreat_date2().trim())){
			sql.append( " and DATE(G.retreat_date) <= '"+carSellManageVo.getRetreat_date2()+"'");
		}
		
		if(carSellManageVo.getInvoice_date()!=null && !"".equals(carSellManageVo.getInvoice_date().trim())){
			sql.append(" and DATE(D.invoice_date) >= '"+carSellManageVo.getInvoice_date()+"'");
		}
		if(carSellManageVo.getInvoice_date2()!=null && !"".equals(carSellManageVo.getInvoice_date2().trim())){
			sql.append( " and DATE(D.invoice_date) <= '"+carSellManageVo.getInvoice_date2()+"'");
		}
		if(carSellManageVo.getStf_Id()!=null && !carSellManageVo.getStf_Id().equals("")){
			sql.append( " and a.xs_car_stf_id="+carSellManageVo.getStf_Id()+"");
		}
		if(carSellManageVo.getXs_Car_Brand()!=null && !carSellManageVo.getXs_Car_Brand().equals("")){
			sql.append( " and B.xs_car_brand="+carSellManageVo.getXs_Car_Brand()+"");
		}
		if(carSellManageVo.getQueryCarModel()!=null && !carSellManageVo.getQueryCarModel().equals("")){
			sql.append( " and b.xs_car_model_id ="+carSellManageVo.getQueryCarModel()+"");
		}
		if(carSellManageVo.getXs_Car_Color()!=null && !carSellManageVo.getXs_Car_Color().equals("")){
			sql.append( " and b.xs_car_color ="+carSellManageVo.getXs_Car_Color()+"");
		}
		if(carSellManageVo.getXs_Custom_Name()!=null && !carSellManageVo.getXs_Custom_Name().equals("")){
			sql.append( " and F.xs_custom_name like '%"+StringEscapeUtils.escapeSql(carSellManageVo.getXs_Custom_Name().trim())+"%'" );
		}
		if(carSellManageVo.getCar_Vin_Number()!=null && !carSellManageVo.getCar_Vin_Number().equals("")){
			sql.append( " and b.xs_car_vin_number like '%"+carSellManageVo.getCar_Vin_Number()+"%'");
		}
		if(carSellManageVo.getXs_Distributor_Id()!=null && !carSellManageVo.getXs_Distributor_Id().equals("")){
			sql.append( " and b.xs_distributor_id ="+carSellManageVo.getXs_Distributor_Id()+"");
		}
		if(carSellManageVo.getPayment_Way()!=null && !carSellManageVo.getPayment_Way().equals("")){
			sql.append( " and e.pay_way ="+carSellManageVo.getPayment_Way()+"");
		}
		if(carSellManageVo.getXs_car_type()!=null && !carSellManageVo.getXs_car_type().equals("")){
			sql.append( " and B.xs_car_type ="+carSellManageVo.getXs_car_type()+"");
		}
		if(carSellManageVo.getXs_Custom_Property()!=null && !carSellManageVo.getXs_Custom_Property().equals("")){
			sql.append( " and F.xs_custom_property ="+carSellManageVo.getXs_Custom_Property()+"");
		}
		if(carSellManageVo.getDeptId()!=null && !carSellManageVo.getDeptId().equals("")){
			sql.append( " and Y.DEPT_ID ="+carSellManageVo.getDeptId()+"");
		}
		sql.append(" GROUP BY  b.xs_car_model_id");
		
		List<Object[]> resultList = carSellManageDao.createSQLQuery(sql.toString(),
				carSellManageVo.getPage(), carSellManageVo.getRows());
		if (resultList != null && resultList.size() > 0) {
			Object[] obj = null;
			CarSellManageVo sell=null;
			for (int i = 0; i < resultList.size(); i++) {
				 sell = new CarSellManageVo();
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				obj = resultList.get(i);
				sell.setXs_Car_Sel_Id(obj[0] != null ? obj[0].toString() : null);
				sell.setXs_Car_Id(obj[1] != null ? obj[1].toString() : null);
				sell.setXs_Car_Model_Id(obj[2] != null ? obj[2].toString() : null);
				sell.setXs_Car_Model(obj[3] != null ? obj[3].toString() : null);
				sell.setNumbers(obj[4] != null ? obj[4].toString() : null);
				sell.setXs_Car_Sel_Transaction_Money(obj[5] != null ? obj[5].toString() : null);
				sell.setXs_Model_CostPrice(obj[6] != null ? obj[6].toString() : null);
				sell.setInvoice_date(obj[7] != null ? obj[7].toString() : null);
				sell.setInvoice_parce(obj[8] != null ? obj[8].toString() : null);
				sell.setState("closed");
				sell.setIconCls("icon-blank");
				list.add(sell);
			}
			//汇总
			sell=new CarSellManageVo();
			sell.setXs_Car_Model("汇总");
			sell.setState("open");
			sell.setIconCls("icon-blank");
			int count = 0;
			Double transaction_Money=0.0;
			Double modelPrice = 0.0;
			Double invoice_parce=0.0;
			for (CarSellManageVo sVo2 : list) {
				if(sVo2.getNumbers()!=null&&!"".equals(sVo2.getNumbers())){
					count += Integer.parseInt(sVo2.getNumbers());
				}
				if(sVo2.getXs_Car_Sel_Transaction_Money()!=null&&!"".equals(sVo2.getXs_Car_Sel_Transaction_Money())){
					transaction_Money +=Double.parseDouble(sVo2.getXs_Car_Sel_Transaction_Money());
				}
				if(sVo2.getXs_Model_CostPrice()!=null&&!"".equals(sVo2.getXs_Model_CostPrice())){
					modelPrice +=Double.parseDouble(sVo2.getXs_Model_CostPrice());
				}
				
				if(sVo2.getInvoice_parce()!=null&&!"".equals(sVo2.getInvoice_parce())){
					invoice_parce +=Double.parseDouble(sVo2.getInvoice_parce());
				}
				 
			}
			
			sell.setNumbers(count+"");
			sell.setXs_Car_Sel_Transaction_Money(transaction_Money+"");
			sell.setXs_Model_CostPrice(modelPrice+"");
			sell.setInvoice_parce(invoice_parce+"");
			footer.add(sell);
		}
		int total = carSellManageDao.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		dg.setFooter(footer);
		return dg;
	
	}

	
	public List<CarSellManageVo> findChildList(CarSellManageVo carSellManageVo)
			throws Exception {
		if (carSellManageVo.getXs_Custom_Name() != null)
			carSellManageVo.setXs_Custom_Name(new String(carSellManageVo
					.getXs_Custom_Name().getBytes("ISO-8859-1"), "UTF-8"));
		return carSellManageDao.findChildList(carSellManageVo);
	}
	/**
	 * 查找销售单相关数据
	 * */
	
	public CarSellManageVo findContact(CarSellManageVo carSellManageVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT aa.temp1,xci.xs_car_licenseName,xci.xs_car_motor_number,");
		sb.append(" xci.xs_car_model_id,xci.xs_car_licensePlate,xci.xs_car_color,");
		sb.append(" xcinfo.stf_id,xcinfo.xs_custom_zipcode,xcinfo.xs_custom_telephone,");
		sb.append(" xcinfo.xs_custom_codeCard,xcinfo.xs_custom_hide_level,");
		sb.append(" xcinfo.xs_custom_source,xcinfo.xs_custom_credentials,");
		sb.append(" xcinfo.xs_custom_property,xcinfo.xs_custom_address,xcinfo.xs_contacts_person,");
		sb.append(" xcsi.xs_car_sel_data,xsl.loan_bank,xsl.first_payment,xsl.balance_,xsl.year_,");
		sb.append(" xcm.xs_model_salesPrice,xcm.xs_model_salesLimitPrice");
		sb.append(" FROM xs_car_info xci INNER JOIN xs_car_sell_info xcsi ON xcsi.xs_car_id=xci.xs_car_id");
		sb.append(" INNER JOIN xs_car_model xcm ON xcm.xs_model_id=xci.xs_car_model_id");
		sb.append(" INNER JOIN xs_custom_info xcinfo ON xcsi.custom_id=xcinfo.custom_id");
		sb.append(" INNER JOIN (SELECT xc.child_id AS temp1,xc.dataValue AS temp2 FROM xs_childdictionary xc) aa ON aa.temp1=xci.xs_car_InteriorColor");
		sb.append(" LEFT OUTER JOIN xs_sell_loan xsl ON xsl.xs_car_sel_id=xcsi.xs_car_sel_id");
		sb.append(" WHERE xcsi.sell_code='"+carSellManageVo.getSell_Code()+"'");
		CarSellManageVo csMVo=new CarSellManageVo();;
		List<Object[]> list=carSellManageDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			Object[] obj=list.get(0);
			if(obj[0]!=null&&obj[0].toString().length()>0)
				csMVo.setXs_Car_InteriorColor(obj[0].toString());
			if(obj[1]!=null&&obj[1].toString().length()>0)
				csMVo.setXs_Car_LicenseName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				csMVo.setCarMotor_Number(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				csMVo.setXs_Car_Model_Id(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				csMVo.setCarLicense_Plate(obj[4].toString());
			if(obj[5]!=null&&obj[5].toString().length()>0)
				csMVo.setCar_Color(obj[5].toString());
			if(obj[6]!=null&&obj[6].toString().length()>0)
				csMVo.setStf_Id(obj[6].toString());
			if(obj[7]!=null&&obj[7].toString().length()>0)
				csMVo.setXs_Custom_Zipcode(obj[7].toString());
			if(obj[8]!=null&&obj[8].toString().length()>0)
				csMVo.setXs_Custom_Telephone(obj[8].toString());
			if(obj[9]!=null&&obj[9].toString().length()>0)
				csMVo.setXs_Custom_CodeCard(obj[9].toString());
			if(obj[10]!=null&&obj[10].toString().length()>0)
				csMVo.setXs_Custom_Hide_Level(obj[10].toString());
			if(obj[11]!=null&&obj[11].toString().length()>0)
				csMVo.setXs_Custom_Source(obj[11].toString());
			if(obj[12]!=null&&obj[12].toString().length()>0)
				csMVo.setXs_Custom_Credentials(obj[12].toString());
			if(obj[13]!=null&&obj[13].toString().length()>0)
				csMVo.setXs_Custom_Property(obj[13].toString());
			if(obj[14]!=null&&obj[14].toString().length()>0)
				csMVo.setXs_Custom_Address(obj[14].toString());
			if(obj[15]!=null&&obj[15].toString().length()>0)
				csMVo.setXs_Contacts_Person(obj[15].toString());
			if(obj[16]!=null&&obj[16].toString().length()>0)
				csMVo.setXs_Car_Sel_Data(obj[16].toString());
			if(obj[17]!=null&&obj[17].toString().length()>0)
				csMVo.setLoan_Bank(obj[17].toString());
			if(obj[18]!=null&&obj[18].toString().length()>0)
				csMVo.setFirst_Payment(obj[18].toString());
			if(obj[19]!=null&&obj[19].toString().length()>0)
				csMVo.setBalance_(obj[19].toString());
			if(obj[20]!=null&&obj[20].toString().length()>0)
				csMVo.setYear_(obj[20].toString());
			if(obj[21]!=null&&obj[21].toString().length()>0)
				csMVo.setXs_Model_SalesPrice(obj[21].toString());
			if(obj[22]!=null&&obj[22].toString().length()>0)
				csMVo.setXs_Model_SalesLimitPrice(obj[22].toString());
		}		
		return csMVo;
	}
	/**
	 * 判断销售单是否放弃购车
	 * */
	
	public Boolean isBackCar(CarSellManageVo carSellManageVo) throws Exception {
		
		return null;
	}
	
}
