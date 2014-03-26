package com.syuesoft.fin.daoimpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.dao.FinMaintenanceReceivablesDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.DayBusinessVo;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.model.FinMaintenanceReceivables;
import com.syuesoft.util.FormatTime;

/**
 * 维修应收款DaoImpl
 * 
 * @author SuMing
 */
@Repository("finMaintenanceReceivablesDao")
public class FinMaintenanceReceivablesDaoImpl extends
        BaseDaoImpl<FinMaintenanceReceivables> implements
        FinMaintenanceReceivablesDao
{	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;

    /**
     * 维修应收款信息 预加载
     */
    public Datagrid loadFinMaintenanceReceivables(
            int page, int rows,String startTime,String endTime,int enterpriseId) throws Exception
    {
    	Datagrid d=new Datagrid();
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        String sql = "SELECT "
                + "fin_maintenance_receivables.MR_ID,"
                + "frt_custom.CUSTOM_NAME,"
                + "frt_pre_clearing.RECEPTION_ID,"
                + "frt_resevation.RESV_REAL_TIME,"
                + "frt_car.CAR_LICENSE,"
                + "frt_pre_clearing.PRECLR_ID,"
                + "frt_pre_clearing.PRECLR_TIME,"
                + "frt_pre_clearing.PRECLR_SUM_AMOUNT,"
                + "fin_maintenance_receivables.MR_RECEIVABLES,"
                + "frt_pre_clearing.PRECLR_NO,"
                + "bas_stuff.STF_ID,"
                + "bas_stuff.STF_NAME,"
                + "reptype.REPT_NAME,"
                + "frt_car.CAR_VIN,"
                + "bas_car_brand.CBRD_NAME"
                + " FROM fin_maintenance_receivables,frt_pre_clearing,"
                + " frt_reception,bas_stuff,frt_resevation,frt_custom,frt_car,bas_car_style,"
                + " bas_car_type,bas_car_brand,reptype"
                + " WHERE fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID"
                + " AND frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"
                + " AND bas_stuff.STF_ID=frt_reception.RECEPTOR"
                + " AND frt_resevation.RESV_ID=frt_reception.RESV_ID"
                + " AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"
                + " AND frt_reception.CAR_ID=frt_car.CAR_ID"
                + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"
                + " AND frt_reception.REPT_ID=reptype.REPT_ID";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(startTime == null  && endTime == null){
        	String  time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,enterpriseId).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sql+=" and DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'";
        	}else{
        		sql+=" and DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d')>='"+sdf.format(new java.util.Date()) + "'";
        	}
        	sql+=" and DATE_FORMAT(frt_pre_clearing.PRECLR_TIME,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'";
        }
        int count = getSQLCount(sql, null);
        List<Object[]> resultList = createSQLQuery(sql, null, page, rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                MaintenanceReceivablesVo mrvo = new MaintenanceReceivablesVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    mrvo.setMrId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setCustomName(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setReceptionId(obj[3].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setResvRealTime(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setCarLicense(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setPreclrId(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setPreclrTime(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setPreclrSumAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo
                            .setMrReceivables(Double.parseDouble(obj[8]
                                    .toString()));
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setPreclrNo(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    mrvo.setStfId(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    mrvo.setStfName(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    mrvo.setReptName(obj[12].toString());
                }
                if (obj[13] != null && !"".equals(obj[13]))
                {
                    mrvo.setCarVin(obj[13].toString());
                }
                if (obj[14] != null && !"".equals(obj[14]))
                {
                    mrvo.setCarBrand(obj[14].toString());
                }
                list.add(mrvo);
            }
        }
        d.setRows(list);
        d.setTotal(count);
        return d;
    }

    /**
     * 维修应收款信息 综合查询
     */
    @SuppressWarnings("unchecked")
    public List<MaintenanceReceivablesVo> searchFinMaintenanceReceivablesByCondition(
            String preclrTimeStart, String preclrTimeEnd,
            String preclrSumAmount, String preFlg, String stfId,
            String resvRealTimeStart, String resvRealTimeEnd,
            String receptionId, String customName, String carLicense,
            String carVin, String carBrand) throws Exception
    {
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT "
                        + "fin_maintenance_receivables.MR_ID,"
                        + "frt_custom.CUSTOM_NAME,"
                        + "frt_pre_clearing.RECEPTION_ID,"
                        + "frt_resevation.RESV_REAL_TIME,"
                        + "frt_car.CAR_LICENSE,"
                        + "frt_pre_clearing.PRECLR_ID,"
                        + "frt_pre_clearing.PRECLR_TIME,"
                        + "frt_pre_clearing.PRECLR_SUM_AMOUNT,"
                        + "fin_maintenance_receivables.MR_RECEIVABLES,"
                        + "frt_pre_clearing.PRECLR_NO,"
                        + "bas_stuff.STF_ID,"
                        + "bas_stuff.STF_NAME,"
                        + "reptype.REPT_NAME,"
                        + "frt_car.CAR_VIN,"
                        + "bas_car_brand.CBRD_NAME"
                        + " FROM fin_maintenance_receivables,frt_pre_clearing,"
                        + "frt_reception"
                        + ",bas_stuff,frt_resevation,frt_custom,frt_car,bas_car_style,"
                        + "bas_car_type,bas_car_brand,reptype"
                        + " WHERE fin_maintenance_receivables.PRECLR_ID=frt_pre_clearing.PRECLR_ID"
                        + " AND frt_pre_clearing.RECEPTION_ID=frt_reception.RECEPTION_ID"
                        + " AND bas_stuff.STF_ID=frt_reception.RECEPTOR"
                        + " AND frt_resevation.RESV_ID=frt_reception.RESV_ID"
                        + " AND frt_reception.CUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_reception.CAR_ID=frt_car.CAR_ID"
                        + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                        + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                        + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID"
                        + " AND frt_reception.REPT_ID=reptype.REPT_ID");
        if (preclrTimeStart != null && !"".equals(preclrTimeStart))
        {
            if (preclrTimeEnd != null && !"".equals(preclrTimeEnd))
                sb.append(" AND frt_pre_clearing.PRECLR_TIME between '"
                        + preclrTimeStart + "' and '" + preclrTimeEnd + "' ");
            else
                sb.append(" AND frt_pre_clearing.PRECLR_TIME >='"
                        + preclrTimeStart + "'");
        }
        else
        {
            if (preclrTimeEnd != null && !"".equals(preclrTimeEnd))
                sb.append(" AND frt_pre_clearing.PRECLR_TIME <='"
                        + preclrTimeEnd + "'");
        }
        if (preclrSumAmount != null && !"".equals(preclrSumAmount))
        {
            sb.append(" AND frt_pre_clearing.PRECLR_SUM_AMOUNT LIKE '%"
                    + StringEscapeUtils.escapeSql(preclrSumAmount.trim()) + "%'");
        }
        if (preFlg != null && !"".equals(preFlg))
        {
            sb.append(" AND bas_childdictionary.dataValue LIKE '%" + StringEscapeUtils.escapeSql(preFlg.trim())
                    + "%'");
        }
        if (stfId != null && !"".equals(stfId))
        {
            sb.append(" AND bas_stuff.STF_ID='" + stfId + "'");
        }
        if (resvRealTimeStart != null && !"".equals(resvRealTimeStart))
        {
            if (resvRealTimeEnd != null && !"".equals(resvRealTimeEnd))
                sb
                        .append(" AND frt_resevation.RESV_REAL_TIME between '"
                                + resvRealTimeStart + "' and '"
                                + resvRealTimeEnd + "'");
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
        if (customName != null && !"".equals(customName))
        {
            sb
                    .append(" AND frt_custom.CUSTOM_NAME LIKE '%" + StringEscapeUtils.escapeSql(customName.trim())
                            + "%'");
        }
        if (carLicense != null && !"".equals(carLicense))
        {
            sb.append(" AND frt_reception.RECE_LICENSE LIKE '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%'");
        }
        if (carVin != null && !"".equals(carVin))
        {
            sb.append(" AND frt_reception.RECE_VIN LIKE '%" + StringEscapeUtils.escapeSql(carVin.trim()) + "%'");
        }
        if (carBrand != null && !"".equals(carBrand))
        {
            sb.append(" AND bas_car_brand.CBRD_NAME LIKE '%" + StringEscapeUtils.escapeSql(carBrand.trim()) + "%'");
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
                    mrvo.setMrId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setCustomName(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setReceptionId(obj[3].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setResvRealTime(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setCarLicense(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setPreclrId(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setPreclrTime(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setPreclrSumAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo
                            .setMrReceivables(Double.parseDouble(obj[8]
                                    .toString()));
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setPreclrNo(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    mrvo.setStfId(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    mrvo.setStfName(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    mrvo.setReptName(obj[12].toString());
                }
               /* if (obj[13] != null && !"".equals(obj[13]))
                {
                    mrvo.setPreFlg(obj[13].toString());
                }*/
                if (obj[13] != null && !"".equals(obj[13]))
                {
                    mrvo.setCarVin(obj[13].toString());
                }
                if (obj[14] != null && !"".equals(obj[14]))
                {
                    mrvo.setCarBrand(obj[14].toString());
                }
                list.add(mrvo);
            }
        }
        return list;
    }

}
