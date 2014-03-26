package com.syuesoft.frt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.contstants.Contstants.STGCARPARA;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.frt.dao.FrtCustomDao;
import com.syuesoft.frt.dao.FrtPreClearingDao;
import com.syuesoft.frt.dao.FrtRcptItemDao;
import com.syuesoft.frt.dao.FrtRcptPartsDao;
import com.syuesoft.frt.dao.FrtReceptionCostDao;
import com.syuesoft.frt.dao.FrtReceptionDao;
import com.syuesoft.frt.dao.FrtReceptionVehicleStructureDao;
import com.syuesoft.frt.dao.FrtResevationDao;
import com.syuesoft.frt.dao.FrtResvAdviceDao;
import com.syuesoft.frt.dao.FrtResvVehicleStructureDao;
import com.syuesoft.frt.service.FrtReceptionService;
import com.syuesoft.frt.service.FrtResevationService;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.ArrearageVo;
import com.syuesoft.frt.vo.CostVo;
import com.syuesoft.frt.vo.FrtCarClewVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasPartsUnit;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.model.FrtPreClearing;
import com.syuesoft.model.FrtPreClearingCost;
import com.syuesoft.model.FrtPreWktime;
import com.syuesoft.model.FrtRcptItem;
import com.syuesoft.model.FrtRcptParts;
import com.syuesoft.model.FrtReception;
import com.syuesoft.model.FrtReceptionCost;
import com.syuesoft.model.FrtReceptionVehicleStructure;
import com.syuesoft.model.FrtResevation;
import com.syuesoft.model.FrtResvAdvice;
import com.syuesoft.model.Reptype;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.IncrementId;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;
/**
 * 前台接车service
 * @author Liujian
 */
