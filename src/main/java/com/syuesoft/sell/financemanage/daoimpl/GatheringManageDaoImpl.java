package com.syuesoft.sell.financemanage.daoimpl;

import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.financemanage.dao.GatheringManageDao;
import com.syuesoft.sell.financemanage.vo.GatheringManageVo;
import com.syuesoft.sell.model.XsSellCollections;
import com.syuesoft.sell.model.XsSellCollectionsDetail;
import com.syuesoft.sell.model.XsSellReceivablesDetail;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.util.SystemUser;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Repository("GatheringManageDao")
public class GatheringManageDaoImpl extends BaseDaoImpl implements GatheringManageDao {
    @Autowired
    private BasCompanyInformationSetDAO basCompanyInformationSetDAO;


    /**
     * 预收款作业----获取预收款汇总记录
     */
    @SuppressWarnings("unchecked")

    public Json findBillInfor(final GatheringManageVo gatheringManageVo, BaseTagDAO baseTagDAO)
            throws Exception {
        StringBuffer sql1 = new StringBuffer("SELECT  a.reserve_code,  a.reserve_dete, b.xs_custom_name,  c.xs_car_code,");
        sql1.append(" a.pact_code,  d.xs_model_name,  a.car_color,");
        sql1.append(" (SELECT datavalue FROM xs_childdictionary n WHERE n.child_id = a.car_color) AS  car_color2,");
        sql1.append(" c.xs_car_vin_number,  c.xs_car_ocn,   a.STF_ID,");
        sql1.append(" (SELECT j.stf_name FROM bas_stuff j WHERE a.STF_ID = j.stf_ID) AS STF_ID2,");
        sql1.append(" a.custom_id,  a.xs_car_id,  a.payment_money,a.reserve_Id,");
        sql1.append(" e.account_CUMULATIVE ,");
        sql1.append(" a.payment_money");
        sql1.append(" FROM xs_sell_car_reserve a  ");
        sql1.append(" LEFT JOIN xs_custom_info b  ON a.custom_id = b.custom_id");
        sql1.append(" LEFT JOIN xs_car_info c  ON a.xs_car_id = c.xs_car_id");
        sql1.append(" LEFT JOIN xs_car_model d  ON a.car_model = d.xs_model_id ");
        sql1.append(" LEFT JOIN xs_sell_collections e  ON e.account_id=a.reserve_code ");
        sql1.append(" LEFT JOIN bas_stuff f  ON a.STF_ID=f.stf_id ");
        sql1.append(" WHERE 1 = 1 and a.del_state<> 1 ");
        sql1.append(" AND A.RESERVE_ID NOT IN (SELECT RESERVE_ID FROM  XS_CAR_SELL_INFO  WHERE RESERVE_ID IS NOT NULL) ");//不在销售记录中的
        StringBuffer sql = new StringBuffer();
        sql.append(sql1);
        //企业编号/
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            sql.append(" and a.enterprise_Id = " + StringEscapeUtils.escapeSql(gatheringManageVo.getEnterpriseId() + ""));
        }
        if (gatheringManageVo.getReserve_Code() != null && !gatheringManageVo.getReserve_Code().equals("")) {
            sql.append(" and a.reserve_code like '%" + StringEscapeUtils.escapeSql(gatheringManageVo.getReserve_Code().trim()) + "%'");
        }
        if (gatheringManageVo.getXs_Custom_Name() != null && !gatheringManageVo.getXs_Custom_Name().equals("")) {
            sql.append(" and b.xs_custom_name like '%" + StringEscapeUtils.escapeSql(gatheringManageVo.getXs_Custom_Name().trim()) + "%'");
        }
        if (gatheringManageVo.getReserve_Date() != null && !"".equals(gatheringManageVo.getReserve_Date())) {
            sql.append(" and DATE(a.reserve_dete)>='" + gatheringManageVo.getReserve_Date() + "'");
        }
        if (gatheringManageVo.getReserve_Date2() != null && !"".equals(gatheringManageVo.getReserve_Date2())) {
            sql.append(" and DATE(a.reserve_dete)<='" + gatheringManageVo.getReserve_Date2() + "'");
        }
        if ((gatheringManageVo.getReserve_Date() == null
                || gatheringManageVo.getReserve_Date().equals(""))
                && (gatheringManageVo.getReserve_Date2() == null
                || gatheringManageVo.getReserve_Date2().equals(""))) {
            sql.append(" and DATE(a.reserve_dete) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");

        }
        if (gatheringManageVo.getPage() != 0 && gatheringManageVo.getRows() != 0) {
            sql.append(" limit " + gatheringManageVo.getRows() * (gatheringManageVo.getPage() - 1) + "," + gatheringManageVo.getRows() + "");
        }

        ResourceBundle data = PropertyResourceBundle.getBundle("jdbc");

        String url = data.getString("connection.url");
        String user = data.getString("connection.username");
        String pwd = data.getString("connection.password");
        String driver = data.getString("connection.driver_class");

