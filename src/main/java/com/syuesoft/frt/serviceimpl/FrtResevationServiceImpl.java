package com.syuesoft.frt.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasCarBodysStatusDAO;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.bas.dao.BasStuffDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.BasCarBodysStatusVo;
import com.syuesoft.base.vo.PartsVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.*;
import com.syuesoft.frt.service.FrtResevationService;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.frt.vo.FrtItemVo;
import com.syuesoft.frt.vo.FrtResevationVo;
import com.syuesoft.model.*;
import com.syuesoft.print.service.PrintService;
import com.syuesoft.st.dao.ReptypeDAO;
import com.syuesoft.util.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 预约/保险估价单service
 *
 * @author Liujian
 */
@Service("frtResevationService")
public class FrtResevationServiceImpl extends BaseLogServiceImpl implements
        FrtResevationService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FrtResevationServiceImpl.class);
    @Autowired
    private FrtRushToRepairDao frtRushToRepairDao;
    @Autowired
    private FrtResevationDao frtResevationDao;
    @Autowired
    private FrtResvPartsDao frtResvPartsDao;
    @Autowired
    private FrtResvItemDao frtResvItemDao;
    @Autowired
    private FrtCarDao frtCarDao;
    @Autowired
    private FrtCustomDao frtCustomDao;
    @Autowired
    private BasStuffDao basStuffDao;
    @Autowired
    private FrtResvVehicleStructureDao frtResvVehicleStructureDao;
    @Autowired
    private FrtReceptionDao frtReceptionDao;
    @Autowired
    private ReptypeDAO reptypeDAO;
    @Autowired
    private FrtRcptPartsDao frtRcptPartsDao;
    @Autowired
    private FrtRcptItemDao frtRcptItemDao;
    @Autowired
    private FrtReceptionVehicleStructureDao frtReceptionVehicleStructureDao;
    @Autowired
    BasCarBodysStatusDAO basCarBodysStatusDAO;
    @Autowired
    private BasCompanyInformationSetDAO basCompanyInformationSetDAO;
    @Autowired
    private PrintService printService;
    @Autowired
    BasPartsUnitDAO basPartsUnitDAO;
    @Autowired
    private FrtService frtService;
    public static HashMap<Short, BasCarBodysStatus> carFrameWorks = null;

    private BasCarBodysStatusVo basCarBodysStatusVo = new BasCarBodysStatusVo();
    ;

    private void loadCarFrameWorks() throws Exception {

        carFrameWorks = new HashMap();
        List<BasCarBodysStatus> list = basCarBodysStatusDAO.findAll();
        if (list != null && list.size() > 0)
            for (BasCarBodysStatus basCarBodysStatus : list) {
                carFrameWorks.put(basCarBodysStatus.getBodyNum(),
                        basCarBodysStatus);
            }
    }

    public FrtRcptItemDao getFrtRcptItemDao() {
        return frtRcptItemDao;
    }

    @Autowired
    public void setFrtRcptItemDao(FrtRcptItemDao frtRcptItemDao) {
        this.frtRcptItemDao = frtRcptItemDao;
    }

    public FrtReceptionVehicleStructureDao getFrtReceptionVehicleStructureDao() {
        return frtReceptionVehicleStructureDao;
    }

    @Autowired
    public void setFrtReceptionVehicleStructureDao(
            FrtReceptionVehicleStructureDao frtReceptionVehicleStructureDao) {
        this.frtReceptionVehicleStructureDao = frtReceptionVehicleStructureDao;
    }

    public ReptypeDAO getReptypeDAO() {
        return reptypeDAO;
    }

    @Autowired
    public void setReptypeDAO(ReptypeDAO reptypeDAO) {
        this.reptypeDAO = reptypeDAO;
    }

    public FrtReceptionDao getFrtReceptionDao() {
        return frtReceptionDao;
    }

    @Autowired
    public void setFrtReceptionDao(FrtReceptionDao frtReceptionDao) {
        this.frtReceptionDao = frtReceptionDao;
    }

    public FrtResevationDao getFrtResevationDao() {
        return frtResevationDao;
    }

    @Autowired
    public void setFrtResevationDao(FrtResevationDao frtResevationDao) {
        this.frtResevationDao = frtResevationDao;
    }

    public FrtRushToRepairDao getFrtRushToRepairDao() {
        return frtRushToRepairDao;
    }

    @Autowired
    public void setFrtRushToRepairDao(FrtRushToRepairDao frtRushToRepairDao) {
        this.frtRushToRepairDao = frtRushToRepairDao;
    }

    public FrtCarDao getFrtCarDao() {
        return frtCarDao;
    }

    @Autowired
    public void setFrtCarDao(FrtCarDao frtCarDao) {
        this.frtCarDao = frtCarDao;
    }

    public BasStuffDao getBasStuffDao() {
        return basStuffDao;
    }

    @Autowired
    public void setBasStuffDao(BasStuffDao basStuffDao) {
        this.basStuffDao = basStuffDao;
    }

    public FrtResvPartsDao getFrtResvPartsDao() {
        return frtResvPartsDao;
    }

    @Autowired
    public void setFrtResvPartsDao(FrtResvPartsDao frtResvPartsDao) {
        this.frtResvPartsDao = frtResvPartsDao;
    }

    public FrtResvItemDao getFrtResvItemDao() {
        return frtResvItemDao;
    }

    @Autowired
    public void setFrtResvItemDao(FrtResvItemDao frtResvItemDao) {
        this.frtResvItemDao = frtResvItemDao;
    }

    public FrtCustomDao getFrtCustomDao() {
        return frtCustomDao;
    }

    @Autowired
    public void setFrtCustomDao(FrtCustomDao frtCustomDao) {
        this.frtCustomDao = frtCustomDao;
    }

    public FrtResvVehicleStructureDao getFrtResvVehicleStructureDao() {
        return frtResvVehicleStructureDao;
    }

    @Autowired
    public void setFrtResvVehicleStructureDao(
            FrtResvVehicleStructureDao frtResvVehicleStructureDao) {
        this.frtResvVehicleStructureDao = frtResvVehicleStructureDao;
    }


    public BasCarBodysStatusDAO getBasCarBodysStatusDAO() {
        return basCarBodysStatusDAO;
    }

    public void setBasCarBodysStatusDAO(BasCarBodysStatusDAO basCarBodysStatusDAO) {
        this.basCarBodysStatusDAO = basCarBodysStatusDAO;
    }

    /**
     * 判断预约单是否取消
     */

    public void modifyIsBespeakOff(FrtResevationVo freVo) throws Exception {
        String hql = "from FrtResevation fr where fr.frtresvFlg="
                + Contstants.DELETE_TAG.DELETENO;
        hql += " and fr.resvStatus='"
                + Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING + "' and  fr.enterpriseId=" + freVo.getEnterpriseId();
        List<FrtResevation> frList = frtResevationDao.find(hql);
        Date nowDate = new Date();
        if (frList != null && frList.size() > 0)
            for (FrtResevation frtResevation : frList) {
                isCancel(frtResevation, null, nowDate);
            }
    }

    /**
     * 获取指定预约申请单信息
     *
     * @throws Exception
     */
    public FrtResevationVo findInfoByResvId(Boolean flag, FrtResevationVo freVo) throws Exception {
        Datagrid dg = datagridFrtResevation(flag, freVo);
        if (dg == null)
            return null;
        FrtResevationVo fr = (FrtResevationVo) dg.getRows().get(0);
        return fr;
    }

    /**
     * 预约/保险估价单汇总datagrid
     */

    public Datagrid datagridFrtResevation(Boolean flag, FrtResevationVo freVo)
            throws Exception {
        Datagrid dg = new Datagrid();
        List<FrtResevationVo> rows = new ArrayList<FrtResevationVo>();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer("SELECT c.*,d.tempdata2 FROM(SELECT a.*,b.tempdata1 FROM");
        sql.append(" (SELECT v.*,fr.RESEVATION_REP_PER FROM frt_resevation_view v,frt_resevation fr");
        sql.append(" WHERE fr.RESV_ID = v.resvId AND fr.FRTRESV_FLG=" + Contstants.DELETE_TAG.DELETENO + " ");
        if (flag != null)
            if (flag == true) {// 查找保险估价单
                sql.append(" AND fr.BESPEAK_PRIZE_TAG=" + Contstants.DELETE_TAG.DELETEYES + "");
            } else {// 查找预约单
                sql.append(" AND fr.BESPEAK_PRIZE_TAG=" + Contstants.DELETE_TAG.DELETENO + "");
            }

        //sql.append(" AND fr.RESV_STATUS='"+ Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING + "'");
        // sql.append(" AND fr.RESV_TO_RCPT=" + Contstants.NEARCAR_TAG.NEARCARNO + "");
        sql.append(" )a,");
        sql.append(" (SELECT bc.dataKey,bc.dataValue AS tempdata1 FROM bas_parentdictionary bp,bas_childdictionary bc");
        sql.append(" WHERE bc.parent_id=bp.parent_id AND bp.dataKey='" + Contstants.BESPEAKSTATE_TAG.BESPEAKSTATEKEY + "')b");
        sql.append(" WHERE a.resvStatus=b.dataKey)c LEFT OUTER JOIN");
        sql.append(" (SELECT bc1.dataKey,bc1.dataValue AS tempdata2 FROM bas_parentdictionary bp1,bas_childdictionary bc1");
        sql.append(" WHERE bc1.parent_id=bp1.parent_id AND bp1.dataKey='" + Contstants.BESPEAKCLASS_TAG.BESPEAKCLASSKEY + "'");
        sql.append(" )d ON c.resvType=d.datakey WHERE 1=1   ");

        if (freVo.getEnterpriseId() != null && !("".equals(freVo.getEnterpriseId()))) {
            sql.append("  AND  c.enterpriseId=" + freVo.getEnterpriseId());
        }

        // 预约编号
        if (null != freVo.getResvId() && !freVo.getResvId().trim().equals("")) {
            sql.append(" and c.resvId like '%"
                    + StringEscapeUtils.escapeSql(freVo.getResvId().trim())
                    + "%'");
        }
        // 预约分类
        if (null != freVo.getResvType()
                && !freVo.getResvType().trim().equals("")) {
            sql.append(" and c.resvType = '" + freVo.getResvType() + "'");
        }
        // 车辆编号
        if (null != freVo.getCarId() && !freVo.getCarId().trim().equals("")) {
            if (freVo.getFlag() != null && freVo.getFlag() == true) {
                sql.append(" and c.carId='" + freVo.getCarId() + "'");
            } else {
                freVo.setCarId(freVo.getCarId().replace(",", ""));
                sql.append(" and c.carLicense like '%"
                        + StringEscapeUtils.escapeSql(freVo.getCarId().trim())
                        + "%'");
            }
        }
        // 车辆品牌
        if (null != freVo.getCbrdId() && !freVo.getCbrdId().trim().equals("")) {
            sql.append(" and c.cbrdId like '%"
                    + StringEscapeUtils.escapeSql(freVo.getCbrdId().trim())
                    + "%'");
        }
        //车辆牌照
        if (null != freVo.getCarLicense() &&
                !freVo.getCarLicense().trim().equals("")) {
            sql.append(" and c.carLicense like '%" + StringEscapeUtils.escapeSql(freVo.getCarLicense().trim()) + "%'");
        }
        if (null != freVo.getCarVin() &&
                !freVo.getCarVin().trim().equals("")) {
            sql.append(" and c.carVin like '%" + StringEscapeUtils.escapeSql(freVo.getCarVin().trim()) + "%'");
        }
        // 客户名称
        if (null != freVo.getCustomId()
                && !freVo.getCustomId().trim().equals("")) {
            sql.append(" and c.customId like '%"
                    + StringEscapeUtils.escapeSql(freVo.getCustomId().trim())
                    + "%'");
        }
        // 预约状态
        if (null != freVo.getResvStatus() && !freVo.getResvStatus().equals("")) {
            sql
                    .append(" and c.resvStatus  like '%"
                            + StringEscapeUtils
                            .escapeSql(freVo.getResvStatus()) + "%'");
        }
        // 接待员
        if (null != freVo.getStfId() && !freVo.getStfId().equals("")) {
            sql.append(" and c.stfId =" + freVo.getStfId());
        }
        // 维修类别
        if (null != freVo.getReptId() && !freVo.getReptId().equals("")) {
            sql.append(" and c.reptId =" + freVo.getReptId());
        }
        // 预约工位
        if (null != freVo.getRepwkId() && !freVo.getRepwkId().equals("")) {
            sql.append(" and c.repwkId = '" + freVo.getRepwkId() + "'");
        }
        // 预约登记时间
        if (null != freVo.getResvRealTimeBegin()
                && !freVo.getResvRealTimeBegin().equals("")) {
            Date date = Utils.dateFormat(freVo.getResvRealTimeBegin(), true);
            sql.append(" and c.resvRealTime  >= '" + freVo.getResvRealTimeBegin() + "'");
        }
        // 预约登记时间
        if (null != freVo.getResvRealTimeEnd()
                && !freVo.getResvRealTimeEnd().equals("")) {
            Date date = Utils.dateFormat(freVo.getResvRealTimeEnd(), true);
            sql.append(" and c.resvRealTime  <= '" + freVo.getResvRealTimeEnd() + "'");

        } 
       /* if(freVo.getResvRealTimeEnd()==null && freVo.getResvRealTimeBegin()==null){
            sql.append(" and DATE_FORMAT(c.resvRealTime,'%Y-%m-%d %H-%i-%s')>='"+ FormatTime.yesTempTady(FormatTime.DEFAULT_FORMAT, Integer.parseInt(basCompanyInformationSetDAO.getBasCompanyInformationSet(PARAMETER_SET.COLLIGATES, Contstants.COLLIGATES.DEFAULTSHOWDAY,freVo.getEnterpriseId()).getCiValue()))+ "'");
        	 sql.append(" and DATE_FORMAT(c.resvRealTime,'%Y-%m-%d %H-%i-%s')<='"+ FormatTime.yesTady(FormatTime.DEFAULT_FORMAT) +"'");
        }*/
        // 预约进店时间
        if (null != freVo.getResvEnterTimeBegin()
                && !freVo.getResvEnterTimeBegin().equals("")) {
            Date date = Utils.dateFormat(freVo.getResvEnterTimeBegin(), true);
            sql.append(" and c.resvEnterTime  >= '"
                    + freVo.getResvEnterTimeBegin() + "'");
        }
        // 预约进店时间
        if (null != freVo.getResvEnterTimeEnd()
                && !freVo.getResvEnterTimeEnd().equals("")) {
            Date date = Utils.dateFormat(freVo.getResvEnterTimeEnd(), true);
            sql.append(" and c.resvEnterTime  <= '"
                    + freVo.getResvEnterTimeEnd() + "'");
        }
        if (freVo.getSort() != null && !"".equals(freVo.getSort().trim())
                && freVo.getOrder() != null
                && !"".equals(freVo.getOrder().trim())) {
            sql.append(" order by c." + freVo.getSort() + " "
                    + freVo.getOrder());
        }
        List<Object[]> rowsList = frtResevationDao.createSQLQuery(sql
                .toString(), params, freVo.getPage(), freVo.getRows());

        if (rowsList != null && rowsList.size() > 0) {
            for (Object[] obj : rowsList) {
                FrtResevationVo fVo = new FrtResevationVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                    fVo.setResvId(obj[0].toString());
                if (obj[1] != null && obj[1].toString().length() > 0)
                    fVo.setCarId(obj[1].toString());
                if (obj[2] != null && obj[2].toString().length() > 0)
                    fVo.setCarLicense(obj[2].toString());
                if (obj[3] != null && obj[3].toString().length() > 0)
                    fVo.setCarVin(obj[3].toString());
                if (obj[4] != null && obj[4].toString().length() > 0)
                    fVo.setCarMotorId(obj[4].toString());
                if (obj[9] != null && obj[9].toString().length() > 0)
                    fVo.setCustomId(obj[9].toString());
                if (obj[10] != null && obj[10].toString().length() > 0)
                    fVo.setCustomName(obj[10].toString());
                if (obj[12] != null && obj[12].toString().length() > 0)
                    fVo.setResvEnterTime(MyBeanUtils.getInstance()
                            .formatString(obj[12].toString()));
                if (obj[13] != null && obj[13].toString().length() > 0)
                    fVo.setReptId(new Short(obj[13].toString()));
                if (obj[14] != null && obj[14].toString().length() > 0)
                    fVo.setReptName(obj[14].toString());
                if (obj[15] != null && obj[15].toString().length() > 0)
                    fVo.setRepwkId(new Short(obj[15].toString()));
                if (obj[16] != null && obj[16].toString().length() > 0)
                    fVo.setRepwkName(obj[16].toString());
                if (obj[17] != null && obj[17].toString().length() > 0)
                    fVo.setResvStatus(obj[17].toString());
                if (obj[19] != null && obj[19].toString().length() > 0)
                    fVo.setResvDistance(new Integer(obj[19].toString()));
                else
                    fVo.setResvDistance(0);
                if (obj[20] != null && obj[20].toString().length() > 0)
                    fVo.setResvRealTime(MyBeanUtils.getInstance().formatString(
                            obj[20].toString()));
                if (obj[21] != null && obj[21].toString().length() > 0)
                    fVo.setStfId(new Short(obj[21].toString()));
                if (obj[22] != null && obj[22].toString().length() > 0)
                    fVo.setStfName(obj[22].toString());
                if (obj[23] != null && obj[23].toString().length() > 0)
                    fVo.setResvFixpel(obj[23].toString());
                if (obj[24] != null && obj[24].toString().length() > 0)
                    fVo.setResvFixtel(obj[24].toString());
                if (obj[25] != null && obj[25].toString().length() > 0)
                    fVo.setResvFixphone(obj[25].toString());
                if (obj[26] != null && obj[26].toString().length() > 0)
                    fVo.setRepgrpId(new Short(obj[26].toString()));
                if (obj[27] != null && obj[27].toString().length() > 0)
                    fVo.setRepgrpName(obj[27].toString());
                if (obj[28] != null && obj[28].toString().length() > 0)
                    fVo.setResvType(obj[28].toString());
                if (obj[29] != null && obj[29].toString().length() > 0)
                    fVo.setResvTypeName(obj[29].toString());
                if (obj[30] != null && obj[30].toString().length() > 0)
                    fVo.setResvRemark(obj[30].toString());
                if (obj[31] != null && obj[31].toString().length() > 0)
                    fVo.setRtrServices(obj[31].toString());

                if (obj[32] != null && obj[32].toString().length() > 0)
                    fVo.setRtrSatisfaction(obj[32].toString());
                if (obj[33] != null && obj[33].toString().length() > 0)
                    fVo.setRtrReportTime(MyBeanUtils.getInstance()
                            .formatString(obj[33].toString()));
                if (obj[34] != null && obj[34].toString().length() > 0)
                    fVo.setRtrIdea(obj[34].toString());
                if (obj[35] != null && obj[35].toString().length() > 0)
                    fVo.setRtrReplyTime(MyBeanUtils.getInstance().formatString(
                            obj[35].toString()));
                if (obj[36] != null && obj[36].toString().length() > 0)
                    fVo.setRtrIsCome(obj[36].toString());
                if (obj[37] != null && obj[37].toString().length() > 0)
                    fVo.setRtrReason(obj[37].toString());
                if (obj[38] != null && obj[38].toString().length() > 0)
                    fVo.setRtrInTime(MyBeanUtils.getInstance().formatString(
                            obj[38].toString()));
                if (obj[39] != null && obj[39].toString().length() > 0)
                    fVo.setRtrOutTime(MyBeanUtils.getInstance().formatString(
                            obj[39].toString()));
                if (obj[40] != null && obj[40].toString().length() > 0)
                    fVo.setRtrCsr(obj[40].toString());
                if (obj[41] != null && obj[41].toString().length() > 0)
                    fVo.setRtrReturnVisitTime(MyBeanUtils.getInstance()
                            .formatString(obj[41].toString()));
                if (obj[42] != null && obj[42].toString().length() > 0)
                    fVo.setRtrRemarke(obj[42].toString());
                if (obj[44] != null && obj[44].toString().length() > 0)
                    fVo.setResevationRepPer(obj[44].toString());
                if (obj[45] != null && obj[45].toString().length() > 0)
                    fVo.setResvStatusName(obj[45].toString());
               /* if (obj[45] != null && obj[45].toString().length() > 0)
                    fVo.setResvTypeName(obj[45].toString());*/
                rows.add(fVo);
            }
        }
        int total = frtResevationDao.getSQLCount(sql.toString(), params);
        dg.setTotal(total);
        dg.setRows(rows);
        return dg;
    }

    /**
     * 查找车结构信息
     */
    public List<FrtResvVehicleStructure> findAllCarFrameWorks(
            FrtResevationVo freVo) throws Exception {
        String vehicle = freVo.getVehicle();
        Integer enterpriseId = freVo.getEnterpriseId();
        List<FrtResvVehicleStructure> vehicleList = null;
        if (vehicle != null && vehicle.length() > 0) {
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            vehicleList = JSON.parseArray(jsVehicle.get("rows").toString(),
                    FrtResvVehicleStructure.class);
        }
        if (vehicleList == null) {
            vehicleList = new ArrayList();
        }
        if (carFrameWorks == null || carFrameWorks.size() == 0) {
            loadCarFrameWorks();
        }
        Short id = freVo.getStateId();
        BasCarBodysStatus bcbs = carFrameWorks.get(id);
        FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
        frs.setId(bcbs.getBodyNum());
        frs.setCode(bcbs.getBodyId());
        frs.setName(bcbs.getBodyName());
        vehicleList.add(frs);
        return vehicleList;
    }

    /**
     * 添加车身结构
     */

    public Datagrid addVehicleStructure(List<FrtResvVehicleStructure> list,
                                        FrtResevationVo freVo) throws Exception {
        if (carFrameWorks == null || carFrameWorks.size() == 0) {
            loadCarFrameWorks();
        }
        Short id = freVo.getStateId();
        if (id == 1) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("1"));
            frs.setCode("2C");
            frs.setName("右脚灯");
            list.add(frs);
        }
        if (id == 2) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("2"));
            frs.setCode("2D");
            frs.setName("右轮眉");
            list.add(frs);
        }
        if (id == 3) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("3"));
            frs.setCode("2E");
            frs.setName("右挡泥板");
            list.add(frs);
        }
        if (id == 4) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("4"));
            frs.setCode("2F");
            frs.setName("右前门");
            list.add(frs);
        }
        if (id == 5) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("5"));
            frs.setCode("2G");
            frs.setName("右前门");
            list.add(frs);
        }
        if (id == 6) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("6"));
            frs.setCode("2H");
            frs.setName("右后门");
            list.add(frs);
        }
        if (id == 7) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("7"));
            frs.setCode("2I");
            frs.setName("右后门");
            list.add(frs);
        }

        if (id == 8) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("8"));
            frs.setCode("2J");
            frs.setName("右后挡泥板");
            list.add(frs);
        }
        if (id == 9) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("9"));
            frs.setCode("2K");
            frs.setName("右后脚");
            list.add(frs);
        }
        if (id == 10) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("10"));
            frs.setCode("2L");
            frs.setName("右后尾灯");
            list.add(frs);
        }
        if (id == 11) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("11"));
            frs.setCode("3A");
            frs.setName("右前脚灯");
            list.add(frs);
        }
        if (id == 12) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("12"));
            frs.setCode("3B");
            frs.setName("右前灯");
            list.add(frs);
        }
        if (id == 13) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("13"));
            frs.setCode("3F");
            frs.setName("右反光镜");
            list.add(frs);
        }
        if (id == 14) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("14"));
            frs.setCode("3G");
            frs.setName("右前窗玻璃");
            list.add(frs);
        }
        if (id == 15) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("15"));
            frs.setCode("3H");
            frs.setName("右中柱");
            list.add(frs);
        }
        if (id == 16) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("16"));
            frs.setCode("3I");
            frs.setName("右后窗玻璃");
            list.add(frs);
        }
        if (id == 17) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("17"));
            frs.setCode("3J");
            frs.setName("有后叶子板");
            list.add(frs);
        }
        if (id == 18) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("18"));
            frs.setCode("3K");
            frs.setName("右后脚");
            list.add(frs);
        }
        if (id == 19) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("19"));
            frs.setCode("4A");
            frs.setName("前保险杠");
            list.add(frs);
        }
        if (id == 20) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("20"));
            frs.setCode("4B");
            frs.setName("中网");
            list.add(frs);
        }
        if (id == 21) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("21"));
            frs.setCode("4C");
            frs.setName("前标");
            list.add(frs);
        }
        if (id == 22) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("22"));
            frs.setCode("4D");
            frs.setName("引擎盖");
            list.add(frs);
        }
        if (id == 23) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("23"));
            frs.setCode("4E");
            frs.setName("右雨刮片");
            list.add(frs);
        }
        if (id == 24) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("24"));
            frs.setCode("4F");
            frs.setName("前挡玻璃");
            list.add(frs);
        }
        if (id == 25) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("25"));
            frs.setCode("4G");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 26) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("26"));
            frs.setCode("4H");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 27) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("27"));
            frs.setCode("4I");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 28) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("28"));
            frs.setCode("4J");
            frs.setName("后档玻璃");
            list.add(frs);
        }
        if (id == 29) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("29"));
            frs.setCode("4K");
            frs.setName("后行李箱盖");
            list.add(frs);
        }
        if (id == 30) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("30"));
            frs.setCode("4L");
            frs.setName("右后尾灯");
            list.add(frs);
        }
        if (id == 31) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("31"));
            frs.setCode("4M");
            frs.setName("右后尾灯");
            list.add(frs);
        }
        if (id == 32) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("32"));
            frs.setCode("4N");
            frs.setName("后保险杠");
            list.add(frs);
        }
        if (id == 33) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("33"));
            frs.setCode("5A");
            frs.setName("前保险杠");
            list.add(frs);
        }
        if (id == 34) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("34"));
            frs.setCode("5B");
            frs.setName("中网");
            list.add(frs);
        }
        if (id == 35) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("35"));
            frs.setCode("5C");
            frs.setName("前标");
            list.add(frs);
        }
        if (id == 36) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("36"));
            frs.setCode("5D");
            frs.setName("引擎盖");
            list.add(frs);
        }
        if (id == 37) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("37"));
            frs.setCode("5E");
            frs.setName("左雨刮片");
            list.add(frs);
        }
        if (id == 38) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("38"));
            frs.setCode("5F");
            frs.setName("前挡玻璃");
            list.add(frs);
        }
        if (id == 39) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("39"));
            frs.setCode("5G");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 40) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("40"));
            frs.setCode("5H");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 41) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("41"));
            frs.setCode("5I");
            frs.setName("车顶");
            list.add(frs);
        }
        if (id == 42) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("42"));
            frs.setCode("5J");
            frs.setName("后档玻璃");
            list.add(frs);
        }
        if (id == 43) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("43"));
            frs.setCode("5K");
            frs.setName("后行李盖");
            list.add(frs);
        }
        if (id == 44) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("44"));
            frs.setCode("5L");
            frs.setName("左后尾灯");
            list.add(frs);
        }
        if (id == 45) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("45"));
            frs.setCode("5M");
            frs.setName("左后尾灯");
            list.add(frs);
        }
        if (id == 46) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("46"));
            frs.setCode("5N");
            frs.setName("后保险杠");
            list.add(frs);
        }
        if (id == 47) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("47"));
            frs.setCode("6F");
            frs.setName("左反光镜");
            list.add(frs);
        }
        if (id == 48) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("48"));
            frs.setCode("6A");
            frs.setName("左前灯");
            list.add(frs);
        }

        if (id == 49) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("49"));
            frs.setCode("6B");
            frs.setName("左前大灯");
            list.add(frs);
        }
        if (id == 50) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("50"));
            frs.setCode("6F");
            frs.setName("左反光镜");
            list.add(frs);
        }
        if (id == 51) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("51"));
            frs.setCode("6G");
            frs.setName("左前窗玻璃");
            list.add(frs);
        }
        if (id == 52) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("52"));
            frs.setCode("6H");
            frs.setName("左中柱");
            list.add(frs);
        }
        if (id == 53) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("53"));
            frs.setCode("6I");
            frs.setName("左后窗玻璃");
            list.add(frs);
        }
        if (id == 54) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("54"));
            frs.setCode("6J");
            frs.setName("左后叶子板");
            list.add(frs);
        }
        if (id == 55) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("55"));
            frs.setCode("6K");
            frs.setName("左后脚");
            list.add(frs);
        }
        if (id == 56) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("56"));
            frs.setCode("7C");
            frs.setName("右脚灯");
            list.add(frs);
        }
        if (id == 57) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("57"));
            frs.setCode("7D");
            frs.setName("右轮眉");
            list.add(frs);
        }
        if (id == 58) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("58"));
            frs.setCode("7E");
            frs.setName("右挡泥板");
            list.add(frs);
        }
        if (id == 59) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("59"));
            frs.setCode("7F");
            frs.setName("左前门");
            list.add(frs);
        }
        if (id == 60) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("60"));
            frs.setCode("7G");
            frs.setName("左前门");
            list.add(frs);
        }
        if (id == 61) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("61"));
            frs.setCode("7H");
            frs.setName("左后门");
            list.add(frs);
        }
        if (id == 62) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("62"));
            frs.setCode("7I");
            frs.setName("左后门");
            list.add(frs);
        }
        if (id == 63) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("63"));
            frs.setCode("7J");
            frs.setName("左后挡泥板");
            list.add(frs);
        }
        if (id == 64) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("64"));
            frs.setCode("7K");
            frs.setName("左后角");
            list.add(frs);
        }
        if (id == 62) {
            FrtResvVehicleStructure frs = new FrtResvVehicleStructure();
            frs.setId(new Short("62"));
            frs.setCode("7L");
            frs.setName("左后尾灯");
            ;
            list.add(frs);
        }
        Datagrid Datagrid = new Datagrid();
        Datagrid.setTotal(list.size());
        Datagrid.setRows(list);

        return Datagrid;
    }

    /**
     * 移除车身结构
     */

    public List<FrtResvVehicleStructure> removeVehicleStructure(
            FrtResevationVo freVo) throws Exception {
        List<FrtResvVehicleStructure> list = null;
        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0) {
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            list = JSON.parseArray(jsVehicle.get("rows").toString(),
                    FrtResvVehicleStructure.class);
        }
        if (list == null) {
            list = new ArrayList();
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(freVo.getStateId())) {
                    list.remove(list.get(i));
                }
            }

        } catch (Exception e) {
            logger.error("移除车身结构", e);
        }
        return list;
    }

    /**
     * 根据车档案id查询车档案
     */

    public FrtCarVo getFrtCar(String carId) throws Exception {
        FrtCar fc = frtCarDao.get(FrtCar.class, carId);
        FrtCarVo fcVo = new FrtCarVo();
        fcVo.setCustomId(fc.getFrtCustom().getCustomId());
        fcVo.setCarVin(fc.getCarVin());
        fcVo.setCarRelationPerson(fc.getCarRelationPerson());
        fcVo.setCarRelationTel1(fc.getCarRelationTel1());
        fcVo.setCarRelationTel2(fc.getCarRelationTel2());
        fcVo.setReceptionRemark(fc.getReceptionRemark());
        return fcVo;
    }

    /**
     * 保存预约/保险估价单
     */

    public Msg save(Boolean flag, FrtResevationVo freVo) throws Exception {
        if (flag == true) {
            return saveFrtInsurePrize(flag, freVo);
        } else {
            return saveFrtResevation(flag, freVo);
        }
    }

    private Msg saveFrt(Boolean flag, FrtResevationVo freVo) throws Exception {

        List<PartsVo> partsList = null;
        List<FrtItemVo> itemList = null;
        List<FrtResvVehicleStructure> frvsList = null;

        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0) {
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            frvsList = JSON.parseArray(jsVehicle.get("rows").toString(),
                    FrtResvVehicleStructure.class);
        }
        String items = freVo.getItems();
        if (items != null && items.length() > 0) {
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0) {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        FrtResevation fre = new FrtResevation();
        FrtResvParts frtResvParts = null;
        FrtResvItem frtResvItem = null;
        FrtResvVehicleStructure frtResvVehicleStructure = null;
        Msg msg = new Msg();
        try {
            // BeanUtils.copyProperties(freVo, fre, new String[]{"resvId"});
            MyBeanUtils.getInstance().copyBeans(freVo, fre);
            if (flag == true) {
                fre.setBespeakPrizeTag(Contstants.DELETE_TAG.DELETEYES);
                fre.setResvId(CreateID.createId("frtInsurePrize",
                        Contstants.INSUREDOCUMENTID));
            } else {
                fre.setBespeakPrizeTag(Contstants.DELETE_TAG.DELETENO);
                fre.setResvId(CreateID.createId("FrtResevation",
                        Contstants.BESPEAKDOCUMENTID));
            }
            fre.setFrtresvFlg(Contstants.DELETE_TAG.DELETENO);
            fre.setResvToRcpt(Contstants.NEARCAR_TAG.NEARCARNO);
            FrtCar car = frtCarDao.get(FrtCar.class, freVo.getCarId());
            if (fre.getResvFixpel() != null && fre.getResvFixpel().length() > 0)
                car.setCarRelationPerson(fre.getResvFixpel());
            if (fre.getResvFixtel() != null && fre.getResvFixtel().length() > 0)
                car.setCarRelationTel1(fre.getResvFixtel());
            if (fre.getResvFixphone() != null
                    && fre.getResvFixphone().length() > 0)
                car.setCarRelationTel2(fre.getResvFixphone());
            if (freVo.getCarRealationSex() != null
                    && freVo.getCarRealationSex().length() > 0)
                car.setCarRealationSex(freVo.getCarRealationSex());
            fre.setFrtCar(car);
            FrtCustom custom = (FrtCustom) frtCustomDao.get("from  FrtCustom  fc where fc.customId='" + freVo.getCustomId() + "'");
            fre.setFrtCustom(custom);
            fre.setStfId(freVo.getStfId());
            fre.setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING);
            fre.setEnterpriseId(freVo.getEnterpriseId());
            Serializable frId = frtResevationDao.save(fre);
            msg.setObj(frId);
            if (partsList != null && partsList.size() > 0) {
                for (PartsVo partsVo : partsList) {
                    frtResvParts = new FrtResvParts();
                    frtResvParts.setPartsName(partsVo.getPartsName());
//                    BasPartsUnit basPartsUnit = new BasPartsUnit();
//                    basPartsUnit.setPunitId(partsVo.getPunitId());
//                    basPartsUnit.setPunitName(partsVo.getPunitName());
//                    frtResvParts.setBasPartsUnit(basPartsUnit);
                    if (partsVo.getPunitId() != null) {
                        frtResvParts.setBasPartsUnit(basPartsUnitDAO.get(BasPartsUnit.class, partsVo.getPunitId()));
                    } else {
                        frtResvParts.setBasPartsUnit(null);
                    }
                    frtResvParts.setPartsNum(partsVo.getPartsNum());
                    frtResvParts.setPartsRepairPrice(partsVo
                            .getPartsRepairPrice());
                    frtResvParts.setPartsAmount(partsVo.getPartsAmount());
                    frtResvParts.setFrtResevation(fre);
                    frtResvParts.setPartsId(partsVo.getPartsId());
                    frtResvParts.setStoreId(partsVo.getStoreId());
                    frtResvParts.setEnterpriseId(freVo.getEnterpriseId());
                    frtResvPartsDao.save(frtResvParts);
                }
            }
            if (itemList != null && itemList.size() > 0) {
                for (FrtItemVo itemVo : itemList) {
                    frtResvItem = new FrtResvItem();
                    frtResvItem.setFrtResevation(fre);
                    frtResvItem.setRepitemAmount(itemVo.getRepitemAmount());
                    frtResvItem.setRepitemId(itemVo.getRepitemId());
                    frtResvItem.setRepitemName(itemVo.getRepitemName());
                    frtResvItem.setRepitemNum(itemVo.getRepitemTime());
                    frtResvItem.setRepitemInnerNum(itemVo.getInternalTime());
                    frtResvItem.setStfId(itemVo.getStfId());
                    frtResvItem.setEnterpriseId(freVo.getEnterpriseId());
                    frtResvItemDao.save(frtResvItem);
                }
            }

            if (frvsList != null && frvsList.size() > 0) {
                for (FrtResvVehicleStructure frtrvStructure : frvsList) {
                    frtResvVehicleStructure = new FrtResvVehicleStructure();
                    // frtResvVehicleStructure.setId(new Short("0"));
                    frtResvVehicleStructure.setFrtResevation(fre);
                    frtResvVehicleStructure.setName(frtrvStructure
                            .getName());
                    frtResvVehicleStructure.setCode(frtrvStructure
                            .getCode());
                    frtResvVehicleStructure.setState(frtrvStructure
                            .getState());
                    frtResvVehicleStructure.setEnterpriseId(freVo.getEnterpriseId());
                    frtResvVehicleStructureDao
                            .save(frtResvVehicleStructure);
                }
            }
            if (freVo.getRtrServices() != null
                    && !"".equals(freVo.getRtrServices())) {
                FrtRushToRepair frtr = new FrtRushToRepair();
                // BeanUtils.copyProperties(freVo, frtr);
                MyBeanUtils.getInstance().copyBeans(freVo, frtr);
                frtr.setRtrId(fre.getResvId());
                fre.setFrtRushToRepair(frtr);
                frtr.setFrtResevation(fre);
                frtr.setEnterpriseId(freVo.getEnterpriseId());
                frtRushToRepairDao.save(frtr);
            }
            if (flag == true)
                msg.setMsg("保存保险估价单成功！");
            else
                msg.setMsg("保存预约估价单成功!");
            msg.setSuccess(true);
        } catch (Exception e) {
            if (flag == true)
                msg.setMsg("保存保险估价单失败！");
            else
                msg.setMsg("保存预约估价单失败!");
            msg.setSuccess(false);
        }
        return msg;
    }

    /**
     * 新增预约申请单
     */
    @Log(moduleName = "前台管理", content = "新增预约申请单", opertype = "新增")
    private Msg saveFrtResevation(Boolean flag, FrtResevationVo freVo) throws Exception {
        return saveFrt(flag, freVo);
    }

    /**
     * 新增保险估价单
     */
    @Log(moduleName = "前台管理", content = "新增保险估价单", opertype = "新增")
    private Msg saveFrtInsurePrize(Boolean flag, FrtResevationVo freVo) throws Exception {
        return saveFrt(flag, freVo);
    }

    private void copyData(List<PartsVo> partsList, List<FrtItemVo> itemList,
                          List<FrtResvVehicleStructure> frvsList, FrtResevationVo freVo,
                          FrtResevation fre) throws Exception {
        if (freVo.getRtrServices() != null
                && !"".equals(freVo.getRtrServices())) {
            FrtRushToRepair frtr = new FrtRushToRepair();
            // BeanUtils.copyProperties(freVo, frtr);
            MyBeanUtils.getInstance().copyBeans(freVo, frtr);
            frtr.setRtrId(fre.getResvId());
            fre.setFrtRushToRepair(frtr);
            frtr.setEnterpriseId(freVo.getEnterpriseId());
            frtr.setFrtResevation(fre);
        } else {
            freVo.setVehicleFlag(true);
        }
        if (partsList != null && partsList.size() > 0) {
            for (PartsVo pVo : partsList) {
                FrtResvParts frp = new FrtResvParts();
                // BeanUtils.copyProperties(pVo, frp);
                MyBeanUtils.getInstance().copyBeans(pVo, frp);
                if (pVo.getPunitId() != null) {
                    frp.setBasPartsUnit(basPartsUnitDAO.get(BasPartsUnit.class, pVo.getPunitId()));
                } else {
                    frp.setBasPartsUnit(null);
                }
                frp.setFrtResevation(fre);
                frp.setEnterpriseId(freVo.getEnterpriseId());
                fre.getFrtResvPartses().add(frp);
            }
        }
        if (itemList != null && itemList.size() > 0) {
            for (FrtItemVo iVo : itemList) {
                // repitemAmount
                FrtResvItem fri = new FrtResvItem();
                fri.setFrtResevation(fre);
                fri.setRepitemAmount(iVo.getRepitemAmount());
                fri.setRepitemId(iVo.getRepitemId());
                fri.setRepitemName(iVo.getRepitemName());
                fri.setRepitemNum(iVo.getRepitemTime());
                fri.setRepitemInnerNum(iVo.getInternalTime());
                fri.setStfId(iVo.getStfId());
                fri.setFrtResevation(fre);
                fri.setEnterpriseId(freVo.getEnterpriseId());
                fre.getFrtResvItems().add(fri);
            }
        }
        if (frvsList != null && frvsList.size() > 0) {
            for (FrtResvVehicleStructure frvs : frvsList) {
                frvs.setFrtResevation(fre);
                frvs.setEnterpriseId(freVo.getEnterpriseId());
                fre.getFrtResvVehicleStructures().add(frvs);
            }
        }
    }

    /**
     * 删除预约/保险估价单
     */

    public Msg remove(Boolean flag, String id) throws Exception {
        if (flag == true) {
            return removeFrtInsurePrize(flag, id);
        } else {
            return removeFrtResevation(flag, id);
        }
    }

    /**
     * 删除预约申请单
     */
    @Log(moduleName = "前台管理", content = "删除预约申请单", opertype = "删除")
    private Msg removeFrtResevation(Boolean flag, String id) throws Exception {
        return removeFrt(flag, id);
    }

    /**
     * 删除保险估价单
     */
    @Log(moduleName = "前台管理", content = "删除保险估价单", opertype = "删除")
    private Msg removeFrtInsurePrize(Boolean flag, String id) throws Exception {
        return removeFrt(flag, id);
    }

    private Msg removeFrt(Boolean flag, String id) throws Exception {
        Msg msg = new Msg();
        try {
            FrtResevation fr = frtResevationDao.get(FrtResevation.class, id);
            fr.setFrtresvFlg(Contstants.DELETE_TAG.DELETEYES);
            if (flag == true) {
                msg.setMsg("删除保险估价单成功！");
            } else {
                msg.setMsg("删除预约估价单成功！");
            }
            msg.setSuccess(true);
        } catch (Exception es) {
            if (flag == true) {
                msg.setMsg("删除保险估价单失败！");
            } else {
                msg.setMsg("删除预约估价单失败！");
            }
        }
        return msg;
    }

    /**
     * 更新预约/保险估价单
     */

    public Msg edit(Boolean flag, FrtResevationVo freVo) throws Exception {
        if (flag == true) {
            return editFrtInsurePrize(flag, freVo);
        } else {
            return editFrtResevation(flag, freVo);
        }
    }

    /**
     * 更新预约申请单
     */
    @Log(moduleName = "前台管理", content = "更新预约申请单", opertype = "更新")
    private Msg editFrtResevation(Boolean flag, FrtResevationVo freVo) throws Exception {
        return editFrt(flag, freVo);
    }

    /**
     * 更新保险估价单
     */
    @Log(moduleName = "前台管理", content = "更新保险估价单", opertype = "更新")
    private Msg editFrtInsurePrize(Boolean flag, FrtResevationVo freVo) throws Exception {
        return editFrt(flag, freVo);
    }

    private Msg editFrt(Boolean flag, FrtResevationVo freVo) throws Exception {

        List<PartsVo> partsList = null;
        List<FrtItemVo> itemList = null;
        List<FrtResvVehicleStructure> frvsList = null;
        String vehicle = freVo.getVehicle();
        if (vehicle != null && vehicle.length() > 0) {
            JSONObject jsVehicle = JSON.parseObject(vehicle);
            frvsList = JSON.parseArray(jsVehicle.get("rows").toString(), FrtResvVehicleStructure.class);
        }
        String items = freVo.getItems();
        if (items != null && items.length() > 0) {
            JSONObject jsItems = JSON.parseObject(items);
            itemList = JSON.parseArray(jsItems.get("rows").toString(), FrtItemVo.class);
        }
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0) {
            JSONObject jsParts = JSON.parseObject(parts);
            partsList = JSON.parseArray(jsParts.get("rows").toString(), PartsVo.class);
        }
        FrtResevation fre = new FrtResevation();
        Msg msg = new Msg();
        // BeanUtils.copyProperties(freVo, fre);
        MyBeanUtils.getInstance().copyBeans(freVo, fre);
        FrtCustom fc = new FrtCustom();
        // BeanUtils.copyProperties(freVo,fc);
        MyBeanUtils.getInstance().copyBeans(freVo, fc);
        fre.setFrtCustom(fc);
        FrtCar fcr = new FrtCar();
        // BeanUtils.copyProperties(freVo, fcr);
        MyBeanUtils.getInstance().copyBeans(freVo, fcr);
        fre.setFrtCar(fcr);
        // fcr.setFrtCustom(fc);
        fre.setFrtresvFlg(Contstants.DELETE_TAG.DELETENO);
        fre.setResvToRcpt(Contstants.NEARCAR_TAG.NEARCARNO);
        try {
            copyData(partsList, itemList, frvsList, freVo, fre);
            FrtResevation temp = frtResevationDao.get(FrtResevation.class, fre
                    .getResvId());
            if (items == null || items.length() == 0)
                fre.setFrtResvItems(temp.getFrtResvItems());
            if (parts == null || parts.length() == 0)
                fre.setFrtResvPartses(temp.getFrtResvPartses());
            if (vehicle == null || vehicle.length() == 0)
                fre.setFrtResvVehicleStructures(temp
                        .getFrtResvVehicleStructures());
            if (freVo.getVehicleFlag() != null
                    && freVo.getVehicleFlag() == true) {
                fre.setFrtRushToRepair(temp.getFrtRushToRepair());
            }
            if (flag == true) {
                fre.setBespeakPrizeTag(Contstants.DELETE_TAG.DELETEYES);
            } else {
                fre.setBespeakPrizeTag(Contstants.DELETE_TAG.DELETENO);
            }
            fre.setEnterpriseId(freVo.getEnterpriseId());
            frtResevationDao.merge(fre);
            // frtResevationDao.updateFrtResevation(fre);
            // frtResevationDao.flush();
            // frtResevationDao.clear();
            if (flag == true) {
                msg.setMsg("更新保险估价单成功！");
            } else {
                msg.setMsg("更新预约估价单成功!");
            }
            msg.setSuccess(true);
        } catch (Exception e) {
            if (flag == true) {
                msg.setMsg("更新保险估价单失败！");
            } else {
                msg.setMsg("更新预约估价单失败！");
            }
        }
        return msg;
    }

    /**
     * 从数据库中查询维修配件
     */

    public Datagrid findPartsByResvId(String resvId, Integer enterpriseId) throws Exception {
        List<PartsVo> partsList = new ArrayList();
        List<FrtResvParts> resvPartsList = frtResvPartsDao
                .find("from FrtResvParts frp where frp.frtResevation.resvId ='"
                        + resvId + "'");
        PartsVo pVo = null;
        if (resvPartsList != null && resvPartsList.size() > 0) {
            for (FrtResvParts frp : resvPartsList) {
                pVo = new PartsVo();
                // BeanUtils.copyProperties(frp, pVo);
                MyBeanUtils.getInstance().copyBeans(frp, pVo);
                pVo.setClaimsId(frtService.getDefaultClaimsType(enterpriseId));
                pVo.setClaimsName(frtService.getDefaultClaimsTypeName(enterpriseId));
                pVo.setReptypId(frtService.getDefaultRepairType(enterpriseId));
                pVo.setReptypName(frtService.getDefaultRepairTypeName(enterpriseId));
                if (frp.getBasPartsUnit() != null)
                    pVo.setPunitId(frp.getBasPartsUnit().getPunitId());
                pVo.setPunitName(null);
                partsList.add(pVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(partsList);
        dg.setTotal(partsList.size());
        return dg;
    }

    /**
     * 从数据库中查询维修项目
     */

    public Datagrid findItemByResvId(String resvId, Integer id) throws Exception {
        List<FrtItemVo> itemList = new ArrayList();
        List<FrtResvItem> resvItemList = frtResvItemDao
                .find("from FrtResvItem fri where fri.frtResevation.resvId = '"
                        + resvId + "'");
        String sql = "select * from frt_resv_item where RESV_ID='"
                + resvId.trim() + "'";
        FrtItemVo iVo = null;
        if (resvItemList != null && resvItemList.size() > 0) {
            for (FrtResvItem fr : resvItemList) {
                iVo = new FrtItemVo();
                // BeanUtils.copyProperties(fr, iVo);
                MyBeanUtils.getInstance().copyBeans(fr, iVo);
                iVo.setInternalTime(fr.getRepitemInnerNum());
                iVo.setRepitemTime(fr.getRepitemNum());
                BasStuff bs = basStuffDao
                        .get(" from BasStuff bs where bs.stfId="
                                + iVo.getStfId());
                if (bs != null) {
                    iVo.setStfName(bs.getStfName());
                }
                iVo.setClaimsId(frtService.getDefaultClaimsType(id));
                iVo.setClaimsName(frtService.getDefaultClaimsTypeName(id));
                iVo.setReptypId(frtService.getDefaultRepairType(id));
                iVo.setReptypName(frtService.getDefaultRepairTypeName(id));
                itemList.add(iVo);
            }
        }
        Datagrid dg = new Datagrid();
        dg.setRows(itemList);
        dg.setTotal(itemList.size());
        return dg;
    }

    /**
     * 从数据库中抢修信息
     */

    public FrtRushToRepair findServiceByResvId(String resvId) throws Exception {
        // TODO Auto-generated method stub
        return frtRushToRepairDao.get(FrtRushToRepair.class, resvId);
    }

    /**
     * 查找车辆结构数据
     */

    public Datagrid findVehicleStructure(FrtResevationVo freVo)
            throws Exception {
        // TODO Auto-generated method stub
        List<FrtResvVehicleStructure> vehicleList = new ArrayList();
        List<Object[]> list = frtResvVehicleStructureDao
                .createSQLQuery("SELECT brvs.*,bcbs.BODY_NUM FROM frt_resv_vehicle_structure brvs,bas_car_bodys_status bcbs	WHERE brvs.CODE=bcbs.BODY_ID AND  brvs.RESV_ID='"
                        + freVo.getResvId() + "'");
        FrtResvVehicleStructure fst = null;
        if (list != null && list.size() > 0)
            for (Object[] objects : list) {
                fst = new FrtResvVehicleStructure();
                if (objects[5] != null && objects[5].toString().length() > 0)
                    fst.setId(Short.parseShort(objects[5].toString()));
                if (objects[1] != null && objects[1].toString().length() > 0)
                    fst.setCode(objects[1].toString());
                // FrtResevation fr=new FrtResevation();
                // fr.setResvId(objects[2].toString());
                // fst.setFrtResevation(fr);
                if (objects[3] != null && objects[3].toString().length() > 0)
                    fst.setName(objects[3].toString());
                if (objects[4] != null && objects[4].toString().length() > 0)
                    fst.setState(objects[4].toString());
                vehicleList.add(fst);
            }
        Datagrid dg = new Datagrid();
        dg.setTotal(vehicleList.size());
        dg.setRows(vehicleList);
        return dg;
    }

    /**
     * 更改预约状态
     *
     * @throws Exception
     */

    public void isGoFactory(FrtResevation frv) throws Exception {
        Msg msg = new Msg();
        Date enterTime = frv.getResvEnterTime();
        Date nowDate = new Date();
        long time1 = enterTime.getTime();
        long time2 = nowDate.getTime();
        isOldOrBeforeOrOntime(time1, time2, frv);
    }

    public void isOldOrBeforeOrOntime(Long time1, Long time2, FrtResevation frv) {
        double temp = (double) (time2 - time1) / (1000 * 60);
        if (Math.abs(temp) <= Contstants.ONTIMEGOFACTORYOFFSET) {
            // 准时进厂
            frv
                    .setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_ONTIMEGO);
        } else {
            if (temp < 0) {
                // 预约提前
                frv
                        .setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKBEF);
            } else {
                // 预约延误
                frv
                        .setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOLD);
                // int tempDay=temp/60;
                // if(tempDay<=Contstants.DELAYGOFACTORYMETE){
                // //预约延误
                // frv.setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOLD);
                // }else{
                // //预约取消
                // frv.setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF);
                // }
            }
        }
    }

    /**
     * 转为前台接车单
     */

    public Msg saveSwitchFrt(Boolean flag, FrtResevationVo freVo)
            throws Exception {
        if (flag == true) {
            return saveSwitchFrtAsFrtInsurePrize(flag, freVo);
        } else {
            return saveSwitchFrtAsFrtResevation(flag, freVo);
        }
    }

    /**
     * 预约申请单转前台接车
     */
    @Log(moduleName = "前台管理", content = "预约申请单转前台接车", opertype = "更新/转前台接车")
    private Msg saveSwitchFrtAsFrtResevation(Boolean flag, FrtResevationVo freVo)
            throws Exception {
        return saveSwitchFrtAsFrt(flag, freVo);
    }

    /**
     * 保险估价单转前台接车
     */
    @Log(moduleName = "前台管理", content = "保险估价单转前台接车", opertype = "更新/转前台接车")
    private Msg saveSwitchFrtAsFrtInsurePrize(Boolean flag, FrtResevationVo freVo)
            throws Exception {
        return saveSwitchFrtAsFrt(flag, freVo);
    }

    private Msg saveSwitchFrtAsFrt(Boolean flag, FrtResevationVo freVo)
            throws Exception {
        // TODO Auto-generated method stub
        Msg msg = new Msg();
        try {
            String defaultClaimsType = frtService.getDefaultClaimsType(freVo.getEnterpriseId());
            if (defaultClaimsType == null || "".equals(defaultClaimsType)) {
                msg.setMsg("请设置索赔性质!");
                msg.setSuccess(true);
                return msg;
            }
            String defaultClaimsTypeName = frtService.getDefaultClaimsTypeName(freVo.getEnterpriseId());
            if (defaultClaimsTypeName == null || "".equals(defaultClaimsTypeName)) {
                msg.setMsg("请设置索赔性质!");
                msg.setSuccess(true);
                return msg;
            }
            String defaultRepairType = frtService.getDefaultRepairType(freVo.getEnterpriseId());
            if (defaultRepairType == null || "".equals(defaultRepairType)) {
                msg.setMsg("请设置收费性质!");
                msg.setSuccess(true);
                return msg;
            }
            String defaultRepairTypeName = frtService.getDefaultRepairTypeName(freVo.getEnterpriseId());
            if (defaultRepairTypeName == null || "".equals(defaultRepairTypeName)) {
                msg.setMsg("请设置收费性质!");
                msg.setSuccess(true);
                return msg;
            }
            FrtReception fr = new FrtReception();
            FrtResevation frv = frtResevationDao.get(FrtResevation.class, freVo
                    .getResvId());
            frv.setResvToRcpt(Contstants.NEARCAR_TAG.NEARCARYES);

            isGoFactory(frv);// 更改预约状态

            frtResevationDao.merge(frv);
            fr.setFrtCustom(frv.getFrtCustom());
            fr.setFrtCar(frv.getFrtCar());
            Reptype rt = new Reptype();
            rt.setReptId(frv.getReptId());
            fr.setReptype(rt);
            fr.setResvId(frv.getResvId());
            fr.setReceptionDistance(frv.getResvDistance());
            fr.setReceptionMaintFlg(Contstants.MAINTAIN_TAG.MAINTAINNO
                    .toString());
            fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState1
                    .toString());
            fr.setRepgrpId(frv.getRepgrpId());
            fr.setRepwkId(frv.getRepwkId());
            fr.setReceptionEndTime(null);
            fr.setReceptionTechnician(null);
            fr.setReceptionInsurPer(null);
            fr.setReceptionRepPer(frv.getResevationRepPer());
            fr.setPropRepPer(frv.getResvFixpel());
            fr.setPropPhone(frv.getResvFixphone());
            fr.setPropTel(frv.getResvFixtel());
            fr.setValuables(Contstants.VALUABLESRES_TAG.VALUABLESRESNO);
            fr.setFuelSituation(null);
            fr.setOldPieces(null);
            fr.setFinComId(null);
            fr.setFinStatus(Contstants.TOWORKSHOP_TAG.TOWORKSHOPNO);
            fr.setExpDelCarTime(null);
            fr.setProblemDesc(null);
            fr.setReceptionRemark(null);
            fr.setInterDate(new Date());
            fr.setReceptor(null);
            fr.setFrtReceptionCosts(null);
            fr.setCorrection(Contstants.WORK_TAG.WORKNO);
            fr.setRcptFlg(Contstants.DELETE_TAG.DELETENO);
            fr.setManagementFee(0.00d);
            fr.setFinAllTag(Contstants.CLAIM_TAG.NOCLAIM);
            fr.setFinTag(Contstants.CLAIM_TAG.NOCLAIM);
            fr.setReceptionStatus(Contstants.DOCUMENT_TAG.DOCUMENTState0
                    .toString());
            fr.setRcptBranch(frtService.getDefaultRcptBranch());
            fr.setEnterpriseId(freVo.getEnterpriseId());
            copydata(frv.getFrtResvPartses(), frv.getFrtResvItems(), frv
                    .getFrtResvVehicleStructures(), fr, defaultClaimsType, defaultClaimsTypeName,
                    defaultRepairType, defaultRepairTypeName);
            // fr.setFrtRcptPartses();
            // fr.setFrtRcptItems();
            // fr.setFrtReceptionVehicleStructures();
            fr.setReceptionId(CreateID.createId("FrtReception",
                    Contstants.PROSCENIUMNEARID));
            frtReceptionDao.save(fr);
            msg.setMsg("转前台接车成功!");
            msg.setSuccess(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg.setMsg("转前台接车失败！");
            msg.setSuccess(false);
        }
        return msg;
    }

    private void copydata(Set<FrtResvParts> partsSet, Set<FrtResvItem> itemSet,
                          Set<FrtResvVehicleStructure> structureSet, FrtReception fr,
                          String defaultClaimsType, String defaultClaimsTypeName, String defaultRepairType, String defaultRepairTypeName)
            throws Exception {
        if (partsSet != null && partsSet.size() > 0) {
            FrtRcptParts frp = null;
            for (FrtResvParts frtResvParts : partsSet) {
                frp = new FrtRcptParts();
                frp.setPunitId(frtResvParts.getBasPartsUnit().getPunitId());
                frp.setFrtReception(fr);
                frp.setPartsAmount(frtResvParts.getPartsAmount());
                frp.setPartsId(frtResvParts.getPartsId());
                frp.setPartsName(frtResvParts.getPartsName());
                frp.setPartsNum(frtResvParts.getPartsNum());
                frp.setPartsRepairPrice(frtResvParts.getPartsRepairPrice());
                frp.setStoreId(frtResvParts.getStoreId());
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setClaimsId(Short.parseShort(defaultClaimsType));
                basClaimsType.setEnterpriseId(fr.getEnterpriseId());
                basRepairType.setReptypId(Short.parseShort(defaultRepairType));
                basRepairType.setEnterpriseId(fr.getEnterpriseId());
                frp.setBasClaimsType(basClaimsType);
                frp.setBasRepairType(basRepairType);
                frp.setEnterpriseId(fr.getEnterpriseId());
                frtRcptPartsDao.save(frp);
                // fr.getFrtRcptPartses().add(frp);
            }
        }
        if (itemSet != null && itemSet.size() > 0) {
            FrtRcptItem fri = null;
            for (FrtResvItem frtResvItem : itemSet) {
                fri = new FrtRcptItem();
                fri.setRepitemId(frtResvItem.getRepitemId());
                fri.setRepitemName(frtResvItem.getRepitemName());
                fri.setRepitemNum(frtResvItem.getRepitemNum());
                fri.setRcptitemInnerTime(frtResvItem.getRepitemInnerNum());
                fri.setRepitemAmount(frtResvItem.getRepitemAmount());
                fri.setStfId(frtResvItem.getStfId());
                BasClaimsType basClaimsType = new BasClaimsType();
                BasRepairType basRepairType = new BasRepairType();
                basClaimsType.setClaimsId(Short.parseShort(defaultClaimsType));
                basClaimsType.setEnterpriseId(fr.getEnterpriseId());
                basRepairType.setReptypId(Short.parseShort(defaultRepairType));
                basRepairType.setEnterpriseId(fr.getEnterpriseId());
                fri.setBasClaimsType(basClaimsType);
                fri.setBasRepairType(basRepairType);
                fri.setReceptionRemark("");
                fri.setFrtReception(fr);
                fri.setEnterpriseId(fr.getEnterpriseId());
                frtRcptItemDao.save(fri);
                // fr.getFrtRcptItems().add(fri);
            }
        }
        if (structureSet != null && structureSet.size() > 0) {
            FrtReceptionVehicleStructure frvs = null;
            for (FrtResvVehicleStructure frtResvVehicleStructure : structureSet) {
                frvs = new FrtReceptionVehicleStructure();
                // frvs.setId(frtResvVehicleStructure.getId());
                frvs.setName(frtResvVehicleStructure.getName());
                frvs.setCode(frtResvVehicleStructure.getCode());
                frvs.setState(frtResvVehicleStructure.getState());
                frvs.setEnterpriseId(fr.getEnterpriseId());
                frvs.setFrtReception(fr);
                frtReceptionVehicleStructureDao.save(frvs);
                // fr.getFrtReceptionVehicleStructures().add(frvs);
            }
        }
    }

    /**
     * 增加自定义计划项目
     */

    public List<FrtItemVo> addFrtResevationItem(FrtResevationVo freVo)
            throws Exception {
        List<FrtItemVo> itemList = null;
        String item = freVo.getItems();
        if (item != null && item.length() > 0) {
            JSONObject jsItems = JSON.parseObject(item);
            itemList = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (itemList == null) {
            itemList = new ArrayList();
        }
        FrtItemVo iv = new FrtItemVo();
        iv.setRepitemId(IncrementId.getItemId());
        iv.setRepitemName(Contstants.DESIGNREPITEMNAME);
        iv.setRepitemNum(1d);
        iv.setRepitemTime(Contstants.REPITEMTIME);
        iv.setInternalTime(Contstants.INTERNALTIME);
        iv.setRepitemAmount(Contstants.REPITEMAMOUNT);
        itemList.add(iv);
        return itemList;
    }

    /**
     * 查询套餐项目并添加到预约/保险估价单中
     */

    public List<FrtItemVo> findItemListByRpId(FrtResevationVo freVo)
            throws Exception {
        List<FrtItemVo> list = null;
        String item = freVo.getItems();
        if (item != null && item.length() > 0) {
            JSONObject jsItems = JSON.parseObject(item);
            list = JSON.parseArray(jsItems.get("rows").toString(),
                    FrtItemVo.class);
        }
        if (list == null) {
            list = new ArrayList();
        }
        StringBuffer sb = new StringBuffer(
                "SELECT REPITEM_ID,REPITEM_NAME,REPITEM_NUM,REPITEM_AMOUNT");
        sb.append(" FROM  bas_repair_package_item brpi WHERE brpi.RP_ID="
                + freVo.getRpId() + "  and  brpi.enterprise_id=" + freVo.getEnterpriseId());
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        FrtItemVo fiVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList) {
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
                // for (FrtItemVo frtItemVo : list) {
                // if(frtItemVo.getRepitemId().equals(fiVo.getRepitemId())){
                // list.remove(frtItemVo);
                // }
                // }
                list.add(fiVo);
            }
        return list;
    }

    /**
     * 查询套餐配件并添加到预约/保险估价单中
     */

    public List<PartsVo> findPartsListByRpId(FrtResevationVo freVo)
            throws Exception {
        List<PartsVo> list = null;
        String parts = freVo.getParts();
        if (parts != null && parts.length() > 0) {
            JSONObject jsParts = JSON.parseObject(parts);
            list = JSON.parseArray(jsParts.get("rows").toString(),
                    PartsVo.class);
        }
        if (list == null) {
            list = new ArrayList();
        }
        StringBuffer sb = new StringBuffer(
                "SELECT brpp.PARTS_ID,brpp.PARTS_NAME,bpu.PUNIT_ID,bpu.PUNIT_NAME,");
        sb
                .append(" brpp.PARTS_NUM,brpp.PARTS_REPAIR_PRICE,brpp.PARTS_AMOUNT,brpp.STORE_ID");
        sb.append(" FROM bas_repair_package_parts brpp,bas_parts_unit bpu");
        sb.append(" WHERE bpu.PUNIT_ID=brpp.PUNIT_ID AND brpp.RP_ID="
                + freVo.getRpId() + " and  brpp.enterprise_id=" + freVo.getEnterpriseId());
        List<Object[]> rowsList = frtReceptionDao.createSQLQuery(sb.toString());
        PartsVo pVo = null;
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] objects : rowsList) {
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
     * 判断某个预约单是否取消
     */

    public Msg findBespeakOffById(FrtResevationVo freVo) throws Exception {
        Msg msg = new Msg();
        String hql = "from FrtResevation fr where fr.frtresvFlg="
                + Contstants.DELETE_TAG.DELETENO;
        hql += " and fr.resvStatus='"
                + Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING
                + "' and fr.resvId='" + freVo.getResvId() + "'";
        FrtResevation fr = frtResevationDao.get(hql);
        if (fr != null) {
            msg.setSuccess(true);
        } else {
            msg.setSuccess(false);
            msg.setMsg("此信息不存在！");
        }
        return msg;
    }

    /**
     * 取消预约单
     */

    public Msg modifyIsBespeakOffById(FrtResevationVo freVo) throws Exception {
        Msg msg = new Msg();
        String hql = "from FrtResevation fr where fr.frtresvFlg="
                + Contstants.DELETE_TAG.DELETENO;
        hql += " and fr.resvStatus='"
                + Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKING
                + "' and fr.resvId='" + freVo.getResvId() + "'";
        FrtResevation fr = frtResevationDao.get(hql);
        if (fr != null) {
            isCancel(fr, msg);
        } else {
            msg.setSuccess(false);
            msg.setMsg("此信息不存在！");
        }
        return msg;
    }

    public void isCancel(FrtResevation fr, Msg msg) throws Exception {
        Date nowDate = new Date();
        isCancel(fr, msg, nowDate);
    }

    public void isCancel(FrtResevation fr, Msg msg, Date nowDate)
            throws Exception {
        if (fr != null) {
            fr.setResvStatus(Contstants.BESPEAKSTATE_TAG.BESPEAKSTATE_BESPEAKOFF);
            frtResevationDao.merge(fr);
        }
    }

    public Boolean isBig(Long time1, Long time2, FrtResevation fr, Msg msg) {
        double temp = (((double) (time2 - time1)) / (1000 * 60));
        double tempHours = temp / 60;
        if (tempHours > Contstants.DELAYGOFACTORYMETE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 套打模版
     */

    public String getPrintHtml(Boolean tag, FrtResevationVo freVo, BasUsers user)
            throws Exception {
        if (true)
            return null;
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String html = printService.getHtmlTemplet(freVo.getPrintTempletId());
        FrtResevationVo frVo = findInfoByResvId(tag, freVo);

//	        
//	        String stfName = user1.getBasStuff().getStfName();
//	        map.put("stfName", stfName != null ? !"".equals(stfName) ? stfName : ""
//	                : "");
//	        String stfSex = user1.getBasStuff().getStfSex();
//	        map.put("stfSex", stfSex != null ? !"".equals(stfSex) ? stfSex : ""
//	                : "");
//	        String basDept = user1.getBasStuff().getBasDept().getDeptName();
//	        map.put("deptName", basDept != null ? !"".equals(basDept) ? basDept
//	                : "" : "");
//	        String basStuff = user1.getBasStuff().getStfPhone();
//	        map.put("stfPhone", basStuff != null ? !"".equals(basStuff) ? basStuff
//	                : "" : "");
//	        String stfTel = user1.getBasStuff().getStfTel();
//	        map.put("stfTel", stfTel != null ? !"".equals(stfTel) ? stfTel : ""
//	                : "");
//	        String stfGj = user1.getBasStuff().getStfGj();
//	        map.put("stfGj", stfGj != null ? !"".equals(stfGj) ? stfGj : "" : "");
//	        Date stfBirthday = user1.getBasStuff().getStfBirthday();
//	        map.put("stfBirthday", stfBirthday != null ? stfBirthday : "");
//	        Date stfWorkDate = user1.getBasStuff().getStfWorkDate();
//	        map.put("stfWorkDate", stfWorkDate != null ? stfWorkDate : "");
//	        String stfByyx = user1.getBasStuff().getStfByyx();
//	        map.put("stfByyx", stfByyx != null ? !"".equals(stfByyx) ? stfByyx : ""
//	                : "");
//	        Date stfBysj = user1.getBasStuff().getStfBysj();
//	        map.put("stfBysj", stfBysj != null ? stfBysj : "");
//	        String stfSxzy = user1.getBasStuff().getStfSxzy();
//	        map.put("stfSxzy", stfSxzy != null ? !"".equals(stfSxzy) ? stfSxzy : ""
//	                : "");
//	        String stfWhcd = user1.getBasStuff().getStfWhcd();
//	        map.put("stfWhcd", stfWhcd != null ? !"".equals(stfWhcd) ? stfWhcd : ""
//	                : "");
        String zd = user.getBasStuff().getStfName();
        map.put("zd", zd != null ? !"".equals(zd) ? zd : "" : "");
        String[] htmls = html.split("</p>");
        for (String html_ : htmls) {
            html_ = html_.replace("<p>", "").trim();
            result += HtmlParse.parse(html_, map);
        }
        return result;
    }

/*	
	public FrtResevation findResevationById(String resvId) throws Exception {
		FrtResevation fr=frtResevationDao.get(FrtResevation.class, resvId);
		if(fr!=null){
			return  fr;
		}
		return  null;
	}*/
}
