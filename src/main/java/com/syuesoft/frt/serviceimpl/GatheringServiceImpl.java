package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasChilddictionaryDao;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.FinClaimsReceivablesDao;
import com.syuesoft.fin.dao.FinMaintenanceReceivablesDao;
import com.syuesoft.fin.dao.StRechargeDao;
import com.syuesoft.fin.dao.StSellChargeDao;
import com.syuesoft.fin.dao.StSellPerchargeDao;
import com.syuesoft.fin.dao.StUsePerchargeDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.BatchBalanceDetailDao;
import com.syuesoft.frt.dao.BatchBalanceGatherDao;
import com.syuesoft.frt.dao.BatchPaymentDetailDao;
import com.syuesoft.frt.dao.FinClaimantMainDao;
import com.syuesoft.frt.dao.FinCollectionScheduleDao;
import com.syuesoft.frt.dao.FinCounterclaimScheduleDao;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.dao.SubstituteBalanceDao;
import com.syuesoft.frt.dao.SubstituteBatchBalanceDetailDao;
import com.syuesoft.frt.dao.SubstitutePaymentDetailDao;
import com.syuesoft.frt.dao.SubstituteSuitPaymentDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.service.GatheringService;
import com.syuesoft.frt.vo.GatheringVo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BatchBalanceDetail;
import com.syuesoft.model.BatchBalanceGather;
import com.syuesoft.model.BatchPaymentDetail;
import com.syuesoft.model.FinClaimantMain;
import com.syuesoft.model.FinClaimsReceivables;
import com.syuesoft.model.FinCollectionSchedule;
import com.syuesoft.model.FinCounterclaimSchedule;
import com.syuesoft.model.FinMaintenanceReceivables;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.model.FrtReception;
import com.syuesoft.model.StRecharge;
import com.syuesoft.model.StSellCharge;
import com.syuesoft.model.StSellOrder;
import com.syuesoft.model.StSellPercharge;
import com.syuesoft.model.StUsePercharge;
import com.syuesoft.model.SubstituteBalance;
import com.syuesoft.model.SubstituteBatchBalanceDetail;
import com.syuesoft.model.SubstitutePaymentDetail;
import com.syuesoft.model.SubstituteSuitPayment;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;
@Transactional
@Service("gatheringServiceImpl")
public class GatheringServiceImpl extends BaseLogServiceImpl implements
		GatheringService {
	@Autowired
	private FrtPreClearingDao frtPreClearingDao;
	@Autowired FrtReceptionDao frtReceptionDao;
	
	@Autowired
	private FinClaimantMainDao finClaimantMainDao;
	@Autowired
	private FinCollectionScheduleDao finCollectionScheduleDao;
	@Autowired
	private FinCounterclaimScheduleDao finCounterclaimScheduleDao;
	@Autowired
	private FinMaintenanceReceivablesDao finMaintenanceReceivablesDao;
	@Autowired
	private FinClaimsReceivablesDao finClaimsReceivablesDao;
	@Autowired
	private BatchBalanceDetailDao batchBalanceDetailDao;
	@Autowired
	private BatchBalanceGatherDao batchBalanceGatherDao;
	@Autowired
	private StRechargeDao stRechargeDao;
	@Autowired
	private StSellPerchargeDao stSellPerchargeDao;
	@Autowired
	private BasStuffDao basStuffDao;
	@Autowired
	private SubstituteBalanceDao substituteBalanceDao;
	@Autowired
	private SubstituteBatchBalanceDetailDao substituteBatchBalanceDetailDao;
	@Autowired
	private SubstitutePaymentDetailDao substitutePaymentDetailDao;
	@Autowired
	private SubstituteSuitPaymentDao substituteSuitPaymentDao;
	@Autowired
	private BatchPaymentDetailDao batchPaymentDetailDao;
	@Autowired
	private StSellChargeDao stSellChargeDao;
	@Autowired
	private BasChilddictionaryDao basChilddictionaryDao;
	@Autowired
	private FrtService frtService;
	@Autowired
	private StUsePerchargeDao stUsePerchargeDao;
	@Autowired private StSellOrderDAO stSellOrderDAO;
	/**
	 * 查找收款信息
	 * */
	
	public Datagrid datagridGathering(GatheringVo gtVo) throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellGathering(gtVo);
		}
		return null;
	}
	
	/**查找维修收款信息*/
	private Datagrid datagridPreclearingGathering(GatheringVo gtVo) throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT fpc.preclr_id,fpc.preclr_time,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,fmr.MR_RECEIVABLES,fmr.MR_CUMULATIVE,fmr.MR_ARREARS,fmr.MR_UNFINISHED");
		sb1.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,frt_car fcar,fin_maintenance_receivables fmr,"); 
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fc.custom_id=fr.custom_id AND fpc.reception_id=fr.reception_id AND fmr.PRECLR_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND a.dataKey=fpc.preclr_inoice_type");
		sb1.append(" AND fmr.MR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED  +"   and   fpc.enterprise_id="+  gtVo.getEnterpriseId());
		
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null){
			sb1.append(" AND fpc.preclr_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin like '%"+StringEscapeUtils.escapeSql(gtVo.getCarVin().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		return commonDatagridGathering(sb1.toString(),gtVo);
	}
	/**查找销售收款信息*/
	private Datagrid datagridSellGathering(GatheringVo gtVo) throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT sspm.PRECLR_ID,sspm.PRECLR_DATE,fc.CUSTOM_ID,fc.custom_name,'' as temp1,");
		sb1.append(" '' as temp2,'' as temp3,'' as temp4,ssc.PAID_AMOUNT,ssc.TOTAL_AMOUNT,ssc.PAID_BALANCE,ssc.CHARGE_UNFINISHED");
		sb1.append(" FROM st_sell_charge ssc,st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc");
		sb1.append(" WHERE ssc.PRECLR_ID=sspm.PRECLR_ID AND sso.SELLMM_ID=sspm.CER_NO");
		sb1.append(" AND fc.CUSTOM_ID=sso.SELLCUSTOM_ID");
		sb1.append(" AND sspm.STATE="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssc.CHARGE_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssc.enterprise_id="+gtVo.getEnterpriseId()    );
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null){
			sb1.append(" AND sspm.PRECLR_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.PRECLR_DATE>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.PRECLR_DATE<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		return commonDatagridGathering(sb1.toString(),gtVo);
	}
	/**查找索赔收款信息*/
	private Datagrid datagridClaimantGathering(GatheringVo gtVo) throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT fcm.claimantm_id,fcm.claimantm_time,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,fcr.CR_RECEIVABLES,fcr.CR_CUMULATIVE,fcr.CR_ARREARS,fcr.CR_UNFINISHED");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");	
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND fr.fin_com_id=brc.relcamp_id");
		sb1.append(" AND fcr.CR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED  +"  and   fcm.enterprise_id="+gtVo.getEnterpriseId());
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null){
			sb1.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		return commonDatagridGathering(sb1.toString(),gtVo);
	}
	/**查找所有收款信息*/
	private Datagrid datagridAllGathering(GatheringVo gtVo) throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT fpc.preclr_id,fpc.preclr_time,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,fmr.MR_RECEIVABLES,fmr.MR_CUMULATIVE,fmr.MR_ARREARS,fmr.MR_UNFINISHED");
		sb1.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,frt_car fcar,fin_maintenance_receivables fmr,"); 
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fc.custom_id=fr.custom_id AND fpc.reception_id=fr.reception_id AND fmr.PRECLR_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND a.dataKey=fpc.preclr_inoice_type");
		sb1.append(" AND fmr.MR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +"  and  fpc.enterprise_id="+gtVo.getEnterpriseId()  );
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND fpc.preclr_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_id='"+gtVo.getCarId()+"'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin like '%"+StringEscapeUtils.escapeSql(gtVo.getCarVin().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" UNION");
		sb1.append(" SELECT sspm.PRECLR_ID,sspm.PRECLR_DATE,fc.CUSTOM_ID,fc.custom_name,'' as temp1,");
		sb1.append(" '' as temp2,'' as temp3,'' as temp4,ssc.PAID_AMOUNT,ssc.TOTAL_AMOUNT,ssc.PAID_BALANCE,ssc.CHARGE_UNFINISHED");
		sb1.append(" FROM st_sell_charge ssc,st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc");
		sb1.append(" WHERE ssc.PRECLR_ID=sspm.PRECLR_ID AND sso.SELLMM_ID=sspm.CER_NO");
		sb1.append(" AND fc.CUSTOM_ID=sso.SELLCUSTOM_ID");
		sb1.append(" AND sspm.STATE="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssc.CHARGE_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND sspm.PRECLR_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.PRECLR_DATE>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.PRECLR_DATE<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		sb1.append(" UNION");
		sb1.append(" SELECT fcm.claimantm_id,fcm.claimantm_time,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,fcr.CR_RECEIVABLES,fcr.CR_CUMULATIVE,fcr.CR_ARREARS,fcr.CR_UNFINISHED");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");	
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND fr.fin_com_id=brc.relcamp_id");
		sb1.append(" AND fcr.CR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		return commonDatagridGathering(sb1.toString(),gtVo);
	}
	private Datagrid commonDatagridGathering(String sql,GatheringVo gtVo) throws Exception {
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			if(objects[11]!=null&&objects[11].toString().length()>0)
				gatheringVo.setUnAchieve(Short.parseShort(objects[11].toString()));
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	private void setDefaultGaheringTimeSect(GatheringVo gtVo)throws Exception{
		if(gtVo.getGatheringBeginTime()==null&&gtVo.getGatheringEndTime()==null){
			String temp=frtService.findDefaultBalanceTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				gtVo.setGatheringEndTime(date);
				gtVo.setGatheringBeginTime(new Date(time));
			}
		}
	}
	
	/**
	 * 查找未收款信息
	 * */
	
	public Datagrid datagridNoGathering(GatheringVo gtVo) throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){//
			return datagridAllNoGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoGathering(gtVo);
		}
		return null;
	}
	
	/**查找维修未收款记录*/
	private Datagrid datagridPreclearingNoGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT fpc.preclr_id,fpc.preclr_time,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,fmr.MR_RECEIVABLES,fmr.MR_CUMULATIVE,fmr.MR_ARREARS");
		sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,frt_car fcar,fin_maintenance_receivables fmr,"); 
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb.append(" WHERE fc.custom_id=fr.custom_id AND fpc.reception_id=fr.reception_id AND fmr.PRECLR_ID=fpc.PRECLR_ID");
		sb.append(" and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND a.dataKey=fpc.preclr_inoice_type");
		sb.append(" AND fmr.MR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null)
			sb.append(" AND fpc.preclr_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		if(gtVo.getCarLicense()!=null&&gtVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarLicense().trim())+"%'");
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0)
			sb.append(" AND fcar.car_vin like '%"+StringEscapeUtils.escapeSql(gtVo.getCarVin().trim())+"%'");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0)
			sb.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(fpc.preclr_time,\'%Y-%m-%d\')>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(fpc.preclr_time,\'%Y-%m-%d\')<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0)
			sb.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0)
			sb.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0)
			sb.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0)
			sb.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0)
			sb.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0)
			sb.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**查找索赔未收款记录*/
	private Datagrid datagridClaimantNoGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT fcm.claimantm_id,fcm.claimantm_time,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,fcr.CR_RECEIVABLES,fcr.CR_CUMULATIVE,fcr.CR_ARREARS");
		sb.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");	
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
		sb.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb.append(" AND fr.fin_com_id=brc.relcamp_id and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb.append(" AND fcr.CR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null)
			sb.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0)
			sb.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0)
			sb.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0)
			sb.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0)
			sb.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0)
			sb.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0)
			sb.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0)
			sb.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				if(objects[0]!=null&&objects[0].toString().length()>0)
					gatheringVo.setPreclrId(objects[0].toString());
				if(objects[1]!=null&&objects[1].toString().length()>0)
					gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
				if(objects[2]!=null&&objects[2].toString().length()>0)
					gatheringVo.setCustomId(objects[2].toString());
				if(objects[3]!=null&&objects[3].toString().length()>0)
					gatheringVo.setCustomName(objects[3].toString());
				if(objects[4]!=null&&objects[4].toString().length()>0)
					gatheringVo.setCarLicense(objects[4].toString());
				if(objects[5]!=null&&objects[5].toString().length()>0)
					gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
				if(objects[6]!=null&&objects[6].toString().length()>0)
					gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
				if(objects[7]!=null&&objects[7].toString().length()>0)
					gatheringVo.setPreclrNo(objects[7].toString());
				if(objects[8]!=null&&objects[8].toString().length()>0)
					gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
				else
					gatheringVo.setPreclrAmount(0.00d);
				if(objects[9]!=null&&objects[9].toString().length()>0)
					gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
				else
					gatheringVo.setCumulativeAmount(0.00d);
				if(objects[10]!=null&&objects[10].toString().length()>0)
					gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
				else
					gatheringVo.setArrearsAmount(0.00d);
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	/**
	 * 查找销售未收款记录
	 */
	private Datagrid datagridSellNoGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT PRECLR_ID,PRECLR_TIME,SELLCUSTOM_ID,CUSTOM_NAME,CAR_LICENSE,"+
		" dataValue,PRECLR_INVOICE_TIME,PRECLR_NO,MR_RECEIVABLES,MR_CUMULATIVE,MR_ARREARS FROM ("+
		" SELECT c.*, bc.dataValue FROM ("+
		" SELECT b.*,frt_custom.CUSTOM_NAME FROM("+
		" SELECT a.*,st_sell_order.SELLCUSTOM_ID,st_sell_order.CAR_LICENSE FROM("+
		" SELECT fin_maintenance_receivables.*,frt_pre_clearing.PRECLR_TIME"+
		" ,frt_pre_clearing.PRECLR_INVOICE_TIME,frt_pre_clearing.PRECLR_NO,frt_pre_clearing.RECEPTION_ID"+
		" ,frt_pre_clearing.PRECLR_TO_MONEY,frt_pre_clearing.PRECLR_INOICE_TYPE"+
		" FROM fin_maintenance_receivables LEFT JOIN frt_pre_clearing"+
		" ON fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID and frt_pre_clearing.enterprise_id="+gtVo.getEnterpriseId()+") AS a"+
		" INNER JOIN st_sell_order ON a.RECEPTION_ID=st_sell_order.SELLMM_ID)AS b"+
		" INNER JOIN frt_custom ON b.SELLCUSTOM_ID=frt_custom.CUSTOM_ID) AS c"+
		" INNER JOIN bas_childdictionary bc ON c.preclr_inoice_type=bc.dataKey) AS d"+
		" WHERE PRECLR_ID LIKE 'XSJS%' AND MR_BATCH_TAG=1 AND PRECLR_TO_MONEY=1");
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null)
			sb.append(" AND PRECLR_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0)
			sb.append(" AND SELLCUSTOM_ID='"+gtVo.getCustomId()+"'");
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(PRECLR_TIME,\'%Y-%m-%d\') >='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(PRECLR_TIME,\'%Y-%m-%d\') <='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
		for (Object[] objects : rowsList) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	/**
	 *查找所有未收款记录
    */
	private Datagrid datagridAllNoGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("" +
		" select * from (SELECT PRECLR_ID,PRECLR_TIME,CUSTOM_ID,CUSTOM_NAME,CAR_LICENSE,"+
		" dataValue,PRECLR_INVOICE_TIME,PRECLR_NO,MR_RECEIVABLES,MR_CUMULATIVE,MR_ARREARS,CAR_VIN,preclr_inoice_type,CAR_RELATION_PERSON,CAR_RELATION_TEL1 FROM ("+
		" SELECT d.*,frt_car.CAR_LICENSE,frt_car.CAR_VIN,frt_car.CAR_RELATION_PERSON,frt_car.CAR_RELATION_TEL1 FROM ("+
		" SELECT c.*, bc.dataValue FROM ("+
		" SELECT b.*,frt_custom.CUSTOM_NAME FROM("+
		" SELECT a.*,frt_reception.CAR_ID,frt_reception.CUSTOM_ID FROM("+
		" SELECT fin_maintenance_receivables.*,frt_pre_clearing.PRECLR_TIME"+
		" ,frt_pre_clearing.PRECLR_INVOICE_TIME,frt_pre_clearing.PRECLR_NO,frt_pre_clearing.RECEPTION_ID"+
		" ,frt_pre_clearing.PRECLR_TO_MONEY,frt_pre_clearing.PRECLR_INOICE_TYPE"+
		" FROM fin_maintenance_receivables LEFT JOIN frt_pre_clearing"+
		" ON fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID  and frt_pre_clearing.enterprise_id="+gtVo.getEnterpriseId()+") AS a"+
		" INNER JOIN frt_reception ON a.RECEPTION_ID=frt_reception.RECEPTION_ID)AS b"+
		" INNER JOIN frt_custom ON b.CUSTOM_ID=frt_custom.CUSTOM_ID) AS c"+
		" INNER JOIN bas_childdictionary bc ON c.preclr_inoice_type=bc.dataKey) AS d"+
		" LEFT JOIN frt_car ON d.CAR_ID =frt_car.CAR_ID) AS e"+
		" WHERE PRECLR_ID LIKE 'JS%' AND MR_BATCH_TAG=1 AND PRECLR_TO_MONEY=1 "+
		" UNION"+
		" SELECT PRECLR_ID,PRECLR_TIME,SELLCUSTOM_ID,CUSTOM_NAME,CAR_LICENSE,"+
		" dataValue,PRECLR_INVOICE_TIME,PRECLR_NO,MR_RECEIVABLES,MR_CUMULATIVE,MR_ARREARS,NULL AS CAR_VIN,preclr_inoice_type ,NULL AS CAR_RELATION_PERSON,NULL AS CAR_RELATION_TEL1 FROM ("+
		" SELECT c.*, bc.dataValue FROM ("+
		" SELECT b.*,frt_custom.CUSTOM_NAME FROM("+
		" SELECT a.*,st_sell_order.SELLCUSTOM_ID,st_sell_order.CAR_LICENSE FROM("+
		" SELECT fin_maintenance_receivables.*,frt_pre_clearing.PRECLR_TIME"+
		" ,frt_pre_clearing.PRECLR_INVOICE_TIME,frt_pre_clearing.PRECLR_NO,frt_pre_clearing.RECEPTION_ID"+
		" ,frt_pre_clearing.PRECLR_TO_MONEY,frt_pre_clearing.PRECLR_INOICE_TYPE"+
		" FROM fin_maintenance_receivables LEFT JOIN frt_pre_clearing"+
		" ON fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID) AS a"+
		" INNER JOIN st_sell_order ON a.RECEPTION_ID=st_sell_order.SELLMM_ID and st_sell_order.enterprise_id="+gtVo.getEnterpriseId()+" )AS b"+
		" INNER JOIN frt_custom ON b.SELLCUSTOM_ID=frt_custom.CUSTOM_ID) AS c"+
		" INNER JOIN bas_childdictionary bc ON c.preclr_inoice_type=bc.dataKey) AS d"+
		" WHERE PRECLR_ID LIKE 'XSJS%' AND MR_BATCH_TAG=1 AND PRECLR_TO_MONEY=1");
        sb.append(" UNION ");
        sb.append(" SELECT fcm.claimantm_id,fcm.claimantm_time,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
        sb.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,fcr.CR_RECEIVABLES,fcr.CR_CUMULATIVE,fcr.CR_ARREARS,NULL AS CAR_VIN,NULL AS claimantm_invoice_type,NULL AS CAR_RELATION_PERSON,NULL AS CAR_RELATION_TEL1");
        sb.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");	
        sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
        sb.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
        sb.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
        sb.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
        sb.append(" AND fr.fin_com_id=brc.relcamp_id and fcm.enterprise_id="+gtVo.getEnterpriseId()+") as f where 1=1 ");
        if(gtVo.getBalanceId()!=null&&!gtVo.getBalanceId().equals(""))
			sb.append(" AND PRECLR_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"'");
		if(gtVo.getCarLicense()!=null&&gtVo.getCarLicense().length()>0)
			sb.append(" AND CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(gtVo.getCarLicense().trim())+"%'");
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0)
			sb.append(" AND CAR_VIN like '%"+StringEscapeUtils.escapeSql(gtVo.getCarVin().trim())+"%'");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0)
			sb.append(" AND CUSTOM_ID='"+gtVo.getCustomId()+"'");
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(PRECLR_TIME,\'%Y-%m-%d\') >='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0)
			sb.append(" AND DATE_FORMAT(PRECLR_TIME,\'%Y-%m-%d\') <='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0)
			sb.append(" AND PRECLR_INVOICE_TIME>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0)
			sb.append(" AND PRECLR_INVOICE_TIME<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0)
			sb.append(" AND PRECLR_NO like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0)
			sb.append(" AND preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0)
			sb.append(" AND f.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0)
			sb.append(" AND f.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	
	private String opinionId(String id,String tag){
		StringBuffer sb=new StringBuffer();
		if(id!=null&&id.length()>0){
			for(int i=0;i<id.length();i++){
				if(!(id.charAt(i)>='0'&&id.charAt(i)<='9'))
					sb.append(id.charAt(i));
			}
			if(sb.toString().equals(tag))
				return id;
			else
				return null;			
		}else
			return null;
		
	} 
	
	/**
	 * 增加索赔收款信息
	 * */
	@Log(moduleName="前台管理",content="新增索赔收款",opertype="新增")
	
	public Msg saveClaimantGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		try {
			if(gtVo.getUnAchieve()==null)
				gtVo.setUnAchieve(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			if(gtVo.getSubstitute()==null)
				gtVo.setSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			FinClaimsReceivables fcr=finClaimsReceivablesDao.get("from FinClaimsReceivables fcr where fcr.claimantmId='"+gtVo.getPreclrId()+"'");
			if(fcr != null){
    			if(fcr.getCrCumulative()==null)
    				fcr.setCrCumulative(0.00d);
    			fcr.setCrCumulative(fcr.getCrCumulative()+gtVo.getPreclrRealAmount());
    			//fcr.setCrUnFinished(gtVo.getUnAchieve());
    			if(fcr.getCrSubstitute()==null)
    				fcr.setCrSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    			//代付
    			if(gtVo.getSubstituteClaimantId()!=null&&gtVo.getSubstituteClaimantId().toString().length()>0
    					&&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){    
    				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
    				ssp.setSspId(CreateID.createId("SubstituteSuitPayment_claimant", Contstants.CLAIMSSUITSUBTITUTEGATHERING));
    				ssp.setCustomId(gtVo.getSubstituteClaimantId());
    				ssp.setSspReceivables(gtVo.getSubstituteMoney());
    				ssp.setSspCumulative(0.00d);
    				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
    				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspArrears(gtVo.getSubstituteMoney());
    				ssp.setSspDate(new Date());
    				ssp.setBalanceId(gtVo.getGatheringId());
    				if(ssp.getSspArrears()==0.00){
    					ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
    				}
    				substituteSuitPaymentDao.save(ssp);
    				SubstituteBalance sb=new SubstituteBalance();
    				sb.setSspId(ssp.getSspId());
    				sb.setStrikeId(gtVo.getPreclrId());
    				substituteBalanceDao.save(sb);
    				fcr.setCrSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
    			}else{
        			FinCounterclaimSchedule fccs=new FinCounterclaimSchedule();
        			fccs.setFccsId(gtVo.getGatheringId());                                                               //结算单号
        			fccs.setFccsDate(new Date());                                                                        //付款日期
        			fccs.setFccsPayable(gtVo.getPreclrAmount());                                                         //应付金额
        			fccs.setFccsPaymentMoney(gtVo.getPreclrRealAmount());                                                //付款金额
        			fccs.setPaymentId(gtVo.getGatheringWise());                                                          //付款方式
        			if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() == 0.0d){                                //找零为0.00时
        			    fccs.setFccsArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());                          //欠款= 应付金额 - 付款金额
        			    fccs.setFcsPayment(gtVo.getOtherBalance());                                                      //找零
        			}else if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() > 0.0d){                                                                                               //找零不为0.00时
        			    fccs.setFccsArrears(gtVo.getPreclrRealAmount() - gtVo.getPreclrAmount()-gtVo.getOtherBalance()); //欠款= 付款金额 -应付金额 - 找零
        			    fccs.setFcsPayment(gtVo.getOtherBalance());
        			}else{
                        msg.setMsg("数据不全没有找到找零金额");
                        msg.setSuccess(false);
                        return msg;
                    }
        			fccs.setStfId(gtVo.getStfId());
        			if(fccs.getFccsArrears()==0.00){
        				fccs.setFccsUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
        			}
        			fccs.setFccsRemark(gtVo.getGatheringRemark());
        			fccs.setCrId(fcr.getCrId());
        			finCounterclaimScheduleDao.save(fccs);
        			fcr.setCrArrears(fccs.getFccsArrears());
        			finClaimsReceivablesDao.merge(fcr);
        			
        			claimantGatheringChangeState(gtVo.getPreclrId());
        			msg.setMsg("增加索赔收款信息成功！");
        			msg.setSuccess(true);
    			}
			}else{
                msg.setMsg("索赔应收款账单不存在");
                msg.setSuccess(false);
            }
		} catch (Exception e) {
			msg.setMsg("增加索赔收款信息失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**判断有无代付，更改付款状态（是否付清）
	 * @throws Exception */
	private void claimantGatheringChangeState(String preclrId) throws Exception{
		FinClaimsReceivables fcr=finClaimsReceivablesDao.get("from FinClaimsReceivables fcr where fcr.claimantmId='"+preclrId+"'");
		if(fcr.getCrSubstitute()==Contstants.OPINIONFINISHED_TAG.UNFINISHED){
			if(fcr.getCrArrears()==0.00){
				fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finClaimsReceivablesDao.merge(fcr);
			}
		}else
			claimantSubstituteGatheringChangeState(preclrId);
	}
	/**
	 * 增加维修收款信息
	 * */
	@Log(moduleName="前台管理",content="新增维修收款",opertype="新增")
	
	public Msg savePreclearingGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		try {
			if(gtVo.getUnAchieve()==null)
				gtVo.setUnAchieve(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			if(gtVo.getSubstitute()==null)
				gtVo.setSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get("from FinMaintenanceReceivables fmr where fmr.preclrId='"+gtVo.getPreclrId()+"'");
			if(fmr!=null){
				if(fmr.getMrCumulative()==null)
					fmr.setMrCumulative(0.00d);
				if(fmr.getMrCumulative()==null)
					fmr.setMrCumulative(gtVo.getPreclrRealAmount());
				else
				    fmr.setMrCumulative(fmr.getMrCumulative()+gtVo.getPreclrRealAmount());
				if(fmr.getMrSubstitute()==null)
					fmr.setMrSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
				//代付
				if(gtVo.getSubstituteCustomId()!=null&&gtVo.getSubstituteCustomId().toString().length()>0
				        &&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){
				    ssp.setSspId(CreateID.createId("SubstituteSuitPayment_preClearing", Contstants.SERVICESUITSUBTITUTEGATHERING));
				    ssp.setCustomId(gtVo.getSubstituteCustomId());
				    ssp.setSspReceivables(gtVo.getSubstituteMoney());
				    ssp.setSspCumulative(0.00d);
				    ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				    ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
				    ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				    ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				    ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				    ssp.setSspArrears(gtVo.getSubstituteMoney());
				    ssp.setSspDate(new Date());
				    ssp.setBalanceId(gtVo.getGatheringId());
				    if(ssp.getSspArrears()==0.00){
				        ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
				    }
				    ssp.setEnterpriseId(gtVo.getEnterpriseId());
				    substituteSuitPaymentDao.save(ssp);
				    SubstituteBalance sb=new SubstituteBalance();
				    sb.setSspId(ssp.getSspId());
				    sb.setStrikeId(gtVo.getPreclrId());
				    sb.setEnterpriseId(gtVo.getEnterpriseId());
				    substituteBalanceDao.save(sb);
				    fmr.setMrSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
				}else{
    				FinCollectionSchedule fcs=new FinCollectionSchedule();
    				fcs.setFcsId(gtVo.getGatheringId());
    				fcs.setFcsDate(gtVo.getGatheringTime());
    				fcs.setFcsPayable(gtVo.getPreclrAmount());
    				fcs.setFcsPaymentMoney(gtVo.getPreclrRealAmount());
    				fcs.setPaymentId(gtVo.getGatheringWise());
    				if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() == 0.0d){
                        fcs.setFcsArrears(gtVo.getPreclrAmount() - gtVo.getPreclrRealAmount());
                        fcs.setFcsPayment(gtVo.getOtherBalance());
                    }else if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() > 0.0d){
                        fcs.setFcsArrears(gtVo.getPreclrRealAmount() - gtVo.getPreclrAmount() - gtVo.getOtherBalance());
                        fcs.setFcsPayment(gtVo.getOtherBalance());
                    }else{
                        msg.setMsg("数据不全没有找到找零金额");
                        msg.setSuccess(false);
                        return msg;
                    }
    				fcs.setStfId(gtVo.getStfId());
    				fcs.setFcsUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				if(fcs.getFcsArrears()==0.00){
    				    fcs.setFcsUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
    				}
    				fcs.setFcsRemark(gtVo.getGatheringRemark());
    				fcs.setMrId(fmr.getMrId());
    				//fcs.setFcsReserve(gtVo.getHiveUse());//储备金金额
    				fcs.setEnterpriseId(gtVo.getEnterpriseId());
    				finCollectionScheduleDao.save(fcs);
    				
    				fmr.setMrArrears(fcs.getFcsArrears());
    				finMaintenanceReceivablesDao.merge(fmr);
    				if(gtVo.getBeforeMoney()==null||gtVo.getBeforeMoney().toString().length()==0)
    				    gtVo.setBeforeMoney(0.00d);
    				Double tempAmount=gtVo.getPreclrRealAmount();
    				/****************/
    				if(gtVo.getGatheringWise().equals(Contstants.GATHERINGWISE_FLG.GATHERINGWISEASRECEPTION)){	
    				    if(gtVo.getBeforeId()!=null&&gtVo.getBeforeId().length()>0){
    				        StRecharge sr=stRechargeDao.get(StRecharge.class, Integer.parseInt(gtVo.getBeforeId()));
    				        StUsePercharge sup=new StUsePercharge();
    				        sup.setStRecharge(sr);
    				        BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());     
    				        sup.setBasStuff(bs);
    				        sup.setUseAmount(gtVo.getPreclrRealAmount());
    				        sup.setBackAmount(fmr.getMrReceivables());
    				        sup.setRepcollId(fcs.getFcsId());
    				        sup.setEnterpriseId(gtVo.getEnterpriseId());
    				        stUsePerchargeDao.save(sup);
    				        if(tempAmount>0)
    				            sr.setSurplusMoney(0.00d);	
    				        else
    				            sr.setSurplusMoney(Math.abs(tempAmount));
    				        stRechargeDao.merge(sr);					
    				    }
    				}
    				/**************/
    				if(gtVo.getHiveId()!=null&&gtVo.getHiveId().toString().length()>0&&tempAmount>0){
    				    StRecharge sr=stRechargeDao.get(StRecharge.class, gtVo.getHiveId());
    				    Double hiveMoney=gtVo.getHiveUse();
    				    if(tempAmount<hiveMoney){
    				        hiveMoney=tempAmount-Math.abs(tempAmount-gtVo.getHiveUse());
    				    }
    				    sr.setSurplusMoney(sr.getSurplusMoney()-hiveMoney);
    				    stRechargeDao.merge(sr);
    				    StUsePercharge sup=new StUsePercharge();
    				    sup.setStRecharge(sr);
    				    BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());     
    				    sup.setBasStuff(bs);
    				    sup.setUseAmount(hiveMoney);
    				    sup.setBackAmount(fmr.getMrReceivables());
    				    sup.setRepcollId(fcs.getFcsId());
    				    sup.setEnterpriseId(gtVo.getEnterpriseId());
    				    stUsePerchargeDao.save(sup);						
    				}
    				preclearingGatheringChangeState(gtVo.getPreclrId());
    				msg.setMsg("增加维修收款信息成功！");
    				msg.setSuccess(true);
				}
			}else{
                msg.setMsg("维修应收款账单不存在");
                msg.setSuccess(false);
            }
		} catch (Exception e) {
			msg.setMsg("增加维修收款信息失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**判断有无代付，更改付款状态（是否付清）
	 * @throws Exception */
	private void preclearingGatheringChangeState(String preclrId) throws Exception{
		FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get("from FinMaintenanceReceivables fmr where fmr.preclrId='"+preclrId+"'");
		if(fmr.getMrSubstitute()==Contstants.OPINIONFINISHED_TAG.UNFINISHED){
			if(fmr.getMrArrears()==0.00){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}else{
			preclearingSubstituteGatheringChangeState(preclrId);
		}
	}
	/**
	 * 增加索赔批量收款
	 * */
	@Log(moduleName="前台管理",content="新增索赔批量收款",opertype="新增")
	
	public Msg saveClaimantBatchGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		BatchBalanceDetail bbd=null;
		try {
			BatchBalanceGather bbg=null;
			if(gtVo.getBbgId()==null||gtVo.getBbgId().length()==0){
				bbg=new BatchBalanceGather();
				bbg.setBbgId(CreateID.createId("BatchBalanceGather_batch_claimant", Contstants.CLAIMSBATCHGATHERINGCOLLECT));
				
				gtVo.setTempPreclrIds(gtVo.getTempPreclrIds().replaceAll("\r\n", ","));
				String [] tempStr=gtVo.getTempPreclrIds().split(",");
				String dataStr=gtVo.getTempPreclrIds();
				dataStr=dataStr.replaceAll(",", "','");
				dataStr="'"+dataStr+"'";
				for (String string : tempStr) {
					bbd=new BatchBalanceDetail();
					bbd.setStrikeId(string);
					bbd.setBbgId(bbg.getBbgId());
					batchBalanceDetailDao.save(bbd);
				}
				frtPreClearingDao.updateBatch("update fin_claims_receivables fcr set fcr.CR_BATCH_TAG="+
						Contstants.OPINIONFINISHED_TAG.FINISHED+" where fcr.CLAIMANTM_ID in("+dataStr+")");
				bbg.setBbgReceivables(gtVo.getSumAmount());
				bbg.setBbgCumulative(gtVo.getPreclrRealAmount());
				bbg.setBbgArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
				bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setCustomId(gtVo.getCustomId());
				bbg.setBbgDate(new Date());
				batchBalanceGatherDao.save(bbg);
				gtVo.setBbgId(bbg.getBbgId());
			}else{
				bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
				if(bbg.getBbgCumulative()==null)
					bbg.setBbgCumulative(0.00d);
				bbg.setBbgCumulative(bbg.getBbgCumulative()+gtVo.getPreclrRealAmount());
				if(bbg.getBbgSubstitute()==null)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			}
			if(gtVo.getSubstituteClaimantId()!=null&&gtVo.getSubstituteClaimantId().toString().length()>0
					&&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){
				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
				ssp.setSspId(CreateID.createId("SubstituteSuitPayment_batch_claimant", Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING));
				ssp.setCustomId(gtVo.getSubstituteClaimantId());
				ssp.setSspReceivables(gtVo.getSubstituteMoney());
				ssp.setSspCumulative(0.00d);
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspArrears(gtVo.getSubstituteMoney());
				if(ssp.getSspArrears()==0.00){
					ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
				}
				ssp.setSspDate(new Date());
				ssp.setBalanceId(gtVo.getGatheringId());
				substituteSuitPaymentDao.save(ssp);
				SubstituteBalance sb=new SubstituteBalance();
				sb.setSspId(ssp.getSspId());
				sb.setStrikeId(gtVo.getPreclrId());
				substituteBalanceDao.save(sb);
				if(bbg.getBbgSubstitute()!=Contstants.OPINIONFINISHED_TAG.FINISHED)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
			}
			
			BatchPaymentDetail bpd=new BatchPaymentDetail(); 
			bpd.setBpdId(gtVo.getGatheringId());
			bpd.setBbgId(gtVo.getBbgId());
			bpd.setStfId(gtVo.getStfId());
			bpd.setPaymentId(gtVo.getGatheringWise());
			bpd.setBpdPayable(gtVo.getPreclrAmount());
			bpd.setBpdPaymentMoney(gtVo.getPreclrRealAmount());
			if(gtVo.getPreclrRealAmount()==null)
				gtVo.setPreclrRealAmount(0.00d);
			if(gtVo.getOtherBalance() == 0.0d){
			    bpd.setBpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
			}else{
			    bpd.setBpdArrears(gtVo.getPreclrRealAmount() - gtVo.getPreclrAmount()-gtVo.getOtherBalance());
			}
			bpd.setBpdDate(gtVo.getGatheringTime());
			if(bpd.getBpdArrears()==0.00){
				bpd.setBpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			bpd.setBpdRemark(gtVo.getGatheringRemark());
			batchPaymentDetailDao.save(bpd);
			bbg.setBbgArrears(bpd.getBpdArrears());
			if(bbg.getBbgDifferenceBalance()==Contstants.OPINIONFINISHED_TAG.FINISHED){
				changeDifferenceBalance(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
				changeBatchGathering(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);				
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			batchBalanceGatherDao.merge(bbg);
			msg.setMsg("增加索赔批量收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加索赔批量收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * 增加维修批量收款
	 * */
	@Log(moduleName="前台管理",content="新增维修批量收款",opertype="新增")
	
	public Msg savePreclearingBatchGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		try {
			BatchBalanceGather bbg=null;
			if(gtVo.getBbgId()==null||gtVo.getBbgId().length()==0){
				BatchBalanceDetail bbd=null;
				bbg=new BatchBalanceGather();
				bbg.setBbgId(CreateID.createId("BatchBalanceGather_batch_preClearing", Contstants.SERVICEBATCHGATHERINGCOLLECT));
				
				gtVo.setTempPreclrIds(gtVo.getTempPreclrIds().replaceAll("\r\n", ","));
				String [] tempStr=gtVo.getTempPreclrIds().split(",");
				String dataStr=gtVo.getTempPreclrIds();
				dataStr=dataStr.replaceAll(",", "','");
				dataStr="'"+dataStr+"'";
				for (String string : tempStr) {
					bbd=new BatchBalanceDetail();
					bbd.setStrikeId(string);
					bbd.setBbgId(bbg.getBbgId());
					batchBalanceDetailDao.save(bbd);
				}
				frtPreClearingDao.updateBatch("update fin_maintenance_receivables fmr set fmr.MR_BATCH_TAG="+
									Contstants.OPINIONFINISHED_TAG.FINISHED+" where fmr.PRECLR_ID in("+dataStr+")");
				bbg.setBbgReceivables(gtVo.getSumAmount());
				bbg.setBbgCumulative(gtVo.getPreclrRealAmount());
				if(gtVo.getOtherBalance() == 0.0d){
				     bbg.setBbgArrears(gtVo.getPreclrAmount() - gtVo.getPreclrRealAmount());
				}else{
				     bbg.setBbgArrears(gtVo.getPreclrRealAmount() - gtVo.getPreclrAmount() - gtVo.getOtherBalance());
				}
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
				bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setCustomId(gtVo.getCustomId());
				bbg.setBbgDate(new Date());
				batchBalanceGatherDao.save(bbg);
				gtVo.setBbgId(bbg.getBbgId());
			}else{
				bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
				if(bbg.getBbgCumulative()==null)
					bbg.setBbgCumulative(0.00d);
				bbg.setBbgCumulative(bbg.getBbgCumulative()+gtVo.getPreclrRealAmount());
				if(bbg.getBbgSubstitute()==null)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			if(gtVo.getSubstituteCustomId()!=null&&gtVo.getSubstituteCustomId().toString().length()>0
					&&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){
				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
				ssp.setSspId(CreateID.createId("SubstituteSuitPayment_batch_preClearing", Contstants.SERVICESUITSUBTITUTEBATCHGATHERING));
				ssp.setCustomId(gtVo.getSubstituteCustomId());
				ssp.setSspReceivables(gtVo.getSubstituteMoney());
				ssp.setSspCumulative(0.00d);
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspArrears(gtVo.getSubstituteMoney());
				ssp.setSspDate(new Date());
				ssp.setBalanceId(gtVo.getGatheringId());
				if(ssp.getSspArrears()==0.00){
					ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
				}
				substituteSuitPaymentDao.save(ssp);
				SubstituteBalance sb=null;
				List<BatchBalanceDetail> list=batchBalanceDetailDao.find("select bbd from BatchBalanceDetail bbd where bbd.bbgId='"+bbg.getBbgId()+"'");
				for (BatchBalanceDetail batchBalanceDetail : list) {
					sb=new SubstituteBalance();
					sb.setSspId(ssp.getSspId());
					sb.setStrikeId(batchBalanceDetail.getStrikeId());
					substituteBalanceDao.save(sb);
				}
				if(bbg.getBbgSubstitute()!=Contstants.OPINIONFINISHED_TAG.FINISHED)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
			}
			
			BatchPaymentDetail bpd=new BatchPaymentDetail(); 
			bpd.setBpdId(gtVo.getGatheringId());
			bpd.setBbgId(gtVo.getBbgId());
			bpd.setStfId(gtVo.getStfId());
			bpd.setPaymentId(gtVo.getGatheringWise());
			bpd.setBpdPayable(gtVo.getPreclrAmount());
			bpd.setBpdPaymentMoney(gtVo.getPreclrRealAmount());
			if(gtVo.getPreclrRealAmount()==null)
				gtVo.setPreclrRealAmount(0.00d);
			bpd.setBpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
			bpd.setBpdDate(gtVo.getGatheringTime());
			if(bpd.getBpdArrears()==0.00){
				bpd.setBpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			bpd.setBpdRemark(gtVo.getGatheringRemark());
			batchPaymentDetailDao.save(bpd);
			bbg.setBbgArrears(bpd.getBpdArrears());
			if(bbg.getBbgDifferenceBalance()==Contstants.OPINIONFINISHED_TAG.FINISHED){
				changeDifferenceBalance(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
				changeBatchGathering(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);				
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			batchBalanceGatherDao.merge(bbg);
			msg.setMsg("增加维修批量收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加维修批量收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**判断是否差额结算，更改付清状态
	 * @throws Exception */
	private void changeDifferenceBalance(String bbgId,Short type) throws Exception{
		changeBatchGathering(bbgId,type);
	}
	/**判断是否付清，更改付清状态*/
	private void changeBatchGathering(String bbgId,Short type) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT DISTINCT ssp.SSP_ID,ssp.SSP_UNFINISHED  ");
		sb.append(" FROM substitute_suit_payment ssp");
		sb.append(" WHERE ssp.BALANCE_ID IN(");
		sb.append(" SELECT bpd.BPD_ID FROM batch_balance_gather bbg,batch_payment_detail bpd");
		sb.append(" WHERE bbg.BBG_ID=bpd.BBG_ID AND bbg.BBG_ID='"+bbgId+"')");
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString());
		Boolean flag=true;
		BatchBalanceGather bbg=batchBalanceGatherDao.get(BatchBalanceGather.class, bbgId);
		if(rowsList!=null&&rowsList.size()>0){
			for (Object[] obj : rowsList) {
				if(Contstants.OPINIONFINISHED_TAG.UNFINISHED.equals(new Short(obj[1].toString()))){
					flag=false;
					break;
				}
			}
		}
		if(flag==true){
			bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			changeFmrUnFinished(bbg.getBbgId(),type);
		}
		batchBalanceGatherDao.merge(bbg);
	}
	/**更改应收款付清状态*/
	private Boolean changeFmrUnFinished(String bbgId,Short type) throws Exception{
		StringBuffer sb=new StringBuffer();
		if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE)){
			sb.append("select distinct fmr from BatchBalanceDetail bbd,BatchBalanceGather bbg,");
			sb.append(" FinMaintenanceReceivables fmr where bbg.bbgId=bbd.bbgId and fmr.mrBatchTag="+Contstants.OPINIONFINISHED_TAG.FINISHED);
			sb.append("  and fmr.preclrId=bbd.strikeId and bbg.bbgId='"+bbgId+"'");
			List<FinMaintenanceReceivables> fmrList=finMaintenanceReceivablesDao.find(sb.toString());
			if(fmrList==null||fmrList.size()==0){
				return true;
			}
			for (FinMaintenanceReceivables fmr : fmrList) {
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE)){
			sb.append("select distinct fcr from BatchBalanceDetail bbd,BatchBalanceGather bbg,");
			sb.append(" FinClaimsReceivables fcr where bbg.bbgId=bbd.bbgId and fcr.crBatchTag="+Contstants.OPINIONFINISHED_TAG.FINISHED);
			sb.append("  and fcr.claimantmId=bbd.strikeId and bbg.bbgId='"+bbgId+"'");
			List<FinClaimsReceivables> fmrList=finClaimsReceivablesDao.find(sb.toString());
			if(fmrList==null||fmrList.size()==0){
				return true;
			}
			for (FinClaimsReceivables fcr : fmrList) {
				fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finClaimsReceivablesDao.merge(fcr);
			}
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE)){
			sb.append("select distinct charge from BatchBalanceDetail bbd,BatchBalanceGather bbg,");
			sb.append(" StSellCharge charge where bbg.bbgId=bbd.bbgId and charge.chargeBatchTag="+Contstants.OPINIONFINISHED_TAG.FINISHED);
			sb.append("  and charge.preclrId=bbd.strikeId and bbg.bbgId='"+bbgId+"'");
			List<StSellCharge> chargeList=stSellChargeDao.find(sb.toString());
			if(chargeList==null||chargeList.size()==0){
				return true;
			}
			for (StSellCharge charge : chargeList) {
				charge.setChargeUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				stSellChargeDao.merge(charge);
			}
		}else{
			return true;
		}
		return false;
	}
	
	/**
	 *增加销售批量收款 
	 * */
	@Log(moduleName="前台管理",content="新增销售批量收款",opertype="新增")
	
	public Msg saveSellBatchGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		try {
			BatchBalanceGather bbg=null;
			if(gtVo.getBbgId()==null||gtVo.getBbgId().length()==0){
				BatchBalanceDetail bbd=null;
				bbg=new BatchBalanceGather();
				bbg.setBbgId(CreateID.createId("BatchBalanceGather_batch_sell", Contstants.SELLBATCHGATHERINGCOLLECT));
				gtVo.setTempPreclrIds(gtVo.getTempPreclrIds().replaceAll("\r\n", ","));
				String [] tempStr=gtVo.getTempPreclrIds().split(",");
				String dataStr=gtVo.getTempPreclrIds();
				dataStr=dataStr.replaceAll(",", "','");
				dataStr="'"+dataStr+"'";
				for (String string : tempStr) {
					bbd=new BatchBalanceDetail();
					bbd.setStrikeId(string);
					bbd.setBbgId(bbg.getBbgId());
					batchBalanceDetailDao.save(bbd);
				}
				frtPreClearingDao.updateBatch("update st_sell_charge charge set charge.CHARGE_BATCH_TAG="+
									Contstants.OPINIONFINISHED_TAG.FINISHED+" where charge.PRECLR_ID in("+dataStr+")");
				bbg.setBbgReceivables(gtVo.getSumAmount());
				bbg.setBbgCumulative(gtVo.getPreclrRealAmount());
				bbg.setBbgArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
				bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setCustomId(gtVo.getCustomId());
				bbg.setBbgDate(new Date());
				batchBalanceGatherDao.save(bbg);
				gtVo.setBbgId(bbg.getBbgId());
			}else{
				bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
				if(bbg.getBbgCumulative()==null)
					bbg.setBbgCumulative(0.00d);
				bbg.setBbgCumulative(bbg.getBbgCumulative()+gtVo.getPreclrRealAmount());
				if(bbg.getBbgSubstitute()==null)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					bbg.setBbgDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			if(gtVo.getSubstituteCustomId()!=null&&gtVo.getSubstituteCustomId().toString().length()>0
					&&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){
				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
				ssp.setSspId(CreateID.createId("SubstituteSuitPayment_batch_sell", Contstants.SELLSUITSUBTITUTEBATCHGATHERING));
				ssp.setCustomId(gtVo.getSubstituteCustomId());
				ssp.setSspReceivables(gtVo.getSubstituteMoney());
				ssp.setSspCumulative(0.00d);
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspArrears(gtVo.getSubstituteMoney());
				ssp.setSspDate(new Date());
				ssp.setBalanceId(gtVo.getGatheringId());
				if(ssp.getSspArrears()==0.00){
					ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
				}
				substituteSuitPaymentDao.save(ssp);
				SubstituteBalance sb=null;
				List<BatchBalanceDetail> list=batchBalanceDetailDao.find("select bbd from BatchBalanceDetail bbd where bbd.bbgId='"+bbg.getBbgId()+"'");
				for (BatchBalanceDetail batchBalanceDetail : list) {
					sb=new SubstituteBalance();
					sb.setSspId(ssp.getSspId());
					sb.setStrikeId(batchBalanceDetail.getStrikeId());
					substituteBalanceDao.save(sb);
				}
				if(bbg.getBbgSubstitute()!=Contstants.OPINIONFINISHED_TAG.FINISHED)
					bbg.setBbgSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
			}
			
			BatchPaymentDetail bpd=new BatchPaymentDetail(); 
			bpd.setBpdId(gtVo.getGatheringId());
			bpd.setBbgId(gtVo.getBbgId());
			bpd.setStfId(gtVo.getStfId());
			bpd.setPaymentId(gtVo.getGatheringWise());
			bpd.setBpdPayable(gtVo.getPreclrAmount());
			bpd.setBpdPaymentMoney(gtVo.getPreclrRealAmount());
			if(gtVo.getPreclrRealAmount()==null)
				gtVo.setPreclrRealAmount(0.00d);
			bpd.setBpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
			bpd.setBpdDate(gtVo.getGatheringTime());
			if(bpd.getBpdArrears()==0.00){
				bpd.setBpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			bpd.setBpdRemark(gtVo.getGatheringRemark());
			batchPaymentDetailDao.save(bpd);
			bbg.setBbgArrears(bpd.getBpdArrears());
			if(bbg.getBbgDifferenceBalance()==Contstants.OPINIONFINISHED_TAG.FINISHED){
				changeDifferenceBalance(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
				changeBatchGathering(bbg.getBbgId(),Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);				
			}else if(bbg.getBbgArrears().equals(0.00d)&&bbg.getBbgSubstitute().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);				
			}
			batchBalanceGatherDao.merge(bbg);
			msg.setMsg("增加销售批量收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加销售批量收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * 增加销售收款
	 * */
	@Log(moduleName="前台管理",content="新增销售收款",opertype="新增")
	
	public Msg saveSellGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		try {
			if(gtVo.getUnAchieve()==null)//获取付清状态
				gtVo.setUnAchieve(Contstants.OPINIONFINISHED_TAG.UNFINISHED);//未收清
			if(gtVo.getSubstitute()==null)
				gtVo.setSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get(" from FinMaintenanceReceivables fmr where fmr.preclrId='"+gtVo.getPreclrId()+"'");
			if(fmr != null){
    			if(fmr.getMrCumulative()==null)
    				fmr.setMrCumulative(0.00d);
    			fmr.setMrCumulative(fmr.getMrCumulative()+gtVo.getPreclrRealAmount());//累计收款
    			if(fmr.getMrSubstitute()==null)
    				fmr.setMrSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);//是否代付
    			//代付
    			if(gtVo.getSubstituteCustomId()!=null&&gtVo.getSubstituteCustomId().toString().length()>0&&gtVo.getSubstituteMoney()!=null&&gtVo.getSubstituteMoney().toString().length()>0){//代付信息操作
    				SubstituteSuitPayment ssp=new SubstituteSuitPayment();
    				ssp.setSspId(CreateID.createId("SubstituteSuitPayment_sell", Contstants.SELLSUITSUBTITUTEGATHERING));
    				ssp.setCustomId(gtVo.getSubstituteCustomId());
    				ssp.setSspReceivables(gtVo.getSubstituteMoney());
    				ssp.setSspCumulative(0.00d);
    				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspType(Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
    				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
    				ssp.setSspArrears(gtVo.getSubstituteMoney());
    				ssp.setSspDate(new Date());
    				ssp.setBalanceId(gtVo.getGatheringId());
    				if(ssp.getSspArrears()==0.00){
    					ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
    				}
    				substituteSuitPaymentDao.save(ssp);
    				SubstituteBalance sb=new SubstituteBalance();
    				sb.setSspId(ssp.getSspId());
    				sb.setStrikeId(gtVo.getPreclrId());
    				substituteBalanceDao.save(sb);
    				fmr.setMrSubstitute(Contstants.OPINIONFINISHED_TAG.FINISHED);	//代付人
    			}else{
        			FinCollectionSchedule fcs=new FinCollectionSchedule();//维修销售收款明细记录表
        			if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() == 0.0d){
        			    fcs.setFcsArrears(gtVo.getPreclrAmount() - gtVo.getPreclrRealAmount());
        			    fcs.setFcsPayment(gtVo.getOtherBalance());
        			}else if(gtVo.getOtherBalance() != null && gtVo.getOtherBalance() > 0.0d){
        			    fcs.setFcsArrears(gtVo.getPreclrRealAmount() - gtVo.getPreclrAmount() - gtVo.getOtherBalance());
        			    fcs.setFcsPayment(gtVo.getOtherBalance());
        			}else{
        			    msg.setMsg("数据不全没有找到找零金额");
        			    msg.setSuccess(false);
        			    return msg;
        			}
        			if(fcs.getFcsArrears()==0.00){
            			fcs.setFcsId(gtVo.getGatheringId());
            			fcs.setFcsDate(gtVo.getGatheringTime());
            			fcs.setFcsPayable(gtVo.getPreclrAmount());
            			fcs.setFcsPaymentMoney(gtVo.getPreclrRealAmount());
            			fcs.setPaymentId(gtVo.getGatheringWise());
            			fcs.setStfId(gtVo.getStfId());
            			fcs.setFcsUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
        				fcs.setFcsUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
            			fcs.setFcsRemark(gtVo.getGatheringRemark());
            			fcs.setMrId(fmr.getMrId());
            			fcs.setFcsPayment(gtVo.getOtherBalance());
            			//fcs.setFcsReserve(gtVo.getHiveUse());//储备金金额
            			finCollectionScheduleDao.save(fcs);
            			fmr.setMrArrears(fcs.getFcsArrears());//欠款
            			finMaintenanceReceivablesDao.merge(fmr);
            			if(gtVo.getHiveId()!=null&&gtVo.getHiveId().toString().length()>0){//储备使用记录
            				StRecharge sr=stRechargeDao.get(StRecharge.class, gtVo.getHiveId());
            				sr.setSurplusMoney(sr.getSurplusMoney()-gtVo.getHiveUse());
            				stRechargeDao.merge(sr);
            				BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());                                                          
            				StSellPercharge sspe=new StSellPercharge();
            				sspe.setStRecharge(sr);                                                                                                                    
            				sspe.setBasStuff(bs);   
            				sspe.setBasChilddictionary(basChilddictionaryDao.get("from BasChilddictionary bc where bc.dataKey='"+gtVo.getGatheringWise()+"'"));
            				sspe.setPerchargeDate(new Date());                                                                                                      
            				sspe.setCurPercharge(gtVo.getHiveUse());                                                                                                                 
            				sspe.setPreclrInoiceType(gtVo.getPreclrInoiceType());
            				sspe.setPreclrNo(gtVo.getPreclrNo());
            				if(!Contstants.RECEIPT_TAG.OTHERTAX.equals(gtVo.getPreclrInoiceType())){					
            					sspe.setPreclrInvoiceTime(new Date());
            				}
            				sspe.setChargeRemark(gtVo.getGatheringRemark());
            				sspe.setPerchargeId(CreateID.createId("StSellPercharge", Contstants.SERVICEBEFOREGATHERING));
            				sspe.setFlag(0);
            				stSellPerchargeDao.save(sspe);							
            			}
            			sellGatheringChangeState(gtVo.getPreclrId());
            			msg.setMsg("增加销售收款信息成功！");
            			msg.setSuccess(true);
    			    }
			    }
			}else{
                msg.setMsg("配件销售应收款账单不存在");
                msg.setSuccess(false);
            }
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("增加销售收款信息失败！");
			msg.setSuccess(false);
		}
		return msg;
	}

	/**判断有无代付，更改付款状态（是否付清）
	 * @throws Exception */
	private void sellGatheringChangeState(String preclrId) throws Exception{
		FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get(" from FinMaintenanceReceivables fmr where fmr.preclrId='"+preclrId+"'");
		if(fmr.getMrSubstitute()==Contstants.OPINIONFINISHED_TAG.UNFINISHED){//是否代付
			if(fmr.getMrArrears()==0.00){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}else{
			sellSubstituteGatheringChangeState(preclrId);
		}
	}
	/**判断代付销售收款是否付清，更改收款状态（是否收清）*/
	private void sellSubstituteGatheringChangeState(String preclrId) throws Exception{
		FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get(" from FinMaintenanceReceivables fmr where fmr.preclrId='"+preclrId+"'");
		if(fmr.getMrArrears()==0.00){
			StringBuffer sb=new StringBuffer("SELECT ssp FROM SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" WHERE sb.sspId=ssp.sspId AND sb.strikeId='"+preclrId+"'");
			Boolean flag=true;
			List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb.toString());
			if(list!=null&&list.size()>0)
				for (SubstituteSuitPayment ssp : list) {
					if(!(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.FINISHED))){
						flag=false;
						break;
					}
				}
			if(flag==true){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}else{
			if(fmr.getMrUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNPAYMENT)){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}
	}	
	/**
	 * 查找批量收款相关数据
	 * */
	
	public GatheringVo datagridBatchGatheringByPreclrId(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getTag()!=null)
		if(gtVo.getTag()==true){
			if(gtVo.getPreclrIds()!=null){
				String [] tempArray=gtVo.getPreclrIds().split(",");
				gtVo.setPreclrId(tempArray[0]);
				String temp=gtVo.getPreclrIds().replaceAll(",", "','");
				temp="'"+temp+"'";
				gtVo.setPreclrIds(temp);
				if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null){
					return datagridClaimantBatchGatheringByPreclrId(gtVo,gtVo.getTag());
				}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null){
					return datagridPreclearingBatchGatheringByPreclrId(gtVo,gtVo.getTag());
				}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null){
					return datagridSellBatchGatheringByPreclrId(gtVo,gtVo.getTag());
				}
			}			
		}else{
			if(opinionId(gtVo.getBbgId(),Contstants.CLAIMSBATCHGATHERINGCOLLECT)!=null){
				return datagridClaimantBatchGatheringByPreclrId(gtVo,gtVo.getTag());
			}else if(opinionId(gtVo.getBbgId(),Contstants.SERVICEBATCHGATHERINGCOLLECT)!=null){
				return datagridPreclearingBatchGatheringByPreclrId(gtVo,gtVo.getTag());
			}else if(opinionId(gtVo.getBbgId(),Contstants.SELLBATCHGATHERINGCOLLECT)!=null){
				return datagridSellBatchGatheringByPreclrId(gtVo,gtVo.getTag());
			}
		}
		return null;
	}
	/**查找维修批量收款相关数据*/
	private GatheringVo datagridPreclearingBatchGatheringByPreclrId(GatheringVo gtVo,Boolean tag) throws Exception{
		GatheringVo gathering=null;
		if(tag==false){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_preClearing", Contstants.SERVICEBATCHGATHERING));
				return gathering;
			}
			BatchBalanceGather bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
			if(bbg!=null){
				gathering=new GatheringVo();
				gathering.setBbgId(gtVo.getBbgId());
				gathering.setPreclrAmount(bbg.getBbgArrears());
			}
			StringBuffer sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc WHERE fc.custom_id='"+bbg.getCustomId()+"'");
			List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
			if(lt!=null&&lt.size()>0){
				Object[] obj=lt.get(0);
				gathering.setCustomId(obj[0].toString());
				gathering.setCustomName(obj[1].toString());
				gathering.setCustomTel(obj[2].toString());
				gathering.setCustomAddr(obj[3].toString());
			}
			StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
			sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
			sb1.append(" WHERE bvi.CAR_VIN=fcar.CAR_VIN AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
			sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gathering.getCustomId()+"'");
			List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
			if(list1!=null&&list1.size()>0){
				Object[] obj=list1.get(0);
				if(obj[0]!=null&&obj[0].toString().length()>0)
					gathering.setMemberId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
				else
					gathering.setSumIntegral(0);
				if(obj[2]!=null&&obj[2].toString().length()>0)
					gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
				else
					gathering.setUseIntegral(0);
				if(obj[3]!=null&&obj[3].toString().length()>0)
					gathering.setMemberClass(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					gathering.setMemberGrade(obj[4].toString());
			}
			gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
			gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_preClearing", Contstants.SERVICEBATCHGATHERING));
			return gathering;
		}else{
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_preClearing", Contstants.SERVICEBATCHGATHERING));
				return gathering;
			}
		}
		String sql="SELECT SUM(fpc.preclr_real_amount) FROM frt_pre_clearing fpc WHERE fpc.preclr_id in("+gtVo.getPreclrIds()+")";
		List list=frtPreClearingDao.createSQLQuery(sql);
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			gathering.setPreclrAmount(Double.parseDouble(list.get(0).toString()));
			gathering.setSumAmount(Double.parseDouble(list.get(0).toString()));
		}else{
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
		sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc");
		sb.append(" WHERE fpc.reception_id=fr.reception_id AND fc.custom_id=fr.custom_id AND fpc.preclr_id='"+gtVo.getPreclrId()+"'");
		List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
		if(lt!=null&&lt.size()>0){
			Object[] obj=lt.get(0);
			gathering.setCustomId(obj[0].toString());
			gathering.setCustomName(obj[1].toString());
			gathering.setCustomTel(obj[2].toString());
			gathering.setCustomAddr(obj[3].toString());
		}
//		StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
//		sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
//		sb1.append(" WHERE bvi.car_id=fcar.car_id AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
//		sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gathering.getCustomId()+"'");
//		List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
//		if(list1!=null&&list1.size()>0){
//			Object[] obj=list1.get(0);
//			if(obj[0]!=null&&obj[0].toString().length()>0)
//				gathering.setMemberId(obj[0].toString());
//			if(obj[1]!=null&&obj[1].toString().length()>0)
//				gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
//			else
//				gathering.setSumIntegral(0);
//			if(obj[2]!=null&&obj[2].toString().length()>0)
//				gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
//			else
//				gathering.setUseIntegral(0);
//			if(obj[3]!=null&&obj[3].toString().length()>0)
//				gathering.setMemberClass(obj[3].toString());
//			if(obj[4]!=null&&obj[4].toString().length()>0)
//				gathering.setMemberGrade(obj[4].toString());
//		}
		//gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
		//gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_preClearing", Contstants.SERVICEBATCHGATHERING));
		return gathering;
	}
	/**查找索赔批量收款相关数据*/
	private GatheringVo datagridClaimantBatchGatheringByPreclrId(GatheringVo gtVo,Boolean tag) throws Exception{
		GatheringVo gathering=null;
		if(tag==false){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_claimant", Contstants.CLAIMSBATCHGATHERING));
				return gathering;
			}
			BatchBalanceGather bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
			if(bbg!=null){
				gathering=new GatheringVo();
				gathering.setBbgId(gtVo.getBbgId());
				gathering.setPreclrAmount(bbg.getBbgArrears());
			}
			StringBuffer sb=new StringBuffer("SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_tel1,brc.relcamp_addr");
			sb.append(" FROM bas_relation_campany brc WHERE brc.relcamp_id="+bbg.getCustomId()+"");
			List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
			if(lt!=null&&lt.size()>0){
				Object[] obj=lt.get(0);
				gathering.setCustomId(obj[0].toString());
				gathering.setCustomName(obj[1].toString());
				gathering.setCustomTel(obj[2].toString());
				gathering.setCustomAddr(obj[3].toString());
			}
//			gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
//			gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_claimant", Contstants.CLAIMSBATCHGATHERING));
			return gathering;
		}else{
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_claimant", Contstants.CLAIMSBATCHGATHERING));
				return gathering;
			}
		}
		String sql="SELECT SUM(fcm.claimantm_amount) FROM fin_claimant_main fcm WHERE fcm.claimantm_id in("+gtVo.getPreclrIds()+")";
		List list=frtPreClearingDao.createSQLQuery(sql);
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			gathering.setPreclrAmount(Double.parseDouble(list.get(0).toString()));
			gathering.setSumAmount(Double.parseDouble(list.get(0).toString()));
		}else{
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_tel1,brc.relcamp_addr");
		sb.append(" FROM bas_relation_campany brc,fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr");
		sb.append(" WHERE fr.reception_id=fpc.reception_id AND fcm.preclr_id=fpc.preclr_id AND brc.relcamp_id=fr.fin_com_id");
		sb.append(" and fcm.claimantm_id='"+gtVo.getPreclrId()+"'");
		List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
		if(lt!=null&&lt.size()>0){
			Object[] obj=lt.get(0);
			gathering.setCustomId(obj[0].toString());
			gathering.setCustomName(obj[1].toString());
			gathering.setCustomTel(obj[2].toString());
			gathering.setCustomAddr(obj[3].toString());
		}
//		gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
//		gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_claimant", Contstants.CLAIMSBATCHGATHERING));
		return gathering;
	}
	/**查找销售批量收款相关数据*/
	private GatheringVo datagridSellBatchGatheringByPreclrId(GatheringVo gtVo,Boolean tag)throws Exception{
		GatheringVo gathering=null;
		if(tag==false){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_sell", Contstants.SELLBATCHGATHERING));
				return gathering;
			}
			BatchBalanceGather bbg=batchBalanceGatherDao.get(BatchBalanceGather.class,gtVo.getBbgId());
			if(bbg!=null){
				gathering=new GatheringVo();
				gathering.setBbgId(gtVo.getBbgId());
				gathering.setPreclrAmount(bbg.getBbgArrears());
			}
			StringBuffer sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc WHERE fc.custom_id='"+bbg.getCustomId()+"'");
			List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
			if(lt!=null&&lt.size()>0){
				Object[] obj=lt.get(0);
				gathering.setCustomId(obj[0].toString());
				gathering.setCustomName(obj[1].toString());
				gathering.setCustomTel(obj[2].toString());
				gathering.setCustomAddr(obj[3].toString());
			}
//			gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
//			gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_sell", Contstants.SELLBATCHGATHERING));
			return gathering;
		}else{
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering=new GatheringVo();
				gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
				gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_sell", Contstants.SELLBATCHGATHERING));
				return gathering;
			}
		}
		String sql="SELECT SUM(sspm.preclr_amount) FROM st_sell_preclr_main sspm WHERE sspm.preclr_id in("+gtVo.getPreclrIds()+")";
		List list=frtPreClearingDao.createSQLQuery(sql);
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			gathering.setPreclrAmount(Double.parseDouble(list.get(0).toString()));
			gathering.setSumAmount(Double.parseDouble(list.get(0).toString()));
		}else{
			return null;
		}
		StringBuffer sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
		sb.append(" FROM st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc");
		sb.append(" WHERE sspm.CER_NO=sso.SELLMM_ID AND fc.custom_id=sso.SELLCUSTOM_ID AND sspm.preclr_id='"+gtVo.getPreclrId()+"'");
		List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
		if(lt!=null&&lt.size()>0){
			Object[] obj=lt.get(0);
			gathering.setCustomId(obj[0].toString());
			gathering.setCustomName(obj[1].toString());
			gathering.setCustomTel(obj[2].toString());
			gathering.setCustomAddr(obj[3].toString());
		}
//		gathering.setTagId(Contstants.CLAIMSBATCHGATHERING);
//		gathering.setGatheringId(CreateID.createId("BatchPaymentDetail_batch_sell", Contstants.SELLBATCHGATHERING));
		return gathering;
	}
	
	/**
	 * 查找收款相关数据
	 * */
	
	public GatheringVo datagridGatheringByPreclrId(GatheringVo gtVo) throws Exception {
		if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null){//索赔结算单号 
			return datagridClaimantGatheringByPreclrId(gtVo);
		}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null){//维修结算单号
			return datagridPreclearingGatheringByPreclrId(gtVo);
		}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null){//销售结算单号
			return datagridSellGatheringByPreclrId(gtVo);
		}
		return null;
	}
	
	/**查找维修收款相关数据*/
	private GatheringVo datagridPreclearingGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getTag()!=null&&gtVo.getTag().toString().equals("true")){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("FinCollectionSchedule", Contstants.SERVICEGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT fmr.MR_ARREARS,fcar.car_license,fcar.car_vin,");
		sb.append(" fcar.car_motor_id,bcb.cbrd_name,bct.ctype_name,bcc.color_name,");
		sb.append(" fc.custom_name,fc.custom_tel1,fc.custom_addr,rt.rept_name,a.dataValue,fcar.car_id");
		sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,frt_car fcar,fin_maintenance_receivables fmr,");
		sb.append(" bas_car_type bct,bas_car_color bcc,bas_car_brand bcb,reptype rt,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb.append(" WHERE fpc.reception_id=fr.reception_id AND fr.custom_id=fc.custom_id AND fcar.car_id=fr.car_id AND fmr.PRECLR_ID=fpc.PRECLR_ID");
		sb.append(" AND bct.ctype_id=fcar.ctype_id AND bct.cbrd_id=bcb.cbrd_id AND fcar.color_id=bcc.color");
		sb.append(" AND rt.rept_id=fr.rept_id AND a.dataKey=fpc.preclr_inoice_type");
		sb.append(" AND fpc.preclr_id='"+gtVo.getPreclrId()+"'");
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setPreclrId(gtVo.getPreclrId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCarLicense(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCarVinName(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCarMotorIdName(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				gathering.setCbrdName(obj[4].toString());
			if(obj[5]!=null&&obj[5].toString().length()>0)
				gathering.setCtypeName(obj[5].toString());
			if(obj[6]!=null&&obj[6].toString().length()>0)
				gathering.setColorName(obj[6].toString());
			if(obj[7]!=null&&obj[7].toString().length()>0)
				gathering.setCustomName(obj[7].toString());
			if(obj[8]!=null&&obj[8].toString().length()>0)
				gathering.setCustomTel(obj[8].toString());
			if(obj[9]!=null&&obj[9].toString().length()>0)
				gathering.setCustomAddr(obj[9].toString());
			if(obj[10]!=null&&obj[10].toString().length()>0)
				gathering.setReptName(obj[10].toString());
			if(obj[11]!=null&&obj[11].toString().length()>0)
				gathering.setInvoiceTypeName(obj[11].toString());
			if(obj[12]!=null&&obj[12].toString().length()>0)
				gathering.setCarId(obj[12].toString());
		}
//		StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
//		sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
//		sb1.append(" WHERE bvi.car_id=fcar.car_id AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
//		sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gtVo.getCustomId()+"'");
//		List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
//		if(list1!=null&&list1.size()>0){
//			Object[] obj=list1.get(0);
//			if(obj[0]!=null&&obj[0].toString().length()>0)
//				gathering.setMemberId(obj[0].toString());
//			if(obj[1]!=null&&obj[1].toString().length()>0)
//				gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
//			else
//				gathering.setSumIntegral(0);
//			if(obj[2]!=null&&obj[2].toString().length()>0)
//				gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
//			else
//				gathering.setUseIntegral(0);
//			if(obj[3]!=null&&obj[3].toString().length()>0)
//				gathering.setMemberClass(obj[3].toString());
//			if(obj[4]!=null&&obj[4].toString().length()>0)
//				gathering.setMemberGrade(obj[4].toString());
//		}
		if(gathering != null && gathering.getCarId() != null){
    		StRecharge sr=stRechargeDao.get("from StRecharge sr where sr.perchargeType='储备金' and sr.frtCar.carId='"+gathering.getCarId()+"'");
    		if(sr!=null){
    			if(sr.getRechargeId()!=null&&sr.getRechargeId().toString().length()>0){
    				gathering.setHiveId(sr.getRechargeId());
    			}
    			if(sr.getSurplusMoney()!=null&&sr.getSurplusMoney().toString().length()>0)
    				gathering.setHiveBalance(sr.getSurplusMoney());
    			else
    				gathering.setHiveBalance(0.00d);
    		}else{
    			gathering.setHiveBalance(0.00d);
    		}
    		gathering.setHiveUse(0.00d);
    		gathering.setTagId(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID);
    		gathering.setGatheringId(CreateID.createId("FinCollectionSchedule", Contstants.SERVICEGATHERING));
		}
		return gathering;
	}
	/**查找索赔收款相关数据*/
	private GatheringVo datagridClaimantGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getTag()!=null&&gtVo.getTag().toString().equals("true")){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("FinCounterclaimSchedule", Contstants.CLAIMSGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT fcr.CR_ARREARS,brc.relcamp_name,");
		sb.append(" brc.relcamp_tel1,brc.relcamp_addr,a.dataValue");
		sb.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb.append(" WHERE  fcm.preclr_id=fpc.preclr_id AND fpc.reception_id=fr.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
		sb.append(" AND brc.relcamp_id=fr.fin_com_id AND a.dataKey=fcm.claimantm_invoice_type");
		sb.append(" AND fcm.claimantm_id='"+gtVo.getPreclrId()+"'");
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setPreclrId(gtVo.getPreclrId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCustomName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCustomTel(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCustomAddr(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				gathering.setInvoiceTypeName(obj[4].toString());
		}
		gathering.setHiveBalance(0.00d);
		gathering.setHiveUse(0.00d);
		gathering.setTagId(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID);
		gathering.setGatheringId(CreateID.createId("FinCounterclaimSchedule", Contstants.CLAIMSGATHERING));
		return gathering;
	}
	
	/**查找销售收款相关数据
	 * @throws Exception */
	private GatheringVo datagridSellGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getTag()!=null&&gtVo.getTag().toString().equals("true")){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("StSellChargeitem", Contstants.SELLGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT MR_ARREARS,CUSTOM_NAME,CUSTOM_TEL1,CUSTOM_ADDR,PRECLR_ID FROM("+
		" SELECT b.*,frt_custom.CUSTOM_NAME,frt_custom.CUSTOM_TEL1,frt_custom.CUSTOM_ADDR FROM ("+
		" SELECT a.*,st_sell_order.SELLCUSTOM_ID FROM (	"+
		" SELECT fin_maintenance_receivables.*,frt_pre_clearing.RECEPTION_ID "+
		" FROM fin_maintenance_receivables LEFT JOIN frt_pre_clearing"+
		" ON fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID) AS a"+
		" INNER JOIN st_sell_order ON a.RECEPTION_ID=st_sell_order.SELLMM_ID) AS b"+
		" INNER JOIN frt_custom ON b.SELLCUSTOM_ID=frt_custom.CUSTOM_ID) AS c where 1=1 ");
		sb.append(" AND PRECLR_ID='"+gtVo.getPreclrId()+"'");
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setPreclrId(gtVo.getPreclrId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCustomName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCustomTel(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCustomAddr(obj[3].toString());
		}
//		StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
//		sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
//		sb1.append(" WHERE bvi.car_id=fcar.car_id AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
//		sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gtVo.getCustomId()+"'");
//		List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
//		if(list1!=null&&list1.size()>0){
//			Object[] obj=list1.get(0);
//			if(obj[0]!=null&&obj[0].toString().length()>0)
//				gathering.setMemberId(obj[0].toString());
//			if(obj[1]!=null&&obj[1].toString().length()>0)
//				gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
//			else
//				gathering.setSumIntegral(0);
//			if(obj[2]!=null&&obj[2].toString().length()>0)
//				gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
//			else
//				gathering.setUseIntegral(0);
//			if(obj[3]!=null&&obj[3].toString().length()>0)
//				gathering.setMemberClass(obj[3].toString());
//			if(obj[4]!=null&&obj[4].toString().length()>0)
//				gathering.setMemberGrade(obj[4].toString());
//		}
		gathering.setHiveUse(0.00d);
		gathering.setTagId(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID);
		gathering.setGatheringId(CreateID.createId("StSellChargeitem", Contstants.SELLGATHERING));
		return gathering;
	}
	/**
	 * 查找批量收款记录
	 * */
	
	public Datagrid datagridBatchGatheringOld(GatheringVo gtVo)
			throws Exception {	
		if(opinionId(gtVo.getBbgId(),Contstants.CLAIMSBATCHGATHERINGCOLLECT)!=null){
			return datagridBatchGatheringOldByBbgId(gtVo);
		}else if(opinionId(gtVo.getBbgId(),Contstants.SERVICEBATCHGATHERINGCOLLECT)!=null){
			return datagridBatchGatheringOldByBbgId(gtVo);
		}else if(opinionId(gtVo.getBbgId(),Contstants.SELLBATCHGATHERINGCOLLECT)!=null){
			return datagridBatchGatheringOldByBbgId(gtVo);
		}
		return null;
	}
	private Datagrid datagridBatchGatheringOldByBbgId(GatheringVo gtVo)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT bpd.BPD_ID,bpd.BPD_DATE,bpd.BPD_PAYABLE,bpd.BPD_PAYMENT_MONEY,");
		sb.append(" bpd.BPD_ARREARS,a.dataValue,bs.STF_NAME,bpd.BPD_REMARK");
		sb.append(" FROM batch_payment_detail bpd,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp"); 
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' ) a");
		sb.append(" WHERE bs.STF_ID=bpd.STF_ID AND a.dataKey=bpd.PAYMENT_ID");
		if(gtVo.getBbgId()!=null&&gtVo.getBbgId().length()>0)
			sb.append(" AND bpd.BBG_ID='"+gtVo.getBbgId()+"'");
		else
			sb.append(" AND bpd.BBG_ID='-1'");
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setGatheringId(objects[0].toString());
				gatheringVo.setPaymentTime(objects[1].toString());
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setGatheringWise(objects[5].toString());
				gatheringVo.setStfName(objects[6].toString());
				gatheringVo.setGatheringRemark(objects[7].toString());
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找收款记录
	 * */
	
	public Datagrid datagridGatheringOld(GatheringVo gtVo) throws Exception {
		if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null){
			return datagridClaimantGatheringOld(gtVo);
		}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null){
			return datagridPreclearingGatheringOld(gtVo);
		}else if(opinionId(gtVo.getPreclrId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null){
			return datagridSellGatheringOld(gtVo);
		}
		return null;
	}
	/**查找索赔收款记录*/
	private Datagrid datagridClaimantGatheringOld(GatheringVo gtVo)throws Exception{
	    Datagrid dg=new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT fccs.FCCS_ID,fccs.FCCS_DATE,fccs.FCCS_PAYABLE,fccs.FCCS_PAYMENT_MONEY,");
		sb.append(" fccs.FCCS_ARREARS,a.dataValue,bs.STF_NAME,fccs.FCCS_REMARK,fccs.FCCS_PAYMENT");
		sb.append(" FROM fin_claims_receivables fcr,fin_counterclaim_schedule fccs,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' )a");
		sb.append(" WHERE fccs.CR_ID=fcr.CR_ID AND fccs.STF_ID=bs.STF_ID AND a.dataKey=fccs.PAYMENT_ID");
		if(gtVo.getPreclrId()!=null&&gtVo.getPreclrId().length()>0)
			sb.append(" AND fcr.CLAIMANTM_ID='"+gtVo.getPreclrId()+"'");
		else
			sb.append(" AND fcr.CLAIMANTM_ID='-1'");
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0){
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setGatheringId(objects[0] != null ? objects[0].toString() : "");
				gatheringVo.setPaymentTime(objects[1] != null ? objects[1].toString()  : "");
				gatheringVo.setPayableAmount(objects[2] != null ? Double.parseDouble(objects[2].toString()) : 0.00d);
				gatheringVo.setPaymentAmount(objects[3] != null ? Double.parseDouble(objects[3].toString()) : 0.00d);
				gatheringVo.setArrearsAmount(objects[4] != null ? Double.parseDouble(objects[4].toString()) : 0.00d);
				gatheringVo.setGatheringWise(objects[5] != null ? objects[5].toString() : "");
				gatheringVo.setStfName(objects[6] != null ? objects[6].toString() : "");
				gatheringVo.setGatheringRemark(objects[7] != null ? objects[7].toString() : "");
				gatheringVo.setFcsPayment(objects[8] != null ? Double.parseDouble(objects[8].toString()) : 0.00d);
				rows.add(gatheringVo);
			}
		}
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**查找维修收款记录*/
	private Datagrid datagridPreclearingGatheringOld(GatheringVo gtVo)throws Exception{
	    Datagrid dg=new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT fcs.FCS_ID,fcs.FCS_DATE,fcs.FCS_PAYABLE,fcs.FCS_PAYMENT_MONEY,");
		sb.append(" fcs.FCS_ARREARS,a.dataValue,bs.STF_NAME,fcs.FCS_REMARK,fcs.FCS_PAYMENT");
		sb.append(" FROM fin_maintenance_receivables fmr,fin_collection_schedule fcs,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' )a");
		sb.append(" WHERE fcs.MR_ID=fmr.MR_ID AND fcs.STF_ID=bs.STF_ID AND a.dataKey=fcs.PAYMENT_ID   and  fmr.enterprise_id="+gtVo.getEnterpriseId());
		if(gtVo.getPreclrId()!=null&&gtVo.getPreclrId().length()>0)
			sb.append(" AND fmr.PRECLR_ID='"+gtVo.getPreclrId()+"'");
		else
			sb.append(" AND fmr.PRECLR_ID='-1'");
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0){
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				if(objects[0]!=null&&objects[0].toString().length()>0)
					gatheringVo.setGatheringId(objects[0].toString());
				if(objects[1]!=null&&objects[1].toString().length()>0)
					gatheringVo.setPaymentTime(objects[1].toString());
				if(objects[2]!=null&&objects[2].toString().length()>0)
					gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				if(objects[3]!=null&&objects[3].toString().length()>0)
					gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				if(objects[4]!=null&&objects[4].toString().length()>0)
					gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				if(objects[5]!=null&&objects[5].toString().length()>0)
					gatheringVo.setGatheringWise(objects[5].toString());
				if(objects[6]!=null&&objects[6].toString().length()>0)
					gatheringVo.setStfName(objects[6].toString());
				if(objects[7]!=null&&objects[7].toString().length()>0)
					gatheringVo.setGatheringRemark(objects[7].toString());
				if(objects[8]!=null&&objects[8].toString().length()>0)
                    gatheringVo.setFcsPayment(Double.parseDouble(objects[8].toString()));
				rows.add(gatheringVo);
			}
		}
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	/**查找销售收款记录*/
	private Datagrid datagridSellGatheringOld(GatheringVo gtVo)throws Exception{
	    Datagrid dg=new Datagrid();
		StringBuffer sb=new StringBuffer("SELECT ssc.SSC_ID,ssc.SSC_DATE,ssc.SSC_PAYABLE,ssc.SSC_PAYMENT_MONEY,");
		sb.append(" ssc.SSC_ARREARS,a.dataValue,bs.STF_NAME,ssc.SSC_REMARK");
		sb.append(" FROM st_sell_charge charge,st_sell_chargeitem ssc,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' )a");
		sb.append(" WHERE ssc.CHARGE_ID=charge.CHARGE_ID AND ssc.STF_ID=bs.STF_ID AND a.dataKey=ssc.PAYMENT_ID");
		if(gtVo.getPreclrId()!=null&&gtVo.getPreclrId().length()>0)
			sb.append(" AND charge.PRECLR_ID='"+gtVo.getPreclrId()+"'");
		else
			sb.append(" AND charge.PRECLR_ID='-1'");
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0){
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setGatheringId(objects[0].toString());
				gatheringVo.setPaymentTime(objects[1].toString());
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setGatheringWise(objects[5].toString());
				gatheringVo.setStfName(objects[6].toString());
				gatheringVo.setGatheringRemark(objects[7].toString());
				rows.add(gatheringVo);
			}
		}
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找批量未收款信息
	 * */
	
	public Datagrid datagridNoBatchGathering(GatheringVo gtVo) throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllNoBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoBatchGathering(gtVo);
		}
		return null;
	}
	/**查找维修批量未收款记录*/
	private Datagrid datagridPreclearingNoBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT fpc.preclr_id,fpc.preclr_time,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,fmr.MR_RECEIVABLES,fmr.MR_CUMULATIVE,fmr.MR_ARREARS");
		sb1.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,frt_car fcar,fin_maintenance_receivables fmr,"); 
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fc.custom_id=fr.custom_id AND fpc.reception_id=fr.reception_id AND fmr.PRECLR_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND a.dataKey=fpc.preclr_inoice_type");
		sb1.append(" AND fmr.MR_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +"");
		sb1.append(" AND fmr.MR_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED );
		if(gtVo.getTag()==true){
			sb1.append(" AND fmr.MR_RECEIVABLES=fmr.MR_ARREARS");
		}
		
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)!=null){
			sb1.append(" AND fpc.preclr_id='"+gtVo.getBalanceId()+"'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_id='"+gtVo.getCarId()+"'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sb1.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb1.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**查找索赔批量未收款记录*/
	private Datagrid datagridClaimantNoBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT fcm.claimantm_id,fcm.claimantm_time,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,fcr.CR_RECEIVABLES,fcr.CR_CUMULATIVE,fcr.CR_ARREARS");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,fin_claims_receivables fcr,");	
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		
//		sb1.append(" AND fcm.CLAIMANTM_ID NOT IN(SELECT DISTINCT bbd.STRIKE_ID");
//		sb1.append(" FROM batch_balance_gather bbg,batch_balance_detail bbd	WHERE bbd.bbg_id=bbg.bbg_id)");
//		
		sb1.append(" AND fr.fin_com_id=brc.relcamp_id AND fcr.CR_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED+"");
		if(gtVo.getTag()==true){
			sb1.append(" AND fcr.CR_RECEIVABLES=fcr.CR_ARREARS");
		}
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)!=null){
			sb1.append(" AND fcm.claimantm_id='"+gtVo.getBalanceId()+"'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sb1.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
			for (Object[] objects : rowsList1) {
				gatheringVo=new GatheringVo();
				if(objects[0]!=null&&objects[0].toString().length()>0)
					gatheringVo.setPreclrId(objects[0].toString());
				if(objects[1]!=null&&objects[1].toString().length()>0)
					gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
				if(objects[2]!=null&&objects[2].toString().length()>0)
					gatheringVo.setCustomId(objects[2].toString());
				if(objects[3]!=null&&objects[3].toString().length()>0)
					gatheringVo.setCustomName(objects[3].toString());
				if(objects[4]!=null&&objects[4].toString().length()>0)
					gatheringVo.setCarLicense(objects[4].toString());
				if(objects[5]!=null&&objects[5].toString().length()>0)
					gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
				if(objects[6]!=null&&objects[6].toString().length()>0)
					gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
				if(objects[7]!=null&&objects[7].toString().length()>0)
					gatheringVo.setPreclrNo(objects[7].toString());
				if(objects[8]!=null&&objects[8].toString().length()>0)
					gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
				else
					gatheringVo.setPreclrAmount(0.00d);
				if(objects[9]!=null&&objects[9].toString().length()>0)
					gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
				else
					gatheringVo.setCumulativeAmount(0.00d);
				if(objects[10]!=null&&objects[10].toString().length()>0)
					gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
				else
					gatheringVo.setArrearsAmount(0.00d);
				rows1.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sb1.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**查找销售批量未收款记录
	 * @throws Exception */
	private Datagrid datagridSellNoBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT sspm.preclr_id,sspm.preclr_date,fc.custom_id,fc.custom_name,");
		sb1.append(" charge.PAID_AMOUNT,charge.TOTAL_AMOUNT,charge.PAID_BALANCE");
		sb1.append(" FROM st_sell_charge charge,frt_custom fc,st_sell_preclr_main sspm,st_sell_order sso");
		sb1.append(" WHERE fc.custom_id=sso.SELLCUSTOM_ID AND sspm.CER_NO=sso.SELLMM_ID AND charge.PRECLR_ID=sspm.PRECLR_ID");
		//sb1.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND charge.CHARGE_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +"");
		sb1.append(" AND charge.CHARGE_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED );
		if(gtVo.getTag()==true){
			sb1.append(" AND charge.PAID_AMOUNT=charge.PAID_BALANCE");
		}
		if(opinionId(gtVo.getBalanceId(),Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)!=null){
			sb1.append(" AND sspm.preclr_id='"+gtVo.getBalanceId()+"'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sb1.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setPreclrId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setPreclrTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[4].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[5].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[6].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb1.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**查找所有批量未收款记录*/
	private Datagrid datagridAllNoBatchGathering(GatheringVo gtVo){
		return null;
	}
	/**
	 *查找批量收款汇总信息
	 * */
	
	public Datagrid datagridBatchGatheringCollect(GatheringVo gtVo) throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllBatchGatheringCollect(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantBatchGatheringCollect(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclrearingBatchGatheringCollect(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellBatchGatheringCollect(gtVo);
		}
		return null;
	}
	/**查找维修批量收款汇总信息*/
	private Datagrid datagridPreclrearingBatchGatheringCollect(GatheringVo gtVo)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT bbg.BBG_ID,bbg.BBG_DATE,bbg.BBG_RECEIVABLES,bbg.BBG_CUMULATIVE,bbg.BBG_ARREARS,fc.CUSTOM_NAME,bbg.BBG_DIFFERENCE_BALANCE");
		sb.append(" FROM batch_balance_gather bbg,frt_custom fc");
		sb.append(" WHERE fc.CUSTOM_ID=bbg.CUSTOM_ID");
		sb.append(" AND bbg.BBG_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND bbg.BBG_ID like '%"+Contstants.SERVICEBATCHGATHERINGCOLLECT+"%'");
		return datagridBatchGatheringCollect(gtVo,sb.toString());
	}
	/**查找索赔批量收款汇总信息*/
	private Datagrid datagridClaimantBatchGatheringCollect(GatheringVo gtVo)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT bbg.BBG_ID,bbg.BBG_DATE,bbg.BBG_RECEIVABLES,bbg.BBG_CUMULATIVE,bbg.BBG_ARREARS, brc.RELCAMP_NAME,bbg.BBG_DIFFERENCE_BALANCE");
		sb.append(" FROM batch_balance_gather bbg,bas_relation_campany brc");
		sb.append(" WHERE brc.relcamp_id=bbg.CUSTOM_ID");
		sb.append(" AND bbg.BBG_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND bbg.BBG_ID like '%"+Contstants.CLAIMSBATCHGATHERINGCOLLECT+"%'");
		return datagridBatchGatheringCollect(gtVo,sb.toString()); 
	}
	/**查找销售批量收款汇总信息*/
	private Datagrid datagridSellBatchGatheringCollect(GatheringVo gtVo)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT bbg.BBG_ID,bbg.BBG_DATE,bbg.BBG_RECEIVABLES,bbg.BBG_CUMULATIVE,bbg.BBG_ARREARS,fc.CUSTOM_NAME,bbg.BBG_DIFFERENCE_BALANCE");
		sb.append(" FROM batch_balance_gather bbg,frt_custom fc");
		sb.append(" WHERE fc.CUSTOM_ID=bbg.CUSTOM_ID");
		sb.append(" AND bbg.BBG_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND bbg.BBG_ID like '%"+Contstants.SELLBATCHGATHERINGCOLLECT+"%'");
		return datagridBatchGatheringCollect(gtVo,sb.toString());
	}
	/**
	 *查找批量收款汇总信息
	 * */
	private Datagrid datagridBatchGatheringCollect(GatheringVo gtVo,String sql)throws Exception{
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setBbgId(objects[0].toString());
				gatheringVo.setCreateTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setCustomName(objects[5].toString());
				gatheringVo.setDifferenceBalance(Short.parseShort(objects[6].toString()));
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**查找所有批量收款汇总信息*/
	private Datagrid datagridAllBatchGatheringCollect(GatheringVo gtVo)throws Exception{
		return null;
	}
	/************************************************代付**************************************************/
	/**
	 * 查找代付批量收款相关数据
	 * */
	
	public GatheringVo datagridSubstituteGatheringBySspId(GatheringVo gtVo) throws Exception {
		if(opinionId(gtVo.getSspId(),Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING)!=null){
			return datagridClaimantBatchGatheringBySspId(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SERVICESUITSUBTITUTEBATCHGATHERING)!=null){
			return datagridPreclearingBatchGatheringBySspId(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SELLSUITSUBTITUTEBATCHGATHERING)!=null){
			return datagridSellBatchGatheringBySspId(gtVo);
		}
		return null;
	}
	/**查找代付批量维修收款相关数据*/
	private GatheringVo datagridPreclearingBatchGatheringBySspId(GatheringVo gtVo) throws Exception{
		GatheringVo gatheringVo=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gatheringVo=new GatheringVo();
			gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_preClearing", Contstants.SERVICESUBTITUTEGATHERING));
			return gatheringVo;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT fc.CUSTOM_NAME,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR");
		sb.append(" FROM frt_custom fc ,substitute_suit_payment ssp WHERE ssp.CUSTOM_ID=fc.CUSTOM_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString());
		if(rowsList!=null&&rowsList.size()>0){
			Object[] objects=rowsList.get(0);
			gatheringVo=new GatheringVo();
			gatheringVo.setCustomName(objects[0].toString());
			gatheringVo.setCustomTel(objects[1].toString());
			gatheringVo.setCustomAddr(objects[2].toString());
		}
		gatheringVo.setSspId(gtVo.getSspId());
		SubstituteSuitPayment ssp=substituteSuitPaymentDao.get(SubstituteSuitPayment.class, gtVo.getSspId());
		gatheringVo.setPreclrAmount(ssp.getSspArrears());
		gatheringVo.setHiveUse(0.00d);
		//gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_preClearing", Contstants.SERVICESUBTITUTEGATHERING));
		return gatheringVo;
	}
	/**查找代付批量索赔收款相关数据*/
	private GatheringVo datagridClaimantBatchGatheringBySspId(GatheringVo gtVo) throws Exception{
		GatheringVo gatheringVo=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gatheringVo=new GatheringVo();
			gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_claimant", Contstants.CLAIMSSUBTITUTEGATHERING));
			return gatheringVo;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT brc.RELCAMP_NAME,brc.RELCAMP_TEL1,brc.RELCAMP_ADDR");
		sb.append(" FROM bas_relation_campany brc,substitute_suit_payment ssp WHERE ssp.CUSTOM_ID=brc.RELCAMP_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString());
		if(rowsList!=null&&rowsList.size()>0){
			Object[] objects=rowsList.get(0);
			gatheringVo=new GatheringVo();
			gatheringVo.setCustomName(objects[0].toString());
			gatheringVo.setCustomTel(objects[1].toString());
			gatheringVo.setCustomAddr(objects[2].toString());
		}
		gatheringVo.setSspId(gtVo.getSspId());
		SubstituteSuitPayment ssp=substituteSuitPaymentDao.get(SubstituteSuitPayment.class, gtVo.getSspId());
		gatheringVo.setPreclrAmount(ssp.getSspArrears());
		gatheringVo.setHiveUse(0.00d);
		//gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_claimant", Contstants.CLAIMSSUBTITUTEGATHERING));
		return gatheringVo;
	}
	/**查找代付批量销售收款相关数据
	 * @throws Exception */
	private GatheringVo datagridSellBatchGatheringBySspId(GatheringVo gtVo) throws Exception{
		GatheringVo gatheringVo=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gatheringVo=new GatheringVo();
			gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_sell", Contstants.SELLSUBTITUTEGATHERING));
			return gatheringVo;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT fc.CUSTOM_NAME,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR");
		sb.append(" FROM frt_custom fc ,substitute_suit_payment ssp WHERE ssp.CUSTOM_ID=fc.CUSTOM_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString());
		if(rowsList!=null&&rowsList.size()>0){
			Object[] objects=rowsList.get(0);
			gatheringVo=new GatheringVo();
			gatheringVo.setCustomName(objects[0].toString());
			gatheringVo.setCustomTel(objects[1].toString());
			gatheringVo.setCustomAddr(objects[2].toString());
		}
		gatheringVo.setSspId(gtVo.getSspId());
		SubstituteSuitPayment ssp=substituteSuitPaymentDao.get(SubstituteSuitPayment.class, gtVo.getSspId());
		gatheringVo.setPreclrAmount(ssp.getSspArrears());
		gatheringVo.setHiveUse(0.00d);
//		gatheringVo.setGatheringId(CreateID.createId("SubstitutePaymentDetail_sell", Contstants.SELLSUBTITUTEGATHERING));
		return gatheringVo;
	}
	/**
	 *查找批量代付收款记录 
	 * */
	
	public Datagrid datagridBatchSubstituteGatheringOld(GatheringVo gtVo)
			throws Exception {
		if(opinionId(gtVo.getSspId(),Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
			return datagridClaimantBatchSubstituteGatheringOld(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
			return datagridPreclearingBatchSubstituteGatheringOld(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
			return datagridSellBatchSubstituteGatheringOld(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
			return datagridClaimantBatchSubstituteGatheringOld(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
			return datagridPreclearingBatchSubstituteGatheringOld(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
			return datagridSellBatchSubstituteGatheringOld(gtVo);
		}
		return null;
	}
	/**查找批量代付维修收款记录*/
	private Datagrid datagridPreclearingBatchSubstituteGatheringOld(GatheringVo gtVo) throws Exception{
		return datagridSubstituteGatheringOld(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
	}
	/**查找批量代付索赔收款记录*/
	private Datagrid datagridClaimantBatchSubstituteGatheringOld(GatheringVo gtVo) throws Exception{
		return datagridSubstituteGatheringOld(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
	}
	/**查找批量代付销售收款记录
	 * @throws Exception */
	private Datagrid datagridSellBatchSubstituteGatheringOld(GatheringVo gtVo) throws Exception{
		return datagridSubstituteGatheringOld(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
	}
	/**查找代付批量收款记录*/
	private Datagrid datagridSubstituteGatheringOld(GatheringVo gtVo,Short tag)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT sbbd.SBBD_ID,sbbd.SBBD_DATE,sbbd.SBBD_PAYABLE,sbbd.SBBD_PAYMENT_MONEY,");
		sb.append(" sbbd.SBBD_ARREARS,a.dataValue,bs.STF_NAME,sbbd.SBBD_REMARK");
		sb.append(" FROM substitute_suit_payment ssp,substitute_batch_balance_detail sbbd,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' )a"); 
		sb.append(" WHERE sbbd.SSP_ID=ssp.SSP_ID AND sbbd.STF_ID=bs.STF_ID"); 
		sb.append(" AND a.dataKey=sbbd.PAYMENT_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setGatheringId(objects[0].toString());
				gatheringVo.setPaymentTime(objects[1].toString());
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setGatheringWise(objects[5].toString());
				gatheringVo.setStfName(objects[6].toString());
				gatheringVo.setGatheringRemark(objects[7].toString());
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找代付批量未收款信息
	 * */
	
	public Datagrid datagridNoSubstituteBatchGathering(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllNoSubstituteBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoSubstituteBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoSubstituteBatchGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoSubstituteBatchGathering(gtVo);
		}
		return null;
	}
	/**查找代付批量维修未收款记录*/
	private Datagrid datagridPreclearingNoSubstituteBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.CUSTOM_ID,fc.CUSTOM_NAME,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb.append(" FROM substitute_suit_payment ssp,frt_custom fc WHERE fc.CUSTOM_ID=ssp.CUSTOM_ID AND ssp.SSP_ID like '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%'");
		if(gtVo.getTag()==true){
			sb.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		return datagridNoSubstituteBatchGathering(sb.toString(),gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
	}
	/**查找代付批量索赔未收款记录*/
	private Datagrid datagridClaimantNoSubstituteBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,brc.RELCAMP_ID,brc.RELCAMP_NAME,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb.append(" FROM substitute_suit_payment ssp,bas_relation_campany brc WHERE brc.RELCAMP_ID=ssp.CUSTOM_ID AND ssp.SSP_ID like '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%'");
		if(gtVo.getTag()==true){
			sb.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		return datagridNoSubstituteBatchGathering(sb.toString(),gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
	}
	/**查找代付批量销售未收款记录
	 * @throws Exception */
	private Datagrid datagridSellNoSubstituteBatchGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.CUSTOM_ID,fc.CUSTOM_NAME,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb.append(" FROM substitute_suit_payment ssp,frt_custom fc WHERE fc.CUSTOM_ID=ssp.CUSTOM_ID AND ssp.SSP_ID like '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%'");
		if(gtVo.getTag()==true){
			sb.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		return datagridNoSubstituteBatchGathering(sb.toString(),gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
	}
	private Datagrid datagridNoSubstituteBatchGathering(String sql,GatheringVo gtVo,Short tag) throws Exception{
		StringBuffer sb=new StringBuffer(sql);
		sb.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb.append(" AND ssp.SSP_TYPE="+tag+"");
		sb.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setSspId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setCreateTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[4].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[5].toString()));
			else
				gatheringVo.setPaymentAmount(0.00d);
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[6].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**查找批量代付所有未收款记录*/
	private Datagrid datagridAllNoSubstituteBatchGathering(GatheringVo gtVo){
		return null;
	}
	/**
	 * 查找代付未收款信息
	 * */
	
	public Datagrid datagridNoSubstituteGathering(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoSubstituteGathering(gtVo);
		}
		return null;
	}
	/**查找代付维修未收款记录*/
	private Datagrid datagridPreclearingNoSubstituteGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,");
		sb1.append(" ssp.SSP_ARREARS FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue");
		sb1.append(" FROM bas_childdictionary bc, bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id");
		sb1.append(" AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a ");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND a.dataKey=fpc.preclr_inoice_type and fpc.enterprise_id="+gtVo.getEnterpriseId());
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getTag()!=null&&gtVo.getTag()==true){
			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId().trim()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		return commonDatagridNoSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找代付索赔未收款记录*/
	private Datagrid datagridClaimantNoSubstituteGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND sb.STRIKE_ID=fcm.CLAIMANTM_ID AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.custom_id=brc.relcamp_id and  fcm.enterprise_id="+gtVo.getEnterpriseId());
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getTag()==true){
			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		return commonDatagridNoSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找代付销售未收款记录
	 * @throws Exception */
	private Datagrid datagridSellNoSubstituteGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,'' as carLicense,");
		sb1.append(" '' as temp1,'' as temp2,'' as temp3,");
		sb1.append(" ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb1.append(" FROM st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.CER_NO=sso.SELLMM_ID AND sb.STRIKE_ID=sspm.PRECLR_ID");
		//sb1.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getTag()==true){
			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		return commonDatagridNoSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找代付所有未收款记录
	 * @throws Exception */
	private Datagrid datagridAllNoSubstituteGathering(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,");
		sb1.append(" ssp.SSP_ARREARS FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue");
		sb1.append(" FROM bas_childdictionary bc, bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id");
		sb1.append(" AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a ");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND a.dataKey=fpc.preclr_inoice_type and fpc.enterprise_id="+gtVo.getEnterpriseId());
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getTag()!=null&&gtVo.getTag()==true){
			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(new String(gtVo.getCarId().getBytes("ISO-8859-1"),"UTF-8"))+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId().trim()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" UNION");
		sb1.append(" SELECT ssp.SSP_ID,ssp.SSP_DATE,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND sb.STRIKE_ID=fcm.CLAIMANTM_ID AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.custom_id=brc.relcamp_id and fcm.enterprise_id="+gtVo.getEnterpriseId());
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getTag()==true){
			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
		}
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
//		sb1.append(" UNION");
//		sb1.append(" SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,'' as carLicense,");
//		sb1.append(" '' as temp1,'' as temp2,'' as temp3,");
//		sb1.append(" ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS");
//		sb1.append(" FROM st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc,");
//		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
//		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
//		sb1.append(" AND sspm.CER_NO=sso.SELLMM_ID AND sb.STRIKE_ID=sspm.PRECLR_ID");
//		//sb1.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
//		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
//		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
//		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");
//		if(gtVo.getTag()==true){
//			sb1.append(" AND ssp.SSP_RECEIVABLES=ssp.SSP_ARREARS");
//		}
//		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
//			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
//		}
//		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
//			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
//		}
//		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
//			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId()+"'");
//		}
//		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
//			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
//		}
//		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
//			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
//		}
		return commonDatagridNoSubstituteGathering(sb1.toString(),gtVo);
	}
	private Datagrid commonDatagridNoSubstituteGathering(String sql,GatheringVo gtVo) throws Exception{
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setSspId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setCreateTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());				
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找代付收款信息
	 * */
	
	public Datagrid datagridSubstituteGathering(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellSubstituteGathering(gtVo);
		}
		return null;
	}
	/**查找所有代付收款信息*/
	private Datagrid datagridAllSubstituteGathering(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,'' as carLicense,");
		sb1.append(" '' as temp1,'' as temp2,'' as temp3,");
		sb1.append(" ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,ssp.SSP_UNFINISHED");
		sb1.append(" FROM st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.CER_NO=sso.SELLMM_ID AND sb.STRIKE_ID=sspm.PRECLR_ID");
		//sb1.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		sb1.append(" UNION");
		
		
		sb1.append(" SELECT ssp.SSP_ID,ssp.SSP_DATE,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,ssp.SSP_UNFINISHED");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND sb.STRIKE_ID=fcm.CLAIMANTM_ID AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.custom_id=brc.relcamp_id and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		sb1.append(" UNION");
		sb1.append(" SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,");
		sb1.append(" ssp.SSP_ARREARS,ssp.SSP_UNFINISHED FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue");
		sb1.append(" FROM bas_childdictionary bc, bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id");
		sb1.append(" AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a ");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND a.dataKey=fpc.preclr_inoice_type and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId().trim()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		return commonDatagridSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找销售代付收款信息*/
	private Datagrid datagridSellSubstituteGathering(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,'' as carLicense,");
		sb1.append(" '' as temp1,'' as temp2,'' as temp3,");
		sb1.append(" ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,ssp.SSP_UNFINISHED");
		sb1.append(" FROM st_sell_preclr_main sspm,st_sell_order sso,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.CER_NO=sso.SELLMM_ID AND sb.STRIKE_ID=sspm.PRECLR_ID");
		//sb1.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		return commonDatagridSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找索赔代付收款信息*/
	private Datagrid datagridClaimantSubstituteGathering(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,brc.relcamp_id,brc.relcamp_name,'' as carLicense,");
		sb1.append(" a.dataValue,fcm.claimantm_invoice_time,fcm.claimantm_invoice_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,ssp.SSP_UNFINISHED");
		sb1.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,");
		sb1.append(" bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
		sb1.append(" WHERE fcm.preclr_id=fpc.preclr_id AND fr.reception_id=fpc.reception_id  AND sb.STRIKE_ID=fcm.CLAIMANTM_ID AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND a.dataKey=fcm.claimantm_invoice_type AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.custom_id=brc.relcamp_id and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getTempCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		return commonDatagridSubstituteGathering(sb1.toString(),gtVo);
	}
	/**查找维修代付收款信息*/
	private Datagrid datagridPreclearingSubstituteGathering(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,fc.custom_id,fc.custom_name,fcar.car_license,");
		sb1.append(" a.dataValue,fpc.preclr_invoice_time,fpc.preclr_no,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,");
		sb1.append(" ssp.SSP_ARREARS,ssp.SSP_UNFINISHED FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb,");
		sb1.append(" (SELECT bc.dataKey,bc.dataValue");
		sb1.append(" FROM bas_childdictionary bc, bas_parentdictionary bp WHERE bp.parent_id=bc.parent_id");
		sb1.append(" AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a ");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND a.dataKey=fpc.preclr_inoice_type and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getTempCustomId()!=null&&gtVo.getTempCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getTempCustomId().trim()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		return commonDatagridSubstituteGathering(sb1.toString(),gtVo);
	}
	private Datagrid commonDatagridSubstituteGathering(String sql,GatheringVo gtVo) throws Exception{
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setSspId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setCreateTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomId(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomName(objects[3].toString());				
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setCarLicense(objects[4].toString());
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setPreclrInoiceTypeName(objects[5].toString());
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setPreclrInvoiceTime(MyBeanUtils.getInstance().formatString(objects[6].toString()));
			if(objects[7]!=null&&objects[7].toString().length()>0)
				gatheringVo.setPreclrNo(objects[7].toString());
			if(objects[8]!=null&&objects[8].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[8].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[9]!=null&&objects[9].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[9].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[10]!=null&&objects[10].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[10].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			if(objects[11]!=null&&objects[11].toString().length()>0)
				gatheringVo.setUnAchieve(Short.parseShort(objects[11].toString()));
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找代付收款汇总信息
	 * */
	
	public Datagrid datagridSubstituteGatheringMain(GatheringVo gtVo)throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
//			return datagridSellSubstituteGatheringMain(gtVo);
		}
		return null;
	}
	/**查找所有代付收款汇总信息*/
	public Datagrid datagridAllSubstituteGatheringMain(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_TEL1,brc.relcamp_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM fin_claimant_main fcm,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE brc.relcamp_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND sb.STRIKE_ID=fcm.claimantm_ID");
		sb1.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		sb1.append(" GROUP BY brc.relcamp_ID");
		sb1.append(" UNION");
		sb1.append(" SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +""); 
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
//		sb1.append(" UNION");
//		sb1.append(" SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
//		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
//		sb1.append(" SUM(ssp.SSP_ARREARS) FROM st_sell_preclr_main sspm,st_sell_charge ssc,frt_custom fc,");
//		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
//		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
//		sb1.append(" AND sspm.PRECLR_ID=ssc.PRECLR_ID  AND sb.STRIKE_ID=sspm.PRECLR_ID");
//		sb1.append(" AND ssc.state="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
//		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
//		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");		
//		if(gtVo.getBalanceId()!=null&&gtVo.getBalanceId().length()>0){
//			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
//		}
//		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
//			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
//		}
//		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
//			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
//		}
//		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
//			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
//		}
//		sb1.append(" GROUP BY fc.CUSTOM_ID");
		return commonDatagridSubstituteGatheringMain(gtVo,sb1.toString());
	}
	/**查找索赔代付收款汇总信息*/
	public Datagrid datagridClaimantSubstituteGatheringMain(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_TEL1,brc.relcamp_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM fin_claimant_main fcm,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE brc.relcamp_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND sb.STRIKE_ID=fcm.claimantm_ID and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		sb1.append(" GROUP BY brc.relcamp_ID");
		return commonDatagridSubstituteGatheringMain(gtVo,sb1.toString());
	}
	/**查找维修代付收款汇总信息*/
	public Datagrid datagridPreclearingSubstituteGatheringMain(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +""); 
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		return commonDatagridSubstituteGatheringMain(gtVo,sb1.toString());
	}
	/**查找销售代付收款汇总信息*/
	public Datagrid datagridSellSubstituteGatheringMain(GatheringVo gtVo)throws Exception {
		StringBuffer sb1=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM st_sell_preclr_main sspm,st_sell_charge ssc,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.PRECLR_ID=ssc.PRECLR_ID  AND sb.STRIKE_ID=sspm.PRECLR_ID");
		sb1.append(" AND ssc.state="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");		
		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		return commonDatagridSubstituteGatheringMain(gtVo,sb1.toString());
	}
	private Datagrid commonDatagridSubstituteGatheringMain(GatheringVo gtVo,String sql) throws Exception{
		Datagrid dg=new Datagrid();
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setCustomId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setCustomName(objects[1].toString());
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomTel(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomAddr(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[4].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[5].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[6].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			if(gatheringVo.getArrearsAmount()==0.00d)
				gatheringVo.setUnAchieve(Contstants.OPINIONFINISHED_TAG.FINISHED);
			else
				gatheringVo.setUnAchieve(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找代付收款相关数据
	 * */
	
	public GatheringVo datagridSubstituteGatheringByPreclrId(GatheringVo gtVo)
			throws Exception {
		if(opinionId(gtVo.getSspId(),Contstants.CLAIMSSUITSUBTITUTEGATHERING)!=null){
			return datagridClaimantSubstituteGatheringByPreclrId(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			return datagridPreclearingSubstituteGatheringByPreclrId(gtVo);
		}else if(opinionId(gtVo.getSspId(),Contstants.SELLSUITSUBTITUTEGATHERING)!=null){
			return datagridSellSubstituteGatheringByPreclrId(gtVo);
		}
		return null;
	}
	/**查找代付维修收款相关数据*/
	private GatheringVo datagridPreclearingSubstituteGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_preClearing", Contstants.SERVICESUBTITUTEGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ARREARS,fcar.car_license,fcar.car_vin, fcar.car_motor_id,");
		sb.append(" bcb.cbrd_name,bct.ctype_name,bcc.color_name, fc.custom_name,fc.custom_tel1,");
		sb.append(" fc.custom_addr,rt.rept_name,a.dataValue,fcar.car_id,fpc.preclr_id");
		sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,substitute_balance sb,");
		sb.append(" frt_car fcar,substitute_suit_payment ssp,bas_car_type bct,");
		sb.append(" bas_car_color bcc,bas_car_brand bcb,reptype rt,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc, bas_parentdictionary bp"); 
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a"); 
		sb.append(" WHERE fpc.reception_id=fr.reception_id AND ssp.custom_id=fc.custom_id");
		sb.append(" AND fcar.car_id=fr.car_id AND sb.STRIKE_ID=fpc.PRECLR_ID and sb.SSP_ID=ssp.SSP_ID");
		sb.append(" AND bct.ctype_id=fcar.ctype_id AND bct.cbrd_id=bcb.cbrd_id");
		sb.append(" AND fcar.color_id=bcc.color AND rt.rept_id=fr.rept_id");
		sb.append(" AND a.dataKey=fpc.preclr_inoice_type"); 
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setSspId(gtVo.getSspId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCarLicense(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCarVinName(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCarMotorIdName(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				gathering.setCbrdName(obj[4].toString());
			if(obj[5]!=null&&obj[5].toString().length()>0)
				gathering.setCtypeName(obj[5].toString());
			if(obj[6]!=null&&obj[6].toString().length()>0)
				gathering.setColorName(obj[6].toString());
			if(obj[7]!=null&&obj[7].toString().length()>0)
				gathering.setCustomName(obj[7].toString());
			if(obj[8]!=null&&obj[8].toString().length()>0)
				gathering.setCustomTel(obj[8].toString());
			if(obj[9]!=null&&obj[9].toString().length()>0)
				gathering.setCustomAddr(obj[9].toString());
			if(obj[10]!=null&&obj[10].toString().length()>0)
				gathering.setReptName(obj[10].toString());
			if(obj[11]!=null&&obj[11].toString().length()>0)
				gathering.setInvoiceTypeName(obj[11].toString());
			if(obj[12]!=null&&obj[12].toString().length()>0)
				gathering.setCarId(obj[12].toString());
			if(obj[13]!=null&&obj[13].toString().length()>0)
				gathering.setPreclrId(obj[13].toString());
		}
//		StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
//		sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
//		sb1.append(" WHERE bvi.car_id=fcar.car_id AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
//		sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gtVo.getCustomId()+"'");
//		List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
//		if(list1!=null&&list1.size()>0){
//			Object[] obj=list1.get(0);
//			if(obj[0]!=null&&obj[0].toString().length()>0)
//				gathering.setMemberId(obj[0].toString());
//			if(obj[1]!=null&&obj[1].toString().length()>0)
//				gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
//			else
//				gathering.setSumIntegral(0);
//			if(obj[2]!=null&&obj[2].toString().length()>0)
//				gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
//			else
//				gathering.setUseIntegral(0);
//			if(obj[3]!=null&&obj[3].toString().length()>0)
//				gathering.setMemberClass(obj[3].toString());
//			if(obj[4]!=null&&obj[4].toString().length()>0)
//				gathering.setMemberGrade(obj[4].toString());
//		}
//		StRecharge sr=stRechargeDao.get("from StRecharge sr where sr.frtCar.carId='"+gathering.getCarId()+"'");
//			if(sr!=null){
//				if(sr.getRechargeId()!=null&&sr.getRechargeId().toString().length()>0){
//					gathering.setHiveId(sr.getRechargeId());
//				}
//				if(sr.getSurplusMoney()!=null&&sr.getSurplusMoney().toString().length()>0)
//					gathering.setHiveBalance(sr.getSurplusMoney());
//				else
//					gathering.setHiveBalance(0.00d);
//			}else{
//				gathering.setHiveBalance(0.00d);
//			}
		gathering.setHiveUse(0.00d);
		gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_preClearing", Contstants.SERVICESUBTITUTEGATHERING));
		return gathering;
	}
	/**查找代付索赔收款相关数据*/
	private GatheringVo datagridClaimantSubstituteGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_claimant", Contstants.CLAIMSSUBTITUTEGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ARREARS,brc.relcamp_name, brc.relcamp_tel1,brc.relcamp_addr,a.dataValue,fcm.claimantm_id");
		sb.append(" FROM fin_claimant_main fcm,frt_pre_clearing fpc,frt_reception fr,substitute_balance sb,");
		sb.append(" bas_relation_campany brc,substitute_suit_payment ssp,");
	 	sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc, bas_parentdictionary bp");
	 	sb.append("  WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"') a");
	 	sb.append(" WHERE  fcm.preclr_id=fpc.preclr_id AND fpc.reception_id=fr.reception_id  and sb.SSP_ID=ssp.SSP_ID");
	 	sb.append(" AND sb.STRIKE_ID=fcm.CLAIMANTM_ID AND brc.relcamp_id=ssp.custom_id");  
	 	sb.append(" AND a.dataKey=fcm.claimantm_invoice_type");
	 	if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setSspId(gtVo.getSspId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCustomName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCustomTel(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCustomAddr(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				gathering.setInvoiceTypeName(obj[4].toString());
			if(obj[5]!=null&&obj[5].toString().length()>0)
				gathering.setPreclrId(obj[5].toString());
		}  
		gathering.setHiveUse(0.00d);
		gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_claimant", Contstants.CLAIMSSUBTITUTEGATHERING));
		return gathering;
	}
	/**查找代付销售收款相关数据
	 * @throws Exception */
	private GatheringVo datagridSellSubstituteGatheringByPreclrId(GatheringVo gtVo) throws Exception{
		GatheringVo gathering=null;
		if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
			gathering=new GatheringVo();
			gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_sell", Contstants.SELLSUBTITUTEGATHERING));
			return gathering;
		}
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ARREARS,fc.custom_name,fc.custom_tel1,fc.custom_addr,sspm.preclr_id");
		sb.append(" FROM st_sell_preclr_main sspm,frt_custom fc,substitute_balance sb,substitute_suit_payment ssp"); 
		sb.append(" WHERE ssp.custom_id=fc.custom_id AND sb.STRIKE_ID=sspm.PRECLR_ID and sb.SSP_ID=ssp.SSP_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> list=frtPreClearingDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			Object[] obj=list.get(0);
			gathering.setSspId(gtVo.getSspId());
			if(obj[0]!=null&&obj[0].toString().length()>0)
				gathering.setPreclrAmount(Double.parseDouble(obj[0].toString()));
			else
				gathering.setPreclrAmount(0.00d);
			if(obj[1]!=null&&obj[1].toString().length()>0)
				gathering.setCustomName(obj[1].toString());
			if(obj[2]!=null&&obj[2].toString().length()>0)
				gathering.setCustomTel(obj[2].toString());
			if(obj[3]!=null&&obj[3].toString().length()>0)
				gathering.setCustomAddr(obj[3].toString());
			if(obj[4]!=null&&obj[4].toString().length()>0)
				gathering.setPreclrId(obj[4].toString());
		}
//		StringBuffer sb1=new StringBuffer("SELECT bvi.vip_id,bvi.VIP_TOTAL_INTEGRAL,bvi.VIP_INTEGRAL,bvg.VIP_GRUOP_NAME,bvl.VIP_LEVEL_NAME");
//		sb1.append(" FROM bas_vip_infor bvi,frt_car fcar,bas_vip_gruop bvg,bas_vip_level bvl"); 
//		sb1.append(" WHERE bvi.car_id=fcar.car_id AND bvi.VIP_GRUOP_ID=bvg.VIP_GRUOP_ID");
//		sb1.append(" AND bvi.VIP_LEVEL_ID=bvl.VIP_LEVEL_ID AND fcar.CUSTOM_ID='"+gtVo.getCustomId()+"'");
//		List<Object[]> list1=frtPreClearingDao.createSQLQuery(sb1.toString());
//		if(list1!=null&&list1.size()>0){
//			Object[] obj=list1.get(0);
//			if(obj[0]!=null&&obj[0].toString().length()>0)
//				gathering.setMemberId(obj[0].toString());
//			if(obj[1]!=null&&obj[1].toString().length()>0)
//				gathering.setSumIntegral(Integer.parseInt(obj[1].toString()));
//			else
//				gathering.setSumIntegral(0);
//			if(obj[2]!=null&&obj[2].toString().length()>0)
//				gathering.setUseIntegral(Integer.parseInt(obj[2].toString()));
//			else
//				gathering.setUseIntegral(0);
//			if(obj[3]!=null&&obj[3].toString().length()>0)
//				gathering.setMemberClass(obj[3].toString());
//			if(obj[4]!=null&&obj[4].toString().length()>0)
//				gathering.setMemberGrade(obj[4].toString());
//		}
//		StRecharge sr=stRechargeDao.get("from StRecharge sr where sr.frtCar.carId='"+gathering.getCarId()+"'");
//			if(sr!=null){
//				if(sr.getRechargeId()!=null&&sr.getRechargeId().toString().length()>0){
//					gathering.setHiveId(sr.getRechargeId());
//				}
//				if(sr.getSurplusMoney()!=null&&sr.getSurplusMoney().toString().length()>0)
//					gathering.setHiveBalance(sr.getSurplusMoney());
//				else
//					gathering.setHiveBalance(0.00d);
//			}else{
//				gathering.setHiveBalance(0.00d);
//			}
		gathering.setHiveUse(0.00d);
		gathering.setGatheringId(CreateID.createId("SubstitutePaymentDetail_sell", Contstants.SELLSUBTITUTEGATHERING));
		return gathering;
	}
	
	/**
	 * 查找代付收款记录
	 * */
	
	public Datagrid datagridSubstituteGatheringOld(GatheringVo gtVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT spd.SPD_ID,spd.SPD_DATE,spd.SPD_PAYABLE,spd.SPD_PAYMENT_MONEY,");
		sb.append(" spd.SPD_ARREARS,a.dataValue,bs.STF_NAME,spd.SPD_REMARK");
		sb.append(" FROM substitute_suit_payment ssp,substitute_payment_detail spd,bas_stuff bs,");
		sb.append(" (SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"' )a"); 
		sb.append(" WHERE spd.SSP_ID=ssp.SSP_ID AND spd.STF_ID=bs.STF_ID"); 
		sb.append(" AND a.dataKey=spd.PAYMENT_ID");
		if(gtVo.getSspId()!=null&&gtVo.getSspId().length()>0){				
			sb.append(" AND ssp.SSP_ID='"+gtVo.getSspId()+"'");				
		}else{
			sb.append(" AND ssp.SSP_ID='-1'");
		}
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sb.toString(),gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setGatheringId(objects[0].toString());
				gatheringVo.setPaymentTime(objects[1].toString());
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setGatheringWise(objects[5].toString());
				gatheringVo.setStfName(objects[6].toString());
				gatheringVo.setGatheringRemark(objects[7].toString());
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 增加批量代付索赔收款
	 * */
	@Log(moduleName="前台管理",content="新增批量代付索赔收款",opertype="新增")
	
	public Msg saveClaimantBatchSubstituteGathering(GatheringVo gtVo,String type,Boolean flag)
			throws Exception {
		Msg msg=new Msg();
		try {
			if(flag==true){
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE,"SubstituteSuitPayment_batch_sub_b_claimant",Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT);
			}else{
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE,"SubstituteSuitPayment_batch_sub_claimant",Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT);				
			}
			msg.setMsg("增加批量代付索赔收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加批量代付索赔收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 * 增加代付索赔收款
	 * */
	@Log(moduleName="前台管理",content="新增代付索赔收款",opertype="新增")
	
	public Msg saveClaimantSubstituteGathering(GatheringVo gtVo)
			throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                                 
			SubstituteSuitPayment ssp=substituteSuitPaymentDao.get("from SubstituteSuitPayment ssp where ssp.sspId='"+gtVo.getSspId()+"'");
			if(ssp.getSspCumulative()==null)                                                                                                               
				ssp.setSspCumulative(0.00d);                                                                                                               
			ssp.setSspCumulative(ssp.getSspCumulative()+gtVo.getPreclrRealAmount());                                                                                                                                                          
			SubstitutePaymentDetail spd=new SubstitutePaymentDetail();                                                                                         
			spd.setSpdId(gtVo.getGatheringId());                                                                                                          
			spd.setSpdDate(gtVo.getGatheringTime());                                                                                                      
			spd.setSpdPayable(gtVo.getPreclrAmount());                                                                                                    
			spd.setSpdPaymentMoney(gtVo.getPreclrRealAmount());                                                                                           
			spd.setPaymentId(gtVo.getGatheringWise());                                                                                                    
			spd.setSpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());                                                                         
			spd.setStfId(gtVo.getStfId());                                                                                                                
			spd.setSpdUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			if(spd.getSpdArrears()==0.00){
				spd.setSpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			spd.setSpdRemark(gtVo.getGatheringRemark());                                                                                                
			spd.setSspId(ssp.getSspId());                                                                                                                 
			substitutePaymentDetailDao.save(spd);                                                                                                           
			ssp.setSspArrears(spd.getSpdArrears());  
			if(ssp.getSspArrears()==0.00){
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);				
			}
			substituteSuitPaymentDao.merge(ssp);                                                                                                      
			/**************/                                                                                                                              
			if(gtVo.getHiveId()!=null&&gtVo.getHiveId().toString().length()>0){                                                                           
				StRecharge sr=stRechargeDao.get(StRecharge.class, gtVo.getHiveId());                                                                      
				sr.setSurplusMoney(sr.getSurplusMoney()-gtVo.getHiveUse());                                                                               
				stRechargeDao.merge(sr);                                                                                                                  
				BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());                                                          
				StSellPercharge sspe=new StSellPercharge();
				sspe.setStRecharge(sr);                                                                                                                    
				sspe.setBasStuff(bs);   
				sspe.setBasChilddictionary(basChilddictionaryDao.get("from BasChilddictionary bc where bc.dataKey='"+gtVo.getGatheringWise()+"'"));
				sspe.setPerchargeDate(new Date());                                                                                                      
				sspe.setCurPercharge(gtVo.getHiveUse());                                                                                                                 
				sspe.setPreclrInoiceType(gtVo.getPreclrInoiceType());
				sspe.setPreclrNo(gtVo.getPreclrNo());
				if(!Contstants.RECEIPT_TAG.OTHERTAX.equals(gtVo.getPreclrInoiceType())){					
					sspe.setPreclrInvoiceTime(new Date());
				}
				sspe.setChargeRemark(gtVo.getGatheringRemark());
				sspe.setPerchargeId(CreateID.createId("StSellPercharge", Contstants.SERVICEBEFOREGATHERING));
				sspe.setFlag(0);
				stSellPerchargeDao.save(sspe);							                                                                                              
			}        
			SubstituteBalance sb=substituteBalanceDao.get("from SubstituteBalance sb where sb.sspId='"+ssp.getSspId()+"'");
			claimantSubstituteGatheringChangeState(sb.getStrikeId());
			msg.setMsg("增加代付索赔收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付索赔收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;                         
	}
	/**判断代付索赔收款是否付清，更改收款状态（是否收清）*/
	private void claimantSubstituteGatheringChangeState(String preclrId) throws Exception{
		FinClaimsReceivables fcr=finClaimsReceivablesDao.get("from FinClaimsReceivables fcr where fcr.claimantmId='"+preclrId+"'");
		if(fcr.getCrArrears()==0.00){
			StringBuffer sb=new StringBuffer("SELECT ssp FROM SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" WHERE sb.sspId=ssp.sspId AND sb.strikeId='"+preclrId+"'");
			Boolean flag=true;
			List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb.toString());
			if(list!=null&&list.size()>0)
				for (SubstituteSuitPayment ssp : list) {
					if(!(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.FINISHED))){
						flag=false;
						break;
					}
				}
			if(flag==true){
				fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finClaimsReceivablesDao.merge(fcr);
			}
		}else{
			if(fcr.getCrUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNPAYMENT)){
				fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				finClaimsReceivablesDao.merge(fcr);
			}
		}
	}	
	/**
	 * 增加批量代付维修收款
	 * */
	@Log(moduleName="前台管理",content="新增批量代付维修收款",opertype="新增")
	
	public Msg savePreclearingBatchSubstituteGathering(GatheringVo gtVo,String type,Boolean flag)
			throws Exception {
		Msg msg=new Msg();
		try {
			if(flag==true){
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE,"SubstituteSuitPayment_batch_sub_b_preClearing",Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT);
			}else{				
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE,"SubstituteSuitPayment_batch_sub_preClearing",Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT);
			}
			msg.setMsg("增加批量代付维修收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加批量代付维修收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**增加批量代付收款*/
	private void saveSubData(GatheringVo gtVo,String toChangeType,Short type,String tableName,String tagId) throws Exception{
		SubstituteBalance sb=null;
			SubstituteSuitPayment ssp=null;
			if(gtVo.getSspId()==null||gtVo.getSspId().length()==0){
				ssp=new SubstituteSuitPayment();
				ssp.setSspId(CreateID.createId(tableName,tagId));
				gtVo.setTempPreclrIds(gtVo.getTempPreclrIds().replaceAll("\r\n", ","));
				String [] tempStr=gtVo.getTempPreclrIds().split(",");
				String dataStr=gtVo.getTempPreclrIds();
				dataStr=dataStr.replaceAll(",", "','");
				dataStr="'"+dataStr+"'";				
				for (String string : tempStr) {
					sb=new SubstituteBalance();
					sb.setStrikeId(string);
					sb.setSspId(ssp.getSspId());
					substituteBalanceDao.save(sb);
				}
				frtPreClearingDao.updateBatch("update substitute_suit_payment ssp set ssp.SSP_BATCH_TAG="+
									Contstants.OPINIONFINISHED_TAG.FINISHED+" where ssp.SSP_ID in("+dataStr+")");
				ssp.setSspReceivables(gtVo.getPreclrAmount());
				ssp.setSspCumulative(gtVo.getPreclrRealAmount());
				ssp.setSspArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspBatch(Contstants.OPINIONFINISHED_TAG.FINISHED);
				ssp.setSspBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				ssp.setSspType(type);
				ssp.setSspDate(new Date());
				ssp.setBalanceId(null);
				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
				ssp.setCustomId(gtVo.getCustomId());
				substituteSuitPaymentDao.save(ssp);
				gtVo.setSspId(ssp.getSspId());
			}else{
				ssp=substituteSuitPaymentDao.get(SubstituteSuitPayment.class,gtVo.getSspId());
				if(ssp.getSspCumulative()==null)
					ssp.setSspCumulative(0.00d);
				ssp.setSspCumulative(ssp.getSspCumulative()+gtVo.getPreclrRealAmount());
				ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				if(gtVo.getDifferenceBalance()!=null)
					ssp.setSspDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			SubstituteBatchBalanceDetail sbbd=new SubstituteBatchBalanceDetail(); 
			sbbd.setSbbdId(gtVo.getGatheringId());
			sbbd.setSspId(gtVo.getSspId());
			sbbd.setStfId(gtVo.getStfId());
			sbbd.setPaymentId(gtVo.getGatheringWise());
			sbbd.setSbbdPayable(gtVo.getPreclrAmount());
			sbbd.setSbbdPaymentMoney(gtVo.getPreclrRealAmount());
			if(gtVo.getPreclrRealAmount()==null)
				gtVo.setPreclrRealAmount(0.00d);
			sbbd.setSbbdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());
			sbbd.setSbbdDate(gtVo.getGatheringTime());
			sbbd.setSbbdDifferenceBalance(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			if(gtVo.getDifferenceBalance()!=null)
				sbbd.setSbbdDifferenceBalance(Contstants.OPINIONFINISHED_TAG.FINISHED);
			sbbd.setSbbdUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
			if(sbbd.getSbbdArrears()==0.00){
				sbbd.setSbbdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}
			sbbd.setSbbdDate(gtVo.getGatheringTime());
			sbbd.setSbbdType(type);
			sbbd.setCustomId(gtVo.getCustomId());
			sbbd.setSbbdRemark(gtVo.getGatheringRemark());
			substituteBatchBalanceDetailDao.save(sbbd);
			ssp.setSspArrears(sbbd.getSbbdArrears());
			Boolean tag=false;
			if(ssp.getSspDifferenceBalance()==Contstants.OPINIONFINISHED_TAG.FINISHED){
				tag=true;
			}else if(ssp.getSspArrears().equals(0.00d)){
				tag=true;
			}
			if(tag==true){
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				changetoBatchUnfinished(ssp.getSspId());
				Boolean flag=false;
				if(toChangeType.equals(Contstants.SERVICESUITSUBTITUTEGATHERING)){
					flag=true;
				}else if(toChangeType.equals(Contstants.CLAIMSSUITSUBTITUTEGATHERING)){
					flag=true;
				}else if(toChangeType.equals(Contstants.SELLSUITSUBTITUTEGATHERING)){
					flag=true;
				}else if(toChangeType.equals(Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT)){
					flag=true;
				}else if(toChangeType.equals(Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT)){
					flag=true;
				}else if(toChangeType.equals(Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT)){
					flag=true;
				}
				if(flag==true){
					changeSubstituteGatheringUnfinished(ssp.getSspId(),toChangeType);
				}else{
					changeBatchSubstituteGatheringUnfinished(ssp.getSspId(),type);					
				}
			}
			substituteSuitPaymentDao.merge(ssp);
	}	
	/**更改转批量代付收款的代付批量应收款付清状态*/
	private void changetoBatchUnfinished(String sspId) throws Exception{
		StringBuffer sb=new StringBuffer("select ssp from SubstituteSuitPayment ssp where ssp.sspId in");
		sb.append(" (select sb.strikeId from SubstituteSuitPayment ssp,SubstituteBalance sb");
		sb.append(" where sb.sspId=ssp.sspId and ssp.sspId='"+sspId+"')");
		List<SubstituteSuitPayment> sspList=substituteSuitPaymentDao.find(sb.toString());
		for (SubstituteSuitPayment ssp : sspList) {
			if(ssp.getSspBatchTag().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				substituteSuitPaymentDao.merge(ssp);
			}
		}
	}
	/**更改收清状态*/
	private Boolean changeSubstituteGatheringUnfinished(String sspId,String type)throws Exception{
		StringBuffer sb=new StringBuffer();
		if(type.equals(Contstants.SERVICESUITSUBTITUTEGATHERING)||type.equals(Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT)){
			sb.append("select fmr from SubstituteSuitPayment ssp,FinMaintenanceReceivables fmr,FinCollectionSchedule fcs");
			sb.append(" where fcs.mrId=fmr.mrId and fcs.fcsId=ssp.balanceId and ssp.sspId in");
			sb.append(" (select sb.strikeId from SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" where sb.sspId=ssp.sspId and ssp.sspId='"+sspId+"')");
			FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get(sb.toString());
			if(fmr==null){
				return true;
			}
			if(fmr.getMrArrears()==0.00){
				Boolean flag=true;
				StringBuffer sb1=new StringBuffer("select distinct ssp from SubstituteSuitPayment ssp where ssp.balanceId IN");
				sb1.append("(select fcs.fcsId from FinMaintenanceReceivables fmr,FinCollectionSchedule fcs where fmr.mrId=fcs.mrId and fmr.mrId='"+fmr.getMrId()+"')");
				List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb1.toString());
				if(list!=null&&list.size()>0)
					for (SubstituteSuitPayment ssp : list) {
						if(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)&&
								ssp.getSspDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
							flag=false;
							break;
						}
					}
				if(flag==true){
					fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
					finMaintenanceReceivablesDao.merge(fmr);
				}
			}
		}else if(type.equals(Contstants.CLAIMSSUITSUBTITUTEGATHERING)||type.equals(Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT)){
			sb.append("select fcr from SubstituteSuitPayment ssp,FinClaimsReceivables fcr,FinCounterclaimSchedule fccs");
			sb.append(" where fccs.crId=fcr.crId and fccs.fccsId=ssp.balanceId and ssp.sspId in");
			sb.append(" (select sb.strikeId from SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" where sb.sspId=ssp.sspId and ssp.sspId='"+sspId+"')");
			FinClaimsReceivables fcr=finClaimsReceivablesDao.get(sb.toString());
			if(fcr==null){
				return true;
			}
			if(fcr.getCrArrears().equals(0.00)){
				Boolean flag=true;
				StringBuffer sb1=new StringBuffer("select distinct ssp from SubstituteSuitPayment ssp where ssp.balanceId IN");
				sb1.append("(select fccs.fccsId from FinClaimsReceivables fcr,FinCounterclaimSchedule fccs where fcr.crId=fccs.crId and fcr.crId='"+fcr.getCrId()+"')");
				List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb1.toString());
				if(list!=null&&list.size()>0)
					for (SubstituteSuitPayment ssp : list) {
						if(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)&&
								ssp.getSspDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
							flag=false;
							break;
						}
					}
				if(flag==true){
					fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
					finClaimsReceivablesDao.merge(fcr);
				}
			}
		}else if(type.equals(Contstants.SELLSUITSUBTITUTEGATHERING)||type.equals(Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT)){
			sb.append("select charge from SubstituteSuitPayment ssp,StSellCharge charge,StSellChargeitem ssc");
			sb.append(" where ssc.stSellCharge.chargeId=charge.chargeId and ssc.sscId=ssp.balanceId and ssp.sspId in");
			sb.append(" (select sb.strikeId from SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" where sb.sspId=ssp.sspId and ssp.sspId='"+sspId+"')");
			StSellCharge charge=stSellChargeDao.get(sb.toString());
			if(charge==null){
				return true;
			}
			if(charge.getPaidBalance().equals(0.00)){
				Boolean flag=true;
				StringBuffer sb1=new StringBuffer("select distinct ssp from SubstituteSuitPayment ssp where ssp.balanceId IN");
				sb1.append("(select ssc.sscId from StSellCharge charge,StSellChargeitem ssc where charge.chargeId=ssc.stSellCharge.chargeId and charge.chargeId='"+charge.getChargeId()+"')");
				List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb1.toString());
				if(list!=null&&list.size()>0)
					for (SubstituteSuitPayment ssp : list) {
						if(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)&&
								ssp.getSspDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
							flag=false;
							break;
						}
					}
				if(flag==true){
					charge.setChargeUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
					stSellChargeDao.merge(charge);
				}
			}
		}else{
			return true;
		}
		return false;
	}
	/**更改收清状态*/
	private void changeBatchSubstituteGatheringUnfinished(String sspId,Short type) throws Exception{
		StringBuffer sb1=new StringBuffer("select distinct bbg from BatchBalanceGather bbg,BatchPaymentDetail bpd");
		sb1.append(" where bpd.bbgId=bbg.bbgId and bpd.bpdId in");
		sb1.append(" (select ssp.balanceId from SubstituteSuitPayment ssp where ssp.sspId in");
		sb1.append(" (select sb.strikeId from SubstituteSuitPayment ssp,SubstituteBalance sb");
		sb1.append(" where sb.sspId=ssp.sspId and ssp.sspId='"+sspId+"'))");
		List<BatchBalanceGather> bbgList=batchBalanceGatherDao.find(sb1.toString());
		Boolean flag=true;
		Boolean tag=false;
		for (BatchBalanceGather bbg : bbgList) {
			if(bbg.getBbgDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
				tag=true;
			}else if(bbg.getBbgArrears()==0.00){
				tag=true;
			}			
			if(tag==true){
				StringBuffer sb=new StringBuffer("select distinct ssp from SubstituteSuitPayment ssp where ssp.balanceId IN");
				sb.append("(select bpd.bpdId from BatchBalanceGather bbg,BatchPaymentDetail bpd where bpd.bbgId=bbg.bbgId and bbg.bbgId='"+bbg.getBbgId()+"')");
				List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb.toString());
				if(list!=null&&list.size()>0)
					for (SubstituteSuitPayment ssp : list) {
						if(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)&&
								ssp.getSspDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.UNFINISHED)){
							flag=false;
							break;
						}
					}
				if(flag==true){
					bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
					 changeFmrUnFinished(bbg.getBbgId(),type);
					batchBalanceGatherDao.merge(bbg);
				}
			}
			tag=false;
			flag=true;
		}
	}
	/**
	 * 增加代付维修收款
	 * */
	@Log(moduleName="前台管理",content="新增代付维修收款",opertype="新增")
	
	public Msg savePreclearingSubstituteGathering(GatheringVo gtVo)
			throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                            
			SubstituteSuitPayment ssp=substituteSuitPaymentDao.get("from SubstituteSuitPayment ssp where ssp.sspId='"+gtVo.getSspId()+"'");
			if(ssp.getSspCumulative()==null)                                                                                                               
				ssp.setSspCumulative(0.00d);                                                                                                               
			ssp.setSspCumulative(ssp.getSspCumulative()+gtVo.getPreclrRealAmount());                                                                                                                                                       
			SubstitutePaymentDetail spd=new SubstitutePaymentDetail();                                                                                         
			spd.setSpdId(gtVo.getGatheringId());                                                                                                          
			spd.setSpdDate(gtVo.getGatheringTime());                                                                                                      
			spd.setSpdPayable(gtVo.getPreclrAmount());                                                                                                    
			spd.setSpdPaymentMoney(gtVo.getPreclrRealAmount());                                                                                           
			spd.setPaymentId(gtVo.getGatheringWise());                                                                                                    
			spd.setSpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());                                                                         
			spd.setStfId(gtVo.getStfId());                                                                                                                
			if(spd.getSpdArrears()==0.00){
				spd.setSpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}                                                                                                    
			spd.setSpdRemark(gtVo.getGatheringRemark());                                                                                                
			spd.setSspId(ssp.getSspId());                                                                                                                 
			substitutePaymentDetailDao.save(spd);                                                                                                           
			ssp.setSspArrears(spd.getSpdArrears());
			if(ssp.getSspArrears()==0.00){
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);				
			}
			substituteSuitPaymentDao.merge(ssp);                                                                                                      
			/**************/                                                                                                                              
			if(gtVo.getHiveId()!=null&&gtVo.getHiveId().toString().length()>0){                                                                           
				StRecharge sr=stRechargeDao.get(StRecharge.class, gtVo.getHiveId());                                                                      
				sr.setSurplusMoney(sr.getSurplusMoney()-gtVo.getHiveUse());                                                                               
				stRechargeDao.merge(sr);                                                                                                                  
				BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());                                                          
				StSellPercharge sspe=new StSellPercharge();
				sspe.setStRecharge(sr);                                                                                                                    
				sspe.setBasStuff(bs);   
				sspe.setBasChilddictionary(basChilddictionaryDao.get("from BasChilddictionary bc where bc.dataKey='"+gtVo.getGatheringWise()+"'"));
				sspe.setPerchargeDate(new Date());                                                                                                      
				sspe.setCurPercharge(gtVo.getHiveUse());                                                                                                                 
				sspe.setPreclrInoiceType(gtVo.getPreclrInoiceType());
				sspe.setPreclrNo(gtVo.getPreclrNo());
				if(!Contstants.RECEIPT_TAG.OTHERTAX.equals(gtVo.getPreclrInoiceType())){					
					sspe.setPreclrInvoiceTime(new Date());
				}
				sspe.setChargeRemark(gtVo.getGatheringRemark());
				sspe.setPerchargeId(CreateID.createId("StSellPercharge", Contstants.SERVICEBEFOREGATHERING));
				sspe.setFlag(0);
				stSellPerchargeDao.save(sspe);							                                                                                              
			} 
			SubstituteBalance sb=substituteBalanceDao.get("from SubstituteBalance sb where sb.sspId='"+ssp.getSspId()+"'");
			preclearingSubstituteGatheringChangeState(sb.getStrikeId());
			msg.setMsg("增加代付维修收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付维修收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;                                                                                                                                       
	}
	/**判断代付维修收款是否付清，更改收款状态（是否收清）*/
	private void preclearingSubstituteGatheringChangeState(String preclrId) throws Exception{
		FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get("from FinMaintenanceReceivables fmr where fmr.preclrId='"+preclrId+"'");
		if(fmr.getMrArrears()==0.00){
			StringBuffer sb=new StringBuffer("SELECT ssp FROM SubstituteSuitPayment ssp,SubstituteBalance sb");
			sb.append(" WHERE sb.sspId=ssp.sspId AND sb.strikeId='"+preclrId+"'");
			Boolean flag=true;
			List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb.toString());
			if(list!=null&&list.size()>0)
				for (SubstituteSuitPayment ssp : list) {
					if(!(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.FINISHED))){
						flag=false;
						break;
					}
				}
			if(flag==true){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				finMaintenanceReceivablesDao.merge(fmr);
			}
		}else{
			if(fmr.getMrUnFinished().equals(Contstants.OPINIONFINISHED_TAG.UNPAYMENT)){
				fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
				finMaintenanceReceivablesDao.merge(fmr);				
			}
		}
	}	
	/**
	 * 增加批量代付销售收款
	 * */
	@Log(moduleName="前台管理",content="新增批量代付销售收款",opertype="新增")
	
	public Msg saveSellBatchSubstituteGathering(GatheringVo gtVo,String type,Boolean flag)
			throws Exception {
		Msg msg=new Msg();
		try {
			if(flag==true){
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE,"SubstituteSuitPayment_batch_sub_b_sell",Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT);
			}else{
				saveSubData(gtVo,type,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE,"SubstituteSuitPayment_batch_sub_sell",Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT);				
			}
			msg.setMsg("增加批量代付销售收款成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("增加批量代付销售收款失败！");
			msg.setSuccess(false);
			e.printStackTrace();
		}
		return msg;
	}
	/**
	 *增加代付销售收款 
	 * */
	@Log(moduleName="前台管理",content="新增代付销售收款",opertype="新增")
	
	public Msg saveSellSubstituteGathering(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                            
			SubstituteSuitPayment ssp=substituteSuitPaymentDao.get("from SubstituteSuitPayment ssp where ssp.sspId='"+gtVo.getSspId()+"'");
			if(ssp.getSspCumulative()==null)                                                                                                               
				ssp.setSspCumulative(0.00d);                                                                                                               
			ssp.setSspCumulative(ssp.getSspCumulative()+gtVo.getPreclrRealAmount());                                                                                                                                                       
			SubstitutePaymentDetail spd=new SubstitutePaymentDetail();                                                                                         
			spd.setSpdId(gtVo.getGatheringId());                                                                                                          
			spd.setSpdDate(gtVo.getGatheringTime());                                                                                                      
			spd.setSpdPayable(gtVo.getPreclrAmount());                                                                                                    
			spd.setSpdPaymentMoney(gtVo.getPreclrRealAmount());                                                                                           
			spd.setPaymentId(gtVo.getGatheringWise());                                                                                                    
			spd.setSpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());                                                                         
			spd.setStfId(gtVo.getStfId());                                                                                                                
			if(spd.getSpdArrears()==0.00){
				spd.setSpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
			}                                                                                                    
			spd.setSpdRemark(gtVo.getGatheringRemark());                                                                                                
			spd.setSspId(ssp.getSspId());                                                                                                                 
			substitutePaymentDetailDao.save(spd);                                                                                                           
			ssp.setSspArrears(spd.getSpdArrears());
			if(ssp.getSspArrears()==0.00){
				ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);				
			}
			substituteSuitPaymentDao.merge(ssp);                                                                                                      
			/**************/                                                                                                                              
			if(gtVo.getHiveId()!=null&&gtVo.getHiveId().toString().length()>0){                                                                           
				StRecharge sr=stRechargeDao.get(StRecharge.class, gtVo.getHiveId());                                                                      
				sr.setSurplusMoney(sr.getSurplusMoney()-gtVo.getHiveUse());                                                                               
				stRechargeDao.merge(sr);                                                                                                                  
				BasStuff bs=basStuffDao.get("from BasStuff bs where bs.stfId="+gtVo.getStfId());                                                          
				StSellPercharge sspe=new StSellPercharge();
				sspe.setStRecharge(sr);                                                                                                                    
				sspe.setBasStuff(bs);   
				sspe.setBasChilddictionary(basChilddictionaryDao.get("from BasChilddictionary bc where bc.dataKey='"+gtVo.getGatheringWise()+"'"));
				sspe.setPerchargeDate(new Date());                                                                                                      
				sspe.setCurPercharge(gtVo.getHiveUse());                                                                                                                 
				sspe.setPreclrInoiceType(gtVo.getPreclrInoiceType());
				sspe.setPreclrNo(gtVo.getPreclrNo());
				if(!Contstants.RECEIPT_TAG.OTHERTAX.equals(gtVo.getPreclrInoiceType())){					
					sspe.setPreclrInvoiceTime(new Date());
				}
				sspe.setChargeRemark(gtVo.getGatheringRemark());
				sspe.setPerchargeId(CreateID.createId("StSellPercharge", Contstants.SERVICEBEFOREGATHERING));
				sspe.setFlag(0);
				stSellPerchargeDao.save(sspe);				                                                                                              
			}        
			SubstituteBalance sb=substituteBalanceDao.get("from SubstituteBalance sb where sb.sspId='"+ssp.getSspId()+"'");
			sellSubstituteGatheringChangeState(sb.getStrikeId());
			msg.setMsg("增加代付销售收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付销售收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;             
	}
	/**
	 * 增加代付批量索赔收款
	 * */
	@Log(moduleName="前台管理",content="新增代付批量索赔收款",opertype="新增")
	
	public Msg saveClaimantSubstituteGatheringAsBatch(GatheringVo gtVo)
			throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                                                                                                             
			saveSubstituteGatheringAsBatch(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
			msg.setMsg("增加代付批量索赔收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付批量索赔收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;
	}
	/**
	 * 增加代付批量维修收款
	 * */
	@Log(moduleName="前台管理",content="新增代付批量维修收款",opertype="新增")
	
	public Msg savePreclearingSubstituteGatheringAsBatch(GatheringVo gtVo)
			throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                                                                                                             
			saveSubstituteGatheringAsBatch(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
			msg.setMsg("增加代付批量维修收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付批量维修收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;         
	}
	/**
	 * 增加代付批量销售收款
	 * */
	@Log(moduleName="前台管理",content="新增代付批量销售收款",opertype="新增")
	
	public Msg saveSellSubstituteGatheringAsBatch(GatheringVo gtVo)
			throws Exception {
		Msg msg=new Msg();                                                                                                                                
		try {                                                                                                                                             
			saveSubstituteGatheringAsBatch(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
			msg.setMsg("增加代付批量销售收款信息成功！");                                                                                                                    
			msg.setSuccess(true);                                                                                                                         
		} catch (Exception e) {                                                                                                                           
			msg.setMsg("增加代付批量销售收款信息失败！");                                                                                                                    
			msg.setSuccess(false);                                                                                                                        
			e.printStackTrace();                                                                                                                          
		}                                                                                                                                                 
		return msg;
	}
	/**增加代付批量收款*/
	private void saveSubstituteGatheringAsBatch(GatheringVo gtVo,Short type) throws Exception{
		SubstituteSuitPayment ssp=substituteSuitPaymentDao.get("from SubstituteSuitPayment ssp where ssp.sspId='"+gtVo.getSspId()+"'");
		if(ssp.getSspCumulative()==null)                                                                                                               
			ssp.setSspCumulative(0.00d);                                                                                                               
		ssp.setSspCumulative(ssp.getSspCumulative()+gtVo.getPreclrRealAmount());                                                                                                                                                        
		SubstitutePaymentDetail spd=new SubstitutePaymentDetail();                                                                                         
		spd.setSpdId(gtVo.getGatheringId());                                                                                                          
		spd.setSpdDate(gtVo.getGatheringTime());                                                                                                      
		spd.setSpdPayable(gtVo.getPreclrAmount());                                                                                                    
		spd.setSpdPaymentMoney(gtVo.getPreclrRealAmount());                                                                                           
		spd.setPaymentId(gtVo.getGatheringWise());                                                                                                    
		spd.setSpdArrears(gtVo.getPreclrAmount()-gtVo.getPreclrRealAmount());  
		if(spd.getSpdArrears()==0.00){
			spd.setSpdUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);                                                                                                    			
		}
		spd.setStfId(gtVo.getStfId());                                                                                                                
		spd.setSpdRemark(gtVo.getGatheringRemark());                                                                                                
		spd.setSspId(ssp.getSspId());                                                                                                                 
		substitutePaymentDetailDao.save(spd);                                                                                                           
		ssp.setSspArrears(spd.getSpdArrears());  
		if(ssp.getSspArrears()==0.00){
			ssp.setSspUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);  
		}
		substituteSuitPaymentDao.merge(ssp);
		changeGatheringUnfinished(gtVo.getSspId(),type);
	}
	/**更改收清状态*/
	private void changeGatheringUnfinished(String sspId,Short type) throws Exception{
		String hql="select bbg from SubstituteSuitPayment ssp,BatchBalanceGather bbg,BatchPaymentDetail bpd where bpd.bbgId=bbg.bbgId and ssp.balanceId=bpd.bpdId and ssp.sspId='"+sspId+"'";
		BatchBalanceGather bbg=batchBalanceGatherDao.get(hql);
		Boolean flag=true;
		Boolean tag=false;
		if(bbg.getBbgDifferenceBalance().equals(Contstants.OPINIONFINISHED_TAG.FINISHED)){
			tag=true;
		}else if(bbg.getBbgArrears()==0.00){
			tag=false;
		}
		if(tag==true){
			StringBuffer sb=new StringBuffer("SELECT ssp FROM SubstituteSuitPayment ssp WHERE ssp.balanceId IN");
			sb.append("(select bpd.bpdId from BatchBalanceGather bbg,BatchPaymentDetail bpd where bpd.bbgId=bbg.bbgId and bbg.bbgId='"+bbg.getBbgId()+"')");
			List<SubstituteSuitPayment> list=substituteSuitPaymentDao.find(sb.toString());
			if(list!=null&&list.size()>0)
				for (SubstituteSuitPayment ssp : list) {
					if(!(ssp.getSspUnFinished().equals(Contstants.OPINIONFINISHED_TAG.FINISHED))){
						flag=false;
						break;
					}
				}
			if(flag==true){
				bbg.setBbgUnFinished(Contstants.OPINIONFINISHED_TAG.FINISHED);
				changeFmrUnFinished(bbg.getBbgId(),type);
				batchBalanceGatherDao.merge(bbg);
			}
		}
	}
	/**
	 * 查找批量代付相关数据
	 * */
	
	public GatheringVo datagridBatchSubstituteGatheringBySspId(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getTag()!=null)
			if(gtVo.getTag()==true){
				if(gtVo.getPreclrIds()!=null&&gtVo.getPreclrIds().length()>0){
					String [] tempArray=gtVo.getPreclrIds().split(",");
					gtVo.setPreclrId(tempArray[0]);
					String temp=gtVo.getPreclrIds().replaceAll(",", "','");
					temp="'"+temp+"'";
					gtVo.setPreclrIds(temp);
					if(opinionId(gtVo.getPreclrId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
					}else if(opinionId(gtVo.getPreclrId(),Contstants.CLAIMSSUITSUBTITUTEGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
					}else if(opinionId(gtVo.getPreclrId(),Contstants.SELLSUITSUBTITUTEGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
					}else if(opinionId(gtVo.getPreclrId(),Contstants.SERVICESUITSUBTITUTEBATCHGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
					}else if(opinionId(gtVo.getPreclrId(),Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
					}else if(opinionId(gtVo.getPreclrId(),Contstants.SELLSUITSUBTITUTEBATCHGATHERING)!=null){
						return datagridBatchSubstituteGatheringBySspIdAsTrue(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
					}
				}			
			}else{
				if(opinionId(gtVo.getSspId(),Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
				}else if(opinionId(gtVo.getSspId(),Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
				}else if(opinionId(gtVo.getSspId(),Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
				}else if(opinionId(gtVo.getSspId(),Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
				}else if(opinionId(gtVo.getSspId(),Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
				}else if(opinionId(gtVo.getSspId(),Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT)!=null){
					return datagridBatchSubstituteGatheringBySspIdAsFalse(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
				}
			}
			return null;
	}
	/**查找代付收款
	 * @throws Exception */
	private GatheringVo datagridBatchSubstituteGatheringBySspIdAsTrue(GatheringVo gtVo,Short type) throws Exception{
		GatheringVo gathering=null;
		String sql="SELECT SUM(ssp.SSP_ARREARS) FROM substitute_suit_payment ssp WHERE ssp.ssp_id in("+gtVo.getPreclrIds()+")";
		List list=frtPreClearingDao.createSQLQuery(sql);
		if(list!=null&&list.size()>0){
			gathering=new GatheringVo();
			gathering.setPreclrAmount(Double.parseDouble(list.get(0).toString()));
		}else{
			return null;
		}
		StringBuffer sb=null;
		if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_preClearing", Contstants.SERVICEBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc,substitute_suit_payment ssp");
			sb.append(" WHERE fc.custom_id=ssp.custom_id AND ssp.SSP_ID='"+gtVo.getPreclrId()+"'");			
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_claimant", Contstants.CLAIMSBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT brc.RELCAMP_ID,brc.RELCAMP_NAME,brc.RELCAMP_TEL1,brc.RELCAMP_ADDR ");
			sb.append(" FROM bas_relation_campany brc,substitute_suit_payment ssp");
			sb.append(" WHERE brc.RELCAMP_ID=ssp.custom_id and ssp.SSP_ID='"+gtVo.getPreclrId()+"'");
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_sell", Contstants.SELLBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc,substitute_suit_payment ssp");
			sb.append(" WHERE fc.custom_id=ssp.custom_id AND ssp.SSP_ID='"+gtVo.getPreclrId()+"'");				
		}
		List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
		if(lt!=null&&lt.size()>0){
			Object[] obj=lt.get(0);
			gathering.setCustomId(obj[0].toString());
			gathering.setCustomName(obj[1].toString());
			gathering.setCustomTel(obj[2].toString());
			gathering.setCustomAddr(obj[3].toString());
		}
		return gathering;
	}
	/**查找批量代付收款相关数据*/
	private GatheringVo datagridBatchSubstituteGatheringBySspIdAsFalse(GatheringVo gtVo,Short type) throws Exception{
		GatheringVo gathering=new GatheringVo();
		StringBuffer sb=null;
		SubstituteSuitPayment ssp=substituteSuitPaymentDao.get(SubstituteSuitPayment.class, gtVo.getSspId());
		gathering.setSspId(gtVo.getSspId());
		gathering.setPreclrAmount(ssp.getSspArrears());
		if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_preClearing", Contstants.SERVICEBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc,substitute_suit_payment ssp");
			sb.append(" WHERE fc.custom_id=ssp.CUSTOM_ID AND ssp.SSP_ID='"+gtVo.getSspId()+"'");			
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_claimant", Contstants.CLAIMSBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT brc.RELCAMP_ID,brc.RELCAMP_NAME,brc.RELCAMP_TEL1,brc.RELCAMP_ADDR ");
			sb.append(" FROM bas_relation_campany brc,substitute_suit_payment ssp");
			sb.append(" WHERE brc.RELCAMP_ID=ssp.CUSTOM_ID AND ssp.SSP_ID='"+gtVo.getSspId()+"'");
		}else if(type.equals(Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE)){
			if(gtVo.getFlag()!=null&&gtVo.getFlag()==true){
				gathering.setGatheringId(CreateID.createId("substituteBatchBalanceDetail_sell", Contstants.SELLBATCHSUBTITUTEGATHERING));
				return gathering;
			}
			sb=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.custom_Tel1,fc.custom_addr ");
			sb.append(" FROM frt_custom fc,substitute_suit_payment ssp");
			sb.append(" WHERE fc.custom_id=ssp.CUSTOM_ID AND ssp.SSP_ID='"+gtVo.getSspId()+"'");			
		}
		List<Object[]> lt=frtPreClearingDao.createSQLQuery(sb.toString());
		if(lt!=null&&lt.size()>0){
			Object[] obj=lt.get(0);
			gathering.setCustomId(obj[0].toString());
			gathering.setCustomName(obj[1].toString());
			gathering.setCustomTel(obj[2].toString());
			gathering.setCustomAddr(obj[3].toString());
		}
		return gathering;
	}
	/**
	 * 查找批量代付收款汇总信息
	 * */
	
	public Datagrid datagridBatchSubstituteGatheringCollect(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllBatchSubstituteGatheringCollect(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantBatchSubstituteGatheringCollect(gtVo,Contstants.BATCHBALANCETYPE_TAG.COUNTERCLAIMBALANCE);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclrearingBatchSubstituteGatheringCollect(gtVo,Contstants.BATCHBALANCETYPE_TAG.SERVICEBALANCE);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellBatchSubstituteGatheringCollect(gtVo,Contstants.BATCHBALANCETYPE_TAG.SELLBALANCE);
		}
		return null;
	}
	/**查找维修批量代付收款汇总信息*/
	private Datagrid datagridPreclrearingBatchSubstituteGatheringCollect(GatheringVo gtVo,Short type)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,fc.CUSTOM_NAME,ssp.SSP_DIFFERENCE_BALANCE");
		sb.append(" FROM substitute_suit_payment ssp,frt_custom fc");
		sb.append(" WHERE fc.CUSTOM_ID=ssp.CUSTOM_ID");
		sb.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND ssp.SSP_BATCH="+Contstants.OPINIONFINISHED_TAG.FINISHED+"");
		sb.append(" AND ssp.SSP_TYPE="+type);
		sb.append(" AND ssp.SSP_ID IN (");
		sb.append(" SELECT a.SSP_ID FROM substitute_suit_payment a WHERE  a.SSP_ID LIKE '"+Contstants.SERVICEBATCHSUBSTITUTEGATHERINGCOLLECT+"%'");
		sb.append(" OR a.SSP_ID LIKE '"+Contstants.SERVICEBATCHSUBSTITUTEBATCHGATHERINGCOLLECT+"%'");
		sb.append(" )");
		return findDatagridBySql(gtVo,sb.toString());
	}
	/**查找索赔批量代付收款汇总信息*/
	private Datagrid datagridClaimantBatchSubstituteGatheringCollect(GatheringVo gtVo,Short type)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,brc.RELCAMP_NAME,ssp.SSP_DIFFERENCE_BALANCE");
		sb.append(" FROM substitute_suit_payment ssp,bas_relation_campany brc");
		sb.append(" WHERE brc.relcamp_id=ssp.CUSTOM_ID");
		sb.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND ssp.SSP_BATCH="+Contstants.OPINIONFINISHED_TAG.FINISHED+"");
		sb.append(" AND ssp.SSP_TYPE="+type);
		sb.append(" AND ssp.SSP_ID IN (");
		sb.append(" SELECT a.SSP_ID FROM substitute_suit_payment a WHERE  a.SSP_ID LIKE '"+Contstants.CLAIMSBATCHSUBSTITUTEGATHERINGCOLLECT+"%'");
		sb.append(" OR a.SSP_ID LIKE '"+Contstants.CLAIMSBATCHSUBSTITUTEBATCHGATHERINGCOLLECT+"%'");
		sb.append(" )");
		return findDatagridBySql(gtVo,sb.toString());
	}
	/**查找销售批量代付收款汇总信息*/
	private Datagrid datagridSellBatchSubstituteGatheringCollect(GatheringVo gtVo,Short type)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT ssp.SSP_ID,ssp.SSP_DATE,ssp.SSP_RECEIVABLES,ssp.SSP_CUMULATIVE,ssp.SSP_ARREARS,fc.CUSTOM_NAME,ssp.SSP_DIFFERENCE_BALANCE");
		sb.append(" FROM substitute_suit_payment ssp,frt_custom fc");
		sb.append(" WHERE fc.CUSTOM_ID=ssp.CUSTOM_ID");
		sb.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb.append(" AND ssp.SSP_BATCH="+Contstants.OPINIONFINISHED_TAG.FINISHED+"");
		sb.append(" AND ssp.SSP_TYPE="+type);
		sb.append(" AND ssp.SSP_ID IN (");
		sb.append(" SELECT a.SSP_ID FROM substitute_suit_payment a WHERE  a.SSP_ID LIKE '"+Contstants.SELLBATCHSUBSTITUTEGATHERINGCOLLECT+"%'");
		sb.append(" OR a.SSP_ID LIKE '"+Contstants.SELLBATCHSUBSTITUTEBATCHGATHERINGCOLLECT+"%'");
		sb.append(" )");
		return findDatagridBySql(gtVo,sb.toString());
	}
	/**查找所有批量代付收款汇总信息*/
	private Datagrid datagridAllBatchSubstituteGatheringCollect(GatheringVo gtVo)throws Exception{
		return findDatagridBySql(gtVo,null);
	}
	private Datagrid findDatagridBySql(GatheringVo gtVo,String sql) throws Exception{
		List<Object[]> rowsList=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows=new ArrayList<GatheringVo>();
		GatheringVo gatheringVo=null;
		if(rowsList!=null&&rowsList.size()>0)
			for (Object[] objects : rowsList) {
				gatheringVo=new GatheringVo();
				gatheringVo.setSspId(objects[0].toString());
				gatheringVo.setCreateTime(MyBeanUtils.getInstance().formatString(objects[1].toString()));
				gatheringVo.setPayableAmount(Double.parseDouble(objects[2].toString()));
				gatheringVo.setPaymentAmount(Double.parseDouble(objects[3].toString()));
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[4].toString()));
				gatheringVo.setCustomName(objects[5].toString());
				gatheringVo.setDifferenceBalance(Short.parseShort(objects[6].toString()));
				rows.add(gatheringVo);
			}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 查找批量代付未收款信息
	 * */
	
	public Datagrid datagridNoBatchSubstituteGathering(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoSubstituteGathering(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoSubstituteGathering(gtVo);
		}
		return null;
	}
	/**
	 * 查找结算单是否已转索赔单
	 * */
	
	public Msg findIsClaims(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		FinClaimantMain fcm=finClaimantMainDao.get(" from FinClaimantMain fcm where fcm.claimantmTag="+Contstants.DELETE_TAG.DELETENO+" and fcm.preclrId='"+gtVo.getPreclrId().trim()+"'");
		if(fcm!=null){
			msg.setSuccess(true);
		}
		return msg;
	}
	/**
	 * 收银转结算
	 * */
	@Log(moduleName="前台管理",content="收银转结算",opertype="更新/转结算")
	
	public Msg modifyTransBalance(GatheringVo gtVo) throws Exception {
		Msg msg=new Msg();
		if(gtVo.getPreclrId()!=null){
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < gtVo.getPreclrId().length(); i++) {
				if(!(gtVo.getPreclrId().charAt(i)>='0'&&gtVo.getPreclrId().charAt(i)<='9'))
					sb.append(gtVo.getPreclrId().charAt(i));
			}
			try {
				if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)||sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)){//维修结算或销售结算
					frtPreClearingDao.updateBatch("update frt_pre_clearing fpc set fpc.PRECLR_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYNO+" where fpc.PRECLR_ID='"+gtVo.getPreclrId()+"'");
					finMaintenanceReceivablesDao.delete(finMaintenanceReceivablesDao.get("from FinMaintenanceReceivables fmr where fmr.preclrId='"+gtVo.getPreclrId()+"'"));
				}else if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID)){//索赔结算
					frtPreClearingDao.updateBatch("update fin_claimant_main fcm set fcm.CLAIMANTM_TO_MONEY="+Contstants.TOMONEY_TAG.TOMONEYNO+" where fcm.CLAIMANTM_ID='"+gtVo.getPreclrId()+"'");
					finClaimsReceivablesDao.delete(finClaimsReceivablesDao.get("from FinClaimsReceivables fcr where fcr.claimantmId='"+gtVo.getPreclrId()+"'"));
				}
				FrtPreClearing fpc = frtPreClearingDao.get(FrtPreClearing.class,gtVo.getPreclrId());
				if(fpc!=null&&!fpc.equals("")){
					if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID)){
					    FrtReception frp=frtReceptionDao.get(FrtReception.class,fpc.getReceptionId());
						if(frp!=null&&!frp.equals("")){
							
							frp.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState11);
							frtReceptionDao.merge(frp);
						}
					}
					if(sb.toString().equals(Contstants.BALANCEIDTYPE_TAG.SELLBALANCEID)){
						StSellOrder sso=stSellOrderDAO.get(StSellOrder.class, fpc.getReceptionId());
						sso.setPreclrState("已结算");
					}
				}
				msg.setSuccess(true);
				msg.setMsg("转结算成功！");
			} catch (Exception e) {
				msg.setMsg("转结算失败！");
			}
		}else
			msg.setMsg("转结算失败！");
		return msg;
	}
	
	/**
	 * 查找维修预收款
	 */
	
	public GatheringVo findReceptionBeforeBalance(GatheringVo gtVo)
			throws Exception {
		StRecharge sr=stRechargeDao.get("select sr from StRecharge sr,FrtCar fc where fc.carId=sr.frtCar.carId and sr.perchargeType='预收款' and fc.carLicense='"
										+gtVo.getCarLicense()+"'");
		GatheringVo gtVos=new GatheringVo();
		if(sr!=null){
			gtVos.setBeforeId(sr.getRechargeId().toString());
			if(sr.getSurplusMoney()==null||sr.getSurplusMoney().toString().length()==0)
				sr.setSurplusMoney(0.00d);
			gtVos.setBeforeMoney(sr.getSurplusMoney());
		}
		return gtVos;
	}
	/**
	 * 查找代付未收款汇总信息
	 * */
	
	public Datagrid datagridNoSubstituteGatheringMain(GatheringVo gtVo)
			throws Exception {
		if(gtVo.getPreclearingClass()==null||gtVo.getPreclearingClass().toString().length()==0)
			gtVo.setPreclearingClass(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSINIT);
		setDefaultGaheringTimeSect(gtVo);
		if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSALL.equals(gtVo.getPreclearingClass())){
			return datagridAllNoSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSCLAIMANT.equals(gtVo.getPreclearingClass())){
			return datagridClaimantNoSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSPRECLEARING.equals(gtVo.getPreclearingClass())){
			return datagridPreclearingNoSubstituteGatheringMain(gtVo);
		}else if(Contstants.PRECLEARINGCLASS_TAG.PRECLEARINGCLASSSELL.equals(gtVo.getPreclearingClass())){
			return datagridSellNoSubstituteGatheringMain(gtVo);
		}
		return null;
	}
	/**查找代付销售未收款汇总记录
	 * @throws Exception */
	private Datagrid datagridSellNoSubstituteGatheringMain(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM st_sell_preclr_main sspm,st_sell_charge ssc,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.PRECLR_ID=ssc.PRECLR_ID  AND sb.STRIKE_ID=sspm.PRECLR_ID");
		sb1.append(" AND ssc.state="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");		
		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+gtVo.getGatheringBeginTime()+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		return datagridNoSubstituteGatheringMain_Common(gtVo,sb1.toString());
	}
	/**查找代付维修未收款汇总记录*/
	private Datagrid datagridPreclearingNoSubstituteGatheringMain(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +""); 
		sb1.append(" and fpc.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		return datagridNoSubstituteGatheringMain_Common(gtVo,sb1.toString());
	}
	/**查找代付索赔未收款汇总记录*/
	private Datagrid datagridClaimantNoSubstituteGatheringMain(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_TEL1,brc.relcamp_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM fin_claimant_main fcm,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE brc.relcamp_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND sb.STRIKE_ID=fcm.claimantm_ID and fcm.enterprise_id="+gtVo.getEnterpriseId());
		sb1.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		sb1.append(" GROUP BY brc.relcamp_ID");
		return datagridNoSubstituteGatheringMain_Common(gtVo,sb1.toString());
	}
	/**查找代付所有未收款汇总记录
	 * @throws Exception */
	private Datagrid datagridAllNoSubstituteGatheringMain(GatheringVo gtVo) throws Exception{
		StringBuffer sb1=new StringBuffer("SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM st_sell_preclr_main sspm,st_sell_charge ssc,frt_custom fc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID"); 
		sb1.append(" AND sspm.PRECLR_ID=ssc.PRECLR_ID  AND sb.STRIKE_ID=sspm.PRECLR_ID");
		sb1.append(" AND ssc.state="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +" ");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SELLSUITSUBTITUTEBATCHGATHERING+"%')");		
		if(opinionId(gtVo.getBalanceId(),Contstants.SERVICESUITSUBTITUTEGATHERING)!=null){
			sb1.append(" AND ssp.SSP_ID like '%"+StringEscapeUtils.escapeSql(gtVo.getBalanceId().trim())+"%'");
		}
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND sspm.preclr_date<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		sb1.append(" UNION");
		sb1.append(" SELECT fc.custom_id,fc.custom_name,fc.CUSTOM_TEL1,fc.CUSTOM_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM frt_pre_clearing fpc,frt_reception fr,frt_custom fc,");
		sb1.append(" frt_car fcar,substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE fc.custom_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND fpc.reception_id=fr.reception_id AND sb.STRIKE_ID=fpc.PRECLR_ID");
		sb1.append(" AND fcar.car_id=fr.car_id AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED +""); 
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.SERVICESUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND fc.custom_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getCarId()!=null&&gtVo.getCarId().length()>0){
			sb1.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(gtVo.getCarId().trim())+"%'");
		}
		if(gtVo.getCarVin()!=null&&gtVo.getCarVin().length()>0){
			sb1.append(" AND fcar.car_vin='"+gtVo.getCarVin()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fpc.preclr_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fpc.preclr_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fpc.preclr_inoice_type='"+gtVo.getInvoiceType()+"'");
		}
		if(gtVo.getLinkMan()!=null&&gtVo.getLinkMan().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_PERSON like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkMan().trim())+"%'");
		}
		if(gtVo.getLinkTel()!=null&&gtVo.getLinkTel().length()>0){
			sb1.append(" AND fcar.CAR_RELATION_TEL1 like '%"+StringEscapeUtils.escapeSql(gtVo.getLinkTel().trim())+"%'");
		}
		sb1.append(" GROUP BY fc.CUSTOM_ID");
		sb1.append(" UNION");
		sb1.append(" SELECT brc.relcamp_id,brc.relcamp_name,brc.relcamp_TEL1,brc.relcamp_ADDR,");
		sb1.append(" SUM(ssp.SSP_RECEIVABLES),SUM(ssp.SSP_CUMULATIVE),");
		sb1.append(" SUM(ssp.SSP_ARREARS) FROM fin_claimant_main fcm,bas_relation_campany brc,");
		sb1.append(" substitute_suit_payment ssp,substitute_balance sb");
		sb1.append(" WHERE brc.relcamp_id=ssp.custom_id  AND sb.SSP_ID=ssp.SSP_ID");
		sb1.append(" AND sb.STRIKE_ID=fcm.claimantm_ID");
		sb1.append(" AND fcm.claimantm_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+"");
		//sb1.append(" AND ssp.SSP_UNFINISHED="+Contstants.OPINIONFINISHED_TAG.UNFINISHED+"");
		sb1.append(" AND ssp.SSP_BATCH_TAG="+Contstants.OPINIONFINISHED_TAG.UNFINISHED);
		sb1.append(" AND ssp.SSP_ID NOT IN(SELECT ssp.SSP_ID from substitute_suit_payment ssp where ssp.SSP_ID LIKE '%"+Contstants.CLAIMSSUITSUBTITUTEBATCHGATHERING+"%')");
		if(gtVo.getCustomId()!=null&&gtVo.getCustomId().length()>0){
			sb1.append(" AND brc.relcamp_id='"+gtVo.getCustomId()+"'");
		}
		if(gtVo.getGatheringBeginTime()!=null&&gtVo.getGatheringBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time>='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringBeginTime())+"'");
		}
		if(gtVo.getGatheringEndTime()!=null&&gtVo.getGatheringEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_time<='"+MyBeanUtils.getInstance().getString(gtVo.getGatheringEndTime())+"'");
		}
		if(gtVo.getInvoiceBeginTime()!=null&&gtVo.getInvoiceBeginTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time>='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceBeginTime())+"'");
		}
		if(gtVo.getInvoiceEndTime()!=null&&gtVo.getInvoiceEndTime().toString().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_time<='"+MyBeanUtils.getInstance().getString(gtVo.getInvoiceEndTime())+"'");
		}
		if(gtVo.getInvoiceNo()!=null&&gtVo.getInvoiceNo().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_no like '%"+StringEscapeUtils.escapeSql(gtVo.getInvoiceNo().trim())+"%'");
		}
		if(gtVo.getInvoiceType()!=null&&gtVo.getInvoiceType().length()>0){
			sb1.append(" AND fcm.claimantm_invoice_type='"+gtVo.getInvoiceType()+"'");
		}
		sb1.append(" GROUP BY brc.relcamp_ID");
		return datagridNoSubstituteGatheringMain_Common(gtVo,sb1.toString());
	}
	private Datagrid datagridNoSubstituteGatheringMain_Common(GatheringVo gtVo,String sql) throws Exception{
		Datagrid dg=new Datagrid();
		List<Object[]> rowsList1=frtPreClearingDao.createSQLQuery(sql,gtVo.getPage(),gtVo.getRows());
		List<GatheringVo> rows1=new ArrayList();
		GatheringVo gatheringVo=null;
		if(rowsList1!=null&&rowsList1.size()>0)
		for (Object[] objects : rowsList1) {
			gatheringVo=new GatheringVo();
			if(objects[0]!=null&&objects[0].toString().length()>0)
				gatheringVo.setCustomId(objects[0].toString());
			if(objects[1]!=null&&objects[1].toString().length()>0)
				gatheringVo.setCustomName(objects[1].toString());
			if(objects[2]!=null&&objects[2].toString().length()>0)
				gatheringVo.setCustomTel(objects[2].toString());
			if(objects[3]!=null&&objects[3].toString().length()>0)
				gatheringVo.setCustomAddr(objects[3].toString());
			if(objects[4]!=null&&objects[4].toString().length()>0)
				gatheringVo.setPreclrAmount(Double.parseDouble(objects[4].toString()));
			else
				gatheringVo.setPreclrAmount(0.00d);
			if(objects[5]!=null&&objects[5].toString().length()>0)
				gatheringVo.setCumulativeAmount(Double.parseDouble(objects[5].toString()));
			else
				gatheringVo.setCumulativeAmount(0.00d);
			if(objects[6]!=null&&objects[6].toString().length()>0)
				gatheringVo.setArrearsAmount(Double.parseDouble(objects[6].toString()));
			else
				gatheringVo.setArrearsAmount(0.00d);
			rows1.add(gatheringVo);
		}
		int total=frtPreClearingDao.getSQLCount(sql, null);
		dg.setRows(rows1);
		dg.setTotal(total);
		return dg;
	}
}