        Class.forName(driver);
        Connection conn = (Connection) DriverManager.getConnection(url, user, pwd);
        PreparedStatement P = conn.prepareStatement(sql.toString());
        ResultSet rs = P.executeQuery();

        List list = new ArrayList();
        while (rs.next()) {
            GatheringManageVo vo = new GatheringManageVo();
            if (rs.getString(1) != null) {
                vo.setReserve_Code(rs.getString(1) + "");
            }
            if (rs.getString(2) != null) {
                vo.setReserve_Date(rs.getString(2) + "");
            }
            if (rs.getString(3) != null) {
                vo.setXs_Custom_Name(rs.getString(3) + "");
            }
            if (rs.getString(4) != null) {
                vo.setXs_Car_Code(rs.getString(4) + "");
            }
            if (rs.getString(5) != null) {
                vo.setPact_Code(rs.getString(5) + "");
            }
            if (rs.getString(6) != null) {
                vo.setXs_Model_Name(rs.getString(6) + "");
            }
            if (rs.getString(7) != null) {
                vo.setCar_Color(rs.getString(7) + "");
            }
            if (rs.getString(8) != null) {
                vo.setCar_Color2(rs.getString(8) + "");
            }
            if (rs.getString(9) != null) {
                vo.setXs_Car_Vin_Number(rs.getString(9) + "");
            }
            if (rs.getString(10) != null) {
                vo.setXs_Car_Ocn(rs.getString(10) + "");
            }

            if (rs.getString(11) != null) {
                vo.setStf_Id(rs.getString(11) + "");
            }
            if (rs.getString(12) != null) {
                vo.setStf_Name(rs.getString(12) + "");
            }

            if (rs.getString(13) != null) {
                vo.setCustom_Id(rs.getString(13) + "");
            }
            if (rs.getString(14) != null) {
                vo.setXs_Car_Id(rs.getString(14) + "");
            }
            if (rs.getString(15) != null) {
                vo.setPayment_Money(rs.getString(15) + "");
            }//
            if (rs.getString(16) != null) {
                vo.setReserve_Id(rs.getString(16) + "");
            }
            vo.setAccount_Cumulative(rs.getString(17) != null ? rs.getString(17) : "0.00");
            if (rs.getString(18) != null) {
                vo.setPayment_Money(rs.getString(18) + "");
            }
            list.add(vo);
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(getCountBySQL(sql1.toString()));
        return json;
    }


    /**
     * 获取应收款信息
     */

    public Json findShouldAccountInfor(GatheringManageVo gatheringManageVo, BaseTagDAO baseTagDAO)
            throws Exception {
        String hql = "SELECT a.Account_Code,a.Account_Date,a.Account_Amount,a.Account_Arrears,DATEDIFF(NOW(),a.Account_Date)," +
                "a.Account_Cumulative,a.Account_Person,a.Account_Receivables,a.Account_Unfinished,a.Collections_Id,a.Account_Type , " +
                "b.custom_id,b.xs_custom_name,c.xs_car_id, c.xs_car_code, c.xs_car_vin_number," +
                " a.account_classify" +
                " FROM xs_sell_collections a, xs_custom_info b, xs_car_info c" +
                " WHERE a.custom_id = b.custom_id" +
                " AND a.xs_car_id = c.xs_car_id and account_TYPE<>" + baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.YSK, gatheringManageVo.getEnterpriseId()) + "";//

