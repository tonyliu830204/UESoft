package com.syuesoft.sell.sellZhProject.serviceimpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsChilddictionary;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.model.XsSellZhProject;
import com.syuesoft.sell.sellZhProject.dao.SellZhProjectDao;
import com.syuesoft.sell.sellZhProject.service.SellZhProjectService;
import com.syuesoft.sell.sellZhProject.vo.SellZhProjectVo;
import com.syuesoft.sell.util.dao.SellAccountDao;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.MyBeanUtils;
import com.syuesoft.util.SystemUser;
@Service("sellZhProjectService")
public class SellZhProjectServiceImpl  extends BaseLogServiceImpl implements SellZhProjectService{
	private SellZhProjectDao sellZhProjectDao;
    private BaseTagDAO baseTagDAO;
    private SellAccountDao accountDao;
    @Resource
    private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
  
    
/*	private static String examineState="未审核";//185
	private static String examineState1="已审核";//186
	private static String insuranceType="是";
	private static String insuranceType1="否";//215
	private static String type="装潢";
	private static String remark="赠送";
*/
  //获取初始化时间
	private String edate = FormatTime.date2Str(new java.util.Date(), "yyyy-MM-dd");
	
	public SellAccountDao getAccountDao() {
		return accountDao;
	}
	@Autowired
	public void setAccountDao(SellAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public SellZhProjectDao getSellZhProjectDao() {
		return sellZhProjectDao;
	}
	@Autowired
	public void setSellZhProjectDao(SellZhProjectDao sellZhProjectDao) {
		this.sellZhProjectDao = sellZhProjectDao;
	}

	
	public Datagrid getZhList(SellZhProjectVo sellZhProjectVo) throws Exception {
		
		return sellZhProjectDao.getZhList(sellZhProjectVo);
	}
	
	@Log(systemName="销售系统", moduleName="装潢(销售及赠送)",opertype="新增")
	public void saveSellZh(SellZhProjectVo sellZhProjectVo) throws Exception {
		
		SellZhProjectVo zhFrom=JSON.parseObject(sellZhProjectVo.getZhFrom(),SellZhProjectVo.class);
		List<SellZhProjectVo> zhList=JSON.parseArray(sellZhProjectVo.getZhLists(),SellZhProjectVo.class);
		XsCarSellInfo sellInfo=(XsCarSellInfo) sellZhProjectDao.get("from  XsCarSellInfo sell where sell.xsCarSelId="+zhFrom.getXs_Car_Sel_Id());
		sellInfo.setIszhProject(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1,sellZhProjectVo.getEnterpriseId()));
		sellInfo.setZhProjecRemark(zhFrom.getZhProjecRemark());
		sellZhProjectDao.merge(sellInfo);
		
		if(zhList!=null && zhList.size()>0){
			String code=CreateID.createId("zhpro", Contstants.SELL_BILLSDEPLOY.ZHPROJECT);
			for (SellZhProjectVo sell : zhList) {
				XsSellZhProject zhpro = new XsSellZhProject();
				 zhpro.setXsCarSellInfo(sellInfo);
				 zhpro.setZhProject(sell.getZsprojectId());
				 zhpro.setUnitNum(sell.getUnitNum());
				 zhpro.setCostPrice(sell.getProjectCostamount());
				 zhpro.setSelLPrice(sell.getProjectSellamount());
				 zhpro.setPreferentialPrice(sell.getPreferentialPrice());
				 zhpro.setDecorateSell(sell.getDecorateSell());
				 zhpro.setDecorateAmount(sell.getDecorateAmount());
				 zhpro.setZhProjectCode(code);
				 zhpro.setZhProjectPerson(zhFrom.getZhProjectPerson());
				 zhpro.setZhProjectDate(zhFrom.getZhProjectDate());
				 zhpro.setRemark(sell.getZhRemark());
				 XsChilddictionary chi = null;
				 chi=baseTagDAO.getXsChilddictionaryById(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId()));
				 zhpro.setXsChilddictionaryByAudit(chi);
				 XsChilddictionary chi2 = null;
				 chi2=baseTagDAO.getXsChilddictionaryById(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellZhProjectVo.getEnterpriseId()));
				 zhpro.setXsChilddictionaryByZhProjectReckoning(chi2);
				 sellZhProjectDao.save(zhpro);
			}
			setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"新增了单号为【"+code+"】的装潢单！");
			
		}

	}
	public BaseTagDAO getBaseTagDAO() {
		return baseTagDAO;
	}
	@Autowired
	public void setBaseTagDAO(BaseTagDAO baseTagDAO) {
		this.baseTagDAO = baseTagDAO;
	}
	
	@Log(systemName="销售系统", moduleName="装潢(销售及赠送)",opertype="修改")
	public void updateSellZh(SellZhProjectVo sellZhProjectVo) throws Exception {
		
		SellZhProjectVo zhFrom=JSON.parseObject(sellZhProjectVo.getZhFrom(),SellZhProjectVo.class);
		List<SellZhProjectVo> insertedList = JSON.parseArray(sellZhProjectVo.getInserted(),SellZhProjectVo.class );
		List<SellZhProjectVo> updatedList = JSON.parseArray(sellZhProjectVo.getUpdated(),SellZhProjectVo.class );
		List<SellZhProjectVo> deletedList = JSON.parseArray(sellZhProjectVo.getDeleted(),SellZhProjectVo.class );
		XsCarSellInfo sellInfo=(XsCarSellInfo) sellZhProjectDao.get("from  XsCarSellInfo sell where sell.xsCarSelId="+zhFrom.getXs_Car_Sel_Id());
		sellInfo.setZhProjecRemark(zhFrom.getZhProjecRemark());
		sellZhProjectDao.merge(sellInfo);
		if(insertedList!=null && insertedList.size()>0){
			for (SellZhProjectVo sell : insertedList) {
				
				XsSellZhProject zhpro=new XsSellZhProject();
				 zhpro.setXsCarSellInfo(sellInfo);
				 zhpro.setZhProject(sell.getZsprojectId());
				 zhpro.setUnitNum(sell.getUnitNum());
				 zhpro.setCostPrice(sell.getProjectCostamount());
				 zhpro.setSelLPrice(sell.getProjectSellamount());
				 zhpro.setPreferentialPrice(sell.getPreferentialPrice());
				 zhpro.setDecorateSell(sell.getDecorateSell());
				 zhpro.setDecorateAmount(sell.getDecorateAmount());
				 zhpro.setZhProjectCode(zhFrom.getZhProjectCode());
				 zhpro.setZhProjectPerson(zhFrom.getZhProjectPerson());
				 zhpro.setZhProjectDate(zhFrom.getZhProjectDate());
				 zhpro.setRemark(sell.getZhRemark());
//				 zhpro.setAudit(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId()));
//				 zhpro.setZhProjectReckoning(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellZhProjectVo.getEnterpriseId()));
				 XsChilddictionary chi=null;
				 Integer audit= baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId());
				if(audit!=null){
					chi=baseTagDAO.findById(audit);
				}
				 zhpro.setXsChilddictionaryByAudit(chi);
				 XsChilddictionary Reckonings=null;
				 Integer reckoningId= baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellZhProjectVo.getEnterpriseId());
				if(audit!=null){
					Reckonings=baseTagDAO.findById(reckoningId);
				}
				 zhpro.setXsChilddictionaryByZhProjectReckoning(Reckonings);
				 sellZhProjectDao.save(zhpro);
			}

			}
		if(updatedList!=null && updatedList.size()>0){
			for (SellZhProjectVo sell : updatedList) {
				XsSellZhProject zhpro=new XsSellZhProject();
				zhpro.setId(sell.getZhid());
				 zhpro.setXsCarSellInfo(sellInfo);
				 zhpro.setZhProject(sell.getZsprojectId());
				 zhpro.setUnitNum(sell.getUnitNum());
				 zhpro.setCostPrice(sell.getProjectCostamount());
				 zhpro.setSelLPrice(sell.getProjectSellamount());
				 zhpro.setPreferentialPrice(sell.getPreferentialPrice());
				 zhpro.setDecorateSell(sell.getDecorateSell());
				 zhpro.setDecorateAmount(sell.getDecorateAmount());
				 zhpro.setZhProjectCode(zhFrom.getZhProjectCode());
				 zhpro.setZhProjectPerson(zhFrom.getZhProjectPerson());
				 zhpro.setRemark(sell.getZhRemark());
				 zhpro.setZhProjectDate(zhFrom.getZhProjectDate());
				 /*chi.setChildId(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId()));
				 zhpro.setXsChilddictionaryByAudit(chi);
				 chi.setChildId(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellZhProjectVo.getEnterpriseId()));
				 zhpro.setXsChilddictionaryByZhProjectReckoning(chi);*/
				 XsChilddictionary chi=null;
				 Integer audit= baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId());
				if(audit!=null){
					chi=baseTagDAO.findById(audit);
				}
				 zhpro.setXsChilddictionaryByAudit(chi);
				 XsChilddictionary Reckonings=null;
				 Integer reckoningId= baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2,sellZhProjectVo.getEnterpriseId());
				if(audit!=null){
					Reckonings=baseTagDAO.findById(reckoningId);
				}
				 zhpro.setXsChilddictionaryByZhProjectReckoning(Reckonings);
				 sellZhProjectDao.update(zhpro);
			}
		}
		if(deletedList!=null && deletedList.size()>0){
			for (SellZhProjectVo sell : deletedList) {
				XsSellZhProject zhpro=(XsSellZhProject) sellZhProjectDao.get("from XsSellZhProject where id="+sell.getZhid());
				sellZhProjectDao.delete(zhpro);
			}
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"修改了单号为【"+zhFrom.getZhProjectCode()+"】的装潢单！");

		
	}
	
	@Log(systemName="销售系统", moduleName="装潢(销售及赠送)",opertype="删除")
	public void deleteSellZh(SellZhProjectVo sellZhProjectVo) throws Exception {
		 List<Object> sa =sellZhProjectDao.find(" from XsSellZhProject xszp where xszp.zhProjectCode='"+sellZhProjectVo.getZhProjectCode()+"'");
		
		 for (Object object : sa) {
			XsSellZhProject xx=(XsSellZhProject)object;
			sellZhProjectDao.delete(xx);	
		}
		// sellZhProjectDao.delete(sellZhProjectDao.get(" from XsSellZhProject xszp where xszp.zhProjectCode='"+sellZhProjectVo.getZhProjectCode()+"'"));
		List<Object> list=sellZhProjectDao.find("from XsSellZhProject temp where temp.xsCarSellInfo.xsCarSelId="+sellZhProjectVo.getXs_Car_Sel_Id());
		if(!(list!=null && list.size()>0)){
			XsCarSellInfo sellInfo=(XsCarSellInfo) sellZhProjectDao.get("from  XsCarSellInfo sell where sell.xsCarSelId="+sellZhProjectVo.getXs_Car_Sel_Id());
			sellInfo.setIszhProject(null);
			sellZhProjectDao.merge(sellInfo);			
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"删除了单号为【"+sellZhProjectVo.getZhProjectCode()+"】的装潢单！");

//		XsSellAccount  account=(XsSellAccount) sellZhProjectDao.get("from  XsSellAccount account where account.accountTypeId='"+sellZhProjectVo.getZhProjectCode()+"'");
//		if(account!=null){
//			sellZhProjectDao.delete(account);
//		}
	}
	
	public Datagrid findSellInfor(SellZhProjectVo sellZhProjectVo)
			throws Exception {
		String sdate = FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, sellZhProjectVo.getEnterpriseId()).getCiValue()));
		Datagrid dg = new Datagrid();
		StringBuffer sql =new StringBuffer( "SELECT  a.xs_car_sel_id,  a.xs_car_sel_data,  d.xs_custom_name," +
				"  f.xs_car_vin_number,  f.xs_car_ocn, " +
				"(SELECT  k.datavalue  FROM xs_childdictionary k  WHERE f.xs_car_brand = k.child_id) AS xs_car_brand," +
				"  g.xs_model_name, a.xs_car_sel_transaction_money,  h.xs_distributor_name," +
				"  (SELECT k.datavalue  FROM xs_childdictionary k WHERE a.audit = k.child_id) AS audits," +
				"  a.out_id,  a.xs_car_give_up,  a.iszh_project,  a.zh_projec_remark,  zh.zh_project_date, " +	
				" zh.zh_project_person,  su.STF_NAME,  zh.zh_project_code,  zh.audit," +
				"  (SELECT  k.datavalue  FROM xs_childdictionary k  WHERE zh.audit = k.child_id) AS examinName," +
				"  zh.zh_project_reckoning," +
				" (SELECT  k.datavalue  FROM xs_childdictionary k   WHERE zh.zh_project_reckoning = k.child_id) AS reckName," +
				"  co.account_state, d.xs_custom_telephone,a.sell_code,co.account_code " +
				" FROM xs_car_sell_info a " +
				"  join xs_sell_flow_control flow on flow.xsfcontrol_document=a.sell_code and" +
				" flow.xsfcontrol_car_id=a.xs_car_id " +
				"  JOIN xs_custom_info d   ON a.custom_id = d.custom_id " +
				"  JOIN xs_car_info f  ON a.xs_car_id = f.xs_car_id" +
				" AND f.xs_car_sell_state IN	(SELECT child.child_id  FROM xs_childdictionary child, xs_parentdictionary parent"+
                " WHERE child.parent_id = parent.parent_id   " +
                " AND parent.dataKey = '"+Contstants.SELLSTATE.BASE_SELLSTATE+"'   AND child.dataKey IN('"+
                Contstants.SELLSTATE.NOTSOLD+"'," +
                		"'"+Contstants.SELLSTATE.SELLOUT+"'," +
                				"'"+Contstants.SELLSTATE.AFTERSELL+"'))"+
				"  LEFT JOIN xs_car_model g  ON f.xs_car_model_id = g.xs_model_id" +
				"  LEFT JOIN xs_distributor_info h  ON f.xs_distributor_id = h.xs_distributor_id" );
			if(sellZhProjectVo.getIszhProject()!=null && !("".equals(sellZhProjectVo.getIszhProject())) && "true".equals(sellZhProjectVo.getIszhProject())){
				sql.append("  JOIN xs_sell_zh_project zh  ON zh.xs_car_sel_id = a.xs_car_sel_id ");
			}else{
				sql.append("  LEFT JOIN xs_sell_zh_project zh  ON zh.xs_car_sel_id = a.xs_car_sel_id" );
				}
				sql.append("  LEFT JOIN bas_stuff su  ON zh.zh_project_person = su.STF_ID" +
				"  LEFT JOIN xs_sell_account co" +
				"  ON co.account_type_id = zh.zh_project_code " +
				" WHERE (a.xs_car_give_up IS NULL OR a.xs_car_give_up=0) " +
//				"and a.audit=(SELECT child.child_id FROM xs_childdictionary  child, " +
//						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
//						"AND parent.dataKey='"+Contstants.BASE_SELL.ADUIT+"' AND " +
//								"child.dataKey='"+Contstants.BASE_SELL.ADUIT1+"'" +
//						")  " +
						"");
		//企业编号
		if(sellZhProjectVo.getEnterpriseId()!=null && !sellZhProjectVo.getEnterpriseId().equals("")){
			sql.append(" AND a.enterprise_Id="+sellZhProjectVo.getEnterpriseId()+"");
		}	
		if (sellZhProjectVo.getXs_Car_Sel_Data() != null
				&& !sellZhProjectVo.getXs_Car_Sel_Data().equals("")) {
					sql.append(" and DATE(A.Xs_Car_Sel_Data) >= '" +sellZhProjectVo.getXs_Car_Sel_Data() + "'");
				} 
		if (sellZhProjectVo.getXs_Car_Sel_Data2() != null
				&& !sellZhProjectVo.getXs_Car_Sel_Data2().equals("")) {
					sql.append(" and DATE(A.Xs_Car_Sel_Data) <= '" +sellZhProjectVo.getXs_Car_Sel_Data2() + "'");
		}
		if(sellZhProjectVo.getIszhProject()!=null && !("".equals(sellZhProjectVo.getIszhProject())) && "true".equals(sellZhProjectVo.getIszhProject())){

			if((sellZhProjectVo.getXs_Car_Sel_Data() == null
					|| sellZhProjectVo.getXs_Car_Sel_Data().equals("")) 
					&& (sellZhProjectVo.getXs_Car_Sel_Data2() == null
					|| sellZhProjectVo.getXs_Car_Sel_Data2().equals(""))){
				sql.append(" and DATE(A.Xs_Car_Sel_Data) BETWEEN '" + sdate + "' AND '"
				+ edate + "'");
			}
		}
		if (sellZhProjectVo.getZhProjectDate() != null
				&& !sellZhProjectVo.getZhProjectDate().equals("")) {
					sql.append(" and DATE(zh.zh_project_date) >= '" +sellZhProjectVo.getZhProjectDate() + "'");
				} 
		if (sellZhProjectVo.getZhProjectDate2() != null
				&& !sellZhProjectVo.getZhProjectDate2().equals("")) {
					sql.append(" and DATE(zh.zh_project_date) <= '" +sellZhProjectVo.getZhProjectDate2() + "'");
		}
		if(sellZhProjectVo.getXs_Car_Sel_Id()!=null && !sellZhProjectVo.getXs_Car_Sel_Id().equals("")){
			sql.append( "  and a.xs_car_sel_id='"+sellZhProjectVo.getXs_Car_Sel_Id()+"'");
		}
		if(sellZhProjectVo.getXs_Custom_Name()!=null && !sellZhProjectVo.getXs_Custom_Name().equals("")){
			sql.append( "  and d.Xs_Custom_Name like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getXs_Custom_Name().trim())+"%'");
		}
	
		if(sellZhProjectVo.getXs_Car_Vin_Number()!=null && !sellZhProjectVo.getXs_Car_Vin_Number().equals("")){
			sql.append( " and f.Xs_Car_Vin_Number like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getXs_Car_Vin_Number().trim())+"%'");
		}
		if(sellZhProjectVo.getXs_Car_Ocn()!=null && !sellZhProjectVo.getXs_Car_Ocn().equals("")){
			sql.append( " and f.Xs_Car_Ocn like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getXs_Car_Ocn().trim())+"%'");
		}
		
		if(sellZhProjectVo.getZhProjectCode()!=null && !sellZhProjectVo.getZhProjectCode().equals("")){
			sql.append("  and zh.zh_project_code like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getZhProjectCode().trim())+"%'");
		}
		if(sellZhProjectVo.getSellCode()!=null && !sellZhProjectVo.getSellCode().equals("")){
			sql.append("  and a.sell_code like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getSellCode().trim())+"%'");
		}
	
//		if(sellZhProjectVo.getIszhProject()!=null && !("".equals(sellZhProjectVo.getIszhProject())) && "false".equals(sellZhProjectVo.getIszhProject())){
//			sql.append( " and (a.iszh_project IS  NULL  or a.iszh_project=(SELECT child.child_id FROM xs_childdictionary  child, " +
//						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
//						"AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
//								"child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE2+"'" +
//						") )");
//		}
		if(sellZhProjectVo.getIszhProject()!=null &&  "true".equals(sellZhProjectVo.getIszhProject())){
			sql.append("  and (a.iszh_project IS NOT NULL  or a.iszh_project=(SELECT child.child_id FROM xs_childdictionary  child, " +
						"xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
						"AND parent.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE+"' AND " +
								"child.dataKey='"+Contstants.BASE_SELL.PAYMENTSTATE1+"'" +
						") ) GROUP BY zh.zh_project_code ") ;
		}else{
			sql.append("  GROUP BY a.xs_car_sel_id");
		}
		List<Object[]> lst=sellZhProjectDao.createSQLQuery(sql.toString(), sellZhProjectVo.getPage(), sellZhProjectVo.getRows());
		List<SellZhProjectVo> rows=new ArrayList<SellZhProjectVo>();
		if(lst!=null && lst.size()>0){
			for (Object [] obj:lst) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				SellZhProjectVo vo = new SellZhProjectVo();
				if(obj[0]!=null){vo.setXs_Car_Sel_Id((Integer)obj[0]);}
				if(obj[1]!=null){vo.setXs_Car_Sel_Data(obj[1].toString());}
				if(obj[2]!=null){vo.setXs_Custom_Name(obj[2].toString());}
				if(obj[3]!=null){vo.setXs_Car_Vin_Number(obj[3].toString());}
				if(obj[4]!=null){vo.setXs_Car_Ocn(obj[4].toString());}
				if(obj[5]!=null){vo.setXs_Car_Brand(obj[5].toString());}
				if(obj[6]!=null){vo.setXs_Model_Name(obj[6].toString());}
				if(obj[7]!=null){vo.setXs_Car_Sel_Transaction_Money(obj[7].toString());}
				if(obj[8]!=null){vo.setXs_Distributor_Name(obj[8].toString());}
				if(obj[9]!=null){vo.setExamine(obj[9].toString());}
				if(obj[10]!=null){vo.setOut_Id(obj[10].toString());}
				if(obj[11]!=null){vo.setXs_Car_Give_Up(obj[11].toString());}
				if(obj[12]!=null){vo.setIszhProject(obj[12].toString());}
				if(obj[13]!=null){vo.setZhProjecRemark(obj[13].toString());}
				if(obj[14]!=null){vo.setZhProjectDate(fmt.format(obj[14]));}
				if(obj[15]!=null){vo.setZhProjectPerson((Integer)obj[15]);}
				if(obj[16]!=null){vo.setStfName(obj[16].toString());}
				if(obj[17]!=null){vo.setZhProjectCode(obj[17].toString());}
				if(obj[18]!=null){vo.setAudit((Integer)obj[18]);}
				if(obj[19]!=null){vo.setAuditName(obj[19].toString());}
				if(obj[20]!=null){vo.setZhProjectReckoning((Integer)obj[20]);}
				if(obj[21]!=null){vo.setReckoningName(obj[21].toString());}
				if(obj[22]!=null){vo.setStates((Integer)obj[22]);}
				if(obj[23]!=null){vo.setMobilephone(obj[23].toString());}
				if(obj[24]!=null){vo.setSellCode(obj[24].toString());}
				if(obj[25]!=null){vo.setAccountCode(obj[25].toString());}
				rows.add(vo);
				}
			}	
		int total =sellZhProjectDao.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	@Log(systemName="销售系统", moduleName="装潢(销售及赠送)",opertype="审核")

	public void updateExamin(SellZhProjectVo sellZhProjectVo) throws Exception {
	
		List<Object> lst=sellZhProjectDao.find("from XsSellZhProject   where zhProjectCode='"+sellZhProjectVo.getZhProjectCode()+"'");;
		if(lst!=null && lst.size()>0){
			for(Object obj:lst){
				XsSellZhProject sellPro=(XsSellZhProject)obj;
				XsChilddictionary chi = new XsChilddictionary();
				if(Integer.parseInt(sellPro.getXsChilddictionaryByAudit().getChildId().toString())==
					Integer.parseInt(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId()).toString())){
					chi.setChildId(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT1,sellZhProjectVo.getEnterpriseId()));
					sellPro.setXsChilddictionaryByAudit(chi);
					setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"审核了单号为【"+sellZhProjectVo.getZhProjectCode()+"】的装潢单！");

				}else{
					chi.setChildId(baseTagDAO.getChildId(Contstants.BASE_SELL.ADUIT,Contstants.BASE_SELL.ADUIT2,sellZhProjectVo.getEnterpriseId()));
					sellPro.setXsChilddictionaryByAudit(chi);
					setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"取消审核了单号为【"+sellZhProjectVo.getZhProjectCode()+"】的装潢单！");

				}
				sellZhProjectDao.merge(sellPro);
			}
		
		}
		
	}
	
	@Log(systemName="销售系统", moduleName="装潢(销售及赠送)",opertype="转结算")

	public void updateSum(SellZhProjectVo sellZhProjectVo) throws Exception {
		List<Object> list=sellZhProjectDao.findExaminState(sellZhProjectVo);
		double sum=0;
		if(list!=null && list.size()>0){
			XsChilddictionary chi = new XsChilddictionary();
			for (Object object : list) {
				XsSellZhProject sellPro=(XsSellZhProject)object;
				chi.setChildId(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE1,sellZhProjectVo.getEnterpriseId()));
				sellPro.setXsChilddictionaryByZhProjectReckoning(chi);
				sellZhProjectDao.merge(sellPro);
				if(Integer.parseInt(sellPro.getRemark().toString())!=
					Integer.parseInt((baseTagDAO.getChildId(Contstants.BASE_SELL.ZHREMARK,Contstants.BASE_SELL.ZHREMARK2,sellZhProjectVo.getEnterpriseId()).toString()))){
					sum+=sellPro.getDecorateSell();			
				}				
			}
			XsSellAccount account=(XsSellAccount) sellZhProjectDao.get(" from  XsSellAccount account where account.accountTypeId='"+sellZhProjectVo.getZhProjectCode()+"'");
			if(account==null){
			int types=(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE,Contstants.BASE_SELL.ACCOUNTTYPE3,sellZhProjectVo.getEnterpriseId()));//装潢
			accountDao.saveSellAccount(sellZhProjectVo.getXs_Car_Sel_Id(),sellZhProjectVo.getZhProjectCode(), types, sum,sellZhProjectVo.getZhProjecRemark(),sellZhProjectVo.getEnterpriseId());
			}else{
				account.setAccountMoney(sum);
				//account.setAccountGyration(baseTagDAO.getChildId(Contstants.BASE_SELL.PAYMENTSTATE,Contstants.BASE_SELL.PAYMENTSTATE2));
				account.setRemark(sellZhProjectVo.getZhProjecRemark());
				sellZhProjectDao.merge(account);
			}
				
		}
		setContent(""+SystemUser.getUser().getBasStuff().getStfName()+"结算了单号为【"+sellZhProjectVo.getZhProjectCode()+"】的装潢单！");

}
	//车辆出库：新增选销售单
	
	public Datagrid getSellList(SellZhProjectVo sellZhProjectVo)
			throws Exception {
		Datagrid dg = new Datagrid();
		StringBuffer sql =new StringBuffer( "SELECT a.xs_car_sel_id,b.xs_car_id,a.sell_code, a.custom_id," +
				"c.xs_custom_name,b.details_id,d.xs_car_code,e.xs_model_name,d.xs_car_vin_number	" +
				"FROM xs_car_sell_info a " +
				"JOIN   xs_custom_info c ON  c.custom_id = a.custom_id " +
				"JOIN   xs_car_info  d ON d.xs_car_id = a.xs_car_id " +
				"JOIN  xs_sell_instorehouse_del b ON d.xs_car_id = b.xs_car_id" +
				" JOIN  xs_car_model E ON e.xs_model_id = d.xs_car_model_id " +
				" JOIN   xs_sell_flow_control flow ON flow.xsfcontrol_document = a.sell_code" +
				"  AND flow.xsfcontrol_car_id = a.xs_car_id " +
				"where   a.out_id=0   AND b.details_id NOT IN( SELECT details_id  " +
				"FROM xs_sell_retreat WHERE instorehouse_type='"+baseTagDAO.findChildCon(Contstants.INSTORESTYLE.BACK,Contstants.INSTORESTYLE.PARENTINSTORE,sellZhProjectVo.getEnterpriseId())+"') " +
				"and a.audit="+baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,sellZhProjectVo.getEnterpriseId())
				 );
		
		//企业编号
		if(sellZhProjectVo.getEnterpriseId()!=null && !sellZhProjectVo.getEnterpriseId().equals("")){
			sql.append(" AND a.enterprise_id ="+sellZhProjectVo.getEnterpriseId()+"");
		}
		if(sellZhProjectVo.getSellCode()!=null && !sellZhProjectVo.getSellCode().equals("")){
			sql.append( "  and a.sell_code like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getSellCode().trim())+"%'");
		}
		if(sellZhProjectVo.getXs_Custom_Name()!=null && !sellZhProjectVo.getXs_Custom_Name().equals("")){
			sql.append( "  and d.Xs_Custom_Name like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getXs_Custom_Name().trim())+"%'");
		}
		if(sellZhProjectVo.getXs_Car_Vin_Number()!=null && !sellZhProjectVo.getXs_Car_Vin_Number().equals("")){
			sql.append( "  and d.xs_car_vin_number like '%"+StringEscapeUtils.escapeSql(sellZhProjectVo.getXs_Car_Vin_Number().trim())+"%'");
		}


		List<Object[]> lst=sellZhProjectDao.createSQLQuery(sql.toString(), sellZhProjectVo.getPage(), sellZhProjectVo.getRows());
		List<SellZhProjectVo> rows=new ArrayList<SellZhProjectVo>();
		if(lst!=null && lst.size()>0){
			for (Object [] obj:lst) {
				SellZhProjectVo vo = new SellZhProjectVo();
				if(obj[0]!=null){vo.setXs_Car_Sel_Id((Integer)obj[0]);}
				if(obj[1]!=null){vo.setXsCarId((Integer)obj[1]);}
				if(obj[2]!=null){vo.setSellCode(obj[2].toString());}
				if(obj[3]!=null){vo.setXs_Custom_Id((Integer)obj[3]);}
				if(obj[4]!=null){vo.setXs_Custom_Name(obj[4].toString());}
				if(obj[5]!=null){vo.setDetailsId((Integer)obj[5]);}
				if(obj[6]!=null){vo.setCarCode(obj[6].toString());}
				if(obj[7]!=null){vo.setXs_Model_Name(obj[7].toString());}
				if(obj[8]!=null){vo.setXs_Car_Vin_Number(obj[8].toString());}
				rows.add(vo);
				}
			}	
		int total =sellZhProjectDao.getSQLCount(sql.toString(),null);
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	public Boolean isRefundment(SellZhProjectVo sellZhProjectVo)
			throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.YISHENHE, Contstants.BASE_SELL.ADUIT,sellZhProjectVo.getEnterpriseId());
		if(sellZhProjectVo.getAudit().equals(examine))
			return true;
		return false;
	}
	
	public Boolean isUse(SellZhProjectVo sellZhProjectVo) throws Exception {
		String sql =  "SELECT a.account_type_id FROM Xs_Sell_Account a, xs_sell_zh_project B,Xs_Sell_Flow_Control c " +
		" where a.account_type_id=b.zh_project_code and a.account_code = c.xsfcontrol_document  " +
		" and a.account_type_id ='"+sellZhProjectVo.getZhProjectCode()+"'" ;
		List list = sellZhProjectDao.createSQLQuery(sql);
		if(list!=null  && list.size()>0 ){
			return true;
		}
		return false;
	}
	
	public Boolean isNotRefundment(SellZhProjectVo sellZhProjectVo)
			throws Exception {
		Integer examine=baseTagDAO.findChildCon(Contstants.ADUIT.WEISHENHE, Contstants.BASE_SELL.ADUIT,sellZhProjectVo.getEnterpriseId());
		if(sellZhProjectVo.getAudit().equals(examine))
			return true;
		return false;
	}
	
	public List getZhDelInfor(SellZhProjectVo SellZhProjectVo) throws Exception {
		return sellZhProjectDao.getZhDelInfor(SellZhProjectVo);
	}
	
	public DatagridAnalyze getZhInfor(SellZhProjectVo sellZhProjectVo)
			throws Exception {
		// TODO Auto-generated method stub
		return sellZhProjectDao.getZhInfor(sellZhProjectVo);
	}

	
	
}
