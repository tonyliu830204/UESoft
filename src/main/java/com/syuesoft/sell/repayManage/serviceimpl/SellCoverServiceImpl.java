package com.syuesoft.sell.repayManage.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.model.XsRepay;
import com.syuesoft.sell.model.XsRepayProject;
import com.syuesoft.sell.model.XsSellCover;
import com.syuesoft.sell.model.XsSellCoverContent;
import com.syuesoft.sell.repayManage.dao.SellCoverDao;
import com.syuesoft.sell.repayManage.service.SellCoverService;
import com.syuesoft.sell.repayManage.vo.SellCoverVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.SnapMap;
import com.syuesoft.util.SystemUser;

@Service("sellCoverService")
public class SellCoverServiceImpl extends BaseLogServiceImpl implements
		SellCoverService {
	private SellCoverDao SellCoverDao;

	public SellCoverDao getSellCoverDao() {
		return SellCoverDao;
	}
	@Resource
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	@Resource
	private BaseTagDAO baseTagDAO;

	@Autowired
	public void setSellCoverDao(SellCoverDao sellCoverDao) {
		SellCoverDao = sellCoverDao;
	}

	//获取初始化时间
	private String edate = FormatTime.yesTady("yyyy-MM-dd");

	/**
	 * 查询销售信息
	 */
	
	public Datagrid getSellList(SellCoverVo sellCoverVo ) throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()));
		Datagrid dg = new Datagrid();
		StringBuffer sql = new StringBuffer(
				"SELECT A.reserve_id,f.xs_car_brand, F.xs_car_code,f.xs_car_model_id, F.xs_car_color, "
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =f.xs_car_brand) AS brand,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =F.xs_car_color) AS color,"
						+ "g.xs_model_name,F.xs_car_vin_number,B.custom_id,e.xs_custom_name, B.xs_car_sel_id,"
						+ "b.xs_car_sel_data,b.xs_car_sel_remark,D.consult_degree,"
						+ " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.consult_degree) AS degree,"
						+ "E.xs_custom_phone,E.xs_custom_telephone,E.xs_custom_application,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_application) AS application,"
						+ " E.xs_custom_address,  E.xs_custom_occupation,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_occupation) AS occupation,"
						+ " E.xs_custom_income,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =  E.xs_custom_income) AS income,"
						+ " e.xs_custom_diploma,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =  e.xs_custom_diploma) AS diploma,"
						+ " E.xs_custom_birthday,E.xs_custom_credentials,  E.xs_custom_sex,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = E.xs_custom_sex) AS sex,"
						+ "E.STF_ID, H.STF_NAME,  F.xs_car_motor_number,"
						+ "F.xs_car_licensePlate,F.xs_car_sell_state,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id = F.xs_car_sell_state) AS state,"
						+ " A.pact_code ,f.upData,D.consult_actual_date,D.consult_rate ," +
						"C.xs_repay_name ,A.car_outputVolume,f.xs_car_rideLimit_number,d.travel_course,E.xs_custom_zipcode," +
						"D.consult_call_state,"
						+ "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =D.consult_call_state) AS stateaa "
						+ ", B.sell_code " +
								"FROM  xs_car_sell_info B "
						+ "LEFT OUTER JOIN xs_sell_car_reserve A ON a.reserve_id=b.reserve_id "
						+" LEFT OUTER JOIN (SELECT * FROM  xs_sell_cover k " +
						" WHERE k.consult_actual_date IN " +
						" (SELECT MAX(consult_actual_date)FROM xs_sell_cover GROUP BY xs_sell_cover.xs_car_sel_id)) AS D " +
						" ON d.xs_car_sel_id=b.xs_car_sel_id "
						+ " JOIN xs_custom_info E ON e.custom_id=b.custom_id "						
						+ " JOIN xs_car_info F ON b.xs_car_id=f.xs_car_id "
						+ " JOIN xs_car_model G  ON g.xs_model_id=f.xs_car_model_id "
						+ " JOIN bas_stuff H ON h.STF_ID=e.STF_ID"
						+ " LEFT OUTER JOIN xs_repay  C ON c.xs_repay_id=d.consult_rate " +
						" JOIN xs_sell_flow_control co on co.xsfcontrol_document=b.sell_code and " +
						" co.xsfcontrol_car_id=b.xs_car_id "
						+ " WHERE (b.xs_car_give_up IS NULL OR   b.xs_car_give_up=0) and B.sellafter_status=1 ");
		
		//企业编号
		if (sellCoverVo.getEnterpriseId() != null
				&& !"".equals(sellCoverVo.getEnterpriseId())) {
			sql.append(" and b.Enterprise_Id ="+sellCoverVo.getEnterpriseId()+ "");
		}

		if (sellCoverVo.getXsCarSelData() != null
				&& !sellCoverVo.getXsCarSelData().equals("")) {
					sql.append(" and DATE(b.xs_car_sel_data) >= '" +sellCoverVo.getXsCarSelData() + "'");
				} 
		if (sellCoverVo.getXsCarSelData2() != null
				&& !sellCoverVo.getXsCarSelData2().equals("")) {
					sql.append(" and DATE(b.xs_car_sel_data) <= '" +sellCoverVo.getXsCarSelData2() + "'");
		}
		if((sellCoverVo.getXsCarSelData() == null
				|| sellCoverVo.getXsCarSelData().equals("")) 
				&& (sellCoverVo.getXsCarSelData2() == null
				|| sellCoverVo.getXsCarSelData2().equals(""))){
			sql.append(" and DATE(b.xs_car_sel_data) BETWEEN '" + sdate + "' AND '"
			+ edate + "'");
		}

		if (sellCoverVo.getXsCustomName() != null
				&& !"".equals(sellCoverVo.getXsCustomName())) {
			sql.append(" and e.xs_custom_name like '%"
					+ StringEscapeUtils.escapeSql(sellCoverVo.getXsCustomName().trim()) + "%'");
		}
		if (sellCoverVo.getXsCustomPhone() != null
				&& !"".equals(sellCoverVo.getXsCustomPhone())) {
			sql.append(" and E.xs_custom_telephone like'%"
					+ sellCoverVo.getXsCustomPhone() + "%'");
		}
		if (sellCoverVo.getStfId() != null
				&& !"".equals(sellCoverVo.getStfId())) {
			sql.append(" and e.STF_ID='" + sellCoverVo.getStfId() + "'");
		}
		if (sellCoverVo.getConsulTRate() != null
				&& !"".equals(sellCoverVo.getConsulTRate())) {
			sql.append("  and D.consult_rate='" + sellCoverVo.getConsulTRate() + "'");
		}
		if (sellCoverVo.getConsulTRate2() != null
				&& !"".equals(sellCoverVo.getConsulTRate2())) {
			sql.append(" and D.consult_actual_date is NULL and D.consult_rate='" + sellCoverVo.getConsulTRate2() + "'");
		}
		if (sellCoverVo.getConsultCallState() != null
				&& !"".equals(sellCoverVo.getConsultCallState())) {
			sql.append(" and  D.consult_call_state='" + sellCoverVo.getConsultCallState() + "'");
		}

		if (sellCoverVo.getAuditDate() != null
				&& !"".equals(sellCoverVo.getAuditDate())) {
			sql.append(" and DATE(f.upData)>='" + sellCoverVo.getAuditDate() + "'");
		}

		if (sellCoverVo.getAuditDate2() != null
				&& !"".equals(sellCoverVo.getAuditDate2())) {
			sql.append(" and DATE(f.upData)<='" + sellCoverVo.getAuditDate2() + "'");
		}
		if (sellCoverVo.getConsultActualDate() != null
				&& !"".equals(sellCoverVo.getConsultActualDate())) {
			sql.append(" and DATE(D.consult_actual_date)>='" + sellCoverVo.getConsultActualDate() + "'");
		}

		if (sellCoverVo.getConsultActualDate2() != null
				&& !"".equals(sellCoverVo.getConsultActualDate2())) {
			sql.append(" and DATE(D.consult_actual_date)<='" + sellCoverVo.getConsultActualDate2() + "'");
		}
		if (sellCoverVo.getVinCode() != null
				&& !"".equals(sellCoverVo.getVinCode())) {
			sql.append(" and  F.xs_car_vin_number like '%" + StringEscapeUtils.escapeSql(sellCoverVo.getVinCode().trim()) + "%'");
		}
		if (sellCoverVo.getSell_code() != null
				&& !"".equals(sellCoverVo.getSell_code())) {
			sql.append("  and B.sell_code like '%" + sellCoverVo.getSell_code()
					+ "%'");
		}
		/*if (sellCoverVo.getCarSellState() != null
				&& !"".equals(sellCoverVo.getCarSellState()) && "true".equals(sellCoverVo.getCarSellState())) {
			sql.append(" and (f.xs_car_sell_state=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.BASE_SELLSTATE+"' AND " +
						"child.dataKey='"+Contstants.SELLSTATE.SELLOUT+"'" +
						"))");
		}*/
			
		sql.append(" group by B.xs_car_sel_id");
	
		List<Object[]> resultList = SellCoverDao.createSQLQuery(sql.toString(), sellCoverVo
				.getPage(), sellCoverVo.getRows());
		List<SellCoverVo> list = new ArrayList<SellCoverVo>();
		if (resultList != null && resultList.size() > 0) {
			SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Object[] obj = null;
			for (int i = 0; i < resultList.size(); i++) {
				SellCoverVo sell = new SellCoverVo();
				obj = resultList.get(i);
				sell.setReserveId(obj[0] != null ? Integer.parseInt(obj[0]
						.toString()) : null);
				sell.setCarBrand(obj[1] != null ? Integer.parseInt(obj[1]
						.toString()) : null);
				sell.setCarCode(obj[2] != null ? obj[2].toString() : null);
				sell.setCarModel(obj[3] != null ? Integer.parseInt(obj[3]
						.toString()) : null);
				sell.setCarColor(obj[4] != null ? Integer.parseInt(obj[4]
						.toString()) : null);
				sell.setCarBrandName(obj[5] != null ? obj[5].toString() : null);
				sell.setCarColorName(obj[6] != null ? obj[6].toString() : null);
				sell.setCarModelName(obj[7] != null ? obj[7].toString() : null);
				sell.setVinCode(obj[8] != null ? obj[8].toString() : null);
				sell.setCustomId(obj[9] != null ? Integer.parseInt(obj[9]
						.toString()) : null);
				sell.setXsCustomName(obj[10] != null ? obj[10].toString()
						: null);
				sell.setXsCarSelId(obj[11] != null ? Integer.parseInt(obj[11]
						.toString()) : null);
				sell.setXsCarSelData(obj[12] != null ? obj[12].toString()
						: null);
				sell.setXsCarSelRemark(obj[13] != null ? obj[13].toString()
						: null);
				sell.setConsultDegree(obj[14] != null ? Integer
						.parseInt(obj[14].toString()) : null);
				sell.setConsultDegreeType(obj[15] != null ? obj[15].toString()
						: null);
				sell.setXsCustomPhone(obj[16] != null ? obj[16].toString()
						: null);
				sell.setXsCustomTelephone(obj[17] != null ? obj[17].toString()
						: null);
				sell.setXsCustomApplication(obj[18] != null ? Integer
						.parseInt(obj[18].toString()) : null);
				sell.setXsCustomApplicationN(obj[19] != null ? obj[19]
						.toString() : null);
				sell.setXsCustomAddress(obj[20] != null ? obj[20].toString()
						: null);
				sell.setXsCustomOccupation(obj[21] != null ? Integer
						.parseInt(obj[21].toString()) : null);
				sell.setXsCustomOccupationN(obj[22] != null ? obj[22]
						.toString() : null);
				sell.setXsCustomIncome(obj[23] != null ? Integer
						.parseInt(obj[23].toString()) : null);
				sell.setXsCustomIncomeN(obj[24] != null ? obj[24].toString()
						: null);
				sell.setXsCustomDiploma(obj[25] != null ? Integer
						.parseInt(obj[25].toString()) : null);
				sell.setXsCustomDiplomaN(obj[26] != null ? obj[26].toString()
						: null);
				sell
						.setXsCustomBirthday(obj[27] != null ? ((Timestamp) obj[27])
								: null);
				sell.setXsCustomCredentials(obj[28] != null ? obj[28]
						.toString() : null);
				sell.setXsCustomSex(obj[29] != null ? Integer.parseInt(obj[29]
						.toString()) : null);
				sell.setSexName(obj[30] != null ? obj[30].toString() : null);
				sell.setStfId(obj[31] != null ? Integer.parseInt(obj[31]
						.toString()) : null);
				sell.setStfName(obj[32] != null ? obj[32].toString() : null);
			

				sell.setCarMotorNumber(obj[33] != null ? obj[33].toString()
						: null);
				sell.setCarLicensePlate(obj[34] != null ? obj[34].toString()
						: null);
				sell.setCarSellState(obj[35] != null ? obj[35]
						.toString() : null);
				sell.setCarSellStateN(obj[36] != null ? obj[36].toString()
						: null);
				sell.setPactCode(obj[37] != null ? obj[37].toString() : null);
				sell.setAuditDate(obj[38] != null ? obj[38].toString() : null);
				sell.setConsultActualDate(obj[39] != null ?  fmt.format(obj[39])
								: null);
				sell.setConsulTRate(obj[40] != null ? Integer.parseInt(obj[40]
						.toString()) : null);
				sell.setConsulTRateN(obj[41] != null ? obj[41].toString()
						: null);
				sell.setCarOutputVolume(obj[42] != null ? Integer.parseInt(obj[42].toString()) : null);
				sell.setLimitLoadNum(obj[43] != null ? Integer.parseInt(obj[43].toString()) : null);
				sell.setTravelCourse(obj[44] != null ?Double.parseDouble(obj[44].toString()) : null);
				sell.setXsCustomZipcode(obj[45] != null ?obj[45].toString() : null);
				sell.setConsultCallState(obj[46] != null ? Integer
						.parseInt(obj[46].toString()) : null);
				sell.setConsultCallStateN(obj[47] != null ? obj[47].toString()
						: null);
				sell.setSell_code(obj[48] != null ? obj[48].toString()
						: null);
				list.add(sell);

			}
		}
		int total = SellCoverDao.getSQLCount(sql.toString(), null);
		dg.setRows(list);
		dg.setTotal(total);
		return dg;
	
	}

	
	public Datagrid getSellCover(SellCoverVo sellCoverVo) throws Exception {
		return SellCoverDao.getSellCover(sellCoverVo);
	}

	// 修改
	
	@Log(systemName = "销售系统", moduleName = "售后回访管理", opertype = "修改")
	public void modifySellCover(SellCoverVo sellCoverVo) throws Exception {
		BaseBean sell = SellCoverDao
				.get("from XsCustomInfo cus where cus.customId='"
						+ sellCoverVo.getCustomId() + "'");
		XsCustomInfo in = (XsCustomInfo) sell;
		in.setXsCustomPhone(sellCoverVo.getXsCustomPhone());
		in.setXsCustomTelephone(sellCoverVo.getXsCustomTelephone());
		in.setXsCustomAddress(sellCoverVo.getXsCustomAddress());
		SellCoverDao.merge(in);
		BaseBean cover = SellCoverDao
				.get("from XsSellCover cover where cover.consultId='"
						+ sellCoverVo.getConsultId() + "'");
		XsSellCover co = (XsSellCover) cover;
		SimpleDateFormat sple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(sellCoverVo.getConsultPlanDate() != null
				&& !sellCoverVo.getConsultPlanDate().equals("")){
			co.setConsultPlanDate(sple.parse(sellCoverVo.getConsultPlanDate()));
		}
		if(sellCoverVo.getConsultActualDate() != null
				&& !sellCoverVo.getConsultActualDate().equals("")){
			co.setConsultActualDate(sple.parse(sellCoverVo.getConsultActualDate()));
		}
		XsChilddictionary degree=null;
		if(sellCoverVo.getConsultDegree()!=null&&!"".equals(sellCoverVo.getConsultDegree())){
			degree=baseTagDAO.findById(sellCoverVo.getConsultDegree());
		}
		co.setXsSellCoverDegree(degree);
		co.setTravelCourse(sellCoverVo.getTravelCourse());
		co.setConsultSuggest(sellCoverVo.getConsultSuggest());
		if(sellCoverVo.getDisposeDate() != null&& !sellCoverVo.getDisposeDate().equals("") ){
			co.setDisposeDate(sple.parse(sellCoverVo.getDisposeDate()));
		}
		co.setDisposeResult(sellCoverVo.getDisposeResult());
		XsChilddictionary state=null;
		if(sellCoverVo.getConsultCallState()!=null&&!"".equals(sellCoverVo.getConsultCallState())){
			state=baseTagDAO.findById(sellCoverVo.getConsultCallState());
		}
		co.setXsSellCoverCallState(state);
		co.setRemark(sellCoverVo.getRemark());
		SellCoverDao.merge(co);
		setContent("" + SystemUser.getUser().getBasStuff().getStfName()
				+ "修改了单号为【" + sellCoverVo.getConsultId() + "】的售后回访单！");

	}

	/**
	 * 查询回访进度字段
	 */
	
	public List<ComboBoxJson> getInfo(SellCoverVo sellCoverVo) throws Exception {
		String hql = "from XsRepay where 1=1 ";
		if(sellCoverVo.getEnterpriseId()!=null&&!"".equals(sellCoverVo.getEnterpriseId())){
			hql += " and enterpriseId="+sellCoverVo.getEnterpriseId();
		}
		if (sellCoverVo.getQ() != null && !("".equals(sellCoverVo.getQ()))) {
			hql += "	and repayName like '%"
					+ StringEscapeUtils.escapeSql(sellCoverVo.getQ().trim())
					+ "%'";
		}
		List<BaseBean> list = SellCoverDao.find(hql);
		List<ComboBoxJson> rows = new ArrayList<ComboBoxJson>();
		if (list != null && list.size() > 0) {
			for (BaseBean baseB : list) {
				XsRepay xzs = (XsRepay) baseB;
				ComboBoxJson cbs = new ComboBoxJson();
				cbs.setId(xzs.getRepayId().toString());
				cbs.setText(xzs.getRepayName());
				rows.add(cbs);
			}
		}
		return rows;
	}

	/**
	 * 调查表
	 */
	
	public List getResearch(SellCoverVo sellCoverVo) throws Exception {
		return SellCoverDao.getResearch(sellCoverVo);
	}

	
	public void addResearchList(SellCoverVo sellCoverVo) throws Exception {
		List<SellCoverVo> research = JSON.parseArray(sellCoverVo
				.getAddResearchList(), SellCoverVo.class);

		if (research != null && research.size() > 0) {
			for (SellCoverVo sell : research) {
				XsSellCoverContent con=new XsSellCoverContent();
				con.setContentId(sell.getContentId());
				con.setConsultId(sell.getConsultId());
				con.setProjectId(sell.getProjectId());
				con.setRemark(sell.getRemark());
				con.setConsultId(sell.getConsultId());
				XsChilddictionary score=null;
				if( sell.getProjectScore()!=null&&!"".equals( sell.getProjectScore())){
					 score= baseTagDAO.findById( sell.getProjectScore());	
				}
				if(score!=null){
					con.setProjectScore(Integer.parseInt(score.getDataValue()));	
				}
				XsChilddictionary evl=null;
				if(sell.getProjectEvaluate()!=null&&!"".equals(sell.getProjectEvaluate())){
					evl=baseTagDAO.findById(sell.getProjectEvaluate());
				}
				con.setXsSellCoverContentEvaluate(evl);
				
			/*	if (list != null && list.size() > 0) {
					sell.setProjectScore(Integer.parseInt(list.get(0)
							.toString()));*/
				//}
				SellCoverDao.saveOrUpdate(con);
			}
		}

	}

	
	public Datagrid getInSellList(SellCoverVo sellCoverVo) throws Exception {
		return SellCoverDao.getInSellList(sellCoverVo);
	}

	
	public Datagrid getSellCoverMange(SellCoverVo sellCoverVo) throws Exception {

		return SellCoverDao.getSellCoverMange(sellCoverVo);

	}
	
	/**
	 * 获取动态列
	 */
	
	public List getDateColumn(SellCoverVo sellCoverVo) throws Exception {
		String datecolumn = "";
		List list = new ArrayList();
		if (
				sellCoverVo.getConsultActualDate() != null&& !sellCoverVo.getConsultActualDate().trim().equals("") &&
				sellCoverVo.getConsultActualDate2() != null&& !sellCoverVo.getConsultActualDate2().trim().equals("")
			) {
			String syear1 = sellCoverVo.getConsultActualDate().trim().substring(0,4);
			String smonth1 = sellCoverVo.getConsultActualDate().trim().substring(5,7); 
			String syear2 = sellCoverVo.getConsultActualDate2().trim().substring(0,4);
			String smonth2 = sellCoverVo.getConsultActualDate2().trim().substring(5,7);
			
			int iyear1 = Integer.parseInt(syear1);
			int imonth1 = Integer.parseInt(smonth1);
			int iyear2 = Integer.parseInt(syear2);
			int imonth2 = Integer.parseInt(smonth2);
			// 获取两时间之间的月份数
			 int month = (iyear2 - iyear1) * 12 + (imonth2 - imonth1) + 1;
			 datecolumn = syear1+"-"+smonth1;
			 String mdate = "";
			 for (int i = 1; i < month; i++) {
				 imonth1 += 1;
				if (imonth1 < 10) {
					 mdate = "0" + imonth1;
					 } else {
						 if(imonth1>12){
							 imonth1 = 1;
							 mdate = "01";
							 iyear1 += 1;
						 }else{
							 mdate = imonth1 + "";
						 }	 
					 };
				 datecolumn += ","+iyear1+"-"+mdate;
			}
			String[] str = datecolumn.split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		return list;
	}

	/*** 
	 * 方法概述:将前台传过来的回访日期分割截取成每一个月的时间段传给service
	 * 跟踪满意度统计
	 */
	@SuppressWarnings("unchecked")
	
	public List getDateInfomation(SellCoverVo sellCoverVo) throws Exception {
		String sdate =FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()));
		List trlist = new ArrayList();
		StringBuffer sql = new StringBuffer("");
		sql.append(" 	SELECT INFO.consult_actual_date,");
		sql.append("	SUM(IF(INFO.degree='满意',INFO.SUMS,0)) AS '满意',");
		sql.append("	SUM(IF(INFO.degree='很满意',INFO.SUMS,0)) AS '很满意'," );
		sql.append("	SUM(IF(INFO.degree='一般',INFO.SUMS,0)) AS '一般'," );
		sql.append("	SUM(IF(INFO.degree='不满意',INFO.SUMS,0)) AS '不满意'," );
		sql.append("	SUM(IF(INFO.degree='很不满意',INFO.SUMS,0)) AS '很不满意'," );
		sql.append("	SUM(IF(INFO.degree='无',INFO.SUMS,0)) AS '无'," );
		sql.append("	SUM(INFO.SUMS) AS TOTAL" );
		sql.append("	FROM " );
		sql.append("	(SELECT" );
		sql.append("	COUNT(*) AS SUMS," );
		sql.append("	A.consult_degree," );
		sql.append("	(SELECT B.datavalue  FROM xs_childdictionary B  WHERE B.child_id = A.consult_degree) AS degree ," );
		sql.append("	DATE_FORMAT(A.consult_actual_date,'%Y-%m') AS consult_actual_date" );
		sql.append("	FROM xs_sell_cover A JOIN xs_car_sell_info sell ON a.xs_car_sel_id = sell.xs_car_sel_id " );
		sql.append("	LEFT JOIN xs_repay rp ON a.consult_rate = rp.xs_repay_id " );
		sql.append("	JOIN xs_car_info car ON sell.xs_car_id = car.xs_car_id " );
		sql.append("	JOIN xs_custom_info custom ON custom.custom_id = sell.custom_id " );
		sql.append("	JOIN bas_stuff stf ON stf.STF_ID = custom.STF_ID " );
		sql.append("	WHERE 1 = 1 " );

		//回访日期
		if(sellCoverVo.getEnterpriseId()!=null && !sellCoverVo.getEnterpriseId().equals("")){
			sql.append("	AND A.enterprise_Id= '"+sellCoverVo.getEnterpriseId()+"'" );
		}
		
		//回访日期
		if(sellCoverVo.getConsultActualDate()!=null && !sellCoverVo.getConsultActualDate().equals("")){
			sql.append("	AND DATE(consult_actual_date) >= '"+sellCoverVo.getConsultActualDate()+"'" );
		}
		if(sellCoverVo.getConsultActualDate2()!=null && !sellCoverVo.getConsultActualDate2().equals("")){
			sql.append("	AND DATE(consult_actual_date) <= '"+sellCoverVo.getConsultActualDate2()+"'" );
		}
		if((sellCoverVo.getConsultActualDate() == null
				|| sellCoverVo.getConsultActualDate().equals("")) 
				&& (sellCoverVo.getConsultActualDate2() == null
				|| sellCoverVo.getConsultActualDate2().equals(""))){
		sql.append("	AND DATE(consult_actual_date) BETWEEN '"+sdate+"' AND '"+edate+"'" );
		}
		//销售日期
		if(sellCoverVo.getXsCarSelData()!=null && !sellCoverVo.getXsCarSelData().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) >= '"+sellCoverVo.getXsCarSelData()+"'" );
		}
		if(sellCoverVo.getXsCarSelData2()!=null && !sellCoverVo.getXsCarSelData2().equals("")){
			sql.append("	AND DATE(sell.xs_car_sel_data) <= '"+sellCoverVo.getXsCarSelData2()+"'" );
		}
		// 品牌
		if (sellCoverVo.getCarBrand() != null
				&& !sellCoverVo.getCarBrand().equals("")) {
			sql
					.append(" and car.xs_car_brand=" + sellCoverVo.getCarBrand()
							+ "");
		}
		// 业务员
		if (sellCoverVo.getStfId() != null
				&& !sellCoverVo.getStfId().equals("")) {
			sql.append(" and custom.stf_id=" + sellCoverVo.getStfId() + "");
		}
		// 回访进度
		if (sellCoverVo.getConsulTRate() != null
				&& !sellCoverVo.getConsulTRate().equals("")) {
			sql.append(" and A.consult_rate=" + sellCoverVo.getConsulTRate()
					+ "");
		}
		// 部门
		if (sellCoverVo.getDeptId() != null
				&& !sellCoverVo.getDeptId().equals("")) {
			sql.append(" and stf.DEPT_ID=" + sellCoverVo.getDeptId() + "");
		}
		//单击行列时触发   
		if(sellCoverVo.getFlag()!=null && sellCoverVo.getFlag()==true){
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("verySatisfaction")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE,Contstants.BASE_SELL.henmanyi,sellCoverVo.getEnterpriseId()) );
			}
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("satisfaction")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE, Contstants.BASE_SELL.manyi,sellCoverVo.getEnterpriseId()) );
			}
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("ordinary")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE,Contstants.BASE_SELL.yiban,sellCoverVo.getEnterpriseId()) );
			}
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("veryNotSatisfaction")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE, Contstants.BASE_SELL.henbumanyi,sellCoverVo.getEnterpriseId()) );
			}
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("notSatisfaction")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE, Contstants.BASE_SELL.bumanyi,sellCoverVo.getEnterpriseId()) );
			}
			if(sellCoverVo.getSatisfactionName()!=null && sellCoverVo.getSatisfactionName().trim().equals("not")){
				sql.append("	AND A.consult_degree="+baseTagDAO.getChildId(Contstants.BASE_SELL.CONSULTDEGREE, Contstants.BASE_SELL.wu,sellCoverVo.getEnterpriseId()) );
			}
			
		}	
		sql.append("	GROUP BY A.consult_degree, consult_actual_date" );
		sql.append("	) AS INFO" );
		sql.append("	GROUP BY INFO.consult_actual_date" );
		
		List list = SellCoverDao.createSQLQuery(sql.toString());
		if(list!=null && list.size()>0){
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[])list.get(i);
				SellCoverVo sellCoverVo2 = new SellCoverVo();
				sellCoverVo2.setConsultActualDate(obj[0]!=null ? obj[0].toString() : "");
				sellCoverVo2.setSatisfaction(obj[1]!=null ? obj[1].toString() : "");
				sellCoverVo2.setVerySatisfaction(obj[2]!=null ? obj[2].toString() : "");
				sellCoverVo2.setOrdinary(obj[3]!=null ? obj[3].toString() : "");
				sellCoverVo2.setNotSatisfaction(obj[4]!=null ? obj[4].toString() : "");
				sellCoverVo2.setVeryNotSatisfaction(obj[5]!=null ? obj[5].toString() : "");
				sellCoverVo2.setNot(obj[6]!=null ? obj[6].toString() : "");
				trlist.add(sellCoverVo2);
			}
		}
		return trlist;
	}

	//售后回访分析折线图
	@SuppressWarnings("unchecked")
	
	public JFreeChart getMapbyTime(SellCoverVo sellCoverVo) throws Exception {
		List<SnapMap> snapList = new ArrayList();
		SnapMap sm = null;
		List rlist = getDateInfomation(sellCoverVo);
		List<SellCoverVo> list = rlist;
		if (list != null && list.size() > 0) {
			for (SellCoverVo sellCoverVos : list) {
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getVerySatisfaction()));
				sm.setSnapLineName("很满意");
				sm.setSnapLineColor(new Color(1,0,167));
				snapList.add(sm);
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getSatisfaction()));
				sm.setSnapLineName("满意");
				sm.setSnapLineColor(new Color(0,176,20));
				snapList.add(sm);
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getOrdinary()));
				sm.setSnapLineName("一般");
				sm.setSnapLineColor(new Color(255,128,0));
				snapList.add(sm);
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getNotSatisfaction()));
				sm.setSnapLineName("不满意");
				sm.setSnapLineColor(new Color(110,35,184));
				snapList.add(sm);
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getVeryNotSatisfaction()));
				sm.setSnapLineName("很不满");
				sm.setSnapLineColor(new Color(255,48,65));
				snapList.add(sm);
				sm = new SnapMap();
				sm.setSnapXData(sellCoverVos.getConsultActualDate());
				sm.setSnapYData(Double.parseDouble(sellCoverVos.getNot()));
				sm.setSnapLineName("无");
				sm.setSnapLineColor(new Color(255,249,170));
				snapList.add(sm);
			}
		}
		return CreateJFreeChart.findSnapMap("客户月满意度折线图", "满意度", "月统计量",
				snapList, null, Font.BOLD, 20, null, Font.PLAIN, 14, null,
				Font.PLAIN, 14, null, Font.PLAIN, 14,
				CategoryLabelPositions.UP_45, null, Font.PLAIN, 11, null, null,
				sellCoverVo.getIs3D());
	}

	
	public Datagrid getProjectStatistics(SellCoverVo sellCoverVo)
			throws Exception {
		return SellCoverDao.getProjectStatistics(sellCoverVo);
	}

	
	public Datagrid geTtimelyAnalysis(SellCoverVo sellCoverVo) throws Exception {
		return SellCoverDao.geTtimelyAnalysis(sellCoverVo);
	}

	/**
	 * 拼接datagrid字符串
	 */
	
	public String getDatagridString(SellCoverVo sellCoverVo) throws Exception {
		String s = "";
		String hql="from XsRepayProject where 1=1 ";
		if(sellCoverVo.getEnterpriseId()!=null&&!"".equals(sellCoverVo.getEnterpriseId())){
			hql+=" and enterpriseId="+sellCoverVo.getEnterpriseId();
		}
		List rlist = SellCoverDao.find(hql);
		List<XsRepayProject> xsRepayProjectlist = rlist;
		for (XsRepayProject xsRepayProject : xsRepayProjectlist) {
			s += ",{field : 'projectName" + xsRepayProject.getProjectId()
					+ "',title : '" + xsRepayProject.getProjectName()
					+ "',width : 250,sortable : true}";
		}
		return s;
	}

	/**
	 * 跟踪记录汇总获取datagrid的值
	 */
	
	public List getDatagridValue(SellCoverVo sellCoverVo) throws Exception {

		StringBuffer sql1 = new StringBuffer(
				"SELECT"
						+ " e.xs_custom_name      AS xs_custom_name,"
						+ " (SELECT xs_model_name FROM xs_car_info aa ,xs_car_model bb WHERE aa.xs_car_model_id = bb.xs_model_id AND aa.xs_car_id=d.xs_car_id ) AS xs_model_name,"
						+ " e.xs_custom_telephone AS xs_custom_telephone,"
						+ " h.STF_NAME            AS stf_name,"
						+ " d.xs_car_sel_data 	 AS xs_car_sel_data,"
						+ " j.consult_suggest  as consult_suggest, "
						+ "j.consult_actual_date,"
						+ " h.DEPT_ID ,e.STF_ID,"
						+ " (SELECT cc.xs_car_brand FROM xs_car_info cc WHERE cc.xs_car_id=d.xs_car_id ) AS xs_car_brand,"
						+ " j.consult_rate,d.enterprise_id ");

		StringBuffer sql2 = new StringBuffer(
				"FROM  "
						+ " xs_sell_cover j  " +
								"JOIN xs_car_sell_info d ON j.xs_car_sel_id = d.xs_car_sel_id"
						+ "  JOIN xs_custom_info e ON d.custom_id = e.custom_id"
						+ " LEFT JOIN bas_stuff h ON h.STF_ID = e.STF_ID"
						+ " LEFT JOIN ("
						+ " SELECT p.consult_id,k.xs_project_name,p.project_score "
						+ " FROM  xs_sell_cover_content p " +
						" JOIN xs_repay_project k ON p.project_id = k.xs_project_id"
						+ "	 JOIN xs_sell_cover q ON q.consult_id = p.consult_id"
						+ "	GROUP BY p.consult_id"
						+ "  ) AS bb ON bb.consult_id = j.consult_id"
						+ "  GROUP BY j.consult_id "
						+ " HAVING j.consult_actual_date IS NOT NULL  ");

		List rslist = SellCoverDao.find("FROM XsRepayProject where enterpriseId="+sellCoverVo.getEnterpriseId());
		List<XsRepayProject> XsRepayProjectlist = rslist;
		String score = "";
		for (XsRepayProject xsRepayProject : XsRepayProjectlist) {
			sql1.append(" ,SUM(CASE bb.xs_project_name WHEN '"
					+ xsRepayProject.getProjectName()
					+ "' THEN bb.project_score ELSE 0 END) AS '"
					+ xsRepayProject.getProjectName() + "'");

		}
		StringBuffer newsql = new StringBuffer();
		newsql.append(sql1);
		newsql.append(sql2);
		// 条件
		// 销售日期
		if(sellCoverVo.getEnterpriseId()!=null&&!"".equals(sellCoverVo.getEnterpriseId())){
			newsql.append(" and d.enterprise_id ="+sellCoverVo.getEnterpriseId());
		}
		if (sellCoverVo.getXsCarSelData() != null
				&& !sellCoverVo.getXsCarSelData().equals("")) {
			
				newsql.append(" and DATE(d.xs_car_sel_data) >='" + sellCoverVo.getXsCarSelData() + "'");
			}
		if (sellCoverVo.getXsCarSelData2() != null
					&& !sellCoverVo.getXsCarSelData2().equals("")) {
				newsql.append(" and DATE(d.xs_car_sel_data) <= '" + sellCoverVo.getXsCarSelData2() + "'");
			}
	
		// 回访日期
		
		
			if (sellCoverVo.getConsultActualDate() != null
					&& !"".equals(sellCoverVo.getConsultActualDate())) {
				newsql.append(" and DATE(j.consult_actual_date) >= '"+sellCoverVo.getConsultActualDate()+"'");
			}
			if (sellCoverVo.getConsultActualDate2() != null
					&& !"".equals(sellCoverVo.getConsultActualDate2())) {
			newsql.append(" and DATE(j.consult_actual_date) <= '"+sellCoverVo.getConsultActualDate2()+"'");
		
			}
			if((sellCoverVo.getConsultActualDate() == null
					||"".equals(sellCoverVo.getConsultActualDate()))&&
					(sellCoverVo.getConsultActualDate2() == null
						||"".equals(sellCoverVo.getConsultActualDate2()))){
			newsql.append(" and DATE(j.consult_actual_date) BETWEEN " +
					"'"+FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,sellCoverVo.getEnterpriseId()).getCiValue()))+"'" +
							" AND '"+FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2)+"'");
			
		}
		// 品牌
		if (sellCoverVo.getCarBrand() != null
				&& !sellCoverVo.getCarBrand().equals("")) {
			newsql
					.append(" and xs_car_brand=" + sellCoverVo.getCarBrand()
							+ "");
		}
		// 业务员
		if (sellCoverVo.getStfId() != null
				&& !sellCoverVo.getStfId().equals("")) {
			newsql.append(" and e.stf_id=" + sellCoverVo.getStfId() + "");
		}
		// 回访进度
		if (sellCoverVo.getConsulTRate() != null
				&& !sellCoverVo.getConsulTRate().equals("")) {
			newsql.append(" and j.consult_rate=" + sellCoverVo.getConsulTRate()
					+ "");
		}
		// 部门
		if (sellCoverVo.getDeptId() != null
				&& !sellCoverVo.getDeptId().equals("")) {
			newsql.append(" and h.DEPT_ID=" + sellCoverVo.getDeptId() + "");
		}

		List returnlist = SellCoverDao.createSQLQuery(newsql.toString());

		List objlist = new ArrayList();
		Object[] obj = null;
		if (returnlist != null && returnlist.size() > 0) {
			Map map = null;
			for (int i = 0; i < returnlist.size(); i++) {
				obj = (Object[]) returnlist.get(i);
				map = new HashMap();
				map.put("xsCustomName", obj[0]);
				map.put("xsCarModel", obj[1]);
				map.put("xsCustomTel", obj[2]);
				map.put("stfName", obj[3]);
				map.put("xsCarSelDate", obj[4]);
				map.put("consultSuggest", obj[5]);

				int count = 10;
				for (XsRepayProject xsRepayProject : XsRepayProjectlist) {
					map.put("projectName" + xsRepayProject.getProjectId() + "",
							obj[count]);
					count++;
				}
				objlist.add(map);
			}
		}
		return objlist;
	}


}