        //企业编号
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            hql += " AND b.enterprise_id=" + gatheringManageVo.getEnterpriseId() + "";
        }
        if (gatheringManageVo.getXs_Custom_Name() != null && !gatheringManageVo.getXs_Custom_Name().equals("")) {
            hql += " and b.xs_custom_name like '%" + StringEscapeUtils.escapeSql(gatheringManageVo.getXs_Custom_Name().trim()) + "%'";
        }
        if (gatheringManageVo.getXs_Car_Vin_Number() != null && !gatheringManageVo.getXs_Car_Vin_Number().equals("")) {
            hql += " and c.xs_car_vin_number like '%" + StringEscapeUtils.escapeSql(gatheringManageVo.getXs_Car_Vin_Number().trim()) + "%'";
        }
        if (gatheringManageVo.getAccount_Date() != null && !gatheringManageVo.getAccount_Date().equals("")) {
            hql += " and a.Account_Date >='" + gatheringManageVo.getAccount_Date() + "'";
        }
        if (gatheringManageVo.getAccount_Date2() != null && !gatheringManageVo.getAccount_Date2().equals("")) {
            hql += " and a.Account_Date <='" + gatheringManageVo.getAccount_Date2() + "'";

        }
        if ((gatheringManageVo.getAccount_Date() == null || gatheringManageVo.getAccount_Date().equals("")) &&
                (gatheringManageVo.getAccount_Date2() == null || gatheringManageVo.getAccount_Date2().equals(""))) {
            hql += " and DATE(a.Account_Date) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'";

        }
        List rlist = createSQLQuery(hql, gatheringManageVo.getPage(), gatheringManageVo.getRows());

        List list2 = new ArrayList();
        if (rlist != null && rlist.size() > 0) {
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[]) rlist.get(i);
                GatheringManageVo vo = new GatheringManageVo();
                vo.setAccount_Code(obj[0] != null ? obj[0].toString() : "");
                vo.setAccount_Date(obj[1] != null ? obj[1].toString() : "");
                vo.setAccount_Amount(obj[2] != null ? obj[2].toString() : "");
                vo.setAccount_Arrears(obj[3] != null ? obj[3].toString() : "");
                vo.setAccount_Arrears_Age(obj[4] != null ? obj[4].toString() : "");
                vo.setAccount_Cumulative(obj[5] != null ? obj[5].toString() : "");
                vo.setAccount_Person(SystemUser.getUser().getBasStuff().getStfName());
                //获取该车辆的累计预收金额
                StringBuilder sb = new StringBuilder("SELECT b.account_CUMULATIVE FROM xs_sell_collections b  " +
                        "WHERE b.account_id = (SELECT a.reserve_code FROM xs_sell_car_reserve a  " +
                        " INNER JOIN xs_sell_flow_control xsfc ON xsfc.xsfcontrol_car_id=a.xs_car_id   AND a.reserve_code=xsfc.xsfcontrol_document " +
                        "WHERE a.xs_car_id = " + obj[13] + " AND " +
                        "  a.order_state!=" + baseTagDAO.getChildId(Contstants.BASE_SELL.ORDER_STATE, Contstants.BASE_SELL.QUXIAO, gatheringManageVo.getEnterpriseId()) + "" +
                        " and a.enterprise_id=" + gatheringManageVo.getEnterpriseId() + " GROUP BY a.reserve_code ORDER BY a.reserve_dete DESC LIMIT 1)");
                List sumlist = createSQLQuery(sb.toString());
                Double sumMoney = sumlist != null && sumlist.size() > 0 ? Double.parseDouble(sumlist.get(0).toString()) : 0.00;
                //如果是代办，装潢，保险 不显示累计预收款
                if (obj[16] != null && obj[16].toString().equals(baseTagDAO.getChildId(Contstants.BASE_SELL.ACCOUNTTYPE, Contstants.BASE_SELL.ACCOUNTTYPE4, gatheringManageVo.getEnterpriseId()).toString())) {
                    vo.setSum_Money(sumMoney.toString());
                } else {
                    vo.setSum_Money("" + 0d + "");
                }
                //应收金额
                Double accountReceivables = obj[7] != null ? Double.parseDouble(obj[7].toString()) : 0.00;
                vo.setAccount_Receivables(accountReceivables + "");
                //如果应收金额 - 累计应收款 - 累计预收款 = 0 则为已付清
                if ((accountReceivables - Double.parseDouble(obj[5] + "") - sumMoney) <= 0) {
                    List datavalue = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id=" + baseTagDAO.getChildId(Contstants.BASE_SELL.SHIFOUFUQING, Contstants.BASE_SELL.YFQ, gatheringManageVo.getEnterpriseId()) + "");
                    vo.setAccount_Unfinished(datavalue != null ? datavalue.get(0).toString() : "");
                } else {
                    List datavalue = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id=" + baseTagDAO.getChildId(Contstants.BASE_SELL.SHIFOUFUQING, Contstants.BASE_SELL.WFQ, gatheringManageVo.getEnterpriseId()) + "");
                    vo.setAccount_Unfinished(datavalue != null ? datavalue.get(0).toString() : "");
                }
                //获取数据字典中的对应的类型
                List clist = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id=" + obj[10] + "");
                vo.setAccount_Type(clist != null ? clist.get(0).toString() : "");
                vo.setCollections_Id(obj[9] != null ? obj[9].toString() : "");
                vo.setCustom_Id(obj[11] != null ? obj[11].toString() : "");
                vo.setXs_Custom_Name(obj[12] != null ? obj[12].toString() : "");
                vo.setXs_Car_Id(obj[13] != null ? obj[13].toString() : "");
                vo.setXs_Car_Code(obj[14] != null ? obj[14].toString() : "");
                vo.setXs_Car_Vin_Number(obj[15] != null ? obj[15].toString() : "");
                vo.setAccount_Classify(obj[16] != null ? obj[16].toString() : "");
                List slist = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id=" + obj[16] + "");
                vo.setAccount_Classify_Name(slist != null ? slist.get(0).toString() : "");
                list2.add(vo);
            }
        }
        Json json = new Json();
        json.setRows(list2);
        json.setTotal(getCountBySQL(hql));
        return json;
    }

    /**
     * 获取收款记录信息
     */

    public Json getAmonunt(GatheringManageVo gatheringManageVo)
            throws Exception {
        Json json = new Json();
        if (gatheringManageVo.getCollections_Id() != null && !gatheringManageVo.getCollections_Id().equals("")) {
            String hql = "from XsSellReceivablesDetail a where a.xsSellCollections.collectionsId=" + gatheringManageVo.getCollections_Id() + "";
            List rlist = find(hql);
            List<XsSellReceivablesDetail> list = rlist;
            List list2 = new ArrayList();
            List list3 = null;
            if (list != null && list.size() > 0) {
                for (XsSellReceivablesDetail xsSellReceivablesDetail : list) {
                    GatheringManageVo vo = new GatheringManageVo();
                    vo.setDate_(xsSellReceivablesDetail.getDate());
                    vo.setDetails_Id(xsSellReceivablesDetail.getDetailsId() + "");
                    if (xsSellReceivablesDetail.getDetailType() != null) {
                        list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellReceivablesDetail.getDetailType() + "");
                        vo.setDetail_Type(list3.get(0) + "");
                    }
                    if (xsSellReceivablesDetail.getExamine() != null) {
                        list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellReceivablesDetail.getExamine() + "");
                        vo.setExamine(list3.get(0) + "");
                    }
                    if (xsSellReceivablesDetail.getInvoice() != null) {
                        list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellReceivablesDetail.getInvoice() + "");
                        vo.setInvoice(list3.get(0) + "");
                    }
                    if (xsSellReceivablesDetail.getReceivedWay() != null) {
                        list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellReceivablesDetail.getReceivedWay() + "");
                        vo.setReceived_Way(list3.get(0) + "");
                    }
                    if (xsSellReceivablesDetail.getStfId() != null) {
                        list3 = createSQLQuery("SELECT STF_NAME FROM bas_stuff WHERE stf_id =" + xsSellReceivablesDetail.getStfId() + "");
                        vo.setStf_Id(list3.get(0) + "");
                    }
                    vo.setInvoice_Num(xsSellReceivablesDetail.getInvoiceNum());
                    vo.setReceived_Money(xsSellReceivablesDetail.getReceivedMoney() + "");
                    vo.setRemark(xsSellReceivablesDetail.getRemark());
                    list2.add(vo);
                }
            }
            json.setRows(list2);
            json.setTotal(getCount(hql));
        }
        return json;
    }


    /**
     * 删除预收款记录
     */

    public void deleteYamount(GatheringManageVo gatheringManageVo)
            throws Exception {
        XsSellCollections xsSellCollections = new XsSellCollections();
        if (gatheringManageVo.getCollections_Id() != null && !gatheringManageVo.getCollections_Id().equals("")) {
            xsSellCollections.setCollectionsId(Integer.parseInt(gatheringManageVo.getCollections_Id()));
            delete(xsSellCollections);
        }

    }


    /**
     * 修改预收款记录
     */

    public void updateYamount(GatheringManageVo gatheringManageVo)
            throws Exception {
        XsSellCollections xsSellCollections = (XsSellCollections) get("from XsSellCollections where collectionsId = " + gatheringManageVo.getCollections_Id() + "");
        xsSellCollections.setAccountReceivables(Double.parseDouble(gatheringManageVo.getAccount_Receivables()));
        xsSellCollections.setAccountAmount(Double.parseDouble(gatheringManageVo.getAccount_Receivables()));
        xsSellCollections.setAccountCumulative(Double.parseDouble(gatheringManageVo.getAccount_Receivables()));
        merge(xsSellCollections);
    }


    /**
     * 获取预收款使用记录
     */
    public List getUseRecord(GatheringManageVo gatheringManageVo, BaseTagDAO baseTagDAO)
            throws Exception {

        List list2 = new ArrayList();
        List list3 = null;
        if (gatheringManageVo.getReserve_Code() != null && !gatheringManageVo.getReserve_Code().equals("")) {
            List listid = find("select b.collectionsId from XsSellCollections b WHERE b.accountId='" + gatheringManageVo.getReserve_Code() + "' AND b.xsSellCollectionType.childId=" + baseTagDAO.getChildId(Contstants.BASE_SELL.SHOUKUANLEIXING, Contstants.BASE_SELL.YSK, gatheringManageVo.getEnterpriseId()) + "");
            //获取收款单号
            int collectionsId = 0;
            if (listid != null) {
                for (int i = 0; i < listid.size(); i++) {
                    collectionsId = Integer.parseInt(listid.get(i) + "");
                    String hql = "from XsSellCollectionsDetail a where a.xsSellCollections.collectionsId=" + collectionsId + "";
                    List xsSellCollectionsDetaillist = find(hql);
                    List<XsSellCollectionsDetail> xsSellCollectionsDetaillist2 = xsSellCollectionsDetaillist;
                    if (xsSellCollectionsDetaillist2 != null && xsSellCollectionsDetaillist2.size() > 0) {
                        for (XsSellCollectionsDetail xsSellCollectionsDetail : xsSellCollectionsDetaillist2) {

                            GatheringManageVo vo = new GatheringManageVo();
                            vo.setDate_(xsSellCollectionsDetail.getDate());
                            vo.setDetails_Id(xsSellCollectionsDetail.getDetailsId() + "");
                            if (xsSellCollectionsDetail.getXsSellCollectionsDetailDetailType().getChildId() != null) {
                                list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellCollectionsDetail.getXsSellCollectionsDetailDetailType().getChildId() + "");
                                vo.setDetail_Type(list3.get(0) + "");
                            }
                            if (xsSellCollectionsDetail.getXsSellCollectionsDetailExamine().getChildId() != null) {
                                list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellCollectionsDetail.getXsSellCollectionsDetailExamine().getChildId() + "");
                                vo.setExamine(list3.get(0) + "");
                            }
                            if (xsSellCollectionsDetail.getXsSellCollectionsDetailInvoice().getChildId() != null) {
                                list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellCollectionsDetail.getXsSellCollectionsDetailInvoice().getChildId() + "");
                                vo.setInvoice(list3.get(0) + "");
                            }
                            if (xsSellCollectionsDetail.getXsSellCollectionsDetailWay().getChildId() != null) {
                                list3 = createSQLQuery("SELECT datavalue FROM xs_childdictionary WHERE child_id =" + xsSellCollectionsDetail.getXsSellCollectionsDetailWay().getChildId() + "");
                                vo.setReceived_Way(list3.get(0) + "");
                            }
                            if (xsSellCollectionsDetail.getStfId() != null && !xsSellCollectionsDetail.getStfId().equals("")) {
                                list3 = createSQLQuery("SELECT STF_NAME FROM bas_stuff WHERE stf_id =" + xsSellCollectionsDetail.getStfId() + "");
                                vo.setStf_Id(list3 != null ? list3.get(0) + "" : "");
                            }

                            vo.setInvoice_Num(xsSellCollectionsDetail.getInvoiceNum());
                            vo.setReceived_Money(xsSellCollectionsDetail.getReceivedMoney() + "");
                            vo.setRemark(xsSellCollectionsDetail.getRemark());
                            list2.add(vo);
                        }
                    }

                }

            }
        }
        return list2;

    }

    //预收款查询
    @SuppressWarnings("unchecked")

    public Datagrid getReadyBillInfor(GatheringManageVo gatheringManageVo)
            throws Exception {
        Datagrid dg = new Datagrid();
        StringBuffer sql = new StringBuffer("SELECT a.account_code,d.date_, a.account_id ," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.car_brand)  brand," +
                "c.xs_model_name,e.xs_custom_name,e.xs_custom_telephone,f.STF_NAME,d.received_money," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.received_way)  way," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.invoice)  inv," +
                "d.invoice_num,d.remark " +
                "FROM xs_sell_collections A " +
                "JOIN xs_sell_car_reserve B ON b.reserve_code=a.account_id " +
                "JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=b.reserve_code AND " +
                " flow.xsfcontrol_code='" + Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4 + "'" +
                "JOIN xs_car_model C ON c.xs_model_id=b.car_model" +
                " LEFT JOIN xs_sell_collections_detail D ON d.collections_id=a.collections_id " +
                " JOIN xs_custom_info E ON e.custom_id=b.custom_id " +
                " JOIN bas_stuff F ON f.STF_ID=d.stf_id  where 1=1 ");

        //企业编号
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            sql.append(" AND b.enterprise_id=" + gatheringManageVo.getEnterpriseId() + "");
        }

        if (gatheringManageVo.getDate_() != null && !"".equals(gatheringManageVo.getDate_())) {

            sql.append(" and d.date_ >='" + gatheringManageVo.getDate_() + "'");
        }
        if (gatheringManageVo.getDate_two() != null && !"".equals(gatheringManageVo.getDate_two())) {

            sql.append(" and d.date_ <='" + gatheringManageVo.getDate_two() + "'");
        }
        if ((gatheringManageVo.getDate_() == null || "".equals(gatheringManageVo.getDate_()))
                && gatheringManageVo.getDate_two() == null || "".equals(gatheringManageVo.getDate_two())) {
            sql.append(" and DATE(d.date_) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
        }
        if (gatheringManageVo.getXs_Custom_Name() != null && !"".equals(gatheringManageVo.getXs_Custom_Name())) {
            sql.append(" and e.xs_custom_name like '%" + gatheringManageVo.getXs_Custom_Name() + "%'");
        }
        if (gatheringManageVo.getAccount_Id() != null && !"".equals(gatheringManageVo.getAccount_Id())) {
            sql.append(" and a.account_id like '%" + gatheringManageVo.getAccount_Id() + "%'");
        }

        if (gatheringManageVo.getReceived_Way_id() != null && !"".equals(gatheringManageVo.getReceived_Way_id())) {
            sql.append(" and d.received_way ='" + gatheringManageVo.getReceived_Way_id() + "'");
        }


        List<Object[]> resultList = createSQLQuery(sql.toString(),
                gatheringManageVo.getPage(), gatheringManageVo.getRows());
        List<GatheringManageVo> list = new ArrayList<GatheringManageVo>();
        if (resultList != null && resultList.size() > 0) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++) {
                GatheringManageVo sell = new GatheringManageVo();
                obj = resultList.get(i);
                sell.setAccount_Code(obj[0] != null ? obj[0].toString() : null);
                sell.setDate_(obj[1] != null ? obj[1].toString() : null);
                sell.setAccount_Id(obj[2] != null ? obj[2].toString() : null);
                sell.setCarBrand(obj[3] != null ? obj[3].toString() : null);
                sell.setXs_Model_Name(obj[4] != null ? obj[4].toString() : null);
                sell.setXs_Custom_Name(obj[5] != null ? obj[5].toString() : null);
                sell.setTel(obj[6] != null ? obj[6].toString() : null);
                sell.setStf_Name(obj[7] != null ? obj[7].toString() : null);
                sell.setReceived_Money(obj[8] != null ? obj[8].toString() : null);
                sell.setReceived_Way(obj[9] != null ? obj[9].toString() : null);
                sell.setInvoice(obj[10] != null ? obj[10].toString() : null);
                sell.setInvoice_Num(obj[11] != null ? obj[11].toString() : null);
                sell.setRemark(obj[12] != null ? obj[12].toString() : null);
                list.add(sell);
            }
        }
        int total = this.getSQLCount(sql.toString(), null);
        dg.setRows(list);
        dg.setTotal(total);
        return dg;
    }

    @SuppressWarnings("unchecked")

    public Datagrid getYuEBillInfor(GatheringManageVo gatheringManageVo)
            throws Exception {
        Datagrid dg = new Datagrid();
        StringBuffer sql = new StringBuffer("SELECT a.account_code,d.date_, a.account_id ," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.car_brand)  brand," +
                "c.xs_model_name,e.xs_custom_name,e.xs_custom_telephone,SUM(d.received_money) " +
                " FROM xs_sell_collections A " +
                "JOIN xs_sell_car_reserve B ON b.reserve_code=a.account_id " +
                "JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=b.reserve_code AND" +
                " flow.xsfcontrol_code='" + Contstants.SELLFLOWCONTROLCODE.CONTROLCODE4 + "'" +
                "JOIN xs_car_model C ON c.xs_model_id=b.car_model " +
                " JOIN xs_sell_collections_detail D ON d.collections_id=a.collections_id " +
                "JOIN xs_custom_info E ON e.custom_id=b.custom_id " +
                " WHERE d.detail_type=( SELECT child.child_id FROM xs_childdictionary  child, " +
                "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
                "AND parent.dataKey='" + Contstants.BASE_SELL.KXLX + "'" +
                "AND child.dataKey='" + Contstants.BASE_SELL.SR + "' and child.enterprise_id=" + gatheringManageVo.getEnterpriseId() + ")");
        //企业编号
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            sql.append(" AND b.enterprise_id=" + gatheringManageVo.getEnterpriseId() + "");
        }
        if (gatheringManageVo.getXs_Custom_Name() != null && !"".equals(gatheringManageVo.getXs_Custom_Name())) {
            sql.append(" and e.xs_custom_name like '%" + gatheringManageVo.getXs_Custom_Name() + "%'");
        }
        if (gatheringManageVo.getAccount_Id() != null && !"".equals(gatheringManageVo.getAccount_Id())) {
            sql.append(" and a.account_id like '%" + gatheringManageVo.getAccount_Id() + "%'");
        }

        if (gatheringManageVo.getDate_() != null && !"".equals(gatheringManageVo.getDate_())) {

            sql.append(" and d.date_ >='" + gatheringManageVo.getDate_() + "'");
        }
        if (gatheringManageVo.getDate_two() != null && !"".equals(gatheringManageVo.getDate_two())) {

            sql.append(" and d.date_ <='" + gatheringManageVo.getDate_two() + "'");
        }
        if ((gatheringManageVo.getDate_() == null || "".equals(gatheringManageVo.getDate_()))
                && gatheringManageVo.getDate_two() == null || "".equals(gatheringManageVo.getDate_two())) {
            sql.append(" and DATE(d.date_) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
        }

        sql.append("  GROUP BY a.collections_id");

        List<Object[]> resultList = createSQLQuery(sql.toString(),
                gatheringManageVo.getPage(), gatheringManageVo.getRows());
        List<GatheringManageVo> list = new ArrayList<GatheringManageVo>();
        if (resultList != null && resultList.size() > 0) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++) {
                GatheringManageVo sell = new GatheringManageVo();
                obj = resultList.get(i);
                sell.setAccount_Code(obj[0] != null ? obj[0].toString() : null);
                sell.setDate_(obj[1] != null ? obj[1].toString() : null);
                sell.setAccount_Id(obj[2] != null ? obj[2].toString() : null);
                sell.setCarBrand(obj[3] != null ? obj[3].toString() : null);
                sell.setXs_Model_Name(obj[4] != null ? obj[4].toString() : null);
                sell.setXs_Custom_Name(obj[5] != null ? obj[5].toString() : null);
                sell.setTel(obj[6] != null ? obj[6].toString() : null);
                sell.setReceived_Money(obj[7] != null ? obj[7].toString() : null);
                list.add(sell);
            }
        }
        int total = this.getSQLCount(sql.toString(), null);
        dg.setRows(list);
        dg.setTotal(total);
        return dg;
    }

    @SuppressWarnings("unchecked")

    public Datagrid getShouldBillInfor(GatheringManageVo gatheringManageVo)
            throws Exception {
        Datagrid dg = new Datagrid();
        StringBuffer sql = new StringBuffer("SELECT a.account_code,d.date_, a.account_id ," +
                " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.xs_car_brand) AS brand," +
                " c.xs_model_name,b.xs_car_vin_number,e.xs_custom_name,e.xs_custom_telephone,f.STF_NAME,d.received_money," +
                " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.received_way) AS way," +
                " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =d.invoice)AS  inv," +
                " D.invoice_num,d.remark," +
                " (SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.account_classify) AS ty," +
                "a.account_DATE " +
                " FROM xs_sell_collections A " +
                " JOIN xs_car_info b ON b.xs_car_id=a.xs_car_id " +
                " JOIN xs_car_model C ON c.xs_model_id=b.xs_car_model_id " +
                " JOIN xs_custom_info E ON e.custom_id=a.custom_id " +
                " JOIN bas_stuff F ON f.STF_ID=a.account_PERSON " +
                " JOIN xs_sell_account co ON co.account_code=a.account_id  " +
                " JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=co.xs_car_sel_id " +
                " JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=sell.sell_code " +
                " AND flow.xsfcontrol_car_id=sell.xs_car_id " +
                " LEFT JOIN xs_sell_receivables_detail D ON d.collections_id=a.collections_id  " +
                "where 1=1 ");

        //企业编号
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            sql.append(" AND sell.enterprise_id=" + gatheringManageVo.getEnterpriseId() + "");
        }
        if (gatheringManageVo.getDate_() != null && !"".equals(gatheringManageVo.getDate_())) {

            sql.append(" and DATE(a.account_DATE) >='" + gatheringManageVo.getDate_() + "'");
        }
        if (gatheringManageVo.getDate_two() != null && !"".equals(gatheringManageVo.getDate_two())) {

            sql.append(" and DATE(a.account_DATE) <='" + gatheringManageVo.getDate_two() + "'");
        }
        if ((gatheringManageVo.getDate_() == null || "".equals(gatheringManageVo.getDate_()))
                && gatheringManageVo.getDate_two() == null || "".equals(gatheringManageVo.getDate_two())) {
            sql.append(" and DATE(a.account_DATE) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
        }

        if (gatheringManageVo.getXs_Custom_Name() != null && !"".equals(gatheringManageVo.getXs_Custom_Name())) {
            sql.append(" and e.xs_custom_name like '%" + gatheringManageVo.getXs_Custom_Name() + "%'");
        }
        if (gatheringManageVo.getXs_Car_Vin_Number() != null && !"".equals(gatheringManageVo.getXs_Car_Vin_Number())) {
            sql.append(" and b.xs_car_vin_number like '%" + gatheringManageVo.getXs_Car_Vin_Number() + "%'");
        }

        if (gatheringManageVo.getReceived_Way_id() != null && !"".equals(gatheringManageVo.getReceived_Way_id())) {
            sql.append(" and d.received_way ='" + gatheringManageVo.getReceived_Way_id() + "'");
        }


        List<Object[]> resultList = createSQLQuery(sql.toString(),
                gatheringManageVo.getPage(), gatheringManageVo.getRows());
        List<GatheringManageVo> list = new ArrayList<GatheringManageVo>();
        if (resultList != null && resultList.size() > 0) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++) {
                GatheringManageVo sell = new GatheringManageVo();
                obj = resultList.get(i);
                sell.setAccount_Code(obj[0] != null ? obj[0].toString() : null);
                sell.setDate_(obj[1] != null ? obj[1].toString() : null);
                sell.setAccount_Id(obj[2] != null ? obj[2].toString() : null);
                sell.setCarBrand(obj[3] != null ? obj[3].toString() : null);
                sell.setXs_Model_Name(obj[4] != null ? obj[4].toString() : null);
                sell.setXs_Car_Vin_Number(obj[5] != null ? obj[5].toString() : null);
                sell.setXs_Custom_Name(obj[6] != null ? obj[6].toString() : null);
                sell.setTel(obj[7] != null ? obj[7].toString() : null);
                sell.setStf_Name(obj[8] != null ? obj[8].toString() : null);
                sell.setReceived_Money(obj[9] != null ? obj[9].toString() : null);
                sell.setReceived_Way(obj[10] != null ? obj[10].toString() : null);
                sell.setInvoice(obj[11] != null ? obj[11].toString() : null);
                sell.setInvoice_Num(obj[12] != null ? obj[12].toString() : null);
                sell.setRemark(obj[13] != null ? obj[13].toString() : null);
                sell.setAccount_Classify_Name(obj[14] != null ? obj[14].toString() : null);
                sell.setAccount_Date(obj[15] != null ? obj[15].toString() : null);

                list.add(sell);
            }
        }
        int total = this.getSQLCount(sql.toString(), null);
        dg.setRows(list);
        dg.setTotal(total);
        return dg;
    }


    public Datagrid getQkBillInfor(GatheringManageVo gatheringManageVo)
            throws Exception {
        Datagrid dg = new Datagrid();
        StringBuffer sql = new StringBuffer("SELECT a.account_code, a.account_id ," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =b.xs_car_brand) AS brand," +
                "c.xs_model_name,e.xs_custom_name,e.xs_custom_telephone," +
                "a.account_ARREARS,b.xs_car_vin_number," +
                "(SELECT datavalue FROM xs_childdictionary A WHERE A.child_id =a.account_classify) AS ty  " +
                "FROM xs_sell_collections A " +
                "JOIN xs_car_info b ON b.xs_car_id=a.xs_car_id " +
                "JOIN xs_car_model C ON c.xs_model_id=b.xs_car_model_id " +
                " JOIN xs_custom_info E ON e.custom_id=a.custom_id " +
                "JOIN xs_sell_account co ON co.account_code=a.account_id  " +
                "JOIN xs_car_sell_info sell ON sell.xs_car_sel_id=co.xs_car_sel_id " +
                "JOIN xs_sell_flow_control flow ON flow.xsfcontrol_document=sell.sell_code " +
                "AND flow.xsfcontrol_car_id=sell.xs_car_id " +
                "WHERE account_UNFINISHED=( SELECT child.child_id FROM xs_childdictionary  child, " +
                "xs_parentdictionary parent  WHERE child.parent_id = parent.parent_id " +
                "AND parent.dataKey='" + Contstants.BASE_SELL.SHIFOUFUQING + "'" +
                "AND child.dataKey='" + Contstants.BASE_SELL.WFQ + "' and child.enterprise_id=" + gatheringManageVo.getEnterpriseId() + ")");
        //企业编号
        if (gatheringManageVo.getEnterpriseId() != null && !gatheringManageVo.getEnterpriseId().equals("")) {
            sql.append(" AND sell.enterprise_id=" + gatheringManageVo.getEnterpriseId() + "");
        }
        if (gatheringManageVo.getXs_Custom_Name() != null && !"".equals(gatheringManageVo.getXs_Custom_Name())) {
            sql.append(" and e.xs_custom_name like '%" + gatheringManageVo.getXs_Custom_Name() + "%'");
        }
        if (gatheringManageVo.getXs_Car_Vin_Number() != null && !"".equals(gatheringManageVo.getXs_Car_Vin_Number())) {
            sql.append(" and b.xs_car_vin_number like '%" + gatheringManageVo.getXs_Car_Vin_Number() + "%'");
        }

        if (gatheringManageVo.getAccount_Date() != null && !"".equals(gatheringManageVo.getAccount_Date())) {
            sql.append(" and a.account_DATE >= '" + gatheringManageVo.getAccount_Date() + "'");
        }
        if (gatheringManageVo.getAccount_Date2() != null && !"".equals(gatheringManageVo.getAccount_Date2())) {
            sql.append(" and a.account_DATE <='" + gatheringManageVo.getAccount_Date2() + "'");

        }
        if ((gatheringManageVo.getAccount_Date() == null || "".equals(gatheringManageVo.getAccount_Date())) &&
                (gatheringManageVo.getAccount_Date2() == null || "".equals(gatheringManageVo.getAccount_Date2()))) {
            sql.append(" and DATE(a.account_DATE) between " +
                    " '" + FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT2, (basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY, gatheringManageVo.getEnterpriseId()).getCiValue())) + "'" +
                    " and  '" +
                    FormatTime.yesTady(FormatTime.DEFAULT_FORMAT2) + "'");
        }

        List<Object[]> resultList = createSQLQuery(sql.toString(),
                gatheringManageVo.getPage(), gatheringManageVo.getRows());
        List<GatheringManageVo> list = new ArrayList<GatheringManageVo>();
        if (resultList != null && resultList.size() > 0) {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++) {
                GatheringManageVo sell = new GatheringManageVo();
                obj = resultList.get(i);
                sell.setAccount_Code(obj[0] != null ? obj[0].toString() : null);
                sell.setAccount_Id(obj[1] != null ? obj[1].toString() : null);
                sell.setCarBrand(obj[2] != null ? obj[2].toString() : null);
                sell.setXs_Model_Name(obj[3] != null ? obj[3].toString() : null);

                sell.setXs_Custom_Name(obj[4] != null ? obj[4].toString() : null);
                sell.setTel(obj[5] != null ? obj[5].toString() : null);
                sell.setAccount_Arrears(obj[6] != null ? obj[6].toString() : null);
                sell.setXs_Car_Vin_Number(obj[7] != null ? obj[7].toString() : null);
                sell.setAccount_Classify_Name(obj[8] != null ? obj[8].toString() : null);
                list.add(sell);
            }
        }
        int total = this.getSQLCount(sql.toString(), null);
        dg.setRows(list);
        dg.setTotal(total);
        return dg;
    }

}
