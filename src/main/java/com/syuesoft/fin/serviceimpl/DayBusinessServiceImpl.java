package com.syuesoft.fin.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.FinMaintenanceReceivablesDao;
import com.syuesoft.fin.service.DayBusinessService;
import com.syuesoft.fin.vo.DayBusinessVo;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.MyBeanUtils;

@Service("dayBusinessService")
public class DayBusinessServiceImpl implements  DayBusinessService
{

    @Autowired
    private FinMaintenanceReceivablesDao finMaintenanceReceivablesDao;
    @Autowired
	private FrtService frtService;
    /**
     * 财务模块 日收款查询 父节点（结算日期信息加载）
     */
    public DatagridAnalyze loadPreclrDate(DayBusinessVo dbVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(dbVo);
    	formatCode(dbVo);
    	if(dbVo.getClassWay()==null||dbVo.getClassWay().length()==0)
    		dbVo.setClassWay("service");
    	dbVo.setClassWay(dbVo.getClassWay().replaceAll(" ", ""));
    	String[] classWay=dbVo.getClassWay().split(",");
    	StringBuffer sb=new StringBuffer("SELECT cc.temp0,COUNT(cc.temp),SUM(temp2) FROM (");
    	if(classWay.length==1){
    		if(classWay[0].equals("service")){
    			addServiceSql(sb,dbVo,false);
    		}else if(classWay[0].equals("claims")){
    			addClaimsSql(sb,dbVo,false);
    		}else if(classWay[0].equals("sell")){
    			addSellSql(sb,dbVo,false);
    		}      		
    	}else if(classWay.length>1){
    		if(classWay[0].equals("service")){
    			addServiceSql(sb,dbVo,false);
    		}else if(classWay[0].equals("claims")){
    			addClaimsSql(sb,dbVo,false);
    		}else if(classWay[0].equals("sell")){
    			addSellSql(sb,dbVo,false);
    		}
    		sb.append(" UNION");
    		if(classWay[1].equals("service")){
    			addServiceSql(sb,dbVo,false);
    		}else if(classWay[1].equals("claims")){
    			addClaimsSql(sb,dbVo,false);
    		}else if(classWay[1].equals("sell")){
    			addSellSql(sb,dbVo,false);
    		}
    		if(classWay.length>2){
    			sb.append(" UNION");
        		if(classWay[2].equals("service")){
        			addServiceSql(sb,dbVo,false);
        		}else if(classWay[2].equals("claims")){
        			addClaimsSql(sb,dbVo,false);
        		}else if(classWay[2].equals("sell")){
        			addSellSql(sb,dbVo,false);
        		}
    		}
    	}
    	sb.append(" ) cc GROUP BY cc.temp0");
    	 List<DayBusinessVo> rows = new ArrayList<DayBusinessVo>();
    	 List<DayBusinessVo> footers = new ArrayList<DayBusinessVo>();
         List<Object[]> list = finMaintenanceReceivablesDao.createSQLQuery(sb.toString(),dbVo.getPage(),dbVo.getRows());
         DayBusinessVo dbs=new DayBusinessVo();
         dbs.setCustomName("0");
         dbs.setFcsPaymentMoney("0");
         DayBusinessVo db=null;
         int i=1;
         if (list != null && list.size() > 0)
        	 for (Object[] obj : list) {
				db=new DayBusinessVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					db.setFcsDate(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					db.setCustomName("共【"+obj[1].toString()+"】笔");
				dbs.setCustomName((Integer.parseInt(dbs.getCustomName())+(Integer.parseInt(obj[1].toString())))+"");
				if(obj[2]!=null&&obj[2].toString().length()>0)
					db.setFcsPaymentMoney(obj[2].toString());
				dbs.setFcsPaymentMoney((Double.parseDouble(dbs.getFcsPaymentMoney())+(Double.parseDouble(obj[2].toString())))+"");
				db.setTempId(db.getFcsDate()+"main"+i);
				i++;
				db.setState("closed");
				rows.add(db);
			}
         dbs.setPaidResourcel("");
         dbs.setFcsDate("合计");
         dbs.setState("open");
         footers.add(dbs);
         int total=finMaintenanceReceivablesDao.getSQLCount(sb.toString(), null);
         dbs.setCustomName("共【"+dbs.getCustomName()+"】笔");
         DatagridAnalyze da=new DatagridAnalyze();
         da.setRows(rows);
         da.setFooter(footers);
         da.setTotal(total);
         return da;
    }
    private void addServiceSql(StringBuffer sb,DayBusinessVo dbVo,Boolean flag){
    	sb.append(" SELECT fcs.fcs_id AS temp,fcs.fcs_payment_money AS temp2,");
    	if(flag==false){
    		sb.append(" DATE(fcs.fcs_date) AS temp0");    		
    	}else if(flag==true){
    		sb.append(" '维修收款' AS temp1");
    	}
		sb.append(" FROM fin_collection_schedule fcs INNER JOIN fin_maintenance_receivables fmr ON fmr.mr_id=fcs.mr_id");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fmr.preclr_id");		
		sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");		
		sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");		
		sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");		
		sb.append(" INNER JOIN (");		
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");		
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'");		
		sb.append(" ) aa ON aa.dataKey=fpc.preclr_inoice_type");		 
		sb.append(" INNER JOIN (");		
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");		 
		sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"'");		
		sb.append(" ) bb ON bb.dataKey=fcs.payment_id");		 
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");		
		sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");		
		sb.append(" INNER JOIN bas_stuff bsf ON bsf.stf_id=fcs.stf_id");
		sb.append(" WHERE 1=1  AND  fcs.enterprise_id="+dbVo.getEnterpriseId());
		if(dbVo.getFcsTempDate()!=null&&dbVo.getFcsTempDate().length()>0)
			sb.append(" AND Date(fcs.fcs_date)='"+dbVo.getFcsTempDate()+"'");
		if(flag==false){
			if(dbVo.getPreclrDateStart()!=null&&dbVo.getPreclrDateStart().length()>0)
				sb.append(" AND Date(fcs.fcs_date)>='"+dbVo.getPreclrDateStart()+"'");
			if(dbVo.getPreclrDateEnd()!=null&&dbVo.getPreclrDateEnd().length()>0)
				sb.append(" AND Date(fcs.fcs_date)<='"+dbVo.getPreclrDateEnd()+"'");
		}
		if(dbVo.getInvoiceType()!=null&&dbVo.getInvoiceType().length()>0)
			sb.append(" AND fpc.preclr_inoice_type='"+dbVo.getInvoiceType()+"'");
		if(dbVo.getCbrdId()!=null&&dbVo.getCbrdId().length()>0)
			sb.append(" AND bct.cbrd_id="+dbVo.getCbrdId());
		if(dbVo.getReptId()!=null&&dbVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+dbVo.getReptId());
		if(dbVo.getCustomName()!=null&&dbVo.getCustomName().length()>0)
			sb.append(" AND fc.custom_name like '%"+StringEscapeUtils.escapeSql(dbVo.getCustomName().trim())+"%'");
		if(dbVo.getCarLicense()!=null&&dbVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(dbVo.getCarLicense().trim())+"%'");
		if(dbVo.getGatheringWise()!=null&&dbVo.getGatheringWise().length()>0)
			sb.append(" AND fcs.payment_id='"+dbVo.getGatheringWise()+"'");
		if(dbVo.getServicePerson()!=null&&dbVo.getServicePerson().length()>0)
			sb.append(" AND fr.receptor="+dbVo.getServicePerson());
		if(dbVo.getRcptBranch()!=null&&dbVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+dbVo.getRcptBranch()+"'");
    }
	private void addClaimsSql(StringBuffer sb,DayBusinessVo dbVo,Boolean flag){
		sb.append(" SELECT fcs.fccs_id AS temp,fcs.fccs_payment_money AS temp2,");
		if(flag==false){
    		sb.append(" DATE(fcs.fccs_date) AS temp0");    		
    	}else if(flag==true){
    		sb.append(" '索赔收款' AS temp1");
    	}
    	sb.append(" FROM fin_claims_receivables fcr INNER JOIN fin_counterclaim_schedule fcs ON fcs.cr_id=fcr.cr_id");
    	sb.append(" INNER JOIN fin_claimant_main fcm ON fcm.claimantm_id=fcr.claimantm_id");
    	sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id");
    	sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
    	sb.append(" INNER JOIN bas_relation_campany brc ON brc.relcamp_id=fr.fin_com_id AND brc.relcamp_flg=1");
    	sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
    	sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
    	sb.append(" INNER JOIN (");
    	sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp"); 
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'");
    	sb.append(" ) aa ON aa.dataKey=fpc.preclr_inoice_type ");
    	sb.append(" INNER JOIN (");
    	sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp"); 
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"'");
    	sb.append(" ) bb ON bb.dataKey=fcs.payment_id");
    	sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
    	sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
    	sb.append(" INNER JOIN bas_stuff bsf ON bsf.stf_id=fcs.stf_id");
    	sb.append(" WHERE 1=1    and  fcr.enterprise_id="+dbVo.getEnterpriseId());
    	if(dbVo.getFcsTempDate()!=null&&dbVo.getFcsTempDate().length()>0)
    		sb.append(" AND Date(fcs.fccs_date)='"+dbVo.getFcsTempDate()+"'");
    	if(flag==false){
    		if(dbVo.getPreclrDateStart()!=null&&dbVo.getPreclrDateStart().length()>0)
    			sb.append(" AND Date(fcs.fccs_date)>='"+dbVo.getPreclrDateStart()+"'");
    		if(dbVo.getPreclrDateEnd()!=null&&dbVo.getPreclrDateEnd().length()>0)
    			sb.append(" AND Date(fcs.fccs_date)<='"+dbVo.getPreclrDateEnd()+"'");
    	}
		if(dbVo.getInvoiceType()!=null&&dbVo.getInvoiceType().length()>0)
			sb.append(" AND fcm.claimantm_invoice_type='"+dbVo.getInvoiceType()+"'");
		if(dbVo.getCbrdId()!=null&&dbVo.getCbrdId().length()>0)
			sb.append(" AND bct.cbrd_id="+dbVo.getCbrdId());
		if(dbVo.getReptId()!=null&&dbVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+dbVo.getReptId());
		if(dbVo.getCustomName()!=null&&dbVo.getCustomName().length()>0)
			sb.append(" AND brc.relcamp_name like '%"+StringEscapeUtils.escapeSql(dbVo.getCustomName().trim())+"%'");
		if(dbVo.getCarLicense()!=null&&dbVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(dbVo.getCarLicense().trim())+"%'");
		if(dbVo.getGatheringWise()!=null&&dbVo.getGatheringWise().length()>0)
			sb.append(" AND fcs.payment_id='"+dbVo.getGatheringWise()+"'");
		if(dbVo.getServicePerson()!=null&&dbVo.getServicePerson().length()>0)
			sb.append(" AND fr.receptor="+dbVo.getServicePerson());
		if(dbVo.getRcptBranch()!=null&&dbVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+dbVo.getRcptBranch()+"'");
	}
	private void addSellSql(StringBuffer sb,DayBusinessVo dbVo,Boolean flag){
		
	}
    /**
     * 财务模块 日收款查询 二级节点（收款分类信息加载）
     */
    public List<DayBusinessVo> loadPaidResource(DayBusinessVo dbVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(dbVo);
    	formatCode(dbVo);
    	if(dbVo.getClassWay()==null||dbVo.getClassWay().length()==0)
    		dbVo.setClassWay("service");
    	dbVo.setClassWay(dbVo.getClassWay().replaceAll(" ", ""));
    	String[] classWay=dbVo.getClassWay().split(",");
    	StringBuffer sb=new StringBuffer("SELECT cc.temp1,COUNT(cc.temp),SUM(temp2) FROM (");
    	if(classWay.length==1){
    		if(classWay[0].equals("service")){
    			addServiceSql(sb,dbVo,true);
    		}else if(classWay[0].equals("claims")){
    			addClaimsSql(sb,dbVo,true);
    		}else if(classWay[0].equals("sell")){
    			addSellSql(sb,dbVo,true);
    		}      		
    	}else if(classWay.length>1){
    		if(classWay[0].equals("service")){
    			addServiceSql(sb,dbVo,true);
    		}else if(classWay[0].equals("claims")){
    			addClaimsSql(sb,dbVo,true);
    		}else if(classWay[0].equals("sell")){
    			addSellSql(sb,dbVo,true);
    		}
    		sb.append(" UNION");
    		if(classWay[1].equals("service")){
    			addServiceSql(sb,dbVo,true);
    		}else if(classWay[1].equals("claims")){
    			addClaimsSql(sb,dbVo,true);
    		}else if(classWay[1].equals("sell")){
    			addSellSql(sb,dbVo,true);
    		}
    		if(classWay.length>2){
    			sb.append(" UNION");
        		if(classWay[2].equals("service")){
        			addServiceSql(sb,dbVo,true);
        		}else if(classWay[2].equals("claims")){
        			addClaimsSql(sb,dbVo,true);
        		}else if(classWay[2].equals("sell")){
        			addSellSql(sb,dbVo,true);
        		}
    		}
    	}
    	sb.append(" ) cc GROUP BY cc.temp1");
    	 List<DayBusinessVo> rows = new ArrayList<DayBusinessVo>();
         List<Object[]> list = finMaintenanceReceivablesDao.createSQLQuery(sb.toString(),dbVo.getPage(),dbVo.getRows());
         DayBusinessVo db=null;
         int i=1;
         if (list != null && list.size() > 0)
        	 for (Object[] obj : list) {
				db=new DayBusinessVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					db.setPaidResourcel(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					db.setCustomName("共【"+obj[1].toString()+"】笔");
				if(obj[2]!=null&&obj[2].toString().length()>0)
					db.setFcsPaymentMoney(obj[2].toString());
				db.set_parentId(dbVo.getFcsTempDate());
				db.setTempId(dbVo.getFcsTempDate()+"detail"+i);
				i++;
				db.setState("closed");
				rows.add(db);
			}
    	return rows;
//        return finMaintenanceReceivablesDao.loadPaidResource(dayBusinessVo
//                .getFcsDate(), dayBusinessVo.getCustomName(), dayBusinessVo
//                .getCarLicense(), dayBusinessVo.getDateValue1());
    }

    /**
     * 财务模块 日收款查询 三级节点信息加载（收款分类信息加载）
     */
    public List<DayBusinessVo> loadDayPaid(DayBusinessVo dbVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(dbVo);
    	formatCode(dbVo);
    	dbVo.setClassTempWay(new String(dbVo.getClassTempWay().getBytes("ISO-8859-1"),"UTF-8"));
    	StringBuffer sb=new StringBuffer();
    	if(dbVo.getClassTempWay().equals("维修收款")){
    		addServiceByService(sb,dbVo);
    	}else if(dbVo.getClassTempWay().equals("索赔收款")){
    		addClaimsByClaims(sb,dbVo);
    	}else if(dbVo.getClassTempWay().equals("销售收款")){
    		addSellBySell(sb,dbVo);
    	}
    	 List<DayBusinessVo> rows = new ArrayList<DayBusinessVo>();
         List<Object[]> list = finMaintenanceReceivablesDao.createSQLQuery(sb.toString(),dbVo.getPage(),dbVo.getRows());
         DayBusinessVo db=null;
         int i=1;
         if (list != null && list.size() > 0)
        	 for (Object[] obj : list) {
				db=new DayBusinessVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					db.setCustomName(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					db.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					db.setReceptionId(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					db.setInterDate(MyBeanUtils.getInstance().formatString(obj[3].toString()));
				if(obj[4]!=null&&obj[4].toString().length()>0)
					db.setPreclrId(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					db.setPreclrSumAmount(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					db.setFcsPaymentMoney(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					db.setGatheringWise(obj[7].toString());
				if(obj[8]!=null&&obj[8].toString().length()>0)
					db.setInvoiceType(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					db.setFcsRemark(obj[9].toString());
				if(obj[10]!=null&&obj[10].toString().length()>0)
					db.setPreclrWkTimeAmount(obj[10].toString());
				if(obj[11]!=null&&obj[11].toString().length()>0)
					db.setPreMprMatAmount(obj[11].toString());
				if(obj[12]!=null&&obj[12].toString().length()>0)
					db.setPreclrRealAmount(obj[12].toString());
				if(obj[13]!=null&&obj[13].toString().length()>0)
					db.setFcsId(obj[13].toString());
				if(obj[14]!=null&&obj[14].toString().length()>0)
					db.setServicePerson(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					db.setReptName(obj[15].toString());
				if(obj[16]!=null&&obj[16].toString().length()>0)
					db.setPreclrNo(obj[16].toString());
				if(obj[17]!=null&&obj[17].toString().length()>0)
					db.setBalancePerson(obj[17].toString());
				db.set_parentId(dbVo.getClassTempWay());
				db.setTempId(dbVo.getFcsTempDate()+"detail_ch"+i);
				i++;
				db.setState("open");
				rows.add(db);
			}
    	return rows;
//        return finMaintenanceReceivablesDao.loadDayPaid(dayBusinessVo
//                .getFcsId(), dayBusinessVo.getPaidResourcel());
    }
    private void addServiceByService(StringBuffer sb,DayBusinessVo dbVo){
    	sb.append("SELECT fc.custom_name,fcar.car_license,fr.reception_id,fr.inter_date,");
    	sb.append(" fpc.preclr_id,fpc.preclr_sum_amount,fcs.fcs_payment_money,bb.temp2,aa.temp1,fcs.fcs_remark,");
    	sb.append(" fpc.preclr_wktime_amount,fpc.pre_mpr_mat_amount,fpc.preclr_real_amount,fcs.fcs_id,");
    	sb.append(" bs.stf_name,rt.rept_name,fpc.preclr_no,bsf.stfName1");
    	sb.append(" FROM fin_collection_schedule fcs INNER JOIN fin_maintenance_receivables fmr ON fmr.mr_id=fcs.mr_id");
    	sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fmr.preclr_id");
    	sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
    	sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
    	sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
    	sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fr.custom_id");
    	sb.append(" INNER JOIN (");
    	sb.append(" SELECT bc.dataKey,bc.dataValue AS temp1 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'"); 
    	sb.append(" ) aa ON aa.dataKey=fpc.preclr_inoice_type");
    	sb.append(" INNER JOIN ("); 
    	sb.append(" SELECT bc.dataKey,bc.dataValue AS temp2 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp");
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"'"); 
    	sb.append(" ) bb ON bb.dataKey=fcs.payment_id");
    	sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
    	sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
    	sb.append(" INNER JOIN (select tt.stf_id,tt.stf_name as stfName1 from bas_stuff tt ) bsf ON bsf.stf_id=fcs.stf_id");
    	sb.append(" WHERE 1=1  AND  fcs.enterprise_id="+dbVo.getEnterpriseId());
    	sb.append(" AND Date(fcs.fcs_date)='"+dbVo.getFcsTempDate()+"'");
//    	if(dbVo.getPreclrDateStart()!=null&&dbVo.getPreclrDateStart().length()>0)
//			sb.append(" AND fpc.preclr_time>='"+dbVo.getPreclrDateStart()+"'");
//		if(dbVo.getPreclrDateEnd()!=null&&dbVo.getPreclrDateEnd().length()>0)
//			sb.append(" AND fpc.preclr_time<='"+dbVo.getPreclrDateEnd()+"'");
		if(dbVo.getInvoiceType()!=null&&dbVo.getInvoiceType().length()>0)
			sb.append(" AND fpc.preclr_inoice_type='"+dbVo.getInvoiceType()+"'");
		if(dbVo.getCbrdId()!=null&&dbVo.getCbrdId().length()>0)
			sb.append(" AND bct.cbrd_id="+dbVo.getCbrdId());
		if(dbVo.getReptId()!=null&&dbVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+dbVo.getReptId());
		if(dbVo.getCustomName()!=null&&dbVo.getCustomName().length()>0)
			sb.append(" AND fc.custom_name like '%"+StringEscapeUtils.escapeSql(dbVo.getCustomName().trim())+"%'");
		if(dbVo.getCarLicense()!=null&&dbVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(dbVo.getCarLicense().trim())+"%'");
		if(dbVo.getGatheringWise()!=null&&dbVo.getGatheringWise().length()>0)
			sb.append(" AND fcs.payment_id='"+dbVo.getGatheringWise()+"'");
		if(dbVo.getServicePerson()!=null&&dbVo.getServicePerson().length()>0)
			sb.append(" AND fr.receptor="+dbVo.getServicePerson());
		if(dbVo.getRcptBranch()!=null&&dbVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+dbVo.getRcptBranch()+"'");
    }
    private void addClaimsByClaims(StringBuffer sb,DayBusinessVo dbVo){
    	sb.append("SELECT brc.relcamp_name,fcar.car_license,");
    	sb.append(" fr.reception_id,fr.inter_date,fcm.claimantm_id,fpc.preclr_sum_amount,fcs.fccs_payment_money,");
    	sb.append(" bb.temp2,aa.temp1,fcs.fccs_remark,");
    	sb.append(" fpc.preclr_wktime_amount,fpc.pre_mpr_mat_amount,fpc.preclr_real_amount,fcs.fccs_id,");
    	sb.append(" bs.stf_name,rt.rept_name,fpc.preclr_no,bsf.stfName1");
    	sb.append(" FROM fin_claims_receivables fcr INNER JOIN fin_counterclaim_schedule fcs ON fcs.cr_id=fcr.cr_id");
    	sb.append(" INNER JOIN fin_claimant_main fcm ON fcm.claimantm_id=fcr.claimantm_id");
    	sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id");
    	sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=fpc.reception_id");
    	sb.append(" INNER JOIN bas_relation_campany brc ON brc.relcamp_id=fr.fin_com_id AND brc.relcamp_flg=1");
    	sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
    	sb.append(" INNER JOIN bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
    	sb.append(" INNER JOIN (");
    	sb.append(" SELECT bc.dataKey,bc.dataValue AS temp1 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp"); 
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RECEIPT_TAG.RECEIPTKEY+"'");
    	sb.append(" ) aa ON aa.dataKey=fpc.preclr_inoice_type ");
    	sb.append(" INNER JOIN (");
    	sb.append(" SELECT bc.dataKey,bc.dataValue AS temp2 FROM bas_childdictionary bc INNER JOIN bas_parentdictionary bp"); 
    	sb.append(" ON bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.GATHERINGWISE_FLG.GATHERINGWISEKEY+"'");
    	sb.append(" ) bb ON bb.dataKey=fcs.payment_id");
    	sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
    	sb.append(" INNER JOIN reptype rt ON rt.rept_id=fr.rept_id");
    	sb.append(" INNER JOIN (select tt.stf_id,tt.stf_name as stfName1 from bas_stuff tt ) bsf ON bsf.stf_id=fcs.stf_id");
    	sb.append(" WHERE 1=1  and  fcr.enterprise_id="+dbVo.getEnterpriseId());
    	sb.append(" AND Date(fcs.fccs_date)='"+dbVo.getFcsTempDate()+"'");
//    	if(dbVo.getPreclrDateStart()!=null&&dbVo.getPreclrDateStart().length()>0)
//			sb.append(" AND fcm.claimantm_time>='"+dbVo.getPreclrDateStart()+"'");
//		if(dbVo.getPreclrDateEnd()!=null&&dbVo.getPreclrDateEnd().length()>0)
//			sb.append(" AND fcm.claimantm_time<='"+dbVo.getPreclrDateEnd()+"'");
		if(dbVo.getInvoiceType()!=null&&dbVo.getInvoiceType().length()>0)
			sb.append(" AND fcm.claimantm_invoice_type='"+dbVo.getInvoiceType()+"'");
		if(dbVo.getCbrdId()!=null&&dbVo.getCbrdId().length()>0)
			sb.append(" AND bct.cbrd_id="+dbVo.getCbrdId());
		if(dbVo.getReptId()!=null&&dbVo.getReptId().length()>0)
			sb.append(" AND rt.rept_id="+dbVo.getReptId());
		if(dbVo.getCustomName()!=null&&dbVo.getCustomName().length()>0)
			sb.append(" AND brc.relcamp_name like '%"+StringEscapeUtils.escapeSql(dbVo.getCustomName().trim())+"%'");
		if(dbVo.getCarLicense()!=null&&dbVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+StringEscapeUtils.escapeSql(dbVo.getCarLicense().trim())+"%'");
		if(dbVo.getGatheringWise()!=null&&dbVo.getGatheringWise().length()>0)
			sb.append(" AND fcs.payment_id='"+dbVo.getGatheringWise()+"'");
		if(dbVo.getServicePerson()!=null&&dbVo.getServicePerson().length()>0)
			sb.append(" AND fr.receptor="+dbVo.getServicePerson());
		if(dbVo.getRcptBranch()!=null&&dbVo.getRcptBranch().length()>0)
			sb.append(" AND fr.rcpt_branch='"+dbVo.getRcptBranch()+"'");
    }
    private void addSellBySell(StringBuffer sb,DayBusinessVo dbVo){
    	
    }
    private void formatCode(DayBusinessVo dbVo) throws UnsupportedEncodingException{
    	if(dbVo.getCustomName()!=null&&dbVo.getCustomName().length()>0)
    		dbVo.setCustomName(new String(dbVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
    	if(dbVo.getCarLicense()!=null&&dbVo.getCarLicense().length()>0)
    		dbVo.setCarLicense(new String(dbVo.getCarLicense().getBytes("ISO8859-1"),"UTF-8"));
    }
    private void setDefaultPreclrTimeSect(DayBusinessVo dbVo)throws Exception{
		if((dbVo.getPreclrDateStart()==null||dbVo.getPreclrDateStart().length()==0)&&
				(dbVo.getPreclrDateEnd()==null||dbVo.getPreclrDateEnd().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				dbVo.setPreclrDateEnd(MyBeanUtils.getInstance().getString(date));
				dbVo.setPreclrDateStart(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
