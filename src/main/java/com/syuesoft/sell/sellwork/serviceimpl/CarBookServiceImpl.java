package com.syuesoft.sell.sellwork.serviceimpl;

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
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.dao.CarModelDAO;
import com.syuesoft.sell.base.dao.CustomInfoDAO;
import com.syuesoft.sell.control.dao.XsSellFlowControlDao;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.sell.model.XsCarModel;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.model.XsSellCollections;
import com.syuesoft.sell.model.XsSellFlowControl;
import com.syuesoft.sell.sellwork.dao.CarBookDao;
import com.syuesoft.sell.sellwork.service.CarBookService;
import com.syuesoft.sell.sellwork.vo.CarBookVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;
import com.syuesoft.util.SystemUser;

@Service("carBookService")
public class CarBookServiceImpl extends BaseLogServiceImpl implements CarBookService {

	@Resource
	private CarBookDao carBookDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	@Resource
	private XsSellFlowControlDao xsSellFlowControlDao;
	@Resource
	private CarModelDAO carModelDAO ;
	@Resource
	private CustomInfoDAO customInfoDAO;
	
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	/**
	 * 添加预定信息
	 */
	@Log(systemName="销售系统", moduleName="车辆预订管理",opertype="新增")
	
	public Message addCarBookInfor(CarBookVo carBookVo) throws Exception {

		Message msg = new Message();
//		XsSellCarReserve xsSellCarReserveinfor = (XsSellCarReserve)carBookDao.get("from XsSellCarReserve where customId="+carBookVo.getXs_Custom_Id()+"");
//		//要求小客户只预订一辆车  判断客户是否已经预订过车辆
//		if(xsSellCarReserveinfor!=null){
//			msg.setSuccess(false);
//			msg.setMsg("该客户已预订车辆！");
//			return msg;
//		}else{
//		}//if end
			XsSellCarReserve x = new XsSellCarReserve();
			//x.setAllocateState(0);
			
			
			//企业编号
			x.setEnterpriseId(carBookVo.getEnterpriseId()!=null && !carBookVo.getEnterpriseId().equals("") ? carBookVo.getEnterpriseId(): 0);
			if(carBookVo.getXs_Car_Brand()!=null && !carBookVo.getXs_Car_Brand().equals("")){
				XsChilddictionary carBrand = baseTagDAO.findById(Integer.parseInt(carBookVo.getXs_Car_Brand()));
				x.setXsChilddictionary(carBrand);
			}
			if(carBookVo.getXs_Car_Color()!=null && !carBookVo.getXs_Car_Color().equals("")){
				XsChilddictionary carColor= baseTagDAO.findById(Integer.parseInt(carBookVo.getXs_Car_Color()));
				x.setXsChilddictionary1(carColor);
			}
			x.setCarModel(carBookVo.getXs_Car_Model_Id()!=null && !carBookVo.getXs_Car_Model_Id().equals("") ? Integer.parseInt(carBookVo.getXs_Car_Model_Id()):0);
			if(carBookVo.getCar_Output_Volume()!=null && !carBookVo.getCar_Output_Volume().equals("")){
				XsChilddictionary carOutput= baseTagDAO.findById(Integer.parseInt(carBookVo.getCar_Output_Volume()));
				x.setXsChilddictionary3(carOutput);
			}
		
			//x.setCarOutputVolume(carBookVo.getCar_Output_Volume()!=null && !carBookVo.getCar_Output_Volume().equals("") ? Integer.parseInt(carBookVo.getCar_Output_Volume()): 0);
			if(carBookVo.getXs_Car_Trim()!=null && !carBookVo.getXs_Car_Trim().equals("")){
				XsChilddictionary carTrim= baseTagDAO.findById(Integer.parseInt(carBookVo.getXs_Car_Trim()));
				x.setXsChilddictionary2(carTrim);
			}
			
			x.setCustomId(carBookVo.getXs_Custom_Id()!=null && !carBookVo.getXs_Custom_Id().equals("") ?Integer.parseInt(carBookVo.getXs_Custom_Id()): 0);
			x.setCustomOpinion(carBookVo.getCustom_Opinion());
			x.setMobilephone(carBookVo.getXs_Custom_Telephone());
			if(carBookVo.getXs_Custom_Property()!=null && !carBookVo.getXs_Custom_Property().equals("")){
				XsChilddictionary custom_Property= baseTagDAO.findById(Integer.parseInt(carBookVo.getXs_Custom_Property()));
				x.setXsChilddictionary4(custom_Property);
			}
			
			x.setDecorateMoney(carBookVo.getDecorate_Money()!=null && !carBookVo.getDecorate_Money().equals("") ? Double.parseDouble(carBookVo.getDecorate_Money()): 0);
			x.setDelState(0);
			//审核情况 默认未审核
			int cid = baseTagDAO.getChildId(Contstants.ADUIT.ADUIT,Contstants.ADUIT.WEISHENHE,carBookVo.getEnterpriseId());
				XsChilddictionary examine= baseTagDAO.findById(cid);
				x.setXsChilddictionary6(examine);
			
			//x.setExamine(cid);
			x.setFirstPayMoney(carBookVo.getFirst_Pay_Money()!=null && !carBookVo.getFirst_Pay_Money().equals("") ?Double.parseDouble(carBookVo.getFirst_Pay_Money()):0);
			x.setLevel(carBookVo.getLevel()!=null && !carBookVo.getLevel().equals("") ? Integer.parseInt(carBookVo.getLevel()): 0);
			x.setLimitLoadNum(carBookVo.getLimit_Load_Num()!=null && !carBookVo.getLimit_Load_Num().equals("") ? Integer.parseInt(carBookVo.getLimit_Load_Num()):0);
			x.setLoanBank(carBookVo.getLoan_Bank()!=null && !carBookVo.getLoan_Bank().equals("") ? Integer.parseInt(carBookVo.getLoan_Bank()) : 0);
			x.setLoanLimitMoney(carBookVo.getLoan_Limit_Money()!=null && !carBookVo.getLoan_Limit_Money().equals("") ? Double.parseDouble(carBookVo.getLoan_Limit_Money()):0.00);
			x.setLoanLimitYear(carBookVo.getLoan_Limit_Year() !=null && !carBookVo.getLoan_Limit_Year().equals("") ? Double.parseDouble(carBookVo.getLoan_Limit_Year()):0.00);
			//订单状态 默认预定中
			int oid = baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE,Contstants.BASE_SELL.YUDINGZHONG,carBookVo.getEnterpriseId());
			XsChilddictionary orderState= baseTagDAO.findById(oid);
			x.setXsChilddictionary7(orderState);
			//x.setOrderState(oid);
			x.setPactCode(carBookVo.getPact_Code());
			x.setPaymentMoney(carBookVo.getPayment_Money()!=null && !carBookVo.getPayment_Money().equals("") ? Double.parseDouble(carBookVo.getPayment_Money()): 0.00);
			
