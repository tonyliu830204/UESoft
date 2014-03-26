package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasClaimsTypeDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.dao.FinMaintenanceReceivablesDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.dao.FrtPreCostDao;
import com.syuesoft.frt.dao.FrtPreWktimeDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.service.FrtPreClearingService;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.CostVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtPreClearingVo;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.model.FinMaintenanceReceivables;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.model.FrtPreClearingCost;
import com.syuesoft.model.FrtPreParts;
import com.syuesoft.model.FrtPreWktime;
import com.syuesoft.model.FrtReception;
import com.syuesoft.model.StSellOrder;
import com.syuesoft.st.dao.StSellOrderDAO;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;
/**
 * 交车结算service
 * @author Liujian
 */
@Service("frtPreClearingService")
public class FrtPreClearingServiceImpl extends BaseLogServiceImpl implements
        FrtPreClearingService
{
	@Autowired
    private FrtPreClearingDao frtPreClearingDao;
    @Autowired
    private FrtPreWktimeDao frtPreWktimeDao;
    @Autowired
    private FrtCarDao frtCarDao;
    @Autowired
    private FrtPreCostDao frtPreCostDao;
    @Autowired
    private FrtReceptionDao frtReceptionDao;
    @Autowired
    private FinMaintenanceReceivablesDao finMaintenanceReceivablesDao;
    @Autowired
    private BasClaimsTypeDao basClaimsTypeDao;
    @Autowired
    private FrtService frtService; 
    @Autowired StSellOrderDAO stSellOrderDAO;
    
    /**
     * 交车结算datagrid
     */
    
    public Datagrid datagridFrtPreClearing(FrtPreClearingVo fpcVo)throws Exception{
        List<FrtPreClearingVo> rows = new ArrayList<FrtPreClearingVo>();
        StringBuffer sb = new StringBuffer("SELECT k.* FROM (SELECT a.* FROM("+
		" SELECT  fpc.PRECLR_ID  AS preclrId,fpc.RECEPTION_ID AS receptionId,"+
		" fc.CAR_LICENSE AS carLicense,fc.CAR_MOTOR_ID AS carMotorId,"+
		" fcu.CUSTOM_NAME AS customName,fpc.PRECLR_TIME AS preclrTime,"+
		" fpc.PRECLR_INOICE_TYPE AS preclrInoiceType,"+
		" fpc.PRECLR_INVOICE_TIME AS preclrInvoiceTime,"+
		" fpc.PRECLR_NO AS preclrNo, fpc.PRE_MPR_MAT_AMOUNT AS preMprMatAmount,"+
		" fpc.PRECLR_MATERIAL_RATE AS preclrMaterialRate, fpc.PRECLR_WKTIME_AMOUNT AS preclrWktimeAmount,"+
		" fpc.PRECLR_WKTIME_RATE AS preclrWktimeRate, fpc.PRECLR_OTHER_AMOUNT AS preclrOtherAmount,"+
		" fpc.PRECLR_SUM_AMOUNT AS preclrSumAmount, fpc.PRECLR_SUM_RATE AS preclrSumRate,"+
		" fpc.PRECLR_REAL_AMOUNT AS preclrRealAmount, fpc.PRECLR_NOTAX_COST AS preclrNoTaxCost,"+
		" fpc.PRECLR_TAX_COST AS preclrTaxCost, fpc.PRECLR_INSTR AS preclrInstr,"+
		" fpc.PRECLR_REMARK  AS preclrRemark,fpc.PRECLR_MANAGEMENT_FEE AS preclrManagementFee,"+
		" fcu.CUSTOM_TEL1 AS customTel1,fc.CAR_VIN AS carVin,"+
		" rt.REPT_ID AS reptId, fpc.STF_ID AS stfId,"+
		" fre.FIN_COM_ID AS finComId, fre.FIN_TAG AS finTag,"+
		" fre.FIN_ALL_TAG AS finAllTag, fpc.PRECLR_TO_MONEY AS preclrToMoney,"+
		" fc.CAR_ID AS careId,rt.REPT_NAME AS reptName,bs.STF_NAME,bc.dataValue,' ' AS PRECLR_STATE"+
		" FROM frt_pre_clearing fpc INNER JOIN frt_reception fre ON fre.RECEPTION_ID = fpc.RECEPTION_ID"+
		" INNER JOIN frt_car fc ON fc.CAR_ID = fre.CAR_ID INNER JOIN frt_custom fcu ON fcu.CUSTOM_ID = fc.CUSTOM_ID"+
		" INNER JOIN reptype rt ON rt.rept_id=fre.rept_id"+
		" LEFT JOIN bas_stuff bs ON bs.STF_ID=fpc.STF_ID"+
		" INNER JOIN bas_childdictionary bc ON fpc.PRECLR_INOICE_TYPE= bc.datakey"+
		" WHERE 1=1 AND fpc.PRE_FLG=" + Contstants.DELETE_TAG.DELETENO+
		"  and  fpc.enterprise_id="+fpcVo.getEnterpriseId() +
		" AND fre.RECEPTION_STATUS IN('"+ Contstants.DOCUMENT_TAG.DOCUMENTState8 + "','"+Contstants.DOCUMENT_TAG.DOCUMENTState9 + "','"+Contstants.DOCUMENT_TAG.DOCUMENTState10 + "','"+Contstants.DOCUMENT_TAG.DOCUMENTState11 + "','"+Contstants.DOCUMENT_TAG.DOCUMENTState12 + "')) a"+
		" UNION SELECT cc.* FROM ("+
		" SELECT fpc.PRECLR_ID  AS preclrId1,fpc.RECEPTION_ID AS receptionId1, sso.CAR_LICENSE AS carLicense1,-1 AS carMotorId1,"+
		" fcu.CUSTOM_NAME AS customName1, fpc.PRECLR_TIME AS preclrTime1, fpc.PRECLR_INOICE_TYPE AS preclrInoiceType1, "+
		" fpc.PRECLR_INVOICE_TIME AS preclrInvoiceTime1, fpc.PRECLR_NO AS preclrNo1, fpc.PRE_MPR_MAT_AMOUNT AS preMprMatAmount1,"+
		" fpc.PRECLR_MATERIAL_RATE AS preclrMaterialRate1, fpc.PRECLR_WKTIME_AMOUNT AS preclrWktimeAmount1, "+
		" fpc.PRECLR_WKTIME_RATE AS preclrWktimeRate1, fpc.PRECLR_OTHER_AMOUNT AS preclrOtherAmount1, "+
		" fpc.PRECLR_SUM_AMOUNT AS preclrSumAmount1, fpc.PRECLR_SUM_RATE AS preclrSumRate1, "+
		" fpc.PRECLR_REAL_AMOUNT AS preclrRealAmount1, fpc.PRECLR_NOTAX_COST AS preclrNoTaxCost1, "+
		" fpc.PRECLR_TAX_COST AS preclrTaxCost1, fpc.PRECLR_INSTR AS preclrInstr1, fpc.PRECLR_REMARK  AS preclrRemark1,"+
		" fpc.PRECLR_MANAGEMENT_FEE AS preclrManagementFee1, '' AS customTel2,'' AS carVin1, -1 AS reptId1,"+
		" fpc.STF_ID AS stfId1,-1  AS finComId1, -1 AS finTag1,-1 AS finAllTag1, fpc.PRECLR_TO_MONEY AS preclrToMoney1, "+
		" -1 AS careId1,''  AS reptName1,bs.STF_NAME as STF_NAME,bc.dataValue,sso.PRECLR_STATE as PRECLR_STATE "+
		" FROM frt_pre_clearing fpc "+
		" INNER JOIN st_sell_order sso ON fpc.RECEPTION_ID = sso.SELLMM_ID  and fpc.PRE_FLG=0 "+
		" INNER JOIN frt_custom fcu ON fcu.CUSTOM_ID =sso.SELLCUSTOM_ID "+
		" LEFT JOIN bas_stuff bs ON bs.STF_ID=fpc.STF_ID"+
		" INNER JOIN bas_childdictionary bc ON fpc.PRECLR_INOICE_TYPE= bc.datakey"+
		" ) cc where PRECLR_STATE='已结算' or PRECLR_STATE='已转结算') AS k  where 1=1 ");
        if (fpcVo.getPreclrId() != null && fpcVo.getPreclrId().length() > 0)
            sb.append(" and k.preclrId like '%" + StringEscapeUtils.escapeSql(fpcVo.getPreclrId().trim())+ "%'");
        if (fpcVo.getReceptionId() != null&& fpcVo.getReceptionId().length() > 0)
            sb.append(" and k.receptionId like '%" + StringEscapeUtils.escapeSql(fpcVo.getReceptionId().trim()) + "%'");
        if (fpcVo.getCarLicense() != null && fpcVo.getCarLicense().length() > 0)
            sb.append(" and k.carLicense like '%"+ StringEscapeUtils.escapeSql(fpcVo.getCarLicense().trim())+ "%'");
        if (fpcVo.getPreclrTimeBegin() != null&& fpcVo.getPreclrTimeBegin().toString().length() > 0)
        {
            if (fpcVo.getPreclrTimeEnd() != null&& fpcVo.getPreclrTimeEnd().toString().length() > 0)
                sb.append(" and k.preclrTime>='" + fpcVo.getPreclrTimeBegin()+ "' and k.preclrTime<='" + fpcVo.getPreclrTimeEnd()+ "'");
            else
                sb.append(" and k.preclrTime>='" + fpcVo.getPreclrTimeBegin() + "'");
        }
        else if (fpcVo.getPreclrTimeEnd() != null&& fpcVo.getPreclrTimeEnd().toString().length() > 0)
            sb.append(" and k.preclrTime<='" + fpcVo.getPreclrTimeEnd() + "'");
//        //默认结算时间
//        if(fpcVo.getPreclrTimeBegin() == null && fpcVo.getPreclrTimeEnd() == null){
//    		sb.append(" and DATE_FORMAT(k.preclrTime,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT).getCiValue()))+ "'");
//    		sb.append(" and DATE_FORMAT(k.preclrTime,'%%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
//        }	
        if (fpcVo.getPreclrInvoiceTimeBegin() != null && fpcVo.getPreclrInvoiceTimeBegin().toString().length() > 0) {
            if (fpcVo.getPreclrInvoiceTimeEnd() != null&& fpcVo.getPreclrInvoiceTimeEnd().toString().length() > 0)
                sb.append(" and k.preclrInvoiceTime>='"+ fpcVo.getPreclrInvoiceTimeBegin()+ "' and k.preclrInvoiceTime<='"+ fpcVo.getPreclrInvoiceTimeEnd() + "'");
            else
                sb.append(" and k.preclrInvoiceTime>='"+ fpcVo.getPreclrInvoiceTimeBegin() + "'");
        }
        else if (fpcVo.getPreclrInvoiceTimeEnd() != null&& fpcVo.getPreclrInvoiceTimeEnd().toString().length() > 0)
            sb.append(" and k.preclrInvoiceTime<='"+ fpcVo.getPreclrInvoiceTimeEnd() + "'");
        if (fpcVo.getPreclrInoiceType() != null&& fpcVo.getPreclrInoiceType().toString().length() > 0)
            sb.append(" and k.preclrInoiceType=" + fpcVo.getPreclrInoiceType());
        if (fpcVo.getPreclrNo() != null && fpcVo.getPreclrNo().length() > 0)
            sb.append(" and k.preclrNo like '%" + StringEscapeUtils.escapeSql(fpcVo.getPreclrNo().trim())+ "%'");
        if (fpcVo.getSort() != null && !"".equals(fpcVo.getSort().trim())&& fpcVo.getOrder() != null&& !"".equals(fpcVo.getOrder().trim()))
            sb.append(" order by k." + fpcVo.getSort().trim() + " "+ fpcVo.getOrder().trim());
        List<Object[]> rowsList = frtPreClearingDao.createSQLQuery(sb.toString());
        int total = 0;
        if (rowsList != null && rowsList.size() > 0){
        	 FrtPreClearingVo fVo=null;
        	 total=rowsList.size();
             for (Object[] obj : rowsList){
                fVo= new FrtPreClearingVo();
                fVo.setPreclrId(obj[0] != null && !obj[0].equals("") ? obj[0].toString() : " ");
                fVo.setReceptionId(obj[1] != null && !obj[1].equals("") ? obj[1].toString() : " ");
                fVo.setCarLicense(obj[2] != null && !obj[2].equals("") ? obj[2].toString() : " ");
                fVo.setCarMotorId(obj[3] != null && !obj[3].equals("") ? obj[3].toString() : " ");
                fVo.setCustomName(obj[4] != null && !obj[4].equals("") ? obj[4].toString() : " ");
                fVo.setPreclrTime(obj[5] != null && !obj[5].equals("") ? MyBeanUtils.getInstance().formatString(obj[5].toString()): " ");
                fVo.setPreclrInoiceType(obj[6] != null && !obj[6].equals("") ? obj[6].toString(): " ");
                fVo.setPreclrInvoiceTime(obj[7] != null && !obj[7].equals("") ? MyBeanUtils.getInstance().formatString(obj[7].toString()): " ");
                fVo.setPreclrNo(obj[8] != null && !obj[8].equals("") ? obj[8].toString(): " ");
                fVo.setPreMprMatAmount(obj[9] != null && !obj[9].equals("") ? new Double(obj[9].toString()) : 0.00d);
                fVo.setPreclrMaterialRate(obj[10] != null && !obj[10].equals("") ? new Double(obj[10].toString()) * 100 : 100d);
                fVo.setPreclrWktimeAmount(obj[11] != null && !obj[11].equals("") ? new Double(obj[11].toString()) : 0.00d);
                fVo.setPreclrWktimeRate(obj[12] != null && !obj[12].equals("") ? new Double(obj[12].toString()) * 100 : 100d);
                fVo.setPreclrOtherAmount(obj[13] != null && !obj[13].equals("") ? new Double(obj[13].toString()) : 0.00d);
                fVo.setPreclrSumAmount(obj[14] != null && !obj[14].equals("") ? new Double(obj[14].toString()) : 0.00d);
                fVo.setPreclrSumRate(obj[15] != null && !obj[15].equals("") ? new Double(obj[15].toString()) * 100 : 100d);
                fVo.setPreclrRealAmount(obj[16] != null && !obj[16].equals("") ? new Double(obj[16].toString()) : 0.00d);
                fVo.setPreclrNoTaxCost(obj[17] != null && !obj[17].equals("") ? new Double(obj[17].toString()) : 0.00d);
                fVo.setPreclrTaxCost(obj[18] != null && !obj[18].equals("") ? new Double(obj[18].toString()) : 0.00d);
                fVo.setPreclrInstr(obj[19] != null && !obj[19].equals("") ? obj[19].toString() : " ");
                fVo.setPreclrRemark(obj[20] != null && !obj[20].equals("") ? obj[20].toString() : " ");
                fVo.setPreclrManagementFee(obj[21] != null && !obj[21].equals("") ? new Double(obj[21].toString()) : 0.00d);
                fVo.setCarRelationTel1(obj[22] != null && !obj[22].equals("") ? obj[22].toString() : " ");
                fVo.setCarVin(obj[23] != null && !obj[23].equals("") ? obj[23].toString() : " ");
                fVo.setReptId(obj[24] != null && !obj[24].equals("") ? obj[24].toString() : " ");
                if(fVo.getReptId() != null && !" ".equals(fVo.getReptId())){
                	if(fVo.getReptId().equals(frtService.getDefaultFirstmaintain()))
	                   fVo.setPreMprMatAmountOld(0d);
	                else
	                   fVo.setPreMprMatAmountOld(getPrePartsRateAmount(fVo.getPreclrId()));
                }else{
                	fVo.setPreMprMatAmountOld(0d);
                }
                fVo.setStfId(obj[25] != null && !obj[25].equals("") ? Short.parseShort(obj[25].toString()) : null);
                fVo.setFinComId(obj[26] != null && !obj[26].equals("") ? Short.parseShort(obj[26].toString()) : 0);
                fVo.setFinTag(obj[27] != null && !obj[27].equals("") ? Short.parseShort(obj[27].toString()) : 0);
                fVo.setFinAllTag(obj[28] != null && !obj[28].equals("") ? Short.parseShort(obj[28].toString()) : 0);
                fVo.setPreclrToMoney(obj[29] != null && !obj[29].equals("") ? Short.parseShort(obj[29].toString()) : 0);
                fVo.setCarId(obj[30] != null && !obj[30].equals("") ? obj[30].toString() : " ");
                fVo.setReptName(obj[31] != null && !obj[31].equals("") ? obj[31].toString() : " ");
                fVo.setStfName(obj[32] != null && !obj[32].equals("") ? obj[32].toString() : " ");
                fVo.setTempName1(obj[33] != null && !obj[33].equals("") ? obj[33].toString() : " ");
                fVo.setPreClrState(obj[34] != null && !obj[34].equals("") ? obj[34].toString() : " ");//结算状态
                rows.add(fVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 删除交车结算单
     */
    @Log(moduleName = "前台管理", content = "删除交车结算单", opertype = "删除")
    
    public Msg remove(String preclrId) throws Exception
    {
        Msg msg = new Msg();
        try{
            FrtPreClearing fpc = frtPreClearingDao.get(FrtPreClearing.class,preclrId);
            fpc.setPreFlg(Contstants.DELETE_TAG.DELETEYES);//删除标识
            if(preclrId!=null&&!preclrId.equals("")){
            	 String id=preclrId.substring(0,2);
                 if(id.equals("XS")){//修改销售单结算状态
                	 StSellOrder sso=stSellOrderDAO.get(StSellOrder.class,fpc.getReceptionId());
                	 if(sso!=null&&!fpc.equals("")){
                		 sso.setPreclrState("未结算");
                		 stSellOrderDAO.merge(sso);
                	 }
                 }else{//修改前台接车工单状态
                	 FrtReception fr=frtReceptionDao.get(FrtReception.class, fpc.getReceptionId());
                	 if(fr!=null&&fr.equals("")){
                		 fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState0);//修改前台接车工单状态
                         frtPreClearingDao.merge(fpc);
                	 }
                 }
            }
            msg.setSuccess(true);
            msg.setMsg("删除交车结算单成功！");
        }catch(Exception es){
            msg.setMsg("删除交车结算单失败！");
            es.printStackTrace();
        }
        return msg;
    }

    private Double getPrePartsRateAmount(String preclrId) throws Exception
    {
    	StringBuffer sb=new StringBuffer("SELECT SUM(fpp.PARTS_RATE_AMOUNT) FROM frt_pre_parts fpp,bas_claims_type bct");
    	sb.append(" WHERE bct.CLAIMS_ID=fpp.CLAIMS_TYPE AND bct.CLAIMS_TO_MONEY="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES+" AND fpp.PRECLR_ID='"+preclrId.trim()+"'");
        List rowsList = frtPreWktimeDao.createSQLQuery(sb.toString(), null);
        if (rowsList != null)
            return Double.parseDouble(rowsList.get(0) == null ? "0.00"
                    : rowsList.get(0).toString());
        else
            return 0.00d;
    }

    /**
     * 根据交车结算单id查询材料清单
     */
    
    public Datagrid findPrePartsById(String preclrId) throws Exception
    {
        List<PartsVo> fpcPartsList = new ArrayList<PartsVo>();
        StringBuffer sb = new StringBuffer("");
        if(preclrId!=null&&!preclrId.equals("")){
        	String id=preclrId.substring(0,2);
        	if(id.equals("XS")){
        		sb.append("SELECT '' as a1,frt_pre_parts.PARTS_INDEX,frt_pre_parts.PARTS_ID,frt_pre_parts.PARTS_COUNT,frt_pre_parts.PARTS_PRICE,frt_pre_parts.PARTS_AMOUNT " +
        				" ,'' as a2,'' as a3,'' as a4,'' as a5,frt_pre_parts.PARTS_NAME,'' as a6,frt_pre_parts.parts_rate,frt_pre_parts.parts_rate_amount,'' as a9,'' as a10,'' as a16,'' as a17 FROM frt_pre_parts where 1=1 ");
        	}else{
        		sb.append("SELECT frt_pre_parts.*,bct.CLAIMS_NAME FROM frt_pre_parts,bas_claims_type bct WHERE bct.CLAIMS_ID=frt_pre_parts.CLAIMS_TYPE");
        	}
        }
        if (preclrId != null && preclrId.length() > 0)
            sb.append(" and frt_pre_parts.PRECLR_ID ='" + preclrId.trim() + "'");
        else
            sb.append(" and frt_pre_parts.PRECLR_ID ='-1'");
        List<Object[]> rowsList = frtPreWktimeDao.createSQLQuery(sb.toString(),null);
        if (rowsList != null && rowsList.size() > 0){
            for (Object[] obj : rowsList){
                PartsVo pVo = new PartsVo();
                if (obj[1] != null &&!"".equals(obj[1]))
                    pVo.setPartsIndex(Integer.parseInt(obj[1].toString()));
                if (obj[2] != null && !"".equals(obj[2]))
                    pVo.setPartsId(obj[2].toString());
                if (obj[3] != null && !"".equals(obj[3]))
                    pVo.setPartsCount(Double.parseDouble(obj[3].toString()));
                else
                    pVo.setPartsCount(0.00d);
                if (obj[4] != null && !"".equals(obj[4]))
                    pVo.setPartsPrice(new Double(obj[4].toString()));
                else
                    pVo.setPartsPrice(0.00d);
                if (obj[5] != null && !"".equals(obj[5]))
                    pVo.setPartsAmount(new Double(obj[5].toString()));
                else
                    pVo.setPartsAmount(0.00d);
                if (obj[6] != null && !"".equals(obj[6]))
                    pVo.setRelcampId(new Short(obj[6].toString()));
                if (obj[7] != null && !"".equals(obj[7]))
                    pVo.setReptypId(new Short(obj[7].toString()));
                if (obj[8] != null && !"".equals(obj[8]))
                    pVo.setClaimsId(new Short(obj[8].toString()));
                if (obj[9] != null && !"".equals(obj[9]))
                    pVo.setSettlementDiscount(new Double(obj[9].toString()));
                if (obj[10] != null && !"".equals(obj[10]))
                    pVo.setPartsName(obj[10].toString());
                if (obj[12] != null && !"".equals(obj[12]))
                    pVo.setPartsRate(Double.parseDouble(obj[12].toString()) * 100);
                else
                    pVo.setPartsRate(100d);
                if (obj[13] != null && !"".equals(obj[13]))
                    pVo.setPartsRateAmount(Double.parseDouble(obj[13].toString()));
                else
                    pVo.setPartsRateAmount(0.00d);
                if (obj[14] != null && !"".equals(obj[14]))
                	pVo.setStoreId(obj[14].toString());
                if (obj[18] != null && !"".equals(obj[18]))
                    pVo.setClaimsName(obj[18].toString());
                fpcPartsList.add(pVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(fpcPartsList);
        dg.setTotal(fpcPartsList.size());
        return dg;
    }

    /**
     * 根据交车结算单id查询项目清单
     */
    
    public Datagrid findPreItemById(String preclrId) throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT fpw.*,STF_NAME,bct.CLAIMS_NAME ");
        sb.append(" FROM frt_pre_wktime fpw,bas_stuff bs,bas_claims_type bct");
        sb
                .append(" where bs.STF_ID=fpw.STF_ID AND bct.CLAIMS_ID=fpw.CLAIMS_TYPE");
        if (preclrId != null && !"".equals(preclrId.trim()))
        {
            sb.append(" AND fpw.PRECLR_ID ='" + preclrId.trim() + "'");
        }
        else
        {
            sb.append(" AND fpw.PRECLR_ID ='-1'");
        }
        List<FrtItemVo> rows = new ArrayList<FrtItemVo>();
        List<Object[]> rowsList = frtPreWktimeDao.createSQLQuery(sb.toString(),
                null);
        if (rowsList != null && rowsList.size() > 0)
        {
            for (Object[] obj : rowsList)
            {
                FrtItemVo iVo = new FrtItemVo();
                if (obj[2] != null && obj[2].toString().length() > 0)
                    iVo.setRepitemId(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    iVo.setRepitemName(obj[3].toString());
                if (obj[4] != null && obj[4].toString().length() > 0)
                    iVo.setWktimeNum(Double.parseDouble(obj[4].toString()));
                else
                    iVo.setWktimeNum(0.00d);
                if (obj[5] != null && obj[5].toString().length() > 0)
                    iVo.setInnerWktime(Double.parseDouble(obj[5].toString()));
                else
                    iVo.setInnerWktime(0.00d);
                if (obj[6] != null && obj[6].toString().length() > 0)
                    iVo.setWktimeAmount(new Double(obj[6].toString()));
                else
                    iVo.setWktimeAmount(0.00d);
                if (obj[7] != null && obj[7].toString().length() > 0)
                    iVo.setRelcampId(new Short(obj[7].toString()));
                if (obj[8] != null && obj[8].toString().length() > 0)
                    iVo.setReptypId(new Short(obj[8].toString()));
                if (obj[9] != null && obj[9].toString().length() > 0)
                    iVo.setClaimsId(new Short(obj[9].toString()));
                if (obj[10] != null && obj[10].toString().length() > 0)
                    iVo.setStfId(new Short(obj[10].toString()));
                if (obj[11] != null && obj[11].toString().length() > 0)
                    iVo.setSettlementDiscount(new Double(obj[11].toString()));
                if (obj[14] != null && obj[14].toString().length() > 0)
                    iVo.setStfName(obj[14].toString());
                if (obj[15] != null && obj[15].toString().length() > 0)
                    iVo.setClaimsName(obj[15].toString());
                rows.add(iVo);
            }
        }
        Datagrid dg = new Datagrid();
        if (rows != null && rows.size() > 0)
        {
            dg.setRows(rows);
            dg.setTotal(rows.size());
        }
        else
        {
            dg.setRows(new ArrayList());
            dg.setTotal(0);
        }
        return dg;
    }

    /**
     * 更新交车结算单
     */
    @Log(moduleName = "前台管理", content = "更新交车结算单", opertype = "更新")
    
    public Msg edit(FrtPreClearingVo fpcVo) throws Exception
    {
        List<PartsVo> partsList = null;
        String parts = fpcVo.getParts();
        if (parts != null && parts.length() > 0){
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),PartsVo.class);
        }
        Msg msg = new Msg();
        FrtPreClearing frtPreClearing = null;
        try
        {
            double partsRateAmount = 0;
            frtPreClearing = frtPreClearingDao.get(FrtPreClearing.class, fpcVo.getPreclrId());
            partsRateAmount = copyData(frtPreClearing, partsList);
            if (fpcVo.getPreclrInoiceType() != null&& fpcVo.getPreclrInoiceType().toString().length() > 0)
                frtPreClearing.setPreclrInoiceType(fpcVo.getPreclrInoiceType());
            if (fpcVo.getPreclrNo() != null && fpcVo.getPreclrNo().length() > 0)
                frtPreClearing.setPreclrNo(fpcVo.getPreclrNo());
            if (fpcVo.getPreclrTime() != null&& fpcVo.getPreclrTime().toString().length() > 0)
                frtPreClearing.setPreclrTime(MyBeanUtils.getInstance().getDate(fpcVo.getPreclrTime()));
            if (fpcVo.getPreclrMaterialRate() != null&& fpcVo.getPreclrMaterialRate().toString().length() > 0)
                frtPreClearing.setPreclrMaterialRate(fpcVo.getPreclrMaterialRate() / 100);
            else
                frtPreClearing.setPreclrMaterialRate(Contstants.DISCOUNTRATE / 100);
            if (fpcVo.getPreclrWktimeRate() != null&& fpcVo.getPreclrWktimeRate().toString().length() > 0)
                frtPreClearing.setPreclrWktimeRate(fpcVo.getPreclrWktimeRate() / 100);
            else
                frtPreClearing.setPreclrWktimeRate(Contstants.DISCOUNTRATE / 100);
            if (fpcVo.getPreclrManagementFee() != null&& fpcVo.getPreclrManagementFee().toString().length() > 0)
                frtPreClearing.setPreclrManagementFee(fpcVo.getPreclrManagementFee());
            if (fpcVo.getPreclrInstr() != null&& fpcVo.getPreclrInstr().toString().length() > 0)
                frtPreClearing.setPreclrInstr(fpcVo.getPreclrInstr());
            if (fpcVo.getPreclrRemark() != null&& fpcVo.getPreclrRemark().length() > 0)
                frtPreClearing.setPreclrRemark(fpcVo.getPreclrRemark());
            if (fpcVo.getPreclrInvoiceTime() != null&& fpcVo.getPreclrInvoiceTime().toString().length() > 0){
                if (!frtPreClearing.getPreclrInoiceType().equals(Contstants.RECEIPT_TAG.OTHERTAX))
                    frtPreClearing.setPreclrInvoiceTime(MyBeanUtils.getInstance().getDate(fpcVo.getPreclrInvoiceTime()));
                else
                    frtPreClearing.setPreclrInvoiceTime(null);
            }
            if (fpcVo.getStfId() != null&& fpcVo.getStfId().toString().length() > 0)
                frtPreClearing.setStfId(fpcVo.getStfId());
            double tempsum = 0d;
            double tempsum2 = 0d;
            FrtReception fr=null;
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < frtPreClearing.getReceptionId().length(); i++) {
    			if(!(frtPreClearing.getReceptionId().charAt(i)>='0'&&frtPreClearing.getReceptionId().charAt(i)<='9')){
    				sb.append(frtPreClearing.getReceptionId().charAt(i));
    			}
    		}
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
            	fr=frtReceptionDao.get(FrtReception.class, frtPreClearing.getReceptionId());
            	if(!(fr.getReptype().getReptId().toString().equals(frtService.getDefaultFirstmaintain()))){
            		tempsum += (frtPreClearing.getPreclrWktimeAmount() * frtPreClearing.getPreclrWktimeRate());
            		tempsum += (partsRateAmount * frtPreClearing.getPreclrMaterialRate());
            		tempsum += frtPreClearing.getPreclrOtherAmount();
            		tempsum += frtPreClearing.getPreclrManagementFee();
            		/***************/
            		tempsum2 += frtPreClearing.getPreclrWktimeAmount();
            		tempsum2 += frtPreClearing.getPreMprMatAmount();
            		tempsum2 += frtPreClearing.getPreclrOtherAmount();
            		tempsum2 += frtPreClearing.getPreclrManagementFee();            	
            	}            	
            }else{
        		tempsum += (partsRateAmount * frtPreClearing.getPreclrMaterialRate());
        		tempsum += frtPreClearing.getPreclrOtherAmount();
        		tempsum += frtPreClearing.getPreclrManagementFee();
        		/***************/
        		tempsum2 += frtPreClearing.getPreclrWktimeAmount();
        		tempsum2 += frtPreClearing.getPreMprMatAmount();
        		tempsum2 += frtPreClearing.getPreclrOtherAmount();
        		tempsum2 += frtPreClearing.getPreclrManagementFee();   
            }
            frtPreClearing.setPreclrSumAmount(tempsum2);
            frtPreClearing.setPreclrRealAmount(tempsum);
            if(!(tempsum==0||tempsum2==0))
            	frtPreClearing.setPreclrSumRate(tempsum / tempsum2);
            else
            	frtPreClearing.setPreclrSumRate(1d);
            if (fpcVo.getFinAllTag() == null|| fpcVo.getFinAllTag().toString().length() == 0)
                fpcVo.setFinAllTag(Contstants.CLAIM_TAG.NOCLAIM);
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
            	fr.setFinAllTag(fpcVo.getFinAllTag());
            }
            if (fpcVo.getFinTag() == null|| fpcVo.getFinTag().toString().length() == 0)
                fpcVo.setFinTag(Contstants.CLAIM_TAG.NOCLAIM);
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
            	fr.setFinTag(fpcVo.getFinTag());
            	if(fpcVo.getFinComId()!=null&&fpcVo.getFinComId().toString().length()>0)
            		fr.setFinComId(fpcVo.getFinComId());
            }
            frtPreClearingDao.merge(frtPreClearing);
            StSellOrder sso = stSellOrderDAO.get(StSellOrder.class, frtPreClearing.getReceptionId());
    		if(sso!=null&&!sso.equals("")){
    			sso.setPreclrState("已结算");
    			stSellOrderDAO.merge(sso);
    		}else{
    			fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState12);
    			frtReceptionDao.merge(fr);
    		}
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
            	if (fr.getFinTag() == Contstants.CLAIM_TAG.CLAIMNO){
            		FrtReception frp=frtReceptionDao.get(FrtReception.class, frtPreClearing.getReceptionId());
            		if (frp.getFinAllTag() == Contstants.CLAIM_TAG.CLAIMNO)
            			msg.setMsg("更新交车结算单(含全额索理赔)成功!");
            		else
            			msg.setMsg("更新交车结算单(含索理赔)成功!");
            		msg.setSuccess(true);
            	}
            	else{
            		msg.setSuccess(true);
            		msg.setMsg("更新交车结算单成功!");
            	}            	
            }else{
            	msg.setSuccess(true);
        		msg.setMsg("更新销售结算单成功!");
            }
        }
        catch(Exception es){
            msg.setMsg("更新交车结算单失败!");
            es.printStackTrace();
        }
        return msg;
    }

    private Double copyData(FrtPreClearing fpc, List<PartsVo> partsList) throws Exception
    {
        double partsRateAmount = 0d;
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < fpc.getReceptionId().length(); i++) {
			if(!(fpc.getReceptionId().charAt(i)>='0'&&fpc.getReceptionId().charAt(i)<='9')){
				sb.append(fpc.getReceptionId().charAt(i));
			}
		}
        if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
        	FrtReception frp=frtReceptionDao.get(FrtReception.class, fpc.getReceptionId());
        	if(frp!=null&&frp.getReptype()!=null&&frp.getReptype().getReptId().toString().equals(frtService.getDefaultFirstmaintain()))
        		return partsRateAmount;        	
        }
        Set<FrtPreParts> set1 = fpc.getFrtPrePartses();
        if (partsList != null && partsList.size() > 0){
            for (FrtPreParts frtPreParts : set1){
                for (PartsVo pVo : partsList){
                    if (pVo.getPartsIndex().equals(frtPreParts.getPartsIndex())){
                    	if(!sb.toString().equals(Contstants.PROSCENIUMNEARID)){
                    		frtPreParts.setPartsRate(pVo.getPartsRate() / 100);
                        	frtPreParts.setPartsRateAmount(pVo.getPartsRateAmount());
                        	partsRateAmount += pVo.getPartsRateAmount();
                    	}else{
                    		BasClaimsType bct = basClaimsTypeDao.get(BasClaimsType.class, pVo.getClaimsId());
                    		if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString())){
                    			frtPreParts.setPartsRate(pVo.getPartsRate() / 100);
                    			frtPreParts.setPartsRateAmount(pVo.getPartsRateAmount());
                    			partsRateAmount += pVo.getPartsRateAmount();
                    		}
                    	}
                    }
                }
            }
        }
        double tempCount = 0d;
        for (FrtPreParts frtPreParts : set1){
            tempCount += frtPreParts.getPartsRateAmount();
        }
        fpc.setFrtPrePartses(set1);
        return partsRateAmount;
    }
    /**
     * 根据结算单号查找其他费用信息
     * */
    
    public Datagrid findPreCostById(String preclrId) throws Exception
    {
        String sql = "SELECT fpcc.* FROM frt_pre_clearing_cost fpcc where 1=1";
        if (preclrId != null && !"".equals(preclrId.trim())){
            sql += " and fpcc.PRECLR_ID ='" + preclrId.trim() + "'";
        }
        List<CostVo> rows = new ArrayList<CostVo>();
        List<Object[]> rowsList = frtPreCostDao.createSQLQuery(sql, null);
        if (rowsList != null && rowsList.size() > 0)
        {
            for (Object[] obj : rowsList)
            {
                CostVo iVo = new CostVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                {
                    iVo.setCostId(Short.parseShort(obj[0].toString()));
                }
                if (obj[1] != null && obj[1].toString().length() > 0)
                {
                    iVo.setCostItem(obj[1].toString());
                }
                if (obj[2] != null && obj[2].toString().length() > 0)
                {
                    iVo.setCostAmount(new Double(obj[2].toString()));
                }
                if (obj[3] != null && obj[3].toString().length() > 0)
                {
                    iVo.setCostExplain(obj[3].toString());
                }
                rows.add(iVo);
            }
        }
        Datagrid dg = new Datagrid();
        if (rows != null && rows.size() > 0){
            dg.setRows(rows);
            dg.setTotal(rows.size());
        }else{
            dg.setRows(new ArrayList());
            dg.setTotal(0);
        }
        return dg;
    }

    /**
     * 交车结算单转车间返工
     * */
    @Log(moduleName = "前台管理", content = "交车结算单转车间返工", opertype = "更新/转车间返工")
    
    public Msg modifyBack(FrtPreClearingVo fpcVo) throws Exception
    {
        Msg msg = new Msg();
        try{
            FrtPreClearing fpc = frtPreClearingDao.get(FrtPreClearing.class,fpcVo.getPreclrId());
            frtPreClearingDao.updateBatch("DELETE FROM frt_pre_parts WHERE PRECLR_ID='"+ fpcVo.getPreclrId() + "'");
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < fpc.getReceptionId().length(); i++) {
     			if(!(fpc.getReceptionId().charAt(i)>='0'&&fpc.getReceptionId().charAt(i)<='9')){
     				sb.append(fpc.getReceptionId().charAt(i));
     			}
     		}
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){//为前台接车单号
            	frtPreClearingDao.updateBatch("DELETE FROM frt_pre_wktime WHERE PRECLR_ID='"+ fpcVo.getPreclrId() + "'");
                frtPreClearingDao.updateBatch("DELETE FROM frt_pre_clearing_cost WHERE PRECLR_ID='"+ fpcVo.getPreclrId() + "'");
            }
            fpc.setPreclrOtherAmount(0.00d);
            fpc.setPreclrRealAmount(0.00d);
            fpc.setPreclrSumAmount(0.00d);
            fpc.setPreclrWktimeAmount(0.00d);
            fpc.setPreclrManagementFee(0.00d);
            fpc.setPreMprMatAmount(0.00d);
            fpc.setPreclrTime(null);
            frtPreClearingDao.merge(fpc);
            if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){//为前台接车单号
//            	FrtReception fr = frtReceptionDao.get(FrtReception.class, fpcVo.getReceptionId());
//                fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState1);//修改工单状态为待派工
//                fr.setCorrection(Contstants.WORK_TAG.WORKYES);//修改返工状态为：返工
//                fr.setReceptionFactTime(null);
//                frtReceptionDao.merge(fr);
//                frtReceptionDao.flush();
                frtReceptionDao.executeSQL("UPDATE frt_reception SET RECEPTION_STATUS='"+Contstants.DOCUMENT_TAG.DOCUMENTState1+"',CORRECTION="+Contstants.WORK_TAG.WORKYES+",RECEPTION_FACT_TIME=NULL WHERE reception_id='"+fpcVo.getReceptionId()+"'");
            }else{
            	StSellOrder sso=stSellOrderDAO.get(StSellOrder.class, fpc.getReceptionId());
            	sso.setPreclrState("未结算");
            	stSellOrderDAO.merge(sso);
            }
            msg.setMsg("结算单返工成功！");
            msg.setSuccess(true);
        }catch(Exception es){
            msg.setMsg("结算单返工失败！");
            es.printStackTrace();
        }
        return msg;
    }

    /**
     * 交车结算单转收银
     * */
    @Log(moduleName = "前台管理", content = "交车结算单转收银", opertype = "更新/转收银")
    
    public Msg modifyTransformMoney(FrtPreClearingVo fpcVo) throws Exception
    {
        Msg msg = new Msg();
        try{
            FinMaintenanceReceivables fmr = new FinMaintenanceReceivables();//
            FrtPreClearing fpc=frtPreClearingDao.get(" from FrtPreClearing fr where fr.receptionId='"+fpcVo.getReceptionId()+"'");
            if (isFull(fpc))
                msg.setMsg("信息不完整，请补全信息后再进行相关操作！");
            else{
                fpc.setPreclrToMoney(Contstants.TOMONEY_TAG.TOMONEYYES);//结算状态为已转收银
                frtPreClearingDao.merge(fpc);//修改结算表结算状态信息
                fmr.setMrId(CreateID.createId("FinMaintenanceReceivables",Contstants.SERVICESUITGATHERING));//维修应收款编号
                fmr.setPreclrId(fpc.getPreclrId());//前台结算编号
                fmr.setMrReceivables(fpc.getPreclrRealAmount());//应收款金额
                fmr.setMrCumulative(0.00d);//累计收款
                fmr.setMrSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);//是否代付
                fmr.setMrUnFinished(Contstants.OPINIONFINISHED_TAG.UNPAYMENT);//是否付清
                fmr.setMrBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);//批量收款标识
                fmr.setMrArrears(fpc.getPreclrRealAmount());//欠款
                fmr.setEnterpriseId(fpcVo.getEnterpriseId());
                finMaintenanceReceivablesDao.save(fmr);
                FrtReception fr=frtReceptionDao.get(FrtReception.class, fpc.getReceptionId());
                if(fr!=null&&!fr.equals("")){
                	FrtCar fc = frtCarDao.get(FrtCar.class, fr.getFrtCar().getCarId());
                    Short tag = fr.getReptype().getReptClass();
                    if (fr.getReptype().getReptId().toString().equals(frtService.getDefaultFirstmaintain()))// 首保
                        fc.setCarFstInsuranceDate(fr.getInterDate());
                    if (tag.toString().equals(Contstants.SERVICESORT.SERVICESORTASSERVICE.toString()))// 维修
                    {
                        fc.setCarLastRepairDate(fr.getInterDate());
                        fc.setCarLastRepairDistance(fr.getReceptionDistance());
                        if (fc.getCarRepairCnt() == null)
                            fc.setCarRepairCnt(0);
                        fc.setCarRepairCnt(fc.getCarRepairCnt() + 1);
                    }else if (tag.toString().equals(Contstants.SERVICESORT.SERVICESORTASMAINTAIN.toString()))// 保养
                    {
                        fc.setCarLastMaintDate(fr.getInterDate());
                        fc.setCarLastMaintDistance(fr.getReceptionDistance());
                        if (fc.getCarMaintCnt() == null)
                            fc.setCarMaintCnt(0);
                        fc.setCarMaintCnt(fc.getCarMaintCnt() + 1);
                        modifyMaint(fc);//保养信息更改
                    }
                    frtCarDao.merge(fc);//修改车辆档案表
                    avgDistance(fc,fr);
                    frtCarDao.merge(fc);//车辆档案信息修改
                    fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState13);
                    frtReceptionDao.merge(fr);//修改工单状态为已结算
                }else{
                	StSellOrder sso=stSellOrderDAO.get(StSellOrder.class, fpc.getReceptionId());
                	if(sso!=null&&!sso.equals("")){
                		sso.setPreclrState("已结算");
                		stSellOrderDAO.merge(sso);
                	}
                }
                msg.setSuccess(true);
                msg.setMsg("转收银成功!");
            }
        }
        catch(Exception es){
            msg.setMsg("转收银失败！");
        }
        return msg;
    }
    
    
    private void avgDistance(FrtCar fc,FrtReception fr) throws Exception{
    	StringBuffer sb=new StringBuffer("(SELECT fr.reception_distance,fr.inter_date");
    	sb.append(" FROM frt_reception fr INNER JOIN (");
    	sb.append(" SELECT fr.reception_id,fr.inter_date FROM frt_pre_clearing fpc,frt_reception fr");		
    	sb.append(" WHERE fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fr.car_id='"+fc.getCarId()+"') aa");		
    	sb.append(" ON aa.reception_id=fr.reception_id  where  fr.enterprise_id="+fr.getEnterpriseId());		
    	sb.append(" ORDER BY fr.inter_date ASC LIMIT 1)");	
    	
    	List<Object[]> list=frtCarDao.createSQLQuery(sb.toString());
    	int firstDistance=0;
    	int lastDistance=0;
    	Date firstInterDate=null;
    	Date lastInterDate=null;
    	int mistake=1;
    	if(list!=null&&list.size()>0){
    		if(list.get(0)[0]!=null&&list.get(0)[0].toString().length()>0)
    			firstDistance=Integer.parseInt(list.get(0)[0].toString());
    		if(list.get(0)[1]!=null&&list.get(0)[1].toString().length()>0)
    			firstInterDate=(Date)list.get(0)[1];
    	}
    	if(fr.getReceptionDistance()!=null)
    		lastDistance=fr.getReceptionDistance();
    	if(fr.getInterDate()!=null)
    		lastInterDate=fr.getInterDate();
    	if(firstInterDate!=null&&lastInterDate!=null)
    		mistake=getDays(lastInterDate)-getDays(firstInterDate);
    	if(mistake==0)
    		mistake=1;
    	int avg=(lastDistance-firstDistance)/mistake;
    	fc.setCarDistancePerDay(avg);
    }
    private Integer getDays(Date date){
    	long count=date.getTime();
    	long count2=count/1000/60/60/24;
    	return Integer.parseInt(count2+"");
    }
    private void modifyMaint(FrtCar fc) throws Exception{
    	Integer count=fc.getCarMaintInterva();
    	int lastDistance=fc.getCarLastMaintDistance();
        fc.getCarLastMaintDate();
        int distance=0;
        if(fc.getBasCarType().getBasCarBrand().getCbrdDistance()==null||fc.getBasCarType().getBasCarBrand().getCbrdDistance().toString().length()==0){
        	distance=Integer.parseInt(frtService.getDefaultMaintDistance());        	
        }else{
        	distance=fc.getBasCarType().getBasCarBrand().getCbrdDistance();
        }
        int days=0;
        if(fc.getBasCarType().getBasCarBrand().getCbrdDays()==null||fc.getBasCarType().getBasCarBrand().getCbrdDays().toString().length()==0){
        	days=Integer.parseInt(frtService.getDefaultMaintDays());        	
        }else{
        	days=fc.getBasCarType().getBasCarBrand().getCbrdDays();
        }
       int avg=fc.getCarDistancePerDay();
       if(avg==0)
    	   avg=1;
       int mistake=distance/avg;
       if(count!=null&&count.toString().length()>0){
    	   if(mistake>count)
    		   mistake=count;
       }else{
    	   if(mistake>days)
    		   mistake=days;
       }
       Date dd=(Date) fc.getCarLastMaintDate().clone();
       dd.setDate(fc.getCarLastMaintDate().getDate()+mistake);
       fc.setCarNextMaintDate(dd);
       fc.setCarNextMaintDistance(lastDistance+distance);
    }
    private Boolean isFull(FrtPreClearing fpc)
    {
        Boolean flag = false;
        if (fpc.getPreclrTime() == null
                || fpc.getPreclrTime().toString().length() == 0)
        {
            flag = true;
        }
        else if (fpc.getPreclrInoiceType() == null
                || fpc.getPreclrInoiceType().toString().length() == 0)
        {
            flag = true;
        }
        else if (fpc.getStfId() == null
                || fpc.getStfId().toString().length() == 0)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 转洗车或待交车
     * */
    
    public Msg modifyTransFormReceptionState(FrtPreClearingVo fpcVo)
            throws Exception
    {
            if (fpcVo.getReceptionStatus().equals(
                    Contstants.DOCUMENT_TAG.DOCUMENTState9))
            {
            	return modifyTransFormReceptionStateASNine(fpcVo);
            }
            else if (fpcVo.getReceptionStatus().equals(
                    Contstants.DOCUMENT_TAG.DOCUMENTState10))
            {
            	return modifyTransFormReceptionStateASTen(fpcVo);
            }
            return null;
    }
    /**
     * 交车结算单转洗车
     * */
    @Log(moduleName = "前台管理", content = "交车结算单转洗车", opertype = "更新/转洗车")
    private Msg modifyTransFormReceptionStateASNine(FrtPreClearingVo fpcVo){
    	Msg msg = new Msg();
        try
        {
            FrtReception fr = frtReceptionDao.get(FrtReception.class, fpcVo
                    .getReceptionId());
            fr.setReceptionStatus(fpcVo.getReceptionStatus());
            msg.setSuccess(true);
            msg.setMsg("转洗车成功！");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("转洗车失败！");
        }
        return msg;
    }
    /**
     * 交车结算单转待交车
     * */
    @Log(moduleName = "前台管理", content = "交车结算单转待交车", opertype = "更新/转待交车")
    private Msg modifyTransFormReceptionStateASTen(FrtPreClearingVo fpcVo){
    	Msg msg = new Msg();
        try
        {
            FrtReception fr = frtReceptionDao.get(FrtReception.class, fpcVo
                    .getReceptionId());
            fr.setReceptionStatus(fpcVo.getReceptionStatus());
            msg.setSuccess(true);
            msg.setMsg("转待交车成功！");
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            msg.setMsg("转待交车失败！");
        }
        return msg;
    }
    
    public List totemoney(FrtPreClearingVo fpcVo) throws Exception
    {
    	FrtPreClearing fpc = frtPreClearingDao.get(FrtPreClearing.class, fpcVo
    			.getPreclrId());
    	 StringBuffer sb=new StringBuffer();
         for (int i = 0; i < fpc.getReceptionId().length(); i++) {
 			if(!(fpc.getReceptionId().charAt(i)>='0'&&fpc.getReceptionId().charAt(i)<='9')){
 				sb.append(fpc.getReceptionId().charAt(i));
 			}
 		 }
         if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
             String hql = "from FrtReception where receptionId = '"+fpc.getReceptionId()+"'";
        	 List<FrtReception> frps=frtReceptionDao.find(hql);
        	 if(frps != null && frps.size() > 0){
        	     FrtReception frp = frps.get(0);
            	 if(frp.getReptype().getReptId().toString().equals(frtService.getDefaultFirstmaintain())){
            		 List list = new ArrayList();
            		 list.add(0d);
            		 list.add(0d);
            		 list.add(0d);
            		 list.add(0d);
            		 list.add(0d);
            		 list.add(0d);
            		 return list;
            	 } 
        	 }
         }
        List<PartsVo> partsList = null;
        String parts = fpcVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        double sumMoney = 0;
        double preclrWktimeAmount = 0;
        double preMprMatAmount = 0;
        double otherAmount = 0;
        List<PartsVo> list1 = partsList;
        Set<FrtPreWktime> list2 = fpc.getFrtPreWktimes();
        Set<FrtPreClearingCost> list3 = fpc.getFrtPreClearingCosts();
        if (list1 != null && list1.size() > 0)
            for (PartsVo partsVo : list1)
            {
            	if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
            		BasClaimsType bct = basClaimsTypeDao.get(
            				BasClaimsType.class, partsVo.getClaimsId());
            		if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString()))
            		{
            			preMprMatAmount += partsVo.getPartsRateAmount();
            		}            		
            	}else{
            		preMprMatAmount += partsVo.getPartsRateAmount();
            	}
            }
        if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
        	if (list2 != null && list2.size() > 0)
        		for (FrtPreWktime itemVo : list2)
        		{
        			BasClaimsType bct = basClaimsTypeDao.get(
        					BasClaimsType.class, itemVo.getClaimsType());
        			if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString()))
        			{
        				preclrWktimeAmount += itemVo.getWktimeAmount();
        			}
        		}
        	if (list3 != null && list3.size() > 0)
        		for (FrtPreClearingCost costVo : list3)
        		{
        			otherAmount += costVo.getCostAmount();
        		}        	
        }
        double preclrManagementFee = 0.00d;
        if(sb.toString().equals(Contstants.PROSCENIUMNEARID)){
        	preclrManagementFee = fpc.getPreclrManagementFee();
        }

        sumMoney += preclrWktimeAmount;
        sumMoney += preMprMatAmount;
        sumMoney += otherAmount;
        sumMoney += preclrManagementFee;
        List list = new ArrayList();
        list.add(preclrWktimeAmount);
        list.add(preMprMatAmount);
        // 管理费
        double realAmount = 0.00d;
        list.add(preclrManagementFee);
        list.add(otherAmount);
        list.add(fpc.getPreclrSumAmount());
        list.add(sumMoney);
        return list;
    }
    
    /**
     * 判断结算单是否已收银
     */
    public boolean doIsExist(FrtPreClearingVo fpcVo)throws Exception{
    	String hql=" from FinMaintenanceReceivables fmr where fmr.preclrId='"+fpcVo.getPreclrId()+"'";
    	FinMaintenanceReceivables fmr=finMaintenanceReceivablesDao.get(hql);
    	if(fmr!=null&&!fmr.equals(""))
    		return true;
    	return false;
    }
    
    

}
