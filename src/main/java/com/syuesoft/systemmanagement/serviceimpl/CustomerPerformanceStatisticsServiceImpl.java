package com.syuesoft.systemmanagement.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.model.BasStuff;
import com.syuesoft.systemmanagement.dao.CustomerPerformanceStatisticsDao;
import com.syuesoft.systemmanagement.service.CustomerPerformanceStatisticsService;
import com.syuesoft.systemmanagement.vo.CustomerPerformanceStatisticsVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.MyBeanUtils;

@Service("customerPerformanceStatisticsService")
public class CustomerPerformanceStatisticsServiceImpl implements
		CustomerPerformanceStatisticsService {
	@Autowired
	private CustomerPerformanceStatisticsDao customerPerformanceStatisticsDao;
	@Autowired
	private BasStuffDao basStuffDao;
	@Autowired
	private FrtService frtService;
	
	/**
	 * 索赔结算工时统计detail
	 * */
	
	public Datagrid findClaimsHoursDetail(CustomerPerformanceStatisticsVo cpsVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fcm.claimantm_time,fcm.claimantm_id,fcar.car_license,fct.repitem_name,fct.claimantt_amount");
		sb.append(" FROM fin_claimant_main fcm INNER JOIN fin_claimant_time fct ON fct.claimantm_id=fcm.claimantm_id");
		sb.append(" AND fcm.claimantm_tag="+Contstants.DELETE_TAG.DELETENO+" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(cpsVo.getClaimantmTimeBegin()!=null&&cpsVo.getClaimantmTimeBegin().length()>0)
			sb.append(" AND fcm.claimantm_time>='"+cpsVo.getClaimantmTimeBegin()+"'");
		if(cpsVo.getClaimantmTimeEnd()!=null&&cpsVo.getClaimantmTimeEnd().length()>0)
			sb.append(" AND fcm.claimantm_time<='"+cpsVo.getClaimantmTimeEnd()+"'");
		sb.append(" INNER JOIN fin_claims_receivables fcr ON fcr.claimantm_id=fcm.claimantm_id");
		if((cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)||
				(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)||
					(cpsVo.getBalanceFlag()!=null&&cpsVo.getBalanceFlag()==true)){
			sb.append(" INNER JOIN(");
			sb.append(" SELECT fcr.claimantm_id,fcs.fccs_date");
			sb.append(" FROM fin_claims_receivables fcr INNER JOIN fin_counterclaim_schedule fcs ON fcs.cr_id=fcr.cr_id");
			sb.append(" ORDER BY fcs.fccs_date ASC LIMIT 1");
			sb.append(" ) aa ON aa.claimantm_id=fcm.claimantm_id");			
		}
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND aa.fccs_date>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND aa.fccs_date<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		if(cpsVo.getFinComId()!=null&&cpsVo.getFinComId().length()>0)
			sb.append(" AND fr.fin_com_id="+cpsVo.getFinComId());
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fct.stf_id");
		sb.append(" AND bs.stf_id="+cpsVo.getStfId());
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(),cpsVo.getPage(),cpsVo.getRows());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setClaimantmTime(MyBeanUtils.getInstance().formatString(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setClaimantmId(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setCarLicense(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setItemName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[4].toString()));
				rows.add(cps);
			}
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 索赔结算工时统计main
	 * */
	
	public Datagrid findClaimsHoursMain(CustomerPerformanceStatisticsVo cpsVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT bs.stf_id,bs.stf_name,SUM(fct.claimantt_amount) AS temp1");
		sb.append(" FROM fin_claimant_main fcm INNER JOIN fin_claimant_time fct ON fct.claimantm_id=fcm.claimantm_id");
		sb.append(" AND fcm.claimantm_tag="+Contstants.DELETE_TAG.DELETENO+" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(cpsVo.getClaimantmTimeBegin()!=null&&cpsVo.getClaimantmTimeBegin().length()>0)
			sb.append(" AND fcm.claimantm_time>='"+cpsVo.getClaimantmTimeBegin()+"'");
		if(cpsVo.getClaimantmTimeEnd()!=null&&cpsVo.getClaimantmTimeEnd().length()>0)
			sb.append(" AND fcm.claimantm_time<='"+cpsVo.getClaimantmTimeEnd()+"'");
		sb.append(" INNER JOIN fin_claims_receivables fcr ON fcr.claimantm_id=fcm.claimantm_id");
		if((cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)||
				(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)||
					(cpsVo.getBalanceFlag()!=null&&cpsVo.getBalanceFlag()==true)){
			sb.append(" INNER JOIN(");
			sb.append(" SELECT fcr.claimantm_id,fcs.fccs_date");
			sb.append(" FROM fin_claims_receivables fcr INNER JOIN fin_counterclaim_schedule fcs ON fcs.cr_id=fcr.cr_id");
			sb.append(" ORDER BY fcs.fccs_date ASC LIMIT 1");
			sb.append(" ) aa ON aa.claimantm_id=fcm.claimantm_id");			
		}
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND aa.fccs_date>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND aa.fccs_date<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		if(cpsVo.getFinComId()!=null&&cpsVo.getFinComId().length()>0)
			sb.append(" AND fr.fin_com_id="+cpsVo.getFinComId());
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fct.stf_id");
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" AND bs.stf_id="+cpsVo.getStfId());
		sb.append(" GROUP BY bs.stf_id");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setStfId(Short.parseShort(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[2].toString()));
				rows.add(cps);
			}
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(Integer.MAX_VALUE);
		return dg;
	}

	/**
	 * 维修人员业务统计汇总
	 * */
	
	public DatagridAnalyze findServicePersonMain(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT dd.stf_id,dd.stf_name,");
		sb.append(" SUM(dd.temp1),SUM(dd.temp2),SUM(dd.temp3),SUM(dd.temp4),");
		sb.append(" SUM(dd.temp5),SUM(dd.temp6)");
		sb.append(" FROM (");
		sb.append(" SELECT cc.stf_id,cc.stf_name,");
		sb.append(" (SELECT CASE WHEN cc.temp1!='' THEN cc.temp1 ELSE 0 END ) AS temp1,");
		sb.append(" (SELECT CASE WHEN cc.temp2!='' THEN cc.temp2 ELSE 0 END ) AS temp2,");
		sb.append(" (SELECT CASE WHEN cc.temp3!='' THEN cc.temp3 ELSE 0 END ) AS temp3,");
		sb.append(" (SELECT CASE WHEN cc.temp4!='' THEN cc.temp4 ELSE 0 END ) AS temp4,");
		sb.append(" (SELECT CASE WHEN cc.temp5!='' THEN cc.temp5 ELSE 0 END ) AS temp5,");
		sb.append(" (SELECT CASE WHEN cc.temp6!='' THEN cc.temp6 ELSE 0 END ) AS temp6");
		sb.append(" FROM(");
		sb.append(" SELECT bs.stf_id,bs.stf_name,aa.wktime_amount AS temp1,");
		sb.append(" (aa.wktime_amount*aa.preclr_wktime_rate) AS temp2,");
		sb.append(" bb.parts_rate_amount AS temp3,");
		sb.append(" (bb.parts_rate_amount*bb.preclr_material_rate) AS temp4,");
		sb.append(" bb.temp1 AS temp5,bb.temp2 AS temp6");
		sb.append(" FROM (SELECT * FROM bas_stuff bs WHERE bs.enterprise_id="+cpsVo.getEnterpriseId()+" AND bs.REPGRP_ID IS NOT NULL) bs LEFT OUTER JOIN");
		sb.append(" (");
		sb.append(" SELECT fpw.stf_id,fpw.wktime_amount,fpc.preclr_wktime_rate"); 
		sb.append(" FROM frt_pre_wktime fpw INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES); 
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time >= '"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_reception fr on fr.reception_id=fpc.reception_id");
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" ) aa ON aa.stf_id=bs.stf_id");
		sb.append(" LEFT OUTER  JOIN");
		sb.append(" (");
		sb.append(" SELECT som.picking_member,fpp.parts_rate_amount,");
		sb.append(" fpc.preclr_material_rate,(fpp.parts_notax_cost*fpp.parts_count) AS temp1,");
		sb.append(" (fpp.parts_tax_cost*fpp.parts_count) AS temp2 FROM");
		sb.append(" frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id"); 
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN st_out_main som  ON fr.reception_id=som.cer_no");
		sb.append(" INNER JOIN st_out_item soi ON soi.stom_id=som.stom_id");
		sb.append(" INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpc.preclr_id ");
		sb.append(" AND fpp.parts_id=soi.parts_id AND fpp.store_id=soi.store_id");
		sb.append(" ) bb ON bb.picking_member=bs.stf_id");
		sb.append(" ) cc ");
		sb.append(" ) dd GROUP BY dd.stf_id");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<CustomerPerformanceStatisticsVo> footers=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(), cpsVo.getPage(), cpsVo.getRows());
		CustomerPerformanceStatisticsVo cpsv=new CustomerPerformanceStatisticsVo();
		cpsv.setItemAmount(0d);
		cpsv.setItemRebateAmount(0d);
		cpsv.setPartsAmount(0d);
		cpsv.setPartsRebateAmount(0d);
		cpsv.setNoTaxAmount(0d);
		cpsv.setTaxAmount(0d);
		cpsv.setStfName("合计");
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setStfId(Short.parseShort(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[2].toString()));
				cpsv.setItemAmount(cpsv.getItemAmount()+cps.getItemAmount());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setItemRebateAmount(Double.parseDouble(obj[3].toString()));
				cpsv.setItemRebateAmount(cpsv.getItemRebateAmount()+cps.getItemRebateAmount());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setPartsAmount(Double.parseDouble(obj[4].toString()));
				cpsv.setPartsAmount(cpsv.getPartsAmount()+cps.getPartsAmount());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setPartsRebateAmount(Double.parseDouble(obj[5].toString()));
				cpsv.setPartsRebateAmount(cpsv.getPartsRebateAmount()+cps.getPartsRebateAmount());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setNoTaxAmount(Double.parseDouble(obj[6].toString()));
				cpsv.setNoTaxAmount(cpsv.getNoTaxAmount()+cps.getNoTaxAmount());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setTaxAmount(Double.parseDouble(obj[7].toString()));
				cpsv.setTaxAmount(cpsv.getTaxAmount()+cps.getTaxAmount());
				rows.add(cps);
			}
		footers.add(cpsv);
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}

	/**
	 * 结算工时查询(按维修员)
	 * */
	
	public DatagridAnalyze findBalanceHoursQuery(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT brp.repgrp_id,brp.repgrp_name,SUM(fpw.wktime_num) AS temp1");
		sb.append(" FROM  bas_repair_group brp INNER JOIN bas_stuff bs ON bs.repgrp_id=brp.repgrp_id");
		if(cpsVo.getServiceGroupId()!=null&&cpsVo.getServiceGroupId().length()>0)
			sb.append(" AND brp.repgrp_id="+cpsVo.getServiceGroupId());
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.stf_id=bs.stf_id");
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" AND bs.stf_id="+cpsVo.getStfId());
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id  AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		sb.append(" GROUP BY brp.repgrp_id");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<CustomerPerformanceStatisticsVo> footers=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(), cpsVo.getPage(), cpsVo.getRows());
		CustomerPerformanceStatisticsVo cpsv=new CustomerPerformanceStatisticsVo();
		cpsv.setItemTime(0d);
		cpsv.setServiceGroupName("合计");
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setServiceGroupId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setServiceGroupName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[2].toString()));
				cpsv.setItemTime(cpsv.getItemTime()+cps.getItemTime());
				cps.setStfName("查看详情");
				cps.setState("closed");
				rows.add(cps);
			}
		cpsv.setStfName("");
		footers.add(cpsv);
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	/**
	 * 结算工时查询-子项信息(按维修员)
	 * */
	
	public List<CustomerPerformanceStatisticsVo> findBalanceHoursQueryChild(CustomerPerformanceStatisticsVo cpsVo)
			throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bs.stf_name,fr.reception_id,fcar.car_license,rt.rept_name,");
		sb.append(" fpw.repitem_name,fpw.wktime_num,fc.custom_name,fpc.preclr_time");
		sb.append(" FROM bas_stuff bs INNER JOIN frt_pre_wktime fpw ON fpw.stf_id=bs.stf_id");
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" AND bs.stf_id="+cpsVo.getStfId());
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id  AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		sb.append(" WHERE bs.repgrp_id="+cpsVo.getServiceGroupId());
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		CustomerPerformanceStatisticsVo cps=null;
		Set<String> temp=new HashSet<String>();
		boolean flag=false;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0){
					if(temp!=null&&temp.size()>0){
						for (String string : temp) {
							if(!(obj[0].toString().equals(string))){
								flag=true;
								break;
							}
						}
						if(flag==true){
							cps.setStfName(obj[0].toString());
							flag=false;
						}else{
							cps.setStfName("");
							cps.setIconCls("");
						}
					}else{
						cps.setStfName(obj[0].toString());
						temp.add(cps.getStfName());
					}
				}
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setReceptionId(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setCarLicense(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setReptName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[5].toString()));					
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setCustomName(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[7].toString()));
				cps.set_parentId(cpsVo.getServiceGroupId());
				cps.setState("open");
				rows.add(cps);
			}
		return rows;
	}
	/**
	 * 结算工时查询 (按接待员)
	 * */
	
	public DatagridAnalyze findBalanceHoursQueryForRecivePerson(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bs.stf_id,bs.stf_name,SUM(fpw.wktime_num) AS temp1");
		sb.append(" FROM  bas_stuff bs INNER JOIN frt_reception fr ON fr.receptor=bs.stf_id");
		if(cpsVo.getReceivePerson()!=null&&cpsVo.getReceivePerson().toString().length()>0)
			sb.append(" AND bs.stf_id="+cpsVo.getReceivePerson());
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fr.reception_id=fpc.reception_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.preclr_id=fpc.preclr_id");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		sb.append(" GROUP BY bs.stf_id");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<CustomerPerformanceStatisticsVo> footers=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(), cpsVo.getPage(), cpsVo.getRows());
		CustomerPerformanceStatisticsVo cpsv=new CustomerPerformanceStatisticsVo();
		cpsv.setItemTime(0d);
		cpsv.setStfName("合计");
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setStfId(Short.parseShort(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[2].toString()));
				cpsv.setItemTime(cpsv.getItemTime()+cps.getItemTime());
				cps.setReceptionId("查看详情");
				cps.setState("closed");
				rows.add(cps);
			}
		cpsv.setReceptionId("");
		footers.add(cpsv);
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	/**
	 * 结算工时查询-子项信息(按接待员)
	 * */
	
	public List<CustomerPerformanceStatisticsVo> findBalanceHoursQueryChildForRecivePerson(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT fr.reception_id,fcar.car_license,rt.rept_name,");
		sb.append(" fpw.repitem_name,fpw.wktime_num,fc.custom_name,fpc.preclr_time");
		sb.append(" FROM  bas_stuff bs INNER JOIN frt_reception fr ON fr.receptor=bs.stf_id");
		if(cpsVo.getReceivePerson()!=null&&cpsVo.getReceivePerson().toString().length()>0)
			sb.append(" AND bs.stf_id="+cpsVo.getReceivePerson());
		if(cpsVo.getReceptionId()!=null&&cpsVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(cpsVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fr.reception_id=fpc.reception_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.preclr_id=fpc.preclr_id");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		sb.append(" WHERE bs.stf_id="+cpsVo.getReceivePerson());
		
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setReceptionId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setReptName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setItemName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[4].toString()));					
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setCustomName(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[6].toString()));
				cps.set_parentId(cpsVo.getReceivePerson());
				cps.setState("open");
				rows.add(cps);
			}
		return rows;
	}
	/*****************************************/
	/**
	 * 维修人员业绩统计(按维修员)
	 * */
	
	public DatagridAnalyze findServicePersonScore(CustomerPerformanceStatisticsVo cpsVo)
			throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bb.*,(bb.temp2+temp3)");
		sb.append(" FROM (");
		sb.append(" SELECT cc.stf_id,cc.stf_name,cc.repgrp_name,SUM(cc.WKTIME_NUM) AS temp1,SUM(cc.WKTIME_AMOUNT) AS temp2,");
		sb.append(" cc.temp3,cc.temp4,cc.temp5,SUM(cc.temp6) AS temp6,sum(cc.temp7) as temp7");
		sb.append(" FROM(");
		sb.append(" SELECT bs.stf_id,bs.stf_name,brp.repgrp_name,fpw.WKTIME_NUM,fpw.WKTIME_AMOUNT,");	
		sb.append(" (SELECT CASE WHEN aa.temp3!='' THEN aa.temp3 ELSE 0 END ) AS temp3,");	
		sb.append(" (SELECT CASE WHEN aa.temp4!='' THEN aa.temp4 ELSE 0 END ) AS temp4,");	
		sb.append(" (SELECT CASE WHEN aa.temp5!='' THEN aa.temp5 ELSE 0 END ) AS temp5,");	
		sb.append(" (fpw.WKTIME_AMOUNT*(1-fpc.PRECLR_WKTIME_RATE)) AS temp6,");
		sb.append(" (fpw.WKTIME_AMOUNT*fpc.PRECLR_WKTIME_RATE) AS temp7");
		sb.append(" FROM bas_stuff bs LEFT OUTER JOIN bas_repair_group brp ON brp.repgrp_id=bs.repgrp_id");
		if(cpsVo.getServiceGroupId()!=null&&cpsVo.getServiceGroupId().length()>0)
			sb.append(" AND brp.repgrp_id="+cpsVo.getServiceGroupId());
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.stf_id=bs.stf_id");	
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND fpw.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id  AND fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		if(cpsVo.getReptId()!=null&&cpsVo.getReptId().length()>0)
			sb.append(" AND fr.rept_id="+cpsVo.getReptId());
		if(cpsVo.getRcptBranch()!=null&&cpsVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+cpsVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(cpsVo.getCarTypeId()!=null&&cpsVo.getCarTypeId().length()>0)
			sb.append(" AND bct.ctype_id="+cpsVo.getCarTypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		if(cpsVo.getCarBrandId()!=null&&cpsVo.getCarBrandId().length()>0)
			sb.append(" AND bcb.cbrd_id="+cpsVo.getCarBrandId());
		sb.append(" INNER JOIN (SELECT * FROM bas_stuff ) stuff ON stuff.stf_id=fr.receptor");	
		sb.append(" LEFT OUTER JOIN ");	
		sb.append(" (");
		sb.append(" SELECT som.cer_no,SUM(fpp.parts_amount) AS temp3,SUM(fpp.parts_notax_cost*fpp.parts_count) AS temp4,");	
		sb.append(" SUM(fpp.parts_tax_cost*fpp.parts_count) AS temp5, som.PICKING_MEMBER FROM");
		sb.append(" st_out_main som INNER JOIN st_out_item soi ON soi.stom_id=som.stom_id");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND soi.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_parts fpp ON fpp.parts_id=soi.parts_id AND fpp.store_id=soi.store_id");  
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpp.preclr_id");       
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");     
		sb.append(" AND fr.reception_id=som.cer_no");      
		sb.append(" GROUP BY som.cer_no");
		sb.append(" ) aa ON fr.reception_id=aa.cer_no AND bs.stf_id=aa.picking_member");	
		sb.append(" ) cc");
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" WHERE cc.stf_id="+cpsVo.getStfId());
		sb.append(" GROUP BY cc.stf_id");
		sb.append(" ) bb");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<CustomerPerformanceStatisticsVo> footers=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(), cpsVo.getPage(), cpsVo.getRows());
		CustomerPerformanceStatisticsVo cpsv=new CustomerPerformanceStatisticsVo();
		cpsv.setItemTime(0d);
		cpsv.setItemAmount(0d);
		cpsv.setPartsAmount(0d);
		cpsv.setItemRebateAmount(0d);
		cpsv.setItemFactAmount(0d);
		cpsv.setSumAmount(0d);
		cpsv.setNoTaxAmount(0d);
		cpsv.setTaxAmount(0d);
		cpsv.setStfName("合计");
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setStfId(Short.parseShort(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setServiceGroupName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[3].toString()));
				cpsv.setItemTime(cpsv.getItemTime()+cps.getItemTime());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[4].toString()));
				cpsv.setItemAmount(cpsv.getItemAmount()+cps.getItemAmount());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setPartsAmount(Double.parseDouble(obj[5].toString()));
				cpsv.setPartsAmount(cpsv.getPartsAmount()+cps.getPartsAmount());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setNoTaxAmount(Double.parseDouble(obj[6].toString()));
				cpsv.setNoTaxAmount(cpsv.getNoTaxAmount()+cps.getNoTaxAmount());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setTaxAmount(Double.parseDouble(obj[7].toString()));
				cpsv.setTaxAmount(cpsv.getTaxAmount()+cps.getTaxAmount());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					cps.setItemRebateAmount(Double.parseDouble(obj[8].toString()));
				cpsv.setItemRebateAmount(cpsv.getItemRebateAmount()+cps.getItemRebateAmount());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					cps.setItemFactAmount(Double.parseDouble(obj[9].toString()));
				cpsv.setItemFactAmount(cpsv.getItemFactAmount()+cps.getItemFactAmount());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					cps.setSumAmount(Double.parseDouble(obj[10].toString()));
				cpsv.setSumAmount(cpsv.getSumAmount()+cps.getSumAmount());
				cps.setReceptionId("查看工单详情");
				cps.setState("closed");
				rows.add(cps);
			}
		cpsv.setReceptionId("");
		footers.add(cpsv);
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	/**
	 * 维修人员业绩统计-子项信息(按维修员)
	 * */
	
	public List<CustomerPerformanceStatisticsVo> findServicePersonScoreChild(CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bb.*,(bb.temp2+temp3)");
		sb.append(" FROM (");		
		sb.append(" SELECT cc.reception_id,cc.custom_name,cc.car_license,cc.ctype_name,");
		sb.append(" SUM(cc.WKTIME_NUM) AS temp1,SUM(cc.WKTIME_AMOUNT) AS temp2,");
		sb.append(" cc.temp3,cc.temp4,cc.temp5,");
		sb.append(" SUM(cc.temp6) AS temp6,SUM(cc.temp7) AS temp7,cc.stf_name");
		sb.append(" FROM (");
		sb.append(" SELECT fr.reception_id,fc.custom_name,fcar.car_license,bct.ctype_name,");	
		sb.append(" fpw.WKTIME_NUM,fpw.WKTIME_AMOUNT,");	
		sb.append(" (SELECT CASE WHEN aa.temp3!='' THEN aa.temp3 ELSE 0 END ) AS temp3,");	
		sb.append(" (SELECT CASE WHEN aa.temp4!='' THEN aa.temp4 ELSE 0 END ) AS temp4,");	
		sb.append(" (SELECT CASE WHEN aa.temp5!='' THEN aa.temp5 ELSE 0 END ) AS temp5,");	
		sb.append(" (fpw.WKTIME_AMOUNT*(1-fpc.PRECLR_WKTIME_RATE)) AS temp6,");
		sb.append(" (fpw.WKTIME_AMOUNT*fpc.PRECLR_WKTIME_RATE) AS temp7,bs.stf_name");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id AND fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		if(cpsVo.getReptId()!=null&&cpsVo.getReptId().length()>0)
			sb.append(" AND fr.rept_id="+cpsVo.getReptId());
		if(cpsVo.getRcptBranch()!=null&&cpsVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+cpsVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");	
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");	
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");
		}
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(cpsVo.getCarTypeId()!=null&&cpsVo.getCarTypeId().length()>0)
			sb.append(" AND bct.ctype_id="+cpsVo.getCarTypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		if(cpsVo.getCarBrandId()!=null&&cpsVo.getCarBrandId().length()>0)
			sb.append(" AND bcb.cbrd_id="+cpsVo.getCarBrandId());
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.preclr_id=fpc.preclr_id ");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND fpw.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" LEFT OUTER JOIN ");	
		sb.append(" (");	
		sb.append(" SELECT som.cer_no,SUM(fpp.parts_amount) AS temp3,SUM(fpp.parts_notax_cost*fpp.parts_count) AS temp4,");	
		sb.append(" SUM(fpp.parts_tax_cost*fpp.parts_count) AS temp5, som.PICKING_MEMBER FROM");
		sb.append(" st_out_main som INNER JOIN st_out_item soi ON soi.stom_id=som.stom_id");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND soi.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_parts fpp ON fpp.parts_id=soi.parts_id AND fpp.store_id=soi.store_id");  
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpp.preclr_id");       
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");     
		sb.append(" AND fr.reception_id=som.cer_no");      
		sb.append(" GROUP BY som.cer_no");
		sb.append(" ) aa ON fr.reception_id=aa.cer_no AND aa.picking_member="+cpsVo.getStfId());	
		sb.append(" WHERE fpw.stf_id="+cpsVo.getStfId());	
		sb.append(" ) cc");
		sb.append(" GROUP BY cc.reception_id");
		sb.append(" ) bb");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setReceptionId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setCustomName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setCarLicense(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setCarTypeName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[4].toString()));
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[5].toString()));
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setPartsAmount(Double.parseDouble(obj[6].toString()));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setNoTaxAmount(Double.parseDouble(obj[7].toString()));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					cps.setTaxAmount(Double.parseDouble(obj[8].toString()));
				if(obj[9]!=null&&obj[9].toString().length()>0)
					cps.setItemRebateAmount(Double.parseDouble(obj[9].toString()));
				if(obj[10]!=null&&obj[10].toString().length()>0)
					cps.setItemFactAmount(Double.parseDouble(obj[10].toString()));
				if(obj[11]!=null&&obj[11].toString().length()>0)
					cps.setReceivePerson(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					cps.setSumAmount(Double.parseDouble(obj[12].toString()));
				cps.setItemName(weaveItem(cps.getReceptionId(),cpsVo.getStfId().toString(),cpsVo));
				cps.set_parentId(cpsVo.getStfId().toString());
				cps.setState("open");
				rows.add(cps);
			}
		return rows;
	}
	private String weaveItem(String receptionId,String stfId,CustomerPerformanceStatisticsVo cpsVo) throws Exception{
		BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+stfId);
		String stfName=bs.getStfName();
		StringBuffer sb=new StringBuffer("SELECT fpw.REPITEM_NAME");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id AND fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(cpsVo.getRcptBranch()!=null&&cpsVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+cpsVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(cpsVo.getCarTypeId()!=null&&cpsVo.getCarTypeId().length()>0)
			sb.append(" AND bct.ctype_id="+cpsVo.getCarTypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		if(cpsVo.getCarBrandId()!=null&&cpsVo.getCarBrandId().length()>0)
			sb.append(" AND bcb.cbrd_id="+cpsVo.getCarBrandId());
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.preclr_id=fpc.preclr_id");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND fpw.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" WHERE fpw.stf_id="+stfId+" AND fr.reception_id='"+receptionId+"'");
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		StringBuffer sbr=new StringBuffer("");
		if(list!=null&&list.size()>0){
			for (Object object : list) {
				sbr.append(object.toString()+"("+stfName+"),");
			}
				return sbr.substring(0, sbr.length()-1);			
		}
		return "";
	}
	/**
	 * 维修人员业绩统计-子项信息(按工单号)
	 * */
	
	public List<CustomerPerformanceStatisticsVo> findServicePersonScoreChildForReceptionId(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bb.*,(bb.temp2+temp3)");
		sb.append(" FROM (");
		sb.append(" SELECT cc.stf_id,cc.stf_name,cc.repgrp_name,SUM(cc.WKTIME_NUM) AS temp1,SUM(cc.WKTIME_AMOUNT) AS temp2,");
		sb.append(" cc.temp3,cc.temp4,cc.temp5,SUM(cc.temp6) AS temp6,SUM(cc.temp7) AS temp7");
		sb.append(" FROM(");
		sb.append(" SELECT bs.stf_id,bs.stf_name,brp.repgrp_name,fpw.WKTIME_NUM,fpw.WKTIME_AMOUNT,");
		sb.append(" (SELECT CASE WHEN aa.temp3!='' THEN aa.temp3 ELSE 0 END ) AS temp3,");	
		sb.append(" (SELECT CASE WHEN aa.temp4!='' THEN aa.temp4 ELSE 0 END ) AS temp4,");	
		sb.append(" (SELECT CASE WHEN aa.temp5!='' THEN aa.temp5 ELSE 0 END ) AS temp5,");	
		sb.append(" (fpw.WKTIME_AMOUNT*(1-fpc.PRECLR_WKTIME_RATE)) AS temp6,");
		sb.append(" (fpw.WKTIME_AMOUNT*fpc.PRECLR_WKTIME_RATE) AS temp7");
		sb.append(" FROM bas_stuff bs LEFT OUTER JOIN bas_repair_group brp ON brp.repgrp_id=bs.repgrp_id");	
		if(cpsVo.getServiceGroupId()!=null&&cpsVo.getServiceGroupId().length()>0)
			sb.append(" AND brp.repgrp_id="+cpsVo.getServiceGroupId());
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpw.stf_id=bs.stf_id");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND fpw.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id");	
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id  AND fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		sb.append(" AND fr.reception_id='"+cpsVo.getReceptionId()+"'");		
		if(cpsVo.getRcptBranch()!=null&&cpsVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+cpsVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN (SELECT * FROM bas_stuff ) stuff ON stuff.stf_id=fr.receptor");	
		sb.append(" LEFT OUTER JOIN"); 	
		sb.append(" (");	
		sb.append(" SELECT som.cer_no,SUM(fpp.parts_amount) AS temp3,SUM(fpp.parts_notax_cost*fpp.parts_count) AS temp4,");	
		sb.append(" SUM(fpp.parts_tax_cost*fpp.parts_count) AS temp5, som.PICKING_MEMBER FROM");
		sb.append(" st_out_main som INNER JOIN st_out_item soi ON soi.stom_id=som.stom_id");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND soi.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_parts fpp ON fpp.parts_id=soi.parts_id AND fpp.store_id=soi.store_id");  
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpp.preclr_id");       
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");     
		sb.append(" AND fr.reception_id=som.cer_no");      
		sb.append(" GROUP BY som.cer_no");
		sb.append(" ) aa ON fr.reception_id=aa.cer_no AND bs.stf_id=aa.picking_member");	
		sb.append(" ) cc");
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" WHERE cc.stf_id="+cpsVo.getStfId());
		sb.append(" GROUP BY cc.stf_id");
		sb.append(" ) bb");
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setStfId(Short.parseShort(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setServiceGroupName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[3].toString()));
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[4].toString()));
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setPartsAmount(Double.parseDouble(obj[5].toString()));
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setNoTaxAmount(Double.parseDouble(obj[6].toString()));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setTaxAmount(Double.parseDouble(obj[7].toString()));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					cps.setItemRebateAmount(Double.parseDouble(obj[8].toString()));
				if(obj[9]!=null&&obj[9].toString().length()>0)
					cps.setItemFactAmount(Double.parseDouble(obj[9].toString()));
				if(obj[10]!=null&&obj[10].toString().length()>0)
					cps.setSumAmount(Double.parseDouble(obj[10].toString()));
				cps.setItemName(weaveItem(cpsVo.getReceptionId(),cps.getStfId().toString(),cpsVo));
				cps.set_parentId(cpsVo.getReceptionId());
				cps.setState("open");
				rows.add(cps);
			}
		return rows;
	}
	/**
	 * 维修人员业绩统计(按工单号)
	 * */
	
	public DatagridAnalyze findServicePersonScoreForReceptionId(
			CustomerPerformanceStatisticsVo cpsVo) throws Exception {
		setDefaultGaheringTimeSect(cpsVo);
		StringBuffer sb=new StringBuffer("SELECT bb.*,(bb.temp2+temp3)");
		sb.append(" FROM (");
		sb.append(" SELECT  cc.reception_id,cc.custom_name,cc.car_license,cc.ctype_name,SUM(cc.WKTIME_NUM) AS temp1,SUM(cc.WKTIME_AMOUNT) AS temp2,");
		sb.append(" cc.temp3,cc.temp4,cc.temp5,SUM(cc.temp6) AS temp6,SUM(cc.temp7) AS temp7,cc.stf_name");
		sb.append(" FROM(");
		sb.append(" SELECT fr.reception_id,fc.custom_name,fcar.car_license,bct.ctype_name,fpw.WKTIME_NUM,fpw.WKTIME_AMOUNT,");
		sb.append(" (SELECT CASE WHEN aa.temp3!='' THEN aa.temp3 ELSE 0 END ) AS temp3,");	
		sb.append(" (SELECT CASE WHEN aa.temp4!='' THEN aa.temp4 ELSE 0 END ) AS temp4,");
		sb.append(" (SELECT CASE WHEN aa.temp5!='' THEN aa.temp5 ELSE 0 END ) AS temp5,");	
		sb.append(" (fpw.WKTIME_AMOUNT*(1-fpc.PRECLR_WKTIME_RATE)) AS temp6,");
		sb.append(" (fpw.WKTIME_AMOUNT*fpc.PRECLR_WKTIME_RATE) AS temp7,stuff.stf_name");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" and fpc.enterprise_id="+cpsVo.getEnterpriseId());
		if(cpsVo.getPreclrTimeBegin()!=null&&cpsVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+cpsVo.getPreclrTimeBegin()+"'");
		if(cpsVo.getPreclrTimeEnd()!=null&&cpsVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+cpsVo.getPreclrTimeEnd()+"'");
		if(cpsVo.getReptId()!=null&&cpsVo.getReptId().length()>0)
			sb.append(" AND fr.rept_id="+cpsVo.getReptId());
		if(cpsVo.getRcptBranch()!=null&&cpsVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+cpsVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN frt_pre_wktime fpw ON fpc.preclr_id=fpw.preclr_id");
		if(cpsVo.getItemName()!=null&&cpsVo.getItemName().length()>0)
			sb.append(" AND fpw.repitem_name like '%"+StringEscapeUtils.escapeSql(cpsVo.getItemName().trim())+"%'");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND fpw.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN bas_stuff bs ON fpw.stf_id=bs.stf_id	");
		sb.append(" LEFT OUTER JOIN bas_repair_group brp ON brp.repgrp_id=bs.repgrp_id");
		if(cpsVo.getServiceGroupId()!=null&&cpsVo.getServiceGroupId().length()>0)
			sb.append(" AND brp.repgrp_id="+cpsVo.getServiceGroupId());
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(cpsVo.getCarLicense()!=null&&cpsVo.getCarLicense().length()>0){
			cpsVo.setCarLicense(new String(cpsVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(cpsVo.getCarLicense().trim())+"%'");			
		}
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(cpsVo.getCarTypeId()!=null&&cpsVo.getCarTypeId().length()>0)
			sb.append(" AND bct.ctype_id="+cpsVo.getCarTypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		if(cpsVo.getCarBrandId()!=null&&cpsVo.getCarBrandId().length()>0)
			sb.append(" AND bcb.cbrd_id="+cpsVo.getCarBrandId());
		sb.append(" INNER JOIN (SELECT * FROM bas_stuff ) stuff ON stuff.stf_id=fr.receptor");
		sb.append(" LEFT OUTER JOIN");	
		sb.append(" ("); 	
		sb.append(" SELECT som.cer_no,SUM(fpp.parts_amount) AS temp3,SUM(fpp.parts_notax_cost*fpp.parts_count) AS temp4,");	
		sb.append(" SUM(fpp.parts_tax_cost*fpp.parts_count) AS temp5, som.PICKING_MEMBER FROM");
		sb.append(" st_out_main som INNER JOIN st_out_item soi ON soi.stom_id=som.stom_id");
		if(cpsVo.getClaimsTerm()!=null&&cpsVo.getClaimsTerm().length()>0)
			sb.append(" AND soi.claims_type in("+cpsVo.getClaimsTerm()+")");
		sb.append(" INNER JOIN frt_pre_parts fpp ON fpp.parts_id=soi.parts_id AND fpp.store_id=soi.store_id");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpp.preclr_id");       
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");     
		sb.append(" AND fr.reception_id=som.cer_no");      
		sb.append(" GROUP BY som.cer_no");
		sb.append(" ) aa ON fr.reception_id=aa.cer_no AND bs.stf_id=aa.picking_member");	
		if(cpsVo.getStfId()!=null&&cpsVo.getStfId().toString().length()>0)
			sb.append(" WHERE bs.stf_id="+cpsVo.getStfId());
		sb.append(" ) cc");
		sb.append(" GROUP BY cc.reception_id");
		sb.append(" ) bb");
		List<CustomerPerformanceStatisticsVo> footers=new ArrayList<CustomerPerformanceStatisticsVo>();
		List<CustomerPerformanceStatisticsVo> rows=new ArrayList<CustomerPerformanceStatisticsVo>();
		CustomerPerformanceStatisticsVo cpsv=new CustomerPerformanceStatisticsVo();
		cpsv.setItemTime(0d);
		cpsv.setItemAmount(0d);
		cpsv.setPartsAmount(0d);
		cpsv.setItemRebateAmount(0d);
		cpsv.setItemFactAmount(0d);
		cpsv.setSumAmount(0d);
		cpsv.setNoTaxAmount(0d);
		cpsv.setTaxAmount(0d);
		cpsv.setReceptionId("合计");
		List<Object[]> list=customerPerformanceStatisticsDao.createSQLQuery(sb.toString(), cpsVo.getPage(), cpsVo.getRows());
		CustomerPerformanceStatisticsVo cps=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				cps=new CustomerPerformanceStatisticsVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					cps.setReceptionId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					cps.setCustomName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					cps.setCarLicense(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					cps.setCarTypeName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					cps.setItemTime(Double.parseDouble(obj[4].toString()));
				cpsv.setItemTime(cpsv.getItemTime()+cps.getItemTime());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					cps.setItemAmount(Double.parseDouble(obj[5].toString()));
				cpsv.setItemAmount(cpsv.getItemAmount()+cps.getItemAmount());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					cps.setPartsAmount(Double.parseDouble(obj[6].toString()));
				cpsv.setPartsAmount(cpsv.getPartsAmount()+cps.getPartsAmount());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					cps.setNoTaxAmount(Double.parseDouble(obj[7].toString()));
				cpsv.setNoTaxAmount(cpsv.getNoTaxAmount()+cps.getNoTaxAmount());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					cps.setTaxAmount(Double.parseDouble(obj[8].toString()));
				cpsv.setTaxAmount(cpsv.getTaxAmount()+cps.getTaxAmount());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					cps.setItemRebateAmount(Double.parseDouble(obj[9].toString()));
				cpsv.setItemRebateAmount(cpsv.getItemRebateAmount()+cps.getItemRebateAmount());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					cps.setItemFactAmount(Double.parseDouble(obj[10].toString()));
				cpsv.setItemFactAmount(cpsv.getItemFactAmount()+cps.getItemFactAmount());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					cps.setReceivePerson(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					cps.setSumAmount(Double.parseDouble(obj[12].toString()));
				cpsv.setSumAmount(cpsv.getSumAmount()+cps.getSumAmount());
				if(isExistsPreclearingItem(cps.getReceptionId())){
					cps.setStfName("查看详情");
					cps.setState("closed");					
				}else{
					cps.setStfName("无维修项目");
					cps.setState("open");
                }
				rows.add(cps);
			}
		cpsv.setStfName("");
		footers.add(cpsv);
		int total=customerPerformanceStatisticsDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	private boolean isExistsPreclearingItem(String receptionId) throws Exception{
    	List list=customerPerformanceStatisticsDao.createSQLQuery("select fpw.preclr_id from frt_pre_wktime fpw inner join frt_pre_clearing fpc " +
    			"where fpw.preclr_id=fpc.preclr_id and fpc.reception_id='"+receptionId+"'");
    	if(list!=null&&list.size()>0)
    		return true;
    	return false;
    }
	private void setDefaultGaheringTimeSect(CustomerPerformanceStatisticsVo cpsVo)throws Exception{
		if((cpsVo.getPreclrTimeBegin()==null||cpsVo.getPreclrTimeBegin().length()==0)&&
				(cpsVo.getPreclrTimeEnd()==null||cpsVo.getPreclrTimeEnd().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				cpsVo.setPreclrTimeEnd(MyBeanUtils.getInstance().getString(date));
				cpsVo.setPreclrTimeBegin(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