			if(carBookVo.getPay_Way()!=null && !carBookVo.getPay_Way().equals("")){
				XsChilddictionary pay_Way= baseTagDAO.findById(Integer.parseInt(carBookVo.getPay_Way()));
				x.setXsChilddictionary5(pay_Way);
			}
			//x.setPayWay(carBookVo.getPay_Way()!=null && !carBookVo.getPay_Way().equals("") ? Integer.parseInt(carBookVo.getPay_Way()):0);
			x.setPredictTakeDate(carBookVo.getPredict_Take_Date()!=null ? carBookVo.getPredict_Take_Date() : "");
			x.setRemark(carBookVo.getRemark());
			x.setReserveCode(carBookVo.getReserve_Code());
			String code = CreateID.createId("XsSellCarReserve", Contstants.SELL_BILLSDEPLOY.CARRESERVE);
			x.setReserveCode(code);
			x.setSellingprice(carBookVo.getSellingprice()!=null && !carBookVo.getSellingprice().equals("") ? Double.parseDouble(carBookVo.getSellingprice()):0.00);
			x.setShingleMoney(carBookVo.getShingle_Money()!=null && !carBookVo.getShingle_Money().equals("") ? Double.parseDouble(carBookVo.getShingle_Money()):0.00);
			
			x.setStfId(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
			x.setStfIdPerson(carBookVo.getStf_Id_Person()!=null && !carBookVo.getStf_Id_Person().equals("") ? Integer.parseInt(carBookVo.getStf_Id_Person()):0);
			x.setVinCode(carBookVo.getXs_Car_Vin_Number());
			x.setXsCarId(carBookVo.getXs_Car_Id()!= null && !carBookVo.getXs_Car_Id().equals("") ? Integer.parseInt(carBookVo.getXs_Car_Id()): 0);
			x.setXsDistributorCode(carBookVo.getXs_Distributor_Code());
			x.setVinCode(carBookVo.getXs_Car_Vin_Number()!=null ? carBookVo.getXs_Car_Vin_Number() : "");
			x.setReserveDete(new java.sql.Date(new java.util.Date().getTime())+"");
			carBookDao.save(x);
			
			//修改客户档案成交状态为 预定客户
			if(carBookVo.getXs_Custom_Id()!=null && !carBookVo.getXs_Custom_Id().equals("")){
				XsCustomInfo xsCustomInfo = (XsCustomInfo)carBookDao.get("from XsCustomInfo where customId="+carBookVo.getXs_Custom_Id()+"");
				//获取成交状态 （预定状态）
				int id = baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SWA,carBookVo.getEnterpriseId());
				xsCustomInfo.setXsCustomDeal(id);
				carBookDao.merge(xsCustomInfo);
			}
			//添加流程控制记录
			XsSellFlowControl xsSellFlowControl = new XsSellFlowControl();
			xsSellFlowControl.setXsfcontrolCode(Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4);
			xsSellFlowControl.setXsfcontrolDocument(code);
			xsSellFlowControlDao.save(xsSellFlowControl);
			
