package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.future.util.compareDoubleToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.service.ReceiveOperationService;
import com.syuesoft.frt.vo.FrtWorkOrderVo;
import com.syuesoft.frt.vo.ReceiveOperationVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.MyBeanUtils;
/**
 * 接待员业绩统计Service
 * */
@Service("receiveOperationService")
public class ReceiveOperationServiceImpl implements ReceiveOperationService {
	@Autowired
	private FrtResevationDao frtResevationDao;
	@Autowired
	private FrtService frtService;
	/**
	 * 接待员业绩统计明细
	 * */
	
	public DatagridAnalyze receiveOperationDetail(ReceiveOperationVo roVo)
			throws Exception {
		setDefaultPreclrTimeSect(roVo);
		StringBuffer sb=new StringBuffer("SELECT bs.stf_id,bs.stf_name,fr.reception_id,fcar.car_license,fc.custom_name,fr.inter_date,");
		sb.append(" fpc.preclr_time,'交车结算' AS temp1,fpc.preclr_wktime_amount,fpc.pre_mpr_mat_amount,");
		sb.append(" fpc.preclr_other_amount,fpc.preclr_sum_amount,fpc.preclr_real_amount");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(roVo.getPreclrTimeBegin()!=null&&roVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+roVo.getPreclrTimeBegin()+"'");
		if(roVo.getPreclrTimeEnd()!=null&&roVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+roVo.getPreclrTimeEnd()+"'");
		if(roVo.getReptId()!=null&&roVo.getReptId().length()>0)
			sb.append(" AND fr.rept_id="+roVo.getReptId());
		if(roVo.getRcptBranch()!=null&&roVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+roVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		if(roVo.getStfId()!=null&&roVo.getStfId().length()>0)
			sb.append(" AND bs.stf_id="+roVo.getStfId());
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(roVo.getCarLicense()!=null&&roVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(roVo.getCarLicense().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id  WHERE fr.enterprise_id="+roVo.getEnterpriseId()  );
		sb.append(" UNION");
		sb.append(" SELECT bs.stf_id,bs.stf_name,fr.reception_id,fcar.car_license,fc.custom_name,fr.inter_date,");
		sb.append(" fcm.claimantm_time,'索理赔结算' AS temp1,fcm.claimantm_time_amount,fcm.claimantm_parts_amount,");
		sb.append(" fcm.claimantm_other_amount,fcm.claimantm_amount,fcm.claimantm_amount AS temp2");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		if(roVo.getPreclrTimeBegin()!=null&&roVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+roVo.getPreclrTimeBegin()+"'");
		if(roVo.getPreclrTimeEnd()!=null&&roVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+roVo.getPreclrTimeEnd()+"'");
		if(roVo.getReptId()!=null&&roVo.getReptId().length()>0)
			sb.append(" AND fr.rept_id="+roVo.getReptId());
		if(roVo.getRcptBranch()!=null&&roVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+roVo.getRcptBranch()+"'");
		sb.append(" INNER JOIN fin_claimant_main fcm ON fcm.preclr_id=fpc.preclr_id");
		sb.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		if(roVo.getStfId()!=null&&roVo.getStfId().length()>0)
			sb.append(" AND bs.stf_id="+roVo.getStfId());
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
		if(roVo.getCarLicense()!=null&&roVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(roVo.getCarLicense().trim())+"%'");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id  WHERE fr.enterprise_id="+roVo.getEnterpriseId() );
		List<ReceiveOperationVo> footers=new ArrayList();
		List<ReceiveOperationVo> rows=new ArrayList();
		List<Object[]> list=frtResevationDao.createSQLQuery(sb.toString());
		ReceiveOperationVo ros=new ReceiveOperationVo();
		ros.setItemsAmount(0d);
		ros.setPartsAmount(0d);
		ros.setSumAmount(0d);
		ros.setOtherAmount(0d);
		ros.setRealAmount(0d);
		int count=0;
		ReceiveOperationVo ro=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				ro=new ReceiveOperationVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					ro.setStfId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					ro.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					ro.setReceptionId(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					ro.setCarLicense(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					ro.setCustomName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					ro.setInterDate(MyBeanUtils.getInstance().formatString(obj[5].toString()));
				if(obj[6]!=null&&obj[6].toString().length()>0)
					ro.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[6].toString()));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					ro.setPreclrType(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0){
					ro.setItemsAmount(Double.parseDouble(obj[8].toString()));
					ros.setItemsAmount(ros.getItemsAmount()+ro.getItemsAmount());					
				}else{
					ro.setItemsAmount(0d);
				}
				if(obj[9]!=null&&obj[9].toString().length()>0){
					ro.setPartsAmount(Double.parseDouble(obj[9].toString()));
					ros.setPartsAmount(ros.getPartsAmount()+ro.getPartsAmount());					
				}else{
					ro.setPartsAmount(0d);
				}
				if(obj[10]!=null&&obj[10].toString().length()>0){
					ro.setOtherAmount(Double.parseDouble(obj[10].toString()));
					ros.setOtherAmount(ros.getOtherAmount()+ro.getOtherAmount());					
				}else{
					ro.setOtherAmount(0d);
				}
				if(obj[11]!=null&&obj[11].toString().length()>0){
					ro.setSumAmount(Double.parseDouble(obj[11].toString()));
					ros.setSumAmount(ros.getSumAmount()+ro.getSumAmount());					
				}else{
					ro.setSumAmount(0d);
				}
				if(obj[12]!=null&&obj[12].toString().length()>0){
					ro.setRealAmount(Double.parseDouble(obj[12].toString()));
					ros.setRealAmount(ros.getRealAmount()+ro.getRealAmount());					
				}else{
					ro.setRealAmount(0d);
				}
				rows.add(ro);
				count++;
			}
		HashMap<String,List<ReceiveOperationVo>> hashMap=new HashMap();
		if(rows.size()>0){
			Iterator it=null;
			String tempName=null;
			List<ReceiveOperationVo> tempList=null;
			Boolean flag=false;
			for (ReceiveOperationVo vo : rows) {
				it=hashMap.keySet().iterator();
				while(it.hasNext()){
					tempName=it.next().toString();
					if(vo.getStfId().equals(tempName)){
						tempList=hashMap.get(tempName);
						vo.setStfName(null);
						tempList.add(vo);
						flag=true;
						break;
					}
				}
				if(!flag){
					tempList=new ArrayList();
					tempList.add(vo);
					hashMap.put(vo.getStfId(), tempList);
				}
				tempName=null;
				tempList=null;
				flag=false;
			}
		}
		if(hashMap.size()>0){
			Iterator it=hashMap.keySet().iterator();
			List<ReceiveOperationVo> tempList=null;
			ReceiveOperationVo vo=null;
			while(it.hasNext()){
				tempList=hashMap.get(it.next());
				vo=new ReceiveOperationVo();
				vo.setStfId(tempList.get(0).getStfId()+"s");
				vo.setStfName(tempList.get(0).getStfName());
				vo.setReceptionId("小计");
				vo.setCarLicense(tempList.size()+"");
				vo.setPartsAmount(0d);
				vo.setItemsAmount(0d);
				vo.setSumAmount(0d);
				vo.setRealAmount(0d);
				vo.setOtherAmount(0d);
				for (ReceiveOperationVo vos : tempList) {
					vo.setItemsAmount(vo.getItemsAmount()+vos.getItemsAmount());
					vo.setPartsAmount(vo.getPartsAmount()+vos.getPartsAmount());
					vo.setOtherAmount(vo.getOtherAmount()+vos.getOtherAmount());
					vo.setSumAmount(vo.getSumAmount()+vos.getSumAmount());
					vo.setRealAmount(vo.getRealAmount()+vos.getRealAmount());
				}
				tempList.add(vo);
				tempList=null;
				vo=null;
			}
			rows.clear();
			Iterator its=hashMap.keySet().iterator();
			while(its.hasNext()){
				tempList=hashMap.get(its.next());
				for (ReceiveOperationVo vos : tempList) {
					rows.add(vos);
				}
				tempList=null;
			}
		}
		
		ros.setStfName("汇总");
		ros.setCarLicense(count+"");
		footers.add(ros);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(rows.size());
		da.setFooter(footers);
		return da;
	}
	/**
	 * 接待员业绩统计汇总
	 * */
	
	public DatagridAnalyze receiveOperationMain(ReceiveOperationVo roVo)
			throws Exception {
		setDefaultPreclrTimeSect(roVo);
		StringBuffer sb=new StringBuffer("SELECT aa.*");
		sb.append(" FROM(");
		sb.append(" SELECT bs.stf_id,bs.stf_name,COUNT(fpc.preclr_id) AS temp,SUM(fpc.preclr_wktime_amount) AS temp1,");
		sb.append(" SUM(fpc.pre_mpr_mat_amount) AS temp2,SUM(fpc.preclr_sum_amount) AS temp3,");
		sb.append(" SUM(fpc.preclr_notax_cost) AS temp4,SUM(fpc.preclr_tax_cost) AS temp5");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES  +"	 AND  fr.enterprise_id="+roVo.getEnterpriseId());
		if(roVo.getPreclrTimeBegin()!=null&&roVo.getPreclrTimeBegin().length()>0)
			sb.append(" AND fpc.preclr_time>='"+roVo.getPreclrTimeBegin()+"'");
		if(roVo.getPreclrTimeEnd()!=null&&roVo.getPreclrTimeEnd().length()>0)
			sb.append(" AND fpc.preclr_time<='"+roVo.getPreclrTimeEnd()+"'");
		if(roVo.getReceptionId()!=null&&roVo.getReceptionId().length()>0)
			sb.append(" AND fr.reception_id like '%"+StringEscapeUtils.escapeSql(roVo.getReceptionId().trim())+"%'");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		sb.append(" GROUP BY bs.stf_id");
		sb.append(" ) aa ");
		List<ReceiveOperationVo> footers=new ArrayList();
		List<ReceiveOperationVo> rows=new ArrayList();
		List<Object[]> list=frtResevationDao.createSQLQuery(sb.toString(), roVo.getPage(), roVo.getRows());
		ReceiveOperationVo ros=new ReceiveOperationVo();
		ros.setCount(0);
		ros.setItemsAmount(0d);
		ros.setPartsAmount(0d);
		ros.setSumAmount(0d);
		ros.setNoTaxCost(0d);
		ros.setTaxCost(0d);
		ros.setRate(0d);
		ReceiveOperationVo ro=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				ro=new ReceiveOperationVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					ro.setStfId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					ro.setStfName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					ro.setCount(Integer.parseInt(obj[2].toString()));
				ros.setCount(ros.getCount()+ro.getCount());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					ro.setItemsAmount(Double.parseDouble(obj[3].toString()));
				ros.setItemsAmount(ros.getItemsAmount()+ro.getItemsAmount());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					ro.setPartsAmount(Double.parseDouble(obj[4].toString()));
				ros.setPartsAmount(ros.getPartsAmount()+ro.getPartsAmount());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					ro.setSumAmount(Double.parseDouble(obj[5].toString()));
				ros.setSumAmount(ros.getSumAmount()+ro.getSumAmount());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					ro.setNoTaxCost(Double.parseDouble(obj[6].toString()));
				ros.setNoTaxCost(ros.getNoTaxCost()+ro.getNoTaxCost());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					ro.setTaxCost(Double.parseDouble(obj[7].toString()));
				ros.setTaxCost(ros.getTaxCost()+ro.getTaxCost());
				ro.setPartsCompareTime(comparePartsAndTime(ro.getPartsAmount(),ro.getItemsAmount()));
				rows.add(ro);
			}
		ros.setStfName("合计");
		ros.setPartsCompareTime(comparePartsAndTime(ros.getPartsAmount(),ros.getItemsAmount()));
		footers.add(ros);
		int total=frtResevationDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	private String comparePartsAndTime(Double partsAmount,Double timeAmount){
		return new compareDoubleToString().compareDoublePercentum(partsAmount, timeAmount);
	}
	private void setDefaultPreclrTimeSect(ReceiveOperationVo roVo)throws Exception{
		if((roVo.getPreclrTimeBegin()==null||roVo.getPreclrTimeBegin().length()==0)&&
				(roVo.getPreclrTimeEnd()==null||roVo.getPreclrTimeEnd().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				roVo.setPreclrTimeEnd(MyBeanUtils.getInstance().getString(date));
				roVo.setPreclrTimeBegin(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
