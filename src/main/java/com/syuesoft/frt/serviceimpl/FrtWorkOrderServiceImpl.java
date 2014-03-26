package com.syuesoft.frt.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasMountingsTypeInfoDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.TimeManageVo;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.dao.FrtPrePartsDao;
import com.syuesoft.frt.dao.FrtPreWktimeDao;
import com.syuesoft.frt.dao.FrtRcptItemDao;
import com.syuesoft.frt.dao.FrtRcptPartsDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.service.FrtWorkOrderService;
import com.syuesoft.frt.vo.FrtWorkOrderBaseVo;
import com.syuesoft.frt.vo.FrtWorkOrderClaimPartsVo;
import com.syuesoft.frt.vo.FrtWorkOrderItemVo;
import com.syuesoft.frt.vo.FrtWorkOrderPartsVo;
import com.syuesoft.frt.vo.FrtWorkOrderRepaiStatisticalStatementVo;
import com.syuesoft.frt.vo.FrtWorkOrderSettlementSituationItemVo;
import com.syuesoft.frt.vo.FrtWorkOrderSettlementSituationPartsVo;
import com.syuesoft.frt.vo.FrtWorkOrderVo;
import com.syuesoft.frt.vo.FrtWorkOrderclaimExwarehouseVo;
import com.syuesoft.frt.vo.frtWorkOrderSettlementSituationBalanceVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.MyBeanUtils;

/**
 * 工单综合查询service
 * 
 * @author Liujian
 * 
 */