			//日志
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"新增编号为["+code+"]的预订单！");
			msg.setSuccess(true);
			msg.setMsg("预订单添加成功！");
			return msg;
	}
	/**
	 *删除预订单
	 */
	@Log(systemName="销售系统", moduleName="车辆预订管理",opertype="删除")
	
	public int deleteCarBookInfor(CarBookVo carBookVo) throws Exception {
	    
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除编号为["+carBookVo.getReserve_Code()+"]的预订单！");
		List rlist1=carBookDao.createSQLQuery("select xcsi.* from xs_car_sell_info xcsi inner join xs_sell_flow_control xsfc on xsfc.xsfcontrol_car_id=xcsi.xs_car_id and xsfc.xsfcontrol_document=xcsi.sell_code where xcsi.reserve_id="+carBookVo.getReserve_Id());
		List rlist2=carBookDao.createSQLQuery(" select * from xs_sell_collections WHERE account_id ='"+carBookVo.getReserve_Code()+"'");
		if(rlist1!=null && rlist1.size()>0){//是否已销售
			return 1;
		}else if(rlist2!=null && rlist2.size()>0){//是否有预收款
			return 2;
		}else{//判断此预定单是否已销售 是否有预收款，如果都没有则将状态改为删除状态
			XsSellCarReserve xsSellCarReserve = (XsSellCarReserve)carBookDao.get("FROM XsSellCarReserve WHERE reserveCode='"+carBookVo.getReserve_Code()+"'");
			xsSellCarReserve.setDelState(1); //1 为删除状态
			carBookDao.merge(xsSellCarReserve);
			XsCustomInfo xci=customInfoDAO.get("from XsCustomInfo where customId="+xsSellCarReserve.getCustomId());
			xci.setXsCustomDeal(baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.SS,carBookVo.getEnterpriseId()));
			customInfoDAO.merge(xci);
			//删除流程控制记录
			List<XsSellFlowControl> xsSellFlowControllist = xsSellFlowControlDao.find("from XsSellFlowControl BB where BB.xsfcontrolDocument='"+carBookVo.getReserve_Code()+"'");
			for (XsSellFlowControl xsSellFlowControl2 : xsSellFlowControllist) {
				xsSellFlowControlDao.delete(xsSellFlowControl2);
			}
			return 0;
		}
	}
	
	/**
	 * 获取预订单汇总记录
	 */
	
	public Json findCarBookInfor(CarBookVo carBookVo) throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES,Contstants.COLLIGATES.DEFAULTSHOWDAY,carBookVo.getEnterpriseId()).getCiValue());
		List list = new ArrayList();
		Json json = new Json();
			//
			Integer id = baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE,Contstants.BASE_SELL.QUXIAO,carBookVo.getEnterpriseId());
			String sql ="select * from car_book A WHERE  A.xsfcontrol_code="+Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4+" and A.del_state <> 1 and A.order_state <> "+id+"";
			
			if (carBookVo.getReserve_Dete() != null
					&& !carBookVo.getReserve_Dete().equals("")) {
						sql += " and DATE(A.Reserve_Dete) >= '" +carBookVo.getReserve_Dete() + "'";
					} 
			if (carBookVo.getReserve_Dete2() != null
					&& !carBookVo.getReserve_Dete2().equals("")) {
						sql += " and DATE(A.Reserve_Dete) <= '" +carBookVo.getReserve_Dete2() + "'";
			}
			if((carBookVo.getReserve_Dete() == null
					|| carBookVo.getReserve_Dete().equals("")) 
					&& (carBookVo.getReserve_Dete2() == null
					|| carBookVo.getReserve_Dete2().equals(""))){
				sql += " and DATE(A.Reserve_Dete) BETWEEN '" + sdate + "' AND '"
				+ edate + "'";
			}
			if (carBookVo.getPredict_Take_Date() != null
					&& !carBookVo.getPredict_Take_Date().equals("")) {
				sql += " and DATE(A.Predict_Take_Date) >= '" +carBookVo.getPredict_Take_Date() + "'";
			} 
			if (carBookVo.getPredict_Take_Date2() != null
					&& !carBookVo.getPredict_Take_Date2().equals("")) {
				sql += " and DATE(A.Predict_Take_Date) <= '" +carBookVo.getPredict_Take_Date2() + "'";
			}
			if(carBookVo.getEnterpriseId()!=null && !carBookVo.getEnterpriseId().equals("")){
				sql += " and A.Enterprise_Id = "+carBookVo.getEnterpriseId()+"";
			}
			if(carBookVo.getOrder_State()!=null && !carBookVo.getOrder_State().equals("")){
				sql += " and A.Order_State = "+carBookVo.getOrder_State()+"";
			}
			if(carBookVo.getXs_Custom_Name()!=null && !carBookVo.getXs_Custom_Name().equals("")){
				sql += " and A.Custom_id = "+carBookVo.getXs_Custom_Name()+"";
			}
			if(carBookVo.getPact_Code()!=null && !carBookVo.getPact_Code().equals("")){
				sql += " and A.pact_code like '%"+StringEscapeUtils.escapeSql(carBookVo.getPact_Code().trim())+"%'";
			}
			if(carBookVo.getXs_Car_Brand()!=null && !carBookVo.getXs_Car_Brand().equals("")){
				sql += " and A.car_brand = "+carBookVo.getXs_Car_Brand()+"";
			}
			if(carBookVo.getXs_Car_Model_Id()!=null && !carBookVo.getXs_Car_Model_Id().equals("")){
				sql += " and  A.xs_model_id = "+carBookVo.getXs_Car_Model_Id()+"";
			}
			if(carBookVo.getXs_Custom_Telephone()!=null && !carBookVo.getXs_Custom_Telephone().equals("")){
				sql += " and A.Xs_Custom_Phone2  like '%"+carBookVo.getXs_Custom_Telephone()+"%'";
			}
			if(carBookVo.getLevel()!=null && !carBookVo.getLevel().equals("")){
				sql += " and A.xs_custom_hide_level = "+carBookVo.getLevel()+"";
			}//
			if(carBookVo.getDept_Name()!=null && !carBookVo.getDept_Name().equals("")){
				sql += " and A.dept_id ="+carBookVo.getDept_Name()+"";
			}
			if(carBookVo.getStf_Id()!=null && !carBookVo.getStf_Id().equals("")){
				sql += " and A.stf_Id = "+carBookVo.getStf_Id()+"";
			}
			if(carBookVo.getXs_Car_Color()!=null && !carBookVo.getXs_Car_Color().equals("")){
				sql += " and A.car_color = "+carBookVo.getXs_Car_Color()+"";
			}//
			if(carBookVo.getReserve_Code()!=null && !carBookVo.getReserve_Code().equals("")){
				sql += " and A.Reserve_Code like '%"+StringEscapeUtils.escapeSql(carBookVo.getReserve_Code().trim())+"%'";
			}
			sql += " ORDER BY A.reserve_id DESC ";
			List rlist = carBookDao.createSQLQuery(sql,carBookVo.getPage(),carBookVo.getRows());
		
			//
			Object[] obj = null;
			if(rlist!=null){
				for (int i = 0; i < rlist.size(); i++) {
					obj = (Object[])rlist.get(i);
					CarBookVo vo = new CarBookVo();
					
					if(obj[0]!=null){vo.setReserve_Code(obj[0].toString());}
					if(obj[1]!=null){vo.setReserve_Dete(obj[1].toString());}
					if(obj[2]!=null){vo.setXs_Custom_Name(obj[2].toString());}
					if(obj[3]!=null){vo.setPact_Code(obj[3].toString());}
					if(obj[4]!=null){vo.setStf_Name(obj[4].toString());}
					if(obj[5]!=null){vo.setStf_Id_PersonName(obj[5].toString());}
					if(obj[6]!=null){vo.setXs_Custom_Telephone(obj[6].toString());}
					if(obj[7]!=null){vo.setXs_Model_Name(obj[7].toString());}
					if(obj[8]!=null){vo.setXs_Car_Vin_Number(obj[8].toString());}
					if(obj[9]!=null){vo.setLevel_Name(obj[9].toString());}
					if(obj[10]!=null){vo.setPredict_Take_Date(obj[10].toString());}
					if(obj[11]!=null){vo.setPayment_Money(obj[11].toString());}
					if(obj[12]!=null){vo.setSellingprice(obj[12].toString());}
					if(obj[13]!=null){vo.setOrder_State_Name(obj[13].toString());}
					if(obj[14]!=null){vo.setLimit_Load_Num(obj[14].toString());}
					if(obj[15]!=null){vo.setCustom_Opinion(obj[15].toString());}
					if(obj[16]!=null){vo.setRemark(obj[16].toString());}
					if(obj[17]!=null){vo.setExamine_Name(obj[17].toString());}
					if(obj[18]!=null){vo.setXs_Car_Trim_Name(obj[18].toString());}
					if(obj[19]!=null){vo.setDept_Name(obj[19].toString());}
					if(obj[20]!=null){vo.setXs_Car_Color_Name(obj[20].toString());}
					if(obj[21]!=null){vo.setXs_Car_Brand_Name(obj[21].toString());}
					if(obj[22]!=null){vo.setCar_Output_Volume_Name(obj[22].toString());}
					if(obj[23]!=null){vo.setXs_Distributor_Name(obj[23].toString());}
					if(obj[24]!=null){vo.setFirst_Pay_Money(obj[24].toString());}
					if(obj[25]!=null){vo.setXs_Providebank_Name(obj[25].toString());}
					if(obj[26]!=null){vo.setLoan_Limit_Money(obj[26].toString());}
					if(obj[27]!=null){vo.setLoan_Limit_Year(obj[27].toString());}
					if(obj[28]!=null){vo.setShingle_Money(obj[28].toString());}
					if(obj[29]!=null){vo.setXs_Custom_Property_Name(obj[29].toString());}
					if(obj[30]!=null){vo.setDecorate_Money(obj[30].toString());}
					if(obj[31]!=null){vo.setXs_Custom_Id(obj[31].toString());}
					if(obj[32]!=null){vo.setXs_Car_Model_Id(obj[32].toString());}
					if(obj[33]!=null){vo.setOrder_State(obj[33].toString());}
					if(obj[34]!=null){vo.setExamine(obj[34].toString());}
					if(obj[35]!=null){vo.setXs_Car_Trim(obj[35].toString());}
					if(obj[36]!=null){vo.setXs_Car_Color(obj[36].toString());}
					if(obj[37]!=null){vo.setXs_Car_Brand(obj[37].toString());}
					if(obj[38]!=null){vo.setCar_Output_Volume(obj[38].toString());}
					if(obj[39]!=null){vo.setXs_Custom_Property(obj[39].toString());}
					if(obj[40]!=null){vo.setReserve_Id(obj[40].toString());}
					if(obj[42]!=null){vo.setXs_Car_Id(obj[42].toString());}
					if(obj[44]!=null){vo.setDept_Id(obj[44].toString());}
					if(obj[45]!=null){vo.setLevel(obj[45].toString());}
					if(obj[46]!=null){vo.setStf_Id(obj[46].toString());}
					if(obj[47]!=null){vo.setStf_Id_Person(obj[47].toString());}
					list.add(vo);
				}
			}
			json.setRows(list);
			json.setTotal(carBookDao.getCountBySQL(sql));
			return json;
		
	}
	/**
	 * 修改预定单
	 */
	@Log(systemName="销售系统", moduleName="车辆预订管理",opertype="修改")
	
	public void updateCarBook(CarBookVo carBookVo) throws Exception {
	    
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"修改编号为["+carBookVo.getReserve_Code()+"]的预订单！");
		XsSellCarReserve xsSellCarReserve = (XsSellCarReserve)carBookDao.get("from XsSellCarReserve WHERE reserveCode='"+carBookVo.getReserve_Code()+"'");
		 //int reserveId;
		 String reserveCode = carBookVo.getReserve_Code();
		 int customId = null != carBookVo.getXs_Custom_Id() && ! "".equals(carBookVo.getXs_Custom_Id()) ? Integer.parseInt(carBookVo.getXs_Custom_Id()) : 0;
		 int xsCarId = carBookVo.getXs_Car_Id()!=null && !carBookVo.getXs_Car_Id().equals("") ? Integer.parseInt(carBookVo.getXs_Car_Id()) : 0;
		 int stfIdPerson = carBookVo.getStf_Id_Person()!=null && !carBookVo.getStf_Id_Person().equals("")? Integer.parseInt(carBookVo.getStf_Id_Person()) : 0;
		 String reserveDete = carBookVo.getReserve_Dete()!=null && !carBookVo.getReserve_Dete().equals("") ? carBookVo.getReserve_Dete() : "";
		 int carBrand = carBookVo.getXs_Car_Brand()!=null && !carBookVo.getXs_Car_Brand().equals("") ? Integer.parseInt(carBookVo.getXs_Car_Brand()) : 0;
		 int carModel = carBookVo.getXs_Car_Model_Id()!=null && !carBookVo.getXs_Car_Model_Id().equals("") ? Integer.parseInt(carBookVo.getXs_Car_Model_Id()) : 0;
		 int carColor = carBookVo.getXs_Car_Color()!=null && !carBookVo.getXs_Car_Color().equals("")? Integer.parseInt(carBookVo.getXs_Car_Color()) : 0;
		 int carTrim = carBookVo.getXs_Car_Trim()!=null && !carBookVo.getXs_Car_Trim().equals("")? Integer.parseInt(carBookVo.getXs_Car_Trim()) : 0;
		 int carOutputVolume = carBookVo.getCar_Output_Volume()!=null && !carBookVo.getCar_Output_Volume().equals("") ? Integer.parseInt(carBookVo.getCar_Output_Volume()) : 0;
		 int limitLoadNum = carBookVo.getLimit_Load_Num()!=null && !carBookVo.getLimit_Load_Num().equals("") ? Integer.parseInt(carBookVo.getLimit_Load_Num()) : 0;
		 String pactCode = carBookVo.getPact_Code()!=null ? carBookVo.getPact_Code() : "";
		 int customProperty = carBookVo.getXs_Custom_Property()!=null && !carBookVo.getXs_Custom_Property().equals("") ? Integer.parseInt(carBookVo.getXs_Custom_Property()) : 0;
		 Double paymentMoney = carBookVo.getPayment_Money()!=null && !carBookVo.getPayment_Money().equals("") ? Double.parseDouble(carBookVo.getPayment_Money()) : 0.00;
		 int payWay = carBookVo.getPay_Way()!=null && !carBookVo.getPay_Way().equals("")? Integer.parseInt(carBookVo.getPay_Way()) : 0;
		 Double sellingprice = carBookVo.getSellingprice()!=null && !carBookVo.getSellingprice().equals("") ? Double.parseDouble(carBookVo.getSellingprice()) : 0.00;
		 int examine = carBookVo.getExamine()!=null && !carBookVo.getExamine().equals("") ? Integer.parseInt(carBookVo.getExamine()) : 0;
		 int orderState = carBookVo.getOrder_State()!=null && !carBookVo.getOrder_State().equals("") ? Integer.parseInt(carBookVo.getOrder_State()) : 0;
		 String predictTakeDate = carBookVo.getPredict_Take_Date()!=null && !carBookVo.getPredict_Take_Date().equals("") ? carBookVo.getPredict_Take_Date() : "";
		 int stfId = carBookVo.getStf_Id()!=null && !carBookVo.getStf_Id().equals("") ? Integer.parseInt(carBookVo.getStf_Id()) : 0;
		 String xsDistributorCode = carBookVo.getXs_Distributor_Code()!=null ? carBookVo.getXs_Distributor_Code() : "";
		 int level = carBookVo.getLevel()!=null && !carBookVo.getLevel().equals("") ? Integer.parseInt(carBookVo.getLevel()) : 0;
		 Double firstPayMoney = carBookVo.getFirst_Pay_Money()!=null && !carBookVo.getFirst_Pay_Money().equals("") ? Double.parseDouble(carBookVo.getFirst_Pay_Money()) : 0.00;
		 int loanBank = carBookVo.getLoan_Bank()!=null && !carBookVo.getLoan_Bank().equals("") ? Integer.parseInt(carBookVo.getLoan_Bank()) : 0;
		 Double loanLimitMoney = carBookVo.getLoan_Limit_Money()!=null && !carBookVo.getLoan_Limit_Money().equals("") ? Double.parseDouble(carBookVo.getLoan_Limit_Money()) : 0.00;
		 String loanLimitYear = carBookVo.getLoan_Limit_Year()!=null ? carBookVo.getLoan_Limit_Year() : "";
		 String vinCode = carBookVo.getVin_Code()!=null ? carBookVo.getVin_Code() : "";
		 String customOpinion = carBookVo.getCustom_Opinion()!=null ? carBookVo.getCustom_Opinion() : "";
		 Double shingleMoney = carBookVo.getShingle_Money()!=null && !carBookVo.getShingle_Money().equals("") ? Double.parseDouble(carBookVo.getShingle_Money()) : 0.00;
		 Double decorateMoney = carBookVo.getDecorate_Money()!=null && !carBookVo.getDecorate_Money().equals("") ? Double.parseDouble(carBookVo.getDecorate_Money()) : 0.00;
		 //int allocateState = carBookVo.getAllocate_State()!=null ? Integer.parseInt(carBookVo.getAllocate_State()) : 0;
		 int delState = carBookVo.getDel_State()!=null && !carBookVo.getDel_State().equals("") ? Integer.parseInt(carBookVo.getDel_State()) : 0;
		 String remark = carBookVo.getRemark()!=null ? carBookVo.getRemark() : "";
		
		 xsSellCarReserve.setCustomId(customId);
		 xsSellCarReserve.setXsCarId(xsCarId);
		 xsSellCarReserve.setStfIdPerson(stfIdPerson);
		 xsSellCarReserve.setReserveDete(reserveDete);
		 if(carBrand!=0){
		 XsChilddictionary carbrand= baseTagDAO.findById(carBrand);
		 xsSellCarReserve.setXsChilddictionary(carbrand);
		 }
		// xsSellCarReserve.setCarBrand(carBrand);
		 xsSellCarReserve.setCarModel(carModel);
		 if(carColor!=0){
			 XsChilddictionary carcolor= baseTagDAO.findById(carColor);
			 xsSellCarReserve.setXsChilddictionary1(carcolor);
		 }
		 //xsSellCarReserve.setCarColor(carColor);
		 if(carTrim!=0){
			 XsChilddictionary cartrim= baseTagDAO.findById(carTrim);
			 xsSellCarReserve.setXsChilddictionary2(cartrim);
		 }
		// xsSellCarReserve.setCarTrim(carTrim);
		 if(carOutputVolume!=0){
			 XsChilddictionary caroutputVolume= baseTagDAO.findById(carOutputVolume);
			 xsSellCarReserve.setXsChilddictionary3(caroutputVolume);
		 }
		 //xsSellCarReserve.setCarOutputVolume(carOutputVolume);
		 xsSellCarReserve.setLimitLoadNum(limitLoadNum);
		 xsSellCarReserve.setPactCode(pactCode);
		 if(customProperty!=0){
			 XsChilddictionary customproperty= baseTagDAO.findById(customProperty);
			 xsSellCarReserve.setXsChilddictionary4(customproperty);
		 }
		 //xsSellCarReserve.setCustomProperty(customProperty);
		 xsSellCarReserve.setPaymentMoney(paymentMoney);
		 if(payWay!=0){
			 XsChilddictionary payway= baseTagDAO.findById(payWay);
			 xsSellCarReserve.setXsChilddictionary5(payway);
		 }
		 //xsSellCarReserve.setPayWay(payWay);
		 xsSellCarReserve.setSellingprice(sellingprice);
		 if(orderState!=0){
			 XsChilddictionary orderstate= baseTagDAO.findById(orderState);
			 xsSellCarReserve.setXsChilddictionary7(orderstate);
		 }
		// xsSellCarReserve.setOrderState(orderState);
		 xsSellCarReserve.setPredictTakeDate(predictTakeDate);
		 xsSellCarReserve.setXsDistributorCode(xsDistributorCode);
		 xsSellCarReserve.setLevel(level);
		 xsSellCarReserve.setFirstPayMoney(firstPayMoney);
		 xsSellCarReserve.setLoanBank(loanBank);
		 xsSellCarReserve.setLoanLimitMoney(loanLimitMoney);
		 xsSellCarReserve.setVinCode(vinCode);
		 xsSellCarReserve.setCustomOpinion(customOpinion);
		 xsSellCarReserve.setShingleMoney(shingleMoney);
		 xsSellCarReserve.setDecorateMoney(decorateMoney);
		 //xsSellCarReserve.setAllocateState(allocateState);
		 xsSellCarReserve.setDelState(delState);
		 xsSellCarReserve.setRemark(remark);
		 carBookDao.merge(xsSellCarReserve);
		
	}
	/**
	 * 取消订单前 审核是否有预收款
	 */
	
	public Message doAduitCancelBook(CarBookVo carBookVo) throws Exception {
		//查询预收款记录是否有预收款
		XsSellCollections xsSellCollections = (XsSellCollections)carBookDao.get("from XsSellCollections WHERE accountId ='"+carBookVo.getReserve_Code()+"' ");
		Message msg = new Message();
		if(xsSellCollections!=null && xsSellCollections.getAccountCumulative()>0){
			msg.setMsg("该预订单已有预收款，请确认？(提示：若确认，系统将自动取消预定单并通知退款！)");
			msg.setSuccess(false);//
		}else{
			XsSellCarReserve xsSellCarReserve = (XsSellCarReserve)carBookDao.get(" FROM XsSellCarReserve WHERE reserveCode='"+carBookVo.getReserve_Code()+"'");
			XsChilddictionary oid = baseTagDAO.getChild(Contstants.BASE_SELL.ORDER_STATE,Contstants.BASE_SELL.QUXIAO,carBookVo.getEnterpriseId());
			xsSellCarReserve.setXsChilddictionary7(oid);
			carBookDao.merge(xsSellCarReserve);
			int customState=baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SS,carBookVo.getEnterpriseId());	
			if(xsSellCarReserve.getXsCarId()!=null&&xsSellCarReserve.getXsCarId().toString().length()>0){
				int carState=baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,carBookVo.getEnterpriseId());				
				xsSellFlowControlDao.executeSql("update xs_car_info set xs_car_sell_state="+carState+" where xs_car_id="+xsSellCarReserve.getXsCarId());
			}
			xsSellFlowControlDao.executeSql("update xs_custom_info set xs_custom_deal="+customState+" where custom_id="+xsSellCarReserve.getCustomId());
			msg.setMsg("已取消订单！");
			msg.setSuccess(true);//
		}
		return msg;
	}
	
	/**
	 * 取消预定
	 */
	@Log(systemName="销售系统", moduleName="车辆预订管理",opertype="取消预定")
	
	public void doCancelBook(CarBookVo carBookVo) throws Exception {
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"取消预定单：["+carBookVo.getReserve_Code()+"]");
		//获取预收款累计金额
		XsSellCollections xsSellCollections = (XsSellCollections)carBookDao.get("from XsSellCollections WHERE accountId ='"+carBookVo.getReserve_Code()+"' ");
		//通知退款（插入一条退款记录到退款单中）
		XsBackCarLog xsbackCarLog = new XsBackCarLog();
		//如果是预定单退款 则该编号为预定单号
		xsbackCarLog.setBackCarCode(CreateID.createId("xsBackCarLog_before", Contstants.SELL_BILLSDEPLOY.CARRESERVEREFUNDMENT));
		xsbackCarLog.setBackCarDocument(carBookVo.getReserve_Code()!=null ? carBookVo.getReserve_Code() : "");
		xsbackCarLog.setBackCarSunmoney(xsSellCollections.getAccountCumulative());
		xsbackCarLog.setBackCarMoney(0d);
		xsbackCarLog.setBackCarDate(new java.sql.Date(new java.util.Date().getTime()).toString());
		xsbackCarLog.setBackCarPerson(Integer.parseInt(SystemUser.getUser().getBasStuff().getStfId().toString()));
		xsbackCarLog.setExamine(baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,carBookVo.getEnterpriseId()));
		carBookDao.save(xsbackCarLog);
		
		XsSellCarReserve xsSellCarReserve = (XsSellCarReserve)carBookDao.get(" FROM XsSellCarReserve WHERE reserveCode='"+carBookVo.getReserve_Code()+"'");
		xsSellCarReserve.setXsChilddictionary7( baseTagDAO.getChild(Contstants.BASE_SELL.ORDER_STATE,Contstants.BASE_SELL.QUXIAO,carBookVo.getEnterpriseId()));
		carBookDao.merge(xsSellCarReserve);
		int customState=baseTagDAO.getChildId(Contstants.BASE_SELL.BASE_DEALSTATE,Contstants.BASE_SELL.SS,carBookVo.getEnterpriseId());	
		if(xsSellCarReserve.getXsCarId()!=null&&xsSellCarReserve.getXsCarId().toString().length()>0){
			int carState=baseTagDAO.getChildId(Contstants.SELLSTATE.BASE_SELLSTATE,Contstants.SELLSTATE.FORSALE,carBookVo.getEnterpriseId());				
			xsSellFlowControlDao.executeSql("update xs_car_info set xs_car_sell_state="+carState+" where xs_car_id="+xsSellCarReserve.getXsCarId());
		}
		xsSellFlowControlDao.executeSql("update xs_custom_info set xs_custom_deal="+customState+" where custom_id="+xsSellCarReserve.getCustomId());
	}
	/**
	 * 审核
	 */
	@Log(systemName="销售系统", moduleName="车辆预订管理",opertype="审核")
	
	public Msg doAudit(CarBookVo carBookVo) throws Exception {
		Msg msg = new Msg();
		XsSellCarReserve xsSellCarReserve = (XsSellCarReserve)carBookDao.get(" FROM XsSellCarReserve WHERE reserveCode='"+carBookVo.getReserve_Code()+"'");
		if(carBookVo.getExamine()!=null && carBookVo.getExamine().equals(""+baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.WEISHENHE,carBookVo.getEnterpriseId())+"")){
			xsSellCarReserve.setXsChilddictionary6(baseTagDAO.getChild(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE, carBookVo.getEnterpriseId()));
			carBookDao.doAudit(xsSellCarReserve);
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"审核预定单：["+carBookVo.getReserve_Code()+"]");
			msg.setMsg("审核预订单成功！");
			msg.setSuccess(true);
		}else if(carBookVo.getExamine()!=null && carBookVo.getExamine().equals(""+baseTagDAO.getChildId(Contstants.ADUIT.ADUIT, Contstants.ADUIT.YISHENHE,carBookVo.getEnterpriseId())+"")){
			msg.setMsg("给预订单已审核，不能反审核！");
			msg.setSuccess(false);
		}
		return msg;
		
	}
	/**
	 * 根据车辆型号查找标准销售价或限价
	 * */
	
	public Double findCarTypeSellPriceOrControlPrice(CarBookVo carBookVo,Boolean flag) throws Exception {
		XsCarModel xcm=carModelDAO.get("from XsCarModel xcm where xcm.enterpriseId="+carBookVo.getEnterpriseId()+" and  xcm.modelId="+carBookVo.getCarModerId());
		if(!(flag!=null&&flag==true)){
			return xcm.getModelSalesPrice();			
		}else{
			if(xcm.getModelThreeSalesLimitPrice()!=null&&xcm.getModelThreeSalesLimitPrice()>=0){
				return xcm.getModelThreeSalesLimitPrice();
			}
			if(xcm.getModelTwoSalesLimitPrice()!=null&&xcm.getModelTwoSalesLimitPrice()>=0){
				return xcm.getModelTwoSalesLimitPrice();
			}
			if(xcm.getModelSalesLimitPrice()!=null&&xcm.getModelSalesLimitPrice()>=0){
				return xcm.getModelSalesLimitPrice();
			}
		}
		return 0d;
	}
	//判读是否已审核
	public Boolean isRefundment(CarBookVo carBookVo) throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,carBookVo.getEnterpriseId());
		
		if(Integer.parseInt(carBookVo.getExamine())==examine)
			return true;
		return false;
	}


}
