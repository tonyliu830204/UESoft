package com.syuesoft.fin.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.dao.StSellChargeDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.model.StSellCharge;
import com.syuesoft.util.FormatTime;

@Repository("stSellChargeDao")
public class StSellChargeDaoImpl extends BaseDaoImpl<StSellCharge> implements
        StSellChargeDao
{
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    /**
     * 加载销售应收款信息
     */
    public Datagrid loadStSellCharge(int page, int rows,String startTime,String endTime,int enterpriseId)
            throws Exception
    {
    	Datagrid d=new Datagrid();
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        StringBuffer sb = new StringBuffer(
                " SELECT "
                        + "st_sell_charge.CHARGE_ID,"
                        + "frt_custom.CUSTOM_NAME,"
                        + "st_sell_order.SELLMM_ID,"
                        + "st_sell_order.SELLMM_DATE,"
                        + "st_sell_order.CAR_LICENSE,"
                        + "bas_car_brand.CBRD_NAME,"
                        + "frt_car.CAR_VIN,"
                        + "st_sell_order.SELLMM_SUM_AMOUNT,"
                        + "st_sell_preclr_main.PRECLR_AMOUNT,"
                        + "st_sell_charge.PAID_AMOUNT"
                        + " FROM st_sell_charge,st_sell_preclr_main,st_sell_order,frt_custom,frt_car,"
                        + " bas_car_style,bas_car_type,bas_car_brand"
                        + " WHERE st_sell_charge.PRECLR_ID=st_sell_preclr_main.PRECLR_ID"
                        + " AND st_sell_preclr_main.CER_NO=st_sell_order.SELLMM_ID"
                        + " AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_car.CAR_LICENSE=st_sell_order.CAR_LICENSE"
                        + " AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                        + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                        + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID");
        if(startTime == null  && endTime == null){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,enterpriseId).getCiValue();
        	if(time!=null && !("".equals(time))){
        		sb.append(" and DATE_FORMAT(st_sell_preclr_main.PRECLR_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        	}else{
        		sb.append(" and DATE_FORMAT(st_sell_preclr_main.PRECLR_DATE,'%Y-%m-%d')>='"+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ "'");
        	}
    		sb.append(" and DATE_FORMAT(st_sell_preclr_main.PRECLR_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }	
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString(), null, page,
                rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                MaintenanceReceivablesVo mrvo = new MaintenanceReceivablesVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    mrvo.setChargeId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setCustomName(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setSellmmId(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setSellmmDate(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setCarLicense(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setCarBrand(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setCarVin(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setSellmmSumAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo.setPreclrSumAmount(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setPaidAmount(obj[9].toString());
                }
                list.add(mrvo);
            }
        }
        d.setRows(list);
        d.setTotal(count);
        return d;
    }

    /**
     * 销售应收款信息 综合查询
     * 
     * @throws Exception
     * @throws Exception
     */
    public List<MaintenanceReceivablesVo> searchStSellChargeByCondition(
            String sellmmDateStart, String sellmmDateEnd,
            String preclrSumAmount, String carBrand, String preclrDateStart,
            String preclrDateEnd, String cerNo, String customName,
            String carLicense, String carVin) throws Exception
    {
        List<MaintenanceReceivablesVo> list = new ArrayList<MaintenanceReceivablesVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT "
                        + "st_sell_charge.CHARGE_ID,"
                        + "frt_custom.CUSTOM_NAME,"
                        + "st_sell_order.SELLMM_ID,"
                        + "st_sell_order.SELLMM_DATE,"
                        + "st_sell_order.CAR_LICENSE,"
                        + "bas_car_brand.CBRD_NAME,"
                        + "frt_car.CAR_VIN,"
                        + "st_sell_order.SELLMM_SUM_AMOUNT,"
                        + "st_sell_preclr_main.PRECLR_AMOUNT,"
                        + "st_sell_charge.PAID_AMOUNT"
                        + ",st_sell_preclr_main.PRECLR_DATE"
                        + " FROM st_sell_charge,st_sell_preclr_main,st_sell_order,frt_custom,frt_car,"
                        + " bas_car_style,bas_car_type,bas_car_brand"
                        + " WHERE st_sell_charge.PRECLR_ID=st_sell_preclr_main.PRECLR_ID"
                        + " AND st_sell_preclr_main.CER_NO=st_sell_order.SELLMM_ID"
                        + " AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_car.CAR_LICENSE=st_sell_order.CAR_LICENSE"
                        + " AND st_sell_order.SELLCUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND frt_car.CAR_CSTL_ID=bas_car_style.CAR_CSTL_NAME"
                        + " AND bas_car_style.CTYPE_ID=bas_car_type.CTYPE_ID"
                        + " AND bas_car_type.CBRD_ID=bas_car_brand.CBRD_ID");
        if (sellmmDateStart != null && !"".equals(sellmmDateStart))
        {
            if (sellmmDateEnd != null && !"".equals(sellmmDateEnd))
                sb.append(" AND st_sell_order.SELLMM_DATE between '"
                        + sellmmDateStart + "' and '" + sellmmDateEnd + "'");
            else
                sb.append(" AND  st_sell_order.SELLMM_DATE >= '"
                        + sellmmDateStart + "'");
        }
        else
        {
            if (sellmmDateEnd != null && !"".equals(sellmmDateEnd))
                sb.append(" AND  st_sell_order.SELLMM_DATE <= '"
                        + sellmmDateEnd + "'");
        }
        if (preclrSumAmount != null && !"".equals(preclrSumAmount))
        {
            sb.append(" AND st_sell_preclr_main.PRECLR_AMOUNT like '%"
                    + StringEscapeUtils.escapeSql(preclrSumAmount.trim()) + "%'");
        }
        if (carBrand != null && !"".equals(carBrand))
        {
            sb.append(" AND bas_car_brand.CBRD_NAME like '%" + StringEscapeUtils.escapeSql(carBrand.trim()) + "%'");
        }
        if (preclrDateStart != null && !"".equals(preclrDateStart))
        {
            if (preclrDateEnd != null && !"".equals(preclrDateEnd))
                sb.append(" AND st_sell_preclr_main.PRECLR_DATE  between '"
                        + preclrDateStart + "' and '" + preclrDateEnd + "'");
            else
                sb.append(" AND st_sell_preclr_main.PRECLR_DATE >='"
                        + preclrDateStart + "'");
        }
        else
        {
            if (preclrDateEnd != null && !"".equals(preclrDateEnd))
                sb.append(" AND st_sell_preclr_main.PRECLR_DATE <='"
                        + preclrDateEnd + "'");
        }
        if (cerNo != null && !"".equals(cerNo))
        {
            sb.append(" AND st_sell_charge.CER_NO like '%" + StringEscapeUtils.escapeSql(cerNo.trim()) + "%'");
        }
        if (customName != null && !"".equals(customName))
        {
            sb
                    .append(" AND frt_custom.CUSTOM_NAME like '%" + StringEscapeUtils.escapeSql(customName.trim())
                            + "%'");
        }
        if (carLicense != null && !"".equals(carLicense))
        {
            sb.append(" AND st_sell_order.CAR_LICENSE like '%" + StringEscapeUtils.escapeSql(carLicense.trim())
                    + "%'");
        }
        if (carVin != null && !"".equals(carVin))
        {
            sb.append(" AND frt_car.CAR_VIN like '%" + StringEscapeUtils.escapeSql(carVin.trim()) + "%'");
        }
        List<Object[]> resultList = createSQLQuery(sb.toString(), null);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                MaintenanceReceivablesVo mrvo = new MaintenanceReceivablesVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    mrvo.setChargeId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    mrvo.setCustomName(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    mrvo.setSellmmId(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    mrvo.setSellmmDate(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    mrvo.setCarLicense(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    mrvo.setCarBrand(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    mrvo.setCarVin(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    mrvo.setSellmmSumAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    mrvo.setPreclrSumAmount(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    mrvo.setPaidAmount(obj[9].toString());
                }
                list.add(mrvo);
            }
        }
        return list;
    }

    /**
     * 销售营收款汇总信息 预加载
     */
    public List<StSellChargeVo> loadsStSellCharge(int page, int rows)
            throws Exception
    {
        List<StSellChargeVo> list = new ArrayList<StSellChargeVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT st_sell_charge.* "
                        + ",frt_custom.CUSTOM_NAME"
                        + ",bas_childdictionary.dataValue"
                        + " FROM st_sell_charge,st_sell_preclr_main,frt_custom,bas_childdictionary"
                        + " WHERE st_sell_charge.PRECLR_ID=st_sell_preclr_main.PRECLR_ID"
                        + " AND st_sell_preclr_main.CUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND st_sell_preclr_main.CLASSFICATION=bas_childdictionary.child_id"
                        + " AND st_sell_charge.state=1");
        int count = getSQLCount(sb.toString(), null);
        if (count > 0)
        {
            HttpSession sesion = ServletActionContext.getRequest().getSession();
            sesion.setAttribute("ssc_StSellCharge_resultSize", count);
        }
        List<Object[]> resultList = createSQLQuery(sb.toString(), null, page,
                rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellChargeVo StSellChargeVo = new StSellChargeVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    StSellChargeVo.setChargeId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    StSellChargeVo.setPreclrId(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    if (obj[2].toString().equals("1"))
                    {
                        StSellChargeVo.setStockOut("是");
                    }
                    else
                    {
                        StSellChargeVo.setStockOut("否");
                    }
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    StSellChargeVo.setPaidAmount(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    if (Double.parseDouble(obj[3].toString()) < Double
                            .parseDouble(obj[4].toString()))
                    {
                        StSellChargeVo.setTotalAmount(obj[3].toString());
                    }
                    else
                    {
                        StSellChargeVo.setTotalAmount(obj[4].toString());
                    }
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    StSellChargeVo.setPaidBalance(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    StSellChargeVo.setVerifyState(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    StSellChargeVo.setVerifyDate(obj[7].toString().substring(0,
                            19));
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    StSellChargeVo.setRemark(obj[8].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    StSellChargeVo.setCustomName(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    StSellChargeVo.setClassfication(obj[11].toString());
                }
                list.add(StSellChargeVo);
            }
        }
        return list;
    }

    /**
     * 销售营收款汇总信息 综合查询
     */
    public List<StSellChargeVo> searchStSellChargeByCondition(
            String verifyDate, String chargeId, String preclrId,
            String stockOut, String verifyState) throws Exception
    {
        List<StSellChargeVo> list = new ArrayList<StSellChargeVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT st_sell_charge.* "
                        + ",frt_custom.CUSTOM_NAME"
                        + ",bas_childdictionary.dataValue"
                        + " FROM st_sell_charge,st_sell_preclr_main,frt_custom,bas_childdictionary"
                        + " WHERE st_sell_charge.PRECLR_ID=st_sell_preclr_main.PRECLR_ID"
                        + " AND st_sell_preclr_main.CUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND st_sell_preclr_main.CLASSFICATION=bas_childdictionary.child_id");
        if (verifyDate != null && !"".equals(verifyDate))
        {
            sb.append(" AND st_sell_charge.VERIFY_DATE='"
                    + verifyDate.substring(0, 10) + "'");
        }
        if (chargeId != null && !"".equals(chargeId))
        {
            sb.append(" AND st_sell_charge.CHARGE_ID='" + chargeId + "'");
        }
        if (preclrId != null && !"".equals(preclrId))
        {
            sb.append(" AND st_sell_charge.PRECLR_ID='" + preclrId + "'");
        }
        if (stockOut != null && !"".equals(stockOut))
        {
            if (stockOut.equals("56"))
            {
                sb.append(" AND st_sell_charge.STOCK_OUT=1");
            }
            else
            {
                sb.append(" AND st_sell_charge.STOCK_OUT=0");
            }
        }
        if (verifyState != null && !"".equals(verifyState))
        {
            sb.append(" AND st_sell_charge.VERIFY_STATE='" + verifyState + "'");
        }
        List<Object[]> resultList = createSQLQuery(sb.toString(), null);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellChargeVo StSellChargeVo = new StSellChargeVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    StSellChargeVo.setChargeId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    StSellChargeVo.setPreclrId(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    if (obj[2].toString().equals("1"))
                    {
                        StSellChargeVo.setStockOut("是");
                    }
                    else
                    {
                        StSellChargeVo.setStockOut("否");
                    }
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    StSellChargeVo.setPaidAmount(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    if (Double.parseDouble(obj[3].toString()) < Double
                            .parseDouble(obj[4].toString()))
                    {
                        StSellChargeVo.setTotalAmount(obj[3].toString());
                    }
                    else
                    {
                        StSellChargeVo.setTotalAmount(obj[4].toString());
                    }
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    StSellChargeVo.setPaidBalance(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    StSellChargeVo.setVerifyState(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    StSellChargeVo.setVerifyDate(obj[7].toString().substring(0,
                            19));
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    StSellChargeVo.setRemark(obj[8].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    StSellChargeVo.setCustomName(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    StSellChargeVo.setClassfication(obj[11].toString());
                }
                list.add(StSellChargeVo);
            }
        }
        return list;
    }
}