@Service("frtReceptionService")
public class FrtReceptionServiceImpl extends BaseLogServiceImpl implements
        FrtReceptionService
{

	 @Autowired private FrtReceptionDao frtReceptionDao;
	 @Autowired private FrtRcptPartsDao frtRcptPartsDao;
	 @Autowired private FrtRcptItemDao frtRcptItemDao;
	 @Autowired private FrtReceptionCostDao frtReceptionCostDao;
	 @Autowired private FrtCarDao frtCarDao;
	 @Autowired private FrtCustomDao frtCustomDao;
	 @Autowired private BasPartsUnitDAO basPartsUnitDAO;
	 @Autowired private FrtReceptionVehicleStructureDao frtReceptionVehicleStructureDao;
	 @Autowired private FrtResvAdviceDao frtResvAdviceDao;
	 @Autowired private FrtPreClearingDao frtPreClearingDao;
	 @Autowired private FrtResevationDao frtResevationDao;
	 @Autowired private FrtResvVehicleStructureDao frtResvVehicleStructureDao;
	 @Autowired private FrtResevationService frtResevationService;
	 @Autowired private BasClaimsTypeDao basClaimsTypeDao;
	 @Autowired private FrtService frtService;
	 @Autowired private  BasCompanyInformationSetDAO basCompanyInformationSetDAO;
 
	/**
     * 删除接车单
     */
    @Log(moduleName = "前台管理", content = "删除前台接车单", opertype = "删除")
    
    public Msg remove(String receptionId){
        Msg msg = new Msg();
        try
        {
            FrtReception fr = frtReceptionDao.get(FrtReception.class,receptionId);
            fr.setRcptFlg(Contstants.DELETE_TAG.DELETEYES);//删除
            if (fr.getResvId() != null && fr.getResvId().length() > 0)
            {
                FrtResevation frv = frtResevationDao.get(FrtResevation.class,fr.getResvId());
                if (frv != null){
                	frv.setResvToRcpt(Contstants.NEARCAR_TAG.NEARCARNO);//未转接车
                	frv.setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING);//预约中
                }
            }
            msg.setSuccess(true);
            msg.setMsg("删除接车单成功！");
        }catch(Exception es){
            msg.setMsg("删除接车单失败！");
            es.printStackTrace();
        }
        return msg;

    }
    /**
     * 工单管理汇总datagrid
     */
    public Datagrid loadFrtReception(FrtReceptionVo freVo) throws Exception{
    	Datagrid dg = new Datagrid();
        List<FrtReceptionVo> rows = new ArrayList<FrtReceptionVo>();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer("select * from (SELECT RECEPTION_ID as receptionId,(CASE WHEN PRECLR_TIME IS NULL THEN '' WHEN PRECLR_TIME IS NOT NULL THEN PRECLR_ID END) as preclrId,CAR_LICENSE as carLicense,CAR_VIN as carVin,CUSTOM_NAME as customName,CUSTOM_TEL1 as customTel1,REPT_NAME as reptName,"+
		" RECEPTOR_NAME as receptorName,INTER_DATE as interDate,PRECLR_TIME as preclrTime,RECEPTION_STATUS_NAME as receptionStatusName,RECEPTION_REP_PER as receptionRepPer,CAR_ID as carId,REPT_ID as reptId,RCPT_FLG,RECEPTION_STATUS_KEY FROM ("+
		" SELECT e.*,k.dataValue AS RECEPTION_STATUS_NAME,k.dataKey as RECEPTION_STATUS_KEY FROM ("+
		" SELECT d.*,frt_pre_clearing.PRECLR_ID,frt_pre_clearing.PRECLR_TIME FROM ("+
		" SELECT c.*,bs1.STF_NAME AS RECEPTOR_NAME FROM("+
		" SELECT b.*,reptype.REPT_NAME FROM ("+
		" SELECT a.*,frt_custom.CUSTOM_NAME,frt_custom.CUSTOM_TEL1 FROM ("+
		" SELECT frt_reception.*,frt_car.CAR_LICENSE,frt_car.CAR_VIN FROM frt_reception LEFT JOIN frt_car"+
		" ON frt_reception.CAR_ID=frt_car.CAR_ID  WHERE   frt_reception.enterprise_id="+freVo.getEnterpriseId() +") AS a"+
		" LEFT JOIN frt_custom ON a.CUSTOM_ID=frt_custom.CUSTOM_ID) AS b"+
		" LEFT JOIN reptype ON b.REPT_ID=reptype.REPT_ID) AS c"+
		" LEFT JOIN bas_stuff bs1 ON c.RECEPTOR=bs1.STF_ID) AS d"+
		" LEFT JOIN frt_pre_clearing ON d.RECEPTION_ID=frt_pre_clearing.RECEPTION_ID) AS e"+
		" LEFT JOIN (SELECT bas_childdictionary.dataKey,bas_childdictionary.dataValue "+
		" FROM bas_childdictionary,bas_parentdictionary "+
		" WHERE bas_childdictionary.parent_id=bas_parentdictionary.parent_id"+
		" AND bas_parentdictionary.dataKey='rcptStatus') AS k"+
		" ON e.RECEPTION_STATUS=k.dataKey) AS f ) as m WHERE RCPT_FLG=0");
        if (freVo.getReceptionId() != null&& freVo.getReceptionId().length() > 0)
            sb.append(" and receptionId like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptionId().trim()) + "%'");
        
        if (freVo.getPreclrId() != null && freVo.getPreclrId().length() > 0)
            sb.append(" and preclrId like '%"+ StringEscapeUtils.escapeSql(freVo.getPreclrId().trim())+ "%'");
        
        if (freVo.getCarLicense() != null && freVo.getCarLicense().length() > 0)
            sb.append(" and carId ='" + StringEscapeUtils.escapeSql(freVo.getCarLicense().trim()) + "'");
        
        if (freVo.getCustomName() != null&& freVo.getCustomName().toString().length() > 0)
            sb.append(" and customName like '%"+StringEscapeUtils.escapeSql(freVo.getCustomName().trim())+ "%'");
        
        if (freVo.getCustomTel1() != null&& freVo.getCustomTel1().toString().length() > 0)
            sb.append(" and customTel1 like '%"+ StringEscapeUtils.escapeSql(freVo.getCustomTel1().trim())+ "%'");
       
        if (freVo.getReptName() != null&& freVo.getReptName().toString().length() > 0)
            sb.append(" and reptId=" + freVo.getReptName());
        
        if (freVo.getReceptorName() != null&& freVo.getReceptorName().toString().length() > 0)
            sb.append(" and receptorName like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptorName().trim())+ "%'");
       
        if (freVo.getInterDateBegin() != null&& freVo.getInterDateBegin().toString().length() > 0)
        {
            if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
                sb.append(" and interDate between '"+ freVo.getInterDateBegin() + "' and '"+ freVo.getInterDateEnd() + "'");
            else
                sb.append(" and interDate >= '" +freVo.getInterDateBegin() + "'");
        }
        else if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
            sb.append(" and interDate <= '"+ freVo.getInterDateEnd() + "'");
        
        if (freVo.getPreclrTimeBegin() != null&& freVo.getPreclrTimeBegin().toString().length() > 0)
        {
            if (freVo.getPreclrTimeEnd() != null&& freVo.getPreclrTimeEnd().toString().length() > 0)
                sb.append(" and preclrTime between '"+ freVo.getPreclrTimeBegin() + "' and '"+ freVo.getPreclrTimeEnd() + "'");
            else
                sb.append(" and preclrTime >= '" +freVo.getPreclrTimeBegin() + "'");
        }
        else if (freVo.getPreclrTimeEnd() != null&& freVo.getPreclrTimeEnd().toString().length() > 0)
            sb.append(" and preclrTime <= '"+ freVo.getPreclrTimeEnd() + "'");
        
        if (freVo.getReceptionStatusName() != null&& freVo.getReceptionStatusName().toString().length() > 0)
            sb.append(" and RECEPTION_STATUS_KEY like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptionStatusName().trim())+ "%'");
        
        if (freVo.getReceptionRepPer() != null&& freVo.getReceptionRepPer().toString().length() > 0)
            sb.append(" and receptionRepPer like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptionRepPer().trim())+ "%'");
        if (freVo.getSort() != null && !"".equals(freVo.getSort().trim())&& freVo.getOrder() != null&& !"".equals(freVo.getOrder().trim()))
            sb.append(" order by " + freVo.getSort().trim() + " "+ freVo.getOrder().trim());
        List<Object[]> rowsList = new ArrayList<Object[]>();
        if(freVo.getPage()!=0&&freVo.getRows()!=0)
        	rowsList = frtReceptionDao.createSQLQuery(sb.toString(),freVo.getPage(), freVo.getRows());
        else
        	rowsList =frtReceptionDao.createSQLQuery(sb.toString());
        int total = 0;
        if(params!=null&&params.equals(""))
        	total = frtReceptionDao.getSQLCount(sb.toString(), params);
        else
        	total = frtReceptionDao.getSQLCount(sb.toString(), null);
        if (rowsList != null && rowsList.size() > 0){
            for (Object[] obj : rowsList){
                FrtReceptionVo fVo = new FrtReceptionVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    fVo.setReceptionId(obj[0].toString());
                if (obj[1] != null && obj[1].toString().length() > 0)
                    fVo.setPreclrId(obj[1].toString());
                if (obj[2] != null && obj[2].toString().length() > 0)
                	fVo.setCarLicense(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                	fVo.setCarVin(obj[3].toString());
                if (obj[4] != null && obj[4].toString().length() > 0)
                	fVo.setCustomName(obj[4].toString());
                if (obj[5] != null && obj[5].toString().length() > 0)
                    fVo.setCustomTel1(obj[5].toString());
                if (obj[6] != null && obj[6].toString().length() > 0)
                	fVo.setReptName(obj[6].toString());
                if (obj[7] != null && obj[7].toString().length() > 0)
                	fVo.setReceptorName(obj[7].toString());
                if (obj[8] != null && obj[8].toString().length() > 0)
                	fVo.setInterDate(obj[8].toString());
                if (obj[9] != null && obj[9].toString().length() > 0)
                	fVo.setPreclrTime(obj[9].toString());
                if (obj[10] != null && obj[10].toString().length() > 0)
                	fVo.setReceptionStatusName(obj[10].toString());
                if (obj[11] != null && obj[11].toString().length() > 0)
                	fVo.setReceptionRepPer(obj[11].toString());
                if (obj[12] != null && obj[12].toString().length() > 0)
                	fVo.setCarId(obj[12].toString());
                rows.add(fVo);
            }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }
    

    /**
     * 前台接车datagrid
     */
    
    public Datagrid datagridReception(FrtReceptionVo freVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<FrtReceptionVo> rows = new ArrayList<FrtReceptionVo>();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer("SELECT e.*,g.tempdata3 FROM(");
        sb.append(" SELECT c.*,d.tempdata2 FROM (SELECT a.*,b.tempdata1 FROM");
        sb.append(" (SELECT v.*,f.RCPT_FLG,f.VALUABLES,s.tempdata,t.fuelName,u.oldPartsName FROM  frt_reception_view v");
        sb.append(" LEFT JOIN frt_reception f");
        sb.append(" ON v.receptionId=f.RECEPTION_ID ");
        sb.append(" LEFT OUTER JOIN");
        sb.append(" (SELECT bc.dataKey,bc.dataValue AS tempdata FROM bas_parentdictionary bp,bas_childdictionary bc");
        sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                + Contstants.VALUABLESRES_TAG.VALUABLESRESKEY + "') s");
        sb.append(" ON s.dataKey=f.VALUABLES");
        sb.append(" LEFT OUTER JOIN ");
        sb.append(" (SELECT bc.dataKey,bc.dataValue AS fuelName FROM bas_parentdictionary bp,bas_childdictionary bc");
        sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                + Contstants.FUELTHING_TAG.FUELTHINGKEY + "') t");
        sb.append(" ON t.dataKey=v.fuelSituation");
        sb.append(" LEFT OUTER JOIN ");
        sb.append(" (SELECT bc.dataKey,bc.dataValue AS oldPartsName FROM bas_parentdictionary bp,bas_childdictionary bc");
        sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                + Contstants.OLDPARTS_TAG.OLDPARTSKEY + "') u");
        sb.append(" ON u.dataKey=v.oldPieces");
        sb.append(" )a,");
        sb.append(" (SELECT bc.dataKey,bc.dataValue AS tempdata1 FROM bas_parentdictionary bp,bas_childdictionary bc");
        sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                + Contstants.DOCUMENT_TAG.DOCUMENTKEY + "')b");
        sb.append(" WHERE a.receptionStatus=b.dataKey )c,");
        sb.append(" (SELECT bc1.dataKey,bc1.dataValue AS tempdata2 FROM bas_parentdictionary bp1,bas_childdictionary bc1");
        sb.append(" WHERE bc1.parent_id=bp1.parent_id AND bp1.dataKey='"
                + Contstants.TOWORKSHOP_TAG.TOWORKSHOPKEY + "'");
        sb.append(" )d WHERE c.finStatus=d.datakey)e,");
        sb.append(" (SELECT bcs.dataKey,bcs.dataValue AS tempdata3 FROM bas_parentdictionary bps,bas_childdictionary bcs");
        sb.append(" WHERE bcs.parent_id=bps.parent_id AND bps.dataKey='"
                + Contstants.MAINTAIN_TAG.MAINTAINKEY + "'");
        sb.append(" )g WHERE e.receptionMaintFlg=g.datakey ");
        sb.append(" and e.RCPT_FLG=" + Contstants.DELETE_TAG.DELETENO + "");
        if (freVo.getReceptionId() != null&& freVo.getReceptionId().length() > 0)
            sb.append(" and e.receptionId like '%"+ StringEscapeUtils.escapeSql(freVo.getReceptionId().trim()) + "%'");
        if (freVo.getResvId() != null && freVo.getResvId().length() > 0)
            sb.append(" and e.resvId like '%"+ StringEscapeUtils.escapeSql(freVo.getResvId().trim())+ "%'");
        if (freVo.getCarId() != null && freVo.getCarId().length() > 0)
            sb.append(" and e.carLicense like '%" + StringEscapeUtils.escapeSql(freVo.getCarId().trim()) + "%'");
        if (freVo.getCustomName() != null && freVo.getCustomName().length() > 0)
            sb.append(" and e.customName like '%"+ StringEscapeUtils.escapeSql(freVo.getCustomName().trim())+ "%'");
        if (freVo.getReptId() != null&& freVo.getReptId().toString().length() > 0)
            sb.append(" and e.reptId=" + freVo.getReptId());
        if (freVo.getReceptionStatus() != null&& freVo.getReceptionStatus().toString().length() > 0)
            sb.append(" and e.receptionStatus=" + freVo.getReceptionStatus());
        if (freVo.getInterDateBegin() != null&& freVo.getInterDateBegin().toString().length() > 0)
        {
            if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
                sb.append(" and e.interDate between '"+ freVo.getInterDateBegin() + "' and '"+ freVo.getInterDateEnd() + "'");
            else
                sb.append(" and e.interDate >= '" +freVo.getInterDateBegin() + "'");
        }
        else if (freVo.getInterDateEnd() != null&& freVo.getInterDateEnd().toString().length() > 0)
            sb.append(" and e.interDate <= '"+ freVo.getInterDateEnd() + "'");
        String ciVlaue=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.STGCARPARA, STGCARPARA.AUOTSHOWSERCAR,freVo.getEnterpriseId()).getCiValue();
        if(ciVlaue==null){
        	//默认进厂日期
            if(freVo.getInterDateBegin() == null && freVo.getInterDateEnd() == null ){
        		sb.append(" and DATE_FORMAT(e.interDate,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,freVo.getEnterpriseId()).getCiValue()))+ "'");
        		sb.append(" and DATE_FORMAT(e.interDate,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
            }	
        }
        if (freVo.getReceptionEndTimeBegin() != null
                && freVo.getReceptionEndTimeBegin().toString().length() > 0)
        {
            if (freVo.getReceptionEndTimeEnd() != null&& freVo.getReceptionEndTimeEnd().toString().length() > 0)
                sb.append(" and e.receptionEndTime between '"+ freVo.getReceptionEndTimeBegin()+ "' and '"+ freVo.getReceptionEndTimeEnd() + "'");
            else
                sb.append(" and e.receptionEndTime >= '"+freVo.getReceptionEndTimeBegin() + "'");
        }
        else if (freVo.getReceptionEndTimeEnd() != null&& freVo.getReceptionEndTimeEnd().toString().length() > 0)
        {
            sb.append(" and e.receptionEndTime <= '" + freVo.getReceptionEndTimeEnd() + "'");
        }
        if (freVo.getExpDelCarTimeBegin() != null&& freVo.getExpDelCarTimeBegin().toString().length() > 0)
        {
            if (freVo.getExpDelCarTimeEnd() != null&& freVo.getExpDelCarTimeEnd().toString().length() > 0)
                sb.append(" and e.expDelCarTime between '"+ freVo.getExpDelCarTimeBegin() + "' and '"+ freVo.getExpDelCarTimeEnd() + "'");
            else
                sb.append(" and e.expDelCarTime >= '"+ freVo.getExpDelCarTimeBegin() + "'");
        }
        else if (freVo.getExpDelCarTimeEnd() != null&& freVo.getExpDelCarTimeEnd().toString().length() > 0)
            sb.append(" and e.expDelCarTime <= '"+ freVo.getExpDelCarTimeEnd() + "'");
        if (freVo.getSort() != null && !"".equals(freVo.getSort().trim())&& freVo.getOrder() != null&& !"".equals(freVo.getOrder().trim()))
            sb.append(" order by e." + freVo.getSort().trim() + " "+ freVo.getOrder().trim());
        List<Object[]> rowsList = new ArrayList<Object[]>();
        if(freVo.getPage()!=0&&freVo.getRows()!=0){
        	rowsList = frtReceptionDao.createSQLQuery(sb.toString(),freVo.getPage(), freVo.getRows());
        }else{
        	rowsList =frtReceptionDao.createSQLQuery(sb.toString());
        }
        int total = 0;
        if(params!=null&&params.equals("")){
        	total = frtReceptionDao.getSQLCount(sb.toString(), params);
        }else{
        	total = frtReceptionDao.getSQLCount(sb.toString(), null);
        }
        if (rowsList != null && rowsList.size() > 0){
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
                if (obj[16] != null && obj[16].toString().length() > 0)
                    fVo.setRepwkId(new Short(obj[15].toString()));
                if (obj[16] != null && obj[16].toString().length() > 0)
                    fVo.setRepwkName(obj[16].toString());
                if (obj[17] != null && obj[17].toString().length() > 0)
                    fVo.setInterDate(MyBeanUtils.getInstance().formatString(obj[17].toString()));
                if (obj[18] != null && obj[18].toString().length() > 0)
                    fVo.setReceptionEndTime(MyBeanUtils.getInstance().formatString(obj[18].toString()));
                if (obj[19] != null && obj[19].toString().length() > 0)
                    fVo.setExpDelCarTime(MyBeanUtils.getInstance().formatString(obj[19].toString()));
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
                if (obj[39] != null && obj[39].toString().length() > 0)
                    fVo.setValuables(obj[39].toString());
                if (obj[40] != null && obj[40].toString().length() > 0)
                    fVo.setValuablesName(obj[40].toString());
                if (obj[41] != null && obj[41].toString().length() > 0)
                    fVo.setFuelSituationName(obj[41].toString());
                if (obj[42] != null && obj[42].toString().length() > 0)
                    fVo.setOldPiecesName(obj[42].toString());
                if (obj[43] != null && obj[43].toString().length() > 0)
                	fVo.setReceptionStatusName(obj[43].toString());
                if (obj[44] != null && obj[44].toString().length() > 0)
                	 fVo.setFinStatusName(obj[44].toString());
                if (obj[45] != null && obj[45].toString().length() > 0)
                	 fVo.setReceptionMaintFlgName(obj[45].toString());
                rows.add(fVo);
            }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    // 从数据库中查询维修配件
    
    public Datagrid findPartsByRcptId(String rcptId) throws Exception
    {
		List<PartsVo> partsList = new ArrayList();
    	if(rcptId!=null && !("".equals(rcptId))){
    		if(!("-1".equals(rcptId))){
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
        if(rcptId!=null && !("".equals(rcptId))){
        	if(!("-1".equals(rcptId))){
        	    StringBuffer sb = new StringBuffer("SELECT a.*, bs.STF_NAME FROM (SELECT fri.REPITEM_ID,fri.REPITEM_NAME,fri.REPITEM_NUM,fri.RCPTITEM_INNER_TIME,");
                sb.append(" fri.REPITEM_AMOUNT,fri.STF_ID,fri.PLAN_START_TIME,fri.PLAN_COMPL_TIME,");
                sb.append(" fri.REPTYP_ID,brt.REPTYP_NAME,fri.CLAIMS_ID,bct.CLAIMS_NAME,fri.RECEPTION_REMARK");
                sb.append(" FROM frt_rcpt_item fri,bas_repair_type brt,bas_claims_type bct");
                sb.append(" WHERE 1=1 AND brt.REPTYP_ID=fri.REPTYP_ID AND bct.CLAIMS_ID=fri.CLAIMS_ID ");
                if (rcptId != null && !"".equals(rcptId.trim()))
                {
                    sb.append(" AND fri.RECEPTION_ID='" + rcptId.trim() + "'");
                }
                sb.append(" ) a LEFT JOIN bas_stuff bs ON bs.STF_ID = a.STF_ID");
                List<Object[]> friList = frtRcptItemDao.createSQLQuery(sb.toString(),null);
                if (friList != null && friList.size() > 0)
                {
                    FrtItemVo iVo = null;
                    for (Object[] objects : friList)
                    {
                        iVo = new FrtItemVo();
                        if (objects[0] != null && objects[0].toString().length() > 0)
                            iVo.setRepitemId(objects[0].toString());
                        if (objects[1] != null && objects[1].toString().length() > 0)
                            iVo.setRepitemName(objects[1].toString());
                        if (objects[2] != null && objects[2].toString().length() > 0)
                            iVo.setRepitemTime(Double
                                    .parseDouble(objects[2].toString()));
                        else
                            iVo.setRepitemTime(0.00d);
                        if (objects[3] != null && objects[3].toString().length() > 0)
                            iVo.setInternalTime(Double.parseDouble(objects[3]
                                    .toString()));
                        else
                            iVo.setInternalTime(0.00d);
                        if (objects[4] != null && objects[4].toString().length() > 0)
                            iVo.setRepitemAmount(Double.parseDouble(objects[4]
                                    .toString()));
                        else
                            iVo.setRepitemAmount(0.00d);
                        if (objects[5] != null && objects[5].toString().length() > 0)
                            iVo.setStfId(Short.parseShort(objects[5].toString()));
                        if (objects[6] != null && objects[6].toString().length() > 0)
                            iVo.setPlanStartTime(MyBeanUtils.getInstance()
                                    .formatString(objects[6].toString()));
                        if (objects[7] != null && objects[7].toString().length() > 0)
                            iVo.setPlanComplTime(MyBeanUtils.getInstance()
                                    .formatString(objects[7].toString()));
                        if (objects[8] != null && objects[8].toString().length() > 0)
                            iVo.setReptypId(Short.parseShort(objects[8].toString()));
                        if (objects[9] != null && objects[9].toString().length() > 0)
                            iVo.setReptypName(objects[9].toString());
                        if (objects[10] != null && objects[10].toString().length() > 0)
                            iVo.setClaimsId(Short.parseShort(objects[10].toString()));
                        if (objects[11] != null && objects[11].toString().length() > 0)
                            iVo.setClaimsName(objects[11].toString());
                        if (objects[12] != null && objects[12].toString().length() > 0)
                            iVo.setRepitemRemark(objects[12].toString());
                        if (objects[13] != null && objects[13].toString().length() > 0)
                            iVo.setStfName(objects[13].toString());
                        itemList.add(iVo);
                    }
                }
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(itemList);
        dg.setTotal(itemList.size());
        return dg;
    }

    /**
     * 从数据库中查询其他费用明细
     */
    
    public Datagrid findCostByRcptId(String rcptId) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<CostVo> costList = new ArrayList();
        if(rcptId!=null && !("".equals(rcptId))){
        	if(!("-1".equals(rcptId))){
       		 String sql = "SELECT * FROM frt_reception_cost AS frc WHERE 1=1 ";
               if (rcptId != null && !"".equals(rcptId.trim()))
               {
                   sql += " and frc.RECEPTION_ID ='" + rcptId.trim() + "'";
               }
               List<Object[]> objList = frtReceptionCostDao.createSQLQuery(sql, null);
               if (objList != null && objList.size() > 0)
               {
                   CostVo cVo = null;
                   for (Object[] obj : objList)
                   {
                       cVo = new CostVo();
                       if (obj[0] != null && obj[0].toString().length() > 0)
                           cVo.setCostId(new Short(obj[0].toString()));
                       if (obj[1] != null && obj[1].toString().length() > 0)
                           cVo.setCostItem(obj[1].toString());
                       if (obj[2] != null && obj[2].toString().length() > 0)
                           cVo.setCostAmount(new Double(obj[2].toString()));
                       if (obj[3] != null && obj[3].toString().length() > 0)
                           cVo.setCostExplain(obj[3].toString());
                       if (obj[4] != null && obj[4].toString().length() > 0)
                           cVo.setReceptionId(obj[4].toString());
                       costList.add(cVo);
                   }
               }
        	}
        }
        dg.setRows(costList);
        dg.setTotal(costList.size());
        return dg;
    }

    /**
     * 更新前台接车单
     * */
    @Log(moduleName = "前台管理", content = "更新前台接车单", opertype = "更新")
    
    public Msg edit(FrtReceptionVo freVo) throws Exception
    {

        List<PartsVo> partsList = null;
        List<FrtItemVo> itemList = null;
        List<FrtReceptionVehicleStructure> frceList = null;
        List<CostVo> costList = null;
        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0)
        {
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            frceList = JSON.parseArray(jsVehicle.get("rows").toString(),
                    FrtReceptionVehicleStructure.class);
        }
        String items = freVo.getItems();
        if (items != null && items.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        String others = freVo.getOthers();
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            costList = JSON.parseArray(jsOthers.get("rows").toString(),
                    CostVo.class);
        }

        FrtReception fre = new FrtReception();
        Msg msg = new Msg();
        // BeanUtils.copyProperties(freVo, fre);
        MyBeanUtils.getInstance().copyBeans(freVo, fre);
        FrtCustom fc = new FrtCustom();
        // BeanUtils.copyProperties(freVo,fc);
        MyBeanUtils.getInstance().copyBeans(freVo, fc);
        fre.setFrtCustom(fc);
        Reptype reptype = new Reptype();
        // BeanUtils.copyProperties(freVo, reptype);
        MyBeanUtils.getInstance().copyBeans(freVo, reptype);
        fre.setReptype(reptype);
        reptype.getFrtReceptions().add(fre);
        FrtCar fcr = new FrtCar();
        // BeanUtils.copyProperties(freVo, fcr);
        MyBeanUtils.getInstance().copyBeans(freVo, fcr);
        fre.setResvId(freVo.getResvId());
        fre.setFrtCar(fcr);
        Boolean flag = copyData(partsList, itemList, frceList, costList, freVo,
                fre);
        try
        {
            if (flag)
            {
                FrtReception temp = frtReceptionDao.get(FrtReception.class, fre
                        .getReceptionId());
                if (items == null || items.length() == 0)
                    fre.setFrtRcptItems(temp.getFrtRcptItems());
                if (parts == null || parts.length() == 0)
                    fre.setFrtRcptPartses(temp.getFrtRcptPartses());
                if (others == null || others.length() == 0)
                    fre.setFrtReceptionCosts(temp.getFrtReceptionCosts());
                if (vehicle == null || vehicle.length() == 0)
                    fre.setFrtReceptionVehicleStructures(temp
                            .getFrtReceptionVehicleStructures());

                fre.setRcptFlg(Contstants.DELETE_TAG.DELETENO);
                fre.setManagementFee(0.00d);
                fre.setFinAllTag(Contstants.CLAIM_TAG.NOCLAIM);
                fre.setFinTag(Contstants.CLAIM_TAG.NOCLAIM);
                fre.setCorrection(Contstants.WORK_TAG.WORKNO);
                fre.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPNO);
                if(fre.getRcptBranch()==null||fre.getRcptBranch().length()==0){
                	fre.setRcptBranch(frtService.getDefaultRcptBranch());
                }
                fre.setEnterpriseId(freVo.getEnterpriseId());
                frtReceptionDao.merge(fre);
                msg.setMsg("更新前台接车单成功!");
                msg.setSuccess(true);
            }
            else
            {
                msg.setMsg("配件或项目信息不完整，请填写完整后再保存！");
                msg.setSuccess(false);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("更新前台接车单失败！");
            msg.setSuccess(false);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 保存前台接车单
     * */
    @Log(moduleName = "前台管理", content = "新增前台接车单", opertype = "新增")
    
    public synchronized Msg save(FrtReceptionVo freVo) throws Exception
    {
        List<PartsVo> partsList = null;
        List<FrtItemVo> itemList = null;
        List<FrtReceptionVehicleStructure> frceList = null;
        List<CostVo> costList = null;
        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0){
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            frceList = JSON.parseArray(jsVehicle.get("rows").toString(),FrtReceptionVehicleStructure.class);
        }
        String items = freVo.getItems();
        if (items != null && items.length() > 0){
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),FrtItemVo.class);
        }
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0){
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),PartsVo.class);
        }
        String others = freVo.getOthers();
        if (others != null && others.length() > 0){
            JSONObject jsOthers = JSON.parseObject(others);
            costList = JSON.parseArray(jsOthers.get("rows").toString(),CostVo.class);
        }
        Boolean flag = false;
        FrtReception fre = new FrtReception();
        FrtReceptionCost frtReceptionCost = null;
        FrtRcptParts frtRcptParts = null;
        FrtRcptItem frtRcptItem = null;
        Msg msg = new Msg();
        try
        {
            MyBeanUtils.getInstance().copyBeans(freVo, fre);
            if (fre.getResvId() != null && fre.getResvId().length() > 0){
                FrtResevation frv = frtResevationDao.get(FrtResevation.class,fre.getResvId());
                frtResevationService.isGoFactory(frv);// 更改预约状态
                frtResevationDao.merge(frv);
            }
            fre.setReceptionId(CreateID.createId("FrtReception",Contstants.PROSCENIUMNEARID));
            fre.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState0.toString());
            if (freVo.getResvId() != null && freVo.getResvId().length() > 0){
                FrtResevation frv = frtResevationDao.get(FrtResevation.class,freVo.getResvId());
                frv.setResvToRcpt(Contstants.NEARCAR_TAG.NEARCARYES);
                fre.setFrtCustom(frv.getFrtCustom());
                frv.getFrtCar().setCarRelationPerson(fre.getPropRepPer());
                frv.getFrtCar().setCarRelationTel1(fre.getPropTel());
                frv.getFrtCar().setCarRelationTel2(fre.getPropPhone());
                frv.getFrtCar().setReceptionRemark(freVo.getReceptionRemark());
                fre.setFrtCar(frv.getFrtCar());
            }else{
                FrtCar car = frtCarDao.get(FrtCar.class, freVo.getCarId());
                car.setCarRelationPerson(fre.getPropRepPer());
                car.setCarRelationTel1(fre.getPropTel());
                car.setCarRelationTel2(fre.getPropPhone());
                car.setReceptionRemark(freVo.getReceptionRemark());
                fre.setFrtCar(car);
                FrtCustom custom = (FrtCustom) frtCustomDao.get("from  FrtCustom  fc where fc.customId='"+freVo.getCustomId()+"'" );
                fre.setFrtCustom(custom);
            }
            Reptype rt = new Reptype();
            rt.setReptId(freVo.getReptId());
            fre.setReptype(rt);
            fre.setManagementFee(0.00d);
            fre.setFinAllTag(Contstants.CLAIM_TAG.NOCLAIM);
            fre.setFinTag(Contstants.CLAIM_TAG.NOCLAIM);
            fre.setRcptFlg(Contstants.DELETE_TAG.DELETENO);
            fre.setCorrection(Contstants.WORK_TAG.WORKNO);
            fre.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPNO);
            fre.setRcptBranch(frtService.getDefaultRcptBranch());
            fre.setEnterpriseId(freVo.getEnterpriseId());
            frtReceptionDao.save(fre);
            if (partsList != null && partsList.size() > 0)
            {
                for (PartsVo pVo : partsList)
                {
                    frtRcptParts = new FrtRcptParts();
                    // BeanUtils.copyProperties(pVo, frtRcptParts);
                    MyBeanUtils.getInstance().copyBeans(pVo, frtRcptParts);
                    BasClaimsType basClaimsType = new BasClaimsType();
                    BasRepairType basRepairType = new BasRepairType();
                    if (pVo.getClaimsId() == null|| pVo.getClaimsId().toString().length() == 0){
                        flag = true;
                        break;
                    }
                    basClaimsType.setClaimsId(pVo.getClaimsId());
                    if (pVo.getReptypId() == null|| pVo.getReptypId().toString().length() == 0){
                        flag = true;
                        break;
                    }
                    basRepairType.setReptypId(pVo.getReptypId());
                    frtRcptParts.setPunitId(pVo.getPunitId());
                    frtRcptParts.setBasClaimsType(basClaimsType);
                    frtRcptParts.setBasRepairType(basRepairType);
                    frtRcptParts.setFrtReception(fre);
                    frtRcptParts.setEnterpriseId(freVo.getEnterpriseId());
                    frtRcptPartsDao.save(frtRcptParts);
                }
                if (itemList != null && itemList.size() > 0){
                    for (FrtItemVo iVo : itemList){
                        frtRcptItem = new FrtRcptItem();
                        // BeanUtils.copyProperties(iVo, frtRcptItem);
                        MyBeanUtils.getInstance().copyBeans(iVo, frtRcptItem);
                        frtRcptItem.setRcptitemInnerTime(iVo.getInternalTime());
                        frtRcptItem.setRepitemNum(iVo.getRepitemTime());
                        frtRcptItem.setReceptionRemark(iVo.getRepitemRemark());
                        BasClaimsType basClaimsType = new BasClaimsType();
                        BasRepairType basRepairType = new BasRepairType();
                        if (iVo.getClaimsId() == null|| iVo.getClaimsId().toString().length() == 0){
                            flag = true;
                            break;
                        }
                        basClaimsType.setClaimsId(iVo.getClaimsId());
                        if (iVo.getReptypId() == null|| iVo.getReptypId().toString().length() == 0){
                            flag = true;
                            break;
                        }
                        basRepairType.setReptypId(iVo.getReptypId());
                        frtRcptItem.setBasClaimsType(basClaimsType);
                        frtRcptItem.setBasRepairType(basRepairType);
                        frtRcptItem.setFrtReception(fre);
                        frtRcptItem.setEnterpriseId(freVo.getEnterpriseId());
                        frtRcptItemDao.save(frtRcptItem);
                    }
                }

                if (costList != null && costList.size() > 0){
                    for (CostVo cost : costList){
                        frtReceptionCost = new FrtReceptionCost();
                        frtReceptionCost.setCostItem(cost.getCostItem());
                        frtReceptionCost.setCostExplain(cost.getCostExplain());
                        frtReceptionCost.setCostAmount(cost.getCostAmount());
                        frtReceptionCost.setFrtReception(fre);
                        frtReceptionCost.setEnterpriseId(freVo.getEnterpriseId());
                        frtReceptionCostDao.save(frtReceptionCost);
                    }
                }
                if (frceList != null && frceList.size() > 0){
                    for (FrtReceptionVehicleStructure frtrvStructure : frceList){
                        frtrvStructure.setFrtReception(fre);
                        frtrvStructure.setEnterpriseId(freVo.getEnterpriseId());
                        frtReceptionVehicleStructureDao.save(frtrvStructure);
                    }
                }
            }
//            fre.setReceptionStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPNO);
            msg.setMsg("保存前台单成功!");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            if (flag)
            {
                msg.setMsg("信息不完整，保存失败！");
            }
            else
            {
                msg.setMsg("保存前台单失败!");
            }
            msg.setSuccess(false);
            e.printStackTrace();
        }
        return msg;
    }

    private Boolean copyData(List<PartsVo> partsList, List<FrtItemVo> itemList,
            List<FrtReceptionVehicleStructure> frceList, List<CostVo> costList,
            FrtReceptionVo freVo, FrtReception fre) throws Exception
    {
        Boolean flag = true;
        if (frceList.size() > 0)
        {
            for (FrtReceptionVehicleStructure frvs : frceList)
            {
                frvs.setFrtReception(fre);
                frvs.setEnterpriseId(freVo.getEnterpriseId());
                fre.getFrtReceptionVehicleStructures().add(frvs);
            }
        }
        if (partsList != null && partsList.size() > 0)
        {
            for (PartsVo pVo : partsList)
            {
                FrtRcptParts frp = new FrtRcptParts();
                // BeanUtils.copyProperties(pVo, frp);
                MyBeanUtils.getInstance().copyBeans(pVo, frp);
                frp.setEnterpriseId(freVo.getEnterpriseId());
                // frp.setRcptpartsIndex(Integer.parseInt(pVo.getPartsId()));
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setEnterpriseId(freVo.getEnterpriseId());
                basRepairType.setEnterpriseId(freVo.getEnterpriseId());
                if (pVo.getClaimsId() == null
                        || pVo.getClaimsId().toString().length() == 0)
                {
                    flag = false;
                    break;
                }
                basClaimsType.setClaimsId(pVo.getClaimsId());
                if (pVo.getReptypId() == null
                        || pVo.getReptypId().toString().length() == 0)
                {
                    flag = false;
                    break;
                }
                basRepairType.setReptypId(pVo.getReptypId());
                frp.setPunitId(pVo.getPunitId());
                frp.setBasClaimsType(basClaimsType);
                frp.setBasRepairType(basRepairType);
                frp.setFrtReception(fre);
                fre.getFrtRcptPartses().add(frp);
            }
        }
        if (itemList != null && itemList.size() > 0)
        {
            for (FrtItemVo iVo : itemList)
            {
                FrtRcptItem fri = new FrtRcptItem();
                // BeanUtils.copyProperties(iVo, fri);
                MyBeanUtils.getInstance().copyBeans(iVo, fri);
                fri.setRcptitemInnerTime(iVo.getInternalTime());
                fri.setRepitemNum(iVo.getRepitemTime());
                fri.setReceptionRemark(iVo.getRepitemRemark());
                fri.setEnterpriseId(freVo.getEnterpriseId());
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setEnterpriseId(freVo.getEnterpriseId());
                basRepairType.setEnterpriseId(freVo.getEnterpriseId());
                if (iVo.getClaimsId() == null
                        || iVo.getClaimsId().toString().length() == 0)
                {
                    flag = false;
                    break;
                }
                basClaimsType.setClaimsId(iVo.getClaimsId());
                if (iVo.getReptypId() == null
                        || iVo.getReptypId().toString().length() == 0)
                {
                    flag = false;
                    break;
                }
                basRepairType.setReptypId(iVo.getReptypId());
                fri.setBasClaimsType(basClaimsType);
                fri.setBasRepairType(basRepairType);
                fri.setFrtReception(fre);
                fre.getFrtRcptItems().add(fri);

            }
        }
        if (costList != null && costList.size() > 0)
        {
            for (CostVo cost : costList)
            {
                FrtReceptionCost frtReceptionCost = new FrtReceptionCost();
                frtReceptionCost.setEnterpriseId(freVo.getEnterpriseId());
                frtReceptionCost.setCostItem(cost.getCostItem());
                frtReceptionCost.setCostExplain(cost.getCostExplain());
                frtReceptionCost.setCostAmount(cost.getCostAmount());
                frtReceptionCost.setFrtReception(fre);
                fre.getFrtReceptionCosts().add(frtReceptionCost);
            }
        }
        return flag;
    }

    /** 添加其他费用 */
    
    public List<CostVo> addFrtReceptionCost(FrtReceptionVo freVo)
            throws Exception
    {
        String others = freVo.getOthers();
        List<CostVo> otherslist = null;
        if (others != null && others.length() > 0)
        {
            JSONObject jsOthers = JSON.parseObject(others);
            otherslist = JSON.parseArray(jsOthers.get("rows").toString(),
                    CostVo.class);
        }
        if (otherslist == null)
        {
            otherslist = new ArrayList();
        }
        CostVo frc = new CostVo();
        // frc.setTagId(freVo.getId());
        frc.setCostItem("输入收费项目名称");
        frc.setCostAmount(0.00d);
        frc.setCostExplain("输入收费说明");
        otherslist.add(frc);
        return otherslist;
    }

    /**
     * 查找车辆结构数据
     * */
    
    public Datagrid findVehicleStructure(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtReceptionVehicleStructure> vehicleStructureList = new ArrayList();
        if(freVo.getReceptionId()!=null && !("".equals(freVo.getReceptionId()))){
        	if(!("-1".equals(freVo.getReceptionId()))){
        		 List<Object[]> list = frtReceptionVehicleStructureDao
                 .createSQLQuery(
                         "SELECT frvs.*,bcbs.BODY_NUM FROM frt_reception_vehicle_structure frvs,bas_car_bodys_status bcbs	WHERE frvs.CODE=bcbs.BODY_ID AND  frvs.RECEPTION_ID='"
                                 + freVo.getReceptionId().trim() + "'" , null);
         FrtReceptionVehicleStructure fst = null;
         if (list != null && list.size() > 0)
             for (Object[] objects : list)
             {
                 if (objects[2] != null)
                 {
                     fst = new FrtReceptionVehicleStructure();
                     if (objects[5] != null
                             && objects[5].toString().length() > 0)
                         fst.setId(Short.parseShort(objects[5].toString()));
                     if (objects[2] != null
                             && objects[2].toString().length() > 0)
                         fst.setCode(objects[2].toString());
                     // FrtReception fr=new FrtReception();
                     // fr.setReceptionId(objects[1].toString());
                     // fst.setFrtReception(fr);
                     if (objects[3] != null
                             && objects[3].toString().length() > 0)
                         fst.setName(objects[3].toString());
                     if (objects[4] != null
                             && objects[4].toString().length() > 0)
                         fst.setState(objects[4].toString());
                     vehicleStructureList.add(fst);
                 }
             }
        	}
        }
        Datagrid dg = new Datagrid();
        dg.setTotal(vehicleStructureList.size());
        dg.setRows(vehicleStructureList);
        return dg;
    }

    private boolean exists(String id) throws Exception
    {
        Boolean flag = false;
        if (Contstants.DOCUMENT_TAG.DOCUMENTState2.equals(id))
        {
        }
        else if (Contstants.DOCUMENT_TAG.DOCUMENTState3.equals(id))
        {
        }
        else if (Contstants.DOCUMENT_TAG.DOCUMENTState4.equals(id))
        {
        }
        else if (Contstants.DOCUMENT_TAG.DOCUMENTState5.equals(id))
        {
        }
        else if (Contstants.DOCUMENT_TAG.DOCUMENTState6.equals(id))
        {
        }
        else if (Contstants.DOCUMENT_TAG.DOCUMENTState7.equals(id))
        {
        }
        else
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 将前台接车单转到结算
     * */
    @Log(moduleName = "前台管理", content = "前台接车单转结算", opertype = "更新/转结算")
    
    public Msg modifyTransFormBalanace(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        FrtPreClearing fpc = new FrtPreClearing();
        FrtReception fre = null;
        try
        {
            fre = frtReceptionDao.get(FrtReception.class, freVo
                    .getReceptionId());
            if (isFull(freVo))
            {
                msg.setMsg("信息不完整，请填写完整后再进行相关操作！");
            }
            else if (exists(fre.getReceptionStatus()))
            {
                fre.setReceptionStatus(freVo.getReceptionStatus());
                fpc.setPreclrId(CreateID.createId("FrtPreClearing",
                        Contstants.BALANCEIDTYPE_TAG.SERVICEBALANCEID));
                fpc.setReceptionId(freVo.getReceptionId());
                if(saveCopyData(fpc, fre)){
                	 msg.setMsg("信息不完整，缺少索赔性质或收费性质，<br>请填写完整后再进行相关操作！");
                }else{
                	FrtPreClearingtotemoney(fpc);
                	addCost(fpc, fre);
                	fpc.setPreclrInoiceType(Contstants.RECEIPT_TAG.RECEIPTINIT);
                	fpc.setPreFlg(Contstants.DELETE_TAG.DELETENO);
                	fpc.setPreclrToMoney(Contstants.TOMONEY_TAG.TOMONEYNO);
                	fpc.setStfId(null);
                	fpc.setEnterpriseId(freVo.getEnterpriseId());
                	frtPreClearingDao.save(fpc);
                	fre.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPYES);
                	frtReceptionDao.merge(fre);
                	msg.setMsg("转结算成功！");
                	msg.setSuccess(true);                	
                }
            }
            else
            {
                msg.setMsg("车间正在维修中，操作失败！");
            }
        }
        catch(Exception e)
        {
            msg.setMsg("更新 工单失败!");
            e.printStackTrace();
        }
        return msg;
    }

    private void addCost(FrtPreClearing fpc, FrtReception fre) throws Exception
    {
        double taxCost = 0.00d;
        double noTaxCost = 0.00d;
        // StringBuffer sb=new
        // StringBuffer("SELECT SUM(frp.parts_num*soi.NOTAX_CAST),SUM(frp.parts_num*soi.TAX_CAST)");
        // sb.append(" FROM frt_rcpt_parts  frp ,frt_parts fp,st_out_item soi");
        // sb.append(" WHERE soi.PARTS_ID=fp.PARTS_ID AND fp.PARTS_ID=frp.PARTS_ID");
        // sb.append(" AND frp.reception_id='"+fre.getReceptionId()+"'");
        // List<Object[]> list=frtReceptionDao.createSQLQuery(sb.toString());
        // if(list!=null&&list.size()>0){
        // noTaxCost+=Double.parseDouble(list.get(0)[0].toString());
        // taxCost+=Double.parseDouble(list.get(0)[1].toString());
        // }
        // List list=new ArrayList();
        // String
        // sql="SELECT SUM((fri.REPITEM_AMOUNT/fri.REPITEM_NUM)*fri.RCPTITEM_INNER_TIME) FROM frt_rcpt_item fri WHERE fri.RECEPTION_ID='"+fre.getReceptionId()+"'";
        // list=frtReceptionDao.createSQLQuery(sql);
        // if(list!=null&&list.size()>0){
        // taxCost+=Double.parseDouble(list.get(0).toString());
        // noTaxCost+=Double.parseDouble(list.get(0).toString());
        // }
        // list.clear();
        // list.removeAll(list);
        // String
        // sql2="SELECT SUM(frc.cost_amount) FROM frt_reception_cost frc WHERE frc.reception_id='"+fre.getReceptionId()+"'";
        // List lists=frtReceptionDao.createSQLQuery(sql2);
        // if(lists!=null&&lists.size()>0){
        // taxCost+=Double.parseDouble(lists.get(0).toString());
        // noTaxCost+=Double.parseDouble(lists.get(0).toString());
        // }
        fpc.setPreclrNoTaxCost(noTaxCost);
        fpc.setPreclrTaxCost(taxCost);
    }

    private Boolean saveCopyData(FrtPreClearing fpc, FrtReception fre)
            throws NumberFormatException, Exception
    {
        Set<FrtReceptionCost> set2 = fre.getFrtReceptionCosts();
        FrtPreClearingCost fpcc = null;
        for (FrtReceptionCost frtReceptionCost : set2)
        {
            fpcc = new FrtPreClearingCost();
            fpcc.setFrtPreClearing(fpc);
            fpcc.setCostAmount(frtReceptionCost.getCostAmount());
            fpcc.setCostExplain(frtReceptionCost.getCostExplain());
            fpcc.setCostItem(frtReceptionCost.getCostItem());
            fpcc.setEnterpriseId(fre.getEnterpriseId());
            fpc.getFrtPreClearingCosts().add(fpcc);
        }
        Set<FrtRcptItem> set1 = fre.getFrtRcptItems();
        FrtPreWktime fpw = null;

        for (FrtRcptItem frtRcptItem : set1)
        {
            fpw = new FrtPreWktime();
            fpw.setFrtPreClearing(fpc);
            // fpw.setRelcampId(relcampId);
            fpw.setRepitemId(frtRcptItem.getRepitemId());
            fpw.setRepitemName(frtRcptItem.getRepitemName());
            fpw.setWktimeNum(frtRcptItem.getRepitemNum());
            fpw.setInnerWktime(frtRcptItem.getRcptitemInnerTime());
            fpw.setWktimeAmount(frtRcptItem.getRepitemAmount());
            if(frtRcptItem.getBasRepairType().getReptypId()==null||frtRcptItem.getBasRepairType().getReptypId().toString().length()==0)
            		return true;
            fpw.setReptypId(frtRcptItem.getBasRepairType().getReptypId());
            if(frtRcptItem.getBasClaimsType().getClaimsId()==null||frtRcptItem.getBasClaimsType().getClaimsId().toString().length()==0)
            		return true;
            fpw.setClaimsType(frtRcptItem.getBasClaimsType().getClaimsId());
            fpw.setStfId(frtRcptItem.getStfId());
            fpw.setEnterpriseId(fre.getEnterpriseId());
            // fpw.setSettlementDiscount(settlementDiscount);
            // fpw.setWktimeIndex(Integer.parseInt(CreateID.createId("FrtPreWktime",
            // "")));
            fpc.getFrtPreWktimes().add(fpw);
        }
        //		
        // Set<FrtRcptParts> set=fre.getFrtRcptPartses();
        // FrtPreParts pp=null;
        // for (FrtRcptParts frtRcptParts : set) {
        // pp=new FrtPreParts();
        // pp.setPartsId(frtRcptParts.getPartsId());
        // pp.setPartsCount(frtRcptParts.getPartsNum());
        // pp.setPartsPrice(frtRcptParts.getPartsRepairPrice());
        // pp.setPartsAmount(frtRcptParts.getPartsAmount());
        // pp.setPartsRate(Contstants.DISCOUNTRATE);
        // pp.setPartsRateAmount(frtRcptParts.getPartsAmount());
        // pp.setFrtPreClearing(fpc);
        // pp.setPartsName(frtRcptParts.getPartsName());
        // pp.setReptypId(frtRcptParts.getBasRepairType().getReptypId());
        // pp.setClaimsType(frtRcptParts.getBasClaimsType().getClaimsId());
        // //pp.setStinvdPrice(stinvdPrice);
        // //pp.setSettlementDiscount(settlementDiscount);
        // pp.setPartsIndex(Integer.parseInt(CreateID.createId("FrtPreParts",
        // "")));
        // //pp.setRelcampId(relcampId);
        // fpc.getFrtPrePartses().add(pp);
        // }
        return false;
    }

    private void FrtPreClearingtotemoney(FrtPreClearing fpc) throws Exception
    {
        // TODO Auto-generated method stub
        double sumMoney = 0;
        double preclrWktimeAmount = 0;
        double preMprMatAmount = 0;
        double otherAmount = 0;
        // Set<FrtRcptParts> list1=partsList;
        Set<FrtPreWktime> list2 = fpc.getFrtPreWktimes();
        Set<FrtPreClearingCost> list3 = fpc.getFrtPreClearingCosts();
        // if(list1!=null&&list1.size()>0)
        // for (FrtRcptParts partsVo : list1) {
        // preMprMatAmount+=partsVo.getPartsAmount();
        // }
        if (list2 != null && list2.size() > 0)
            for (FrtPreWktime item : list2)
            {
                if (item.getClaimsType() != null&&item.getClaimsType().toString().length()>0)
                {
                    BasClaimsType bct = basClaimsTypeDao.get(
                            BasClaimsType.class, item.getClaimsType());
                    if (bct.getClaimsToMoney().toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYYES.toString()))
                    {
                        preclrWktimeAmount += item.getWktimeAmount();
                    }
                }
            }
        if (list3 != null && list3.size() > 0)
            for (FrtPreClearingCost cost : list3)
            {
                otherAmount += cost.getCostAmount();
            }
        sumMoney += preclrWktimeAmount;
        sumMoney += preMprMatAmount;
        sumMoney += otherAmount;
        fpc.setPreMprMatAmount(preMprMatAmount);

        fpc.setPreclrWktimeAmount(preclrWktimeAmount);
        fpc.setPreclrOtherAmount(otherAmount);
        fpc.setPreclrSumAmount(sumMoney);
        fpc.setPreclrManagementFee(fpc.getPreclrSumAmount()
                * Contstants.MANAGEMENTRATE);
        fpc.setPreclrWktimeRate(Contstants.DISCOUNTRATE / 100);
        fpc.setPreclrMaterialRate(Contstants.DISCOUNTRATE / 100);
    }

    /**
     * 将前台接车单转到车间
     * */
    @Log(moduleName = "前台管理", content = "前台接车单转车间", opertype = "更新/转车间")
    
    public Msg modifyTransFormPlant(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        try
        {
            if (isFull(freVo))
            {
                msg.setMsg("信息不完整，请填写完整后再进行相关操作！");
            }
            else
            {
            	FrtReception frt = frtReceptionDao.get(FrtReception.class, freVo
                        .getReceptionId());
                frt.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPYES);
                frt.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState1);
                frtReceptionDao.merge(frt);
                msg.setMsg("信息转车间成功！");
                msg.setSuccess(true);
            }
        }
        catch(Exception es)
        {
            msg.setMsg("信息转车间失败！");
            es.printStackTrace();
        }
        return msg;
    }

    private Boolean isFull(FrtReceptionVo freVo) throws Exception
    {
        Boolean flag = false;
        FrtReception frt = frtReceptionDao.get(FrtReception.class, freVo
                .getReceptionId());
        if (frt == null)
        {
            flag = false;
            return flag;
        }
        if (frt.getReceptionEndTime() == null)
        {
            flag = true;
        }
        else if (frt.getFrtCustom() == null)
        {
            flag = true;
        }
        else if (frt.getFrtCar() == null)
        {
            flag = true;
        }
        else if (frt.getReptype() == null)
        {
            flag = true;
        }
        else if (frt.getReceptionTechnician() == null)
        {

        }
        else if (frt.getReceptionInsurPer() == null)
        {

        }
        else if (frt.getReceptionRepPer() == null)
        {

        }
        else if (frt.getFinComId() == null)
        {
            flag = true;
        }
        else if (frt.getExpDelCarTime() == null)
        {
            flag = true;
        }
        else if (frt.getInterDate() == null)
        {
            flag = true;
        }
        else if (frt.getReceptor() == null)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 增加维修建议
     * */
    @Log(moduleName = "前台管理", content = "新增维修建议", opertype = "新增")
    
    public Msg addFrtResvAdvice(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        FrtResvAdvice frtResvAdvice = new FrtResvAdvice();
        // BeanUtils.copyProperties(freVo, frtResvAdvice);
        MyBeanUtils.getInstance().copyBeans(freVo, frtResvAdvice);
        try
        {
            FrtCar fc = new FrtCar();
            fc.setCarId(freVo.getCarId());
            frtResvAdvice.setFrtCar(fc);
            frtResvAdvice.setAdviceFlg(Contstants.DELETE_TAG.DELETENO);
            frtResvAdviceDao.save(frtResvAdvice);
            frtResvAdvice.setEnterpriseId(freVo.getEnterpriseId());
            msg.setSuccess(true);
            msg.setMsg("增加维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            msg.setMsg("增加维修建议失败！");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 删除维修建议
     * */
    @Log(moduleName = "前台管理", content = "删除维修建议", opertype = "删除")
    
    public Msg deleteFrtResvAdvice(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        // FrtResvAdvice frtResvAdvice=new FrtResvAdvice();
        // BeanUtils.copyProperties(freVo, frtResvAdvice);

        try
        {
            // frtResvAdviceDao.delete(frtResvAdvice);
            FrtResvAdvice frtResvAdvice = frtResvAdviceDao.get(
                    FrtResvAdvice.class, freVo.getAdviceId());
            frtResvAdvice.setAdviceFlg(Contstants.DELETE_TAG.DELETEYES);
            msg.setSuccess(true);
            msg.setMsg("删除维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            msg.setMsg("删除维修建议失败！");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 查找维修建议
     * */
    
    public Datagrid findFrtResvAdviceByCarId(FrtReceptionVo freVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtReceptionVo> frtResvAdviceList = new ArrayList<FrtReceptionVo>();
        int total = 0;
        if(freVo.getCarId()!=null && !("".equals(freVo.getCarId()))){
        	if(!("-1".equals(freVo.getCarId()))){
        		  StringBuffer sb = new StringBuffer(
                  "SELECT a.*,b.procesStateName FROM (");
          sb
                  .append(" SELECT advice_id,fra.CAR_ID AS carId,advice_context AS adviceContext,");
          sb
                  .append(" advice_time AS adviceTime,advice_time_end AS adviceTimeEnd,");
          sb
                  .append(" write_person AS writePerson,bs.STF_NAME AS writePersonName,");
          sb
                  .append(" proces_context AS procesContext,proces_state AS procesState,");
          sb
                  .append(" proces_proson AS procesProson,bsf.STF_NAME AS procesProsonName,");
          sb.append(" advice_class AS adviceClass,CAR_LICENSE AS carLicense,");
          sb.append(" CUSTOM_NAME AS customName,bc.dataValue AS adviceClassName");
          sb.append(" FROM frt_resv_advice fra,frt_car fc,frt_custom fcs ,");
          sb
                  .append(" bas_stuff bs,bas_stuff bsf,bas_childdictionary bc,bas_parentdictionary bp");
          sb.append(" WHERE fc.CAR_ID=fra.CAR_ID AND fra.advice_flg="
                  + Contstants.DELETE_TAG.DELETENO
                  + " AND fcs.CUSTOM_ID=fc.CUSTOM_ID");
          sb
                  .append(" AND bs.STF_ID=fra.write_person AND bsf.STF_ID=fra.proces_proson");
          sb
                  .append(" AND bp.parent_id=bc.parent_id AND fra.advice_class=bc.datakey AND bp.dataKey='"
                          + Contstants.ADVICECLASS_TAG.ADVICECLASSKEY + "' ");
          sb.append( " )a, (SELECT bc.dataKey,bc.dataValue AS procesStateName");
          sb.append(" FROM bas_parentdictionary bp,bas_childdictionary bc");
          sb.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='"
                  + Contstants.PROCESSTATE_TAG.PROCESSTATEKEY + "'");
          sb.append(" )b WHERE a.procesState=b.datakey ");
          if (freVo.getCarId() != null && freVo.getCarId().length() > 0)
          {
              sb.append(" and a.carId='" + freVo.getCarId() + "'");
          }
          else
          {
              sb.append(" and a.carId='-1'");
          }
          if (freVo.getSort() != null && !"".equals(freVo.getSort().trim())
                  && freVo.getOrder() != null
                  && !"".equals(freVo.getOrder().trim()))
          {
              sb.append(" order by a." + freVo.getSort().trim() + " "
                      + freVo.getOrder().trim());
          }
          List<Object[]> list = frtReceptionDao.createSQLQuery(sb.toString(),
                  null, freVo.getPage(), freVo.getRows());
          total = frtReceptionDao.getSQLCount(sb.toString(), null);
          if (list != null && list.size() > 0)
              for (Object[] objects : list)
              {
                  FrtReceptionVo fr = new FrtReceptionVo();
                  if (objects[0] != null && objects[0].toString().length() > 0)
                      fr.setAdviceId(Integer.parseInt(objects[0].toString()));
                  if (objects[1] != null && objects[1].toString().length() > 0)
                      fr.setCarId(objects[1].toString());
                  if (objects[2] != null && objects[2].toString().length() > 0)
                      fr.setAdviceContext(objects[2].toString());
                  if (objects[3] != null && objects[3].toString().length() > 0)
                      fr.setAdviceTime(MyBeanUtils.getInstance().formatString(
                              objects[3].toString()));
                  if (objects[4] != null && objects[4].toString().length() > 0)
                      fr.setAdviceTimeEnd(MyBeanUtils.getInstance().formatString(
                              objects[4].toString()));
                  if (objects[5] != null && objects[5].toString().length() > 0)
                      fr.setWritePerson(Integer.parseInt(objects[5].toString()));
                  if (objects[6] != null && objects[6].toString().length() > 0)
                      fr.setWritePersonName(objects[6].toString());
                  if (objects[7] != null && objects[7].toString().length() > 0)
                      fr.setProcesContext(objects[7].toString());
                  if (objects[8] != null && objects[8].toString().length() > 0)
                      fr.setProcesState(Integer.parseInt(objects[8].toString()));
                  if (objects[9] != null && objects[9].toString().length() > 0)
                      fr.setProcesProson(Integer.parseInt(objects[9].toString()));
                  if (objects[10] != null && objects[10].toString().length() > 0)
                      fr.setProcesProsonName(objects[10].toString());
                  if (objects[11] != null && objects[11].toString().length() > 0)
                      fr.setAdviceClass(Short.parseShort(objects[11].toString()));
                  if (objects[12] != null && objects[12].toString().length() > 0)
                      fr.setCarLicense(objects[12].toString());
                  if (objects[13] != null && objects[13].toString().length() > 0)
                      fr.setCustomName(objects[13].toString());
                  if (objects[14] != null && objects[14].toString().length() > 0)
                      fr.setAdviceClassName(objects[14].toString());
                  if (objects[15] != null && objects[15].toString().length() > 0)
                      fr.setProcesStateName(objects[15].toString());
                  frtResvAdviceList.add(fr);
              }
        	}
        }
      
        Datagrid dg = new Datagrid();
        dg.setTotal(total);
        dg.setRows(frtResvAdviceList);
        return dg;
    }

    /**
     * 修改维修建议
     * */
    @Log(moduleName = "前台管理", content = "更新维修建议", opertype = "更新")
    
    public Msg updateFrtResvAdvice(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        try
        {
            FrtResvAdvice frtResvAdvice = frtResvAdviceDao.get(
                    FrtResvAdvice.class, freVo.getAdviceId());
            frtResvAdvice.setProcesState(freVo.getProcesState());
            frtResvAdvice.setAdviceTimeEnd(MyBeanUtils.getInstance().getDate(
                    freVo.getAdviceTimeEnd()));
            frtResvAdvice.setProcesProson(freVo.getProcesProson());
            frtResvAdvice.setProcesContext(freVo.getProcesContext());
            FrtCar fc = new FrtCar();
            fc.setCarId(freVo.getCarId());
            frtResvAdvice.setFrtCar(fc);
            frtResvAdviceDao.merge(frtResvAdvice);
            msg.setSuccess(true);
            msg.setMsg("修改维修建议成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            msg.setMsg("修改维修建议失败！");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 计算费用总和
     * */
    
    public List totemoney(FrtReceptionVo freVo) throws Exception
    {
        List list = null;
    	List<BasClaimsType> bctList=basClaimsTypeDao.find("from BasClaimsType");
    	HashMap hashMap=new HashMap();
    	if(bctList != null && bctList.size() > 0){
        	for (BasClaimsType basClaimsType : bctList) {
    			hashMap.put(basClaimsType.getClaimsId(), basClaimsType.getClaimsToMoney());
    		}
            List<PartsVo> list1 = null;
            List<FrtItemVo> list2 = null;
            List<CostVo> list3 = null;
            String items = freVo.getItems();
            if (items != null && items.length() > 0)
            {
                JSONObject jsItems = JSON.parseObject(items);
                list2 = JSON.parseArray(jsItems.get("rows").toString(),
                        FrtItemVo.class);
            }
            String parts = freVo.getParts();
            if (parts != null && parts.length() > 0)
            {
                JSONObject jsParts = JSON.parseObject(parts);
                list1 = JSON.parseArray(jsParts.get("rows").toString(),
                        PartsVo.class);
            }
            String others = freVo.getOthers();
            if (others != null && others.length() > 0)
            {
                JSONObject jsOthers = JSON.parseObject(others);
                list3 = JSON.parseArray(jsOthers.get("rows").toString(),
                        CostVo.class);
            }
            double sumMoney = 0;
            double preclrWktimeAmount = 0;
            double preMprMatAmount = 0;
            double otherAmount = 0;
            if (list1 != null && list1.size() > 0)
                for (PartsVo partsVo : list1)
                {
                    if (hashMap.get(partsVo.getClaimsId()).toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYNO))
                    {
                        continue;
                    }
                    preMprMatAmount += partsVo.getPartsAmount();
                }
            if (list2 != null && list2.size() > 0)
                for (FrtItemVo itemVo : list2)
                {
                    if (hashMap.get(itemVo.getClaimsId()).toString().equals(Contstants.CLAIMSTYPETOMOENY.CLAIMSTYPETOMOENYNO))
                    {
                        continue;
                    }
                    if (itemVo.getRepitemAmount() != null
                            && itemVo.getRepitemTime() != null)
                        preclrWktimeAmount += (itemVo.getRepitemAmount());
                }
            if (list3 != null && list3.size() > 0)
                for (CostVo costVo : list3)
                {
                    otherAmount += costVo.getCostAmount();
                }
            sumMoney += preclrWktimeAmount;
            sumMoney += preMprMatAmount;
            sumMoney += otherAmount;
            list = new ArrayList();
            list.add(preclrWktimeAmount);
            list.add(preMprMatAmount);
            // 管理费
            double preclrManagementFee = 0.00d;
            list.add(preclrManagementFee);
            list.add(otherAmount);
            list.add(sumMoney);
    	}
    	return list;
    }

    /**
     * 根据预约/保险估价单编号查找相应的车辆信息
     * */
    
    public Datagrid findvehicleStructureListByResvId(FrtReceptionVo freVo)
            throws Exception
    {
        // TODO Auto-generated method stub
        List<FrtReceptionVehicleStructure> vehicleStructureList = new ArrayList();
        List<Object[]> list = frtResvVehicleStructureDao
                .createSQLQuery(
                        "SELECT frvs.*,bcbs.BODY_NUM FROM frt_resv_vehicle_structure frvs,bas_car_bodys_status bcbs WHERE frvs.CODE=bcbs.BODY_ID AND frvs.RESV_ID='"
                                + freVo.getResvId() + "'", null);
        FrtReceptionVehicleStructure fst = null;
        if (list != null && list.size() > 0)
            for (Object[] objects : list)
            {
                fst = new FrtReceptionVehicleStructure();
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fst.setId(Short.parseShort(objects[5].toString()));
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fst.setCode(objects[1].toString());
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

    /**
     * 增加自定义维修项目
     * */
    
    public List<FrtItemVo> addFrtReceptionItem(FrtReceptionVo freVo)
            throws Exception
    {
        List<FrtItemVo> itemList = null;
        String item = freVo.getItems();
        if (item != null && item.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(item);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (itemList == null)
        {
            itemList = new ArrayList();
        }
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
     * 查找维修履历
     * */
    
    public Datagrid serviceRecord(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer(
                "SELECT fri.PLAN_START_TIME,fr.RECEPTION_DISTANCE,fri.REPITEM_NAME,");
        sb.append(" fri.REPITEM_AMOUNT,bs.STF_NAME");
        sb.append(" FROM frt_rcpt_item fri,frt_reception fr,bas_stuff bs,");
        sb.append(" frt_pre_clearing fpc,fin_maintenance_receivables fmr");
        sb
                .append(" WHERE fri.RECEPTION_ID=fr.RECEPTION_ID AND fpc.RECEPTION_ID=fr.RECEPTION_ID AND fpc.PRE_FLG="+Contstants.DELETE_TAG.DELETENO);
        sb
                .append(" AND bs.STF_ID=fri.STF_ID AND fmr.PRECLR_ID=fpc.PRECLR_ID AND fmr.MR_UNFINISHED="
                        + Contstants.OPINIONFINISHED_TAG.FINISHED);
        sb.append(" AND fr.CAR_ID='" + freVo.getCarId() + "'");
        List<FrtReceptionVo> rows = new ArrayList();
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, freVo.getPage(), freVo.getRows());
        FrtReceptionVo frVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                frVo = new FrtReceptionVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    frVo.setPlanStartTime(MyBeanUtils.getInstance()
                            .formatString(objects[0].toString()));
                if (objects[1] != null && objects[1].toString().length() > 0)
                    frVo.setReceptionDistance(Integer.parseInt(objects[1]
                            .toString()));
                if (objects[2] != null && objects[2].toString().length() > 0)
                    frVo.setRepitemName(objects[2].toString());
                if (objects[3] != null && objects[3].toString().length() > 0)
                    frVo.setRepitemAmount(Double.parseDouble(objects[3]
                            .toString()));
                if (objects[4] != null && objects[4].toString().length() > 0)
                    frVo.setStfName(objects[4].toString());
                rows.add(frVo);
            }
        int total = frtPreClearingDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     *查找维修套餐
     * */
    
    public Datagrid selectServiceWeave(FrtReceptionVo freVo) throws Exception
    {
        StringBuffer sb = new StringBuffer(
                "SELECT RP_ID,RP_NAME,ITEM_TIME_AMOUNT,PARTS_AMOUNT,APPLICABLE_BRANDS");
        sb.append(" FROM bas_repair_package brp WHERE 1=1  and   brp.enterprise_id="+freVo.getEnterpriseId());
        if (freVo.getRpId() != null && freVo.getRpId().length() > 0)
        {
            sb.append(" AND brp.RP_ID like '%" + StringEscapeUtils.escapeSql(freVo.getRpId().trim()) + "%'");
        }
        if (freVo.getRpName() != null && freVo.getRpName().length() > 0)
        {
            sb.append(" AND brp.RP_NAME like '%" + StringEscapeUtils.escapeSql(freVo.getRpName().trim()) + "%'");
        }
        List<BasRepairPackageVo> rows = new ArrayList();
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString(),
                null, freVo.getPage(), freVo.getRows());
        BasRepairPackageVo brpVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                brpVo = new BasRepairPackageVo();
                if (objects[0] != null && objects[0].toString().length() > 0)
                    brpVo.setRpId(Short.parseShort(objects[0].toString()));
                if (objects[1] != null && objects[1].toString().length() > 0)
                    brpVo.setRpName(objects[1].toString());
                if (objects[2] != null && objects[2].toString().length() > 0)
                    brpVo.setItemTimeAmount(Double.parseDouble(objects[2]
                            .toString()));
                if (objects[3] != null && objects[3].toString().length() > 0)
                    brpVo.setPartsAmount(Double.parseDouble(objects[3]
                            .toString()));
                rows.add(brpVo);
            }
        int total = frtPreClearingDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     *查询套餐项目并添加到接车单中
     * */
    
    public List<FrtItemVo> findItemListByRpId(FrtReceptionVo freVo)
            throws Exception
    {
        List<FrtItemVo> list = null;
        String item = freVo.getItems();
        if (item != null && item.length() > 0)
        {
            JSONObject jsItems = JSON.parseObject(item);
            list = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (list == null)
        {
            list = new ArrayList();
        }
        StringBuffer sb = new StringBuffer(
                "SELECT REPITEM_ID,REPITEM_NAME,REPITEM_NUM,REPITEM_AMOUNT");
        sb.append(" FROM  bas_repair_package_item brpi WHERE brpi.RP_ID="
                + freVo.getRpId());
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        FrtItemVo fiVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                fiVo = new FrtItemVo();
                fiVo.setRepitemId(objects[0].toString());
                fiVo.setRepitemName(objects[1].toString());
                fiVo.setRepitemTime(Double.parseDouble(objects[2].toString()));
                fiVo
                        .setRepitemAmount(Double.parseDouble(objects[3]
                                .toString()));
                fiVo.setClaimsId(frtService.getDefaultClaimsType(freVo.getEnterpriseId()));
                fiVo.setClaimsName(frtService.getDefaultClaimsTypeName(freVo.getEnterpriseId()));
                fiVo.setReptypId(frtService.getDefaultRepairType(freVo.getEnterpriseId()));
                fiVo.setReptypName(frtService.getDefaultRepairTypeName(freVo.getEnterpriseId()));
                list.add(fiVo);
            }
        return list;
    }

    /**
     * 查询套餐配件并添加到接车单中
     * */
    
    public List<PartsVo> findPartsListByRpId(FrtReceptionVo freVo)
            throws Exception
    {
        List<PartsVo> list = null;
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0)
        {
            JSONObject jsParts = JSON.parseObject(parts);
            list = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        if (list == null)
        {
            list = new ArrayList();
        }
        StringBuffer sb = new StringBuffer(
                "SELECT brpp.PARTS_ID,brpp.PARTS_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,");
        sb
                .append(" brpp.PARTS_NUM,brpp.PARTS_REPAIR_PRICE,brpp.PARTS_AMOUNT,brpp.STORE_ID");
        sb.append(" FROM bas_repair_package_parts brpp,bas_parts_unit bpu");
        sb.append(" WHERE bpu.PUNIT_ID=brpp.PUNIT_ID AND brpp.RP_ID="
                + freVo.getRpId());
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        PartsVo pVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList)
            {
                pVo = new PartsVo();
                pVo.setPartsId(objects[0].toString());
                pVo.setPartsName(objects[1].toString());
                pVo.setPunitId(objects[2].toString());
                pVo.setPunitName(objects[3].toString());
                pVo.setPartsNum(Double.parseDouble(objects[4].toString()));
                pVo.setPartsRepairPrice(Double.parseDouble(objects[5]
                        .toString()));
                pVo.setPartsAmount(Double.parseDouble(objects[6].toString()));
                pVo.setStoreId(objects[7].toString());
                pVo.setClaimsId(frtService.getDefaultClaimsType(freVo.getEnterpriseId()));
                pVo.setClaimsName(frtService.getDefaultClaimsTypeName(freVo.getEnterpriseId()));
                pVo.setReptypId(frtService.getDefaultRepairType(freVo.getEnterpriseId()));
                pVo.setReptypName(frtService.getDefaultRepairTypeName(freVo.getEnterpriseId()));
                list.add(pVo);
            }
        return list;
    }

    /**
     * 更改里程数
     * */
    @Log(moduleName = "前台管理", content = "更改车档案车辆里程数", opertype = "更新/更改里程数")
    
    public Msg modifyDistance(FrtReceptionVo freVo) throws Exception
    {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        try
        {
            FrtCar fc = frtCarDao.get(FrtCar.class, freVo.getCarId());
            if (fc != null)
            {
                fc.setCarLastRepairDistance(freVo.getReceptionDistance());
                frtCarDao.merge(fc);
                msg.setMsg("更改里程数成功！");
                msg.setSuccess(true);
            }
            else
            {
                msg.setMsg("更改里程数失败！");
            }
        }
        catch(Exception e)
        {
            msg.setMsg("更改里程数失败！");
            msg.setSuccess(false);
            e.printStackTrace();
        } finally
        {
            return msg;
        }
    }

    /**
     * 查找车档案里程数
     * */
    
    public Msg findCarDistance(FrtReceptionVo freVo) throws Exception
    {
        Msg msg = new Msg();
        try
        {
            FrtCar fc = frtCarDao.get(FrtCar.class, freVo.getCarId());
            msg.setSuccess(true);
            if(fc!=null)
            	msg.setObj(fc.getCarLastRepairDistance());
            else
            	msg.setObj(0d);
        }
        catch(Exception e)
        {
            msg.setSuccess(false);
            e.printStackTrace();
        } finally
        {
            return msg;
        }
    }

    /**
     * 判断里程数是否符合
     * */
    
    public Boolean isDistanceTrue(FrtReceptionVo freVo) throws Exception
    {
        FrtCar fc = frtCarDao.get(FrtCar.class, freVo.getCarId());
        if (fc.getCarLastRepairDistance() == null)
            fc.setCarLastRepairDistance(0);
        if (freVo.getReceptionDistance() != null)
            if (freVo.getReceptionDistance() <= fc.getCarLastRepairDistance())
            {
                return true;
            }
            else
            {
                return false;
            }
        return null;
    }
    /**
     * 查询前台接车提醒信息
     * */
	
	public Msg findLastService(FrtReceptionVo freVo) throws Exception {
		String defaultReceptionClew=frtService.findDefaultReceptionClew();
		if(defaultReceptionClew==null||defaultReceptionClew.length()==0){
			return null;
		}
		String []temp=defaultReceptionClew.split(",");
		if(temp!=null&&temp.length>0){
			HashMap<String,String> hashMap=new HashMap<String,String>();
			Msg msg=new Msg();
			for (String string : temp) {
				if("rcptClew1".equals(string)||"rcptClew2".equals(string)||"rcptClew5".equals(string)
						||"rcptClew6".equals(string)||"rcptClew7".equals(string)){
					StringBuffer sb1=new StringBuffer("SELECT fr.RECEPTION_ID,");
					sb1.append(" (SELECT DATEDIFF(NOW(),fr.INTER_DATE )),");
					sb1.append(" fc.CAR_LAST_REPAIR_DATE,");
					sb1.append(" fc.CAR_LAST_REPAIR_DISTANCE,");
					sb1.append(" fc.CAR_LAST_MAINT_DATE,");
					sb1.append(" fc.CAR_LAST_MAINT_DISTANCE,");
					sb1.append(" fc.CAR_ANNUAL_DATE,");
					sb1.append(" fc.CAR_EXAMINED_DATE,");
					sb1.append(" fc.CAR_BUSINSURANCE_DATE,");
					sb1.append(" fc.CAR_BASINSURANCE_DATE,");
					sb1.append(" bvi.END_TIME");
					sb1.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing  fpc");
					sb1.append(" ON fpc.RECEPTION_ID=fr.RECEPTION_ID");
					sb1.append(" INNER JOIN frt_car fc ON fc.CAR_ID=fr.CAR_ID");
					sb1.append(" LEFT OUTER JOIN bas_vip_infor bvi");
					sb1.append(" ON fc.VIP_ID=bvi.VIP_ID");
					sb1.append(" WHERE fr.car_id='"+freVo.getCarId()+"' AND fpc.PRE_FLG=" + Contstants.DELETE_TAG.DELETENO+" ORDER BY fr.INTER_DATE DESC LIMIT 1");
					FrtCarClewVo fccVo=new FrtCarClewVo();
					//FrtCar
					List<Object[]> list1=frtReceptionDao.createSQLQuery(sb1.toString());
					if(list1!=null&&list1.size()>0){
						Object[] obj=list1.get(0);
						if(obj[0]!=null&&obj[0].toString().length()>0)
							fccVo.setReceptionId(obj[0].toString());
						if(obj[1]!=null&&obj[1].toString().length()>0)
							fccVo.setNotIntoTheStoreDays(Integer.parseInt(obj[1].toString()));
						if(obj[2]!=null&&obj[2].toString().length()>0)
							fccVo.setCarLastRepairDate(MyBeanUtils.getInstance().formatString(obj[2].toString()));
						if(obj[3]!=null&&obj[3].toString().length()>0)
							fccVo.setCarLastRepairDistance(Integer.parseInt(obj[3].toString()));
						if(obj[4]!=null&&obj[4].toString().length()>0)
							fccVo.setCarLastMaintDate(MyBeanUtils.getInstance().formatString(obj[4].toString()));
						if(obj[5]!=null&&obj[5].toString().length()>0)
							fccVo.setCarLastMaintDistance(Integer.parseInt(obj[5].toString()));
						if(obj[6]!=null&&obj[6].toString().length()>0)
							fccVo.setCarAnnualDate(obj[6].toString());
						if(obj[7]!=null&&obj[7].toString().length()>0)
							fccVo.setCarExaminedDate(obj[7].toString());
						if(obj[8]!=null&&obj[8].toString().length()>0)
							fccVo.setCarBusinsuranceDate(obj[8].toString());
						if(obj[9]!=null&&obj[9].toString().length()>0)
							fccVo.setCarBasinsuranceDate(obj[9].toString());
						if(obj[10]!=null&&obj[10].toString().length()>0)
							fccVo.setVipEndTime(obj[10].toString());
					}
					loadData1(hashMap,fccVo);
				}
				if("rcptClew3".equals(string)){
					List<FrtReceptionVo> rowsAdvice=new ArrayList();
					StringBuffer sb3=new StringBuffer("SELECT fra.advice_time,bs.stf_name,bc.dataValue,fra.advice_context");
					sb3.append(" FROM frt_resv_advice fra,bas_stuff bs,bas_childdictionary bc,bas_parentdictionary bp");
					sb3.append(" WHERE bp.parent_id=bc.parent_id AND bs.stf_id=fra.write_person");
					sb3.append(" AND fra.advice_class=bc.dataKey AND bp.dataKey='"+Contstants.ADVICECLASS_TAG.ADVICECLASSKEY+"'");
					sb3.append(" AND fra.proces_state="+Contstants.PROCESSTATE_TAG.PROCESSTATENO+" AND  fra.CAR_ID='"+freVo.getCarId()+"'");
					FrtReceptionVo frVo=null;
					List<Object[]> list3=frtReceptionDao.createSQLQuery(sb3.toString());
					if(list3!=null&&list3.size()>0)
					for (Object[] obj : list3) {
						frVo=new FrtReceptionVo();
						if(obj[0]!=null&&obj[0].toString().length()>0)
							frVo.setAdviceTime(MyBeanUtils.getInstance().formatString(obj[0].toString()));
						if(obj[1]!=null&&obj[1].toString().length()>0)
							frVo.setStfName(obj[1].toString());
						if(obj[2]!=null&&obj[2].toString().length()>0)
							frVo.setAdviceClassName(obj[2].toString());
						if(obj[3]!=null&&obj[3].toString().length()>0)
							frVo.setAdviceContext(obj[3].toString());
						rowsAdvice.add(frVo);
					}
					loadData3(hashMap,rowsAdvice);
				}
				if("rcptClew4".equals(string)){
					List<ArrearageVo> rowsMoney=new ArrayList();
					StringBuffer sb2=new StringBuffer("SELECT fr.RECEPTION_ID,fpc.PRECLR_ID,fpc.PRECLR_TIME,fmr.MR_RECEIVABLES,fmr.MR_ARREARS");
					sb2.append(" FROM fin_maintenance_receivables fmr,frt_pre_clearing fpc,frt_reception fr");
					sb2.append(" WHERE fr.RECEPTION_ID=fpc.RECEPTION_ID AND fmr.PRECLR_ID=fpc.PRECLR_ID"); 
					sb2.append(" AND fmr.MR_UNFINISHED=1 AND fpc.PRE_FLG=" + Contstants.DELETE_TAG.DELETENO+"  AND fr.CAR_ID='"+freVo.getCarId()+"'");
					List<Object[]> list2=frtReceptionDao.createSQLQuery(sb2.toString());
					ArrearageVo aVo=null;
					if(list2!=null&&list2.size()>0)
					for (Object[] obj : list2) {
						aVo=new ArrearageVo();
						if(obj[0]!=null&&obj[0].toString().length()>0)
							aVo.setReceptionId(obj[0].toString());
						if(obj[1]!=null&&obj[1].toString().length()>0)
							aVo.setPreclrId(obj[1].toString());
						if(obj[2]!=null&&obj[2].toString().length()>0)
							aVo.setPreclrTime(MyBeanUtils.getInstance().formatString(obj[2].toString()));
						if(obj[3]!=null&&obj[3].toString().length()>0)
							aVo.setReceivables(Double.parseDouble(obj[3].toString()));
						if(obj[4]!=null&&obj[4].toString().length()>0)
							aVo.setArrears(Double.parseDouble(obj[4].toString()));
						rowsMoney.add(aVo);
					}
					loadData2(hashMap,rowsMoney);
				}
			}
			msg.setObj(castHashMap(hashMap,temp));
			msg.setSuccess(true);
			return msg;
		}
		return null;
	}
	private String castHashMap(HashMap<String,String> hashMap,String [] temp){
		StringBuffer sb=new StringBuffer();
		for (String string : temp) {
			if("rcptClew1".equals(string)){
				if(hashMap.containsKey("rcptClew1")){
					sb.append(hashMap.get("rcptClew1"));
				}
			}
			if("rcptClew2".equals(string)){
				if(hashMap.containsKey("rcptClew2")){
					sb.append(hashMap.get("rcptClew2"));
				}
			}
			if("rcptClew5".equals(string)){
				if(hashMap.containsKey("rcptClew5")){
					sb.append(hashMap.get("rcptClew5"));
				}
			}
			if("rcptClew6".equals(string)){
				if(hashMap.containsKey("rcptClew6")){
					sb.append(hashMap.get("rcptClew6"));
				}
			}
			if("rcptClew7".equals(string)){
				if(hashMap.containsKey("rcptClew7")){
					sb.append(hashMap.get("rcptClew7"));
				}
			}
		}
		for (String string : temp) {
			if("rcptClew4".equals(string)){
				if(hashMap.containsKey("rcptClew4")){
					sb.append(hashMap.get("rcptClew4"));
					break;
				}
			}
		}
		for (String string : temp) {
			if("rcptClew3".equals(string)){
				if(hashMap.containsKey("rcptClew3")){
					sb.append(hashMap.get("rcptClew3"));
					break;
				}
			}
		}
		return sb.toString();
	}
	private void loadData3(HashMap hashMap,List<FrtReceptionVo> list){
		StringBuffer sb=new StringBuffer("<table style=\"width:100%;\" ><tr><td>发布日期</td><td>发布人</td><td>提醒分类</td><td>维修建议</td></tr>");
		if(list!=null&&list.size()>0)
		for (FrtReceptionVo frVo : list) {
			if(frVo.getAdviceContext()==null)
				frVo.setAdviceContext("");
			sb.append("<tr><td>"+frVo.getAdviceTime()+"</td><td>"+frVo.getStfName()+"</td><td>"+frVo.getAdviceClassName()
						+"</td><td>"+frVo.getAdviceContext()+"</td></tr>");
		}
		sb.append("</table>");
		hashMap.put("rcptClew3", sb.toString());
	}
	private void loadData2(HashMap hashMap,List<ArrearageVo> list){
		StringBuffer sb=new StringBuffer("<table style=\"width:100%;\"><tr><td>工单号</td><td>结算单号</td><td>结算时间</td><td>应付金额</td><td>欠款金额</td></tr>");
		if(list!=null&&list.size()>0)
		for (ArrearageVo aVo : list) {
			sb.append("<tr><td>"+aVo.getReceptionId()+"</td><td>"+aVo.getPreclrId()+"</td><td>"+aVo.getPreclrTime()
						+"</td><td>"+aVo.getReceivables()+"</td><td>"+aVo.getArrears()+"</td></tr>");
		}
		sb.append("</table>");
		hashMap.put("rcptClew4", sb.toString());
	}
	private void loadData1(HashMap hashMap,FrtCarClewVo fccVo){
		if(fccVo.getNotIntoTheStoreDays()==null)
			fccVo.setNotIntoTheStoreDays(0);
		if(fccVo.getCarLastRepairDate()==null)
			fccVo.setCarLastRepairDate("");
		if(fccVo.getCarLastRepairDistance()==null)
			fccVo.setCarLastRepairDistance(0);
		if(fccVo.getCarAnnualDate()==null)
			fccVo.setCarAnnualDate("");
		if(fccVo.getCarExaminedDate()==null)
			fccVo.setCarExaminedDate("");
		if(fccVo.getCarBusinsuranceDate()==null)
			fccVo.setCarBusinsuranceDate("");
		if(fccVo.getCarBasinsuranceDate()==null)
			fccVo.setCarBasinsuranceDate("");
		if(fccVo.getVipEndTime()==null)
			fccVo.setVipEndTime("");
		StringBuffer s1=new StringBuffer("<table><tr>");
		s1.append("<td>未来厂天数:</td><td>"+fccVo.getNotIntoTheStoreDays()+"</td>");
		s1.append("</tr></table>");
		hashMap.put("rcptClew1", s1.toString());
		s1.delete(0, s1.length());
		s1.append("<table><tr>");
		s1.append("<td>最后维修日期:</td><td>"+fccVo.getCarLastRepairDate()+"</td>");
		s1.append("<td>最后维修里程数:</td><td>"+fccVo.getCarLastRepairDistance()+"</td>");
//		s1.append("<td>最后保养日期:</td><td>"+fccVo.getCarLastRepairDate()+"</td>");
//		s1.append("<td>最后保养里程数:</td><td>"+fccVo.getCarLastRepairDistance()+"</td>");
		s1.append("</tr></table>");
		hashMap.put("rcptClew2", s1.toString());
		s1.delete(0, s1.length());
		s1.append("<table><tr>");
		s1.append("<td>年检日期:</td><td>"+fccVo.getCarAnnualDate()+"</td>");
		s1.append("<td>年审日期:</td><td>"+fccVo.getCarExaminedDate()+"</td>");
		s1.append("</tr></table>");
		hashMap.put("rcptClew5", s1.toString());
		s1.delete(0, s1.length());
		s1.append("<table><tr>");
		s1.append("<td>商业险到期:</td><td>"+fccVo.getCarBusinsuranceDate()+"</td>");
		s1.append("<td>交强险到期:</td><td>"+fccVo.getCarBasinsuranceDate()+"</td>");
		s1.append("</tr></table>");
		hashMap.put("rcptClew6", s1.toString());
		s1.delete(0, s1.length());
		s1.append("<table><tr>");
		s1.append("<td>会员到期:</td><td>"+fccVo.getVipEndTime()+"</td>");
		s1.append("</tr></table>");
		hashMap.put("rcptClew7", s1.toString());
	}
	/**
	 * 查询车辆预约信息
	 * */
	
	public Msg bespeakClew(FrtReceptionVo freVo) throws Exception {
		Msg msg=new Msg();
		StringBuffer sb=new StringBuffer("SELECT fr.RESV_ID FROM frt_resevation fr ");
		sb.append(" WHERE  fr.FRTRESV_FLG="+Contstants.DELETE_TAG.DELETENO+" AND RESV_TO_RCPT="+Contstants.NEARCAR_TAG.NEARCARNO+"");
		sb.append(" AND fr.RESV_STATUS='"+Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING+"' AND fr.CAR_ID='"+freVo.getCarId()+"'");
		List<String> rows=new ArrayList();
		List list=frtReceptionDao.createSQLQuery(sb.toString());
		if(list!=null&&list.size()>0){
			for (Object obj : list) {
				rows.add(obj.toString());
			}			
			msg.setSuccess(true);
			msg.setObj(rows.toString());
		}else{
			msg.setSuccess(false);
		}
		return msg;
	}
	/**
	 * 完工分析查询
	 * */
	
	public Datagrid completeAnalyse(FrtReceptionVo freVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fr.RECEPTION_ID, fcar.CAR_LICENSE,fc.CUSTOM_NAME,rt.REPT_NAME,");
		sb.append(" fr.INTER_DATE,fr.RECEPTION_END_TIME,fr.RECEPTION_FACT_TIME");
		sb.append(" FROM frt_reception fr,frt_car fcar,frt_custom fc,reptype rt");
		sb.append(" WHERE fr.CAR_ID=fcar.CAR_ID AND fc.CUSTOM_ID=fr.CUSTOM_ID AND fr.RECEPTION_FACT_TIME!=''");
		sb.append(" AND rt.REPT_ID=fr.REPT_ID AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO);
		sb.append("  and  fr.enterprise_id="+freVo.getEnterpriseId()  );
		if(freVo.getReceptionId()!=null&&freVo.getReceptionId().length()>0)
			sb.append(" AND fr.RECEPTION_ID like '%"+StringEscapeUtils.escapeSql(freVo.getReceptionId().trim())+"%'");
		if(freVo.getCarLicense()!=null&&freVo.getCarLicense().length()>0)
			sb.append(" AND fcar.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(freVo.getCarLicense().trim())+"%'");
		if(freVo.getReceptionEndTimeBegin()!=null&&freVo.getReceptionEndTimeBegin().length()>0)
			sb.append(" AND fr.RECEPTION_END_TIME>='"+freVo.getReceptionEndTimeBegin()+"'");
		if(freVo.getReceptionEndTimeEnd()!=null&&freVo.getReceptionEndTimeEnd().length()>0)
			sb.append(" AND fr.RECEPTION_END_TIME<='"+freVo.getReceptionEndTimeEnd()+"'");
		if(freVo.getReceptionEndTimeBegin()==null && freVo.getReceptionEndTimeEnd()==null){
			BasCompanyInformationSet bcis=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,freVo.getEnterpriseId());
			int num=1;
			if(bcis!=null&&bcis.getCiValue()!=null&&bcis.getCiValue().length()>0){
				num=Integer.parseInt(bcis.getCiValue());
			}
			sb.append(" and DATE_FORMAT( fr.RECEPTION_END_TIME,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, num)+ "'");
			sb.append(" and DATE_FORMAT( fr.RECEPTION_END_TIME,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");	
		}
		List<FrtReceptionVo> rows=new ArrayList();
		List<Object[]> list=frtReceptionDao.createSQLQuery(sb.toString(),freVo.getPage(),freVo.getRows());
		FrtReceptionVo temp=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				temp=new FrtReceptionVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					temp.setReceptionId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					temp.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					temp.setCustomName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					temp.setReptName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					temp.setInterDate(MyBeanUtils.getInstance().formatString(obj[4].toString()));
				if(obj[5]!=null&&obj[5].toString().length()>0)
					temp.setReceptionEndTime(MyBeanUtils.getInstance().formatString(obj[5].toString()));
				if(obj[6]!=null&&obj[6].toString().length()>0)
					temp.setReceptionFactTime(MyBeanUtils.getInstance().formatString(obj[6].toString()));
				if(temp.getReceptionEndTime()!=null&&temp.getReceptionFactTime()!=null){
					temp.setDifferenceTime(((MyBeanUtils.getInstance().getDate(temp.getReceptionFactTime()).getTime()-
							MyBeanUtils.getInstance().getDate(temp.getReceptionEndTime()).getTime())/1000/60));
					if(temp.getDifferenceTime()<=0){
						temp.setFlag(true);
					}else{
						temp.setFlag(false);
					}
				}
				rows.add(temp);
			}
		int total=frtReceptionDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	
	/**
     * 前台接车接待员业务统计
     */
    public Json loadSearchReceptorWork(FrtReceptionVo frtReceptionVo)throws Exception{
    	return frtReceptionDao.loadSearchReceptorWork(frtReceptionVo);
    }

	/**
	 * 接车分部管理查询
	 * */
	
	public Datagrid rcptBranchDatagrid(FrtReceptionVo freVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fr.RECEPTION_ID,fcar.CAR_LICENSE,fc.CUSTOM_NAME,rt.REPT_NAME,aa.dataValue");
		sb.append(" FROM frt_reception fr INNER JOIN frt_car fcar ON fcar.CAR_ID=fr.CAR_ID");
		sb.append(" INNER JOIN frt_custom fc ON fc.CUSTOM_ID=fr.CUSTOM_ID");
		sb.append(" INNER JOIN reptype rt ON  rt.REPT_ID=fr.REPT_ID");
		sb.append(" LEFT OUTER JOIN ");
		sb.append(" (");
		sb.append(" SELECT bc.dataKey,bc.dataValue FROM bas_childdictionary bc,bas_parentdictionary bp");
		sb.append(" WHERE bp.parent_id=bc.parent_id AND bp.dataKey='"+Contstants.RCPTBRANCH.RCPTBRANCHKEY+"'"); 
		sb.append(" ) aa");
		sb.append(" ON fr.RCPT_BRANCH=aa.dataKey");
		sb.append(" WHERE  1=1 AND fr.RCPT_FLG="+Contstants.DELETE_TAG.DELETENO +" and  fr.enterprise_id="+freVo.getEnterpriseId());
		
		if(freVo.getReceptionId()!=null&&freVo.getReceptionId().length()>0)
			sb.append(" AND fr.RECEPTION_ID like '%"+StringEscapeUtils.escapeSql(freVo.getReceptionId().trim())+"%'");
		if(freVo.getCarLicense()!=null&&freVo.getCarLicense().length()>0)
			sb.append(" AND fcar.CAR_LICENSE like '%"+StringEscapeUtils.escapeSql(freVo.getCarLicense().trim())+"%'");
		List<FrtReceptionVo> rows=new ArrayList();
		FrtReceptionVo temp=null;
		List<Object[]> list=frtReceptionDao.createSQLQuery(sb.toString(), freVo.getPage(), freVo.getRows());
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				temp=new FrtReceptionVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					temp.setReceptionId(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					temp.setCarLicense(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					temp.setCustomName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					temp.setReptName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					temp.setRcptBranch(obj[4].toString());
				rows.add(temp);
			}
		int total=frtReceptionDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 *  接车分部管理修改
	 * */
	
	public Msg editRcptBranch(FrtReceptionVo freVo) throws Exception {
		Msg msg=new Msg();
		if(freVo.getIds()!=null&&freVo.getIds().length()>0){
			freVo.getRcptBranch();
			try {
				String[] ids=freVo.getIds().split(",");
				for (String string : ids) {
					FrtReception fr=frtReceptionDao.get(FrtReception.class,string);
					fr.setRcptBranch(freVo.getRcptBranch());
					frtReceptionDao.merge(fr);
				}
				msg.setMsg("接车分部修改成功！");
				msg.setSuccess(true);
			} catch (Exception e) {
				msg.setMsg("接车分部修改失败！");
			}
		}else{
			msg.setMsg("没有要修改的数据！");
		}
		return msg;
	}
	/**
	 * 设置前台预计完工时间
	 * */
	
	public String getSetFinishTime(FrtReceptionVo freVo) throws Exception {
		Date temp=MyBeanUtils.getInstance().getDate(freVo.getInterDate());
		Long ddate=Long.parseLong(frtService.getDefaultFinishTimes());
		Date date=new Date(temp.getTime()+(ddate*60*1000));
		return MyBeanUtils.getInstance().getString(date);
	}
	
}
