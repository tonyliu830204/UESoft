package com.syuesoft.vipmanagement.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.FormatTime;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.dao.IntegralIntegratedQueryDao;
import com.syuesoft.vipmanagement.service.IntegralIntegratedQueryService;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.IntegralIntegratedQueryVo;
/**
 * 
* @ClassName: IntegralIntegratedQueryServiceImpl 
* @Description: TODO(积分综合查询) 
* @author HeXin 
* @date 2013-12-25 上午11:09:58 
* @version 1.0
 */
@Service("integralIntegratedQueryService")
public class IntegralIntegratedQueryServiceImpl implements
		IntegralIntegratedQueryService {
	@Autowired
	private IntegralIntegratedQueryDao integralIntegratedQueryDao;
	@Autowired
    private VipRecordMessageService vipRecordMessageService;
	
	public Json getIntegralIntegratedQuery(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception
    {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        //条件
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            integralIntegratedQueryVo.setCar_License(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim()));
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            integralIntegratedQueryVo.setCar_Vin(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            integralIntegratedQueryVo.setVip_Name(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            integralIntegratedQueryVo.setVip_Tel(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            integralIntegratedQueryVo.setVip_Number(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Level_Id()!=null && !integralIntegratedQueryVo.getVip_Level_Id().equals("")){
            integralIntegratedQueryVo.setVip_Level_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Group_Id()!=null && !integralIntegratedQueryVo.getVip_Group_Id().equals("")){
            integralIntegratedQueryVo.setVip_Group_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            integralIntegratedQueryVo.setVip_Status(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            integralIntegratedQueryVo.setVip_Age(integralIntegratedQueryVo.getVip_Age());
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral(integralIntegratedQueryVo.getVip_Integral());
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral1(integralIntegratedQueryVo.getVip_Integral1());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0, Contstants.VIPOPERATETYPE.OPERATE5);
        params.add(1, Contstants.VIPOPERATETYPE.OPERATE6);
        params.add(2, Contstants.INTEGRALTYPE.INTEGRALTYPE1);
        params.add(3, Contstants.INTEGRALTYPE.INTEGRALTYPE2);
        params.add(4, integralIntegratedQueryVo.getCar_License() != null ? integralIntegratedQueryVo.getCar_License() : "");
        params.add(5, integralIntegratedQueryVo.getCar_Vin() != null ? integralIntegratedQueryVo.getCar_Vin() : "");
        params.add(6, integralIntegratedQueryVo.getVip_Name() != null ? integralIntegratedQueryVo.getVip_Name() : "");
        params.add(7, integralIntegratedQueryVo.getVip_Tel() != null ? integralIntegratedQueryVo.getVip_Tel() : "");
        params.add(8, integralIntegratedQueryVo.getVip_Number() != null ? integralIntegratedQueryVo.getVip_Number() : "");
        params.add(9, integralIntegratedQueryVo.getVip_Level_Id() != null ? integralIntegratedQueryVo.getVip_Level_Id() : "");
        params.add(10, integralIntegratedQueryVo.getVip_Group_Id() != null ? integralIntegratedQueryVo.getVip_Group_Id() : "");
        params.add(11, integralIntegratedQueryVo.getVip_Status() != null ? integralIntegratedQueryVo.getVip_Status() : "");
        params.add(12, integralIntegratedQueryVo.getVip_Age() != null ? integralIntegratedQueryVo.getVip_Age() : "");
        params.add(13, integralIntegratedQueryVo.getVip_Integral() != null ? integralIntegratedQueryVo.getVip_Integral() : "");
        params.add(14, integralIntegratedQueryVo.getVip_Integral1() != null ? integralIntegratedQueryVo.getVip_Integral1() : "");
        params.add(15, integralIntegratedQueryVo.getPage() != 0 ? integralIntegratedQueryVo.getPage() : 0);
        params.add(16, integralIntegratedQueryVo.getRows() != 0 ? integralIntegratedQueryVo.getRows() : 0);
        StringBuffer procedureName=new StringBuffer(" { CALL Points_summary(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> rlist = integralIntegratedQueryDao.getProcedureQuery(procedureName.toString(), params);
        int total = 0;
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Number(obj[1].toString());}
                if(obj[2]!=null){vo.setCar_License(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_Vin(obj[3].toString());}
                if(obj[4]!=null){vo.setVip_Name(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Birthday(FormatTime.timestamp2Str((Timestamp) obj[5]));}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                if(obj[7]!=null){vo.setVip_Status(obj[7].toString());}
                if(obj[8]!=null){vo.setJion_Time(FormatTime.timestamp2Str((Timestamp) obj[8]));}
                if(obj[9]!=null){vo.setEnd_Time(FormatTime.timestamp2Str((Timestamp) obj[9]));}
                if(obj[10]!=null){vo.setVip_Age(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Integral(obj[11].toString());}
                if(obj[12]!=null){vo.setVip_Balance(obj[12].toString());}
                //obj[13]
                if(obj[14]!=null){vo.setVip_Level_Name(obj[14].toString());}
                //obj[15]
                if(obj[16]!=null){vo.setVip_Group_Name(obj[16].toString());}
                if(obj[17]!=null){vo.setRec_Amount(obj[17].toString());}
                if(obj[18]!=null){vo.setGive_Amount(obj[18].toString());}
                if(obj[19]!=null){vo.setGive_Inte(obj[19].toString());}
                if(obj[20]!=null){vo.setVip_Balance18(obj[20].toString());}
                if(obj[21]!=null){vo.setVip_Balance19(obj[21].toString());}
                if(obj[22]!=null){vo.setVip_Balance20(obj[22].toString());}
                if(obj[23]!=null){vo.setVip_Balance21(obj[23].toString());}
                if(obj[24]!=null){vo.setVip_Balance22(obj[24].toString());}
                if(obj[25]!=null){vo.setVip_Balance14(obj[25].toString());}
                if(obj[26]!=null){vo.setVip_Balance15(obj[26].toString());}
                if(obj.length > 27){
                    total =  Integer.parseInt(obj[27].toString());
                }
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
	
	public Json getMaintenancePointsInquiry(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            integralIntegratedQueryVo.setCar_License(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim()));
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            integralIntegratedQueryVo.setCar_Vin(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            integralIntegratedQueryVo.setVip_Name(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            integralIntegratedQueryVo.setVip_Tel(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            integralIntegratedQueryVo.setVip_Number(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Level_Id()!=null && !integralIntegratedQueryVo.getVip_Level_Id().equals("")){
            integralIntegratedQueryVo.setVip_Level_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Group_Id()!=null && !integralIntegratedQueryVo.getVip_Group_Id().equals("")){
            integralIntegratedQueryVo.setVip_Group_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            integralIntegratedQueryVo.setVip_Status(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            integralIntegratedQueryVo.setVip_Age(integralIntegratedQueryVo.getVip_Age());
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral(integralIntegratedQueryVo.getVip_Integral());
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral1(integralIntegratedQueryVo.getVip_Integral1());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0, Contstants.INTEGRALTYPE.INTEGRALTYPE1);
        params.add(1, integralIntegratedQueryVo.getCar_License() != null ? integralIntegratedQueryVo.getCar_License() : "");
        params.add(2, integralIntegratedQueryVo.getCar_Vin() != null ? integralIntegratedQueryVo.getCar_Vin() : "");
        params.add(3, integralIntegratedQueryVo.getVip_Name() != null ? integralIntegratedQueryVo.getVip_Name() : "");
        params.add(4, integralIntegratedQueryVo.getVip_Tel() != null ? integralIntegratedQueryVo.getVip_Tel() : "");
        params.add(5, integralIntegratedQueryVo.getVip_Number() != null ? integralIntegratedQueryVo.getVip_Number() : "");
        params.add(6, integralIntegratedQueryVo.getVip_Level_Id() != null ? integralIntegratedQueryVo.getVip_Level_Id() : "");
        params.add(7, integralIntegratedQueryVo.getVip_Group_Id() != null ? integralIntegratedQueryVo.getVip_Group_Id() : "");
        params.add(8, integralIntegratedQueryVo.getVip_Status() != null ? integralIntegratedQueryVo.getVip_Status() : "");
        params.add(9, integralIntegratedQueryVo.getVip_Age() != null ? integralIntegratedQueryVo.getVip_Age() : "");
        params.add(10, integralIntegratedQueryVo.getVip_Integral() != null ? integralIntegratedQueryVo.getVip_Integral() : "");
        params.add(11, integralIntegratedQueryVo.getVip_Integral1() != null ? integralIntegratedQueryVo.getVip_Integral1() : "");
        params.add(12, integralIntegratedQueryVo.getPage() != 0 ? integralIntegratedQueryVo.getPage() : 0);
        params.add(13, integralIntegratedQueryVo.getRows() != 0 ? integralIntegratedQueryVo.getRows() : 0);
        StringBuffer procedureName=new StringBuffer(" { CALL Maintenance_points_inquiry(?,?,?,?,?,?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> rlist = integralIntegratedQueryDao.getProcedureQuery(procedureName.toString(), params);
        int total = 0;
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setStockId(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setVip_Level_Name(obj[3].toString());}
                if(obj[4]!=null){vo.setVip_Group_Name(obj[4].toString());}
                if(obj[5]!=null){vo.setCar_License(obj[5].toString());}
                if(obj[6]!=null){vo.setCar_Vin(obj[6].toString());}
                if(obj[7]!=null){vo.setPreclr_Time(FormatTime.timestamp2Str((Timestamp) obj[7]));}
                if(obj[8]!=null){vo.setPreclr_Id(obj[8].toString());}
                if(obj[9]!=null){vo.setReception_Id(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Integral(obj[10].toString());}
                if(obj[11]!=null){vo.setOperatorName(obj[11].toString());}
                if(obj.length > 12){
                    total =  Integer.parseInt(obj[12].toString());
                }
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
		return json;
	}
	
	public Json getSellPointsInquiry(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers users)throws Exception
    {
        List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            integralIntegratedQueryVo.setCar_License(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim()));
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            integralIntegratedQueryVo.setCar_Vin(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            integralIntegratedQueryVo.setVip_Name(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            integralIntegratedQueryVo.setVip_Tel(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            integralIntegratedQueryVo.setVip_Number(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Level_Id()!=null && !integralIntegratedQueryVo.getVip_Level_Id().equals("")){
            integralIntegratedQueryVo.setVip_Level_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Group_Id()!=null && !integralIntegratedQueryVo.getVip_Group_Id().equals("")){
            integralIntegratedQueryVo.setVip_Group_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            integralIntegratedQueryVo.setVip_Status(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            integralIntegratedQueryVo.setVip_Age(integralIntegratedQueryVo.getVip_Age());
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral(integralIntegratedQueryVo.getVip_Integral());
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral1(integralIntegratedQueryVo.getVip_Integral1());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0, Contstants.INTEGRALTYPE.INTEGRALTYPE2);
        params.add(1, integralIntegratedQueryVo.getCar_License() != null ? integralIntegratedQueryVo.getCar_License() : "");
        params.add(2, integralIntegratedQueryVo.getCar_Vin() != null ? integralIntegratedQueryVo.getCar_Vin() : "");
        params.add(3, integralIntegratedQueryVo.getVip_Name() != null ? integralIntegratedQueryVo.getVip_Name() : "");
        params.add(4, integralIntegratedQueryVo.getVip_Tel() != null ? integralIntegratedQueryVo.getVip_Tel() : "");
        params.add(5, integralIntegratedQueryVo.getVip_Number() != null ? integralIntegratedQueryVo.getVip_Number() : "");
        params.add(6, integralIntegratedQueryVo.getVip_Level_Id() != null ? integralIntegratedQueryVo.getVip_Level_Id() : "");
        params.add(7, integralIntegratedQueryVo.getVip_Group_Id() != null ? integralIntegratedQueryVo.getVip_Group_Id() : "");
        params.add(8, integralIntegratedQueryVo.getVip_Status() != null ? integralIntegratedQueryVo.getVip_Status() : "");
        params.add(9, integralIntegratedQueryVo.getVip_Age() != null ? integralIntegratedQueryVo.getVip_Age() : "");
        params.add(10, integralIntegratedQueryVo.getVip_Integral() != null ? integralIntegratedQueryVo.getVip_Integral() : "");
        params.add(11, integralIntegratedQueryVo.getVip_Integral1() != null ? integralIntegratedQueryVo.getVip_Integral1() : "");
        params.add(12, integralIntegratedQueryVo.getPage() != 0 ? integralIntegratedQueryVo.getPage() : 0);
        params.add(13, integralIntegratedQueryVo.getRows() != 0 ? integralIntegratedQueryVo.getRows() : 0);
        StringBuffer procedureName=new StringBuffer(" { CALL sell_points_inquiry(?,?,?,?,?,?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> rlist = integralIntegratedQueryDao.getProcedureQuery(procedureName.toString(), params);
        int total = 0;
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setStockId(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Name(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setVip_Level_Name(obj[3].toString());}
                if(obj[4]!=null){vo.setVip_Group_Name(obj[4].toString());}
                if(obj[5]!=null){vo.setCar_License(obj[5].toString());}
                if(obj[6]!=null){vo.setCar_Vin(obj[6].toString());}
                if(obj[7]!=null){vo.setPreclr_Time(FormatTime.timestamp2Str((Timestamp) obj[7]));}
                if(obj[8]!=null){vo.setPreclr_Id(obj[8].toString());}
                if(obj[9]!=null){vo.setReception_Id(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Integral(obj[10].toString());}
                if(obj[11]!=null){vo.setOperatorName(obj[11].toString());}
                if(obj.length > 12){
                    total =  Integer.parseInt(obj[12].toString());
                }
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
    }
	
	public Json getStoredValuePoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        String sql = "select * from stored_Value_Points A where 1 = 1 ";
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            sql += " and A.Car_vin like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            sql += " and A.vip_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            sql += " and A.vip_Tel like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            sql += " and A.Vip_Number like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Level_Name()!=null && !integralIntegratedQueryVo.getVip_Level_Name().equals("")){
            sql += " and A.Vip_Level_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Group_Name()!=null && !integralIntegratedQueryVo.getVip_Group_Name().equals("")){
            sql += " and A.Vip_Group_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            sql += " and A.Vip_Status like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            sql += " and A.Vip_Age = '"+integralIntegratedQueryVo.getVip_Age()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            sql += " and A.Vip_Integral >= '"+integralIntegratedQueryVo.getVip_Integral()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            sql += " and A.Vip_Integral <= '"+integralIntegratedQueryVo.getVip_Integral1()+"'";
        }
        int total = integralIntegratedQueryDao.getCountBySQL(sql);
        List<Object[]> rlist = integralIntegratedQueryDao.createSQLQuery(sql, integralIntegratedQueryVo.getPage(), integralIntegratedQueryVo.getRows());
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setRec_Amount(obj[12].toString());}
                if(obj[13]!=null){vo.setGive_Amount(obj[13].toString());}
                if(obj[14]!=null){vo.setGive_Inte(obj[14].toString());}
                if(obj[15]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[15]));}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
	
	public Json getPresentationPoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        String sql = "select * from presentation_Points A where 1 = 1 ";
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            sql += " and A.Car_vin like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            sql += " and A.vip_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            sql += " and A.vip_Tel like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            sql += " and A.Vip_Number like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Level_Name()!=null && !integralIntegratedQueryVo.getVip_Level_Name().equals("")){
            sql += " and A.Vip_Level_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Group_Name()!=null && !integralIntegratedQueryVo.getVip_Group_Name().equals("")){
            sql += " and A.Vip_Group_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            sql += " and A.Vip_Status like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            sql += " and A.Vip_Age = '"+integralIntegratedQueryVo.getVip_Age()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            sql += " and A.Vip_Integral >= '"+integralIntegratedQueryVo.getVip_Integral()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            sql += " and A.Vip_Integral <= '"+integralIntegratedQueryVo.getVip_Integral1()+"'";
        }
        int total = integralIntegratedQueryDao.getCountBySQL(sql);
        List<Object[]> rlist = integralIntegratedQueryDao.createSQLQuery(sql, integralIntegratedQueryVo.getPage(), integralIntegratedQueryVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setGive_Inte(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[13]));}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
		return json;
	}
	
	public Object getConvertPoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception
	{
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        String sql = "select * from convert_points A where 1 = 1 ";
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            sql += " and A.Car_vin like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            sql += " and A.vip_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            sql += " and A.vip_Tel like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            sql += " and A.Vip_Number like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Level_Name()!=null && !integralIntegratedQueryVo.getVip_Level_Name().equals("")){
            sql += " and A.Vip_Level_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Group_Name()!=null && !integralIntegratedQueryVo.getVip_Group_Name().equals("")){
            sql += " and A.Vip_Group_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            sql += " and A.Vip_Status like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            sql += " and A.Vip_Age = '"+integralIntegratedQueryVo.getVip_Age()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            sql += " and A.Vip_Integral >= '"+integralIntegratedQueryVo.getVip_Integral()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            sql += " and A.Vip_Integral <= '"+integralIntegratedQueryVo.getVip_Integral1()+"'";
        }
        int total = integralIntegratedQueryDao.getCountBySQL(sql);
        List<Object[]> rlist = integralIntegratedQueryDao.createSQLQuery(sql, integralIntegratedQueryVo.getPage(), integralIntegratedQueryVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setGive_Inte(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[13]));}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
	 
	public Json getVipCardUpgrade(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        String sql = "select * from vipcard_up_find A where 1 = 1 ";
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            sql += " and A.Car_License like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            sql += " and A.Car_vin like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            sql += " and A.vip_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            sql += " and A.vip_Tel like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            sql += " and A.Vip_Number like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Level_Name()!=null && !integralIntegratedQueryVo.getVip_Level_Name().equals("")){
            sql += " and A.Vip_Level_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Group_Name()!=null && !integralIntegratedQueryVo.getVip_Group_Name().equals("")){
            sql += " and A.Vip_Group_Name like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Name().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            sql += " and A.Vip_Status like '%"+StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim())+"%'";
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            sql += " and A.Vip_Age = '"+integralIntegratedQueryVo.getVip_Age()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            sql += " and A.Vip_Integral >= '"+integralIntegratedQueryVo.getVip_Integral()+"'";
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            sql += " and A.Vip_Integral <= '"+integralIntegratedQueryVo.getVip_Integral1()+"'";
        }
        int total = integralIntegratedQueryDao.getCountBySQL(sql);
        List<Object[]> rlist = integralIntegratedQueryDao.createSQLQuery(sql, integralIntegratedQueryVo.getPage(), integralIntegratedQueryVo.getRows());
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setGive_Inte(obj[12].toString());}
                if(obj[13]!=null){vo.setDeductionI_ntegration(obj[13].toString());}
                if(obj[14]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[14]));}
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
	
	public Json getAdjournmentFind(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            integralIntegratedQueryVo.setCar_License(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim()));
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            integralIntegratedQueryVo.setCar_Vin(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            integralIntegratedQueryVo.setVip_Name(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            integralIntegratedQueryVo.setVip_Tel(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            integralIntegratedQueryVo.setVip_Number(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Level_Id()!=null && !integralIntegratedQueryVo.getVip_Level_Id().equals("")){
            integralIntegratedQueryVo.setVip_Level_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Group_Id()!=null && !integralIntegratedQueryVo.getVip_Group_Id().equals("")){
            integralIntegratedQueryVo.setVip_Group_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            integralIntegratedQueryVo.setVip_Status(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            integralIntegratedQueryVo.setVip_Age(integralIntegratedQueryVo.getVip_Age());
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral(integralIntegratedQueryVo.getVip_Integral());
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral1(integralIntegratedQueryVo.getVip_Integral1());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0, Contstants.VIPOPERATETYPE.OPERATE5);
        params.add(1, integralIntegratedQueryVo.getCar_License() != null ? integralIntegratedQueryVo.getCar_License() : "");
        params.add(2, integralIntegratedQueryVo.getCar_Vin() != null ? integralIntegratedQueryVo.getCar_Vin() : "");
        params.add(3, integralIntegratedQueryVo.getVip_Name() != null ? integralIntegratedQueryVo.getVip_Name() : "");
        params.add(4, integralIntegratedQueryVo.getVip_Tel() != null ? integralIntegratedQueryVo.getVip_Tel() : "");
        params.add(5, integralIntegratedQueryVo.getVip_Number() != null ? integralIntegratedQueryVo.getVip_Number() : "");
        params.add(6, integralIntegratedQueryVo.getVip_Level_Id() != null ? integralIntegratedQueryVo.getVip_Level_Id() : "");
        params.add(7, integralIntegratedQueryVo.getVip_Group_Id() != null ? integralIntegratedQueryVo.getVip_Group_Id() : "");
        params.add(8, integralIntegratedQueryVo.getVip_Status() != null ? integralIntegratedQueryVo.getVip_Status() : "");
        params.add(9, integralIntegratedQueryVo.getVip_Age() != null ? integralIntegratedQueryVo.getVip_Age() : "");
        params.add(10, integralIntegratedQueryVo.getVip_Integral() != null ? integralIntegratedQueryVo.getVip_Integral() : "");
        params.add(11, integralIntegratedQueryVo.getVip_Integral1() != null ? integralIntegratedQueryVo.getVip_Integral1() : "");
        params.add(12, integralIntegratedQueryVo.getPage() != 0 ? integralIntegratedQueryVo.getPage() : 0);
        params.add(13, integralIntegratedQueryVo.getRows() != 0 ? integralIntegratedQueryVo.getRows() : 0);
        StringBuffer procedureName=new StringBuffer(" { CALL adjournment_find(?,?,?,?,?,?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> rlist = integralIntegratedQueryDao.getProcedureQuery(procedureName.toString(), params);
        int total = 0;
        Object[] obj = null;
        if(rlist != null && rlist.size() > 0){
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setGive_Inte(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[13]));}
                if(obj.length > 14){
                    total =  Integer.parseInt(obj[14].toString());
                }
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
	
	public Json getExitMemberFind(
			IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)
			throws Exception {
	    List<IntegralIntegratedQueryVo> list = new ArrayList<IntegralIntegratedQueryVo>();
        Json json = new Json();
        if(integralIntegratedQueryVo.getCar_License()!=null && !integralIntegratedQueryVo.getCar_License().equals("")){
            integralIntegratedQueryVo.setCar_License(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_License().trim()));
        }
        if(integralIntegratedQueryVo.getCar_Vin()!=null && !integralIntegratedQueryVo.getCar_Vin().equals("")){
            integralIntegratedQueryVo.setCar_Vin(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getCar_Vin().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Name()!=null && !integralIntegratedQueryVo.getVip_Name().equals("")){
            integralIntegratedQueryVo.setVip_Name(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Name().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Tel()!=null && !integralIntegratedQueryVo.getVip_Tel().equals("")){
            integralIntegratedQueryVo.setVip_Tel(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Tel().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Number()!=null && !integralIntegratedQueryVo.getVip_Number().equals("")){
            integralIntegratedQueryVo.setVip_Number(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Number().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Level_Id()!=null && !integralIntegratedQueryVo.getVip_Level_Id().equals("")){
            integralIntegratedQueryVo.setVip_Level_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Level_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Group_Id()!=null && !integralIntegratedQueryVo.getVip_Group_Id().equals("")){
            integralIntegratedQueryVo.setVip_Group_Id(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Group_Id().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Status()!=null && !integralIntegratedQueryVo.getVip_Status().equals("")){
            integralIntegratedQueryVo.setVip_Status(StringEscapeUtils.escapeSql(integralIntegratedQueryVo.getVip_Status().trim()));
        }
        if(integralIntegratedQueryVo.getVip_Age()!=null && !integralIntegratedQueryVo.getVip_Age().equals("")){
            integralIntegratedQueryVo.setVip_Age(integralIntegratedQueryVo.getVip_Age());
        }
        if(integralIntegratedQueryVo.getVip_Integral()!=null && !integralIntegratedQueryVo.getVip_Integral().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral(integralIntegratedQueryVo.getVip_Integral());
        }
        if(integralIntegratedQueryVo.getVip_Integral1()!=null && !integralIntegratedQueryVo.getVip_Integral1().trim().equals("")){
            integralIntegratedQueryVo.setVip_Integral1(integralIntegratedQueryVo.getVip_Integral1());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(0, Contstants.VIPOPERATETYPE.OPERATE6);
        params.add(1, integralIntegratedQueryVo.getCar_License() != null ? integralIntegratedQueryVo.getCar_License() : "");
        params.add(2, integralIntegratedQueryVo.getCar_Vin() != null ? integralIntegratedQueryVo.getCar_Vin() : "");
        params.add(3, integralIntegratedQueryVo.getVip_Name() != null ? integralIntegratedQueryVo.getVip_Name() : "");
        params.add(4, integralIntegratedQueryVo.getVip_Tel() != null ? integralIntegratedQueryVo.getVip_Tel() : "");
        params.add(5, integralIntegratedQueryVo.getVip_Number() != null ? integralIntegratedQueryVo.getVip_Number() : "");
        params.add(6, integralIntegratedQueryVo.getVip_Level_Id() != null ? integralIntegratedQueryVo.getVip_Level_Id() : "");
        params.add(7, integralIntegratedQueryVo.getVip_Group_Id() != null ? integralIntegratedQueryVo.getVip_Group_Id() : "");
        params.add(8, integralIntegratedQueryVo.getVip_Status() != null ? integralIntegratedQueryVo.getVip_Status() : "");
        params.add(9, integralIntegratedQueryVo.getVip_Age() != null ? integralIntegratedQueryVo.getVip_Age() : "");
        params.add(10, integralIntegratedQueryVo.getVip_Integral() != null ? integralIntegratedQueryVo.getVip_Integral() : "");
        params.add(11, integralIntegratedQueryVo.getVip_Integral1() != null ? integralIntegratedQueryVo.getVip_Integral1() : "");
        params.add(12, integralIntegratedQueryVo.getPage() != 0 ? integralIntegratedQueryVo.getPage() : 0);
        params.add(13, integralIntegratedQueryVo.getRows() != 0 ? integralIntegratedQueryVo.getRows() : 0);
        StringBuffer procedureName=new StringBuffer(" { CALL exit_menber_find(?,?,?,?,?,?,?,?,?,?,?,?,?,?) } ");
        List<Object[]> rlist = integralIntegratedQueryDao.getProcedureQuery(procedureName.toString(), params);
        int total = 0;
        if(rlist != null && rlist.size() > 0){
            Object[] obj = null;
            for (int i = 0; i < rlist.size(); i++) {
                obj = (Object[])rlist.get(i);
                IntegralIntegratedQueryVo vo = new IntegralIntegratedQueryVo();
                if(obj[0]!=null){vo.setVip_Rec_Id(obj[0].toString());}
                if(obj[1]!=null){vo.setVip_Id(obj[1].toString());}
                if(obj[2]!=null){vo.setVip_Number(obj[2].toString());}
                if(obj[3]!=null){vo.setCar_License(obj[3].toString());}
                if(obj[4]!=null){vo.setCar_Vin(obj[4].toString());}
                if(obj[5]!=null){vo.setVip_Name(obj[5].toString());}
                if(obj[6]!=null){vo.setVip_Tel(obj[6].toString());}
                //obj[7]会员状态
                if(obj[8]!=null){vo.setVip_Integral(obj[8].toString());}
                if(obj[9]!=null){vo.setVip_Level_Name(obj[9].toString());}
                if(obj[10]!=null){vo.setVip_Group_Name(obj[10].toString());}
                if(obj[11]!=null){vo.setVip_Age(obj[11].toString());}
                if(obj[12]!=null){vo.setGive_Inte(obj[12].toString());}
                if(obj[13]!=null){vo.setVip_Rec_Date(FormatTime.timestamp2Str((Timestamp) obj[13]));}
                if(obj.length > 14){
                    total =  Integer.parseInt(obj[14].toString());
                }
                list.add(vo);
            }
        }
        json.setTotal(total);
        json.setRows(list);
        return json;
	}
}