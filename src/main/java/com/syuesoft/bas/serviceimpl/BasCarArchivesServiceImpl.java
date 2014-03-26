package com.syuesoft.bas.serviceimpl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syuesoft.Tag.Log;
import com.syuesoft.bas.service.BasCarArchivesService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.dao.FrtCarDao;
import com.syuesoft.frt.dao.FrtCustomDao;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.BasCarColor;
import com.syuesoft.model.BasCarStyle;
import com.syuesoft.model.BasCarType;
import com.syuesoft.model.FrtCar;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.CreateID;
import com.syuesoft.util.FormatDate;
import com.syuesoft.util.Msg;
import com.syuesoft.util.MyBeanUtils;

/**
 * 车档案service
 * @author Liujian
 */
@Service("basCarArchivesService")
public class BasCarArchivesServiceImpl extends BaseLogServiceImpl implements
        BasCarArchivesService
{
    @Autowired
    private FrtCustomDao frtCustomDao;
    @Autowired
    private FrtCarDao frtCarDao;
    private static String updateDate=null;

    /**
     * 根据id查询客户信息
     */
    
    public FrtCustomVo getCustomById(String customId) throws Exception
    {
        FrtCustom fcu = (FrtCustom) frtCustomDao.get("from  FrtCustom where customId='"+customId+"'" );
        FrtCustomVo fVo = new FrtCustomVo();
        BeanUtils.copyProperties(fcu, fVo);
        return fVo;
    }

    /**
     * 客户档案combogrid
     */
    
    public Datagrid datagridCustom(FrtCarVo fcVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        List<FrtCustomVo> rows = new ArrayList<FrtCustomVo>();
        String hql = "from FrtCustom fcu where fcu.enterpriseId="+fcVo.getEnterpriseId()+" and fcu.customFlag="+Contstants.ONOROFF.ONOROFFYES;
        Map<String, Object> params = new HashMap<String, Object>();
        if (fcVo.getQ() != null && !"".equals(fcVo.getQ().trim())){
            hql += " and fcu.customName like '%" + StringEscapeUtils.escapeSql(fcVo.getQ().trim()) + "%'";
        }
        List<FrtCustom> fcuList = frtCustomDao.find(hql, params,fcVo.getPage(), fcVo.getRows());
        int total = frtCustomDao.getCount(hql, params);
        if (fcuList != null && fcuList.size() > 0){
            for (FrtCustom fcu: fcuList){
                FrtCustomVo fcuVo = new FrtCustomVo();
                BeanUtils.copyProperties(fcu, fcuVo);
                rows.add(fcuVo);
            }
        }
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    /**
     * 车档案datagrid
     */
    
    public Datagrid datagrid(FrtCarVo fcVo) throws Exception
    {
    	if(fcVo.getPage()==0)
    		fcVo.setPage(1);
    	if(fcVo.getRows()==0)
    		fcVo.setRows(1);
    	updateNoIntoDays(null,fcVo.getPage(), fcVo.getRows());
        String params = addWhere(fcVo);
        List<Object[]> rowsList = frtCarDao.datagrid(params, fcVo.getPage(),
                fcVo.getRows());
        List<FrtCarVo> rows = convertProperties(rowsList);
        StringBuffer sb=new StringBuffer("select v.*,bc.dataValue from frt_car_view v,frt_custom fc,");
    	sb.append(" Bas_Childdictionary bc,Bas_Parentdictionary bp ");
    	sb.append(" where bp.parent_Id=bc.parent_Id and bc.dataKey=v.carClass and bp.dataKey='"+Contstants.CARCLASS_TAG.CARCLASSKEY + "'");
    	sb.append(" and fc.custom_id=v.customId and fc.custom_flag="+Contstants.ONOROFF.ONOROFFYES);
        if (params != null && !params.equals("")){
            sb.append(params);
        }
        int  total = frtCarDao.getSQLCount(sb.toString(), null);
        Datagrid dg = new Datagrid();
        dg.setRows(rows);
        dg.setTotal(total);
        return dg;
    }

    private String addWhere(FrtCarVo fcVo) throws Exception
    {
        String params = "";
        if (fcVo.getCreateDateBegin() != null
                && !"".equals(fcVo.getCreateDateBegin()))
        {
            String date = fcVo.getCreateDateBegin();
            params += " and LEFT(v.createDate,10) >= '" + date + "'";
        }
        if (fcVo.getCreateDateEnd() != null
                && !"".equals(fcVo.getCreateDateEnd()))
        {
            String date =fcVo.getCreateDateEnd();
            params += " and LEFT(v.createDate,10) <= '" + date + "'";
        }
        if (fcVo.getCarId() != null && !"".equals(fcVo.getCarId().trim()))
        {
            params += " and v.carId like '%" + StringEscapeUtils.escapeSql(fcVo.getCarId().trim()) + "%'";
        }
        if (fcVo.getCarLicense() != null && !"".equals(fcVo.getCarLicense().trim()))
        {
            params += " and v.carLicense like '%" + StringEscapeUtils.escapeSql(fcVo.getCarLicense().trim())
                    + "%'"; // v.carLicense wangxx 2013-3-20
        }
        if (fcVo.getCarVin() != null && !"".equals(fcVo.getCarVin().trim()))
        {
            params += " and v.carVin like '%" + StringEscapeUtils.escapeSql(fcVo.getCarVin().trim()) + "%'";
        }
        if (fcVo.getCarMotorId() != null
                && !"".equals(fcVo.getCarMotorId().trim()))
        {
            params += " and v.carMotorId like '%" + StringEscapeUtils.escapeSql(fcVo.getCarMotorId().trim())
                    + "%'";
        }
        if (fcVo.getCarRecord() != null
                && !"".equals(fcVo.getCarRecord().trim()))
        {
            params += " and v.carRecord like '%" + StringEscapeUtils.escapeSql(fcVo.getCarRecord().trim())
                    + "%'";
        }
        if (fcVo.getCarBuyDateBegin() != null
                && !"".equals(fcVo.getCarBuyDateBegin()))
        {
            String date =fcVo.getCarBuyDateBegin();
            params += " and v.carBuyDate >= '" + date + "'";
        }
        if (fcVo.getCarBuyDateEnd() != null
                && !"".equals(fcVo.getCarBuyDateEnd()))
        {
            String date =fcVo.getCarBuyDateEnd();
            params += " and v.carBuyDate <= '" + date + "'";
        }
        if (fcVo.getCbrdId() != null && !"".equals(fcVo.getCbrdId()))
        {
            params += " and v.cbrdId = " + fcVo.getCbrdId();
        }
        if (fcVo.getCtypeId() != null && !"".equals(fcVo.getCtypeId()))
        {
            params += " and v.ctypeId = " + fcVo.getCtypeId();
        }
        if (fcVo.getCarCstlName() != null && !"".equals(fcVo.getCarCstlName()))
        {
            params += " and v.carCstlName = " + fcVo.getCarCstlName();
        }
        if (fcVo.getSlsId() != null && !"".equals(fcVo.getSlsId()))
        {
            params += " and v.slsId = " + fcVo.getSlsId();
        }
        if (fcVo.getCustomName() != null
                && !"".equals(fcVo.getCustomName().trim()))
        {
            params += " and (v.customName like '%"
                    + StringEscapeUtils.escapeSql(fcVo.getCustomName().trim()) + "%' or v.customJm like '%"
                    + StringEscapeUtils.escapeSql(fcVo.getCustomName().trim()) + "%') ";
        }
        if (fcVo.getCustomTel1() != null
                && !"".equals(fcVo.getCustomTel1().trim()))
        {
            params += " and v.customTel1 like '%" + StringEscapeUtils.escapeSql(fcVo.getCustomTel1().trim())
                    + "%'";
        }
        if (fcVo.getNatureId() != null && !"".equals(fcVo.getNatureId()))
        {
            params += " and v.natureId = " + fcVo.getNatureId();
        }
        if (fcVo.getCstgId() != null && !"".equals(fcVo.getCstgId()))
        {
            params += " and v.cstgId = " + fcVo.getCstgId();
        }
        if (fcVo.getCstId() != null && !"".equals(fcVo.getCstId()))
        {
            params += " and v.cstId = " + fcVo.getCstId();
        }
        if (fcVo.getPareaId() != null && !"".equals(fcVo.getPareaId()))
        {
            params += " and v.pareaId = " + fcVo.getPareaId();
        }
        if (fcVo.getColor() != null && !"".equals(fcVo.getColor()))
        {
            params += " and v.color = " + fcVo.getColor();
        }
        // 购买日期
        if (fcVo.getCarBuyDateBegin() != null
                && !"".equals(fcVo.getCarBuyDateBegin()))
        {
            params += " and LEFT(v.CarBuyDate,10) >= '" + fcVo.getCarBuyDateBegin()
                    + "'";
        }
        if (fcVo.getCarBuyDateEnd() != null
                && !"".equals(fcVo.getCarBuyDateEnd()))
        {
            params += " and LEFT(v.CarBuyDate,10) <= '" + fcVo.getCarBuyDateEnd() + "'";
        }
        // 领证日期
        if (fcVo.getCarLicenseDateBegin() != null
                && !"".equals(fcVo.getCarLicenseDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLicenseDate >= '"
                    + df.format(fcVo.getCarLicenseDateBegin()) + "'";
        }
        if (fcVo.getCarLicenseDateEnd() != null
                && !"".equals(fcVo.getCarLicenseDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLicenseDate <= '"
                    + df.format(fcVo.getCarLicenseDateEnd()) + "'";
        }
        // 交强险期
        if (fcVo.getCarBasinsuranceDateBegin() != null
                && !"".equals(fcVo.getCarBasinsuranceDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarBasinsuranceDate >= '"
                    + df.format(fcVo.getCarBasinsuranceDateBegin()) + "'";
        }
        if (fcVo.getCarBasinsuranceDateEnd() != null
                && !"".equals(fcVo.getCarBasinsuranceDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarBasinsuranceDate <= '"
                    + df.format(fcVo.getCarBasinsuranceDateEnd()) + "'";
        }
        // 商业险期
        if (fcVo.getCarBusinsuranceDateBegin() != null
                && !"".equals(fcVo.getCarBusinsuranceDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarBusinsuranceDate >= '"
                    + df.format(fcVo.getCarBusinsuranceDateBegin()) + "'";
        }
        if (fcVo.getCarBusinsuranceDateEnd() != null
                && !"".equals(fcVo.getCarBusinsuranceDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarBusinsuranceDate <= '"
                    + df.format(fcVo.getCarBusinsuranceDateEnd()) + "'";
        }
        // 年检到期
        if (fcVo.getCarAnnualDateBegin() != null
                && !"".equals(fcVo.getCarAnnualDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarAnnualDate >= '"
                    + df.format(fcVo.getCarAnnualDateBegin()) + "'";
        }
        if (fcVo.getCarAnnualDateEnd() != null
                && !"".equals(fcVo.getCarAnnualDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarAnnualDate <= '"
                    + df.format(fcVo.getCarAnnualDateEnd()) + "'";
        }
        // 首保日期
        if (fcVo.getCarFstInsuranceDateBegin() != null
                && !"".equals(fcVo.getCarFstInsuranceDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarFstInsuranceDate >= '"
                    + df.format(fcVo.getCarFstInsuranceDateBegin()) + "'";
        }
        if (fcVo.getCarFstInsuranceDateEnd() != null
                && !"".equals(fcVo.getCarFstInsuranceDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarFstInsuranceDate <= '"
                    + df.format(fcVo.getCarFstInsuranceDateEnd()) + "'";
        }
        // 最后维修日
        if (fcVo.getCarLastRepairDateBegin() != null
                && !"".equals(fcVo.getCarLastRepairDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLastRepairDate >= '"
                    + df.format(fcVo.getCarLastRepairDateBegin()) + "'";
        }
        if (fcVo.getCarLastRepairDateEnd() != null
                && !"".equals(fcVo.getCarLastRepairDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLastRepairDate <= '"
                    + df.format(fcVo.getCarLastRepairDateEnd()) + "'";
        }
        // 最后保养日
        if (fcVo.getCarLastMaintDateBegin() != null
                && !"".equals(fcVo.getCarLastMaintDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLastMaintDate >= '"
                    + df.format(fcVo.getCarLastMaintDateBegin()) + "'";
        }
        if (fcVo.getCarLastMaintDateEnd() != null
                && !"".equals(fcVo.getCarLastMaintDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarLastMaintDate <= '"
                    + df.format(fcVo.getCarLastMaintDateEnd()) + "'";
        }
        // 年审到期
        if (fcVo.getCarExaminedDateBegin() != null
                && !"".equals(fcVo.getCarExaminedDateBegin()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarExaminedDate >= '"
                    + df.format(fcVo.getCarExaminedDateBegin()) + "'";
        }
        if (fcVo.getCarExaminedDateEnd() != null
                && !"".equals(fcVo.getCarExaminedDateEnd()))
        {
            java.text.DateFormat df = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            params += " and v.CarExaminedDate <= '"
                    + df.format(fcVo.getCarExaminedDateEnd()) + "'";
        }
        params+=" and v.enterpriseId="+fcVo.getEnterpriseId();
        if (fcVo.getSort() != null && !"".equals(fcVo.getSort())
                && fcVo.getOrder() != null && !"".equals(fcVo.getOrder()))
        {
            params += " order by v." + fcVo.getSort() + " " + fcVo.getOrder();
        }
       
        return params;
    }

    /**
     * 数据类型转换，把sql原始数据转换成java类对应数据类型并构造成一个vo对象存入list中返回
     * 
     * @param rowsList
     * @return
     */
    public List<FrtCarVo> convertProperties(List<Object[]> rowsList)
            throws Exception
    {
        List<FrtCarVo> rows = new ArrayList<FrtCarVo>();
        if (rowsList != null && rowsList.size() > 0)
            for (Object[] obj : rowsList)
            {
                FrtCarVo fVo = new FrtCarVo();
                if (obj[0] != null && obj[0].toString().length() > 0)
                {
                    fVo.setCarId(obj[0].toString());
                }
                if (obj[1] != null && obj[1].toString().length() > 0)
                {
                    fVo.setCarLicense(obj[1].toString());
                }
                if (obj[2] != null && obj[2].toString().length() > 0)
                {
                    fVo.setCarVin(obj[2].toString());
                }
                if (obj[3] != null && obj[3].toString().length() > 0)
                {
                    fVo.setCarMotorId(obj[3].toString());
                }
                if (obj[4] != null && obj[4].toString().length() > 0)
                {
                    fVo.setCbrdId(new Short(obj[4].toString()));
                }
                if (obj[5] != null && obj[5].toString().length() > 0)
                {
                    fVo.setCbrdName(obj[5].toString());
                }
                if (obj[6] != null && obj[6].toString().length() > 0)
                {
                    fVo.setCtypeId(new Short(obj[6].toString()));
                }
                if (obj[7] != null && obj[7].toString().length() > 0)
                {
                    fVo.setCtypeName(obj[7].toString());
                }
                if (obj[8] != null && obj[8].toString().length() > 0)
                {
                    fVo.setCarCstlName(new Short(obj[8].toString()));
                }
                if (obj[9] != null && obj[9].toString().length() > 0)
                {
                    fVo.setCstlName(obj[9].toString());
                }
                if (obj[10] != null && obj[10].toString().length() > 0)
                {
                    fVo.setColor(new Short(obj[10].toString()));
                }
                if (obj[11] != null && obj[11].toString().length() > 0)
                {
                    fVo.setColorName(obj[11].toString());
                }
                if (obj[12] != null && obj[12].toString().length() > 0)
                {
                    fVo.setSlsId(new Short(obj[12].toString()));
                }
                if (obj[13] != null && obj[13].toString().length() > 0)
                {
                    fVo.setSlsName(obj[13].toString());
                }
                if (obj[14] != null && obj[14].toString().length() > 0)
                {
                    fVo.setCarBuyDate(obj[14].toString().substring(0,10));
                }
                if (obj[15] != null && obj[15].toString().length() > 0)
                {
                    fVo.setCarLicenseDate(obj[15].toString().substring(0,10));
                }
                if (obj[16] != null && obj[16].toString().length() > 0)
                {
                    fVo.setCarRealationLisence(obj[16].toString());
                }
                if (obj[17] != null && obj[17].toString().length() > 0)
                {
                    fVo.setAllowCarTypeId(new Short(obj[17].toString()));
                }
                if (obj[18] != null && obj[18].toString().length() > 0)
                {
                    fVo.setAllowCarTypeName(obj[18].toString());
                }
                if (obj[19] != null && obj[19].toString().length() > 0)
                {
                    fVo.setCarBasinsuranceDate(obj[19].toString().substring(0,10));
                }
                if (obj[20] != null && obj[20].toString().length() > 0)
                {
                    fVo.setCarBusinsuranceDate(obj[20].toString().substring(0,10));
                }
                if (obj[21] != null && obj[21].toString().length() > 0)
                {
                    fVo.setRelcampId(new Short(obj[21].toString()));
                }
                if (obj[22] != null && obj[22].toString().length() > 0)
                {
                    fVo.setRelcampName(obj[22].toString());
                }
                if (obj[23] != null && obj[23].toString().length() > 0)
                {
                    fVo.setCarAnnualDate(obj[23].toString().substring(0,10));
                }
                if (obj[24] != null && obj[24].toString().length() > 0)
                {
                    fVo.setCarExaminedDate(obj[24].toString().substring(0,10));
                }
                if (obj[25] != null && obj[25].toString().length() > 0)
                {
                    fVo.setTwoDimensionDate(obj[25].toString().substring(0,10));
                }
                if (obj[26] != null && obj[26].toString().length() > 0)
                {
                    fVo.setCarMaintInterva(new Integer(obj[26].toString()));
                }
                if (obj[27] != null && obj[27].toString().length() > 0)
                {
                    fVo.setCarFstInsuranceDate(obj[27].toString().substring(0,10));
                }
                if (obj[28] != null && obj[28].toString().length() > 0)
                {
                    fVo.setCreateDate(MyBeanUtils.getInstance().formatString(obj[28].toString()));
                }
                if (obj[29] != null && obj[29].toString().length() > 0)
                {
                    fVo.setCarRecord(obj[29].toString());
                }
                if (obj[30] != null && obj[30].toString().length() > 0)
                {
                    fVo.setCustomId(obj[30].toString());
                }
                if (obj[31] != null && obj[31].toString().length() > 0)
                {
                    fVo.setCustomName(obj[31].toString());
                }
                if (obj[32] != null && obj[32].toString().length() > 0)
                {
                    fVo.setCustomJm(obj[32].toString());
                }
                if (obj[33] != null && obj[33].toString().length() > 0)
                {
                    fVo.setCustomSex(obj[33].toString());
                }
                if (obj[34] != null && obj[34].toString().length() > 0)
                {
                    fVo.setNatureId(new Short(obj[34].toString()));
                }
                if (obj[35] != null && obj[35].toString().length() > 0)
                {
                    fVo.setNatureName(obj[35].toString());
                }
                if (obj[36] != null && obj[36].toString().length() > 0)
                {
                    fVo.setCstgId(new Short(obj[36].toString()));
                }
                if (obj[37] != null && obj[37].toString().length() > 0)
                {
                    fVo.setCstgName(obj[37].toString());
                }
                if (obj[38] != null && obj[38].toString().length() > 0)
                {
                    fVo.setCstId(new Short(obj[38].toString()));
                }
                if (obj[39] != null && obj[39].toString().length() > 0)
                {
                    fVo.setCstName(obj[39].toString());
                }
                if (obj[40] != null && obj[40].toString().length() > 0)
                {
                    fVo.setPareaId(new Short(obj[40].toString()));
                }
                if (obj[41] != null && obj[41].toString().length() > 0)
                {
                    fVo.setPareaName(obj[41].toString());
                }
                if (obj[42] != null && obj[42].toString().length() > 0)
                {
                    fVo.setCustomAddr(obj[42].toString());
                }
                if (obj[43] != null && obj[43].toString().length() > 0)
                {
                    fVo.setCustomTel1(obj[43].toString());
                }
                if (obj[44] != null && obj[44].toString().length() > 0)
                {
                    fVo.setCustomTel2(obj[44].toString());
                }
                if (obj[45] != null && obj[45].toString().length() > 0)
                {
                    fVo.setCustomEmail(obj[45].toString());
                }
                if (obj[46] != null && obj[46].toString().length() > 0)
                {
                    fVo.setCarBirthday(obj[46].toString().substring(0,10));
                }
                if (obj[47] != null && obj[47].toString().length() > 0)
                {
                    fVo.setCarRealationIdentifyCd(obj[47].toString());
                }
                if (obj[48] != null && obj[48].toString().length() > 0)
                {
                    fVo.setVipNumber(obj[48].toString());
                }
                if (obj[49] != null && obj[49].toString().length() > 0)
                {
                    fVo.setVipLevelId(new Integer(obj[49].toString()));
                }
                if (obj[50] != null && obj[50].toString().length() > 0)
                {
                    fVo.setVipLevelName(obj[50].toString());
                }
                if (obj[51] != null && obj[51].toString().length() > 0)
                {
                    fVo.setVipGroupId(new Integer(obj[51].toString()));
                }
                if (obj[52] != null && obj[52].toString().length() > 0)
                {
                    fVo.setVipGruopName(obj[52].toString());
                }
                if (obj[53] != null && obj[53].toString().length() > 0)
                {
                    fVo.setVipStatus(obj[53].toString());
                }
                if (obj[54] != null && obj[54].toString().length() > 0)
                {
                    fVo.setJoinTime(obj[54].toString().substring(0,10));
                }
                if (obj[55] != null && obj[55].toString().length() > 0)
                {
                    fVo.setEndTime(obj[55].toString());
                }
                if (obj[56] != null && obj[56].toString().length() > 0)
                {
                    fVo.setVipAge(FormatDate.dateDiffYear((Date) obj[54],
                            (Date) obj[55]));
                }
                if (obj[57] != null && obj[57].toString().length() > 0)
                {
                    fVo.setVipBalance(new Double(obj[57].toString()));
                }
                if (obj[58] != null && obj[58].toString().length() > 0)
                {
                    fVo.setVipIntegral(Double.parseDouble(obj[58].toString()));
                }
                if (obj[59] != null && obj[59].toString().length() > 0)
                {
                    fVo.setVipHobby(obj[59].toString());
                }
                if (obj[60] != null && obj[60].toString().length() > 0)
                {
                    fVo.setCarLastRepairDate(obj[60].toString().substring(0,10));
                }
                if (obj[61] != null && obj[61].toString().length() > 0)
                {
                    fVo.setCarLastRepairDistance(new Integer(obj[61]
                                    .toString()));
                }
                if (obj[62] != null && obj[62].toString().length() > 0)
                {
                    fVo.setCarRepairCnt(new Integer(obj[62].toString()));
                }
                if (obj[63] != null && obj[63].toString().length() > 0)
                {
                    fVo.setCarLastMaintDate(obj[63].toString().substring(0,10));
                }
                if (obj[64] != null && obj[64].toString().length() > 0)
                {
                    fVo
                            .setCarLastMaintDistance(new Integer(obj[64]
                                    .toString()));
                }
                if (obj[65] != null && obj[65].toString().length() > 0)
                {
                    fVo.setCarMaintCnt(new Integer(obj[65].toString()));
                }
                if (obj[66] != null && obj[66].toString().length() > 0)
                {
                    fVo
                            .setCarNextMaintDistance(new Integer(obj[66]
                                    .toString()));
                }
                if (obj[67] != null && obj[67].toString().length() > 0)
                {
                    fVo.setCarNextMaintDate(obj[67].toString().substring(0,10));
                }
                if (obj[68] != null && obj[68].toString().length() > 0)
                {
                    fVo.setCarDistancePerDay(new Integer(obj[68].toString()));
                }
                if (obj[69] != null && obj[69].toString().length() > 0)
                {
                    fVo.setNotIntoTheStoreDays(new Integer(obj[69].toString()));
                }
                if (obj[70] != null && obj[70].toString().length() > 0)
                {
                    fVo.setCarRemark(obj[70].toString());
                }
                if (obj[71] != null && obj[71].toString().length() > 0)
                {
                    fVo.setReceptionRemark(obj[71].toString());
                }
                if (obj[72] != null && obj[72].toString().length() > 0)
                {
                    fVo.setCarPostalCode(obj[72].toString());
                }
                if (obj[73] != null && obj[73].toString().length() > 0)
                {
                    fVo.setCarClass(obj[73].toString());
                }
                if (obj[75] != null && obj[75].toString().length() > 0)
                {
                    fVo.setCarClassName(obj[75].toString());
                }
                if (fVo.getCustomSex() != null
                        && fVo.getCustomSex().equals(Contstants.SEXTYPE.MAN))
                {
                    fVo.setCustomSexName("男");
                }
                else if (fVo.getCustomSex() != null
                        && fVo.getCustomSex().equals(Contstants.SEXTYPE.WOMAN))
                {
                    fVo.setCustomSexName("女");
                }
                else
                {
                    fVo.setCustomSexName("");
                }
                rows.add(fVo);
            }
        if (rows.size() > 0)
        {
            return rows;
        }
        return new ArrayList();
    }

    /**
     * 保存车档案
     */
    @Transactional
    @Log(moduleName = "车辆档案管理", opertype = "新增车辆档案", content = "车辆档案管理-->新增车辆档案")
    public synchronized Msg save(FrtCarVo fcVo)
    {
        Msg msg = new Msg();
        FrtCar fc = new FrtCar();
        try
        {
        	MyBeanUtils.getInstance().copyBeans(fcVo, fc);
        	fc.setCarId(null);
        	fc.setCreateDate(new Date());
            fc.setCarId(CreateID.createId("FrtCar", ""));
            saveOrEditCopyData(fcVo, fc);
            fc.setCarDistancePerDay(0);
            fc.setNotIntoTheStoreDays(0);
            fc.setEnterpriseId(fcVo.getEnterpriseId());
            Serializable sl = frtCarDao.save(fc);
            msg.setMsg("保存车档案成功！");
            msg.setObj(sl);
            msg.setSuccess(true);
            setContent("新增车辆档案,档案编号: " + fcVo.getCarId());
        }
        catch(Exception e)
        {
            msg.setMsg("保存车档案失败！");
            e.printStackTrace();
        }
        return msg;
    }

    private void saveOrEditCopyData(FrtCarVo fcVo, FrtCar fc) throws Exception
    {
        if (fcVo.getCustomId() != null && !"".equals(fcVo.getCustomId()))
        {
            FrtCustom frtCustom = new FrtCustom();
            frtCustom.setCustomId(fcVo.getCustomId());
            fc.setFrtCustom(frtCustom);
        }
        if (fcVo.getCtypeId() != null && !"".equals(fcVo.getCtypeId()))
        {
            BasCarType bct = new BasCarType();
            bct.setCtypeId(fcVo.getCtypeId());
            fc.setBasCarType(bct);
        }
        if (fcVo.getCarCstlName() != null && !"".equals(fcVo.getCarCstlName()))
        {
            BasCarStyle basCarStyle = new BasCarStyle();
            basCarStyle.setCarCstlName(fcVo.getCarCstlName());
            fc.setBasCarStyle(basCarStyle);
        }
        if (fcVo.getColor() != null && !"".equals(fcVo.getColor()))
        {
            BasCarColor basCarColor = new BasCarColor();
            basCarColor.setColor(fcVo.getColor());
            fc.setBasCarColor(basCarColor);
        }
    }

    /**
     * 删除车档案
     */
    
    @Log(moduleName = "车辆档案管理", opertype = "删除车辆档案", content = "车辆档案管理-->删除车辆档案")
    public void remove(String carId) throws Exception
    {
        frtCarDao.delete(frtCarDao.get(FrtCar.class, carId));
        setContent("车辆档案管理-->删除车辆档案,档案编号:" + carId);
    }

    /**
     * 更新车档案
     */
    
    @Log(moduleName = "车辆档案管理", opertype = "更新车辆档案", content = "车辆档案管理-->更新车辆档案")
    public void edit(FrtCarVo fcVo) throws Exception
    {
        FrtCar fc = frtCarDao.get(FrtCar.class,fcVo.getCarId());
        //BeanUtils.copyProperties(fcVo, fc);
        if(fc!=null){
        	MyBeanUtils.getInstance().copyBeans(fcVo, fc);
            saveOrEditCopyData(fcVo, fc);
            if(fc.getCarDistancePerDay()==null||fc.getCarDistancePerDay().toString().length()==0)
            	fc.setCarDistancePerDay(0);
            if(fc.getNotIntoTheStoreDays()==null||fc.getNotIntoTheStoreDays().toString().length()==0)
            	fc.setNotIntoTheStoreDays(0);
            fc.setEnterpriseId(fcVo.getEnterpriseId());
            frtCarDao.merge(fc);
        }
        
        setContent("车辆档案管理-->更新车辆档案,档案编号:" + fcVo.getCarId());
    }

    /**
     * 查询VIN号
     * 
     * @throws Exception
     */
    
    public List<ComboBoxJson> findAllCarVin(String q,Integer id) throws Exception
    {
        String hql = "from FrtCar fc where 1 = 1  and  fc.frtCustom.customFlag=TRUE   and  fc.enterpriseId="+id;
        if (q != null && !"".equals(q))
        {
            hql += "	and fc.carVin like '%" + StringEscapeUtils.escapeSql(q.trim()) + "%'";
        }
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        List<FrtCar> fcList = frtCarDao.find(hql);
        if (fcList != null && fcList.size() > 0)
        {
            for (FrtCar fc : fcList)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(fc.getCarVin());
                json.setText(fc.getCarVin());
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 判断车牌照是否已存在
     */
    
    public FrtCar carLicenseIsExits(String carLicense) throws Exception
    {
        String hql = "from FrtCar fc where 1 = 1 and fc.carLicense='"
                + carLicense.trim() + "'";
        FrtCar fc = frtCarDao.get(hql);
        if (fc != null)
        {
            return fc;
        }
        return null;
    }

    /**
     * 判断VIN号是否已存在
     */
    
    public FrtCar carVinIsExits(String carVin) throws Exception
    {
        String hql = "from FrtCar fc where 1 = 1 and fc.carVin = '"
                + carVin.trim() + "'";
        FrtCar fc = frtCarDao.get(hql);
        FrtCar frtCar = new FrtCar();
        if (fc != null)
        {
            return frtCar;
        }
        return null;
    }
    /**
     * 根据vin号查找查档案信息
     * */
    
    public FrtCar findFrtCarByVin(String carVin) throws Exception
    {
        String hql = "from FrtCar fc where 1 = 1 and fc.carVin = '"
                + carVin.trim() + "'";
        FrtCar fc = frtCarDao.get(hql);
        FrtCar frtCar = new FrtCar();
        if (fc != null)
        {
            frtCar.setCarId(fc.getCarId());
            frtCar.setFrtCustom(new FrtCustom(fc.getFrtCustom().getCustomId()));
            frtCar.setCarVin(fc.getCarVin());
            frtCar.setCarRelationPerson(fc.getCarRelationPerson());
            frtCar.setCarRelationTel1(fc.getCarRelationTel1());
            frtCar.setCarRelationTel2(fc.getCarRelationTel2());
            frtCar.setReceptionRemark(fc.getReceptionRemark());
        }
        return frtCar;

    }
    /**
     * 根据客户编号查找查档案信息
     * */
    
	public FrtCar findFrtCarByCustomId(String customId) throws Exception {
    	   String hql = "from FrtCar fc where 1 = 1 and fc.frtCustom.customId = '"
               + customId.trim() + "'";
	       List<FrtCar> fcList = frtCarDao.find(hql);
	       FrtCar frtCar = new FrtCar();
	       if (fcList != null)
	       {
	    	   FrtCar fc=fcList.get(0);
    		   frtCar.setCarId(fc.getCarId());
	           frtCar.setFrtCustom(new FrtCustom(fc.getFrtCustom().getCustomId()));
	           frtCar.setCarVin(fc.getCarVin());
	           frtCar.setCarRelationPerson(fc.getCarRelationPerson());
	           frtCar.setCarRelationTel1(fc.getCarRelationTel1());
	           frtCar.setCarRelationTel2(fc.getCarRelationTel2());
	           frtCar.setReceptionRemark(fc.getReceptionRemark());
	       }
	       return frtCar;
	}

	
    public Msg isExistsVin(String carVin) throws Exception
    {
        String hql = "from FrtCar fc where 1 = 1 and fc.carVin = '"
                + carVin.trim() + "'";
        FrtCar fc = frtCarDao.get(hql);
        Msg msg = new Msg();
        if (fc != null)
        {
            msg.setSuccess(true);
        }
        return msg;
    }
	/**
	 * 前台查询车档案
	 * */
	
	public Datagrid findAllCarByTerm(FrtCarVo fcVo) throws Exception {
		formatCode(fcVo);
		updateNoIntoDays(null,fcVo.getPage(), fcVo.getRows());
		StringBuffer sb=new StringBuffer("SELECT  fcar.car_license,fcar.car_vin, fcar.car_motor_id, bcb.cbrd_name, bct.ctype_name, bcc.color_name, bcs.cstl_name,");
		sb.append(" fcar.car_buy_date, fcar.car_accept_back, fcar.car_license_date, fcar.car_last_repair_date, fcar.car_repair_cnt,");  
		sb.append(" fcar.car_last_repair_distance, fcar.car_basinsurance_date, brc.relcamp_name,fc.custom_name,fc.custom_addr,");
		sb.append(" fc.custom_tel1,fc.custom_tel2,fc.linkman,fcar.car_relation_person,fcar.car_relation_tel2,bvi.vip_id,bvi.end_time,");
		sb.append(" bvi.vip_birthday,bca.parea_name,'' AS temp2,aa.receptor,fcar.car_id,bcss.sls_name,'' AS temp3,fcar.create_date,");
		sb.append(" fcar.car_remark,'' AS temp4,fcar.car_examined_date,fcar.car_annual_date,fcar.car_last_maint_date,fcar.car_last_maint_distance,");
		sb.append(" fcar.car_maint_cnt,fcar.car_next_maint_date,fcar.car_next_maint_distance,car_distance_per_day,fcar.not_into_the_store_days");  
		sb.append(" FROM frt_car fcar  INNER JOIN bas_car_type bct   ON bct.ctype_id = fcar.ctype_id");
		sb.append(" INNER JOIN bas_car_brand bcb ON bcb.cbrd_id = bct.cbrd_id");
		sb.append(" INNER JOIN bas_car_color bcc  ON bcc.color = fcar.color_id");
		sb.append(" left JOIN bas_car_style bcs  ON bcs.car_cstl_name = fcar.car_cstl_id"); 
		sb.append(" LEFT OUTER JOIN bas_relation_campany brc  ON brc.relcamp_id=fcar.relcamp_id"); 
		sb.append(" INNER JOIN frt_custom fc ON fc.custom_id=fcar.custom_id");   
		sb.append(" LEFT OUTER JOIN bas_custom_area bca ON fc.parea_id=bca.parea_id");  
		sb.append(" LEFT OUTER JOIN bas_custom_nature bcn ON bcn.nature_id=fc.nature_id");
		sb.append(" LEFT OUTER JOIN bas_custom_type bctype on bctype.cst_id=fc.cst_id");
		sb.append(" LEFT OUTER JOIN bas_car_sellers bcss ON bcss.sls_id=fcar.sls_id");  
		sb.append(" LEFT OUTER JOIN bas_vip_infor bvi ON bvi.vip_id=fcar.vip_id");
		sb.append(" LEFT OUTER JOIN (");  
		sb.append(" SELECT DISTINCT aa.car_id,bs.stf_name AS receptor,aa.temp"); 
		sb.append(" FROM ( ");
		sb.append(" SELECT frt.car_id,MAX(frt.inter_date) AS temp FROM frt_reception frt");
		sb.append(" GROUP BY frt.car_id");
		sb.append(" ) aa INNER JOIN frt_reception fr ON fr.car_id=aa.car_id and   fr.enterprise_id ="+fcVo.getEnterpriseId() +" AND aa.temp=fr.inter_date");
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		sb.append(" ) aa ON aa.car_id=fcar.car_id");
		sb.append(" WHERE 1=1  and   fcar.enterprise_id ="+fcVo.getEnterpriseId() );
		if(fcVo.getCarLicense()!=null&&fcVo.getCarLicense().length()>0)
			sb.append(" AND fcar.car_license like '%"+ StringEscapeUtils.escapeSql(fcVo.getCarLicense().trim())+"%'");
		if(fcVo.getCarVin()!=null&&fcVo.getCarVin().length()>0)
			sb.append(" AND fcar.car_vin like '%"+ StringEscapeUtils.escapeSql(fcVo.getCarVin().trim())+"%'");
		if(fcVo.getCarMotorId()!=null&&fcVo.getCarMotorId().length()>0)
			sb.append(" AND fcar.car_motor_id like '%"+ StringEscapeUtils.escapeSql(fcVo.getCarMotorId().trim())+"%'");
		if(fcVo.getPareaId()!=null&&fcVo.getPareaId().toString().length()>0)
			sb.append(" AND bca.parea_id="+fcVo.getPareaId());
		if(fcVo.getCbrdId()!=null&&fcVo.getCbrdId().toString().length()>0)
			sb.append(" AND bcb.cbrd_id="+fcVo.getCbrdId());
		if(fcVo.getCtypeId()!=null&&fcVo.getCtypeId().toString().length()>0)
			sb.append(" AND bct.ctype_id="+fcVo.getCtypeId());
		if(fcVo.getCarCstlName()!=null&&fcVo.getCarCstlName().toString().length()>0)
			sb.append(" AND bcs.car_cstl_name="+fcVo.getCarCstlName());
		if(fcVo.getCustomName()!=null&&fcVo.getCustomName().length()>0)
			sb.append(" AND fc.custom_name like '%"+ StringEscapeUtils.escapeSql(fcVo.getCustomName().trim())+"%'");
		if(fcVo.getCarLicenseDateBegin()!=null&&fcVo.getCarLicenseDateBegin().length()>0)
			sb.append(" AND fcar.car_license_date>='"+fcVo.getCarLicenseDateBegin()+"'");
		if(fcVo.getCarLicenseDateEnd()!=null&&fcVo.getCarLicenseDateEnd().length()>0)
			sb.append(" AND fcar.car_license_date<='"+fcVo.getCarLicenseDateEnd()+"'");
		if(fcVo.getCarBasinsuranceDateBegin()!=null&&fcVo.getCarBasinsuranceDateBegin().length()>0)
			sb.append(" AND fcar.car_basinsurance_date>='"+fcVo.getCarBasinsuranceDateBegin()+"'");
		if(fcVo.getCarBasinsuranceDateEnd()!=null&&fcVo.getCarBasinsuranceDateEnd().length()>0)
			sb.append(" AND fcar.car_basinsurance_date<='"+fcVo.getCarBasinsuranceDateEnd()+"'");
		if(fcVo.getCarBusinsuranceDateBegin()!=null&&fcVo.getCarBusinsuranceDateBegin().length()>0)
			sb.append(" AND fcar.car_businsurance_date>='"+fcVo.getCarBusinsuranceDateBegin()+"'");
		if(fcVo.getCarBusinsuranceDateEnd()!=null&&fcVo.getCarBusinsuranceDateEnd().length()>0)
			sb.append(" AND fcar.car_businsurance_date<='"+fcVo.getCarBusinsuranceDateEnd()+"'");
		if(fcVo.getCarBuyDateBegin()!=null&&fcVo.getCarBuyDateBegin().length()>0)
			sb.append(" AND fcar.car_buy_date>='"+fcVo.getCarBuyDateBegin()+"'");
		if(fcVo.getCarBuyDateEnd()!=null&&fcVo.getCarBuyDateEnd().length()>0)
			sb.append(" AND fcar.car_buy_date<='"+fcVo.getCarBuyDateEnd()+"'");
		if(fcVo.getCarFstInsuranceDateBegin()!=null&&fcVo.getCarFstInsuranceDateBegin().length()>0)
			sb.append(" AND fcar.car_fst_insurance_date>='"+fcVo.getCarFstInsuranceDateBegin()+"'");
		if(fcVo.getCarFstInsuranceDateEnd()!=null&&fcVo.getCarFstInsuranceDateEnd().length()>0)
			sb.append(" AND fcar.car_fst_insurance_date<='"+fcVo.getCarFstInsuranceDateEnd()+"'");
		if(fcVo.getCarLastRepairDateBegin()!=null&&fcVo.getCarLastRepairDateBegin().length()>0)
			sb.append(" AND fcar.car_last_repair_date>='"+fcVo.getCarLastRepairDateBegin()+"'");
		if(fcVo.getCarLastRepairDateEnd()!=null&&fcVo.getCarLastRepairDateEnd().length()>0)
			sb.append(" AND fcar.car_last_repair_date<='"+fcVo.getCarLastRepairDateEnd()+"'");
		if(fcVo.getVipId()!=null&&fcVo.getVipId().length()>0)
			sb.append(" AND bvi.vip_id like '%"+ StringEscapeUtils.escapeSql(fcVo.getVipId().trim())+"%'");
		if(fcVo.getVipBirthDay()!=null&&fcVo.getVipBirthDay().length()>0)
			sb.append(" AND bvi.vip_birthday='"+fcVo.getVipBirthDay()+"'");
		if(fcVo.getSlsId()!=null&&fcVo.getSlsId().toString().length()>0)
			sb.append(" AND bcss.sls_id="+fcVo.getSlsId());
		if(fcVo.getCarRepairCnt()!=null&&fcVo.getCarRepairCnt().toString().length()>0)
			sb.append(" AND fcar.car_repair_cnt="+fcVo.getCarRepairCnt());
		if(fcVo.getIsVip()!=null&&fcVo.getIsVip()==true)
			sb.append(" AND fcar.vip_id!=''");
		if(fcVo.getNatureId()!=null&&fcVo.getNatureId().toString().length()>0)
			sb.append(" AND bcn.nature_id="+fcVo.getNatureId());
		if(fcVo.getCstId()!=null&&fcVo.getCstId().toString().length()>0)
			sb.append(" AND bctype.cst_id="+fcVo.getCstId());
		if(fcVo.getCustomAddr()!=null&&fcVo.getCustomAddr().length()>0)
			sb.append(" AND fc.custom_addr like '%"+ StringEscapeUtils.escapeSql(fcVo.getCustomAddr().trim())+"%'");
		if(fcVo.getCarRemark()!=null&&fcVo.getCarRemark().length()>0)
			sb.append(" AND fcar.car_remark like '%"+ StringEscapeUtils.escapeSql(fcVo.getCarRemark().trim())+"%'");
		if(fcVo.getCarAnnualDateBegin()!=null&&fcVo.getCarAnnualDateBegin().length()>0)
			sb.append(" AND fcar.car_annual_date>='"+fcVo.getCarAnnualDateBegin()+"'");
		if(fcVo.getCarAnnualDateEnd()!=null&&fcVo.getCarAnnualDateEnd().length()>0)
			sb.append(" AND fcar.car_annual_date<='"+fcVo.getCarAnnualDateEnd()+"'");
		if(fcVo.getCarExaminedDateBegin()!=null&&fcVo.getCarExaminedDateBegin().length()>0)
			sb.append(" AND fcar.car_examined_date>='"+fcVo.getCarExaminedDateBegin()+"'");
		if(fcVo.getCarExaminedDateEnd()!=null&&fcVo.getCarExaminedDateEnd().length()>0)
			sb.append(" AND fcar.car_examined_date<='"+fcVo.getCarExaminedDateEnd()+"'");
		if(fcVo.getCarNextMaintDateBegin()!=null&&fcVo.getCarNextMaintDateBegin().length()>0)
			sb.append(" AND fcar.car_next_maint_date>='"+fcVo.getCarNextMaintDateBegin()+"'");
		if(fcVo.getCarNextMaintDateEnd()!=null&&fcVo.getCarNextMaintDateEnd().length()>0)
			sb.append(" AND fcar.car_next_maint_date<='"+fcVo.getCarNextMaintDateEnd()+"'");
		if(fcVo.getCarBirthdayBegin()!=null&&fcVo.getCarBirthdayBegin().length()>0)
			sb.append(" AND fcar.car_birthday>='"+fcVo.getCarBirthdayBegin()+"'");
		if(fcVo.getCarBirthdayEnd()!=null&&fcVo.getCarBirthdayEnd().length()>0)
			sb.append(" AND fcar.car_birthday<='"+fcVo.getCarBirthdayEnd()+"'");
		if(fcVo.getCreateDateBegin()!=null&&fcVo.getCreateDateBegin().length()>0)
			sb.append(" AND fcar.create_date>='"+fcVo.getCreateDateBegin()+"'");
		if(fcVo.getCreateDateEnd()!=null&&fcVo.getCreateDateEnd().length()>0)
			sb.append(" AND fcar.create_date<='"+fcVo.getCreateDateEnd()+"'");
		if(fcVo.getLastDistanceBegin()!=null&&fcVo.getLastDistanceBegin().length()>0)
			sb.append(" AND fcar.car_last_repair_distance>="+fcVo.getLastDistanceBegin()+"");
		if(fcVo.getLastDistanceEnd()!=null&&fcVo.getLastDistanceEnd().length()>0)
			sb.append(" AND fcar.car_last_repair_distance<="+fcVo.getLastDistanceEnd()+"");
		List<FrtCarVo> rows=new ArrayList();
		List<Object[]> list=frtCarDao.createSQLQuery(sb.toString(), fcVo.getPage(), fcVo.getRows());
		FrtCarVo fc=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				fc=new FrtCarVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					fc.setCarLicense(obj[0].toString());
				if(obj[1]!=null&&obj[1].toString().length()>0)
					fc.setCarVin(obj[1].toString());
				if(obj[2]!=null&&obj[2].toString().length()>0)
					fc.setCarMotorId(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					fc.setCbrdName(obj[3].toString());
				if(obj[4]!=null&&obj[4].toString().length()>0)
					fc.setCtypeName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					fc.setColorName(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					fc.setCstlName(obj[6].toString());
				if(obj[7]!=null&&obj[7].toString().length()>0)
					fc.setCarBuyDate(obj[7].toString().substring(0,10));
				if(obj[8]!=null&&obj[8].toString().length()>0)
					fc.setAcceptBack(obj[8].toString());
				if(obj[9]!=null&&obj[9].toString().length()>0)
					fc.setCarLicenseDate(obj[9].toString().substring(0,10));
				if(obj[10]!=null&&obj[10].toString().length()>0)
					fc.setCarLastRepairDate(obj[10].toString().substring(0,10));
				if(obj[11]!=null&&obj[11].toString().length()>0)
					fc.setCarRepairCnt(Integer.parseInt(obj[11].toString()));
				if(obj[12]!=null&&obj[12].toString().length()>0)
					fc.setCarLastRepairDistance(Integer.parseInt(obj[12].toString()));
				if(obj[13]!=null&&obj[13].toString().length()>0)
					fc.setCarBasinsuranceDate(obj[13].toString().substring(0,10));
				if(obj[14]!=null&&obj[14].toString().length()>0)
					fc.setRelcampName(obj[14].toString());
				if(obj[15]!=null&&obj[15].toString().length()>0)
					fc.setCustomName(obj[15].toString());
				if(obj[16]!=null&&obj[16].toString().length()>0)
					fc.setCustomAddr(obj[16].toString());
				if(obj[17]!=null&&obj[17].toString().length()>0)
					fc.setCustomTel1(obj[17].toString());
				if(obj[18]!=null&&obj[18].toString().length()>0)
					fc.setCustomTel2(obj[18].toString());
				if(obj[19]!=null&&obj[19].toString().length()>0)
					fc.setLinkMan(obj[19].toString());
				if(obj[20]!=null&&obj[20].toString().length()>0)
					fc.setCarRelationPerson(obj[20].toString());
				if(obj[21]!=null&&obj[21].toString().length()>0)
					fc.setCarRelationTel1(obj[21].toString());
				if(obj[22]!=null&&obj[22].toString().length()>0)
					fc.setVipId(obj[22].toString());
				if(obj[23]!=null&&obj[23].toString().length()>0)
					fc.setEndTime(obj[23].toString());
				if(obj[24]!=null&&obj[24].toString().length()>0)
					fc.setVipBirthDay(obj[24].toString());
				if(obj[25]!=null&&obj[25].toString().length()>0)
					fc.setPareaName(obj[25].toString());
				if(obj[26]!=null&&obj[26].toString().length()>0)
					fc.setTemp1(obj[26].toString());
				if(obj[27]!=null&&obj[27].toString().length()>0)
					fc.setReceivePerson(obj[27].toString());
				if(obj[28]!=null&&obj[28].toString().length()>0)
					fc.setCarId(obj[28].toString());
				if(obj[29]!=null&&obj[29].toString().length()>0)
					fc.setSlsName(obj[29].toString());
				if(obj[30]!=null&&obj[30].toString().length()>0)
					fc.setTemp2(obj[30].toString());
				if(obj[31]!=null&&obj[31].toString().length()>0)
					fc.setCreateDate(MyBeanUtils.getInstance().formatString(obj[31].toString()));
				if(obj[32]!=null&&obj[32].toString().length()>0)
					fc.setCarRemark(obj[32].toString());
				if(obj[33]!=null&&obj[33].toString().length()>0)
					fc.setTemp3(obj[33].toString());
				if(obj[34]!=null&&obj[34].toString().length()>0)
					fc.setCarExaminedDate(obj[34].toString().substring(0,10));
				if(obj[35]!=null&&obj[35].toString().length()>0)
					fc.setCarAnnualDate(obj[35].toString().substring(0,10));
				if(obj[36]!=null&&obj[36].toString().length()>0)
					fc.setCarLastMaintDate(obj[36].toString().substring(0,10));
				if(obj[37]!=null&&obj[37].toString().length()>0)
					fc.setCarLastMaintDistance(Integer.parseInt(obj[37].toString()));
				if(obj[38]!=null&&obj[38].toString().length()>0)
					fc.setCarMaintCnt(Integer.parseInt(obj[38].toString()));
				if(obj[39]!=null&&obj[39].toString().length()>0)
					fc.setCarNextMaintDate(obj[39].toString().substring(0,10));
				if(obj[40]!=null&&obj[40].toString().length()>0)
					fc.setCarNextMaintDistance(Integer.parseInt(obj[40].toString()));
				if(obj[41]!=null&&obj[41].toString().length()>0)
					fc.setCarDistancePerDay(Integer.parseInt(obj[41].toString()));
				if(obj[42]!=null&&obj[42].toString().length()>0)
					fc.setNotIntoTheStoreDays(Integer.parseInt(obj[42].toString()));
				rows.add(fc);
			}
		int total=frtCarDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}

	/**
	 * 前台车档案查询-数据分析
	 * */
	
	public Datagrid findAllCarAnalyse(FrtCarVo fcVo) throws Exception {
		if(fcVo.getAnalyseWay()==null||fcVo.getAnalyseWay().length()==0)
			fcVo.setAnalyseWay("aa");
		List<FrtCarVo> fcList = null;
        String data = fcVo.getAnalyseData();
        Datagrid dg=new Datagrid();
        if (!(data != null && data.length() > 0))
        	return dg;
        
        JSONObject jsData = JSON.parseObject(data);
        fcList = JSON.parseArray(jsData.get("rows").toString(),FrtCarVo.class);
        if(fcList==null)
        	return dg;
        String code=null;
        if(fcVo.getAnalyseWay().equals("aa")){
        	code="pareaName";
        }else if(fcVo.getAnalyseWay().equals("bb")){
        	code="cbrdName";
        }else if(fcVo.getAnalyseWay().equals("cc")){
        	code="ctypeName";
        }
        HashMap<String,FrtCarVo> hashMap=new HashMap();
        Integer sumCount=0;
        FrtCarVo car=null;
        Iterator it=null;
        Field tempField=null;
        Field[] field=FrtCarVo.class.getDeclaredFields();
        for (Field field2 : field) {
        	field2.setAccessible(true);
        	if(field2.getName().equals(code)){
        		tempField=field2;
        		break;
        	}
        }
        for (FrtCarVo frtCarVo : fcList) {
        	boolean tag=false;
        	if(hashMap.size()==0){
        		tag=true;
        	}
        	it=hashMap.keySet().iterator();
        	String name1=null;
        	tempField.setAccessible(true);
        	String name2=tempField.get(frtCarVo).toString();
        	while(it.hasNext()){
        		name1=it.next().toString();
        		boolean flag=false;
        		if(name1.equals(name2)){
        			hashMap.get(name1).setAnalyseCount(hashMap.get(name1).getAnalyseCount()+1);
        			flag=true;
        			break;
        		}
        		if(!flag){
        			tag=true;
        		}
        	}
        	if(tag){
        		car=new FrtCarVo();
        		car.setAnalyseWay(name2);
        		car.setAnalyseCount(1);
        		hashMap.put(name2, car);
        	}
		}
        List<FrtCarVo> rows=new ArrayList();
        it=hashMap.keySet().iterator();
        while(it.hasNext()){
    		rows.add(hashMap.get(it.next()));
    	}
        for (FrtCarVo frtCarVo : rows) {
			sumCount+=frtCarVo.getAnalyseCount();
		}
        for (FrtCarVo frtCarVo : rows) {
			frtCarVo.setAnalyseRate(Double.parseDouble(frtCarVo.getAnalyseCount().toString())/Double.parseDouble(sumCount.toString()));
		}
        dg.setRows(rows);
        dg.setTotal(rows.size());
		return dg;
	}
	/**
	 * 前台车档案查询-维修记录
	 * */
	
	public Datagrid findAllReceptionByCarId(FrtCarVo fcVo) throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fr.inter_date,fr.reception_distance,bs.stf_name,fr.reception_fact_time,");
		sb.append(" aa.stfName,fr.reception_id,fpc.preclr_sum_amount");
		sb.append(" FROM frt_reception fr INNER JOIN frt_pre_clearing fpc ON fpc.reception_id=fr.reception_id");
		sb.append(" AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES);
		sb.append(" INNER JOIN bas_stuff bs ON bs.stf_id=fr.receptor");
		sb.append(" INNER JOIN (SELECT bs.stf_id,bs.stf_name AS stfName FROM bas_stuff bs) aa"); 
		sb.append(" ON aa.stf_id=fr.reception_technician");
		if(fcVo.getCarId()==null||fcVo.getCarId().length()==0)
			sb.append(" where fr.car_id='-1'");
		else
			sb.append(" where fr.car_id='"+fcVo.getCarId()+"'");
		List<FrtReceptionVo> rows=new ArrayList();
		List<Object[]> list=frtCarDao.createSQLQuery(sb.toString(), fcVo.getPage(), fcVo.getRows());
		FrtReceptionVo frVo=null;
		if(list!=null&&list.size()>0)
			for (Object[] obj : list) {
				frVo=new FrtReceptionVo();
				if(obj[0]!=null&&obj[0].toString().length()>0)
					frVo.setInterDate(MyBeanUtils.getInstance().formatString(obj[0].toString()));
				if(obj[1]!=null&&obj[1].toString().length()>0)
					frVo.setReceptionDistance(Integer.parseInt(obj[1].toString()));
				if(obj[2]!=null&&obj[2].toString().length()>0)
					frVo.setStfName(obj[2].toString());
				if(obj[3]!=null&&obj[3].toString().length()>0)
					frVo.setReceptionFactTime(MyBeanUtils.getInstance().formatString(obj[3].toString()));
				if(obj[4]!=null&&obj[4].toString().length()>0)
					frVo.setReceptionTechnicianName(obj[4].toString());
				if(obj[5]!=null&&obj[5].toString().length()>0)
					frVo.setReceptionId(obj[5].toString());
				if(obj[6]!=null&&obj[6].toString().length()>0)
					frVo.setPreclrSumAmount(obj[6].toString());
				frVo.setItems("查看项目");
				frVo.setState("closed");
				rows.add(frVo);
			}
		int total=frtCarDao.getSQLCount(sb.toString(), null);
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(total);
		return dg;
	}
	/**
	 * 前台车档案查询-维修记录-子项
	 * */
	
	public List<FrtReceptionVo> findAllReceptionByCarIdForChild(FrtCarVo fcVo)
			throws Exception {
		StringBuffer sb=new StringBuffer("SELECT fpw.repitem_name FROM frt_pre_wktime fpw INNER JOIN frt_pre_clearing fpc ON fpc.preclr_id=fpw.preclr_id");
		sb.append(" WHERE fpc.reception_id='"+fcVo.getReceptionId()+"'");
		List<FrtReceptionVo> rows=new ArrayList();
		 List list=frtCarDao.createSQLQuery(sb.toString());
		 FrtReceptionVo frt=null;
		 if(list!=null && list.size()>0){
			 for (Object obj : list) {
					frt=new FrtReceptionVo();
					frt.setItems(obj.toString());
					frt.set_parentId(fcVo.getReceptionId());
					frt.setState("open");
					rows.add(frt);
				}
		 }
		return rows;
	}
	private void formatCode(FrtCarVo fcVo) throws UnsupportedEncodingException{
		if(fcVo.getCarLicense()!=null&&fcVo.getCarLicense().length()>0)
			fcVo.setCarLicense(new String(fcVo.getCarLicense().getBytes("ISO-8859-1"),"UTF-8"));
		if(fcVo.getCarMotorId()!=null&&fcVo.getCarMotorId().length()>0)
			fcVo.setCarMotorId(new String(fcVo.getCarMotorId().getBytes("ISO-8859-1"),"UTF-8"));
		if(fcVo.getCustomName()!=null&&fcVo.getCustomName().length()>0)
			fcVo.setCustomName(new String(fcVo.getCustomName().getBytes("ISO-8859-1"),"UTF-8"));
		if(fcVo.getCustomAddr()!=null&&fcVo.getCustomAddr().length()>0)
			fcVo.setCustomAddr(new String(fcVo.getCustomAddr().getBytes("ISO-8859-1"),"UTF-8"));
		if(fcVo.getCarRemark()!=null&&fcVo.getCarRemark().length()>0)
			fcVo.setCarRemark(new String(fcVo.getCarRemark().getBytes("ISO-8859-1"),"UTF-8"));
	}
	/**
	 * 修改车辆未来厂天数(carId为null则修改所有)
	 * */
	
	public Msg updateNoIntoDays(String carId,int page,int rows) throws Exception {
		Msg msg=new Msg();
		if(updateDate!=null&&updateDate.length()>0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			if(updateDate.equals(sdf.format(new Date()))){
				msg.setMsg("已更新未来厂天数！");
				msg.setSuccess(true);
				return msg;
			}
		}
		try {
			if(carId!=null&&carId.length()>0){
				modifyNoIntoDays(carId);
				msg.setMsg("更新未来厂天数成功！");
				msg.setSuccess(true);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				updateDate=sdf.format(new Date());
				return msg;
			}
			List<FrtCar> list=frtCarDao.find("from FrtCar ",page,rows);
			if(list!=null&&list.size()>0)
				for (FrtCar frtCar : list) {
					modifyNoIntoDays(frtCar.getCarId());
				}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			updateDate=sdf.format(new Date());
			msg.setMsg("更新未来厂天数成功！");
			msg.setSuccess(true);			
		} catch (Exception e) {
			msg.setMsg("更新未来厂天数失败！");
		}
		return msg;
	}

	public void modifyNoIntoDays(String carId) throws Exception{
		StringBuffer sb=new StringBuffer("UPDATE frt_car SET NOT_INTO_THE_STORE_DAYS=");
	    sb.append(" (SELECT (SELECT CASE WHEN aa.temp!='' THEN aa.temp ELSE 0 END) AS temp");
	    sb.append(" FROM("); 
		sb.append(" SELECT DATEDIFF(NOW(),");	 
		sb.append(" (");
		sb.append(" SELECT MAX(fpc.PRECLR_TIME) AS data2 FROM frt_pre_clearing fpc,frt_reception fr");	 
		sb.append(" WHERE fr.RECEPTION_ID=fpc.RECEPTION_ID AND fpc.preclr_to_money="+Contstants.TOMONEY_TAG.TOMONEYYES+" AND fr.car_id='"+carId+"'");
		sb.append(" )");	 	
		sb.append(" ) AS temp ) aa )WHERE car_id='"+carId+"'");
		frtCarDao.executeSql(sb.toString());	
	}
}