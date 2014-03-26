package com.syuesoft.fin.serviceimpl;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ognl.Ognl;

import org.apache.commons.lang.StringEscapeUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.ReptypeDao;
import com.syuesoft.bas.serviceimpl.BaseServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.TimeManageService;
import com.syuesoft.fin.vo.DayBusinessThingVo;
import com.syuesoft.fin.vo.TimeManageVo;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.model.Reptype;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateJFreeChart;
import com.syuesoft.util.MyBeanUtils;
import com.syuesoft.util.PoleMap;
import com.syuesoft.util.SnapMap;
/**
 * 日期经营情况查询Service
 * */
@Service("timeManageService")
public class TimeManageServiceImpl extends BaseServiceImpl implements
        TimeManageService
{

	@Autowired
	private ReptypeDao reptypeDao;
	@Autowired
	private FrtService frtService;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
	/**
	 * 日营业情况查询
	 * */
    
	public DatagridAnalyze findDayBusinessThing(TimeManageVo tmVo,Boolean sign)
			throws Exception {
    	setDefaultPreclrTimeSect(tmVo);
    	if(sign!=null&&sign==true){
	    	if(tmVo.getCarLicense()!=null&&tmVo.getCarLicense().length()>0)
	    		tmVo.setCarLicense(new String(tmVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
	    	if(tmVo.getCustomName()!=null&&tmVo.getCustomName().length()>0)
	    		tmVo.setCustomName(new String(tmVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	}
    	String whetherTax=frtService.getDefaultwhetherTax();
    	tmVo.setWhetherTax(false);
    	if(whetherTax!=null&&whetherTax.length()>0)
    		tmVo.setWhetherTax(true);
    	StringBuffer sb=new StringBuffer("SELECT dd.preclr_time,dd.preclr_id,dd.car_license,dd.temp1,dd.rept_name,dd.temp2,dd.preclr_no,"+
		" dd.custom_name,dd.stf_name,dd.preclr_wktime_amount,dd.pre_mpr_mat_amount,dd.preclr_other_amount,"+
    	" dd.preclr_sum_amount,dd.preclr_real_amount,");
		if(!tmVo.getWhetherTax())
			sb.append(" dd.temp3,");
		else
			sb.append(" dd.temp4,");
		if(!tmVo.getWhetherTax())
			sb.append(" (CASE WHEN dd.temp5!='' THEN dd.temp5 ELSE 0 END) AS temp5,");
		else
			sb.append(" (CASE WHEN dd.temp6!='' THEN dd.temp6 ELSE 0 END) AS temp6,");
		if(!tmVo.getWhetherTax())
			sb.append(" ((dd.preclr_real_amount-dd.temp3)/1.17) AS temp7,");
		else
			sb.append(" ((dd.preclr_real_amount-dd.temp4)/1.17) AS temp8,");
		if(!tmVo.getWhetherTax()){
			sb.append(" (CASE WHEN ((dd.preclr_real_amount-dd.temp3)/dd.preclr_real_amount)!=''"); 
			sb.append(" THEN ((dd.preclr_real_amount-dd.temp3)/dd.preclr_real_amount) ELSE 0 END) AS temp9,");
		}else{
			sb.append(" (CASE WHEN ((dd.preclr_real_amount-dd.temp4)/dd.preclr_real_amount)!=''");
			sb.append(" THEN ((dd.preclr_real_amount-dd.temp4)/dd.preclr_real_amount) ELSE 0 END) AS temp10,");			
		}
			 
		if(!tmVo.getWhetherTax())
			sb.append(" dd.preclr_notax_cost,");
		else
			sb.append(" dd.preclr_tax_cost,");
		sb.append(" dd.preclr_remark,dd.temp11,dd.temp12");
		sb.append(" FROM (");
		sb.append(" SELECT DATE(cc.preclr_time) as preclr_time,cc.preclr_id,cc.car_license,cc.temp1,cc.rept_name,cc.temp2,cc.preclr_no,");
		sb.append(" cc.custom_name,cc.stf_name,cc.preclr_wktime_amount,cc.pre_mpr_mat_amount,cc.preclr_other_amount,");
		sb.append(" cc.preclr_sum_amount,cc.preclr_real_amount,");
		if(!tmVo.getWhetherTax())
			sb.append(" (CASE WHEN cc.temp3!='' THEN cc.temp3 ELSE 0 END) temp3,");
		else
			sb.append(" (CASE WHEN cc.temp4!='' THEN cc.temp4 ELSE 0 END) temp4,");
		if(!tmVo.getWhetherTax())
			sb.append(" ((cc.pre_mpr_mat_amount-(CASE WHEN cc.temp3!='' THEN cc.temp3 ELSE 0 END))/cc.pre_mpr_mat_amount) AS temp5,");
		else
			sb.append(" ((cc.pre_mpr_mat_amount-(CASE WHEN cc.temp4!='' THEN cc.temp4 ELSE 0 END))/cc.pre_mpr_mat_amount) AS temp6,");
		if(!tmVo.getWhetherTax())
			sb.append(" (CASE WHEN cc.preclr_notax_cost!='' THEN cc.preclr_notax_cost ELSE 0 END)  preclr_notax_cost,");
		else
			sb.append(" (CASE WHEN cc.preclr_tax_cost!='' THEN cc.preclr_tax_cost ELSE 0 END) preclr_tax_cost,");
		sb.append(" cc.preclr_remark,cc.temp11,cc.temp12");	
		sb.append(" FROM (");	
		/**************************************/
		if(tmVo.getPreclearingClass()!=null&&tmVo.getPreclearingClass().length()>0){
			if(tmVo.getPreclearingClass().equals("aa")){
				sb.append(preclrSql(tmVo));	
			}else if(tmVo.getPreclearingClass().equals("bb")){
				sb.append(claimsSql(tmVo));	
			}else{
				sb.append(preclrSql(tmVo));
				sb.append(" UNION ");
				sb.append(claimsSql(tmVo));
			}
		}else{
			sb.append(preclrSql(tmVo));
			sb.append(" UNION ");
			sb.append(claimsSql(tmVo));
		}
		/**************************************/
		sb.append(" ) cc");	
		sb.append(" ) dd");
		sb.append(" WHERE 1=1");
		if(tmVo.getBeginTime()!=null&&tmVo.getBeginTime().length()>0)
			sb.append(" AND dd.preclr_time>='"+tmVo.getBeginTime()+"'");
		if(tmVo.getEndTime()!=null&&tmVo.getEndTime().length()>0)
			sb.append(" AND dd.preclr_time<='"+tmVo.getEndTime()+"'");
		sb.append(" ORDER BY dd.preclr_time asc");
		List<DayBusinessThingVo> footers=new ArrayList();
		List<DayBusinessThingVo> rows=new ArrayList();
		List<Object[]> list=reptypeDao.createSQLQuery(sb.toString());
		DayBusinessThingVo dbts=new DayBusinessThingVo();
		dbts.setItemsAmount(0d);
		dbts.setPartsAmount(0d);
		dbts.setSumAmount(0d);
		dbts.setOtherAmount(0d);
		dbts.setRealAmount(0d);
		dbts.setPreclrCost(0d);
		dbts.setPartsWideRate(0d);
		dbts.setGain(0d);
		dbts.setPreclrWideRate(0d);
		dbts.setReceptionCost(0d);
		int count=0;
		DayBusinessThingVo dbt=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				dbt=new DayBusinessThingVo();
				if(obj[0]!=null&&obj[0].toString().length()>0){
					dbt.setPreclrTime(obj[0].toString());
					dbt.setPreclrTimeHidden(MyBeanUtils.getInstance().getDate(obj[0].toString(), "yyyy-MM-dd"));
				}
				if(obj[1]!=null&&obj[1].toString().length()>0)
					dbt.setPreclrId(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					dbt.setCarLicense(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					dbt.setCarTypeName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					dbt.setReptName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					dbt.setInvoiceTypeName(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					dbt.setInvoiceNumber(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					dbt.setCustomName(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					dbt.setStfName(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0){
					dbt.setItemsAmount(Double.parseDouble(obj[9].toString()));
					dbts.setItemsAmount(dbts.getItemsAmount()+dbt.getItemsAmount());
				}
				if(obj[10]!=null&&obj[10].toString().length()>0){
					dbt.setPartsAmount(Double.parseDouble(obj[10].toString()));
					dbts.setPartsAmount(dbts.getPartsAmount()+dbt.getPartsAmount());
				}
				if(obj[11]!=null&&obj[11].toString().length()>0){
					dbt.setOtherAmount(Double.parseDouble(obj[11].toString()));
					dbts.setOtherAmount(dbts.getOtherAmount()+dbt.getOtherAmount());
				}
				if(obj[12]!=null&&obj[12].toString().length()>0){
					dbt.setSumAmount(Double.parseDouble(obj[12].toString()));
					dbts.setSumAmount(dbts.getSumAmount()+dbt.getSumAmount());
				}
				if(obj[13]!=null&&obj[13].toString().length()>0){
					dbt.setRealAmount(Double.parseDouble(obj[13].toString()));
					dbts.setRealAmount(dbts.getRealAmount()+dbt.getRealAmount());
				}
				if(obj[14]!=null&&obj[14].toString().length()>0){
					dbt.setPreclrCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[14].toString())));
					dbts.setPreclrCost(dbts.getPreclrCost()+dbt.getPreclrCost());
				}
				if(obj[15]!=null&&obj[15].toString().length()>0){
					dbt.setPartsWideRate(Double.parseDouble(obj[15].toString()));
					dbts.setPartsWideRate(dbts.getPartsWideRate()+dbt.getPartsWideRate());
				}
				if(obj[16]!=null&&obj[16].toString().length()>0){
					dbt.setGain(Double.parseDouble(obj[16].toString()));
					dbts.setGain(dbts.getGain()+dbt.getGain());
				}
				if(obj[17]!=null&&obj[17].toString().length()>0){
					dbt.setPreclrWideRate(Double.parseDouble(obj[17].toString()));
					dbts.setPreclrWideRate(dbts.getPreclrWideRate()+dbt.getPreclrWideRate());
				}
				if(obj[18]!=null&&obj[18].toString().length()>0){
					dbt.setReceptionCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[18].toString())));
					dbts.setReceptionCost(dbts.getReceptionCost()+dbt.getReceptionCost());
				}
				if(obj[19]!=null&&obj[19].toString().length()>0)
					dbt.setPreclrRemark(obj[19].toString());
				if(obj[20]!=null&&obj[20].toString().length()>0)
					dbt.setRcptBranch(obj[20].toString());
				if(obj[21]!=null&&obj[21].toString().length()>0)
					dbt.setItems(obj[21].toString());
				dbt.setItems(weaveItems(dbt.getPreclrId()));
				dbt.setBigheadId(dbt.getPreclrId());
				rows.add(dbt);
				count++;
			}
		dbts.setPreclrCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
				Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,dbts.getPreclrCost().toString())));
		dbts.setReceptionCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
				Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,dbts.getReceptionCost().toString())));
		HashMap<String,List<DayBusinessThingVo>> hashMap=new HashMap();
		if(rows.size()>0){
			Iterator it=null;
			String tempName=null;
			List<DayBusinessThingVo> tempList=null;
			Boolean flag=false;
			for (DayBusinessThingVo vo : rows) {
				it=hashMap.keySet().iterator();
				while(it.hasNext()){
					tempName=it.next().toString();
					if(vo.getPreclrTime().equals(tempName)){
						tempList=hashMap.get(tempName);
						vo.setPreclrTime(null);
						tempList.add(vo);
						flag=true;
						break;
					}
				}
				if(!flag){
					tempList=new ArrayList();
					tempList.add(vo);
					hashMap.put(vo.getPreclrTime(), tempList);
				}
				tempName=null;
				tempList=null;
				flag=false;
			}
		}
		if(hashMap.size()>0){
			Iterator it=hashMap.keySet().iterator();
			List<DayBusinessThingVo> tempList=null;
			DayBusinessThingVo vo=null;
			while(it.hasNext()){
				tempList=hashMap.get(it.next());
				vo=new DayBusinessThingVo();
				vo.setBigheadId(tempList.get(0).getPreclrId()+"s");
				vo.setPreclrId("小计");
				vo.setPreclrTime(tempList.get(0).getPreclrTime());
				vo.setCarLicense(tempList.size()+"");
				
				vo.setItemsAmount(0d);
				vo.setPartsAmount(0d);
				vo.setSumAmount(0d);
				vo.setOtherAmount(0d);
				vo.setRealAmount(0d);
				vo.setPreclrCost(0d);
				vo.setPartsWideRate(0d);
				vo.setGain(0d);
				vo.setPreclrWideRate(0d);
				vo.setReceptionCost(0d);
				vo.setPreclrTimeHidden(tempList.get(0).getPreclrTimeHidden());
				for (DayBusinessThingVo vos : tempList) {
					vo.setItemsAmount(vo.getItemsAmount()+vos.getItemsAmount());
					vo.setPartsAmount(vo.getPartsAmount()+vos.getPartsAmount());
					vo.setOtherAmount(vo.getOtherAmount()+vos.getOtherAmount());
					vo.setSumAmount(vo.getSumAmount()+vos.getSumAmount());
					vo.setRealAmount(vo.getRealAmount()+vos.getRealAmount());
					vo.setPreclrCost(vo.getPreclrCost()+vos.getPreclrCost());
					vo.setGain(vo.getGain()+vos.getGain());
					vo.setReceptionCost(vo.getReceptionCost()+vos.getReceptionCost());
				}
				vo.setReceptionCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,vo.getReceptionCost().toString())));
				vo.setPreclrCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,vo.getPreclrCost().toString())));
				if(vo.getPartsAmount()!=0d)
					vo.setPartsWideRate((vo.getPartsAmount()-vo.getPreclrCost())/vo.getPartsAmount());
				if(vo.getRealAmount()!=0d)
					vo.setPreclrWideRate((vo.getRealAmount()-vo.getPreclrCost())/vo.getRealAmount());
				tempList.add(vo);
				tempList=null;
				vo=null;
			}
			
			rows.clear();
			Iterator its=hashMap.keySet().iterator();
			while(its.hasNext()){
				tempList=hashMap.get(its.next());
				for (DayBusinessThingVo vos : tempList) {
					rows.add(vos);
				}
				tempList=null;
			}
			DayBusinessThingVo temp=null;
			for (int i = 1; i < rows.size(); i++) {
				for (int j = 0; j < rows.size()-i; j++) {
					if(rows.get(j).getPreclrTimeHidden().getTime()>rows.get(j+1).getPreclrTimeHidden().getTime()){
						temp=rows.get(j);
						rows.set(j,rows.get(j+1));
						rows.set(j+1,temp);
					}
				}
			}
		}
		dbts.setCarLicense(count+"");
		dbts.setPreclrTime("合计");
		footers.add(dbts);
    	DatagridAnalyze da=new DatagridAnalyze();
    	da.setFooter(footers);
    	da.setRows(rows);
    	da.setTotal(rows.size());
		return da;
	}
    
    private String preclrSql(TimeManageVo tmVo){
    	StringBuffer sb=new StringBuffer(" SELECT fpc.preclr_time,fpc.preclr_id,fcar.car_license,CONCAT(CONCAT(bcb.cbrd_name,'/'),bct.ctype_name) AS temp1,");		
		sb.append(" rt.rept_name,aa.dataValue AS temp2,fpc.preclr_no,fc.custom_name,bs.stf_name,");		
		sb.append(" fpc.preclr_wktime_amount,fpc.pre_mpr_mat_amount,fpc.preclr_other_amount,fpc.preclr_sum_amount,fpc.preclr_real_amount,");
		if(!tmVo.getWhetherTax()){
			sb.append(" (SELECT SUM(fpp.parts_count*fpp.parts_notax_cost)");		
			sb.append(" FROM frt_pre_clearing fpcs INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpcs.preclr_id");		
			sb.append(" INNER JOIN bas_claims_type bct ON bct.claims_id=fpp.claims_type");		
			sb.append(" AND bct.claims_to_money="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);		
			sb.append(" WHERE fpc.preclr_id=fpcs.preclr_id");		
			sb.append(" ) AS temp3,");			
		}else{
			sb.append(" (SELECT SUM(fpp.parts_count*fpp.parts_tax_cost)");		
			sb.append(" FROM frt_pre_clearing fpcs INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpcs.preclr_id");		
			sb.append(" INNER JOIN bas_claims_type bct ON bct.claims_id=fpp.claims_type");		
			sb.append(" AND bct.claims_to_money="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);		
			sb.append(" WHERE fpc.preclr_id=fpcs.preclr_id");		
			sb.append(" ) AS temp4,");			
		}
		if(!tmVo.getWhetherTax())
			sb.append(" fpc.preclr_notax_cost,");
		else
			sb.append(" fpc.preclr_tax_cost,");
		sb.append(" fpc.preclr_remark,bb.dataValue AS temp11,'维修项目' AS temp12");		
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");		
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" AND fr.enterprise_id="+tmVo.getEnterpriseId());		
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(tmVo.getCarLicense()!=null&&tmVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(tmVo.getCarLicense().trim())+"%'");
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(tmVo.getCtypeId()!=null&&tmVo.getCtypeId().length()>0)
			sb.append(" AND bct.ctype_id="+tmVo.getCtypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");	
		if(tmVo.getCbrdId()!=null&&tmVo.getCbrdId().length()>0)
			sb.append(" AND bcb.cbrd_id="+tmVo.getCbrdId());
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		if(tmVo.getReptId()!=null&&tmVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+tmVo.getReptId());
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
		if(tmVo.getCustomName()!=null&&tmVo.getCustomName().length()>0)
			sb.append(" AND fc.custom_name like '%"+StringEscapeUtils.escapeSql(tmVo.getCustomName().trim())+"%'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");	
		if(tmVo.getServicePerson()!=null&&tmVo.getServicePerson().length()>0)
			sb.append(" AND bs.stf_id="+tmVo.getServicePerson());
		sb.append(" INNER JOIN (SELECT bsf.stf_id,bsf.stf_name as tempName FROM bas_stuff bsf WHERE bsf.enterprise_id="+tmVo.getEnterpriseId()+") tt ON tt.stf_id=fpc.stf_id");
		if(tmVo.getPreclrPerson()!=null&&tmVo.getPreclrPerson().length()>0)
			sb.append(" AND tt.stf_id="+tmVo.getPreclrPerson());
		sb.append(" INNER JOIN (");		
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");		
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'");		
		sb.append(" ) aa ON aa.dataKey=fpc.preclr_inoice_type");		
		sb.append(" INNER JOIN (");		
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");		
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RCPTBRANCH.RCPTBRANCHKEY+"'");		
		sb.append(" ) bb ON bb.dataKey=fr.rcpt_branch");
    	return sb.toString();
    }
    private String claimsSql(TimeManageVo tmVo){
    	StringBuffer sb=new StringBuffer(" SELECT fcm.claimantm_time AS preclr_time,fcm.claimantm_id AS preclr_id,fcar.car_license,CONCAT(CONCAT(bcb.cbrd_name,'/'),bct.ctype_name) AS temp1,");
    	sb.append(" rt.rept_name,aa.dataValue AS temp2,fcm.claimantm_invoice_no AS preclr_no,brc.relcamp_name AS custom_name,bs.stf_name,");
		sb.append(" fcm.claimantm_time_amount AS preclr_wktime_amount,fcm.claimantm_parts_amount AS pre_mpr_mat_amount,");
		sb.append(" fcm.claimantm_other_amount AS preclr_other_amount,fcm.claimantm_amount AS preclr_sum_amount,fcm.claimantm_amount AS preclr_real_amount,");
		if(!tmVo.getWhetherTax()){
			sb.append(" fcm.claimantm_notax_cost AS temp3,");			
		}else{
			sb.append(" fcm.claimantm_tax_cost AS temp4,");
		}
		if(!tmVo.getWhetherTax()){
			sb.append(" (SELECT SUM(fpp.parts_count*fpp.parts_notax_cost)");
			sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpc.preclr_id");
			sb.append(" INNER JOIN fin_claimant_main fcms ON fcms.preclr_id=fpc.preclr_id");
			sb.append(" INNER JOIN bas_claims_type bct ON bct.claims_id=fpp.claims_type");
			sb.append(" AND bct.claims_flg="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
			sb.append(" WHERE fcms.claimantm_id=fcm.claimantm_id");
			sb.append(" ) AS preclr_notax_cost,");
		}
		else{
			sb.append(" (SELECT SUM(fpp.parts_count*fpp.parts_tax_cost)");
			sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpc.preclr_id");
			sb.append(" INNER JOIN fin_claimant_main fcms ON fcms.preclr_id=fpc.preclr_id");
			sb.append(" INNER JOIN bas_claims_type bct ON bct.claims_id=fpp.claims_type");
			sb.append(" AND bct.claims_flg="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
			sb.append(" WHERE fcms.claimantm_id=fcm.claimantm_id");
			sb.append(" ) AS preclr_tax_cost,");
		}
		sb.append(" fcm.claimantm_remark AS preclr_remark,bb.dataValue AS temp11,'索赔项目' AS temp12");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES +" AND fr.enterprise_id="+tmVo.getEnterpriseId());
		sb.append(" INNER JOIN fin_claimant_main fcm ON fcm.preclr_id=fpc.preclr_id");
		sb.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(tmVo.getCarLicense()!=null&&tmVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(tmVo.getCarLicense().trim())+"%'");
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		if(tmVo.getCtypeId()!=null&&tmVo.getCtypeId().length()>0)
			sb.append(" AND bct.ctype_id="+tmVo.getCtypeId());
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		if(tmVo.getCbrdId()!=null&&tmVo.getCbrdId().length()>0)
			sb.append(" AND bcb.cbrd_id="+tmVo.getCbrdId());
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
		if(tmVo.getReptId()!=null&&tmVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+tmVo.getReptId());
		sb.append(" INNER JOIN bas_relation_campany brc ON brc.relcamp_id=fr.fin_com_id");
		if(tmVo.getCustomName()!=null&&tmVo.getCustomName().length()>0)
			sb.append(" AND brc.relcamp_name like '%"+StringEscapeUtils.escapeSql(tmVo.getCustomName().trim())+"%'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		if(tmVo.getServicePerson()!=null&&tmVo.getServicePerson().length()>0)
			sb.append(" AND bs.stf_id="+tmVo.getServicePerson());
		sb.append(" INNER JOIN (SELECT bsf.stf_id,bsf.stf_name as tempName FROM bas_stuff bsf WHERE bsf.enterprise_id="+tmVo.getEnterpriseId()+") tt ON tt.stf_id=fcm.claimantm_clr_stf_id");
		if(tmVo.getPreclrPerson()!=null&&tmVo.getPreclrPerson().length()>0)
			sb.append(" AND tt.stf_id="+tmVo.getPreclrPerson());
		sb.append(" INNER JOIN (");
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'");
		sb.append(" ) aa ON aa.dataKey=fcm.claimantm_invoice_type");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RCPTBRANCH.RCPTBRANCHKEY+"'");
		sb.append(" ) bb ON bb.dataKey=fr.rcpt_branch ");
		return sb.toString();
    }
    private String weaveItems(String preclrId) throws Exception{
    	StringBuffer sb=new StringBuffer();
    	StringBuffer sbr=new StringBuffer();
    	for (int i = 0; i < preclrId.length(); i++) {
			if(!(preclrId.charAt(i)>='0'&&preclrId.charAt(i)<='9')){
				sbr.append(preclrId.charAt(i));
			}
		}
    	if(sbr.toString().equals(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)){
    		sb.append("SELECT fct.repitem_name,bs.stf_name");
    		sb.append(" FROM fin_claimant_time fct INNER JOIN fin_claimant_main fcm ON fcm.claimantm_id=fct.claimantm_id");
    		sb.append(" AND fcm.claimantm_id='"+preclrId+"'");	
    		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fct.stf_id");
    	}else if(sbr.toString().equals(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)){
    		sb.append("SELECT fpw.repitem_name,bs.stf_name");
    		sb.append(" FROM frt_pre_wktime fpw INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id");
    		sb.append(" AND fpc.preclr_id='"+preclrId+"'");
    		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fpw.stf_id");    		
    	}else{
    		return null;
    	}
    	List<Object[]> list=reptypeDao.createSQLQuery(sb.toString());
    	StringBuffer sbs=new StringBuffer();
    	if(list!=null&&list.size()>0)
    		for (Object[] obj : list) {
    			if(obj[0]!=null&&obj[0].toString().length()>0&&obj[1]!=null&&obj[1].toString().length()>0)
    				sbs.append(obj[0].toString()+"("+obj[1].toString()+"),");
			}
    	if(sbs.length()>0)
    		return sbs.substring(0, sbs.length()-1);
    	return sbs.toString();
    }
    /**
     * 获取日营业情况折线图信息
     * */
	
	public JFreeChart findDayBusinessThingSnapMap(TimeManageVo tmVo)
			throws Exception {
		DatagridAnalyze da=findDayBusinessThing(tmVo,true);
		List<DayBusinessThingVo> rows=da.getRows();
		List<SnapMap> snapList=new ArrayList();
		SnapMap sm=null;
		if(rows!=null&&rows.size()>0)
			for (DayBusinessThingVo dbtVo : rows) {
				if(dbtVo.getCustomName()==null||dbtVo.getCustomName().length()==0){
					sm=new SnapMap();
					sm.setSnapYData(dbtVo.getSumAmount());
					sm.setSnapLineName("收入金额折线");
					sm.setSnapXData(dbtVo.getPreclrTime());
					sm.setSnapLineColor(Color.blue);
					snapList.add(sm);
				}
			}
		return CreateJFreeChart.findSnapMap("日营业情况折线图", "日期", "收入金额",snapList,tmVo.getIs3D());
	}
	/**
	 * 获取日营业情况柱状图信息  
	 * */
	
	public JFreeChart findDayBusinessThingPoleMap(TimeManageVo tmVo) throws Exception {
		DatagridAnalyze da=findDayBusinessThing(tmVo,true);
		List<DayBusinessThingVo> footers=da.getFooter();
		List<PoleMap> poleMapList=new ArrayList();
		PoleMap pm=null;
		if(footers!=null&&footers.size()>0){
			DayBusinessThingVo dbtVo = footers.get(0);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#FF0000"));
			pm.setPoleBarName("工时费");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getItemsAmount());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#00FF00"));
			pm.setPoleBarName("材料费");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getPartsAmount());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#FF00FF"));
			pm.setPoleBarName("其他");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getOtherAmount());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#800000"));
			pm.setPoleBarName("收入合计");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getSumAmount());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#008000"));
			pm.setPoleBarName("实际收款");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getRealAmount());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#000080"));
			pm.setPoleBarName("结算成本");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getPreclrCost());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#808000"));
			pm.setPoleBarName("利润");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getGain());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#800080"));
			pm.setPoleBarName("结算毛利率");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getPreclrWideRate());
			poleMapList.add(pm);
			pm=new PoleMap();
			pm.setPoleBarColor(Color.decode("#008080"));
			pm.setPoleBarName("工单成本");
			pm.setPoleXData("");
			pm.setPoleYData(dbtVo.getReceptionCost());
			poleMapList.add(pm);
		}
        return CreateJFreeChart.findPoleMap("日营业情况柱状图", "分类", "金额",poleMapList,0.4d,tmVo.getIs3D());
	}
	/**
     * 查询日经营情况
     * */
	
	public String findDayManage(TimeManageVo timeManageVo) throws Exception {
		setDefaultPreclrTimeSect(timeManageVo);
		timeManageVo.setDatagridId("dayManageSearchDatagrid");
		timeManageVo.setDatagridUrl("findDayManage");
		timeManageVo.setSnapUrl("findDayManageSnapMap");
		timeManageVo.setCakeUrl("findDayManageCakeMap");
		timeManageVo.setFormName("dayManageQueryForm");
		timeManageVo.setExpression("%Y-%m-%d");
		return findYearManage(timeManageVo);
	}
	/**
	 * 获取日经营情况饼图信息
	 * */
	
	public JFreeChart findDayManageCakeMap(TimeManageVo timeManageVo)
			throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<Reptype> list=reptypeDao.find("from Reptype");
		timeManageVo.setFlag(true);
		String source=findDayManage(timeManageVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelected(list,timeManageVo);
			Double value=null;
			for (Reptype reptype : list) {
				value=Double.parseDouble(Ognl.getValue("data"+reptype.getReptId(), rows.get(0)).toString());
				cakeHashMap.put(reptype.getReptName(), value);
			}			
		}
		String cakeName="日经营情况饼图";
		if(timeManageVo.getSelectDate()!=null&&timeManageVo.getSelectDate().length()>0){
			cakeName="("+timeManageVo.getSelectDate()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,true,timeManageVo.getIs3D());
	}
	/**
	 * 获取日经营情况折线图信息
	 * */
	
	public JFreeChart findDayManageSnapMap(TimeManageVo timeManageVo)
			throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype");
		isSelected(list,timeManageVo);
		List<SnapMap> snapList=new ArrayList();
		timeManageVo.setFlag(true);
		String source=findDayManage(timeManageVo);
		if(!(source==null||source.length()==0)){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			String tempField="sumCount";
			if(timeManageVo.getSelectedField()!=null&&timeManageVo.getSelectedField().length()>0&&(!timeManageVo.getSelectedField().equals("selectDate"))){
				tempField=timeManageVo.getSelectedField();
			}
			SnapMap sm=null;
			if(rows!=null&&rows.size()>0){
				for (Object object : rows) {		
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
					sm.setSnapXData(Ognl.getValue("selectDate", object).toString());
					sm.setSnapLineName("维修量折线");
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}
			}			
		}
		String snapName="日经营情况折线图";
		if(list!=null&&list.size()==1){
			snapName="("+list.get(0).getReptName()+")"+snapName;
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "维修量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,timeManageVo.getIs3D());
	}
	/**
     * 查询月经营情况
     * */
	
	public String findMonthManage(TimeManageVo timeManageVo) throws Exception {
		timeManageVo.setDatagridId("monthManageSearchDatagrid");
		timeManageVo.setDatagridUrl("findMonthManage");
		timeManageVo.setSnapUrl("findMonthManageSnapMap");
		timeManageVo.setCakeUrl("findMonthManageCakeMap");
		timeManageVo.setFormName("monthManageQueryForm");
		timeManageVo.setExpression("%Y-%m");
		return findYearManage(timeManageVo);
	}
	/**
	 * 获取月经营情况饼图信息
	 * */
	
	public JFreeChart findMonthManageCakeMap(TimeManageVo timeManageVo)
			throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<Reptype> list=reptypeDao.find("from Reptype");
		timeManageVo.setFlag(true);
		String source=findMonthManage(timeManageVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelected(list,timeManageVo);
			Double value=null;
			for (Reptype reptype : list) {
				value=Double.parseDouble(Ognl.getValue("data"+reptype.getReptId(), rows.get(0)).toString());
				cakeHashMap.put(reptype.getReptName(), value);
			}			
		}
		String cakeName="月经营情况饼图";
		if(timeManageVo.getSelectDate()!=null&&timeManageVo.getSelectDate().length()>0){
			cakeName="("+timeManageVo.getSelectDate()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,true,timeManageVo.getIs3D());
	}
	/**
	 * 获取月经营情况折线图信息
	 * */
	
	public JFreeChart findMonthManageSnapMap(TimeManageVo timeManageVo)
			throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype");
		isSelected(list,timeManageVo);
		List<SnapMap> snapList=new ArrayList();
		timeManageVo.setFlag(true);
		String source=findMonthManage(timeManageVo);
		if(!(source==null||source.length()==0)){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			String tempField="sumCount";
			if(timeManageVo.getSelectedField()!=null&&timeManageVo.getSelectedField().length()>0&&(!timeManageVo.getSelectedField().equals("selectDate"))){
				tempField=timeManageVo.getSelectedField();
			}
			SnapMap sm=null;
			if(rows!=null&&rows.size()>0){
				for (Object object : rows) {		
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
					sm.setSnapXData(Ognl.getValue("selectDate", object).toString());
					sm.setSnapLineName("维修量折线");
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}
			}			
		}
		String snapName="月经营情况折线图";
		if(list!=null&&list.size()==1){
			snapName="("+list.get(0).getReptName()+")"+snapName;
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "维修量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,timeManageVo.getIs3D());
	}
	/**
     * 查询年经营情况
     * */
	
	public String findYearManage(TimeManageVo timeManageVo) throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype  where  enterpriseId="+timeManageVo.getEnterpriseId());
		isSelected(list,timeManageVo);
		StringBuffer sb=new StringBuffer("SELECT aa.interDate,");
		if(list!=null&&list.size()>0){
			for (Reptype reptype : list) {
				sb.append(" COUNT(aa.data"+reptype.getReptId()+"),");
			}		
		}else{
			return null;			
		}
		String sql1=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql1);
		if(timeManageVo.getExpression()==null||timeManageVo.getExpression().length()==0)
			timeManageVo.setExpression("%Y");
		sb.append(" FROM ( SELECT DATE_FORMAT(fpc.PRECLR_TIME, '"+timeManageVo.getExpression()+"') AS interDate,");
		if(list!=null&&list.size()>0)
			for (Reptype reptype : list) {
				sb.append(" (SELECT temp.reception_id FROM frt_reception temp");
				sb.append(" WHERE frt.reception_id=temp.reception_id  AND  temp.enterprise_id="+timeManageVo.getEnterpriseId()+" and frt.rept_id="+reptype.getReptId()+" ) AS  data"+reptype.getReptId()+",");
			}
		String sql=sb.substring(0,sb.length()-1);
		sb=new StringBuffer(sql);
		sb.append(" FROM frt_reception frt,frt_pre_clearing fpc");
		sb.append(" WHERE frt.RECEPTION_ID=fpc.RECEPTION_ID  AND frt.enterprise_id="+timeManageVo.getEnterpriseId() +" and  fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+") aa where 1=1");
		if(timeManageVo.getSelectDate()!=null&&timeManageVo.getSelectDate().length()>0)
			sb.append(" and aa.interDate='"+timeManageVo.getSelectDate()+"'");
		if(timeManageVo.getBeginTime()!=null&&timeManageVo.getBeginTime().length()>0){
			sb.append(" and aa.interDate>='"+timeManageVo.getBeginTime()+"'");
		}
		if(timeManageVo.getEndTime()!=null&&timeManageVo.getEndTime().length()>0){
			sb.append(" and aa.interDate<='"+timeManageVo.getEndTime()+"'");
		}
		sb.append(" GROUP BY aa.interDate");
		List<Object[]> rows=reptypeDao.createSQLQuery(sb.toString(), timeManageVo.getPage(), timeManageVo.getRows());
		int total=reptypeDao.getSQLCount(sb.toString(), null);
		String source=formatTimeManageAnalyze(list,rows,total);
		if(timeManageVo.getFlag()!=null&&timeManageVo.getFlag()==true){
			return source;
		}
		return yearWeaveDatagrid(source,list,timeManageVo);
	}
	private String yearWeaveDatagrid(String source,List<Reptype> list,TimeManageVo timeManageVo){
		if(timeManageVo.getDatagridId()==null||timeManageVo.getDatagridId().length()==0)
			timeManageVo.setDatagridId("yearManageSearchDatagrid");
		if(timeManageVo.getDatagridUrl()==null||timeManageVo.getDatagridUrl().length()==0)
			timeManageVo.setDatagridUrl("findYearManage");
		if(timeManageVo.getSnapUrl()==null||timeManageVo.getSnapUrl().length()==0)
			timeManageVo.setSnapUrl("findYearManageSnapMap");
		if(timeManageVo.getCakeUrl()==null||timeManageVo.getCakeUrl().length()==0)
			timeManageVo.setCakeUrl("findYearManageCakeMap");
		if(timeManageVo.getFormName()==null||timeManageVo.getFormName().length()==0)
			timeManageVo.setFormName("yearManageQueryForm");	
		
		StringBuffer sb=new StringBuffer("$('#"+timeManageVo.getDatagridId()+"').datagrid({");
		sb.append("url : '${pageContext.request.contextPath}/timeManageAction!"+timeManageVo.getDatagridUrl()+".action?flag=true',");
		sb.append("fit:true,fitColumns:true,nowrap:false,singleSelect:true,pagination:true,rownumbers:true,");
		sb.append("showFooter:true,loadMsg:\"数据加载中，请稍后……\",");
		sb.append("columns :[[");
		sb.append("{field:'selectDate',title:'时间段',width:100,sortable:false},");
		if(list!=null&&list.size()>0)
			for (Reptype reptype : list) {
				sb.append("{field:'data"+reptype.getReptId()+"',title:'"+reptype.getReptName()+"',width:80,sortable:false},");
			}
		sb.append("{field:'sumCount',title:'合计',width:80,sortable:false}");
		sb.append("]],");
		sb.append("onSelect:function(rowIndex, rowData){");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img src=\\\"timeManageAction!"+timeManageVo.getCakeUrl()+".action?page=1&rows=1&selectDate=\"+rowData.selectDate+\"&\"");
		sb.append("+$('#"+timeManageVo.getFormName()+"').serialize()+\" \\\"/>\";");
		sb.append("},");
		sb.append("onClickCell:function(rowIndex, field, value){");
		sb.append("var data=$('#"+timeManageVo.getDatagridId()+"').datagrid('options');");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img src=\\\"timeManageAction!"+timeManageVo.getSnapUrl()+".action?page=\"+data.pageNumber+\"&rows=\"+data.pageSize+\"&\"");
		sb.append("+$('#"+timeManageVo.getFormName()+"').serialize()+\"&selectedField=\"+field+\" \\\" />\";");
		sb.append("},");
		sb.append("onBeforeLoad:function(params){");
		sb.append("document.getElementById(\"snapMapImg\").innerHTML=");
		sb.append("\"<img src=\\\"timeManageAction!"+timeManageVo.getSnapUrl()+".action?page=\"+params.page+\"&rows=\"+params.rows+\"&\"");	
		sb.append("+$('#"+timeManageVo.getFormName()+"').serialize()+\" \\\" />\";");
		sb.append("document.getElementById(\"cakeMapImg\").innerHTML=");
		sb.append("\"<img src=\\\"timeManageAction!"+timeManageVo.getCakeUrl()+".action?page=\"+params.page+\"&rows=\"+params.rows+\"&\"");		
		sb.append("+$('#"+timeManageVo.getFormName()+"').serialize()+\" \\\"/>\";");
		sb.append("}");
		sb.append("});");
		return sb.toString();
	}
	private String formatTimeManageAnalyze(List<Reptype> list ,List<Object[]> rows,Integer total){
		int[] toteCounts=new int[list.size()+1];
		StringBuffer sb1=new StringBuffer("\"rows\":[");
		if(rows!=null&&rows.size()>0){
			for (Object[] obj : rows) {
				sb1.append("{");
				sb1.append("\"selectDate\":\""+obj[0]+"\",");
				int i=1;
				int sumCount=0;
				if(list!=null&&list.size()>0)
					for (Reptype reptype : list) {
						sb1.append("\"data"+reptype.getReptId()+"\":"+obj[i]+",");
						sumCount+=Integer.parseInt(obj[i].toString());
						toteCounts[i-1]+=Integer.parseInt(obj[i].toString());
						i++;
					}
				sb1.append("\"sumCount\":"+sumCount+"");
				toteCounts[toteCounts.length-1]+=sumCount;
				sb1.append("},");
			}
			String s1=sb1.substring(0,sb1.length()-1);			
			sb1=new StringBuffer(s1);
		}
		sb1.append("],\"total\":"+total+"}");
		/*****/
		StringBuffer sb2=new StringBuffer("{\"footer\":[{");
		sb2.append("\"selectDate\":\"合计\",");
		int i=0;
		if(list!=null&&list.size()>0)
		for (Reptype reptype : list) {
			sb2.append("\"data"+reptype.getReptId()+"\":"+toteCounts[i]+",");
			i++;
		}
		sb2.append("\"sumCount\":\""+toteCounts[toteCounts.length-1]+"\"}],");
		sb2.append(sb1);
		return sb2.toString();
	}
	private void isSelected(List<Reptype> list,TimeManageVo timeManageVo){
		if(timeManageVo.getSelectedField()!=null&&timeManageVo.getSelectedField().length()>0){
			int count=timeManageVo.getSelectedField().lastIndexOf("data");
			String id=timeManageVo.getSelectedField().substring(count+4);
			Reptype rep=null;
			for (Reptype reptype : list) {
				if(reptype.getReptId().toString().equals(id)){
					rep=reptype;
					break;
				}
			}
			if(rep!=null){
				list.clear();
				list.add(rep);				
			}
		}
	}
	 /**获取年经营情况饼图信息*/
	
	public JFreeChart findYearManageCakeMap(TimeManageVo timeManageVo)
			throws Exception {
		HashMap<String,Double> cakeHashMap=new HashMap();
		List<Reptype> list=reptypeDao.find("from Reptype");
		timeManageVo.setFlag(true);
		String source=findYearManage(timeManageVo);
		if(source!=null&&source.length()>0){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("footer").toString());
			isSelected(list,timeManageVo);
			Double value=null;
			for (Reptype reptype : list) {
				value=Double.parseDouble(Ognl.getValue("data"+reptype.getReptId(), rows.get(0)).toString());
				cakeHashMap.put(reptype.getReptName(), value);
			}			
		}
		String cakeName="年经营情况饼图";
		if(timeManageVo.getSelectDate()!=null&&timeManageVo.getSelectDate().length()>0){
			cakeName="("+timeManageVo.getSelectDate()+")"+cakeName;
		}
		return CreateJFreeChart.findCakeMap(cakeName,cakeHashMap,true,timeManageVo.getIs3D());
	}

	 /**获取年经营情况折线图信息*/
	
	public JFreeChart findYearManageSnapMap(TimeManageVo timeManageVo)
			throws Exception {
		List<Reptype> list=reptypeDao.find("from Reptype");
		isSelected(list,timeManageVo);
		List<SnapMap> snapList=new ArrayList();
		timeManageVo.setFlag(true);
		String source=findYearManage(timeManageVo);
		if(!(source==null||source.length()==0)){
			JSONObject json=JSON.parseObject(source);
			List rows=(List)JSON.parse(json.get("rows").toString());
			String tempField="sumCount";
			if(timeManageVo.getSelectedField()!=null&&timeManageVo.getSelectedField().length()>0&&(!timeManageVo.getSelectedField().equals("selectDate"))){
				tempField=timeManageVo.getSelectedField();
			}
			SnapMap sm=null;
			if(rows!=null&&rows.size()>0){
				for (Object object : rows) {		
					sm=new SnapMap();
					sm.setSnapYData(Double.parseDouble(Ognl.getValue(tempField, object).toString()));
					sm.setSnapXData(Ognl.getValue("selectDate", object).toString());
					sm.setSnapLineName("维修量折线");
					sm.setSnapLineColor(Color.red);
					snapList.add(sm);
				}
			}			
		}
		String snapName="年经营情况折线图";
		if(list!=null&&list.size()==1){
			snapName="("+list.get(0).getReptName()+")"+snapName;
		}
		return CreateJFreeChart.findSnapMap(snapName, "时间段", "维修量", snapList,null,Font.BOLD,20,null,Font.PLAIN,14,
				null,Font.PLAIN,14,	null,Font.PLAIN ,14,CategoryLabelPositions.UP_45,null,Font.PLAIN,11,null,null,timeManageVo.getIs3D());
	}
	 private void setDefaultPreclrTimeSect(TimeManageVo tmVo)throws Exception{
		if((tmVo.getBeginTime()==null||tmVo.getBeginTime().length()==0)&&
				(tmVo.getEndTime()==null||tmVo.getEndTime().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				tmVo.setEndTime(MyBeanUtils.getInstance().getString(date));
				tmVo.setBeginTime(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
	
}