@Service("frtWorkOrderService")
public class FrtWorkOrderServiceImpl extends BaseLogServiceImpl implements
        FrtWorkOrderService
{
    @Autowired
    private FrtReceptionDao frtReceptionDao;
	@Autowired
	private FrtService frtService;
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

    /**
     * 工单-基本信息
     * */
    
    public Datagrid datagridFrtWorkOrderBase(FrtWorkOrderVo fwoVo)
            throws Exception
    {
    	if(fwoVo.getCarId()!=null&&fwoVo.getCarId().length()>0)
    		fwoVo.setCarId(new String(fwoVo.getCarId().getBytes("ISO-8859-1"),"UTF-8"));
        // TODO Auto-generated method stub
        List<FrtWorkOrderBaseVo> rows = new ArrayList<FrtWorkOrderBaseVo>();
        StringBuffer sb = new StringBuffer("SELECT distinct fr.RECEPTION_ID,fc.CAR_LICENSE,fc1.CUSTOM_NAME,fr.INTER_DATE,");
        sb.append(" bct.CTYPE_NAME,fr.RECEPTION_DISTANCE,rt.REPT_NAME,bs.STF_NAME as temp1,");
        sb.append(" fpc.PRECLR_TIME,bs1.STF_NAME,fc.CAR_VIN,");
        sb.append(" fc.CAR_MOTOR_ID,bcb.CBRD_NAME,bcc.COLOR_NAME,b.tempData2,");
        sb.append(" fc.CAR_BUY_DATE,fc.CAR_ACCEPT_BACK,fc.CAR_LICENSE_DATE,fc.CAR_LAST_REPAIR_DATE,");
        sb.append(" fc.CAR_REPAIR_CNT,fc.CAR_LAST_REPAIR_DISTANCE,fc.CAR_BASINSURANCE_DATE,");
        sb.append(" fc.CAR_BUSINSURANCE_DATE,brc.RELCAMP_NAME,fcust.CUSTOM_ADDR,fcust.CUSTOM_TEL1,fcust.CUSTOM_TEL2,");
        sb.append(" fcust.LINKMAN,fc.CAR_RELATION_PERSON,fc.CAR_RELATION_TEL1,bvi.VIP_NUMBER,");
        sb.append(" bvi.END_TIME,bvi.VIP_BIRTHDAY,fcust.CUSTOM_EMAIL,fc.CAR_POSTALCODE,fc.CAR_ID,bcs.SLS_NAME,fc.CAR_RECORD,");
        sb.append(" fc.CREATE_DATE,fc.CAR_REMARK,fc.CAR_EXAMINED_DATE,fc.CAR_ANNUAL_DATE,");
        sb.append(" fc.CAR_LAST_MAINT_DATE,fc.CAR_LAST_MAINT_DISTANCE,fc.CAR_MAINT_CNT,");
        sb.append(" fc.CAR_NEXT_MAINT_DATE,fc.CAR_NEXT_MAINT_DISTANCE,fc.CAR_DISTANCE_PER_DAY,");
        sb.append(" 1 AS timeLimit");
        sb.append(" FROM frt_reception fr INNER JOIN frt_car fc  ON fr.CAR_ID=fc.CAR_ID");
        sb.append(" INNER JOIN bas_car_color bcc ON bcc.COLOR=fc.COLOR_ID ");
        sb.append(" INNER JOIN bas_car_type bct ON bct.CTYPE_ID=fc.CTYPE_ID");
        sb.append(" INNER JOIN bas_car_brand bcb ON bct.CBRD_ID=bcb.CBRD_ID");
        sb.append(" INNER JOIN frt_custom fc1 ON fc1.CUSTOM_ID=fc.CUSTOM_ID ");
        sb.append(" INNER JOIN reptype rt  ON rt.REPT_ID=fr.REPT_ID");
        sb.append(" INNER JOIN bas_stuff bs ON fr.RECEPTOR=bs.STF_ID");
        sb.append(" INNER JOIN frt_pre_clearing fpc  ON fpc.RECEPTION_ID=fr.RECEPTION_ID");
        sb.append(" INNER JOIN bas_stuff bs1 ON fpc.STF_ID=bs1.STF_ID");
        sb.append(" LEFT OUTER JOIN");
        sb.append(" (SELECT bc1.dataKey,bc1.dataValue AS tempdata2");
        sb.append(" FROM bas_childdictionary bc1,bas_parentdictionary bp1 ");
        sb.append(" WHERE bc1.parent_id=bp1.parent_id AND bp1.dataKey='"
                + Contstants.CARCLASS_TAG.CARCLASSKEY + "') b");
        sb.append(" ON b.dataKey=fc.CAR_CLASS");
        sb.append(" INNER JOIN  frt_custom fcust ON fcust.CUSTOM_ID=fr.CUSTOM_ID");
        sb.append(" INNER JOIN bas_relation_campany brc ON brc.RELCAMP_ID=fc.RELCAMP_ID");
        sb.append(" INNER JOIN bas_car_sellers bcs ON bcs.SLS_ID=fc.SLS_ID");
        sb.append(" LEFT OUTER JOIN bas_vip_infor bvi ON  bvi.VIP_ID=fc.VIP_ID");
        sb.append(" WHERE 1=1 AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
        if (fwoVo.getInterDateBegin() != null
                && fwoVo.getInterDateBegin().toString().length() > 0)
        {
            sb
                    .append(" AND fr.INTER_DATE>='" + fwoVo.getInterDateBegin()
                            + "'");
        }
        if (fwoVo.getInterDateEnd() != null
                && fwoVo.getInterDateEnd().toString().length() > 0)
        {
            sb.append(" AND fr.INTER_DATE<='" + fwoVo.getInterDateEnd() + "'");
        }
       //默认进厂日期
        if(fwoVo.getInterDateBegin() == null && fwoVo.getInterDateEnd() == null ){
        	sb.append(" and DATE_FORMAT(fr.INTER_DATE,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,fwoVo.getEnterpriseId()).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sb.append(" and DATE_FORMAT(fr.INTER_DATE,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(fr.INTER_DATE,'%Y-%m-%d %H-%i-%s')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
        	
    	}	
        if (fwoVo.getCarVin() != null && fwoVo.getCarVin().length() > 0)
        {
            sb.append(" AND fc.CAR_VIN like '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getCarVin().trim())
                    + "%'");
        }
        if (fwoVo.getPreclrInoiceType() != null
                && fwoVo.getPreclrInoiceType().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_INOICE_TYPE='"
                    + fwoVo.getPreclrInoiceType() + "'");
        }
        if (fwoVo.getCbrdId() != null && fwoVo.getCbrdId().length() > 0)
        {
            sb.append(" AND bcb.CBRD_ID='" + fwoVo.getCbrdId() + "'");
        }
        if (fwoVo.getReceptionId() != null
                && fwoVo.getReceptionId().length() > 0)
        {
            sb.append(" AND fr.RECEPTION_ID like '%"
                    + StringEscapeUtils
                            .escapeSql(fwoVo.getReceptionId().trim()) + "%'");
        }
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
        {
            sb.append(" AND fc.car_license like '%" + StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()) + "%'");
        }
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
        {
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
        }
        sb.append("  and  fr.enterprise_id="+fwoVo.getEnterpriseId() );
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderBaseVo fwobv = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwobv = new FrtWorkOrderBaseVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwobv.setReceptionId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwobv.setCarLicense(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwobv.setCustomName(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwobv.setInterDate(MyBeanUtils.getInstance().formatString(
                            objects[3].toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwobv.setCtypeName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwobv.setReceptionDistance(Integer.parseInt(objects[5]
                            .toString()));
                else
                    fwobv.setReceptionDistance(0);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwobv.setReptName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwobv.setStfName(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwobv.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[8].toString()));
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwobv.setStfName1(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fwobv.setCarVin(objects[10].toString());
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fwobv.setCarMotorId(objects[11].toString());
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fwobv.setCbrdName(objects[12].toString());
                if (objects[13] != null && objects[13].toString().length() > 0)
                    fwobv.setColorName(objects[13].toString());
                if (objects[14] != null && objects[14].toString().length() > 0)
                    fwobv.setTempData2(objects[14].toString());
                if (objects[15] != null && objects[15].toString().length() > 0)
                    fwobv.setCarBuyDate(MyBeanUtils.getInstance().formatString(
                            objects[15].toString()));
                if (objects[16] != null && objects[16].toString().length() > 0)
                    fwobv.setCarAcceptBack(Short.parseShort(objects[16]
                            .toString()));
                if (objects[17] != null && objects[17].toString().length() > 0)
                    fwobv.setCarLicenseDate(MyBeanUtils.getInstance()
                            .formatString(objects[17].toString()));
                if (objects[18] != null && objects[18].toString().length() > 0)
                    fwobv.setCarLastRepairDate(MyBeanUtils.getInstance()
                            .formatString(objects[18].toString()));
                if (objects[19] != null && objects[19].toString().length() > 0)
                    fwobv.setCarRepairCnt(Integer.parseInt(objects[19]
                            .toString()));
                else
                    fwobv.setCarRepairCnt(0);
                if (objects[20] != null && objects[20].toString().length() > 0)
                    fwobv.setCarLastRepairDistance(Integer.parseInt(objects[20]
                            .toString()));
                else
                    fwobv.setCarLastRepairDistance(0);
                if (objects[21] != null && objects[21].toString().length() > 0)
                    fwobv.setCarBasinsuranceDate(MyBeanUtils.getInstance()
                            .formatString(objects[21].toString()));
                if (objects[22] != null && objects[22].toString().length() > 0)
                    fwobv.setCarBusinsuranceDate(MyBeanUtils.getInstance()
                            .formatString(objects[22].toString()));
                if (objects[23] != null && objects[23].toString().length() > 0)
                    fwobv.setRelcampName(objects[23].toString());
                if (objects[24] != null && objects[24].toString().length() > 0)
                    fwobv.setCustomAddr(objects[24].toString());
                if (objects[25] != null && objects[25].toString().length() > 0)
                    fwobv.setCustomTel1(objects[25].toString());
                if (objects[26] != null && objects[26].toString().length() > 0)
                    fwobv.setCustomTel2(objects[26].toString());
                if (objects[27] != null && objects[27].toString().length() > 0)
                    fwobv.setLinkMan(objects[27].toString());
                if (objects[28] != null && objects[28].toString().length() > 0)
                    fwobv.setCarRealationPerson(objects[28].toString());
                if (objects[29] != null && objects[29].toString().length() > 0)
                    fwobv.setCarRealationTel1(objects[29].toString());
                if (objects[30] != null && objects[30].toString().length() > 0)
                    fwobv.setVipNumber(objects[30].toString());
                if (objects[31] != null && objects[31].toString().length() > 0)
                    fwobv.setEndTime(MyBeanUtils.getInstance().formatString(
                            objects[31].toString()));
                if (objects[32] != null && objects[32].toString().length() > 0)
                    fwobv.setVipBirthday(MyBeanUtils.getInstance()
                            .formatString(objects[32].toString()));
                if (objects[33] != null && objects[33].toString().length() > 0)
                    fwobv.setCustomEmail(objects[33].toString());
                if (objects[34] != null && objects[34].toString().length() > 0)
                    fwobv.setCarPostalCode(objects[34].toString());
                if (objects[35] != null && objects[35].toString().length() > 0)
                    fwobv.setCarId(objects[35].toString());
                if (objects[36] != null && objects[36].toString().length() > 0)
                    fwobv.setSlsName(objects[36].toString());
                if (objects[37] != null && objects[37].toString().length() > 0)
                    fwobv.setCarRecord(objects[37].toString());
                if (objects[38] != null && objects[38].toString().length() > 0)
                    fwobv.setCreateDate(MyBeanUtils.getInstance().formatString(
                            objects[38].toString()));
                if (objects[39] != null && objects[39].toString().length() > 0)
                    fwobv.setCarRemark(objects[39].toString());
                if (objects[40] != null && objects[40].toString().length() > 0)
                    fwobv.setCarExaminedDate(MyBeanUtils.getInstance()
                            .formatString(objects[40].toString()));
                if (objects[41] != null && objects[41].toString().length() > 0)
                    fwobv.setCarAnnualDate(MyBeanUtils.getInstance()
                            .formatString(objects[41].toString()));
                if (objects[42] != null && objects[42].toString().length() > 0)
                    fwobv.setCarLastMaintDate(MyBeanUtils.getInstance()
                            .formatString(objects[42].toString()));
                if (objects[43] != null && objects[43].toString().length() > 0)
                    fwobv.setCarLastMaintDistance(Integer.parseInt(objects[43]
                            .toString()));
                else
                    fwobv.setCarLastMaintDistance(0);
                if (objects[44] != null && objects[44].toString().length() > 0)
                    fwobv.setCarMaintCnt(Integer.parseInt(objects[44]
                            .toString()));
                else
                    fwobv.setCarMaintCnt(0);
                if (objects[45] != null && objects[45].toString().length() > 0)
                    fwobv.setCarNextMaintDate(MyBeanUtils.getInstance()
                            .formatString(objects[45].toString()));
                if (objects[46] != null && objects[46].toString().length() > 0)
                    fwobv.setCarNextMaintDistance(Integer.parseInt(objects[46]
                            .toString()));
                else
                    fwobv.setCarNextMaintDistance(0);
                if (objects[47] != null && objects[47].toString().length() > 0)
                    fwobv.setCarDistancePerDay(Integer.parseInt(objects[47]
                            .toString()));
                else
                    fwobv.setCarDistancePerDay(0);
                // if(objects[48]!=null&&objects[48].toString().length()>0)
                // fwobv.setTimeLimit(new
                // FormatTime().plusDate(MyBeanUtils.getInstance().getDate(objects[48].toString()),
                // new Date()));
                // else
                fwobv.setTimeLimit(0l);
                fwobv.setPreclrAndClaimant(receptionRepitemName(fwobv
                        .getReceptionId()));
                fwobv.setState("closed");
                rows.add(fwobv);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /** 工单-基本信息-子项 */
    
    public List datagridFrtWorkOrderBaseByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception
    {
        List<FrtWorkOrderBaseVo> rows = new ArrayList<FrtWorkOrderBaseVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fpc.PRECLR_ID,fpc.PRECLR_TIME,bs.STF_NAME FROM  frt_pre_clearing fpc,bas_stuff bs");
        sb.append(" WHERE bs.STF_ID=fpc.STF_ID AND  fpc.RECEPTION_ID='"
                + fwoVo.getReceptionId() + "' AND fpc.PRE_FLG="
                + Contstants.DELETE_TAG.DELETENO);
        sb.append(" UNION");
        sb
                .append(" SELECT fcm.CLAIMANTM_ID,fcm.CLAIMANTM_TIME,bs.STF_NAME FROM fin_claimant_main fcm,bas_stuff bs,frt_pre_clearing fpc");
        sb
                .append(" WHERE bs.STF_ID=fcm.CLAIMANTM_CLR_STF_ID AND fpc.PRECLR_ID=fcm.PRECLR_ID AND fcm.CLAIMANTM_TAG="
                        + Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fpc.PRE_FLG=" + Contstants.DELETE_TAG.DELETENO
                + " AND fpc.RECEPTION_ID='" + fwoVo.getReceptionId() + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderBaseVo fwobv = null;
        int tempId = 1;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwobv = new FrtWorkOrderBaseVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwobv.setPreclrAndClaimant(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwobv.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwobv.setStfName1(objects[2].toString());
                fwobv.setReceptionId(fwoVo.getReceptionId() + "" + tempId++);
                fwobv
                        .setPreclrType(Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID);
                fwobv
                        .setClaimantType(Contstants.BALANCEIDTYPE_TAG.COUNTERCLAIMBALANCEID);
                fwobv.setState("open");
                fwobv.set_parentId(fwoVo.getReceptionId());
                rows.add(fwobv);
            }
        return rows;
    }

    /** 查找维修项目与维修人员 */
    private String receptionRepitemName(String receptionId) throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT fri.REPITEM_NAME,bs.STF_NAME FROM frt_rcpt_item fri,bas_stuff bs,frt_reception fr");
        sb
                .append(" WHERE bs.STF_ID=fri.STF_ID AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO+" AND fr.RECEPTION_ID=fri.RECEPTION_ID AND fr.RECEPTION_ID='"
                        + receptionId + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        StringBuffer sbs = new StringBuffer();
        if (rowsList != null && rowsList.size() > 0)
        {
            for (Object[] objects : rowsList)
            {
                sbs.append(objects[0] + "(" + objects[1] + "),");
            }
        }
        if (sbs.length() > 0)
            return sbs.substring(0, sbs.length() - 1);
        else
            return "";
    }

    /**
     * 索赔-配件查询
     * */
    
    public Datagrid datagridFrtWorkOrderClaimParts(FrtWorkOrderVo fwoVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtWorkOrderClaimPartsVo> rows = new ArrayList<FrtWorkOrderClaimPartsVo>();
        StringBuffer sb = new StringBuffer("SELECT fp.PARTS_ID2,fp.PARTS_NAME,fcp.CLAIMANTP_COUNT,fcp.CLAIMANTP_AMOUNT,");
        sb.append(" pcp.PARTS_LATEST_TAXPRICE*fcp.CLAIMANTP_COUNT,");
        sb.append(" pcp.PARTS_LATEST_NOTAXPRICE*fcp.CLAIMANTP_COUNT,fp.PARTS_LIBRARY");
        sb.append(" FROM fin_claimant_main fcm,fin_claimant_parts fcp,frt_parts fp,frt_car fcar,");
        sb.append(" parts_change_price pcp,frt_pre_clearing fpc,frt_reception fr,fin_claims_receivables fcr");
        sb.append(" WHERE fcp.CLAIMANTM_ID=fcm.CLAIMANTM_ID AND fp.PARTS_ID=fcp.PARTS_ID AND pcp.PARTS_ID=fp.PARTS_ID");
        sb.append(" AND fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.PRECLR_ID=fcm.PRECLR_ID AND fcar.car_id=fr.car_id");
        sb.append(" AND fcr.CLAIMANTM_ID=fcm.CLAIMANTM_ID  AND fcm.CLAIMANTM_TAG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fcm.enterprise_id="+fwoVo.getEnterpriseId());
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
        {
            sb.append(" AND fcar.car_license  like '%" + StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()) + "%'");
        }
        if (fwoVo.getClaimantTimeBegin() != null
                && fwoVo.getClaimantTimeBegin().length() > 0)
        {
            sb.append(" AND fcm.CLAIMANTM_TIME>='"
                    + fwoVo.getClaimantTimeBegin() + "'");
        }
        if (fwoVo.getClaimantTimeEnd() != null
                && fwoVo.getClaimantTimeEnd().length() > 0)
        {
            sb.append(" AND fcm.CLAIMANTM_TIME<='" + fwoVo.getClaimantTimeEnd()
                    + "'");
        }
        if(fwoVo.getClaimantTimeBegin() == null &&  fwoVo.getClaimantTimeEnd() == null){
        	BasCompanyInformationSet bcis=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,fwoVo.getEnterpriseId());
			int num=1;
			if(bcis!=null&&bcis.getCiValue()!=null&&bcis.getCiValue().length()>0){
				num=Integer.parseInt(bcis.getCiValue());
			}
        	sb.append(" and DATE_FORMAT(fcm.CLAIMANTM_TIME,'%Y-%m-%d%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, num)+ "'");
        	sb.append(" and DATE_FORMAT(fcm.CLAIMANTM_TIME,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }
        if (fwoVo.getPartsCode() != null && fwoVo.getPartsCode().length() > 0)
        {
            sb.append(" AND fp.PARTS_ID2 like '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getPartsCode().trim())
                    + "%'");
        }
        if (fwoVo.getBalanceTimeBegin() != null
                && fwoVo.getBalanceTimeBegin().toString().length() > 0)
        {
            sb.append(" ");
        }
        if (fwoVo.getBalanceTimeEnd() != null
                && fwoVo.getBalanceTimeEnd().toString().length() > 0)
        {
            sb.append(" ");
        }
        if (fwoVo.getPartsName() != null
                && fwoVo.getPartsName().toString().length() > 0)
        {
            sb.append(" AND fp.PARTS_NAME LIKE '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getPartsName().trim())
                    + "%'");
        }
        if (fwoVo.getPaymentThing() != null
                && fwoVo.getPaymentThing().length() > 0)
        {
            if (fwoVo.getPaymentThing().equals(
                    Contstants.PAYMENTTHING_TAG.PAYMENTTHINGYES))
            {
                sb.append(" AND fcr.CR_UNFINISHED="
                        + Contstants.OPINIONFINISHED_TAG.FINISHED);
            }
            else if (fwoVo.getPaymentThing().equals(
                    Contstants.PAYMENTTHING_TAG.PAYMENTTHINGNO))
            {
                sb.append(" AND fcr.CR_UNFINISHED="
                        + Contstants.OPINIONFINISHED_TAG.UNPAYMENT);
            }
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderClaimPartsVo fwocp = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwocp = new FrtWorkOrderClaimPartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwocp.setPartsCode(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwocp.setPartsName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwocp.setPartsCount(Double.parseDouble(objects[2]
                            .toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwocp.setClaimantAmount(Double.parseDouble(objects[3]
                            .toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwocp.setClaimantTaxCost(Double.parseDouble(objects[4]
                            .toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwocp.setClaimantNoTaxCost(Double.parseDouble(objects[5]
                            .toString()));
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwocp.setDepotLocation(objects[6].toString());
                rows.add(fwocp);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 工单-维修项目
     * */
    
    public Datagrid datagridFrtWorkOrderItem(FrtWorkOrderVo fwoVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(fwoVo);
        List<FrtWorkOrderItemVo> rows = new ArrayList<FrtWorkOrderItemVo>();
        StringBuffer sb = new StringBuffer("SELECT fr.RECEPTION_ID, fpc.PRECLR_ID, fpc.PRECLR_TIME, bs1.STF_NAME, fc.CUSTOM_NAME, fcar.CAR_LICENSE, bct.CTYPE_NAME, fr.RECEPTION_DISTANCE, fc.LINKMAN, fc.LINKMAN_TEL");
        sb.append(" FROM frt_reception fr, frt_pre_clearing fpc, frt_car fcar, bas_car_type bct, frt_custom fc, frt_pre_wktime fpw, bas_stuff bs1");
        sb.append(" WHERE fr.CAR_ID = fcar.CAR_ID");
        sb.append(" AND fr.CUSTOM_ID = fc.CUSTOM_ID");
        sb.append(" AND fcar.CTYPE_ID = bct.CTYPE_ID");
        sb.append(" AND fr.RECEPTION_ID = fpc.RECEPTION_ID");
        sb.append(" AND fr.RECEPTOR = bs1.STF_ID");       
        sb.append(" AND fpw.PRECLR_ID=fpc.PRECLR_ID");      
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" and  fr.enterprise_id="+fwoVo.getEnterpriseId());
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
        {
            sb.append(" AND fcar.car_license like '%" + new String(fwoVo.getCarId().getBytes("ISO-8859-1"),"UTF-8") + "%'");
        }
        if (fwoVo.getPreclrTimeBegin() != null
                && fwoVo.getPreclrTimeBegin().toString().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME>='" + fwoVo.getPreclrTimeBegin()
                    + "'");
        }
        if (fwoVo.getPreclrTimeEnd() != null
                && fwoVo.getPreclrTimeEnd().toString().length() > 0)
        {
        	
            sb.append(" AND fpc.PRECLR_TIME<='" + fwoVo.getPreclrTimeEnd()
                    + "'");
        }
        if (fwoVo.getReceivePerson() != null
                && fwoVo.getReceivePerson().toString().length() > 0)
        {
            sb.append(" AND fr.RECEPTOR=" + fwoVo.getReceivePerson());
        }
        if (fwoVo.getServicePerson() != null
                && fwoVo.getServicePerson().toString().length() > 0)
        {
            sb.append(" AND fpw.STF_ID=" + fwoVo.getServicePerson());
        }
        if (fwoVo.getInterDateBegin() != null
                && fwoVo.getInterDateBegin().toString().length() > 0)
        {
            sb
                    .append(" AND fr.INTER_DATE>='" + fwoVo.getInterDateBegin()
                            + "'");
        }
        if (fwoVo.getInterDateEnd() != null
                && fwoVo.getInterDateEnd().toString().length() > 0)
        {
            sb.append(" AND fr.INTER_DATE<='" + fwoVo.getInterDateEnd() + "'");
        }
        if (fwoVo.getCbrdId() != null && fwoVo.getCbrdId().length() > 0)
        {
            sb.append(" AND bct.CBRD_ID='" + fwoVo.getCbrdId() + "'");
        }
        if (fwoVo.getReptId() != null
                && fwoVo.getReptId().toString().length() > 0)
        {
            sb.append(" AND fr.REPT_ID=" + fwoVo.getReptId());
        }
        if (fwoVo.getClaimsId() != null
                && fwoVo.getClaimsId().toString().length() > 0)
        {
            sb.append(" AND fpw.CLAIMS_TYPE=" + fwoVo.getClaimsId());
        }
        if (fwoVo.getItemName() != null && fwoVo.getItemName().length() > 0)
        {
            sb.append(" AND fpw.REPITEM_NAME like '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getItemName().trim())
                    + "%'");
        }
        if (fwoVo.getReceptionTechnician() != null
                && fwoVo.getReceptionTechnician().toString().length() > 0)
        {
            sb.append(" AND fr.RECEPTION_TECHNICIAN="
                    + fwoVo.getReceptionTechnician());
        }
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
        {
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
        }
        if (fwoVo.getShowUnBalance() != null
                && fwoVo.getShowUnBalance() == true)
        {
            sb.append(" AND fpc.PRECLR_TO_MONEY="
                    + Contstants.TOMONEY_TAG.TOMONEYNO);
        }
        else
        {
            sb.append(" AND fpc.PRECLR_TO_MONEY="
                    + Contstants.TOMONEY_TAG.TOMONEYYES);
        }
        if (fwoVo.getRepgrpId() != null
                && fwoVo.getRepgrpId().toString().length() > 0)
        {
            sb.append(" AND fr.REPGRP_ID=" + fwoVo.getRepgrpId());
        }
//        if (fwoVo.getBalanceClass() != null
//                && fwoVo.getBalanceClass().length() > 0)
//        {
//
//        }
        sb.append(" group by RECEPTION_ID ");
        if (fwoVo.getArrangeWise() != null
                && fwoVo.getArrangeWise().equals("receptionId"))
        {
            sb.append(" ORDER BY fr.RECEPTION_ID DESC");
        }
        else if (fwoVo.getArrangeWise() != null
                && fwoVo.getArrangeWise().equals("carId"))
        {
            sb.append(" ORDER BY fr.CAR_ID DESC");
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderItemVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderItemVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setReceptionId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setPreclrId(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setStfName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setCustomName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setCarLicense(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setCtypeName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwov.setReceptionDistance(Integer.parseInt(objects[7]
                            .toString()));
                else
                    fwov.setReceptionDistance(0);
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwov.setLinkMan(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwov.setLinkManTel(objects[9].toString());
                if(isExistsPreclearingItem(fwov.getPreclrId())){
                	fwov.setRepitemName("查看维修项目");
                	fwov.setState("closed");
                }else{
                	fwov.setRepitemName("无维修项目");
                	fwov.setState("open");
                }
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    private boolean isExistsPreclearingItem(String preclrId) throws Exception{
    	List list=frtReceptionDao.createSQLQuery("select fpw.preclr_id from frt_pre_wktime fpw where fpw.preclr_id='"+preclrId+"'");
    	if(list!=null&&list.size()>0)
    		return true;
    	return false;
    }
    /**
     * 工单-维修配件
     * */
    
    public Datagrid datagridFrtWorkOrderParts(FrtWorkOrderVo fwoVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(fwoVo);
        List<FrtWorkOrderPartsVo> rows = new ArrayList<FrtWorkOrderPartsVo>();
        StringBuffer sb = new StringBuffer();
        if (fwoVo.getBringPerson() != null
                && fwoVo.getBringPerson().toString().length() > 0)
        {
            sb = new StringBuffer("SELECT DISTINCT fpc.RECEPTION_ID,fr.INTER_DATE,fpc.PRECLR_TIME,bs1.STF_NAME,fc.CUSTOM_NAME,fcar.CAR_LICENSE,fr.RECEPTION_DISTANCE,");
            sb.append(" fcar.CAR_VIN,fcar.CAR_RELATION_TEL1,fc.CUSTOM_ADDR");
            sb.append(" FROM frt_pre_parts fpp,frt_parts fp,st_out_main som,frt_pre_clearing fpc,frt_reception fr,bas_stuff bs1,frt_custom fc,frt_car fcar,bas_stuff bs");
            sb.append(" WHERE  fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpp.PRECLR_ID=fpc.PRECLR_ID AND fp.PARTS_ID=fpp.PARTS_ID");
            sb.append(" AND bs1.STF_ID=fr.RECEPTOR AND fc.CUSTOM_ID=fr.CUSTOM_ID AND fcar.CAR_ID=fr.CAR_ID");
            sb.append(" AND  som.CER_NO=fpc.RECEPTION_ID AND som.PICKING_MEMBER="+ fwoVo.getBringPerson());
            sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        }
        else
        {
            sb = new StringBuffer("SELECT DISTINCT fpc.RECEPTION_ID,fr.INTER_DATE,fpc.PRECLR_TIME,bs1.STF_NAME,fc.CUSTOM_NAME,fcar.CAR_LICENSE,fr.RECEPTION_DISTANCE,");
            sb.append(" fcar.CAR_VIN,fcar.CAR_RELATION_TEL1,fc.CUSTOM_ADDR");
            sb.append(" FROM frt_pre_parts fpp,frt_parts fp,frt_pre_clearing fpc,frt_reception fr,bas_stuff bs1,frt_custom fc,frt_car fcar,bas_stuff bs");
            sb.append(" WHERE  fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpp.PRECLR_ID=fpc.PRECLR_ID AND fp.PARTS_ID=fpp.PARTS_ID");
            sb.append(" AND bs1.STF_ID=fr.RECEPTOR AND fc.CUSTOM_ID=fr.CUSTOM_ID AND fcar.CAR_ID=fr.CAR_ID");
            sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        }
        if (fwoVo.getCarId() != null && !fwoVo.getCarId().trim().equals(""))
            sb.append(" AND fcar.CAR_ID='" + StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()) + "'");
        if (fwoVo.getPreclrTimeBegin() != null
                && fwoVo.getPreclrTimeBegin().toString().length() > 0)
            sb.append(" AND DATE_FORMAT(fpc.PRECLR_TIME,'%Y-%m-%d')>='" + fwoVo.getPreclrTimeBegin()
                    + "'");
        if (fwoVo.getPreclrTimeEnd() != null
                && fwoVo.getPreclrTimeEnd().toString().length() > 0)
            sb.append(" AND DATE_FORMAT(fpc.PRECLR_TIME,'%Y-%m-%d')<='" + fwoVo.getPreclrTimeEnd()
                    + "'");
        if (fwoVo.getReceivePerson() != null
                && fwoVo.getReceivePerson().toString().length() > 0)
            sb.append(" AND fr.RECEPTOR=" + fwoVo.getReceivePerson());
        if (fwoVo.getReceptionTechnician() != null
                && fwoVo.getReceptionTechnician().toString().length() > 0)
            sb.append(" AND fr.RECEPTION_TECHNICIAN="
                    + fwoVo.getReceptionTechnician());
        if (fwoVo.getReptId() != null
                && fwoVo.getReptId().toString().length() > 0)
            sb.append(" AND fr.REPT_ID=" + fwoVo.getReptId());
        if (fwoVo.getClaimsId() != null
                && fwoVo.getClaimsId().toString().length() > 0)
            sb.append(" AND fpp.CLAIMS_TYPE=" + fwoVo.getClaimsId());
        if (fwoVo.getPartsName() != null && fwoVo.getPartsName().length() > 0)
            sb.append(" AND fpp.PARTS_NAME like '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getPartsName().trim())
                    + "%'");
        if (fwoVo.getPtypeId() != null && fwoVo.getPtypeId().length() > 0)
            sb.append(" AND fp.PTYPE_ID=" + fwoVo.getPtypeId());
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
//        if (fwoVo.getBalanceClass() != null
//                && fwoVo.getBalanceClass().length() > 0)
        sb.append(" AND fpp.enterprise_id = " + fwoVo.getEnterpriseId() );
        if (fwoVo.getPage() == 0)
            fwoVo.setPage(1);
        if (fwoVo.getRows() == 0)
            fwoVo.setRows(10);
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderPartsVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderPartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setReceptionId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setInterDate(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPreclrTime(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setStfName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setCustomName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setCarLicense(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setReceptionDistance(Integer.parseInt(objects[6].toString()));
                else
                    fwov.setReceptionDistance(0);
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwov.setCarVin(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwov.setCarRelationTel1(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwov.setCustomAddr(objects[9].toString());
                fwov.setPartsId2("查看配件");
                fwov.setState("closed");
                rows.add(fwov);

            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 结算单-工时清单
     * */
    
    public Datagrid datagridFrtWorkOrderPreClearingItem(FrtWorkOrderVo fwoVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(fwoVo);
        List<FrtWorkOrderVo> rows = new ArrayList<FrtWorkOrderVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fpc.PRECLR_ID,fpc.PRECLR_TIME,fc.CUSTOM_NAME,");
        sb
                .append(" fcar.CAR_LICENSE,fpc.PRECLR_WKTIME_AMOUNT,brc.RELCAMP_NAME,rt.REPT_NAME");
        sb.append(" FROM frt_pre_clearing fpc,frt_custom fc,frt_reception fr,");
        sb.append(" frt_car fcar,bas_relation_campany brc,reptype rt");
        sb
                .append(" WHERE fc.CUSTOM_ID=fr.CUSTOM_ID AND fr.RECEPTION_ID=fpc.RECEPTION_ID");
        sb
                .append(" AND fcar.CAR_ID=fr.CAR_ID AND fr.FIN_COM_ID=brc.RELCAMP_ID AND rt.REPT_ID=fr.REPT_ID");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        if (fwoVo.getPreclrTimeBegin() != null
                && fwoVo.getPreclrTimeBegin().toString().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME>='" + fwoVo.getPreclrTimeBegin()
                    + "'");
        }
        if (fwoVo.getPreclrTimeEnd() != null
                && fwoVo.getPreclrTimeEnd().toString().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME<='" + fwoVo.getPreclrTimeEnd()
                    + "'");
        }
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
        {
            sb.append(" AND fcar.car_license like '%" + StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()) + "%'");
        }
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
        {
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
        }
        if (fwoVo.getPreclrId() != null && fwoVo.getPreclrId().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_ID LIKE '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getPreclrId().trim())
                    + "%'");
        }
        sb.append("   and  fpc.enterprise_id="+fwoVo.getEnterpriseId());
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setPreclrId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setRepitemId(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setRepitemName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setPreclrAmount(Double.parseDouble(objects[4]
                            .toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setRelcampName(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setReptName(objects[6].toString());
                fwov.setState("closed");
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 结算单-材料清单
     * */
    
    public Datagrid datagridFrtWorkOrderPreClearingParts(FrtWorkOrderVo fwoVo)
            throws Exception
    {
    	setDefaultPreclrTimeSect(fwoVo);
        List<FrtWorkOrderVo> rows = new ArrayList<FrtWorkOrderVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fpc.PRECLR_ID,fpc.PRECLR_TIME,fc.CUSTOM_NAME,");
        sb.append(" fcar.CAR_LICENSE,fpc.PRE_MPR_MAT_AMOUNT,brc.RELCAMP_NAME,rt.REPT_NAME");
        sb.append(" FROM frt_pre_clearing fpc,frt_custom fc,frt_reception fr,");
        sb.append(" frt_car fcar,bas_relation_campany brc,reptype rt");
        sb.append(" WHERE fc.CUSTOM_ID=fr.CUSTOM_ID AND fr.RECEPTION_ID=fpc.RECEPTION_ID");
        sb.append(" AND fcar.CAR_ID=fr.CAR_ID AND fr.FIN_COM_ID=brc.RELCAMP_ID AND rt.REPT_ID=fr.REPT_ID");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        if (fwoVo.getPreclrTimeBegin() != null
                && fwoVo.getPreclrTimeBegin().toString().length() > 0)
            sb.append(" AND fpc.PRECLR_TIME>='" + fwoVo.getPreclrTimeBegin()
                    + "'");
        if (fwoVo.getPreclrTimeEnd() != null
                && fwoVo.getPreclrTimeEnd().toString().length() > 0)
            sb.append(" AND fpc.PRECLR_TIME<='" + fwoVo.getPreclrTimeEnd()
                    + "'");
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
            sb.append(" AND fcar.car_license like '%" + new String(StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()).getBytes("iso-8859-1"),"utf-8") + "%'");
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
        if (fwoVo.getPreclrId() != null && fwoVo.getPreclrId().length() > 0)
            sb.append(" AND fpc.PRECLR_ID LIKE '%"
                    + StringEscapeUtils.escapeSql(fwoVo.getPreclrId().trim())
                    + "%'");
        sb.append("  and  fpc.enterprise_id="+fwoVo.getEnterpriseId()  );
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setPreclrId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPartsId(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setPartsName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setPreclrAmount(Double.parseDouble(objects[4]
                            .toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setRelcampName(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setReptName(objects[6].toString());
                fwov.setState("closed");
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 维修业务统计报表
     * */
    
    public Datagrid datagridFrtWorkOrderRepaiStatisticalStatement(
            FrtWorkOrderVo fwoVo) throws Exception
    {
    	setDefaultPreclrTimeSect(fwoVo);
        List<FrtWorkOrderRepaiStatisticalStatementVo> rows = new ArrayList<FrtWorkOrderRepaiStatisticalStatementVo>();
        StringBuffer sb = new StringBuffer("SELECT fpc.PRECLR_TIME, fr.INTER_DATE,fr.RECEPTION_ID,fc.CAR_VIN,fc.CAR_LICENSE,bcb.CBRD_NAME,");
        sb.append(" bct.CTYPE_NAME,fr.RECEPTION_DISTANCE,rt.REPT_NAME,fct.CUSTOM_NAME,fct.CUSTOM_ADDR,");
        sb.append(" fct.CUSTOM_TEL1,bs.STF_NAME,fpc.PRECLR_WKTIME_AMOUNT,fpc.PRECLR_OTHER_AMOUNT,fpc.PRECLR_MANAGEMENT_FEE,fpc.PRECLR_REAL_AMOUNT,fpc.PRECLR_MATERIAL_RATE");
        sb.append(" FROM frt_pre_clearing fpc,frt_reception fr,frt_car fc,bas_car_type bct,reptype rt,");
        sb.append(" frt_custom fct,bas_stuff bs,bas_car_brand bcb");
        sb.append(" WHERE fr.RECEPTION_ID=fpc.RECEPTION_ID");
        sb.append(" AND fc.CAR_ID=fr.CAR_ID AND fc.CTYPE_ID=bct.CTYPE_ID");
        sb.append(" AND rt.REPT_ID=fr.REPT_ID AND fr.CUSTOM_ID=fct.CUSTOM_ID");
        sb.append(" AND bs.STF_ID=fr.RECEPTOR AND bcb.CBRD_ID=bct.CBRD_ID");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fpc.enterprise_id="+fwoVo.getEnterpriseId());
        if (fwoVo.getInterDateBegin() != null
                && fwoVo.getInterDateBegin().toString().length() > 0)
        {
            sb.append(" AND fr.INTER_DATE>='" + fwoVo.getInterDateBegin()+ "'");
        }
        if (fwoVo.getInterDateEnd() != null
                && fwoVo.getInterDateEnd().toString().length() > 0)
        {
            sb.append(" AND fr.INTER_DATE<='" + fwoVo.getInterDateEnd() + "'");
        }
        if (fwoVo.getCarId() != null && fwoVo.getCarId().length() > 0)
        {
            sb.append(" AND fc.car_license like '%" + StringEscapeUtils.escapeSql(fwoVo.getCarId().trim()) + "%'");
        }
        if (fwoVo.getReceivePerson() != null
                && fwoVo.getReceivePerson().toString().length() > 0)
        {
            sb.append(" AND fr.RECEPTOR=" + fwoVo.getReceivePerson());
        }
        if (fwoVo.getReptId() != null
                && fwoVo.getReptId().toString().length() > 0)
        {
            sb.append(" AND fr.REPT_ID=" + fwoVo.getReptId());
        }
        if (fwoVo.getPreclrTimeBegin() != null
                && fwoVo.getPreclrTimeBegin().toString().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME>='" + fwoVo.getPreclrTimeBegin()
                    + "'");
        }
        if (fwoVo.getPreclrTimeEnd() != null
                && fwoVo.getPreclrTimeEnd().toString().length() > 0)
        {
            sb.append(" AND fpc.PRECLR_TIME<='" + fwoVo.getPreclrTimeEnd()
                    + "'");
        }
        if (fwoVo.getCustomId() != null && fwoVo.getCustomId().length() > 0)
        {
            sb.append(" AND fr.CUSTOM_ID='" + fwoVo.getCustomId() + "'");
        }
        if (fwoVo.getCbrdId() != null && fwoVo.getCbrdId().length() > 0)
        {
            sb.append(" AND bct.CBRD_ID='" + fwoVo.getCbrdId() + "'");
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderRepaiStatisticalStatementVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderRepaiStatisticalStatementVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setPreclrTime(MyBeanUtils.getInstance().formatString(
                            objects[0].toString()));
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setInterDate(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setReceptionId(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setCarVin(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setCarLicense(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setCbrdName(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setCtypeName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwov.setReceptionDistance(Integer.parseInt(objects[7]
                            .toString()));
                else
                    fwov.setReceptionDistance(0);
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwov.setReptName(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwov.setCustomName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fwov.setCustomAddr(objects[10].toString());
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fwov.setCustomTel1(objects[11].toString());
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fwov.setStfName(objects[12].toString());
                if (objects[13] != null && objects[13].toString().length() > 0)
                    fwov.setPreclrWktimeAmount(Double.parseDouble(objects[13]
                            .toString()));
                else
                    fwov.setPreclrWktimeAmount(0.00d);
                if (objects[14] != null && objects[14].toString().length() > 0)
                    fwov.setPreclrOtherAmount(Double.parseDouble(objects[14]
                            .toString()));
                else
                    fwov.setPreclrOtherAmount(0.00d);
                if (objects[15] != null && objects[15].toString().length() > 0)
                    fwov.setPreclrManagementFee(Double.parseDouble(objects[15]
                            .toString()));
                else
                    fwov.setPreclrManagementFee(0.00d);
                if (objects[16] != null && objects[16].toString().length() > 0)
                    fwov.setPreclrRealAmount(Double.parseDouble(objects[16]
                            .toString()));
                else
                    fwov.setPreclrRealAmount(0.00d);
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPreclrPartsAmount(getPrePartsRateAmount(objects[2]
                            .toString(), Double
                            .parseDouble(objects[17] == null ? "1"
                                    : objects[17].toString())));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov
                            .setRepitemName(weaveRepitemName(objects[2]
                                    .toString()));
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    private Double getPrePartsRateAmount(String receptionId, Double partsRate)
            throws Exception
    {
        String sql = "SELECT sum(fpp.PARTS_RATE_AMOUNT) FROM frt_pre_parts fpp,frt_pre_clearing fpc where  fpc.PRECLR_ID=fpp.PRECLR_ID AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND fpc.RECEPTION_ID ='"
                + receptionId.trim() + "'";
        List rowsList = frtReceptionDao.createSQLQuery(sql, null);
        Double partsRateAmount = 0.00d;
        if (rowsList != null)
        {
            partsRateAmount = Double
                    .parseDouble(rowsList.get(0) == null ? "0.00" : rowsList
                            .get(0).toString());
            partsRateAmount *= partsRate;
        }
        return partsRateAmount;
    }

    private String weaveRepitemName(String receptionId) throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT fpw.REPITEM_NAME,bs.STF_NAME FROM frt_pre_wktime fpw,bas_stuff bs,frt_pre_clearing fpc");
        sb
                .append(" WHERE bs.STF_ID=fpw.STF_ID  AND fpc.PRECLR_ID=fpw.PRECLR_ID AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND fpc.RECEPTION_ID='"
                        + receptionId + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        StringBuffer sbs = new StringBuffer();
        if (rowsList != null && rowsList.size() > 0)
        {
            for (Object[] objects : rowsList)
            {
                sbs.append(objects[0] + "(" + objects[1] + "),");
            }
        }
        if (sbs.length() > 0)
            return sbs.substring(0, sbs.length() - 1);
        else
            return "";
    }

    /**
     * 工单-结算情况-工时清单
     * */
    
    public Datagrid datagridFrtWorkOrderSettlementSituationItem(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        // TODO Auto-generated method stub

        List<FrtWorkOrderSettlementSituationItemVo> rows = new ArrayList<FrtWorkOrderSettlementSituationItemVo>();

        StringBuffer sb = new StringBuffer();
        if (fwoVo.getPreclrId() != null && fwoVo.getPreclrId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT fpw.REPITEM_ID,fpw.REPITEM_NAME,fpw.WKTIME_NUM,bs.STF_NAME, bct.CLAIMS_NAME,fri.RECEPTION_REMARK");
            sb
                    .append(" FROM frt_pre_wktime fpw,bas_stuff bs,bas_claims_type bct,frt_rcpt_item fri,frt_pre_clearing fpc");
            sb
                    .append(" WHERE bs.STF_ID=fpw.STF_ID AND bct.CLAIMS_ID=fpw.CLAIMS_TYPE");
            sb
                    .append(" AND fri.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.PRECLR_ID=fpw.PRECLR_ID AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
            sb.append(" AND fpc.PRECLR_ID='" + fwoVo.getPreclrId() + "'");
        }
        else if (fwoVo.getClaimantmId() != null
                && fwoVo.getClaimantmId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT fct.REPITEM_ID,fct.REPITEM_NAME,fct.CLAIMANTT_TIME,");
            sb.append(" bs.STF_NAME,'' AS CLAIMS_NAME,'' AS RECEPTION_REMARK");
            sb
                    .append(" FROM fin_claimant_time fct,bas_stuff bs,fin_claimant_main fcm");
            sb
                    .append(" WHERE bs.STF_ID=fct.STF_ID AND fcm.CLAIMANTM_ID=fct.CLAIMANTM_ID");
            sb.append(" AND fcm.CLAIMANTM_ID='" + fwoVo.getClaimantmId() + "'");
        }
        else if (fwoVo.getReceptionId() != null
                && fwoVo.getReceptionId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT fpw.REPITEM_ID,fpw.REPITEM_NAME,fpw.WKTIME_NUM,bs.STF_NAME, bct.CLAIMS_NAME,fri.RECEPTION_REMARK");
            sb
                    .append(" FROM frt_pre_wktime fpw,bas_stuff bs,bas_claims_type bct,frt_rcpt_item fri,frt_pre_clearing fpc");
            sb
                    .append(" WHERE bs.STF_ID=fpw.STF_ID AND bct.CLAIMS_ID=fpw.CLAIMS_TYPE AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
            sb
                    .append(" AND fri.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.PRECLR_ID=fpw.PRECLR_ID");
            sb.append(" AND fpc.RECEPTION_ID='" + fwoVo.getReceptionId() + "'");
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderSettlementSituationItemVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderSettlementSituationItemVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setRepitemId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setRepitemName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov
                            .setWktimeNum(Double.parseDouble(objects[2]
                                    .toString()));
                else
                    fwov.setWktimeNum(0.00d);
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setStfName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setClaimsName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setReceptionRemark(objects[5].toString());
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 工单-结算情况-材料清单
     * */
    
    public Datagrid datagridFrtWorkOrderSettlementSituationParts(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtWorkOrderSettlementSituationPartsVo> rows = new ArrayList<FrtWorkOrderSettlementSituationPartsVo>();
        StringBuffer sb = new StringBuffer();
        if (fwoVo.getPreclrId() != null && fwoVo.getPreclrId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT bcp.CAR_PART_NAME,fp.FIT_PTYPE,fpp.PARTS_ID,fpp.PARTS_NAME,bpu.PUNIT_NAME,fpp.PARTS_COUNT,fpp.PARTS_PRICE,fpp.PARTS_AMOUNT,fp.PARTS_FLAG,bct.CLAIMS_NAME");
            sb
                    .append(" FROM frt_pre_parts fpp,frt_parts fp,bas_parts_unit bpu,bas_claims_type bct,bas_car_parts bcp,frt_pre_clearing fpc");
            sb
                    .append(" WHERE fp.PARTS_ID=fpp.PARTS_ID AND bct.CLAIMS_ID=fpp.CLAIMS_TYPE AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
            sb
                    .append(" AND bpu.PUNIT_ID=fp.PUNIT_NAME  AND bcp.CAR_PART_ID=fp.POS_NAME");
            sb.append(" AND fpc.PRECLR_ID=fpp.PRECLR_ID");
            sb.append(" AND fpc.PRECLR_ID='" + fwoVo.getPreclrId() + "'");
        }
        else if (fwoVo.getClaimantmId() != null
                && fwoVo.getClaimantmId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT bcp.CAR_PART_NAME,fp.FIT_PTYPE,fcp.PARTS_ID,fcp.PARTS_NAME,");
            sb
                    .append(" bpu.PUNIT_NAME,fcp.CLAIMANTP_COUNT,fcp.CLAIMANTP_PRICE,");
            sb.append(" fcp.CLAIMANTP_AMOUNT,fp.PARTS_FLAG,'' AS CLAIMS_NAME");
            sb
                    .append(" FROM fin_claimant_parts fcp,frt_parts fp,bas_parts_unit bpu,bas_car_parts bcp,fin_claimant_main fcm");
            sb.append(" WHERE fp.PARTS_ID=fcp.PARTS_ID");
            sb
                    .append(" AND bpu.PUNIT_ID=fp.PUNIT_NAME  AND bcp.CAR_PART_ID=fp.POS_NAME");
            sb
                    .append(" AND fcm.CLAIMANTM_ID=fcp.CLAIMANTM_ID AND fcm.CLAIMANTM_ID='"
                            + fwoVo.getClaimantmId() + "'");
        }
        else if (fwoVo.getReceptionId() != null
                && fwoVo.getReceptionId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT DISTINCT bcp.CAR_PART_NAME,fp.FIT_PTYPE,fpp.PARTS_ID,fpp.PARTS_NAME,bpu.PUNIT_NAME,fpp.PARTS_COUNT,fpp.PARTS_PRICE,fpp.PARTS_AMOUNT,fp.PARTS_FLAG,bct.CLAIMS_NAME");
            sb
                    .append(" FROM frt_pre_parts fpp  INNER JOIN frt_pre_clearing fpc ON fpc.PRECLR_ID = fpp.PRECLR_ID INNER JOIN frt_parts fp  ON  fp.PARTS_ID = fpp.PARTS_ID  "+
                    		" INNER JOIN bas_parts_unit bpu  ON bpu.PUNIT_ID = fp.PUNIT_NAME  INNER JOIN bas_claims_type bct ON bct.CLAIMS_ID = fpp.CLAIMS_TYPE  LEFT JOIN bas_car_parts bcp ON bcp.CAR_PART_ID = fp.POS_NAME ");
            sb
                    .append("  WHERE  fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);;
            sb.append(" AND fpc.RECEPTION_ID='" + fwoVo.getReceptionId() + "'");
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderSettlementSituationPartsVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderSettlementSituationPartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setCarPartName(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setFitPtype(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPartsId(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setPartsName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setPunitName(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setPartsCount(Double
                            .parseDouble(objects[5].toString()));
                else
                    fwov.setPartsCount(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setPartsPrice(Double
                            .parseDouble(objects[6].toString()));
                else
                    fwov.setPartsPrice(0.00d);
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwov.setPartsAmount(Double.parseDouble(objects[7]
                            .toString()));
                else
                    fwov.setPartsAmount(0.00d);
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwov.setPartsFlag((Boolean)objects[8]);
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwov.setClaimsName(objects[9].toString());
                rows.add(fwov);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 索赔出库查询
     * */
    
    public Datagrid datagridFrtWorkOrderclaimExwarehouse(FrtWorkOrderVo fwoVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtWorkOrderclaimExwarehouseVo> rows = new ArrayList<FrtWorkOrderclaimExwarehouseVo>();
        StringBuffer sb = new StringBuffer("SELECT DISTINCT fr.RECEPTION_ID,fr.INTER_DATE,fcar.CAR_LICENSE,fcar.CAR_VIN,fcar.CAR_MOTOR_ID,bcb.CBRD_NAME,");
        sb.append(" bct.CTYPE_NAME,fr.RECEPTION_DISTANCE,fcar.CAR_FST_INSURANCE_DATE,");
        sb.append(" fc.CUSTOM_NAME,fc.CUSTOM_ADDR,fc.CUSTOM_TEL1,bs.STF_NAME");
        sb.append(" FROM frt_reception fr,st_out_main som,st_out_item soi,frt_car fcar,");
        sb.append(" bas_car_brand bcb,bas_car_type bct,frt_custom fc,bas_stuff bs,bas_claims_type bclaims");
        sb.append(" WHERE fr.RECEPTION_ID=som.CER_NO AND bs.STF_ID=fr.RECEPTOR AND som.STOM_ID=soi.STOM_ID");
        sb.append(" AND fcar.CAR_ID=fr.CAR_ID AND bct.CTYPE_ID=fcar.CTYPE_ID");
        sb.append(" AND bct.CBRD_ID=bcb.CBRD_ID AND fc.CUSTOM_ID=fr.CUSTOM_ID");
        sb.append(" AND bclaims.CLAIMS_ID=soi.CLAIMS_TYPE");
        sb.append(" AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fr.enterprise_id="+fwoVo.getEnterpriseId());
        if (fwoVo.getStomDateBegin() != null
                && fwoVo.getStomDateBegin().length() > 0)
        {
            sb.append(" AND som.STOM_DATE>='" + fwoVo.getStomDateBegin() + "'");
        }
        if (fwoVo.getStomDateEnd() != null
                && fwoVo.getStomDateEnd().length() > 0)
        {
            sb.append(" AND som.STOM_DATE<='" + fwoVo.getStomDateEnd() + "'");
        }
            sb.append(" AND bclaims.CLAIMS_FLG="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
      
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderclaimExwarehouseVo fwo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwo = new FrtWorkOrderclaimExwarehouseVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwo.setReceptionId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwo.setStomTime(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwo.setCarLicense(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwo.setCarVin(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwo.setCarMotorId(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwo.setCbrdName(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwo.setCtypeName(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwo.setReceptionDistance(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwo.setCarFstInsuranceDate(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwo.setCustomName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fwo.setCustomAddr(objects[10].toString());
                if (objects[11] != null && objects[11].toString().length() > 0)
                    fwo.setCustomTel(objects[11].toString());
                if (objects[12] != null && objects[12].toString().length() > 0)
                    fwo.setStfName(objects[12].toString());
                fwo.setStomId("查看详情");
                fwo.setState("closed");
                rows.add(fwo);
            }
        int total = frtReceptionDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 索赔出库-子项查询
     * */
    
    public List datagridFrtWorkOrderclaimExwarehouseByStomId(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        List<FrtWorkOrderclaimExwarehouseVo> rows = new ArrayList<FrtWorkOrderclaimExwarehouseVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT som.STOM_ID,som.STOM_DATE,soi.ITEM_COUNT,soi.TAX_CAST,soi.NOTAX_CAST,soi.TAX_CASTAMONT,soi.NOTAX_CASTAMONT,");
        sb.append(" fp.PARTS_ID,fp.PARTS_ID2,fp.PARTS_NAME,bct.CLAIMS_NAME");
        sb
                .append(" FROM frt_parts fp,frt_reception fr,st_out_main som,st_out_item soi,bas_claims_type bct");
        sb
                .append(" WHERE fp.PARTS_ID=soi.PARTS_ID AND som.STOM_ID=soi.STOM_ID AND fr.RECEPTION_ID=som.CER_NO");
        sb.append(" and soi.CLAIMS_TYPE=bct.CLAIMS_ID AND fr.RECEPTION_ID='"
                + fwoVo.getReceptionId() + "'");
        sb.append(" AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
        if (fwoVo.getStomDateBegin() != null
                && fwoVo.getStomDateBegin().length() > 0)
        {
            sb.append(" AND som.STOM_DATE>='" + fwoVo.getStomDateBegin() + "'");
        }
        if (fwoVo.getStomDateEnd() != null
                && fwoVo.getStomDateEnd().length() > 0)
        {
            sb.append(" AND som.STOM_DATE<='" + fwoVo.getStomDateEnd() + "'");
        }
        sb.append(" AND bct.CLAIMS_FLG="+Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES);
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, fwoVo.getPage(), fwoVo.getRows());
        FrtWorkOrderclaimExwarehouseVo fwo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwo = new FrtWorkOrderclaimExwarehouseVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwo.setStomId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwo.setStomTime(MyBeanUtils.getInstance().formatString(
                            objects[1].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwo.setDepotCount(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwo.setPartsTaxCost(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwo.setPartsNoTaxCost(objects[4].toString());
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwo.setCostTaxAmount(objects[5].toString());
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwo.setCostNoTaxAmount(objects[6].toString());
                if (objects[7] != null && objects[7].toString().length() > 0)
                    fwo.setPartsId(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    fwo.setPartsId2(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    fwo.setPartsName(objects[9].toString());
                if (objects[10] != null && objects[10].toString().length() > 0)
                    fwo.setProperty(objects[10].toString());
                fwo.setState("open");
                fwo.set_parentId(fwoVo.getReceptionId());
                rows.add(fwo);
            }
        return rows;
    }

    /**
     * 工单-结算情况-费用明细
     * */
    
    public frtWorkOrderSettlementSituationBalanceVo datagridFrtWorkOrderSettlementSituationBalance(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        if (fwoVo.getPreclrId() != null && fwoVo.getPreclrId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT fpc.PRE_MPR_MAT_AMOUNT,fpc.PRECLR_MATERIAL_RATE,fpc.PRECLR_WKTIME_AMOUNT,");
            sb
                    .append(" fpc.PRECLR_WKTIME_RATE,fpc.PRECLR_OTHER_AMOUNT,fpc.PRECLR_MANAGEMENT_FEE,fpc.PRECLR_SUM_AMOUNT");
            sb.append(" FROM frt_pre_clearing fpc WHERE fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND  fpc.PRECLR_ID='"
                    + fwoVo.getPreclrId() + "'");
        }
        else if (fwoVo.getClaimantmId() != null
                && fwoVo.getClaimantmId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT fcm.CLAIMANTM_PARTS_AMOUNT,'1' AS PRECLR_MATERIAL_RATE,fcm.CLAIMANTM_TIME_AMOUNT,");
            sb
                    .append(" '1' AS PRECLR_WKTIME_RATE,fcm.CLAIMANTM_OTHER_AMOUNT,fcm.CLAIMANTM_MANAGEMENT_FEE,fcm.CLAIMANTM_AMOUNT");
            sb.append(" FROM fin_claimant_main fcm WHERE fcm.CLAIMANTM_TAG="+Contstants.DELETE_TAG.DELETENO+" AND fcm.CLAIMANTM_ID='"
                    + fwoVo.getClaimantmId() + "'");
        }
        else if (fwoVo.getReceptionId() != null
                && fwoVo.getReceptionId().length() > 0)
        {
            sb = new StringBuffer(
                    "SELECT fpc.PRE_MPR_MAT_AMOUNT,fpc.PRECLR_MATERIAL_RATE,fpc.PRECLR_WKTIME_AMOUNT,");
            sb
                    .append(" fpc.PRECLR_WKTIME_RATE,fpc.PRECLR_OTHER_AMOUNT,fpc.PRECLR_MANAGEMENT_FEE,fpc.PRECLR_SUM_AMOUNT");
            sb.append(" FROM frt_pre_clearing fpc WHERE fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+" AND  fpc.RECEPTION_ID='"
                    + fwoVo.getReceptionId() + "'");
        }
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        frtWorkOrderSettlementSituationBalanceVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new frtWorkOrderSettlementSituationBalanceVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setPreMprMatAmount(Double.parseDouble(objects[0]
                            .toString()));
                else
                    fwov.setPreMprMatAmount(0.00d);
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setPreclrMaterialRate(Double.parseDouble(objects[1]
                            .toString()) * 100);
                else
                    fwov.setPreMprMatAmount(100d);
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPreclrWktimeAmount(Double.parseDouble(objects[2]
                            .toString()));
                else
                    fwov.setPreclrWktimeAmount(0.00d);
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setPreclrWktimeRate(Double.parseDouble(objects[3]
                            .toString()) * 100);
                else
                    fwov.setPreclrWktimeRate(100d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setPreclrOtherAmount(Double.parseDouble(objects[4]
                            .toString()));
                else
                    fwov.setPreclrOtherAmount(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setPreclrManagementFee(Double.parseDouble(objects[5]
                            .toString()));
                else
                    fwov.setPreclrManagementFee(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    fwov.setPreclrSumAmount(Double.parseDouble(objects[6]
                            .toString()));
                else
                    fwov.setPreclrSumAmount(0.00d);
                fwov.setPreclrWktimeAmounts(fwov.getPreclrWktimeAmount()
                        * (fwov.getPreclrWktimeRate() / 100));
                fwov.setPreMprMatAmounts(fwov.getPreMprMatAmount()
                        * (fwov.getPreclrMaterialRate() / 100));
                break;
            }
        return fwov;
    }

    /**
     * 工单-维修项目-子项
     * */
    
    public List datagridFrtWorkOrderItemByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtWorkOrderItemVo> rows = new ArrayList<FrtWorkOrderItemVo>();
        StringBuffer sb = new StringBuffer("SELECT fpw.REPITEM_NAME,fpw.WKTIME_NUM,fpw.WKTIME_AMOUNT,bs.STF_NAME, bclt.CLAIMS_NAME");
        sb.append(" FROM frt_pre_wktime fpw,bas_stuff bs,bas_stuff bs1,bas_claims_type bclt,frt_pre_clearing fpc,");
        sb.append(" frt_reception fr,frt_custom fc,frt_car fcar,bas_car_type bct");
        sb.append(" WHERE bs.STF_ID=fpw.STF_ID AND bclt.CLAIMS_ID=fpw.CLAIMS_TYPE");
        sb.append(" AND fpc.PRECLR_ID=fpw.PRECLR_ID AND fr.RECEPTION_ID=fpc.RECEPTION_ID");
        sb.append(" AND fc.CUSTOM_ID=fr.CUSTOM_ID AND fcar.CAR_ID=fr.CAR_ID");
        sb.append(" AND bct.CTYPE_ID=fcar.CTYPE_ID AND bs1.STF_ID=fr.RECEPTOR");
        sb.append(" AND fpc.RECEPTION_ID='" + fwoVo.getReceptionId() + "'");
        sb.append(" AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderItemVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderItemVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setRepitemName(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov
                            .setWktimeNum(Double.parseDouble(objects[1]
                                    .toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setWktimeAmount(Double.parseDouble(objects[2]
                            .toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setStfName1(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setClaimsName(objects[4].toString());
                fwov.setState("open");
                fwov.set_parentId(fwoVo.getReceptionId());
                rows.add(fwov);
            }
        return rows;
    }

    /**
     * 工单-维修配件-子项
     * */
    
    public List datagridFrtWorkOrderPartsByReceptionId(FrtWorkOrderVo fwoVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtWorkOrderPartsVo> rows = new ArrayList<FrtWorkOrderPartsVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fp.PARTS_ID2,fpp.PARTS_NAME,bpp.POS_NAME,bpu.PUNIT_NAME,fpp.PARTS_COUNT,");
        sb
                .append(" fpp.PARTS_PRICE, fpp.PARTS_AMOUNT, bs.STF_NAME,fp.PARTS_FLAG,");
        sb
                .append(" bct.CLAIMS_NAME,soi.NOTAX_CAST,soi.TAX_CAST,soi.NOTAX_CASTAMONT,soi.TAX_CASTAMONT");
        sb
                .append(" FROM frt_pre_parts fpp,frt_parts fp,bas_parts_position bpp,");
        sb
                .append(" bas_parts_unit bpu,st_out_main som,st_out_item soi,bas_claims_type bct,");
        sb.append(" bas_stuff bs,frt_pre_clearing fpc");
        sb
                .append(" WHERE fp.PARTS_ID=fpp.PARTS_ID  AND fp.POS_NAME=bpp.POS_ID");
        sb
                .append(" AND bpu.PUNIT_ID=fp.PUNIT_NAME  AND som.PICKING_MEMBER=bs.STF_ID");
        sb
                .append(" AND soi.STOM_ID=som.STOM_ID   AND soi.PARTS_ID=fp.PARTS_ID AND som.CER_NO=fpc.RECEPTION_ID");
        sb
                .append(" AND bct.CLAIMS_ID=fpp.CLAIMS_TYPE  AND fpp.PRECLR_ID=fpc.PRECLR_ID AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb.append(" AND fpc.RECEPTION_ID='" + fwoVo.getReceptionId() + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderPartsVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderPartsVo();
                if (objects[0] != null)
                    fwov.setPartsId2(objects[0].toString());
                if (objects[1] != null)
                    fwov.setPartsName(objects[1].toString());
                if (objects[2] != null)
                    fwov.setPosName(objects[2].toString());
                if (objects[3] != null)
                    fwov.setPunitName(objects[3].toString());
                if (objects[4] != null)
                    fwov.setPartsCount(Double
                            .parseDouble(objects[4].toString()));
                else
                    fwov.setPartsCount(0.00d);
                if (objects[5] != null)
                    fwov.setPartsPrice(Double
                            .parseDouble(objects[5].toString()));
                else
                    fwov.setPartsPrice(0.00d);
                if (objects[6] != null)
                    fwov.setPartsAmount(Double.parseDouble(objects[6]
                            .toString()));
                else
                    fwov.setPartsAmount(0.00d);
                if (objects[7] != null)
                    fwov.setStfName1(objects[7].toString());
                if (objects[8] != null)
                    fwov.setPartsFlg(objects[8].toString().charAt(0));
                if (objects[9] != null)
                    fwov.setClaimsName(objects[9].toString());
                if (objects[10] != null)
                    fwov.setNotaxCast(Double
                            .parseDouble(objects[10].toString()));
                else
                    fwov.setNotaxCast(0.00d);
                if (objects[11] != null)
                    fwov.setTaxCast(Double.parseDouble(objects[11].toString()));
                else
                    fwov.setTaxCast(0.00d);
                if (objects[12] != null)
                    fwov.setNotaxCastAmont(Double.parseDouble(objects[12]
                            .toString()));
                else
                    fwov.setNotaxCastAmont(0.00d);
                if (objects[13] != null)
                    fwov.setTaxCastAmont(Double.parseDouble(objects[13]
                            .toString()));
                else
                    fwov.setTaxCastAmont(0.00d);
                fwov.setState("open");
                fwov.set_parentId(fwoVo.getReceptionId());
                rows.add(fwov);
            }
        return rows;
    }

    /**
     * 结算单-工时清单-子项
     * */
    
    public List datagridFrtWorkOrderPreClearingItemByPreclrId(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        List<FrtWorkOrderVo> rows = new ArrayList<FrtWorkOrderVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fpw.REPITEM_ID,fpw.REPITEM_NAME,WKTIME_NUM,WKTIME_AMOUNT,bs.STF_NAME");
        sb
                .append(" FROM frt_pre_wktime fpw,bas_stuff bs WHERE bs.STF_ID=fpw.STF_ID AND fpw.PRECLR_ID='"
                        + fwoVo.getPreclrId() + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setRepitemId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setRepitemName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setSumCount(Double.parseDouble(objects[2].toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setPreclrAmount(Double.parseDouble(objects[3]
                            .toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setStfName(objects[4].toString());
                fwov.setPreclrId("");
                fwov.setState("open");
                fwov.set_parentId(fwoVo.getPreclrId());
                rows.add(fwov);
            }
        return rows;
    }

    /**
     * 结算单-材料清单-子项
     * */
    
    public List datagridFrtWorkOrderPreClearingPartsByPreclrId(
            FrtWorkOrderVo fwoVo) throws Exception
    {
        List<FrtWorkOrderVo> rows = new ArrayList<FrtWorkOrderVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT fp.PARTS_ID,fp.PARTS_NAME,bpu.PUNIT_NAME,fpp.PARTS_PRICE,fpp.PARTS_COUNT,fpp.PARTS_AMOUNT");
        sb.append(" FROM frt_pre_parts fpp,bas_parts_unit bpu,frt_parts fp");
        sb
                .append(" WHERE fp.PARTS_ID=fpp.PARTS_ID AND fp.PUNIT_NAME=bpu.PUNIT_ID");
        sb.append(" AND fpp.PRECLR_ID='" + fwoVo.getPreclrId() + "'");
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null);
        FrtWorkOrderVo fwov = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fwov = new FrtWorkOrderVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    fwov.setPartsId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fwov.setPartsName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fwov.setPunitName(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fwov.setPartsPrice(Double
                            .parseDouble(objects[3].toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fwov.setSumCount(Double.parseDouble(objects[4].toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fwov.setPreclrAmount(Double.parseDouble(objects[5]
                            .toString()));
                fwov.setPreclrId("");
                fwov.setState("open");
                fwov.set_parentId(fwoVo.getPreclrId());
                rows.add(fwov);
            }
        return rows;
    }
    private void setDefaultPreclrTimeSect(FrtWorkOrderVo fwoVo)throws Exception{
		if((fwoVo.getPreclrTimeBegin()==null||fwoVo.getPreclrTimeBegin().length()==0)&&
				(fwoVo.getPreclrTimeEnd()==null||fwoVo.getPreclrTimeEnd().length()==0)){
			String temp=frtService.findDefaultPreclrTimeSect();
			if(temp!=null&&temp.length()>0){
				Date date=new Date();
				long time=date.getTime();
				long count=Long.parseLong(temp);
				time=time-Math.abs(count*60*60*24*1000);
				fwoVo.setPreclrTimeEnd(MyBeanUtils.getInstance().getString(date));
				fwoVo.setPreclrTimeBegin(MyBeanUtils.getInstance().getString(new Date(time)));
			}
		}
	}
}
