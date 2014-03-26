package com.syuesoft.sell.sellwork.serviceimpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.base.service.CustomInfoService;
import com.syuesoft.sell.base.vo.CustomInfoVo;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsSellCustomSalesman;
import com.syuesoft.sell.model.XsSellCustomSalesmanDetail;
import com.syuesoft.sell.model.XsSellReceiverelation;
import com.syuesoft.sell.model.XsSellReceiverelationId;
import com.syuesoft.sell.sellwork.dao.BackRegisterDao;
import com.syuesoft.sell.sellwork.service.BackRegisterService;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.SystemUser;

@Service("backRegisterService")
public class BackRegisterServiceImpl extends BaseLogServiceImpl implements BackRegisterService {

	@Resource
	private BackRegisterDao backRegisterDao;
	@Resource
	private BaseTagDAO baseTagDAO;
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	
	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");
	
	/**
	 * 保存登记表明细信息
	 */
	@Log(systemName="销售系统", moduleName="來店客流登记",opertype="新增")
	
	public void saveDetailAll(BackRegisterVo backRegisterVo) throws Exception {
		final List<BackRegisterVo> arylist = JSON.parseArray(backRegisterVo
				.getInserted(), BackRegisterVo.class);
		if (arylist != null && arylist.size() > 0) {
			XsSellCustomSalesman xsSellCustomSalesman = new XsSellCustomSalesman();
			for (BackRegisterVo vo : arylist) {
				XsSellCustomSalesmanDetail xsSellCustomSalesmanDetail = new XsSellCustomSalesmanDetail();
				xsSellCustomSalesmanDetail
						.setCarModel(vo.getCar_Model() != null
								&& !vo.getCar_Model().equals("") ? Integer
								.parseInt(vo.getCar_Model()) : null);
				xsSellCustomSalesmanDetail
				.setCarBrand(vo.getCar_Brand() != null
						&& !vo.getCar_Brand().equals("") ? Integer
								.parseInt(vo.getCar_Brand()) : null);
				xsSellCustomSalesmanDetail
						.setCustomLevel(vo.getCustom_Level() != null
								&& !vo.getCustom_Level().equals("") ? Integer
								.parseInt(vo.getCustom_Level()) : null);
				xsSellCustomSalesmanDetail.setCustomName(vo.getCustom_Name());
				xsSellCustomSalesmanDetail
						.setFirstOrmany(vo.getFirst_Ormany() != null
								&& !vo.getFirst_Ormany().equals("") ? Integer
								.parseInt(vo.getFirst_Ormany()) : 0);
				xsSellCustomSalesmanDetail
						.setIstestdrive(vo.getIstestdrive() != null
								&& !vo.getIstestdrive().equals("") ? Integer
								.parseInt(vo.getIstestdrive()) : null);
				xsSellCustomSalesmanDetail.setManNum(vo.getMan_Num() != null
						&& !vo.getMan_Num().equals("") ? Integer.parseInt(vo
						.getMan_Num()) : null);
				xsSellCustomSalesmanDetail
						.setWomanNum(vo.getWoman_Num() != null
								&& !vo.getWoman_Num().equals("") ? Integer
								.parseInt(vo.getWoman_Num()) :null );
				xsSellCustomSalesmanDetail.setMessageChannel(vo
						.getMessage_Channel() != null
						&& !vo.getMessage_Channel().equals("") ? Integer
						.parseInt(vo.getMessage_Channel()) : null);
				xsSellCustomSalesmanDetail.setRegisterState(vo
						.getRegister_State() != null
						&& !vo.getRegister_State().equals("") ? Integer
						.parseInt(vo.getRegister_State()) : null);
				xsSellCustomSalesmanDetail.setRemark(vo.getRemark());
				xsSellCustomSalesmanDetail.setTalkContent(vo.getTalk_Content());
				xsSellCustomSalesmanDetail.setTalkWay(vo.getTalk_Way() != null
						&& !vo.getTalk_Way().equals("") ? Integer.parseInt(vo
						.getTalk_Way()) : null);
				xsSellCustomSalesmanDetail.setStfId(vo.getStf_Id() != null
						&& !vo.getStf_Id().equals("") ? Integer.parseInt(vo
						.getStf_Id()) : null);
				xsSellCustomSalesmanDetail.setTelephone(vo.getTelephone());
				SimpleDateFormat simp = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				xsSellCustomSalesmanDetail.setInDate(simp.parse(vo
						.getRegister_Date()));
				xsSellCustomSalesmanDetail.setOutDate(vo.getExit_Date());
				xsSellCustomSalesmanDetail.setRegisterState(baseTagDAO
						.getChildId(Contstants.BASE_SELL.REGISTERSTATE,
								Contstants.BASE_SELL.RETURNRESAULT,backRegisterVo.getEnterpriseId()));// 待处理

				xsSellCustomSalesmanDetail.setTestdriveModel(vo
						.getTestdrive_Model() != null
						&& !vo.getTestdrive_Model().equals("") ? Integer
						.parseInt(vo.getTestdrive_Model()) : null);
				String did = "";

				try {
					did = CreateID.createId("XsSellCustomSalesmanDetail",
							Contstants.SELL_BILLSDEPLOY.CUSTOMSALESMANDETAIL);
				} catch (Exception e) {
					e.printStackTrace();
				}
				xsSellCustomSalesmanDetail.setXsCustomSalesmanDetailId(did);
				// 获取汇总编号
				String xsCustomSalesmanId = ServletActionContext.getRequest()
						.getSession().getAttribute("xscustomsalesmanid")
						.toString();
				xsSellCustomSalesman.setXsCustomSalesmanId(xsCustomSalesmanId);
				xsSellCustomSalesmanDetail
						.setXsSellCustomSalesman(xsSellCustomSalesman);

				backRegisterDao.save(xsSellCustomSalesmanDetail);

				setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"登记编号为["+xsSellCustomSalesmanDetail.getXsCustomSalesmanDetailId()+"]的记录！");
			}
		}
	}

	/**
	 * 保存汇总信息
	 */
	
	public void saveRecord(BackRegisterVo backRegisterVo) throws Exception {

		//保存汇总
		String xs_custom_salesman_id = CreateID.createId(
				"XsSellCustomSalesmanDetail",
				Contstants.SELL_BILLSDEPLOY.CUSTOMSALESMAN);

		ServletActionContext.getRequest().getSession().setAttribute(
				"xscustomsalesmanid", xs_custom_salesman_id);

		XsSellCustomSalesman xsSellCustomSalesman = new XsSellCustomSalesman();
		// 获取系统登名称
		if (backRegisterVo.getRegister_Date() != null
				&& !backRegisterVo.getRegister_Date().equals("")) {
			xsSellCustomSalesman.setRegisterDate(backRegisterVo
					.getRegister_Date());
		}
		if (backRegisterVo.getExit_Date() != null
				&& !backRegisterVo.getExit_Date().equals("")) {
			xsSellCustomSalesman.setExitDate(backRegisterVo.getExit_Date());
		}
		if (backRegisterVo.getRemark() != null
				&& !backRegisterVo.getRemark().equals("")) {
			xsSellCustomSalesman.setRemark(backRegisterVo.getRemark());
		}
		if (backRegisterVo.getStf_Id() != null
				&& !backRegisterVo.getStf_Id().equals("")) {
			xsSellCustomSalesman.setStfId(Integer.parseInt(backRegisterVo
					.getStf_Id()));
		}
		if (backRegisterVo.getTotal() != null
				&& !backRegisterVo.getTotal().equals("")) {
			xsSellCustomSalesman.setSumNumber(Integer.parseInt(backRegisterVo
					.getTotal()));
		}
		if (backRegisterVo.getWeather() != null
				&& !backRegisterVo.getWeather().equals("")) {
			xsSellCustomSalesman.setWeather(Integer.parseInt(backRegisterVo
					.getWeather()));
		}
		xsSellCustomSalesman.setXsCustomSalesmanId(xs_custom_salesman_id);
		//企业编号
		xsSellCustomSalesman.setEnterpriseId(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("") ? backRegisterVo.getEnterpriseId() : null);
		backRegisterDao.save(xsSellCustomSalesman);
	}

	/**
	 * 获取登记表汇总信息
	 */
	
	public Json findRecord(BackRegisterVo backRegisterVo) throws Exception {
		List list = new ArrayList();
		Json json = new Json();
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		String sql = "SELECT a.xs_custom_salesman_id,a.register_date,a.STF_ID,a.remark,a.weather,a.sum_number,b.DEPT_NAME,c.STF_NAME "
				+ "	FROM Xs_Sell_Custom_Salesman a, bas_dept b, bas_stuff c "
				+ "	WHERE a.STF_ID = c.STF_ID AND b.DEPT_ID = c.DEPT_ID ";
		
		//企业编号
		if (backRegisterVo.getEnterpriseId() != null
				&& !backRegisterVo.getEnterpriseId().equals("")) {
			sql += " and a.Enterprise_Id=" + backRegisterVo.getEnterpriseId() + "";
		}
		if (backRegisterVo.getRegister_Date() != null
				&& !backRegisterVo.getRegister_Date().equals("")) {
					sql += " and DATE(a.Register_Date) >= '" +backRegisterVo.getRegister_Date() + "'";
				} 
		if (backRegisterVo.getRegister_Date2() != null
				&& !backRegisterVo.getRegister_Date2().equals("")) {
					sql += " and DATE(a.Register_Date) <= '" +backRegisterVo.getRegister_Date2() + "'";
		}
		if((backRegisterVo.getRegister_Date() == null 
			|| backRegisterVo.getRegister_Date().equals(""))
			&& (backRegisterVo.getRegister_Date2() == null 
			|| backRegisterVo.getRegister_Date2().equals(""))
			){
			sql += " and DATE(a.Register_Date) BETWEEN '" + sdate + "' AND '"
			+ edate + "'";
		}
		if (backRegisterVo.getStf_Id() != null
				&& !backRegisterVo.getStf_Id().equals("")) {
			sql += " and a.stf_Id=" + backRegisterVo.getStf_Id() + "";
		}
		sql += " order by a.xs_custom_salesman_id desc";

		try {
			List rlist = backRegisterDao.findRecord(backRegisterVo, sql);
			if (rlist != null && rlist.size() > 0) {
				Object[] obj = null;
				for (int i = 0; i < rlist.size(); i++) {
					obj = (Object[]) rlist.get(i);
					BackRegisterVo vo = new BackRegisterVo();
					if (obj[0] != null) {
						vo.setXs_Custom_Salesman_Id(obj[0].toString());
					}
					if (obj[1] != null) {
						vo.setRegister_Date(obj[1].toString());
					}
					if (obj[2] != null) {
						vo.setStf_Id(obj[2].toString());
					}
					if (obj[3] != null) {
						vo.setRemark(obj[3].toString());
					}
					if (obj[4] != null) {
						vo.setWeather(obj[4].toString());
					}
					if (obj[5] != null) {
						vo.setTotal(obj[5].toString());
					}
					if (obj[6] != null) {
						vo.setDept_Name(obj[6].toString());
					}
					if (obj[7] != null) {
						vo.setStf_Name(obj[7].toString());
					}
					list.add(vo);
				}
			}
			json.setRows(list);
			json.setTotal(backRegisterDao.getCountBySQL(sql));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 通过登记表汇总编号查询明细信息
	 */
	
	public Json findRecordById(BackRegisterVo backRegisterVo) throws Exception {
		return backRegisterDao.findRecordById(backRegisterVo);
	}

	/**
	 * 删除明细信息
	 */
	@Log(systemName="销售系统", moduleName="來店客流登记",opertype="删除")
	
	public void deleteDetailInfo(BackRegisterVo backRegisterVo)
			throws Exception {
		backRegisterDao.deleteDetailInfo(backRegisterVo);
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除编号为["+backRegisterVo.getXs_Custom_Salesman_Detail_Id()+"]的登记表明细记录！");
	}

	/**
	 * 删除汇总信息
	 */
	@Log(systemName="销售系统", moduleName="來店客流登记",opertype="删除")
	
	public void deleteRecord(BackRegisterVo backRegisterVo) throws Exception {
		backRegisterDao.deleteRecord(backRegisterVo);
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除编号为["+backRegisterVo.getXs_Custom_Salesman_Id()+"]的登记表汇总记录！");
	}

	/**
	 * 保存修改后的明细信息 
	 */
	@Log(systemName="销售系统", moduleName="來店客流登记",opertype="修改")
	
	public void saveEditDetailAll(BackRegisterVo backRegisterVo)
			throws Exception {

		List<BackRegisterVo> arylist = JSON.parseArray(backRegisterVo
				.getInserted(), BackRegisterVo.class);
		for (BackRegisterVo vo : arylist) {
			XsSellCustomSalesmanDetail xsSellCustomSalesmanDetail = (XsSellCustomSalesmanDetail)backRegisterDao.get("FROM XsSellCustomSalesmanDetail WHERE xsCustomSalesmanDetailId='"+vo.getXs_Custom_Salesman_Detail_Id()+"'") ;
			xsSellCustomSalesmanDetail.setCarBrand(vo.getCar_Brand() != null
					&& !vo.getCar_Brand().equals("") ? Integer.parseInt(vo
							.getCar_Brand()) : null);
			xsSellCustomSalesmanDetail.setCarModel(vo.getCar_Model() != null
					&& !vo.getCar_Model().equals("") ? Integer.parseInt(vo
					.getCar_Model()) : null);
			xsSellCustomSalesmanDetail
					.setCustomLevel(vo.getCustom_Level() != null
							&& !vo.getCustom_Level().equals("") ? Integer
							.parseInt(vo.getCustom_Level()) : null);
			xsSellCustomSalesmanDetail.setCustomName(vo.getCustom_Name());
			xsSellCustomSalesmanDetail
					.setFirstOrmany(vo.getFirst_Ormany() != null
							&& !vo.getFirst_Ormany().equals("") ? Integer
							.parseInt(vo.getFirst_Ormany()) : 0);
			xsSellCustomSalesmanDetail
					.setIstestdrive(vo.getIstestdrive() != null
							&& !vo.getIstestdrive().equals("") ? Integer
							.parseInt(vo.getIstestdrive()) : null);
			xsSellCustomSalesmanDetail.setManNum(vo.getMan_Num() != null
					&& !vo.getMan_Num().equals("") ? Integer.parseInt(vo
					.getMan_Num()) : 0);
			xsSellCustomSalesmanDetail.setWomanNum(vo.getWoman_Num() != null
					&& !vo.getWoman_Num().equals("") ? Integer.parseInt(vo
					.getWoman_Num()) : 0);
			xsSellCustomSalesmanDetail.setMessageChannel(vo
					.getMessage_Channel() != null
					&& !vo.getMessage_Channel().equals("") ? Integer
					.parseInt(vo.getMessage_Channel()) : null);
			xsSellCustomSalesmanDetail
					.setRegisterState(vo.getRegister_State() != null
							&& !vo.getRegister_State().equals("") ? Integer
							.parseInt(vo.getRegister_State()) : null);
			xsSellCustomSalesmanDetail.setRemark(vo.getRemark());
			xsSellCustomSalesmanDetail.setTalkContent(vo.getTalk_Content());
			xsSellCustomSalesmanDetail.setTalkWay(vo.getTalk_Way() != null
					&& !vo.getTalk_Way().equals("") ? Integer.parseInt(vo
					.getTalk_Way()) : null);
			xsSellCustomSalesmanDetail.setTelephone(vo.getTelephone());
			xsSellCustomSalesmanDetail.setTestdriveModel(vo
					.getTestdrive_Model() != null
					&& !vo.getTestdrive_Model().equals("") ? Integer
					.parseInt(vo.getTestdrive_Model()) : null);
			xsSellCustomSalesmanDetail.setStfId(vo.getStf_Id() != null
					&& !vo.getStf_Id().equals("") ? Integer.parseInt(vo
					.getStf_Id()) : null);
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			xsSellCustomSalesmanDetail.setInDate(simp.parse(vo
					.getRegister_Date()));
			xsSellCustomSalesmanDetail.setOutDate(vo.getExit_Date());

			xsSellCustomSalesmanDetail.setXsCustomSalesmanDetailId(vo
					.getXs_Custom_Salesman_Detail_Id());

			XsSellCustomSalesman xsSellCustomSalesman = new XsSellCustomSalesman();
			xsSellCustomSalesman.setXsCustomSalesmanId(vo
					.getXs_Custom_Salesman_Id());
			xsSellCustomSalesmanDetail
					.setXsSellCustomSalesman(xsSellCustomSalesman);
			backRegisterDao.merge(xsSellCustomSalesmanDetail);
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"修改编号为["+backRegisterVo.getXs_Custom_Salesman_Detail_Id()+"]的登记表明细记录！");
		}

	}

	/**
	 * 保存修改后的汇总信息
	 */
	
	public void saveEditRecord(BackRegisterVo backRegisterVo) throws Exception {
		XsSellCustomSalesman xsSellCustomSalesman = (XsSellCustomSalesman)backRegisterDao.get("FROM  XsSellCustomSalesman where xsCustomSalesmanId='"+backRegisterVo.getXs_Custom_Salesman_Id()+"'");
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			xsSellCustomSalesman.setRegisterDate(backRegisterVo.getRegister_Date());
		}
		if(backRegisterVo.getExit_Date()!=null && !backRegisterVo.getExit_Date().equals("")){
			xsSellCustomSalesman.setRegisterDate(backRegisterVo.getExit_Date());
		}
		xsSellCustomSalesman.setRemark(backRegisterVo.getRemark());
		xsSellCustomSalesman.setStfId(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("") ? Integer.parseInt(backRegisterVo.getStf_Id()) : null);
		xsSellCustomSalesman.setSumNumber(backRegisterVo.getTotal()!=null && !backRegisterVo.getTotal().equals("") ? Integer.parseInt(backRegisterVo.getTotal()) : 0);
		xsSellCustomSalesman.setWeather(backRegisterVo.getWeather()!=null && !backRegisterVo.getWeather().equals("") ? Integer.parseInt(backRegisterVo.getWeather()) : null);
		xsSellCustomSalesman.setXsCustomSalesmanId(backRegisterVo.getXs_Custom_Salesman_Id());
		backRegisterDao.merge(xsSellCustomSalesman);
	}

	
	public List getBasStuff() throws Exception {
		return backRegisterDao.getBasStuff();
	}

	/**
	 * 放弃跟踪
	 */
	@Log( systemName="销售系统", moduleName="來店客流登记",opertype="放弃")
	
	public void doAbandonAttention(BackRegisterVo backRegisterVo)
			throws Exception {

		XsSellCustomSalesmanDetail xsSellCustomSalesmanDetail = (XsSellCustomSalesmanDetail) backRegisterDao
				.get("from XsSellCustomSalesmanDetail where xsCustomSalesmanDetailId='"
						+ backRegisterVo.getXs_Custom_Salesman_Detail_Id()
						+ "'");
		int xsCustomDeal = baseTagDAO.getChildId(
				Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.DS,backRegisterVo.getEnterpriseId());
		xsSellCustomSalesmanDetail.setXsCustomBargainState(xsCustomDeal);
		// 放弃跟踪原因
		xsSellCustomSalesmanDetail.setRegisterState(backRegisterVo
				.getAbandreasonid() != null
				&& !backRegisterVo.getAbandreasonid().equals("") ? Integer
				.parseInt(backRegisterVo.getAbandreasonid()) : null);
		//流失、放弃日期
		xsSellCustomSalesmanDetail.setLoseDate(new java.util.Date());
		// 修改登记表的状态 放弃客户
		backRegisterDao.merge(xsSellCustomSalesmanDetail);
		// 修改客户档案 放弃客户
		String sql = " update xs_custom_info set xs_custom_deal="
				+ xsCustomDeal
				+ " where custom_id=(select custom_id from xs_sell_receiverelation where xs_custom_salesman_detail_id='"
				+ backRegisterVo.getXs_Custom_Salesman_Detail_Id() + "')";
		backRegisterDao.createSQLQueryOutFind(sql);
		
		setContent("放弃跟踪名称为"+backRegisterVo.getCustom_Name()+"的客户！");
	}

	/**
	 * 转为潜在客户
	 */
	@SuppressWarnings("unchecked")
	@Log( systemName="销售系统", moduleName="來店客流登记",opertype="转入")
	
	public void doTurnin(BackRegisterVo backRegisterVo,
			CustomInfoService customInfoService) throws Exception {

		XsSellCustomSalesmanDetail xsSellCustomSalesmanDetail = (XsSellCustomSalesmanDetail) backRegisterDao
				.get("from XsSellCustomSalesmanDetail where xsCustomSalesmanDetailId='"
						+ backRegisterVo.getXs_Custom_Salesman_Detail_Id()
						+ "'");
		int RegisterState = baseTagDAO.getChildId(
				Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.SS,backRegisterVo.getEnterpriseId());
		xsSellCustomSalesmanDetail.setRegisterState(RegisterState); // 状态改为转入跟踪系统
		backRegisterDao.merge(xsSellCustomSalesmanDetail);
		String xsCustomName = backRegisterVo.getCustom_Name() != null
				&& !backRegisterVo.getCustom_Name().equals("") ? backRegisterVo
				.getCustom_Name() : null;
		String xsCustomTelephone = backRegisterVo.getTelephone() != null
				&& !backRegisterVo.getTelephone().equals("") ? backRegisterVo
				.getTelephone() : null;
		int xsCustomHideLevel = 0;
		if (backRegisterVo.getCustom_Level() != null
				&& !backRegisterVo.getCustom_Level().equals("")) {
			xsCustomHideLevel = Integer.parseInt(backRegisterVo
					.getCustom_Level());
		} else {
			xsCustomHideLevel = baseTagDAO.getChildId(
					Contstants.BASE_SELL.CUSTOMPROPERTY, Contstants.BASE_SELL.QITA,backRegisterVo.getEnterpriseId()); // N 其他
		}
		// 渠道 来源
		int xsCustomSource = backRegisterVo.getMessage_Channel() != null
				&& !backRegisterVo.getMessage_Channel().equals("") ? Integer
				.parseInt(backRegisterVo.getMessage_Channel()) : 0;
		String xsCustomAddress = backRegisterVo.getXs_Custom_Address() != null
				&& !backRegisterVo.getXs_Custom_Address().equals("") ? backRegisterVo
				.getXs_Custom_Address()
				: null;
		// 建档日期
		String XsCustomInputdata = backRegisterVo.getXs_Custom_Inputdata() != null
				&& !backRegisterVo.getXs_Custom_Inputdata().equals("") ? backRegisterVo
				.getXs_Custom_Inputdata()
				: null;
		// 业务员
		String stfid = backRegisterVo.getStf_Id() != null
				&& !backRegisterVo.getStf_Id().equals("") ? backRegisterVo
				.getStf_Id() : "";
		XsCustomInfo xsCustomInfo = new XsCustomInfo();
		xsCustomInfo.setStfId(Integer.parseInt(stfid));
		xsCustomInfo.setXsCustomName(xsCustomName);
		xsCustomInfo.setXsCustomTelephone(xsCustomTelephone);
		xsCustomInfo.setXsCustomHideLevel(xsCustomHideLevel);
		xsCustomInfo.setXsCustomSource(xsCustomSource);
		xsCustomInfo.setXsCustomAddress(xsCustomAddress);//
		xsCustomInfo.setEnterpriseId(backRegisterVo.getEnterpriseId());//
		// 成交状态 --->改为潜在客户
		int xsCustomDeal = baseTagDAO.getChildId(
				Contstants.BASE_SELL.BASE_DEALSTATE, Contstants.BASE_SELL.SS,backRegisterVo.getEnterpriseId());
		xsCustomInfo.setXsCustomDeal(xsCustomDeal);
		xsCustomInfo.setXsCustomInputdata(new java.sql.Date(
				new java.util.Date().getTime())
				+ "");

		List list = backRegisterDao
				.createSQLQuery("select * from xs_sell_receiverelation where xs_custom_salesman_detail_id='"
						+ backRegisterVo.getXs_Custom_Salesman_Detail_Id()
						+ "'");
		if (list == null) {
			CustomInfoVo customInfoVo = new CustomInfoVo();
			BeanUtils.copyProperties(xsCustomInfo, customInfoVo);
			Serializable serializable = customInfoService
					.addCustomInfo(customInfoVo);

			XsSellReceiverelationId xsSellReceiverelationId = new XsSellReceiverelationId();
			XsSellReceiverelation xsSellReceiverelation = new XsSellReceiverelation();
			xsCustomInfo.setCustomId(Integer.parseInt(serializable.toString()));
			xsSellReceiverelationId.setXsCustomInfo(xsCustomInfo);
			xsSellReceiverelationId.setXsCustomSalesmanDetailId(backRegisterVo
					.getXs_Custom_Salesman_Detail_Id());
			xsSellReceiverelation.setId(xsSellReceiverelationId);
			backRegisterDao.save(xsSellReceiverelation);
			setContent("将名称为"+backRegisterVo.getCustom_Name()+"的客户转为潜在客户！");
		}

	}

	/**
	 * 來店客流量登记 浏览
	 */
	
	public Json findRecordLook(BackRegisterVo backRegisterVo) throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		String sql = "SELECT * from register_browse_record A where 1 = 1 ";
	
		if (backRegisterVo.getRegister_Date() != null
				&& !backRegisterVo.getRegister_Date().equals("")) {
					sql += " and DATE(A.Register_Date) >= '" +backRegisterVo.getRegister_Date() + "'";
				} 
		if (backRegisterVo.getRegister_Date2() != null
				&& !backRegisterVo.getRegister_Date2().equals("")) {
					sql += " and DATE(A.Register_Date) <= '" +backRegisterVo.getRegister_Date2() + "'";
		}
		if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals(""))){
			sql += " and DATE(A.Register_Date) BETWEEN '" + sdate + "' AND '"
			+ edate + "'";
		}
		//企业编号
		if (backRegisterVo.getEnterpriseId() != null
				&& !backRegisterVo.getEnterpriseId().equals("")) {
			sql += " and a.Enterprise_Id=" + backRegisterVo.getEnterpriseId() + "";
		}
		if (backRegisterVo.getStf_Id() != null
				&& !backRegisterVo.getStf_Id().equals("")) {
			sql += " and a.stf_Id=" + backRegisterVo.getStf_Id() + "";
		}
		
		if (backRegisterVo.getInData() != null
				&& !backRegisterVo.getInData().equals("")) {
					sql += " and in_date >= '" +backRegisterVo.getInData()+ "'";
		}
		if (backRegisterVo.getInData2() != null
				&& !backRegisterVo.getInData2().equals("")) {
					sql += " and in_date <= '" +backRegisterVo.getInData2()+ "'";
		}
		if (backRegisterVo.getTalk_Way() != null
				&& !backRegisterVo.getTalk_Way().equals("")) {
			sql += " and talk_way=" + backRegisterVo.getTalk_Way() + "";
		}
		if (backRegisterVo.getCustom_Name() != null
				&& !backRegisterVo.getCustom_Name().equals("")) {
		sql += " and custom_name like '%" + StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())
					+ "%'";
		}
		if (backRegisterVo.getTelephone() != null				&& !backRegisterVo.getTelephone().equals("")) {
			sql += " and telephone=" + backRegisterVo.getTelephone() + "";
		}
		if (backRegisterVo.getCar_Model() != null
				&& !backRegisterVo.getCar_Model().equals("")) {
			sql += " and car_model=" + backRegisterVo.getCar_Model() + "";
		}
		sql +=" order by a.xs_custom_salesman_detail_id desc";
		List rlist = backRegisterDao.findRecordLook(sql, backRegisterVo
				.getPage(), backRegisterVo.getRows());
		List list = new ArrayList();
		if (rlist != null && rlist.size() > 0) {
			Object[] obj = null;
			for (int i = 0; i < rlist.size(); i++) {
				obj = (Object[]) rlist.get(i);
				BackRegisterVo vo = new BackRegisterVo();
				if (obj[0] != null) {
					vo.setXs_Custom_Salesman_Detail_Id(obj[0].toString());
				}
				if (obj[1] != null) {
					vo.setCustom_Name(obj[1].toString());
				}
				if (obj[2] != null) {
					vo.setTelephone(obj[2].toString());
				}
				if (obj[3] != null) {
					vo.setStf_Id(obj[3].toString());
				}
				if (obj[4] != null) {
					vo.setStf_Name(obj[4].toString());
				}
				if (obj[5] != null) {
					vo.setTalk_Content(obj[5].toString());
				}
				if (obj[6] != null) {
					vo.setCustom_Level(obj[6].toString());
				}
				if (obj[7] != null) {
					vo.setCustom_Level_Name(obj[7].toString());
				}
				if (obj[8] != null) {
					vo.setMessage_Channel(obj[8].toString());
				}
				if (obj[9] != null) {
					vo.setMessage_Channel_Name(obj[9].toString());
				}
				if (obj[10] != null) {
					vo.setRegister_State(obj[10].toString());
				}
				if (obj[11] != null) {
					vo.setRemark(obj[11].toString());
				}
				if (obj[12] != null) {
					vo.setTalk_Way(obj[12].toString());
				}
				if (obj[13] != null) {
					vo.setTalk_Way_Name(obj[13].toString());
				}
				if (obj[14] != null) {
					vo.setIstestdrive(obj[14].toString());
				}
				if (obj[15] != null) {
					vo.setTestdrive_Model(obj[15].toString());
				}
				if (obj[16] != null) {
					vo.setTestdrive_Model_Name(obj[16].toString());
				}
				if (obj[17] != null) {
					vo.setCar_Model(obj[17].toString());
				}
				if (obj[18] != null) {
					vo.setCar_Model_Name(obj[18].toString());
				}
				if (obj[19] != null) {
					vo.setXs_Custom_Salesman_Id(obj[19].toString());
				}
				if (obj[20] != null) {
					vo.setRegister_Date(obj[20].toString());
				}
				if (obj[21] != null) {
					vo.setTracing_State(obj[21].toString());
				}
				if (obj[22] != null) {
					vo.setXs_Custom_Deal(obj[22].toString());
					vo.setOperate(obj[22].toString());
				}
				if (obj[23] != null) {
					vo.setInData(obj[23].toString());
				}
					
				list.add(vo);
			}
		}
		Json json = new Json();
		json.setRows(list);
		json.setTotal(backRegisterDao.getCountBySQL(sql));
		return json;

	}

	/**
	 * 客流时间段分析 返回动态列时间段
	 */
	
	public List doTimeAssay(BackRegisterVo backRegisterVo) throws Exception {
		ResourceBundle bundleMessage = null;
		List newlist = new ArrayList();
			// 判断是通过那种时间段统计
			if (backRegisterVo.getHours() == 30) {
				bundleMessage = PropertyResourceBundle.getBundle("timesPart30");
			} else if (backRegisterVo.getHours() == 60) {
				bundleMessage = PropertyResourceBundle.getBundle("timesPart60");
			} else if (backRegisterVo.getHours() == 90) {
				bundleMessage = PropertyResourceBundle.getBundle("timesPart90");
			} else {
				bundleMessage = PropertyResourceBundle
						.getBundle("timesPart120");
			}
			Enumeration<String> keys = bundleMessage.getKeys();
			List<String> list = new ArrayList<String>();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				list.add(key);
			}
			java.util.Comparator comp = java.util.Collections.reverseOrder();
			java.util.Collections.sort(list);

			for (int i = 0; i < list.size(); i++) {
				String keyvalue = bundleMessage.getString(list.get(i));
				newlist.add(keyvalue);
			}
		return newlist;
	}
	
	/**
	 * 获取各时间段内的统计量
	 */
	
	public String getTimeAssay(BackRegisterVo backRegisterVo) throws Exception {
		SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
		//获取默认系统时间段
		String systemtime = FormatTime.yesTempTady("yyyy-MM-dd", 
				(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, 
						Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		String systemt = simp.format(simp.parse(systemtime));
		String sdate = systemt;
		String edate = simp.format(new java.sql.Date(new java.util.Date().getTime()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//调用 本类方法 获取动态列时间段
		List coloumlist = this.doTimeAssay(backRegisterVo);
		Map map = new HashMap();
		Object[] obj = null;
		StringBuffer sb = new StringBuffer("{\"rows\":[{");
			//
			StringBuffer forsql = new StringBuffer("" +
					"	SELECT DATE(sa.register_Date),COUNT(*) AS total " +
					"	FROM xs_sell_custom_salesman_detail del " +
					"	JOIN  xs_sell_custom_salesman sa " +
					"	ON del.xs_custom_salesman_id = sa.xs_custom_salesman_id " +
					"	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = del.xs_custom_salesman_detail_id" +
					"	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
					"	WHERE  1 = 1  ");
			//企业编号
			if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
				forsql.append("  AND  sa.enterprise_id= '"+backRegisterVo.getEnterpriseId()+"'");
			}
			//添加查询条件
			if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
				forsql.append("  AND  del.custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" );
			}
			if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
				forsql.append("  AND  del.Car_brand = "+backRegisterVo.getCar_Brand()+"" );
			}
			if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
				forsql.append("  AND  del.Car_Model = "+backRegisterVo.getCar_Model()+"" );
			}
			
			if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
				forsql.append("  AND  cus.stf_Id = "+backRegisterVo.getStf_Id()+"" );
			}
			if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
				forsql.append("  AND  del.Custom_Level= "+backRegisterVo.getCustom_Level()+"") ;
			}
			if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
				forsql.append("  AND  del.Message_Channel= "+backRegisterVo.getMessage_Channel()+"") ;
			}
			if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
				forsql.append("  AND  del.Talk_Way= "+backRegisterVo.getTalk_Way()+"" );
			}
			String csql = forsql.toString();
			
			if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
				forsql.append(" AND DATE(del.in_date) >= '"+backRegisterVo.getRegister_Date()+"'");
			}
			if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
				forsql.append(" AND DATE(del.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'");
			}
			if((backRegisterVo.getRegister_Date() == null
					|| backRegisterVo.getRegister_Date().equals("")) 
					&& (backRegisterVo.getRegister_Date2() == null
					|| backRegisterVo.getRegister_Date2().equals("")))
			{
				forsql.append(" AND DATE(del.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
			}
			forsql.append(" GROUP BY DATE(del.in_date)");
			
			List outsqllist = backRegisterDao.createSQLQuery(forsql.toString());
			
			String stardate = "";
			String enddate = "";
			
			if(outsqllist!=null && outsqllist.size()>0){
				Object[] objt = null;
				for (int i = 0; i < outsqllist.size(); i++) {
					objt = (Object[])outsqllist.get(i);
					
					if(coloumlist!=null && coloumlist.size()>0){
						int alltotal = 0;
						for (int j = 0; j < coloumlist.size(); j++) {
							//获取的keyvalue是时间段  在这里做键 为动态列的 field 名称 
							String timecolumn = coloumlist.get(j).toString();
							if(!timecolumn.trim().equals("09:00以前") && !timecolumn.trim().equals("18:00以后")){
							String[] time = timecolumn.trim().split("-");
							//拼接时间段作为查询条件统计该时间段内的客流量
								stardate = objt[0]+" "+time[0]+":00";
								enddate = objt[0]+" "+time[1]+":00";
								
							}else if(timecolumn.trim().equals("18:00以后")){
								stardate = objt[0]+" "+timecolumn.substring(0, 5)+":00";
								enddate = objt[0]+" 00:00:00";
							}else{
								stardate = objt[0]+" 00:00:00";
								enddate = objt[0]+" 09:00:00";
							}
							String count = "";
							sb.append("\"registerdate\":\" "+ objt[0] +" \",");
							//
							String ccsql = csql + " AND del.in_date >= '"+stardate+"' AND del.in_date <='"+enddate+"' GROUP BY DATE(del.in_date) ";
							
							List getsqllist = backRegisterDao.createSQLQuery(ccsql.toString());
							if(getsqllist!=null && getsqllist.size()>0){
								for (int k = 0; k < getsqllist.size(); k++) {
									obj = (Object[]) getsqllist.get(k);
									count = obj[1]!=null ? obj[1].toString() : "" ;
									alltotal +=  obj[1]!=null ? Integer.parseInt(obj[1].toString()) : 0;
								}
							}
							sb.append("\"count" +timecolumn.replace(':', ' ').replace('-', ' ').replaceAll(" ", "")+ "\":\" "+ count +" \",");
							
						}//for
						//添加合计
						sb.append("\"counttotal\":\" "+ alltotal +" \",");
					}//if
					String s = sb.substring(0, sb.length() - 1);
					sb = new StringBuffer(s);
					sb.append("},{");
				}
				//添加汇总
				String rs = this.addTotals(coloumlist, outsqllist, csql);
				sb.append(rs);
			}
			sb.append("}],\"total\":"
					+ Integer.MAX_VALUE
					+ "}");
			//
		return sb.toString();
	}
	
	//汇总
	public String addTotals(List coloumlist,List outsqllist, String csql)throws Exception{
		StringBuffer sb = new StringBuffer("\"registerdate\":\"汇总\",");
		if(coloumlist!=null && coloumlist.size()>0){
			int lastrowstotal = 0;
			for (int j = 0; j < coloumlist.size(); j++) {
				Object[] objt = null; 
				String stardate = "";
				String enddate = "";
				int rowstotal = 0;
				for (int i = 0; i < outsqllist.size(); i++) {
					objt = (Object[])outsqllist.get(i);
					//#######################
					String timecolumn = coloumlist.get(j).toString();
					if(!timecolumn.trim().equals("09:00以前") && !timecolumn.trim().equals("18:00以后")){
					String[] time = timecolumn.trim().split("-");
					//拼接时间段作为查询条件统计该时间段内的客流量
						stardate = objt[0]+" "+time[0]+":00";
						enddate = objt[0]+" "+time[1]+":00";
						
					}else if(timecolumn.trim().equals("18:00以后")){
						stardate = objt[0]+" "+timecolumn.substring(0, 5)+":00";
						enddate = objt[0]+" 00:00:00";
					}else{
						stardate = objt[0]+" 00:00:00";
						enddate = objt[0]+" 09:00:00";
					}
					//
					String ccsql = csql + " AND del.in_date >= '"+stardate+"' AND del.in_date <='"+enddate+"' GROUP BY DATE(del.in_date) ";
					
					List getsqllist = backRegisterDao.createSQLQuery(ccsql.toString());
					if(getsqllist!=null && getsqllist.size()>0){
						Object[] obj = null;
						for (int k = 0; k < getsqllist.size(); k++) {
							obj = (Object[]) getsqllist.get(k);
							rowstotal +=  obj[1]!=null ? Integer.parseInt(obj[1].toString()) : 0;
							lastrowstotal += obj[1]!=null ? Integer.parseInt(obj[1].toString()) : 0;
						}
					}
					sb.append("\"count" +timecolumn.replace(':', ' ').replace('-', ' ').replaceAll(" ", "")+ "\":\" "+rowstotal+" \",");
					}	
				}//for
				sb.append("\"counttotal\":\" "+lastrowstotal+" \"");
			
			}//if
		return sb.toString();
		}

	/**
	 * 客流业务员分析  返回业务员名称 作为列
	 */
	
	public List doRegisterWorker(BackRegisterVo backRegisterVo)
			throws Exception {
		List list = new ArrayList();
		String slist = getRegisterWorker(backRegisterVo);
		if(slist!=null&&!slist.equals("")){
			String[] s = slist.split(",");
			for (int i = 0; i < s.length; i++) {
				list.add(s[i]);
			}
		}
		
		return  list;
	}

	/**客流业务员分析
	 * 获取业务员分析统计结果的json字符串
	 */
	
	public String getRegisterWorker(BackRegisterVo backRegisterVo)
			throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//企业编号
		StringBuilder condition = new StringBuilder("");
		StringBuilder jsonstrs = new StringBuilder("");
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition.append(" AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ");
		}
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			condition.append(" AND DATE(A.in_date) >= '"+backRegisterVo.getRegister_Date()+"'");
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition.append(" AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'");
		}
		if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition.append(" AND DATE(A.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition.append("  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" );
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition.append("  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" );
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition.append("  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" );
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition.append("  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" );
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition.append("  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" );
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition.append("  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" );
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition.append("  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" );
		}
		
		
		StringBuilder jsonstr =  new StringBuilder("{");
		//
		StringBuilder dsql = new StringBuilder( "SELECT A.STF_ID," );
		dsql.append( "	BF.STF_NAME " );
		dsql.append( "	FROM xs_sell_custom_salesman_detail A " );
		dsql.append( "	JOIN bas_stuff BF ON A.STF_ID = BF.STF_ID " );
		dsql.append( "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id " );
		dsql.append( "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id " );
		dsql.append( "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id " );
		dsql.append( "	JOIN  bas_stuff_job bjb ON bf.STF_ID = bjb.STF_ID ");
		dsql.append( "	JOIN bas_job_property bjp ON bjb.JOB_PRO_ID = bjp.JOB_PRO_ID AND bjp.JOB_PRO_ID=12 ");
		dsql.append( "	WHERE 1 = 1 " );
		dsql.append(condition);
		dsql.append(" GROUP BY A.STF_ID ");
		
		StringBuilder namesql = new StringBuilder("	SELECT ");
		namesql.append(" DATE(A.in_date) ");
		namesql.append(" FROM xs_sell_custom_salesman_detail A ");
		namesql.append(" JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id ");
		namesql.append(" LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id ");
		namesql.append(" LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id ");
		namesql.append(" WHERE 1 = 1 ");
		namesql.append(condition);
		namesql.append(" GROUP BY DATE(A.in_date) ");
		
		List<Object[]> dlist = backRegisterDao.createSQLQuery(dsql.toString());
		//如果是查询列则直接返回列名称
		if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
			String strlist = "";
			if(dlist!=null && dlist.size()>0){
				for (Object[] bj : dlist) {
					
					strlist += ","+bj[0]+"#"+bj[1];
				}
				strlist = strlist.substring(1);
			}
			return strlist;
		}
		List namelist = backRegisterDao.createSQLQuery(namesql.toString());
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				String objdate  = namelist.get(i).toString();
				jsonstr.append("\"registerdate\":\""+objdate+"\"");
				if(dlist!=null && dlist.size()>0){
					int sums  = 0; 
					for (Object[] objs : dlist) {
						StringBuilder sql = new StringBuilder("	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A "); 
						sql.append("	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" );
						sql.append(" 	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" );
						sql.append(" 	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" );
						sql.append("	WHERE 1 = 1" );
						sql.append("	AND DATE(A.in_date)='"+objdate+"' " );
						sql.append("	AND A.stf_id="+objs[0]+" " );
							
						String count = backRegisterDao.createSQLQuery(sql.toString()).get(0)!=null ? backRegisterDao.createSQLQuery(sql.toString()).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr.append(",\"stfname"+objs[0].toString().replaceAll(" ", "")+"\":\""+counts+"\"");
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr.append(",\"sums\":\""+sums+"\"");
					jsonstr.append(  "},{" );
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr.append("\"registerdate\":\"汇总\"");
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							String objdate  = namelist.get(i).toString();
							StringBuilder sql = new StringBuilder("	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A ");
							sql.append("	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id");
							sql.append("	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id");
							sql.append("	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id");
							sql.append("	WHERE 1 = 1");
							sql.append("	AND DATE(A.in_date)='"+objdate+"'");
							sql.append("	AND A.stf_id="+objs[0]+"");
							String count = backRegisterDao.createSQLQuery(sql.toString()).get(0)!=null ? backRegisterDao.createSQLQuery(sql.toString()).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr.append(",\"stfname"+objs[0].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"");
					}// for
					//最后一列汇总
					jsonstr.append(",\"sums\":\""+sumstatal+"\"");
				}
				jsonstr.append( "},{" ); 
				jsonstrs.append(jsonstr.toString().substring(0, jsonstr.toString().length()-2));
			//汇总###################################
		}
		StringBuilder  newjsonstr = new StringBuilder( "{\"rows\":[");
		newjsonstr.append(jsonstrs); 
		String news = newjsonstr.append("],\"total\":"+Integer.MAX_VALUE+"}").toString(); 
		return news;
	
	}
	/**
	 * 客流车型号分析  返回型号名称 作为列
	 */
	
	public List doRegisterCarmodel(BackRegisterVo backRegisterVo)
	throws Exception {
		List list = new ArrayList();
		String slist = getRegisterCarmodel(backRegisterVo);
		String[] s = slist.split(",");
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return  list;
	}
	
	/**
	 * 客流车型分析 统计结果的json字符串
	 */
	
	public String getRegisterCarmodel(BackRegisterVo backRegisterVo)
	throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//企业编号
		StringBuilder condition = new StringBuilder("");
		
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			condition.append(" AND DATE(a.in_date) >= '"+backRegisterVo.getRegister_Date()+"'");
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition.append(" AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'");
		}
		if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition.append(" AND DATE(A.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'");
		}
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition.append(" AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ");
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition.append("  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" );
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition.append("  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" );
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition.append("  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" );
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition.append("  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" );
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition.append( "  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" );
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition.append("  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" );
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition.append("  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" );
		}
		StringBuilder  newjsonstr = new StringBuilder("{\"rows\":[");
		StringBuilder jsonstr = new StringBuilder("{");
		StringBuilder last = new StringBuilder("");
		
		//
		StringBuilder dsql = new StringBuilder( "SELECT A.car_model,b.xs_model_name ");
		dsql.append( "	FROM xs_sell_custom_salesman_detail A");
		dsql.append( "	JOIN xs_car_model B ON A.car_model = B.xs_model_id");
		dsql.append( "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id");
		dsql.append( "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id");
		dsql.append( "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id");
		dsql.append( "	WHERE 1 = 1");
		dsql.append( condition);
		dsql.append( " GROUP BY a.car_model ");
		//查询等级名称
		StringBuilder namesql = new StringBuilder("	SELECT ");
		namesql.append("	DATE(A.in_date)");
		namesql.append("	FROM xs_sell_custom_salesman_detail A ");
		namesql.append("	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id ");
		namesql.append("	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id ");
		namesql.append("	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id ");
		namesql.append("	WHERE 1 = 1 ");
		namesql.append(condition);
		namesql.append(" GROUP BY DATE(A.in_date) ");
			List dlist = backRegisterDao.createSQLQuery(dsql.toString());
			//如果是查询列则直接返回列名称
			if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
				Object[] bj = null;
				String strlist = "";
				if(dlist!=null){
					for (int k = 0; k < dlist.size(); k++) {
						bj = (Object[])dlist.get(k);
						strlist += ","+bj[1];
					}
					strlist = strlist.substring(1);
				}
				return strlist;
			}
			
			List namelist = backRegisterDao.createSQLQuery(namesql.toString());
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				String objdate  = namelist.get(i).toString();
				jsonstr.append("\"registerdate\":\""+objdate+"\"");
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sums  = 0; 
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						StringBuilder sql = new StringBuilder( "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " );
						sql.append("	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" );
						sql.append("	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" );
						sql.append("	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" );
						sql.append("	WHERE 1 = 1 " );
						sql.append("	AND DATE(A.in_date)='"+objdate+"' " );
						sql.append("	AND A.car_model="+objs[0]+" " );
						String count = backRegisterDao.createSQLQuery(sql.toString()).get(0)!=null ? backRegisterDao.createSQLQuery(sql.toString()).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr.append(",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+counts+"\"");
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr.append(",\"sums\":\""+sums+"\"");
					jsonstr.append("},{");
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr.append("\"registerdate\":\"汇总\"");
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							String objdate  = namelist.get(i).toString();
							StringBuilder sql = new StringBuilder("	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A ");
							sql.append("	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id");
							sql.append("	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id");
							sql.append("	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id");
							sql.append("	WHERE 1 = 1");
							sql.append("	AND DATE(A.in_date)='"+objdate+"'");
							sql.append("	AND A.car_model="+objs[0]+"");
					
							String count = backRegisterDao.createSQLQuery(sql.toString()).get(0)!=null ? backRegisterDao.createSQLQuery(sql.toString()).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr.append(",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"");
					}// for
					//最后一列汇总
					jsonstr.append(",\"sums\":\""+sumstatal+"\"");
				}
				jsonstr.append( "},{");
			
			//汇总###################################
			newjsonstr.append(jsonstr); 
			last.append(newjsonstr.substring(0, newjsonstr.length()-2));
		}
		last.append("],\"total\":"+Integer.MAX_VALUE+"}"); 
		return last.toString();
			
	
	}
	/**
	 * 接待员车型号分析  返回型号名称 作为列
	 */
	
	public List doRegisterUserCarmodel(BackRegisterVo backRegisterVo)
	throws Exception {
//		//
//		String sql = "SELECT b.xs_model_name FROM xs_sell_custom_salesman_detail a, xs_car_model b, xs_sell_custom_salesman C WHERE  A.xs_custom_salesman_id = C.xs_custom_salesman_id AND a.car_model = b.xs_model_id ";
//		//企业编号
//		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
//			sql+=" AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ";
//		}
//		sql += " GROUP BY a.car_model";
//		List list = new ArrayList();
//		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
//			list = backRegisterDao.createSQLQuery(sql);
//		}
		List list = new ArrayList();
		String slist = getRegisterUserCarmodel(backRegisterVo);
		String[] s = slist.split(",");
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return  list;
	}
	
	/**
	 * 接待员车型分析 统计结果的json字符串
	 */
	
	public String getRegisterUserCarmodel(BackRegisterVo backRegisterVo)
	throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//企业编号
		String condition = "";
		
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			condition+= " AND DATE(A.in_date) >= '"+backRegisterVo.getRegister_Date()+"'";
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition+=" AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'";
		}
		if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition+=" AND DATE(A.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'";
		}
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition += " AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ";
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition += "  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" ;
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition += "  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" ;
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition += "  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" ;
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition += "  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" ;
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition += "  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" ;
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition += "  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" ;
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition += "  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" ;
		}
		String returnstr = "";
		String  newjsonstr = "{\"rows\":[";
		String jsonstr = "{";
		//
		String dsql =	 "	SELECT A.car_model,b.xs_model_name " +
					 	 "	FROM xs_sell_custom_salesman_detail A" +
					 	 "	JOIN xs_car_model B ON A.car_model = B.xs_model_id" +
					 	 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
					 	 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
					 	 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
					 	 "	WHERE 1 = 1   "+condition+"  GROUP BY a.car_model" ;
		
		//查询等级名称
		String namesql = "	SELECT " +
						 "	A.stf_id," +
						 "	bf.stf_name" +
						 "	FROM xs_sell_custom_salesman_detail A" +
						 " 	LEFT JOIN bas_stuff bf ON  bf.stf_id = A.stf_id" +
						 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
						 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
						 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
						 "	WHERE 1 = 1  "+condition+" GROUP BY A.stf_id";
			//
			List dlist = backRegisterDao.createSQLQuery(dsql);
			//如果是查询列则直接返回列名称
			if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
				Object[] bj = null;
				String strlist = "";
				if(dlist!=null && dlist.size()>0){
					for (int k = 0; k < dlist.size(); k++) {
						bj = (Object[])dlist.get(k);
						strlist += ","+bj[1];
					}
					strlist = strlist.substring(1);
				}
				return strlist;
			}
			
			List namelist = backRegisterDao.createSQLQuery(namesql);
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				Object[] obj  = (Object[])namelist.get(i);
				jsonstr += "\"stfname\":\""+obj[1]+"\"";
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sums  = 0; 
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
									 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
									 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
									 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
									 "	WHERE 1 = 1 " +
									 "	AND A.stf_id="+obj[0]+"  " +
									 "	AND A.car_model="+objs[0]+" ";
							
						String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+counts+"\"";
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr += ",\"sums\":\""+sums+"\"";
					jsonstr = jsonstr + "},{";
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr += "\"stfname\":\"汇总\"";
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							Object[] obj  = (Object[])namelist.get(i);
							String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
							 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
							 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
							 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
							 "	WHERE 1 = 1 " +
							 "	AND A.stf_id="+obj[0]+"  " +
							 "	AND A.car_model="+objs[0]+" ";
					
							String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"";
					}// for
					//最后一列汇总
					jsonstr += ",\"sums\":\""+sumstatal+"\"";
				}
				jsonstr = jsonstr + "},{";
			
			//汇总###################################
			newjsonstr += jsonstr; 
			newjsonstr = newjsonstr.substring(0, newjsonstr.length()-2);
		}
		String news = newjsonstr+"],\"total\":"+Integer.MAX_VALUE+"}"; 
		return news;
			
	}
	/**
	 * 客流车级别分析  获取车型号
	 */
	
	public List doRegisterCarLevel(BackRegisterVo backRegisterVo)
			throws Exception {
		List list = new ArrayList();
		String slist = getRegisterCarLevel(backRegisterVo);
		String[] s = slist.split(",");
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return  list;

	}
	/**
	 * 客流车级别分析  统计结果的json字符串
	 */
	
	public String getRegisterCarLevel(BackRegisterVo backRegisterVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet
				(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//企业编号
		String condition = "";
		//String[] registerdate = null;
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			condition+= " AND DATE(a.in_date) >= '"+backRegisterVo.getRegister_Date()+"'";
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition+=" AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'";
		}if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition+=" AND DATE(A.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'";
		}
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition += " AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ";
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition += "  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" ;
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition += "  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" ;
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition += "  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" ;
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition += "  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" ;
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition += "  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" ;
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition += "  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" ;
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition += "  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" ;
		}
		String returnstr = "";
		String  newjsonstr = "{\"rows\":[";
		String jsonstr = "{";
		
		//
		String dsql =	 "	SELECT A.car_model,b.xs_model_name " +
					 	 "	FROM xs_sell_custom_salesman_detail A" +
					 	 "	JOIN xs_car_model B ON A.car_model = B.xs_model_id" +
					 	 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
					 	 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
					 	 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
					 	 "	WHERE 1 = 1   "+condition+"  GROUP BY a.car_model" ;
		
		//查询等级名称
		String namesql = "	SELECT A.custom_level,B.xs_leva_name " +
						 "	FROM xs_sell_custom_salesman_detail A" +
						 " 	JOIN xs_custom_leva B ON  B.xs_leva_id = A.custom_level" +
						 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
						 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
						 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
						 "	WHERE 1 = 1  "+condition+" GROUP BY A.custom_level";
		//
			List dlist = backRegisterDao.createSQLQuery(dsql);
			//如果是查询列则直接返回列名称
			if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
				Object[] bj = null;
				String strlist = "";
				if(dlist!=null && dlist.size()>0){
					for (int k = 0; k < dlist.size(); k++) {
						bj = (Object[])dlist.get(k);
						strlist += ","+bj[1];
					}
					strlist = strlist.substring(1);
				}
				return strlist;
			}
			
			List namelist = backRegisterDao.createSQLQuery(namesql);
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				Object[] obj  = (Object[])namelist.get(i);
				jsonstr += "\"leva_name\":\""+obj[1]+"\"";
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sums  = 0; 
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
									 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
									 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
									 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
									 "	WHERE 1 = 1 " +
									 "	AND A.custom_level="+obj[0]+"  " +
									 "	AND A.car_model="+objs[0]+" ";
							
						String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+counts+"\"";
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr += ",\"sums\":\""+sums+"\"";
					jsonstr = jsonstr + "},{";
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr += "\"leva_name\":\"汇总\"";
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							Object[] obj  = (Object[])namelist.get(i);
							String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
							 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
							 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
							 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
							 "	WHERE 1 = 1 " +
							 "	AND A.custom_level="+obj[0]+"  " +
							 "	AND A.car_model="+objs[0]+" ";
					
							String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"";
					}// for
					//最后一列汇总
					jsonstr += ",\"sums\":\""+sumstatal+"\"";
				}
				jsonstr = jsonstr + "},{";
			
			//汇总###################################
			newjsonstr += jsonstr; 
			newjsonstr = newjsonstr.substring(0, newjsonstr.length()-2);
		}
		String news = newjsonstr+"],\"total\":"+Integer.MAX_VALUE+"}"; 
		return news;
			
	}

	/**
	 * 客户来源分析动态列获取
	 */
	
	public List doRegisterCustomSource(BackRegisterVo backRegisterVo)
			throws Exception {
		List list = new ArrayList();
		String slist = getRegisterCustomSource(backRegisterVo);
		String[] s = slist.split(",");
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return  list;
	}
	/**
	 * 客户来源分析
	 */
	
	public String getRegisterCustomSource(BackRegisterVo backRegisterVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet
				(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//查询条件
		//企业编号
		String condition = "";
		//String[] registerdate = null;
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			condition+= " AND DATE(a.in_date) >= '"+backRegisterVo.getRegister_Date()+"'";
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition+=" AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'";
		}if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition+=" AND DATE(A.in_date) BETWEEN '"+sdate+"' AND '"+edate+"'";
		}
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition += " AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ";
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition += "  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" ;
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition += "  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" ;
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition += "  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" ;
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition += "  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" ;
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition += "  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" ;
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition += "  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" ;
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition += "  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" ;
		}
		String returnstr = "";
		String  newjsonstr = "{\"rows\":[";
		String jsonstr = "{";
		
		//
		String dsql =	 "	SELECT A.car_model,b.xs_model_name " +
					 	 "	FROM xs_sell_custom_salesman_detail A" +
					 	 "	JOIN xs_car_model B ON A.car_model = B.xs_model_id" +
					 	 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
					 	 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
					 	 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
					 	 "	WHERE 1 = 1   "+condition+"  GROUP BY a.car_model" ;
		
		//查询等级名称
		String namesql = "	SELECT " +
						 "	A.message_channel," +
						 "	(SELECT datavalue FROM xs_childdictionary CH WHERE CH.child_id =A.message_channel) AS channel " +
						 "	FROM xs_sell_custom_salesman_detail A" +
						 " 	JOIN xs_custom_leva B ON  B.xs_leva_id = A.custom_level" +
						 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
						 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
						 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
						 "	WHERE 1 = 1  "+condition+" GROUP BY A.message_channel";
		
		//
			List dlist = backRegisterDao.createSQLQuery(dsql);
			//如果是查询列则直接返回列名称
			if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
				Object[] bj = null;
				String strlist = "";
				if(dlist!=null && dlist.size()>0){
					for (int k = 0; k < dlist.size(); k++) {
						bj = (Object[])dlist.get(k);
						strlist += ","+bj[1];
					}
					strlist = strlist.substring(1);
				}
				return strlist;
			}
			List namelist = backRegisterDao.createSQLQuery(namesql);
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				Object[] obj  = (Object[])namelist.get(i);
				jsonstr += "\"messagechannel\":\""+obj[1]+"\"";
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sums  = 0; 
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
									 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
									 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
									 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
									 "	WHERE 1 = 1 " +
									 "	AND A.message_channel="+obj[0]+"  " +
									 "	AND A.car_model="+objs[0]+" ";
							
						String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+counts+"\"";
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr += ",\"sums\":\""+sums+"\"";
					jsonstr = jsonstr + "},{";
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr += "\"messagechannel\":\"汇总\"";
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							Object[] obj  = (Object[])namelist.get(i);
							String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
							 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
							 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
							 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
							 "	WHERE 1 = 1 " +
							 "	AND A.message_channel="+obj[0]+"  " +
							 "	AND A.car_model="+objs[0]+" ";
					
							String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr += ",\"model"+objs[1].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"";
					}// for
					//最后一列汇总
					jsonstr += ",\"sums\":\""+sumstatal+"\"";
				}
				jsonstr = jsonstr + "},{";
			
			//汇总###################################
			newjsonstr += jsonstr; 
			newjsonstr = newjsonstr.substring(0, newjsonstr.length()-2);
		}
		String news = newjsonstr+"],\"total\":"+Integer.MAX_VALUE+"}"; 
		return news;
			
	}
	
	/**
	 * 接待员车品牌分析
	 */
	
	public List doRegisterCarBrand(BackRegisterVo backRegisterVo)
			throws Exception {
		List list = new ArrayList();
		String slist = getRegisterCarBrand(backRegisterVo);
		String[] s = slist.split(",");
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		return  list;
	}
	
	/**
	 * 接待员车品牌分析
	 */
	
	public String getRegisterCarBrand(BackRegisterVo backRegisterVo)
			throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet
				(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,backRegisterVo.getEnterpriseId()).getCiValue()));
		Calendar can = Calendar.getInstance();
		List list = new ArrayList();
		//查询条件
		//企业编号
		String condition = "";
		//String[] registerdate = null;
		if(backRegisterVo.getRegister_Date()!=null && !backRegisterVo.getRegister_Date().equals("")){
			//获取登记日期
			condition += " AND DATE(A.in_date) >= '"+backRegisterVo.getRegister_Date()+"'";
		}
		if(backRegisterVo.getRegister_Date2()!=null && !backRegisterVo.getRegister_Date2().equals("")){
			condition += " AND DATE(A.in_date) <= '"+backRegisterVo.getRegister_Date2()+"'";

		}
		if((backRegisterVo.getRegister_Date() == null
				|| backRegisterVo.getRegister_Date().equals("")) 
				&& (backRegisterVo.getRegister_Date2() == null
				|| backRegisterVo.getRegister_Date2().equals("")))
		{
			condition += " AND DATE(A.in_date) between '"+sdate+"' and '"+edate+"'";
		}
		if(backRegisterVo.getEnterpriseId()!=null && !backRegisterVo.getEnterpriseId().equals("")){
			condition += " AND C.enterprise_id ="+backRegisterVo.getEnterpriseId()+"  ";
		}
		if(backRegisterVo.getCustom_Name()!=null && !backRegisterVo.getCustom_Name().equals("")){
			condition += "  and  cus.xs_custom_name like '%"+StringEscapeUtils.escapeSql(backRegisterVo.getCustom_Name().trim())+"%'" ;
		}
		if(backRegisterVo.getCar_Model()!=null && !backRegisterVo.getCar_Model().equals("")){
			condition += "  and  A.Car_Model = "+backRegisterVo.getCar_Model()+"" ;
		}
		if(backRegisterVo.getCar_Brand()!=null && !backRegisterVo.getCar_Brand().equals("")){
			condition += "  and  A.Car_brand = "+backRegisterVo.getCar_Brand()+"" ;
		}
		if(backRegisterVo.getStf_Id()!=null && !backRegisterVo.getStf_Id().equals("")){
			condition += "  and  A.stf_Id = "+backRegisterVo.getStf_Id()+"" ;
		}
		if(backRegisterVo.getCustom_Level()!=null && !backRegisterVo.getCustom_Level().equals("")){
			condition += "  and  A.custom_level= "+backRegisterVo.getCustom_Level()+"" ;
		}
		if(backRegisterVo.getMessage_Channel()!=null && !backRegisterVo.getMessage_Channel().equals("")){
			condition += "  and  A.Message_Channel= "+backRegisterVo.getMessage_Channel()+"" ;
		}
		if(backRegisterVo.getTalk_Way()!=null && !backRegisterVo.getTalk_Way().equals("")){
			condition += "  and  A.Talk_Way= "+backRegisterVo.getTalk_Way()+"" ;
		}
		String returnstr = "";
		String  newjsonstr = "{\"rows\":[";
		String jsonstr = "{";
		
		//
		String dsql =	 "	SELECT A.car_brand," +
						 "	(SELECT datavalue FROM xs_childdictionary CH WHERE CH.child_id =A.car_brand) AS brand " +
					 	 "	FROM xs_sell_custom_salesman_detail A" +
					 	 "	JOIN xs_car_model B ON A.car_model = B.xs_model_id" +
					 	 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
					 	 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
					 	 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
					 	 "	WHERE 1 = 1   "+condition+"  GROUP BY A.car_brand" ;
		//查询等级名称
		String namesql = "	SELECT " +
						 "	A.stf_id," +
						 "	bf.stf_name" +
						 "	FROM xs_sell_custom_salesman_detail A" +
						 " 	LEFT JOIN bas_stuff bf ON  bf.stf_id = A.stf_id" +
						 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
						 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
						 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
						 "	WHERE 1 = 1  "+condition+" GROUP BY A.stf_id";
		
		//
			List dlist = backRegisterDao.createSQLQuery(dsql);
			//如果是查询列则直接返回列名称
			if(backRegisterVo.getFlag()!=null && backRegisterVo.getFlag()==true){
				Object[] bj = null;
				String strlist = "";
				if(dlist!=null && dlist.size()>0){
					for (int k = 0; k < dlist.size(); k++) {
						bj = (Object[])dlist.get(k);
						strlist += ","+bj[1];
					}
					strlist = strlist.substring(1);
				}
				return strlist;
			}
			List namelist = backRegisterDao.createSQLQuery(namesql);
		
		if(namelist!=null && namelist.size()>0){
			for (int i = 0; i < namelist.size(); i++) {
				Object[] obj  = (Object[])namelist.get(i);
				jsonstr += "\"stfname\":\""+obj[1]+"\"";
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sums  = 0; 
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
									 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
									 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
									 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
									 "	WHERE 1 = 1 " +
									 "	AND A.stf_id="+obj[0]+"  " +
									 "	AND A.car_brand="+objs[0]+" ";
							
						String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
						String counts  = Integer.parseInt(count)>0 ? count : "";
						jsonstr += ",\"brand"+objs[1].toString().replaceAll(" ", "")+"\":\""+counts+"\"";
						sums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}// for
					
					jsonstr += ",\"sums\":\""+sums+"\"";
					jsonstr = jsonstr + "},{";
				}
			}
			
			//汇总###################################
				if(dlist!=null && dlist.size()>0){
					Object[] objs = null;
					int sumstatal = 0;
					jsonstr += "\"stfname\":\"汇总\"";
					for (int j = 0; j < dlist.size(); j++) {
						objs = (Object[])dlist.get(j);
						int columnsums = 0;
						for (int i = 0; i < namelist.size(); i++) {
							Object[] obj  = (Object[])namelist.get(i);
							String sql = "	SELECT COUNT(*) FROM xs_sell_custom_salesman_detail A " +
							 "	JOIN xs_sell_custom_salesman C ON A.xs_custom_salesman_id = C.xs_custom_salesman_id" +
							 "	LEFT JOIN xs_sell_receiveRelation cent ON cent.xs_custom_salesman_detail_id = A.xs_custom_salesman_detail_id" +
							 "	LEFT JOIN xs_custom_info cus ON cus.custom_id = cent.custom_id" +
							 "	WHERE 1 = 1 " +
							 "	AND A.stf_id="+obj[0]+"  " +
							 "	AND A.car_brand="+objs[0]+" ";
					
							String count = backRegisterDao.createSQLQuery(sql).get(0)!=null ? backRegisterDao.createSQLQuery(sql).get(0).toString() : "";
							String counts  = Integer.parseInt(count)>0 ? count : "";
							//最后一行汇总
							columnsums += counts!=null && !counts.equals("") ? Integer.parseInt(counts) : 0;
						}
						sumstatal += columnsums;
						jsonstr += ",\"brand"+objs[1].toString().replaceAll(" ", "")+"\":\""+columnsums+"\"";
					}// for
					//最后一列汇总
					jsonstr += ",\"sums\":\""+sumstatal+"\"";
				}
				jsonstr = jsonstr + "},{";
			
			//汇总###################################
			newjsonstr += jsonstr; 
			newjsonstr = newjsonstr.substring(0, newjsonstr.length()-2);
		}
		String news = newjsonstr+"],\"total\":"+Integer.MAX_VALUE+"}"; 
		return news;
			
	}

	}
