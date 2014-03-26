package com.syuesoft.fin.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants.OTHERPARAMETER;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.dao.StSellPreclrMainDao;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.fin.vo.StSellPreclrVo;
import com.syuesoft.model.StSellPreclrMain;
import com.syuesoft.util.FormatTime;

/**
 * 销售结算单汇总dao
 * 
 * @author SuMing
 */
@Repository("stSellPreclrMainDao")
public class StSellPreclrMainDaoImpl extends BaseDaoImpl<StSellPreclrMain>
        implements StSellPreclrMainDao
{
	@Autowired
	private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    /**
     * 销售结算单汇总信息 预加载
     */
    public Datagrid loadStSellPreclrMain(int page, int rows,String start, String end,int enterpriseId)
            throws Exception{
    	Datagrid d=new Datagrid();
        List<StSellPreclrVo> list = new ArrayList<StSellPreclrVo>();
        StringBuffer sb =new StringBuffer("SELECT * FROM ("+
		" SELECT b.*,bas_childdictionary.dataValue FROM ("+
		" SELECT a.*,bas_stuff.STF_NAME FROM ("+
		" SELECT st_sell_preclr_main.*,frt_custom.CUSTOM_NAME FROM st_sell_preclr_main LEFT JOIN frt_custom"+
		" ON st_sell_preclr_main.CUSTOM_ID=frt_custom.CUSTOM_ID AND st_sell_preclr_main.STATE=0) AS a"+
		" LEFT JOIN bas_stuff ON a.MANAGER=bas_stuff.STF_ID) AS b"+
		" LEFT JOIN bas_childdictionary ON b.CLASSFICATION=bas_childdictionary.child_id"+
		")AS c where 1=1 ");
        if(start == null ){
        	String time=basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.OTHERPARAMETER,OTHERPARAMETER.BALANACCOUNTSECT,enterpriseId).getCiValue();
        	if(time!=null)
        		sb.append(" and DATE_FORMAT(PRECLR_DATE,'%Y-%m-%d')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(time))+ "'");
        }
		if(end == null)
			sb.append(" and DATE_FORMAT(PRECLR_DATE,'%Y-%m-%d')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString(), null, page, rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellPreclrVo sspvo = new StSellPreclrVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                    sspvo.setPreclrId(obj[0].toString());
                if (obj[1] != null && !"".equals(obj[1]))
                    sspvo.setPreclrDate(obj[1].toString().substring(0, 10));
                if (obj[1] != null && !"".equals(obj[1]))
                    sspvo.setTpreclrDate(obj[1].toString().substring(0, 19));
                if (obj[2] != null && !"".equals(obj[2]))
                    sspvo.setCerNo(obj[2].toString());
                if (obj[3] != null && !"".equals(obj[3]))
                    sspvo.setCustomId(obj[3].toString());
                if (obj[4] != null && !"".equals(obj[4]))
                    sspvo.setCustomName(obj[4].toString());
                if (obj[5] != null && !"".equals(obj[5]))
                    sspvo.setPreclrNum(obj[5].toString());
                if (obj[6] != null && !"".equals(obj[6]))
                    sspvo.setPreclrAmount(obj[6].toString());
                if (obj[7] != null && !"".equals(obj[7]))
                    sspvo.setPreclrCostAmount(obj[7].toString());
                if (obj[8] != null && !"".equals(obj[8]))
                    sspvo.setClassfication(obj[8].toString());
                if (obj[9] != null && !"".equals(obj[9]))
                    sspvo.setClassficationName(obj[9].toString());
                if (obj[10] != null && !"".equals(obj[10]))
                    sspvo.setManager(obj[10].toString());
                if (obj[11] != null && !"".equals(obj[11]))
                    sspvo.setManagerName(obj[11].toString());
                if (obj[12] != null && !"".equals(obj[12]))
                    sspvo.setRemark(obj[12].toString());
                if (obj[13] != null && !"".equals(obj[13]))
                    if (Boolean.parseBoolean(obj[13].toString()))
                        sspvo.setState("是");
                    else
                        sspvo.setState("否");
                list.add(sspvo);
            }
        }
        d.setRows(list);
        d.setTotal(count);
        return d;
    }

    /**
     * 销售结算单汇总信息 综合查询
     */
    public List<StSellPreclrVo> searchStSellPreclrMainByCondition(
            String preclrDateStart, String preclrDateEnd, String preclrId,
            String customId, String classfication, String cerNo, String state)
            throws Exception{
        List<StSellPreclrVo> list = new ArrayList<StSellPreclrVo>();
        StringBuffer sb =new StringBuffer("SELECT * FROM ("+
        		" SELECT b.*,bas_childdictionary.dataValue FROM ("+
        		" SELECT a.*,bas_stuff.STF_NAME FROM ("+
        		" SELECT st_sell_preclr_main.*,frt_custom.CUSTOM_NAME FROM st_sell_preclr_main LEFT JOIN frt_custom"+
        		" ON st_sell_preclr_main.CUSTOM_ID=frt_custom.CUSTOM_ID AND st_sell_preclr_main.STATE=0) AS a"+
        		" LEFT JOIN bas_stuff ON a.MANAGER=bas_stuff.STF_ID) AS b"+
        		" LEFT JOIN bas_childdictionary ON b.CLASSFICATION=bas_childdictionary.child_id"+
        		")AS c where 1=1 ");
        if (preclrDateStart != null && !"".equals(preclrDateStart))
            if (preclrDateEnd != null && !"".equals(preclrDateEnd))
                sb.append(" AND PRECLR_DATE between '"
                        + preclrDateStart + "' and '" + preclrDateEnd + "'");
            else
                sb.append(" AND PRECLR_DATE>='"
                        + preclrDateStart + "'");
        else if (preclrDateEnd != null && !"".equals(preclrDateEnd))
            sb.append(" AND PRECLR_DATE<='" + preclrDateEnd
                    + "'");
        if (preclrId != null && !"".equals(preclrId))
            sb.append(" AND PRECLR_ID LIKE '%" + StringEscapeUtils.escapeSql(preclrId.trim())
                    + "%'");
        if (customId != null && !"".equals(customId))
            sb.append(" AND CUSTOM_ID LIKE '%" + StringEscapeUtils.escapeSql(customId.trim())
                    + "%'");
        if (classfication != null && !"".equals(classfication))
            sb.append(" AND CLASSFICATION LIKE '%"
                    + StringEscapeUtils.escapeSql(classfication.trim()) + "%'");
        if (cerNo != null && !"".equals(cerNo))
            sb.append(" AND  CER_NO LIKE '%" + StringEscapeUtils.escapeSql(cerNo.trim())
                            + "%'");
        if (state != null && !"".equals(state))
            if (state.equals("是"))
                sb.append(" AND  STATE=1");
            else
                sb.append(" AND  STATE=0");
        List<Object[]> resultList = createSQLQuery(sb.toString(), null);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellPreclrVo sspvo = new StSellPreclrVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    sspvo.setPreclrId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    String date = obj[1].toString();
                    date = date.substring(0, 10);
                    sspvo.setPreclrDate(date);
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    sspvo.setTpreclrDate(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    sspvo.setCerNo(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    sspvo.setCustomId(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    sspvo.setCustomName(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    sspvo.setPreclrNum(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    sspvo.setPreclrAmount(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    sspvo.setPreclrCostAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    sspvo.setClassfication(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    sspvo.setClassficationName(obj[9].toString());
                }
                if (obj[10] != null && !"".equals(obj[10]))
                {
                    sspvo.setManager(obj[10].toString());
                }
                if (obj[11] != null && !"".equals(obj[11]))
                {
                    sspvo.setManagerName(obj[11].toString());
                }
                if (obj[12] != null && !"".equals(obj[12]))
                {
                    sspvo.setRemark(obj[12].toString());
                }
                if (obj[13] != null && !"".equals(obj[13]))
                    if (Boolean.parseBoolean(obj[13].toString()))
                        sspvo.setState("是");
                    else
                        sspvo.setState("否");
                list.add(sspvo);
            }
        }
        return list;
    }

    /**
     * 销售应收款模块 结算单信息 预加载
     */
    public List<StSellChargeVo> load_ssc_StSellreclr() throws Exception
    {
        List<StSellChargeVo> list = new ArrayList<StSellChargeVo>();
        StringBuffer sql = new StringBuffer(
                "SELECT st_sell_preclr_main.PRECLR_ID"
                        + ",st_sell_preclr_main.PRECLR_AMOUNT"
                        + ",frt_custom.CUSTOM_NAME"
                        + ",bas_childdictionary.dataValue,"
                        + "st_sell_charge.CHARGE_ID"
                        + ",st_sell_charge.PRECLR_ID"
                        + ",st_sell_charge.TOTAL_AMOUNT"
                        + ",st_sell_charge.PAID_AMOUNT"
                        + ",st_sell_charge.VERIFY_DATE"
                        + ",st_sell_charge.REMARK"
                        + " FROM st_sell_preclr_main,frt_custom,bas_childdictionary,st_sell_charge"
                        + " WHERE st_sell_preclr_main.CUSTOM_ID=frt_custom.CUSTOM_ID"
                        + " AND bas_childdictionary.child_id=st_sell_preclr_main.CLASSFICATION"
                        + " AND st_sell_preclr_main.PRECLR_ID=st_sell_charge.PRECLR_ID");
        List<Object[]> resultList = createSQLQuery(sql.toString(), null);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                StSellChargeVo sscvo = new StSellChargeVo();
                obj = resultList.get(i);
                if (obj[0] != null && !"".equals(obj[0]))
                {
                    sscvo.setPreclrId(obj[0].toString());
                }
                if (obj[1] != null && !"".equals(obj[1]))
                {
                    sscvo.setPreclrAmount(obj[1].toString());
                }
                if (obj[2] != null && !"".equals(obj[2]))
                {
                    sscvo.setCustomName(obj[2].toString());
                }
                if (obj[3] != null && !"".equals(obj[3]))
                {
                    sscvo.setClassfication(obj[3].toString());
                }
                if (obj[4] != null && !"".equals(obj[4]))
                {
                    sscvo.setChargeId(obj[4].toString());
                }
                if (obj[5] != null && !"".equals(obj[5]))
                {
                    sscvo.setPreclrId(obj[5].toString());
                }
                if (obj[6] != null && !"".equals(obj[6]))
                {
                    sscvo.setTotalAmount(obj[6].toString());
                }
                if (obj[7] != null && !"".equals(obj[7]))
                {
                    sscvo.setPaidAmount(obj[7].toString());
                }
                if (obj[8] != null && !"".equals(obj[8]))
                {
                    sscvo.setVerifyDate(obj[8].toString());
                }
                if (obj[9] != null && !"".equals(obj[9]))
                {
                    sscvo.setRemark(obj[9].toString());
                }
                list.add(sscvo);
            }
        }
        return list;
    }

}
