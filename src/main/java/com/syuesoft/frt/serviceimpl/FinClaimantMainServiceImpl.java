package com.syuesoft.frt.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INDEMNITYS;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.fin.dao.FinClaimantPartsDao;
import com.syuesoft.fin.dao.FinClaimsReceivablesDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FinClaimantMainCostDao;
import com.syuesoft.frt.dao.FinClaimantMainDao;
import com.syuesoft.frt.dao.FinClaimantTimeDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.service.FinClaimantMainService;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.FinClaimantMainVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.model.FinClaimantMain;
import com.syuesoft.model.FinClaimantMainCost;
import com.syuesoft.model.FinClaimantParts;
import com.syuesoft.model.FinClaimantTime;
import com.syuesoft.model.FinClaimsReceivables;
import com.syuesoft.model.FrtReception;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.IncrementId;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;

/**
 * 索理赔结算单service
 * 
 * @author Liujian
 * 
 */
@Transactional
@Service("finClaimantMainService")
public class FinClaimantMainServiceImpl extends BaseLogServiceImpl implements
        FinClaimantMainService
{
	@Autowired
    private FinClaimantPartsDao finClaimantPartsDao;
    @Autowired
    private FinClaimantMainCostDao finClaimantMainCostDao;
    @Autowired
    private FinClaimantTimeDao finClaimantTimeDao;
    @Autowired
    private FinClaimantMainDao finClaimantMainDao;
    @Autowired
    private FrtReceptionDao frtReceptionDao;
    @Autowired
    private FinClaimsReceivablesDao finClaimsReceivablesDao;
    @Autowired
    private FrtService frtService;
    @Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    /**
     * 增加所理赔信息
     * */
    @Log(moduleName = "前台管理", content = "新增索理赔单", opertype = "新增")
    
    public synchronized Msg save(FinClaimantMainVo fcmVo) throws Exception
    {
        List<FrtItemVo> itemList = null;
        List<PartsVo> partsList = null;
        List<FinClaimantMainCost> costList = null;
        String items = fcmVo.getItems();
        if (items != null && items.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (itemList == null)
            itemList = new ArrayList<FrtItemVo>();
        String parts = fcmVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        if (partsList == null)
            partsList = new ArrayList<PartsVo>();
        String others = fcmVo.getOthers();
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            costList = JSON.parseArray(jsOthers.get("rows").toString(),
                    FinClaimantMainCost.class);
        }
        if (costList == null)
            costList = new ArrayList<FinClaimantMainCost>();
        Msg msg = new Msg();
        FinClaimantMain fcm = new FinClaimantMain();
        // BeanUtils.copyProperties(fcmVo, fcm);
        MyBeanUtils.getInstance().copyBeans(fcmVo, fcm);
        try
        {
            fcm.setClaimantmId(CreateID.createId("FinClaimantMain",Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID));
            FrtReception frt = frtReceptionDao.get(FrtReception.class, fcmVo.getReceptionId());
            frt.setFinTag(Contstants.CLAIM_TAG.CLAIMYES);
            if (frt.getFinAllTag().equals(Contstants.CLAIM_TAG.CLAIMNO))
                frt.setFinAllTag(Contstants.CLAIM_TAG.CLAIMYES);
            if (fcmVo.getClaimantmInvoiceTime() != null&& fcmVo.getClaimantmInvoiceTime().toString().length() > 0)
            {
                if (!fcmVo.getClaimantmInvoiceType().equals(Contstants.RECEIPT_TAG.OTHERTAX))
                    fcm.setClaimantmInvoiceTime(MyBeanUtils.getInstance().getDate(fcmVo.getClaimantmInvoiceTime()));
                else
                    fcm.setClaimantmInvoiceTime(null);
            }
            fcm.setClaimantmTag(Contstants.DELETE_TAG.DELETENO);
            fcm.setClaimantmToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
            if (fcm.getClaimantmOtherAmount() == null)
                fcm.setClaimantmOtherAmount(0.00d);
            if (fcm.getClaimantmManagementFee() == null)
                fcm.setClaimantmManagementFee(0.00d);
            if (fcm.getClaimantmAmount() == null)
            {
                fcm.setClaimantmAmount(0.00d);
            }
            if (fcm.getClaimantmPartsAmount() == null)
                fcm.setClaimantmPartsAmount(0.00);
            if (fcm.getClaimantmTimeAmount() == null)
                fcm.setClaimantmTimeAmount(0.00);
            fcm.setClaimantmStatus(Contstants.AUDIT_TAG.AUDITNOS.toString());
            fcm.setEnterpriseId(fcmVo.getEnterpriseId());
            finClaimantMainDao.save(fcm);
            saveData(itemList, partsList, costList, fcm);
            finClaimantMainDao.merge(fcm);
            sumMoney(fcm);
            finClaimantMainDao.merge(fcm);
            msg.setMsg("增加索理赔单成功！");
            msg.setSuccess(true);
        }
        catch(Exception es)
        {
            msg.setMsg("增加索理赔单失败！");
            es.printStackTrace();
        }
        return msg;
    }
    private void saveData(List<FrtItemVo> itemList, List<PartsVo> partsList,
            List<FinClaimantMainCost> costList, FinClaimantMain fcm)
            throws Exception
    {
        List<FinClaimantMainCost> list3 = costList;
        FinClaimantMainCost fcmc = null;
        if(list3!=null&&list3.size()>0)
        for (FinClaimantMainCost finClaimantMainCost : list3)
        {
            fcmc = finClaimantMainCost;
            fcmc.setFinClaimantMain(fcm);
            finClaimantMainCostDao.save(fcmc);
            fcm.getFinClaimantMainCosts().add(fcmc);
        }
        List<PartsVo> list2 = partsList;
        FinClaimantParts fcp = null;
        if(list2!=null&&list2.size()>0)
        for (PartsVo partsVo : list2)
        {
        	if(partsVo.getPartsTaxCost()==null||partsVo.getPartsTaxCost().toString().length()==0)
        		partsVo.setPartsTaxCost(0.00d);
        	if(partsVo.getPartsNoTaxCost()==null||partsVo.getPartsNoTaxCost().toString().length()==0)
        		partsVo.setPartsNoTaxCost(0.00d);
            fcp = new FinClaimantParts();
            fcp.setPartsId(partsVo.getPartsId());
            fcp.setPartsName(partsVo.getPartsName());
            fcp.setClaimantpPrice(partsVo.getPartsRepairPrice());
            fcp.setClaimantpCount(partsVo.getPartsNum());
            fcp.setClaimantpAmount(partsVo.getPartsAmount());
            fcp.setRelcampId(partsVo.getRelcampId());
            fcp.setReptypId(partsVo.getReptypId());
            fcp.setStoreId(partsVo.getStoreId());
            fcp.setClaimantpTaxCost(partsVo.getPartsTaxCost());
            fcp.setClaimantpNoTaxCost(partsVo.getPartsNoTaxCost());
            fcp.setFinClaimantMain(fcm);
            finClaimantPartsDao.save(fcp);
            fcm.getFinClaimantPartses().add(fcp);
        }
        List<FrtItemVo> list1 = itemList;
        FinClaimantTime fct = null;
        if(list1!=null&&list1.size()>0)
        for (FrtItemVo itemVo : list1)
        {
            fct = new FinClaimantTime();
            fct.setRepitemId(itemVo.getRepitemId());
            fct.setRepitemName(itemVo.getRepitemName());
            fct.setClaimanttAmount(itemVo.getRepitemAmount());
            fct.setClaimanttTime(itemVo.getRepitemTime());
            fct.setClaimanttInnerTime(itemVo.getInternalTime());
            fct.setStfId(itemVo.getStfId());
            fct.setReptypId(itemVo.getReptypId());
            fct.setFinClaimantMain(fcm);
            finClaimantTimeDao.save(fct);
            fcm.getFinClaimantTimes().add(fct);
        }
    }

    
    public List isExist(String receptionId) throws Exception
    {
        return finClaimantMainDao.isExist(receptionId);
    }

    /**
     * 删除索理赔单
     * */
    @Log(moduleName = "前台管理", content = "删除索理赔单", opertype = "删除")
    
    public Msg delete(FinClaimantMainVo fcmVo) throws Exception
    {
        // finClaimantMainDao.delete(claimantmId);
        Msg msg = new Msg();
        try
        {
            FrtReception frt = frtReceptionDao.get(FrtReception.class, fcmVo
                    .getReceptionId());
            frt.setFinTag(Contstants.CLAIM_TAG.CLAIMNO);
            if (frt.getFinAllTag().equals(Contstants.CLAIM_TAG.CLAIMYES))
            {
                frt.setFinAllTag(Contstants.CLAIM_TAG.CLAIMNO);
            }
            FinClaimantMain fcm = finClaimantMainDao.get(FinClaimantMain.class,
                    fcmVo.getClaimantmId());
            fcm.setClaimantmTag(Contstants.DELETE_TAG.DELETEYES);
            finClaimantMainDao.merge(fcm);
            msg.setMsg("删除索赔结算单成功！");
            msg.setSuccess(true);
        }
        catch(Exception es)
        {
            msg.setMsg("删除索赔结算单失败！");
            es.printStackTrace();
        }
        return msg;
    }

    /**
     * 索理赔单datagrid
     * */
    @Transactional
    public Datagrid datagridFinClaimantMain(FinClaimantMainVo fcmVo)
            throws Exception
    {
        List<FinClaimantMainVo> rows = new ArrayList<FinClaimantMainVo>();
        StringBuffer sql = new StringBuffer("SELECT d.*,e.claimantmStatusName FROM (SELECT a.*,");
        sql.append(" c.tempdata2 AS claimantmInvoiceTypeName,b.tempdata1 AS claimantmCheckStfName FROM");
        sql.append(" (SELECT CLAIMANTM_ID AS claimantmId,fcm.PRECLR_ID AS preclrId,fpc.RECEPTION_ID AS receptionId,fcr.CAR_ID AS carId,");
        sql.append(" CAR_LICENSE AS carLicense,CAR_VIN AS carVin,CAR_MOTOR_ID AS carMotorId,");
        sql.append(" CUSTOM_NAME AS customName,CLAIMANTM_TIME AS claimantmTime,CLAIMANTM_INVOICE_TYPE AS claimantmInvoiceType,");
        sql.append(" CLAIMANTM_INVOICE_TIME AS claimantmInvoiceTime,CLAIMANTM_INVOICE_NO AS claimantmInvoiceNo,");
        sql.append(" CLAIMANTM_CLR_STF_ID AS claimantmClrStfId,CLAIMANTM_PARTS_AMOUNT AS claimantmPartsAmount,");
        sql.append(" CLAIMANTM_TIME_AMOUNT AS claimantmTimeAmount,CLAIMANTM_OTHER_AMOUNT AS claimantmOtherAmount,");
        sql.append(" CLAIMANTM_MANAGEMENT_FEE AS claimantmManagementFee,CLAIMANTM_AMOUNT AS claimantmAmount,");
        sql.append(" CLAIMANTM_STATUS AS claimantmStatus,");
        sql.append(" CLAIMANTM_CHECK_STF_ID AS claimantmCheckStfId,CLAIMANTM_REMARK AS claimantmRemark,");
        sql.append(" bs1.STF_NAME AS claimantmClrStfName,fcm.CLAIMANTM_TO_MONEY as claimantmToMoney,");
        
        sql.append(" fcm.CLAIMANTM_NOTAX_COST as claimantmNoTaxCost,fcm.CLAIMANTM_TAX_COST as claimantmTaxCost, bcs.dataValue");
        
        sql.append(" FROM frt_custom fc,fin_claimant_main fcm,frt_car fcr,frt_reception fr,frt_pre_clearing fpc,bas_stuff bs1,bas_parentdictionary bps,bas_childdictionary bcs");
        sql.append(" WHERE fc.CUSTOM_ID=fcr.CUSTOM_ID and fc.enterprise_id="+fcmVo.getEnterpriseId());
        sql.append(" AND fcm.CLAIMANTM_TAG="
                        + Contstants.DELETE_TAG.DELETENO
                        + " AND fcm.PRECLR_ID=fpc.PRECLR_ID AND fr.RECEPTION_ID=fpc.RECEPTION_ID AND fr.CAR_ID=fcr.CAR_ID");
        sql.append(" AND fr.FIN_TAG=" + Contstants.CLAIM_TAG.CLAIMYES+ " ");
        
        sql.append(" AND fr.FIN_TAG=" + Contstants.CLAIM_TAG.CLAIMYES + "");
        
        sql.append(" AND bs1.STF_ID=fcm.CLAIMANTM_CLR_STF_ID AND bcs.parent_id=bps.parent_id AND bps.dataKey='"+Contstants.TOMONEY_TAG.TOMONEY_TAGKEY
                +"' AND fcm.CLAIMANTM_TO_MONEY = bcs.dataKey)a");
        sql.append(" LEFT OUTER JOIN (SELECT bs1.STF_ID AS stfId,bs1.STF_NAME AS tempdata1 FROM bas_stuff bs1)b");
        sql.append(" ON a.claimantmCheckStfId=b.stfId LEFT OUTER JOIN");
        sql.append(" (SELECT bc.dataKey,bc.dataValue AS tempdata2 FROM bas_parentdictionary bp,bas_childdictionary bc");
        sql.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                + Contstants.RECEIPT_TAG.RECEIPTKEY + "')c");
        sql.append(" ON a.claimantmInvoiceType=c.datakey)d,");
        sql.append(" (SELECT bcs.dataKey,bcs.dataValue AS claimantmStatusName FROM bas_parentdictionary bps,bas_childdictionary bcs");
        sql.append(" WHERE bcs.parent_id=bps.parent_id AND bps.dataKey='"
                + Contstants.AUDIT_TAG.AUDITKEY + "'");
        sql.append(" )e WHERE d.claimantmStatus=e.datakey");

        if (fcmVo.getPreclrId() != null && !"".equals(fcmVo.getPreclrId()))
        {
            sql.append(" and d.preclrId like '%"
                    + StringEscapeUtils
                            .escapeSql(fcmVo.getClaimantmId().trim()) + "%'");
        }
        if (fcmVo.getReceptionId() != null
                && !"".equals(fcmVo.getReceptionId()))
        {
            sql.append(" and d.receptionId like '%"
                    + StringEscapeUtils
                            .escapeSql(fcmVo.getReceptionId().trim()) + "%'");
        }
        if (fcmVo.getCarId() != null && !"".equals(fcmVo.getCarId()))
        {
            sql.append(" and d.carLicense like '%"
                    + StringEscapeUtils.escapeSql(fcmVo.getCarId().trim())
                    + "%'");
        }
        if (fcmVo.getCarVin() != null && !"".equals(fcmVo.getCarVin()))
        {
            sql.append(" and d.carVin like '%"
                    + StringEscapeUtils.escapeSql(fcmVo.getCarVin().trim())
                    + "%'");
        }
        if (fcmVo.getCarMotorId() != null && !"".equals(fcmVo.getCarMotorId()))
        {
            sql.append(" and d.carMotorId like '%"
                    + StringEscapeUtils.escapeSql(fcmVo.getCarMotorId().trim())
                    + "%'");
        }
        if (fcmVo.getCustomName() != null && !"".equals(fcmVo.getCustomName()))
        {
            sql.append(" and d.customName like '%"
                    + StringEscapeUtils.escapeSql(fcmVo.getCustomName().trim())
                    + "%'");
        }
        if (fcmVo.getClaimantmTimeBegin() != null
                && !"".equals(fcmVo.getClaimantmTimeBegin()))
        {
            sql.append(" and d.claimantmTime>= '"
                    + fcmVo.getClaimantmTimeBegin() + "'");
        }
        if (fcmVo.getClaimantmTimeEnd() != null
                && !"".equals(fcmVo.getClaimantmTimeEnd()))
        {
            sql.append(" and d.claimantmTime <= '"
                    + fcmVo.getClaimantmTimeEnd() + "'");
        }
        if(fcmVo.getClaimantmTimeBegin() == null && fcmVo.getClaimantmTimeEnd() == null){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,fcmVo.getEnterpriseId() ).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sql.append(" and DATE_FORMAT(d.claimantmTime,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        	}else{
        		sql.append(" and DATE_FORMAT(d.claimantmTime,'%Y-%m-%d %H-%i-%s')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	
        	sql.append(" and DATE_FORMAT(d.claimantmTime,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
        if (fcmVo.getClaimantmInvoiceTimeBegin() != null
                && !"".equals(fcmVo.getClaimantmInvoiceTimeBegin()))
        {
            sql.append(" and d.claimantmInvoiceTime >= '"
                    + fcmVo.getClaimantmInvoiceTimeBegin() + "'");
        }
        if (fcmVo.getClaimantmInvoiceTimeEnd() != null
                && !"".equals(fcmVo.getClaimantmInvoiceTimeEnd()))
        {
            sql.append(" and d.claimantmInvoiceTime <= '"
                    + fcmVo.getClaimantmInvoiceTimeEnd() + "'");
        }
        if (fcmVo.getClaimantmInvoiceType() != null
                && !"".equals(fcmVo.getClaimantmInvoiceType()))
        {
            sql.append(" and d.claimantmInvoiceType = "
                    + fcmVo.getClaimantmInvoiceType());
        }
        if (fcmVo.getClaimantmInvoiceNo() != null
                && !"".equals(fcmVo.getClaimantmInvoiceNo()))
        {
            sql.append(" and d.claimantmInvoiceNo like '%"
                    + StringEscapeUtils.escapeSql(fcmVo.getClaimantmInvoiceNo()
                            .trim()) + "%'");
        }
        if (fcmVo.getClaimantmCheckStfId() != null
                && !"".equals(fcmVo.getClaimantmCheckStfId()))
        {
            sql.append(" and d.claimantmCheckStfId = "
                    + fcmVo.getClaimantmCheckStfId());
        }
        if (fcmVo.getClaimantmClrStfId() != null
                && !"".equals(fcmVo.getClaimantmClrStfId()))
        {
            sql.append(" and d.claimantmClrStfId = "
                    + fcmVo.getClaimantmClrStfId());
        }
        if (fcmVo.getSort() != null && !"".equals(fcmVo.getSort().trim())
                && fcmVo.getOrder() != null
                && !"".equals(fcmVo.getOrder().trim()))
        {
            sql.append(" order by d." + fcmVo.getSort().trim() + " "
                    + fcmVo.getOrder().trim());
        }
        FinClaimantMainVo fcmv = null;
        List<Object[]> rowsList = finClaimantMainDao.createSQLQuery(sql
                .toString(), null, fcmVo.getPage(), fcmVo.getRows());
        int total = finClaimantMainDao.getSQLCount(sql.toString(), null);
        try
        {
            if (rowsList != null && rowsList.size() > 0)
                for (Object[] obj : rowsList)
                {
                    fcmv = new FinClaimantMainVo();
                    if (obj[0] != null && obj[0].toString().length() > 0)
                        fcmv.setClaimantmId(obj[0].toString());
                    if (obj[1] != null && obj[1].toString().length() > 0)
                        fcmv.setPreclrId(obj[1].toString());
                    if (obj[2] != null && obj[2].toString().length() > 0)
                        fcmv.setReceptionId(obj[2].toString());
                    if (obj[3] != null && obj[3].toString().length() > 0)
                        fcmv.setCarId(obj[3].toString());
                    if (obj[4] != null && obj[4].toString().length() > 0)
                        fcmv.setCarLicense(obj[4].toString());
                    if (obj[5] != null && obj[5].toString().length() > 0)
                        fcmv.setCarVin(obj[5].toString());
                    if (obj[6] != null && obj[6].toString().length() > 0)
                        fcmv.setCarMotorId(obj[6].toString());
                    if (obj[7] != null && obj[7].toString().length() > 0)
                        fcmv.setCustomName(obj[7].toString());
                    if (obj[8] != null && obj[8].toString().length() > 0)
                        fcmv.setClaimantmTime(MyBeanUtils.getInstance()
                                .formatString(obj[8].toString()));
                    if (obj[9] != null && obj[9].toString().length() > 0)
                        fcmv.setClaimantmInvoiceType(obj[9].toString());
                    if (obj[10] != null && obj[10].toString().length() > 0)
                        fcmv.setClaimantmInvoiceTime(MyBeanUtils.getInstance()
                                .formatString(obj[10].toString()));
                    if (obj[11] != null && obj[11].toString().length() > 0)
                        fcmv.setClaimantmInvoiceNo(obj[11].toString());
                    if (obj[12] != null && obj[12].toString().length() > 0)
                        fcmv.setClaimantmClrStfId(Short.parseShort(obj[12]
                                .toString()));
                    if (obj[13] != null && obj[13].toString().length() > 0)
                        fcmv.setClaimantmPartsAmount(Double.parseDouble(obj[13]
                                .toString()));
                    else
                        fcmv.setClaimantmPartsAmount(0.00d);
                    if (obj[14] != null && obj[14].toString().length() > 0)
                        fcmv.setClaimantmTimeAmount(Double.parseDouble(obj[14]
                                .toString()));
                    else
                        fcmv.setClaimantmTimeAmount(0.00d);
                    if (obj[15] != null && obj[15].toString().length() > 0)
                        fcmv.setClaimantmOtherAmount(Double.parseDouble(obj[15]
                                .toString()));
                    else
                        fcmv.setClaimantmOtherAmount(0.00d);
                    if (obj[16] != null && obj[16].toString().length() > 0)
                        fcmv.setClaimantmManagementFee(Double
                                .parseDouble(obj[16].toString()));
                    else
                        fcmv.setClaimantmManagementFee(0.00d);
                    if (obj[17] != null && obj[17].toString().length() > 0)
                        fcmv.setClaimantmAmount(Double.parseDouble(obj[17]
                                .toString()));
                    else
                        fcmv.setClaimantmAmount(0.00d);
                    if (obj[18] != null && obj[18].toString().length() > 0)
                        fcmv.setClaimantmStatus((obj[18].toString()));
                    if (obj[19] != null && obj[19].toString().length() > 0)
                        fcmv.setClaimantmCheckStfId(Short.parseShort(obj[19]
                                .toString()));
                    if (obj[20] != null && obj[20].toString().length() > 0)
                        fcmv.setClaimantmRemark(obj[20].toString());
                    if (obj[21] != null && obj[21].toString().length() > 0)
                        fcmv.setClaimantmClrStfName(obj[21].toString());
                    if (obj[22] != null && obj[22].toString().length() > 0)
                        fcmv.setClaimantmToMoney(Short.parseShort(obj[22]
                                .toString()));
                    if (obj[23] != null && obj[23].toString().length() > 0)
                        fcmv.setClaimantmNoTaxCost(Double.parseDouble(obj[23].toString()));
                    if (obj[24] != null && obj[24].toString().length() > 0)
                        fcmv.setClaimantmTaxCost(Double.parseDouble(obj[24].toString()));
                    if (obj[25] != null && obj[25].toString().length() > 0)
                        fcmv.setMoneyStatusName(obj[25].toString());
                    if (obj[26] != null && obj[26].toString().length() > 0)
                        fcmv.setClaimantmInvoiceTypeName(obj[26].toString());
                    if (obj[27] != null && obj[27].toString().length() > 0)
                        fcmv.setClaimantmCheckStfName(obj[27].toString());
                    if (obj[28] != null && obj[28].toString().length() > 0)
                        fcmv.setClaimantmStatusName(obj[28].toString());
                    rows.add(fcmv);
                }
        }
        catch(Exception es){
            es.printStackTrace();
        }
        Datagrid dg = new Datagrid();
        dg.setTotal(total);
        dg.setRows(rows);

        return dg;
    }

    /**
     * 根据索理赔单号查找其他费用
     * */
    
    public Datagrid findCostByFcmId(FinClaimantMainVo fcmVo) throws Exception
    {
        // TODO Auto-generated method stub
        List<FinClaimantMainCost> costlist = new ArrayList();
        String hql = "from FinClaimantMainCost frp where 1=1 ";
        if (fcmVo.getClaimantmId() != null && !"".equals(fcmVo.getClaimantmId().trim()))
        {
            hql += " and frp.finClaimantMain.claimantmId ='" + fcmVo.getClaimantmId().trim()
                    + "'";
        }
        else
        {
            hql += " and frp.finClaimantMain.claimantmId ='-1'";
        }
        List<FinClaimantMainCost> rcptPartsList = finClaimantMainCostDao
                .find(hql);
        FinClaimantMainCost fcc = null;
        if (rcptPartsList != null && rcptPartsList.size() > 0)
            for (FinClaimantMainCost finClaimantMainCost : rcptPartsList)
            {
                fcc = new FinClaimantMainCost();
                fcc.setCostId(finClaimantMainCost.getCostId());
                fcc.setCostName(finClaimantMainCost.getCostName());
                fcc.setCostAmount(finClaimantMainCost.getCostAmount());
                fcc.setCostExplain(finClaimantMainCost.getCostExplain());
                costlist.add(fcc);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(costlist);
        dg.setTotal(costlist.size());
        return dg;
    }

    /**
     * 根据索理赔单号查找索赔项目
     * */
    
    public Datagrid findItemByFcmId(FinClaimantMainVo fcmVo) throws Exception
    {
        List<FrtItemVo> itemlist = new ArrayList<FrtItemVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fct.REPITEM_ID,fct.REPITEM_NAME,fct.CLAIMANTT_TIME,");
        sb.append(" fct.CLAIMANTT_INNER_TIME,fct.CLAIMANTT_AMOUNT,fct.STF_ID,bs.STF_NAME,fct.REPTYP_ID,brt.REPTYP_NAME");
        sb.append(" FROM fin_claimant_time fct,bas_stuff bs,bas_repair_type brt");
        sb.append(" WHERE  bs.STF_ID=fct.STF_ID AND brt.REPTYP_ID=fct.REPTYP_ID and brt.enterprise_id="+fcmVo.getEnterpriseId());
        if (fcmVo.getClaimantmId() != null && !"".equals(fcmVo.getClaimantmId().trim()))
            sb.append(" and fct.CLAIMANTM_ID ='" + fcmVo.getClaimantmId().trim() + "'");
        else
            sb.append(" and fct.CLAIMANTM_ID ='-1'");
        List<Object[]> rcptPartsList = finClaimantTimeDao.createSQLQuery(sb.toString(), null);
        FrtItemVo fct = null;
        if (rcptPartsList != null && rcptPartsList.size() > 0)
            for (Object[] objects : rcptPartsList)
            {
                fct = new FrtItemVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fct.setRepitemId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fct.setRepitemName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fct.setRepitemTime(Double.parseDouble(objects[2].toString()));
                else
                    fct.setRepitemTime(0.00d);
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fct.setInternalTime(Double.parseDouble(objects[3].toString()));
                else
                    fct.setInternalTime(0.00d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fct.setRepitemAmount(Double.parseDouble(objects[4].toString()));
                else
                    fct.setRepitemAmount(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fct.setStfId(Short.parseShort(objects[5].toString()));
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fct.setStfName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fct.setReptypId(Short.parseShort(objects[7].toString()));
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fct.setReptypName(objects[8].toString());
                itemlist.add(fct);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(itemlist);
        dg.setTotal(itemlist.size());
        return dg;
    }

    /**
     * 根据索理赔单号查找索赔配件
     * */
    
    public Datagrid findPartsByFcmId(FinClaimantMainVo cmVo) throws Exception
    {
        List<PartsVo> partsList = new ArrayList<PartsVo>();
        StringBuffer sb = new StringBuffer("SELECT DISTINCT fcp.PARTS_ID,fcp.PARTS_NAME,fcp.RELCAMP_ID,fcp.CLAIMANTP_COUNT,");
        sb.append(" fcp.CLAIMANTP_PRICE,fcp.CLAIMANTP_AMOUNT,fcp.REPTYP_ID,brt.REPTYP_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,");
        sb.append(" bsh.STORE_ID,bsh.STORE_NAME,fcp.CLAIMANTP_TAX_COST,fcp.CLAIMANTP_NOTAX_COST");
        sb.append(" FROM fin_claimant_parts fcp, frt_parts fp,bas_parts_unit bpu,bas_repair_type brt,bas_storehouse bsh");
        sb.append(" WHERE fp.PARTS_ID=fcp.PARTS_ID AND bpu.PUNIT_ID=fp.PUNIT_NAME AND brt.REPTYP_ID=fcp.REPTYP_ID AND bsh.STORE_ID=fcp.STORE_ID and fp.enterprise_id="+cmVo.getEnterpriseId());
        if (cmVo.getClaimantmId() != null && !"".equals(cmVo.getClaimantmId().trim()))
            sb.append(" and fcp.CLAIMANTM_ID ='" + cmVo.getClaimantmId().trim() + "'");
        else
            sb.append(" and fcp.CLAIMANTM_ID ='-1'");
        List<Object[]> list = finClaimantPartsDao.createSQLQuery(sb.toString(),null);
        PartsVo fcp = null;
        if (list != null && list.size() > 0)
            for (Object[] objects : list)
            {
                fcp = new PartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fcp.setPartsId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fcp.setPartsName(objects[1].toString());
                // if(objects[2] != null&&objects[2].toString().length()>0)
                // fcp.setRelcampId(Short.parseShort(objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fcp.setPartsNum(Double.parseDouble(objects[3].toString()));
                else
                    fcp.setPartsNum(0.00d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fcp.setPartsRepairPrice(Double.parseDouble(objects[4].toString()));
                else
                    fcp.setPartsRepairPrice(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fcp.setPartsAmount(Double.parseDouble(objects[5].toString()));
                else
                    fcp.setPartsAmount(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fcp.setReptypId(Short.parseShort(objects[6].toString()));
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fcp.setReptypName(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fcp.setPunitId(Short.parseShort(objects[8].toString()));
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fcp.setPunitName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fcp.setStoreId(Short.parseShort(objects[10].toString()));
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fcp.setStoreName(objects[11].toString());
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fcp.setPartsTaxCost(Double.parseDouble(objects[12].toString()));
                else
                    fcp.setPartsTaxCost(0.00d);
                if (objects[13] != null && objects[13].toString().length() > 0)
                    fcp.setPartsNoTaxCost(Double.parseDouble(objects[13].toString()));
                else
                    fcp.setPartsNoTaxCost(0.00d);
                partsList.add(fcp);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(partsList);
        dg.setTotal(partsList.size());
        return dg;
    }

    /**
     * 添加其他费用项
     * */
    
    public List<FinClaimantMainCost> addFinClaimantMainCost(
            FinClaimantMainVo fcmVo) throws Exception
    {
        List<FinClaimantMainCost> list = null;
        String others = fcmVo.getOthers();
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            list = JSON.parseArray(jsOthers.get("rows").toString(),
                    FinClaimantMainCost.class);
        }
        if (list == null)
            list = new ArrayList<FinClaimantMainCost>();
        FinClaimantMainCost frc = new FinClaimantMainCost();
        // frc.setTagId(freVo.getId());
        frc.setCostName("输入收费项目名称");
        frc.setCostAmount(0.00d);
        frc.setCostExplain("输入收费说明");
        list.add(frc);

        return list;
    }

    /**
     * 移除其他费用项
     * */
    
    public Datagrid removeFinClaimantMainCost(List<FinClaimantMainCost> list,
            FinClaimantMainVo fcmVo) throws Exception
    {
        // TODO Auto-generated method stub
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getCostName().trim().equals(
                    fcmVo.getCostName().trim()))
            {
                list.remove(list.get(i));
            }
        }
        Datagrid Datagrid = new Datagrid();
        Datagrid.setTotal(list.size());
        Datagrid.setRows(list);
        return Datagrid;
    }

    /**
     * 查找未确认的索赔结算单
     * */
    
    public Datagrid findIdeaByStatus(FinClaimantMainVo fcmVo) throws Exception
    {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer(
                "SELECT fpc.PRECLR_ID,fc.CAR_LICENSE,fpc.PRECLR_TIME,");
        sb.append(" fr.FIN_COM_ID,brc.RELCAMP_NAME,fpc.RECEPTION_ID,");
        sb.append(" fc.CAR_VIN,fce.CUSTOM_NAME,fr.RECEPTION_DISTANCE,bs.STF_NAME,");
        sb.append(" fpc.PRE_MPR_MAT_AMOUNT ,fpc.PRECLR_WKTIME_AMOUNT,fpc.PRECLR_OTHER_AMOUNT,");
        sb.append(" (fpc.PRE_MPR_MAT_AMOUNT+fpc.PRECLR_OTHER_AMOUNT+fpc.PRECLR_WKTIME_AMOUNT),");
        sb.append(" fpc.PRECLR_NOTAX_COST,fpc.PRECLR_TAX_COST");
        sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_car fc,frt_custom fce,");
        sb.append(" bas_relation_campany brc,bas_stuff bs");
        sb.append(" WHERE fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.enterprise_id="+fcmVo.getEnterpriseId()+" AND fc.CAR_ID=fr.CAR_ID");
        sb.append(" AND fce.CUSTOM_ID=fc.CUSTOM_ID AND fr.FIN_TAG="+ Contstants.CLAIM_TAG.CLAIMNO + "");
        sb.append(" AND brc.RELCAMP_ID=fr.FIN_COM_ID AND bs.STF_ID=fr.RECEPTOR AND fpc.PRE_FLG="
                        + Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fpc.PRECLR_TO_MONEY=" + Contstants.TOMONEY_TAG.TOMONEYYES + "");
        if (fcmVo.getClaimantmTimeBegin() != null
                && fcmVo.getClaimantmTimeBegin().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME>='" + fcmVo.getClaimantmTimeBegin()
                    + "'");
        }
        if (fcmVo.getClaimantmTimeEnd() != null
                && fcmVo.getClaimantmTimeEnd().length() > 0)
        {
        	sb.append(" AND fpc.PRECLR_TIME<='" + fcmVo.getClaimantmTimeEnd()
                    + "'");
        }
        if(fcmVo.getClaimantmTimeBegin() == null &&  fcmVo.getClaimantmTimeEnd() == null){
        	if(fcmVo.getFlag()!=null && fcmVo.getFlag()==true){
        		String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,fcmVo.getEnterpriseId()).getCiValue();
            	if(time!=null && !("".equals(time))){
            		sb.append(" and DATE_FORMAT(fpc.PRECLR_TIME,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
            	}else{
            		sb.append(" and DATE_FORMAT(fpc.PRECLR_TIME,'%Y-%m-%d %H-%i-%s')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
            	}
            	sb.append(" and DATE_FORMAT(fpc.PRECLR_TIME,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        	}
        	
        }
        if (fcmVo.getRelcampId() != null
                && fcmVo.getRelcampId().toString().length() > 0)
        {
            sb.append(" AND fr.FIN_COM_ID like '%" + StringEscapeUtils.escapeSql(fcmVo.getRelcampId().toString().trim())
                    + "%'");
        }
        if (fcmVo.getSort() != null && !"".equals(fcmVo.getSort().trim())
                && fcmVo.getOrder() != null
                && !"".equals(fcmVo.getOrder().trim()))
        {
            if (fcmVo.getSort().trim().equals("receptionId"))
            {
                sb.append(" order by fpc.RECEPTION_ID "
                        + fcmVo.getOrder().trim());
            }
            else
            {
                sb.append(" order by fpc." + fcmVo.getSort().trim() + " "
                        + fcmVo.getOrder().trim());
            }
        }
        List<FinClaimantMainVo> rows = new ArrayList<FinClaimantMainVo>();
        List<Object[]> list = finClaimantMainDao.createSQLQuery(sb.toString(),
                null, fcmVo.getPage(), fcmVo.getRows());
        int total = finClaimantMainDao.getSQLCount(sb.toString(), null);
        FinClaimantMainVo fcmv = null;
        if (list != null && list.size() > 0)
            for (Object[] objects : list)
            {
                fcmv = new FinClaimantMainVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fcmv.setPreclrId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fcmv.setCarLicense(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fcmv.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fcmv.setRelcampId(Short.parseShort(objects[3].toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fcmv.setRelcampName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fcmv.setReceptionId(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fcmv.setCarVin(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fcmv.setCustomName(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fcmv.setReceptionDistance(Integer.parseInt(objects[8]
                            .toString()));
                else
                    fcmv.setReceptionDistance(0);
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fcmv.setStfName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fcmv.setClaimantmPartsAmount(Double.parseDouble(objects[10]
                            .toString()));
                else
                    fcmv.setClaimantmPartsAmount(0.00d);
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fcmv.setClaimantmTimeAmount(Double.parseDouble(objects[11]
                            .toString()));
                else
                    fcmv.setClaimantmTimeAmount(0.00d);
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fcmv.setClaimantMainCost(Double.parseDouble(objects[12]
                            .toString()));
                else
                    fcmv.setClaimantMainCost(0.00d);
                if (objects[13] != null && objects[13].toString().length() > 0)
                    fcmv.setClaimantmAmount(Double.parseDouble(objects[13]
                            .toString()));
                else
                    fcmv.setClaimantmAmount(0.00d);
                if (objects[14] != null && objects[14].toString().length() > 0)
                    fcmv.setClaimantmNoTaxCost(Double.parseDouble(objects[14]
                            .toString()));
                else
                    fcmv.setClaimantmNoTaxCost(0.00d);
                if (objects[15] != null && objects[15].toString().length() > 0)
                    fcmv.setClaimantmTaxCost(Double.parseDouble(objects[15]
                            .toString()));
                else
                    fcmv.setClaimantmTaxCost(0.00d);
                
                rows.add(fcmv);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 根据维修工单号查找未索理赔项目
     * */
    public Datagrid findItemByReceptionId(FinClaimantMainVo fcmVo) throws Exception
    {
        List<FrtItemVo> list = new ArrayList();
        FrtReception frt = frtReceptionDao.get(FrtReception.class, fcmVo.getReceptionId());
        StringBuffer sb = new StringBuffer(
                "SELECT fpw.REPITEM_ID,fpw.REPITEM_NAME,fpw.STF_ID,fpw.WKTIME_NUM,fpw.INNER_WKTIME,fpw.WKTIME_AMOUNT,bs.STF_NAME,fpw.REPTYP_ID,brt.REPTYP_NAME");
        sb.append(" FROM frt_pre_clearing fpc,frt_pre_wktime fpw,bas_stuff bs,bas_repair_type brt,bas_claims_type bct");
        if (frt.getFinAllTag() != null&& frt.getFinAllTag().equals(Contstants.CLAIM_TAG.CLAIMYES))
        {
            sb.append(" WHERE fpw.PRECLR_ID=fpc.PRECLR_ID AND bs.STF_ID=fpw.STF_ID AND brt.REPTYP_ID=fpw.REPTYP_ID AND fpc.RECEPTION_ID='"
                            + fcmVo.getEnterpriseId() + "'");
        }
        else{
            sb.append(" WHERE fpw.PRECLR_ID=fpc.PRECLR_ID AND bs.STF_ID=fpw.STF_ID AND brt.REPTYP_ID=fpw.REPTYP_ID ");
            sb.append(" AND fpc.RECEPTION_ID='" + fcmVo.getReceptionId() + "'");
            sb.append(" AND bct.CLAIMS_FLG="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
        }
        sb.append(" AND bct.CLAIMS_ID=fpw.CLAIMS_TYPE");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        List<Object[]> lists = finClaimantMainDao.createSQLQuery(sb.toString(),
                null);
        FrtItemVo fct = null;
        if (lists != null && lists.size() > 0)
            for (Object[] objects : lists)
            {
                fct = new FrtItemVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fct.setRepitemId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fct.setRepitemName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fct.setStfId(Short.parseShort(objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fct.setRepitemTime(Double.parseDouble(objects[3].toString()));
                else
                    fct.setRepitemTime(0.00d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fct.setInternalTime(Double.parseDouble(objects[4].toString()));
                else
                    fct.setInternalTime(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0){
                    String ciValue=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.INDEMNITYS, INDEMNITYS.CLAIMTIMEPRICE,fcmVo.getEnterpriseId()).getCiValue();
                    if(ciValue==null || "".equals(ciValue)){
                    	ciValue="1";
                    }
                    	 fct.setRepitemAmount(Double.parseDouble(objects[3].toString())*Double.parseDouble(ciValue));
                }else
                    fct.setRepitemAmount(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fct.setStfName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fct.setReptypId(Short.parseShort(objects[7].toString()));
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fct.setReptypName(objects[8].toString());
                list.add(fct);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(list);
        dg.setTotal(list.size());
        return dg;
    }

    /**
     * 根据维修工单号查找未索赔配件
     * */
    
    public Datagrid findPartsByReceptionId(FinClaimantMainVo fcmVo) throws Exception
    {
        // TODO Auto-generated method stub
        List<PartsVo> list = new ArrayList<PartsVo>();
        StringBuffer sb = new StringBuffer("SELECT fpp.PARTS_ID,fpp.PARTS_NAME,fpp.RELCAMP_ID,fpp.PARTS_PRICE,fpp.PARTS_AMOUNT,");
        sb.append(" fpp.PARTS_COUNT,fpp.REPTYP_ID,brt.REPTYP_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,bsh.STORE_ID,bsh.STORE_NAME,");
        sb.append(" fpp.PARTS_TAX_COST,fpp.PARTS_NOTAX_COST");
        sb.append(" FROM frt_pre_clearing fpc,frt_pre_parts fpp ,bas_claims_type bct,bas_repair_type brt,");
        sb.append(" bas_parts_unit bpu,bas_storehouse bsh,frt_parts fp ");
        sb.append(" WHERE fpc.PRECLR_ID=fpp.PRECLR_ID and fpc.enterprise_id="+fcmVo.getEnterpriseId()+" AND bct.CLAIMS_ID=fpp.CLAIMS_TYPE AND bct.CLAIMS_FLG="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
        sb.append(" AND bsh.STORE_ID=fpp.STORE_ID");
        sb.append(" AND brt.REPTYP_ID=fpp.REPTYP_ID AND bpu.PUNIT_ID=fp.PUNIT_NAME");
        sb.append(" AND fpp.PARTS_ID=fp.PARTS_ID AND fpc.RECEPTION_ID='"
                + fcmVo.getReceptionId() + "'");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        List<Object[]> lists = finClaimantMainDao.createSQLQuery(sb.toString(),
                null);
        PartsVo fcp = null;
        if (lists != null && lists.size() > 0)
            for (Object[] objects : lists)
            {
                fcp = new PartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fcp.setPartsId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fcp.setPartsName(objects[1].toString());
                // if(objects[2] != null&&objects[2].toString().length()>0)
                // fcp.setRelcampId(Short.parseShort(objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fcp.setPartsRepairPrice(Double.parseDouble(objects[3]
                            .toString()));
                else
                    fcp.setPartsRepairPrice(0.00d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fcp.setPartsAmount(Double.parseDouble(objects[4].toString()));
                else
                    fcp.setPartsAmount(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fcp.setPartsNum(Double.parseDouble(objects[5].toString()));
                else
                    fcp.setPartsNum(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fcp.setReptypId(Short.parseShort(objects[6].toString()));
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fcp.setReptypName(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fcp.setPunitId(Short.parseShort(objects[8].toString()));
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fcp.setPunitName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fcp.setStoreId(Short.parseShort(objects[10].toString()));
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fcp.setStoreName(objects[11].toString());
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fcp.setPartsTaxCost(Double.parseDouble(objects[12].toString()));
                else
                    fcp.setPartsTaxCost(0.00d);
                if (objects[13] != null && objects[13].toString().length() > 0)
                    fcp.setPartsNoTaxCost(Double.parseDouble(objects[13].toString()));
                else
                    fcp.setPartsNoTaxCost(0.00d);
                list.add(fcp);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(list);
        dg.setTotal(list.size());
        return dg;
    }

    /**
     * 根据维修工单号查找其他费用
     * */
    
    public Datagrid findCostByReceptionId(FinClaimantMainVo fcmVo) throws Exception
    {
        List<FinClaimantMainCost> list = new ArrayList<FinClaimantMainCost>();
        StringBuffer sb = new StringBuffer(
                "SELECT fpcc.COST_ITEM,fpcc.COST_AMOUNT,fpcc.COST_EXPLAIN FROM frt_pre_clearing_cost fpcc ,frt_pre_clearing fpc");
        sb.append(" WHERE fpcc.PRECLR_ID=fpc.PRECLR_ID and fpc.enterprise_id="+fcmVo.getEnterpriseId()+" AND fpc.RECEPTION_ID='"
                + fcmVo.getReceptionId() + "'");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        List<Object[]> lists = finClaimantMainDao.createSQLQuery(sb.toString(),
                null);
        FinClaimantMainCost fcmc = null;
        if (lists != null && lists.size() > 0)
            for (Object[] objects : lists)
            {
                fcmc = new FinClaimantMainCost();
                fcmc.setCostName(objects[0].toString());
                fcmc.setCostAmount(Double.parseDouble(objects[1].toString()));
                fcmc.setCostExplain(objects[2].toString());
                list.add(fcmc);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(list);
        dg.setTotal(list.size());
        return dg;
    }

    /**
     * 更新索理赔单
     * */
    @Log(moduleName = "前台管理", content = "更新索理赔单", opertype = "更新")
    
    public Msg edit(FinClaimantMainVo fcmVo) throws Exception
    {
        List<FrtItemVo> itemList = null;
        List<PartsVo> partsList = null;
        List<FinClaimantMainCost> costList = null;

        String items = fcmVo.getItems();
        if (items != null && items.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (itemList == null)
        {
            itemList = new ArrayList();
        }
        String parts = fcmVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        if (partsList == null)
        {
            partsList = new ArrayList();
        }
        String others = fcmVo.getOthers();
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            costList = JSON.parseArray(jsOthers.get("rows").toString(),
                    FinClaimantMainCost.class);
        }
        if (costList == null)
        {
            costList = new ArrayList();
        }

        Msg msg = new Msg();
        FinClaimantMain fcm = new FinClaimantMain();
        // BeanUtils.copyProperties(fcmVo, fcm);
        MyBeanUtils.getInstance().copyBeans(fcmVo, fcm);
        if (fcmVo.getClaimantmInvoiceTime() != null
                && fcmVo.getClaimantmInvoiceTime().toString().length() > 0)
        {
            if (!fcmVo.getClaimantmInvoiceType().equals(
                    Contstants.RECEIPT_TAG.OTHERTAX))
                fcm.setClaimantmInvoiceTime(MyBeanUtils.getInstance().getDate(
                        fcmVo.getClaimantmInvoiceTime()));
            else
                fcm.setClaimantmInvoiceTime(null);
        }
        copyData(itemList, partsList, costList, fcm);
        fcm.setClaimantmTag(Contstants.DELETE_TAG.DELETENO);
        fcm.setClaimantmToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
        try
        {
            FinClaimantMain temp = finClaimantMainDao.get(
                    FinClaimantMain.class, fcm.getClaimantmId());
            if (parts == null || parts.length() == 0)
                fcm.setFinClaimantPartses(temp.getFinClaimantPartses());
            if (items == null || items.length() == 0)
                fcm.setFinClaimantTimes(temp.getFinClaimantTimes());
            if (others == null || others.length() == 0)
                fcm.setFinClaimantMainCosts(temp.getFinClaimantMainCosts());
            sumMoney(fcm);
            //finClaimantMainDao.executeSQL("delete from fin_claimant_parts where CLAIMANTM_ID='"+fcm.getClaimantmId()+"'");
            //finClaimantMainDao.executeSQL("delete from fin_claimant_time where CLAIMANTM_ID='"+fcm.getClaimantmId()+"'");
           // finClaimantMainDao.executeSQL("delete from fin_claimant_main_cost where CLAIMANTM_ID='"+fcm.getClaimantmId()+"'");
            finClaimantMainDao.merge(fcm);
            msg.setMsg("更新索理赔单成功！");
            msg.setSuccess(true);
        }
        catch(Exception es)
        {
            msg.setMsg("更新索理赔单失败！");
            es.printStackTrace();
        }
        return msg;
    }

    private void sumMoney(FinClaimantMain fcm) throws Exception
    {
        double costAmount = 0;
        double partsAmount = 0;
        double timeAmount = 0;
        double taxAmount = 0;
        double noTaxAmount = 0;
        Set<FinClaimantMainCost> set1 = fcm.getFinClaimantMainCosts();
        if(set1!=null&&set1.size()>0)
        for (FinClaimantMainCost finClaimantMainCost : set1)
        {
            costAmount += finClaimantMainCost.getCostAmount();
        }
        Set<FinClaimantParts> set2 = fcm.getFinClaimantPartses();
        if(set2!=null&&set2.size()>0)
        for (FinClaimantParts finClaimantParts : set2)
        {
            partsAmount += finClaimantParts.getClaimantpAmount();
            taxAmount +=finClaimantParts.getClaimantpTaxCost()*finClaimantParts.getClaimantpCount();
            noTaxAmount +=finClaimantParts.getClaimantpNoTaxCost()*finClaimantParts.getClaimantpCount();
        }
        Set<FinClaimantTime> set3 = fcm.getFinClaimantTimes();
        if(set3!=null&&set3.size()>0)
        for (FinClaimantTime finClaimantTime : set3)
        {
            timeAmount += finClaimantTime.getClaimanttAmount();
        }
        fcm.setClaimantmTaxCost(taxAmount);
        fcm.setClaimantmNoTaxCost(noTaxAmount);
        fcm.setClaimantmPartsAmount(partsAmount);
        fcm.setClaimantmTimeAmount(timeAmount);
        fcm.setClaimantmOtherAmount(costAmount);
        fcm.setClaimantmAmount(timeAmount + partsAmount + costAmount);
        fcm.setClaimantmManagementFee(fcm.getClaimantmAmount()
                * frtService.findDefaultClaimsRate());
    }

    private void copyData(List<FrtItemVo> itemList, List<PartsVo> partsList,
            List<FinClaimantMainCost> costList, FinClaimantMain fcm)
            throws Exception
    {
        List<FinClaimantMainCost> list3 = costList;
        FinClaimantMainCost fcmc = null;
        if(list3!=null&&list3.size()>0)
        for (FinClaimantMainCost finClaimantMainCost : list3)
        {
            fcmc = finClaimantMainCost;
            fcmc.setFinClaimantMain(fcm);
            fcm.getFinClaimantMainCosts().add(fcmc);
        }
        List<PartsVo> list2 = partsList;
        FinClaimantParts fcp = null;
        if(list2!=null&&list2.size()>0)
        for (PartsVo partsVo : list2)
        {
        	if(partsVo.getPartsTaxCost()==null||partsVo.getPartsTaxCost().toString().length()==0)
        		partsVo.setPartsTaxCost(0.00d);
        	if(partsVo.getPartsNoTaxCost()==null||partsVo.getPartsNoTaxCost().toString().length()==0)
        		partsVo.setPartsNoTaxCost(0.00d);
            fcp = new FinClaimantParts();
            fcp.setPartsId(partsVo.getPartsId());
            fcp.setPartsName(partsVo.getPartsName());
            fcp.setClaimantpPrice(partsVo.getPartsRepairPrice());
            fcp.setClaimantpCount(partsVo.getPartsNum());
            fcp.setClaimantpAmount(partsVo.getPartsAmount());
            fcp.setRelcampId(partsVo.getRelcampId());
            fcp.setReptypId(partsVo.getReptypId());
            fcp.setStoreId(partsVo.getStoreId());
            fcp.setFinClaimantMain(fcm);
            fcp.setClaimantpTaxCost(partsVo.getPartsTaxCost());
            fcp.setClaimantpNoTaxCost(partsVo.getPartsNoTaxCost());
            fcm.getFinClaimantPartses().add(fcp);
        }
        List<FrtItemVo> list1 = itemList;
        FinClaimantTime fct = null;
        if(list1!=null&&list1.size()>0)
        for (FrtItemVo itemVo : list1)
        {
            fct = new FinClaimantTime();
            fct.setRepitemId(itemVo.getRepitemId());
            fct.setRepitemName(itemVo.getRepitemName());
            fct.setClaimanttAmount(itemVo.getRepitemAmount());
            fct.setClaimanttTime(itemVo.getRepitemTime());
            fct.setClaimanttInnerTime(itemVo.getInternalTime());
            fct.setReptypId(itemVo.getReptypId());
            fct.setStfId(itemVo.getStfId());
            fct.setFinClaimantMain(fcm);
            fcm.getFinClaimantTimes().add(fct);
        }
    }

    /**
     * 计算费用总和
     * */
    
    public List totemoney(FinClaimantMainVo fcmVo) throws Exception
    {
        List<PartsVo> list1 = null;
        List<FrtItemVo> list2 = null;
        List<FinClaimantMainCost> list3 = null;
        String items = fcmVo.getItems();
        if (items != null && items.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(items);
            list2 = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (list2 == null)
        {
            list2 = new ArrayList();
        }
        String parts = fcmVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            list1 = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        if (list1 == null)
        {
            list1 = new ArrayList();
        }
        String others = fcmVo.getOthers();
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            list3 = JSON.parseArray(jsOthers.get("rows").toString(),
                    FinClaimantMainCost.class);
        }
        if (list3 == null)
        {
            list3 = new ArrayList();
        }
        double sumMoney = 0;
        double preclrWktimeAmount = 0;
        double preMprMatAmount = 0;
        double otherAmount = 0;
        double taxAmount = 0;
        double noTaxAmount = 0;
        if (list1 != null && list1.size() > 0)
            for (PartsVo partsVo : list1)
            {
            	if(partsVo.getPartsTaxCost()==null||partsVo.getPartsTaxCost().toString().length()==0)
            		partsVo.setPartsTaxCost(0.00d);
            	if(partsVo.getPartsNoTaxCost()==null||partsVo.getPartsNoTaxCost().toString().length()==0)
            		partsVo.setPartsNoTaxCost(0.00d);
                 preMprMatAmount = preMprMatAmount + partsVo.getPartsAmount();
                 taxAmount+=partsVo.getPartsTaxCost()*partsVo.getPartsNum();
                 noTaxAmount+=partsVo.getPartsNoTaxCost()*partsVo.getPartsNum();
            }
        if (list2 != null && list2.size() > 0)
            for (FrtItemVo itemVo : list2)
            {
                if (itemVo.getRepitemAmount() != null
                        && itemVo.getRepitemTime() != null)
                    preclrWktimeAmount += itemVo.getRepitemAmount();
            }
        if (list3 != null && list3.size() > 0)
            for (FinClaimantMainCost costVo : list3)
            {
                otherAmount += costVo.getCostAmount();
            }
        double preclrManagementFee = 0.00d;
        sumMoney += preclrWktimeAmount;
        sumMoney += preMprMatAmount;
        sumMoney += otherAmount;
        sumMoney += preclrManagementFee;
        List list = new ArrayList();
        list.add(preclrWktimeAmount);
        list.add(preMprMatAmount);
        // 管理费
        list.add(preclrManagementFee);
        
        list.add(otherAmount);
        list.add(sumMoney);
        list.add(taxAmount);
        list.add(noTaxAmount);
        return list;
    }

    @Log(moduleName = "前台管理", content = "索理赔转收银", opertype = "更新/转收银")
    
    public Msg modifyTransformMoney(FinClaimantMainVo fcmVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        try
        {
            FinClaimsReceivables fcr = new FinClaimsReceivables();
            FinClaimantMain fcm = finClaimantMainDao.get(FinClaimantMain.class,
                    fcmVo.getClaimantmId());
            fcm.setClaimantmToMoney(Contstants.TOMONEY_TAG.TOMONEYYES);
            fcr.setCrCumulative(0.00d);
            fcr.setCrReceivables(fcm.getClaimantmAmount());
            fcr.setClaimantmId(fcm.getClaimantmId());
            fcr.setCrUnFinished(Contstants.OPINIONFINISHED_TAG.UNPAYMENT);
            fcr.setCrSubstitute(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
            fcr.setCrBatchTag(Contstants.OPINIONFINISHED_TAG.UNFINISHED);
            fcr.setCrArrears(fcm.getClaimantmAmount());
            fcr.setCrId(CreateID.createId("FinClaimsReceivables",
                    Contstants.CLAIMSSUITGATHERING));
            fcr.setEnterpriseId(fcmVo.getEnterpriseId());
            finClaimsReceivablesDao.save(fcr);
            msg.setSuccess(true);
            msg.setMsg("转收银成功!");
        }
        catch(Exception es)
        {
            msg.setMsg("转收银失败！");
        }
        return msg;
    }

    /**
     * 增加自定义索赔项目
     * */
    
    public List<FrtItemVo> addFinClaimantMainItem(FinClaimantMainVo fcmVo)
            throws Exception
    {
        List<FrtItemVo> itemList = null;
        String item = fcmVo.getItems();
        if (item != null && item.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(item);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (itemList == null)
        {
            itemList = new ArrayList<FrtItemVo>();
        }
        FrtItemVo iv = new FrtItemVo();
        iv.setRepitemId(IncrementId.getItemId());
        iv.setRepitemName(Contstants.CLAIMANSREPITEMNAME);
        iv.setRepitemNum(1d);
        iv.setRepitemTime(Contstants.REPITEMTIME);
        iv.setInternalTime(Contstants.INTERNALTIME);
        iv.setRepitemAmount(Contstants.REPITEMAMOUNT);
        iv.setClaimsId(frtService.getDefaultClaimsType(fcmVo.getEnterpriseId()));
        iv.setClaimsName(frtService.getDefaultClaimsTypeName(fcmVo.getEnterpriseId()));
        iv.setReptypId(frtService.getDefaultRepairType(fcmVo.getEnterpriseId()));
        iv.setReptypName(frtService.getDefaultRepairTypeName(fcmVo.getEnterpriseId()));
        itemList.add(iv);
        return itemList;
    }

    /**
     * 取消审核操作
     * */
    @Log(moduleName = "前台管理", content = "取消索理赔单审核", opertype = "更新/取消审核")
    
    public Msg modifyClaimsValidateAsFalse(FinClaimantMainVo fcmVo)
            throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FinClaimantMain fcm = finClaimantMainDao.get(FinClaimantMain.class,
                    fcmVo.getClaimantmId());
            fcm.setClaimantmStatus(Contstants.AUDIT_TAG.AUDITNOS.toString());
            fcm.setClaimantmCheckStfId(fcmVo.getClaimantmCheckStfId());
            finClaimantMainDao.merge(fcm);
            msg.setMsg("索赔单【" + fcmVo.getClaimantmId() + "】取消审核成功！");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("索赔单【" + fcmVo.getClaimantmId() + "】取消审核失败！");
            msg.setSuccess(false);
        } finally
        {
            return msg;
        }
    }

    /**
     * 审核确认操作
     * */
    @Log(moduleName = "前台管理", content = "索理赔单审核", opertype = "更新/审核")
    
    public Msg modifyClaimsValidateAsTrue(FinClaimantMainVo fcmVo)
            throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FinClaimantMain fcm = finClaimantMainDao.get(FinClaimantMain.class,
                    fcmVo.getClaimantmId());
            fcm.setClaimantmStatus(Contstants.AUDIT_TAG.AUDITYESS.toString());
            fcm.setClaimantmCheckStfId(fcmVo.getClaimantmCheckStfId());
            finClaimantMainDao.merge(fcm);
            msg.setMsg("索赔单【" + fcmVo.getClaimantmId() + "】审核成功！");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("索赔单【" + fcmVo.getClaimantmId() + "】审核失败！");
            msg.setSuccess(false);
        } finally
        {
            return msg;
        }
    }

    /**
     * 校验审核操作
     * */
    
    public Msg isClaimsValidate(FinClaimantMainVo fcmVo) throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FinClaimantMain fcm = finClaimantMainDao.get(FinClaimantMain.class,
                    fcmVo.getClaimantmId());
            if (fcm.getClaimantmStatus().equals(Contstants.AUDIT_TAG.AUDITYESS))
                msg.setSuccess(true);
            else
                msg.setSuccess(false);
        }
        catch(Exception e)
        {
            msg.setMsg("判断索理赔单有无确认失败！");
            msg.setSuccess(false);
        }
          return msg;
    }
    /**
     * 查找索赔成本对比分析数据
     * */
	
	public DatagridAnalyze claimCostContrastAnalyse(FinClaimantMainVo fcmVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fcm.claimantm_id,fcm.claimantm_time,fcm.claimantm_notax_cost,");
		sb.append(" fcm.claimantm_tax_cost,fpc.preclr_notax_cost,fpc.preclr_tax_cost,");
		sb.append(" (fpc.preclr_notax_cost-fcm.claimantm_notax_cost) AS temp1,");
		sb.append(" (fpc.preclr_tax_cost-fcm.claimantm_tax_cost) AS temp2");
		sb.append(" FROM fin_claimant_main fcm INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id");
		sb.append(" AND fcm.claimantm_tag="+Contstants.DELETE_TAG.DELETENO);
		sb.append(" AND fcm.enterprise_id="+fcmVo.getEnterpriseId()     );
		if(fcmVo.getClaimantmTimeBegin()!=null&&fcmVo.getClaimantmTimeBegin().length()>0)
			sb.append(" AND fcm.claimantm_time>='"+fcmVo.getClaimantmTimeBegin()+"'");
		if(fcmVo.getClaimantmTimeEnd()!=null&&fcmVo.getClaimantmTimeEnd().length()>0)
			sb.append(" AND fcm.claimantm_time<='"+fcmVo.getClaimantmTimeEnd()+"'");
		if(fcmVo.getClaimantmTimeBegin()==null && fcmVo.getClaimantmTimeEnd()!=null){
			sb.append(" and DATE_FORMAT( fcm.claimantm_time,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,fcmVo.getEnterpriseId()).getCiValue()))+ "'");
			sb.append(" and DATE_FORMAT( fcm.claimantm_time,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");	
		}
		if(fcmVo.getClaimantmId()!=null&&fcmVo.getClaimantmId().length()>0)
			sb.append(" AND fcm.claimantm_id like '%"+StringEscapeUtils.escapeSql(fcmVo.getClaimantmId().trim())+"%'");
		List<FinClaimantMainVo> rows=new ArrayList<FinClaimantMainVo>();
		List<FinClaimantMainVo> footers=new ArrayList<FinClaimantMainVo>();
		FinClaimantMainVo fcmv=new FinClaimantMainVo();
		fcmv.setClaimantmNoTaxCost(0d);
		fcmv.setPreclrNoTaxCost(0d);
		fcmv.setDifferenceNoTaxCost(0d);
		fcmv.setClaimantmTaxCost(0d);
		fcmv.setPreclrTaxCost(0d);
		fcmv.setDifferenceTaxCost(0d);
		List<Object[]> list=finClaimantMainDao.createSQLQuery(sb.toString(), fcmVo.getPage(), fcmVo.getRows());
		FinClaimantMainVo fcm=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				fcm=new FinClaimantMainVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					fcm.setClaimantmId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					fcm.setClaimantmTime(MyBeanUtils.getInstance().formatString(obj[1].toString()));
				if(obj[2]!=null&&obj[2].toString().length()>0){
					fcm.setClaimantmNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[2].toString())));
					fcmv.setClaimantmNoTaxCost(fcmv.getClaimantmNoTaxCost()+fcm.getClaimantmNoTaxCost());
				}
				if(obj[3]!=null&&obj[3].toString().length()>0){
					fcm.setClaimantmTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[3].toString())));
					fcmv.setClaimantmTaxCost(fcmv.getClaimantmTaxCost()+fcm.getClaimantmTaxCost());					
				}
				if(obj[4]!=null&&obj[4].toString().length()>0){
					fcm.setPreclrNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[4].toString())));
					fcmv.setPreclrNoTaxCost(fcmv.getPreclrNoTaxCost()+fcm.getPreclrNoTaxCost());					
				}
				if(obj[5]!=null&&obj[5].toString().length()>0){
					fcm.setPreclrTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[5].toString())));
					fcmv.setPreclrTaxCost(fcmv.getPreclrTaxCost()+fcm.getPreclrTaxCost());					
				}
				if(obj[6]!=null&&obj[6].toString().length()>0){
					fcm.setDifferenceNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[6].toString())));
					fcmv.setDifferenceNoTaxCost(fcmv.getDifferenceNoTaxCost()+fcm.getDifferenceNoTaxCost());					
				}
				if(obj[7]!=null&&obj[7].toString().length()>0){
					fcm.setDifferenceTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[7].toString())));
					fcmv.setDifferenceTaxCost(fcmv.getDifferenceTaxCost()+fcm.getDifferenceTaxCost());					
				}
				fcmv.setClaimantmNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getClaimantmNoTaxCost().toString())));
				fcmv.setClaimantmTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getClaimantmTaxCost().toString())));
				fcmv.setPreclrNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getPreclrNoTaxCost().toString())));
				fcmv.setPreclrTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getPreclrTaxCost().toString())));
				fcmv.setDifferenceNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getDifferenceNoTaxCost().toString())));
				fcmv.setDifferenceTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
						Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,fcmv.getDifferenceTaxCost().toString())));
				fcm.setState("closed");
				fcm.setPartsName("查看详情");
				rows.add(fcm);
			}
		fcmv.setClaimantmId("合计");
		fcmv.setPartsName("");
		footers.add(fcmv);
		int total=finClaimantMainDao.getSQLCount(sb.toString(), null);
		DatagridAnalyze da=new DatagridAnalyze();
		da.setRows(rows);
		da.setTotal(total);
		da.setFooter(footers);
		return da;
	}
	/**
	 * 查找索赔成本对比分析-子项信息
	 * */
	
	public List claimCostContrastAnalyseChild(FinClaimantMainVo fcmVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT bb.*,bb.temp3-bb.temp1,bb.temp4-bb.temp2");
		 sb.append(" FROM(");
		sb.append(" SELECT fcp.parts_id,fcp.parts_name,fcp.store_id,"); 
		sb.append(" (fcp.claimantp_notax_cost*fcp.claimantp_count) AS temp1,");
		sb.append(" (fcp.claimantp_tax_cost*fcp.claimantp_count) AS temp2,");
		sb.append(" (SELECT CASE WHEN aa.temp1!='' THEN aa.temp1 ELSE 0 END ) AS temp3,");
		sb.append(" (SELECT CASE WHEN aa.temp2!='' THEN aa.temp2 ELSE 0 END ) AS temp4,");
		sb.append(" fcp.claimantp_count AS temp5");
		sb.append(" FROM  fin_claimant_main fcm INNER JOIN fin_claimant_parts fcp ON fcp.claimantm_id=fcm.claimantm_id");
		sb.append(" INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fcm.preclr_id");
		if(fcmVo.getClaimantmId()!=null&&fcmVo.getClaimantmId().length()>0)
			sb.append(" AND fcm.claimantm_id='"+fcmVo.getClaimantmId().trim()+"'");
		else
			sb.append(" AND fcm.claimantm_id='-1'");
		sb.append(" LEFT OUTER JOIN");
		sb.append(" ("); 
		sb.append(" SELECT fpc.preclr_id,fpp.parts_id,fpp.store_id,");
		sb.append(" (fpp.parts_notax_cost*fpp.parts_count) AS temp1,");
		sb.append(" (fpp.parts_tax_cost*fpp.parts_count) AS temp2");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpc.preclr_id");
		sb.append(" ) aa ON aa.preclr_id=fcm.preclr_id AND aa.parts_id=fcp.parts_id AND aa.store_id=fcp.store_id");
		sb.append(" ) bb");
		sb.append(" UNION");		
		sb.append(" SELECT bb.*,bb.temp3-bb.temp1,bb.temp4-bb.temp2");
		sb.append(" FROM(");
		sb.append(" SELECT fpp.parts_id,fpp.parts_name,fpp.store_id,");
		sb.append(" (SELECT CASE WHEN aa.temp1!='' THEN aa.temp1 ELSE 0 END ) AS temp1,");
		sb.append(" (SELECT CASE WHEN aa.temp2!='' THEN aa.temp2 ELSE 0 END ) AS temp2,");
		sb.append(" (fpp.parts_notax_cost*fpp.parts_count) AS temp3,");
		sb.append(" (fpp.parts_tax_cost*fpp.parts_count) AS temp4,");
		sb.append(" (SELECT CASE WHEN aa.temp3!='' THEN aa.temp3 ELSE 0 END ) AS temp5");
		sb.append(" FROM frt_pre_clearing fpc INNER JOIN frt_pre_parts fpp ON fpp.preclr_id=fpc.preclr_id");
		sb.append(" INNER JOIN  fin_claimant_main fcm ON fpc.preclr_id=fcm.preclr_id");
		if(fcmVo.getClaimantmId()!=null&&fcmVo.getClaimantmId().length()>0)
			sb.append(" AND fcm.claimantm_id='"+fcmVo.getClaimantmId().trim()+"'");
		else
			sb.append(" AND fcm.claimantm_id='-1'");
		sb.append(" LEFT OUTER JOIN");
		sb.append(" ( ");
		sb.append(" SELECT fcm.claimantm_id,fcp.parts_id,fcp.parts_name,fcp.store_id,"); 
		sb.append(" (fcp.claimantp_notax_cost*fcp.claimantp_count) AS temp1,");
		sb.append(" (fcp.claimantp_tax_cost*fcp.claimantp_count) AS temp2,");
		sb.append(" fcp.claimantp_count AS temp3");
		sb.append(" FROM  fin_claimant_main fcm INNER JOIN fin_claimant_parts fcp ON fcp.claimantm_id=fcm.claimantm_id");
		sb.append(" ) aa ON aa.claimantm_id=fcm.claimantm_id AND aa.parts_id=fpp.parts_id AND aa.store_id=fpp.store_id");
		sb.append(" ) bb");
		List<FinClaimantMainVo> rows=new ArrayList<FinClaimantMainVo>();
		List<Object[]> list=finClaimantMainDao.createSQLQuery(sb.toString(), fcmVo.getPage(), fcmVo.getRows());
		FinClaimantMainVo fcm=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				fcm=new FinClaimantMainVo();
				if(obj[1]!=null&&obj[1].toString().length()>0)
					fcm.setPartsName(obj[1].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					fcm.setClaimantmNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[3].toString())));
				if(obj[4]!=null&&obj[4].toString().length()>0)
					fcm.setClaimantmTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[4].toString())));
				if(obj[5]!=null&&obj[5].toString().length()>0)
					fcm.setPreclrNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[5].toString())));
				if(obj[6]!=null&&obj[6].toString().length()>0)
					fcm.setPreclrTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[6].toString())));
				if(obj[7]!=null&&obj[7].toString().length()>0)
					fcm.setClaimantpCount(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[7].toString())));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					fcm.setDifferenceNoTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[8].toString())));
				if(obj[9]!=null&&obj[9].toString().length()>0)
					fcm.setDifferenceTaxCost(Double.parseDouble(basCompanyInformationSetDAO.repairPersistFigure(
							Contstants.PARAMETER_SET.INOUTDEPOT, Contstants.INOUTDEPOT.COSTDECIMAL,obj[9].toString())));
				fcm.set_parentId(fcmVo.getClaimantmId());
				fcm.setState("open");
				rows.add(fcm);
			}
		return rows;
	}
 
	
}
