package com.syuesoft.fin.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.ManageInstanceService;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.ManageInstanceVo;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.MyBeanUtils;
/**
 * 经营情况查询Service
 * */
@Service("manageInstanceService")
public class ManageInstanceServiceImpl implements ManageInstanceService {
	@Autowired
	private FrtPreClearingDao frtPreClearingDao; 
	 @Autowired
		private FrtService frtService;
	/**
	 * 车辆结算排行
	 * */
	
	public Datagrid findCarBalanceUntangle(ManageInstanceVo miVo)
			throws Exception {
		setDefaultPreclrTimeSect(miVo);
		StringBuffer sb=new StringBuffer("SELECT fcar.car_id,fcar.car_license,bct.ctype_name,bvi.vip_id,fc.custom_name,fc.custom_tel1,");
		sb.append(" aa.temp1,bb.temp2,fc.custom_iden,fc.custom_birthday,fc.custom_addr,brc.relcamp_name,");
		sb.append(" cc.temp3,cc.temp4");
		sb.append(" FROM");
		sb.append(" frt_car fcar INNER JOIN frt_custom fc ON fcar.custom_id=fc.custom_id"); 
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id=bct.cbrd_id");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT frt.car_id,SUM(fpc.preclr_sum_amount) AS temp1");
		sb.append(" FROM frt_reception frt,frt_pre_clearing fpc WHERE fpc.reception_id=frt.reception_id ");
		if(miVo.getPreclrTimeBegin()!=null&&miVo.getPreclrTimeBegin().length()>0){
			sb.append(" AND fpc.preclr_time>='"+miVo.getPreclrTimeBegin()+"'");
		}
		if(miVo.getPreclrTimeEnd()!=null&&miVo.getPreclrTimeEnd().length()>0){
			sb.append(" AND fpc.preclr_time<='"+miVo.getPreclrTimeEnd()+"'");
		}
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"  AND frt.enterprise_id="+miVo.getEnterpriseId()+"  GROUP BY frt.car_id");
		sb.append(" ) aa ON aa.car_id=fcar.car_id");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT frt.car_id,COUNT(frt.reception_id) AS temp2 FROM frt_reception frt  WHERE 1=1");
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" AND frt.rcpt_flg="+Contstants.DELETE_TAG.DELETENO+" AND frt.enterprise_id="+miVo.getEnterpriseId()+" GROUP BY frt.car_id");
		sb.append(" ) bb ON bb.car_id=fcar.car_id");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT temp.car_id,temp.temp3,temp.temp4 FROM (");
		sb.append(" SELECT frt.car_id,frt.reception_distance AS temp3, frt.inter_date AS temp4 FROM frt_reception frt WHERE 1=1");
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" AND frt.rcpt_flg="+Contstants.DELETE_TAG.DELETENO+"  AND frt.enterprise_id="+miVo.getEnterpriseId()+"  ORDER BY frt.inter_date DESC");
		sb.append(" ) temp GROUP BY temp.car_id");
		sb.append(" ) cc ON cc.car_id=fcar.car_id");
		sb.append(" LEFT OUTER JOIN bas_vip_infor bvi ON  bvi.CAR_VIN = fcar.CAR_VIN");
		sb.append(" LEFT OUTER JOIN bas_relation_campany brc ON brc.relcamp_id=fcar.relcamp_id");
		sb.append(" WHERE 1=1  AND   fcar.enterprise_id="+miVo.getEnterpriseId()+" AND  fc.CUSTOM_FLAG=TRUE");
		if(miVo.getCbrdId()!=null&&miVo.getCbrdId().length()>0)
			sb.append(" and bcb.cbrd_id="+miVo.getCbrdId());
		if(miVo.getCarLicense()!=null&&miVo.getCarLicense().length()>0)
			sb.append(" and fcar.car_license like '%"+StringEscapeUtils.escapeSql(miVo.getCarLicense().trim())+"%'");
		if(miVo.getCustomName()!=null&&miVo.getCustomName().length()>0)
			sb.append(" and fc.custom_name like '%"+StringEscapeUtils.escapeSql(miVo.getCustomName().trim())+"%'");
		List<ManageInstanceVo> rows=new ArrayList<ManageInstanceVo>();
		ManageInstanceVo miv=null;
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString(), miVo.getPage(), miVo.getRows());
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				miv=new ManageInstanceVo();
				if(obj[1]!=null&&obj[1].toString().length()>0)
					miv.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					miv.setCtypeName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					miv.setVipId(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					miv.setCustomName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					miv.setCustomTel1(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					miv.setPreclrSumAmount(Double.parseDouble(obj[6].toString()));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					miv.setReceptionCount(Integer.parseInt(obj[7].toString()));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					miv.setCustomIden(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					miv.setCustomBirthday(MyBeanUtils.getInstance().getString(MyBeanUtils.getInstance().getDate(obj[9].toString()),"yyyy-MM-dd"));
				if(obj[10]!=null&&obj[10].toString().length()>0)
					miv.setCustomAddr(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					miv.setRelcmapName(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					miv.setLastDistance(Integer.parseInt(obj[12].toString()));
				if(obj[13]!=null&&obj[13].toString().length()>0)
					miv.setLastInterDate(MyBeanUtils.getInstance().formatString(obj[13].toString()));
				rows.add(miv);	
			}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 成本分析
	 * */
	
	public DatagridAnalyze findCostAanalyse(ManageInstanceVo miVo) throws Exception {
		setDefaultPreclrTimeSect(miVo);
		StringBuffer sb=new StringBuffer("SELECT DISTINCT bcb.cbrd_id,bcb.cbrd_name,aa.temp1,bb.temp2,bb.temp3,");
		sb.append(" bb.temp4,bb.temp5,cc.temp6,cc.temp7,cc.temp8,cc.temp9,dd.temp10,dd.temp11");
		sb.append(" FROM bas_car_brand bcb INNER JOIN bas_car_type bct ON  bct.cbrd_id=bcb.cbrd_id");
		sb.append(" INNER JOIN frt_car fcar ON fcar.ctype_id=bct.ctype_id");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT bcb.cbrd_id,COUNT(frt.reception_id) AS temp1 FROM");
		sb.append(" frt_reception frt INNER JOIN frt_car fcar ON fcar.car_id=frt.car_id");
		sb.append(" INNER JOIN bas_car_type bct ON fcar.ctype_id=bct.ctype_id");	 
		sb.append(" INNER JOIN bas_car_brand bcb  ON bct.cbrd_id=bcb.cbrd_id");
		sb.append(" WHERE 1=1 AND  frt.enterprise_id="+miVo.getEnterpriseId());
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" and frt.rcpt_flg="+Contstants.DELETE_TAG.DELETENO+" GROUP BY bcb.cbrd_id");
		sb.append(" ) aa ON aa.cbrd_id=bcb.cbrd_id");
		sb.append(" INNER JOIN (");
		sb.append(" SELECT bcb.cbrd_id,COUNT(fpc.preclr_id) AS temp2,SUM(fpc.preclr_sum_amount) AS temp3,");
		sb.append(" SUM(fpc.preclr_notax_cost) AS temp4,SUM(fpc.preclr_tax_cost) AS temp5 FROM");	
		sb.append(" frt_pre_clearing fpc INNER JOIN  frt_reception frt ON fpc.reception_id=frt.reception_id");	
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=frt.car_id ");	
		sb.append(" INNER JOIN bas_car_type bct ON fcar.ctype_id=bct.ctype_id");	
		sb.append(" INNER JOIN bas_car_brand bcb  ON bct.cbrd_id=bcb.cbrd_id");	
		sb.append(" WHERE fpc.pre_flg="+Contstants.DELETE_TAG.DELETENO);
		if(miVo.getPreclrTimeBegin()!=null&&miVo.getPreclrTimeBegin().length()>0){
			sb.append(" AND fpc.preclr_time>='"+miVo.getPreclrTimeBegin()+"'");
		}
		if(miVo.getPreclrTimeEnd()!=null&&miVo.getPreclrTimeEnd().length()>0){
			sb.append(" AND fpc.preclr_time<='"+miVo.getPreclrTimeEnd()+"'");
		}
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fpc.enterprise_id="+miVo.getEnterpriseId()+" GROUP BY bcb.cbrd_id");
		sb.append(" ) bb ON bb.cbrd_id=bcb.cbrd_id");
		sb.append(" LEFT OUTER JOIN (");
		sb.append(" SELECT bcb.cbrd_id,COUNT(fcm.claimantm_id) AS temp6,SUM(fcm.claimantm_amount) AS temp7,");	
		sb.append(" SUM(fcm.claimantm_notax_cost) AS temp8,SUM(fcm.claimantm_tax_cost) AS temp9");	
		sb.append(" FROM");	
		sb.append(" fin_claimant_main fcm INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id");	
		sb.append(" INNER JOIN  frt_reception frt ON fpc.reception_id=frt.reception_id");	
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=frt.car_id");	
		sb.append(" INNER JOIN bas_car_type bct ON fcar.ctype_id=bct.ctype_id");
		sb.append(" INNER JOIN bas_car_brand bcb  ON bct.cbrd_id=bcb.cbrd_id");	
		sb.append(" WHERE fpc.pre_flg="+Contstants.DELETE_TAG.DELETENO);
		if(miVo.getPreclrTimeBegin()!=null&&miVo.getPreclrTimeBegin().length()>0){
			sb.append(" AND fpc.preclr_time>='"+miVo.getPreclrTimeBegin()+"'");
		}
		if(miVo.getPreclrTimeEnd()!=null&&miVo.getPreclrTimeEnd().length()>0){
			sb.append(" AND fpc.preclr_time<='"+miVo.getPreclrTimeEnd()+"'");
		}
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND  fcm.enterprise_id="+miVo.getEnterpriseId()+" GROUP BY bcb.cbrd_id");
		sb.append(" ) cc ON cc.cbrd_id=bcb.cbrd_id");
		sb.append(" LEFT OUTER JOIN (");
		sb.append(" SELECT bcb.cbrd_id,SUM(som.NOTAX_CASTAMONT) AS temp10,SUM(som.TAX_CASTAMONT) AS temp11");
		sb.append(" FROM st_out_item soi INNER JOIN st_out_main som ON som.STOM_ID=soi.STOM_ID");		
		sb.append(" INNER JOIN frt_reception frt ON frt.reception_id=som.CER_NO");		
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=frt.car_id");		
		sb.append(" INNER JOIN bas_car_type bct ON fcar.ctype_id=bct.ctype_id");	 
		sb.append(" INNER JOIN bas_car_brand bcb  ON bct.cbrd_id=bcb.cbrd_id");		
		sb.append(" WHERE frt.rcpt_flg="+Contstants.DELETE_TAG.DELETENO);		
		sb.append(" AND frt.reception_id NOT IN(");		
		sb.append(" SELECT fpc.reception_id FROM frt_pre_clearing fpc WHERE fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYNO +"  AND  fpc.enterprise_id="+miVo.getEnterpriseId() );
		if(miVo.getPreclrTimeBegin()!=null&&miVo.getPreclrTimeBegin().length()>0){
			sb.append(" AND fpc.preclr_time>='"+miVo.getPreclrTimeBegin()+"'");
		}
		if(miVo.getPreclrTimeEnd()!=null&&miVo.getPreclrTimeEnd().length()>0){
			sb.append(" AND fpc.preclr_time<='"+miVo.getPreclrTimeEnd()+"'");
		}
		if(miVo.getReptId()!=null&&miVo.getReptId().length()>0)
			sb.append(" AND frt.rept_id="+miVo.getReptId());
		sb.append(" )GROUP BY bcb.cbrd_id");		
		sb.append(" ) dd ON dd.cbrd_id=bcb.cbrd_id where 1=1   and  bcb.enterprise_id="+miVo.getEnterpriseId());		
		if(miVo.getCbrdId()!=null&&miVo.getCbrdId().length()>0)
			sb.append(" and bcb.cbrd_id="+miVo.getCbrdId());
		
		List<ManageInstanceVo> rows=new ArrayList();
		List<ManageInstanceVo> footers=new ArrayList();
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString(), miVo.getPage(), miVo.getRows());
		ManageInstanceVo miv=null;
		ManageInstanceVo mi=new ManageInstanceVo();
		mi.setReceptionCount(0);
		mi.setPreclrCount(0);
		mi.setPreclrSumAmount(0d);
		mi.setPreclrNoTaxAmount(0d);
		mi.setPreclrTaxAmount(0d);
		mi.setClaimantCount(0);
		mi.setClaimantSumAmount(0d);
		mi.setClaimantNoTaxAmount(0d);
		mi.setClaimantTaxAmount(0d);
		mi.setUnPreclrNoTaxAmount(0d);
		mi.setUnPreclrTaxAmount(0d);
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				miv=new ManageInstanceVo();
				if(obj[1]!=null&&obj[1].toString().length()>0)
					miv.setCbrdName(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0){
					miv.setReceptionCount(Integer.parseInt(obj[2].toString()));
					mi.setReceptionCount(mi.getReceptionCount()+miv.getReceptionCount());					
				}
				if(obj[3]!=null&&obj[3].toString().length()>0){
					miv.setPreclrCount(Integer.parseInt(obj[3].toString()));
					mi.setPreclrCount(mi.getPreclrCount()+miv.getPreclrCount());					
				}
				if(obj[4]!=null&&obj[4].toString().length()>0){
					miv.setPreclrSumAmount(Double.parseDouble(obj[4].toString()));
					mi.setPreclrSumAmount(mi.getPreclrSumAmount()+miv.getPreclrSumAmount());					
				}
				if(obj[5]!=null&&obj[5].toString().length()>0){
					miv.setPreclrNoTaxAmount(Double.parseDouble(obj[5].toString()));
					mi.setPreclrNoTaxAmount(mi.getPreclrNoTaxAmount()+miv.getPreclrNoTaxAmount());					
				}
				if(obj[6]!=null&&obj[6].toString().length()>0){
					miv.setPreclrTaxAmount(Double.parseDouble(obj[6].toString()));
					mi.setPreclrTaxAmount(mi.getPreclrTaxAmount()+miv.getPreclrTaxAmount());					
				}
				if(obj[7]!=null&&obj[7].toString().length()>0){
					miv.setClaimantCount(Integer.parseInt(obj[7].toString()));
					mi.setClaimantCount(mi.getClaimantCount()+miv.getClaimantCount());					
				}
				if(obj[8]!=null&&obj[8].toString().length()>0){
					miv.setClaimantSumAmount(Double.parseDouble(obj[8].toString()));
					mi.setClaimantSumAmount(mi.getClaimantSumAmount()+miv.getClaimantSumAmount());					
				}
				if(obj[9]!=null&&obj[9].toString().length()>0){
					miv.setClaimantNoTaxAmount(Double.parseDouble(obj[9].toString()));
					mi.setClaimantNoTaxAmount(mi.getClaimantNoTaxAmount()+miv.getClaimantNoTaxAmount());					
				}
				if(obj[10]!=null&&obj[10].toString().length()>0){
					miv.setClaimantTaxAmount(Double.parseDouble(obj[10].toString()));
					mi.setClaimantTaxAmount(mi.getClaimantTaxAmount()+miv.getClaimantTaxAmount());					
				}
				if(obj[11]!=null&&obj[11].toString().length()>0){
					miv.setUnPreclrNoTaxAmount(Double.parseDouble(obj[11].toString()));
					mi.setUnPreclrNoTaxAmount(mi.getUnPreclrNoTaxAmount()+miv.getUnPreclrNoTaxAmount());					
				}
				if(obj[12]!=null&&obj[12].toString().length()>0){
					miv.setUnPreclrTaxAmount(Double.parseDouble(obj[12].toString()));
					mi.setUnPreclrTaxAmount(mi.getUnPreclrTaxAmount()+miv.getUnPreclrTaxAmount());					
				}
				rows.add(miv);
			}
		footers.add(mi);
		DatagridAnalyze dga=new DatagridAnalyze();
		dga.setRows(rows);
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		dga.setTotal(total);
		dga.setFooter(footers);
		return dga;
	}
	private void setDefaultPreclrTimeSect(ManageInstanceVo miVo)throws Exception{
		if((miVo.getPreclrTimeBegin()==null||miVo.getPreclrTimeBegin().length()==0)&&
				(miVo.getPreclrTimeEnd()==null||miVo.getPreclrTimeEnd().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				miVo.setPreclrTimeEnd(MyBeanUtils.getInstance().getString(date));
				miVo.setPreclrTimeBegin(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
