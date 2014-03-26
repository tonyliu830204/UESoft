package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasClaimsTypeDao;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.dao.FrtRcptItemDao;
import com.syuesoft.frt.dao.FrtRcptPartsDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.dao.FrtReceptionVehicleStructureDao;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.service.FrtWorkshopManagerService;
import com.syuesoft.frt.vo.FrtEmergeVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtPrePartsVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.model.BasPartsUnit;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.model.FrtPreClearingCost;
import com.syuesoft.model.FrtPreParts;
import com.syuesoft.model.FrtPreWktime;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.model.FrtRcptParts;
import com.syuesoft.model.FrtReception;
import com.syuesoft.model.FrtReceptionCost;
import com.syuesoft.model.FrtReceptionVehicleStructure;
import com.syuesoft.model.Reptype;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.IncrementId;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;
/**
 * 车间管理Service
 * @author Liujian
 */
@Service("workshopManagerService")
public class FrtWorkshopManagerServiceImpl extends BaseLogServiceImpl implements
        FrtWorkshopManagerService{

    @Autowired
    private FrtReceptionDao frtReceptionDao;
    @Autowired
    private FrtReceptionVehicleStructureDao frtReceptionVehicleStructureDao;
    @Autowired
    private FrtRcptPartsDao frtRcptPartsDao;
    @Autowired
    private FrtRcptItemDao frtRcptItemDao;
    @Autowired
    private FrtPreClearingDao frtPreClearingDao;
    @Autowired
    private BasPartsUnitDAO basPartsUnitDAO;
    @Autowired
    private BasClaimsTypeDao basClaimsTypeDao;
    @Autowired
    private FrtService frtService;
    @Autowired
    private BasCompanyInformationSetDAO  basCompanyInformationSetDAO;
    
    /**
     * 车间管理datagrid
     */
    
    public Datagrid datagridFrtWorkshop(FrtReceptionVo freVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<FrtReceptionVo> rows = new ArrayList<FrtReceptionVo>();
        int total=0;
        if(freVo.getParam()==null || "".equals(freVo.getParam())|| freVo.getParam()==true){
             StringBuffer sb = new StringBuffer("SELECT c.*,d.receptionMaintFlgName FROM(");
             sb.append(" SELECT a.*,b.finStatusName FROM(");
             sb.append(" SELECT v.*,f.VALUABLES,bc.dataValue AS receptionStatusName,s.tempdata,t.fuelName,u.oldPartsName");
             sb.append(" FROM frt_reception_view v,frt_reception f,bas_childdictionary bc,bas_parentdictionary bp,");
             sb.append(" (SELECT bc.dataKey,bc.dataValue AS tempdata FROM bas_parentdictionary bp,bas_childdictionary bc");
             sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                     + Contstants.VALUABLESRES_TAG.VALUABLESRESKEY + "') s,");
             sb.append(" (SELECT bc.dataKey,bc.dataValue AS fuelName FROM bas_parentdictionary bp,bas_childdictionary bc");
             sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                     + Contstants.FUELTHING_TAG.FUELTHINGKEY + "') t,");
             sb.append(" (SELECT bc.dataKey,bc.dataValue AS oldPartsName FROM bas_parentdictionary bp,bas_childdictionary bc");
             sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                     + Contstants.OLDPARTS_TAG.OLDPARTSKEY + "') u");
             sb.append(" WHERE v.receptionId=f.RECEPTION_ID AND bp.parent_id=bc.parent_id AND f.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
             sb.append(" AND s.dataKey=f.VALUABLES AND t.dataKey=v.fuelSituation AND u.dataKey=v.oldPieces");
             sb.append(" and v.receptionStatus ");
             sb.append(" IN('" + Contstants.DOCUMENT_TAG.DOCUMENTState1 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState2 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState3 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState4 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState5 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState6 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState14 + "','"
                     + Contstants.DOCUMENT_TAG.DOCUMENTState7 + "')");
             sb.append(" and f.RCPT_FLG=" + Contstants.DELETE_TAG.DELETENO + "");
             sb.append(" AND v.receptionStatus=bc.datakey AND bp.dataKey='"
                     + Contstants.DOCUMENT_TAG.DOCUMENTKEY + "')a,");
             sb.append(" (SELECT bcs.dataKey,bcs.dataValue AS finStatusName ");
             sb.append(" FROM bas_parentdictionary bps,bas_childdictionary bcs");
             sb.append(" WHERE bcs.parent_id=bps.parent_id AND bps.dataKey='"
                     + Contstants.TOWORKSHOP_TAG.TOWORKSHOPKEY + "'");
             sb.append(" )b WHERE a.finStatus=b.datakey ");
             sb.append(" ) c,(SELECT bc.dataKey,bc.dataValue AS receptionMaintFlgName");
             sb.append(" FROM bas_parentdictionary bp,bas_childdictionary bc");
             sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                     + Contstants.MAINTAIN_TAG.MAINTAINKEY + "'");
             sb.append(" )d WHERE c.receptionMaintFlg=d.datakey");
             if (freVo.getReceptionId() != null&& freVo.getReceptionId().length() > 0)
                 sb.append(" and c.receptionId like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptionId().trim()) + "%'");
             if (freVo.getResvId() != null && freVo.getResvId().length() > 0)
                 sb.append(" and c.resvId like '%"+ StringEscapeUtils.escapeSql(freVo.getResvId().trim())+ "%'");
             if (freVo.getCarId() != null && freVo.getCarId().length() > 0)
                 sb.append(" and c.carLicense like '%"+ StringEscapeUtils.escapeSql(freVo.getCarId().trim())+ "%'");
             if (freVo.getCustomName() != null && freVo.getCustomName().length() > 0)
                 sb.append(" and c.customName like '%"+ StringEscapeUtils.escapeSql(freVo.getCustomName().trim())+ "%'");
             if (freVo.getReptId() != null&& freVo.getReptId().toString().length() > 0)
                 sb.append(" and c.reptId=" + freVo.getReptId());
             if (freVo.getReceptionStatus() != null&& freVo.getReceptionStatus().toString().length() > 0)
                 sb.append(" and c.receptionStatus='" + freVo.getReceptionStatus()+ "'");
             if (freVo.getInterDateBegin() != null&& freVo.getInterDateBegin().toString().length() > 0)
                 if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
                     sb.append(" and c.interDate between '"+ freVo.getInterDateBegin() + "' and '"+ freVo.getInterDateEnd() + "'");
                 else
                     sb.append(" and c.interDate >= '"+ freVo.getInterDateBegin() + "'");
             else if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
                 sb.append(" and c.interDate <= '"+ freVo.getInterDateEnd() + "'");
             if(freVo.getInterDateBegin()== null && freVo.getInterDateEnd() == null ){
            	 if(freVo.getFlage()!=null && freVo.getFlage()!=true){
            			sb.append(" and DATE_FORMAT(c.interDate,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,freVo.getEnterpriseId()).getCiValue()))+ "'");
                     	sb.append(" and DATE_FORMAT(c.interDate,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
            	 }
             }	
             if (freVo.getReceptionEndTimeBegin() != null&& freVo.getReceptionEndTimeBegin().toString().length() > 0)
                 if (freVo.getReceptionEndTimeEnd() != null&& freVo.getReceptionEndTimeEnd().toString().length() > 0)
                     sb.append(" and c.receptionEndTime between '"+ freVo.getReceptionEndTimeBegin() + "' and '"+ freVo.getReceptionEndTimeEnd() + "'");
                 else
                     sb.append(" and c.receptionEndTime >= '"+ freVo.getReceptionEndTimeBegin() + "'");
             else if (freVo.getReceptionEndTimeEnd() != null && freVo.getReceptionEndTimeEnd().toString().length() > 0)
                 sb.append(" and c.receptionEndTime <= '" + freVo.getReceptionEndTimeEnd() + "'");
             if (freVo.getExpDelCarTimeBegin() != null&& freVo.getExpDelCarTimeBegin().toString().length() > 0)
                 if (freVo.getExpDelCarTimeEnd() != null&& freVo.getExpDelCarTimeEnd().toString().length() > 0)
                     sb.append(" and c.expDelCarTime between '"+ freVo.getExpDelCarTimeBegin() + "' and '"+ freVo.getExpDelCarTimeEnd() + "'");
                 else
                     sb.append(" and c.expDelCarTime >= '" + freVo.getExpDelCarTimeBegin()+ "'");
             else if (freVo.getExpDelCarTimeEnd() != null && freVo.getExpDelCarTimeEnd().toString().length() > 0)
                 sb.append(" and c.expDelCarTime <= '"+ freVo.getExpDelCarTimeEnd() + "'");
             if (freVo.getSort() != null && !"".equals(freVo.getSort().trim())&& freVo.getOrder() != null&& !"".equals(freVo.getOrder().trim()))
                 sb.append(" order by c." + freVo.getSort().trim() + " "+ freVo.getOrder().trim());
             else
             	sb.append(" order by c.receptionId");
             List<Object[]> rowsList = new ArrayList<Object[]>();
             if(freVo.getPage()!=0&&freVo.getRows()!=0){
            	 rowsList = frtReceptionDao.createSQLQuery(sb.toString(), freVo.getPage(), freVo.getRows());
             }else{
            	 rowsList = frtReceptionDao.createSQLQuery(sb.toString());
             }
             total = frtReceptionDao.getSQLCount(sb.toString(), null);
             if (rowsList != null && rowsList.size() > 0)
             {
                 for (Object[] obj : rowsList)
                 {
                     FrtReceptionVo fVo = new FrtReceptionVo();
                     if (obj[0] != null && obj[0].toString().length() > 0)
                         fVo.setReceptionId(obj[0].toString());
                     if (obj[1] != null && obj[1].toString().length() > 0)
                         fVo.setResvId(obj[1].toString());
                     if (obj[2] != null && obj[2].toString().length() > 0)
                         fVo.setCarId(obj[2].toString());
                     if (obj[3] != null && obj[3].toString().length() > 0)
                         fVo.setCarLicense(obj[3].toString());
                     if (obj[4] != null && obj[4].toString().length() > 0)
                         fVo.setCarVin(obj[4].toString());
                     if (obj[5] != null && obj[5].toString().length() > 0)
                         fVo.setCarMotorId(obj[5].toString());
                     if (obj[6] != null && obj[6].toString().length() > 0)
                         fVo.setCustomId(obj[6].toString());
                     if (obj[7] != null && obj[7].toString().length() > 0)
                         fVo.setCustomName(obj[7].toString());
                     if (obj[8] != null && obj[8].toString().length() > 0)
                         fVo.setReptId(new Short(obj[8].toString()));
                     if (obj[9] != null && obj[9].toString().length() > 0)
                         fVo.setReptName(obj[9].toString());
                     if (obj[10] != null && obj[10].toString().length() > 0)
                         fVo.setReceptionDistance(new Integer(obj[10].toString()));
                     else
                         fVo.setReceptionDistance(0);
                     if (obj[11] != null && obj[11].toString().length() > 0)
                         fVo.setReceptionMaintFlg(obj[11].toString());
                     if (obj[12] != null && obj[12].toString().length() > 0)
                         fVo.setReceptionStatus(obj[12].toString());
                     if (obj[13] != null && obj[13].toString().length() > 0)
                         fVo.setRepgrpId(new Short(obj[13].toString()));
                     if (obj[14] != null && obj[14].toString().length() > 0)
                         fVo.setRepgrpName(obj[14].toString());
                     if (obj[15] != null && obj[15].toString().length() > 0)
                         fVo.setRepwkId(new Short(obj[15].toString()));
                     if (obj[16] != null && obj[16].toString().length() > 0)
                         fVo.setRepwkName(obj[16].toString());
                     if (obj[17] != null && obj[17].toString().length() > 0)
                         fVo.setInterDate(MyBeanUtils.getInstance().formatString(
                                 obj[17].toString()));
                     if (obj[18] != null && obj[18].toString().length() > 0)
                         fVo.setReceptionEndTime(MyBeanUtils.getInstance()
                                 .formatString(obj[18].toString()));
                     if (obj[19] != null && obj[19].toString().length() > 0)
                         fVo.setExpDelCarTime(MyBeanUtils.getInstance()
                                 .formatString(obj[19].toString()));
                     if (obj[20] != null && obj[20].toString().length() > 0)
                         fVo.setReceptionTechnician(new Short(obj[20].toString()));
                     if (obj[21] != null && obj[21].toString().length() > 0)
                         fVo.setReceptionTechnicianName(obj[21].toString());
                     if (obj[22] != null && obj[22].toString().length() > 0)
                         fVo.setReceptionInsurPer(new Short(obj[22].toString()));
                     if (obj[23] != null && obj[23].toString().length() > 0)
                         fVo.setReceptionInsurPerName(obj[23].toString());
                     if (obj[24] != null && obj[24].toString().length() > 0)
                         fVo.setReceptionRepPer(obj[24].toString());
                     if (obj[25] != null && obj[25].toString().length() > 0)
                         fVo.setPropRepPer(obj[25].toString());
                     if (obj[26] != null && obj[26].toString().length() > 0)
                         fVo.setReceptor(new Short(obj[26].toString()));
                     if (obj[27] != null && obj[27].toString().length() > 0)
                         fVo.setReceptorName(obj[27].toString());
                     if (obj[28] != null && obj[28].toString().length() > 0)
                         fVo.setPropPhone(obj[28].toString());
                     if (obj[29] != null && obj[29].toString().length() > 0)
                         fVo.setPropTel(obj[29].toString());
                     if (obj[30] != null && obj[30].toString().length() > 0)
                         fVo.setManagementFee(new Double(obj[30].toString()));
                     else
                         fVo.setManagementFee(0.00d);
                     if (obj[31] != null && obj[31].toString().length() > 0)
                         fVo.setFuelSituation(obj[31].toString());
                     if (obj[32] != null && obj[32].toString().length() > 0)
                         fVo.setOldPieces(obj[32].toString());
                     if (obj[33] != null && obj[33].toString().length() > 0)
                         fVo.setFinComId(new Short(obj[33].toString()));
                     if (obj[34] != null && obj[34].toString().length() > 0)
                         fVo.setRelcampName(obj[34].toString());
                     if (obj[35] != null && obj[35].toString().length() > 0)
                         fVo.setFinStatus(obj[35].toString());
                     if (obj[36] != null && obj[36].toString().length() > 0)
                         fVo.setProblemDesc(obj[36].toString());
                     if (obj[37] != null && obj[37].toString().length() > 0)
                         fVo.setReceptionRemark(obj[37].toString());
                     if (obj[38] != null && obj[38].toString().length() > 0)
                         fVo.setValuables(obj[38].toString());
                     if (obj[39] != null && obj[39].toString().length() > 0)
                         fVo.setReceptionStatusName(obj[39].toString());

                     if (obj[40] != null && obj[40].toString().length() > 0)
                         fVo.setValuablesName(obj[40].toString());
                     if (obj[41] != null && obj[41].toString().length() > 0)
                         fVo.setFuelSituationName(obj[41].toString());
                     if (obj[42] != null && obj[42].toString().length() > 0)
                         fVo.setOldPiecesName(obj[42].toString());
                     if (obj[43] != null && obj[43].toString().length() > 0)
                         fVo.setFinStatusName(obj[43].toString());
                     if (obj[44] != null && obj[44].toString().length() > 0)
                         fVo.setReceptionMaintFlgName(obj[44].toString());
                     rows.add(fVo);
                 }
             }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 删除接车单
     */
    
    public Msg remove(String receptionId) throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FrtReception fr = frtReceptionDao.get(FrtReception.class,
                    receptionId);
            fr.setRcptFlg(Contstants.DELETE_TAG.DELETEYES);
            msg.setMsg("删除接车单成功！");
            msg.setSuccess(true);
        }
        catch(Exception es)
        {
            msg.setMsg("删除接车单失败！");
        }
        return msg;
    }

    // 从数据库中查询维修配件
    
    public Datagrid findPartsByRcptId(String rcptId) throws Exception
    {
        List<PartsVo> partsList = new ArrayList();
        String hql = "from FrtRcptParts frp where 1=1 ";
        if (rcptId != null && !"".equals(rcptId.trim()))
        {
            hql += " and frp.frtReception.receptionId ='" + rcptId.trim() + "'";
        }
        List<FrtRcptParts> rcptPartsList = frtRcptPartsDao.find(hql);
        if (rcptPartsList != null && rcptPartsList.size() > 0)
        {
            BasPartsUnit bpu = null;
            for (FrtRcptParts frp : rcptPartsList)
            {
                bpu = basPartsUnitDAO.findById(frp.getPunitId());
                PartsVo pVo = new PartsVo();
                // BeanUtils.copyProperties(frp, pVo);
                MyBeanUtils.getInstance().copyBeans(frp, pVo);
                pVo.setPartsIndex(frp.getRcptpartsIndex());
                if (frp.getBasClaimsType() != null)
                {
                    pVo.setClaimsId(frp.getBasClaimsType().getClaimsId());
                    pVo.setClaimsName(frp.getBasClaimsType().getClaimsName());
                }
                if (frp.getBasRepairType() != null)
                {
                    pVo.setReptypId(frp.getBasRepairType().getReptypId());
                    pVo.setReptypName(frp.getBasRepairType().getReptypName());
                }
                pVo.setPunitName(bpu.getPunitName());
                partsList.add(pVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(partsList);
        dg.setTotal(partsList.size());
        return dg;
    }

    // 从数据库中查询维修项目
    
    public Datagrid findItemByRcptId(String rcptId) throws Exception
    {
        List<FrtItemVo> itemList = new ArrayList();
        StringBuffer sb = new StringBuffer(
                "SELECT fri.*,bs.STF_NAME,brt.REPTYP_NAME,bct.CLAIMS_NAME");
        sb.append(" FROM frt_rcpt_item fri,bas_stuff bs,");
        sb.append(" bas_repair_type brt,bas_claims_type bct");
        sb.append(" WHERE bs.STF_ID=fri.STF_ID");
        sb.append(" AND brt.REPTYP_ID=fri.REPTYP_ID");
        sb.append(" AND bct.CLAIMS_ID=fri.CLAIMS_ID");
        if (rcptId != null && !"".equals(rcptId.trim()))
        {
            sb.append(" and fri.RECEPTION_ID ='" + rcptId.trim() + "'");
        }
        List<Object[]> friList = frtRcptItemDao.createSQLQuery(sb.toString(),
                null);
        if (friList != null && friList.size() > 0)
        {
            FrtItemVo iVo = null;
            for (Object[] objects : friList)
            {
                iVo = new FrtItemVo();
                if (objects[2] != null && objects[2].toString().length() > 0)
                    iVo.setRepitemId(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    iVo.setRepitemName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    iVo.setRepitemTime(Double
                            .parseDouble(objects[4].toString()));
                else
                    iVo.setRepitemTime(0.00d);
                if (objects[5] != null && objects[5].toString().length() > 0)
                    iVo.setInternalTime(Double.parseDouble(objects[5]
                            .toString()));
                else
                    iVo.setInternalTime(0.00d);
                if (objects[6] != null && objects[6].toString().length() > 0)
                    iVo.setRepitemAmount(Double.parseDouble(objects[6]
                            .toString()));
                else
                    iVo.setRepitemAmount(0.00d);
                if (objects[7] != null && objects[7].toString().length() > 0)
                    iVo.setStfId(Short.parseShort(objects[7].toString()));
                if (objects[8] != null && objects[8].toString().length() > 0)
                    iVo.setPlanStartTime(MyBeanUtils.getInstance()
                            .formatString(objects[8].toString()));
                if (objects[9] != null && objects[9].toString().length() > 0)
                    iVo.setPlanComplTime(MyBeanUtils.getInstance()
                            .formatString(objects[9].toString()));
                if (objects[10] != null && objects[10].toString().length() > 0)
                    iVo.setReptypId(Short.parseShort(objects[10].toString()));
                if (objects[11] != null && objects[11].toString().length() > 0)
                    iVo.setClaimsId(Short.parseShort(objects[11].toString()));
                if (objects[12] != null && objects[12].toString().length() > 0)
                    iVo.setRepitemRemark(objects[12].toString());
                if (objects[15] != null && objects[15].toString().length() > 0)
                    iVo.setStfName(objects[15].toString());
                if (objects[16] != null && objects[16].toString().length() > 0)
                    iVo.setReptypName(objects[16].toString());
                if (objects[17] != null && objects[17].toString().length() > 0)
                    iVo.setClaimsName(objects[17].toString());
                itemList.add(iVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(itemList);
        dg.setTotal(itemList.size());
        return dg;
    }

    private boolean exists(FrtReceptionVo freVo) throws Exception
    {
        boolean flag = false;
        if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState1.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState2.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals( Contstants.DOCUMENT_TAG.DOCUMENTState3.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState4.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals( Contstants.DOCUMENT_TAG.DOCUMENTState5.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState6.toString()))
        {
        }
        else if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState7.toString())){
        	
        }
        else if (freVo.getReceptionStatus().equals(Contstants.DOCUMENT_TAG.DOCUMENTState8.toString()))
            flag = true;
        else
            flag = true;
        return flag;
    }
    
    private Boolean workShopVal(List<PartsVo> partsList, List<FrtItemVo> itemList,
            List<FrtReceptionVehicleStructure> frceList, FrtReceptionVo freVo) throws Exception{
    	String workShopVal=frtService.findDefaultWorkShopVal();
    	if(workShopVal!=null&&workShopVal.length()>0)
    	if (freVo.getReceptionStatus().equals(
                Contstants.DOCUMENT_TAG.DOCUMENTState8.toString()))
        {
    		FrtReception fre=new FrtReception();
    		copyData(partsList, itemList, frceList, freVo, fre);
    		FrtPreClearing fpc=new FrtPreClearing();
    		frtPrePartsByReceptionId(fpc,freVo.getReceptionId());
    		Set<FrtRcptParts> setFre=fre.getFrtRcptPartses();
    		List<FrtRcptParts> listFre=new ArrayList();
    		for (FrtRcptParts frtRcptParts : setFre) {
    			listFre.add(frtRcptParts);
    		}
    		for (int i = 1; i < listFre.size(); i++) {
				for (int j = 0; j < listFre.size()-i; j++) {
					if(listFre.get(j).getPartsId()==listFre.get(j+1).getPartsId()){
						listFre.get(j).setPartsNum(listFre.get(j).getPartsNum()+listFre.get(j+1).getPartsNum());
						listFre.remove(j+1);
					}
				}
			}
    		List<FrtPreParts> listFpc=new ArrayList();
    		Set<FrtPreParts> setFpc=fpc.getFrtPrePartses();
    		for (FrtPreParts frtPreParts : setFpc) {
				listFpc.add(frtPreParts);
			}
    		for (int i = 1; i < listFpc.size(); i++) {
				for (int j = 0; j < listFpc.size()-i; j++) {
					if(listFpc.get(j).getPartsId()==listFpc.get(j+1).getPartsId()){
						listFpc.get(j).setPartsCount(listFpc.get(j).getPartsCount()+listFpc.get(j+1).getPartsCount());
						listFpc.remove(j+1);
					}
				}
			}
    		if(listFpc.size()!=listFre.size()){
    			return true;
    		}
    		for (int i = 0; i < listFre.size(); i++) {
				if(listFre.get(i).getPartsId()==listFpc.get(i).getPartsId()){
					if(listFre.get(i).getPartsNum()!=listFpc.get(i).getPartsCount()){
						return true;
					}
				}
			}
        }
    	return false;
    }
    /**
     * 更新车间管理单
     * */
    @Log(moduleName = "前台管理", content = "更新车间管理单", opertype = "更新")
    
    public Msg edit(FrtReceptionVo freVo) throws Exception
    {
        List<PartsVo> partsList = null;
        List<FrtItemVo> itemList = null;
        List<FrtReceptionVehicleStructure> frceList = null;
        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0){
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            frceList = JSON.parseArray(jsVehicle.get("rows").toString(),FrtReceptionVehicleStructure.class);
        }
        String items = freVo.getItems();
        if (items != null && items.length() > 0){
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(), FrtItemVo.class);
        }
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0){
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),PartsVo.class);
        }
        FrtReception temp = new FrtReception();
        FrtReception fre = new FrtReception();
        Msg msg = new Msg();
        try{
        	if(workShopVal(partsList, itemList, frceList, freVo)){
        		msg.setMsg("计划用料与仓库出料不一致，请比对后再完工！");
        		msg.setSuccess(false);
        		return msg;
        	}
            temp = frtReceptionDao.find(" from FrtReception fr where fr.receptionId='"+freVo.getReceptionId()+"'").get(0);
            MyBeanUtils.getInstance().copyBeans(temp, fre);
            MyBeanUtils.getInstance().copyBeans(freVo, fre);
            FrtCustom fc = new FrtCustom();
            MyBeanUtils.getInstance().copyBeans(freVo, fc);
            fre.setFrtCustom(fc);
            Reptype reptype = new Reptype();
            MyBeanUtils.getInstance().copyBeans(freVo, reptype);
            fre.setReptype(reptype);
            reptype.getFrtReceptions().add(fre);
            FrtCar fcr = new FrtCar();
            MyBeanUtils.getInstance().copyBeans(freVo, fcr);
            fre.setFrtCar(fcr);
            copyData(partsList, itemList, frceList, freVo, fre);
            if (fre.getFinAllTag() == null)
                fre.setFinAllTag(Contstants.CLAIM_TAG.NOCLAIM);
            if (fre.getFinTag() == null)
                fre.setFinTag(Contstants.CLAIM_TAG.NOCLAIM);
            fre.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPYES);
            //fre.setPrestatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPYES);
            fre.setRcptBranch(frtService.getDefaultRcptBranch());
            fre.setEnterpriseId(freVo.getEnterpriseId());
            FrtPreClearing fpc = new FrtPreClearing();
            fpc.setEnterpriseId(freVo.getEnterpriseId());
            if (exists(freVo)){
            	msg=toBalance(fre,fpc,msg);
            	if(!(msg.getSuccess()))
            		return msg;
            }else{
            	frtReceptionDao.merge(fre);
                msg.setMsg("更新工单成功!");
            }
            msg.setSuccess(true);
        }catch(Exception e){
            msg.setMsg("更新 工单失败!");
            msg.setSuccess(false);
            e.printStackTrace();
        }
        return msg;
    }
    private Msg toBalance(FrtReception fre,FrtPreClearing fpc,Msg msg) throws Exception{
    	fre.setReceptionFactTime(new Date());
    	frtReceptionDao.merge(fre);
        FrtPreClearing fpcg = null;
        fpcg = frtPreClearingDao
                .get("from FrtPreClearing fpc where fpc.receptionId='"
                        + fre.getReceptionId()
                        + "' and fpc.preFlg="
                        + Contstants.DELETE_TAG.DELETENO);
        
        if (fpcg == null)
        {
            fpc.setPreclrId(CreateID.createId("FrtPreClearing",
                    Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID));
            fre.setCorrection(Contstants.WORK_TAG.WORKNO);
            fpc.setReceptionId(fre.getReceptionId());
            Boolean flag=copyData(fpc, fre);
            if(flag!=null&&flag==true){
            	msg.setSuccess(false);
            	msg.setMsg("数据操作出现错误！");
            	return msg;
            }
            fre.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState11);
            frtReceptionDao.merge(fre);
            fpc.setPreclrInoiceType(Contstants.RECEIPT_TAG.RECEIPTINIT);
            fpc.setPreFlg(Contstants.DELETE_TAG.DELETENO);
            frtPreClearingtotemoney(fpc);
            fpc.setStfId(null);
            fpc.setPreclrToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
            fpc.setEnterpriseId(fre.getEnterpriseId());
            frtPreClearingDao.save(fpc);
            msg.setMsg("转结算成功！");
        }
        else
        {
            fpcg.setReceptionId(fre.getReceptionId());
            Boolean flag=copyData(fpcg, fre);
            if(flag!=null&&flag==true){
            	msg.setSuccess(false);
            	msg.setMsg("数据操作出现错误！");
            	return msg;
            }
            fre.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState11);
            frtReceptionDao.merge(fre);
            frtPreClearingtotemoney(fpcg);
            fpcg.setPreFlg(Contstants.DELETE_TAG.DELETENO);
            if (fpc.getPreclrInoiceType() == null)
                fpc.setPreclrInoiceType(Contstants.RECEIPT_TAG.RECEIPTINIT);
            fpcg.setPreclrToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
            fpc.setEnterpriseId(fre.getEnterpriseId());
            fpcg.setPreclrTime(new Date());
            frtPreClearingDao.merge(fpcg);
            msg.setMsg("更新结算单成功！");
        }
        msg.setSuccess(true);
        return msg;
    }
    private Boolean copyData(FrtPreClearing fpc, FrtReception fre)
            throws NumberFormatException, Exception
    {
        Set<FrtReceptionCost> set2 = fre.getFrtReceptionCosts();
        if (fpc.getPreclrHasUnrealData() == null)
        {
            fpc.setPreclrHasUnrealData(Contstants.FAKEDATA_TAG.FAKEDATANO);
        }
        FrtPreClearingCost fpcc = null;
        // fpc.getFrtPreClearingCosts().removeAll(fpc.getFrtPreClearingCosts());
        fpc.getFrtPreClearingCosts().clear();
        for (FrtReceptionCost frtReceptionCost : set2)
        {
            fpcc = new FrtPreClearingCost();
            fpcc.setFrtPreClearing(fpc);
            // fpcc.setCostId(costId);
            fpcc.setCostAmount(frtReceptionCost.getCostAmount());
            fpcc.setCostExplain(frtReceptionCost.getCostExplain());
            fpcc.setCostItem(frtReceptionCost.getCostItem());
            fpc.getFrtPreClearingCosts().add(fpcc);
        }
        Set<FrtRcptItem> set1 = fre.getFrtRcptItems();
        FrtPreWktime fpw = null;
        fpc.getFrtPreWktimes().clear();
        // fpc.getFrtPreWktimes().removeAll(fpc.getFrtPreWktimes());
        for (FrtRcptItem frtRcptItem : set1)
        {
            fpw = new FrtPreWktime();
            fpw.setFrtPreClearing(fpc);
            // fpw.setRelcampId(relcampId);
            fpw.setRepitemId(frtRcptItem.getRepitemId());
            fpw.setRepitemName(frtRcptItem.getRepitemName());
            fpw.setClaimsType(frtRcptItem.getBasClaimsType().getClaimsId());
            fpw.setWktimeNum(frtRcptItem.getRepitemNum());
            fpw.setInnerWktime(frtRcptItem.getRcptitemInnerTime());
            fpw.setWktimeAmount(frtRcptItem.getRepitemAmount());
            fpw.setReptypId(frtRcptItem.getBasRepairType().getReptypId());
            fpw.setStfId(frtRcptItem.getStfId());
            // fpw.setSettlementDiscount(settlementDiscount);
            // fpw.setWktimeIndex(Integer.parseInt(CreateID.createId("FrtPreWktime",
            // "")));
            fpc.getFrtPreWktimes().add(fpw);
        }
        return frtPrePartsByReceptionId(fpc, fre.getReceptionId());
    }
    /** 查找出仓单材料信息 */
    private Boolean frtPrePartsByReceptionId(FrtPreClearing fpc, String receptionId)
            throws Exception
    {
        //查询出库单
        StringBuffer sb = new StringBuffer("SELECT soi.PARTS_ID,soi.ITEM_COUNT,soi.ITEM_PRICE,soi.AMOUNT,");
        sb.append(" soi.ITEM_CHARGE,soi.CLAIMS_TYPE,fp.PARTS_NAME,soi.INDEX_ID,soi.STORE_ID,soi.TAX_CAST,soi.NOTAX_CAST");
        sb.append(" FROM st_out_main som,st_out_item soi,frt_parts fp");
        sb.append(" WHERE soi.STOM_ID=som.STOM_ID AND fp.PARTS_ID=soi.PARTS_ID");
        sb.append(" AND som.CER_NO='" + receptionId + "'");
        List<Object[]> partsList = frtReceptionDao.createSQLQuery(sb.toString(), null);
        List<FrtPrePartsVo> fppList=new ArrayList();
        FrtPrePartsVo fppVo = null;
        if (partsList != null && partsList.size() > 0)
            for (Object[] objects : partsList)
            {
            	fppVo = new FrtPrePartsVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                	fppVo.setPartsId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                	fppVo.setPartsCount(Double.parseDouble(objects[1].toString()));
                else
                	fppVo.setPartsCount(0.00d);
                if (objects[2] != null && objects[2].toString().length() > 0)
                	fppVo.setPartsPrice(Double.parseDouble(objects[2].toString()));
                else
                	fppVo.setPartsPrice(0.00d);
                if (objects[3] != null && objects[3].toString().length() > 0)
                	fppVo.setPartsAmount(Double.parseDouble(objects[3].toString()));
                else
                	fppVo.setPartsAmount(0.00d);
                if (objects[4] != null && objects[4].toString().length() > 0)
                	fppVo.setReptypId(Short.parseShort(objects[4].toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                	fppVo.setClaimsType(Short.parseShort(objects[5].toString()));
                if (objects[6] != null && objects[6].toString().length() > 0)
                	fppVo.setPartsName(objects[6].toString());
                if(objects[7]!=null&&objects[7].toString().length()>0)
                	fppVo.setIndexId(Integer.parseInt(objects[7].toString()));
                if(objects[8]!=null&&objects[8].toString().length()>0)
                	fppVo.setStoreId(Short.parseShort(objects[8].toString()));
                
                if (objects[9] != null && objects[9].toString().length() > 0)
                	fppVo.setPartsTaxCost(Double.parseDouble(objects[9].toString()));
                else
                	fppVo.setPartsTaxCost(0.00d);
                if (objects[10] != null && objects[10].toString().length() > 0)
                	fppVo.setPartsNoTaxCost(Double.parseDouble(objects[10].toString()));
                else
                	fppVo.setPartsNoTaxCost(0.00d);
                fppVo.setRelcampId(null);
                fppVo.setSettlementDiscount(null);
                fppVo.setPartsFlg(Contstants.DELETE_TAG.DELETENO);
                fppVo.setPartsRate(Contstants.DISCOUNTRATE / 100);
                fppVo.setPartsRateAmount(fppVo.getPartsAmount());
                fppVo.setFrtPreClearing(fpc);
                fppList.add(fppVo);
            }
        //查询退料单
        StringBuffer sbs = new StringBuffer("SELECT srpd.PARTS_ID,srpd.STRTPD_CNT,srpd.STORE_ID,srpd.INDEX_ID");
        sbs.append(" FROM st_rt_parts_main srpm,st_rt_parts_detail srpd");
        sbs.append(" WHERE srpm.STRTPM_ID=srpd.STRTPM_ID AND srpm.RECEPTION_ID='"+ receptionId + "'");
        List<Object[]> strpdList = frtReceptionDao.createSQLQuery(sbs.toString(), null);
        List<TempEntity> tempList = new ArrayList();
        TempEntity te = null;
        if (strpdList != null && strpdList.size() > 0)
            for (Object[] objects : strpdList)
            {
                te = new TempEntity();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    te.setPartsId(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    te.setPartsCount(Double.parseDouble(objects[1].toString()));
                else
                    te.setPartsCount(0.00d);
                if (objects[2] != null && objects[2].toString().length() > 0)
                    te.setStoreId(Short.parseShort(objects[2].toString()));
                if(objects[3]!=null&&objects[3].toString().length()>0)
                	te.setIndexId(Integer.parseInt(objects[3].toString()));
                tempList.add(te);
            }
        unitStRtParts(fppList,tempList);//合并退料配件
        
        unitFrtParts(fppList);//合并出库配件
        Boolean flag=unitInAndOutParts(fppList,tempList); /**合并出库，退料配件*/
        if(flag!=null&&flag==true){
        	return true;
        }
        fpc.getFrtPrePartses().clear();
        //迁移数据到结算配件
        FrtPreParts fpp=null;
        Double noTaxCost=0d;
        Double taxCost=0d;
        if(fppList.size()>0)
        for (FrtPrePartsVo frtPrePartsVo : fppList) {
        	if(frtPrePartsVo.getFlag().equals(false)){
        		fpp=new FrtPreParts();
        		MyBeanUtils.getInstance().copyBeans(frtPrePartsVo, fpp);
        		fpc.getFrtPrePartses().add(fpp); 
        		noTaxCost+=(fpp.getPartsNoTaxCost()*fpp.getPartsCount());
        		taxCost+=(fpp.getPartsTaxCost()*fpp.getPartsCount());
        	}
		}
        fpc.setPreclrNoTaxCost(noTaxCost);
        fpc.setPreclrTaxCost(taxCost);
        return false;
    }
    /**合并退料配件*/
    private void unitStRtParts(List<FrtPrePartsVo> fppList,List<TempEntity> tempList){
    	if(!(fppList.size()>0&&tempList.size()>0))
    		return ;
    	HashMap<Integer,Short> hashMap=new HashMap();
    	for (FrtPrePartsVo fppVo : fppList) {
    		hashMap.put(fppVo.getIndexId(), fppVo.getClaimsType());
    	}
    	if(tempList.size()==1){
    		tempList.get(0).setClaimsType(hashMap.get(tempList.get(0).getIndexId()));
    	}
    	for (TempEntity tempEntity : tempList) {
    		tempEntity.setClaimsType(hashMap.get(tempEntity.getIndexId()));
		}
    	for (int i = 0; i < tempList.size(); i++) {
			for (int j = i+1; j < tempList.size(); j++) {
				if(tempList.get(i).getFlag().equals(false)&&tempList.get(j).getFlag().equals(false)&&
						tempList.get(i).getPartsId().equals(tempList.get(j).getPartsId())&&
							tempList.get(i).getStoreId().equals(tempList.get(j).getStoreId())&&
							tempList.get(i).getClaimsType().equals(tempList.get(j).getClaimsType())){
					tempList.get(i).setPartsCount(tempList.get(i).getPartsCount()+tempList.get(j).getPartsCount());
					tempList.get(j).setFlag(true);
				}
			}
		}
    }
    /**合并出库配件*/
    private void unitFrtParts(List<FrtPrePartsVo> fppList){
    	if (!(fppList!= null && fppList.size() > 0)){
    			return;
    	}
    	for (int i = 0; i < fppList.size(); i++) {
			for (int j = i+1; j < fppList.size(); j++) {
				if(fppList.get(i).getFlag().equals(false)&&fppList.get(j).getFlag().equals(false)&&
						fppList.get(i).getPartsId().equals(fppList.get(j).getPartsId())&&
							fppList.get(i).getStoreId().equals(fppList.get(j).getStoreId())&&
								fppList.get(i).getClaimsType().equals(fppList.get(j).getClaimsType())){
					fppList.get(i).setPartsCount(fppList.get(i).getPartsCount()+fppList.get(j).getPartsCount());
					fppList.get(i).setPartsAmount(fppList.get(i).getPartsCount()*fppList.get(i).getPartsPrice());
					fppList.get(i).setPartsRateAmount(fppList.get(i).getPartsAmount());
					fppList.get(j).setFlag(true);
				}
			}
		}
    }
    /**合并出库，退料配件*/
    private Boolean unitInAndOutParts(List<FrtPrePartsVo> fppList,List<TempEntity> tempList){
    	if(!(fppList.size()>0&&tempList.size()>0))
    		return false;
    	for (int i = 0; i < fppList.size(); i++) {
			for (int j = 0; j < tempList.size(); j++) {
				if(fppList.get(i).getFlag().equals(false)&&tempList.get(j).getFlag().equals(false)&&
						fppList.get(i).getPartsId().equals(tempList.get(j).getPartsId())&&
							fppList.get(i).getStoreId().equals(tempList.get(j).getStoreId())&&
								fppList.get(i).getClaimsType().equals(tempList.get(j).getClaimsType())){
    				if(fppList.get(i).getPartsCount()<tempList.get(j).getPartsCount()){
    					return true;
    				}
    				if(fppList.get(i).getPartsCount().equals(tempList.get(j).getPartsCount())){
    					fppList.remove(i);
    				}else{
    					fppList.get(i).setPartsCount(fppList.get(i).getPartsCount()-tempList.get(j).getPartsCount());
    					fppList.get(i).setPartsAmount(fppList.get(i).getPartsCount()*fppList.get(i).getPartsPrice());
    					fppList.get(i).setPartsRateAmount(fppList.get(i).getPartsAmount());
    				}
    				tempList.get(j).setFlag(true);
    			}
			}
		}
    	for (TempEntity tempEntity : tempList) {
			if(tempEntity.getFlag().equals(false)){
				return true;
			}
		}
    	return false;
    }
    private void frtPreClearingtotemoney(FrtPreClearing fpc) throws Exception
    {
        double sumMoney = 0d;
        double preclrWktimeAmount = 0d;
        double preMprMatAmount = 0d;
        double otherAmount = 0d;
        FrtReception fr=frtReceptionDao.get(FrtReception.class,fpc.getReceptionId());
        if(!(fr.getReptype().getReptId().toString().equals(frtService.getDefaultFirstmaintain()))){
        	Set<FrtPreParts> list1 = fpc.getFrtPrePartses();
            Set<FrtPreWktime> list2 = fpc.getFrtPreWktimes();
            Set<FrtPreClearingCost> list3 = fpc.getFrtPreClearingCosts();
            if (list1 != null && list1.size() > 0)
                for (FrtPreParts parts : list1)
                {
                    if (parts.getClaimsType() != null)
                    {
                        BasClaimsType bct = basClaimsTypeDao.get(
                                BasClaimsType.class, parts.getClaimsType());
                        if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString()))
                        {
                            preMprMatAmount += parts.getPartsAmount();
                        }
                    }
                }
            if (list2 != null && list2.size() > 0)
                for (FrtPreWktime item : list2)
                {
                    if (item.getClaimsType() != null)
                    {
                        BasClaimsType bct = basClaimsTypeDao.get(BasClaimsType.class, item.getClaimsType());
                        if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString()))
                            preclrWktimeAmount += item.getWktimeAmount();
                    }
                }
            if (list3 != null && list3.size() > 0)
                for (FrtPreClearingCost costVo : list3)
                {
                    otherAmount += costVo.getCostAmount();
                }
            sumMoney += preclrWktimeAmount;
            sumMoney += preMprMatAmount;
            sumMoney += otherAmount;
            fpc.setPreMprMatAmount(preMprMatAmount);
        	fpc.setPreclrWktimeAmount(preclrWktimeAmount);
        	fpc.setPreclrOtherAmount(otherAmount);
        	fpc.setPreclrSumAmount(sumMoney);
        	fpc.setPreclrMaterialRate(Contstants.DISCOUNTRATE / 100);
        	fpc.setPreclrSumRate(Contstants.DISCOUNTRATE / 100);
        	fpc.setPreclrWktimeRate(Contstants.DISCOUNTRATE / 100);
        	// 管理费
            double rate=Double.parseDouble(frtService.getDefaultServiceRate());
            String serviceWay=frtService.getDefaultServiceWay();
            if(serviceWay.equals(Contstants.ACCESSORIESWAY.NOTHING)){
            	fpc.setPreclrManagementFee(0d);
            }else if(serviceWay.equals(Contstants.ACCESSORIESWAY.TIMEAMOUNT)){
            	fpc.setPreclrManagementFee(fpc.getPreclrWktimeAmount()*rate);
            }else if(serviceWay.equals(Contstants.ACCESSORIESWAY.PARTSAMOUNT)){
            	fpc.setPreclrManagementFee(fpc.getPreMprMatAmount()*rate);
            }else if(serviceWay.equals(Contstants.ACCESSORIESWAY.SUMAMOUNT)){            	
            	fpc.setPreclrManagementFee(fpc.getPreclrSumAmount()*rate);
            }
        }else{
        	fpc.setPreMprMatAmount(0d);
            fpc.setPreclrWktimeAmount(0d);
            fpc.setPreclrOtherAmount(0d);
            fpc.setPreclrSumAmount(0d);
            fpc.setPreclrMaterialRate(1d);
            fpc.setPreclrSumRate(1d);
            fpc.setPreclrWktimeRate(1d);
            // 管理费
            fpc.setPreclrManagementFee(0d);
        }
    }

    private void copyData(List<PartsVo> partsList, List<FrtItemVo> itemList,
            List<FrtReceptionVehicleStructure> frceList, FrtReceptionVo freVo,
            FrtReception fre) throws Exception
    {
        // if(freVo.getRtrServices() != null &&
        // !"".equals(freVo.getRtrServices())){
        // FrtRushToRepair frtr = new FrtRushToRepair();
        // BeanUtils.copyProperties(freVo, frtr);
        // frtr.setRtrId(fre.getResvId());
        // fre.setFrtRushToRepair(frtr);
        // frtr.setFrtResevation(fre);
        // }
        if (frceList.size() > 0)
        {
            fre.getFrtReceptionVehicleStructures().removeAll(
                    fre.getFrtReceptionVehicleStructures());
            for (FrtReceptionVehicleStructure frvs : frceList){
                frvs.setFrtReception(fre);
                frvs.setEnterpriseId(freVo.getEnterpriseId());
                fre.getFrtReceptionVehicleStructures().add(frvs);
            }
        }
        if (partsList != null && partsList.size() > 0)
        {
            // fre.getFrtRcptPartses().removeAll(fre.getFrtRcptPartses());
            fre.getFrtRcptPartses().clear();
            for (PartsVo pVo : partsList)
            {
                FrtRcptParts frp = new FrtRcptParts();
                // BeanUtils.copyProperties(pVo, frp);
                MyBeanUtils.getInstance().copyBeans(pVo, frp);
                frp.setRcptpartsIndex(pVo.getPartsIndex());
                frp.setEnterpriseId(freVo.getEnterpriseId());
                // frp.setRcptpartsIndex(Integer.parseInt(pVo.getPartsId()));
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setClaimsId(pVo.getClaimsId());
                basRepairType.setReptypId(pVo.getReptypId());
                basClaimsType.setEnterpriseId(freVo.getEnterpriseId());
                basRepairType.setEnterpriseId(freVo.getEnterpriseId());
                frp.setBasClaimsType(basClaimsType);
                frp.setBasRepairType(basRepairType);
                frp.setFrtReception(fre);
                fre.getFrtRcptPartses().add(frp);
            }
        }
        if (itemList != null && itemList.size() > 0)
        {
            fre.getFrtRcptItems().removeAll(fre.getFrtRcptItems());
            for (FrtItemVo iVo : itemList)
            {
                FrtRcptItem fri = new FrtRcptItem();
                fri.setEnterpriseId(freVo.getEnterpriseId());
                // BeanUtils.copyProperties(iVo, fri);
                MyBeanUtils.getInstance().copyBeans(iVo, fri);
                fri.setRcptitemInnerTime(iVo.getInternalTime());
                fri.setRepitemNum(iVo.getRepitemTime());
                fri.setReceptionRemark(iVo.getRepitemRemark());
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setClaimsId(iVo.getClaimsId());
                basRepairType.setReptypId(iVo.getReptypId());
                basClaimsType.setEnterpriseId(freVo.getEnterpriseId());
                basRepairType.setEnterpriseId(freVo.getEnterpriseId());
                fri.setBasClaimsType(basClaimsType);
                fri.setBasRepairType(basRepairType);
                fri.setFrtReception(fre);
                fre.getFrtRcptItems().add(fri);
            }
        }
    }

    /**
     * 查找车辆结构数据
     */
    
    public Datagrid findVehicleStructure(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtReceptionVehicleStructure> vehicleStructureList = new ArrayList();
        List<Object[]> list = frtReceptionVehicleStructureDao
                .createSQLQuery("SELECT frvs.*,bcbs.BODY_NUM FROM frt_reception_vehicle_structure frvs,bas_car_bodys_status bcbs	WHERE frvs.CODE=bcbs.BODY_ID AND  frvs.RECEPTION_ID='"
                        + freVo.getReceptionId().trim() + "'");
        FrtReceptionVehicleStructure fst = null;
        if (list != null && list.size() > 0)
            for (Object[] objects : list)
            {
                fst = new FrtReceptionVehicleStructure();
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fst.setId(Short.parseShort(objects[5].toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    fst.setCode(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fst.setName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fst.setState(objects[4].toString());
                vehicleStructureList.add(fst);
            }
        Datagrid dg = new Datagrid();
        dg.setTotal(vehicleStructureList.size());
        dg.setRows(vehicleStructureList);
        return dg;
    }

    /***
     * 增加自定义维修项目
     * */
    
    public List<FrtItemVo> addFrtWorkshopManagerItem(FrtReceptionVo freVo)
            throws Exception
    {
        List<FrtItemVo> itemList = null;
        String item = freVo.getItems();
        if (item != null && item.length() > 0){
            JSONObject jsItems = JSON.parseObject(item);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),FrtItemVo.class);
        }
        if (itemList == null)
            itemList = new ArrayList<FrtItemVo>();
        FrtItemVo iv = new FrtItemVo();
        iv.setRepitemId(IncrementId.getItemId());
        iv.setRepitemName(Contstants.SERVICEREPITEMNAME);
        iv.setRepitemNum(1d);
        iv.setRepitemTime(Contstants.REPITEMTIME);
        iv.setInternalTime(Contstants.INTERNALTIME);
        iv.setRepitemAmount(Contstants.REPITEMAMOUNT);
        iv.setClaimsId(frtService.getDefaultClaimsType(freVo.getEnterpriseId()));
        iv.setClaimsName(frtService.getDefaultClaimsTypeName(freVo.getEnterpriseId()));
        iv.setReptypId(frtService.getDefaultRepairType(freVo.getEnterpriseId()));
        iv.setReptypName(frtService.getDefaultRepairTypeName(freVo.getEnterpriseId()));
        iv.setReceptionRemark("");
        itemList.add(iv);
        return itemList;
    }

    /**
     * 出料查询
     * */
    
    public Datagrid datagridEmerge(FrtReceptionVo freVo) throws Exception
    {
        StringBuffer sb = new StringBuffer("");
        String id=freVo.getReceptionId();
        if(id!=null&&!id.equals("")){
        	id=id.substring(0,2);
        	if(id.equals("XS")){
        		sb.append("SELECT CTYPE_NAME,PARTS_ID2,PARTS_NAME,PUNIT_NAME,PARTS_COUNT,PARTS_PRICE,PARTS_AMOUNT,STF_NAME,"+
				" SELLMM_ID,SELLMM_DATE,REPTYP_NAME,CLAIMS_NAME FROM ("+
				" SELECT e.*,bas_stuff.STF_NAME FROM ("+
				" SELECT d.*,st_sell_order.SELLMM_STF_ID,st_sell_order.SELLMM_ID,"+
				" st_sell_order.SELLMM_DATE,' ' AS REPTYP_NAME,' ' AS CLAIMS_NAME FROM ("+
				" SELECT c.*,frt_pre_clearing.RECEPTION_ID FROM ("+
				" SELECT b.*,bas_parts_unit.PUNIT_NAME FROM ("+
				" SELECT a.*,bas_car_type.CTYPE_NAME FROM ("+
				" SELECT frt_pre_parts.*,frt_parts.PTYPE_ID,frt_parts.PARTS_ID2,frt_parts.PUNIT_NAME AS PUNIT_ID FROM frt_pre_parts "+
				" LEFT JOIN frt_parts ON frt_pre_parts.PARTS_ID=frt_parts.PARTS_ID) AS a"+
				" LEFT JOIN bas_car_type ON a.PTYPE_ID=bas_car_type.CTYPE_NAME) AS b"+
				" LEFT JOIN bas_parts_unit ON b.PUNIT_ID=bas_parts_unit.PUNIT_ID) AS c"+
				" LEFT JOIN frt_pre_clearing ON c.PRECLR_ID=frt_pre_clearing.PRECLR_ID AND frt_pre_clearing.PRE_FLG="+Contstants.DELETE_TAG.DELETENO+") AS d"+
				" LEFT JOIN st_sell_order ON d.RECEPTION_ID=st_sell_order.SELLMM_ID) AS e"+
				" LEFT JOIN bas_stuff ON e.SELLMM_STF_ID=bas_stuff.STF_ID) AS f ");
        	}else{
    		    sb.append("SELECT bct.CTYPE_NAME,fp.PARTS_ID2,fp.PARTS_NAME,bpu.PUNIT_NAME,");
    	        sb.append(" soi.ITEM_COUNT,soi.ITEM_PRICE,soi.AMOUNT,bs.STF_NAME,som.STOM_ID,");
    	        sb.append(" som.STOM_DATE,brt.REPTYP_NAME,bclamt.CLAIMS_NAME");
    	        sb.append(" FROM frt_car fc,bas_car_type bct,frt_parts fp,frt_reception fr,");
    	        sb.append(" bas_parts_unit bpu,st_out_main som,st_out_item soi,bas_stuff bs,");
    	        sb.append(" bas_repair_type brt,bas_claims_type bclamt");
    	        sb.append(" WHERE bct.CTYPE_ID=fc.CTYPE_ID AND fr.CAR_ID=fc.CAR_ID");
    	        sb.append(" AND soi.PARTS_ID=fp.PARTS_ID AND bpu.PUNIT_ID=fp.PUNIT_NAME");
    	        sb.append(" AND soi.STOM_ID=som.STOM_ID AND som.CER_NO=fr.RECEPTION_ID");
    	        sb.append(" AND bs.STF_ID=som.PICKING_MEMBER AND soi.ITEM_CHARGE=brt.REPTYP_ID");
    	        sb.append(" AND bclamt.CLAIMS_ID=soi.CLAIMS_TYPE AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
    	        sb.append(" AND fr.RECEPTION_ID='" + freVo.getReceptionId() + "'");
    	        sb.append(" UNION ");
    	        sb.append(" SELECT bct.ctype_name,fp.parts_id2,fp.parts_name,bpu.punit_name,srpd.strtpd_cnt,");
    	        sb.append(" soi.item_price,(srpd.strtpd_cnt*soi.item_price) AS amount,");
    	        sb.append(" bs.stf_name,srpm.strtpm_id,srpm.strtpm_date,brt.reptyp_name,bclamt.claims_name");
    	        sb.append(" FROM st_rt_parts_main srpm INNER JOIN st_rt_parts_detail srpd ON srpm.strtpm_id=srpd.strtpm_id");
    	        sb.append(" INNER JOIN frt_reception fr ON fr.reception_id=srpm.reception_id");
    	        sb.append(" AND fr.RECEPTION_ID='" + freVo.getReceptionId() + "'");
    	        sb.append(" INNER JOIN frt_car fcar ON fcar.car_id=fr.car_id");
    	        sb.append(" INNER JOIN st_out_item soi ON soi.index_id=srpd.index_id");
    	        sb.append(" INNER JOIN  bas_car_type bct ON bct.ctype_id=fcar.ctype_id");
    	        sb.append(" INNER JOIN frt_parts fp ON fp.parts_id=srpd.parts_id");
    	        sb.append(" INNER JOIN bas_parts_unit bpu ON bpu.punit_id=fp.punit_name");
    	        sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=srpm.stf_id");
    	        sb.append(" INNER JOIN bas_repair_type brt ON brt.reptyp_id=soi.item_charge");
    	        sb.append(" INNER JOIN bas_claims_type bclamt ON bclamt.claims_id=soi.claims_type");
        	}
        }
        List<FrtEmergeVo> rows = new ArrayList<FrtEmergeVo>();
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList){
            	FrtEmergeVo feVo  = new FrtEmergeVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    feVo.setCarType(objects[0].toString());
                if (objects[1] != null && objects[1].toString().length() > 0)
                    feVo.setPartsCode(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    feVo.setPartsName(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    feVo.setPunitName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    feVo.setPartsCount(Double.parseDouble(objects[4].toString()));
                if (objects[5] != null && objects[5].toString().length() > 0)
                    feVo.setPartsPrice(Double.parseDouble(objects[5].toString()));
                if (objects[6] != null && objects[6].toString().length() > 0)
                    feVo.setPartsAmount(Double.parseDouble(objects[6].toString()));
                if (objects[7] != null && objects[7].toString().length() > 0)
                    feVo.setStfName(objects[7].toString());
                if (objects[8] != null && objects[8].toString().length() > 0)
                    feVo.setStorageId(objects[8].toString());
                if (objects[9] != null && objects[9].toString().length() > 0)
                    feVo.setStorageTime(MyBeanUtils.getInstance().formatString(objects[9].toString()));
                if (objects[10] != null && objects[10].toString().length() > 0)
                    feVo.setChargeName(objects[10].toString());
                if (objects[11] != null && objects[11].toString().length() > 0)
                    feVo.setClaimsName(objects[11].toString());
                rows.add(feVo);
            }
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(rows.size());
        return dg;
    }

    /**
     * 转前台
     * */
    @Log(moduleName = "前台管理", content = "车间管理单转前台", opertype = "更新/转前台")
    
    public Msg modifyCastProcenium(FrtReceptionVo freVo) throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FrtReception fr = frtReceptionDao.get(FrtReception.class, freVo.getReceptionId());
            fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState0);
            fr.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPNO);
            fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState0);
            frtReceptionDao.merge(fr);
            msg.setMsg("信息转前台成功！");
            msg.setSuccess(true);
        }
        catch(Exception es){
            msg.setMsg("信息转前台失败！");
        }
        return msg;
    }
    /**
     * 更改工单状态
     * */
	
	public Msg modifyReceptionStatus(FrtReceptionVo freVo) throws Exception {
		Msg msg=new Msg();
		try {			
			FrtReception fre = frtReceptionDao.get(FrtReception.class, freVo.getReceptionId());
			if (exists(freVo)){
				 FrtPreClearing fpc = new FrtPreClearing();
				 fpc.setPreclrTime(new Date());
            	 msg=toBalance(fre,fpc,msg);
            	if(!(msg.getSuccess()))
            		return msg;
            	fre.setReceptionStatus(freVo.getReceptionStatus());
            }else{
            	fre.setReceptionStatus(freVo.getReceptionStatus());
            	msg.setMsg("更改工单状态成功！");
			}
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setMsg("更改工单状态失败！");
		}
		return msg;
	}
}

class TempEntity
{
    private String partsId;

    private Double partsCount;

    private Short storeId;

    private Integer indexId;
    
    private Short claimsType;

    private Boolean flag=false;
    
	public String getPartsId() {
		return partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}

	public Double getPartsCount() {
		return partsCount;
	}

	public void setPartsCount(Double partsCount) {
		this.partsCount = partsCount;
	}

	public Short getStoreId() {
		return storeId;
	}

	public void setStoreId(Short storeId) {
		this.storeId = storeId;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Short getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(Short claimsType) {
		this.claimsType = claimsType;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
   
}