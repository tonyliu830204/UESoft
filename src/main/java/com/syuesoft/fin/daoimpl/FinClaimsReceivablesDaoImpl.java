package com.syuesoft.fin.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.dao.FinClaimsReceivablesDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.model.FinClaimsReceivables;
import com.syuesoft.util.FormatTime;

/**
 * 索赔应收款DaoImpl
 * 
 * @author SuMing
 */
@Repository("finClaimsReceivablesDao")
public class FinClaimsReceivablesDaoImpl extends
        BaseDaoImpl<FinClaimsReceivables> implements FinClaimsReceivablesDao
{
	@Autowired
	private BasCompanyInformationSetDAO  basCompanyInformationSetDAO;

    /**
     * 索赔应收款信息 预加载
     */
    
    public Datagrid loadFinClaimsReceivables(int page,
            int rows,String startDate,String endDate,int enterpriseId) throws Exception
    {
    	Datagrid d=new Datagrid();
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        String sql = "SELECT "
                + " bas_relation_campany.RELCAMP_NAME"
                + ",frt_reception.RECEPTION_ID"
                + ",frt_custom.CUSTOM_NAME"
                + ",frt_car.CAR_LICENSE"
                + ",fin_claimant_main.CLAIMANTM_ID"
                + ",fin_claimant_main.CLAIMANTM_TIME"
                + ",fin_claimant_main.CLAIMANTM_AMOUNT"
                + ",fin_claims_receivables.CR_RECEIVABLES"
                + ",fin_claimant_main.CLAIMANTM_INVOICE_NO"
                + ",bas_stuff.STF_NAME"
                + ",reptype.REPT_NAME"
                + ",frt_car.CAR_VIN"
                + ",bas_car_brand.CBRD_NAME"
                + ",frt_resevation.RESV_REAL_TIME"
                + " FROM"
                + " fin_claims_receivables,fin_claimant_main,frt_reception,frt_pre_clearing,"
                + " frt_custom,bas_stuff,reptype,frt_resevation,bas_relation_campany,frt_car"
                + " ,bas_car_style,bas_car_type,bas_car_brand"
                + " WHERE fin_claims_receivables.CLAIMANTM_ID=fin_claimant_main.CLAIMANTM_ID"
                + " AND fin_claimant_main.PRECLR_ID=frt_pre_clearing.PRECLR_ID"
                + " AND frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"
                + " AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"
                + " AND frt_reception.RECEPTOR=bas_stuff.STF_ID"
                + " AND frt_reception.REPT_ID=reptype.REPT_ID"
                + " AND frt_reception.RESV_ID=frt_resevation.RESV_ID"
                + " AND bas_relation_campany.RELCAMP_ID=frt_reception.FIN_COM_ID"
                + " AND frt_reception.CAR_ID=frt_car.CAR_ID"
                + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"
                + " AND frt_reception.REPT_ID=reptype.REPT_ID";
        if(startDate == null && endDate == null){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,enterpriseId).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sql+=" and DATE_FORMAT(fin_claimant_main.CLAIMANTM_TIME,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'";
        	}else{
        		sql+=" and DATE_FORMAT(fin_claimant_main.CLAIMANTM_TIME,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())+ "'";
        	}
    		
    		sql+=" and DATE_FORMAT(fin_claimant_main.CLAIMANTM_TIME,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'";
        }	
        int count = getSQLCount(sql, null);
        List<Object[]> resultList = createSQLQuery(sql, null, page, rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                MaintenanceReceivablesVo mrvo = new MaintenanceReceivablesVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    mrvo.setRelcampName(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setReceptionId(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setCustomName(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setReceLicense(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setClaimantmId(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setClaimantmTime(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setClaimantmAmount(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setCrReceivables(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo.setClaimantmInvoiceNo(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setStfName(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    mrvo.setReptName(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    mrvo.setCarVin(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    mrvo.setCbrdName(obj[12].toString());
                }
                if (obj[13] != null && !"".equals(obj[13]))
                {
                    mrvo.setResvRealTime(obj[13].toString());
                }
                list.add(mrvo);
            }
        }
        d.setRows(list);
        d.setTotal(count);
        return d;
    }

    /**
     * 索赔应收款信息 综合查询
     */
    @SuppressWarnings("unchecked")
    
    public List<MaintenanceReceivablesVo> searchFinClaimsReceivablesByCondition(
            String claimantmTimeStart, String claimantmTimeEnd,
            String claimantmAmount, String stfName, String resvRealTimeStart,
            String resvRealTimeEnd, String receptionId, String relcampName,
            String receLicense, String carVin, String cbrdName)
            throws Exception
    {
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT "
                        + " bas_relation_campany.RELCAMP_NAME"
                        + ",frt_reception.RECEPTION_ID"
                        + ",frt_custom.CUSTOM_NAME"
                        + ",frt_car.CAR_LICENSE"
                        + ",fin_claimant_main.CLAIMANTM_ID"
                        + ",fin_claimant_main.CLAIMANTM_TIME"
                        + ",fin_claimant_main.CLAIMANTM_AMOUNT"
                        + ",fin_claims_receivables.CR_RECEIVABLES"
                        + ",fin_claimant_main.CLAIMANTM_INVOICE_NO"
                        + ",bas_stuff.STF_NAME"
                        + ",reptype.REPT_NAME"
                        + ",frt_car.CAR_VIN"
                        + ",bas_car_brand.CBRD_NAME"
                        + ",frt_resevation.RESV_REAL_TIME"
                        + " FROM"
                        + " fin_claims_receivables,fin_claimant_main,frt_reception,"
                        + " frt_custom,bas_stuff,reptype,frt_resevation,bas_relation_campany,frt_car"
                        + " ,bas_car_style,bas_car_type,bas_car_brand,frt_pre_clearing"
                        + " WHERE fin_claims_receivables.CLAIMANTM_ID=fin_claimant_main.CLAIMANTM_ID"
                        + " AND fin_claimant_main.PRECLR_ID=frt_pre_clearing.PRECLR_ID"
                        + " AND frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"
                        + " AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_reception.RECEPTOR=bas_stuff.STF_ID"
                        + " AND frt_reception.REPT_ID=reptype.REPT_ID"
                        + " AND frt_reception.RESV_ID=frt_resevation.RESV_ID"
                        + " AND bas_relation_campany.RELCAMP_ID=frt_reception.FIN_COM_ID"
                        + " AND frt_reception.CAR_ID=frt_car.CAR_ID"
                        + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                        + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                        + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"
                        + " AND frt_reception.REPT_ID=reptype.REPT_ID");
        if (claimantmTimeStart != null && !"".equals(claimantmTimeStart))
        {
            if (claimantmTimeEnd != null && !"".equals(claimantmTimeEnd))
                sb.append(" AND fin_claimant_main.CLAIMANTM_TIME between '"
                        + claimantmTimeStart + "' and '" + claimantmTimeEnd
                        + "'");
            else
                sb.append(" AND fin_claimant_main.CLAIMANTM_TIME >= '"
                        + claimantmTimeStart + "'");
        }
        else
        {
            if (claimantmTimeEnd != null && !"".equals(claimantmTimeEnd))
                sb.append(" AND fin_claimant_main.CLAIMANTM_TIME<='"
                        + claimantmTimeEnd + "'");
        }
        if (claimantmAmount != null && !"".equals(claimantmAmount))
        {
            sb.append(" AND fin_claimant_main.CLAIMANTM_AMOUNT LIKE '%"
                    + StringEscapeUtils.escapeSql(claimantmAmount.trim()) + "%'");
        }
        if (stfName != null && !"".equals(stfName))
        {
            sb.append(" AND bas_stuff.STF_NAME='" + stfName + "'");
        }
        if (resvRealTimeStart != null && !"".equals(resvRealTimeStart))
        {
            if (resvRealTimeEnd != null && !"".equals(resvRealTimeEnd))
                sb
                        .append(" AND frt_resevation.RESV_REAL_TIME between '"
                                + resvRealTimeStart + "' and '"
                                + resvRealTimeEnd + "'");
            else
                sb.append(" AND frt_resevation.RESV_REAL_TIME >= '"
                        + resvRealTimeStart + "'");
        }
        else
        {
            if (resvRealTimeEnd != null && !"".equals(resvRealTimeEnd))
                sb.append(" AND frt_resevation.RESV_REAL_TIME <= '"
                        + resvRealTimeEnd + "'");
        }
        if (receptionId != null && !"".equals(receptionId))
        {
            sb.append(" AND frt_pre_clearing.RECEPTION_ID LIKE '%"
                    + StringEscapeUtils.escapeSql(receptionId.trim()) + "%'");
        }
        if (relcampName != null && !"".equals(relcampName))
        {
            sb.append(" AND bas_relation_campany.RELCAMP_NAME LIKE '%"
                    + StringEscapeUtils.escapeSql(relcampName.trim()) + "%'");
        }
        if (receLicense != null && !"".equals(receLicense))
        {
            sb.append(" AND frt_car.CAR_LICENSE LIKE '%" + StringEscapeUtils.escapeSql(receLicense.trim())
                    + "%'");
        }
        if (carVin != null && !"".equals(carVin))
        {
            sb.append(" AND frt_car.CAR_VIN LIKE '%" + StringEscapeUtils.escapeSql(carVin.trim()) + "%'");
        }
        if (cbrdName != null && !"".equals(cbrdName))
        {
            sb.append(" AND bas_car_brand.CBRD_NAME LIKE '%" + StringEscapeUtils.escapeSql(cbrdName.trim()) + "%'");
        }
        List resultList = createSQLQuery(sb.toString(), null);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                MaintenanceReceivablesVo mrvo = new MaintenanceReceivablesVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    mrvo.setRelcampName(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setReceptionId(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setCustomName(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setReceLicense(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setClaimantmId(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setClaimantmTime(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setClaimantmAmount(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setCrReceivables(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo.setClaimantmInvoiceNo(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setStfName(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    mrvo.setReptName(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    mrvo.setCarVin(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    mrvo.setCbrdName(obj[12].toString());
                }
                if (obj[13] != null && !"".equals(obj[13]))
                {
                    mrvo.setResvRealTime(obj[13].toString());
                }
                list.add(mrvo);
            }
        }
        return list;
    }
}
